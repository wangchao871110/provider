package com.xfs.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsCompanyInfo;
import com.xfs.base.model.BsCompanyInfoDetail;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysTenantparamlist;
import com.xfs.common.http.HttpUtil;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.enums.SearchType;
import com.xfs.common.mongo.vo.SearchField;

@Service
public class BsCompanyInfoServiceImpl implements BsCompanyInfoService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsCompanyInfoService.class);

	@Autowired
	MongoDao mongoDao;

	@Autowired
	SysTenantparamlistService sysTenantparamlistService;
	
	@Autowired
	SysDictitemService sysDictitemService;
	
	@Autowired
	BdBusinessfieldService bdBusinessFieldService;
	
	@Override
	public BsCompanyInfo queryCompanyInfoByName(String name) {
		// TODO Auto-generated method stub
		BsCompanyInfo info;
		info = queryInMongoByName(name);
		if (info == null) {
			info = syncFromThirdAPI(name);
		}
		return info;
	}

	@Override
	public BsCompanyInfo queryCompanyInfoAllByName(String name) {
		// TODO Auto-generated method stub
		BsCompanyInfo info;
		info = queryCompanyInfoByName(name);
		if (info != null) {
			if (info.getXfsSearchStatus().equals(BsCompanyInfo.SearchStatus_Base)) {
				queryDetailFromThirdAPI(info);
			}
		}
		return info;
	}

//	@Override
//	private BsCompanyInfo queryCompanyInfoWithDetailByName(String name) {
//		// TODO Auto-generated method stub
//		BsCompanyInfo info;
//		info = queryCompanyInfoByName(name);
//		if (info != null) {
//			if (BsCompanyInfo.SearchStatus_Base.equals(info.getXfsSearchStatus())) {
//				queryDetailFromThirdAPI(info);
//				info.setXfsSearchStatus(BsCompanyInfo.SearchStatus_Finish);
//				saveBsCompanyInfoToMango(info);
//			}
//		}
//		return info;
//	}

	private BsCompanyInfo queryInMongoByName(String name) {
		SearchField namesearch = new SearchField("name", name, SearchType.Equal);
		List<SearchField> searchlist = new ArrayList<SearchField>(3);
		searchlist.add(namesearch);
		List<Map<String, Object>> resultlist = mongoDao.query(BsCompanyInfo.class.getSimpleName(), searchlist);
		if (resultlist != null && resultlist.size() > 0) {
			String jsonString = JSON.toJSONString(resultlist.get(0));
//			JSON json = JSON.parseObject(jsonString);
//			return JSON.toJavaObject(json, BsCompanyInfo.class);
			return JSON.parseObject(jsonString, BsCompanyInfo.class);
		}
		return null;
	}

	// mongo不能返回主键，所以同步不返回查询结果,后续再从mongo中重新读取
	private BsCompanyInfo syncFromThirdAPI(String name) {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("keyword", name);
		
		SysTenantparamlist url = sysTenantparamlistService.findByCode("corpAuth.searchlist.url");
		SysTenantparamlist key = sysTenantparamlistService.findByCode("corpAuth.searchlist.appkey");
		dataMap.put("appkey", key.getParamValue());
		JSONObject json = connectionFromQIXIN(url.getParamValue(), dataMap);
		BsCompanyInfo result = null;
		if (json != null) {
			List<Map> cpinfos = JSON.parseArray(json.getString("items"), Map.class);
			for (Map info : cpinfos) {
				BsCompanyInfo bci = queryInMongoByName((String) info.get("name"));
				if (bci == null) {
					bci = new BsCompanyInfo();
					bci.setQXId((String) info.get("id"));
					bci.setName((String) info.get("name"));
					bci.setOper_name((String)info.get("oper_name"));
					bci.setStart_date((String)info.get("start_date"));
					bci.setXfsSearchStatus(BsCompanyInfo.SearchStatus_Base);
					saveBsCompanyInfoToMango(bci);
				}
				if (bci.getName().equals(name)) {
					result = bci;
				}
			}
		}
		return result;
	}


	private void queryDetailFromThirdAPI(BsCompanyInfo info) {
		if (BsCompanyInfo.SearchStatus_Base.equals(info.getXfsSearchStatus())) {
			
			Map<String, Map<String, String>> parammap = new HashMap<>();
			List<SysTenantparamlist> params = sysTenantparamlistService.findByPartCode("corpAuth.");
				
			List<SysDictitem> ifs = sysDictitemService.findByDictName("corpAuth");
			if (ifs != null) {
				for (SysDictitem item : ifs) {
					if (!item.getCode().equals("searchlist")){
						parammap.put(item.getCode(), new HashMap<String, String>());
					}
				}
			}
			
			for (SysTenantparamlist param : params) {
				if (!param.getParamCode().startsWith("corpAuth.searchlist")) {
					String[] strs = param.getParamCode().split("\\.");
					Map<String,String> map = parammap.get(strs[1]);
					map.put(strs[2], param.getParamValue());
				}
			}
			List<BsCompanyInfoDetail> details = new ArrayList<BsCompanyInfoDetail>();
			info.setCompanyInfoDetails(details);
			for (String key : parammap.keySet()) {
				Map<String, String> param = parammap.get(key);
				String url = param.get("url");
				String appkey = param.get("appkey");
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("id", info.getQXId());
				dataMap.put("appkey", appkey);
				if (key.equals("detail")) {
					JSONObject json = connectionFromQIXIN(url, dataMap);
					if (json != null) {
						info.setContactJson(json.toString());
						info.setCreditNo((String) json.get("credit_no"));
						if (StringUtils.isEmpty(info.getCreditNo()) || info.getCreditNo().equals("-")) {
							info.setCreditNo((String) json.get("reg_no"));
						}
						info.setBusiScope((String) json.get("scope"));
						if (null != json.get("regist_capi")) {
							String capi = (String) json.get("regist_capi");
//							capi = capi.replace("人民币","");
//							capi = capi.replace(" ","");
//							capi = capi.replace("万", "0000");
//							capi = capi.replace("千", "000");
							info.setRegisteredCapital(capi);
						}
						info.setRegisteredGov((String) json.get("belong_org"));
						info.setCompanyType((String) json.get("econ_kind"));
					}
				}
				else {
					BdBusinessfield qry = new BdBusinessfield();
					qry.setSource("corpAuth." + key);
					List<BdBusinessfield> flds = bdBusinessFieldService.findAll(qry);
					Map<String, String> itemMap = new HashMap<>();
					if (flds != null) {
						for (BdBusinessfield fld : flds) {
							itemMap.put(fld.getCode(), fld.getName());
						}
					}
					details.addAll(queryDetailFromThirdAPI(info.getQXId(), appkey, url, key, 0, itemMap));
				}
			}
			info.setXfsSearchStatus(BsCompanyInfo.SearchStatus_Finish);
			saveBsCompanyInfoToMango(info);
		}
	}

	private List<BsCompanyInfoDetail> queryDetailFromThirdAPI(String id, String appkey, String url, String detailType, int skip,
			Map<String, String> itemMap) {
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("id", id);
		dataMap.put("pagesize", "100");
		dataMap.put("skip", String.valueOf(skip));
		dataMap.put("appkey", appkey);
		JSONObject json = connectionFromQIXIN(url, dataMap);
		List<BsCompanyInfoDetail> details = new ArrayList<BsCompanyInfoDetail>();
		if (null != json) {
			int total = 0;
			if (json.get("total") != null)
				total = (int) json.get("total");
			List<String> strs = JSON.parseArray(json.get("items").toString(), String.class);
			if (strs != null && strs.size() > 0) {
				int order = skip + 1;
				for (String str : strs) {
					BsCompanyInfoDetail detail = new BsCompanyInfoDetail();
					detail.setDetailType(detailType);
					detail.setOrder(order);
					detail.setJson(str);
					StringBuffer content = new StringBuffer();
					Map<String, String> items = JSON.parseObject(str, Map.class);
					if (items != null) {
						for (String key : items.keySet()) {
							String itemname = itemMap.get(key);
							if (itemname != null) {
								content.append(String.format("%s:%s ", itemname, items.get(key)));
							}
						}
						detail.setContent(content.toString());
					}
					details.add(detail);
					order++;
				}
			}
			if (total > (skip + 100)) {
				details.addAll(queryDetailFromThirdAPI(id, appkey, url, detailType, skip + 100, itemMap));
			}
		}
		return details;
	}

	private JSONObject connectionFromQIXIN(String url, Map<String, String> dataMap) {
		try {
			String json = HttpUtil.doPost(url, dataMap);
			Map<String, Object> result = JSON.parseObject(json, Map.class);
			String status = String.valueOf(result.get("status"));
			if ("200".equals(status)) {
				return (JSONObject)result.get("data");
			} else if ("201".equals(status)) {
				if(log.isDebugEnabled()){
					log.debug(String.format("启信查询无结果url:%s datamap:%s", url, dataMap));
				}
				return null;
			} else {
				if(log.isTraceEnabled()){
					log.error(String.format("启信%s status%s url:%s datamap:%s", result.get("message"), status, url, dataMap));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(log.isTraceEnabled()){
				log.error("启信查询失败", e);
			}
		}
		return null;
	}

	private void saveBsCompanyInfoToMango(BsCompanyInfo info) {
//		String _id = info.get_id();
//		if (StringUtils.isEmpty(_id)) {
//			info.set_id(UUIDUtil.randomUUID());
//		}
		String json = JSON.toJSONString(info);
		
		SearchField namesearch = new SearchField("name", info.getName(), SearchType.Equal);
		List<SearchField> searchlist = new ArrayList<SearchField>(3);
		searchlist.add(namesearch);
		List<Map<String, Object>> exists = mongoDao.query(BsCompanyInfo.class.getSimpleName(), searchlist);
		
		
		if (exists != null && exists.size() > 0) {
//			mongoDao.updateByPrimaryKey(BsCompanyInfo.class.getSimpleName(), json, info.get_id());
			mongoDao.delete(BsCompanyInfo.class.getSimpleName(), searchlist);
		}
		mongoDao.insert(BsCompanyInfo.class.getSimpleName(), json);

	}

}
