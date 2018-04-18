package com.xfs.aop.task.cs;


import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;

import java.util.Map;

/**
 * Created by konglc on 2016-08-15.
 * 任务单切面调用类
 */
public interface CorpTaskAspectService {

    public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result);

    public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result, TaskExecuteType taskExecuteType);

}
