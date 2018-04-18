package com.xfs.aop.task.sps;

import java.util.Map;

import com.xfs.base.dto.BusinessField;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;


/**
 * 任务单数据转换、校验接口
 */
public interface TaskDataParseInterface {

    /**
     * 检查当前任务单是否属于该类型
     */
    public boolean checkBussinessByTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame);

    /**
     * 在保存任务单之间校验
     * 业务数据、任务单数据
     * @param spsTask
     * @param businessParame
     * @return boolean
     */
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);

    /**
     * 在保存任务单之间校验
     * 业务数据、任务单数据
     * @param spsTask
     * @param businessParame
     * @return boolean
     */
    public boolean beforeSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Map<String, BusinessField> businessFieldMap, Result result);

    /**
     * 保存前 处理任务单数据
     * @param spsTask
     * @param businessParame
     */
    public void dealBeforeSave(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame) ;

    /**
     * 处理待处理 任务单业务数据
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealToDoTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);

    /**
     * 任务单已完成后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealCompletedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);

    /**
     * 任务单已关闭后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealClosedTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);


    /**
     * 任务单异常后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealErrorTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);
    /**
     * 任务等待，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealSubmitTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, Result result);
}
