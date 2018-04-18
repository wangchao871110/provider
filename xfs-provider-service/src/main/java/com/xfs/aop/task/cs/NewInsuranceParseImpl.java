package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.service.SysDictitemService;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.corp.enums.InsuranceState;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.sp.dto.HospitalDto;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.service.SpsSchemeEmpService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 增加 转入 社保 公积金
 */
@Service
public class NewInsuranceParseImpl extends BaseTaskDataParse {
    private static final Logger log = Logger.getLogger(NewInsuranceParseImpl.class);

    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;
    @Autowired
    private SpsFixedpointhospitalService spsFixedpointhospitalService;
    @Autowired
    private SysDictitemService sysDictitemService;

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            //社保(2=增员,3=转入) 、公积金(10=增加)
            if (BsType.NEW_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.INTO_INSURANCE.code().equals(spsTask.getBstypeId()) || spsTask.getBstypeId().equals(BsType.ADD_INSURANCE.code())){
                return true;
            }
        }
        return false;
    }

    /**
     * 业务数据校验
     * @param info
     * @param spsTask
     * @param businessParame
     * @param businessFieldMap
     * @param cmEmployee
     * @param result
     * @return
     */
    @Override
	public boolean busDataValidity(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap,CmEmployee cmEmployee, Result result) {
        if(!super.busDataValidity(info,spsTask,businessParame,businessFieldMap,cmEmployee,result))
            return false;
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        if(BsType.NEW_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.INTO_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.SIGLE_NEW_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.SIGLE_INTO_INSURANCE.code().equals(spsTask.getBstypeId())){
            if(!InsuranceState.OFF.name().equals(cmEmployee.getInsuranceState()) && !InsuranceState.UNDEAL.name().equals(cmEmployee.getInsuranceState()) && !InsuranceState.DECREASED.name().equals(cmEmployee.getInsuranceState()) && !InsuranceState.INCREASING.name().equals(cmEmployee.getInsuranceState())){
                if(null == spsTask.getTaskId()){
                    result.setSuccess(false).setError("人员社保参保状态必须为未缴纳");
                    return false;
                }
            }
            if(StringUtils.isBlank(curMap.get("insuranceSalary"))){
                result.setSuccess(false).setError("社保申报工资为空");
                return false;
            }
            try {
                new BigDecimal(curMap.get("insuranceSalary").trim());
            } catch (NumberFormatException e) {
                result.setSuccess(false).setError("社保申报工资格式错误");
                return false;
            }
            if(StringUtils.isBlank(curMap.get("insuranceLiveType"))){
                result.setSuccess(false).setError("社保参保类型为空");
                return false;
            }
            //补全参保方式name 帅哥用
            List<SysDictitem> dictitems = sysDictitemService.findByDictNameAndArea("insuranceLiveType_doc",spsTask.getAreaId());
            if(CollectionUtils.isNotEmpty(dictitems)){
                for(SysDictitem dictitem:dictitems){
                    if(dictitem.getCode().equals(curMap.get("insuranceLiveType"))){
                        curMap.put("insuranceLiveType_name",dictitem.getName());
                        break;
                    }
                }
            }
            String json = verifyXianZhong(curMap.get("insurance"),result);
            if(json == null){
                return false;
            }
            curMap.put("insurance",json);
            if(businessFieldMap.get("hospital") != null && curMap.get("hospital")!=null){
                json = verifyHospital(curMap.get("hospital"),result);
                if(json == null){
                    return false;
                }
            }
            
            if (null == cmEmployee.getScheme().getInsuranceAccountId()) {
                result.setSuccess(false).setError("当前服务方案未配置缴纳方式，不能参保!");
                return false;
            }
        }else{
            if(!InsuranceState.OFF.name().equals(cmEmployee.getFundState()) && !InsuranceState.UNDEAL.name().equals(cmEmployee.getFundState()) && !InsuranceState.DECREASED.name().equals(cmEmployee.getFundState()) && !InsuranceState.INCREASING.name().equals(cmEmployee.getFundState())){
                if(null == spsTask.getTaskId()){
                    result.setSuccess(false).setError("人员公积金参保状态必须为未缴纳");
                    return false;
                }
            }
            if(StringUtils.isBlank(curMap.get("fundSalary"))){
                result.setSuccess(false).setError("公积金申报工资为空");
                return false;
            }
            try {
                new BigDecimal(curMap.get("fundSalary").trim());
            } catch (NumberFormatException e) {
                result.setSuccess(false).setError("公积金申报工资格式错误");
                return false;
            }
            String json = verifyXianZhong(curMap.get("insurance"),result);
            if(json == null){
                return false;
            }
            curMap.put("insurance",json);
            if (null == cmEmployee.getScheme().getFundAccountId()) {
                result.setSuccess(false).setError("当前服务方案未配置缴纳方式，不能参保!");
                return false;
            }
        }
        spsTask.setJson(JSON.toJSONString(curMap));
        return true;
	}

    /**
     * TODO 任务单处理
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
		TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
		Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);

        if(spsTask.getBstypeId() == BsType.NEW_INSURANCE.code() || spsTask.getBstypeId() == BsType.INTO_INSURANCE.code()){
            cmEmployee.setInsuranceState(InsuranceState.INCREASING.name());
            cmEmployee.setInsuranceSalary(new BigDecimal(curMap.get("insuranceSalary").trim()));
            cmEmployee.setInsurancePeriod(spsTask.getBeginDate());
        }

        if(spsTask.getBstypeId() == BsType.ADD_INSURANCE.code()){
            cmEmployee.setFundState(InsuranceState.INCREASING.name());
            cmEmployee.setFundSalary(new BigDecimal(curMap.get("fundSalary")));
            cmEmployee.setFundPeriod(spsTask.getBeginDate());
        }
        //获取人员户口性质
        //cmEmployee.setInsuranceLivingType(curMap.get("insuranceLiveType"));
        //获取人员头像
        cmEmployee.setHeadPhoto(curMap.get("photo"));
		if (null == cmEmployee.getSchemeId()) {
			cmEmployee.setSchemeId(cmEmployee.getScheme().getSchemeId());
		}
		//设置员工新方案
        cmEmployee.setSchemeId(spsTask.getSchemeId());
    }

    /**
     * COMPLATED 任务单处理
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        String InsuranceType = "";
        BigDecimal base = null;
        // 社保(2=增员,3=转入) 、
        if((spsTask.getBstypeId() == BsType.NEW_INSURANCE.code() || spsTask.getBstypeId() == BsType.INTO_INSURANCE.code())){
            InsuranceType = BsType.NEW_INSURANCE.getInsurance_fund_type();
            base = new BigDecimal(curMap.get("insuranceSalary").trim());
            cmEmployee.setInsuranceSalary(base);
            cmEmployee.setInsuranceState(InsuranceState.ON.name());
        }
    	// 公积金(10=增加)
        if(spsTask.getBstypeId() == BsType.ADD_INSURANCE.code()){
            cmEmployee.setFundState(InsuranceState.ON.name());
            base = new BigDecimal(curMap.get("fundSalary"));
            cmEmployee.setFundSalary(base);
            InsuranceType = BsType.ADD_INSURANCE.getInsurance_fund_type();
        }
        /**
         * 修改人员基本信息
         */
        cmEmployee.setMobile(curMap.get("mobile"));
        cmEmployee.setInsuranceLivingType(curMap.get("insuranceLiveType"));
        cmEmployee.setHeadPhoto(curMap.get("photo"));
        cmEmployee.setIdentityCode(curMap.get("identity_code"));
        spsTask.setMobile(curMap.get("mobile"));
        /**
         * 成功后进行算费
         */
        try {
            cmEmployee.setCmEmployeeInsurances(convertJsonToEmpInsu(spsTask,curMap.get("insurance"),base));
            for(CmEmployeeInsurance cmEmployeeInsurance : cmEmployee.getCmEmployeeInsurances())
                cmEmployeeInsurance.setExtend(true);
            result = cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),cmEmployee,InsuranceType,null,spsTask.getTaskId());
            if (!result.isSuccess()) {
                result.setSuccess(false);
                result.setError(result.getError());
            }
        } catch (Exception e) {
            log.error("缴费详情计算错误",e);
            throw new RuntimeException("缴费详情计算错误",e);
        }
    }

    /**
     * CLOSED 任务单处理
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
    	if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType())){
            if((spsTask.getBstypeId() == BsType.NEW_INSURANCE.code() || spsTask.getBstypeId() == BsType.INTO_INSURANCE.code())){
                cmEmployee.setInsuranceState(InsuranceState.OFF.name());
            }
            if(spsTask.getBstypeId() == BsType.ADD_INSURANCE.code()){
                cmEmployee.setFundState(InsuranceState.OFF.name());
            }
            cmEmployeeInsuranceService.cancelEmployeeInsuranceByBeginTaskId(info,spsTask.getTaskId());
        }else{
            if((spsTask.getBstypeId() == BsType.NEW_INSURANCE.code() || spsTask.getBstypeId() == BsType.INTO_INSURANCE.code())){
                cmEmployee.setInsuranceState(InsuranceState.ON.name());
            }
            if(spsTask.getBstypeId() == BsType.ADD_INSURANCE.code()){
                cmEmployee.setFundState(InsuranceState.ON.name());
            }
        }
    }

    /**
     * ERROR 任务单处理
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealErrorTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType())){
            cmEmployeeInsuranceService.cancelEmployeeInsuranceByBeginTaskId(info,spsTask.getTaskId());
        }
    }

    /**
     * SUBMIT 任务单处理
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     */
    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result) {
        /**
         * 算费逻辑与TODO 方法一致
         */
        dealToDoTask(info,spsTask,businessParame,cmEmployee,result);

        /**
         * 参保后人员状态变更为ON
         */
        if(spsTask.getBstypeId() == BsType.NEW_INSURANCE.code() || spsTask.getBstypeId() == BsType.INTO_INSURANCE.code()){
            cmEmployee.setInsuranceState(InsuranceState.ON.name());
        }
        if(spsTask.getBstypeId() == BsType.ADD_INSURANCE.code()){
            cmEmployee.setFundState(InsuranceState.ON.name());
        }
    }

    /**
     * 校验险种
     */
    private String verifyXianZhong(String insurance, Result result){
        if(StringUtils.isBlank(insurance)){
            result.setSuccess(false).setError("至少选择一种险种");
            return null;
        }
        TypeReference<List<InsuranceTypeDto>> ref = new TypeReference<List<InsuranceTypeDto>>(){};
        List<InsuranceTypeDto> insurances = null;
        try {
            insurances = JSON.parseObject(insurance,ref);
        } catch (Exception e) {
            result.setSuccess(false).setError("险种格式错误");
            return null;
        }

        for(InsuranceTypeDto insutype: insurances){
            if(insutype.getCompanyRatio() == null){
                result.setSuccess(false).setError(insutype.getInsuranceName()+"单位比例不能为空");
                return null;
            }
            if(insutype.getPersonalRatio() == null){
                result.setSuccess(false).setError(insutype.getInsuranceName()+"个人比例不能为空");
                return null;
            }
            BigDecimal compRatio = insutype.getCompanyRatio();
            compRatio = compRatio.divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP);
            BigDecimal psnRatio = insutype.getPersonalRatio();
            psnRatio = psnRatio.divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP);
            insutype.setCompanyRatio(compRatio);
            insutype.setPersonalRatio(psnRatio);
        }
        return JSON.toJSONString(insurances);
    }

    /**
     * 将险种json转化为计算list
     * @param json
     * @return
     */
    public List<CmEmployeeInsurance> convertJsonToEmpInsu(SpsTask spsTask,String json,BigDecimal base){
        List<CmEmployeeInsurance> list = new ArrayList<>();
        TypeReference<List<InsuranceTypeDto>> ref = new TypeReference<List<InsuranceTypeDto>>(){};
        List<InsuranceTypeDto> insurances = JSON.parseObject(json,ref);
        if(CollectionUtils.isNotEmpty(insurances)){
            for(InsuranceTypeDto insu:insurances){
                CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
                employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
                if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType()))
                    employeeInsurance.setCorpPaybase(base);
                else
                    employeeInsurance.setCorpPaybase(insu.getCorpPaybase());
                if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType()))
                    employeeInsurance.setEmpPaybase(base);
                else
                    employeeInsurance.setEmpPaybase(insu.getEmpPaybase());
                employeeInsurance.setRatioId(insu.getRatioId());
                employeeInsurance.setEmpRatio(insu.getPersonalRatio());
                employeeInsurance.setCorpRatio(insu.getCompanyRatio());
                employeeInsurance.setBeginPeriod(insu.getBeginDate());
                list.add(employeeInsurance);
            }
        }
        return list;
    }
    /**
     * 校验定点医院
     * @return
     */
    private String verifyHospital(String hospitals,Result result){
        if(StringUtils.isBlank(hospitals)){
            result.setSuccess(false).setError("定点医院不能为空");
            return null;
        }
        TypeReference<List<HospitalDto>> ref = new TypeReference<List<HospitalDto>>(){};
        List<HospitalDto> hospitalList = null;
        try {
            hospitalList = JSON.parseObject(hospitals,ref);
        } catch (Exception e) {
            result.setSuccess(false).setError("定点医院格式错误");
            return null;
        }
        SpsFixedpointhospital query = new SpsFixedpointhospital();
        Integer ddyy = 0;
        Integer sqyy = 0;
        for(HospitalDto hospitalDto: hospitalList){
            if(StringUtils.isBlank(hospitalDto.getCode())){
                result.setSuccess(false).setError("定点医院代码不能为空");
                return null;
            }
            if(StringUtils.isBlank(hospitalDto.getLevel())){
                result.setSuccess(false).setError("定点医院等级不能为空");
                return null;
            }
            query.setHospitalCodeEq(hospitalDto.getCode());
            if(CollectionUtils.isEmpty(spsFixedpointhospitalService.findAll(query))){
                result.setSuccess(false).setError("定点医院["+hospitalDto.getName()+"]不存在");
                return null;
            }
            if(hospitalDto.getLevel().indexOf("社区") > -1){
                sqyy++;
            }else {
                ddyy++;
            }
        }
        return JSON.toJSONString(hospitalList);
    }

}
