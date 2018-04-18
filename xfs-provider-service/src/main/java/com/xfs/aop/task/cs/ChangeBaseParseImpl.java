package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.corp.service.CmEmployeeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 变更员工基数
 * Created by yangf on 2016/10/24.
 */
@Service
public class ChangeBaseParseImpl extends BaseTaskDataParse {
    private static final Logger log = Logger.getLogger(ChangeBaseParseImpl.class);
    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;
    @Autowired
    CmEmployeeService cmEmployeeService;
    @Autowired
    NewInsuranceParseImpl newInsuranceParseImpl;

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (BsType.INSUR_PAYBASE.code().equals(spsTask.getBstypeId())  || BsType.FUND_PAYBASE.code().equals(spsTask.getBstypeId()) || BsType.LOWRE_INSUR_PAYBASE.code().equals(spsTask.getBstypeId()) || BsType.LOWER_FUND_PAYBASE.code().equals(spsTask.getBstypeId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee employee, Result result) {
        if(!super.busDataValidity(info,spsTask,businessParame,businessFieldMap,employee,result))
            return false;
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        String month = curMap.get("beginPeriod");
        if(StringUtils.isBlank(month)){
            result.setSuccess(false).setError("调整月份不能为空");
            return false;
        }
        if(BsType.INSUR_PAYBASE.code().equals(spsTask.getBstypeId()) || BsType.LOWRE_INSUR_PAYBASE.code().equals(spsTask.getBstypeId())){
            String insuranceSalaryStr = curMap.get("insuranceSalary");
            if(StringUtils.isBlank(insuranceSalaryStr)){
                result.setSuccess(false).setError("社保基数不能为空");
                return false;
            }
            BigDecimal insuranceSalary = BigDecimal.ZERO;
            try{
                insuranceSalary = new BigDecimal(insuranceSalaryStr);
            }catch (Exception e){
                result.setSuccess(false).setError("社保基数格式错误");
                return false;
            }
            if(insuranceSalary.compareTo(BigDecimal.ZERO) > 0)
                employee.setInsuranceSalary(insuranceSalary);
        }else {
            String fundSalaryStr = curMap.get("fundSalary");
            if(StringUtils.isBlank(fundSalaryStr)){
                result.setSuccess(false).setError("公积金基数不能为空");
                return false;
            }
            BigDecimal fundSalary = BigDecimal.ZERO;
            try{
                fundSalary = new BigDecimal(fundSalaryStr);
            }catch (Exception e){
                result.setSuccess(false).setError("公积金基数格式错误");
                return false;
            }
            if(fundSalary.compareTo(BigDecimal.ZERO) > 0)
                employee.setFundSalary(fundSalary);
        }
        return true;
    }

    @Override
    public boolean busBeforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cur_employee, Result result) {
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        if(null != businessParame && null != businessParame.get("INSDETAIL"))
            return true;
        if(IStaticVarConstant.CMCORPTYPE_SELFSERVICE.equals(spsTask.getCompanyType())){
            List<Integer> empIds = new ArrayList<>();
            empIds.add(spsTask.getEmpId());
            if(BsType.INSUR_PAYBASE.code().equals(spsTask.getBstypeId())){
                String beginDate = StringUtils.isBlank(curMap.get("beginPeriod")) ? curMap.get("beginDate") : curMap.get("beginPeriod");
                List<CmEmployee> employees = cmEmployeeService.findEmpListWithDetailByEmpids(empIds,beginDate,"INSURANCE");
                if(!CollectionUtils.isNotEmpty(employees) ){
                    spsTask.setBstypeId(BsType.NEW_INSURANCE.code());
                    BigDecimal base = new BigDecimal(curMap.get("insuranceSalary").trim());
                    try {
                        cur_employee.setCmEmployeeInsurances(newInsuranceParseImpl.convertJsonToEmpInsu(spsTask,curMap.get("insurance"),base));
                        for(CmEmployeeInsurance cmEmployeeInsurance : cur_employee.getCmEmployeeInsurances())
                            cmEmployeeInsurance.setExtend(true);
                        cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),cur_employee,"INSURANCE",null,spsTask.getTaskId());
                    } catch (Exception e) {
                        log.error("缴费详情计算错误",e);
                        throw new RuntimeException("缴费详情计算错误",e);
                    }
                    spsTask.setBstypeId(BsType.INSUR_PAYBASE.code());
                    cur_employee.setInsuranceState("ON");
                    spsTask.setType("COMPLETED");
                    result.setSuccess(false);
                }
            }
            if(BsType.FUND_PAYBASE.code().equals(spsTask.getBstypeId())){
                String beginDate = StringUtils.isBlank(curMap.get("beginPeriod")) ? curMap.get("beginDate") : curMap.get("beginPeriod");
                List<CmEmployee> fundemployees = cmEmployeeService.findEmpListWithDetailByEmpids(empIds,beginDate,"FUND");
                if(!CollectionUtils.isNotEmpty(fundemployees)){
                    spsTask.setBstypeId(BsType.ADD_INSURANCE.code());
                    BigDecimal base = new BigDecimal(curMap.get("fundSalary").trim());
                    try {
                        cur_employee.setCmEmployeeInsurances(newInsuranceParseImpl.convertJsonToEmpInsu(spsTask,curMap.get("insurance"),base));
                        for(CmEmployeeInsurance cmEmployeeInsurance : cur_employee.getCmEmployeeInsurances())
                            cmEmployeeInsurance.setExtend(true);
                        cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),cur_employee,"FUND",null,spsTask.getTaskId());
                    } catch (Exception e) {
                        log.error("缴费详情计算错误",e);
                        throw new RuntimeException("缴费详情计算错误",e);
                    }
                    spsTask.setBstypeId(BsType.FUND_PAYBASE.code());
                    cur_employee.setFundState("ON");
                    spsTask.setType("COMPLETED");
                    result.setSuccess(false);
                }
            }
        }
        return result.isSuccess();
    }


    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee employee, Result result) {
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);
        List<Integer> empIds = new ArrayList<>();
        empIds.add(spsTask.getEmpId());
        if(BsType.INSUR_PAYBASE.code().equals(spsTask.getBstypeId()) || BsType.LOWRE_INSUR_PAYBASE.code().equals(spsTask.getBstypeId())){
            String items = curMap.get("insuranceItems");
            List<Integer> insuraceIds = new ArrayList<>();
            if(StringUtils.isNotBlank(items)){
                try {
                    insuraceIds = JSON.parseArray(items, Integer.class);
                }catch (Exception e){
                    log.error("变更基数更新缴费详细 转换险种id错误：险种id json："+items);
                }
            }
            List<CmEmployee> employees = cmEmployeeService.findEmpListWithDetailByEmpids(empIds,curMap.get("beginPeriod"),"INSURANCE");
            if(CollectionUtils.isNotEmpty(employees)){
                employee = employees.get(0);
                //去掉比例ID/月份
                for(CmEmployeeInsurance insurance : employee.getCmEmployeeInsurances()){
                    insurance.setRatioId(null);
                    insurance.setBeginPeriod(null);
                }
            }
            if(CollectionUtils.isNotEmpty(employee.getCmEmployeeInsurances())){
                for (Iterator iter = employee.getCmEmployeeInsurances().iterator(); iter.hasNext();) {
                    CmEmployeeInsurance cmEmployeeInsurance = (CmEmployeeInsurance)iter.next();
                    if(!insuraceIds.contains(cmEmployeeInsurance.getInsuranceId())){
                        iter.remove();
                    }else if(BsType.INSUR_PAYBASE.code().equals(spsTask.getBstypeId())){
                        cmEmployeeInsurance.setEmpPaybase(employee.getInsuranceSalary());
                        cmEmployeeInsurance.setCorpPaybase(employee.getInsuranceSalary());
                    }
                }
            }

            if(CollectionUtils.isNotEmpty(employee.getCmEmployeeInsurances())){
                cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),employee,"INSURANCE",curMap.get("beginPeriod"),spsTask.getTaskId());
            }
        }else if(BsType.FUND_PAYBASE.code().equals(spsTask.getBstypeId()) || BsType.LOWER_FUND_PAYBASE.code().equals(spsTask.getBstypeId())){
            String items = curMap.get("fundItems");
            List<Integer> insuraceIds = new ArrayList<>();
            if(StringUtils.isNotBlank(items)){
                try {
                    insuraceIds = JSON.parseArray(items, Integer.class);
                }catch (Exception e){
                    log.error("变更基数更新缴费详细 转换险种id错误：险种id json："+items);
                }
            }
            List<CmEmployee> employees = cmEmployeeService.findEmpListWithDetailByEmpids(empIds,curMap.get("beginPeriod"),"FUND");
            if(CollectionUtils.isNotEmpty(employees)){
                employee = employees.get(0);
                //去掉比例ID/月份
                for(CmEmployeeInsurance insurance : employee.getCmEmployeeInsurances()){
                    insurance.setRatioId(null);
                    insurance.setBeginPeriod(null);
                }
            }
            if(CollectionUtils.isNotEmpty(employee.getCmEmployeeInsurances())){
                for (Iterator iter = employee.getCmEmployeeInsurances().iterator(); iter.hasNext();) {
                    CmEmployeeInsurance cmEmployeeInsurance = (CmEmployeeInsurance)iter.next();
                    if(!insuraceIds.contains(cmEmployeeInsurance.getInsuranceId())){
                        iter.remove();
                    }else if(BsType.FUND_PAYBASE.code().equals(spsTask.getBstypeId())){
                        cmEmployeeInsurance.setCorpPaybase(employee.getFundSalary());
                        cmEmployeeInsurance.setEmpPaybase(employee.getFundSalary());
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(employee.getCmEmployeeInsurances())){
                cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),employee,"FUND",curMap.get("beginPeriod"),spsTask.getTaskId());
            }
        }


    }

}
