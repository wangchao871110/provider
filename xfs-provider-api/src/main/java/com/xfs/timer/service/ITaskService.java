package com.xfs.timer.service;


import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;

/**
 * 定时任务接口类
 * 
 * @author wangdx
 * 
 * @date 2016-6-6
 */
public interface ITaskService {

	/**
	 * 启动调度器
	 * 
	 */
	public boolean startup() throws TaskException;

	/**
	 * 关闭调度器
	 * 
	 */
	public boolean shutdown() throws TaskException;

	/**
	 * 调度器是否已经启动
	 * 
	 */
	public boolean isStarted() throws TaskException;

	/**
	 * 增加一条任务,当任务有触发器时会启动任务和其相关触发器,没有触发器时会挂起任务
	 * 
	 * @param taskJob
	 *            任务对象
	 */
	public boolean schedule(TaskJob taskJob) throws TaskException;

	/**
	 * 追加一个触发器绑定到一个任务上
	 * 
	 * @param taskJob
	 * @param taskTrigger
	 * @return
	 * @throws TaskException
	 * @throws SchedulerException
	 */
	public void addTriggerToJob(TaskJob taskJob, TaskTrigger taskTrigger) throws TaskException;

	/**
	 * 暂停一条任务(所有的触发器都会暂停)
	 * 
	 * @param taskJob
	 *            任务对象
	 */
	public boolean pause(TaskJob taskJob) throws TaskException;

	/**
	 * 暂停一个触发器(只暂停对应的触发器)
	 * 
	 * @param taskTrigger
	 *            触发器对象
	 */
	public boolean pause(TaskTrigger taskTrigger) throws TaskException;

	/**
	 * 暂停所有任务
	 */
	public boolean pauseAll() throws TaskException;

	/**
	 * 删除任务
	 * 
	 * @param TaskJobs
	 */
	public boolean delete(TaskJob... taskJobs) throws TaskException;

	/**
	 * 恢复一个暂停的任务
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return
	 * @throws HorizonTaskException
	 */
	public boolean resume(TaskJob taskJob) throws TaskException;

	/**
	 * 恢复一个暂停的触发器
	 * 
	 * @param taskTrigger
	 *            触发器对象
	 * @return
	 * @throws HorizonTaskException
	 */
	public boolean resume(TaskTrigger taskTrigger) throws TaskException;

	/**
	 * 恢复所有暂停的任务
	 * 
	 */
	public boolean resumeAll() throws TaskException;

	/**
	 * 取消触发器的运行
	 * 
	 * @param taskTriggers
	 */
	public boolean unschedule(TaskTrigger... taskTriggers) throws TaskException;

	/**
	 * 获取一个任务信息
	 * 
	 * @param jobCode
	 * @param jobGroup
	 */
	public TaskJob getJob(String jobCode, String jobGroup) throws TaskException;

	/**
	 * 获取任务关联的触发器
	 * 
	 * @param jobCode
	 * @param jobGroup
	 */
	public List<Trigger> getTriggersOfJob(String jobCode, String jobGroup) throws TaskException;

	/**
	 * 获取 org.quartz.Scheduler 对象
	 * 
	 * @return
	 */
	public Scheduler getScheduler();

	/**
	 * 获得触发器状态,不存在 0; 激活 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 * 
	 * @param triggerCode
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	public int getTriggerState(String triggerCode, String triggerGroup) throws TaskException;
}
