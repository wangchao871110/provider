package com.xfs.aop.task.sps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.util.TaskNoNum;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.enums.InsuranceState;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.sp.dto.HospitalDto;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeEmpService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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
    CmEmployeeDao cmEmployeeDao;
    @Autowired
    private BsAreaDao bsAreaDao;
    @Autowired
    private SpsFixedpointhospitalService spsFixedpointhospitalService;
    @Autowired
    private SysDictitemService sysDictitemService;

    @Autowired
    private SpsSchemeEmpService spsSchemeEmpService;



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

    @Override
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, Result result) {

        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee = cmEmployeeDao.findByPK(cmEmployee);
        System.out.println( BsType.NEW_INSURANCE.code());
        System.out.println( InsuranceState.OFF.name());
        if(BsType.NEW_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.INTO_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.SIGLE_NEW_INSURANCE.code().equals(spsTask.getBstypeId()) || BsType.SIGLE_INTO_INSURANCE.code().equals(spsTask.getBstypeId())){
            if(!InsuranceState.OFF.name().equals(cmEmployee.getInsuranceState()) && !InsuranceState.UNDEAL.name().equals(cmEmployee.getInsuranceState()) && !InsuranceState.DECREASED.name().equals(cmEmployee.getInsuranceState())){
                result.setSuccess(false).setError("人员社保参保状态必须为未缴纳");
                return false;
            }
            List<SpsTask> tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{BsType.REDUCE_INSURANCE.code()},null,new String[]{"TODO","SUBMIT"});
            if(CollectionUtils.isNotEmpty(tasks)){
                result.setSuccess(false).setError("已存进行中的离职任务,不能参保");
                return false;
            }
            //校验任务单重复
            tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{BsType.NEW_INSURANCE.code(),BsType.INTO_INSURANCE.code(),BsType.SIGLE_NEW_INSURANCE.code(),BsType.SIGLE_INTO_INSURANCE.code()},null,new String[]{"TODO","SUBMIT"});
            if(CollectionUtils.isNotEmpty(tasks)){
                result.setSuccess(false).setError("已存在相同任务");
                return false;
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
            String json = verifyXianZhong(curMap.get("insurance"),spsTask.getAreaId(),"INSURANCE",curMap.get("insuranceLiveType"),result);
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

            SpsScheme scheme = new SpsScheme();
            scheme.setSchemeId(spsTask.getSchemeId());
            scheme = spsSchemeService.findByPK(scheme);
            if (null == scheme.getInsuranceAccountId()) {
                result.setSuccess(false).setError("当前服务方案未配置缴纳方式，不能参保!");
                return false;
            }
        }else{
            if(!"OFF".equals(cmEmployee.getFundState()) && !"UNDEAL".equals(cmEmployee.getFundState()) && !"DECREASED".equals(cmEmployee.getFundState())){
                result.setSuccess(false).setError("人员公积金参保状态必须为未缴纳");
                return false;
            }
            List<SpsTask> tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{BsType.QUIT_INSURANCE.code()},null,new String[]{"TODO","SUBMIT"});
            if(CollectionUtils.isNotEmpty(tasks)){
                result.setSuccess(false).setError("已存进行中的离职任务,不能参保");
                return false;
            }
            //校验任务单重复
            tasks = spsTaskService.findTaskByBsTypeAndState(spsTask.getEmpId(),new Integer[]{BsType.ADD_INSURANCE.code(),BsType.UNSEALED_INSURANCE.code(),BsType.INTO_UNSEALED.code()},null,new String[]{"TODO","SUBMIT"});
            if(CollectionUtils.isNotEmpty(tasks)){
                result.setSuccess(false).setError("已存在相同任务");
                return false;
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
            String json = verifyXianZhong(curMap.get("insurance"),spsTask.getAreaId(),"FUND",null,result);
            if(json == null){
                return false;
            }
            curMap.put("insurance",json);
            SpsScheme scheme = new SpsScheme();
            scheme.setSchemeId(spsTask.getSchemeId());
            scheme = spsSchemeService.findByPK(scheme);
            if (null == scheme.getFundAccountId()) {
                result.setSuccess(false).setError("当前服务方案未配置缴纳方式，不能参保!");
                return false;
            }
        }
        spsTask.setJson(JSON.toJSONString(curMap));
        return true;
    }

    /**
     * 校验险种
     */
    private String verifyXianZhong(String insurance, Integer areaId, String insuFundType, String insuranceLivingType, Result result){
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

        BsArea bsArea = new BsArea();
        bsArea.setAreaId(areaId);
        bsArea = bsAreaDao.findByPK(bsArea);
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
    public List<CmEmployeeInsurance> convertJsonToEmpInsu(String json,BigDecimal base){
        List<CmEmployeeInsurance> list = new ArrayList<>();
        TypeReference<List<InsuranceTypeDto>> ref = new TypeReference<List<InsuranceTypeDto>>(){};
        List<InsuranceTypeDto> insurances = JSON.parseObject(json,ref);
        if(CollectionUtils.isNotEmpty(insurances)){
            for(InsuranceTypeDto insu:insurances){
                CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
                employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
                employeeInsurance.setCorpPaybase(insu.getCorpPaybase());
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
        if(ddyy > 4){
            result.setSuccess(false).setError("定点医院不能超过4家");
            return null;
        }
        if(ddyy < 2){
            result.setSuccess(false).setError("定点医院不能少于2家");
            return null;
        }
        if(sqyy > 1){
            result.setSuccess(false).setError("社区医院不能超过1家");
            return null;
        }
        return JSON.toJSONString(hospitalList);
    }
    @Override
    public void dealBeforeSave(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) {

    }

    @Override
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        //
        List<Integer> list = new ArrayList<Integer>();
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        String InsuranceType = "";
        BigDecimal base = null;
        if(spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3){
            base = new BigDecimal(curMap.get("insuranceSalary").trim());
            cmEmployee.setInsuranceState("INCREASING");
            cmEmployee.setInsuranceSalary(new BigDecimal(curMap.get("insuranceSalary").trim()));
            cmEmployee.setInsuranceLivingType((String)curMap.get("insuranceLiveType"));
            cmEmployee.setInsurancePeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            InsuranceType = "INSURANCE";
        }

        if(spsTask.getBstypeId() == 10){
            base = new BigDecimal(curMap.get("fundSalary"));
            cmEmployee.setFundState("INCREASING");
            cmEmployee.setFundSalary(new BigDecimal(curMap.get("fundSalary")));
            cmEmployee.setFundPeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            InsuranceType = "FUND";
        }
        cmEmployee.setSchemeId(spsTask.getSchemeId());
        //员工方案中间表处理
        CmEmployee oldEmp = cmEmployeeService.findByPk(spsTask.getEmpId());
        Result r;
        if (null != oldEmp.getSchemeId()) {
            r = spsSchemeService.transferScheme(info,oldEmp.getSchemeId(), cmEmployee.getSchemeId(), spsTask.getSpId(), cmEmployee.getEmpId().toString());
            if (!r.isSuccess()) {
                result.setSuccess(false);
                result.setError(r.getError());
                return;
            }
        } else {
            spsSchemeEmpService.addSchemeEmp(info, cmEmployee.getEmpId(), cmEmployee.getSchemeId());
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        try {
            cmEmployee.setCmEmployeeInsurances(convertJsonToEmpInsu(curMap.get("insurance"),base));
            for(CmEmployeeInsurance cmEmployeeInsurance : cmEmployee.getCmEmployeeInsurances())
                cmEmployeeInsurance.setExtend(true);
            r = cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),cmEmployee,InsuranceType,null,spsTask.getTaskId());
            if (!r.isSuccess()) {
                result.setSuccess(false);
                result.setError(r.getError());
            }
        } catch (Exception e) {
            log.error("缴费详情计算错误",e);
            throw new RuntimeException("缴费详情计算错误",e);
        }
        if(spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3 || spsTask.getBstypeId() == 22 || spsTask.getBstypeId() == 23){
            //上海地区特殊新增一条招工任务单
            if(spsTask.getAreaId() != null){
                BsArea area = new BsArea();
                area.setAreaId(spsTask.getAreaId());
                area = bsAreaDao.findByPK(area);
                if (area != null && area.getBelongCity() == 100) {
                    SpsTask shTask = new SpsTask();
                    BeanUtils.copyProperties(spsTask,shTask);
                    shTask.setTaskId(null);
                    shTask.setBstypeId(12);

                    String taskNo = TaskNoNum.makeOrderNum();
                    if(taskNo == null){
                        log.error("招工任务单未获取到任务单号");
                    }else{
                        shTask.setTaskNo(taskNo);
                        spsTaskService.save(info,shTask);
                    }
                }
            }
        }
        if (ups >0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }
    }

    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        // 社保(2=增员,3=转入) 、
        if (spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3){
            cmEmployee.setInsuranceState("ON");
        }
        // 公积金(10=增加)
        if (spsTask.getBstypeId() == 10){
            cmEmployee.setFundState("ON");
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups > 0) {
            result.setSuccess(true).setError("完成任务单执行成功");
        }else{
            result.setSuccess(false).setError("完成任务单执行失败！");
        }
    }

    @Override
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        // 社保(2=增员,3=转入) 、
        //2017-01-22 弥磊需求 (社保、公积金)员工实作单终止操作时,员工状态为(增员中、减员中)相应改为(增员中->在缴中;减员中->已减员;)
        if(spsTask.getBstypeId() ==2 || spsTask.getBstypeId() ==3){
            cmEmployee.setInsuranceState("ON");
        }
        // 公积金(10=增加)
        if(spsTask.getBstypeId() ==10){
            cmEmployee.setFundState("ON");
        }

        //cmEmployeeInsuranceService.cancelEmployeeInsuranceByBeginTaskId(info,spsTask.getTaskId());

        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        if (ups >0) {
            result.setSuccess(true).setError("完成任务单执行成功");
        }else{
            result.setSuccess(false).setError("完成任务单执行失败！");
        }
    }

    @Override
    public void dealErrorTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result) {
        CmEmployee cmEmployee = new CmEmployee();
        cmEmployee.setEmpId(spsTask.getEmpId());
        cmEmployee.setCpId(spsTask.getCpId());
        //
        List<Integer> list = new ArrayList<Integer>();
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        String InsuranceType = "";
        BigDecimal base = null;
        if(spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3){
            base = new BigDecimal(curMap.get("insuranceSalary").trim());
            cmEmployee.setInsuranceState("INCREASING");
            cmEmployee.setInsuranceSalary(new BigDecimal(curMap.get("insuranceSalary").trim()));
            cmEmployee.setInsuranceLivingType((String)curMap.get("insuranceLiveType"));
            cmEmployee.setInsurancePeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            InsuranceType = "INSURANCE";
        }

        if(spsTask.getBstypeId() == 10){
            base = new BigDecimal(curMap.get("fundSalary"));
            cmEmployee.setFundState("INCREASING");
            cmEmployee.setFundSalary(new BigDecimal(curMap.get("fundSalary")));
            cmEmployee.setFundPeriod(spsTask.getBeginDate());
            list.add(cmEmployee.getEmpId());
            InsuranceType = "FUND";
        }
        cmEmployee.setSchemeId(spsTask.getSchemeId());
        //员工方案中间表处理
        CmEmployee oldEmp = cmEmployeeService.findByPk(spsTask.getEmpId());
        Result r = spsSchemeService.transferScheme(info,oldEmp.getSchemeId(), cmEmployee.getSchemeId(), spsTask.getSpId(), cmEmployee.getEmpId().toString());
        if (!r.isSuccess()) {
            throw new RuntimeException(r.getMsg());
        }
        Integer ups = cmEmployeeDao.update(info,cmEmployee);
        try {
            cmEmployee.setCmEmployeeInsurances(convertJsonToEmpInsu(curMap.get("insurance"),base));
            for(CmEmployeeInsurance cmEmployeeInsurance : cmEmployee.getCmEmployeeInsurances())
                cmEmployeeInsurance.setExtend(true);
            cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),cmEmployee,InsuranceType,null,spsTask.getTaskId());
        } catch (Exception e) {
            log.error("新参保算费错误",e);
            throw new RuntimeException("缴费详情计算错误",e);
        }
        if(spsTask.getBstypeId() == 2 || spsTask.getBstypeId() == 3 || spsTask.getBstypeId() == 22 || spsTask.getBstypeId() == 23){
            //上海地区特殊新增一条招工任务单
            if(spsTask.getAreaId() != null){
                BsArea area = new BsArea();
                area.setAreaId(spsTask.getAreaId());
                area = bsAreaDao.findByPK(area);
                if(area != null && area.getBelongCity() == 100){
                    SpsTask shTask = new SpsTask();
                    BeanUtils.copyProperties(spsTask,shTask);
                    shTask.setTaskId(null);
                    shTask.setBstypeId(12);
                    String taskNo = TaskNoNum.makeOrderNum();
                    if(taskNo == null){
                        log.error("招工任务单未获取到任务单号");
                    }else{
                        shTask.setTaskNo(taskNo);
                        spsTaskService.save(info,shTask);
                    }
                }
            }
        }
        if (ups >0) {
            result.setSuccess(true);
        }else{
            result.setError("处理失败！");
        }




    }


}
