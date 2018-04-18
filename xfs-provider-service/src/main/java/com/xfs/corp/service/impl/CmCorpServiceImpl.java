package com.xfs.corp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xfs.aop.task.cs.TaskBotApi;
import com.xfs.common.constant.IStaticVarConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.BsChannelUserDao;
import com.xfs.base.dao.CsRegistHistoryDao;
import com.xfs.base.dao.SysUploadfileDAO;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsChannelUser;
import com.xfs.base.model.BsCompanyInfo;
import com.xfs.base.model.CsRegistHistory;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.BsCompanyInfoService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.bill.dao.SpsBillDao;
import com.xfs.bill.dao.SpsBillTempCorpDao;
import com.xfs.bill.model.SpsBill;
import com.xfs.bill.model.SpsBillTempCorp;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.bill.service.SpsBillService;
import com.xfs.bill.service.SpsBillTemplateService;
import com.xfs.bs.dto.CorpAccount;
import com.xfs.business.dao.SpsTaskDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Constant;
import com.xfs.common.util.MobileValidator;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.dto.CorpInfo;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.settlement.model.BlBalance;
import com.xfs.settlement.service.BlBalanceService;
import com.xfs.sp.dao.SpCmRelationDao;
import com.xfs.sp.dao.SpsAccountDao;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpCmRelationService;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sps.util.vo.Md5Util;
import com.xfs.sps.utils.ImportExcelUtil;
import com.xfs.ts.model.SpsTsPersonFlow;
import com.xfs.user.dao.SysFunctionDataDao;
import com.xfs.user.model.SysFunctionData;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysFunctionDataService;
import com.xfs.user.service.SysUserRoleService;
import com.xfs.user.service.SysUserService;
import com.xfs.user.service.UserService;


/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-8 14:10:00
 * @version 	: V1.0
 */
@Service
public class CmCorpServiceImpl implements CmCorpService,IStaticVarConstant {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmCorpServiceImpl.class);

	@Autowired
	private SpsSchemeDao spsSchemeDao;
	
	@Autowired
	private CmCorpDao cmCorpDao;
	@Autowired
	private BsAreaService bsAreaService;
	@Autowired
	private SpCmRelationDao spCmRelationDao;
	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
	private SpsAccountDao accountDao;
	@Autowired
	private SpCmRelationService relationService;
	@Autowired
	private CmEmployeeService cmEmployeeService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CmEmployeeDao cmEmployeeDao;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private BsAreaDao bsAreaDao;
	@Autowired
	private UserService userService;
	@Autowired
	private BsCompanyInfoService bsCompanyInfoService;
	@Autowired
	private SysUploadfileDAO sysUploadfileDAO;
	@Autowired
	private SpsTaskDao spsTaskDao;
	@Autowired
	private BsChannelUserDao channelUserDao;
	@Autowired
	private CsRegistHistoryDao registHistoryDao;
	@Autowired
	private BlBalanceService blBalanceService;
	@Autowired
	private SpsBillService spsBillService;
	@Autowired
	private SpsBillDao spsBillDao;
	@Autowired
	private SpServiceService spServiceService;
	@Autowired
	private SpCmRelationService spCmRelationService;

    @Autowired
    private SpsBillTemplateService spsBillTemplateService;

    @Autowired
    private SpsBillTempCorpDao spsBillTempCorpDao;
	@Autowired
	private SysFunctionDataDao sysFunctionDataDao;

	public int save(ContextInfo cti, CmCorp vo) {
		return cmCorpDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, CmCorp vo) {
		return cmCorpDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, CmCorp vo) {
		return cmCorpDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, CmCorp vo) {
		return cmCorpDao.update(cti, vo);
	}


	public List<CmCorp> findAll(CmCorp vo) {

		List<CmCorp> datas = cmCorpDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/11 16:09:14

	@Override
	public CmCorp findByPK(Integer cpId) {
		CmCorp corp = new CmCorp();
		corp.setCpId(cpId);
		return cmCorpDao.findByPK(corp);
	}

	/**
	 * 根据corpId 查询企业数据
	 */
	@Override
	public CmCorp findOneByCorpId(CmCorp vo) {
		CmCorp datas = (CmCorp) cmCorpDao.findOneByCorpId(vo);
		return datas;
	}

	/**
	 * 根据corpId 和 spid 查询企业数据
	 * @param vo
	 * @return
     */
	@Override
	public CmCorp findOneByCorpIdAndSpId(CmCorp vo) {
		CmCorp datas = (CmCorp) cmCorpDao.findOneByCorpId(vo);
		return datas;
	}


	@Override
	public Result insertCorp(ContextInfo cti,CmCorp vo) {
		Result result = Result.createResult().setSuccess(false);
		vo.setCreateDt(new Date());
		// 根据企业名称查询企业名称是否重复
		Integer intsss = cmCorpDao.findCorpByCorpName(vo);
		if (1 > intsss) {
			Integer ints = this.insert(cti, vo);// 企业表
			SpCmRelation spCmRelation = new SpCmRelation();
			if (vo.getCpId() != null) {
				spCmRelation.setCreateBy(vo.getCreateBy());
				spCmRelation.setCreateDt(vo.getCreateDt());
				spCmRelation.setCpId(vo.getCpId());
				spCmRelation.setSpId(vo.getSpId());
				spCmRelation.setAreaId(vo.getAreaId());
				spCmRelation.setInsuranceType(vo.getInsuranceType());
				spCmRelation.setRegNum(vo.getRegNum());
				spCmRelation.setRegNumpass(vo.getRegNumpass());
				spCmRelation.setRegUsbkeypass(vo.getRegUsbkeypass());
				Integer intss = spCmRelationDao.insert(cti, spCmRelation);// 企业关系表
				if (ints > 0 && intss > 0) {
					result.setSuccess(true);
				} else {
					result.setSuccess(false);
					result.setError("保存失败！");
				}
			} else {
				result.setSuccess(false);
				result.setError("保存失败！");
			}
		} else {
			result.setSuccess(false);
			result.setError("企业名称重复！");
		}
		return result;
	}


	private  Map<String,BigDecimal> getRange(SpsAccount spsAccount){
		Map<String, BigDecimal> result = new HashMap<>();
		return  result;
	}
	

	// 根据企业ID 关系ID 删除企业表 关系表中数据
	@Override
	public Result removeCorp(ContextInfo cti,CmCorp vo) {
		Result result = Result.createResult().setSuccess(false);
		SpCmRelation spCmRelation = new SpCmRelation();
		spCmRelation.setCpId(vo.getCpId());
		Integer delcorp = this.remove(cti, vo);
		if (delcorp > 0) {
			Integer delsp = spCmRelationDao.removeByCpId(cti, spCmRelation);
			if (delsp > 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} else {
			result.setSuccess(false);
		}
		return result;
	}

    /**
     * 删除客户 客户关系和任务单方案等关联的数据
     * @param cti
     * @param vo
     * @return
     */
	@Override
	public Result removeCorpNew(ContextInfo cti,CmCorp vo) {
		Result result = Result.createResult().setSuccess(false);
        if(vo.getCpId() == null || cti ==null){
            return  result ;
        }
		SpCmRelation spCmRelation = new SpCmRelation();
        SpsTask spsTask = new SpsTask();
        spsTask.setSpId(cti.getOrgId());
        spsTask.setCpId(vo.getCpId());
        spsTask.setType("TODO");
        Integer taskConut  = spsTaskDao.countNoCompletedTask(spsTask);
        //当用户和客户之间的任务单存在未完成的时候不允许删除
        if(taskConut > 0){
            return  result ;
        }
        //当客户么有开通商城 删除该客户表的数据  表：cm_corp
        spCmRelation.setCpId(vo.getCpId());
        CmCorp cmCorp = cmCorpDao.findByPK(vo);
        if(!"MALL".equals(cmCorp.getSource()) && !"Y".equals(cmCorp.getOpenmall())){
            this.remove(cti, vo);
            //删除客户下面的员工
            CmEmployee cmEmployee = new CmEmployee();
            cmEmployee.setCpId(vo.getCpId());
            cmEmployee.setDr(1);
            cmEmployeeDao.updateByCpId(cti, cmEmployee);
        }
        //删除和客户之间的关系
        spCmRelation.setSpId(cti.getOrgId());
        spCmRelationDao.delRELATIONByCpIdandSpId(cti, spCmRelation);
        //删除方案
        SpsScheme spsScheme = new SpsScheme();
        spsScheme.setSpId(cti.getOrgId());
        spsScheme.setCpId(vo.getCpId());
        spsSchemeDao.removeBySpIdAndCpId(cti, spsScheme);
        //删除用户和客户下面的任务单
        spsTaskDao.removeBySpIdAndCpId(cti, spsTask);
        //删除都成功了 不管改变了几条 只要么有异常都算成功
        result.setSuccess(true);
		return result;
	}

    /**
     * 删除客户之前查信客户的相关的信息 luyong 删除校验
     * @param cti
     * @param vo
     * @return
     */
	@Override
	public Result removeCorpChek(ContextInfo cti,CmCorp vo) {
		Result result = Result.createResult().setSuccess(false);
		SpCmRelation spCmRelation = new SpCmRelation();
		spCmRelation.setCpId(vo.getCpId());
        SpsTask spsTask = new SpsTask();
        spsTask.setSpId(cti.getOrgId());
        spsTask.setCpId(vo.getCpId());
        spsTask.setType("TODO");
        Integer taskConut  = spsTaskDao.countNoCompletedTask(spsTask);
        //当用户和客户之间的任务单存在未完成的时候不允许删除
        if(taskConut > 0){
            result.setSuccess(false);
            result.setDataValue("taskCont",taskConut);
            return  result ;
        }
        SpsBill spsBill = new SpsBill();
        spsBill.setCpId(vo.getCpId());
        spsBill.setDr(0);
        spsBill.setSpId(cti.getOrgId());
        Integer billChekCount  = spsBillDao.countNoSureBill(spsBill);
        if(billChekCount > 0){
            result.setSuccess(true);
            result.setDataValue("billChekCount",billChekCount);
            return  result ;
        }
        result.setSuccess(true);
		return result;
	}


	@Override
	public void saveCorp(ContextInfo cti,CmCorp cmCorp, SpCmRelation cmRelation) {
		cmCorp.setUnComplete("N");
		if(cmCorp.getCpId() == null){
			//新增啊,先插入企业再插入关系
			cmCorp.setCreateDt(new Date());
			cmCorpDao.insert(cti, cmCorp);
			//创建企业默认方案
			createCorpDefaultScheme(cti,cmCorp);
			//创建企业与服务商关系
			createSpCmRelation(cti,cmRelation,cmCorp,"SAAS",2);
		}else{
			cmCorpDao.save(cti, cmCorp);
		}
		//保存后  添加当前用户的权限
		addUserFundData(cti,cmCorp,cti.getUserId(),cti.getUserType());

		//保存后  添加 客服权限
		addUserFundData(cti,cmCorp,cmCorp.getCollaborator(),CMCORPTYPE_SERVICE);

        //增加默认模板
      //  addCorpBillTemp(cti, cmCorp);

	}

    /**
	 *  创建企业默认方案
	 *  @param   cti
	 *  @param   cmCorp
	 *	@return 			: void
	 *  @createDate  	: 2016-12-26 14:11
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-26 14:11
	 *  @updateAuthor  :
	 */
	private void createCorpDefaultScheme(ContextInfo cti,CmCorp cmCorp){
		SpsScheme spsScheme=new SpsScheme();
		spsScheme.setCpId(cmCorp.getCpId());
		spsScheme.setSpId(cmCorp.getSpId());
		if(!StringUtils.isBlank(cmCorp.getShortName()) && !cmCorp.getShortName().equals("null"))
			spsScheme.setName(cmCorp.getShortName() + "默认方案");
		else
			spsScheme.setName(cmCorp.getName() + "默认方案");
		if(null!=cmCorp.getCpAddressArea()){
			BsArea area = bsAreaService.getRedisAreaMap().get(cmCorp.getCpAddressArea());
			spsScheme.setAreaId(area.getBelongCity());
		}

		spsScheme.setState("USE");
		spsScheme.setSchemeType("DIY");//自己处理
		spsScheme.setIsdefault("Y");
		spsScheme.setIspackage("N");
		spsScheme.setDr(0);
		spsScheme.setBillType("ADVANCE_CHARGE");
		spsSchemeDao.save(cti, spsScheme);
	}

	/**
	 *  创建服务商与企业关系
	 *  @param   cti
	 *  @param   cmRelation
	 *  @param   cmCorp
	 *	@return 			: void
	 *  @createDate  	: 2016-12-26 14:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-26 14:16
	 *  @updateAuthor  :
	 */
	private void createSpCmRelation(ContextInfo cti,SpCmRelation cmRelation,CmCorp cmCorp,String scorue,Integer contractType){
		cmRelation.setCpId(cmCorp.getCpId());
		cmRelation.setSource(scorue);
		cmRelation.setContractType(contractType); // 合同类型
		spCmRelationDao.insert(cti, cmRelation);
	}

	/**
	 *  增加用户数据权限
	 *  @param   cti
	 *  @param   cmCorp
	 *  @param   userId
	 *  @param   userType
	 *	@return 			: void
	 *  @createDate  	: 2016-12-26 15:54
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-26 15:54
	 *  @updateAuthor  :
	 */
	private void addUserFundData(ContextInfo cti,CmCorp cmCorp,Integer userId,String userType){
		if(null!= userId){
			SysFunctionData sysFunctionData = new SysFunctionData();
			sysFunctionData.setUserId(userId);
			sysFunctionData.setUserType(userType);
			List<SysFunctionData> functionDatas = sysFunctionDataService.findAll(sysFunctionData);
			if(null!=functionDatas && functionDatas.size()>0){
				if(1 == functionDatas.size() && "Y".equals(functionDatas.get(0).getIsAll())){
					//有 所有企业权限，不处理
				}else{
					//判断 有没有这个企业的 权限
					boolean flag = false;
					for(int i=0;i<functionDatas.size();i++){
						SysFunctionData obj = functionDatas.get(i);
						//判断权限是否相等，改为equal
						if(cmCorp.getCpId().equals(obj.getDataId())){
							flag = true;
							break;
						}

					}
					if(!flag){//没有权限 ，添加
						SysFunctionData curVo = new SysFunctionData();
						curVo.setDataType(Constant.DATATYPE);
						curVo.setUserId(userId);
						curVo.setUserType(userType);
						curVo.setDataId(cmCorp.getCpId());
						curVo.setIsAll("N");
						curVo.setDr(0);
						sysFunctionDataService.insert(cti, curVo);
					}
				}
			}else{
				SysFunctionData curVo = new SysFunctionData();
				curVo.setDataType(Constant.DATATYPE);
				curVo.setUserId(userId);
				curVo.setUserType(userType);
				curVo.setDataId(cmCorp.getCpId());
				curVo.setIsAll("N");
				curVo.setDr(0);
				sysFunctionDataService.insert(cti, curVo);
			}

			//判断是否 有 客服角色，没有就添加
			SysUserRole roleVo = new SysUserRole();
			roleVo.setUserId(userId);
			roleVo.setRoleId(SysRole.SAAS_C_SERVCIE);//4:saas客服
			List<SysUserRole> roleList = sysUserRoleService.findAll(roleVo);
			if(null == roleList || roleList.size()<1){
				sysUserRoleService.insert(cti, roleVo);
			}
		}
	}

	@Override
	public Result editCorp(ContextInfo cti,Integer cid, Integer orgId) {
		Result result = Result.createResult();

		// 公司信息
		CmCorp cmcorp = findByPK(cid);
		
		SysUser vo = new SysUser();
		vo.setUserId(cmcorp.getCollaborator());
		SysUser user = sysUserService.findByPK(vo);

		result.setDataValue("user", user);
		result.setDataValue("cm", cmcorp);
		
		/*1  */
		//查社保人数
		 Map<String,Object> insMap = new HashMap<String,Object>();
		   insMap.put("spId", orgId);
		   insMap.put("state", "INSURANCE");
		   List<Map<String,Object>>  insList= cmEmployeeService.queryInsurNumBySpId(insMap);
		   
		   result.setDataValue("insList", insList);
		 //查公积金人数
		   Map<String,Object> insMap1 = new HashMap<String,Object>();
		   insMap1.put("spId", orgId);
		   insMap1.put("state", "FUND");
		   List<Map<String,Object>>  insList1= cmEmployeeService.queryInsurNumBySpId(insMap1);
		   result.setDataValue("insList1", insList1);
		/*2*/

		SpCmRelation relationCOnfition = new SpCmRelation();
		relationCOnfition.setCpId(cid);
		relationCOnfition.setSpId(orgId);
		SpCmRelation cmRelation = relationService.findAll(relationCOnfition).get(0);

		result.setDataValue("cmRelation", cmRelation);
		//非商城客户  判断 企业账号是否已存，存在时候 手机号不能编辑
		String isExistsAccount = "0";
		if(StringUtils.isNotBlank(cmcorp.getMobile()) && !"MALL".equals(cmRelation.getSource())){
			SysUser customerVo = new SysUser();
			customerVo.setMobile(cmcorp.getMobile());
			customerVo.setUserType("CUSTOMER");
			customerVo.setOrgId(cmcorp.getCpId());
			List<SysUser> accounts =sysUserService.findAll(customerVo);
			if(null!=accounts && accounts.size()>0){
				isExistsAccount = "1";
			}
		}
		result.setDataValue("isExistsAccount", isExistsAccount);

		return result;
	}

	/**
	 * 
	 * 
	 * 检查公司名称是否重复
	 * 
	 * 检查社保库和公积金的库是否为同一个
	 * 
	 */
	@Override
	public Result checkCorpInfo(CmCorp corp) {
		Result result = Result.createResult().setSuccess(false);
         //如果是新建
		if(corp.getCpId() == null){
			//新建的公司姓名，要跟数据库中判断是否重复
			CmCorp cmCorp =  cmCorpDao.findCorpByName(corp.getCorpName(),corp.getSpId());
			if(cmCorp != null && cmCorp.getCpId() != null ){
				result.setDataValue("cmCorp", cmCorp);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		}else{
			//如果是更新
			CmCorp cmCorp  = cmCorpDao.findCorpByName(corp.getCorpName(),corp.getSpId(),corp.getCpId());
			if(cmCorp != null && cmCorp.getCpId() != null ){
				result.setDataValue("cmCorp", cmCorp);
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		}
		return result;
	}

	@Override
	public Result readImportFileInfo(Integer fileId)  throws Exception{
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
		//查询 excel中 sheet页 级 标题行
		Map<String, Object> sheetData = readExcelTitle(filePath);
		if(sheetData != null && !sheetData.isEmpty()){
			result.setDataValue("sheetData",sheetData);
			result.setSuccess(true);
		}else{
			result.setError("没有可用的内容");
		}
		return result;
	}

	@Override
	public Result readTemplateRealtion(Integer fileId,String sheetName) throws Exception {
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
		//根据 业务类型 查询 字段信息
		List<Map<String,String>> sysfields = new ArrayList<Map<String,String>>();
		sysfields.add(getCorpFieldMap());

		Map<String,Map<String, Map<String, Object>>> resObj = ImportExcelUtil.readExcelTitle(filePath, sysfields, null, null, null,sheetName,"*");
		result.setDataValue("sheetData",resObj);
//	 	 result.setDataValue("isShowSelectSheet", resObj.size()<=1?"0":"1");先去掉这个，如果只有一个sheet时  显示默认选中

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
	public Map<String, Object> readExcelTitle(String filePath) throws Exception {
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
					Map<String,Object> sheetMap = new HashMap<String,Object>();
					int corpIndex = 0;
					for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
						String cellContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
						if (StringUtils.isNotBlank(cellContent)) {
							titleList.add(cellContent);
						}
					}
					if (null != titleList && titleList.size() > 0) {
						sheetMap.put("titleList",titleList);
					}
					//不存在的企业名称
					List<String> noExistCorp = new ArrayList<String>();

					//sheet格式没问题则返回
					if(sheetMap != null && !sheetMap.isEmpty()){
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
	 * @return
	 */
	private Map<String,String> getCorpFieldMap(){
		Map<String,String> fieldMap = new HashMap<>();
		fieldMap.put("客户名称","corpName");
		fieldMap.put("客户简称","shortName");
		fieldMap.put("客户联系人","contactPsn");
		fieldMap.put("手机号码","mobile");
		fieldMap.put("邮箱","mail");
//		fieldMap.put("机构客服经理","collaborator");
		fieldMap.put("企业所在地区","cpAddressAreaId");
		fieldMap.put("企业详细地址","cpAddress");
//		fieldMap.put("社保缴纳方式","insuranceType");
//		fieldMap.put("社保库名称","insuranceaccountId");
//		fieldMap.put("社保开户地址","areaId1");
//		fieldMap.put("工伤比例","inCorp");
//		fieldMap.put("社保登记证号","regNum1");
//		fieldMap.put("社保登记口令","regNumpass1");
//		fieldMap.put("社保登记证证书密码","regUsbkeypass");
//		fieldMap.put("社保托收日","collectionDay1");
//		fieldMap.put("公积金缴纳方式","fundType");
//		fieldMap.put("公积金库名称","fundaccountId");
//		fieldMap.put("公积金开户地址","areaId2");
//		fieldMap.put("公积金企业比例","corption");
//		fieldMap.put("公积金个人比例","psntion");
//		fieldMap.put("公积金账号","regNum2");
//		fieldMap.put("公积金密码","regNumpass2");
//		fieldMap.put("公积金托收日","collectionDay2");
//		fieldMap.put("收费方式","chargeMode");
//		fieldMap.put("单价","servicePrice");
//		fieldMap.put("账单日","billDay");
//		fieldMap.put("失业比例","unemploymentRate");//失业比例
		return fieldMap;
	}
	@Override
	public Result importCorpData(ContextInfo cti,Integer fileId, String selectContent,String sheetName) {
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
					colNameMap.put(itemArr[0].trim(), itemArr[1].trim());
				}
			}
		}

		Map<String,Map<String, String>>  sheet_orm = new HashMap<String,Map<String, String>>();
		sheet_orm.put(sheetName, colNameMap);
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
		Map<Integer, Map<String, String>> resObj = null;
		try {
			resObj = ImportExcelUtil.importExcelGetLastCell(filePath, getCorpFieldMap(), sheet_orm);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("读取excel异常！");
		}

		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		boolean isError = false;

		try {

//			if (colNameMap.get("*企业名称")==null
//					|| colNameMap.get("*企业简称")==null
//					|| colNameMap.get("*客户联系人")==null
//					|| colNameMap.get("*手机号")==null
//					|| colNameMap.get("*邮箱")==null
//					|| colNameMap.get("*客服经理")==null
//					|| colNameMap.get("*企业详细地址")==null
//					|| colNameMap.get("*社保缴纳方式")==null
//					|| colNameMap.get("社保库")==null
//					) {
//				result.setError("模板错误，请下载最新模板！");
//				result.setSuccess(false);
//				return result;
//			}
			// 区域
			BsArea bss = new BsArea();

			Map<String, Integer> bsAreaNameMap = new HashMap<String, Integer>();
			Map<String, Integer> bsAreaFullNameMap = new HashMap<String, Integer>();
			List<BsArea> lists = bsAreaService.findAll(bss);
			for (BsArea bsArea : lists) {
				bsAreaNameMap.put(bsArea.getName(), bsArea.getAreaId());
			}
			for (BsArea bsArea : lists) {
				bsAreaFullNameMap.put(bsArea.getFullname(), bsArea.getAreaId());
			}
			//查询当前服务商下所有企业联系人手机号
			Map<String, String> spExistMobileMap = new HashMap<String, String>();
			List<Map<String,Object>> spExistMobileList = cmCorpDao.findExistMobileBySpId(cti.getOrgId());
			for (Map<String, Object> map : spExistMobileList) {
				if(null == map.get("mobile") || StringUtils.equals("", map.get("mobile").toString()))
					continue;
				spExistMobileMap.put(map.get("mobile").toString(), map.get("sp_id").toString());
			}
			//导入数据部分 创建一个map来存 客户手机号
			Map<String, String> spImportMobileMap = new HashMap<String, String>();
			//社保大库
//			HashMap<String, SpsAccount> commonAccountMap = new HashMap<String,SpsAccount>();
//			SpsAccount account = new SpsAccount();
//			account.setAccType("COMMON");
//			account.setInsuranceFundType("INSURANCE");
//			List<SpsAccount> accounts = accountService.findAll(account);
//			for(SpsAccount ac: accounts){
//				commonAccountMap.put(ac.getName(), ac);
//			}
//
//			//公积金大库
//			HashMap<String, SpsAccount> singleAccountMap = new HashMap<String,SpsAccount>();
//			SpsAccount conditionAccount = new SpsAccount();
//			conditionAccount.setAccType("COMMON");
//			conditionAccount.setInsuranceFundType("FUND");
//			List<SpsAccount> fundAccounts = accountService.findAll(conditionAccount);
//			for(SpsAccount ac: fundAccounts){
//				singleAccountMap.put(ac.getName(), ac);
//			}
			//校验excel 是否有重复数据
			Map<String,String> excelCorpNameMap = new HashMap<String,String>();

			String tempName ="";

			// 处理内容行
			for (Integer key:resObj.keySet()) {
				Map row = resObj.get(key);
				if (null == row) {
					continue;
				}

				// 处理列
				Map<String, String> curMap = new HashMap<String, String>();
				String error = "";
				String corpName="";
				curMap.put("row", key.toString());

				// 校验 当前行中每一列数据

				//公司名称
				if (null == row.get("corpName") || "".equals(row.get("corpName").toString().replaceAll(" ", "").trim())) {
					error += "客户名称为空!<br>";
					corpName="";
				} else {
					corpName = row.get("corpName").toString().replaceAll(" ", "").trim();
					//String  corpName = row.get("corpName").toString().replaceAll(" ", "").trim();
					String regEx="[`~!@#$%^&*+=|{}':;',//[//].<>/?~！@#￥%……&*+|{} ‘；：”“’。，、？]";
					Pattern   p   =   Pattern.compile(regEx);
					Matcher   m   =   p.matcher(corpName);
					if( m.find()){
						error += "客户名称包含特殊字符!<br>";
					}
					//校验客户名称时  查询 所有服务商下所以客户
					CmCorp cmCorp = cmCorpDao.findCorpByName(corpName,cti.getOrgId());

					if((cmCorp != null && cmCorp.getCpId() != null ) || corpName.equals(tempName)){
						error += "客户名称重复!<br>";
					}else if(excelCorpNameMap.containsKey(corpName)){
						error += "Excel中客户名称重复!<br>";
					}else{
						curMap.put("corpName", row.get("corpName").toString().replaceAll(" ", "").trim());
					}
					//curMap.put("name",corpName);
				}
				curMap.put("name",corpName);
				excelCorpNameMap.put(corpName, "");

				//公司简称
				
				if (null == row.get("shortName") || "".equals(row.get("shortName").toString().replaceAll(" ", "").trim())) {					
					error += "客户简称为空!<br>";
				} else {
					String shortName=(String) row.get("shortName");
					String shortNamelength=com.xfs.common.util.StringUtils.getUtfStr(shortName);
					if(shortNamelength.length()>6){
						error += "客户简称不能超过6位!<br>";
					}else{
					curMap.put("shortName", row.get("shortName").toString().replaceAll(" ", "").trim());
					}
				}


				//客户联系人
				if (null == row.get("contactPsn") || "".equals(row.get("contactPsn").toString().replaceAll(" ", "").trim())) {
//					error += "客户联系人为空!<br>";
				} else {
					curMap.put("contactPsn", row.get("contactPsn").toString().replaceAll(" ", "").trim());
				}

				//手机
				if (null == row.get("mobile") || "".equals(row.get("mobile").toString().replaceAll(" ", "").trim())) {
//					error += "手机号为空!<br>";
				} else {
					if (!MobileValidator.isMobileNO(row.get("mobile").toString().replaceAll(" ", "").trim())) {
						error += "手机号码格式不正确!<br>";
					} else {
						String impMobile = row.get("mobile").toString().replaceAll(" ", "").trim();
						//1先和服务商已有客户手机号进行校验
						if(null != spExistMobileMap.get(impMobile)){
							error += "导入客户联系人手机号已经存在!<br>";
						}
						//2 在和导入的数据进行比对 如果没有就存入比对map
						if(null != spImportMobileMap.get(impMobile)){
							error += "导入客户数据中中该手机号已存在!<br>";
						}else{
							spImportMobileMap.put(impMobile, "");
							curMap.put("mobile", impMobile);
						}
					}
				}

				//邮箱
				
				if (null == row.get("mail") || "".equals(row.get("mail").toString().replaceAll(" ", "").trim())) {
//					error += "邮箱为空!<br>";
				} else {
					String mail=(String) row.get("mail");
					if(com.xfs.common.util.StringUtils.isEmail(mail)){
					curMap.put("mail", row.get("mail").toString().replaceAll(" ", "").trim());
					}else{
						error += "邮箱格式不正确!<br>";
					}
					
				}

				//客户经理
//				if (null == row.get("collaborator") || "".equals(row.get("collaborator").toString().replaceAll(" ", "").trim())) {
////					error += "客户经理为空!<br>";
//				} else {
//					//TODO: 改为了int类型
////					curMap.put("collaborator", row.get("collaborator").toString().replaceAll(" ", "").trim());
//				}

				//客户所在地区
				if (null == row.get("cpAddressAreaId") || "".equals(row.get("cpAddressAreaId").toString().replaceAll(" ", "").trim())) {
//					error += "客户所在地区为空!<br>";
				} else {
					String areaName = row.get("cpAddressAreaId").toString().replaceAll(" ", "").trim();
					if (null != bsAreaNameMap.get(areaName)) {
						curMap.put("cpAddressAreaId", bsAreaNameMap.get(areaName).toString());
					} else if(null != bsAreaFullNameMap.get(areaName)){
						curMap.put("cpAddressAreaId", bsAreaFullNameMap.get(areaName).toString());
					}else {
//						error += "未找到该参保地区!<br>";
					}
				}

				//客户详细
				if (null == row.get("cpAddress") || "".equals(row.get("cpAddress").toString().replaceAll(" ", "").trim())) {
//					error += "客户详细地址为空!<br>";
				} else {
					curMap.put("cpAddress", row.get("cpAddress").toString().replaceAll(" ", "").trim());
				}


				//社保缴纳方式
//				if (null == row.get("insuranceType") || "".equals(row.get("insuranceType").toString().replaceAll(" ", "").trim())) {
////					error += "社保缴纳方式为空!<br>";
//				} else {
//					String  insuranceTypeName =  row.get("insuranceType").toString().replaceAll(" ", "").trim();
//					if("社保大库".equals(insuranceTypeName)){
//						curMap.put("insuranceType", "COMMON");
//					}else if("社保小库".equals(insuranceTypeName)){
//						curMap.put("insuranceType", "SINGLE");
//					}else{
//						error += "社保缴纳方式不正确!<br>";
//					}
//				}

				//社保库
//				if("COMMON".equals(curMap.get("insuranceType"))){
//					if (null == row.get("insuranceaccountId") || "".equals(row.get("insuranceaccountId").toString().replaceAll(" ", "").trim())) {
////						error += "社保大库为空!<br>";
//					} else {
//						String kuName = row.get("insuranceaccountId").toString().replaceAll(" ", "").trim();
//						SpsAccount va = commonAccountMap.get(kuName);
//						if( va != null ){
//							curMap.put("insuranceaccountId",va.getAccId().toString());
//						}else {
//							error += "请填写正确的社保大库!<br>";
//						}
//					}
//				}
				//社保开户地址
//				if("SINGLE".equals(curMap.get("insuranceType"))){
//
//					if (null == row.get("areaId1") || "".equals(row.get("areaId1").toString().replaceAll(" ", "").trim())) {
//						error += "社保开户地址为空!<br>";
//					} else {
//						String areaName = row.get("areaId1").toString().replaceAll(" ", "").trim();
//						if (null != bsAreaNameMap.get(areaName)) {
//							curMap.put("areaId1", bsAreaNameMap.get(areaName).toString());
//							SpsAccount  sps =new SpsAccount();
//							sps.setAreaId(bsAreaNameMap.get(areaName));
//							sps.setInsuranceFundType("INSURANCE");
//							Map<String ,BigDecimal > mm = new HashMap<>();
//							mm = getRange(sps);
//							String inCorp = row.get("inCorp") == null?null:row.get("inCorp").toString().replace("","").trim();
//							
//							String unemploymentRate = row.get("unemploymentRate") == null?null:row.get("unemploymentRate").toString().replace("","").trim();
//							//String inCorp = row.get("inCorp") == null?null:row.get("inCorp").toString().replace("","").trim();
////							if(mm.get("minCorpRatio").equals(mm.get("maxCorpRatio"))){ unemploymentRate
////								curMap.put("inCorp",mm.get("minCorpRatio").toString());
////							}else {
////								if(StringUtils.isNotBlank(inCorp)){
////									BigDecimal corpRtion = null;
////									try {
////										corpRtion = com.xfs.common.util.StringUtils.toBigDecimal(inCorp);
////										if(corpRtion>=mm.get("minCorpRatio")&&corpRtion<=mm.get("maxCorpRatio")) {
////											curMap.put("inCorp",corpRtion.toString());
////										} else {
////											error+="工伤比例填写错误!<br>";
////										}
////									} catch (NumberFormatException e) {
////										error+="工伤比例格式错误，需要为数字类型!<br>";
////									}
////								}else {
////									error+="未填写工伤比例!<br>";
////								}
////							}
//							if(StringUtils.isNotBlank(inCorp)){
//								BigDecimal corpRtion = null;
//								try {
//									corpRtion = com.xfs.common.util.StringUtils.toBigDecimal(inCorp);
//									if(corpRtion.compareTo(mm.get("minCorpRatio"))>=0&&corpRtion.compareTo(mm.get("maxCorpRatio"))<=0) {
//										curMap.put("inCorp",corpRtion.toString());
//									} else {
//										error+="工伤比例填写错误!<br>";
//									}
//								} catch (NumberFormatException e) {
//									error+="工伤比例格式错误，需要为数字类型!<br>";
//								}
//							}else {
//								error+="选择社保小库时工伤比例必录!<br>";
//							}
//							
//							//校验失业比例
//							if(StringUtils.isNotBlank(unemploymentRate)){
//								BigDecimal uneRate = null;
//								try {
//									uneRate = com.xfs.common.util.StringUtils.toBigDecimal(unemploymentRate);
//									
//									String big="1";
//									String mix="0";
//									BigDecimal bige=com.xfs.common.util.StringUtils.toBigDecimal(big);
//									BigDecimal mixe=com.xfs.common.util.StringUtils.toBigDecimal(mix);
//									//if(uneRate.compareTo(mm.get("minCorpRatio"))>=0&&uneRate.compareTo(mm.get("maxCorpRatio"))<=0) {
//									
//									if(uneRate.compareTo(mixe)>=0&&uneRate.compareTo(bige)<=0){
//										
//										curMap.put("unemploymentRate",uneRate.toString());
//									} else {
//										error+="失业比例填写错误!<br>";
//									}
//								} catch (NumberFormatException e) {
//									error+="失业比例格式错误，需要为数字类型!<br>";
//								}
//							}else {
//								error+="选择社保小库时失业比例必录!<br>";
//							}
//							
//							
//							
//							
//						} else if(null != bsAreaFullNameMap.get(areaName)){
//							curMap.put("areaId1", bsAreaFullNameMap.get(areaName).toString());
//						}else {
//							error += "未找到该社保开户地址!<br>";
//						}
//					}
//
//					//社保登记号
//					if (null == row.get("regNum1") || "".equals(row.get("regNum1").toString().replaceAll(" ", "").trim())) {
//					} else {
//						curMap.put("regNum1", row.get("regNum1").toString().replaceAll(" ", "").trim());
//					}
//
//
//					//社保登记口令
//					if (null == row.get("regNumpass1") || "".equals(row.get("regNumpass1").toString().replaceAll(" ", "").trim())) {
//					} else {
//						curMap.put("regNumpass1", row.get("regNumpass1").toString().replaceAll(" ", "").trim());
//					}
//
//					//社保登regUsbkeypass
//					if (null == row.get("regUsbkeypass") || "".equals(row.get("regUsbkeypass").toString().replaceAll(" ", "").trim())) {
//					} else {
//						curMap.put("regUsbkeypass", row.get("regUsbkeypass").toString().replaceAll(" ", "").trim());
//					}
//
//					//社保托收日  ,该字段已 去掉
////					if (null == row.get("collectionDay1") || "".equals(row.get("collectionDay1").toString().replaceAll(" ", "").trim())) {
////					} else {
////						curMap.put("collectionDay1", row.get("collectionDay1").toString().replaceAll(" ", "").trim());
////						Integer d = Integer.valueOf(row.get("collectionDay1").toString().replaceAll(" ", "").trim());
////						if(d>31 || d<1 ){
////							error += "社保托收日应在1到31之间!<br>";
////						}
////					}
//					//unemploymentRate  失业比例
//					if (null == row.get("unemploymentRate") || "".equals(row.get("unemploymentRate").toString().replaceAll(" ", "").trim())) {
//						error += "选择社保小库时失业比例必录!<br>";
//					} else {
//						curMap.put("unemploymentRate", row.get("unemploymentRate").toString().replaceAll(" ", "").trim());
//						BigDecimal d = com.xfs.common.util.StringUtils.toBigDecimal(row.get("unemploymentRate").toString().replaceAll(" ", "").trim());
//						if(d.compareTo(new BigDecimal(100))>0 || d.compareTo(BigDecimal.ZERO)<=0 ){
//							error += "失业比例应在0到100之间!<br>";
//						}
//					}
//					
//				}
				//公积金缴纳方式
//				if (null == row.get("fundType") || "".equals(row.get("fundType").toString().replaceAll(" ", "").trim())) {
////					error += "公积金缴纳方式为空!<br>";
//				} else {
//
//					String  fundTypeName =  row.get("fundType").toString().replaceAll(" ", "").trim();
//					if("公积金大库".equals(fundTypeName)){
//						curMap.put("fundType", "COMMON");
//					}else if("公积金小库".equals(fundTypeName)){
//						curMap.put("fundType", "SINGLE");
//					}else{
//						error += "公积金缴纳方式不正确!<br>";
//					}
//				}

				//公积金库
//				if("COMMON".equals(curMap.get("fundType"))){
//
//					if (null == row.get("fundaccountId") || "".equals(row.get("fundaccountId").toString().replaceAll(" ", "").trim())) {
////						error += "公积金大库为空!<br>";
//					} else {
//						String kuName = row.get("fundaccountId").toString().replaceAll(" ", "").trim();
//						SpsAccount va = singleAccountMap.get(kuName);
//						if( va != null ){
//							curMap.put("fundaccountId",va.getAccId().toString());
//						}else {
//							error += "请填写正确的公积金大库!<br>";
//						}
//					}
//				}
//				if("SINGLE".equals(curMap.get("fundType"))){
//
//					if (null == row.get("areaId2") || "".equals(row.get("areaId2").toString().replaceAll(" ", "").trim())) {
//						error += "公积金开户地址为空!<br>";
//					} else {
//						String areaName = row.get("areaId2").toString().replaceAll(" ", "").trim();
//
//						if (null != bsAreaNameMap.get(areaName)) {
//							curMap.put("areaId2", bsAreaNameMap.get(areaName).toString());
//							SpsAccount  sps =new SpsAccount();
//							sps.setAreaId(bsAreaNameMap.get(areaName));
//							sps.setInsuranceFundType("FUND");
//							Map<String ,BigDecimal > mk = new HashMap<>();
//							mk = getRange(sps);
//							String corption = row.get("corption") == null ?null:row.get("corption").toString().replace("","").trim();
//							String psntion =  row.get("psntion")== null ?null:row.get("psntion").toString().replace("","").trim();
//							if(mk.get("minCorpRatio").equals(mk.get("maxCorpRatio"))){
//								curMap.put("fundCorp",mk.get("minCorpRatio").toString());
//							}else {
//								if(StringUtils.isNotBlank(corption)){
//									BigDecimal corpRtion = null;
//									try {
//										corpRtion = com.xfs.common.util.StringUtils.toBigDecimal(corption);
//										if(corpRtion.compareTo(mk.get("minCorpRatio"))>=0&&corpRtion.compareTo(mk.get("maxCorpRatio"))<=0) {
//											curMap.put("fundCorp",corption);
//										} else {
//											error+="公积金公司缴纳比例填写错误!<br>";
//										}
//									} catch (NumberFormatException e) {
//										error+="公积金公司缴纳比例填写错误,必须是数字类型!<br>";
//									}
//								}else {
////									error+="未填写公积金公司缴纳比例!<br>";
//								}
//							}
//
//							if(mk.get("minPsnRatio").equals(mk.get("maxPsnRatio"))){
//								curMap.put("fundPsn",mk.get("minPsnRatio").toString());
//							}else {
//								if(StringUtils.isNotBlank(psntion)){
//									BigDecimal psntionD = null;
//									try {
//										psntionD = com.xfs.common.util.StringUtils.toBigDecimal(psntion);
//										if(psntionD.compareTo(mk.get("minPsnRatio"))>=0&&psntionD.compareTo(mk.get("maxPsnRatio"))<=0) {
//											curMap.put("fundPsn",psntion);
//										} else {
//											error+="公积金个人缴纳比例填写错误!<br>";
//										}
//									} catch (NumberFormatException e) {
//										error+="公积金个人缴纳比例填写错误，必须是数字类型!<br>";
//									}
//								}else {
////									error+="未填写公积金个人司缴纳比例!<br>";
//								}
//							}
//						} else if(null != bsAreaFullNameMap.get(areaName)){
//							curMap.put("areaId2", bsAreaFullNameMap.get(areaName).toString());
//						}else {
//							error += "未找到该公积金开户地址!<br>";
//						}
//					}
//
//					//公积金账号
//					if (null == row.get("regNum2") || "".equals(row.get("regNum2").toString().replaceAll(" ", "").trim())) {
//					} else {
//						curMap.put("regNum2", row.get("regNum2").toString().replaceAll(" ", "").trim());
//					}
//					//公积金密码
//					if (null == row.get("regNumpass2") || "".equals(row.get("regNumpass2").toString().replaceAll(" ", "").trim())) {
//					} else {
//						curMap.put("regNumpass2", row.get("regNumpass2").toString().replaceAll(" ", "").trim());
//					}
//
//
//					//公积金托收日,已去掉
////					if (null == row.get("collectionDay2") || "".equals(row.get("collectionDay2").toString().replaceAll(" ", "").trim())) {
////					} else {
////						curMap.put("collectionDay2", row.get("collectionDay2").toString().replaceAll(" ", "").trim());
////						Integer d = Integer.valueOf(row.get("collectionDay2").toString().replaceAll(" ", "").trim());
////						if(d>31 || d<1 ){
////							error += "公积金托收日应在1到31之间!<br>";
////						}
////					}
//
//				}
				//收费方式,已去掉
//				if (null == row.get("chargeMode") || "".equals(row.get("chargeMode").toString().replaceAll(" ", "").trim())) {
////					error += "收费方式为空!<br>";
//				} else {
//					if("人月".equals(row.get("chargeMode").toString().replaceAll(" ", "").trim())){
//						curMap.put("chargeMode", "MANMONTH");
//					}else{
//						curMap.put("chargeMode", "OTHER");
//					}
//				}


				//收费金额为空,已去掉
//				if (null == row.get("servicePrice") || "".equals(row.get("servicePrice").toString().replaceAll(" ", "").trim())) {
//
//				} else {
//					curMap.put("servicePrice", row.get("servicePrice").toString().replaceAll(" ", "").trim());
//
//				}

				//账单日,已去掉
//				if (null == row.get("billDay") || "".equals(row.get("billDay").toString().replaceAll(" ", "").trim())) {
//
//				} else {
//					curMap.put("billDay", row.get("billDay").toString().replaceAll(" ", "").trim());
//					Integer d = Integer.valueOf(row.get("billDay").toString().replaceAll(" ", "").trim());
//					if(d>31 || d<1 ){
//						error += "账单日应在1到31之间!<br>";
//					}
//
//				}
				if (!"".equals(error)) {
					curMap.put("error", error);
					errorDataList.add(curMap);
					isError = true;
				}
				
				dataList.add(curMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("excel读取出错！");
			result.setSuccess(false);
			throw new BusinessException("excel读取出错！");
		}

		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataList", errorDataList);
			result.setDataValue("errorNum", errorDataList.size());
		} else {

			SysFunctionData sysFunctionData = new SysFunctionData();
			sysFunctionData.setUserId(cti.getUserId());
			sysFunctionData.setUserType(cti.getUserType());
			List<SysFunctionData> functionDatas = sysFunctionDataService.findAll(sysFunctionData);
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {

					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");
					CmCorp cmCorp = new CmCorp();
//					cmCorp.setSource("SAAS");//saas
					cmCorp.setCorpName(curMap.get("corpName"));
					cmCorp.setShortName(curMap.get("shortName"));
					cmCorp.setContactPsn(curMap.get("contactPsn"));
					cmCorp.setMobile(curMap.get("mobile"));
					cmCorp.setMail(curMap.get("mail"));
//					cmCorp.setCollaborator(curMap.get("collaborator"));
					if(null!=curMap.get("cpAddressAreaId")){
						cmCorp.setCpAddressAreaId(Integer.parseInt(curMap.get("cpAddressAreaId")));
						cmCorp.setCpAddressArea(Integer.parseInt(curMap.get("cpAddressAreaId")));
					}
					cmCorp.setCpAddress(curMap.get("cpAddress"));
					cmCorp.setSpId(cti.getOrgId());//getSpId()
					//新增啊,先插入客户再插入关系
					cmCorpDao.insert(cti, cmCorp);
					/*1*/
					SpsScheme spsScheme=new SpsScheme();
					spsScheme.setCpId(cmCorp.getCpId());
					spsScheme.setSpId(cmCorp.getSpId());
					spsScheme.setName(cmCorp.getShortName()+"默认方案");
					if(null!=cmCorp.getCpAddressArea()){
						BsArea area = bsAreaService.getRedisAreaMap().get(cmCorp.getCpAddressArea());
						spsScheme.setAreaId(area.getBelongCity());
					}
					spsScheme.setIsdefault("Y");
					spsScheme.setState("USE");
					spsScheme.setSchemeType("DIY");//自己处理
					spsScheme.setIspackage("N");
					spsScheme.setDr(0);
//					spsScheme.setEffectiveDate(DateUtil.getCurYearMonthStr());
					spsSchemeDao.save(cti, spsScheme);
					
					//查询 城市险种
//					if(null!=cmCorp.getCpAddressArea()){
//						BsArea area = bsAreaService.getRedisAreaMap().get(cmCorp.getCpAddressArea());
//						List<Map<String,Object>> cityInsurances  = bdInsuranceareaService.findInsurancesByAreaId(area.getBelongCity());
//						if(null!=cityInsurances && cityInsurances.size()>0){
//							for(int j=0;j<cityInsurances.size();j++){
//								Map<String,Object> insMap = cityInsurances.get(j);
//								SpsSchemeInsurance newSchemeInsurance = new SpsSchemeInsurance();
//								newSchemeInsurance.setSchemeId(spsScheme.getSchemeId());
//								newSchemeInsurance.setInsuranceId(Integer.valueOf(insMap.get("insurance_id").toString()));
//								newSchemeInsurance.setDr(0);
//								spsSchemeInsuranceDao.insert(newSchemeInsurance);
//							}
//						}
//					}
					/*2*/

					SpCmRelation cmRelation = new SpCmRelation();
					cmRelation.setCpId(cmCorp.getCpId());
					cmRelation.setSpId(cti.getOrgId());
//					cmRelation.setInsuranceType(curMap.get("insuranceType"));
//					cmRelation.setFundType(curMap.get("fundType"));
					cmRelation.setSource("SAAS");
					cmRelation.setContractType(2); // 两方合同

					if("SINGLE".equals(curMap.get("insuranceType"))){
						//社保小库的话
						SpsAccount account = new SpsAccount();
						account.setAccType("SINGLE");
						String areaId1 = curMap.get("areaId1");
						String inCorp = curMap.get("inCorp");

						Map<String ,BigDecimal> map= null;
						if(StringUtils.isNotBlank(areaId1) ){
							account.setAreaId(Integer.parseInt(areaId1));
							account.setInsuranceFundType("INSURANCE");
							account.setCpId(cmCorp.getCpId());
							BsArea area = new BsArea();
							area.setAreaId(Integer.parseInt(areaId1));
							BsArea dbarea = bsAreaService.findbyPK(area);
							if(dbarea  != null){
								account.setName(cmCorp.getCorpName()+"_"+dbarea.getName()); //TODO  确认是否按照这个规则。客户名字+加上地区名字+"小库"
							}else {
								account.setName(cmCorp.getCorpName());
							}
						}

						if(StringUtils.isNotBlank(inCorp)) {
							account.setCorpRatio(com.xfs.common.util.StringUtils.toBigDecimal(inCorp));
						}

//						if(!StringUtils.isEmpty(curMap.get("collectionDay1"))){
//							account.setCollectionDay(Integer.parseInt(curMap.get("collectionDay1")));
//						}
						account.setSpId(cmCorp.getSpId());
						account.setRegNum(curMap.get("regNum1"));
						account.setRegNumpass(curMap.get("regNumpass1"));
						account.setRegUsbkeypass(curMap.get("regUsbkeypass"));
						//失业比例
						account.setUnemploymentRate(new BigDecimal(curMap.get("unemploymentRate")));
						account.setDr(0);
						account.setState("ON");
						accountDao.insert(cti, account);
//						cmRelation.setInsuranceaccountId(account.getAccId());
					} else  if("COMMON".equals(curMap.get("insuranceType"))){
//						if(StringUtils.isNotBlank(curMap.get("insuranceaccountId"))){
//							cmRelation.setInsuranceaccountId(Integer.parseInt(curMap.get("insuranceaccountId")));
//						}
					}
					if("SINGLE".equals(curMap.get("fundType"))){
						//公积金小库的话
						SpsAccount account = new SpsAccount();
						account.setAccType("SINGLE");
						String areaId2 = curMap.get("areaId2");

						String fundCorp = curMap.get("fundCorp");
						String fundPsn = curMap.get("fundPsn");
						Map<String ,BigDecimal> map = null;
						if(StringUtils.isNotBlank(areaId2) ){
							account.setAreaId(Integer.parseInt(areaId2));
							account.setInsuranceFundType("FUND");
							account.setCpId(cmCorp.getCpId());
							BsArea area = new BsArea();
							area.setAreaId(Integer.parseInt(areaId2));
							BsArea dbarea = bsAreaService.findbyPK(area);

							if(dbarea  != null){
								account.setName(cmCorp.getCorpName()+"_"+dbarea.getName()); //TODO  确认是否按照这个规则。客户名字+加上地区名字+"小库"
							}else {
								account.setName(cmCorp.getCorpName());
							}
						}

						if(StringUtils.isNotBlank(fundCorp)){
							account.setCorpRatio(com.xfs.common.util.StringUtils.toBigDecimal(fundCorp));
						}
						if(StringUtils.isNotBlank(fundPsn)){
							account.setEmpRatio(com.xfs.common.util.StringUtils.toBigDecimal(fundPsn));
						}

//						if(!StringUtils.isEmpty(curMap.get("collectionDay2"))){
//							account.setCollectionDay(Integer.parseInt(curMap.get("collectionDay2")));
//						}
						account.setSpId(cmCorp.getSpId());
						account.setRegNum(curMap.get("regNum2"));
						account.setRegNumpass(curMap.get("regNumpass2"));
						account.setDr(0);
						account.setState("ON");
						accountDao.insert(cti, account);
//						cmRelation.setFundaccountId(account.getAccId());
					}  else  if("COMMON".equals(curMap.get("fundType"))){
//						cmRelation.setFundaccountId(Integer.parseInt(curMap.get("fundaccountId")));
					}
//					if(curMap.get("servicePrice")!=null){
//						cmRelation.setServicePrice(com.xfs.common.util.StringUtils.toBigDecimal(curMap.get("servicePrice")));
//					}
//					cmRelation.setBillDay(curMap.get("billDay"));
//					cmRelation.setChargeMode(curMap.get("chargeMode"));
					spCmRelationDao.insert(cti, cmRelation);
					
					//保存后  添加当前用户的权限
					if(null!= cti.getUserId()){
						if(null!=functionDatas && functionDatas.size()>0){
							if(1 == functionDatas.size() && "Y".equals(functionDatas.get(0).getIsAll())){
								//有 所有企业权限，不处理
							}else{
								//判断 有没有这个企业的 权限
								boolean flag = false;
								for(int j=0;j<functionDatas.size();j++){
									SysFunctionData obj = functionDatas.get(j);
									if(cmCorp.getCpId().equals(obj.getDataId())){
										flag = true;
										break;
									}
									
								}
								if(!flag){//没有权限 ，添加
									SysFunctionData curVo = new SysFunctionData();
									curVo.setDataType(Constant.DATATYPE);
									curVo.setUserId(cti.getUserId());
									curVo.setUserType(cti.getUserType());
									curVo.setDataId(cmCorp.getCpId());
									curVo.setIsAll("N");
									curVo.setDr(0);
									sysFunctionDataService.insert(cti, curVo);
								}
							}
						}else{
							SysFunctionData curVo = new SysFunctionData();
							curVo.setDataType(Constant.DATATYPE);
							curVo.setUserId(cti.getUserId());
							curVo.setUserType(cti.getUserType());
							curVo.setDataId(cmCorp.getCpId());
							curVo.setIsAll("N");
							curVo.setDr(0);
							sysFunctionDataService.insert(cti, curVo);
						}
					}
					
				}
				result.setSuccess(true);
				result.setDataValue("successNum", dataList.size());
			} else {
				result.setError("Excel模板中无数据！");
				result.setSuccess(false);
			}

		}
		return result;

	}

	/**
	 * 自动导入更新员工
	 * @param cpId
	 * @return
     */
	@Override
	public Result autoUpsertEmployee(ContextInfo cti,Integer cpId,Integer spId){
		Result result = Result.createResult();
		result.setSuccess(true);
		result.setError("自动更新成功");
		SpCmRelation relationCOnfition = new SpCmRelation();
		relationCOnfition.setCpId(cpId);
		relationCOnfition.setSpId(spId);
		SpCmRelation cmRelation = relationService.findAll(relationCOnfition).get(0);
		CmCorp cmCorp = new CmCorp();
		cmCorp.setCpId(cpId);
		cmCorp = cmCorpDao.findByPK(cmCorp);
		if( cmCorp == null || "Y".equals(cmCorp.getUnComplete())){
			result.setSuccess(false);
			result.setError("未找到客户或客户信息不完善");
			return result;
		}
		if(cmRelation == null) {
			result.setSuccess(false);
			result.setError("该客户不属于当前服务商");
			return result;
		}
		return result;
	}

	@Override
	public List<CmCorp> findCorpBySpId(CmCorp vo, ContextInfo cti) {
		return cmCorpDao.freeFindBySpId(vo, cti);
	}

	/**
	 * 成都公积金信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee fundTransformEmplByCd(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String fundState = jo.get("缴存状态") == null ? null:jo.get("缴存状态").toString();
		if(StringUtils.isNotBlank(fundState)){
			if(fundState.equals("正常")){
				cmEmployee.setFundState("ON");
			}else{
				cmEmployee.setFundState("OFF");
			}
		}
		BigDecimal fundBase = jo.get("缴存基数") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴存基数").toString());
	//	cmEmployee.setFundBase(fundBase);
		return cmEmployee;
	}

	/**
	 * 成都社保信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
     * @return
     */
	private CmEmployee insuranceTransformEmplByCd(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String workState = jo.get("人员状态") == null ? null:jo.get("人员状态").toString();
		if(StringUtils.isNotBlank(workState)){
			if(workState.equals("参保缴费")){
			//	cmEmployee.setWorkState("IN");
				cmEmployee.setInsuranceState("ON");
			}else if(workState.equals("暂停缴费")){
			//	cmEmployee.setWorkState("OUT");
				cmEmployee.setInsuranceState("OFF");
			}
		}
		String livingType = jo.get("人员类别") == null ? null:jo.get("人员类别").toString();
		if(StringUtils.isNotBlank(livingType)){
			if("城镇职工".equals(livingType)){
			//	cmEmployee.setLivingType("TOWN");
			}else{
		//		cmEmployee.setLivingType("COUNTRY");
			}
			if ("城镇职工".equals(livingType)) {
				cmEmployee.setInsuranceLivingType("TOWN");
			} else if ("原综保本市户籍劳动者".equals(livingType)) {
				cmEmployee.setInsuranceLivingType("GENERALLOCAL");
			} else if ("非本市户籍农民工".equals(livingType)) {
				cmEmployee.setInsuranceLivingType("OUTSIDECOUNTRY");
			}
		}
		BigDecimal insuranceBase = jo.get("缴存基数") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴存基数").toString());
	//	cmEmployee.setInsuranceBase(insuranceBase);

		return cmEmployee;
	}
	/**
	 * 北京公积金信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee fundTransformEmplByBj(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String fundState = jo.get("缴存状态") == null ? null:jo.get("缴存状态").toString();
		if(StringUtils.isNotBlank(fundState)){
			if(fundState.equals("缴存")){
				cmEmployee.setFundState("ON");
			}else{
				cmEmployee.setFundState("OFF");
			}
		}
		BigDecimal fundBase = jo.get("缴存基数") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴存基数").toString());
	//	cmEmployee.setFundBase(fundBase);
		return cmEmployee;
	}

	/**
	 * 广州社保信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee insuranceTransformEmplByGz(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String livingType = jo.get("户籍类型") == null ? null:jo.get("户籍类型").toString();
		if(StringUtils.isNotBlank(livingType)){
			if("本地非农业户口".equals(livingType)){
		//		cmEmployee.setLivingType("TOWN");
			}
			if ("本地非农业户口".equals(livingType)) {
				cmEmployee.setInsuranceLivingType("TOWN");
			}
		}
		String identityType = jo.get("身份证明类别") == null ? null:jo.get("身份证明类别").toString();
		if(StringUtils.isNotBlank(identityType)){
			if("居民身份证".equals(identityType)){
				cmEmployee.setIdentityType("IDCard");
			}
		}

		BigDecimal insuranceBase = jo.get("缴费工资") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴费工资").toString());
	//	cmEmployee.setInsuranceBase(insuranceBase);

		String insuranceState = jo.get("人员状态") == null ? null:jo.get("人员状态").toString();
		if(StringUtils.isNotBlank(insuranceState)){
			if("在职".equals(insuranceState)){
				cmEmployee.setInsuranceState("ON");
			}else{
				cmEmployee.setInsuranceState("OFF");
			}
		}

		return cmEmployee;
	}

	/**
	 * 广州公积金信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee fundTransformEmplByGz(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String fundState = jo.get("缴存状态") == null ? null:jo.get("缴存状态").toString();
		if(StringUtils.isNotBlank(fundState)){
			if(fundState.equals("缴存")){
				cmEmployee.setFundState("ON");
			}else{
				cmEmployee.setFundState("OFF");
			}
		}
		BigDecimal fundBase = jo.get("缴存基数") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴存基数").toString());
	//	cmEmployee.setFundBase(fundBase);
		return cmEmployee;
	}
	/**
	 * 深圳社保信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee insuranceTransformEmplBySz(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String livingType = jo.get("户籍") == null ? null:jo.get("户籍").toString();
		if(StringUtils.isNotBlank(livingType)){
			if("非深户农村".equals(livingType)){
		//		cmEmployee.setLivingType("COUNTRY");
			}else if("深户".equals(livingType)){
		//		cmEmployee.setLivingType("TOWN");
		//		cmEmployee.setInsuranceLivingType("LOCAL");
			}else if("非深户城镇".equals(livingType)){
		//		cmEmployee.setLivingType("TOWN");
			}
		}
		String insuranceLivingType = jo.get("医疗") == null ? null:jo.get("医疗").toString();
		if(StringUtils.isNotBlank(insuranceLivingType)){
			if(StringUtils.isBlank(cmEmployee.getInsuranceLivingType())){
				if("基本医疗保险一档".equals(insuranceLivingType)){
					cmEmployee.setInsuranceLivingType("OUTSIDE1");
				}else if("基本医疗保险二档".equals(insuranceLivingType)){
					cmEmployee.setInsuranceLivingType("OUTSIDE2");
				}else if("基本医疗保险三档".equals(insuranceLivingType)){
					cmEmployee.setInsuranceLivingType("OUTSIDE3");
				}
			}
		}
		BigDecimal insuranceBase = jo.get("缴存基数") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("缴存基数").toString());
	//	cmEmployee.setInsuranceBase(insuranceBase);

		String insuranceState = jo.get("缴交状态") == null ? null:jo.get("缴交状态").toString();
		if(StringUtils.isNotBlank(insuranceState)){
			if("正常".equals(insuranceState)){
				cmEmployee.setInsuranceState("ON");
			}else{
				cmEmployee.setInsuranceState("OFF");
			}
		}

		return cmEmployee;
	}
	/**
	 * 北京社保信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee insuranceTransformEmplByBj(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		String livingType = jo.get("户口性质") == null ? null:jo.get("户口性质").toString();
		if(StringUtils.isNotBlank(livingType)){
			if("农村（农业户口）".equals(livingType)){
			//	cmEmployee.setLivingType("COUNTRY");
			}
		}
		String mobile = jo.get("参保人电话") == null ? null:jo.get("参保人电话").toString();
		if(StringUtils.isNotBlank(mobile)){
			cmEmployee.setMobile(mobile);
		}

		String insuranceLivingType = jo.get("缴费人员类别") == null ? null:jo.get("缴费人员类别").toString();
		if(StringUtils.isNotBlank(insuranceLivingType)){
			if("本市城镇职工".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("LOCALTOWN");
			}else if("外埠城镇职工".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("LOCALCOUNTRY");
			}else if("本市农村劳动力".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("OUTSIDETOWN");
			}else if("外埠农村劳动力".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("OUTSIDECOUNTRY");
			}else if("本市农村劳动力（24号文）".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("LOCALCOUNTRY24");
			}else if("有雇工的本市城镇个体工商户".equals(insuranceLivingType)){
				cmEmployee.setInsuranceLivingType("LOCALTOWNSELFEMPLOYED");
			}
		}
		BigDecimal insuranceBase = jo.get("申报月均工资收入（元）") == null ? null:com.xfs.common.util.StringUtils.toBigDecimal(jo.get("申报月均工资收入（元）").toString());
	//	cmEmployee.setInsuranceBase(insuranceBase);

		String insuranceState = jo.get("医疗参保人员类别") == null ? null:jo.get("医疗参保人员类别").toString();
		if(StringUtils.isNotBlank(insuranceState)){
			if("在职职工".equals(insuranceState)){
				cmEmployee.setInsuranceState("ON");
			}else{
				cmEmployee.setInsuranceState("OFF");
			}
		}

		return cmEmployee;
	}
	/**
	 * 深圳公积金信息转换
	 * @param spsTsPersonFlow
	 * @param cmEmployee
	 * @return
	 */
	private CmEmployee fundTransformEmplBySz(SpsTsPersonFlow spsTsPersonFlow,CmEmployee cmEmployee){
		JSONObject jo = JSONObject.parseObject(spsTsPersonFlow.getJson());
		//TODO 深圳公积金
		return cmEmployee;
	}
	/**
	 * 创建企业账号
	 *
	 * @author lifq
	 *
	 * 2016年7月29日  下午3:47:04
	 */
	public Result createEmpUser(ContextInfo cti,Integer cpId){
		Result result = Result.createResult().setSuccess(false);
		CmCorp cmCorp = findByPK(cpId);
		//判断 账号是否已存
		CmCorp queryExist = new CmCorp();
		queryExist.setCorpName(cmCorp.getCorpName());
		queryExist.setOpenmall("Y");
		Integer rs = cmCorpDao.findCorpByCorpName(queryExist);
		if(rs > 0){
			result.setError("该企业名称已在商城开通服务，请勿重复开通！");
			return result;
		}
		SysUser vo = new SysUser();
		vo.setMobile(cmCorp.getMobile());
		if(StringUtils.isBlank(cmCorp.getMobile())){
			result.setError("该企业手机号不存在，请先维护手机号信息！");
			return result;
		}
		if(StringUtils.isBlank(cmCorp.getContactPsn())){
			result.setError("该企业联系人不存在，请先维护联系人信息！");
			return result;
		}
		vo.setUserType("CUSTOMER");
		List<SysUser> users =sysUserService.findAll(vo);
		if(null!=users && users.size()>0){
			result.setError("该企业账号已存在，不能重复创建！");
			return result;
		}
		SysUser sysUser = new SysUser();
		// 设置公司联系人实体
		sysUser.setRealName(cmCorp.getContactPsn());
		sysUser.setUserName(cmCorp.getContactPsn());
		sysUser.setMobile(cmCorp.getMobile());
		String pwd = com.xfs.common.util.StringUtils.getRandomSixString();
		sysUser.setPassword(Md5Util.md5(pwd));
		sysUser.setOrgId(cmCorp.getCpId());
		sysUser.setCreateDt(new Date());
		sysUser.setUserType("CUSTOMER");
		sysUser.setEnabled(1);
		sysUser.setCreateBy(cti.getUserId()+"");
		sysUser.setCreateDt(new Date());
		sysUserService.insert(cti, sysUser);
		//modify by lifq,添加数据权限,创建账号时候 肯定没有权限，直接新增
		SysFunctionData dataVo = new SysFunctionData();
		dataVo.setUserId(sysUser.getUserId());
		dataVo.setUserType(sysUser.getUserType());
		dataVo.setDataType(Constant.DATATYPE);
		dataVo.setDataId(cmCorp.getCpId());
		dataVo.setDr(0);
		dataVo.setIsAll("N");
		sysFunctionDataService.insert(cti,dataVo);
		/**
		 * 商城默认管理员角色
		 */
		sysUserRoleService.addOrEdit(null, sysUser.getUserId(),new Integer[]{SysRole.CUSTOMER_SUPER_ROLE_ID});
		String smsContent = "尊敬的企业，您的帐号已开通，密码为：" +pwd+"【用友薪福社】";
		boolean smsresult = false;
		try {
			smsresult =  SmsUtil.sendSms(cmCorp.getMobile(), smsContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(smsresult){
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("message", "账号创建成功，密码已发送到手机号"+cmCorp.getMobile()+"！");
			result.setData(dataMap);
		}
		result.setSuccess(true);
		return result;
	}
	/**
	 * 查询 手机号 是否存在
	 *
	 * @author lifq
	 *
	 * 2016年8月14日  下午5:16:07
	 */
	public List<Map<String,Object>> findExistMobile(String mobile,String cpId){
		return cmCorpDao.findExistMobile(mobile,cpId);
	}
	@Override
	public List<CmCorp> findCorpBySpId(Integer spId, ContextInfo cti) {
		return cmCorpDao. findCorpBySpId(spId, cti);
	}
	@Override
	public List<CmCorp> findCorpBySpIdWithOutAuth(Integer spId,SysUser user) {
		return cmCorpDao.findCorpBySpIdWithOutAuth(spId,user);
	}
	/**
	 * 查询小库信息（分页查询）
	 *
	 * @author lifq
	 *
	 * 2016年9月23日  上午11:17:06
	 */
	public PageModel findSingleAccountPage(PageInfo pi, Map<String,String> paraMap,ContextInfo user){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmCorpDao.countfindSingleAccountPage(paraMap);// 查询总数
		pm.setTotal(total);
		List<Map<String,Object>> datas = cmCorpDao.findSingleAccountPage(paraMap, pageIndex, pageSize);// 查询列表
		pm.setDatas(datas);
		return pm;
	}

	//----------bs 合并 start------------

	@Override
	public PageModel queryCorpAccountList(PageInfo pi, CmCorp cmCorp) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = cmCorpDao.queryCorpAccountCount(cmCorp);
		pm.setTotal(total);
		List<CorpAccount> datas = cmCorpDao.queryCorpAccountList(cmCorp, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	/**
	 * <p>Title: bs查询企业</p>
	 * <p>Description: bs查询企业</p>
	 * ${tags}
	 */
	@Override
	public List<CmCorp> findAllToBs(CmCorp vo){

		List<CmCorp> datas = cmCorpDao.freeFind(vo);
		for(CmCorp cmCorp:datas){
			if(cmCorp.getCpAddressArea()!=null){
				if(null == cmCorp.getCpAddressArea())continue;
				BsArea bsArea=new BsArea();
				bsArea.setAreaId(cmCorp.getCpAddressArea());
				BsArea bArea = bsAreaDao.findByPK(bsArea);
				if(null == bArea || null == bArea.getFullname()) continue;
				cmCorp.setAreaName(bArea.getFullname());
			}

		}
		return datas;

	}
	@Override
	public int countFindAll(){
		return cmCorpDao.countFindAll();
	}
	@Override
	public List<Map<String, Object>> countByDay(String stringDate){
		return cmCorpDao.countByDay(stringDate);
	}


	@Override
	public PageModel findAllCropList(PageInfo pi, CmCorp cmCorp) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = cmCorpDao.countFreeFindToBs(cmCorp);
		pm.setTotal(total);
		List<CmCorp> cmCorps = cmCorpDao.freeFindToBs(cmCorp, pageIndex, pageSize, " create_dt desc");
		if(CollectionUtils.isNotEmpty(cmCorps)){
			CmEmployee query = new CmEmployee();
			for(CmCorp corp:cmCorps){
				query.setCpId(corp.getCpId());
				corp.setSaasEmpCount(cmEmployeeDao.countFreeFind(query));
			}
		}
		pm.setDatas(cmCorps);
		return pm;
	}
	// 企业HR 开通账号 给手机号发验证码 并校验手机
	@Override
	public Result getCode(CorpInfo corpInfo) {
		Result result = Result.createResult().setSuccess(false);
		if (corpInfo != null && !com.xfs.common.util.StringUtils.isBlank(corpInfo.getPhone())) {
			if (com.xfs.common.util.StringUtils.isMobileNO(corpInfo.getPhone())) {
				try {
					//校验当前手机号是否存在
					SysUser sysUser = new SysUser();
					sysUser.setMobile(corpInfo.getPhone());
					sysUser.setUserType("CUSTOMER");
					List<SysUser> sysUsers = sysUserService.findByMobile(sysUser);
					if(null != sysUsers && sysUsers.size() > 0){
						result.setDataValue("msg", "该手机号已被注册");
						return result;
					}
					Object obj = RedisUtil.get(corpInfo.getPhone());
					if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
						obj = (int)(Math.random() * 9000 + 1000);
					}
					// 验证码 600秒有效期
					boolean flag = RedisUtil.set(corpInfo.getPhone(), obj, 600);
					if (flag) {
						boolean smsFlag = SmsUtil.sendVerificationCode(corpInfo.getPhone(),
								"【薪福社】验证码:" + obj + ",您正在注册用友薪福社商城,如不是本人操作,请忽略！");
						result.setSuccess(true);
						log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
						result.setDataValue("msg", "短信发送成功，请注意查收...");
					} else {
						result.setDataValue("msg", "验证码生成失败...");
					}

				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败...");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码...");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码...");
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> FindAllMap(CmCorp obj) {
		// TODO Auto-generated method stub
		return cmCorpDao.FindAllMap(obj);
	}

	//----------bs 合并 end------------
	//----------cs 合并 start------------
	@Override
	public PageModel findPage(ContextInfo cti,PageInfo pi, CmCorp vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		// 获取数据权限
		vo.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		Integer total = cmCorpDao.countFreeFindBySpId(vo);// 查询当前服务商管理的所有企业总数
		pm.setTotal(total);
		List<CmCorp> datas = cmCorpDao.freeFindBySpId(vo, pageIndex, pageSize);// 查询当前服务商管理的企业列表
		pm.setDatas(datas);
		return pm;
	}




	public Result registerUser(ContextInfo cti, CmCorp cmCorp, SysUser sysUser){
		Result result = Result.createResult();
        // 设置公司联系人实体
        sysUser.setOrgId(cmCorp.getCpId());
        sysUser.setCreateDt(new Date());
        sysUser.setUserType("CUSTOMER");

		if(sysUser.getUserId()!=null && sysUser.getUserId()!=0) {
			SysUser vo = new SysUser();
			vo.setUserId(sysUser.getUserId());
			vo.setUserName(sysUser.getUserName());
			vo.setRealName(sysUser.getRealName());
			vo.setRoleId(sysUser.getRoleId());
			userService.updateUser(cti, vo);
		}else{
			userService.insert(cti, sysUser);
		}

		/**
		 * 设置企业用户角色
		 */
		if(null != sysUser.getRoleId() && sysUser.getRoleId() > 0){
			Integer roleId = sysUser.getRoleId();
			sysUserRoleService.addOrEdit(cti, sysUser.getUserId(),new Integer[]{roleId});

			//删除用户所有数据权限
			sysFunctionDataDao.deleteByUserId(cti, sysUser.getUserId());
			Integer[] schemes = sysUser.getSchemes();
			Integer[] spIds = sysUser.getSpIds();
			if(roleId.equals(SysRole.CUSTOMER_SUPER_ROLE_ID)){
				insertFunctionData(cti, sysUser.getUserId(), null, Constant.DATATYPE, sysUser.getUserType());
			} else {
				if(null != schemes && schemes.length > 0 && !roleId.equals(SysRole.CUSTOMER_SUPER_ROLE_ID)) {
					for (int i = 0; i < schemes.length; i++) {
						insertFunctionData(cti, sysUser.getUserId(), schemes[i], Constant.DATATYPE, sysUser.getUserType());
					}
				}
				if(null != spIds && spIds.length > 0 && !roleId.equals(SysRole.CUSTOMER_SUPER_ROLE_ID)) {
					 for (int i = 0; i < spIds.length; i++) {
						 insertFunctionData(cti, sysUser.getUserId(), spIds[i], Constant.DATATYPESP, sysUser.getUserType());
					 }
				}
			}
			// 返回用户ID
			sysUser.setRoleIds(String.valueOf(roleId));
		}

		result.setDataValue("cmCorp",cmCorp);
		result.setDataValue("sysUser",sysUser);
		return result;
	}


	public Result insertUserAndCity(ContextInfo cti, CmCorp cmCorp, SysUser sysUser) throws Exception{
		Result result = Result.createResult().setSuccess(false);
		if(sysUser.getUserId()==null || sysUser.getUserId()==0) {
			SysUser vo = new SysUser();
			if (StringUtils.isNotBlank(sysUser.getMobile())) {
				vo.setMobile(sysUser.getMobile());
				if (StringUtils.isNotBlank(sysUser.getEmail())) {
					vo.setEmail(sysUser.getEmail());
				}
			} else {
				if (StringUtils.isNotBlank(sysUser.getEmail())) {
					vo.setEmail(sysUser.getEmail());
				} else {

					result.setError("手机号码或Email必须填一个！");
					return result;
				}
			}
			vo.setOrgId(sysUser.getOrgId());
			vo.setUserType("CUSTOMER");
			int flag = sysUserService.checkUserCount(vo);
			if (flag > 0) {
				result.setError("该账号已存在，不能重复创建！");
				return result;
			}
		}
		return registerUser(cti, cmCorp, sysUser);
	}

	@Override
	public List<Map> getAllCompInfoByCondition(CmCorp cmCorp) {
		return cmCorpDao.getAllCompInfoByCondition(cmCorp);
	}

	@Override
	public CmCorp getCorpByCpIdOrTenantId(String id) {
		System.out.println("");
		return cmCorpDao.getCorpByCpIdOrTenantId(id);

	}

	/**
	 *@param corpInfo
	 * @param corpInfo
	 *            公司信息实体
	 */
	public Result register(CorpInfo corpInfo, String source) {
		Result result = Result.createResult();
		CmCorp cmCorp = registerCorp(corpInfo,null,null,source);
		SysUser sysUser = new SysUser();
		// 设置公司联系人实体
		sysUser.setRealName(corpInfo.getContactsName());
		sysUser.setUserName(corpInfo.getContactsName());
		sysUser.setEmail(corpInfo.getEmail());
		sysUser.setMobile(corpInfo.getPhone());
		sysUser.setPassword(corpInfo.getPassword());
		sysUser.setOrgId(cmCorp.getCpId());
		sysUser.setCreateDt(new Date());
		sysUser.setUserType("CUSTOMER");
		userService.insert(null, sysUser);

		// 添加数据权限
		insertFunctionData(null, sysUser.getUserId(),null, Constant.DATATYPE,sysUser.getUserType());
		/**
		 * 设置企业用户默认角色
		 */
		sysUserRoleService.addOrEdit(null, sysUser.getUserId(),new Integer[]{SysRole.CUSTOMER_SUPER_ROLE_ID});
		// 返回用户ID
		corpInfo.setId(sysUser.getUserId());
		sysUser.setRoleIds(String.valueOf(SysRole.CUSTOMER_SUPER_ROLE_ID));
		result.setDataValue("cmCorp",cmCorp);
		result.setDataValue("sysUser",sysUser);
		return result;
	}

	/**
	 * 插入数据权限
	 * @param userId
	 * @param dataId
	 * @param dataType
	 * @param userType
	 */
	@Override
	public void insertFunctionData(ContextInfo cti,Integer userId,Integer dataId,String dataType,String userType) {
		try {
			SysFunctionData sysFunctionData = new SysFunctionData();
			sysFunctionData.setUserId(userId);
			sysFunctionData.setUserType(userType);
			sysFunctionData.setDataType(dataType);
			sysFunctionData.setDr(0);
			if(null == dataId){
				sysFunctionData.setIsAll("Y");
			}else{
				sysFunctionData.setDataId(dataId);
				sysFunctionData.setIsAll("N");
			}
			sysFunctionData.setCreateBy(cti!=null?cti.getUserId():userId);
			sysFunctionData.setCreateDt(new Date());
			sysFunctionDataService.insert(cti, sysFunctionData);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 校验公司信息
	 *
	 * @param corpInfo
	 * 公司信息注册集合
	 */
	@Override
	public Result checkCmCorp(CorpInfo corpInfo) {
		Result result = Result.createResult().setSuccess(false);
		// 验证企业名称
		if (com.xfs.common.util.StringUtils.isBlank(corpInfo.getCorpName())) {
			result.setError("企业名称不能为空!");
			return result;
		}
		String str = corpInfo.getCorpName().replace("(","（").replace(")","）");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5|（|）]+$");
		Matcher matcher = pattern.matcher(str);
		if(str.equals("（）") || !matcher.matches()){
			result.setError("只能由数字、字母、汉字（含括号）组成");
			return result;
		}
		CmCorp cmCorp = new CmCorp();
		cmCorp.setCorpNameEq(corpInfo.getCorpName());
		if (cmCorpDao.countFreeFind(cmCorp) > 0){
			result.setError("企业名称已经存在!");
			return result;
		}
		// 企信查询企业信息
		BsCompanyInfo bsCompanyInfo = bsCompanyInfoService.queryCompanyInfoByName(corpInfo.getCorpName());
		if (!com.xfs.common.util.StringUtils.isBlank(corpInfo.getBusiLicenseNum())
				&& bsCompanyInfo != null && !corpInfo.getBusiLicenseNum().equals(bsCompanyInfo.getCreditNo())) {
			result.setError(String.format("信用代码与实际不符，请正确填写!", corpInfo.getBusiLicenseNum(), corpInfo.getCorpName()));
			return result;
		}
		if (bsCompanyInfo == null) {
			corpInfo.setStatus(1);
		} else {
			//防止获取不到对应注册地址出现异常影响正常流程
			try {
				String address = JSON.parseObject(bsCompanyInfo.getContactJson()).getString("address");
				corpInfo.setCpAddress(address);
				Map<Integer, BsArea> areaMap = bsAreaService.getRedisAreaMap();
				for (Map.Entry<Integer, BsArea> entry : areaMap.entrySet()) {
					BsArea bsArea = entry.getValue();
					Integer areaId = entry.getKey();
					if (address.startsWith(bsArea.getFullname()) && bsArea.getParent() > 0) {
						corpInfo.setCpAddressArea(areaId);
					}
				}
			} catch (Exception e) {}
		}
		// 验证联系人
		if (com.xfs.common.util.StringUtils.isBlank(corpInfo.getContactsName())) {
			result.setError("联系人不能为空!");
			return result;
		}
		if (com.xfs.common.util.StringUtils.isBlank(corpInfo.getPhone())) {
			result.setError("手机号不能为空!");
			return result;
		}
		SysUser sysUser = new SysUser();
		sysUser.setMobile(corpInfo.getPhone());
		sysUser.setUserType("CUSTOMER");
		if (userService.countFreeFind(sysUser) > 0){
			result.setError("手机号码已经存在!");
			return result;
		}
		// 验证邮箱唯一性
		if (!com.xfs.common.util.StringUtils.isBlank(corpInfo.getBusiLicenseNum()) && !com.xfs.common.util.StringUtils.isBlank(corpInfo.getEmail())){
			sysUser = new SysUser();
			sysUser.setEmail(corpInfo.getEmail());
			sysUser.setUserType("CUSTOMER");
			if (userService.countFreeFind(sysUser) > 0){
				result.setError("邮箱已注册，请更改!");
				return result;
			}
		}
		return result.setSuccess(true);
	}

	/**
	 * 更新 公司信息（带校验）
	 *
	 * @param cmCorp
	 */
	@Override
	public void updateCmcorp(ContextInfo cti,CmCorp cmCorp) {
		// 保存
		cmCorpDao.update(cti, cmCorp);
	}
	@Override
	public CmCorp findByCorpName(CmCorp cmCorpParam) {
		return cmCorpDao.findByCorpName(cmCorpParam);

	}

	/**
	 * 根据corpId 查询企业数据
	 */
	@Override
	public CmCorp findOneByCorpIdToCs(CmCorp vo) {
		CmCorp datas =  cmCorpDao.findByPK(vo);
		if(datas != null && datas.getCpAddressAreaId() != null){
			BsArea area = new BsArea();
			area.setAreaId(datas.getCpAddressAreaId());
			area = bsAreaService.findbyPK(area);
			if (null != area) {
				String areaName = area.getFullname();
				datas.setAreaName(areaName);
			}
		}
		return datas;
	}

	public CmCorp registerCorp(CorpInfo corpInfo,String channel,String sname, String source){
		CmCorp cmCorp = new CmCorp();
		cmCorp.setCorpName(corpInfo.getCorpName());
		cmCorp.setCustomerNo(corpInfo.getCustomerNo());
		cmCorp.setContactPsn(corpInfo.getContactsName());
		cmCorp.setMobile(corpInfo.getPhone());
		cmCorp.setUnComplete("Y");
		cmCorp.setOpenmall("Y");
		cmCorp.setSource(source == null ? "MALL" : source);
		cmCorp.setCpAddress(corpInfo.getCpAddress());
		cmCorp.setCpAddressArea(corpInfo.getCpAddressArea());
		cmCorp.setMail(corpInfo.getEmail());
		cmCorp.setPosition(corpInfo.getPosition());
		cmCorp.setTenantId(corpInfo.getTenantId());
		cmCorp.setBusiLicenseNum(corpInfo.getBusiLicenseNum());
		cmCorp.setStatus(String.valueOf(corpInfo.getStatus()));
		cmCorp.setRemark(corpInfo.getRemark());
		if(StringUtils.isNotBlank(corpInfo.getEmployeeSource()))
			cmCorp.setEmployeeSource(corpInfo.getEmployeeSource());
		if(StringUtils.isNotBlank(channel))
			cmCorp.setChannelCode(channel);

		cmCorp.setAppid("xfs20170308750014180402");
		cmCorp.setAppsecret("7bb414abd21a4a44b31ab8d6b61d7eee");

		int r = cmCorpDao.insert(null, cmCorp);
		if (r <= 0) {
			throw new BusinessException("注册企业失败");
		}

		// 注册记录
		if(StringUtils.isNotBlank(channel)){
			CsRegistHistory registHistory = new CsRegistHistory();
			registHistory.setChannel(channel);
			registHistory.setSname(sname);
			registHistory.setCorpId(cmCorp.getCpId());
			if (!com.xfs.common.util.StringUtils.isBlank(sname)) {
				BsChannelUser channelUser = new BsChannelUser();
				channelUser.setCode(sname);
				List<BsChannelUser> list = channelUserDao.freeFind(channelUser);
				if(list != null && !list.isEmpty()){
					if(!com.xfs.common.util.StringUtils.isBlank(list.get(0).getArea())){
						registHistory.setArea(Integer.valueOf(list.get(0).getArea()));
					}
					if(!com.xfs.common.util.StringUtils.isBlank(list.get(0).getGroups())){
						registHistory.setGroups(Integer.valueOf(list.get(0).getGroups()));
					}
				}
			}
			r = registHistoryDao.save(null, registHistory);
			if (r <= 0) {
				throw new BusinessException("注册企业失败");
			}
			corpInfo.setRegistHistoryId(registHistory.getId());
		}
		return cmCorp;
	}
	/**
	 * cs 注册
	 *@param channel
	 * @param corpInfo
	 *            公司信息实体
	 */
	@Override
	public Result register(CorpInfo corpInfo, String channel, String sname, String source) throws Exception {
		return register(corpInfo,null,channel,sname,source);
	}

	public Result register(CorpInfo corpInfo, SysUser sysUser, String channel, String sname, String source) throws Exception {
		Result result = Result.createResult();
		CmCorp cmCorp = registerCorp(corpInfo,channel,sname,source);

		if(null == sysUser) {
			sysUser = new SysUser();
			sysUser.setRoleId(SysRole.CUSTOMER_SUPER_ROLE_ID);
		}
		// 设置公司联系人实体
		if(StringUtils.isNotBlank(corpInfo.getContactsName())){
			sysUser.setRealName(corpInfo.getContactsName());
			sysUser.setUserName(corpInfo.getContactsName());
		}
		if(StringUtils.isNotBlank(corpInfo.getEmail()))
			sysUser.setEmail(corpInfo.getEmail());
		if(StringUtils.isNotBlank(corpInfo.getPhone()))
			sysUser.setMobile(corpInfo.getPhone());
		sysUser.setPassword(corpInfo.getPassword());
		sysUser.setOrgId(cmCorp.getCpId());
		sysUser.setCreateDt(new Date());
		sysUser.setUserType("CUSTOMER");
		userService.insert(null, sysUser);

		if(String.valueOf(SysRole.CUSTOMER_SUPER_ROLE_ID).equals(String.valueOf(sysUser.getRoleId()))){
			// 添加数据权限
			insertFunctionData(null, sysUser.getUserId(),null, Constant.DATATYPE,sysUser.getUserType());

			/**
			 * 设置企业用户默认角色
			 */
			sysUserRoleService.addOrEdit(null, sysUser.getUserId(),new Integer[]{SysRole.CUSTOMER_SUPER_ROLE_ID});
		}

		// 返回用户ID
		corpInfo.setId(sysUser.getUserId());
		sysUser.setRoleIds(String.valueOf(SysRole.CUSTOMER_SUPER_ROLE_ID));
		result.setDataValue("cmCorp",cmCorp);
		result.setDataValue("sysUser",sysUser);
		return result;
	}
	/**
	 * 更新认证前置信息
	 * @param cti
	 * @param cmCorp
	 * @return
	 */
	@Override
	public Result updateInfo(ContextInfo cti,CmCorp cmCorp){
		Result result = new Result();
		CmCorp corp = new CmCorp();
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getShortName())){
			result.setSuccess(false).setError("企业简称不能为空");
			return result;
		}
		corp.setShortName(cmCorp.getShortName().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getMobile())){
			result.setSuccess(false).setError("手机号不能为空");
			return result;
		}
		if(!MobileValidator.isMobileNO(cmCorp.getMobile())){
			result.setSuccess(false).setError("手机号格式错误");
			return result;
		}
		corp.setMobile(cmCorp.getMobile().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getMail())){
			result.setSuccess(false).setError("邮箱不能为空");
			return result;
		}
		if(cmCorp.getMail().indexOf("@") < 0){
			result.setSuccess(false).setError("邮箱格式错误");
			return result;
		}
		corp.setMail(cmCorp.getMail().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getBusiLicenseNum())){
			result.setSuccess(false).setError("信用代码不能为空");
			return result;
		}
		if(cmCorp.getBusiLicenseNum().length() > 50){
			result.setSuccess(false).setError("信用代码长度不能超过50");
			return result;
		}
		if(cmCorp.getCpAddressAreaId() != null && cmCorp.getCpAddressAreaId() > 0){
			corp.setCpAddressAreaId(cmCorp.getCpAddressAreaId());
		}
		if(com.xfs.common.util.StringUtils.isNotBlank(cmCorp.getCpAddress())){
			corp.setCpAddress(cmCorp.getCpAddress());
		}
		corp.setBusiLicenseNum(cmCorp.getBusiLicenseNum().trim());
		corp.setCpId(cti.getOrgId());
		if(cmCorpDao.update(cti, corp) > 0){
			result.setSuccess(true).setError("保存成功");
		}else{
			result.setSuccess(false).setError("保存失败");
		}
		return result;
	}

	/**
	 * 企业认证
	 * @param cti
	 * @param cmCorp
	 * @return
	 */
	@Override
	public Result saveLegalize(ContextInfo cti,CmCorp cmCorp){
		Result result = new Result();
		CmCorp corp = new CmCorp();
		corp.setCpId(cti.getOrgId());
		corp = cmCorpDao.findByPK(corp);
		if(corp.getIsLegalize() == 1){
			result.setSuccess(false).setError("请勿重复认证");
			return result;
		}
		if(com.xfs.common.util.StringUtils.isBlank(corp.getMobile())){
			result.setSuccess(false).setError("请先完善企业手机号");
			return result;
		}
		if(com.xfs.common.util.StringUtils.isBlank(corp.getMail())){
			result.setSuccess(false).setError("请先完善企业邮箱");
			return result;
		}
		if(com.xfs.common.util.StringUtils.isBlank(corp.getBusiLicenseNum())){
			result.setSuccess(false).setError("请先完善企业信用代码");
			return result;
		}
		if(cmCorp.getBusiLicenseFile() == null){
			result.setSuccess(false).setError("请上传营业执照");
			return result;
		}
		SysUploadfile vo = new SysUploadfile();
		vo.setId(cmCorp.getBusiLicenseFile());
		if(sysUploadfileDAO.findByPK(vo) == null){
			result.setSuccess(false).setError("营业执照错误");
			return result;
		}
		if(cmCorp.getCorporateFile() == null){
			result.setSuccess(false).setError("请上传法人身份证");
			return result;
		}
		vo.setId(cmCorp.getCorporateFile());
		if(sysUploadfileDAO.findByPK(vo) == null){
			result.setSuccess(false).setError("法人身份证错误");
			return result;
		}
		if(cmCorp.getDeputyFile() == null){
			result.setSuccess(false).setError("请上传代表人身份证");
			return result;
		}
		vo.setId(cmCorp.getDeputyFile());
		if(sysUploadfileDAO.findByPK(vo) == null){
			result.setSuccess(false).setError("代表人身份证错误");
			return result;
		}
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getOtherFile())){
			result.setSuccess(false).setError("请上CA证书申请表");
			return result;
		}
		if(cmCorp.getOtherFile().length() > 50){
			result.setSuccess(false).setError("附件过多，请删除");
			return result;
		}
		String[] files = cmCorp.getOtherFile().split(",");
		for(String file:files){
			Integer fileId = null;
			try {
				fileId = Integer.parseInt(file);
			} catch (NumberFormatException e) {
				result.setSuccess(false).setError("其他附件错误");
				return result;
			}
			vo.setId(fileId);
			if(sysUploadfileDAO.findByPK(vo) == null){
				result.setSuccess(false).setError("其他附件错误");
				return result;
			}
		}
		//TODO 执行王超utils 返回法大大id
		String json = "{cc:'sss'}";
		Map<String,Object> jsonMap = JSON.parseObject(json);
		String customerNo = jsonMap.get("cc").toString();
		//认证需要更新的字段
		corp = new CmCorp();
		corp.setCpId(cti.getOrgId());
		corp.setBusiLicenseFile(cmCorp.getBusiLicenseFile());
		corp.setCorporateFile(cmCorp.getCorporateFile());
		corp.setDeputyFile(cmCorp.getDeputyFile());
		corp.setOtherFile(cmCorp.getOtherFile());
		corp.setIsLegalize(1);
		corp.setCustomerNo(customerNo);
		if(cmCorpDao.update(cti, corp) > 0){
			result.setSuccess(true).setError("认证成功");
		}else{
			result.setSuccess(false).setError("认证失败");
		}
		return result;
	}

	/**
	 * 更新企业Logo
	 * @param
	 * @param cti
	 * @return
	 */
	@Override
	public Result updateCorpLogo(ContextInfo cti,Integer logo) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if(logo == null){
			result.setSuccess(false);
			result.setError("logo图片不能为空");
			return result;
		}
		CmCorp cmCorp = new CmCorp();
		cmCorp.setCpId(cti.getOrgId());
		cmCorp.setCorpLogo(logo);
		Integer ins = cmCorpDao.update(cti, cmCorp);
		if (ins > 0) {
			result.setSuccess(true);
			result.setError("更新Logo成功！");
			return result;
		} else {
			result.setSuccess(false);
			result.setError("更新Logo失败！");
			return result;
		}

	}

	/**
	 * 首页信息展示
	 * @param cpId
	 * @return
	 */
	@Override
	public Result indexInit(Integer cpId){
		//账单模块
		BlBalance blBalance = new BlBalance();
		blBalance.setOwner(String.valueOf(cpId));
		blBalance.setOwnerType("CORP");
		blBalance = blBalanceService.queryBalance(blBalance);
		//账户余额
		BigDecimal balance = new BigDecimal(0);
		if(blBalance != null){
			balance = blBalance.getAmount();
		}
		//最近账单月份
		String billMonth = spsBillService.queryMaxBillMonth(cpId);

		//任务单模块
		SpsTask query = new SpsTask();
		query.setCpId(cpId);
		query.setType("'SUBMIT'");
		Integer submitCount = spsTaskDao.findTaskCountByCondition(query,null);
		query.setType("'TODO'");
		Integer todoCount = spsTaskDao.findTaskCountByCondition(query,null);
		query.setType("'COMPLETED'");
		Integer completedCount = spsTaskDao.findTaskCountByCondition(query,null);
		query.setType("'ERROR','CLOSED'");
		Integer closeCount = spsTaskDao.findTaskCountByCondition(query,null);

		//员工模块
		CmEmployee empQuery = new CmEmployee();
		empQuery.setCpId(cpId);
		empQuery.setDr(0);
		empQuery.setIsQuit(0);
		//在职员工
		Integer emplCount = cmEmployeeDao.countFreeFind(empQuery);
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0,0);
		Date begin = calendar.getTime();
		calendar.add(Calendar.MONTH,1);
		Date end = calendar.getTime();
		Map<String,Object> entryParam = new HashMap<>();
		entryParam.put("cpId",cpId);
		entryParam.put("dr",0);
		entryParam.put("isQuit",0);
		entryParam.put("entryTimeFrom",begin);
		entryParam.put("entryTimeDtTo",end);
		//本月入职人数
		Integer entryEmpl = cmEmployeeDao.freeCountByMap(entryParam);
		Map<String,Object> leaveParam = new HashMap<>();
		leaveParam.put("cpId",cpId);
		leaveParam.put("dr",0);
		leaveParam.put("isQuit",0);
		leaveParam.put("leaveTimeFrom",begin);
		leaveParam.put("leaveTimeDtTo",end);
		//本月离职人数
		Integer leaveEmpl = cmEmployeeDao.freeCountByMap(leaveParam);

		//社保在缴人数
		empQuery.setIsQuit(null);
		empQuery.setInsuranceStateEq("ON");
		Integer insuEmpCount = cmEmployeeDao.countFreeFind(empQuery);
		//公积金在缴人数
		empQuery.setIsQuit(null);
		empQuery.setInsuranceStateEq(null);
		empQuery.setFundStateEq("ON");
		Integer fundEmpCount = cmEmployeeDao.countFreeFind(empQuery);

		Result result = new Result();
		result.setDataValue("balance",balance);
		result.setDataValue("billMonth",billMonth);
		result.setDataValue("submitCount",submitCount);
		result.setDataValue("todoCount",todoCount);
		result.setDataValue("completedCount",completedCount);
		result.setDataValue("closeCount",closeCount);
		result.setDataValue("emplCount",emplCount);
		result.setDataValue("entryEmpl",entryEmpl);
		result.setDataValue("leaveEmpl",leaveEmpl);
		result.setDataValue("insuEmpCount",insuEmpCount);
		result.setDataValue("fundEmpCount",fundEmpCount);
		Map<String,Object> taskStatistical = taskStatistical(cpId);
		result.setDataValue("taskStatistical",taskStatistical);
		result.setDataValue("taskStatisticalJson",JSON.toJSONString(taskStatistical));
		return  result;
	}

	/**
	 * 首页任务单统计
	 * @param cpId
	 * @return
	 */
	@Override
	public Map<String,Object> taskStatistical(Integer cpId){
		SpsTask query = new SpsTask();
		query.setCpId(cpId);
		List<Map<String,Object>> list = spsTaskDao.findDiyFieldMap(query);
		//任务单统计集合
		Map<String,Object>  insuTypeMap = new HashMap<>();
		Map<String,Object>  fundTypeMap = new HashMap<>();
		//全部选项
		Map<String,Object> insuAll = new HashMap<>();
		Map<String,Object> fundAll = new HashMap<>();
		Integer insuAllCount = 0;
		Integer insuAllSubmit = 0;
		Integer insuAllTodo = 0;
		Integer insuAllComplete = 0;

		Integer fundAllCount = 0;
		Integer fundAllSubmit = 0;
		Integer fundAllTodo = 0;
		Integer fundAllComplete = 0;

		if(CollectionUtils.isNotEmpty(list)){
			for(Map<String,Object> task : list){
				if("FUND".equals(task.get("insurance_fund_type"))){
					Map<String,Object> typeMap = null;
					if("FUND".equals(BsType.getInsType(Integer.parseInt(String.valueOf(task.get("bstype_id")))))){
						if(fundTypeMap.get("type"+task.get("bstype_id")) != null){
							typeMap = (Map<String, Object>) fundTypeMap.get("type"+task.get("bstype_id"));
						}else{
							typeMap = new HashMap<>();
							typeMap.put("count",0);
							typeMap.put("submitCount",0);
							typeMap.put("todoCount",0);
							typeMap.put("completeCount",0);
							typeMap.put("name",task.get("name"));

							fundTypeMap.put("type"+task.get("bstype_id"),typeMap);
						}
					}else{
						//其他
						if(fundTypeMap.get("type0") != null){
							typeMap = (Map<String, Object>) fundTypeMap.get("type0");
						}else{
							typeMap = new HashMap<>();
							typeMap.put("count",0);
							typeMap.put("submitCount",0);
							typeMap.put("todoCount",0);
							typeMap.put("completeCount",0);
							typeMap.put("name","其他");

							fundTypeMap.put("type0",typeMap);
						}
					}
					Integer count = (Integer) typeMap.get("count");
					Integer submitCount = (Integer) typeMap.get("submitCount");
					Integer todoCount = (Integer) typeMap.get("todoCount");
					Integer completeCount = (Integer) typeMap.get("completeCount");

					count++;
					fundAllCount++;
					if("SUBMIT".equals(task.get("type"))){
						submitCount++;
						fundAllSubmit++;
					}else if("TODO".equals(task.get("type"))){
						todoCount++;
						fundAllTodo++;
					}else if("COMPLETED".equals(task.get("type"))){
						completeCount++;
						fundAllComplete++;
					}
					typeMap.put("count",count);
					typeMap.put("submitCount",submitCount);
					typeMap.put("todoCount",todoCount);
					typeMap.put("completeCount",completeCount);
				}else{
					Map<String,Object> typeMap = null;
					if("INSURANCE".equals(BsType.getInsType(Integer.parseInt(String.valueOf(task.get("bstype_id")))))){
						if(insuTypeMap.get("type"+task.get("bstype_id")) != null){
							typeMap = (Map<String, Object>) insuTypeMap.get("type"+task.get("bstype_id"));
						}else{
							typeMap = new HashMap<>();
							typeMap.put("count",0);
							typeMap.put("submitCount",0);
							typeMap.put("todoCount",0);
							typeMap.put("completeCount",0);
							typeMap.put("name",task.get("name"));

							insuTypeMap.put("type"+task.get("bstype_id"),typeMap);
						}
					}else{
						//其他
						if(insuTypeMap.get("type0") != null){
							typeMap = (Map<String, Object>) insuTypeMap.get("type0");
						}else{
							typeMap = new HashMap<>();
							typeMap.put("count",0);
							typeMap.put("submitCount",0);
							typeMap.put("todoCount",0);
							typeMap.put("completeCount",0);
							typeMap.put("name","其他");

							insuTypeMap.put("type0",typeMap);
						}
					}
					Integer count = (Integer) typeMap.get("count");
					Integer submitCount = (Integer) typeMap.get("submitCount");
					Integer todoCount = (Integer) typeMap.get("todoCount");
					Integer completeCount = (Integer) typeMap.get("completeCount");

					count++;
					insuAllCount++;
					if("SUBMIT".equals(task.get("type"))){
						submitCount++;
						insuAllSubmit++;
					}else if("TODO".equals(task.get("type"))){
						todoCount++;
						insuAllTodo++;
					}else if("COMPLETED".equals(task.get("type"))){
						completeCount++;
						insuAllComplete++;
					}
					typeMap.put("count",count);
					typeMap.put("submitCount",submitCount);
					typeMap.put("todoCount",todoCount);
					typeMap.put("completeCount",completeCount);
				}
			}
		}
		insuAll.put("name","全部");
		insuAll.put("count",insuAllCount);
		insuAll.put("submitCount",insuAllSubmit);
		insuAll.put("todoCount",insuAllTodo);
		insuAll.put("completeCount",insuAllComplete);
		insuTypeMap.put("type-1",insuAll);
		//补全其他
		if(!insuTypeMap.containsKey("type0")){
			Map<String,Object> typeMap = new HashMap<>();
			typeMap = new HashMap<>();
			typeMap.put("count",0);
			typeMap.put("submitCount",0);
			typeMap.put("todoCount",0);
			typeMap.put("completeCount",0);
			typeMap.put("name","其他");

			insuTypeMap.put("type0",typeMap);

		}
		fundAll.put("name","全部");
		fundAll.put("count",fundAllCount);
		fundAll.put("submitCount",fundAllSubmit);
		fundAll.put("todoCount",fundAllTodo);
		fundAll.put("completeCount",fundAllComplete);
		fundTypeMap.put("type-1",fundAll);
		//补全其他
		if(!fundTypeMap.containsKey("type0")){
			Map<String,Object> typeMap = new HashMap<>();
			typeMap = new HashMap<>();
			typeMap.put("count",0);
			typeMap.put("submitCount",0);
			typeMap.put("todoCount",0);
			typeMap.put("completeCount",0);
			typeMap.put("name","其他");

			fundTypeMap.put("type0",typeMap);
		}

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("insuType",insuTypeMap);
		resultMap.put("fundType",fundTypeMap);
		return resultMap;
	}

	/**
	 * id查询实体
	 * @param corp
	 * @return
     */
	@Override
	public CmCorp findByPk(CmCorp corp) {
		return cmCorpDao.findByPK(corp);
	}
	//----------cs 合并 end------------


	/**
	 * 福利平台更新企业信息
	 * @param cti
	 * @param cmCorp
	 * @return
	 */
	@Override
	public Result updateInfoWelfare(ContextInfo cti,CmCorp cmCorp){
		Result result = new Result();
		CmCorp corp = new CmCorp();
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getShortName())){
			result.setSuccess(false).setError("企业简称不能为空");
			return result;
		}
		corp.setShortName(cmCorp.getShortName().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getMobile())){
			result.setSuccess(false).setError("手机号不能为空");
			return result;
		}
		if(!MobileValidator.isMobileNO(cmCorp.getMobile())){
			result.setSuccess(false).setError("手机号格式错误");
			return result;
		}

		corp.setMobile(cmCorp.getMobile().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getContactPsn())){
			result.setSuccess(false).setError("联系人不能为空");
			return result;
		}
		corp.setContactPsn(cmCorp.getContactPsn().trim());
		if(com.xfs.common.util.StringUtils.isBlank(cmCorp.getMail())){
			result.setSuccess(false).setError("邮箱不能为空");
			return result;
		}
		if(cmCorp.getMail().indexOf("@") < 0){
			result.setSuccess(false).setError("邮箱格式错误");
			return result;
		}
		corp.setMail(cmCorp.getMail().trim());
		if(cmCorp.getCpAddressAreaId() != null && cmCorp.getCpAddressAreaId() > 0){
			corp.setCpAddressAreaId(cmCorp.getCpAddressAreaId());
		}
		if(com.xfs.common.util.StringUtils.isNotBlank(cmCorp.getCpAddress())){
			corp.setCpAddress(cmCorp.getCpAddress());
		}
		corp.setCpId(cti.getOrgId());
		if(cmCorpDao.update(cti, corp) > 0){
			result.setSuccess(true).setError("保存成功");
		}else{
			result.setSuccess(false).setError("保存失败");
		}
		return result;
	}

	@Override
	public PageModel findBillInfo(PageInfo pi, SpsBill vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillDao.countPbillInfo(vo);// 查询总数
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsBillDao.findPbillInfo(vo, pageIndex, pageSize);// 查询列表
		pm.setDatas(datas);
		return pm;
	}


	@Override
	public Result getPackageCode(CorpInfo corpInfo) {
		Result result = Result.createResult().setSuccess(false);
		if (corpInfo != null && !com.xfs.common.util.StringUtils.isBlank(corpInfo.getPhone())) {
			if (com.xfs.common.util.StringUtils.isMobileNO(corpInfo.getPhone())) {
				try {
					Object obj = RedisUtil.get(corpInfo.getPhone());
					if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
						obj = (int)(Math.random() * 9000 + 1000);
					}
					// 验证码 600秒有效期
					boolean flag = RedisUtil.set(corpInfo.getPhone(), obj, 600);
					if (flag) {
						boolean smsFlag = SmsUtil.sendVerificationCode(corpInfo.getPhone(),
								"【薪福社】验证码:" + obj + ",您正在注册用友薪福社商城,如不是本人操作,请忽略！");
						result.setSuccess(true);
						log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
						result.setDataValue("msg", "短信发送成功，请注意查收...");
					} else {
						result.setDataValue("msg", "验证码生成失败...");
					}

				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败...");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码...");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码...");
		}
		return result;
	}

    @Override
    public PageModel findPageBillTemp(ContextInfo cti,PageInfo pi, CmCorp vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        // 获取数据权限
        vo.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
        Integer total = cmCorpDao.countFreeFindBySpIdMap(vo);// 查询当前服务商管理的所有企业总数
        pm.setTotal(total);
        List<Map<String, Object>> datas = cmCorpDao.freeFindBySpIdMap(vo, pageIndex, pageSize);// 查询当前服务商管理的企业列表
        pm.setDatas(datas);
        return pm;
    }

	/**
	 *  为服务机构
	 *  @param   sendPageSpId 发包方
	 *  @param   receivePageSpId 接包方
	 *  @param   receivePageUserId 接包人ID
	 *  @param   scoure  来源业务标识
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-23 16:27
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-23 16:27
	 *  @updateAuthor  :
	 */
	@Override
	public Result openSpInventedCorp(ContextInfo cti,Integer sendPageSpId,Integer receivePageSpId,Integer receivePageUserId,String scoure){
		Result result = Result.createResult().setSuccess(false);
		/**
		 * 判断当前发包机构是否存在虚拟企业
		 */
		SpService spService = spServiceService.findByPk(sendPageSpId);
		/**
		 * 为发包机构创建虚拟企业
		 */
		CmCorp cmCorp = null;
		if(null != spService && null == spService.getCpId()){
			cmCorp = new CmCorp();
			cmCorp.setSource(scoure);
			cmCorp.setCpAddressArea(spService.getAreaId());
			cmCorp.setCpAddress(spService.getAddress());
			cmCorp.setCorpName(spService.getSpName()+"（协作）");
			if(StringUtils.isBlank(spService.getShortName()))
				cmCorp.setShortName(spService.getSpName()+"（协作）");
			else
				cmCorp.setShortName(spService.getShortName()+"（协作）");
			cmCorp.setContactPsn(spService.getContactor());
			cmCorp.setMobile(spService.getMobile());
			cmCorp.setMail(spService.getSpEmail());
			cmCorp.setContactPsn(cti.getRealName());
			if(null != receivePageUserId)
				cmCorp.setCollaborator(receivePageUserId);
			cmCorp.setUnComplete("N");
			cmCorp.setCreateDt(new Date());
			cmCorp.setSpId(receivePageSpId);

			CmCorp isExistCmCorp = findByCorpName(cmCorp);
			if(null == isExistCmCorp){
				/**
				 * 不存在当前企业信息
				 */
				cmCorpDao.insert(cti, cmCorp);
			}else{
				/**
				 * 存在企业信息
				 */
				cmCorp.setCpId(isExistCmCorp.getCpId());
			}
			/**
			 * 将发包机构虚拟企业与机构绑定
			 */
			if(null != cmCorp.getCpId()){
				spService.setCpId(cmCorp.getCpId());
				spServiceService.update(cti,spService);
			}
		}
		/**
		 * 判断当前接包方与虚拟企业是否建立关系
		 */
		SpCmRelation spCmRelation = spCmRelationService.findBySpIdAndCpId(receivePageSpId,spService.getCpId());
		if(null == spCmRelation){
			if(null == cmCorp)
				cmCorp = findByPK(spService.getCpId());
			/**
			 * 创建虚拟企业与接包方关系
			 */
			spCmRelation = new SpCmRelation();
			spCmRelation.setSpId(receivePageSpId);
			//创建企业与服务商关系
			createSpCmRelation(cti,spCmRelation,cmCorp,scoure,2);
			//创建企业默认方案
			createCorpDefaultScheme(cti,cmCorp);
			//保存后  添加 客服权限
			//查询当前接包方下 薪福邦操作员角色用户列表
			Integer roleId = 9;
			List<SysUserRole> list = sysUserRoleService.findUserListByRoleId(cmCorp.getSpId(),roleId);
				for (SysUserRole sysUserRole : list) {
					addUserFundData(cti,cmCorp,sysUserRole.getUserId(),CMCORPTYPE_SERVICE);
				}
		}
		return result;
	}

	/**
	 *  修改虚拟企业信息
	 *  @param   cti
	 *  @param   spId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-30 10:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-30 10:53
	 *  @updateAuthor  :
	 */
	@Override
	public Result modifySpInventedCorp(ContextInfo cti,Integer spId){
		Result result = Result.createResult().setSuccess(true);
		/**
		 * 获取服务机构信息
		 */
		SpService spService = spServiceService.findByPk(spId);
		if(null != spService && null != spService.getCpId()){
			/**
			 * 修改完善企业信息
			 */
			CmCorp cmCorp = findByPK(spService.getCpId());
			cmCorp.setShortName(spService.getShortName());
			cmCorp.setCpAddressArea(spService.getAreaId());
			cmCorp.setCpAddress(spService.getAddress());
			cmCorp.setMail(spService.getSpEmail());
			cmCorp.setContactPsn(spService.getContactor());
			update(cti,cmCorp);
		}
		return result;
	}

	@Override
	public List freeFindCorpIdsBySpId(ContextInfo cti, CmCorp vo) {
		// 获取数据权限
		List numList = new ArrayList();
		vo.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		List<Map<String, Object>> datas = cmCorpDao.freeFindCorpIdsBySpId(vo);// 查询当前服务商管理的企业列表
		for (Map<String, Object> data: datas) {
			String cp_id  = data.get("cp_id").toString();
			numList.add(cp_id);
		}

		return numList;
	}
/**
 * 更改服务商 服务的企业公司 状态
 */
	@Override
	public Result updateIfStop(ContextInfo cti,CmCorp cmCorp) {
		Result result = Result.createResult().setSuccess(false);
		Integer ups = cmCorpDao.updateSpCmRelationIfStop(cti, cmCorp);
		if(0<ups){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 *  根据租户ID获取企业信息
	 *  @param   tenantId
	 *	@return 			: com.xfs.corp.model.CmCorp
	 *  @createDate  	: 2017-05-17 15:56
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-17 15:56
	 *  @updateAuthor  :
	 */
	@Override
	public CmCorp findCmCorpByTenantId(String tenantId){
		return cmCorpDao.findCmCorpByTenantId(tenantId);
	}

	/**
	 *  获取未同步的企业列表
	 *  @param
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.corp.model.CmCorp>
	 *  @createDate  	: 2017-05-22 15:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-22 15:17
	 *  @updateAuthor  :
	 */
	public Map<String,CmCorp> queryAllUnSynList(){
		List<CmCorp> cmCorpList = cmCorpDao.queryAllUnSynList();
		Map<String,CmCorp> corps = new HashMap<>();
		for(CmCorp corp : cmCorpList){
			corps.put(String.valueOf(corp.getCpId()),corp);
		}
		return corps;
	}

	public List<CmCorp> checkNologinCorpList(){
		return cmCorpDao.checkNologinCorpList();
	}
}