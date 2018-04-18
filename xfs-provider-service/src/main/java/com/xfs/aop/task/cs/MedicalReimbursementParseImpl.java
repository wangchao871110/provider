package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 医疗报销
 *
 * @author
 * @create 2017-04-11 16:23
 **/
@Service
public class MedicalReimbursementParseImpl extends BaseTaskDataParse {
    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.HOSPITALIZATION_REIMBURSEMENT.code().equals(spsTask.getBstypeId()) || BsType.OUTPATIENT_REIMBURSEMENT.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame,CmEmployee cmEmployee, Result result) {
        cmEmployee.setEmpId(spsTask.getEmpId());
        JSONObject json = JSON.parseObject(spsTask.getJson());
        cmEmployee.setMobile(json.getString("mobile"));
        cmEmployee.setName(json.getString("name"));
        cmEmployee.setIdentityCode(json.getString("identity_code"));
        cmEmployeeService.update(info, cmEmployee);
    }
}
