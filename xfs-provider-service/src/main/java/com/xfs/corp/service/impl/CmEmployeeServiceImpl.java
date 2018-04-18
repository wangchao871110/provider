package com.xfs.corp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.acc.dto.AreaSocialRuleVo;
import com.xfs.acc.dto.FundRatio;
import com.xfs.acc.dto.InsuranceLivingTypeDetailsVo;
import com.xfs.acc.dto.InsuranceLivingTypeVo;
import com.xfs.acc.dto.InsuranceRatio;
import com.xfs.acc.dto.Ratio;
import com.xfs.acc.dto.SchemeAccountRatio;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.utils.DateFormatUtil;
import com.xfs.aop.task.cs.CorpTaskAspectService;
import com.xfs.aop.task.cs.NewInsuranceParseImpl;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysMessage;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.base.service.SysMessageService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.bill.service.SpsBillEmpService;
import com.xfs.bill.service.SpsBillService;
import com.xfs.business.dao.BdInsuranceareaDao;
import com.xfs.business.dao.SpsTaskDao;
import com.xfs.business.dto.BatchAdjBaseQuery;
import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsArearatioscope;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BdInsuranceareaService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.BsArearatioscopeService;
import com.xfs.business.service.BsArearatioverService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.excel.ExcelCore;
import com.xfs.common.excel.ExcelHeaderModel;
import com.xfs.common.excel.ExcelModel;
import com.xfs.common.excel.ExcelUtil;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.Arith;
import com.xfs.common.util.Config;
import com.xfs.common.util.Constant;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.IdentityUtil;
import com.xfs.common.util.MobileValidator;
import com.xfs.common.util.TimeUtils;
import com.xfs.common.util.UUIDUtil;
import com.xfs.common.util.VerificationUtils;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.dao.CmDepartmentDao;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.dao.CmEmployeeInsuranceDao;
import com.xfs.corp.dto.BatchReduceEmplQuery;
import com.xfs.corp.dto.CSCmEmployeeDto;
import com.xfs.corp.dto.DynamicFields;
import com.xfs.corp.dto.EmpMonthInsuranceDto;
import com.xfs.corp.dto.EmployeeDto;
import com.xfs.corp.enums.InsuranceEnums;
//192.168.0.46/B/xfs-provider.git
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmDepartment;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.model.CmEmployeeOrg;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.service.CmEmployeeAuditService;
import com.xfs.employeeside.service.EntryService;
import com.xfs.sp.dao.SpCmRelationDao;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.dao.SpsSchemeInsuranceDao;
import com.xfs.sp.dao.SpsSupplierDao;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsAccountRatio;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.model.SpsSchemeInsurance;
import com.xfs.sp.model.SpsSupplier;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsAccountService;
import com.xfs.sp.service.SpsSchemeEmpService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.sps.utils.ImportExcelUtil;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysFunctionDataService;
import com.xfs.user.service.SysUserService;
import com.xfs.wx.message.dto.TextcardMessage;
import com.xfs.wx.message.dto.WxMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author : yangfangwei
 * @date : 2016-11-8 14:10:00
 * @version : V1.0
 */
@Service
public class CmEmployeeServiceImpl implements CmEmployeeService, IRedisKeys ,IStaticVarConstant{

	private static final Logger log = Logger.getLogger(CmEmployeeServiceImpl.class);
	@Autowired
	private CmEmployeeDao cmEmployeeDao;
	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
	private SysDictitemService sysDictitemService;
	@Autowired
	private CmCorpDao cmCorpDao;
	@Autowired
	private BsAreaDao bsAreaDao;
	@Autowired
	private SpCmRelationDao spCmRelationDao;
	@Autowired
	private SysDictitemDAO sysDictitemDAO;
	@Autowired
	private SpsSchemeService spsSchemeService;
	@Autowired
	private SpsSchemeEmpService spsSchemeEmpService;
	@Autowired
	private SpsTaskService spsTaskService;
	@Autowired
	private CmEmployeeInsuranceDao cmEmployeeInsuranceDao;
	@Autowired
	private CmEmployeeInsuranceService cmEmployeeInsuranceService;
	@Autowired
	SpsSchemeDao spsSchemeDao;
	@Autowired
	TaskAspectService taskAspectService;
	@Autowired
	CorpTaskAspectService corpTaskAspectService;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	@Autowired
	private BdInsuranceareaService bdInsuranceareaService;
	@Autowired
	private SpsAccountService spsAccountService;
	@Autowired
	private SpsAccountRatioService spsAccountRatioService;
	@Autowired
	private BsArearatioverService bsArearatioverService;
	@Autowired
	private BsArearatioService bsArearatioService;
	@Autowired
	private BdBusinessfieldService bdBusinessfieldService;
	@Autowired
	private CmDepartmentDao cmDepartmentDao;
	@Autowired
	private SpsTaskDao spsTaskDao;
	@Autowired
	private SpsSchemeInsuranceDao spsSchemeInsuranceDao;
	@Autowired
	private BsArearatioscopeService bsArearatioscopeService;
	@Autowired
	private SpServiceService spServiceService;
	@Autowired
	private BdInsuranceareaDao bdInsuranceareaDao;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SpsBillEmpService spsBillEmpService;
	@Autowired
	private SysMessageService sysMessageService;
	@Autowired
	private SpsSupplierDao spsSupplierDao;
	@Autowired
    CmCorpService cmCorpService;
	@Autowired
    SpsBillService spsBillService;
	@Autowired
	NewInsuranceParseImpl newInsuranceParseImpl;
	@Autowired
	private CmEmployeeAuditService cmEmployeeAuditService;
	@Autowired
	private BdInsuranceService bdInsuranceService;
	@Autowired
    private EntryService entryService;
	@Autowired
    private BdBsareatypeService bdBsareatypeService;

	public int save(ContextInfo cti, CmEmployee vo) {
		return cmEmployeeDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, CmEmployee vo) {
		return cmEmployeeDao.insert(cti, vo);
	}



	public int remove(ContextInfo cti, CmEmployee vo) {
		CmEmployee e = cmEmployeeDao.findByPK(vo);
		int flag = cmEmployeeDao.remove(cti, vo);
		if (flag >= 0) {
			SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
			spsSchemeEmp.setEmpId(vo.getEmpId());
			List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(spsSchemeEmp);
			for (SpsSchemeEmp schemeEmp : list) {
				// schemeEmp.setState("STOP");
				schemeEmp.setDr(1);
				spsSchemeEmpService.update(cti, schemeEmp);
			}

			/*
			 * SpsTask spsTask = new SpsTask(); spsTask.setEmpId(vo.getEmpId());
			 * List<SpsTask> spsTasks = spsTaskService.findAll(spsTask);
			 *
			 * for (SpsTask task : spsTasks) { task.setDr(1);
			 * spsTaskService.update(task); }
			 */
			/* spsSchemeEmpService.removeByEmp(spsSchemeEmp); */
		}
		return flag;
	}


	public int update(ContextInfo cti, CmEmployee vo) {
		return cmEmployeeDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, CmEmployee vo) {
		// nameEq 员工列表搜索 传来的参数
		if (null != vo.getNameEq() && !"".equals(vo.getNameEq().trim())) {
			vo.setNameEq(vo.getNameEq().replaceAll(" ", "").trim());
		}
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		vo.setDr(0);// 0为有效员工
		Integer total = cmEmployeeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmEmployee> datas = cmEmployeeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<CmEmployee> findAll(CmEmployee vo) {

		List<CmEmployee> datas = cmEmployeeDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/08 15:03:51
	public List<CmEmployee> findEmpIdCardByCpId(CmEmployee emep) {

		List<CmEmployee> datas = cmEmployeeDao.findEmpIdCardByCpId(emep);
		return datas;

	}

	public Map<String, Object> findOneByEmpId(CmEmployee vo) {
		vo.setDr(0);// 0为有效员工
		Map<String, Object> datas = cmEmployeeDao.freeFindOneByEmpId(vo);
		return datas;

	}

	/**
	 * 读取导入员工标题
	 *
	 * @throws Exception
	 */
	@Override
	public Result readExcelTitle(Integer fileId, ContextInfo cti) throws Exception {
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String uploadFile = sysUploadfileService.getPhysicsFile(Integer.valueOf(fileId));
		if (null == uploadFile || "".equals(uploadFile)) {
			result.setError("文件不存在！");
			return result;
		}

		// 查询 excel中 sheet页 级 标题行
		List<String> titleList = new ArrayList<>();
		Map<String, List<String>> sheetData = ImportExcelUtil.readExcelTitle(uploadFile);
		for (Map.Entry<String, List<String>> entry : sheetData.entrySet()) {
			titleList = entry.getValue();
		}
		result.setDataValue("titleList", titleList);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 导入员工信息Excel
	 */
	@Override
	public Result importUpdateEmpExcel(ContextInfo cti, Integer fileId, Integer cpId, String selectContent)
			throws BusinessException {
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
		if (StringUtils.isEmpty(selectContent)) {
			result.setSuccess(false);
			result.setError("对应关系不能为空！");
			return result;
		}
		// 前端传来标题匹配字段<K,V>
		Map<String, String> colNameMap = new HashMap<String, String>();
		String selectArr[] = selectContent.split(",");
		if (null != selectArr) {
			for (int i = 0; i < selectArr.length; i++) {
				String itemStr = selectArr[i];
				String[] itemArr = itemStr.split("=");
				if (null != itemArr) {
					colNameMap.put(itemArr[0], itemArr[1]);
				}
			}
		}
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
		CmEmployee cmep = new CmEmployee();
		cmep.setCpId(cpId);
		cmep.setDr(0);
		Date date = new Date();
		// 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数 两个账户ID 两个缴交地区
		Map<String, Object> mapList = cmEmployeeDao.findInsFunByCpId(cpId, cti.getOrgId());
		// mapList.get("insurance_code")//社保 缴纳账户地区编码
		// 公积金 账户ID，缴交地ID
		if (null != mapList.get("fund_account_id")
				&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
			// cmep.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
		}
		if (null != mapList.get("fund_area") && "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
			// cmep.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
		}
		// 社保 账户ID 缴交地ID
		if (null != mapList.get("insurance_account_id")
				&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
			/// cmep.setInsuranceAccountId(Integer.parseInt(mapList.get("insurance_account_id").toString()));
		}
		if (null != mapList.get("insurance_area")
				&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
			// cmep.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").toString()));
		}
		// 社保基数范围
		/*
		 * BsInsurancerule bsinsurance =
		 * bsRuleService.findRatioAndPayBaseByChildArea(cmep.getInsuranceArea(),
		 * "INSURANCE"); if (null == bsinsurance || null ==
		 * bsinsurance.getBaseUpper() || null == bsinsurance.getBaseLower()) {
		 * result.setSuccess(false); Map<String, String> errorMap = new
		 * HashMap<String, String>(); errorMap.put("error",
		 * "未查到该参保人员社保基数范围！<br>"); errorDataList.add(errorMap);
		 * result.setDataValue("errorDataList", errorDataList);
		 * result.setError("未查到该参保人员社保基数范围！<br>"); return result; }
		 */
		// 公积金基数范围
		/*
		 * BsInsurancerule bsfund =
		 * bsRuleService.findRatioAndPayBaseByChildArea(cmep.getFundArea(),
		 * "FUND"); if (null == bsfund || null == bsfund.getBaseLower() || null
		 * == bsfund.getBaseUpper()) { result.setError("未查到该参保人员公积金基数范围！");
		 * result.setSuccess(false); Map<String, String> errorMap = new
		 * HashMap<String, String>(); errorMap.put("error", "未查到该参保人员公积金基数范围！");
		 * errorDataList.add(errorMap); result.setDataValue("errorDataList",
		 * errorDataList); return result; }
		 */
		// 户口性质 农业 城镇
		Map<String, String> sysDicMap = new HashMap<String, String>();
		List<SysDictitem> listdic = sysDictitemService.findByDictName("liveType");
		for (SysDictitem sysDict : listdic) {
			sysDicMap.put(sysDict.getName(), sysDict.getCode());
		}
		// 参保人员类别
		Map<String, String> sysMap = new HashMap<String, String>();
		List<SysDictitem> listdict = new ArrayList<SysDictitem>();
		// mapList.get("insurance_code")//社保 缴纳账户地区编码
		if (null != mapList.get("insurance_code")
				&& "String".equals(mapList.get("insurance_code").getClass().getSimpleName())) {
			listdict = sysDictitemService.findByAreaCode(mapList.get("insurance_code").toString());
			if (null != listdict) {
				for (SysDictitem sysDictio : listdict) {
					sysMap.put(sysDictio.getName(), sysDictio.getCode());
				}
			}
		} else {
			listdict = null;
		}

		// 社保状态 公积金状态 导入Excel里只有这两项
		Map<String, String> insfunMap = new HashMap<String, String>();
		insfunMap.put("正常缴纳", "ON");
		insfunMap.put("未缴纳", "OFF");
		// 列号和标题的<K,V>Map
		Map<Integer, String> colNumMap = new HashMap<Integer, String>();
		// 查询当前服务商 当前企业 有效员工
		List<CmEmployee> listCmEmpId = this.findEmpIdCardByCpId(cmep);
		Map<String, String> idCardMap = new HashMap<String, String>();
		IdcardValidator iv = new IdcardValidator();
		for (CmEmployee iDCardMap : listCmEmpId) {
			idCardMap.put(iDCardMap.getIdentityCode(), "EmpId");
		}

		boolean isError = false;
		OPCPackage pkg;
		XSSFWorkbook xwb = null;
		try {
			pkg = OPCPackage.open(filePath);
			xwb = new XSSFWorkbook(pkg);
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				// 遇到隐藏的sheet页 不读取
				boolean sheethide = xwb.isSheetHidden(i);
				if (sheethide) {
					continue;
				}

				// 读取表格内容
				XSSFSheet sheet = xwb.getSheetAt(0);
				XSSFRow row = null;
				// 处理标题行
				row = sheet.getRow(0);
				for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
					String titleContent = row.getCell(j).toString();
					if (StringUtils.isNotBlank(titleContent)) {
						if (colNameMap.containsKey(titleContent)) {
							String fieldName = colNameMap.get(titleContent);
							colNumMap.put(j, fieldName);
						}
					}
				}
				// 校验 并 处理内容行
				for (int r = 1; r <= sheet.getLastRowNum(); r++) {
					row = sheet.getRow(r);
					if (null == row) {
						continue;
					}
					// 处理列
					Map<String, String> curMap = new HashMap<String, String>();
					String error = "";
					curMap.put("row", r + 1 + "");
					for (Map.Entry<Integer, String> entry : colNumMap.entrySet()) {
						if (null != row.getCell(entry.getKey()) && !"".equals(row.getCell(entry.getKey()).toString())) {
							curMap.put(entry.getValue(), row.getCell(entry.getKey()).toString());
						}
					}
					if (StringUtils.isEmpty(curMap.get("姓名"))) {
						error += "姓名为空！<br>";
					}
					if (StringUtils.isEmpty(curMap.get("身份证号"))) {
						error += "身份证号为空！<br>";
					} else {
						String idcar = curMap.get("身份证号");
						if (!iv.isValidate18Idcard(idcar)) {
							error += "身份证号格式错误！<br>";
						} else if ("Excel".equals(idCardMap.get(idcar))) {
							error += "导入Excel表中员工身份证已存在！<br>";
						} else if ("EmpId".equals(idCardMap.get(idcar))) {
							error += "员工在该企业已存在，请注意！<br>";
						} else {
							idCardMap.put(idcar, "Excel");
							curMap.put("证件类型", "IDCard");// 证件类型 IDType 身份证：ID
						}
					}
					if (StringUtils.isEmpty(curMap.get("手机号"))) {
						error += "手机号为空！<br>";
					} else if (!MobileValidator.isMobileNO(curMap.get("手机号"))) {
						error += "手机号格式错误！<br>";
					}
					if (StringUtils.isEmpty(curMap.get("户口性质"))) {
						error += "户口性质为空!<br>";
					} else if (StringUtils.isEmpty(sysDicMap.get(curMap.get("户口性质")))) {
						error += "未找到该户口性质！<br>";
					}
					if (StringUtils.isEmpty(curMap.get("社保基数"))) {
						error += "社保基数为空！<br>";
					} else {
						/*
						 * if (bsinsurance.getBaseLower().compareTo(Double.
						 * parseDouble(curMap.get("社保基数"))) > 0) { error +=
						 * "社保基数低于本地最低标准！<br>"; } if
						 * (bsinsurance.getBaseUpper().compareTo(Double.
						 * parseDouble(curMap.get("社保基数"))) < 0) { error +=
						 * "社保基数高于本地最高标准！<br>"; }
						 */
					}
					if (StringUtils.isEmpty(curMap.get("公积金基数"))) {
						error += "公积金基数为空!<br>";
					} else {
						/*
						 * if
						 * (bsfund.getBaseLower().compareTo(Double.parseDouble(
						 * curMap.get("公积金基数"))) > 0) { error +=
						 * "公积金基数低于本地最低标准！<br>"; } if
						 * (bsfund.getBaseUpper().compareTo(Double.parseDouble(
						 * curMap.get("公积金基数"))) < 0) { error +=
						 * "公积金基数高于本地最高标准！<br>"; }
						 */
					}
					if (StringUtils.isEmpty(curMap.get("社保状态"))) {
						error += "社保状态为空！<br>";
					} else if (StringUtils.isEmpty(insfunMap.get(curMap.get("社保状态")))) {
						error += "未找到该社保状态！<br>";
					} else {
						if ("ON".equals(insfunMap.get(curMap.get("社保状态")))) {// 社保状态==正常缴纳
							if (StringUtils.isEmpty(curMap.get("参保人员类别"))) {
								error += "参保人员类别为空！<br>";
							} else if (StringUtils.isEmpty(sysMap.get(curMap.get("参保人员类别")))) {
								error += "未找到该参保人员类别！<br>";
							}
						}
					}
					if (StringUtils.isEmpty(curMap.get("公积金状态"))) {
						error += "公积金状态为空！<br>";
					} else if (StringUtils.isEmpty(insfunMap.get(curMap.get("公积金状态")))) {
						error += "未找到该公积金状态！<br>";
					}
					if (!"".equals(error)) {
						curMap.put("error", error);
						errorDataList.add(curMap);
						isError = true;
					}
					dataList.add(curMap);
				}
			}
			// 关闭
			pkg.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("excel读取出错！");
		}
		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataList", errorDataList);
			result.setDataValue("errorNum", errorDataList.size());
		} else {
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");
					CmEmployee cmpl = new CmEmployee();
					cmpl.setCpId(cpId);
					cmpl.setName(curMap.get("姓名"));
					cmpl.setIdentityCode(curMap.get("身份证号"));
					cmpl.setMobile(curMap.get("手机号"));
					// cmpl.setLivingType(sysDicMap.get(curMap.get("户口性质")));//
					// 字典置换
					cmpl.setIdentityType(curMap.get("证件类型"));// 身份证 ID
					cmpl.setJob(curMap.get("职位"));
					// cmpl.setInsuranceBase(Double.parseDouble(curMap.get("社保基数")));
					// cmpl.setFundBase(Double.parseDouble(curMap.get("公积金基数")));
					cmpl.setInsuranceState(insfunMap.get(curMap.get("社保状态")));// 字典置换
					cmpl.setFundState(insfunMap.get(curMap.get("公积金状态")));// 字典置换
					if (!StringUtils.isEmpty(curMap.get("社保状态")) && "ON".equals(insfunMap.get(curMap.get("社保状态")))) {
						cmpl.setInsuranceLivingType(sysMap.get(curMap.get("参保人员类别")));// 字典置换
					}
					cmpl.setCreateBy(cti.getUserId());
					cmpl.setCreateDt(date);
					cmpl.setDr(0);// 0 正常。
					/*
					 * cmpl.setInsuranceArea(cmep.getInsuranceArea());// 社保缴纳地
					 * cmpl.setFundArea(cmep.getFundArea());// 公积金缴纳地
					 * cmpl.setInsuranceAccountId(cmep.getInsuranceAccountId());
					 * // 社保缴纳账户ID
					 * cmpl.setFundAccountId(cmep.getFundAccountId());//
					 * 公积金缴纳账户ID
					 */

					List<CmEmployee> employees = new ArrayList<CmEmployee>();
					employees.add(cmpl);
					Integer insert = cmEmployeeDao.insert(cti, cmpl);
					if (insert > 0) {
						result.setSuccess(true);
						result.setDataValue("successNum", dataList.size());
					} else {
						result.setSuccess(false);
						result.setError("数据导入错误！");
						break;
					}
				}
			} else {
				result.setError("数据导入错误！");
				result.setSuccess(false);
			}
		}
		return result;
	}

	/**
	 * 新增员工
	 */
	@Override
	public Result saveEmployee(ContextInfo cti, CmEmployee cmEmployee) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null != cmEmployee && StringUtils.isNotEmpty(cmEmployee.getEmpId() + "")
				&& !StringUtil.isBlank(cmEmployee.getIdentityCode())) {

			// 当前服务商 当前企业内 所有员工身份证
			Map<String, Object> idcMap = new HashMap<String, Object>();
			List<CmEmployee> idc = cmEmployeeDao.findIdCardBySpIdCpId(cmEmployee);
			for (CmEmployee cmEmployee2 : idc) {
				idcMap.put(cmEmployee2.getIdentityCode(), cmEmployee2.getName());
			}
			if (null != idcMap.get(cmEmployee.getIdentityCode())
					&& idcMap.get(cmEmployee.getIdentityCode()).equals(cmEmployee.getName())) {
				result.setSuccess(false);
				result.setError("新增失败身份证号与姓名组合重复！");
				return result;
			} else {
				cmEmployee.setCreateBy(cti.getUserId());
				cmEmployee.setCreateDt(date);
				cmEmployee.setDr(0);
				// 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数
				Map<String, Object> mapList = cmEmployeeDao.findInsFunByCpId(cmEmployee.getCpId(), cti.getOrgId());
				// 社保 账户ID 缴交地ID
				if (mapList != null) {
					if (null != mapList.get("insurance_account_id")
							&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
						// cmEmployee.setInsuranceAccountId(Integer.parseInt(mapList.get("insurance_account_id").toString()));
					}
					if (null != mapList.get("insurance_area")
							&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
						// cmEmployee.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").toString()));
						// 取社保基数范围
						/*
						 * BsInsurancerule bsinsurance = bsRuleService
						 * .findRatioAndPayBaseByChildArea(cmEmployee.
						 * getInsuranceArea(), "INSURANCE"); if (null ==
						 * bsinsurance || null == bsinsurance.getBaseLower() ||
						 * null == bsinsurance.getBaseUpper()) { //
						 * result.setError("未查到该参保人员社保基数范围！"); //
						 * result.setSuccess(false); // return result; }else{
						 *//*
							 * if(cmEmployee.getInsuranceBase() != null){ if
							 * (cmEmployee.getInsuranceBase().compareTo(
							 * bsinsurance.getBaseLower()) < 0) {
							 * result.setError("社保基数低于本地最低标准！");
							 * result.setSuccess(false); return result; } if
							 * (cmEmployee.getInsuranceBase().compareTo(
							 * bsinsurance.getBaseUpper()) > 0) {
							 * result.setError("社保基数高于本地最高标准！");
							 * result.setSuccess(false); return result; } }
							 *//*
							 * }
							 */
						cmEmployee.setInsuranceState("OFF");
					}
					// 公积金 账户ID，缴交地ID
					if (null != mapList.get("fund_account_id")
							&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
						// cmEmployee.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
					}
					if (null != mapList.get("fund_area")
							&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
						// cmEmployee.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
						// 取公积金基数范围
						/*
						 * BsInsurancerule bsfund =
						 * bsRuleService.findRatioAndPayBaseByChildArea(
						 * cmEmployee.getFundArea(), "FUND"); if (null == bsfund
						 * || null == bsfund.getBaseLower() || null ==
						 * bsfund.getBaseUpper()) { //
						 * result.setError("未查到该参保人员公积金基数范围！"); //
						 * result.setSuccess(false); // return result; }else{
						 *//*
							 * if(cmEmployee.getFundBase() != null){ if
							 * (cmEmployee.getFundBase().compareTo(bsfund.
							 * getBaseUpper()) > 0) {
							 * result.setError("公积金基数高于本地最高标准！");
							 * result.setSuccess(false); return result; } if
							 * (cmEmployee.getFundBase().compareTo(bsfund.
							 * getBaseLower()) < 0) {
							 * result.setError("公积金基数低于本地最低标准！");
							 * result.setSuccess(false); return result; } }
							 *//*
							 * }
							 */
						cmEmployee.setFundState("OFF");
					}

					// 公积金 账户ID，缴交地ID
					if (null != mapList.get("fund_account_id")
							&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
						// cmEmployee.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
					}
					if (null != mapList.get("fund_area")
							&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
						// cmEmployee.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
						cmEmployee.setFundState("OFF");
					}
				}

				Integer ins = this.insert(cti, cmEmployee);
				List<CmEmployee> employees = new ArrayList<CmEmployee>();
				employees.add(cmEmployee);
				if (ins > 0) {
					result.setSuccess(true);
					if (mapList == null) {
						result.setError("新增员工成功，但企业信息不完善，请完善企业信息！");
					} else {
						result.setError("新增员工成功！");
					}
					return result;
				} else {
					result.setSuccess(false);
					result.setError("新增员工失败！");
					return result;
				}
			}

		} else {
			result.setSuccess(false);
			result.setError("新增员工失败！");
			return result;
		}
	}

	@Override
	public Result updateEmployee(ContextInfo cti, CmEmployee cmEmployee) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null == cmEmployee) {
			result.setSuccess(false);
			result.setError("提交的信息不完整");
			return result;
		}
		if (null == cmEmployee.getEmpId()) {
			result.setSuccess(false);
			result.setError("员工id不能为空");
			return result;
		}
		if (StringUtils.isBlank(cmEmployee.getIdentityCode())) {
			result.setSuccess(false);
			result.setError("员工身份证号不能为空");
			return result;
		}
		if (null == cmEmployee.getCpId()) {
			result.setSuccess(false);
			result.setError("所属企业不能为空");
			return result;
		}
		// 当前服务商 当前企业内 所有员工身份证
		Map<String, Object> idcMap = new HashMap<String, Object>();
		List<CmEmployee> idc = cmEmployeeDao.findIdCardBySpIdCpId(cmEmployee);
		for (CmEmployee cmEmployee2 : idc) {
			// 去除本身信息
			if (cmEmployee2.getEmpId() != cmEmployee.getEmpId()) {
				idcMap.put(cmEmployee2.getIdentityCode(), cmEmployee2.getName());
			}
		}
		if (null != idcMap.get(cmEmployee.getIdentityCode())
				&& idcMap.get(cmEmployee.getIdentityCode()).equals(cmEmployee.getName())) {
			result.setSuccess(false);
			result.setError("修改失败身份证号与姓名组合重复！");
			return result;
		} else {
			cmEmployee.setCreateBy(cti.getUserId());
			cmEmployee.setCreateDt(date);
			cmEmployee.setDr(0);
			// 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数
			Map<String, Object> mapList = cmEmployeeDao.findInsFunByCpId(cmEmployee.getCpId(), cti.getOrgId());
			// 社保 账户ID 缴交地ID
			if (mapList != null) {
				if (null != mapList.get("insurance_account_id")
						&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
					// cmEmployee.setInsuranceAccountId(Integer.parseInt(mapList.get("insurance_account_id").toString()));
				}
				if (null != mapList.get("insurance_area")
						&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
					// cmEmployee.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").toString()));
					// 取社保基数范围
					/*
					 * BsInsurancerule bsinsurance = bsRuleService
					 * .findRatioAndPayBaseByChildArea(cmEmployee.
					 * getInsuranceArea(), "INSURANCE"); if (null == bsinsurance
					 * || null == bsinsurance.getBaseLower() || null ==
					 * bsinsurance.getBaseUpper()) { //
					 * result.setError("未查到该参保人员社保基数范围！"); //
					 * result.setSuccess(false); // return result; }else{
					 *//*
						 * if(cmEmployee.getInsuranceBase() != null){ if
						 * (cmEmployee.getInsuranceBase().compareTo(bsinsurance.
						 * getBaseLower()) < 0) {
						 * result.setError("社保基数低于本地最低标准！");
						 * result.setSuccess(false); return result; } if
						 * (cmEmployee.getInsuranceBase().compareTo(bsinsurance.
						 * getBaseUpper()) > 0) {
						 * result.setError("社保基数高于本地最高标准！");
						 * result.setSuccess(false); return result; } }
						 *//*
						 * }
						 */
				}
				// 公积金 账户ID，缴交地ID
				if (null != mapList.get("fund_account_id")
						&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
					// cmEmployee.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
				}
				if (null != mapList.get("fund_area")
						&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
					/*
					 * cmEmployee.setFundArea(Integer.valueOf(Integer.parseInt(
					 * mapList.get("fund_area").toString()))); // 取公积金基数范围
					 * BsInsurancerule bsfund =
					 * bsRuleService.findRatioAndPayBaseByChildArea(cmEmployee.
					 * getFundArea(), "FUND"); if (null == bsfund || null ==
					 * bsfund.getBaseLower() || null == bsfund.getBaseUpper()) {
					 * // result.setError("未查到该参保人员公积金基数范围！"); //
					 * result.setSuccess(false); // return result; }else{
					 *//*
						 * if(cmEmployee.getFundBase() != null){ if
						 * (cmEmployee.getFundBase().compareTo(bsfund.
						 * getBaseUpper()) > 0) {
						 * result.setError("公积金基数高于本地最高标准！");
						 * result.setSuccess(false); return result; } if
						 * (cmEmployee.getFundBase().compareTo(bsfund.
						 * getBaseLower()) < 0) {
						 * result.setError("公积金基数低于本地最低标准！");
						 * result.setSuccess(false); return result; } }
						 *//*
						 * }
						 */
				}

				// 公积金 账户ID，缴交地ID
				if (null != mapList.get("fund_account_id")
						&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
					// cmEmployee.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
				}
				if (null != mapList.get("fund_area")
						&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
					// cmEmployee.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
				}
			}

			Integer ins = this.update(cti, cmEmployee);
			List<CmEmployee> employees = new ArrayList<CmEmployee>();
			cmEmployee = cmEmployeeDao.findByPK(cmEmployee);
			employees.add(cmEmployee);
			if (ins > 0) {
				result.setSuccess(true);
				if (mapList == null) {
					result.setError("修改员工成功，但企业信息不完善，请完善企业信息！");
				} else {
					result.setError("修改员工成功！");
				}
				return result;
			} else {
				result.setSuccess(false);
				result.setError("修改员工失败！");
				return result;
			}
		}

	}

	@Override
	public CmEmployee findPersonByOpenIdOrMobile(CmEmployee vo, Integer sp_id) {
		return cmEmployeeDao.findPersonByOpenIdOrMobile(vo, sp_id);
	}

	/**
	 * 获取企业人员社保费用类型 社保代扣代缴/公积金代扣代缴/服务费用
	 *
	 * @param spid
	 * @param cmid
	 * @return
	 */
	@Override
	public Map<String, Object> sumCmEmployeeFee(Integer spid, Integer cmid) {
		Map<String, Object> obj = new HashMap<>();
		obj.put("spid", spid);
		obj.put("cmid", cmid);
		return cmEmployeeDao.sumCmEmployeeFee(spid, cmid);
	}

	/**
	 * 获取企业人员正常缴纳列表
	 *
	 * @param spid
	 * @param cmid
	 * @return
	 */
	@Override
	public List<CmEmployee> queryCmEmployeeFeeList(Integer spid, Integer cmid) {
		Map<String, Object> obj = new HashMap<>();
		obj.put("spid", spid);
		obj.put("cmid", cmid);
		return cmEmployeeDao.queryCmEmployeeFeeList(spid, cmid);
	}

	/**
	 * 更新 任务单 状态后 处理企业员工信息
	 *
	 * @author lifq
	 *
	 *         2016年5月30日 下午3:04:56
	 */
	public void dealEmpAfterTask(ContextInfo cti, SpsTask task) throws BusinessException {
		if (null == task) {
			return;
		}
		if (!"COMPLETED".equals(task.getType())) {
			return;
		}
		IdcardValidator idValidator = new IdcardValidator();
		if (2 == task.getBstypeId() || 3 == task.getBstypeId() || 10 == task.getBstypeId()) {// 新增
			// or
			// 转入
			CmEmployee emp = new CmEmployee();
			Map<String, String> jsonMap = JSON.parseObject(task.getJson(), Map.class);
			emp.setCpId(task.getCpId());
			emp.setName(task.getName());
			emp.setIdentityCode(task.getIdentityCode());
			if (idValidator.isValidate18Idcard(jsonMap.get("identity_code"))) {
				emp.setIdentityType("IDCard");
			}
			if (StringUtils.isNotBlank(jsonMap.get("insurancebase"))) {
				/*
				 * if(10 == task.getBstypeId()){
				 * emp.setFundBase(Double.valueOf(jsonMap.get("insurancebase")))
				 * ; emp.setFundState("ON"); }else{
				 * emp.setInsuranceBase(Double.valueOf(jsonMap.get(
				 * "insurancebase"))); emp.setInsuranceState("ON"); }
				 */
			}
			// 判断 人员是否存在
			boolean isExists = false;
			CmEmployee vo = new CmEmployee();
			vo.setCpId(task.getCpId());
			vo.setName(task.getName());
			vo.setIdentityCode(task.getIdentityCode());
			List<CmEmployee> empList = cmEmployeeDao.freeFind(vo);
			if (null != empList && empList.size() > 0) {
				isExists = true;
			}

			// emp.setLivingType(jsonMap.get("livingtype"));

			// 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数
			Map<String, Object> mapList = cmEmployeeDao.findInsFunByCpId(emp.getCpId(), task.getSpId());
			// 公积金 账户ID，缴交地ID
//			if (null != mapList.get("fund_account_id")
//					&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
//				// emp.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
//			}
//			if (null != mapList.get("fund_area")
//					&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
//				// emp.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
//			}
//			// 社保 账户ID 缴交地ID
//			if (null != mapList.get("insurance_account_id")
//					&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
//				// emp.setInsuranceAccountId(Integer.parseInt(mapList.get("insurance_account_id").toString()));
//			}
//			if (null != mapList.get("insurance_area")
//					&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
//				// emp.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").toString()));
//			}

			if (2 == task.getAreaId()) {// 北京 缴费人员类别的编码
				Map<String, String> map = new HashMap<String, String>();
				map.put("本市城镇职工", "LOCALTOWN");
				map.put("外埠城镇职工", "LOCALCOUNTRY");
				map.put("本市农村劳动力", "OUTSIDETOWN");
				map.put("外埠农村劳动力", "OUTSIDECOUNTRY");
				map.put("本市农村劳动力（24号文）", "LOCALCOUNTRY24");
				map.put("有雇工的本市城镇个体工商户", "LOCALTOWNSELFEMPLOYED");
				String medpsnType = jsonMap.get("feepsntype");
				emp.setInsuranceLivingType(map.get(medpsnType));// TODO:获取编码
			} else if (502 == task.getAreaId()) {// 深圳
				String livingtype = jsonMap.get("livingtype");
				if ("1 深户".equals(livingtype)) {
					emp.setInsuranceLivingType("LOCAL");
				} else if ("1 一档".equals(jsonMap.get("basicMedicalInsurance"))) {
					emp.setInsuranceLivingType("OUTSIDE1");
				} else if ("2 二挡".equals(jsonMap.get("basicMedicalInsurance"))) {
					emp.setInsuranceLivingType("OUTSIDE2");
				} else if ("3 三挡".equals(jsonMap.get("basicMedicalInsurance"))) {
					emp.setInsuranceLivingType("OUTSIDE3");
				}
			} else if (802 == task.getAreaId()) {// 成都
				String livingtype = jsonMap.get("livingtype");
				if ("城镇职工".equals(livingtype)) {
					emp.setInsuranceLivingType("TOWN");
				} else if ("原综保本市户籍劳动者".equals(livingtype)) {
					emp.setInsuranceLivingType("GENERALLOCAL");
				} else if ("非本市户籍农民工".equals(livingtype)) {
					emp.setInsuranceLivingType("OUTSIDECOUNTRY");
				}
			}

			emp.setDr(0);// 0:未删除 1：已删除
			if (isExists) {
				cmEmployeeDao.update(cti, emp);
			} else {
				cmEmployeeDao.save(cti, emp);
			}

			// 员工生成后 调用算费
			List<CmEmployee> datas = new ArrayList<CmEmployee>();
		} else if (4 == task.getBstypeId() || 11 == task.getBstypeId()) {// 减员
			CmEmployee vo = new CmEmployee();
			vo.setCpId(task.getCpId());
			vo.setName(task.getName());
			vo.setIdentityCode(task.getIdentityCode());
			List<CmEmployee> empList = cmEmployeeDao.freeFind(vo);
			if (null != empList && empList.size() > 0) {
				CmEmployee emp = empList.get(0);
				emp.setInsuranceState("OFF");
				emp.setFundState("OFF");
				emp.setDr(1);// 0:未删除 1：已删除
				cmEmployeeDao.update(cti, emp);
			}
		}

	}

	/**
	 * 当企业改动时更新员工 社保 公积金 缴纳地区 社保公积金
	 *
	 * @param cpId
	 * @return
	 */
	/*
	 * @Override public int updateEmployee(Integer cpId, ContextInfo sys) {
	 * CmEmployee vo = new CmEmployee(); if (null != sys && null !=
	 * cti.getOrgId() && null != cpId) { Date date = new Date();
	 * vo.setCpId(cpId); vo.setModifyBy(cti.getOrgId()); vo.setModifyDt(date);
	 * // 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数 Map<String, Object>
	 * mapList = cmEmployeeDao.findInsFunByCpId(cpId, cti.getOrgId()); // 公积金
	 * 账户ID，缴交地ID if(null!=mapList){ if (null != mapList.get("fund_account_id")
	 * &&
	 * "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName(
	 * ))) { //
	 * vo.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").
	 * toString())); } if (null != mapList.get("fund_area") &&
	 * "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
	 * //
	 * vo.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").
	 * toString()))); } // 社保 账户ID 缴交地ID if (null !=
	 * mapList.get("insurance_account_id") &&
	 * "Integer".equals(mapList.get("insurance_account_id").getClass().
	 * getSimpleName())) { //
	 * vo.setInsuranceAccountId(Integer.parseInt(mapList.get(
	 * "insurance_account_id").toString())); } if (null !=
	 * mapList.get("insurance_area") &&
	 * "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName()
	 * )) { //
	 * vo.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").
	 * toString())); } }
	 *
	 * Integer ins = cmEmployeeDao.updateByCpId(vo); if (0 < ins) { //
	 * 查询当前企业下所有员工list vo.setDr(0);// 正常员工 List<CmEmployee> datas =
	 * cmEmployeeDao.freeFind(vo); // 调用算费 calcFeeHandle.push(cti.getOrgId(),
	 * datas); return ins; } else { return 0; } } else { return 0; } }
	 */

	/**
	 * 查询 社保缴交地、社保库ID、公积金缴交地、公积金库ID
	 *
	 * @param cpId
	 *            企业ID
	 * @param spId
	 *            服务商ID
	 * @param status
	 *            状态值 新增1 新增要加一些条件
	 * @return
	 */
	public List<Map<String, Object>> mapListcpIdspId(Integer cpId, Integer spId, Integer status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapList = cmEmployeeDao.findInsFunByCpId(cpId, spId);
		if (null != mapList.get("fund_account_id")
				&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
			map.put("funAccId", Integer.parseInt(mapList.get("fund_account_id").toString()));
			list.add(map);
		}
		if (null != mapList.get("fund_area") && "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
			map.put("fundArea", Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
			if (null != status && 1 == status) {
				map.put("fundState", "OFF");
			}
		}
		// 社保 账户ID 缴交地ID
		if (null != mapList.get("insurance_account_id")
				&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
			map.put("insAccId", Integer.parseInt(mapList.get("insurance_account_id").toString()));
		}
		if (null != mapList.get("insurance_area")
				&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
			map.put("insArea", Integer.parseInt(mapList.get("insurance_area").toString()));
			if (null != status && 1 == status) {
				map.put("insuState", "OFF");
			}
		}
		list.add(map);
		return list;
	}

	/**
	 * 定时任务，每月一号凌晨，初始化当月现有员工费用
	 */

	@Override
	public void initCalcFeeMonthly() {
		while (RedisUtil.getLock(SAAS_FEE_CALC_SYNCHRONIZED_LOCK, 60000L) != 1) {// 获取当前锁
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<Map<String, Object>> allSpEmployees = cmEmployeeDao.findAllSpEmployee();
		if (null != allSpEmployees && !allSpEmployees.isEmpty()) {
			Map<Integer, List<CmEmployee>> spEmployees = new HashMap<>();
			for (Map<String, Object> employee : allSpEmployees) {
				List<CmEmployee> employees = spEmployees.get(Integer.parseInt(String.valueOf(employee.get("sp_id"))));
				if (null == employees) {
					employees = new ArrayList<>();
					spEmployees.put(Integer.parseInt(String.valueOf(employee.get("sp_id"))), employees);
				}
				employees.add(JSON.parseObject(JSON.toJSONString(employee), CmEmployee.class));
			}
		}
		RedisUtil.delLock(SAAS_FEE_CALC_SYNCHRONIZED_LOCK); // 释放锁
	}

	/**
	 * 根据库id获取所有库下的员工
	 *
	 * @param vo
	 * @return
	 */
	public List<CmEmployee> findEmployeeByAccount(SpsAccount vo) {
		return cmEmployeeDao.findEmployeeByAccount(vo);
	}

	@Override
	public Result readImportFileInfo(Integer fileId, ContextInfo cti) throws Exception {
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (StringUtils.isBlank(filePath)) {
			result.setError("文件不存在！");
			return result;
		}
		// 查询 excel中 sheet页 级 标题行
		Map<String, Object> sheetData = readExcelTitle(filePath, cti);
		if (!sheetData.isEmpty()) {
			result.setDataValue("sheetData", sheetData);
			result.setSuccess(true);
		} else {
			result.setError("没有可用的内容");
		}
		return result;
	}

	@Override
	public Result readTemplateRealtion(Integer fileId, String sheetName, String corpExist) throws Exception {
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (StringUtils.isBlank(filePath)) {
			result.setError("文件不存在！");
			return result;
		}
		// 根据 业务类型 查询 字段信息
		List<Map<String, String>> sysfields = new ArrayList<Map<String, String>>();
		// sysfields.add(getEmployeeFieldMap(corpExist));

		sysfields.add(getEmployeeFieldMap());
		Map<String, Map<String, Map<String, Object>>> resObj = ImportExcelUtil.readExcelTitle(filePath, sysfields, null,
				null, null, sheetName);
		result.setDataValue("sheetData", resObj);
		// result.setDataValue("isShowSelectSheet",
		// resObj.size()<=1?"0":"1");先去掉这个，如果只有一个sheet时 显示默认选中

		result.setSuccess(true);
		return result;
	}

	/**
	 * 读取Excel列表
	 *
	 * @param filePath
	 *            文件路径
	 * @return Map<String,List<String>> key : sheet名称 value : 标题列表
	 */
	public Map<String, Object> readExcelTitle(String filePath, ContextInfo cti) throws Exception {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		excelFiles.add(new File(filePath));
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			xwb = new XSSFWorkbook(fin);
			// 读取表格内容
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				List<String> titleList = new ArrayList<String>();
				// 遇到隐藏的sheet页 不读取
				boolean sheethide = xwb.isSheetHidden(i);
				if (sheethide) {
					continue;
				}
				Sheet sheet = xwb.getSheetAt(i);
				// 定义 row、cell
				// 循环输出表格中的内容
				Row row = sheet.getRow(0);
				if (null != row && row.getPhysicalNumberOfCells() > 1) {// row可能为空
					Map<String, Object> sheetMap = new HashMap<String, Object>();
					boolean corpExist = false;
					int corpIndex = 0;
					for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
						String cellContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
						if (StringUtils.isNotBlank(cellContent)) {
							titleList.add(cellContent);
							// 是否存在企业名称列
							if (cellContent.equals("企业名称")) {
								corpExist = true;
								corpIndex = j;
							}
						}
					}
					if (null != titleList && titleList.size() > 0) {
						sheetMap.put("titleList", titleList);
					}
					// 不存在的企业名称
					List<String> noExistCorp = new ArrayList<String>();
					if (corpExist) {
						Set<String> corpNames = new HashSet<String>();
						for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
							Row jrow = sheet.getRow(j);
							if (jrow == null) {
								continue;
							}
							if (null != jrow.getCell(corpIndex)) {
								corpNames.add(jrow.getCell(corpIndex).toString().trim());
							}
						}
						CmCorp corp = new CmCorp();
						for (String name : corpNames) {
							corp.setCorpName(name);
							corp.setSpId(cti.getOrgId());
							if (cmCorpDao.findCorpByCorpName(corp) == 0) {
								noExistCorp.add(name);
							}
						}
					}
					if (CollectionUtils.isNotEmpty(noExistCorp)) {
						sheetMap.put("noExistCorp", noExistCorp);
						sheetMap.put("noExistCorpSize", noExistCorp.size());
					}
					// sheet格式没问题则返回
					if (!sheetMap.isEmpty()) {
						sheetMap.put("corpExist", corpExist);
						result.put(sheet.getSheetName(), sheetMap);
					}
				}

			}
			fin.close();
		}
		return result;
	}

	/**
	 * 返回员工字段信息集合
	 *
	 * @return
	 */
	private Map<String, String> getEmployeeFieldMap(String corpExist) {
		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put("姓名", "name");
		fieldMap.put("证件号码", "identityCode");
		fieldMap.put("证件类型", "identityType");
		// fieldMap.put("职位","job");
		fieldMap.put("手机号", "mobile");
		// fieldMap.put("户口性质","household");
		// fieldMap.put("社保基数","insuranceBase");
		// fieldMap.put("公积金基数","fundBase");
		/*
		 * fieldMap.put("社保状态","insuranceState");
		 * fieldMap.put("公积金状态","fundState");
		 */
		fieldMap.put("参保地区", "insuranceArea");
		fieldMap.put("参保人员类别", "insuranceLivingType");
		fieldMap.put("方案", "schemeName");
		fieldMap.put("公积金申报工资", "fundSalary");
		fieldMap.put("社保申报工资", "insuranceSalary");
		fieldMap.put("邮箱", "email");

		if ("true".equals(corpExist)) {
			fieldMap.put("企业名称", "corpName");
		}
		return fieldMap;
	}

	/**
	 * 返回员工字段信息集合 2016-11-1 修改版
	 *
	 * @return
	 */
	private Map<String, String> getEmployeeFieldMap() {
		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put("企业名称", "corpName");
		fieldMap.put("姓名", "name");
		fieldMap.put("证件类型", "identityType");
		fieldMap.put("证件号码", "identityCode");
		fieldMap.put("户口性质", "livingType");
		fieldMap.put("社保申报工资", "insuranceBase");
		fieldMap.put("社保开始缴纳月份", "insuBeginMonth");
		fieldMap.put("社保状态", "insuranceState");
		fieldMap.put("公积金申报工资", "fundBase");
		fieldMap.put("公积金开始缴纳月份", "fundBeginMonth");
		fieldMap.put("公积金状态", "fundState");
		fieldMap.put("方案名称", "schemeName");

		fieldMap.put("养老基数", InsuranceEnums.PENSION.getCode() + "Base");
		fieldMap.put("养老起始月", InsuranceEnums.PENSION.getCode() + "BeginDate");
		fieldMap.put("医疗基数", InsuranceEnums.MEDICINE.getCode() + "Base");
		fieldMap.put("医疗起始月", InsuranceEnums.MEDICINE.getCode() + "BeginDate");
		fieldMap.put("失业基数", InsuranceEnums.UNEMPLOYMENT.getCode() + "Base");
		fieldMap.put("失业起始月", InsuranceEnums.UNEMPLOYMENT.getCode() + "BeginDate");
		fieldMap.put("生育基数", InsuranceEnums.MATERNITY.getCode() + "Base");
		fieldMap.put("生育起始月", InsuranceEnums.MATERNITY.getCode() + "BeginDate");
		fieldMap.put("工伤基数", InsuranceEnums.INJURY.getCode() + "Base");
		fieldMap.put("工伤起始月", InsuranceEnums.INJURY.getCode() + "BeginDate");
		return fieldMap;
	}

	/**
	 * 2016-11-1 版本导入员工
	 *
	 * @param fileId
	 * @param cti
	 * @param selectContent
	 * @return
	 */
	public Result importEmpExcel(ContextInfo cti, Integer fileId, String selectContent) throws BusinessException {
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
		if (StringUtils.isEmpty(selectContent)) {
			result.setSuccess(false);
			result.setError("对应关系不能为空！");
			return result;
		}
		// 前端传来标题匹配字段<K,V>
		Map<String, String> colNameMap = new HashMap<String, String>();
		String selectArr[] = selectContent.split(",");
		if (null != selectArr) {
			for (int i = 0; i < selectArr.length; i++) {
				String itemStr = selectArr[i];
				String[] itemArr = itemStr.split("=");
				if (null != itemArr) {
					colNameMap.put(itemArr[0], itemArr[1]);
				}
			}
		}

		Map<String, Map<String, String>> sheet_orm = new HashMap<String, Map<String, String>>();
		List<String> sheelNames;
		try {
			sheelNames = ImportExcelUtil.getSheelNames(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("读取excel Sheel名称异常！");
		}
		Map<String, Object> paraMap = new HashMap<String, Object>(0);
		paraMap.put("sp_id", cti.getOrgId());
		List<Map<String, Object>> cityMap = spsSchemeService.findAllSchemeArea(paraMap);
		if (cityMap == null || cityMap.isEmpty()) {
			result.setSuccess(false);
			result.setError("您还未配置的您的服务城市！");
			return result;
		}
		Map<String, Integer> spServiceArea = new HashMap<>();
		for (Map<String, Object> city : cityMap) {
			spServiceArea.put(city.get("city_name").toString(), (Integer) city.get("city_id"));
		}
		for (String sheelName : sheelNames) {
			if (spServiceArea.containsKey(sheelName)) {
				sheet_orm.put(sheelName, colNameMap);
			}
		}
		if (sheet_orm.isEmpty()) {
			result.setSuccess(false);
			result.setError("未在文件中找到您的服务城市，请检查！");
			return result;
		}
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<String, List<Map<String, String>>> errorDataMap = new HashMap<>();
		Map<String, Map<Integer, Map<String, String>>> sheelMap;
		try {
			sheelMap = ImportExcelUtil.importSheelExcel(filePath, getEmployeeFieldMap(), sheet_orm);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}
		// 户口性质 农业 城镇
		Map<String, String> sysDicMap = new HashMap<String, String>();
		List<SysDictitem> listdic = sysDictitemService.findByDictName("liveType");
		for (SysDictitem sysDict : listdic) {
			sysDicMap.put(sysDict.getName(), sysDict.getCode());
		}
		// 证件类型
		SysDictitem queryDict = new SysDictitem();
		queryDict.setDictionary(89);
		listdic = sysDictitemService.findAll(queryDict);
		Map<String, String> IdTypeMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(listdic)) {
			for (SysDictitem sysDict : listdic) {
				IdTypeMap.put(sysDict.getName(), sysDict.getCode());
			}
		}

		// 查询当前服务商 当前企业 有效员工
		Map<String, String> idCardMap = new HashMap<String, String>();
		// 查询用员工类
		CmEmployee cmep = new CmEmployee();

		// 社保状态 公积金状态 导入Excel里只有这三项
		Map<String, String> insfunMap = new HashMap<String, String>();
		insfunMap.put("在缴中", "ON");
		insfunMap.put("未缴纳", "OFF");
		insfunMap.put("暂不操作", "UNDEAL");

		CmCorp cmCorp = null;
		if (sheelMap.isEmpty()) {
			result.setSuccess(false);
			result.setError("未在文件中获得数据！");
			return result;
		}
		// 校验身份证号
		IdcardValidator iv = new IdcardValidator();
		// 保存用日期
		Date date = new Date();
		// 数据是否有误
		boolean isError = false;
		// 数据错误条数
		Integer errorNum = 0;
		// 查询出企业信息集合
		Map<String, Object> corpQuery = new HashMap<>();
		corpQuery.put("spId", cti.getOrgId());
		corpQuery.put("authority", "ALL");
		corpQuery.put("dr", 0);
		List<Map<String, Object>> corpList = (List<Map<String, Object>>) cmCorpDao
				.queryForList("CM_CORP.FindCorpBySpId", corpQuery);
		Map<String, Integer> corpNameIdMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(corpList)) {
			for (Map<String, Object> corp : corpList) {
				corpNameIdMap.put(corp.get("cpName").toString(), (Integer) corp.get("cpId"));
			}
		}
		// 员工险种集合 <schemeId+beginDate+type,[险种]>
		Map<String, List<InsuranceTypeDto>> InsuranceTypeMap = new HashMap<>();
		// 企业方案集合
		// <企业id，<方案名称，[方案id,地区id]>>
		Map<Integer, Map<String, SpsScheme>> schemeMap = new HashMap<>();
		SimpleDateFormat monthSdf = new SimpleDateFormat("yyyy.MM");
		SimpleDateFormat monthSdf1 = new SimpleDateFormat("yyyy-MM");
		// 月份校验
		String pattern = "^[1-2][0-9][0-9][0-9]\\.[0-1][0-9]$";
		Pattern monP = Pattern.compile(pattern);
		for (Map.Entry<String, Map<Integer, Map<String, String>>> sheel : sheelMap.entrySet()) {
			Integer areaId = spServiceArea.get(sheel.getKey());
			List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
			// 查询 参保 人员类别 modify by lifq 20160914
			Map<String, String> insuranceLiveTypeMap = new HashMap<String, String>();
			BusinessField businessField = bdBusinessfieldService.findLivingType(areaId);
			if (businessField == null || businessField.getOptions() == null || businessField.getOptions().size() < 1) {
				result.setSuccess(false);
				result.setError("未找到" + sheel.getKey() + "下户口性质，请配置！");
				return result;
			}
			for (SysDictitem sysDict : businessField.getOptions()) {
				insuranceLiveTypeMap.put(sysDict.getName(), sysDict.getCode());
			}
			for (Map.Entry<Integer, Map<String, String>> entry : sheel.getValue().entrySet()) {
				Map<String, String> curMap = entry.getValue();
				String error = "";
				curMap.put("row", entry.getKey() + "");
				Integer cpId = null;
				if (StringUtils.isEmpty(curMap.get("corpName")) || StringUtils.isEmpty(curMap.get("corpName").trim())) {
					error += "企业名称为空！<br>";
				} else {
					if (corpNameIdMap.containsKey(curMap.get("corpName").trim())) {
						curMap.put("cpId", corpNameIdMap.get(curMap.get("corpName").trim()).toString());
						cpId = corpNameIdMap.get(curMap.get("corpName").trim());
					} else {
						error += "该企业不存在！<br>";
					}
				}
				String name = null;
				if (StringUtils.isEmpty(curMap.get("name")) || StringUtils.isEmpty(curMap.get("name").trim())) {
					error += "姓名为空！<br>";
				} else {
					name = curMap.get("name").trim();
				}

				if (StringUtils.isEmpty(curMap.get("identityCode"))
						|| StringUtils.isEmpty(curMap.get("identityCode").trim())) {
					error += "证件号为空！<br>";
				} else {
					String idcar = curMap.get("identityCode").trim();
					// 校验唯一性 名称+证件号
					String idVerify = idcar + name;
					if (StringUtils.isBlank(curMap.get("identityType"))
							|| StringUtils.isEmpty(curMap.get("identityType").trim())) {
						error += "请选择证件类型！<br>";
					} else if (curMap.get("identityType").trim().equals("身份证")) {
						if (!iv.isValidate18Idcard(idcar) && !iv.isValidate15Idcard(idcar)) {
							error += "身份证号格式错误！<br>";
						} else if ("Excel".equals(idCardMap.get(idVerify))) {
							error += "导入Excel表中员工身份证已存在！<br>";
						} else if (cpId != null
								&& name != null & this.checkEpIdcardByCpidAndIdcardAndName(cpId, idcar, name)) {
							error += "员工在该企业已存在，请注意！<br>";
						} else {
							idCardMap.put(idVerify, "Excel");
							curMap.put("identityType", "IDCard");// 证件类型 IDType
							// 身份证：ID
						}
					} else {
						if (StringUtils.isBlank(IdTypeMap.get(curMap.get("identityType").trim()))) {
							error += "证件类型不存在！<br>";
						} else if ("Excel".equals(idCardMap.get(idVerify))) {
							error += "导入Excel表中员工证件类型下证件号码已存在！<br>";
						} else {
							idCardMap.put(idVerify, "Excel");
							curMap.put("identityType", IdTypeMap.get(curMap.get("identityType").trim()));// 证件类型
							// IDType
							// 身份证：ID
							if (cpId != null
									&& name != null & this.checkEpIdcardByCpidAndIdcardAndName(cpId, idcar, name)) {
								error += "员工在该企业已存在，请注意！<br>";
							}
						}
					}
				}
				String livingType = null;
				if (StringUtils.isNotEmpty(curMap.get("livingType"))
						&& StringUtils.isNotEmpty(curMap.get("livingType").trim())) {
					if (StringUtils.isEmpty(insuranceLiveTypeMap.get(curMap.get("livingType").trim()))) {
						error += "未找到该户口性质！<br>";
					} else {
						livingType = insuranceLiveTypeMap.get(curMap.get("livingType").trim());
						curMap.put("livingTypeCode", insuranceLiveTypeMap.get(curMap.get("livingType").trim()));
					}
				} else {
					error += "户口性质为空！<br>";
				}
				// 社保申报工资 缴纳月份 社保状态 有一项为空 则 社保状态为不操作
				Boolean insuFalse = false;
				Object pensionBase = curMap.get(InsuranceEnums.PENSION.getCode() + "Base");
				Object medicineBase = curMap.get(InsuranceEnums.MEDICINE.getCode() + "Base");
				Object injuryBase = curMap.get(InsuranceEnums.INJURY.getCode() + "Base");
				Object unemploymentBase = curMap.get(InsuranceEnums.UNEMPLOYMENT.getCode() + "Base");
				Object maternityBase = curMap.get(InsuranceEnums.MATERNITY.getCode() + "Base");
				Object pensionBeginDate = curMap.get(InsuranceEnums.PENSION.getCode() + "BeginDate");
				Object medicineBeginDate = curMap.get(InsuranceEnums.MEDICINE.getCode() + "BeginDate");
				Object injuryBeginDate = curMap.get(InsuranceEnums.INJURY.getCode() + "BeginDate");
				Object unemploymentBeginDate = curMap.get(InsuranceEnums.UNEMPLOYMENT.getCode() + "BeginDate");
				Object maternityBeginDate = curMap.get(InsuranceEnums.MATERNITY.getCode() + "BeginDate");

				if (pensionBase != null && StringUtils.isNotBlank(pensionBase.toString().trim()) && !NumberUtils.isNumber( pensionBase.toString().trim() )
						&& "最低".equals(pensionBase.toString().trim()) && "下限".equals(pensionBase.toString().trim()) && "不缴纳".equals(pensionBase.toString().trim()) ){
					error += "养老基数非数字类型！<br>";
				}
				if (medicineBase != null && StringUtils.isNotBlank(medicineBase.toString().trim()) && !NumberUtils.isNumber( medicineBase.toString().trim() )
						&& "最低".equals(medicineBase.toString().trim()) && "下限".equals(medicineBase.toString().trim()) && "不缴纳".equals(medicineBase.toString().trim()) ){
					error += "医疗基数非数字类型！<br>";
				}
				if (injuryBase != null && StringUtils.isNotBlank(injuryBase.toString().trim()) && !NumberUtils.isNumber( injuryBase.toString().trim() )
						&& "最低".equals(injuryBase.toString().trim()) && "下限".equals(injuryBase.toString().trim()) && "不缴纳".equals(injuryBase.toString().trim()) ){
					error += "工伤基数非数字类型！<br>";
				}
				if (unemploymentBase != null && StringUtils.isNotBlank(unemploymentBase.toString().trim()) && !NumberUtils.isNumber( unemploymentBase.toString().trim() )
						&& "最低".equals(unemploymentBase.toString().trim()) && "下限".equals(unemploymentBase.toString().trim()) && "不缴纳".equals(unemploymentBase.toString().trim()) ){
					error += "失业基数非数字类型！<br>";
				}
				if (maternityBase != null && StringUtils.isNotBlank(maternityBase.toString().trim()) && !NumberUtils.isNumber( maternityBase.toString().trim() )
						&& "最低".equals(maternityBase.toString().trim()) && "下限".equals(maternityBase.toString().trim()) && "不缴纳".equals(maternityBase.toString().trim()) ){
					error += "生育基数非数字类型！<br>";
				}

				if (StringUtils.isEmpty(curMap.get("insuranceBase"))
						|| StringUtils.isEmpty(curMap.get("insuranceBase").trim())) {
					insuFalse = true;

					if ( pensionBeginDate != null && StringUtils.isNotBlank(pensionBeginDate.toString().trim())
							|| medicineBeginDate != null && StringUtils.isNotBlank(medicineBeginDate.toString().trim())
							|| injuryBeginDate != null && StringUtils.isNotBlank(injuryBeginDate.toString().trim())
							|| unemploymentBeginDate != null && StringUtils.isNotBlank(unemploymentBeginDate.toString().trim())
							|| maternityBeginDate != null && StringUtils.isNotBlank(maternityBeginDate.toString().trim())
							|| pensionBase != null && StringUtils.isNotBlank(pensionBase.toString().trim())
							|| medicineBase != null && StringUtils.isNotBlank(medicineBase.toString().trim())
							|| injuryBase != null && StringUtils.isNotBlank(injuryBase.toString().trim())
							|| unemploymentBase != null && StringUtils.isNotBlank(unemploymentBase.toString().trim())
							|| maternityBase != null && StringUtils.isNotBlank(maternityBase.toString().trim()) ){
						error += "“社保申报工资”必需填写！<br>";
					}

				} else if (!NumberUtils.isNumber(curMap.get("insuranceBase").trim())) {
					error += "社保申报工资非数字类型！<br>";
				}
				// yyyy-MM
				String insuBeginMonth = null;
				if (StringUtils.isEmpty(curMap.get("insuBeginMonth"))
						|| StringUtils.isEmpty(curMap.get("insuBeginMonth").trim())) { // 社保月份为空，则判断是否社保其余月份都填
					insuFalse = true;

					if ( pensionBeginDate != null && StringUtils.isNotBlank(pensionBeginDate.toString().trim())
							|| medicineBeginDate != null && StringUtils.isNotBlank(medicineBeginDate.toString().trim())
							|| injuryBeginDate != null && StringUtils.isNotBlank(injuryBeginDate.toString().trim())
							|| unemploymentBeginDate != null && StringUtils.isNotBlank(unemploymentBeginDate.toString().trim())
							|| maternityBeginDate != null && StringUtils.isNotBlank(maternityBeginDate.toString().trim())
							|| pensionBase != null && StringUtils.isNotBlank(pensionBase.toString().trim())
							|| medicineBase != null && StringUtils.isNotBlank(medicineBase.toString().trim())
							|| injuryBase != null && StringUtils.isNotBlank(injuryBase.toString().trim())
							|| unemploymentBase != null && StringUtils.isNotBlank(unemploymentBase.toString().trim())
							|| maternityBase != null && StringUtils.isNotBlank(maternityBase.toString().trim()) ){ // 五险中有一个填写，则月份必填
						error += "“社保开始缴纳月份”必需填写！<br>";
					}

				} else {
					if (!monP.matcher(curMap.get("insuBeginMonth").trim()).matches()) {
						error += "社保开始缴纳月份格式错误！<br>";
					} else {
						try {
							insuBeginMonth = monthSdf1.format(monthSdf.parse(curMap.get("insuBeginMonth").trim()));
						} catch (ParseException e) {
							error += "社保开始缴纳月份格式错误！<br>";
						}
					}
				}
				if (StringUtils.isEmpty(curMap.get("insuranceState"))
						|| StringUtils.isEmpty(curMap.get("insuranceState").trim())) {
					insuFalse = true;
					curMap.put("insuranceStateCode", "UNDEAL");
				} else {
					String insuState = curMap.get("insuranceState").trim();
					if (insuFalse) {
						curMap.put("insuranceStateCode", "UNDEAL");
					} else if ("未缴纳".equals(insuState)) {
						curMap.put("insuranceStateCode", "OFF");
					} else if ("在缴中".equals(insuState) || "缴纳中".equals(insuState)) {
						curMap.put("insuranceStateCode", "ON");
					} else if ("暂不操作".equals(insuState)) {
						curMap.put("insuranceStateCode", "UNDEAL");
					} else {
						error += "社保状态不正确！<br>";
					}
				}

				// 公积金申报工资 缴纳月份 社保状态 有一项为空 则 社保状态为不操作
				Boolean fundFalse = false;
				if (StringUtils.isEmpty(curMap.get("fundBase")) || StringUtils.isEmpty(curMap.get("fundBase").trim())) {
					fundFalse = true;
				} else if (!NumberUtils.isNumber(curMap.get("fundBase").trim())) {
					error += "公积金申报工资非数字类型！<br>";
				}
				// yyyy-MM
				String fundBeginMonth = null;
				if (StringUtils.isEmpty(curMap.get("fundBeginMonth"))
						|| StringUtils.isEmpty(curMap.get("fundBeginMonth").trim())) {
					fundFalse = true;
				} else {
					if (!monP.matcher(curMap.get("fundBeginMonth").trim()).matches()) {
						error += "公积金开始缴纳月份格式错误！<br>";
					} else {
						try {
							fundBeginMonth = monthSdf1.format(monthSdf.parse(curMap.get("fundBeginMonth").trim()));
						} catch (ParseException e) {
							error += "公积金开始缴纳月份格式错误！<br>";
						}
					}
				}
				if (StringUtils.isEmpty(curMap.get("fundState"))
						|| StringUtils.isEmpty(curMap.get("fundState").trim())) {
					fundFalse = true;
					curMap.put("fundStateCode", "UNDEAL");
				} else {
					String fundState = curMap.get("fundState").trim();
					if (fundFalse) {
						curMap.put("fundStateCode", "UNDEAL");
					} else if ("未缴纳".equals(fundState)) {
						curMap.put("fundStateCode", "OFF");
					} else if ("在缴中".equals(fundState) || "缴纳中".equals(fundState)) {
						curMap.put("fundStateCode", "ON");
					} else if ("暂不操作".equals(fundState)) {
						curMap.put("fundStateCode", "UNDEAL");
					} else {
						error += "公积金状态不正确！<br>";
					}
				}
				if (StringUtils.isEmpty(curMap.get("schemeName"))
						|| StringUtils.isEmpty(curMap.get("schemeName").trim())) {
					error += "方案名称不能为空！<br>";
				} else {
					// 企业不为空查询方案
					if (cpId != null) {
						// 先取缓存
						Map<String, SpsScheme> schemes = schemeMap.get(cpId);
						// 查数据库
						if (schemes == null) {
							SpsScheme sQuery = new SpsScheme();
							sQuery.setCpId(cpId);
							sQuery.setSpId(cti.getOrgId());
							sQuery.setStateEq("USE");
							List<SpsScheme> schemeList = spsSchemeDao.freeFind(sQuery);
							schemes = new HashMap<>();
							if (CollectionUtils.isNotEmpty(schemeList)) {
								for (SpsScheme scheme : schemeList) {
									schemes.put(scheme.getName(), scheme);
								}
							}
							schemeMap.put(cpId, schemes);
						}
						Integer schemeId = null;
						if (!schemes.containsKey(curMap.get("schemeName").trim())) {
							error += "方案名称不正确！<br>";
						} else {
							if (schemes.get(curMap.get("schemeName").trim()).getAreaId() == null) {
								error += "请配置该方案下的地区！<br>";
							} else if (!schemes.get(curMap.get("schemeName").trim()).getAreaId().equals(areaId)) {
								error += "参保地区与方案地区不一致！<br>";
							} else {
								curMap.put("schemeId",
										schemes.get(curMap.get("schemeName").trim()).getSchemeId().toString());
								schemeId = schemes.get(curMap.get("schemeName").trim()).getSchemeId();
							}
						}
						// 险种
						if (schemeId != null && !isError) {
							SpsScheme scheme = schemes.get(curMap.get("schemeName").trim());
							if ("ON".equals(curMap.get("insuranceStateCode")) && livingType != null
									&& insuBeginMonth != null) {
								String insuKey = schemeId + curMap.get("insuBeginMonth").trim() + "INSU" + livingType;
								List<InsuranceTypeDto> list = InsuranceTypeMap.get(insuKey);
								// 未从数据库中查询
								if (list == null) {
									list = this.queryAccountInsuranceListByAccountId(scheme.getInsuranceAccountId(),
											livingType, insuBeginMonth);
									if (CollectionUtils.isEmpty(list)) {
										error += "未找到方案下社保险种！<br>";
										InsuranceTypeMap.put(insuKey, new ArrayList<InsuranceTypeDto>());
									} else {
										InsuranceTypeMap.put(insuKey, list);
									}
								} else if (list.isEmpty()) {// 查询后依然是空 防止多次访问
									// 数据库
									error += "未找到方案下社保险种！<br>";
								}
							}
							if ("ON".equals(curMap.get("fundStateCode")) && fundBeginMonth != null) {
								String insuKey = schemeId + curMap.get("fundBeginMonth").trim() + "FUND";
								List<InsuranceTypeDto> list = InsuranceTypeMap.get(insuKey);
								if (list == null) {
									list = this.queryAccountInsuranceListByAccountId(scheme.getFundAccountId(), null,
											fundBeginMonth);
									if (CollectionUtils.isEmpty(list)) {
										error += "未找到方案下公积金险种！<br>";
										InsuranceTypeMap.put(insuKey, new ArrayList<InsuranceTypeDto>());
									} else {
										InsuranceTypeMap.put(insuKey, list);
									}
								} else if (list.isEmpty()) {
									error += "未找到方案下社保险种！<br>";
								}
							}
						}
					}
				}

				if (!"".equals(error)) {
					curMap.put("error", error);
					errorDataList.add(curMap);
					isError = true;
					errorNum++;
				}
				dataList.add(curMap);
			}
			errorDataMap.put(sheel.getKey(), errorDataList);
		}
		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataMap", errorDataMap);
			result.setDataValue("errorNum", errorNum);
		} else {
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {

				for (int i = 0; i < dataList.size(); i++) {
					Boolean dbExist = false;
					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");

					CmEmployee cmpl = new CmEmployee();
					cmpl.setCpId(Integer.parseInt(curMap.get("cpId")));
					cmpl.setName(curMap.get("name").trim());
					cmpl.setIdentityCode(curMap.get("identityCode").trim());
					cmpl.setIdentityType(curMap.get("identityType").trim());// 证件类型
					cmpl.setInsuranceLivingType(curMap.get("livingTypeCode"));
					cmpl.setInsuranceState(curMap.get("insuranceStateCode"));
					cmpl.setFundState(curMap.get("fundStateCode"));
					cmpl.setSchemeId(Integer.parseInt(curMap.get("schemeId")));
					// 添加公积金申报工资
					if (StringUtils.isNotBlank(curMap.get("insuranceBase"))
							&& StringUtils.isNotBlank(curMap.get("insuranceBase").trim())) {
						cmpl.setInsuranceSalary(new BigDecimal(curMap.get("insuranceBase").trim()));
					}

					if (StringUtils.isNotBlank(curMap.get("fundBase"))
							&& StringUtils.isNotBlank(curMap.get("fundBase").trim())) {
						cmpl.setFundSalary(new BigDecimal(curMap.get("fundBase").trim()));
					}
					cmpl.setCreateBy(cti.getUserId());
					cmpl.setCreateDt(date);
					cmpl.setDr(0);// 0 正常。

					Integer insert = cmEmployeeDao.insert(cti, cmpl);

					if (insert > 0) {
						result.setSuccess(true);
					} else {
						result.setSuccess(false);
						result.setError("数据导入错误！");
						break;
					}
					SpsSchemeEmp schemeEmpCondition = new SpsSchemeEmp();
					schemeEmpCondition.setEmpId(cmpl.getEmpId());
					schemeEmpCondition.setSchemeId(cmpl.getSchemeId());
					List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(schemeEmpCondition);

					SpsScheme spsScheme = new SpsScheme();
					spsScheme.setSchemeId(cmpl.getSchemeId());
					spsScheme = spsSchemeService.findByPK(spsScheme);

					//
					if (spsScheme.getIspackage() != null && "Y".equals(spsScheme.getIspackage())) {
						String res = spsSchemeService.viewPackageScheme(cmpl.getSchemeId(), cti.getOrgId());
						if (!"".equals(res)) {
							// 添加到分包分案 由于是新增员工所以不用判断是否存在
							SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
							schemeEmp.setEmpId(cmpl.getEmpId());
							schemeEmp.setSchemeId(Integer.valueOf(res));
							schemeEmp.setState("USE");
							schemeEmp.setDr(0);
							insert = spsSchemeEmpService.insert(cti, schemeEmp);
						}
					}
					if (insert > 0) {
						if (list != null && list.size() > 0) {
							schemeEmpCondition = list.get(0);
							schemeEmpCondition.setState("USE");
							spsSchemeEmpService.update(cti, schemeEmpCondition);
						} else {
							SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
							schemeEmp.setEmpId(cmpl.getEmpId());
							schemeEmp.setSchemeId(cmpl.getSchemeId());
							schemeEmp.setState("USE");
							schemeEmp.setDr(0);
							insert = spsSchemeEmpService.insert(cti, schemeEmp);
						}
					}
					// 计费账单
					if ("ON".equals(cmpl.getInsuranceState())) {
						List<CmEmployeeInsurance> insurances = new ArrayList<>();
						String insuKey = cmpl.getSchemeId() + curMap.get("insuBeginMonth").trim() + "INSU" + curMap.get("livingTypeCode");
						String month = curMap.get("insuBeginMonth").replace(".", "-");
						List<InsuranceTypeDto> insuranceTypeDtos = InsuranceTypeMap.get(insuKey);
						for (InsuranceTypeDto insu : insuranceTypeDtos) {
							CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
							employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
							employeeInsurance.setRatioId(insu.getRatioId());
							// employeeInsurance.setEmpRatio(insu.getPersonalRatio());
							// employeeInsurance.setCorpRatio(insu.getCompanyRatio());
							// employeeInsurance.setBeginPeriod(insu.getBeginDate());
							Object pensionBeginDate = curMap.get(InsuranceEnums.PENSION.getCode() + "BeginDate");
							Object medicineBeginDate = curMap.get(InsuranceEnums.MEDICINE.getCode() + "BeginDate");
							Object injuryBeginDate = curMap.get(InsuranceEnums.INJURY.getCode() + "BeginDate");
							Object unemploymentBeginDate = curMap.get(InsuranceEnums.UNEMPLOYMENT.getCode() + "BeginDate");
							Object maternityBeginDate = curMap.get(InsuranceEnums.MATERNITY.getCode() + "BeginDate");
							Object pensionBase = curMap.get(InsuranceEnums.PENSION.getCode() + "Base");
							Object medicineBase = curMap.get(InsuranceEnums.MEDICINE.getCode() + "Base");
							Object injuryBase = curMap.get(InsuranceEnums.INJURY.getCode() + "Base");
							Object unemploymentBase = curMap.get(InsuranceEnums.UNEMPLOYMENT.getCode() + "Base");
							Object maternityBase = curMap.get(InsuranceEnums.MATERNITY.getCode() + "Base");
							if( insu.getInsuranceTypeId() == InsuranceEnums.PENSION.getInsuranceId() && pensionBase != null && StringUtils.isNotBlank(pensionBase.toString()) ){// pension 养老保险
								if ( "最低".equals(pensionBase.toString().trim()) || "下限".equals(pensionBase.toString().trim()) ) {
									employeeInsurance.setCorpPaybase( insu.getCorpBaseLower() );
									employeeInsurance.setEmpPaybase( insu.getPsnBaseLower() );
								} else if ( "不缴纳".equals(pensionBase.toString().trim()) ) {
									continue;
								} else {
									employeeInsurance.setCorpPaybase(new BigDecimal(pensionBase.toString().trim()));
									employeeInsurance.setEmpPaybase(new BigDecimal(pensionBase.toString().trim()));
								}
							}else if(insu.getInsuranceTypeId() == InsuranceEnums.MEDICINE.getInsuranceId() && medicineBase != null && StringUtils.isNotBlank(medicineBase.toString()) ){// medicine 医疗保险
								if ( "最低".equals(medicineBase.toString().trim()) || "下限".equals(medicineBase.toString().trim()) ) {
									employeeInsurance.setCorpPaybase( insu.getCorpBaseLower() );
									employeeInsurance.setEmpPaybase( insu.getPsnBaseLower() );
								} else if ( "不缴纳".equals(medicineBase.toString().trim()) ) {
									continue;
								} else {
									employeeInsurance.setCorpPaybase(new BigDecimal(medicineBase.toString().trim()));
									employeeInsurance.setEmpPaybase(new BigDecimal(medicineBase.toString().trim()));
								}
							}else if(insu.getInsuranceTypeId() == InsuranceEnums.INJURY.getInsuranceId() && injuryBase != null && StringUtils.isNotBlank(injuryBase.toString()) ){// injury 工伤保险
								if ( "最低".equals(injuryBase.toString().trim()) || "下限".equals(injuryBase.toString().trim()) ) {
									employeeInsurance.setCorpPaybase( insu.getCorpBaseLower() );
									employeeInsurance.setEmpPaybase( insu.getPsnBaseLower() );
								} else if ( "不缴纳".equals(injuryBase.toString().trim()) ) {
									continue;
								} else {
									employeeInsurance.setCorpPaybase(new BigDecimal(injuryBase.toString().trim()));
									employeeInsurance.setEmpPaybase(new BigDecimal(injuryBase.toString().trim()));
								}
							}else if(insu.getInsuranceTypeId() == InsuranceEnums.UNEMPLOYMENT.getInsuranceId() && unemploymentBase != null && StringUtils.isNotBlank(unemploymentBase.toString()) ){// unemployment 失业保险
								if ( "最低".equals(unemploymentBase.toString().trim()) || "下限".equals(unemploymentBase.toString().trim()) ) {
									employeeInsurance.setCorpPaybase( insu.getCorpBaseLower() );
									employeeInsurance.setEmpPaybase( insu.getPsnBaseLower() );
								} else if ( "不缴纳".equals(unemploymentBase.toString().trim()) ) {
									continue;
								} else {
									employeeInsurance.setCorpPaybase(new BigDecimal(unemploymentBase.toString().trim()));
									employeeInsurance.setEmpPaybase(new BigDecimal(unemploymentBase.toString().trim()));
								}
							}else if(insu.getInsuranceTypeId() == InsuranceEnums.MATERNITY.getInsuranceId() && maternityBase != null && StringUtils.isNotBlank(maternityBase.toString()) ){// maternity 生育保险
								if ( "最低".equals(maternityBase.toString().trim()) || "下限".equals(maternityBase.toString().trim()) ) {
									employeeInsurance.setCorpPaybase( insu.getCorpBaseLower() );
									employeeInsurance.setEmpPaybase( insu.getPsnBaseLower() );
								} else if ( "不缴纳".equals(maternityBase.toString().trim()) ) {
									continue;
								} else {
									employeeInsurance.setCorpPaybase(new BigDecimal(maternityBase.toString().trim()));
									employeeInsurance.setEmpPaybase(new BigDecimal(maternityBase.toString().trim()));
								}
							}else {
								employeeInsurance.setCorpPaybase(cmpl.getInsuranceSalary());
								employeeInsurance.setEmpPaybase(cmpl.getInsuranceSalary());
							}
							if( insu.getInsuranceTypeId() == 1 && pensionBeginDate != null && StringUtils.isNotBlank(pensionBeginDate.toString()) ){// pension 养老保险
								employeeInsurance.setBeginPeriod(pensionBeginDate.toString().replace(".", "-"));
							}else if(insu.getInsuranceTypeId() == 2 && medicineBeginDate != null && StringUtils.isNotBlank(medicineBeginDate.toString()) ){// medicine 医疗保险
								employeeInsurance.setBeginPeriod(medicineBeginDate.toString().replace(".", "-"));
							}else if(insu.getInsuranceTypeId() == 3 && injuryBeginDate != null && StringUtils.isNotBlank(injuryBeginDate.toString()) ){// injury 工伤保险
								employeeInsurance.setBeginPeriod(injuryBeginDate.toString().replace(".", "-"));
							}else if(insu.getInsuranceTypeId() == 4 && unemploymentBeginDate != null && StringUtils.isNotBlank(unemploymentBeginDate.toString()) ){// unemployment 失业保险
								employeeInsurance.setBeginPeriod(unemploymentBeginDate.toString().replace(".", "-"));
							}else if(insu.getInsuranceTypeId() == 5 && maternityBeginDate != null && StringUtils.isNotBlank(maternityBeginDate.toString()) ){// maternity 生育保险
								employeeInsurance.setBeginPeriod(maternityBeginDate.toString().replace(".", "-"));
							}else {
								employeeInsurance.setBeginPeriod(month);
							}
							insurances.add(employeeInsurance);
						}
						cmpl.setCmEmployeeInsurances(insurances);
						log.info("导入员工生成社保缴费详情：orgId=" + cti.getOrgId() + ",month" + month + ",empl="
								+ JSON.toJSONString(cmpl));
						Result r = cmEmployeeInsuranceService.adjustEmployeeInsurance(cti, cti.getOrgId(), cmpl, "INSURANCE",
								month, null);
						if (!r.isSuccess()) {
							result.setSuccess(false);
							result.setError(r.getError());
							return result;
						}
					}
					if ("ON".equals(cmpl.getFundState())) {
						List<CmEmployeeInsurance> insurances = new ArrayList<>();
						String insuKey = cmpl.getSchemeId() + curMap.get("fundBeginMonth").trim() + "FUND";
						String month = curMap.get("fundBeginMonth").replace(".", "-");
						List<InsuranceTypeDto> insuranceTypeDtos = InsuranceTypeMap.get(insuKey);
						for (InsuranceTypeDto insu : insuranceTypeDtos) {
							CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
							employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
							employeeInsurance.setCorpPaybase(cmpl.getFundSalary());
							employeeInsurance.setEmpPaybase(cmpl.getFundSalary());
							employeeInsurance.setRatioId(insu.getRatioId());
							// employeeInsurance.setEmpRatio(insu.getPersonalRatio());
							// employeeInsurance.setCorpRatio(insu.getCompanyRatio());
							// employeeInsurance.setBeginPeriod(insu.getBeginDate());
							insurances.add(employeeInsurance);
						}
						cmpl.setCmEmployeeInsurances(insurances);
						log.info("导入员工生成公积金缴费详情：orgId=" + cti.getOrgId() + ",month" + month + ",empl="
								+ JSON.toJSONString(cmpl));
						Result r = cmEmployeeInsuranceService.adjustEmployeeInsurance(cti, cti.getOrgId(), cmpl, "FUND", month,
								null);
						if (!r.isSuccess()) {
							result.setSuccess(false);
							result.setError(r.getError());
							return result;
						}
					}
					if (insert > 0) {
						result.setSuccess(true);
					} else {
						result.setSuccess(false);
						result.setError("数据导入错误！");
						break;
					}
				}
				if (result.isSuccess()) {
					result.setDataValue("successNum", dataList.size());
					result.setDataValue("simpleCorp", "N");
					result.setDataValue("msg", "共导入" + dataList.size() + "条员工信息！");
				}
			} else {
				result.setError("数据导入错误！");
				result.setSuccess(false);
			}
		}

		return result;
	}

	public Result importEmpExcel(ContextInfo cti, Integer fileId, Integer cpId, String cpName, String selectContent,
								 String sheetName, String corpExist) throws BusinessException {
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
		if (StringUtils.isEmpty(selectContent)) {
			result.setSuccess(false);
			result.setError("对应关系不能为空！");
			return result;
		}
		// 前端传来标题匹配字段<K,V>
		Map<String, String> colNameMap = new HashMap<String, String>();
		String selectArr[] = selectContent.split(",");
		if (null != selectArr) {
			for (int i = 0; i < selectArr.length; i++) {
				String itemStr = selectArr[i];
				String[] itemArr = itemStr.split("=");
				if (null != itemArr) {
					colNameMap.put(itemArr[0], itemArr[1]);
				}
			}
		}

		Map<String, Map<String, String>> sheet_orm = new HashMap<String, Map<String, String>>();
		sheet_orm.put(sheetName, colNameMap);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
		Map<Integer, Map<String, String>> resObj = null;
		try {
			resObj = ImportExcelUtil.importExcel(filePath, getEmployeeFieldMap(corpExist), sheet_orm);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}
		// 户口性质 农业 城镇
		Map<String, String> sysDicMap = new HashMap<String, String>();
		List<SysDictitem> listdic = sysDictitemService.findByDictName("liveType");
		for (SysDictitem sysDict : listdic) {
			sysDicMap.put(sysDict.getName(), sysDict.getCode());
		}
		// 查询 参保 人员类别 modify by lifq 20160914
		Map<String, String> insuranceLiveTypeMap = new HashMap<String, String>();
		List<SysDictitem> insuranceLiveTypeList = sysDictitemService.findByDictName("insuranceLiveType_doc");
		if (null != insuranceLiveTypeList && insuranceLiveTypeList.size() > 0) {
			for (SysDictitem sysDict : insuranceLiveTypeList) {
				insuranceLiveTypeMap.put(sysDict.getName(), sysDict.getCode());
			}
		}

		// 证件类型
		Map<String, String> IdTypeMap = new HashMap<String, String>();
		listdic = sysDictitemService.findByDictName("IDType");
		if (CollectionUtils.isNotEmpty(listdic)) {
			for (SysDictitem sysDict : listdic) {
				IdTypeMap.put(sysDict.getName(), sysDict.getCode());
			}
		}

		// 查询当前服务商 当前企业 有效员工
		Map<String, String> idCardMap = new HashMap<String, String>();
		// 查询用员工类
		CmEmployee cmep = new CmEmployee();

		// 社保状态 公积金状态 导入Excel里只有这两项
		Map<String, String> insfunMap = new HashMap<String, String>();
		insfunMap.put("正常缴纳", "ON");
		insfunMap.put("未缴纳", "OFF");

		Set<String> addCropName = new HashSet();
		CmCorp cmCorp = null;
		// 经过如下获取corp还是为空则表明需按企业名称新增企业
		if ("false".equals(corpExist)) {
			CmCorp query = new CmCorp();
			if (cpId != null && cpId > 0) {
				query.setCpId(cpId);
				cmCorp = cmCorpDao.findByPK(query);
			}
			if (cmCorp == null) {
				if (StringUtils.isNotBlank(cpName)) {
					cmCorp = cmCorpDao.findCorpByName(cpName, cti.getOrgId());
				} else {
					result.setSuccess(false);
					result.setError("企业id无效并且企业名称为空！");
					return result;
				}
			}
			if (cmCorp == null && StringUtils.isNotBlank(cpName.trim())) {
				addCropName.add(cpName.trim());
			}
		}
		if (resObj.isEmpty()) {
			result.setSuccess(false);
			result.setError("未在选择的表中获得数据！");
			return result;
		}
		IdcardValidator iv = new IdcardValidator();
		// 保存用日期
		Date date = new Date();
		// 数据是否有误
		boolean isError = false;
		// 地区字典
		Map<String, String> areaMap = new HashMap<>();
		List<BsArea> areaList = bsAreaDao.freeFind(new BsArea());
		if (CollectionUtils.isNotEmpty(areaList)) {
			for (BsArea area : areaList) {
				areaMap.put(area.getName(), area.getCode());
			}
		}
		/*
		 * //地区社保类型 List<Map<String,Object>> listdict = new ArrayList<>(); //
		 * mapList.get("insurance_code")//社保 缴纳账户地区编码 listdict =
		 * sysDictitemDAO.queryForList("SYS_DICTITEM.findCblb",null);
		 * Map<String,Map<String,String>> shebaoMap = new HashMap<>(); if (null
		 * != listdict) { for (Map<String,Object> dict: listdict) {
		 * Map<String,String> sbMap =
		 * shebaoMap.get(dict.get("area_code").toString()); if(sbMap == null){
		 * sbMap = new HashMap<>();
		 * shebaoMap.put(dict.get("area_code").toString(),sbMap); }
		 * sbMap.put(dict.get("name").toString(),dict.get("code").toString()); }
		 * }
		 */
		// 查询出企业信息集合
		// Map<String,Object> corpMap =
		// (Map<String,Object>)cmCorpDao.queryForList("CM_CORP.FindCorpBySpId",cti.getOrgId());
		// List<String> corpNameList = new ArrayList<String>(200);
		// if(CollectionUtils.isNotEmpty(corpMap)){
		//
		// }

		for (Map.Entry<Integer, Map<String, String>> entry : resObj.entrySet()) {
			Map<String, String> curMap = entry.getValue();
			String error = "";
			curMap.put("row", entry.getKey() + "");
			if (StringUtils.isEmpty(curMap.get("name"))) {
				error += "姓名为空！<br>";
			}
			CmCorp thisCorp = null;
			if ("false".equals(corpExist) && cmCorp != null) {
				curMap.put("cpId", cmCorp.getCpId().toString());
				thisCorp = cmCorp;
			}
			if ("true".equals(corpExist)) {
				if (StringUtils.isEmpty(curMap.get("corpName").trim())) {
					error += "企业名称为空！<br>";
				} else {
					thisCorp = cmCorpDao.findCorpByName(curMap.get("corpName").trim(), cti.getOrgId());
					if (thisCorp == null) {
						// addCropName.add(curMap.get("corpName").trim());
						error += "该企业不存在！<br>";
					} else {
						curMap.put("cpId", thisCorp.getCpId().toString());
					}
				}
			}

			if (StringUtils.isEmpty(curMap.get("identityCode"))) {
				error += "证件号为空！<br>";
			} else {
				String idcar = curMap.get("identityCode");
				// 校验唯一性 名称+证件号
				String idVerify = idcar + curMap.get("name");
				if (StringUtils.isBlank(curMap.get("identityType"))) {
					error += "请选择证件格式！<br>";
				} else if (curMap.get("identityType").equals("身份证")) {
					if (!iv.isValidate18Idcard(idcar) && !iv.isValidate15Idcard(idcar)) {
						error += "身份证号格式错误！<br>";
					} else if ("Excel".equals(idCardMap.get(idVerify))) {
						error += "导入Excel表中员工身份证已存在！<br>";
					}
					// else if (corpNameExist &&
					// this.checkEpIdcardByCpidAndIdcard(thisCorp.getCpId(),idcar))
					// {
					// error += "员工在该企业已存在，请注意！<br>";
					// }
					else {
						idCardMap.put(idVerify, "Excel");
						curMap.put("identityType", "IDCard");// 证件类型 IDType
						// 身份证：ID
					}
				} else {
					if (StringUtils.isBlank(IdTypeMap.get(curMap.get("identityType")))) {
						error += "证件类型不存在！<br>";
					} else {
						idCardMap.put(idVerify, "Excel");
						curMap.put("identityType", IdTypeMap.get(curMap.get("identityType")));// 证件类型
						// IDType
						// 身份证：ID
					}
				}
			}
			// if (StringUtils.isEmpty(curMap.get("mobile"))) {
			// error += "手机号为空！<br>";
			// } else if (!MobileValidator.isMobileNO(curMap.get("mobile"))) {
			// error += "手机号格式错误！<br>";
			// }
			if (StringUtils.isNotEmpty(curMap.get("mobile"))) {
				// 科学计数法转正常数字str
				if (VerificationUtils.isENum(curMap.get("mobile"))) {
					curMap.put("mobile", VerificationUtils.eNumConvertStr(curMap.get("mobile")));
				}
				if (!MobileValidator.isMobileNO(curMap.get("mobile"))) {
					error += "手机号格式错误！<br>";
				}
			}

			if (StringUtils.isNotEmpty(curMap.get("household"))) {
				if (StringUtils.isEmpty(sysDicMap.get(curMap.get("household")))) {
					error += "未找到该户口性质！<br>";
				}
			}
			if (StringUtils.isEmpty(curMap.get("insuranceBase"))) {
				// error += "社保基数为空！<br>";
			} else if (!NumberUtils.isNumber(curMap.get("insuranceBase"))) {
				error += "社保基数非数字类型！<br>";
			}
			if (StringUtils.isEmpty(curMap.get("fundBase"))) {
				// error += "公积金基数为空!<br>";
			} else if (!NumberUtils.isNumber(curMap.get("fundBase"))) {
				error += "公积金基数非数字类型！<br>";
			}

			/*
			 * if (StringUtils.isEmpty(curMap.get("insuranceState"))) { // error
			 * += "社保状态为空！<br>"; } else if
			 * (StringUtils.isEmpty(insfunMap.get(curMap.get("insuranceState")))
			 * ) { error += "未找到该社保状态！<br>"; } else { if
			 * ("ON".equals(insfunMap.get(curMap.get("insuranceState")))) {//
			 * 社保状态==正常缴纳
			 */
			if (StringUtils.isEmpty(curMap.get("insuranceArea"))) {
				error += "参保地区为空！<br>";
			} else {
				Set<String> keySet = areaMap.keySet();
				String areakey = curMap.get("insuranceArea");
				String areaCode = null;
				for (String ikey : keySet) {
					if (areakey.indexOf(ikey) == 0 || ikey.indexOf(areakey) == 0) {
						areaCode = areaMap.get(ikey);
						break;
					}
				}
				if (StringUtils.isEmpty(areaCode)) {
					error += "参保地区不存在！<br>";
				} else {
					/*
					 * Map<String,String> areaShebao = null; keySet =
					 * shebaoMap.keySet(); for(String ikey : keySet){
					 * if(areaCode.indexOf(ikey) == 0){ areaShebao =
					 * shebaoMap.get(ikey); } } if(areaShebao == null){ error +=
					 * "该参保地区无参保人员类别！<br>"; }else
					 */ if (StringUtils.isEmpty(curMap.get("insuranceLivingType"))) {
						error += "参保人员类别为空！<br>";
					} else {
						curMap.put("insuranceLivingTypeValue",
								insuranceLiveTypeMap.get(curMap.get("insuranceLivingType")));
					}

					/*
					 * else if (StringUtils.isEmpty(areaShebao.get(curMap.get(
					 * "insuranceLivingType")))) { error += "未找到该参保人员类别！<br>";
					 * }else{
					 * curMap.put("insuranceLivingTypeValue",areaShebao.get(
					 * curMap.get("insuranceLivingType"))); }
					 */
				}
			}

			CmCorp this1Corp = cmCorpDao.findCorpByName(curMap.get("corpName").trim(), cti.getOrgId());
			if (this1Corp != null) {
				if (StringUtils.isNotEmpty(curMap.get("schemeName"))) {
					String schemeName = curMap.get("schemeName");
					SpsScheme scheme = new SpsScheme();
					// CmCorp this1Corp =
					// cmCorpDao.findCorpByName(curMap.get("corpName").trim(),cti.getOrgId());
					scheme.setName(schemeName);
					scheme.setCpId(this1Corp.getCpId());
					scheme.setSpId(cti.getOrgId());
					SpsScheme existScheme = null;

					// 获取参保地区
					BsArea bsArea = new BsArea();
					bsArea.setNameEq(curMap.get("insuranceArea"));
					List<BsArea> bsList = bsAreaDao.freeFind(bsArea);

					BsArea curArea = new BsArea();
					if (bsList != null && bsList.size() > 0) {
						curArea = bsList.get(0);
					}

					if (spsSchemeService.findAll(scheme).size() > 0) {
						existScheme = spsSchemeService.findAll(scheme).get(0);
					}

					if (existScheme == null) {
						error += "该方案不存在！<br>";
					} else {
						BsArea schArea = new BsArea();

						if (existScheme.getAreaId() == null) {
							error += "请配置该方案下的地区！<br>";
						} else {
							schArea.setAreaId(existScheme.getAreaId());
							schArea = bsAreaDao.findByPK(schArea);
							if (curArea.getBelongCity().intValue() != schArea.getBelongCity().intValue()) {
								error += "参保地区跟方案地区不一致！<br>";
							}
						}
					}
				} else {

					if ("".equals(curMap.get("schemeName")) || curMap.get("schemeName") == null) {
						error += "方案不能为空！<br>";
					}

					// CmCorp this1Corp =
					// cmCorpDao.findCorpByName(curMap.get("corpName").trim(),cti.getOrgId());
					/*
					 * SpsScheme schemeCondition = new SpsScheme();
					 * schemeCondition.setSpId(cti.getOrgId());
					 * schemeCondition.setCpId(this1Corp.getCpId());
					 * schemeCondition.setIsdefault("Y"); List<SpsScheme> list =
					 * spsSchemeService.findAll(schemeCondition); //获取参保地区
					 * BsArea bsArea = new BsArea();
					 * bsArea.setNameEq(curMap.get("insuranceArea"));
					 * List<BsArea> bsList = bsAreaDao.freeFind(bsArea);
					 *
					 * BsArea curArea = new BsArea(); if (bsList != null &&
					 * bsList.size() > 0) { curArea = bsList.get(0); } if (list
					 * != null && list.size() > 0) { SpsScheme scheme =
					 * list.get(0); BsArea schArea = new BsArea();
					 * schArea.setAreaId(scheme.getAreaId()); schArea =
					 * bsAreaDao.findByPK(schArea); if
					 * (curArea.getBelongCity().intValue() !=
					 * schArea.getBelongCity().intValue()) { error +=
					 * "参保地区跟默认方案地区不一致！<br>"; } }
					 */
				}
			}

			/* } */
			/* } */
			/*
			 * if (StringUtils.isEmpty(curMap.get("fundState"))) { // error +=
			 * "公积金状态为空！<br>"; } else if
			 * (StringUtils.isEmpty(insfunMap.get(curMap.get("fundState")))) {
			 * error += "未找到该公积金状态！<br>"; }
			 */
			if (!"".equals(error)) {
				curMap.put("error", error);
				errorDataList.add(curMap);
				isError = true;
			}
			dataList.add(curMap);
		}
		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataList", errorDataList);
			result.setDataValue("errorNum", errorDataList.size());
		} else {
			Map<String, Integer> addCorpMap = new HashMap<>();
			if (addCropName.size() > 0) {
				addCorpMap = this.saveUnCompleteCorp(cti, addCropName);
				if (addCropName.size() != addCorpMap.size()) {
					result.setError("新建企业失败！");
					result.setSuccess(false);
					return result;
				}
			}
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {
				// 查询完善后的企业信息
				List<Map<String, Object>> relations = cmEmployeeDao
						.queryForList("CM_EMPLOYEE.QUERY_FUND_INSURANCE_AREA_ID", cti.getOrgId());
				Map<Integer, Map<String, Object>> relationMap = new HashMap<>();
				if (CollectionUtils.isNotEmpty(relations)) {
					for (Map<String, Object> relation : relations) {
						relationMap.put(Integer.parseInt(relation.get("cp_id").toString()), relation);
					}
				}
				// 数据库中已存在并且未删除的员工
				CmEmployee dbCmpl = new CmEmployee();
				dbCmpl.setCreateBy(cti.getOrgId());
				dbCmpl.setDr(0);
				List<CmEmployee> dbEmps = cmEmployeeDao.queryForList("CM_EMPLOYEE.FindByCpId", dbCmpl);
				Map<Integer, Map<String, CmEmployee>> existEmp = new HashMap<>();
				if (CollectionUtils.isNotEmpty(dbEmps)) {
					for (CmEmployee employee : dbEmps) {
						if (employee.getCpId() != null) {
							Map<String, CmEmployee> empMap = existEmp.get(employee.getCpId());
							if (empMap == null) {
								empMap = new HashMap<>();
								existEmp.put(employee.getCpId(), empMap);
							}
							empMap.put(employee.getIdentityCode() + employee.getName(), employee);
						}
					}
				}

				for (int i = 0; i < dataList.size(); i++) {
					Boolean dbExist = false;
					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");
					CmEmployee cmpl = new CmEmployee();
					if (curMap.get("cpId") == null) {
						if ("false".equals(corpExist)) {
							if (cmCorp != null) {
								cmpl.setCpId(cmCorp.getCpId());
							} else {
								cmpl.setCpId(addCorpMap.get(cpName));
							}
						} else {
							cmpl.setCpId(addCorpMap.get(curMap.get("corpName")));
						}
					} else {
						SpsScheme schemeCondition = new SpsScheme();
						schemeCondition.setSpId(cti.getOrgId());
						schemeCondition.setCpId(Integer.parseInt(curMap.get("cpId")));
						schemeCondition.setIsdefault("Y");
						List<SpsScheme> list = spsSchemeService.findAll(schemeCondition);
						if (list == null || list.size() <= 0) {
							SpsScheme spsScheme = new SpsScheme();
							spsScheme.setCpId(Integer.parseInt(curMap.get("cpId")));
							CmCorp corp = new CmCorp();
							corp.setCpId(spsScheme.getCpId());
							corp = cmCorpDao.findByPK(corp);
							spsScheme.setSpId(cti.getOrgId());
							spsScheme.setName("默认方案");
							spsScheme.setIsdefault("Y");
							spsScheme.setState("USE");
							spsScheme.setAreaId(corp.getCpAddressArea());
							spsScheme.setSchemeType("DIY");// 自己处理
							spsScheme.setIspackage("N");
							spsScheme.setDr(0);
							spsSchemeDao.save(cti, spsScheme);
						}

						cmpl.setCpId(Integer.parseInt(curMap.get("cpId")));
					}
					if (cmpl.getCpId() == null) {
						throw new BusinessException("新增企业员工时未找到企业id");
					}
					// 根据企业ID 查询企业表 cpid，spid 在 关联 关系表中查询 社保和公积金的四个参数
					Map<String, Object> mapList = relationMap.get(cmpl.getCpId());
					// 公积金 账户ID，缴交地ID
					if (mapList != null) {
						if (null != mapList.get("fund_account_id")
								&& "Integer".equals(mapList.get("fund_account_id").getClass().getSimpleName())) {
							// cmpl.setFundAccountId(Integer.parseInt(mapList.get("fund_account_id").toString()));
						}
						if (null != mapList.get("fund_area")
								&& "Integer".equals(mapList.get("fund_area").getClass().getSimpleName())) {
							// cmpl.setFundArea(Integer.valueOf(Integer.parseInt(mapList.get("fund_area").toString())));
						}
						// 社保 账户ID 缴交地ID
						if (null != mapList.get("insurance_account_id")
								&& "Integer".equals(mapList.get("insurance_account_id").getClass().getSimpleName())) {
							// cmpl.setInsuranceAccountId(Integer.parseInt(mapList.get("insurance_account_id").toString()));
						}
						if (null != mapList.get("insurance_area")
								&& "Integer".equals(mapList.get("insurance_area").getClass().getSimpleName())) {
							// cmpl.setInsuranceArea(Integer.parseInt(mapList.get("insurance_area").toString()));
						}
					}

					Map<String, CmEmployee> empMap = existEmp.get(cmpl.getCpId());
					String idVerify = curMap.get("identityCode") + curMap.get("name");
					if (empMap != null && empMap.get(idVerify) != null) {
						cmpl = empMap.get(idVerify);
						dbExist = true;
					}

					cmpl.setInsuranceState("OFF");
					cmpl.setFundState("OFF");
					cmpl.setName(curMap.get("name"));
					cmpl.setIdentityCode(curMap.get("identityCode"));
					cmpl.setMobile(curMap.get("mobile"));
					cmpl.setEmail(curMap.get("email"));
					// cmpl.setLivingType(sysDicMap.get(curMap.get("household")));//
					// 字典置换
					cmpl.setInsuranceLivingType((curMap.get("insuranceLivingTypeValue")));
					cmpl.setIdentityType(curMap.get("identityType"));// 身份证 ID
					cmpl.setJob(curMap.get("job"));
					if (StringUtils.isNotBlank(curMap.get("schemeName"))) {
						String schemeName = curMap.get("schemeName");
						SpsScheme scheme = new SpsScheme();
						scheme.setName(schemeName);
						scheme.setCpId(cmpl.getCpId());
						scheme.setSpId(cti.getOrgId());

						if (spsSchemeService.findAll(scheme).size() > 0) {
							scheme = spsSchemeService.findAll(scheme).get(0);
							cmpl.setSchemeId(scheme.getSchemeId());
						} else {
							SpsScheme schemeCondition = new SpsScheme();
							schemeCondition.setSpId(cti.getOrgId());
							schemeCondition.setCpId(cmpl.getCpId());
							schemeCondition.setIsdefault("Y");
							List<SpsScheme> list = spsSchemeService.findAll(schemeCondition);
							if (list != null && list.size() > 0) {
								cmpl.setSchemeId(((SpsScheme) list.get(0)).getSchemeId());
							}
						}
					} else {
						SpsScheme schemeCondition = new SpsScheme();
						schemeCondition.setSpId(cti.getOrgId());
						schemeCondition.setCpId(cmpl.getCpId());
						schemeCondition.setIsdefault("Y");
						List<SpsScheme> list = spsSchemeService.findAll(schemeCondition);
						if (list != null && list.size() > 0) {
							cmpl.setSchemeId(((SpsScheme) list.get(0)).getSchemeId());
						}
					}

					// 添加公积金申报工资
					if (StringUtils.isNotBlank(curMap.get("fundSalary"))) {
						// cmpl.setFundSalary(Double.parseDouble(curMap.get("fundSalary")));
						cmpl.setFundSalary(new BigDecimal(curMap.get("fundSalary")));
					}

					if (StringUtils.isNotBlank(curMap.get("insuranceSalary"))) {
						// cmpl.setInsuranceSalary(Double.parseDouble(curMap.get("fundSalary")));
						cmpl.setInsuranceSalary(new BigDecimal(curMap.get("insuranceSalary")));
					}
					// cmpl.setInsuranceState(insfunMap.get(curMap.get("insuranceState")));//
					// 字典置换
					// cmpl.setFundState(insfunMap.get(curMap.get("fundState")));//
					// 字典置换
					/*
					 * if (!StringUtils.isEmpty(curMap.get("insuranceState")) &&
					 * "ON".equals(insfunMap.get(curMap.get("insuranceState"))))
					 * { cmpl.setInsuranceLivingType(curMap.get(
					 * "insuranceLivingTypeValue"));// 字典置换 }
					 */
					if (!dbExist) {
						cmpl.setCreateBy(cti.getUserId());
						cmpl.setCreateDt(date);
					} else {
						cmpl.setModifyBy(cti.getUserId());
						cmpl.setModifyDt(date);
					}
					cmpl.setDr(0);// 0 正常。

					List<CmEmployee> employees = new ArrayList<CmEmployee>();
					employees.add(cmpl);
					Integer insert = null;
					if (dbExist && cmpl.getEmpId() != null) {
						insert = cmEmployeeDao.update(cti, cmpl);
						List<CmEmployee> listEmp = new ArrayList<CmEmployee>();
						listEmp.add(cmpl);
						// listEmp =
						// cmEmployeeInsuranceService.getEmpInsuranceFromScheme(cti.getOrgId(),
						// listEmp, "", DateUtil.getCurYearMonthStr());
						// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
						// "", listEmp);
						// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
						// "FUND", list);
						// // 2016 0922 start
						// cmEmployeeInsuranceService.updateEmployeeInsuranceByCurStatus(cti.getOrgId(),
						// listEmp, "", DateUtil.getCurYearMonthStr());
						// 2016 0922 end

						if (insert > 0) {
							SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
							spsSchemeEmp.setSchemeId(cmpl.getSchemeId());
							spsSchemeEmp.setEmpId(cmpl.getEmpId());

							if (spsSchemeEmpService.findAll(spsSchemeEmp).size() > 0) {
								spsSchemeEmp = spsSchemeEmpService.findAll(spsSchemeEmp).get(0);
								spsSchemeEmp.setState("USE");
								insert = spsSchemeEmpService.update(cti, spsSchemeEmp);
							} /*
								 * else { insert = -1; }
								 */
							else {
								SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
								schemeEmp.setEmpId(cmpl.getEmpId());
								List<SpsSchemeEmp> sList = spsSchemeEmpService.findAll(schemeEmp);
								if (sList != null && sList.size() > 0) {
									for (SpsSchemeEmp se : sList) {
										SpsScheme spsScheme = new SpsScheme();
										spsScheme.setSchemeId(se.getSchemeId());
										spsScheme = spsSchemeService.findByPK(spsScheme);
										if (spsScheme.getSpId().intValue() == cti.getOrgId().intValue()) {
											se.setState("STOP");
											spsSchemeEmpService.update(cti, se);
										}
									}
								}
								spsSchemeEmp.setState("USE");
								spsSchemeEmpService.insert(cti, spsSchemeEmp);
							}
						}
					} else {
						insert = cmEmployeeDao.insert(cti, cmpl);

						SpsSchemeEmp schemeEmpCondition = new SpsSchemeEmp();
						schemeEmpCondition.setEmpId(cmpl.getEmpId());
						schemeEmpCondition.setSchemeId(cmpl.getSchemeId());
						List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(schemeEmpCondition);

						SpsScheme spsScheme = new SpsScheme();
						spsScheme.setSchemeId(cmpl.getSchemeId());
						spsScheme = spsSchemeService.findByPK(spsScheme);

						//
						if (spsScheme.getIspackage() != null && "Y".equals(spsScheme.getIspackage())) {
							String res = spsSchemeService.viewPackageScheme(cmpl.getSchemeId(), cti.getOrgId());
							if (!"".equals(res)) {
								// 添加到分包分案 由于是新增员工所以不用判断是否存在
								SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
								schemeEmp.setEmpId(cmpl.getEmpId());
								schemeEmp.setSchemeId(Integer.valueOf(res));
								schemeEmp.setState("USE");
								schemeEmp.setDr(0);
								insert = spsSchemeEmpService.insert(cti, schemeEmp);
							}
						}

						if (insert > 0) {
							if (list != null && list.size() > 0) {
								schemeEmpCondition = list.get(0);
								schemeEmpCondition.setState("USE");
								spsSchemeEmpService.update(cti, schemeEmpCondition);
							} else {

								SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
								schemeEmp.setEmpId(cmpl.getEmpId());
								schemeEmp.setSchemeId(cmpl.getSchemeId());
								schemeEmp.setState("USE");
								schemeEmp.setDr(0);
								insert = spsSchemeEmpService.insert(cti, schemeEmp);
							}
						}

						List<CmEmployee> listEmp = new ArrayList<CmEmployee>();
						listEmp.add(cmpl);
						// listEmp =
						// cmEmployeeInsuranceService.getEmpInsuranceFromScheme(cti.getOrgId(),
						// listEmp, "", DateUtil.getCurYearMonthStr());
						// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
						// "", listEmp);
						// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
						// "FUND", list);

						// 2016 0922 start
						// cmEmployeeInsuranceService.adjustEmployeeInsurance(cti.getOrgId(),
						// listEmp, "", DateUtil.getCurYearMonthStr(), null);
						// // 2016 0922 end
						// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
						// "", listEmp);

						/*
						 * SpsSchemeInsurance spsSChemeInsurance = new
						 * SpsSchemeInsurance();
						 * spsSChemeInsurance.setSchemeId(cmpl.getSchemeId());
						 * List<SpsSchemeInsurance> spsSChemeInsuranceList =
						 * spsSchemeInsuranceService.findAll(spsSChemeInsurance)
						 * ;
						 *
						 * for (SpsSchemeInsurance spsSchemeInsurance1:
						 * spsSChemeInsuranceList) { CmEmployeeInsurance
						 * cmEmployeeInsurance = new CmEmployeeInsurance();
						 * cmEmployeeInsurance.setDr(0);
						 * cmEmployeeInsurance.setEmpId(cmpl.getEmpId());
						 * cmEmployeeInsurance.setInsuranceId(
						 * spsSchemeInsurance1.getInsuranceId());
						 * cmEmployeeInsurance.setCorpRatio(spsSchemeInsurance1.
						 * getCorpRatio());
						 * cmEmployeeInsurance.setEmpRatio(spsSchemeInsurance1.
						 * getEmpRatio());
						 * cmEmployeeInsuranceService.insert(cmEmployeeInsurance
						 * ); }
						 */
					}
					if (insert > 0) {
						result.setSuccess(true);
						result.setDataValue("successNum", dataList.size());
					} else {
						result.setSuccess(false);
						result.setError("数据导入错误！");
						break;
					}
				}
				if (result.isSuccess()) {
					if (addCorpMap.size() > 0) {
						result.setDataValue("simpleCorp", "Y");
						result.setDataValue("msg",
								"共导入" + dataList.size() + "条员工信息，有" + addCorpMap.size() + "个新增企业需要完善！");
					} else {
						result.setDataValue("simpleCorp", "N");
						result.setDataValue("msg", "共导入" + dataList.size() + "条员工信息！");
					}
				}
			} else {
				result.setError("数据导入错误！");
				result.setSuccess(false);
			}
		}
		return result;
	}

	/**
	 * 查询该企业下身份证号是否存在
	 *
	 * @param cpid
	 * @param idcard
	 * @return
	 */
	private Boolean checkEpIdcardByCpidAndIdcard(Integer cpid, String idcard) {
		CmEmployee cmEmployee = new CmEmployee();
		cmEmployee.setCpId(cpid);
		cmEmployee.setNameEq(idcard);
		cmEmployee.setDr(0);
		return cmEmployeeDao.countFreeFind(cmEmployee) > 0;
	}

	/**
	 * 新增未完成的企业
	 *
	 * @param corpNames
	 * @return
	 */
	private Map<String, Integer> saveUnCompleteCorp(ContextInfo cti, Set<String> corpNames) {
		Map<String, Integer> rsMap = new HashMap<>();
		for (String corpName : corpNames) {
			CmCorp cmcorp = cmCorpDao.findCorpByName(corpName, cti.getOrgId());
			if (cmcorp != null) {
				rsMap.put(corpName, cmcorp.getCpId());
				continue;
			}
			cmcorp = new CmCorp();
			cmcorp.setCorpName(corpName);
			cmcorp.setSpId(cti.getOrgId());
			cmcorp.setCreateBy(cti.getUserId());
			cmcorp.setUnComplete("Y");
			if (cmCorpDao.insert(cti, cmcorp) > 0) {
				rsMap.put(corpName, cmcorp.getCpId());
				SpCmRelation spCmRelation = new SpCmRelation();
				spCmRelation.setSpId(cti.getOrgId());
				spCmRelation.setCpId(cmcorp.getCpId());
				spCmRelationDao.insert(cti, spCmRelation);
			}

			SpsScheme spsScheme = new SpsScheme();
			spsScheme.setCpId(cmcorp.getCpId());

			spsScheme.setSpId(cti.getOrgId());
			spsScheme.setName(corpName + "默认方案");
			spsScheme.setIsdefault("Y");
			spsScheme.setState("USE");
			spsScheme.setSchemeType("DIY");// 自己处理
			spsScheme.setIspackage("N");
			spsScheme.setDr(0);
			spsSchemeDao.save(cti, spsScheme);

		}
		return rsMap;
	}

	public PageModel findEmployeeList(PageInfo pi, CmEmployee vo, String schemeType, String keyWord, Integer spId,
									  ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		// vo.setDr(0);// 0为有效员工
		// 数据权限
		String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
				Constant.DATATYPE);
		Integer total = cmEmployeeDao.findEmployeeCount(vo, schemeType, keyWord, spId, authority);
		pm.setTotal(total);
		List<Map<String,Object>> datas = cmEmployeeDao.findEmployeeList(vo, schemeType, keyWord, pageIndex, pageSize, spId,
				authority);
		pm.setDatas(datas);
		return pm;
	}

	public boolean addEmployeeAndScheme(ContextInfo cti, CmEmployee employee) {
		employee.setDr(0);

		int result = cmEmployeeDao.insert(cti, employee);
		SpsSchemeEmp schemeEmpCondition = new SpsSchemeEmp();
		schemeEmpCondition.setEmpId(employee.getEmpId());
		schemeEmpCondition.setSchemeId(employee.getSchemeId());
		schemeEmpCondition.setDr(0);
		List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(schemeEmpCondition);

		SpsScheme spsScheme = new SpsScheme();
		spsScheme.setSchemeId(employee.getSchemeId());
		spsScheme = spsSchemeService.findByPK(spsScheme);

		//
		if ("Y".equals(spsScheme.getIspackage())) {
			String res = spsSchemeService.viewPackageScheme(employee.getSchemeId(), cti.getOrgId());
			if (!"".equals(res)) {
				// 添加到分包分案 由于是新增员工所以不用判断是否存在
				SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
				schemeEmp.setEmpId(employee.getEmpId());
				schemeEmp.setSchemeId(Integer.valueOf(res));
				schemeEmp.setState("USE");
				schemeEmp.setDr(0);
				result = spsSchemeEmpService.insert(cti, schemeEmp);
			}
		}

		if ("ENTRUSTED".equals(spsScheme.getSchemeType())) {
			SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
			schemeEmp.setEmpId(employee.getEmpId());
			schemeEmp.setSchemeId(spsScheme.getParentId());
			schemeEmp.setState("USE");
			schemeEmp.setDr(0);
			result = spsSchemeEmpService.insert(cti, schemeEmp);
		}

		if (result > 0) {
			if (list != null && list.size() > 0) {
				schemeEmpCondition = list.get(0);
				schemeEmpCondition.setState("USE");
				spsSchemeEmpService.update(cti, schemeEmpCondition);
			} else {

				SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
				schemeEmp.setEmpId(employee.getEmpId());
				schemeEmp.setSchemeId(employee.getSchemeId());
				schemeEmp.setState("USE");
				schemeEmp.setDr(0);
				result = spsSchemeEmpService.insert(cti, schemeEmp);
			}
		}

		// 增加员工先不计费 20160926
		/*
		 * List<CmEmployee> listEmp = new ArrayList<CmEmployee>();
		 * listEmp.add(employee); if ("ON".equals(employee.getInsuranceState()))
		 * { List<CmEmployee> iEmpList =
		 * cmEmployeeInsuranceService.getEmpInsuranceFromScheme(cti.getOrgId(),
		 * listEmp, "INSURANCE", DateUtil.getCurYearMonthStr());
		 * cmEmployeeInsuranceService.adjustEmployeeInsurance(cti.getOrgId(),
		 * iEmpList, "INSURANCE", DateUtil.getCurYearMonthStr(),null); }
		 *
		 * if ("ON".equals(employee.getFundState())) { List<CmEmployee> fEmpList
		 * =
		 * cmEmployeeInsuranceService.getEmpInsuranceFromScheme(cti.getOrgId(),
		 * listEmp, "FUND", DateUtil.getCurYearMonthStr());
		 * cmEmployeeInsuranceService.adjustEmployeeInsurance(cti.getOrgId(),
		 * fEmpList, "FUND", DateUtil.getCurYearMonthStr(),null); }
		 */

		return result > 0 ? true : false;
	}


	public Result batchSaveEmployee(ContextInfo cti, List<EmployeeDto> employeeDtos,Integer sendPageSpId,Integer receivePageSpId){
		//对员工的身份进行验证
		Result result = Result.createResult().setSuccess(false);
		List<Map<String,Object>> errorList = new ArrayList<>();
		result.setDataValue("isNull" ,false);
		/**
		 * 判断当前发包机构是否存在虚拟企业
		 */
		SpService spService = spServiceService.findByPk(sendPageSpId);
		if (null != employeeDtos  && !employeeDtos.isEmpty() ){
			if (null != employeeDtos.get(0).getCmEmployee()){
				//获取企业下所有的员工姓名及信息
				CmEmployee cmEmployee = new CmEmployee();
				cmEmployee.setCpId(spService.getCpId());
				cmEmployee.setDr(0);
				//只查询员工的姓名与身份证号
				List<CmEmployee> emplist = findAll(cmEmployee);
				Map<String,CmEmployee> empMap = new HashMap<>();
				if (emplist != null && emplist.size()>0){
					for(CmEmployee emp:emplist){
						empMap.put(emp.getIdentityCode(),emp);
					}
				}
				//根据传过来的城市名称查出城市id/查出areaid与城市名称对应表
				BsArea area = new BsArea();
				area.setAreaType("CITY");
				List<BsArea> areaList = bsAreaDao.queryAreaByAreaType(area);
				Map<String,Integer> areaMap = new HashMap<>();
				for (BsArea bsArea:areaList){
					areaMap.put(bsArea.getName(),bsArea.getAreaId());
				}
				//根据户口类型中文名获取code/Map<livingName,livingCode>
				SysDictitem sysDictitem = new SysDictitem();
				sysDictitem.setDictionary(93);
				List<SysDictitem> sysDictitemList = sysDictitemService.findAll(sysDictitem);
				Map<String,String> liveTypeMap = new HashMap<>();
				for (SysDictitem dict:sysDictitemList){
					liveTypeMap.put(dict.getName()+dict.getAreaId(),dict.getCode());
				}
				//获取条件下spsAccount
				SpsAccount spsAccount = new SpsAccount();
				spsAccount.setSpId(receivePageSpId);
				spsAccount.setCpId(spService.getCpId());
				spsAccount.setAccType("COMMON");
				List<SpsAccount> spsAccountList = spsAccountService.findSpsAccountListByConditions(spsAccount);
				Map<Integer,List<SpsAccount>> accountMap = new HashMap<>();
				if (null != spsAccountList && spsAccountList.size() > 0){
					for (SpsAccount acc:spsAccountList){
						if (null == accountMap.get(acc.getAreaId())){
							List<SpsAccount> accList = new ArrayList<>();
							accList.add(acc);
							accountMap.put(acc.getAreaId(),accList);
						}else {
							List<SpsAccount> spsAccounts = accountMap.get(acc.getAreaId());
							spsAccounts.add(acc);
							accountMap.put(acc.getAreaId(),spsAccounts);
						}
					}
				}
				//获取获所有城市的cpid spid下的所有的方案，按照areaId groupby
				SpsScheme spsScheme = new SpsScheme();
				spsScheme.setCpId(spService.getCpId());
				spsScheme.setSpId(receivePageSpId);
				List<SpsScheme> schemeList = spsSchemeService.findSchemeListByConditions(spsScheme);
				/*List<SpsScheme> schemeList = spsSchemeService.findAll(spsScheme);*/
				Map<Integer,Integer> schemeMap = new HashMap<>();
				if (null != schemeList && schemeList.size() >0){
					for (SpsScheme scheme:schemeList){
						schemeMap.put(scheme.getAreaId(),scheme.getSchemeId());
					}
				}
				//身份证name 与 code 对应表
				SysDictitem dictitem = new SysDictitem();
				dictitem.setDictionary(89);
				List<SysDictitem> dictitems = sysDictitemService.findAll(dictitem);
				Map<String,String> IdCodeMap = new HashedMap();
				for (SysDictitem item:dictitems){
					IdCodeMap.put(item.getName(),item.getCode());
				}
				//对发送过来的信息记录条数
				int count = 0;
				//对employeeDtos循环,进行判断,更新或update
				for (EmployeeDto dto:employeeDtos){
					count++;
					if (null != dto.getCmEmployee()){
						//姓名校验
						Map<String,Object> map = new HashedMap();
						if (StringUtils.isBlank(dto.getCmEmployee().getName())){
							map.put("rowNumber",count);
							map.put("name",dto.getCmEmployee().getName());
							map.put("errorInfo","姓名不能为空!");
							errorList.add(map);
							continue;
						}
						//验证身份信息
						if (StringUtils.isBlank(dto.getCmEmployee().getIdentityType()) || StringUtils.isBlank(dto.getCmEmployee().getIdentityCode())){
							map.put("rowNumber",count);
							map.put("name",dto.getCmEmployee().getName());
							map.put("errorInfo","证件信息信息有误!");
							errorList.add(map);
							continue;
						}
						//把员工的证件类型转换成code
						dto.getCmEmployee().setIdentityType(IdCodeMap.get(dto.getCmEmployee().getIdentityType()));
						if (StringUtils.equals("IDCard",dto.getCmEmployee().getIdentityType())){
							boolean is = IdcardValidator.isValidatedAllIdcard(dto.getCmEmployee().getIdentityCode());
							if (!is) {
								map.put("rowNumber",count);
								map.put("name",dto.getCmEmployee().getName());
								map.put("errorInfo","身份证信息信息有误!");
								errorList.add(map);
								continue;
							}
						}
						//参保地校验
						if (StringUtils.isBlank(dto.getAreaName())){
							map.put("rowNumber",count);
							map.put("name",dto.getCmEmployee().getName());
							map.put("errorInfo","参保地不能为空!");
							errorList.add(map);
							continue;
						}
						//户口性质校验
						if (StringUtils.isBlank(dto.getCmEmployee().getInsuranceLivingType())){
							map.put("rowNumber",count);
							map.put("name",dto.getCmEmployee().getName());
							map.put("errorInfo","户口性质不能为空!");
							errorList.add(map);
							continue;
						}
						dto.setAreaId(areaMap.get(dto.getAreaName()));
						dto.getCmEmployee().setInsuranceLivingType(liveTypeMap.get(dto.getCmEmployee().getInsuranceLivingType()+dto.getAreaId()));
						dto.getCmEmployee().setCpId(spService.getCpId());
						dto.getCmEmployee().setDr(0);
						dto.getCmEmployee().setInsuranceState("OFF");
						dto.getCmEmployee().setFundState("OFF");
						//对员工在当前企业下判重,如果重复则update() ,保存员工同时保存方案(update不用保存方案)
						if (empMap != null && empMap.size()>0
								&& null != empMap.get(dto.getCmEmployee().getIdentityCode())
								&& StringUtils.equals(dto.getCmEmployee().getName(),empMap.get(dto.getCmEmployee().getIdentityCode()).getName())){
							dto.getCmEmployee().setEmpId(empMap.get(dto.getCmEmployee().getIdentityCode()).getEmpId());
							cmEmployeeDao.update(cti,dto.getCmEmployee());
						}else {
							/**
							 * 1.根据参保地获取地区信息
							 * 2.根据地区、当前虚拟企业ID、接包方spId获取方案信息 sps_scheme (areaId cpid spId insurance_account_id fund_account_id fund_type:COMMON insurance_type:COMMON)
							 * 2.1.如果不存在当前地区方案，创建方案
							 * 2.1.1 创建方案时，查看当前地区下是否存在 社保库和公积金库 sps_account (areaId,spId,insurance_fund_type : [INSURANCE,FUND] ,acc_type:COMMON)
							 * 2.1.2 方案关联对应的库ID
							 * 3.管理人员与方案关系
							 * SELECT min(acc_id),insurance_fund_type FROM sps_account WHERE sp_id = 49 AND area_id = 6 AND acc_type = 'COMMON' GROUP BY insurance_fund_type;
							 */
							Integer areaId = areaMap.get(dto.getAreaName());
							SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
							if (null != schemeMap && null != schemeMap.get(areaId)) {
								spsSchemeEmp.setSchemeId(schemeMap.get(areaId));
								dto.getCmEmployee().setSchemeId(schemeMap.get(areaId));
							} else {
								//如果不存在当前地区方案，创建方案,并把方案放到schemeMap中
								if (null != accountMap && accountMap.size() > 0 && null != accountMap.get(dto.getAreaId())) {
									List<SpsAccount> spsAccounts = accountMap.get(dto.getAreaId());
									for (SpsAccount acc : spsAccounts) {
										if ("FUND".equals(acc.getInsuranceFundType())) {
											spsScheme.setFundAccountId(acc.getAccId());
											spsScheme.setFundType("COMMON");
										} else {
											spsScheme.setInsuranceAccountId(acc.getAccId());
											spsScheme.setInsuranceType("COMMON");
										}
									}
								}
								spsScheme.setAreaId(dto.getAreaId());
								spsScheme.setName(dto.getAreaName() + "默认方案");
								spsScheme.setState("USE");
								spsScheme.setSchemeType("DIY");//自己处理
								spsScheme.setIsdefault("Y");
								spsScheme.setIspackage("N");
								spsScheme.setDr(0);
								spsScheme.setBillType("ADVANCE_CHARGE");
								spsSchemeService.save(cti, spsScheme);
								//向schemeMap中添加方案信息
								schemeMap.put(dto.getAreaId(), spsScheme.getSchemeId());
								//创建关联表
								spsSchemeEmp.setSchemeId(spsScheme.getSchemeId());
								dto.getCmEmployee().setSchemeId(spsScheme.getSchemeId());
							}
							//保存员工,向empMap中加入员工信息
							cmEmployeeDao.save(cti, dto.getCmEmployee());
							empMap.put(dto.getCmEmployee().getIdentityCode(), dto.getCmEmployee());

							spsSchemeEmp.setEmpId(dto.getCmEmployee().getEmpId());
							spsSchemeEmp.setState("USE");
							spsSchemeEmp.setDr(0);
							//如果保存员工是update则保存关联表,insert则保存关联表
							spsSchemeEmpService.save(cti, spsSchemeEmp);
						}
					}
				}
			}

		}else{
			result.setDataValue("isNull" ,true);
			return result;
		}
		if (errorList.isEmpty()){
			result.setSuccess(true);
			return result;
		}
		result.setDataValue("result",errorList);
		return result;
	}

	/**
	 * 获取员工的姓名与身份证号列表
	 * @param cmEmployee
	 * @return
	 */
	public List<CmEmployee> findEmpNameAndIdentitycodeList(CmEmployee cmEmployee) {
		List<CmEmployee> datas = cmEmployeeDao.findEmpNameAndIdentitycodeList(cmEmployee);
		return datas;
	}

	public CmEmployee findByPK(CmEmployee vo) {
		return cmEmployeeDao.findByPK(vo);
	}
	@Override
	public CmEmployee findByPk(Integer empid) {
		CmEmployee emp = new CmEmployee();
		emp.setEmpId(empid);
		return cmEmployeeDao.findByPK(emp);
	}

	public CmEmployee findEmpAndJsonByPK(CmEmployee vo) {
		return cmEmployeeDao.findEmpAndJsonByPK(vo);
	}
	@Override
	public CmEmployee findEmpAndJsonByPK(Integer empid) {
		CmEmployee emp = new CmEmployee();
		emp.setEmpId(empid);
		return cmEmployeeDao.findEmpAndJsonByPK(emp);
	}

	@Override
	public boolean reductionEmployee(ContextInfo cti, CmEmployee employee, String insuranceReductionDate,
									 String fundReductionDate) {
		int result = -1;

		CmEmployee ExistEmp = cmEmployeeDao.findByPK(employee);
		SpsTask conditionSpsTask = new SpsTask();
		conditionSpsTask.setEmpId(ExistEmp.getEmpId());
		conditionSpsTask.setCpId(ExistEmp.getCpId());
		conditionSpsTask.setSpId(cti.getOrgId());

		/*
		 * List<SpsTask> taskList = spsTaskService.findAll(conditionSpsTask); if
		 * (taskList != null) { for (SpsTask spsTask: taskList) {
		 * spsTaskService.remove(spsTask); } }
		 */

		if (employee.getLeaveTime() != null) {

			employee.setInsuranceState("DECREASING");
			employee.setFundState("DECREASING");
			SpsTask spsTask = new SpsTask();
			spsTask.setCpId(ExistEmp.getCpId());
			spsTask.setEmpId(ExistEmp.getEmpId());
			spsTask.setSpId(cti.getOrgId());
			spsTask.setBstypeId(4);
			spsTask.setBeginDate(insuranceReductionDate);
			spsTask.setType("TODO");
			spsTask.setDr(0);
			spsTaskService.save(cti, spsTask);
			SpsTask spsTask1 = new SpsTask();
			spsTask1.setCpId(ExistEmp.getCpId());
			spsTask1.setEmpId(ExistEmp.getEmpId());
			spsTask1.setSpId(cti.getOrgId());
			spsTask1.setBstypeId(16);
			spsTask1.setBeginDate(fundReductionDate);
			spsTask1.setType("TODO");
			spsTask1.setDr(0);
			spsTaskService.insert(cti, spsTask1);
		} else {
			if (StringUtils.isNotBlank(employee.getInsuranceState())) {
				SpsTask spsTask = new SpsTask();
				spsTask.setCpId(ExistEmp.getCpId());
				spsTask.setEmpId(ExistEmp.getEmpId());
				spsTask.setSpId(cti.getOrgId());
				spsTask.setBstypeId(4);
				spsTask.setBeginDate(insuranceReductionDate);
				spsTask.setType("TODO");
				spsTask.setDr(0);
				spsTaskService.insert(cti, spsTask);
			}

			if (StringUtils.isNotBlank(employee.getFundState())) {
				SpsTask spsTask = new SpsTask();
				spsTask.setCpId(ExistEmp.getCpId());
				spsTask.setEmpId(ExistEmp.getEmpId());
				spsTask.setSpId(cti.getOrgId());
				spsTask.setBstypeId(16);
				spsTask.setBeginDate(fundReductionDate);
				spsTask.setType("TODO");
				spsTask.setDr(0);
				result = spsTaskService.insert(cti, spsTask);
			}
		}

		result = cmEmployeeDao.update(cti, employee);

		return result > 0 ? true : false;
	}

	@Override
	public Map<String, Object> FindEmployeeBasicInfo(Integer empId, Integer spId) {
		return cmEmployeeDao.FindEmployeeBasicInfo(empId, spId);
	}

	/**
	 * 员工管理 > 员工列表 >缴费详情
	 *
	 * @param cmEmployee
	 * @return
	 * @createDate : 2016年11月25日 上午11:10:02
	 * @author : xiyanzhang
	 * @version : v1.0
	 * @updateDate : 2016年11月25日 上午11:10:02
	 * @updateAuthor :
	 */
	@Override
	public Map<String,Object> findEmployeeInsuranceList(CmEmployee cmEmployee) {

		List<String> lists = new ArrayList<String>();
		if(null != cmEmployee){
			lists = this.getTime(cmEmployee.getBeginDate(), cmEmployee.getEndDate());
		}
		CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
		employeeInsurance.setEmpId(cmEmployee.getEmpId());
		employeeInsurance.setProductName(cmEmployee.getProductName());
		//查询五险一金
		List<Map<String, Object>> list = cmEmployeeInsuranceDao.findEmployeeInsuranceListMap(employeeInsurance,lists);
		if(0 < list.size() && null != list ){
			//查询员工 服务费
			HashMap<String, Object> service = this.findEmployeeServiceChargeMap(cmEmployee);
			/**
			 *  循环构建员工详情列表
			 */
			LinkedHashMap<String,EmpMonthInsuranceDto> dataMap = new LinkedHashMap<>();
			for (Map<String, Object> map : list) {
				EmpMonthInsuranceDto monthIns = dataMap.get(String.valueOf(map.get("year")));
				if(null == monthIns){
					monthIns = new EmpMonthInsuranceDto();
					List<Map<String,Object>> insList = new ArrayList<Map<String,Object>>();
					monthIns.setMonthIns(insList);
					dataMap.put(String.valueOf(map.get("year")), monthIns);
				}
				if(StringUtils.equals("CURMONTH", String.valueOf(map.get("insurance_rule")))){
					map.put("billMonth",map.get("year"));

				}
				if(StringUtils.equals("NEXTMONTH", String.valueOf(map.get("insurance_rule")))){
					map.put("billMonth",getMonth(String.valueOf(map.get("year"))));
				}
				if(StringUtils.equals("CURMONTH", String.valueOf(map.get("fund_rule")))){
					map.put("billMonth",map.get("year"));
				}
				if(StringUtils.equals("NEXTMONTH", String.valueOf(map.get("fund_rule")))){
					map.put("billMonth",getMonth(String.valueOf(map.get("year"))));
				}
				monthIns.getMonthIns().add(map);
				monthIns.setCorpTotal(Arith.add(monthIns.getCorpTotal(),(BigDecimal)map.get("corp_payment")));
				monthIns.setEmpTotal(Arith.add(monthIns.getEmpTotal(),(BigDecimal)map.get("emp_payment")));
				map.put("insOfficTotal",Arith.add((BigDecimal)map.get("corp_payment"),(BigDecimal)map.get("emp_payment")));
				monthIns.setOfficeTotal(Arith.add(monthIns.getOfficeTotal(),(BigDecimal)map.get("corp_payment"),(BigDecimal)map.get("emp_payment")));
				monthIns.setMonth(String.valueOf(map.get("year")));
				monthIns.setCostMonth(String.valueOf(map.get("year")));
				monthIns.setAreaName(String.valueOf(map.get("area_name")));
				monthIns.setPrice((BigDecimal)service.get("price"));
			}

			List<String> listTemp = cmEmployeeInsuranceDao.findProductNameList(cmEmployee.getEmpId());
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("InsuranceListsMap", dataMap);
			resultMap.put("productNameList", listTemp);
			return resultMap;
		}else{
			return null;
		}

	}
	/**
	 *  员工详情 缴费详情 服务费
	 *  @param cmEmployee
	 *  @return
	 *  @createDate  	: 2016年11月28日 下午3:30:48
	 *  @author         	: xiyanzhang
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月28日 下午3:30:48
	 *  @updateAuthor  	:
	 */
	@Override
	public HashMap<String, Object> findEmployeeServiceChargeMap(CmEmployee cmEmployee) {
		return cmEmployeeDao.findEmployeeServiceChargeMap(cmEmployee);

	}
	@Override
	public Map<String, Object> findEmployeeInsureInfo(Integer empId) {
		Map<String,Object> empMap = cmEmployeeDao.findEmployeeInsureInfo(empId);
		if(null != empMap )
			return empMap;
		return null;
	}

	public Integer findEmpCount(CmEmployee vo, Integer spId, ContextInfo cti) {
		// nameEq 员工列表搜索 传来的参数
		// 数据权限
		String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
				Constant.DATATYPE);
		vo.setDr(0);// 0为有效员工
		Integer total = cmEmployeeDao.findEmployeeCount(vo, null, null, spId, authority);

		return total;

	}

	@Override
	public List<Map<String, Object>> findEmpBySchemeId(Integer scheme_id, Integer cp_id) {
		return cmEmployeeDao.findEmpBySchemeId(scheme_id, cp_id);
	}

	@Override
	public Map<String, Object> findEmployeeInfoByPK(Integer empId) {
		Map<String,Object> empMap = cmEmployeeDao.findEmployeeInfoByPK(empId);
		if(null != empMap )
			return empMap;
		return null;
	}

	/**
	 *  更改比例替换信息
	 *  @param
	 * @return    :
	 *  @createDate   : 2017/1/19 16:14
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/19 16:14
	 *  @updateAuthor  :
	 */
	@Override
	public Result updateEmInsuranceList(ContextInfo cti,JSONObject paraObj){
		boolean isSuccess = true;
		Result result = Result.createResult().setSuccess(false);
		try {
			Integer empId = paraObj.getInt("empId");
			Map<String, Object> cmEmployee = FindEmployeeBasicInfo(empId, cti.getOrgId());
			SpsTask spsTask = new SpsTask();
			spsTask.setEmpId(empId);
			spsTask.setDr(0);
			spsTask.setJson(paraObj.toString());
			spsTask.setBstypeId(37);
			spsTask.setSchemeId(Integer.parseInt(cmEmployee.get("scheme_id").toString()));
			spsTask.setSpId(cti.getOrgId());
			taskAspectService.saveTask(cti, spsTask, null, result);
			if (!result.isSuccess()) {
				log.error("调整比例失败"+JSON.toJSONString(result.getError()));
			}

		}catch (Exception e){
			e.printStackTrace();
			isSuccess = false;

		}
		return  result;
	}

	@Override
	public boolean updateEmpInsurance(ContextInfo cti, JSONObject paraObj) {
		boolean isSuccess = true;
		Integer empId = paraObj.getInt("empId");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String beginPeriod = dateFormat.format(date);
		if(StringUtils.isNotBlank(paraObj.getString("insuranceMonth"))){
			beginPeriod = paraObj.getString("insuranceMonth");
		}
		CmEmployee employee = new CmEmployee();
		employee.setEmpId(empId);
		employee = cmEmployeeDao.findByPK(employee);

		List<CmEmployeeInsurance> employeeInsurancesList = new ArrayList<CmEmployeeInsurance>();
		Map<Integer, Integer> insMap = new HashMap<>();
		JSONArray insuranceArray = paraObj.getJSONArray("insuranceList");
		if (insuranceArray != null && insuranceArray.size() > 0) {
			for (int i = 0; i < insuranceArray.size(); i++) {

				String insuranceString = insuranceArray.getString(i);
				JSONObject insuranceObject = JSONObject.fromObject(insuranceString);
				CmEmployeeInsurance cmEmployeeInsurance = new CmEmployeeInsurance();
				cmEmployeeInsurance.setEmpId(empId);
				cmEmployeeInsurance.setInsuranceId(insuranceObject.optInt("insuranceId", 0));
				cmEmployeeInsurance.setRatioId(insuranceObject.getInt("ratioId"));

				cmEmployeeInsurance.setCorpRatio(com.xfs.common.util.StringUtils
						.toBigDecimal(insuranceObject.optString("corp_ratio", "0")).divide(new BigDecimal(100)));
				cmEmployeeInsurance.setEmpRatio(com.xfs.common.util.StringUtils
						.toBigDecimal(insuranceObject.optString("emp_ratio", "0")).divide(new BigDecimal(100)));
				cmEmployeeInsurance.setDr(0);
				int insuranceId = insuranceObject.optInt("insuranceId", 0);
				if (insuranceId == 6) {
					cmEmployeeInsurance.setCorpPaybase(employee.getFundSalary());
					cmEmployeeInsurance.setEmpPaybase(employee.getFundSalary());
				} else {
					cmEmployeeInsurance.setCorpPaybase(employee.getInsuranceSalary());
					cmEmployeeInsurance.setEmpPaybase(employee.getInsuranceSalary());
				}

				employeeInsurancesList.add(cmEmployeeInsurance);
				insMap.put(cmEmployeeInsurance.getInsuranceId(), cmEmployeeInsurance.getInsuranceId());
			}

			employee.setCmEmployeeInsurances(employeeInsurancesList);
			// adjust内部只能增加数据，不能减少，外层删除不用险种
			CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
			employeeInsurance.setEmpId(empId);
			List<CmEmployeeInsurance> list = cmEmployeeInsuranceService.findAll(employeeInsurance);
			for (CmEmployeeInsurance cmEmployeeInsurance : list) {
				if (!insMap.containsKey(cmEmployeeInsurance.getInsuranceId())) {
					cmEmployeeInsurance.setDr(1);
					cmEmployeeInsuranceDao.update(cti, cmEmployeeInsurance);
				}
			}
			Result r = cmEmployeeInsuranceService.adjustEmployeeInsurance(cti, cti.getOrgId(), employee, "", beginPeriod, null);
			if (!r.isSuccess()) {
				throw new BusinessException(r.getError());
			}
			// cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod();
			// cmEmployeeInsuranceService.calcEmployeeInsurance(cti.getOrgId(),
			// "FUND", list);
			// cmEmployeeInsuranceService.getEmpInsuranceFromScheme()
		}
		// }
		return isSuccess;
	}

	@Override
	public List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds) {
		return findEmpListWithDetailByEmpids(empIds, DateUtil.getCurYearMonthStr());
	}

	@Override
	public List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period) {
		return findEmpListWithDetailByEmpids(empIds, period, null);
	}

	@Override
	public List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period, String insuranceOrFund) {
		return findEmpListWithDetailByEmpids(empIds, period, insuranceOrFund,"MONTH");
	}

	@Override
	public List<CmEmployee> findEmpListWithDetailByEmpids(List<Integer> empIds, String period, String insuranceOrFund,String payType){
		return cmEmployeeDao.findEmpWithDetailByEmpids(empIds, period, insuranceOrFund,payType);
	}
	/**
	 * 获取员工信息
	 *
	 * @param spId
	 * @param cpId
	 * @param empId
	 * @return
	 */
	@Override
	public CmEmployee queryEmployee(Integer spId, Integer cpId, Integer empId) {
		return cmEmployeeDao.queryEmpInfo(spId, cpId, empId);
	}

	@Override
	public void saveNewInsurance(ContextInfo cti, JSONObject paraObj) {

		Integer empId = paraObj.getInt("empId");

		CmEmployee employee = new CmEmployee();
		employee.setEmpId(empId);
		employee = cmEmployeeDao.findByPK(employee);
		if (paraObj.containsKey("insuranceType")) {
			JSONObject object = paraObj.getJSONObject("insuranceObj");
			String json = object.toString();
			SpsTask spsTask = new SpsTask();
			spsTask.setEmpId(empId);
			spsTask.setCpId(employee.getCpId());
			spsTask.setSpId(cti.getOrgId());
			spsTask.setJson(json);
			spsTask.setType("TODO");
			spsTask.setBstypeId(paraObj.getInt("insuranceType"));
			spsTaskService.insert(cti, spsTask);
			employee.setInsuranceState("INCREASING");
		}

		if (paraObj.containsKey("fundType")) {
			JSONObject object = paraObj.getJSONObject("fundObj");
			String json = object.toString();
			SpsTask spsTask = new SpsTask();
			spsTask.setEmpId(empId);
			spsTask.setCpId(employee.getCpId());
			spsTask.setSpId(cti.getOrgId());
			spsTask.setJson(json);
			spsTask.setType("TODO");
			spsTask.setBstypeId(paraObj.getInt("fundType"));
			spsTaskService.insert(cti, spsTask);
			employee.setFundState("INCREASING");
		}
		cmEmployeeDao.update(cti, employee);
	}

	@Override
	public Map<String, CmEmployee> queryAllEmpBySpId(Integer spId) {
		List<CmEmployee> cmEmployees = cmEmployeeDao.queryAllEmpBySpId(spId);
		Map<String, CmEmployee> cmEmployeeMap = new HashMap<>();
		if (null != cmEmployees && !cmEmployees.isEmpty()) {
			for (CmEmployee cmEmployee : cmEmployees) {
				cmEmployeeMap.put(cmEmployee.getName() + cmEmployee.getIdentityCode(), cmEmployee);
			}
		}
		return cmEmployeeMap;
	}

	/**
	 * 查询 社保、公积金 人数
	 *
	 * @author lifq
	 *
	 *         2016年8月16日 下午9:37:53
	 */
	public List<Map<String, Object>> queryInsurNumBySpId(Map<String, Object> map) {
		return cmEmployeeDao.queryInsurNumBySpId(map);
	}

	public boolean reductionEmployee(ContextInfo cti, List<SpsTask> spsTasks, CmEmployee employee, Result result) {

		boolean isSuccess = true;
		if (null != spsTasks && spsTasks.size() > 0) {
			for (SpsTask spsTask : spsTasks) {
				corpTaskAspectService.saveTask(cti, spsTask, null, result);
				if (!result.isSuccess()) {
					isSuccess = false;
					break;
				}
			}
			if (employee.getLeaveTime() != null) {
				CmEmployee e = new CmEmployee();
				e.setEmpId(employee.getEmpId());
				e.setLeaveTime(employee.getLeaveTime());
				cmEmployeeDao.update(cti, e);
			}

		}
		return isSuccess;
	}

	/**
	 * 根据方案id 查询员工实体
	 *
	 * @author lifq
	 *
	 *         2016年8月24日 下午4:35:57
	 */
	public List<CmEmployee> findEmpEntityBySchemeId(Integer schemeId) {
		return cmEmployeeDao.findEmpEntityBySchemeId(schemeId);
	}

	/**
	 * 根据方案id 查询员工实体
	 *
	 * @author wangxs
	 *
	 *         2016年8月24日 下午4:35:57
	 */
	public boolean supplementInsurance(ContextInfo cti, List<SpsTask> spsTasks, CmEmployee employee, Result result) {
		boolean isSuccess = true;
		if (null != spsTasks && spsTasks.size() > 0) {
			for (SpsTask spsTask : spsTasks) {
				taskAspectService.saveTask(cti, spsTask, null, result);
				if (!result.isSuccess()) {
					isSuccess = false;
					break;
				}
			}
		}
		return isSuccess;
	}

	/**
	 * 根据方案id 查询员工实体 供cs使用
	 *
	 * @author wangxs
	 *
	 *         2016年8月24日 下午4:35:57
	 */
	public boolean supplementInsuranceToCs(ContextInfo cti, List<SpsTask> spsTasks, CmEmployee employee, SpsScheme spsScheme, Result result) {
		boolean isSuccess = true;
		if (null != spsTasks && spsTasks.size() > 0) {
			List<InsuranceTypeDto> insuranceTypeDtoList = null;
			for (SpsTask spsTask : spsTasks) {
				com.alibaba.fastjson.JSONObject json = JSON.parseObject(spsTask.getJson());
				json.put("name",employee.getName());
				json.put("identity_code",employee.getIdentityCode());
				String supplyStart = json.getString("supplementarybegindate");
				if("INSURANCE".equals(BsType.getInsType(spsTask.getBstypeId()))){
					insuranceTypeDtoList = queryAccountInsuranceListByAccountId(spsScheme.getInsuranceAccountId(),employee.getInsuranceLivingType(), supplyStart,"Y");
				}else{
					insuranceTypeDtoList = queryAccountInsuranceListByAccountId(spsScheme.getFundAccountId(), null, supplyStart,"Y");
				}
				com.alibaba.fastjson.JSONObject insObj = JSON.parseObject(spsTask.getJson());
				com.alibaba.fastjson.JSONArray insArr = new com.alibaba.fastjson.JSONArray();
				for (InsuranceTypeDto ins: insuranceTypeDtoList ) {
					ins.setBeginDate(supplyStart);
					ins.setCorpPaybase(new BigDecimal(String.valueOf(json.get("insurancebase"))));
					ins.setEmpPaybase(new BigDecimal(String.valueOf(json.get("insurancebase"))));
					com.alibaba.fastjson.JSONObject obj = JSON.parseObject(JSON.toJSONString(ins));
					insArr.add(obj);
				}
				insObj.put("insurance", insArr);
				spsTask.setJson(insObj.toJSONString());
				corpTaskAspectService.saveTask(cti, spsTask, null, result);
				if (!result.isSuccess()) {
					isSuccess = false;
					break;
				}
			}
		}
		return isSuccess;
	}

	/**
	 * 任务单保存 4种
	 *
	 * @param tasks
	 * @param businessParam
	 * @param result
	 * @return
	 */
	public Result handlerTask(ContextInfo cti, List<SpsTask> tasks, Map businessParam, Result result) {
		if (CollectionUtils.isEmpty(tasks)) {
			result.setSuccess(false).setError("任务单不能为空");
			return result;
		}
		for (SpsTask task : tasks) {
			if (!result.isSuccess()) {
				BusinessException e = new BusinessException(result.getError());
				log.error("sps执行任务单失败，tasks:" + JSON.toJSONString(tasks), e);
				throw e;
			}
			corpTaskAspectService.saveTask(cti, task, businessParam, result);
		}
		if (!result.isSuccess()) {
			BusinessException e = new BusinessException(result.getError());
			log.error("sps执行任务单失败，tasks:" + JSON.toJSONString(tasks), e);
			throw e;
		}
		return result;
	}

	/**
	 * 任务单保存 4种 cs用
	 *
	 * @param tasks
	 * @param businessParam
	 * @param result
	 * @return
	 */
	@Override
	public Result handlerTaskToCs(ContextInfo cti, List<SpsTask> tasks, Map businessParam, Result result) {
		if (CollectionUtils.isEmpty(tasks)) {
			result.setSuccess(false).setError("任务单不能为空");
			return result;
		}
		for (SpsTask task : tasks) {
			if (!result.isSuccess()) {
				BusinessException e = new BusinessException(result.getError());
				log.error("cs执行任务单失败，tasks:" + JSON.toJSONString(tasks), e);
				throw e;
			}
			 corpTaskAspectService.saveTask(cti, task, businessParam, result);
		}
		if (!result.isSuccess()) {
			BusinessException e = new BusinessException(result.getError());
			log.error("cs执行任务单失败，tasks:" + JSON.toJSONString(tasks), e);
			throw e;
		}
		return result;
	}

	@Override
	public boolean recoverEmp(ContextInfo cti, CmEmployee cmEmployee) {
		cmEmployee.setDr(0);
		int isSuccess = cmEmployeeDao.update(cti, cmEmployee);
		if (isSuccess > 0) {
			SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
			spsSchemeEmp.setEmpId(cmEmployee.getEmpId());
			List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(spsSchemeEmp);
			for (SpsSchemeEmp schemeEmp : list) {
				if (!"STOP".equals(schemeEmp.getState())) {
					schemeEmp.setDr(0);
					spsSchemeEmpService.update(cti, schemeEmp);
				}
			}
		}
		return isSuccess > 0 ? true : false;
	}

	/**
	 * 根据社保公积金库查找库比例
	 *
	 * @param accountId
	 * @param livingType
	 * @return
	 */
	public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType, String beginDate){
		return this.queryAccountInsuranceListByAccountId(accountId,livingType,beginDate,null);
	}

	/**
	 * 根据社保公积金库查找库比例
	 *
	 * @param accountId
	 * @param livingType
	 * @return
	 */
	public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType, String beginDate,String isDefault) {
		// 获取当前时间，格式是年月
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String currentPeriod = dateFormat.format(date);

		if (beginDate != null && !"".equals(beginDate)) {
			currentPeriod = beginDate;
		}

		if ("".equals(livingType)) {
			livingType = null;
		}

		// 根据库id获取库
		SpsAccount spsAccount = new SpsAccount();
		spsAccount.setAccId(accountId);
		spsAccount = spsAccountService.findByPK(spsAccount);

		List<InsuranceTypeDto> insuranceTypeDtos = new ArrayList<>();

		if (spsAccount != null) {
			SpsAccountRatio spsAccountRadio = new SpsAccountRatio();
			spsAccountRadio.setAccId(spsAccount.getAccId());
			spsAccountRadio.setLivingTypeEq(livingType);
			if(StringUtils.isNotBlank(isDefault))
				spsAccountRadio.setIsdefaultEq(isDefault);
			List<SpsAccountRatio> accRatioList = spsAccountRatioService.findAll(spsAccountRadio);
			if (accRatioList != null && accRatioList.size() > 0) {
				for (SpsAccountRatio spsAccountRatio : accRatioList) {
					BsArearatio bsArearatio = new BsArearatio();
					bsArearatio.setRatioId(spsAccountRatio.getRatioId());
					bsArearatio.setDr(0);
					Map<String, Object> areaRatio = bsArearatioService.findAreaRatioDetail(bsArearatio);

					// 获取当前时间所在的地区险种比例版本
					BsArearatiover bsArearatiover = new BsArearatiover();
					bsArearatiover.setRatioId(spsAccountRatio.getRatioId());
					// bsArearatiover.setBeginPeriodTo(currentPeriod);
					// bsArearatiover.setEndPeriodFrom(currentPeriod);
					List<BsArearatiover> aOverList = bsArearatioverService.findAll(bsArearatiover);
					if (aOverList != null && aOverList.size() > 0) {
						for (BsArearatiover ver : aOverList) {
							try {
								if (org.springframework.util.StringUtils.isEmpty(ver.getBeginPeriod()) || dateFormat
										.parse(ver.getBeginPeriod()).compareTo(dateFormat.parse(currentPeriod)) <= 0) {
									if (org.springframework.util.StringUtils.isEmpty(ver.getEndPeriod())
											|| dateFormat.parse(ver.getEndPeriod())
											.compareTo(dateFormat.parse(currentPeriod)) >= 0) {
										InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
										insuranceTypeDto.setRatioId(ver.getRatioId());
										insuranceTypeDto.setInsuranceTypeId(
												Integer.valueOf(areaRatio.get("insurance_id").toString()));
										insuranceTypeDto.setInsuranceName(areaRatio.get("insurance_name").toString());
										if ("Y".equals(spsAccountRatio.getIsdefault())) {
											insuranceTypeDto.setIsCheck(true);
										} else {
											insuranceTypeDto.setIsCheck(false);
										}
										insuranceTypeDto
												.setCompanyRatio(ver.getCorpRatio().multiply(new BigDecimal(100)));
										insuranceTypeDto
												.setPersonalRatio(ver.getPsnRatio().multiply(new BigDecimal(100)));
										insuranceTypeDto.setEmpPaybase(ver.getPsnBaseLower());
										insuranceTypeDto.setCorpPaybase(ver.getCorpBaseLower());
										insuranceTypeDto.setCorpBaseLower(ver.getCorpBaseLower());
										insuranceTypeDto.setPsnBaseLower(ver.getPsnBaseLower());
										insuranceTypeDto.setCorpBaseUpper(ver.getCorpBaseUpper());
										insuranceTypeDto.setPsnBaseUpper(ver.getPsnBaseUpper());

										if (insuranceTypeDto.getCorpPaybase().compareTo(ver.getCorpBaseLower()) == 0 ) {
											insuranceTypeDto.setCorpBaseType("lower");
										} else if(insuranceTypeDto.getCorpPaybase().compareTo(ver.getCorpBaseUpper()) == 0 ) {
											insuranceTypeDto.setCorpBaseType("upper");
										}

										if (insuranceTypeDto.getEmpPaybase().compareTo(ver.getPsnBaseLower()) == 0 ) {
											insuranceTypeDto.setPsnBaseType("lower");
										} else  if (insuranceTypeDto.getEmpPaybase().compareTo(ver.getPsnBaseUpper()) == 0 ) {
											insuranceTypeDto.setPsnBaseType("upper");
										}
										insuranceTypeDtos.add(insuranceTypeDto);
										break;
									}
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								log.error("期间转换失败!", e);
								throw new RuntimeException("期间转换失败!", e);
							}
						}
					}
				}
			}
		}

		Map<String, InsuranceTypeDto> insuranceMaps = new LinkedHashMap<String, InsuranceTypeDto>();
		for (InsuranceTypeDto insuranceTypeDto : insuranceTypeDtos) {
			if (!insuranceMaps.containsKey(insuranceTypeDto.getInsuranceTypeId().toString())) {
				//
				// insuranceTypeDto.getCompanyRatioSet().add(insuranceTypeDto.getCompanyRatio());
				// insuranceTypeDto.getPersonalRatioSet().add(insuranceTypeDto.getPersonalRatio());

				insuranceTypeDto.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				insuranceTypeDto.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());

				insuranceMaps.put(insuranceTypeDto.getInsuranceTypeId().toString(), insuranceTypeDto);
			} else {
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());
			}
		}

		return new ArrayList<InsuranceTypeDto>(insuranceMaps.values());
	}

	/**
	 *
	 *  @param
	 * @return    :
	 *  @createDate   : 2017/1/19 14:10
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/19 14:10
	 *  @updateAuthor  :
	 */
	@Override
	public List<InsuranceTypeDto> queryAccountInsurancList(Integer accountId, String livingType, String beginDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		if ("".equals(livingType)) {
			livingType = null;
		}
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		// 根据库id获取库
		SpsAccount spsAccount = new SpsAccount();
		spsAccount.setAccId(accountId);
		spsAccount = spsAccountService.findByPK(spsAccount);
		List<InsuranceTypeDto> insuranceTypeDtos = new LinkedList<>();


		if (spsAccount != null) {

			//获取账户所有险种信息
			SpsAccountRatio spsAccountRadio = new SpsAccountRatio();
			spsAccountRadio.setAccId(spsAccount.getAccId());
			spsAccountRadio.setLivingTypeEq(livingType);
			List<SpsAccountRatio> accRatioList = spsAccountRatioService.findAllList(spsAccountRadio);
			if (accRatioList != null && accRatioList.size() > 0) {
				for (SpsAccountRatio spsAccountRatio : accRatioList) {
					BsArearatio bsArearatio = new BsArearatio();
					bsArearatio.setRatioId(spsAccountRatio.getRatioId());
					bsArearatio.setDr(0);
					//获取地区险种详细信息
					Map<String, Object> areaRatio = bsArearatioService.findAreaRatioDetail(bsArearatio);

					// 获取地区险种比例版本列表
					BsArearatiover bsArearatiover = new BsArearatiover();
					bsArearatiover.setRatioId(spsAccountRatio.getRatioId());
					bsArearatiover.setDr(0);
					List<BsArearatiover> aOverList = bsArearatioverService.findAll(bsArearatiover);
					if (aOverList != null && aOverList.size() > 0) {
						for (BsArearatiover ver : aOverList) {
							try {
								if (StringUtils.isNotEmpty(ver.getBeginPeriod()) && dateFormat.parse(nowDate).compareTo(dateFormat.parse(ver.getBeginPeriod())) >= 0&&
										(StringUtils.isEmpty(ver.getEndPeriod())||(StringUtils.isNotEmpty(ver.getEndPeriod())&&
												dateFormat.parse(nowDate).compareTo(dateFormat.parse(ver.getEndPeriod())) <= 0))){
									InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
									insuranceTypeDto.setRatioId(ver.getRatioId());
									insuranceTypeDto.setInsuranceTypeId(
											Integer.valueOf(areaRatio.get("insurance_id").toString()));
									insuranceTypeDto.setInsuranceName(areaRatio.get("insurance_name").toString());
									if ("Y".equals(spsAccountRatio.getIsdefault())) {
										insuranceTypeDto.setIsCheck(true);
									} else {
										insuranceTypeDto.setIsCheck(false);
									}
									insuranceTypeDto
											.setCompanyRatio(ver.getCorpRatio().multiply(new BigDecimal(100)));
									insuranceTypeDto
											.setPersonalRatio(ver.getPsnRatio().multiply(new BigDecimal(100)));
									insuranceTypeDto.setEmpPaybase(ver.getPsnBaseLower());
									insuranceTypeDto.setCorpPaybase(ver.getCorpBaseLower());

									insuranceTypeDto.setCorpAddition(ver.getCorpAddition());
									insuranceTypeDto.setPsnAddition(ver.getPsnAddition());
									insuranceTypeDto.setBsArearatiover(ver);
									insuranceTypeDtos.add(insuranceTypeDto);

								}
							}catch(Exception e){

							}
						}
					}
				}
			}
		}
		Map<String, InsuranceTypeDto> insuranceMaps = new LinkedHashMap<String, InsuranceTypeDto>();
		for (InsuranceTypeDto insuranceTypeDto : insuranceTypeDtos) {
			if (!insuranceMaps.containsKey(insuranceTypeDto.getInsuranceTypeId().toString())) {
				insuranceTypeDto.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				insuranceTypeDto.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());
				List<BsArearatiover> bsArearatiovers = new ArrayList<>();
				bsArearatiovers.add(insuranceTypeDto.getBsArearatiover());
				insuranceTypeDto.setBsArearatiovers(bsArearatiovers);
				insuranceMaps.put(insuranceTypeDto.getInsuranceTypeId().toString(), insuranceTypeDto);
			} else {
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());
				List<BsArearatiover> bsArearatiovers =((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString())).getBsArearatiovers();
				bsArearatiovers.add(insuranceTypeDto.getBsArearatiover());
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString())).setBsArearatiovers(bsArearatiovers);
			}
		}

		return new LinkedList<InsuranceTypeDto>(insuranceMaps.values());
	}

	/**
	 *  根据方案Id获取各种险种信息
	 *  @param schemceId
	 * @return    :
	 *  @createDate   : 2017/1/17 17:51
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/17 17:51
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,Object>> queryTotalInsuranceListBySchemceId(Integer accountId,String livingType,Integer schemceId) {

		//获取指定方案默认险种的比例及相关信息
		List<Map<String,Object>> spsSchemeInsuranceMaps = spsSchemeInsuranceDao.findSchemeInsuranceBySchemeIdAndAccId(accountId,livingType,schemceId);
		return  spsSchemeInsuranceMaps;

	}

	/**
	 * 根据社保公积金库查找库比例
	 *
	 * @param accountId
	 * @param livingType
	 * @return
	 */
	public List<InsuranceTypeDto> queryAccountInsuranceListByAccountId(Integer accountId, String livingType,
																	   Integer insuranceId, String beginDate) {
		// 获取当前时间，格式是年月
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String currentPeriod = dateFormat.format(date);

		if (beginDate != null && !"".equals(beginDate)) {
			currentPeriod = beginDate;
		}

		if ("".equals(livingType)) {
			livingType = null;
		}

		// 根据库id获取库
		SpsAccount spsAccount = new SpsAccount();
		spsAccount.setAccId(accountId);
		spsAccount = spsAccountService.findByPK(spsAccount);

		List<InsuranceTypeDto> insuranceTypeDtos = new ArrayList<>();

		if (spsAccount != null) {
			SpsAccountRatio spsAccountRadio = new SpsAccountRatio();
			spsAccountRadio.setAccId(spsAccount.getAccId());
			spsAccountRadio.setLivingTypeEq(livingType);
			List<SpsAccountRatio> accRatioList = spsAccountRatioService.findAll(spsAccountRadio);
			if (accRatioList != null && accRatioList.size() > 0) {
				for (SpsAccountRatio spsAccountRatio : accRatioList) {
					BsArearatio bsArearatio = new BsArearatio();
					bsArearatio.setRatioId(spsAccountRatio.getRatioId());
					Map<String, Object> areaRatio = bsArearatioService.findAreaRatioDetail(bsArearatio);
					if (Integer.valueOf(areaRatio.get("insurance_id").toString()).intValue() == insuranceId
							.intValue()) {
						// 获取当前时间所在的地区险种比例版本
						BsArearatiover bsArearatiover = new BsArearatiover();
						bsArearatiover.setRatioId(spsAccountRatio.getRatioId());
						List<BsArearatiover> aOverList = bsArearatioverService.findAll(bsArearatiover);

						if (aOverList != null && aOverList.size() > 0) {
							for (BsArearatiover ver : aOverList) {
								try {
									if (org.springframework.util.StringUtils.isEmpty(ver.getBeginPeriod())
											|| dateFormat.parse(ver.getBeginPeriod())
											.compareTo(dateFormat.parse(currentPeriod)) <= 0) {
										if (org.springframework.util.StringUtils.isEmpty(ver.getEndPeriod())
												|| dateFormat.parse(ver.getEndPeriod())
												.compareTo(dateFormat.parse(currentPeriod)) >= 0) {
											InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
											insuranceTypeDto.setRatioId(ver.getRatioId());
											insuranceTypeDto.setInsuranceTypeId(
													Integer.valueOf(areaRatio.get("insurance_id").toString()));
											insuranceTypeDto
													.setInsuranceName(areaRatio.get("insurance_name").toString());
											if ("Y".equals(spsAccountRatio.getIsdefault())) {
												insuranceTypeDto.setIsCheck(true);
											} else {
												insuranceTypeDto.setIsCheck(false);
											}
											insuranceTypeDto.setRatioName(areaRatio.get("name").toString());
											insuranceTypeDto
													.setCompanyRatio(ver.getCorpRatio().multiply(new BigDecimal(100)));
											insuranceTypeDto
													.setPersonalRatio(ver.getPsnRatio().multiply(new BigDecimal(100)));
											BigDecimal comRation = BigDecimal.ZERO;
											BigDecimal perRation = BigDecimal.ZERO;
											if (ver.getCorpAddition() != null) {
												comRation = ver.getCorpAddition();
											}

											if (ver.getPsnAddition() != null) {
												comRation = ver.getPsnAddition();
											}

											insuranceTypeDto.setCorpAddition(comRation);
											insuranceTypeDto.setPsnAddition(perRation);

											insuranceTypeDtos.add(insuranceTypeDto);
											break;
										}
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									log.error("期间转换失败!", e);
									throw new RuntimeException("期间转换失败!", e);
								}
							}
						}
					}
				}
			}

		}

		return insuranceTypeDtos;
	}


	@Override
	public List<InsuranceTypeDto> queryAccountInsuranceList(Integer accountId, String livingType, Integer insuranceId) {
		if ("".equals(livingType)) {
			livingType = null;
		}

		// 根据库id获取库
		SpsAccount spsAccount = new SpsAccount();
		spsAccount.setAccId(accountId);
		spsAccount = spsAccountService.findByPK(spsAccount);

		List<InsuranceTypeDto> insuranceTypeDtos = new ArrayList<>();

		if (spsAccount != null) {
			SpsAccountRatio spsAccountRadio = new SpsAccountRatio();
			spsAccountRadio.setAccId(spsAccount.getAccId());
			spsAccountRadio.setLivingTypeEq(livingType);
			List<SpsAccountRatio> accRatioList = spsAccountRatioService.findAll(spsAccountRadio);

			for (SpsAccountRatio spsAccountRatio : accRatioList) {
				BsArearatio bsArearatio = new BsArearatio();
				bsArearatio.setRatioId(spsAccountRatio.getRatioId());
				Map<String, Object> areaRatio = bsArearatioService.findAreaRatioDetail(bsArearatio);
				if (Integer.valueOf(areaRatio.get("insurance_id").toString()).intValue() == insuranceId
						.intValue()) {
					// 获取当前时间所在的地区险种比例版本
					BsArearatiover bsArearatiover = new BsArearatiover();
					bsArearatiover.setRatioId(spsAccountRatio.getRatioId());
					List<BsArearatiover> aOverList = bsArearatioverService.findAll(bsArearatiover);
					for (BsArearatiover ver : aOverList) {
						InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
						insuranceTypeDto.setRatioId(ver.getRatioId());
						insuranceTypeDto.setInsuranceTypeId(
								Integer.valueOf(areaRatio.get("insurance_id").toString()));
						insuranceTypeDto
								.setInsuranceName(areaRatio.get("insurance_name").toString());
						if ("Y".equals(spsAccountRatio.getIsdefault())) {
							insuranceTypeDto.setIsCheck(true);
						} else {
							insuranceTypeDto.setIsCheck(false);
						}
						insuranceTypeDto.setRatioName(areaRatio.get("name").toString());
						insuranceTypeDto
								.setCompanyRatio(ver.getCorpRatio().multiply(new BigDecimal(100)));
						insuranceTypeDto
								.setPersonalRatio(ver.getPsnRatio().multiply(new BigDecimal(100)));
						BigDecimal comRation = BigDecimal.ZERO;
						BigDecimal perRation = BigDecimal.ZERO;
						if (ver.getCorpAddition() != null) {
							comRation = ver.getCorpAddition();
						}
						if (ver.getPsnAddition() != null) {
							comRation = ver.getPsnAddition();
						}
						insuranceTypeDto.setCorpAddition(comRation);
						insuranceTypeDto.setPsnAddition(perRation);
						insuranceTypeDto.setBsArearatiover(ver);

						insuranceTypeDtos.add(insuranceTypeDto);
					}
				}
				break;
			}
		}

		return insuranceTypeDtos;
	}

	/**
	 * 查询该企业下身份证号+姓名
	 *
	 * @param cpid
	 * @param idcard
	 * @return
	 */
	private Boolean checkEpIdcardByCpidAndIdcardAndName(Integer cpid, String idcard, String name) {
		CmEmployee cmEmployee = new CmEmployee();
		cmEmployee.setCpId(cpid);
		cmEmployee.setNameEq(name);
		cmEmployee.setIdentityCodeEq(idcard);
		cmEmployee.setDr(0);
		return cmEmployeeDao.CountFreeFindCmEmployee(cmEmployee) > 0;
	}

	/* --------- cs 合并 start -------- */

	/**
	 * <p>
	 * Title: bs询社保在缴公积金在缴总人数
	 * </p>
	 * <p>
	 * Description: bs询社保在缴公积金在缴总人数
	 * </p>
	 * ${tags}
	 */
	@Override
	public int countFindAllEmployee() {
		return cmEmployeeDao.countFindAllEmployee();
	}

	/**
	 * <p>
	 * Title: bs按创建日期查询社保在缴公积金在缴总人数
	 * </p>
	 * <p>
	 * Description: bs按创建日期查询社保在缴公积金在缴总人数
	 * </p>
	 * ${tags}
	 */
	@Override
	public List<Map<String, Object>> countByDayEmployee(String stringDate) {

		return cmEmployeeDao.countByDayEmployee(stringDate);
	}

	/**
	 * <p>
	 * Title: cs单表删除员工
	 * </p>
	 * <p>
	 * Description: cs单表删除员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public int removeToCs(ContextInfo cti, CmEmployee vo) {
		return cmEmployeeDao.remove(cti, vo);
	}

	/**
	 * <p>
	 * Title: cs分页查询员工
	 * </p>
	 * <p>
	 * Description: cs分页查询员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public PageModel findPageToCs(PageInfo pi, CSCmEmployeeDto vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.countPageFind(vo);
		pm.setTotal(total);
		List<CmEmployee> datas = cmEmployeeDao.freeFindPage(vo, pageIndex, pageSize, " create_dt desc");
		pm.setDatas(datas);
		return pm;

	}

	/**
	 * <p>
	 * Title: cs准备导入excel
	 * </p>
	 * <p>
	 * Description: cs准备导入excel
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result readImportFileInfoToCs(Integer fileId, ContextInfo cti) throws Exception {
		Result result = Result.createResult().setSuccess(true);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (StringUtils.isBlank(filePath)) {
			result.setError("文件不存在！");
			return result;
		}
		// 前端传来标题匹配字段<K,V>
		Map<String, String> colNameMap = new HashMap<String, String>();
		colNameMap.put("姓名", "姓名");
		colNameMap.put("证件号码", "证件号码");
		colNameMap.put("证件类型", "证件类型");
		colNameMap.put("职位", "职位");
		colNameMap.put("手机号", "手机号");

		Map<String, Map<String, String>> sheet_orm = new HashMap<String, Map<String, String>>();
		sheet_orm.put("员工导入模板", colNameMap);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Map<Integer, Map<String, String>> resObj = null;
		try {
			resObj = ImportExcelUtil.importExcel(filePath, getEmployeeFieldMapToCs(), sheet_orm);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}

		if (resObj.isEmpty()) {
			result.setSuccess(false);
			result.setError("未在选择的表中获得数据！");
			return result;
		}
		for (Map.Entry<Integer, Map<String, String>> entry : resObj.entrySet()) {
			Map<String, String> curMap = entry.getValue();
			curMap.put("index", entry.getKey().toString());
			dataList.add(curMap);
		}
		result.setDataValue("dataList", dataList);
		result.setDataValue("listSize", dataList.size());
		result.setDataValue("msg", "您一共提交" + dataList.size() + "条数据");
		return result;
	}

	/**
	 * <p>
	 * Title: cs导入excel第二部
	 * </p>
	 * <p>
	 * Description: cs导入excel第二部
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result readTemplateRealtionToCs(Integer fileId, String sheetName, String corpExist) throws Exception {
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (StringUtils.isBlank(filePath)) {
			result.setError("文件不存在！");
			return result;
		}
		// 根据 业务类型 查询 字段信息
		List<Map<String, String>> sysfields = new ArrayList<Map<String, String>>();
		sysfields.add(getEmployeeFieldMap());

		Map<String, Map<String, Map<String, Object>>> resObj = ImportExcelUtil.readExcelTitle(filePath, sysfields, null,
				null, null, sheetName);
		result.setDataValue("sheetData", resObj);
		// result.setDataValue("isShowSelectSheet",
		// resObj.size()<=1?"0":"1");先去掉这个，如果只有一个sheet时 显示默认选中

		result.setSuccess(true);
		return result;
	}

	/**
	 * <p>
	 * Title: cs读取excel列表
	 * </p>
	 * <p>
	 * Description: cs读取excel列表
	 * </p>
	 * ${tags}
	 */
	@Override
	public Map<String, Object> readExcelTitleToCs(String filePath, ContextInfo cti) throws Exception {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		excelFiles.add(new File(filePath));
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			xwb = new XSSFWorkbook(fin);
			// 读取表格内容
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				List<String> titleList = new ArrayList<String>();
				// 遇到隐藏的sheet页 不读取
				boolean sheethide = xwb.isSheetHidden(i);
				if (sheethide) {
					continue;
				}
				Sheet sheet = xwb.getSheetAt(i);
				// 定义 row、cell
				// 循环输出表格中的内容
				Row row = sheet.getRow(0);
				if (null != row && row.getPhysicalNumberOfCells() > 1) {// row可能为空
					Map<String, Object> sheetMap = new HashMap<String, Object>();
					boolean corpExist = false;
					int corpIndex = 0;
					for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
						String cellContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
						if (StringUtils.isNotBlank(cellContent)) {
							titleList.add(cellContent);
						}
					}
					if (null != titleList && titleList.size() > 0) {
						sheetMap.put("titleList", titleList);
					}
					// sheet格式没问题则返回
					if (!sheetMap.isEmpty()) {
						result.put(sheet.getSheetName(), sheetMap);
					}
				}

			}
			fin.close();
		}
		return result;
	}

	/**
	 * <p>
	 * Title: 返回员工字段信息集合
	 * </p>
	 * <p>
	 * Description: 返回员工字段信息集合
	 * </p>
	 * ${tags}
	 */
	private Map<String, String> getEmployeeFieldMapToCs() {
		Map<String, String> fieldMap = new HashMap<>();
		fieldMap.put("姓名", "name");
		fieldMap.put("证件号码", "identityCode");
		fieldMap.put("证件类型", "identityType");
		fieldMap.put("职位", "job");
		fieldMap.put("手机号", "mobile");
		return fieldMap;
	}

	/**
	 * <p>
	 * Title: cs导入员工
	 * </p>
	 * <p>
	 * Description: cs导入员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result importEmpExcel(ContextInfo cti, Integer fileId) throws BusinessException {
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

		// 前端传来标题匹配字段<K,V>
		Map<String, String> colNameMap = new HashMap<String, String>();
		colNameMap.put("姓名", "姓名");
		colNameMap.put("证件号码", "证件号码");
		colNameMap.put("证件类型", "证件类型");
		colNameMap.put("职位", "职位");
		colNameMap.put("手机号", "手机号");

		Map<String, Map<String, String>> sheet_orm = new HashMap<String, Map<String, String>>();
		sheet_orm.put("员工导入模板", colNameMap);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
		Map<Integer, Map<String, String>> resObj = null;
		try {
			resObj = ImportExcelUtil.importExcel(filePath, getEmployeeFieldMapToCs(), sheet_orm);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}
		// 户口性质 农业 城镇
		Map<String, String> sysDicMap = new HashMap<String, String>();
		List<SysDictitem> listdic = sysDictitemDAO.findByDictName("liveType");
		for (SysDictitem sysDict : listdic) {
			sysDicMap.put(sysDict.getName(), sysDict.getCode());
		}

		// 证件类型
		Map<String, String> IdTypeMap = new HashMap<String, String>();
		listdic = sysDictitemDAO.findByDictName("IDType");
		if (CollectionUtils.isNotEmpty(listdic)) {
			for (SysDictitem sysDict : listdic) {
				IdTypeMap.put(sysDict.getName(), sysDict.getCode());
			}
		}

		// 查询当前服务商 当前企业 有效员工
		Map<String, String> idCardMap = new HashMap<String, String>();
		// 查询用员工类
		CmEmployee cmep = new CmEmployee();

		// 社保状态 公积金状态 导入Excel里只有这两项
		Map<String, String> insfunMap = new HashMap<String, String>();
		insfunMap.put("正常缴纳", "ON");
		insfunMap.put("未缴纳", "OFF");

		Set<String> addCropName = new HashSet();

		if (resObj.isEmpty()) {
			result.setSuccess(false);
			result.setError("未在选择的表中获得数据！");
			return result;
		}
		IdcardValidator iv = new IdcardValidator();
		// 保存用日期
		Date date = new Date();
		// 数据是否有误
		boolean isError = false;
		// 地区字典
		Map<String, String> areaMap = new HashMap<>();
		List<BsArea> areaList = bsAreaDao.freeFind(new BsArea());
		if (CollectionUtils.isNotEmpty(areaList)) {
			for (BsArea area : areaList) {
				areaMap.put(area.getName(), area.getCode());
			}
		}
		// 地区社保类型
		List<Map<String, Object>> listdict = new ArrayList<>();
		// mapList.get("insurance_code")//社保 缴纳账户地区编码
		listdict = sysDictitemDAO.queryForList("SYS_DICTITEM.findCblb", null);
		Map<String, Map<String, String>> shebaoMap = new HashMap<>();
		if (null != listdict) {
			for (Map<String, Object> dict : listdict) {
				Map<String, String> sbMap = shebaoMap.get(dict.get("area_code").toString());
				if (sbMap == null) {
					sbMap = new HashMap<>();
					shebaoMap.put(dict.get("area_code").toString(), sbMap);
				}
				sbMap.put(dict.get("name").toString(), dict.get("code").toString());
			}
		}

		for (Map.Entry<Integer, Map<String, String>> entry : resObj.entrySet()) {
			Map<String, String> curMap = entry.getValue();
			String error = "";
			curMap.put("row", entry.getKey() + "");
			if (StringUtils.isEmpty(curMap.get("name"))) {
				error += "姓名为空！<br>";
			}

			if (StringUtils.isEmpty(curMap.get("identityCode"))) {
				error += "证件号为空！<br>";
			} else {
				String idcar = curMap.get("identityCode");
				// 校验唯一性 名称+证件号
				String idVerify = idcar + curMap.get("name");
				if (StringUtils.isBlank(curMap.get("identityType")) || curMap.get("identityType").equals("身份证")) {
					if (!iv.isValidate18Idcard(idcar) && !iv.isValidate15Idcard(idcar)) {
						error += "身份证号格式错误！<br>";
					} else if ("Excel".equals(idCardMap.get(idVerify))) {
						error += "导入Excel表中员工身份证已存在！<br>";
					} else if (cti.getOrgId() != null && curMap.get("name") != null
							& this.checkEpIdcardByCpidAndIdcardAndName(cti.getOrgId(), idcar, curMap.get("name"))) {
						error += "员工在该企业已存在，请注意！<br>";
					} else {
						idCardMap.put(idVerify, "Excel");
						curMap.put("identityType", "IDCard");// 证件类型 IDType
						// 身份证：ID
					}
				} else {
					if (StringUtils.isBlank(IdTypeMap.get(curMap.get("identityType")))) {
						error += "证件类型不存在！<br>";
					} else {
						idCardMap.put(idVerify, "Excel");
						curMap.put("identityType", IdTypeMap.get(curMap.get("identityType")));// 证件类型
						// IDType
						// 身份证：ID
					}
				}
			}

			if (StringUtils.isNotEmpty(curMap.get("mobile"))) {
				// 科学计数法转正常数字str
				if (VerificationUtils.isENum(curMap.get("mobile"))) {
					curMap.put("mobile", VerificationUtils.eNumConvertStr(curMap.get("mobile")));
				}
				if (!MobileValidator.isMobileNO(curMap.get("mobile"))) {
					error += "手机号格式错误！<br>";
				}
			} else {
				error += "手机号不能为空！<br>";
			}
			if (!"".equals(error)) {
				curMap.put("error", error);
				errorDataList.add(curMap);
				isError = true;
			}
			dataList.add(curMap);
		}
		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataList", errorDataList);
			result.setDataValue("errorNum", errorDataList.size());
			result.setDataValue("dataSize", dataList.size());
		} else {
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {
				// 数据库中已存在并且未删除的员工
				CmEmployee dbCmpl = new CmEmployee();
				dbCmpl.setCpId(cti.getOrgId());
				dbCmpl.setDr(0);
				List<CmEmployee> dbEmps = cmEmployeeDao.freeFind(dbCmpl);
				Map<Integer, Map<String, CmEmployee>> existEmp = new HashMap<>();
				if (CollectionUtils.isNotEmpty(dbEmps)) {
					for (CmEmployee employee : dbEmps) {
						if (employee.getCpId() != null) {
							Map<String, CmEmployee> empMap = existEmp.get(employee.getCpId());
							if (empMap == null) {
								empMap = new HashMap<>();
								existEmp.put(employee.getCpId(), empMap);
							}
							empMap.put(employee.getIdentityCode() + employee.getName(), employee);
						}
					}
				}

				for (int i = 0; i < dataList.size(); i++) {
					Boolean dbExist = false;
					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");
					CmEmployee cmpl = new CmEmployee();
					Map<String, CmEmployee> empMap = existEmp.get(cti.getOrgId());
					String idVerify = curMap.get("identityCode") + curMap.get("name");
					if (empMap != null && empMap.get(idVerify) != null) {
						cmpl = empMap.get(idVerify);
						dbExist = true;
					}
					cmpl.setName(curMap.get("name"));
					cmpl.setIdentityCode(curMap.get("identityCode"));
					cmpl.setMobile(curMap.get("mobile"));
					cmpl.setIdentityType(curMap.get("identityType"));// 身份证 ID
					cmpl.setJob(curMap.get("job"));
					if (!dbExist) {
						cmpl.setInsuranceState("OFF");// 字典置换
						cmpl.setFundState("OFF");// 字典置换
						cmpl.setCpId(cti.getOrgId());
						cmpl.setIsQuit(0);
						cmpl.setDepId(0);
						cmpl.setDr(0);// 0 正常。
						cmpl.setCreateBy(cti.getUserId());
						cmpl.setCreateDt(date);
					} else {
						cmpl.setModifyBy(cti.getUserId());
						cmpl.setModifyDt(date);
					}

					Integer insert = null;
					if (dbExist && cmpl.getEmpId() != null) {
						insert = cmEmployeeDao.update(cti, cmpl);
					} else {
						insert = cmEmployeeDao.insert(cti, cmpl);
					}
					if (insert > 0) {
						result.setSuccess(true);
						result.setDataValue("successNum", dataList.size());
					} else {
						result.setSuccess(false);
						result.setError("数据导入错误！");
						break;
					}
				}
				if (result.isSuccess()) {
					result.setDataValue("msg", "共导入" + dataList.size() + "条员工信息！");
				}
			} else {
				result.setError("数据导入错误！");
				result.setSuccess(false);
			}
		}
		return result;
	}

	/**
	 * 查询该企业下身份证号是否存在
	 *
	 * @param cpid
	 * @param idcard
	 * @return
	 */
	private Boolean checkEpIdcardByCpidAndIdcard(Integer cpid, String idcard, String name) {
		CmEmployee cmEmployee = new CmEmployee();
		cmEmployee.setCpId(cpid);
		cmEmployee.setNameEq(name);
		cmEmployee.setIdentityCodeEq(idcard);
		cmEmployee.setDr(0);
		return cmEmployeeDao.CountFreeFindCmEmployee(cmEmployee) > 0;
	}

	/**
	 * <p>
	 * Title: cs新增员工
	 * </p>
	 * <p>
	 * Description: cs新增员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result saveEmployeeToCs(ContextInfo cti, CmEmployee cmEmployee) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null != cmEmployee && StringUtils.isNotEmpty(cmEmployee.getEmpId() + "")
				&& !StringUtils.isBlank(cmEmployee.getIdentityCode())) {
			// 当前企业内 所有员工身份证
			int checkCount = cmEmployeeDao.checkEmpIdCardAndName(cmEmployee);
			if (checkCount > 0) {
				result.setSuccess(false);
				result.setError("新增失败身份证号与姓名组合重复！");
				return result;
			} else {
				cmEmployee.setCreateBy(cti.getUserId());
				cmEmployee.setCreateDt(date);
				cmEmployee.setDr(0);
				cmEmployee.setFundState("OFF");
				cmEmployee.setInsuranceState("OFF");
				Integer ins = this.insert(cti, cmEmployee);
				if (ins > 0) {
					result.setSuccess(true);
					result.setError("新增员工成功！");
					return result;
				} else {
					result.setSuccess(false);
					result.setError("新增员工失败！");
					return result;
				}
			}
		} else {
			result.setSuccess(false);
			result.setError("新增员工失败！");
			return result;
		}
	}

	/**
	 * <p>
	 * Title: cs更新员工
	 * </p>
	 * <p>
	 * Description: cs更新员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result updateEmployeeToCs(ContextInfo cti, CmEmployee cmEmployee) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null == cmEmployee) {
			result.setSuccess(false);
			result.setError("提交的信息不完整");
			return result;
		}
		if (null == cmEmployee.getEmpId()) {
			result.setSuccess(false);
			result.setError("员工id不能为空");
			return result;
		}
		CmEmployee oldemp = new CmEmployee();
		oldemp.setEmpId(cmEmployee.getEmpId());
		oldemp.setCpId(cti.getOrgId());
		List<CmEmployee> list = cmEmployeeDao.freeFind(oldemp);
		if (CollectionUtils.isEmpty(list)) {
			result.setSuccess(false);
			result.setError("员工不存在");
			return result;
		}
		oldemp = list.get(0);
		CmEmployee saveEmp = new CmEmployee();
		saveEmp.setEmpId(cmEmployee.getEmpId());
		saveEmp.setName(cmEmployee.getName());
		saveEmp.setIdentityCode(cmEmployee.getIdentityCode());
		saveEmp.setMobile(cmEmployee.getMobile());
		saveEmp.setEmail(cmEmployee.getEmail());
		saveEmp.setJob(cmEmployee.getJob());
		if ("ON".equals(oldemp.getInsuranceState()) || "ON".equals(oldemp.getFundState())) {
			saveEmp.setName(null);
			saveEmp.setIdentityCode(null);
		} else {
			if (StringUtils.isBlank(saveEmp.getName())) {
				result.setSuccess(false);
				result.setError("员工姓名不能为空");
				return result;
			}
			if (StringUtils.isBlank(saveEmp.getIdentityCode())) {
				result.setSuccess(false);
				result.setError("员工身份证号不能为空");
				return result;
			}
			// 当前当前企业内 所有员工身份证
			int checkCount = cmEmployeeDao.checkEmpIdCardAndName(saveEmp);
			if (checkCount > 0) {
				result.setSuccess(false);
				result.setError("修改失败身份证号与姓名组合重复！");
				return result;
			}
		}
		if (!MobileValidator.isMobileNO(saveEmp.getMobile())) {
			result.setSuccess(false);
			result.setError("手机号格式不正确");
			return result;
		}
		saveEmp.setModifyBy(cti.getUserId());
		saveEmp.setModifyDt(date);
		Integer ins = this.update(cti, saveEmp);
		if (ins > 0) {
			result.setSuccess(true);
			result.setError("修改员工成功！");
			return result;
		} else {
			result.setSuccess(false);
			result.setError("修改员工失败！");
			return result;
		}

	}

	/**
	 * <p>
	 * Title: 根据ID获取员工信息
	 * </p>
	 * <p>
	 * Description: 根据ID获取员工信息
	 * </p>
	 * ${tags}
	 */
	@Override
	public CmEmployee queryEmployeeById(CmEmployee vo) {
		return cmEmployeeDao.findByPK(vo);
	}

	/**
	 * <p>
	 * Title: cs新增员工
	 * </p>
	 * <p>
	 * Description: cs新增员工
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result add(ContextInfo cti, CmEmployee employee) {
		Result result = new Result();
		if (checkEpIdcardByCpidAndIdcard(cti.getOrgId(), employee.getIdentityCode(), employee.getName())) {
			result.setSuccess(false).setError("员工已存在");
			return result;
		}
		employee.setCpId(cti.getOrgId());
		employee.setCreateBy(cti.getUserId());
		employee.setCreateDt(new Date());
		employee.setIdentityType("IDCard");
		employee.setDr(0);
		employee.setDepId(0);
		employee.setIsQuit(0);
		employee.setFundState("OFF");
		employee.setInsuranceState("OFF");
		if (cmEmployeeDao.insert(cti, employee) > 0) {
			result.setDataValue("msg", "新增成功");
			result.setDataValue("empId", employee.getEmpId());
		} else {
			result.setSuccess(false).setError("新增失败");
		}
		return result;
	}

	/**
	 * 身份证号算性别
	 *
	 * @param identityCode
	 * @return
	 */
	private String identityCalaSex(String identityCode) {
		Integer sexCode = 0;
		if (identityCode.length() == 18) {
			sexCode = Integer.parseInt(identityCode.substring(16, 17));
		} else if (identityCode.length() == 15) {
			sexCode = Integer.parseInt(identityCode.substring(14));
		}
		if (sexCode % 2 == 1) {
			return "男";
		} else {
			return "女";
		}
	}

	/**
	 * 身份证号算年龄
	 *
	 * @param identityCode
	 * @return
	 */
	private Integer identityCalaAge(String identityCode) {
		Integer age = 0;
		Calendar a = Calendar.getInstance();
		Integer thisYear = a.get(Calendar.YEAR);
		if (identityCode.length() == 18) {
			age = Integer.parseInt(identityCode.substring(6, 10));
		} else if (identityCode.length() == 15) {
			age = Integer.parseInt(identityCode.substring(6, 8));
			age += 1900;
		}
		return thisYear - age;
	}

	/**
	 * <p>
	 * Title: cs查询人员业务类
	 * </p>
	 * <p>
	 * Description: cs查询人员业务类
	 * </p>
	 * ${tags}
	 */
	@Override
	public CSCmEmployeeDto findEmplInfo(ContextInfo cti, Integer empId) {
		CSCmEmployeeDto cmEmployeeDto = new CSCmEmployeeDto();
		CmEmployee query = new CmEmployee();
		query.setDr(0);
		query.setCpId(cti.getOrgId());
		query.setEmpId(empId);
		List<CmEmployee> list = cmEmployeeDao.freeFind(query);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		BeanUtils.copyProperties(list.get(0), cmEmployeeDto);
		if (cmEmployeeDto.getDepId() != null) {
			CmDepartment department = cmDepartmentDao.findByIdAndOrg(cmEmployeeDto.getDepId(), cti.getOrgId());
			if (department != null) {
				cmEmployeeDto.setDepName(department.getDepName());
			}
		}
		// 户口所在地
		if (StringUtils.isNotBlank(cmEmployeeDto.getIdentityCode()) && cmEmployeeDto.getIdentityCode().length() > 6) {
			cmEmployeeDto.setCityName(
					IdentityUtil.identityCityMap.get(cmEmployeeDto.getIdentityCode().substring(0, 6)));
		}
		cmEmployeeDto.setSex(this.identityCalaSex(cmEmployeeDto.getIdentityCode()));
		cmEmployeeDto.setAge(this.identityCalaAge(cmEmployeeDto.getIdentityCode()));
		return cmEmployeeDto;
	}

	/**
	 * 查询子机构id集合 最多从传入id向下查10级
	 *
	 * @param cti
	 * @param depId
	 * @return 包含传入id
	 */
	private List<Integer> findSubDepByParentId(ContextInfo cti, Integer depId) {
		List<Integer> depIds = new ArrayList<>();
		depIds.add(depId);
		CmDepartment query = new CmDepartment();
		query.setOrgId(cti.getOrgId());
		/**
		 * 查询该企业所有部门
		 */
		List<CmDepartment> list = cmDepartmentDao.freeFind(query);
		Boolean isSub = true;
		/**
		 * 最多向下查10级
		 */
		for (int i = 0; i < 10 && isSub; i++) {
			isSub = false;
			for (Iterator<CmDepartment> departmentIterator = list.iterator(); departmentIterator.hasNext();) {
				CmDepartment department = departmentIterator.next();
				if (depIds.contains(department.getParentId())) {
					depIds.add(department.getDepId());
					isSub = true;
					// 防止重复添加至depids
					departmentIterator.remove();
				}
			}
		}
		return depIds;
	}

	/**
	 * <p>
	 * Title: cs查询子机构id集合 最多从传入id向下查10级
	 * </p>
	 * <p>
	 * Description: cs查询子机构id集合 最多从传入id向下查10级
	 * </p>
	 * ${tags}
	 */
	@Override
	public PageModel findPage(PageInfo pi, ContextInfo cti, CSCmEmployeeDto vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		if (vo.getDepId() != null) {
			// 查询全部部门
			if (vo.getDepId() == -1) {
				vo.setDepId(null);
			} else if (vo.getDepId() == 0) {
				// 未分配
			} else {
				List<Integer> depIds = this.findSubDepByParentId(cti, vo.getDepId());
				StringBuilder sb = new StringBuilder();
				for (Integer depId : depIds) {
					sb.append(depId).append(",");
				}
				String depIn = sb.delete(sb.length() - 1, sb.length()).toString();
				vo.setDepId(null);
				vo.setDepIn(depIn);
			}
		}
		vo.setDr(0);
		vo.setCpId(cti.getOrgId());
		Integer total = cmEmployeeDao.countPageFind(vo);
		pm.setTotal(total);
		List<CmEmployee> datas = cmEmployeeDao.freeFindPage(vo, pageIndex, pageSize, " create_dt desc");
		// pm.setPageCount(total%pageSize > 0 ? total/pageSize
		// +1:total/pageSize);
		pm.setDatas(convert(datas));
		return pm;

	}

	/**
	 * cs 实体类转换业务类
	 *
	 * @param list
	 * @return
	 */
	private List<CSCmEmployeeDto> convert(List<CmEmployee> list) {
		List<CSCmEmployeeDto> dtoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			for (CmEmployee employee : list) {
				CSCmEmployeeDto dto = new CSCmEmployeeDto();
				BeanUtils.copyProperties(employee, dto);
				// 社保
				if ("INCREASING".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("增员中");
				} else if ("UNDEAL".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("暂不处理");
				} else if ("ON".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("正常缴纳");
				} else if ("DECREASING".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("减员中");
				} else if ("DECREASED".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("已减员");
				} else if ("OFF".equals(dto.getInsuranceState())) {
					dto.setInsuranceStateName("未缴纳");
				}
				// 公积金

				if ("INCREASING".equals(dto.getFundState())) {
					dto.setFundStateName("增员中");
				} else if ("UNDEAL".equals(dto.getFundState())) {
					dto.setFundStateName("暂不处理");
				} else if ("ON".equals(dto.getFundState())) {
					dto.setFundStateName("正常缴纳");
				} else if ("DECREASING".equals(dto.getFundState())) {
					dto.setFundStateName("减员中");
				} else if ("DECREASED".equals(dto.getFundState())) {
					dto.setFundStateName("已减员");
				} else if ("OFF".equals(dto.getFundState())) {
					dto.setFundStateName("未缴纳");
				}
				// 最新任务单状态
				List<SpsTask> taskList = spsTaskDao.findTaskInBsType(dto.getEmpId(), null, null,
						new String[] { "SUBMIT", "TODO", "COMPLETED", "CLOSED" }, 0, 1);
				if (!CollectionUtils.isEmpty(taskList)) {
					SpsTask ins = taskList.get(0);
					if (ins.getType().equals("TODO")) {
						dto.setTaskStatus("处理中");
					} else if (ins.getType().equals("COMPLETED")) {
						dto.setTaskStatus("已完成");
					} else if (ins.getType().equals("SUBMIT")) {
						dto.setTaskStatus("等待处理");
					} else if (ins.getType().equals("CLOSED")) {
						dto.setTaskStatus("已关闭");
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * <p>
	 * Title: cs更改员工部门
	 * </p>
	 * <p>
	 * Description: cs更改员工部门
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result updateEmplDepId(ContextInfo cti, String empIds, Integer depId) {
		Result result = new Result();
		if (depId == null) {
			result.setSuccess(false).setError("部门不能为空");
			return result;
		}
		if (StringUtils.isBlank(empIds)) {
			result.setSuccess(false).setError("员工不能为空");
			return result;
		}

		String[] empid = empIds.split(",");
		for (String id : empid) {
			if (!NumberUtils.isNumber(id)) {
				result.setSuccess(false).setError("员工不存在");
				return result;
			}
		}
		// 裁剪id 防止格式错误
		if (empIds.indexOf(",") == 0) {
			empIds = empIds.substring(1, empIds.length());
		}
		if (empIds.lastIndexOf(",") == empIds.length() - 1) {
			empIds = empIds.substring(0, empIds.length() - 1);
		}
		CmDepartment department = new CmDepartment();
		department.setDepId(depId);
		department.setOrgId(cti.getOrgId());
		if (CollectionUtils.isEmpty(cmDepartmentDao.freeFind(department))) {
			result.setSuccess(false).setError("部门不存在");
			return result;
		}
		Integer resultNum = cmEmployeeDao.updateDepId(cti, empIds, depId, cti.getOrgId());
		if (resultNum > 0) {
			result.setSuccess(true).setError("成功移动" + resultNum + "名员工");
		} else {
			result.setSuccess(false).setError("移动失败");
		}
		return result;
	}

	/**
	 * <p>
	 * Title: cs更新员工头像
	 * </p>
	 * <p>
	 * Description: cs更新员工头像
	 * </p>
	 * ${tags}
	 */
	@Override
	public Result updateEmplPhoto(ContextInfo cti, CmEmployee cmEmployee) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null == cmEmployee) {
			result.setSuccess(false);
			result.setError("提交的信息不完整");
			return result;
		}
		if (null == cmEmployee.getEmpId()) {
			result.setSuccess(false);
			result.setError("员工id不能为空");
			return result;
		}
		CmEmployee oldemp = cmEmployeeDao.findByPK(cmEmployee);
		if (oldemp == null) {
			result.setSuccess(false);
			result.setError("员工不存在");
			return result;
		}
		if (StringUtils.isBlank(cmEmployee.getHeadPhoto())) {
			result.setSuccess(false);
			result.setError("头像图片不能为空");
			return result;
		}
		CmEmployee saveEmp = new CmEmployee();
		saveEmp.setEmpId(cmEmployee.getEmpId());
		saveEmp.setHeadPhoto(cmEmployee.getHeadPhoto());
		saveEmp.setModifyBy(cti.getUserId());
		saveEmp.setModifyDt(date);
		Integer ins = this.update(cti, saveEmp);
		if (ins > 0) {
			result.setSuccess(true);
			result.setError("更新头像成功！");
			return result;
		} else {
			result.setSuccess(false);
			result.setError("更新头像失败！");
			return result;
		}

	}

	/**
	 * 通过id查询员工并补全livingTypeName
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public CmEmployee findVoByPK(CmEmployee vo) {
		CmEmployee cmEmployee = cmEmployeeDao.findByPK(vo);
		if (cmEmployee != null && StringUtils.isNotBlank(cmEmployee.getInsuranceLivingType())) {
			List<SysDictitem> list = sysDictitemDAO.findLivingTypeByCode(cmEmployee.getInsuranceLivingType());
			if (CollectionUtils.isNotEmpty(list)) {
				cmEmployee.setInsuranceLivingTypeEq(list.get(0).getName());
			}
		}
		return cmEmployee;
	}

	/**
	 * 查询方案下类型险种
	 *
	 * @param schemeId
	 * @param insuFundType
	 * @return
	 */
	@Override
	public List<InsuranceTypeDto> queryInsuTypeList(Integer schemeId, String insuFundType, String insuranceLivingType) {
		SpsScheme scheme = new SpsScheme();
		scheme.setSchemeId(schemeId);
		scheme = spsSchemeDao.findByPK(scheme);
		if (scheme == null) {
			return null;
		}
		BsArea bsArea = new BsArea();
		bsArea.setAreaId(scheme.getAreaId());
		bsArea = bsAreaDao.findByPK(bsArea);
		Integer areaId = scheme.getAreaId();
		// 先查本地类型
		List<Map<String, Object>> insuTypes = bdInsuranceareaService.findInsurancesByAreaIdAndType(bsArea.getAreaId(),
				insuFundType);
		if (CollectionUtils.isEmpty(insuTypes)) {
			areaId = bsArea.getBelongCity();
			insuTypes = bdInsuranceareaService.findInsurancesByAreaIdAndType(bsArea.getBelongCity(), insuFundType);
		}
		if (CollectionUtils.isEmpty(insuTypes)) {
			return null;
		}
		SpsSchemeInsurance query = new SpsSchemeInsurance();
		query.setSchemeId(schemeId);
		List<SpsSchemeInsurance> defauleTypes = spsSchemeInsuranceDao.freeFind(query);
		Map<Integer, SpsSchemeInsurance> defauleTypeIds = new HashMap<>();
		if (!CollectionUtils.isEmpty(defauleTypes)) {
			for (SpsSchemeInsurance schemeInsurance : defauleTypes) {
				defauleTypeIds.put(schemeInsurance.getInsuranceId(), schemeInsurance);
			}
		}
		List<InsuranceTypeDto> insuranceTypeDtos = new ArrayList<>();
		for (Map<String, Object> insutype : insuTypes) {
			InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
			insuranceTypeDto.setInsuranceTypeId(Integer.parseInt(insutype.get("insurance_id").toString()));
			insuranceTypeDto.setInsuranceName(insutype.get("name").toString());
			insuranceTypeDto.setIsCheck(defauleTypeIds.containsKey(insuranceTypeDto.getInsuranceTypeId()));
			if (defauleTypeIds.containsKey(insuranceTypeDto.getInsuranceTypeId())) {
				SpsSchemeInsurance spsSchemeInsurance = defauleTypeIds.get(insuranceTypeDto.getInsuranceTypeId());
				insuranceTypeDto.setCompanyRatio(spsSchemeInsurance.getCorpRatio() != null
						? spsSchemeInsurance.getCorpRatio().multiply(new BigDecimal(100)) : null);
				insuranceTypeDto.setPersonalRatio(spsSchemeInsurance.getEmpRatio() != null
						? spsSchemeInsurance.getEmpRatio().multiply(new BigDecimal(100)) : null);
			}
			if (insuranceTypeDto.getCompanyRatio() == null) {
				insuranceTypeDto.setCompanyRatio(BigDecimal.ZERO);
			}
			if (insuranceTypeDto.getPersonalRatio() == null) {
				insuranceTypeDto.setPersonalRatio(BigDecimal.ZERO);
			}
			insuranceTypeDtos.add(insuranceTypeDto);
		}
		return insuranceTypeDtos;
	}

	/**
	 * 分页查询批量修改比例员工
	 *
	 * @param ratioEmpQuery
	 *            查询条件
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 15:18 2016/11/16
	 * @author : yangfw@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 15:18 2016/11/16
	 * @updateAuthor :
	 */
	@Override
	public PageModel findBatchRatioEmpPage(ContextInfo cti,PageInfo info, BatchRatioEmpQuery ratioEmpQuery) {

		PageModel pm = new PageModel(info);
		int pageIndex = info.getOffset();
		int pageSize = info.getPagesize();
		// 转换查询
		if (StringUtils.isNotBlank(ratioEmpQuery.getStatesStr())) {
			ratioEmpQuery.setStates(Arrays.asList(ratioEmpQuery.getStatesStr().split(",")));
		}
		if (StringUtils.isBlank(ratioEmpQuery.getLivingTypesStr())) {
			if ("FUND".equals(ratioEmpQuery.getInsuranceType())) {

				// 获取字典项(居住类型),dictionary=93 ,
				SysDictitem sysDictitem = new SysDictitem();
				sysDictitem.setDictionary(93);
				sysDictitem.setAreaId(ratioEmpQuery.getAreaId());
				List<SysDictitem> dictitems = sysDictitemService.findAll(sysDictitem);
				List<String> livingTyps = new ArrayList<>(8);
				if (CollectionUtils.isNotEmpty(dictitems)) {
					for (SysDictitem dictitem : dictitems) {
						livingTyps.add(dictitem.getCode());
					}
				} else {
					livingTyps.add("no");
				}
				ratioEmpQuery.setLivingTypes(livingTyps);
			} else {
				if (ratioEmpQuery.getNewRatioId() != null) {
					BsArearatioscope scope = new BsArearatioscope();
					scope.setRatioId(ratioEmpQuery.getNewRatioId());
					List<BsArearatioscope> scopes = bsArearatioscopeService.findAll(scope);
					List<String> livingTyps = new ArrayList<>(8);
					// 比例未配置户口类型 不应该查出人员
					if (CollectionUtils.isEmpty(scopes)) {
						livingTyps.add("no");
					} else {
						for (BsArearatioscope s : scopes) {
							livingTyps.add(s.getLivingType());
						}
					}
					ratioEmpQuery.setLivingTypes(livingTyps);
				}
			}
		} else {
			String[] livingTyps = ratioEmpQuery.getLivingTypesStr().split(",");
			ratioEmpQuery.setLivingTypes(Arrays.asList(livingTyps));
		}
		// 获取数据权限
		ratioEmpQuery.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		Integer total = cmEmployeeDao.findEmpByBatchRatioCount(ratioEmpQuery);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cmEmployeeDao.findEmpByBatchRatioPage(ratioEmpQuery);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 查询满足比例更换条件员工
	 *
	 * @param ratioEmpQuery
	 *            查询条件
	 * @return : java.util.List<java.util.Map
	 *         <java.lang.String,java.lang.Object>>
	 * @createDate : 16:15 2016/11/16
	 * @author : yangfw@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 16:15 2016/11/16
	 * @updateAuthor :
	 */
	@Override
	public List<Map<String, Object>> findBatchRatioEmp(BatchRatioEmpQuery ratioEmpQuery) {
		// 转换查询
		if (StringUtils.isNotBlank(ratioEmpQuery.getStatesStr())) {
			ratioEmpQuery.setStates(Arrays.asList(ratioEmpQuery.getStatesStr().split(",")));
		}
		if (StringUtils.isBlank(ratioEmpQuery.getLivingTypesStr())) {
			if ("FUND".equals(ratioEmpQuery.getInsuranceType())) {
				// 获取字典项(居住类型),dictionary=93 ,
				SysDictitem sysDictitem = new SysDictitem();
				sysDictitem.setDictionary(93);
				sysDictitem.setAreaId(ratioEmpQuery.getAreaId());
				List<SysDictitem> dictitems = sysDictitemService.findAll(sysDictitem);
				List<String> livingTyps = new ArrayList<>(8);
				if (CollectionUtils.isNotEmpty(dictitems)) {
					for (SysDictitem dictitem : dictitems) {
						livingTyps.add(dictitem.getCode());
					}
				} else {
					livingTyps.add("no");
				}
				ratioEmpQuery.setLivingTypes(livingTyps);
			} else {
				if (ratioEmpQuery.getNewRatioId() != null) {
					BsArearatioscope scope = new BsArearatioscope();
					scope.setRatioId(ratioEmpQuery.getNewRatioId());
					List<BsArearatioscope> scopes = bsArearatioscopeService.findAll(scope);
					List<String> livingTyps = new ArrayList<>(8);
					// 比例未配置户口类型 不应该查出人员
					if (CollectionUtils.isEmpty(scopes)) {
						livingTyps.add("no");
					} else {
						for (BsArearatioscope s : scopes) {
							livingTyps.add(s.getLivingType());
						}
					}
					ratioEmpQuery.setLivingTypes(livingTyps);
				}
			}
		} else {
			String[] livingTyps = ratioEmpQuery.getLivingTypesStr().split(",");
			ratioEmpQuery.setLivingTypes(Arrays.asList(livingTyps));
		}
		List<Map<String, Object>> datas = cmEmployeeDao.findEmpByBatchRatio(ratioEmpQuery);
		return datas;
	}
	public List<String> getTime(String startTime,String endTime){
		List<String> timeList = new ArrayList<>();
		startTime = startTime.replace("-","");
		endTime = endTime.replace("-","");
		int startYear = Integer.parseInt(startTime.substring(0,4));
		int startMonth =Integer.parseInt( startTime.substring(4,6));
//        int endYear =Integer.parseInt(endTime.substring(0,4));
//        int endMonth = Integer.parseInt(endTime.substring(4,6));
		int startTimeInt = Integer.parseInt(startTime);
		int endTimeInt = Integer.parseInt(endTime);
		while (startTimeInt<=endTimeInt){
			if(startMonth<10){
				timeList.add(startYear+"-0"+startMonth);
			}else {
				timeList.add(startYear+"-"+startMonth);
			}
			if(startMonth<12){
				startMonth++;
			}else if(startMonth==12){
				startYear++;
				startMonth = 1;
			}
			if(startMonth<10) {
				startTime = startYear + "0" + startMonth;
			}else {
				startTime = startYear + "" + startMonth;
			}
			startTimeInt = Integer.parseInt(startTime);
		}
		return timeList;
	}
	public String getMonth(String str){
		String startTime = str.replace("-","");
		int startYear = Integer.parseInt(startTime.substring(0,4));//2016
		int startMonth =Integer.parseInt( startTime.substring(4,6));//12
		if(startMonth < 12){
			startMonth++;
		}else if(startMonth >=12){
			startYear++;
			startMonth = 1;
		}
		String months = "";
		if(startMonth <10){
			months = startYear +"-0"+startMonth;
		}else{
			months = startYear +"-"+startMonth;
		}
		return months;
	}


	/**
	 * 分页查询批量减员
	 *  @param   info, query]
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2016/12/15 15:32
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/15 15:32
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findBatchReduceEmpPage(PageInfo info,ContextInfo cti, BatchReduceEmplQuery query) {

		PageModel pm = new PageModel(info);
		int pageIndex = info.getOffset();
		int pageSize = info.getPagesize();

		// 数据权限
		String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
				Constant.DATATYPE);
		query.setAuthority(authority);
		Integer total = cmEmployeeDao.findBatchReduceEmplCount(query);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cmEmployeeDao.findBatchReduceEmplList(query, pageIndex, pageSize);
		if(CollectionUtils.isNotEmpty(datas)){
			Calendar now = Calendar.getInstance();
			Integer nowMonth = (now.get(Calendar.YEAR) * 12) + now.get(Calendar.MONTH)+1;
			//补全身份证类型name
			SysDictitem queryDict = new SysDictitem();
			queryDict.setDictionary(89);
			List<SysDictitem> dictitems = sysDictitemService.findAll(queryDict);
			Map<String,String> identityTypeMap = new HashMap<>();
			if(CollectionUtils.isNotEmpty(dictitems)){
				for(SysDictitem dictitem:dictitems){
					identityTypeMap.put(dictitem.getCode(),dictitem.getName());
				}
			}
			for(Map<String,Object> data:datas){
				data.put("identity_type_name",identityTypeMap.get(data.get("identity_type")));
				String beginMonthStr = (String)data.get("begin_period");
				if(StringUtils.isNotBlank(beginMonthStr)){
					Calendar beginCal = Calendar.getInstance();
					try {
						beginCal.setTime(TimeUtils.convertStringToDate(beginMonthStr));
					} catch (Exception e) {
						continue;
					}
					Integer beginMonth = (beginCal.get(Calendar.YEAR) * 12) + beginCal.get(Calendar.MONTH)+1;
					Integer insu_month = nowMonth-beginMonth +1;
					if(insu_month < 0){
						insu_month = 0;
					}
					data.put("insu_month",insu_month);
				}
			}
		}
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 查询员工所属方案最后受理日期
	 *  @param   empIds 员工id
	 * @return    : java.lang.Integer
	 *  @createDate   : 2016/12/16 11:59
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/16 11:59
	 *  @updateAuthor  :
	 */
	@Override
	public Integer findEmplSchemeMaxEndDate(List<Integer> empIds){
		return cmEmployeeDao.findEmplSchemeMaxEndDate(empIds);
	}

	/**
	 * 批量基数调整exportData
	 *  @param   query
	 * @return    : java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate   : 2016/12/26 19:10
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/26 19:10
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,Object>> findEmplsByBatchAdjBaseExportData(ContextInfo cti,BatchAdjBaseQuery query){
		// 获取数据权限
		query.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		return cmEmployeeDao.findEmplsByBatchAdjBaseExportData(query);
	}

	/**
	 *  获取可批量调整最低基数 员工列表
	 *  @param   info, query
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-01-09 15:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-09 15:52
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findEmplsByBatchAdjLowerBaseList(ContextInfo cti,PageInfo info, BatchAdjBaseQuery query){
		PageModel pm = new PageModel(info);
		// 获取数据权限
		query.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		Integer total = cmEmployeeDao.findEmplsByBatchAdjLowerBaseCount(query);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cmEmployeeDao.findEmplsByBatchAdjLowerBaseList(query, info.getOffset(), info.getPagesize());
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 通过企业id 证件号码与姓名 查询员工
	 * 三个字段缺任何一个返回空
	 *  @param   query
	 * @return    : com.xfs.corp.model.CmEmployee
	 *  @createDate   : 2016/12/27 15:11
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/27 15:11
	 *  @updateAuthor  :
	 */
	@Override
	public CmEmployee findCpEmplByNameAndIdentityCode(CmEmployee query){
		if(StringUtils.isBlank(query.getIdentityCode()) || StringUtils.isBlank(query.getIdentityCode()) || query.getCpId()==null){
			return null;
		}
		return cmEmployeeDao.findCpEmplByNameAndIdentityCode(query);
	}


	/**
	 * 根据社保公积金库查找库单个类型比例
	 *
	 * @param accountId
	 * @param livingType
	 * @return
	 */
	public List<InsuranceTypeDto> queryAccountInsuranceSingleByAccountId(Integer accountId, String livingType,
																		 String beginDate, Integer insuranceTypeId) {
		// 获取当前时间，格式是年月
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String currentPeriod = dateFormat.format(date);

		if (beginDate != null && !"".equals(beginDate)) {
			currentPeriod = beginDate;
		}

		if ("".equals(livingType)) {
			livingType = null;
		}

		// 根据库id获取库
		SpsAccount spsAccount = new SpsAccount();
		spsAccount.setAccId(accountId);
		spsAccount = spsAccountService.findByPK(spsAccount);

		List<InsuranceTypeDto> insuranceTypeDtos = new ArrayList<>();

		if (spsAccount != null) {
			SpsAccountRatio spsAccountRadio = new SpsAccountRatio();
			spsAccountRadio.setAccId(spsAccount.getAccId());
			spsAccountRadio.setLivingTypeEq(livingType);
			List<SpsAccountRatio> accRatioList = spsAccountRatioService.findAll(spsAccountRadio);
			if (accRatioList != null && accRatioList.size() > 0) {
				for (SpsAccountRatio spsAccountRatio : accRatioList) {
					BsArearatio bsArearatio = new BsArearatio();
					bsArearatio.setRatioId(spsAccountRatio.getRatioId());
					bsArearatio.setDr(0);
					Map<String, Object> areaRatio = bsArearatioService.findAreaRatioDetail(bsArearatio);

					if (Integer.parseInt(areaRatio.get("insurance_id").toString()) == insuranceTypeId.intValue()) {
						// 获取当前时间所在的地区险种比例版本
						BsArearatiover bsArearatiover = new BsArearatiover();
						bsArearatiover.setRatioId(spsAccountRatio.getRatioId());
						// bsArearatiover.setBeginPeriodTo(currentPeriod);
						// bsArearatiover.setEndPeriodFrom(currentPeriod);
						List<BsArearatiover> aOverList = bsArearatioverService.findAll(bsArearatiover);
						if (aOverList != null && aOverList.size() > 0) {
							for (BsArearatiover ver : aOverList) {
								try {
									if (org.springframework.util.StringUtils.isEmpty(ver.getBeginPeriod()) || dateFormat
											.parse(ver.getBeginPeriod()).compareTo(dateFormat.parse(currentPeriod)) <= 0) {
										if (org.springframework.util.StringUtils.isEmpty(ver.getEndPeriod())
												|| dateFormat.parse(ver.getEndPeriod())
												.compareTo(dateFormat.parse(currentPeriod)) >= 0) {
											InsuranceTypeDto insuranceTypeDto = new InsuranceTypeDto();
											insuranceTypeDto.setRatioId(ver.getRatioId());
											insuranceTypeDto.setInsuranceTypeId(
													Integer.valueOf(areaRatio.get("insurance_id").toString()));
											insuranceTypeDto.setInsuranceName(areaRatio.get("insurance_name").toString());
											if ("Y".equals(spsAccountRatio.getIsdefault())) {
												insuranceTypeDto.setIsCheck(true);
											} else {
												insuranceTypeDto.setIsCheck(false);
											}
											insuranceTypeDto
													.setCompanyRatio(ver.getCorpRatio().multiply(new BigDecimal(100)));
											insuranceTypeDto
													.setPersonalRatio(ver.getPsnRatio().multiply(new BigDecimal(100)));
											insuranceTypeDto.setEmpPaybase(ver.getPsnBaseLower());
											insuranceTypeDto.setCorpPaybase(ver.getCorpBaseLower());
											insuranceTypeDto.setCorpBaseLower(ver.getCorpBaseLower());
											insuranceTypeDto.setPsnBaseLower(ver.getPsnBaseLower());
											insuranceTypeDto.setCorpBaseUpper(ver.getCorpBaseUpper());
											insuranceTypeDto.setPsnBaseUpper(ver.getPsnBaseUpper());

											insuranceTypeDtos.add(insuranceTypeDto);
											break;
										}
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									log.error("期间转换失败!", e);
									throw new RuntimeException("期间转换失败!", e);
								}
							}
						}
					}
				}
			}
		}

		Map<String, InsuranceTypeDto> insuranceMaps = new LinkedHashMap<String, InsuranceTypeDto>();
		for (InsuranceTypeDto insuranceTypeDto : insuranceTypeDtos) {
			if (!insuranceMaps.containsKey(insuranceTypeDto.getInsuranceTypeId().toString())) {
				//
				// insuranceTypeDto.getCompanyRatioSet().add(insuranceTypeDto.getCompanyRatio());
				// insuranceTypeDto.getPersonalRatioSet().add(insuranceTypeDto.getPersonalRatio());

				insuranceTypeDto.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				insuranceTypeDto.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());

				insuranceMaps.put(insuranceTypeDto.getInsuranceTypeId().toString(), insuranceTypeDto);
			} else {
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getCompanyRatioList().add(insuranceTypeDto.getCompanyRatio());
				((InsuranceTypeDto) insuranceMaps.get(insuranceTypeDto.getInsuranceTypeId().toString()))
						.getPersonalRatioList().add(insuranceTypeDto.getPersonalRatio());
			}
		}

		return new ArrayList<InsuranceTypeDto>(insuranceMaps.values());
	}

	public List<CmEmployee> findEmpNameList(ContextInfo cti,CmEmployee cmEmployee){
		return cmEmployeeDao.findEmpNameList(cti,cmEmployee);
	}

	public Map<String, Object> findEmpTotal(Map<String, Object> map){
		return cmEmployeeDao.findEmpTotal(map);
	}

	public List<Map<String, Object>> findEmpTotalArea(Map<String, Object> map){
		return cmEmployeeDao.findEmpTotalArea(map);
	}

	public PageModel findEmpList(PageInfo pi, CmEmployee cmEmployee){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.findEmpListCount(cmEmployee);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.findEmpList(cmEmployee, pageIndex, pageSize));
		return pm;
	}

	public PageModel paymentTrendList(PageInfo pi, CmEmployee cmEmployee){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.paymentTrendListCount(cmEmployee);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.paymentTrendList(cmEmployee, pageIndex, pageSize));
		return pm;
	}

	public PageModel empTrendList(PageInfo pi, CmEmployee cmEmployee){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.empTrendListCount(cmEmployee);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.empTrendList(cmEmployee, pageIndex, pageSize));
		return pm;
	}

	public List<Map<String,Object>> findEmpList(CmEmployee cmEmployee){
		return cmEmployeeDao.findEmpList(cmEmployee);
	}

	public Map<String, Object> findEmpInfo(CmEmployee cmEmployee){
		return cmEmployeeDao.findEmpInfo(cmEmployee);
	}

	/**
	 *  处理工作台解析后的业务
	 *  @param   dataList
	 *	@return 			: void
	 *  @createDate  	: 2017-03-22 17:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-22 17:41
	 *  @updateAuthor  :
	 */
	public Result handleUploadEmp(ContextInfo cti,List<Map<String,AnalysisData>> dataList){
		return handleUploadEmp(cti,dataList, TaskStateFiled.TODO_ONLINE_APPLICATION, TaskExecuteType.NO_COMPLATE_ADDEXECUTE);
	}

	/**
	 *  处理工作台解析后的业务
	 *  @param   dataList isComplate 是否自动完成
	 *	@return 			: void
	 *  @createDate  	: 2017-03-22 17:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-22 17:41
	 *  @updateAuthor  :
	 */
	public Result handleUploadEmp(ContextInfo cti,List<Map<String,AnalysisData>> dataList,TaskStateFiled taskStateFiled,TaskExecuteType taskExecuteType){
		Result result = Result.createResult();
		if(null != dataList && !dataList.isEmpty()){
			Map<String,Object> data = new HashMap<>();
			for(Map<String,AnalysisData> analysisData : dataList){
				data.clear();
				for(Map.Entry<String,AnalysisData> analysis : analysisData.entrySet()){
					data.put(analysis.getKey(),analysis.getValue().getAnalysisValue());
				}
				//序列化实体
				TypeReference<CmEmployee> empRef = new TypeReference<CmEmployee>(){};
				TypeReference<List<SpsTask>> taskRef = new TypeReference<List<SpsTask>>(){};
				String json = JSON.toJSONString(data);
				CmEmployee employee = JSON.parseObject(json,empRef);
				employee.setDr(0);

				//如果是补缴，则“参保月份”为“社保补缴开始时间”
				if (analysisData.get("bstypeId")!=null && (String.valueOf(BsType.INSUR_BACK.getCode()).equals(analysisData.get("bstypeId").getAnalysisValue())
						|| String.valueOf(BsType.FUND_BACK.getCode()).equals(analysisData.get("bstypeId").getAnalysisValue())) ) {
					try {
						String supplementarybegindate = DateFormatUtil.FormatDate(analysisData.get("supplementarybegindate").getAnalysisValue());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
						Date entryTime = sdf.parse(supplementarybegindate);
						employee.setEntryTime(entryTime);
					} catch (ParseException e) {
						employee.setEntryTime(new Date());
					}
				}else {
					employee.setEntryTime(new Date());
				}
				employee.setInsuranceLivingType(String.valueOf(data.get("insuranceLiveType")));
				//保存员工及任务单
				insertEmp(cti, employee, result, false ,data);
				/**
				 * 创建任务
				 */
				List<SpsTask> spsTasks = JSON.parseObject(String.valueOf(data.get("taskJson")),taskRef);
				if(null != spsTasks && !spsTasks.isEmpty()){
					for(SpsTask spsTask : spsTasks){
						spsTask.setStateFiledId(taskStateFiled.getStateFiledId());
						spsTask.setStateFiledName(taskStateFiled.getStateFiledName());
						spsTask.setEmpId(employee.getEmpId());
						spsTask.setSchemeId(employee.getSchemeId());
						spsTask.setType(taskStateFiled.getTaskType());
						spsTask.setCreateBy(cti.getUserId());
						spsTask.setDr(0);
						String taskSerialNum = (null == analysisData.get("taskSerialNum") || StringUtils.isBlank(analysisData.get("taskSerialNum").getAnalysisValue())) ? UUIDUtil.randomUUID() : analysisData.get("taskSerialNum").getAnalysisValue();
						spsTask.setTaskSerialNum(taskSerialNum);
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						long beginTime1 = System.currentTimeMillis();
						corpTaskAspectService.saveTask(cti, spsTask,JSON.parseObject(spsTask.getJson(),ref), result,taskExecuteType);
						long beginTime2 = System.currentTimeMillis();
						System.out.println("处理任务耗时："+ (beginTime2 - beginTime1));
						if (!result.isSuccess()) {
							log.info(json + "========================================" +result.getMsg());
							continue;
						}else if(result.isSuccess() && taskExecuteType.isAutoComplate() && !TaskStateFiled.COMPLETED_APPLICATION.getTaskType().equals(spsTask.getType())){
							spsTask.setType("COMPLETED");
							corpTaskAspectService.saveTask(cti, spsTask,JSON.parseObject(spsTask.getJson(),ref), result);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 下载社保数据
	 *  @param cti
	 *  @createDate  	: 2017年4月13日 下午2:52:18
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月13日 下午2:52:18
	 *  @updateAuthor  	:
	 */
	@Override
	public String downloadInsFundDatas(ContextInfo cti, String period,String searchWords,String areaId) {
		// 获取参保信息---Excel动态标题头headerModels
		List<Map<String, Object>> headerModels = new ArrayList<>();
		// 社保
		List<Map<String, Object>> insurance = bdInsuranceareaDao.fundInsFundByCpId(cti.getOrgId(),"INSURANCE");
		// 公积金
		List<Map<String, Object>> fund = bdInsuranceareaDao.fundInsFundByCpId(cti.getOrgId(),"FUND");
		// 社保
		List<Map<String, String>> otherHeaderModels = new ArrayList<>();
		Map<String, String> beginMap = new HashMap<>();
		beginMap.put("parentName", "姓名");
		beginMap.put("parentCode", "empname");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "证件号码");
		beginMap.put("parentCode", "identity_code");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "所属月份");
		beginMap.put("parentCode", "insured_month");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "社保城市");
		beginMap.put("parentCode", "areaname");
		otherHeaderModels.add(beginMap);
		for (int i = 0; i < insurance.size(); i++) {
			Map<String, Object> insMap = insurance.get(i);
			// 基数,企业比例,企业金额,个人比例,个人金额
			if("apaybase".equals(insMap.get("childCode"))
					|| "corp_ratio".equals(insMap.get("childCode"))
					|| "corp_payment".equals(insMap.get("childCode"))
					|| "emp_ratio".equals(insMap.get("childCode"))
					|| "emp_payment".equals(insMap.get("childCode"))
					|| "total_sum".equals(insMap.get("childCode"))){
				headerModels.add(insMap);
			}
		}
//		Map<String, Object> ins_sbxj = new HashMap<>();
//		ins_sbxj.put("wxxj", "社保小计");
//		headerModels.add(ins_sbxj);
		Map<String, Object> wxxj_crop = new LinkedHashMap<>();
		wxxj_crop.put("parentCode", "wxxj");
		wxxj_crop.put("parentName", "社保小计");
		wxxj_crop.put("childCode", "crop");
		wxxj_crop.put("childName", "企业");
		headerModels.add(wxxj_crop);

		Map<String, Object> wxxj_emp = new LinkedHashMap<>();
		wxxj_emp.put("parentCode", "wxxj");
		wxxj_emp.put("parentName", "社保小计");
		wxxj_emp.put("childCode", "emp");
		wxxj_emp.put("childName", "个人");
		headerModels.add(wxxj_emp);

		Map<String, Object> wxxj_total = new LinkedHashMap<>();
		wxxj_total.put("parentCode", "wxxj");
		wxxj_total.put("parentName", "社保小计");
		wxxj_total.put("childCode", "total_sum");
		wxxj_total.put("childName", "合计");
		headerModels.add(wxxj_total);

		Map<String, Object> fundArea = new HashMap<>();
		fundArea.put("parentName", "公积金缴纳城市");
		fundArea.put("parentCode", "fund_area");
		headerModels.add(fundArea);
		// 公积金
		for (int i = 0; i < fund.size(); i++) {
			Map<String, Object> map = fund.get(i);
			// 基数,企业比例,企业金额,个人比例,个人金额
			if("apaybase".equals(map.get("childCode"))
					|| "corp_ratio".equals(map.get("childCode"))
					|| "corp_payment".equals(map.get("childCode"))
					|| "emp_ratio".equals(map.get("childCode"))
					|| "emp_payment".equals(map.get("childCode"))
					|| "total_sum".equals(map.get("childCode"))){
				headerModels.add(map);
			}
		}
//		Map<String, Object> fund_sbxj = new HashMap<>();
//		fund_sbxj.put("wxxj", "社保小计");
//		headerModels.add(fund_sbxj);
		if(fund.size() > 0){
			Map<String, Object> wxxjyj_crop = new LinkedHashMap<>();
			wxxjyj_crop.put("parentCode", "wxxjyj");
			wxxjyj_crop.put("parentName", "社保公积金小计");
			wxxjyj_crop.put("childCode", "crop");
			wxxjyj_crop.put("childName", "企业");
			headerModels.add(wxxjyj_crop);

			Map<String, Object> wxxjyj_emp = new LinkedHashMap<>();
			wxxjyj_emp.put("parentCode", "wxxjyj");
			wxxjyj_emp.put("parentName", "社保公积金小计");
			wxxjyj_emp.put("childCode", "emp");
			wxxjyj_emp.put("childName", "个人");
			headerModels.add(wxxjyj_emp);

			Map<String, Object> wxxjyj_total = new LinkedHashMap<>();
			wxxjyj_total.put("parentCode", "wxxjyj");
			wxxjyj_total.put("parentName", "社保公积金小计");
			wxxjyj_total.put("childCode", "total_sum");
			wxxjyj_total.put("childName", "合计");
			headerModels.add(wxxjyj_total);
		}
		Map<String, Object> endMap = new HashMap<>();
		endMap.put("parentCode", "fee_crop_sum");
		endMap.put("parentName", "企业合计");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "fee_emp_sum");
		endMap.put("parentName", "个人合计");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "fee_total");
		endMap.put("parentName", "总计");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "adjust_reason");
		endMap.put("parentName", "备注");
		headerModels.add(endMap);

		List<Map<String,Object>> dataMap = spsBillEmpService.queryAllEmpListInsDetail(cti,period,searchWords,areaId);
		ExcelModel excelModel = ExcelUtil.convert2ExcelModel("五险一金明细表", "五险一金缴纳详情", otherHeaderModels, headerModels, dataMap);
		// 将动态头拼接到Excel上
		List<ExcelModel> excelModels = new ArrayList<>();
		excelModels.add(excelModel);
		ExcelCore core = new ExcelCore();
		core.process(excelModels);

		String fileName = "账单详情.xlsx";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String filePath = format.format(Calendar.getInstance().getTime());
		File excelFile = geteEmptyFile(fileName, filePath);
		core.export(excelFile);
		return excelFile.getPath();
	}


	/**
	 * 获取空文件
	 *
	 * @param fileName
	 * @param curDate
	 * @return
	 */
	private File geteEmptyFile(String fileName, String curDate) {
		String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
		String filePath = fileRootPath + File.separator + curDate;

		// 获取一个时间格式的名称（微信中为简易解决中文名称问题）
		String saveName = generateFileName(fileName);
		File localFile = new File(filePath, saveName);
		if (!localFile.exists()) {
			try {
				// 如果路径不存在,则创建
				if (!localFile.getParentFile().exists()) {
					localFile.getParentFile().mkdirs();
				}
				localFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			localFile = getUniqueFile(filePath, saveName);
		}
		return localFile;
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
	private File getUniqueFile(String path, String fileName) {
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
	 * 友人才用户同步
	 *  @param empId
	 *  @param empJobId
	 *  @param tenantId
	 *  @param businessType
	 *  @return
	 *  @createDate  	: 2017年5月16日 下午5:18:05
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月16日 下午5:18:05
	 *  @updateAuthor  	:
	 */
	@Override
	public Result joinEmpYrc(String empId, String empJobId, String tenantId, Integer businessType) {
		Result result = Result.createResult().setSuccess(true);
		result.setMsg("请求成功");
		try {
			log.info("友人才请求参数======================empId=" + empId + ",empJobId=" + empJobId + ",tenantId=" + tenantId + ",businessType=" + businessType+",time="+new Date());
			result = checkEmp(empId, empJobId, tenantId, businessType,result);
			if(result.isSuccess()){
				Map<String, String> param = new HashMap<>();
				param.put("staff_id", empId);// 员工ID
				param.put("staff_job_id", empJobId);// 人员任职ID
				param.put("tenantid", tenantId);// 租户ID
				param.put("qry_type", businessType.toString());// 查询类型
				param.put("token", Config.getProperty("yonyouhr_token"));// token
				// 调用接口获取员工信息
				String msg = HttpClientUtil.doPost(Config.getProperty("yonyouhr_apiurl")+"/hrsrv/internal/api/staff/querystaffinfoall", param);
				com.alibaba.fastjson.JSONObject msgData = JSON.parseObject(msg);
				// 获取信息是否成功 200：成功
				if(msgData.getString("statusCode").equals("200")){
					// 根据租户ID获取企业信息
					CmCorp cmCorp = cmCorpDao.findCorpByTenantId(tenantId);
					if(null == cmCorp){
						// 创建企业信息
						String ssoMsg = HttpClientUtil.doPost(Config.getProperty("xfs_sso")+"/notify/openTenant?tenantId="+tenantId, null);
						com.alibaba.fastjson.JSONObject ssoDataJson = JSON.parseObject(ssoMsg);
						if(true == ssoDataJson.getBooleanValue("success")){
							cmCorp = cmCorpDao.findCorpByTenantId(tenantId);
						}else{
							log.info("对不起，租户信息有误,time="+new Date());
							return result;
						}
					}
					// 获取所有数据
					com.alibaba.fastjson.JSONObject data = msgData.getJSONObject("data");
					// 获取员工信息
					com.alibaba.fastjson.JSONObject empData = data.getJSONObject("staff");
					CmEmployee cmEmployee = new CmEmployee();
					cmEmployee.setThreeId(empId);
					cmEmployee.setCpId(cmCorp.getCpId());
					cmEmployee.setName(empData.getString("name"));
					cmEmployee.setIdentityCode(empData.getString("cert_no"));
					if(com.xfs.common.util.StringUtils.isNotBlank(empData.getString("photo"))){
						cmEmployee.setHeadPhoto(empData.getString("photo"));
					}
					// 获取证件类型
					SysDictitem sysDictitem = new SysDictitem();
					sysDictitem.setNameEq(empData.getString("cert_type_showname"));
					sysDictitem.setDictionary(89);
					List<SysDictitem> sysDictitems = sysDictitemService.findAll(sysDictitem);
					if(sysDictitems.size() > 0){
						cmEmployee.setIdentityType(sysDictitems.get(0).getCode());
					}else{
						sysDictitem = new SysDictitem();
						sysDictitem.setSynonym(empData.getString("cert_type"));
						sysDictitem.setDictionary(89);
						sysDictitems = sysDictitemService.findAll(sysDictitem);
						if(sysDictitems.size() > 0){
							cmEmployee.setIdentityType(sysDictitems.get(0).getCode());
						}
					}
					cmEmployee.setMobile(empData.getString("mobile"));
					cmEmployee.setDr(0);
					cmEmployee.setInsuranceState("OFF");
					// 根据企业ID和城市获取最小方案
					SpsScheme scheme = new SpsScheme();
					scheme.setCpId(cmCorp.getCpId());
					com.alibaba.fastjson.JSONObject staffJob = data.getJSONObject("staff_job");
					if(com.xfs.common.util.StringUtils.isNotBlank(staffJob.getString("bmlocation_showname"))){
						scheme.setAreaname(staffJob.getString("bmlocation_showname"));
						scheme = spsSchemeService.findMinSchemeByCpIdAndCityNmae(scheme);
						if(null != scheme){
							cmEmployee.setSchemeId(scheme.getSchemeId());
							// 获取户口性质
							sysDictitem = new SysDictitem();
							sysDictitem.setAreaId(scheme.getAreaId());
							sysDictitems = sysDictitemService.findAll(sysDictitem);
							if(sysDictitems.size() == 1){
								cmEmployee.setInsuranceLivingType(sysDictitems.get(0).getCode());
							}
						}
					}
					// 企业
					if(com.xfs.common.util.StringUtils.isNotBlank(staffJob.getString("corp_id_showname"))){
						cmEmployee.setBranch(staffJob.getString("corp_id_showname"));
						ContextInfo cti = new ContextInfo();
						cti.setOrgId(cmEmployee.getCpId());
						saveSupplier(cti,cmEmployee.getBranch());
					}
					// 部门
					if(com.xfs.common.util.StringUtils.isNotBlank(staffJob.getString("org_id_showname"))){
						cmEmployee.setDepName(staffJob.getString("org_id_showname"));
					}

					cmEmployee.setEmail(empData.getString("email"));
					cmEmployee.setFundState("OFF");
					if(com.xfs.common.util.StringUtils.isNotBlank(data.getString("entry_date"))){
						cmEmployee.setEntryTime(new Date(new Long(data.getString("entry_date"))));// 入职时间
					}
					if(com.xfs.common.util.StringUtils.isNotBlank(data.getString("dimi_date"))){
						cmEmployee.setLeaveTime(new Date(new Long(data.getString("dimi_date"))));// 离职时间
					}
					if(com.xfs.common.util.StringUtils.isNotBlank(data.getString("reg_wage"))){
						cmEmployee.setFundSalary(new BigDecimal(data.getString("reg_wage")));
						cmEmployee.setInsuranceSalary(new BigDecimal(data.getString("reg_wage")));
						cmEmployee.setSalary(new BigDecimal(data.getString("reg_wage")));
					}
					// 设置员工是否移除
					if(businessType == 4){
						cmEmployee.setIsRemove(1);
					}
					CmEmployee query = new CmEmployee();
					query.setName(cmEmployee.getName());
					query.setIdentityCode(cmEmployee.getIdentityCode());
					query.setCpId(cmCorp.getCpId());
					query.setDr(0);
					query = cmEmployeeDao.findCpEmplByNameAndIdentityCode(query);
					if(null == query){
						// 保存人员信息
						cmEmployeeDao.save(null, cmEmployee);
						if(null != scheme.getSchemeId()){
							SpsSchemeEmp vo = new SpsSchemeEmp();
							vo.setEmpId(cmEmployee.getEmpId());
							vo.setSchemeId(scheme.getSchemeId());
							vo.setState("USE");
							vo.setDr(0);
							// 保存员工与方案的关系
							spsSchemeEmpService.save(null, vo);
						}
					}else{
						// 只更新第三方ID
						CmEmployee employee = new CmEmployee();
						// 企业
						if(com.xfs.common.util.StringUtils.isNotBlank(staffJob.getString("corp_id_showname"))){
							employee.setBranch(staffJob.getString("corp_id_showname"));
						}
						// 部门
						if(com.xfs.common.util.StringUtils.isNotBlank(staffJob.getString("org_id_showname"))){
							employee.setDepName(staffJob.getString("org_id_showname"));
						}
						employee.setEmail(empData.getString("email"));
						if(com.xfs.common.util.StringUtils.isNotBlank(empData.getString("photo"))){
							employee.setHeadPhoto(empData.getString("photo"));
						}
						if(com.xfs.common.util.StringUtils.isNotBlank(data.getString("reg_wage"))){
							employee.setSalary(new BigDecimal(data.getString("reg_wage")));
						}
						employee.setMobile(empData.getString("mobile"));
						employee.setThreeId(empId);
						employee.setEmpId(query.getEmpId());
						cmEmployeeDao.update(null, employee);
						cmEmployee.setEmpId(query.getEmpId());
					}
					SysUser sysUser = new SysUser();
					sysUser.setTenantId(tenantId);
					List<SysUser> sysUsers = sysUserService.findAll(sysUser);
					// 1:入职;2:转正;3:调动;4:离职（减员）;5:人员信息变化
					if(sysUsers.size() > 0){
						SysMessage sysMessage = null;
						sysMessage = new SysMessage();
						sysMessage.setTodoUserType(sysUsers.get(0).getUserType());
						sysMessage.setTodoUser(sysUsers.get(0).getUserId());
						sysMessage.setTodoOrg(sysUsers.get(0).getOrgId());
						sysMessage.setTitle("「"+cmEmployee.getName()+"」的"+(businessType.equals(1)?"入职的参保任务":"离职的减员任务"));
						sysMessage.setContent(cmEmployee.getName()+"的"+(businessType.equals(1)?"入职的参保任务":"离职的减员任务"));
						sysMessage.setType("YRC");
						sysMessage.setState("TODO");
						sysMessage.setSendUser(cmEmployee.getEmpId());
						sysMessage.setSendOrg(cmEmployee.getCpId());
						sysMessage.setSendTime(new Date());
						sysMessage.setDataId(cmEmployee.getEmpId());
						sysMessageService.save(null, sysMessage);
						// 入职的情况下--发送信息收集信息
						if(businessType.equals(1)){
							sysMessage = new SysMessage();
							sysMessage.setTodoUser(sysUsers.get(0).getUserId());
							sysMessage.setTodoOrg(sysUsers.get(0).getOrgId());
							sysMessage.setEmpId( cmEmployee.getEmpId());
							sysMessage.setTodoUserType("EMPLOYEE");
							sysMessage.setTitle("来自［" + cmEmployee.getName() + "］信息收集");
							sysMessage.setContent("欢迎加入我们！为了更好的服务每一位成员，公司会为每一个成员上社保，请您填写一下参保信息，HR姐姐会为您尽快办理！");
							sysMessage.setType("HELPER");
							sysMessage.setState("TODO");
							sysMessage.setSendUser(cmEmployee.getEmpId());
							sysMessage.setSendOrg(cmEmployee.getCpId());
							sysMessage.setSendUserType("CUSTOMER");
							sysMessage.setSendTime(new Date());
							sysMessage.setDataId(-99);
							sysMessageService.save(null, sysMessage);
						}
					}
				}else{
					log.info("请求友人才接口出错====地址："+Config.getProperty("yonyouhr_apiurl")+"/hrsrv/internal/api/staff/querystaffinfoall,参数："+param.toString()+",time="+new Date());
					return result;
				}
			}
		} catch (Exception e) {
			log.error(e);
			log.info("请求参数有误"+",time="+new Date());
			return result;
		}
		return result;
	}

	/**
	 * 验证员工合法性
	 *  @param empId
	 *  @param empJobId
	 *  @param tenantId
	 *  @param businessType
	 *  @param result
	 *  @return
	 *	@return 			: Result
	 *  @createDate  	: 2017年5月16日 下午6:04:23
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月16日 下午6:04:23
	 *  @updateAuthor  :
	 */
	private Result checkEmp(String empId, String empJobId, String tenantId, Integer businessType,Result result){
		// 1:入职;2:转正;3:调动;4:离职（减员）;5:人员信息变化
//		if(!businessType.equals(1) && !businessType.equals(2) && !businessType.equals(3)
//				&& !businessType.equals(4) && !businessType.equals(5)){
//			result.setSuccess(false);
//			result.setMsg("对不起，不支持当前业务");
//		}
		return result;
	}

	public Result insertEmp(ContextInfo cti, CmEmployee cmEmployee, Result result, boolean isInsert,Map<String,Object> data) throws BusinessException{

		CmEmployee vo = new CmEmployee();
		vo.setName(cmEmployee.getName());
		vo.setIdentityCode(cmEmployee.getIdentityCode());
		vo.setCpId(cmEmployee.getCpId());

		CmEmployee vo2 = new CmEmployee();
		vo2.setCpId(cmEmployee.getCpId());
		vo2.setMobile(cmEmployee.getMobile());
		List<CmEmployee> list = cmEmployeeDao.findEmpByIdCard(vo);
		List<CmEmployee> list2 = null;
		if(StringUtils.isNotBlank(cmEmployee.getMobile())){
			list2 = cmEmployeeDao.findEmpByIdCard(vo2);
		}
		if((list!=null && list.size()>0) || (list2!=null && list2.size()>0)){
			result.setError((list!=null && list.size()>0)?"姓名和身份证重复":"手机号重复");
			if(isInsert){
				result.setSuccess(false);
			}else {
				cmEmployee.setEmpId(list.get(0).getEmpId());
				//在人员重复导入的时候不修改状态，否则如若之前存在该人的任务单的话，人员状态就对应不上了
				cmEmployee.setFundState(null);
				cmEmployee.setInsuranceState(null);
				//modify by lifq 20170824 加入excel导入的标识，不更新 户口类型和工资基数
				if(null!=data && null!=data.get("isFromUploadExcel") && "Y".equals(data.get("isFromUploadExcel").toString())){
					cmEmployee.setInsuranceLivingType(null);
                	cmEmployee.setSalary(null);
				}
				if (StringUtils.isBlank(cmEmployee.getJson())) {
					cmEmployeeDao.update(cti, cmEmployee);
				} else {
					//保存任务单数据到emp上
					saveEmpJson(cti, cmEmployee);
				}
			}
			return result;
		}

		com.alibaba.fastjson.JSONObject oldJson = new com.alibaba.fastjson.JSONObject();
		oldJson.put("name",cmEmployee.getName());
		oldJson.put("identity_code",cmEmployee.getIdentityCode());
		oldJson.put("identity_type",cmEmployee.getIdentityType());
		oldJson.put("mobile",cmEmployee.getMobile());
		oldJson.put("insuranceLiveType",cmEmployee.getInsuranceLivingType());
		//modify by lifq 20170830 加入excel导入的标识，新增员工时候 不更新工资基数
		if(null!=data && null!=data.get("isFromUploadExcel") && "Y".equals(data.get("isFromUploadExcel").toString())){
        	cmEmployee.setSalary(null);
		}
		cmEmployee.setJson(oldJson.toJSONString());
		int flag = cmEmployeeDao.insert(cti, cmEmployee);
		if(flag > 0 && null != cmEmployee.getSchemeId() && cmEmployee.getSchemeId() > 0){
			SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
			schemeEmp.setEmpId(cmEmployee.getEmpId());
			schemeEmp.setSchemeId(cmEmployee.getSchemeId());
			schemeEmp.setState("USE");
			schemeEmp.setDr(0);
			schemeEmp.setModifyDt(new Date());//modify by lifq 20170808，服务商管理添加日期 取该字段，modify_dt不能为空
			int seFlag = spsSchemeEmpService.insert(cti, schemeEmp);
			result.setSuccess(seFlag>0);
		}

		if(!result.isSuccess()){
			result.setError("添加失败，缺少城市方案");
			throw new BusinessException("业务异常。。。添加失败，缺少城市方案");
		}

		try {
			saveSupplier(cti, cmEmployee.getBranch());
		} catch (Exception e) {
			log.error("添加［所属公司］记录异常！"+e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 添加［所属公司］历史记录
	 * @param cti
	 * @param branch
	 */
	private void saveSupplier(ContextInfo cti, String branch){
		//校验通过后 判断是否  添加所属公司
		if(StringUtils.isNotBlank(branch)){
			SpsSupplier supplierVo = new SpsSupplier();
			supplierVo.setSupplierName(branch);
			supplierVo.setSpId(cti.getOrgId());
			supplierVo.setType("BRANCH");
			supplierVo.setDr(0);
			List<SpsSupplier> supplierList = spsSupplierDao.freeFind(supplierVo);
			if(null==supplierList || supplierList.size()<1){
				//没有就新增
				spsSupplierDao.insert(cti, supplierVo);
			}
		}
	}

	public Integer deleteEmps(ContextInfo cti,Integer cpId, String empIds){
		return cmEmployeeDao.deleteEmps(cti, cpId, empIds);
	}

	/**
	 * 获得指定日期的后n月
	 * @param specifiedDay
	 * @param n
	 * @return
	 */
	private static String getSpecifiedMonth(String specifiedDay, int n){
		String dayAfter;
		try {
			Calendar c = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-MM").parse(specifiedDay);
			c.setTime(date);
			int day=c.get(Calendar.MONTH);
			c.set(Calendar.MONTH, day + n);

			dayAfter = new SimpleDateFormat("yyyy-MM").format(c.getTime());
		} catch (ParseException e) {
			return specifiedDay;
		}
		return dayAfter;
	}

	/**
	 * 修改员工参保信息-缴费详情
	 * @param cti
	 * @param cmEmployee
	 * @param spsScheme
	 * @param insType
	 * @param result
	 */
	public void saveEmpInfo(ContextInfo cti, CmEmployee cmEmployee, SpsScheme spsScheme,String insType, Result result){
		/**
		 * 保存当前员工信息
		 */
		int flag = saveEmpJson(cti, cmEmployee);
		if(flag > 0){
			/**
			 * 关联方案
			 */
			SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
			spsSchemeEmp.setEmpId(cmEmployee.getEmpId());
			spsSchemeEmp.setDr(0);
			spsSchemeEmp.setState("USE");
			spsSchemeEmp.setSchemeId(cmEmployee.getSchemeId());
			List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(spsSchemeEmp);
			if(list == null || list.isEmpty()){
				SpsSchemeEmp vo = new SpsSchemeEmp();
				vo.setEmpId(cmEmployee.getEmpId());
				vo.setDr(1);
				spsSchemeEmpService.removeByEmp(cti,vo);
				spsSchemeEmp.setModifyDt(new Date());//modify by lifq 20170808，服务商管理添加日期 取该字段，modify_dt不能为空
				spsSchemeEmpService.insert(cti, spsSchemeEmp);
			}
			result.setSuccess(true);
			/**
			 * 员工参保缴费
			 */
			cmEmployeeInsuranceService.adjustEmployeeInsurance(cti, spsScheme.getSpId(), cmEmployee, insType, null, null);
		}
	}

	/**
	 * 保存员工信息并调基
	 * @param cti
	 * @param cmEmployee
	 * @return
	 */
	public void saveEmpInfo(ContextInfo cti, CmEmployee cmEmployee, Result result,String defBeginDate){

		CmEmployee IdCardVo = new CmEmployee();
		IdCardVo.setIdentityCode(cmEmployee.getIdentityCode());
		IdCardVo.setCpId(cti.getOrgId());

		List<CmEmployee> IdCardList = cmEmployeeDao.findEmpByIdCard(IdCardVo);
		if(IdCardList!=null && IdCardList.size()>0) {//校验身份证重复
			for (int i = 0; i < IdCardList.size(); i++) {
				CmEmployee emp = IdCardList.get(i);
				if(emp.getEmpId().intValue()!=cmEmployee.getEmpId().intValue() && cmEmployee.getIdentityCode().equals(emp.getIdentityCode())){
					result.setError("身份证重复");
					result.setSuccess(false);
					return;
				}
			}
		}

		SpsScheme spsScheme = new SpsScheme();
		spsScheme.setSchemeId(cmEmployee.getSchemeId());
		spsScheme = spsSchemeService.findByPK(spsScheme);


		// 取出方案后，拿到截止日卡beginDate 如，  该方案截止日为 20号， 今天8月21号（大于截止日了），则 beginDate：2017-09
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		Integer endDate = spsScheme.getEndDate();

		String beginPeriod;

		if(StringUtils.isNotBlank(defBeginDate)){
			beginPeriod = defBeginDate;
		}else {
			beginPeriod = DateUtil.getMonthDate();
			if (endDate != null && day > endDate.intValue()) {
				beginPeriod = getSpecifiedMonth(beginPeriod, 1);
			}
		}

		AreaSocialRuleVo areaSocialRuleVo = spsAccountRatioService.findSocialRule(cti,spsScheme.getAreaId(),spsScheme.getSchemeId());
		InsuranceLivingTypeVo issltVo = null;
		if(areaSocialRuleVo!=null && areaSocialRuleVo.getInsuranceLivingType()!=null && areaSocialRuleVo.getInsuranceLivingType().size()>0) {
			issltVo = areaSocialRuleVo.getInsuranceLivingType().get(0);

			for (int i = 0; i < areaSocialRuleVo.getInsuranceLivingType().size(); i++) {
				if (areaSocialRuleVo.getInsuranceLivingType().get(i).getPeopleTypeCode().equals(cmEmployee.getInsuranceLivingType())) {
					issltVo = areaSocialRuleVo.getInsuranceLivingType().get(i);
				}
			}

		}

		com.alibaba.fastjson.JSONObject oldJson = new com.alibaba.fastjson.JSONObject();
		if(StringUtils.isNotBlank(cmEmployee.getJson())) {
			oldJson = JSON.parseObject(cmEmployee.getJson());
		}
		oldJson.put("insuranceSalary", cmEmployee.getInsuranceSalary());
		oldJson.put("fundSalary", cmEmployee.getFundSalary());
		oldJson.put("salary", cmEmployee.getSalary());
		cmEmployee.setJson(oldJson.toJSONString());

		int flag = saveEmpJson(cti, cmEmployee);//update(cti, cmEmployee);

		if(flag==0) {
			result.setError("员工信息修改失败");
			return;
		}else{
			SpsSchemeEmp spsSchemeEmp = new SpsSchemeEmp();
			spsSchemeEmp.setEmpId(cmEmployee.getEmpId());
			spsSchemeEmp.setDr(0);
			spsSchemeEmp.setState("USE");
			spsSchemeEmp.setSchemeId(cmEmployee.getSchemeId());
			List<SpsSchemeEmp> list = spsSchemeEmpService.findAll(spsSchemeEmp);
			if(list == null || list.isEmpty()){
				SpsSchemeEmp vo = new SpsSchemeEmp();
				vo.setEmpId(cmEmployee.getEmpId());
				vo.setDr(1);
				spsSchemeEmpService.removeByEmp(cti,vo);
				spsSchemeEmp.setModifyDt(new Date());//modify by lifq 20170808，服务商管理添加日期 取该字段，modify_dt不能为空
				spsSchemeEmpService.insert(cti, spsSchemeEmp);
			}
			result.setSuccess(true);
		}
		List<SpsTask> spsTasks = new ArrayList<>();

		//如果以前是在缴，现在改成未缴了，需要结束算费（支持单独，社保、公积金）
		if("ON".equals(cmEmployee.getFundState())) {
			SpsTask t2 = new SpsTask();
			t2.setEmpId(cmEmployee.getEmpId());
			t2.setBstypeId(BsType.FUND_PAYBASE.getCode());
			t2.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			com.alibaba.fastjson.JSONObject to2 = new com.alibaba.fastjson.JSONObject();
			to2.put("bstypeId", BsType.FUND_PAYBASE.getCode());
			to2.put("insuranceSalary", cmEmployee.getInsuranceSalary());
			to2.put("fundSalary", cmEmployee.getFundSalary());
			to2.put("salary", cmEmployee.getFundSalary());
			t2.setJson(to2.toJSONString());
			spsTasks.add(t2);
		}

		if("ON".equals(cmEmployee.getInsuranceState())){
			SpsTask t1 = new SpsTask();
			t1.setEmpId(cmEmployee.getEmpId());
			t1.setBstypeId(BsType.INSUR_PAYBASE.getCode());
			t1.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			com.alibaba.fastjson.JSONObject to1 = new com.alibaba.fastjson.JSONObject();
			to1.put("bstypeId", BsType.INSUR_PAYBASE.getCode());
			to1.put("insuranceSalary",cmEmployee.getInsuranceSalary());
			to1.put("fundSalary",cmEmployee.getFundSalary());
			to1.put("salary",cmEmployee.getInsuranceSalary());
			t1.setJson(to1.toJSONString());
			spsTasks.add(t1);
		}

		if (null != spsTasks && !spsTasks.isEmpty()) {

			Map<String, Object> businessParam = new HashMap<>();
			businessParam.put("areaId", cmEmployee.getAreaId());
			businessParam.put("empId", cmEmployee.getEmpId());

			CmEmployee employee = new CmEmployee();
			employee.setEmpId(Integer.parseInt(businessParam.get("empId").toString()));
			employee = findByPK(employee);

			List<InsuranceTypeDto> insuranceTypeDtoList = null;

			String beginDate = DateUtil.getMonthDate();
			if(StringUtils.isNotBlank(defBeginDate)){
				beginDate = defBeginDate;
			}
			for (SpsTask spsTask : spsTasks) {

				com.alibaba.fastjson.JSONObject json = JSON.parseObject(spsTask.getJson());
				json.put("beginDate", beginDate);
				json.put("name",employee.getName());
				json.put("identity_code",employee.getIdentityCode());

				spsTask.setType("COMPLETED");
				spsTask.setCpId(employee.getCpId());
				spsTask.setSpId(spsScheme.getSpId());
				spsTask.setCreateBy(cti.getUserId());
				spsTask.setCompanyType(IStaticVarConstant.CMCORPTYPE_SELFSERVICE);
				spsTask.setDr(0);
				spsTask.setName(employee.getName());
				spsTask.setMobile(employee.getMobile());
				spsTask.setIdentityCode(employee.getIdentityCode());
				spsTask.setBeginDate(beginDate);
				spsTask.setExecuteDate(beginDate);
				spsTask.setSchemeId(spsScheme.getSchemeId());

				if("INSURANCE".equals(BsType.getInsType(spsTask.getBstypeId()))){
					insuranceTypeDtoList = queryAccountInsuranceListByAccountId(spsScheme.getInsuranceAccountId(),
							employee.getInsuranceLivingType(), beginDate,"Y");
					json.put("beginPeriod", StringUtils.isBlank(employee.getInsurancePeriod())?beginDate:employee.getInsurancePeriod());

				}else{
					insuranceTypeDtoList = queryAccountInsuranceListByAccountId(spsScheme.getFundAccountId(),
							null, beginDate,"Y");
					json.put("beginPeriod", StringUtils.isBlank(employee.getFundPeriod())?beginDate:employee.getFundPeriod());

				}

				spsTask.setJson(json.toJSONString());

				com.alibaba.fastjson.JSONObject insObj = JSON.parseObject(spsTask.getJson());
				com.alibaba.fastjson.JSONArray insArr = new com.alibaba.fastjson.JSONArray();

				for (InsuranceTypeDto ins: insuranceTypeDtoList ) {
					ins.setBeginDate(beginDate);
					com.alibaba.fastjson.JSONObject obj = JSON.parseObject(JSON.toJSONString(ins));
					insArr.add(obj);
				}

				if(areaSocialRuleVo!=null && areaSocialRuleVo.getFundType()!=null && areaSocialRuleVo.getFundType().size()>0){

					int[] fundItems = new int[areaSocialRuleVo.getFundType().size()];
					for (int i = 0; i < fundItems.length; i++) {
						fundItems[i] = areaSocialRuleVo.getFundType().get(i).getInsuranceId();
					}
					insObj.put("fundItems", fundItems);

				}
				if(issltVo!=null){

					int[] insuranceItems = new int[issltVo.getInsuranceLivingType().size()];
					for (int i = 0; i < insuranceItems.length; i++) {
						insuranceItems[i] = issltVo.getInsuranceLivingType().get(i).getInsuranceId();
					}
					insObj.put("insuranceItems", insuranceItems);
				}

				insObj.put("insurance", insArr);
				spsTask.setJson(insObj.toJSONString());

			}

			for (int i = 0; i < spsTasks.size(); i++) {
				spsTasks.get(i).setType("COMPLETED");
			}

			for (int i = 0; i < spsTasks.size(); i++) {

				TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
				Map<String,String> curMap = JSON.parseObject(spsTasks.get(i).getJson(),ref);
				BigDecimal base = null;
				if("INSURANCE".equals(BsType.getInsType(spsTasks.get(i).getBstypeId()))){
					base = new BigDecimal(curMap.get("insuranceSalary").trim());
				}else{
					base = new BigDecimal(curMap.get("fundSalary").trim());
				}

				CmEmployee cur_employee = new CmEmployee();
				cur_employee.setEmpId(spsTasks.get(i).getEmpId());
				cur_employee.setCmEmployeeInsurances(newInsuranceParseImpl.convertJsonToEmpInsu(spsTasks.get(i),curMap.get("insurance"),base));
				for(CmEmployeeInsurance cmEmployeeInsurance : cur_employee.getCmEmployeeInsurances())
					cmEmployeeInsurance.setExtend(true);

				cmEmployeeInsuranceService.adjustEmployeeInsurance(cti, spsTasks.get(i).getSpId(), cur_employee, BsType.getInsType(spsTasks.get(i).getBstypeId()), beginPeriod, null);
			}

			if(!result.isSuccess()){// 事物回滚
				throw new BusinessException("调基任务单数据业务异常。。。");
			}

		} else {
			result.setSuccess(true);
		}

		List<Integer> list = new ArrayList<Integer>();
		list.add(cmEmployee.getEmpId());
		if (!"ON".equals(cmEmployee.getFundState())) {
			cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(cti,list, "FUND", beginPeriod, null);
		}
		if (!"ON".equals(cmEmployee.getInsuranceState())) {
			cmEmployeeInsuranceService.endEmployeeInsuranceByEmpIdsAndPeriod(cti,list, "INSURANCE", beginPeriod, null);
		}

		try {
			saveSupplier(cti, cmEmployee.getBranch());
		} catch (Exception e) {
			log.error("添加［所属公司］记录异常！"+e.getMessage(), e);
		}
	}

	private void updateEmpInsurance(ContextInfo cti, Integer empId, String insuranceFundType){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String endPeriod = format.format(new Date());

		CmEmployeeInsurance obj = new CmEmployeeInsurance();
		obj.setEmpId(empId);
		obj.setModifyBy(cti.getUserId());
		obj.setModifyDt(new Date());
		obj.setEndPeriod(endPeriod);
		obj.setInsuranceFundType(insuranceFundType);
		cmEmployeeInsuranceDao.updateEndPeriod(cti, obj);

	}

	/**
	 * 查询 调基人员列表
	 *
	 * @author lifq
	 *
	 * 2017年6月7日  上午10:36:29
	 */
	public PageModel adjustList(PageInfo pi, CmEmployee cmEmployee){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.adjustCount(cmEmployee);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.adjustList(cmEmployee, pageIndex, pageSize));

		return pm;
	}
	/**
	 * 导出 调基人员列表
	 *
	 * @author lifq
	 *
	 * 2017年6月7日  上午10:36:29
	 */
	public List<Map<String,Object>> exportAdjust(ContextInfo cti,String empids){
		return cmEmployeeDao.exportAdjust(cti,empids);
	}

	/**
	 * 保存任务数据
	 * @param cti
	 * @param cmEmployee
	 *
	 * @author quanjiahua@xinfushe.com
	 *
	 * @return
	 */
	public int saveEmpJson(ContextInfo cti, CmEmployee cmEmployee){
		if(StringUtils.isBlank(cmEmployee.getJson())){
			return 0;
		}

		CmEmployee oldEmp = null;
		if(cmEmployee.getEmpId()==null || cmEmployee.getEmpId().intValue() <= 0){
			CmEmployee vo = new CmEmployee();
			vo.setName(cmEmployee.getName());
			vo.setIdentityCode(cmEmployee.getIdentityCode());
			vo.setCpId(cmEmployee.getCpId());
			List<CmEmployee> list = cmEmployeeDao.findEmpByIdCard(vo);
			if(null != list && !list.isEmpty()){
				oldEmp = list.get(0);
				cmEmployee.setEmpId(oldEmp.getEmpId());
			}
		}else{
			oldEmp = findEmpAndJsonByPK(cmEmployee.getEmpId());
		}

		if(null != oldEmp){
			com.alibaba.fastjson.JSONObject oldJson;
			if(StringUtils.isNotBlank(oldEmp.getJson())) {
				oldJson = JSON.parseObject(oldEmp.getJson());
			}else{
				oldJson = new com.alibaba.fastjson.JSONObject();
			}

			oldJson.put("name",oldEmp.getName());
			if (StringUtils.isBlank(cmEmployee.getIdentityCode())) {
				oldJson.put("identity_code", oldEmp.getIdentityCode());
			} else {
				oldJson.put("identity_code", cmEmployee.getIdentityCode());
			}
			oldJson.put("identity_type",oldEmp.getIdentityType());
			oldJson.put("mobile",oldEmp.getMobile());
			oldJson.put("insuranceSalary", cmEmployee.getInsuranceSalary());
			oldJson.put("fundSalary", cmEmployee.getFundSalary());

			com.alibaba.fastjson.JSONObject newJson = JSON.parseObject(cmEmployee.getJson());
			for (Map.Entry<String, Object> entry : newJson.entrySet()) {
				oldJson.put(entry.getKey(), entry.getValue());
			}
			cmEmployee.setJson(clearJson(oldJson.toJSONString()));
			return update(cti, cmEmployee);
		}else{
			cmEmployee.setEmpId(null);
			cmEmployee.setEntryTime(new Date());
			return insert(cti, cmEmployee);
		}
	}

	/**
	 * 清除无用字段
	 * @param json
	 * @return
	 */
	private String clearJson(String json){
		if(StringUtils.isBlank(json))
			return null;
		String[] notAttr = {"beginDate","insurance"};

		com.alibaba.fastjson.JSONObject newJson = JSON.parseObject(json);
		for (int i = 0; i < notAttr.length; i++) {
			if(newJson.containsKey(notAttr[i])) {
				newJson.remove(notAttr[i]);
			}
		}

		return newJson.toJSONString();
	}
	/**
	 * 公积金调基列表
	 *
	 * @author lifq
	 *
	 * 2017年6月29日  上午11:11:30
	 */
	public PageModel fundAdjustList(PageInfo pi, CmEmployee cmEmployee){


		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.fundAdjustListCount(cmEmployee);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.fundAdjustList(cmEmployee, pageIndex, pageSize));

		return pm;
	}
	/**
	 * 导出公积金调基数据
	 *
	 * @author lifq
	 *
	 * 2017年6月29日  上午11:44:05
	 */
	public List<Map<String,Object>> exportFundAdjust(ContextInfo cti,String empids){
		return cmEmployeeDao.exportFundAdjust(cti,empids);
	}
	/**
	 * 查询 服务商员工列表
	 *
	 * @author lifq
	 *
	 * 2017年7月21日  上午11:40:59
	 */
	public PageModel findSpEmpListPage(PageInfo pi, Map<String,Object> paraMap){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeDao.findSpEmpListCount(paraMap);
		pm.setTotal(total);
		pm.setDatas(cmEmployeeDao.findSpEmpList(paraMap, pageIndex, pageSize));

		return pm;
	}
	/**
	 * 查询 人员信息（不分页查询）
	 *
	 * @author lifq
	 *
	 * 2017年7月21日  下午4:25:28
	 */
	public List<Map<String,Object>> findSpEmpList(Map<String,Object> paraMap){
		return cmEmployeeDao.findSpEmpList(paraMap);
	}


	/**
	 * 获取当前企业下所有员工
	 * @param cti
	 * @return
	 */
	public Map<String,CmEmployee> queryCorpAllEmps(ContextInfo cti){
		List<CmEmployee> employees = cmEmployeeDao.queryCorpAllEmps(cti);
		Map<String,CmEmployee> cmEmployeeMap = new HashMap<>();
		for(CmEmployee employee : employees){
			cmEmployeeMap.put(employee.getName() + "_" + employee.getIdentityCode(),employee);
		}
		return cmEmployeeMap;
	}
	/**
	 * 导出 服务商员工列表数据
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午3:10:13
	 */
	public String exportSpEmpList(ContextInfo cti,Map<String,Object> paraMap, CmEmployee cmEmployee){
		// 获取参保信息---Excel动态标题头headerModels
//		List<Map<String, Object>> headerModels = new ArrayList<>();
		Integer spId = paraMap.containsKey("spId") ? (Integer)paraMap.get("spId") : null;
		// 社保
		List<Map<String, Object>> insurance = bdInsuranceareaDao.fundInsFundByCpIdSpId(cti.getOrgId(),spId,"INSURANCE");
		// 公积金
		List<Map<String, Object>> fund = bdInsuranceareaDao.fundInsFundByCpIdSpId(cti.getOrgId(),spId,"FUND");
		
		List<ExcelHeaderModel> excelHeaderModels = new ArrayList<>();
		/**
		 * 基础信息
		 */
		ExcelHeaderModel excelHeaderModel0 = new ExcelHeaderModel("基础信息");
		List<ExcelHeaderModel> excelHeaderModels0 = new ArrayList<>();
		ExcelHeaderModel excelHeaderModel00 = new ExcelHeaderModel("姓名");
		excelHeaderModel00.setAlias("name");
		excelHeaderModels0.add(excelHeaderModel00);
		ExcelHeaderModel excelHeaderModel01 = new ExcelHeaderModel("证件号码");
		excelHeaderModel01.setAlias("identityCode");
		excelHeaderModels0.add(excelHeaderModel01);
		ExcelHeaderModel excelHeaderModel02 = new ExcelHeaderModel("所属公司");
		excelHeaderModel02.setAlias("branch");
		excelHeaderModels0.add(excelHeaderModel02);
		ExcelHeaderModel excelHeaderModel03 = new ExcelHeaderModel("所属月份");
		excelHeaderModel03.setAlias("period");
		excelHeaderModels0.add(excelHeaderModel03);
		ExcelHeaderModel excelHeaderModel04 = new ExcelHeaderModel("参保城市");
		excelHeaderModel04.setAlias("areaName");
		excelHeaderModels0.add(excelHeaderModel04);
		ExcelHeaderModel excelHeaderModel05 = new ExcelHeaderModel("户籍类型");
		excelHeaderModel05.setAlias("livingType");
		excelHeaderModels0.add(excelHeaderModel05);
		excelHeaderModel0.setChildren(excelHeaderModels0);
		excelHeaderModels.add(excelHeaderModel0);
		
		/**
		 * 五险一金缴纳详情
		 */
		ExcelHeaderModel excelHeaderModel1 = new ExcelHeaderModel("五险一金缴纳详情");
		List<ExcelHeaderModel> excelHeaderModels1 = new ArrayList<>();
		for (Map<String, Object> insMap: insurance) {
			// 企业基数,企业比例,企业附加额,企业金额,  个人基数,个人比例,个人附加金额,个人金额
			if("corp_paybase".equals(insMap.get("childCode"))
					|| "corp_ratio".equals(insMap.get("childCode"))
					|| "corp_addition".equals(insMap.get("childCode"))
					|| "corp_payment".equals(insMap.get("childCode"))
					|| "emp_paybase".equals(insMap.get("childCode"))
					|| "emp_ratio".equals(insMap.get("childCode"))
					|| "emp_addition".equals(insMap.get("childCode"))
					|| "emp_payment".equals(insMap.get("childCode"))
					|| "total_sum".equals(insMap.get("childCode"))){
				
				ExcelHeaderModel parentExcelHeaderModel = null;
				if (!excelHeaderModels1.isEmpty()) {
					parentExcelHeaderModel = excelHeaderModels1.get(excelHeaderModels1.size() - 1);
				}
				String parentCode = (String) insMap.get("parentCode");
				if (parentExcelHeaderModel == null || !parentExcelHeaderModel.getAlias().equals(parentCode)) {
					parentExcelHeaderModel = new ExcelHeaderModel((String) insMap.get("parentName"));
					parentExcelHeaderModel.setAlias(parentCode);
					excelHeaderModels1.add(parentExcelHeaderModel);
				}
				ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel((String) insMap.get("childName"));
				excelHeaderModel.setAlias((String) insMap.get("childCode"));
				parentExcelHeaderModel.addChild(excelHeaderModel);
			}
			
		}
		/**
		 * 公积金
		 */
		ExcelHeaderModel fundExcelHeaderModel = new ExcelHeaderModel("公积金");
		fundExcelHeaderModel.setAlias("Fund");
		for (Map<String, Object> fundMap: fund) {
			// 企业基数,企业比例,企业附加额,企业金额,  个人基数,个人比例,个人附加金额,个人金额
			if("corp_paybase".equals(fundMap.get("childCode"))
					|| "corp_ratio".equals(fundMap.get("childCode"))
					|| "corp_addition".equals(fundMap.get("childCode"))
					|| "corp_payment".equals(fundMap.get("childCode"))
					|| "emp_paybase".equals(fundMap.get("childCode"))
					|| "emp_ratio".equals(fundMap.get("childCode"))
					|| "emp_addition".equals(fundMap.get("childCode"))
					|| "emp_payment".equals(fundMap.get("childCode"))
					|| "total_sum".equals(fundMap.get("childCode"))){
				
					ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel((String) fundMap.get("childName"));
					excelHeaderModel.setAlias((String) fundMap.get("childCode"));
					fundExcelHeaderModel.addChild(excelHeaderModel);
			}
			
		}
		if (fundExcelHeaderModel.getChildren() != null) {
			excelHeaderModels1.add(fundExcelHeaderModel);
		}
		/**
		 * 社保小计
		 */
		ExcelHeaderModel insuSumExcelHeaderModel = new ExcelHeaderModel("社保小计");
		insuSumExcelHeaderModel.setAlias("wxxj");
		ExcelHeaderModel insuSumExcelHeaderModel1 = new ExcelHeaderModel("企业");
		insuSumExcelHeaderModel1.setAlias("crop");
		insuSumExcelHeaderModel.addChild(insuSumExcelHeaderModel1);
		ExcelHeaderModel insuSumExcelHeaderModel2 = new ExcelHeaderModel("个人");
		insuSumExcelHeaderModel2.setAlias("emp");
		insuSumExcelHeaderModel.addChild(insuSumExcelHeaderModel2);
		ExcelHeaderModel insuSumExcelHeaderModel3 = new ExcelHeaderModel("合计");
		insuSumExcelHeaderModel3.setAlias("total_sum");
		insuSumExcelHeaderModel.addChild(insuSumExcelHeaderModel3);
		excelHeaderModel1.addChild(insuSumExcelHeaderModel);
		
		excelHeaderModels1.add(insuSumExcelHeaderModel);
		/**
		 * 社保公积金小计
		 */
		ExcelHeaderModel fundSumExcelHeaderModel = new ExcelHeaderModel("社保公积金小计");
		fundSumExcelHeaderModel.setAlias("wxxjyj");
		ExcelHeaderModel fundSumExcelHeaderModel1 = new ExcelHeaderModel("企业");
		fundSumExcelHeaderModel1.setAlias("crop");
		fundSumExcelHeaderModel.addChild(fundSumExcelHeaderModel1);
		ExcelHeaderModel fundSumExcelHeaderModel2 = new ExcelHeaderModel("个人");
		fundSumExcelHeaderModel2.setAlias("emp");
		fundSumExcelHeaderModel.addChild(fundSumExcelHeaderModel2);
		ExcelHeaderModel fundSumExcelHeaderModel3 = new ExcelHeaderModel("合计");
		fundSumExcelHeaderModel3.setAlias("total_sum");
		fundSumExcelHeaderModel.addChild(fundSumExcelHeaderModel3);
		excelHeaderModel1.addChild(fundSumExcelHeaderModel);
		excelHeaderModels1.add(fundSumExcelHeaderModel);
		
		
		excelHeaderModel1.setChildren(excelHeaderModels1);
		excelHeaderModels.add(excelHeaderModel1);
		
		/**
		 * 统计
		 */
		ExcelHeaderModel sumExcelHeaderModel = new ExcelHeaderModel("统计");
		ExcelHeaderModel sumExcelHeaderMode10 = new ExcelHeaderModel("服务费");
		sumExcelHeaderMode10.setAlias("price");
		sumExcelHeaderModel.addChild(sumExcelHeaderMode10);
		ExcelHeaderModel sumExcelHeaderMode11 = new ExcelHeaderModel("企业合计");
		sumExcelHeaderMode11.setAlias("fee_crop_sum");
		sumExcelHeaderModel.addChild(sumExcelHeaderMode11);
		ExcelHeaderModel sumExcelHeaderMode12 = new ExcelHeaderModel("个人合计");
		sumExcelHeaderMode12.setAlias("fee_emp_sum");
		sumExcelHeaderModel.addChild(sumExcelHeaderMode12);
		ExcelHeaderModel sumExcelHeaderMode13 = new ExcelHeaderModel("总计");
		sumExcelHeaderMode13.setAlias("fee_total");
		sumExcelHeaderModel.addChild(sumExcelHeaderMode13);
		excelHeaderModels.add(sumExcelHeaderModel);
		
		/*
		Map<String, String> beginMap = new HashMap<>();
		beginMap.put("parentName", "基本信息");
		beginMap.put("parentCode", "empInfo");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "五险一金缴纳详情");
		beginMap.put("parentCode", "insFundInfo");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "统计");
		beginMap.put("parentCode", "tj");
		otherHeaderModels.add(beginMap);
		
		
		Map<String, Object> empMap = new HashMap<>();
		empMap.put("parentName", "基本信息");
		empMap.put("parentCode", "empInfo");
		empMap.put("childName", "姓名");
		empMap.put("childCode", "name");
		headerModels.add(empMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "证件号码");
		beginMap.put("parentCode", "identityCode");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "所属公司");
		beginMap.put("parentCode", "branch");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "所属月份");
		beginMap.put("parentCode", "period");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "参保城市");
		beginMap.put("parentCode", "areaName");
		otherHeaderModels.add(beginMap);
		beginMap = new HashMap<>();
		beginMap.put("parentName", "户籍类型");
		beginMap.put("parentCode", "livingType");
		otherHeaderModels.add(beginMap);
		for (int i = 0; i < insurance.size(); i++) {
			Map<String, Object> insMap = insurance.get(i);
			// 企业基数,企业比例,企业附加额,企业金额,  个人基数,个人比例,个人附加金额,个人金额
			if("corp_paybase".equals(insMap.get("childCode"))
					|| "corp_ratio".equals(insMap.get("childCode"))
					|| "corp_addition".equals(insMap.get("childCode"))
					|| "corp_payment".equals(insMap.get("childCode"))
					|| "emp_paybase".equals(insMap.get("childCode"))
					|| "emp_ratio".equals(insMap.get("childCode"))
					|| "emp_addition".equals(insMap.get("childCode"))
					|| "emp_payment".equals(insMap.get("childCode"))
					|| "total_sum".equals(insMap.get("childCode"))){
				headerModels.add(insMap);
			}
		}
//				Map<String, Object> ins_sbxj = new HashMap<>();
//				ins_sbxj.put("wxxj", "社保小计");
//				headerModels.add(ins_sbxj);
		Map<String, Object> wxxj_crop = new LinkedHashMap<>();
		wxxj_crop.put("parentCode", "wxxj");
		wxxj_crop.put("parentName", "社保小计");
		wxxj_crop.put("childCode", "crop");
		wxxj_crop.put("childName", "企业");
		headerModels.add(wxxj_crop);

		Map<String, Object> wxxj_emp = new LinkedHashMap<>();
		wxxj_emp.put("parentCode", "wxxj");
		wxxj_emp.put("parentName", "社保小计");
		wxxj_emp.put("childCode", "emp");
		wxxj_emp.put("childName", "个人");
		headerModels.add(wxxj_emp);

		Map<String, Object> wxxj_total = new LinkedHashMap<>();
		wxxj_total.put("parentCode", "wxxj");
		wxxj_total.put("parentName", "社保小计");
		wxxj_total.put("childCode", "total_sum");
		wxxj_total.put("childName", "合计");
		headerModels.add(wxxj_total);

//		Map<String, Object> fundArea = new HashMap<>();
//		fundArea.put("parentName", "公积金缴纳城市");
//		fundArea.put("parentCode", "fund_area");
//		headerModels.add(fundArea);
		// 公积金
		for (int i = 0; i < fund.size(); i++) {
			Map<String, Object> map = fund.get(i);
			// 企业基数,企业比例,企业附加额,企业金额,  个人基数,个人比例,个人附加金额,个人金额
			if("corp_paybase".equals(map.get("childCode"))
					|| "corp_ratio".equals(map.get("childCode"))
					|| "corp_addition".equals(map.get("childCode"))
					|| "corp_payment".equals(map.get("childCode"))
					|| "emp_paybase".equals(map.get("childCode"))
					|| "emp_ratio".equals(map.get("childCode"))
					|| "emp_addition".equals(map.get("childCode"))
					|| "emp_payment".equals(map.get("childCode"))
					|| "total_sum".equals(map.get("childCode"))){
				headerModels.add(map);
			}
		}
//				Map<String, Object> fund_sbxj = new HashMap<>();
//				fund_sbxj.put("wxxj", "社保小计");
//				headerModels.add(fund_sbxj);
		if(fund.size() > 0){
			Map<String, Object> wxxjyj_crop = new LinkedHashMap<>();
			wxxjyj_crop.put("parentCode", "wxxjyj");
			wxxjyj_crop.put("parentName", "社保公积金小计");
			wxxjyj_crop.put("childCode", "crop");
			wxxjyj_crop.put("childName", "企业");
			headerModels.add(wxxjyj_crop);

			Map<String, Object> wxxjyj_emp = new LinkedHashMap<>();
			wxxjyj_emp.put("parentCode", "wxxjyj");
			wxxjyj_emp.put("parentName", "社保公积金小计");
			wxxjyj_emp.put("childCode", "emp");
			wxxjyj_emp.put("childName", "个人");
			headerModels.add(wxxjyj_emp);

			Map<String, Object> wxxjyj_total = new LinkedHashMap<>();
			wxxjyj_total.put("parentCode", "wxxjyj");
			wxxjyj_total.put("parentName", "社保公积金小计");
			wxxjyj_total.put("childCode", "total_sum");
			wxxjyj_total.put("childName", "合计");
			headerModels.add(wxxjyj_total);
		}
		Map<String, Object> endMap = new HashMap<>();
		endMap.put("parentCode", "price");
		endMap.put("parentName", "服务费");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "fee_crop_sum");
		endMap.put("parentName", "企业合计");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "fee_emp_sum");
		endMap.put("parentName", "个人合计");
		headerModels.add(endMap);
		endMap = new LinkedHashMap<>();
		endMap.put("parentCode", "fee_total");
		endMap.put("parentName", "总计");
		headerModels.add(endMap);
		*/
		List<Map<String, Object>> dataList = null;
		if(spId!=null) {
			/**1.查询 员工基本信息 */
			dataList = cmEmployeeDao.findSpEmpList(paraMap);
		}else{
			dataList = cmEmployeeDao.findEmpList(cmEmployee);
		}
		/**2.添加 费用信息 */
		//获取员工动态标题列表
		if(null != dataList && dataList.size()>0 ){
			bindEmpInsuranceI(cti,dataList,"INSDETAIL",paraMap.get("curDate").toString());
		}
//		List<Map<String,Object>> dataMap = spsBillEmpService.queryAllEmpListInsDetail(cti,period,searchWords,areaId);
//		ExcelModel excelModel = ExcelUtil.convert2ExcelModel("五险一金明细表", "五险一金缴纳详情", otherHeaderModels, headerModels, dataList);
		ExcelModel excelModel = new ExcelModel();
		excelModel.setSheetname("五险一金明细表");
		excelModel.setHeaderModels(excelHeaderModels);
		excelModel.setDataMapModels(dataList);
		
		// 将动态头拼接到Excel上
		List<ExcelModel> excelModels = new ArrayList<>();
		excelModels.add(excelModel);
		ExcelCore core = new ExcelCore();
		core.process(excelModels);

		String fileName = "账单详情.xlsx";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String filePath = format.format(Calendar.getInstance().getTime());
		File excelFile = geteEmptyFile(fileName, filePath);
		core.export(excelFile);
		return excelFile.getPath();
		
	}
	/**
	 * 绑定员工费用信息
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午3:40:07
	 */
	private void bindEmpInsuranceI(ContextInfo cti,List<Map<String,Object>> empInfoList,String insType,String period){
         CmCorp cmCorp = cmCorpService.findByPK(cti.getOrgId());

		//费用总计
		BigDecimal total_crop_sum = BigDecimal.ZERO;
		BigDecimal total_emp_sum = BigDecimal.ZERO;
		BigDecimal total_fee_sum = BigDecimal.ZERO;
		BigDecimal total_price = BigDecimal.ZERO;

        for(Map<String,Object> item : empInfoList){
            try {
                if(null == item.get("empId")){
                    continue;
                }
                Integer empId = (Integer)item.get("empId");
                item.put("period",period);//所属月份
                List<InsureEmpDetail> billInsurance =  spsBillService.queryEmpBillPayment(cti,empId,period);
                BigDecimal crop_sum = BigDecimal.ZERO;
                BigDecimal emp_sum = BigDecimal.ZERO;
                BigDecimal fun_crop_sum = BigDecimal.ZERO;
                BigDecimal fun_emp_sum = BigDecimal.ZERO;
                for(InsureEmpDetail rpt : billInsurance){//解析社保信息
                    if(null == rpt.getInsuranceFundType())
                        continue;
                    String ins_type = rpt.getInsuranceFundType();
                    Map<String,String> detail = new HashMap<>();
                    detail.put("corp_paybase", rpt.getCorpPaybase());
                    detail.put("corp_ratio", rpt.getCorpRatio());
                    detail.put("corp_addition", rpt.getCorpAddition());
                    detail.put("corp_payment", rpt.getCorpPayment());
                    detail.put("emp_paybase", rpt.getEmpPaybase());
                    detail.put("emp_ratio", rpt.getEmpRatio());
                    detail.put("emp_addition", rpt.getEmpAddition());
                    detail.put("emp_payment", rpt.getEmpPayment());
                    detail.put("total_sum", rpt.getTotalPayment());
                    item.put(String.valueOf(rpt.getCode()),detail);
                    if("INSURANCE".equals(ins_type)){
                        crop_sum = crop_sum.add(com.xfs.common.util.StringUtils.toBigDecimal(rpt.getCorpPayment()).setScale(2, BigDecimal.ROUND_DOWN));
                        emp_sum = emp_sum.add(com.xfs.common.util.StringUtils.toBigDecimal(rpt.getEmpPayment()).setScale(2, BigDecimal.ROUND_DOWN));
                    }else if("FUND".equals(ins_type)){
                        fun_crop_sum = fun_crop_sum.add(com.xfs.common.util.StringUtils.toBigDecimal(rpt.getCorpPayment()).setScale(2, BigDecimal.ROUND_DOWN));
                        fun_emp_sum = fun_emp_sum.add(com.xfs.common.util.StringUtils.toBigDecimal(rpt.getEmpPayment()).setScale(2, BigDecimal.ROUND_DOWN));
                    }
                    /**
                     * 获取公积金参保地
                     */
                }
                //五险小计
                Map<String,Object> wxxj = new HashMap<>();
                item.put("wxxj",wxxj);
                wxxj.put("crop",crop_sum);
                wxxj.put("emp",emp_sum);
                wxxj.put("total_sum",crop_sum.add(emp_sum));
                //五险一金小计
                Map<String,Object> wxxjyj = new HashMap<>();
                item.put("wxxjyj",wxxjyj);
                wxxjyj.put("crop",crop_sum.add(fun_crop_sum));
                wxxjyj.put("emp",emp_sum.add(fun_emp_sum));
                wxxjyj.put("total_sum",crop_sum.add(emp_sum).add(fun_crop_sum).add(fun_emp_sum));
                //总费用合计---获取员工费用信息
                item.put("fee_crop_sum",crop_sum.add(fun_crop_sum));
                item.put("fee_emp_sum",emp_sum.add(fun_emp_sum));
                BigDecimal price =  new BigDecimal(null==item.get("price")?"0":item.get("price").toString());
                if("INSDETAIL".equals(insType)){
                    item.put("fee_total", Arith.add(crop_sum.add(fun_crop_sum),emp_sum.add(fun_emp_sum),price));
                }else{
                    item.put("fee_total", Arith.add(crop_sum.add(fun_crop_sum),emp_sum.add(fun_emp_sum),price));
                }
                item.put("adjust_reason",null == item.get("adjust_reason") ? "" : item.get("adjust_reason"));
                total_crop_sum = total_crop_sum.add(crop_sum).add(fun_crop_sum);
                total_emp_sum = total_emp_sum.add(emp_sum).add(fun_emp_sum);
                total_fee_sum = total_fee_sum.add(price);
                total_price = total_price.add(price);//服务费总计

            } catch (Exception e) {
                log.info("无方案导致，数据异常：" + JSON.toJSONString(item) + "；异常信息：" + e.getMessage());
            }
        }
        //多出合计一列
		Map<String,Object> t_fee_total = new HashMap<>();
		t_fee_total.put("fee_crop_sum",total_crop_sum);
		t_fee_total.put("fee_emp_sum",total_emp_sum);
		t_fee_total.put("fee",total_fee_sum);
		t_fee_total.put("fee_total", Arith.add(total_crop_sum,total_emp_sum,total_fee_sum));
		t_fee_total.put("adjust_reason","总计");
		t_fee_total.put("price", total_price);

		empInfoList.add(t_fee_total);
	}

	/**
	 * 获取人员相关的 服务商列表名称
	 */
	public List<Map<String, Object>> findSpServiceByEmpCpId(Map<String,Object> paraMap){
		return cmEmployeeDao.findSpServiceByEmpCpId(paraMap);
	}

	/**
	 * 根据当前输入人的信息，查找已经存在数据库的员工
	 * @param cmEmployee
	 * @return
	 */
	public CmEmployee queryExistEmpByInfo(CmEmployee cmEmployee,CmEmployeeOrg cmEmployeeOrg){

		if(!RedisUtil.exists(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId())){
			CmEmployee queryVo = new CmEmployee();
			queryVo.setCpId(cmEmployee.getCpId());
			queryVo.setDr(0);
			List<CmEmployee> cmEmployees = findAll(queryVo);
			if(null == cmEmployees || cmEmployees.isEmpty()){
				cmEmployees = new ArrayList<>();
				RedisUtil.hput(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),"--","--");
			}
			List<CmEmployee> employees;
			for(CmEmployee employee : cmEmployees){
				if(StringUtils.isNotBlank(employee.getIdentityCode()))
					RedisUtil.hput(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),employee.getName()+"_"+employee.getIdentityCode(),employee);

				employees = (List<CmEmployee>)RedisUtil.hget(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),employee.getName());
				if(null != employees){
					employees.add(employee);
					RedisUtil.hput(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),employee.getName(),employees);
				}else {
					employees = new ArrayList<>();
					employees.add(employee);
					RedisUtil.hput(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),employee.getName(),employees);
				}
			}
			RedisUtil.setKeyExpireTime(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),10);
		}else{
			RedisUtil.setKeyExpireTime(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),10);
		}

		Object ob = RedisUtil.hget(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),cmEmployee.getName()+"_"+cmEmployee.getIdentityCode());
		if(null == ob){
			List<CmEmployee> employees = (List<CmEmployee>)RedisUtil.hget(SYNC_SYS_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployee.getCpId(),cmEmployee.getName());
			if(null != employees && !employees.isEmpty() && employees.size() == 1)
				ob = employees.get(0);
			else if(null != employees && !employees.isEmpty() && employees.size() > 1){
				for(CmEmployee emp : employees){
					if(null != cmEmployeeOrg.getEmpId() && emp.getEmpId().equals(cmEmployeeOrg.getEmpId())){
						ob = emp;
						cmEmployeeOrg.setRepeat(1);
					}
				}
				if(null == ob)
					cmEmployeeOrg.setRepeat(0);
			}

		}
		if(null != ob){
			CmEmployee cacheEmployee = (CmEmployee)ob;
			return cacheEmployee;
		}
		return null;
	}

    /**
     * 参保人员趋势图
     * @param cti
     * @param areaId
     * @param months
     * @return
     */
    public List<Map<String,Object>> queryEmpTrend(ContextInfo cti,Integer areaId,List<String> months){
        return cmEmployeeDao.queryEmpTrend(cti, areaId, months);
    }

    /**
     * 任务中心--保存人员信息
     *  @param cti
     *  @param cmEmployee
     *  @param isIns
     *  @param isFund
     *  @param msgId
     *  @param taskId
     *  @param auditId 
     *  @param operation
     *  @createDate  	: 2017年9月21日 下午2:38:40
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月21日 下午2:38:40
     *  @updateAuthor  	:
     */
	@Override
	public List<Integer> saveEmpDetail(Result res,ContextInfo cti, CmEmployee cmEmployee, Integer isIns, Integer isFund, Integer msgId
			, Integer taskId, Integer auditId,Integer operation,List<DynamicFields> otherJson) {
		List<Integer> taskIds = new ArrayList<>();
		// 获取任务单
		SpsTask oldSpsTask = new SpsTask();
		oldSpsTask.setTaskId(taskId);
		oldSpsTask = spsTaskService.findByPK(oldSpsTask);
		com.alibaba.fastjson.JSONObject otherParam = JSON.parseObject(oldSpsTask.getJson());
		// 操作类型 0：处理，1：网申
		if(0 == operation){
			// 获取中间表数据
			CmEmployeeAudit cmEmployeeAudit = new CmEmployeeAudit();
			cmEmployeeAudit.setEmpId(auditId);
			cmEmployeeAudit = cmEmployeeAuditService.findByPK(cmEmployeeAudit);
			if(null != cmEmployeeAudit.getOpenId())
				cmEmployee.setOpenid(cmEmployeeAudit.getOpenId());
			// 根据名称和身份证号查询员工是否存在
			CmEmployee query = new CmEmployee();
			if(null != otherParam.getInteger("userId")){
				query.setEmpId(otherParam.getInteger("userId"));
				query = cmEmployeeDao.findByPK(query);
			}else{
				query.setCpId(cti.getOrgId());
				query.setName(cmEmployeeAudit.getName());
				query.setIdentityCode(cmEmployeeAudit.getIdentityCode());
				query = cmEmployeeDao.findCpEmplByNameAndIdentityCode(query);
			}
			if(null != query && null != query.getEmpId()){
				cmEmployee.setEmpId(query.getEmpId());
			}else{
				cmEmployee.setEmpId(null);
			}
			cmEmployee.setName(oldSpsTask.getName());
			cmEmployee.setAreaId(oldSpsTask.getAreaId().toString());
			cmEmployee.setMobile(oldSpsTask.getMobile());
			if(null != otherParam.get("identity_type")){
				cmEmployee.setIdentityType(otherParam.get("identity_type").toString());
			}
			cmEmployee.setIdentityCode(oldSpsTask.getIdentityCode());
			if(null != otherParam.get("photo")){
				cmEmployee.setHeadPhoto(otherParam.get("photo").toString());
			}
			// 根据方案ID获取方案
			SpsScheme scheme = new SpsScheme();
			scheme.setSchemeId(cmEmployee.getSchemeId());
			scheme.setDr(0);
			scheme = spsSchemeService.findByPK(scheme);
			BigDecimal insuranceSalary = BigDecimal.ZERO;
			BigDecimal fundSalary = BigDecimal.ZERO;
			// 如果不存在方案创建默认方案
			if(null == scheme){
				// 社保公积金比例
				AreaSocialRuleVo areaSocialRuleVo = spsAccountRatioService.findSocialRule(cti,cmEmployeeAudit.getAreaId(),null);
	            SchemeAccountRatio applyMessage = new SchemeAccountRatio();
	            applyMessage.setAreaId(cmEmployeeAudit.getAreaId());
	            List<InsuranceRatio> insuranceRatio = new ArrayList<>();
	            List<FundRatio> fundRatio = new ArrayList<>();
	            applyMessage.setInsuranceRatio(insuranceRatio);
	            applyMessage.setFundRatio(fundRatio);
	            // 默认社保比例
	            for(InsuranceLivingTypeVo insurance : areaSocialRuleVo.getInsuranceLivingType()){
	                InsuranceRatio ratio = new InsuranceRatio();
	                ratio.setInsuranceLivingType(insurance.getPeopleTypeCode());
	                ratio.setRatio(new ArrayList<Ratio>());
	                for(InsuranceLivingTypeDetailsVo detailvo : insurance.getInsuranceLivingType()){
	                    Ratio r = new Ratio();
	                    r.setRatioId(detailvo.getRatioId());
	                    ratio.getRatio().add(r);
	                    // 获取基数下限
	                    if(insuranceSalary.compareTo(BigDecimal.ZERO) == 0){
	                    	insuranceSalary = new BigDecimal(detailvo.getLowerNum());
	                    }else{
	                    	if(insuranceSalary.compareTo(new BigDecimal(detailvo.getLowerNum()))  > 0 
	                    			&& new BigDecimal(detailvo.getLowerNum()).compareTo(BigDecimal.ZERO) > 0){
	                    		insuranceSalary = new BigDecimal(detailvo.getLowerNum());
	                    	}
	                    }
	                }
	                insuranceRatio.add(ratio);
	            }
	            // 默认公积金比例
	            for(InsuranceLivingTypeDetailsVo insurance : areaSocialRuleVo.getFundType()){
	                FundRatio ratio = new FundRatio();
	                ratio.setRatioId(insurance.getRatioId());
	                fundRatio.add(ratio);
	                // 获取基数下限
	                if(fundSalary.compareTo(BigDecimal.ZERO) == 0){
	                	fundSalary = new BigDecimal(insurance.getLowerNum());
	                }else{
	                	if(fundSalary.compareTo(new BigDecimal(insurance.getLowerNum()))  > 0 
	                			&& new BigDecimal(insurance.getLowerNum()).compareTo(BigDecimal.ZERO) > 0){
	                		fundSalary = new BigDecimal(insurance.getLowerNum());
	                	}
	                }
	            }
	            scheme = spsAccountService.saveAccount(cti,applyMessage);
			}
			// 是否缴纳社保 0：未缴纳；1：缴纳
			if(isIns.equals(1)){
				// 社保基数为空或者等于0
				if(null == cmEmployee.getInsuranceSalary() 
						|| cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) != 1){
					List<InsuranceTypeDto> insuranceTypeDtos = this.queryAccountInsuranceListByAccountId(scheme.getInsuranceAccountId(), cmEmployee.getInsuranceLivingType(), cmEmployee.getBeginDate(), "Y");
		            for(InsuranceTypeDto dto : insuranceTypeDtos){
		                if(insuranceSalary.compareTo(BigDecimal.ZERO) == 0){
		                	insuranceSalary = dto.getCorpBaseLower();
		                }else if(insuranceSalary.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
		                	insuranceSalary = dto.getCorpBaseLower();
		                }
		            }
				}else{
					insuranceSalary = cmEmployee.getInsuranceSalary();
				}
				if(null != cmEmployee.getInsuranceSalary() 
						&& cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) == 1){
					cmEmployee.setInsuranceSalary(insuranceSalary);
				}
			}
			// 是否缴纳公积金 0：未缴纳；1：缴纳
			if(isFund.equals(1)){
				// 公积金基数为空或者等于0
				if(null == cmEmployee.getFundSalary() || cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) != 1){
					List<InsuranceTypeDto> insuranceTypeDtos = this.queryAccountInsuranceListByAccountId(scheme.getFundAccountId(), "", cmEmployee.getBeginDate(), "Y");
					for(InsuranceTypeDto dto : insuranceTypeDtos){
		                if(fundSalary.compareTo(BigDecimal.ZERO) == 0){
		                	fundSalary = dto.getCorpBaseLower();
		                }else if(fundSalary.compareTo(dto.getCorpBaseLower())  > 0 && dto.getCorpBaseLower().compareTo(BigDecimal.ZERO) > 0){
		                	fundSalary = dto.getCorpBaseLower();
		                }
		            }
				}else{
					fundSalary = cmEmployee.getFundSalary();
				}
				if(null != cmEmployee.getFundSalary() 
						&& cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) == 1){
					cmEmployee.setFundSalary(fundSalary);
				}
			}
			cmEmployee.setSchemeId(scheme.getSchemeId());
			cmEmployee.setDr(0);
			cmEmployee.setInsuranceState("OFF");
			cmEmployee.setIsQuit(0);
			cmEmployee.setFundState("OFF");
			cmEmployee.setEntryTime(new Date());
			cmEmployee.setIdentityType(cmEmployeeAudit.getIdentityType());
			SysUploadfile sysUploadfile = new SysUploadfile();
			sysUploadfile.setId(cmEmployeeAudit.getHeadPhoto());
			sysUploadfile = sysUploadfileService.findByPK(sysUploadfile);
			if(null != sysUploadfile){
				cmEmployee.setHeadPhoto(sysUploadfile.getSavename());
			}
			// 保存员工
			if(null != cmEmployee.getEmpId()){
				cmEmployeeDao.update(cti, cmEmployee);
			}else{
				cmEmployeeDao.save(cti, cmEmployee);
			}
			SpsSchemeEmp vo = new SpsSchemeEmp();
			vo.setEmpId(cmEmployee.getEmpId());
			vo.setState("USE");
			vo.setDr(0);
			List<SpsSchemeEmp> spsSchemeEmps = spsSchemeEmpService.findAll(vo);
			if(spsSchemeEmps.size() == 0){
				vo.setSchemeId(scheme.getSchemeId());
				// 保存员工与方案的关系
				spsSchemeEmpService.save(cti, vo);
			}else{
				for (int i = 0; i < spsSchemeEmps.size(); i++) {
					SpsSchemeEmp obj = spsSchemeEmps.get(i);
					obj.setSchemeId(scheme.getSchemeId());
					spsSchemeEmpService.update(cti, obj);
				}
			}
			SpsTask spsTask = new SpsTask();
			spsTask.setCpId(cti.getOrgId());
			spsTask.setSpId(scheme.getSpId());
			spsTask.setBeginDate(cmEmployee.getBeginDate());

			//是否在本地工作过 0为在此地工作过，1为第一份工作
			if(0 == cmEmployeeAudit.getIsWorked()){
				spsTask.setBstypeId(3);// 转入
			}else{
				spsTask.setBstypeId(2);// 新参
			}
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(oldSpsTask.getJson());
			jsonObject.put("identity_type", "IDCard");
			jsonObject.put("identity_code", cmEmployee.getIdentityCode());
			jsonObject.put("name", cmEmployee.getName());
			jsonObject.put("mobile", cmEmployee.getMobile());
			jsonObject.put("beginDate", spsTask.getBeginDate());
			jsonObject.put("insuranceLiveType", cmEmployee.getInsuranceLivingType());
			 // 社保默认户口性质和城市下所有户口性质
			SysDictitem sysDictitem = new SysDictitem();
			sysDictitem.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			sysDictitem.setCode(cmEmployee.getInsuranceLivingType());
			sysDictitem.setDictionary(93);
	        List<SysDictitem> liveTypeMap = sysDictitemService.findAll(sysDictitem);
	        jsonObject.put("insuranceLiveType_name", liveTypeMap.get(0).getName());
			jsonObject.put("insuranceSalary", cmEmployee.getInsuranceSalary());
			// 默认等待申报
			spsTask.setType(TaskStateFiled.TODO_WAITING_APPLICATION.getTaskType());
			spsTask.setStateFiledId(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId());
			spsTask.setStateFiledName(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledName());
			spsTask.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			spsTask.setName(cmEmployee.getName());
			spsTask.setMobile(cmEmployee.getMobile());
			spsTask.setIdentityCode(cmEmployee.getIdentityCode());
			spsTask.setDr(0);
			spsTask.setEmpId(cmEmployee.getEmpId());
			spsTask.setSchemeId(scheme.getSchemeId());
			// 是否缴纳社保 0：未缴纳；1：缴纳
			if(isIns.equals(1)){
				// 保存任务单 如果社保基数大于0
				if(cmEmployee.getInsuranceSalary().compareTo(BigDecimal.ZERO) == 1 && null != scheme.getInsuranceAccountId()){
					// 社保类型
					List<InsuranceTypeDto> insurance = findInsuranceType(scheme.getInsuranceAccountId(),cmEmployee.getBeginDate(),cmEmployee.getInsuranceLivingType());
					if(insurance.size() > 0){
						jsonObject.put("insurance", insurance);
						spsTask.setJson(jsonObject.toJSONString());
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						corpTaskAspectService.saveTask(cti, spsTask, JSON.parseObject(spsTask.getJson(),ref), res);
						if(null == spsTask.getTaskId()){
							res.setError(res.getError());
							res.setSuccess(false);
							throw new BusinessException(res.getError());
						}else{
							res.setSuccess(true);
							taskIds.add(spsTask.getTaskId());
						}
					}
				}
			}
			// 是否缴纳公积金 0：未缴纳；1：缴纳
			if(isFund.equals(1)){
				// 保存任务单 如果公积金基数大于0
				if(cmEmployee.getFundSalary().compareTo(BigDecimal.ZERO) == 1 && null != scheme.getFundAccountId()){
					List<InsuranceTypeDto> insurance = findInsuranceType(scheme.getFundAccountId(),cmEmployee.getBeginDate(),null);
					if(insurance.size() > 0){
						spsTask.setTaskId(null);
						spsTask.setBstypeId(10);
						// 公积金
						jsonObject.put("fundSalary", cmEmployee.getFundSalary());
						jsonObject.put("insurance", insurance);
						spsTask.setJson(jsonObject.toJSONString());
						TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
						corpTaskAspectService.saveTask(cti, spsTask, JSON.parseObject(spsTask.getJson(),ref), res);
						if(null == spsTask.getTaskId()){
							res.setError(res.getError());
							res.setSuccess(false);
							throw new BusinessException(res.getError());
						}else{
							taskIds.add(spsTask.getTaskId());
						}
					}
				}
			}
			// 更新临时表为审核通过
			cmEmployeeAudit = new CmEmployeeAudit();
			cmEmployeeAudit.setEmpId(auditId);
			cmEmployeeAudit.setState(3);
			cmEmployeeAudit.setIdentityCode(cmEmployee.getIdentityCode());
			cmEmployeeAudit.setMobile(cmEmployee.getMobile());
			cmEmployeeAudit.setInsuranceLivingType(cmEmployee.getInsuranceLivingType());
			cmEmployeeAudit.setInsuranceFundDate(spsTask.getBeginDate());
			if (isIns.equals(1)) {
				cmEmployeeAudit.setInsuranceSalary(cmEmployee.getInsuranceSalary());
			}
			if (isFund.equals(1)) {
				cmEmployeeAudit.setFundSalary(cmEmployee.getFundSalary());
			}
			cmEmployeeAudit.setAreaId(Integer.parseInt(cmEmployee.getAreaId()));
			cmEmployeeAuditService.update(cti, cmEmployeeAudit);
			// 更新入职任务状态为已完成
			oldSpsTask.setType(TaskStateFiled.COMPLETED_APPLICATION.getTaskType());
			oldSpsTask.setStateFiledId(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId());
			oldSpsTask.setStateFiledName(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledName());
			jsonObject.put("isIns", isIns);
			jsonObject.put("isFund", isFund);
			jsonObject.put("schemeId", scheme.getSchemeId());
			oldSpsTask.setJson(jsonObject.toJSONString());
			oldSpsTask.setSchemeId(spsTask.getSchemeId());
			TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
			Result result = new Result();
			corpTaskAspectService.saveTask(cti, oldSpsTask, JSON.parseObject(spsTask.getJson(),ref), result);
			System.out.println(result.getMsg());
		}else{
			Integer bstypeId = oldSpsTask.getBstypeId();
			if(-99 == oldSpsTask.getBstypeId()){
				bstypeId = otherParam.getInteger("bstypeId");
			}
		    // 判断是否是补缴 
			if(BsType.INSUR_BACK.getCode().equals(bstypeId)
					|| BsType.FUND_BACK.getCode().equals(bstypeId)){
				String supplementarybegindate = "";
				String supplementaryenddate = "";
				for (DynamicFields dynamicFields : otherJson) {
					if(dynamicFields.getKey().equals("supplementarybegindate")){
						supplementarybegindate = dynamicFields.getValue();
					}
					if(dynamicFields.getKey().equals("supplementaryenddate")){
						supplementaryenddate = dynamicFields.getValue();
					}
				}
				this.valiteDate(supplementarybegindate, supplementaryenddate, res);
			}
			if(res.isSuccess()){
				Result needField = entryService.getNeedField(oldSpsTask.getAreaId(), bstypeId);
	            List<BusinessField> businessFieldList = (List<BusinessField>) needField.getData().get("businessfield");
				// 修改后的参数
		        for (DynamicFields dynamicFields : otherJson) {
		            saveToObject(otherParam, dynamicFields.getKey(), dynamicFields.getValue(), businessFieldList);
		        }
				oldSpsTask.setJson(otherParam.toJSONString());
				// 马上网申或者再次网申，判断是否是马上网申，是否要做流程处理
				if(!TaskStateFiled.ERROR_APPLICATION.getTaskType().equals(oldSpsTask.getType())){
					oldSpsTask.setStateFiledId(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId());
					oldSpsTask.setStateFiledName(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledName());
				}
				spsTaskService.update(cti, oldSpsTask);
				taskIds.add(oldSpsTask.getTaskId());
			}
		}
		// 更新消息状态
		if(null != msgId){
			SysMessage sysMessage = new SysMessage();
			sysMessage.setMessageId(msgId);
			// 更新消息为已处理
			sysMessage.setState("DONE");
			sysMessageService.update(cti, sysMessage);
		}
		return taskIds;
	}
	
	/**
	 * 组装json
	 *  @param otherParam
	 *  @param key
	 *  @param value
	 *  @param businessFieldList 
	 *	@return 			: void 
	 *  @createDate  	: 2017年11月3日 上午10:46:22
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月3日 上午10:46:22
	 *  @updateAuthor  :
	 */
	private void saveToObject(com.alibaba.fastjson.JSONObject otherParam, String key, String value, List<BusinessField> businessFieldList) {
        if ("hospital".equals(key) || "photo".equals(key)) {
        	otherParam.put(key, value);
            return;
        }
        for (BusinessField businessField : businessFieldList) {
            List<SysDictitem> options = businessField.getOptions();
            if (businessField.getCode().equals(key)) {
                if (businessField.getType().equals("PULL") && CollectionUtils.isNotEmpty(options)) {
                    for (SysDictitem sysDictitem : options) {
                        if (sysDictitem.getCode().toString().equals(value)) {
                            otherParam.put(key, sysDictitem.getCode());
                            otherParam.put(key + "_name", sysDictitem.getName());
                        }
                    }
                } else {
                    otherParam.put(key, value);
                }
            }
        }
    }
	
	/**
	 * 获取社保公积金类型
	 *  @param accountId
	 *  @return 
	 *	@return 			: List<InsuranceTypeDto> 
	 *  @createDate  	: 2017年3月20日 下午4:13:52
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月20日 下午4:13:52
	 *  @updateAuthor  :
	 */
	private List<InsuranceTypeDto> findInsuranceType(Integer accountId,String beginDate,String insuranceLivingType) {
		List<InsuranceTypeDto> list = new ArrayList<InsuranceTypeDto>();
		InsuranceTypeDto insuranceTypeDto = null;
		SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
		spsAccountRatio.setAccId(accountId);
		spsAccountRatio.setIsdefaultEq("Y");
		spsAccountRatio.setLivingTypeEq(insuranceLivingType);
		List<SpsAccountRatio> spsAccountRatioList = spsAccountRatioService.findAll(spsAccountRatio);
		for(SpsAccountRatio r : spsAccountRatioList){
			insuranceTypeDto = new InsuranceTypeDto();
			BsArearatio ratio = bsArearatioService.findByPK(r.getRatioId());
			if(null != ratio && null != ratio.getBsArearatiovers()){
				BsArearatiover ver = bsArearatioService.getVerByPeriod(beginDate, ratio);
				if(null != ver){
					insuranceTypeDto.setCompanyRatio(ver.getCorpRatio());//公司缴费比例
				    insuranceTypeDto.setInsuranceName(bdInsuranceService.findByPK(ratio.getInsuranceId()).getName());//险种名称
				    insuranceTypeDto.setInsuranceTypeId(ratio.getInsuranceId());//险种ID
				    insuranceTypeDto.setPersonalRatio(ver.getPsnRatio());//个人缴费比例
				    insuranceTypeDto.setRatioId(r.getRatioId());//险种比例
				    insuranceTypeDto.setBeginDate(beginDate);
				    list.add(insuranceTypeDto);
				}
			}
		}
		return list;
	}
	
	/**
     * 验证开始时间 小于结束月份，  并且 结束月份 小于当前月份
     * @param startDate yyyy-MM
     * @param endDate   yyyy-MM
     * @return
     */
    private boolean valiteDate(String startDate, String endDate, Result result) {
    	if(StringUtils.isBlank(startDate)){
    		 result.setError("补缴开始时间不能为空!");
             result.setSuccess(false);
             return false;
    	}
    	if(StringUtils.isBlank(endDate)){
    		result.setError("补缴结束时间不能为空!");
            result.setSuccess(false);
            return false;
    	}
        String prefix1 = StringUtils.substring(startDate, 0, 7);
        String prefix2 = StringUtils.substring(endDate, 0, 7);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        try {
        	start = df.parse(prefix1 + "-00");
		} catch (Exception e) {
			 result.setError("补缴开始时间格式不正确!");
             result.setSuccess(false);
             return false;
		}
        Date end = null;
        try {
        	end = df.parse(prefix2 + "-00");
		} catch (Exception e) {
			 result.setError("补缴结束时间格式不正确!");
             result.setSuccess(false);
             return false;
		}
        if (start.getTime() > end.getTime()) {
            result.setError("补缴开始时间不能大于补缴结束时间!");
            result.setSuccess(false);
            return false;
        }
        String prefix3 = StringUtils.substring(DateUtil.getCurYearMonthStr(), 0, 7);
        Date now = DateUtil.parseDate(prefix3 + "-00", "yyyy-MM-dd");
        if (end.getTime() > now.getTime()) {
            result.setError("补缴结束时间不能大于当前时间!");
            result.setSuccess(false);
            return false;
        }
        return true;
    }

	/**
	 * 任务中心--获取编辑人员详细信息
	 *  @param cti
	 *  @param taskId
	 *  @return 
	 *  @createDate  	: 2017年9月26日 上午9:42:33
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月26日 上午9:42:33
	 *  @updateAuthor  	:
	 */
	@Override
	public List<BusinessField> findEditEmpDetail(ContextInfo cti, Integer taskId) {
        SpsTask spsTask = new SpsTask();
        spsTask.setTaskId(taskId);
        spsTask = spsTaskService.findByPK(spsTask);
        // 获取业务类型
    	com.alibaba.fastjson.JSONObject oldJson = JSON.parseObject(spsTask.getJson());
        Integer bstypeId = spsTask.getBstypeId();
        if(null != oldJson.getInteger("bstypeId")
    			&& BsType.INCREMENT_INSUR_ENTRY.getCode().equals(spsTask.getBstypeId())){
        	bstypeId =oldJson.getInteger("bstypeId");
    	}
        List<BusinessField> bdBusinessfieldList = this.getNeedField(bstypeId, spsTask.getAreaId());
//        if (spsTask.getBstypeId() == BsType.MODIFY_HOSPITAL.code()) {
//            String json = spsTask.getJson();
//            com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(json);
//            String df = object.get("hospital").toString();
//            com.alibaba.fastjson.JSONArray hospital = com.alibaba.fastjson.JSONObject.parseArray(df);
//            object.remove("hospital");
//            this.setDefaultValue(bdBusinessfieldList, object);
//            for (int i = 0; i < hospital.size(); i++) {
//                for (BusinessField businessField : bdBusinessfieldList) {
//                    if (businessField.getCode().equals("hospital" + (i + 1))) {
//                    	com.alibaba.fastjson.JSONObject hospitalJson = com.alibaba.fastjson.JSONObject.parseObject(hospital.get(i).toString());
//                        businessField.setDefaultValue(hospitalJson.getString("name"));
//                    }
//                }
//            }
//        } else {
            String json = spsTask.getJson();
            com.alibaba.fastjson.JSONObject object = JSON.parseObject(json);
            setDefaultValue(bdBusinessfieldList, object,spsTask.getBstypeId());
//        }
        return bdBusinessfieldList;
	}
	
	/**
	 * 获取配置字段
	 *  @param bsTypeId
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<BusinessField> 
	 *  @createDate  	: 2017年9月26日 上午9:53:54
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月26日 上午9:53:54
	 *  @updateAuthor  :
	 */
    public List<BusinessField> getNeedField(Integer bsTypeId, Integer areaId) {
        List<BusinessField> businessFieldList = null;
        // 查看附件 类型
        try {
            Integer[] types = new Integer[1];
            types[0] = bsTypeId;
            businessFieldList = bdBusinessfieldService.queryBusinessFields(areaId, types);
            for (int i=0;i<businessFieldList.size();i++) {
            	// 删除不需要的字段
            	if(bsTypeId.equals(BsType.NEW_INSURANCE.code())){
            		if("name".equals(businessFieldList.get(i).getCode())
                			|| "identity_code".equals(businessFieldList.get(i).getCode())
                			|| "identity_type".equals(businessFieldList.get(i).getCode())){
                		businessFieldList.remove(i);
                		i--;
                		continue;
                	}
            	}
                if (businessFieldList.get(i).getDefaultValue() == null) {
                	businessFieldList.get(i).setDefaultValue("");
                }
                if (businessFieldList.get(i).getType().equals("PULL")) {
                    List<SysDictitem> sysDictitemList = sysDictitemService.findByDictNameAndArea(businessFieldList.get(i).getCode() + "_doc", areaId);
                    businessFieldList.get(i).setOptions(sysDictitemList);
                }
            }
            return businessFieldList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessFieldList;
    }

    /**
     * 设置默认值
     *  @param bdBusinessfieldList
     *  @param object 
     *	@return 			: void 
     *  @createDate  	: 2017年9月26日 上午9:52:14
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月26日 上午9:52:14
     *  @updateAuthor  :
     */
    private void setDefaultValue(List<BusinessField> bdBusinessfieldList, com.alibaba.fastjson.JSONObject object,Integer bstypeId) {
        for (BusinessField businessField : bdBusinessfieldList) {
        	if("oldHos".equals(businessField.getCode())
        			|| "newHos".equals(businessField.getCode())
        			|| ("insuranceSalary".equals(businessField.getCode()) 
        					&& (BsType.INCREMENT_INSUR_ENTRY.code().equals(bstypeId) || BsType.NEW_INSURANCE.code().equals(bstypeId))) 
        			){
        		businessField.setPageIsHidden("HIDDEN");
        		continue;
        	}
            if (object.containsKey(businessField.getCode())) {
                if (businessField.getType().equals("PULL")) {
            		if (object.containsKey(businessField.getCode() + "_name")) {
                        businessField.setDefaultValue(object.get(businessField.getCode()).toString());
                    }
                } else {
                	businessField.setDefaultValue(object.get(businessField.getCode()).toString());
                }
            }
        }
    }
    
    /**
     * 企业微信用户同步
     *  @param txtMessage
     *  @return 
     *  @createDate  	: 2017年10月10日 下午1:33:40
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月10日 下午1:33:40
     *  @updateAuthor  	:
     */
    @Override
	public Result joinEmpWx(com.alibaba.fastjson.JSONObject txtMessage) {
		Result result = Result.createResult().setSuccess(true);
		result.setMsg("请求成功");
		try {
			log.info("企业微信请求参数===========messageType="+txtMessage.getInteger("messageType")+",jsonData=" + txtMessage.toJSONString() +",time="+new Date());
			/**
             * 消息类型
             * 0 权限改变 企业信息,部门信息和所有的成员需要同步
             * 1 失去权限 所有存在的成员的 权限清空
             * 2 用户改变 list中的用户需要新增或者修改
             * 3 部门改变 list中的部门需要新增或者修改
             * 4 用户删除 list中的成员被删除
             */
			// 获取权限信息
			com.alibaba.fastjson.JSONObject wxAuthInfo = com.alibaba.fastjson.JSONObject.parseObject(txtMessage.getString("wxAuthInfo"));
			String createCodes = "";
			if(null != wxAuthInfo && null != wxAuthInfo.get("createCodes")){
				createCodes = wxAuthInfo.getString("createCodes");
			}
			if(StringUtils.isNotBlank(createCodes) && !createCodes.contains("xfs_ss")){
				return result;
			}
			if(1 == txtMessage.getInteger("messageType")){
				Integer cpId = wxAuthInfo.getInteger("ssoCpId");
				// 根据企业ID更新所有移除人员信息
				CmEmployee cmEmployee = new CmEmployee();
				cmEmployee.setCpId(cpId);
				cmEmployee.setIsRemove(1);
				cmEmployeeDao.updateByCpId(null, cmEmployee);
			}else{
				// 获取人员列表
				com.alibaba.fastjson.JSONArray wxUserInfoList = com.alibaba.fastjson.JSONArray.parseArray(txtMessage.get("wxUserInfoList").toString());
				List<WxMessage> list = new ArrayList<>();	
				//获取同步锁
				//String id = com.alibaba.fastjson.JSONObject.parseObject(wxUserInfoList.get(0).toString()).getString("ssoCpId");
				//int flag = RedisUtil.getXLock(RedisKeyConstant.SAVECMEMPLOYEE+id,50000);
				//if(1 == flag){
					// 循环人员列表
					for (int i = 0; i < wxUserInfoList.size(); i++) {
						// 获取人员信息
						com.alibaba.fastjson.JSONObject wxUserInfo = com.alibaba.fastjson.JSONObject.parseObject(wxUserInfoList.get(i).toString());
						// 根据企业ID和人员ID  查询人员信息
						CmEmployee query = new CmEmployee();
						query.setWechatId(wxUserInfo.getString("id"));
						query.setCpId(Integer.valueOf(wxUserInfo.getString("ssoCpId")));
						query = cmEmployeeDao.finByWechatId(query);
						
						com.alibaba.fastjson.JSONObject jsonObject = null;
						if(null == query || StringUtils.isBlank(query.getJson())){
							jsonObject = new com.alibaba.fastjson.JSONObject();
						}else{
							jsonObject = com.alibaba.fastjson.JSONObject.parseObject(query.getJson());
						}
						CmEmployee cmEmployee = new CmEmployee();
						cmEmployee.setIsRemove(0);
						// 设置员工是否被移除
						// 判断是否是社保SAAS处理的人员
						if (null == query && (null == wxUserInfo.getString("appCodes") || !wxUserInfo.getString("appCodes").contains("xfs_ss"))) {
							continue;
						}else if(null != query && (null == wxUserInfo.getString("appCodes") || !wxUserInfo.getString("appCodes").contains("xfs_ss"))){
							cmEmployee.setIsRemove(1);
							cmEmployee.setEmpId(query.getEmpId());
							cmEmployeeDao.update(null, cmEmployee);
							continue;
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("id"))){
							cmEmployee.setWechatId(wxUserInfo.getString("id"));
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("ssoCpId"))){
							cmEmployee.setCpId(wxUserInfo.getInteger("ssoCpId"));
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("name"))){
							cmEmployee.setName(wxUserInfo.getString("name"));
							jsonObject.put("name", cmEmployee.getName());
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("mobile"))){
							cmEmployee.setMobile(wxUserInfo.getString("mobile"));
							jsonObject.put("mobile", cmEmployee.getMobile());
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("email"))){
							cmEmployee.setEmail(wxUserInfo.getString("email"));
							jsonObject.put("email", cmEmployee.getEmail());
						}
						if(StringUtils.isNotBlank(wxUserInfo.getString("depNames"))){
							com.alibaba.fastjson.JSONObject depJson = com.alibaba.fastjson.JSONObject.parseObject(wxUserInfo.getString("depNames"));
							if(StringUtils.isNotBlank(depJson.getString("xfs_ss"))){
								cmEmployee.setDepName(depJson.getString("xfs_ss"));
							}
						}
						if((null != query && StringUtils.isBlank(query.getHeadPhoto())) || null == query){
							if(StringUtils.isNotBlank(wxUserInfo.getString("avatar"))){
								cmEmployee.setHeadPhoto(wxUserInfo.getString("avatar"));
								jsonObject.put("photo", cmEmployee.getHeadPhoto());
							}
						}
						cmEmployee.setJson(jsonObject.toJSONString());
						cmEmployee.setDr(0);
						// 判断当前人员是否存在
						if(null == query){
							cmEmployee.setEntryTime(new Date());
							// 安装保存不发消息
							if(StringUtils.isNotBlank(createCodes) && createCodes.contains("xfs_ss")){
								cmEmployeeDao.save(null, cmEmployee);
								continue;
							}else{
								// 未激活
								if(4 == wxUserInfo.getInteger("status")){
									cmEmployee.setIsSend(0);
								}
								cmEmployeeDao.save(null, cmEmployee);
								// 新增员工并且激活状态  发送信息
								if (1 == wxUserInfo.getInteger("status")) {
									list.add(this.newCmEmployee(cmEmployee));
								}
							}
						}else{
							cmEmployee.setEmpId(query.getEmpId());
							cmEmployeeDao.update(null, cmEmployee);
							// 更新时  当前用户激活并且未发送信息  给企业微信发送一条信息
							if(1 == wxUserInfo.getInteger("status") && 0 == query.getIsSend()){
								list.add(this.newCmEmployee(cmEmployee));
								cmEmployee.setIsSend(1);
								cmEmployeeDao.update(null, cmEmployee);
							}
						}
					}
					// 释放同步锁
					//RedisUtil.delLock(RedisKeyConstant.SAVECMEMPLOYEE+id);
					// 消息推送
					if(list.size() > 0){
						System.out.println("============="+list.toString());
						String msg = sysMessageService.sendMessage(list,"xfs_ss");
						System.out.println("====="+msg);
					}
				//}
			}
		} catch (Exception e) {
			log.error(e);
			result.setSuccess(false);
			result.setMsg("系统出错请联系负责人");
			return result;
		}
		return result;
	}
    
    /**
     * 封装消息对象
     *  @param cmEmployee
     *  @return 
     *	@return 			: TextcardMessage 
     *  @createDate  	: 2017年11月9日 下午5:53:24
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年11月9日 下午5:53:24
     *  @updateAuthor  :
     */
    private TextcardMessage newCmEmployee(CmEmployee cmEmployee) {
    	// 给企业微信员工发信息收集消息
    	TextcardMessage textcardMessage = new TextcardMessage();
		textcardMessage.setTouser(cmEmployee.getWechatId());
		textcardMessage.getTextcard().setTitle("欢迎加入我们！");
		textcardMessage.getTextcard().setDescription("为了更好的服务每一位成员，公司会为每一个成员上社保，请您填写一下参保信息，HR姐姐会为您尽快办理！");
		textcardMessage.getTextcard().setUrl(Config.getProperty("core.home.host")+"/address");
		return textcardMessage;
	}

    /**
     * 根据企业微信ID和企业ID获取人员信息
     *  @param query
     *  @return
     *	@return 			: CmEmployee
     *  @createDate  	: 2017年10月10日 下午3:31:02
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月10日 下午3:31:02
     *  @updateAuthor  :
     */
    public CmEmployee finByWechatId(CmEmployee query){
        return cmEmployeeDao.finByWechatId(query);
    }


	/**
	 * 获取可以同步的员工信息
	 * @param cpId
	 * @param synEmpIds
	 * @return
	 */
	public List<CmEmployee> queryUnRelationEmps(Integer cpId,String[] synEmpIds){
		return cmEmployeeDao.queryUnRelationEmps(cpId,synEmpIds);
	}
}
