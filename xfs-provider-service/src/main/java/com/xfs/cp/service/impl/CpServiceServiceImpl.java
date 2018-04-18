package com.xfs.cp.service.impl;


import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsCompanyInfo;
import com.xfs.base.model.BsCompanyInfoDetail;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysTenantparamlist;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.BsCompanyInfoService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.base.service.SysTenantparamlistService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.http.HttpUtil;
import com.xfs.common.image.ImageMarkLogoUtil;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.enums.SearchType;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Config;
import com.xfs.common.util.Constant;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.FileUtil;
import com.xfs.cp.dao.CpOrderDao;
import com.xfs.cp.dao.CpServiceDao;
import com.xfs.cp.dao.CpServiceHistoryDao;
import com.xfs.cp.dto.UserDto;
import com.xfs.cp.model.CpArea;
import com.xfs.cp.model.CpBusiness;
import com.xfs.cp.model.CpBusinessIntroduction;
import com.xfs.cp.model.CpEvaluation;
import com.xfs.cp.model.CpOrder;
import com.xfs.cp.model.CpQuotedPrice;
import com.xfs.cp.model.CpService;
import com.xfs.cp.model.CpServiceHistory;
import com.xfs.cp.service.CpAreaService;
import com.xfs.cp.service.CpBusinessIntroductionService;
import com.xfs.cp.service.CpEvaluationService;
import com.xfs.cp.service.CpPackageService;
import com.xfs.cp.service.CpQuotedPriceService;
import com.xfs.cp.service.CpServiceService;
import com.xfs.cp.utils.ThumbnailConvert;
import com.xfs.mall.item.dao.BsMallItemCategoryDao;
import com.xfs.mall.item.model.BsMallItemCategory;
import com.xfs.mall.item.service.BsMallItemCategoryService;
import com.xfs.pay.wxpay.sign.MD5Util;
import com.xfs.sp.model.SpService;
import com.xfs.sp.service.SpServiceService;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.model.SysUser;

@Service
public class CpServiceServiceImpl implements CpServiceService {

	private static final Logger log = Logger.getLogger(CpServiceServiceImpl.class);
	
	@Autowired
	MongoDao mongoDao;
	@Autowired
	SysTenantparamlistService sysTenantparamlistService;
	@Autowired
	SysDictitemService sysDictitemService;
	@Autowired
	private CpServiceDao cpServiceDao;
	@Autowired
	BdBusinessfieldService bdBusinessFieldService;
	@Autowired
	private BsCompanyInfoService bsCompanyInfoService;
	@Autowired
	private BsMallItemCategoryDao categoryDao;
	@Autowired
	private CpOrderDao cpOrderDao;
	@Autowired
	private CpPackageService cpPackageService;
	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
	private BsMallItemCategoryService bsMallItemCategoryService;
	@Autowired
	private SpServiceService spServiceService;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private CpAreaService cpAreaService;
    @Autowired
    private CpServiceHistoryDao cpServiceHistoryDao;
    @Autowired
	private CpQuotedPriceService cpQuotedPriceService;
    @Autowired
	private CpBusinessIntroductionService cpBusinessIntroductionService;
    @Autowired
	private CpEvaluationService cpEvaluationService; // 合作满意度
    public int save(ContextInfo cti, CpService vo ){
		return cpServiceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpService vo ){
		return cpServiceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpService vo ){
		return cpServiceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpService vo ){
		return cpServiceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpService vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpServiceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpService> datas = cpServiceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpService> findAll(CpService vo){
		
		List<CpService> datas = cpServiceDao.freeFind(vo);
		return datas;
		
	}
	
	@Override
	public CpService getCpServiceById(CpService cpService) {
		return cpServiceDao.getCpServiceById(cpService);
	}
	
	@Override
	public CpService getCpServiceById(Integer sp_id) {
		CpService cpService = new CpService();
		cpService.setId(sp_id);
		return cpServiceDao.getCpServiceById(cpService);
	}

	@Override
	public int saveSpInfo(ContextInfo ci,CpService cpService) {
		int count=0;

		SpService spService=new SpService();
		spService.setSpId(cpService.getSaasSpId());
		spService.setSpName(cpService.getName());
		
		Result r = checkByQiXin(spService,cpService);
        if(r.isSuccess()){
        	cpServiceDao.updateSAASSp(ci,spService);
    		count=cpServiceDao.update(ci,cpService);
        }
		return count;
	}
	
	private Result checkByQiXin(SpService spService,CpService cpService) {
		Result r = Result.createResult();
		r.setSuccess(false);
		// 信用代码、公司名称不匹配 提示错误
		BsCompanyInfo info;
		try {
			info = bsCompanyInfoService.queryCompanyInfoAllByName(spService.getSpName());
		} catch (Exception e) {
			log.error("查询启信API异常", e);
			r.setError("信息检查失败，请联系客服!");
			return r;
		}

		if (info == null) {
			r.setError(String.format("未检索到%s，请填写正确的公司名称!", spService.getSpName()));
			return r;
		}
		/*if (!spService.getBusiLicenseNum().equals(info.getCreditNo())) {
			r.setError(String.format("信用代码与实际不符，请正确填写!", spService.getBusiLicenseNum(), spService.getSpName()));
			return r;
		}*/

		if (DateUtil.isValidDate(info.getStart_date())){
			spService.setRegisteredGov(info.getRegisteredGov());//登记机关
			spService.setEstablishedTime(DateUtil.parseDate(info.getStart_date(), "yyyy-MM-dd"));//成立日期
			spService.setRegisteredCapital(info.getRegisteredCapital());//注册资本
			spService.setBusiLicenseNum(info.getCreditNo());//统一社会信用代码
			/*组织机构代码*/
			spService.setComanyType(info.getCompanyType());//公司类型
			spService.setLegalPsn(info.getOper_name());//法定代表人
			/*营业状态*/
			/*公司网址*/
			spService.setBusiScope(info.getBusiScope());//经营范围
			/*客户编号*/
			
			cpService.setIsca(1);//登记机关

			cpService.setRegisteredGov(info.getRegisteredGov());//登记机关
			cpService.setEstablishedTime(DateUtil.parseDate(info.getStart_date(), "yyyy-MM-dd"));//成立日期
			cpService.setRegisteredCapital(info.getRegisteredCapital().replace(".000000", ""));//注册资本
			cpService.setBusiLicenseNum(info.getCreditNo());//统一社会信用代码
			
			JSONObject json = JSON.parseObject(info.getContactJson());
			
			cpService.setOrgCode(json.get("org_no").toString());/*组织机构代码*/
			cpService.setComanyType(info.getCompanyType());//公司类型
			cpService.setLegalPsn(info.getOper_name());//法定代表人
			cpService.setBusiStasus(json.get("status").toString());/*营业状态*/
			
		    JSONArray jsonArray = JSONArray.parseArray(json.get("websites").toString());
		    if(jsonArray.size()>0){
		    	if(jsonArray.getJSONObject(0).getString("web_url")!=null){
		    		cpService.setWeburl(jsonArray.getJSONObject(0).getString("web_url").toString());/*公司网址*/
		    	}
		    }
			
			cpService.setBusiScope(info.getBusiScope());//经营范围
			/*客户编号*/
			
			JSONArray partnersJson = JSONArray.parseArray(json.get("partners").toString());
			String partners="";
			for (int i = 0; i < partnersJson.size(); i++) {
				partners+=","+partnersJson.getJSONObject(i).getString("name").toString();
			}
			if(partners.length()>0){
				partners=partners.substring(1, partners.length());
			}
			
			/*股东结构 partners*/
			cpService.setPartners(partners);
			/*注册地址 addresses_register*/
			cpService.setAddressesRegister(json.get("address").toString());
			/*成立日期 start_date*/
		    cpService.setStartDate(info.getStart_date());
			/*发照日期 check_date*/
			cpService.setCheckDate(json.get("check_date").toString());
		}
		
		r.setSuccess(true);
		return r;

	}
	
	
	public BsCompanyInfo queryCompanyInfoAllByName(String name) {
		BsCompanyInfo info;
		info = queryCompanyInfoByName(name);
		if (info != null) {
			if (info.getXfsSearchStatus().equals(BsCompanyInfo.SearchStatus_Base)) {
				queryDetailFromThirdAPI(info);
			}
		}
		return info;
	}

	public BsCompanyInfo queryCompanyInfoByName(String name) {
		BsCompanyInfo info;
		info = queryInMongoByName(name);
		if (info == null) {
			info = syncFromThirdAPI(name);
		}
		return info;
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
	
	private void saveBsCompanyInfoToMango(BsCompanyInfo info) {
		String json = JSON.toJSONString(info);
		
		SearchField namesearch = new SearchField("name", info.getName(), SearchType.Equal);
		List<SearchField> searchlist = new ArrayList<SearchField>(3);
		searchlist.add(namesearch);
		List<Map<String, Object>> exists = mongoDao.query(BsCompanyInfo.class.getSimpleName(), searchlist);
		
		
		if (exists != null && exists.size() > 0) {
			mongoDao.delete(BsCompanyInfo.class.getSimpleName(), searchlist);
		}
		mongoDao.insert(BsCompanyInfo.class.getSimpleName(), json);

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
			if(log.isTraceEnabled()) {
				log.error("启信查询失败", e);
			}
		}
		return null;
	}
	
	private BsCompanyInfo queryInMongoByName(String name) {
		SearchField namesearch = new SearchField("name", name, SearchType.Equal);
		List<SearchField> searchlist = new ArrayList<SearchField>(3);
		searchlist.add(namesearch);
		List<Map<String, Object>> resultlist = mongoDao.query(BsCompanyInfo.class.getSimpleName(), searchlist);
		if (resultlist != null && resultlist.size() > 0) {
			String jsonString = JSON.toJSONString(resultlist.get(0));
			return JSON.parseObject(jsonString, BsCompanyInfo.class);
		}
		return null;
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
	
	@Override
	public List<BsMallItemCategory> getAllBusiness() {
		return cpServiceDao.getAllBusiness();
	}
	@Override
	public List<CpBusiness> getBusinessById(CpService cpService) {
		return cpServiceDao.getBusinessById(cpService);
	}
	@Override
	public CpService getSpLeftBaseInfo(CpService cpServiceLeft) {
		return cpServiceDao.getSpLeftBaseInfo(cpServiceLeft);
	}
	@Override
	public PageModel findRankingPage(PageInfo pi,CpService cpService, int pageIndex, int pageSize) {
		PageModel pm = new PageModel(pi);
		try {
			if(!"".equals(cpService.getStaDate()) && !"".equals(cpService.getEndDate())){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				if("0".equals(cpService.getStaDate()) && "3".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -3);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("3".equals(cpService.getStaDate()) && "5".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -5);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.add(Calendar.YEAR, -3);
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("5".equals(cpService.getStaDate()) && "10".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -10);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.add(Calendar.YEAR, -5);
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("10".equals(cpService.getStaDate()) && "-1".equals(cpService.getEndDate())){
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -10);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String last = format.format(c.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
			}
			// 获取服务商
			cpService.setName(null==cpService.getName()?null:URLDecoder.decode(cpService.getName(), "UTF-8"));
			cpService.setDr(0);
			Integer total = cpServiceDao.findRankingPageCount(cpService);
			pm.setPagesize(pageSize);
			pm.setTotal(total);
			List<CpService> datas = cpServiceDao.findRankingPage(cpService, pageIndex, pageSize);
			for (int i = 0; i < datas.size(); i++) {
				CpOrder cpOrder = cpOrderDao.findPriceBySpId(datas.get(i).getId(),null,null);
				datas.get(i).setPrice(null == cpOrder.getPrice()?0:new BigDecimal(cpOrder.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				//datas.get(i).setServiceArea(cpAreaDao.findNameInId(datas.get(i).getServiceArea()));
				datas.get(i).setCategoryId(categoryDao.findNameBySpId(datas.get(i).getId()));
				String cpaddrss = (null != datas.get(i).getCpAddress()&&!"".equals(datas.get(i).getCpAddress()))
											?datas.get(i).getCpAddress().split(",")[1].split("-")[1]:"";
				// 服务区域
				if(null !=  datas.get(i).getServiceArea()){
					String[] serviceAreaArrey = datas.get(i).getServiceAreaName().split(",");
					if (serviceAreaArrey.length > 0) {
						String serviceArea = "";
						for (int j = 0; j < serviceAreaArrey.length; j++) {
							String[] aStrings = serviceAreaArrey[j].split("-");
							if(aStrings.length > 1){
								if(!serviceArea.contains(aStrings[1])){
									serviceArea += aStrings[1]+",";
								}
							}
						}
						if(!"".equals(serviceArea) || !"".equals(cpaddrss)){
							datas.get(i).setServiceAreaName(serviceArea.contains(cpaddrss)?serviceArea.substring(0, serviceArea.lastIndexOf(",")):serviceArea+cpaddrss);
						}
					}else{
						datas.get(i).setServiceAreaName(cpaddrss);
					}
				}else{
					datas.get(i).setServiceAreaName(cpaddrss);
				}
			}
			
//			Collections.sort(datas, new Comparator<CpService>() {
//				@Override
//				public int compare(CpService o1, CpService o2) {
//					return o1.getAllRanking().compareTo(o2.getAllRanking());
//				}
//			});
			
			pm.setDatas(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	
	@Override
	public PageModel findH5RankingPage(PageInfo pi, CpService cpService) {
		PageModel pm = new PageModel(pi);
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			if(!"".equals(cpService.getStaDate()) && !"".equals(cpService.getEndDate())){
				if("0".equals(cpService.getStaDate()) && "3".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -3);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("3".equals(cpService.getStaDate()) && "5".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -5);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.add(Calendar.YEAR, -3);
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("5".equals(cpService.getStaDate()) && "10".equals(cpService.getEndDate())){
					//获取当前月第一天：
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -10);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String first = format.format(c.getTime());
		            cpService.setStartTime(first+" 00:00:00");
		            //获取当前月最后一天
		            Calendar ca = Calendar.getInstance();    
		            ca.add(Calendar.YEAR, -5);
		            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		            String last = format.format(ca.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
				if("10".equals(cpService.getStaDate()) && "-1".equals(cpService.getEndDate())){
		            Calendar c = Calendar.getInstance();
		            c.add(Calendar.YEAR, -10);
		            c.add(Calendar.MONTH, 0);
		            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		            String last = format.format(c.getTime());
		            cpService.setEndTime(last+" 23:59:59");
				}
			}
			// 获取服务商
			cpService.setName(null==cpService.getName()?null:URLDecoder.decode(cpService.getName(), "UTF-8"));
			if(null == cpService.getOrderBy() ){
				cpService.setOrderBy("all_ranking asc");
			}
			// 主营业务拼音
			if(null != cpService.getCategoryPinyin() && !"0".equals(cpService.getCategoryPinyin()) && !"".equals(cpService.getCategoryPinyin())){
				BsMallItemCategory bsMallItemCategory = bsMallItemCategoryService.getCategoryByPinyin(cpService.getCategoryPinyin());
				cpService.setCategoryId(bsMallItemCategory.getCategoryId().toString());
			}
			// 区域拼音
			if(null != cpService.getAreaPinyin() && !"0".equals(cpService.getAreaPinyin()) && !"".equals(cpService.getAreaPinyin())){
				CpArea cpArea = cpAreaService.getAreaByPinyin(cpService.getAreaPinyin());
				cpService.setServiceArea(cpArea.getId().toString());
			}
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            if((cpService.getYears()==null || cpService.getYears()==calendar.get(Calendar.YEAR))
            		&& (cpService.getMonth()==null || cpService.getMonth()==(calendar.get(Calendar.MONTH)+1))) {
                Integer total = cpServiceDao.findRankingPageCount(cpService);
                int pageIndex = pi.getOffset();
                int pageSize = pi.getPagesize();
                pm.setTotal(total);
                List<CpService> datas = cpServiceDao.findRankingPage(cpService, pageIndex, pageSize);
                calendar.add(Calendar.DAY_OF_MONTH, -15);
                for (int i = 0; i < datas.size(); i++) {
                    int packageNumber = cpPackageService.findPackageNumber(datas.get(i).getId(), calendar.getTime(), new Date());
                    datas.get(i).setOrderNumber(packageNumber);
                    datas.get(i).setCategoryId(categoryDao.findNameBySpId(datas.get(i).getId()));
                    String cpaddrss = (null != datas.get(i).getCpAddress() && !"".equals(datas.get(i).getCpAddress()))
                            ? datas.get(i).getCpAddress().split(",")[1].split("-")[1] : "";
                    // 服务区域
                    if (null != datas.get(i).getServiceArea()) {
                        String[] serviceAreaArrey = datas.get(i).getServiceAreaName().split(",");
                        if (serviceAreaArrey.length > 0) {
                            String serviceArea = "";
                            for (int j = 0; j < serviceAreaArrey.length; j++) {
                                String[] aStrings = serviceAreaArrey[j].split("-");
                                if (aStrings.length > 1) {
                                    if (!serviceArea.contains(aStrings[1])) {
                                        serviceArea += aStrings[1] + ",";
                                    }
                                }
                            }
                            if (!"".equals(serviceArea) || !"".equals(cpaddrss)) {
                                datas.get(i).setServiceAreaName(serviceArea.contains(cpaddrss) ? serviceArea.substring(0, serviceArea.lastIndexOf(",")) : serviceArea + cpaddrss);
                            }
                        } else {
                            datas.get(i).setServiceAreaName(cpaddrss);
                        }
                    } else {
                        datas.get(i).setServiceAreaName(cpaddrss);
                    }
                }

//			Collections.sort(datas, new Comparator<CpService>() {
//				@Override
//				public int compare(CpService o1, CpService o2) {
//					return o1.getAllRanking().compareTo(o2.getAllRanking());
//				}
//			});

                pm.setDatas(datas);
            }else {
                    CpServiceHistory cpServiceHistory = new CpServiceHistory();
                    cpServiceHistory = cpService;
                    cpServiceHistory.setCpServiceId(cpService.getId());
                    cpServiceHistory.setId(null);
                    Integer total = cpServiceHistoryDao.findRankingPageCount(cpServiceHistory);
                    int pageIndex = pi.getOffset();
                    int pageSize = pi.getPagesize();
                    pm.setTotal(total);
                    List<CpServiceHistory> datas = cpServiceHistoryDao.findRankingPage(cpServiceHistory, pageIndex, pageSize);
                    calendar.add(Calendar.DAY_OF_MONTH, -15);
                    for (int i = 0; i < datas.size(); i++) {
                        int packageNumber = cpPackageService.findPackageNumber(datas.get(i).getId(), calendar.getTime(), new Date());
                        datas.get(i).setOrderNumber(packageNumber);

                        datas.get(i).setCategoryId(categoryDao.findNameBySpId(datas.get(i).getId()));
                        String cpaddrss = (null != datas.get(i).getCpAddress() && !"".equals(datas.get(i).getCpAddress()))
                                ? datas.get(i).getCpAddress().split(",")[1].split("-")[1] : "";

                        // 服务区域
                        if (null != datas.get(i).getServiceArea()) {
                            String[] serviceAreaArrey = datas.get(i).getServiceAreaName().split(",");
                            if (serviceAreaArrey.length > 0) {
                                String serviceArea = "";
                                for (int j = 0; j < serviceAreaArrey.length; j++) {
                                    String[] aStrings = serviceAreaArrey[j].split("-");
                                    if (aStrings.length > 1) {
                                        if (!serviceArea.contains(aStrings[1])) {
                                            serviceArea += aStrings[1] + ",";
                                        }
                                    }
                                }
                                if (!"".equals(serviceArea) || !"".equals(cpaddrss)) {
                                    datas.get(i).setServiceAreaName(serviceArea.contains(cpaddrss) ? serviceArea.substring(0, serviceArea.lastIndexOf(",")) : serviceArea + cpaddrss);
                                }
                            } else {
                                datas.get(i).setServiceAreaName(cpaddrss);
                            }
                        } else {
                            datas.get(i).setServiceAreaName(cpaddrss);
                        }
                    }
                    pm.setDatas(datas);
                }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	@Override
	public List<Map<String, Object>> getBusinessAById(CpService cpService) {
		return cpServiceDao.getBusinessAById(cpService);
	}
	@Override
	public List<Map<String, Object>> spSpecialBusinessList(CpService cpService) {
		return cpServiceDao.spSpecialBusinessList(cpService);
	}
	@Override
	public List<Map<String, Object>> getBusinessBById(Map<String, Object> content_id) {
		return cpServiceDao.getBusinessBById(content_id);
	}
	@Override
	public List<Map<String, Object>> getBusinessBByCategoryId(Map<String, Object> whereMap) {
		return cpServiceDao.getBusinessBByCategoryId(whereMap);
	}
	public CpService findCpServiceDetailById(CpService cpService) {
		return cpServiceDao.findCpServiceDetailById(cpService);
	}
	@Override
	public Integer findOrderById(CpService cpService) {
		return cpServiceDao.findOrderById(cpService);
	}
	@Override
	public Double getEstablishedAvgByCpId(Integer cpId) {
		return cpServiceDao.getEstablishedAvgByCpId(cpId);
	}
	@Override
	public Double getRateBusinessByCpId(Integer cpId) {
		return cpServiceDao.getRateBusinessByCpId(cpId);
	}
	@Override
	public Double getRateServiceAreaByCpId(Integer cpId) {
		return cpServiceDao.getRateServiceAreaByCpId(cpId);
	}
	@Override
	public Double getTransactionPriceByCpId(Integer cpId) {
		return cpServiceDao.getTransactionPriceByCpId(cpId);
	}
	@Override
	public int getReciveOrderByCpId(Integer cpId) {
		return cpServiceDao.getReciveOrderByCpId(cpId);
	}
	@Override
	public Double getRateBrowseNumByCpId(Integer cpId) {
		return cpServiceDao.getRateBrowseNumByCpId(cpId);
	}
	@Override
	public Double getRateAttentionNumByCpId(Integer cpId) {
		return cpServiceDao.getRateAttentionNumByCpId(cpId);
	}
	@Override
	public Double getRateIndustryNumByCpId(Integer cpId) {
		return cpServiceDao.getRateIndustryNumByCpId(cpId);
	}
	@Override
	public Double getEfficiencyNumByCpId(Integer cpId) {
		return cpServiceDao.getEfficiencyNumByCpId(cpId);
	}
	@Override
	public Double getAttitudeNumByCpId(Integer cpId) {
		return cpServiceDao.getAttitudeNumByCpId(cpId);
	}
	@Override
	public Double getPowerNumByCpId(Integer cpId) {
		return cpServiceDao.getPowerNumByCpId(cpId);
	}
	@Override
	public Double getProfessionNumByCpId(Integer cpId) {
		return cpServiceDao.getProfessionNumByCpId(cpId);
	}
	@Override
	public List<Map<String, Object>> getProvinceList() {
		return cpServiceDao.getProvinceList();
	}
	@Override
	public List<Map<String, Object>> getCityList() {
		return cpServiceDao.getCityList();
	}
	@Override
	public List<Map<String, Object>> getAreaList() {
		return cpServiceDao.getAreaList();
	}
	@Override
	public List<Map<String, Object>> getCityByFather(Map<String, Object> whereMap) {
		return cpServiceDao.getCityByFather(whereMap);
	}
	@Override
	public List<Map<String, Object>> getAreaByFather(Map<String, Object> whereMap) {
		return cpServiceDao.getAreaByFather(whereMap);
	}
	@Override
	public Double getRateRegisteredCapitalByCpId(Integer cpId) {
		return cpServiceDao.getRateRegisteredCapitalByCpId(cpId);
	}

	/**
	 * 获取同城前两名服务机构
	 */
	@Override
	public List<CpService> findCpServiceByCity(String city, Integer id) {
		return cpServiceDao.findCpServiceByCity(city, id);
	}
	/**
	 * 获取首页数据
	 */
	@Override
	public List<CpService> findIndexList(CpService cpService, int pageIndex, int pageSize) {
		cpService.setDr(0);
		List<CpService> datas = cpServiceDao.findRankingPage(cpService, pageIndex, pageSize);
		for (int i = 0; i < datas.size(); i++) {
			//CpOrder cpOrder = cpOrderDao.findPriceBySpId(datas.get(i).getId(),null,null);
			//datas.get(i).setPrice(null == cpOrder.getPrice()?0:cpOrder.getPrice());
			//datas.get(i).setServiceArea(cpAreaDao.findNameInId(datas.get(i).getServiceArea()));
			datas.get(i).setCategoryId(categoryDao.findNameBySpId(datas.get(i).getId()));
			String cpaddrss = (null != datas.get(i).getCpAddress()&&!"".equals(datas.get(i).getCpAddress()))
										?datas.get(i).getCpAddress().split(",")[1].split("-")[1]:"";
			// 服务区域
			if(null !=  datas.get(i).getServiceArea()){
				String[] serviceAreaArrey = datas.get(i).getServiceAreaName().split(",");
				if (serviceAreaArrey.length > 0) {
					String serviceArea = "";
					for (int j = 0; j < serviceAreaArrey.length; j++) {
						String[] aStrings = serviceAreaArrey[j].split("-");
						if(aStrings.length > 1){
							if(!serviceArea.contains(aStrings[1])){
								serviceArea += aStrings[1]+",";
							}
						}
					}
					if(!"".equals(serviceArea) || !"".equals(cpaddrss)){
						datas.get(i).setServiceAreaName(serviceArea.contains(cpaddrss)?serviceArea.substring(0, serviceArea.lastIndexOf(",")):serviceArea+cpaddrss);
					}
				}else{
					datas.get(i).setServiceAreaName(cpaddrss);
				}
			}else{
				datas.get(i).setServiceAreaName(cpaddrss);
			}
		}
		return datas;
	}




	/**
	 * 上传
     */
	public Result uploadImage(CpService pService, ContextInfo user, MultipartHttpServletRequest multiRequest) {
		Result result = Result.createResult().setSuccess(false);
		String rootPath = Config.getProperty("fileRootPath");
		String curDate = getDateStrByMonth();
		String filePath = rootPath + File.separator + curDate;
		// 第一步 上传 图片
		Iterator<String> iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile onefile = multiRequest.getFile(iter.next());// 由CommonsMultipartFile继承而来,拥有上面的方法.
			if (onefile != null) {
				String saveName = onefile.getOriginalFilename();
				String fileName = saveName;
				saveName = generateFileName(saveName);// 获取一个时间格式的名称（微信中为简易解决中文名称问题）
				File localFile = new File(filePath, saveName);
				if (!localFile.exists()) {
					localFile.getParentFile().mkdirs();
				} else {
					localFile = getUniqueFile(filePath, saveName);// 校验文件名重复
					saveName = localFile.getName();
				}
				if (!localFile.getName().toLowerCase().endsWith(".jpg")
                        && !localFile.getName().toLowerCase().endsWith(".png")
                        && !localFile.getName().toLowerCase().endsWith(".jpeg")
                        && !localFile.getName().toLowerCase().endsWith(".bmp")) {
					result.setError("请上传正确格式图片");
					return result;
				}
				try {
//				   File srcImageFileGood = new File(filePath);  
//				   File srcImageFile = new File(filePath);
//				   JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(srcImageFile);  
//				   BufferedImage image = decoder.decodeAsBufferedImage();  
//				   ImageIO.write(image, "JPEG", srcImageFileGood);  
					// 将上传的文件写入新建的文件中
					onefile.transferTo(localFile);
					BufferedImage oneImg = null; 
					// 原图片的宽和高
					int width = 0;
					int height = 0;
					try{ 
						oneImg = ImageIO.read(localFile); 
						width = oneImg.getWidth(null); 
						height = oneImg.getWidth(null); 
					}catch(Exception e){ 
					    try{ 
					    	ThumbnailConvert tc = new ThumbnailConvert(); 
						    tc.setCMYK_COMMAND(localFile.getPath()); 
						    Image image =null; 
						    image = Toolkit.getDefaultToolkit().getImage(localFile.getPath()); 
						    MediaTracker mediaTracker = new MediaTracker(new Container()); 
						    mediaTracker.addImage(image, 0); 
						    mediaTracker.waitForID(0); 
						    width = image.getWidth(null); 
						    height = image.getHeight(null); 
					    }catch (Exception e1){ 
					    	log.error(e1);
					    } 
					} 
					// 原图片的宽和高
					//int width = oneImg.getWidth(null);
					//int height = oneImg.getHeight(null);
					// 第一次上传原图
					String imageString = AliyunImageUtil.uploadPicture(localFile, "images/cp/");
					if (StringUtils.isBlank(imageString)) {
						result.setError("图片上传失败！");
						break;
					}
					FileUtil f = new FileUtil();
					String fileSize = f.getFileSizes(localFile);
					// 保存到数据库
					SysUploadfile sysUploadfile = new SysUploadfile();
					sysUploadfile.setFilename(fileName);
					sysUploadfile.setSavename(imageString);
					sysUploadfile.setFilepath(curDate);
					sysUploadfile.setFilesize(fileSize);
					if(user!=null){
						sysUploadfile.setCreateuser(user.getUserId());
					}
					sysUploadfileService.save(user,sysUploadfile);
					// 原图的ID 暂时不返回到页面
					result.setDataValue("imageid", sysUploadfile.getId());
					// 创建一张白色图片 格式为png
					int bgw = width * 2;
					int bgh = height * 2;
					BufferedImage bgimages = new BufferedImage(bgw, bgh, BufferedImage.TYPE_INT_RGB);
					String bgPathFileName = filePath + File.separator + "whiteimage" + UUID.randomUUID() + ".jpg";
					Graphics2D bgg = bgimages.createGraphics();
					bgg.setColor(Color.white);
					bgg.fillRect(0, 0, bgw, bgh);
					bgg.drawRect(0, 0, bgw, bgh);
					bgg.dispose();
					// 保存白色背景大图
					File bgFile = new File(bgPathFileName);
					ImageIO.write(bgimages, "jpg", bgFile);
					// 背景图片转换为JPEG、JPG文件
					// FileOutputStream bgOut = new FileOutputStream(bgFile);
					// JPEGImageEncoder encoder =
					// JPEGCodec.createJPEGEncoder(bgOut);
					// encoder.encode(bgimages);
					// bgOut.close();

					// 原图片转换为JPEG、JPG文件
					// FileOutputStream oneOut = new
					// FileOutputStream(localFile);
					// JPEGImageEncoder oneencoder =
					// JPEGCodec.createJPEGEncoder(oneOut);
					// oneencoder.encode(onesImg);
					// oneOut.close();

					// x,y 表示原图 在背景图中原图的坐标位置
					int x = (int) (bgw * 0.5);
					int y = (int) (bgh * 0.5);
					// BufferedImage markImage = ImageIO.read(bgFile);
					int newx = (int) (width * 0.5);
					int newy = (int) (height * 0.5);
					Graphics2D g = bgimages.createGraphics();
					g.drawImage(oneImg, x - newx, y - newy, width, height, null);
					g.dispose();
					String newPathFileName = filePath + File.separator + "newimage" + curDate + ".jpg";
					FileOutputStream out = new FileOutputStream(newPathFileName);
					out.close();
					File newFile = new File(newPathFileName);
					ImageIO.write(bgimages, "jpg", newFile);

					// 上传处理后的新图
					String newimageString = AliyunImageUtil.uploadPicture(newFile, "images/cp/");
					if (StringUtils.isBlank(newimageString)) {
						// 保存失败
						result.setError("图片上传失败！");
						break;
					}
					FileUtil f2 = new FileUtil();
					String newfileSize = f2.getFileSizes(newFile);
					// 保存到数据库
					SysUploadfile newsysUploadfile = new SysUploadfile();
					newsysUploadfile.setFilename(fileName);
					newsysUploadfile.setSavename(newimageString);
					newsysUploadfile.setFilepath(curDate);
					newsysUploadfile.setFilesize(newfileSize);
					if(user!=null){
						newsysUploadfile.setCreateuser(user.getUserId());
					}
					sysUploadfileService.save(user,newsysUploadfile);
					result.setDataValue("newimageid", newsysUploadfile.getId());
					result.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result.setError("上传失败！");
			}
		}
		return result;
	}

	/**
	 * 获得一个以时间格式的新名称
	 *
	 * @param fileName
	 *            原图名称
	 * @return
	 */
	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 如果上传文件名重复，处理文件名 
	 *
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getUniqueFile(String path, String fileName) {
		String pname = fileName.substring(0, fileName.lastIndexOf('.'));
		String suffix = fileName.substring(fileName.lastIndexOf('.'));
		int i = 1;
		while (true) {
			String tempName = pname + i + suffix;
			File tf = new File(path, tempName);
			if (!tf.exists())
				return tf;
			i++;
		}
	}

	/**
	 * 得到年月yyyyMM格式的字符串，供生成生成上传目录使用
	 *
	 * @return
	 */
	public static String getDateStrByMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(Calendar.getInstance().getTime());
	}

	public Result cutImage(String fileId, int x, int y, int width, int height,ContextInfo user, boolean flag) {
		Result result = Result.createResult().setSuccess(false);
		if (!StringUtils.isEmpty(fileId)) {
			String oldFilePic = sysUploadfileService.getPhysicsFile(Integer.parseInt(fileId));
			String newFilePath = oldFilePic.substring(0, oldFilePic.lastIndexOf(File.separator));
			String lastfilePath = newFilePath + UUID.randomUUID() + ".jpg";
			String marklastfile = newFilePath + UUID.randomUUID() + "mark.jpg";

			// 图片转换为JPEG、JPG文件
			try {
				File file = new File(oldFilePic);
				File destFile = new File(lastfilePath);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdir();
				}
				BufferedImage src = ImageIO.read(file); // 读入文件
				Image image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
				BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(image, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				ImageIO.write(tag, "jpg", new FileOutputStream(destFile));// 输出到文件流
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 根据ID 取图片 实体
			String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
			// String newFilePath = fileRootPath + File.separator;
			// 去剪裁图片 返回图片名字
			String fileName = cutImage(lastfilePath, newFilePath, x, y, width, height);
			// 本地创建图片
			File file ;
			// 加水印
			if (flag) {
				String src = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "watermark" + File.separator + "watermark.png";
				ImageMarkLogoUtil.markImageByIcon(src, newFilePath+ File.separator +fileName, marklastfile, 0, 0);
				file = new File(marklastfile);
			}else{
				file = new File(newFilePath, fileName);
			}
			FileUtil f = new FileUtil();
			String fileSize;
			try {
				fileSize = f.getFileSizes(file);
				String curDate = getDateStrByMonth();

				// 存阿里云
				String imageString = AliyunImageUtil.uploadPicture(file, "images/cp/");
				// 保存到数据库
				SysUploadfile sysUploadfile = new SysUploadfile();
				sysUploadfile.setFilename(fileName);
				sysUploadfile.setSavename(imageString);
				sysUploadfile.setFilepath(curDate);
				sysUploadfile.setFilesize(fileSize);
				if(null != user){
					sysUploadfile.setCreateuser(user.getUserId());
				}
				sysUploadfileService.save(user,sysUploadfile);
				// file.delete();
				// oldfile.delete();
				result.setSuccess(true);
				result.setDataValue("imageId", sysUploadfile.getId());
			} catch (Exception e) {
				result.setError("图片保存失败！");
				e.printStackTrace();
			}
		}
		return result;
	}

	public String cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
		return cutImage(new File(srcImg), destImg, new Rectangle(x, y, width, height));
	}

	public String cutImage(File srcImg, String destImgPath, Rectangle rect) {
		File destImg = new File(destImgPath);
		String DEFAULT_CUT_PREVFIX = "cut";
		String newpicFilePath = "";
		if (destImg.exists()) {
			String p = destImg.getPath();
			try {
				if (!destImg.isDirectory())
					p = destImg.getParent();
				if (!p.endsWith(File.separator))
					p = p + File.separator;
				// 拿到剪裁后的图片路径 不要根目录
				newpicFilePath = DEFAULT_CUT_PREVFIX + "_" + new Date().getTime() + "_" + srcImg.getName();
				cutImage(srcImg, new FileOutputStream(
						p + DEFAULT_CUT_PREVFIX + "_" + new Date().getTime() + "_" + srcImg.getName()), rect);
				//
			} catch (FileNotFoundException e) {
				// log.warn("the dest image is not exist.");
				newpicFilePath = "";
			}
		}
		return newpicFilePath;
	}


	public static void cutImage(File srcImg, OutputStream output, Rectangle rect) {
		if (srcImg.exists()) {
			FileInputStream fis = null;
			ImageInputStream iis = null;
			try {

				fis = new FileInputStream(srcImg);
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png,
				// PNG,JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
				String suffix = null;
				// 获取图片后缀 img_path.substring(img_path.lastIndexOf(".") +
				// 1).trim().toLowerCase();
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
				} // 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
					// log.error("Sorry, the image suffix is illegal. the
					// standard image suffix is {}." + types);
					//
					return;
				}
				// 将FileInputStream 转换为ImageInputStream
				iis = ImageIO.createImageInputStream(fis);
				// 根据图片类型获取该种类型的ImageReader
				ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				param.setSourceRegion(rect);
				// 读取 异常
				BufferedImage bi = reader.read(0, param);
				ImageIO.write(bi, suffix, output);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
					if (iis != null)
						iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			// log.warn("the src image is not exist.");
		}
	}

	/**
	 * 从mongo查询出来图片 查看图片
	 */
//	public GridFSDBFile readFileById(String fileId) {
//		SysUploadfile sysupload = new SysUploadfile();
//		sysupload.setId(Integer.parseInt(fileId));
//		SysUploadfile sysup = sysUploadfileService.findByPK(sysupload);
//		GridFSDBFile dbFile = mongoDao.readFileById(sysup.getSavename());
//		return dbFile;
//	}
	@Override
	public Double getReciveOrderRateByCpId(Integer cpId) {
		return cpServiceDao.getReciveOrderRateByCpId(cpId);
	}
	@Override
	public int getComplainNumByCpId(Integer cpId) {
		return cpServiceDao.getComplainNumByCpId(cpId);
	}
	@Override
	public int getCpServiceExpId(CpService cpService) {
		return cpServiceDao.getCpServiceExpId(cpService);
	}
	@Override
	public Integer getCreatPackageCount(Integer cpId) {
		return cpServiceDao.getCreatPackageCount(cpId);
	}
	@Override
	public Double getRateCreatPackageCount(Integer cpId) {
		return cpServiceDao.getRateCreatPackageCount(cpId);
	}
	@Override
	public Double getRiskMarginOfSpsMallInfo(Integer cpId) {
		return cpServiceDao.getRiskMarginOfSpsMallInfo(cpId);
	}
	@Override
	public Integer getCpCustomerPhoneCount(Integer cpId) {
		return cpServiceDao.getCpCustomerPhoneCount(cpId);
	}
	@Override
	public Integer updateBrowseNum(ContextInfo ci,CpService cps) {
		return cpServiceDao.updateBrowseNum(ci,cps);
	}
	@Override
	public List<Map<String, Object>> getCityByName(String cityOfCpAddress) {
		return cpServiceDao.getCityByName(cityOfCpAddress);
	}
	@Override
	public List<Map<String, Object>> FindCpServiceMap(CpService obj) {
		return cpServiceDao.FindCpServiceMap(obj);
	}

	/**
	 * 注册
	 */
	@Override
	public SysUser registUser(UserDto userDto) {
		try {
			SpService spService = new SpService();
			spService.setSpName(userDto.getSpName());		// 服务商名称
			spService.setServiceState("UNAUDITED");			// 服务商类型
			spService.setMobile(userDto.getMobile());		// 手机号
			spService.setEnabledMall("UNSIGN");				// 是否开通商城
			spService.setCreateDt(new Date());				// 创建时间
			spServiceService.save(null, spService);

			CpService cpService = new CpService();
			cpService.setId(spService.getSpId());
			cpService.setSaasSpId(spService.getSpId());
			cpService.setName(userDto.getSpName());
			cpService.setMobile(userDto.getMobile());
			cpService.setDevIndex(0.0);
			cpService.setCreateDt(new Date());				// 创建时间
			cpServiceDao.save(null, cpService);

			SysUser sysUser = new SysUser();
			sysUser.setMobile(userDto.getMobile());							// 手机号
			String password = com.xfs.common.util.StringUtils.getRandomSixString();// 随机密码
			sysUser.setPassword(MD5Util.MD5Encode(password, "UTF-8"));		// 密码
			sysUser.setCreateDt(new Date());								// 创建时间
			sysUser.setUserType(Constant.U_TYPE_SERVICE);					// 用户类型
			sysUser.setLastLoginDt(new Date());								// 最后一次登录时间
			sysUser.setUserName(userDto.getMobile());						// 默认用户名
			sysUser.setRealName(userDto.getMobile());						// 默认真实名称
			sysUser.setOrgId(spService.getSpId());							// 机构ID
			sysUserDao.insert(null, sysUser);
			//里面有启信宝验证
			saveSpInfo(null, cpService);
			// 发送密码
			activeUserWithSms(sysUser,password,"【薪福邦】尊敬的服务商，您的账号已经开通，快来进入吧！请使用手机号登陆，密码为：%s ");
			// 设置session
			//sysUser.setOrgId(cpService.getId());
			// 设置权限
			spServiceService.initSpRole(null,sysUser.getUserId());
			return sysUser;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	/**
	 *
	 * @method comments: 发送短信
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月6日 下午3:03:34
	 * @param sysUser
	 * @param content
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月6日 下午3:03:34 wangchao 创建
	 *
	 */
	public void activeUserWithSms(SysUser sysUser,String password, String content) {
		try {
			content = String.format(content, password);
			boolean smsresult =  SmsUtil.sendSms(sysUser.getMobile(), content);
			if (!smsresult)
				throw new BusinessException("短信发送失败!请检查配置文件是否正确!");
		} catch (Exception e) {
			String errorMessage = "短信发送失败!" + e.getMessage();
			log.error(errorMessage, e);
			throw new BusinessException(errorMessage);
		}
	}
	@Override
	public CpService saveCpService(SysUser sysUser) {
		SpService spService = spServiceService.findSpServiceById(sysUser.getOrgId());
		CpService cpService = new CpService();
		cpService.setId(spService.getSpId());
		cpService.setSaasSpId(spService.getSpId());
		cpService.setName(spService.getSpName());
		cpService.setEmail(spService.getSpEmail());
		cpService.setMobile(spService.getMobile());
		cpService.setShortName(spService.getShortName());
		cpService.setDevIndex(0.0);
		cpService.setBusiLicenseFile(null == spService.getBusiLicenseFile()?null:spService.getBusiLicenseFile());
		cpService.setDr(0);
		cpServiceDao.insert(null,cpService);
		return cpService;
	}
	public int updateCustomIndex(ContextInfo cti,  CpService vo ){
		return cpServiceDao.updateCustomIndex(cti, vo);
	}

	public CpService findByPK(CpService vo ){
		return cpServiceDao.findByPK(vo);
	}
	@Override 
	public Integer getAreaByAreacode(String areacode) {
		return cpServiceDao.getAreaByAreacode(areacode);
	}
	@Override
	public List<CpService> cpServiceContrast(int packageId,ContextInfo sysUser) {
		// 获取报名服务商
		CpQuotedPrice vo = new CpQuotedPrice();
		vo.setPackageId(packageId);
		vo.setSendSpid(sysUser.getOrgId());
		vo.setDr(0);
		List<CpQuotedPrice> list = cpQuotedPriceService.findAll(vo);
		// 获取服务商详细信息
		List<CpService> cpServiceList = new ArrayList<>(list.size());
		for (CpQuotedPrice cpQuotedPrice : list) {
			CpService cpService = new CpService();
			cpService.setId(cpQuotedPrice.getQuotedSpid());// 报价服务商ID
			cpService = cpServiceDao.findCpServiceDetailById(cpService);
			// 获取排名
			CpService cp1 = new CpService();
			cp1.setId(cpService.getId());
			Integer ranking = cpServiceDao.findOrderById(cp1);
			cpService.setAllRanking(ranking);
			// 获取成交金额
			CpService cp2 = new CpService();
			cp2.setId(cpService.getId());
			double price = cpServiceDao.findPriceById(cp2);
			cpService.setPrice(price);
			// 接包数
			CpQuotedPrice cpQuotedPrice2 = new CpQuotedPrice();
			cpQuotedPrice2.setQuotedSpid(cpService.getId());
			int packageNumber = cpQuotedPriceService.findPackageNumber(cpQuotedPrice2);
			cpService.setOrderNumber(packageNumber);
			
			String cpaddrss = (null != cpService.getCpAddress() && !"".equals(cpService.getCpAddress()))
                    ? cpService.getCpAddress().split(",")[1].split("-")[1] : "";
            // 服务区域
            if (null != cpService.getServiceArea()) {
                String[] serviceAreaArrey = cpService.getServiceAreaName().split(",");
                if (serviceAreaArrey.length > 0) {
                    String serviceArea = "";
                    for (int j = 0; j < serviceAreaArrey.length; j++) {
                        String[] aStrings = serviceAreaArrey[j].split("-");
                        if (aStrings.length > 1) {
                            if (!serviceArea.contains(aStrings[1])) {
                                serviceArea += aStrings[1] + "/";
                            }
                        }
                    }
                    if (!"".equals(serviceArea) || !"".equals(cpaddrss)) {
                    	cpService.setServiceAreaName(serviceArea.contains(cpaddrss) ? serviceArea.substring(0, serviceArea.lastIndexOf("/")) : serviceArea + cpaddrss);
                    }
                } else {
                	cpService.setServiceAreaName(cpaddrss);
                }
            } else {
            	cpService.setServiceAreaName(cpaddrss);
            }
			// 业务介绍
            CpBusinessIntroduction cpBusinessIntroduction = new CpBusinessIntroduction();
            cpBusinessIntroduction.setCpId(cpService.getId());
            cpBusinessIntroduction = cpBusinessIntroductionService.findByPK(cpBusinessIntroduction);
			cpService.setCpBusinessIntroduction(cpBusinessIntroduction);
			// 合作满意度和用户点评
			CpEvaluation cpEvaluation = new CpEvaluation();
			cpEvaluation.setSpIdBy(cpService.getId());
			cpEvaluation = cpEvaluationService.findEvaluation(cpEvaluation);
			cpService.setCpEvaluation(cpEvaluation);
			cpServiceList.add(cpService);
		}
		return cpServiceList;
	}
}
