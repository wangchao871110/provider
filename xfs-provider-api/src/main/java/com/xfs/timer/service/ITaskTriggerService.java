package com.xfs.timer.service;


import java.util.List;

import org.quartz.SchedulerException;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;

/**
 * 定时任务触发器接口类
 *
 * @author wangdx
 * 
 * @date 2016-6-13
 */
public interface ITaskTriggerService {

	/**
	 * 获得所有的定时任务触发器列表
	 * 
	 * @return List<TaskTrigger> 触发器列表
	 */
	public List<TaskTrigger> getTaskTriggerList();

	/**
	 * 根据条件获取定时任务触发器
	 * 
	 * @return List<TaskTrigger> 触发器列表
	 */
	public List<TaskTrigger> getTaskTriggerList(TaskTrigger trigger);

	/**
	 * 添加定时任务触发器
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	public boolean addTaskTrigger(TaskTrigger taskTrigger) throws TaskException;

	/**
	 * 保存定时任务触发器基本信息,此方法启动所修改的触发器,任务不在于控制器队列时,会自动生成一个任务
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 * @throws TaskException
	 * @throws SchedulerException
	 */
	public boolean saveTaskTrigger(TaskTrigger taskTrigger, TaskJob taskJob) throws TaskException;

	/**
	 * 删除定时任务触发器
	 * 
	 * @param taskTrigger
	 *            触发器信息,包括id,编码,分组
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	public boolean deleteTaskTrigger(TaskTrigger taskTrigger) throws TaskException;

	/**
	 * 根据定时触发器Id获得定时任务触发器基本信息
	 * 
	 * @param id
	 *            定时任务触发器id
	 * @return TaskTrigger 任务触发器实体
	 */
	public TaskTrigger getTaskTriggerById(int id);

	/**
	 * 根据定时触发器编码获得定时任务触发器基本信息
	 * 
	 * @param triggerCode
	 *            定时触发器编码
	 * @return TaskTrigger 任务触发器实体
	 */
	public TaskTrigger getTaskTriggerByCode(String triggerCode);

	/**
	 * 校验触发器编码是否重复
	 * 
	 * @param triggerCode
	 *            定时任务code
	 * @return true:不重复 ;false:重复
	 */
	public boolean checkTriggerCode(String triggerCode);

	/**
	 * 检查group是否已经存在
	 * 
	 * @param triggerGroup
	 * @return true已经存在fasle不存在
	 */
	public boolean checkTriggerGroup(String triggerGroup);

	/**
	 * 根据group查询trigger
	 * 
	 * @param triggerGroup
	 * @return
	 */
	public List<TaskTrigger> getTriggerByGroup(String triggerGroup);
}
