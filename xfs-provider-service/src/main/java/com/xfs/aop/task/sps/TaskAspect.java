package com.xfs.aop.task.sps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.xfs.business.model.SpsTask;
import com.xfs.common.Result;

/**
 * Created by konglc on 2016-08-01.
 *
 * 提交任务单切面类
 *
 */
@Component
@Aspect
public class TaskAspect {

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
    @Around("execution(public void com.xfs.aop.task.sps.TaskAspectServiceImpl.saveTask(..))")
    public Boolean aroundMethod(ProceedingJoinPoint pjd) {
        ContextInfo info = (ContextInfo)pjd.getArgs()[0];
        SpsTask spsTask = (SpsTask)pjd.getArgs()[1];
        Map<String,Object> businessParam = (Map<String,Object>)pjd.getArgs()[2];
        Object result = pjd.getArgs()[3];
        BaseTaskDataParse baseTaskDataParse = null;
        if(null != taskDataParses && !taskDataParses.isEmpty()){
            for(BaseTaskDataParse taskDataParse : taskDataParses){
                if(taskDataParse.checkBussinessByTask(info,spsTask,businessParam)) {
                    baseTaskDataParse = taskDataParse;
                    break;
                }
            }
        }

        if(null != baseTaskDataParse){
//            if("SUBMIT".equals(spsTask.getType())){
            //根据任务单地区/业务/生效时间计算出执行日期。执行日期如果不是当月 设置任务单状态为SUBMIT,否则TODO

//            }
            //校验任务单历史表中是否存在变更记录
            if(true){//如果历史表中不存在变更记录

                //2016-08 modify by zhangxiyan 任务单修改完 处理员工表里 社保 公积金字段  不需要走新增校验
                boolean ischeck = false;
                if("COMPLETED".equals(spsTask.getType()) || "CLOSED".equals(spsTask.getType())){
                    ischeck = true;
                }else{
                    spsTask.setType("TODO");
                    //根据业务类型校验
                    ischeck = baseTaskDataParse.beforeSaveTask(info,spsTask,businessParam,(Result)result);
                }
                //根据业务类型校验
//                boolean ischeck = baseTaskDataParse.beforeSaveTask(spsTask,businessParam,(Result)result);
                if(ischeck){
                    baseTaskDataParse.dealBeforeSave(info,spsTask,businessParam);
                    try{
                        pjd.proceed();
                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                    if(((Result) result).isSuccess()){
                        if("SUBMIT".equals(spsTask.getType())){
                            baseTaskDataParse.dealSubmitTask(info,spsTask,businessParam,(Result)result);
                        }else if("TODO".equals(spsTask.getType())){
                            baseTaskDataParse.dealToDoTask(info,spsTask,businessParam,(Result)result);
                        }else if("COMPLETED".equals(spsTask.getType())){
                            baseTaskDataParse.dealCompletedTask(info,spsTask,businessParam,(Result)result);
                        }else if("CLOSED".equals(spsTask.getType())){
                            baseTaskDataParse.dealClosedTask(info,spsTask,businessParam,(Result)result);
                        }else if("ERROR".equals(spsTask.getType())){
                            baseTaskDataParse.dealErrorTask(info,spsTask,businessParam,(Result)result);
                        }
                    }
                }
            }
        }
        return ((Result)result).isSuccess();
    }
}
