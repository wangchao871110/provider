package com.xfs.aop.task.cs;

import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmEmployeeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by konglc on 2016-08-01.
 *
 * 提交任务单切面类
 *
 */
@Component
@Aspect
public class TaskAspect {

    @Autowired
    private CmEmployeeService cmEmployeeService;

    private List<BaseTaskDataParse> taskDataParses = new ArrayList<>();

    /**
     * 增加委托事件对象
     * @param taskDataParse
     */
    public void addEventObject(BaseTaskDataParse taskDataParse){
        taskDataParses.add(taskDataParse);
    }

    /**
     * 环绕
     * @param pjd
     * @return
     */
    @Around("execution(public void com.xfs.aop.task.cs.CorpTaskAspectService.saveTask(com.xfs.common.ContextInfo, com.xfs.business.model.SpsTask,java.util.Map, com.xfs.common.Result,com.xfs.business.enums.TaskExecuteType))")
    public Boolean aroundMethod(ProceedingJoinPoint pjd) {
        Object[] args = pjd.getArgs();
        ContextInfo info = (ContextInfo) args[0];
        SpsTask spsTask = (SpsTask) args[1];
        Map<String, Object> businessParam = (Map<String, Object>) args[2];
        Object result = args[3];
        ((Result) result).setSuccess(true);
        TaskExecuteType taskExecuteType = (TaskExecuteType) args[4];
        BaseTaskDataParse baseTaskDataParse = null;
        if (null != taskDataParses && !taskDataParses.isEmpty()) {
            for (BaseTaskDataParse taskDataParse : taskDataParses) {
                if (taskDataParse.checkBussinessByTask(info, spsTask, businessParam)) {
                    baseTaskDataParse = taskDataParse;
                    break;
                }
            }
        }
        if (null != baseTaskDataParse) {
            /**
             * 获取当前人员信息
             */
            CmEmployee cmEmployee = new CmEmployee();
            cmEmployee.setEmpId(spsTask.getEmpId());
            cmEmployee = cmEmployeeService.findByPK(cmEmployee);
            boolean ischeck = baseTaskDataParse.checkDataValidity(info, spsTask, businessParam, cmEmployee, (Result) result);
            if (ischeck) {
                try {
                    baseTaskDataParse.beforeSaveTask(info, spsTask, businessParam, cmEmployee, taskExecuteType, (Result) result);
                    pjd.proceed();
                    baseTaskDataParse.afterSaveTask(info, spsTask, businessParam, cmEmployee, taskExecuteType, (Result) result);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        return ((Result) result).isSuccess();
    }
}
