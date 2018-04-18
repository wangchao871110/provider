package com.xfs.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import com.xfs.common.util.Constant;
import com.xfs.user.service.SysFunctionDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.dao.SpsBatchAdjBaseDao;
import com.xfs.business.dto.SpsBatchAdjBaseVo;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.SpsBatchAdjBase;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.SpsBatchAdjBaseService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.util.Config;
import com.xfs.common.util.FileUtil;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sps.utils.ImportExcelUtil;

@Service
public class SpsBatchAdjBaseServiceImpl implements SpsBatchAdjBaseService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBatchAdjBaseServiceImpl.class);

	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
	private BdInsuranceService bdInsuranceService;
	@Autowired
	private CmCorpService cmCorpService;
	@Autowired
	private CmEmployeeService cmEmployeeService;
	@Autowired
	TaskAspectService taskAspectService;
	@Autowired
	private BsAreaService bsAreaService;
	@Autowired
	private SpsBatchAdjBaseDao spsBatchAdjBaseDao;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;

    @Autowired
    MongoDao mongoDao;
	
	public int save(ContextInfo cti, SpsBatchAdjBase vo ){
		return spsBatchAdjBaseDao.save(cti,vo);
	}
	public int insert( ContextInfo cti,SpsBatchAdjBase vo ){
		return spsBatchAdjBaseDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, SpsBatchAdjBase vo ){
		return spsBatchAdjBaseDao.remove(cti,vo);
	}
	public int update( ContextInfo cti,SpsBatchAdjBase vo ){
		return spsBatchAdjBaseDao.update(cti,vo);
	}
	public PageModel findPage(SpsBatchAdjBase vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = spsBatchAdjBaseDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBatchAdjBase> datas = spsBatchAdjBaseDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsBatchAdjBase> findAll(SpsBatchAdjBase vo){
		
		List<SpsBatchAdjBase> datas = spsBatchAdjBaseDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/26 21:14:54
	/**
	 * 导入更新基数
	 *  @param   cti, fileId
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 2016/12/27 20:09
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/27 20:09
	 *  @updateAuthor  :
	 */
	@Override
	public Result importExcel(ContextInfo cti,Integer fileId){
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			result.setSuccess(false);
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (null == filePath || "".equals(filePath)) {
			result.setError("文件不存在！");
			result.setSuccess(false);
			return result;
		}
		Map<Integer, Map<String, String>> sheelMap;
		List<BdInsurance> insurances = bdInsuranceService.findAll(new BdInsurance());
		try {
			sheelMap = ImportExcelUtil.importSheelExcel(filePath, getEmployeeFieldMap(insurances),2);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}
		if(sheelMap.isEmpty()){
			result.setSuccess(false).setError("文档数据为空");
			return result;
		}
		result = batchAdjBase(cti,sheelMap,insurances,fileId,result);
		return result;
	}

	/**
	 *  批量调整最低基数
	 *  @param   cti, sheelMap
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-09 20:40
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-09 20:40
	 *  @updateAuthor  :
	 */
	@Override
	public Result batchAdjLowerBase(ContextInfo cti,Map<Integer, Map<String, String>> sheelMap){
		Result result = Result.createResult().setSuccess(false);
		List<BdInsurance> insurances = bdInsuranceService.findAll(new BdInsurance());
		result = batchAdjBase(cti,sheelMap,insurances,null,result);
		return result;
	}

	/**
	 *  批量调整基数
	 *  @param   cti, sheelMap, insurances, result
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-09 20:35
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-09 20:35
	 *  @updateAuthor  :
	 */
	private Result batchAdjBase(ContextInfo cti,Map<Integer, Map<String, String>> sheelMap,List<BdInsurance> insurances,Integer fileId,Result result){
		// 查询出企业信息集合
		List<CmCorp> corpList =  cmCorpService.findCorpBySpId(cti.getOrgId(),cti);
		Map<String, CmCorp> corpNameIdMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(corpList)) {
			for (CmCorp corp : corpList) {
				corpNameIdMap.put(corp.getCorpName(),corp);
			}
		}
		// 月份校验
		String pattern = "^[1-2][0-9][0-9][0-9]-[0-1][0-9]$";
		Pattern monP = Pattern.compile(pattern);
		// 文档员工  防止重复
		Map<String, String> idCardMap = new HashMap<String, String>();

		//数据消息
		List<Map<String, String>> dataMsgList = new ArrayList<Map<String, String>>();
		//失败数据
		List<Map<String, String>> errorList = new ArrayList<Map<String, String>>();
		//地区缓存
		Map<String,BsArea> areaMap = new HashedMap();
		List<BsArea> areaList = bsAreaService.findAll(new BsArea());
		if(CollectionUtils.isNotEmpty(areaList)){
			for (BsArea area: areaList){
				areaMap.put(area.getName(),area);
			}
		}
		//保存地区
		Integer areaId = null;
		//保存企业
		Integer corpId = null;
		//保存起始月
		String savePeriod = null;
		//企业是否是同一个
		Set<String> corpName = new HashSet<>();
		//社保业务类型
		Boolean existInsu = false;
		//公积金业务类型
		Boolean existFund = false;
		//成功数据行号 用于删除
		List<Integer> succIndexs = new ArrayList<>();
		//数据条数
		Integer emplCount = 0;
		//成功条数
		Integer successCount= 0;
		//遍历数据
		for(Map.Entry<Integer,Map<String,String>> data: sheelMap.entrySet()){
			emplCount++;
			Map<String, String> curMap = data.getValue();
			String error = "";
			curMap.put("row", data.getKey() + "");
			Integer cpId = null;
			CmCorp corp = null;
			if (StringUtils.isEmpty(curMap.get("corpName")) || StringUtils.isEmpty(curMap.get("corpName").trim())) {
				error += "客户名称为空！<br>";
			} else {
				if (corpNameIdMap.containsKey(curMap.get("corpName").trim())) {
					curMap.put("cpId", corpNameIdMap.get(curMap.get("corpName").trim()).toString());
					corp = corpNameIdMap.get(curMap.get("corpName").trim());
					cpId = corp.getCpId();
					//第一次设置保存企业
					if(corpId == null){
						corpId = cpId;
					}
					corpName.add(corp.getCorpName());
				} else {
					error += "该客户不存在！<br>";
				}
			}
			String name = null;
			if (StringUtils.isEmpty(curMap.get("name")) || StringUtils.isEmpty(curMap.get("name").trim())) {
				error += "员工姓名为空！<br>";
			} else {
				name = curMap.get("name").trim();
			}
			Integer empId = null;
			Integer schemeId = null;
			if (StringUtils.isEmpty(curMap.get("identityCode"))
					|| StringUtils.isEmpty(curMap.get("identityCode").trim())) {
				error += "证件号为空！<br>";
			} else {
				String idcar = curMap.get("identityCode").trim();
				// 校验唯一性 名称+证件号
				String idVerify = idcar + name;
				if ("Excel".equals(idCardMap.get(idVerify))) {
					error += "导入Excel表中证件号码已存在！<br>";
				} else {
					idCardMap.put(idVerify, "Excel");
					if (cpId != null && name != null) {
						CmEmployee empl = new CmEmployee();
						empl.setCpId(cpId);
						empl.setIdentityCode(idcar);
						empl.setName(name);
						empl.setDr(0);
						empl = cmEmployeeService.findCpEmplByNameAndIdentityCode(empl);
						if(empl == null){
							error += "员工不存在！<br>";
						}else {
							empId = empl.getEmpId();
							schemeId = empl.getSchemeId();
						}
					}
				}
			}
			if (StringUtils.isEmpty(curMap.get("area")) || StringUtils.isEmpty(curMap.get("area").trim())) {
				error += "城市为空！<br>";
			} else {
				BsArea area = areaMap.get(curMap.get("area").trim());
				if(area == null){
					error += "城市不存在！<br>";
				}else {
					if(areaId == null){
						areaId = area.getAreaId();
					}
				}
			}
			//是否改变社保申报工资
			Boolean changeInsu = false;

			if (StringUtils.isNotEmpty(curMap.get("newInsuranceSalary"))
					&& StringUtils.isNotBlank(curMap.get("newInsuranceSalary").trim())) {
				try {
					Double.parseDouble(curMap.get("newInsuranceSalary").trim());
					changeInsu = true;
				}catch (NumberFormatException e){
					error += "新社保申报工资格式不正确！<br>";
				}
			}
			//是否改变公积金申报工资
			Boolean changeFund = false;

			if (StringUtils.isNotEmpty(curMap.get("newFundSalary"))
					&& StringUtils.isNotBlank(curMap.get("newFundSalary").trim())) {
				try {
					Double.parseDouble(curMap.get("newFundSalary").trim());
					changeFund = true;
				}catch (NumberFormatException e){
					error += "新公积金申报工资格式不正确！<br>";
				}
			}
			if(!changeFund && !changeInsu){
				error += "社保申报工资与公积金申报工资请至少选择一项进行调整！<br>";
			}
			String beginPeriod = null;
			if (StringUtils.isEmpty(curMap.get("beginPeriod"))
					|| StringUtils.isEmpty(curMap.get("beginPeriod").trim())){
				error += "调整起始月为空！<br>";
			}else{
				if(!monP.matcher(curMap.get("beginPeriod").trim()).matches()){
					error += "调整起始月格式不正确！<br>";
				}else {
					beginPeriod = curMap.get("beginPeriod").trim();
					if(StringUtils.isBlank(savePeriod)){
						savePeriod = beginPeriod;
					}
				}
			}
			//险种调整项
			List<Integer> insuranceItems = new ArrayList<>();
			List<Integer> fundItems = new ArrayList<>();
			for(BdInsurance insurance:insurances){
				if(StringUtils.isNotEmpty(curMap.get(insurance.getCode()))
						&& StringUtils.isNotBlank(curMap.get(insurance.getCode()).trim())){
					if("调整".equals(curMap.get(insurance.getCode()).trim())){
						if("INSURANCE".equals(insurance.getInsuranceFundType())){
							insuranceItems.add(insurance.getInsuranceId());
						}else {
							fundItems.add(insurance.getInsuranceId());
						}
					}
				}
			}
			//导入信息
			Map<String,String> msgMap = new HashMap<>();
			msgMap.put("row",curMap.get("row"));
			msgMap.put("empId",empId == null?"":empId.toString());
			msgMap.put("empName",name == null?"":name.toString());
			msgMap.put("identityCode",curMap.get("identityCode") == null?"":curMap.get("identityCode").trim());
			msgMap.put("cpId",corp == null ? "" : corp.getCpId().toString());
			msgMap.put("shortName",corp == null ? "" : StringUtils.isBlank(corp.getShortName()) ? corp.getCorpName() : corp.getShortName());
			msgMap.put("newInsuranceSalary",curMap.get("newInsuranceSalary") == null ? "" : curMap.get("newInsuranceSalary"));
			msgMap.put("newFundSalary",curMap.get("newFundSalary") == null ? "" : curMap.get("newFundSalary"));
			msgMap.put("insuranceItems",JSON.toJSONString(insuranceItems));
			msgMap.put("fundItems",JSON.toJSONString(fundItems));
			msgMap.put("beginPeriod",beginPeriod);
			msgMap.put("error",error);
			if(error.isEmpty()){
				msgMap.put("isSuccess","true");
				if(changeInsu){
					if(!existInsu){
						existInsu = true;
					}
					SpsTask spstask = new SpsTask();
					spstask.setEmpId(empId);
					spstask.setSpId(cti.getOrgId());
					spstask.setBstypeId(null != fileId ? 31 : 35);
					spstask.setSchemeId(schemeId);
					Map<String,String> jsonMap = new HashMap<>();
					jsonMap.put("insuranceSalary",curMap.get("newInsuranceSalary").trim());
					jsonMap.put("insuranceItems",JSON.toJSONString(insuranceItems));
					jsonMap.put("beginPeriod",beginPeriod);
					spstask.setJson(JSON.toJSONString(jsonMap));
					taskAspectService.saveTask(cti, spstask, null, result);
					if (!result.isSuccess()) {
						msgMap.put("error",result.getError());
						errorList.add(msgMap);
						log.error("批量调整基数失败：spsTask:"+JSON.toJSONString(spstask));
						continue;
					}
				}
				if(changeFund){
					if(!existFund){
						existFund = true;
					}
					SpsTask spstask = new SpsTask();
					spstask.setEmpId(empId);
					spstask.setSpId(cti.getOrgId());
					spstask.setBstypeId(null != fileId ? 32 : 36);
					spstask.setSchemeId(schemeId);
					Map<String,String> jsonMap = new HashMap<>();
					jsonMap.put("fundSalary",curMap.get("newFundSalary").trim());
					jsonMap.put("fundItems",JSON.toJSONString(fundItems));
					jsonMap.put("beginPeriod",beginPeriod);
					spstask.setJson(JSON.toJSONString(jsonMap));
					taskAspectService.saveTask(cti, spstask, null, result);
					if (!result.isSuccess()) {
						msgMap.put("error",result.getError());
						errorList.add(msgMap);
						log.error("批量调整基数失败：spsTask:"+JSON.toJSONString(spstask));
						continue;
					}
				}
				succIndexs.add(data.getKey());
				successCount++;
			}else {
				msgMap.put("isSuccess","false");
				errorList.add(msgMap);
			}
			dataMsgList.add(msgMap);
		}

		if(successCount > 0){
			SpsBatchAdjBase spsBatchAdjBase = new SpsBatchAdjBase();
			spsBatchAdjBase.setFileId(fileId);
			spsBatchAdjBase.setCpId(corpName.size() > 1 ? null : corpId);
			spsBatchAdjBase.setAreaId(areaId);
			spsBatchAdjBase.setAdjType(null != fileId ? 1 : 2);
			spsBatchAdjBase.setBeginMonth(savePeriod);
			spsBatchAdjBase.setCreateBy(cti.getUserId());
			spsBatchAdjBase.setCreateDt(new Date());
			spsBatchAdjBase.setEmpCount(emplCount);
			spsBatchAdjBase.setSpId(cti.getOrgId());
			spsBatchAdjBase.setEmpJson(JSON.toJSONString(dataMsgList));
			String insuFund = existInsu ? "社保":"";
			if(existFund){
				if(StringUtils.isNotBlank(insuFund)){
					insuFund +=",";
				}
				insuFund+="公积金";
			}
			spsBatchAdjBase.setInsuranceFund(insuFund);
			spsBatchAdjBaseDao.save(cti,spsBatchAdjBase);
			result.setSuccess(true);
		}else {
			result.setSuccess(true).setError("操作失败！没有正确的数据");
		}

		try {
			result.setDataValue("errorFileSuccess",true);
			if(null != fileId)
				result.setDataValue("errorFileId", getErrorFile(cti, fileId, succIndexs));
		}catch (Exception e){
			e.printStackTrace();
			result.setDataValue("errorFileSuccess",false);
		}
		result.setDataValue("errorList",errorList);
		result.setDataValue("successCount",successCount);
		result.setDataValue("errorCount",errorList.size());
		return result;
	}

	/**
	 *  返回导入字段信息集合
	 *  @param   insurances
	 * @return    : java.util.Map<java.lang.String,java.lang.String>
	 *  @createDate   : 2016/12/27 14:19
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/27 14:19
	 *  @updateAuthor  :
	 */
	private Map<String, String> getEmployeeFieldMap(List<BdInsurance> insurances) {
		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put("客户名称", "corpName");
		fieldMap.put("员工姓名", "name");
		fieldMap.put("证件号码", "identityCode");
		fieldMap.put("城市", "area");
		fieldMap.put("原社保申报工资", "oldInsuranceSalary");
		fieldMap.put("新社保申报工资", "newInsuranceSalary");
		fieldMap.put("原公积金申报工资", "oldFundSalary");
		fieldMap.put("新公积金申报工资", "newFundSalary");
		fieldMap.put("调整起始月", "beginPeriod");
		for(BdInsurance insurance:insurances){
			fieldMap.put(insurance.getName(),insurance.getCode());
		}
		return fieldMap;
	}

	/**
	 * 分页查询vo数据
	 *  @param   vo
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2016/12/28 11:07
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/28 11:07
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findVoPage(ContextInfo cti,SpsBatchAdjBaseVo vo){
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		// 获取数据权限
		vo.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		pm.setPagesize(pageSize);
		Integer total = spsBatchAdjBaseDao.countFreeFindVo(vo);
		pm.setTotal(total);
		List<SpsBatchAdjBaseVo> datas = spsBatchAdjBaseDao.freeFindVo(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	/**
	 * 查看人员详情数据  emojson 转 list<map>
	 *  @param   id
	 * @return    : com.xfs.business.dto.SpsBatchAdjBaseVo
	 *  @createDate   : 2016/12/29 10:39
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/29 10:39
	 *  @updateAuthor  :
	 */
	@Override
	public SpsBatchAdjBaseVo findByIdForDetail(Integer id) throws Exception{
		SpsBatchAdjBase entity = new SpsBatchAdjBase();
		entity.setId(id);
		entity = spsBatchAdjBaseDao.findByPK(entity);
		SpsBatchAdjBaseVo vo = new SpsBatchAdjBaseVo();
		if(entity != null){
//			BeanUtils.copyProperties(vo,entity);
			if(StringUtils.isNotBlank(entity.getEmpJson())){
				TypeReference<List<Map<String,String>>> ref = new TypeReference<List<Map<String,String>>>(){};
				vo.setEmpJsons(JSON.parseObject(entity.getEmpJson(),ref));
			}
		}
		return vo;
	}

	public Integer getErrorFile(ContextInfo cti,Integer id,List<Integer> succIndexs)throws Exception{

        SysUploadfile vo = new SysUploadfile();
        vo.setId(Integer.valueOf(id));
        SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);

        if (null == uploadFile) {
            return null;
        }

        String curDate = getDateStrByMonth();
        String mogoDbFile = uploadFile.getSavename();
        
        InputStream inputStream = sysUploadfileService.downloadFile(uploadFile.getSavename());
        //GridFSDBFile dbFile = mongoDao.readFileById(mogoDbFile);
        XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xwb.getSheetAt(0);
		Integer lastRowNum=sheet.getLastRowNum();
		Collections.sort(succIndexs, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for(Integer i:succIndexs){
			if(i > lastRowNum){
				sheet.removeRow(sheet.getRow(lastRowNum));
			}else {
				sheet.shiftRows(i,lastRowNum,-1);
			}
        }

        String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
        String filePath = fileRootPath + File.separator + curDate;
        String saveName = "错误信息.xlsx";
        String fileName = saveName;

        // 获取一个时间格式的名称（微信中为简易解决中文名称问题）
        saveName = generateFileName(saveName);
		File path = new File(filePath);
        if (!path.exists()) {
			path.mkdirs();
        }
		File localFile = new File(filePath, saveName);
		if (localFile.exists()) {
			localFile = getUniqueFile(filePath, saveName);
			saveName = localFile.getName();
		}

		BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(localFile));
        // 将文件写入新建的文件中
        xwb.write(outStream);

		outStream.close();
        // 将文件存入mongo
        //saveName = sysUploadfileService.saveFileToMongo(localFile);
		saveName = sysUploadfileService.uploadFile(localFile, "images/sps/");
        if (com.xfs.common.util.StringUtils.isBlank(saveName)) {
           return null;
        }

        FileUtil f = new FileUtil();
        String fileSize = f.getFileSizes(localFile);

        // 保存到数据库
        SysUploadfile sysUploadfile = new SysUploadfile();
        sysUploadfile.setFilename(fileName);
        sysUploadfile.setSavename(saveName);
        sysUploadfile.setFilepath(curDate);
        sysUploadfile.setFilesize(fileSize);
        sysUploadfile.setCreateuser(cti.getUserId());
        sysUploadfileService.save(cti,sysUploadfile);
        return sysUploadfile.getId();
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
}
