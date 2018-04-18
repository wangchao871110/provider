package com.xfs.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xfs.base.dao.SpsExtdataDao;
import com.xfs.base.dao.SpsExtdataheadDao;
import com.xfs.base.dao.SpsExtdatasheetDao;
import com.xfs.base.model.SysUploadfile;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.enums.SearchType;
import com.xfs.common.mongo.vo.Search;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdata;
import com.xfs.sp.model.SpsExtdatahead;
import com.xfs.sp.model.SpsExtdatasheet;
import com.xfs.sps.utils.ImportExcelUtil;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.model.SysUser;

@Service
public class SpsExtdataServiceImpl implements SpsExtdataService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsExtdataServiceImpl.class);

	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
    SysUserDao sysUserDao;
	@Autowired
	MongoDao mongoDao;
	@Autowired
	SpsExtdataDao spsExtdataDao;
	@Autowired
	SpsExtdataheadDao spsExtdataheadDao;
	@Autowired
	SpsExtdatasheetDao spsExtdatasheetDao;

	public int save(ContextInfo cti, SpsExtdata vo) {
		return spsExtdataDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsExtdata vo) {
		return spsExtdataDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsExtdata vo) {
		return spsExtdataDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsExtdata vo) {
		return spsExtdataDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsExtdata vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		vo.setDr(0);// 查询dr=0 未被删除标识
		Integer total = spsExtdataDao.countFindBySpId(vo);
		pm.setTotal(total);
		List<SpsExtdata> datas = spsExtdataDao.freeFindBySpId(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsExtdata> findAll(SpsExtdata vo) {

		List<SpsExtdata> datas = spsExtdataDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/13 11:44:25
	/**
	 * fileId
	 * 文件名存储到库里后创建的ID、str备注信息、curUser、uploadFile.getFilename()文件名、excel文件(int)、
	 * fileId?、create_by、create_dt、dr ? exttab_id、sheet名、
	 * exttab_id、code、列名、数据类型、sheet_id
	 */
	@Override
	public Result importExcel(ContextInfo cti, Integer fileId, String str) throws BusinessException {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null == fileId) {
			result.setError("fileId不能为空！");
			result.setSuccess(false);
			return result;
		}
		SysUploadfile vo = new SysUploadfile();
		vo.setId(Integer.valueOf(fileId));
		SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);
		// 获取文件目录
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (null == uploadFile) {
			result.setError("文件不存在！");
			result.setSuccess(false);
			return result;
		}
		if (null == filePath || "".equals(filePath)) {
			result.setError("文件不存在！");
			result.setSuccess(false);
			return result;
		}
		// 读取循环获取数据 组装成map
		try {
			Map<String, Map<String, Map<String, String>>> impExcelMap = ImportExcelUtil.importExcels(filePath, null);
			if (null != impExcelMap && impExcelMap.toString().contains("error={impError={impError=标题列为空！}}")) {
				result.setSuccess(false);
				result.setError("有sheet页标题列为空！导入失败！");
				return result;
			}
			// 存Excel表
			SpsExtdata spsExtdata = new SpsExtdata();
			spsExtdata.setSpId(cti.getOrgId());// 服务商ID
			spsExtdata.setName(uploadFile.getFilename());// Excel文件名 Mongo里 ID
			spsExtdata.setRemark(str);// 备注
			spsExtdata.setExcelFile(uploadFile.getId());// Excel导入文件ID
			spsExtdata.setCreateBy(cti.getUserId());// 创建人ID
			spsExtdata.setCreateDt(date);// 创建时间
			spsExtdata.setDr(0);// 删除标识0 正常，1非正常
			Integer excelIns = spsExtdataDao.insert(cti, spsExtdata);

			// 循环导入Excel下的Sheet
			for (Map.Entry<String, Map<String, Map<String, String>>> entry : impExcelMap.entrySet()) {
				Map<String, Map<String, String>> valueMap = entry.getValue();
				// Map<String, String> dataMap = new LinkedHashMap<String,
				// String>();
				Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
				boolean bos = true;
				if (excelIns > 0) {
					// 存Sheet表
					SpsExtdatasheet spsExtdataSheet = new SpsExtdatasheet();
					spsExtdataSheet.setExttabId(spsExtdata.getId());// Excel表ID
					spsExtdataSheet.setName(entry.getKey());// Sheet名称spsExtdata.getName()
					Integer sheetIns = spsExtdatasheetDao.insert(cti, spsExtdataSheet);

					boolean jsonmark = true;// 标题是否存入json串
					boolean savaMongo = false;// json是否存入mongo
					for (Map.Entry<String, Map<String, String>> valueEntry : valueMap.entrySet()) {
						Map<String, String> entryMap = valueEntry.getValue();
						for (Map.Entry<String, String> dataEntry : entryMap.entrySet()) {
							if (sheetIns > 0) {
								if (bos) {
									// 存标题表
									SpsExtdatahead spsExtdatahead = new SpsExtdatahead();
									spsExtdatahead.setSheetId(spsExtdataSheet.getId());// SheetId
									// spsExtdatahead.setExttabId(spsExtdata.getId());//
									// 主表ID
									spsExtdatahead.setCode(dataEntry.getKey());// 列名
									spsExtdatahead.setName(spsExtdataSheet.getName());// sheet名
									// spsExtdatahead.setDataType("");//数据类型
									spsExtdataheadDao.insert(cti, spsExtdatahead);
									jsonmark = false;
								}
								if (jsonmark) {
									jsonMap.put("_id", UUID.randomUUID());
									jsonMap.put("ExcelId", spsExtdata.getId());
									jsonMap.put("SheetId", spsExtdataSheet.getId());
									jsonMap.put(dataEntry.getKey(), dataEntry.getValue());
									savaMongo = true;
								}
							}
							jsonmark = true;
						}
						if (savaMongo) {
							String jsonobj = JSON.toJSONString(jsonMap);
							boolean bool = this.saveExcelDatetoMongo(jsonobj);
							// 存mango
							if (!bool) {
								result.setSuccess(false);
								return result;
							}
						}
						bos = false;
					}
				}
			}
			result.setSuccess(true);// 在导入都没有问题 循环完给 返回结果成功为true
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("数据导入错误！");
			result.setSuccess(false);
		}
		return result;

	}

	/**
	 * Excel 数据存入Mongo
	 * 
	 * @param jsonobj
	 * @return
	 */
	public boolean saveExcelDatetoMongo(String jsonobj) {
		boolean isSuccess = mongoDao.insert("ExcelData", jsonobj);
		return isSuccess;
	}

	/**
	 * 从Mongo中取数据
	 */
	public PageModel findExcelDataPage(PageInfo pi, Integer excelId, Integer sheetId, String str, String stri) {

		PageModel pm = new PageModel(pi);
		Search search = new Search();
		List<SearchField> searchFields = new ArrayList<SearchField>();

		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();

		SearchField ExcelId = new SearchField("ExcelId", excelId);
		SearchField SheetId = new SearchField("SheetId", sheetId);
		searchFields.add(ExcelId);
		searchFields.add(SheetId);

		if (!StringUtil.isBlank(str)) {
			SearchField searStr = new SearchField(str, stri, SearchType.Like);
			searchFields.add(searStr);
		}

		search.setAndSearchField(searchFields);
		search.setSortOrder(1);// 排序字段1 正序
		search.setPage(pageIndex);// 开始页数
		search.setPageSize(pageSize);// 查询行数

		int total = (int) mongoDao.count("ExcelData", searchFields); // Mongo里查询需要显示的总条数
		pm.setTotal(total);
		List<Map<String, Object>> listResult = mongoDao.query("ExcelData", search);
		pm.setDatas(listResult);
		return pm;

	}
}
