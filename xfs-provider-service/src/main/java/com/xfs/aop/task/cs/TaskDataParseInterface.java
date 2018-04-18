package com.xfs.aop.task.cs;

import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;

import java.util.Map;


/**
 * 任务单数据转换、校验接口
 */
public interface TaskDataParseInterface {

    /**
     * 检查当前任务单是否属于该类型
     */
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame);

    /**
     * 校验数据有效性
     * @param info
     * @param spsTask
     * @param businessParame
     * @param cmEmployee
     * @param result
     * @return
     */
    public boolean checkDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);

    /**
     * 在保存任务单之间校验
     * 业务数据、任务单数据
     * @param spsTask
     * @param businessParame
     * @return boolean
     */
    public boolean beforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result);


    /**
     * 处理待处理 任务单业务数据
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealToDoTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);

    /**
     * 任务单已完成后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealCompletedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);

    /**
     * 任务单已关闭后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealClosedTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);


    /**
     * 任务单异常后，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealErrorTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);
    /**
     * 任务等待，业务处理
     * @param spsTask
     * @param businessParame
     * @param result
     */
    public void dealSubmitTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, Result result);

    /**
     * 保存任务单之后做数据处理
     *  @param info
     *  @param spsTask
     *  @param businessParame
     *  @param result
     *  @return
     *	@return 			: boolean
     *  @createDate  	: 2017年4月24日 下午1:40:35
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月24日 下午1:40:35
     *  @updateAuthor  :
     */
    public void afterSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result);
}
