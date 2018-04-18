package com.xfs.aop.task.cs;

import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公积金提取
 *
 * @author
 * @create 2017-04-11 16:20
 **/
@Service
public class FundExtractParseImpl extends BaseTaskDataParse {
    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.NEW_HOUSE_CHAMBER.code().equals(spsTask.getBstypeId()) || BsType.SECOND_HOUSE_CHAMBER.code().equals(spsTask.getBstypeId()) || BsType.NO_HOUSE_CHAMBER.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }
}
