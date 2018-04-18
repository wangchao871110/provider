package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.corp.service.CmEmployeeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 变更员工比例
 * Created by yangf on 2016/10/24.
 */
@Service
public class ChangeRatioParseImpl extends BaseTaskDataParse {
    private static final Logger log = Logger.getLogger(ChangeRatioParseImpl.class);
    @Autowired
    CmEmployeeInsuranceService cmEmployeeInsuranceService;
    @Autowired
    CmEmployeeService cmEmployeeService;

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (BsType.RATIO_INSUR.getCode().equals(spsTask.getBstypeId()) || BsType.RATIO_FUND.getCode().equals(spsTask.getBstypeId())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee cmEmployee, Result result) {
        if(!super.busDataValidity(info,spsTask,businessParame,businessFieldMap,cmEmployee,result))
            return false;
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);

        String month = curMap.get("beginMonth");
        if(StringUtils.isBlank(month)){
            result.setSuccess(false).setError("调整月份不能为空");
            return false;
        }
        if(spsTask.getSpId() == null){
            result.setSuccess(false).setError("服务商id不能为空");
            return false;
        }
        if(StringUtils.isBlank(curMap.get("newRatioId"))){
            result.setSuccess(false).setError("比例id不能为空");
            return false;
        }
        if(StringUtils.isBlank(curMap.get("insuranceId"))){
            result.setSuccess(false).setError("险种不能为空");
            return false;
        }
        if(StringUtils.isBlank(curMap.get("insuranceType"))){
            result.setSuccess(false).setError("险种类型不能为空");
            return false;
        }
        //完成任务单
        spsTask.setType("COMPLETED");
        return true;
    }


    @Override
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(spsTask.getEmpId());
        employee = cmEmployeeService.findByPK(employee);

        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> curMap = JSON.parseObject(spsTask.getJson(),ref);

        List<CmEmployeeInsurance> insurances = new ArrayList<>();
        String month = curMap.get("beginMonth");
        Integer insuranceId = Integer.parseInt(curMap.get("insuranceId"));
        CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
        employeeInsurance.setInsuranceId(insuranceId);
        if(34 == spsTask.getBstypeId()){
            employeeInsurance.setCorpPaybase(employee.getFundSalary());
            employeeInsurance.setEmpPaybase(employee.getFundSalary());
        }else if(33 == spsTask.getBstypeId()){
            employeeInsurance.setCorpPaybase(employee.getInsuranceSalary());
            employeeInsurance.setEmpPaybase(employee.getInsuranceSalary());
        }
        Integer ratioId = Integer.parseInt(curMap.get("newRatioId"));
        employeeInsurance.setRatioId(ratioId);
        employeeInsurance.setBeginTask(spsTask.getTaskId());
        employeeInsurance.setBeginPeriod(month);
        insurances.add(employeeInsurance);
        employee.setCmEmployeeInsurances(insurances);
        log.info("变更比例生成社保缴费详情：month"+month+",empl="+JSON.toJSONString(employee));
        Result r = cmEmployeeInsuranceService.adjustEmployeeInsurance(info,spsTask.getSpId(),employee,curMap.get("insuranceType"),month,spsTask.getTaskId());
        if (!r.isSuccess()) {
            result.setSuccess(false);
            result.setError(r.getError());
        }

    }
}
