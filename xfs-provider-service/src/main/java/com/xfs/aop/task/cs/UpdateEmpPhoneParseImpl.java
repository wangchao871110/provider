package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dto.BusinessField;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;
import org.springframework.stereotype.Service;

import java.util.Map;

/** 修改员工手机号
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-23 17:20
 */
@Service
public class UpdateEmpPhoneParseImpl extends BaseTaskDataParse {

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.MODIFY_PHONE.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean busDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, CmEmployee cmEmployee, Result result) {
        return super.busDataValidity(info, spsTask, businessParame, businessFieldMap, cmEmployee, result);
    }

    @Override
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee ,Result result) {
        JSONObject json = JSON.parseObject(spsTask.getJson());
        cmEmployee.setMobile(json.getString("mobile"));
    }
}
