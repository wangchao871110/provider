package com.xfs.aop.task.sps;


import java.util.Map;

import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;

/**
 * Created by konglc on 2016-08-15.
 * 任务单切面调用类
 */
public interface TaskAspectService {

    public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result);

}
