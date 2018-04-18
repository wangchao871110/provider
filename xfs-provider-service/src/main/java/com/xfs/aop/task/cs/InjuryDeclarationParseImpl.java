package com.xfs.aop.task.cs;

import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 工伤申报
 * @author   zhanghj
 * @create 2017-04-11 16:38
 **/
@Service
public class InjuryDeclarationParseImpl extends BaseTaskDataParse {


    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (null != spsTask && null != spsTask.getBstypeId()) {
            if (BsType.INJURY_DECLARATION.code().equals(spsTask.getBstypeId())) {
                return true;
            }
        }
        return false;
    }

}
