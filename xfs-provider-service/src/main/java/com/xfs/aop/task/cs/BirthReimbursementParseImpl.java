package com.xfs.aop.task.cs;

import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 生育报销
 * @author
 * @create 2017-04-11 16:30
 **/
@Service
public class BirthReimbursementParseImpl extends BaseTaskDataParse {

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.BIRTH_REIMBURSEMENT.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }
}
