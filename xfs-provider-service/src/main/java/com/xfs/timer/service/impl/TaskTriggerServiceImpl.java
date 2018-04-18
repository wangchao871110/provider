package com.xfs.timer.service.impl;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;
import com.xfs.timer.dao.TaskTriggerDao;
import com.xfs.timer.service.ITaskService;
import com.xfs.timer.service.ITaskTriggerService;

@Service
public class TaskTriggerServiceImpl implements ITaskTriggerService {
	@Autowired
	private TaskTriggerDao ttDao;

	@Autowired
	private ITaskService taskService;

	/**
	 * 获得所有的定时任务触发器列表
	 * 
	 * @return List<TaskTrigger> 触发器列表
	 */
	@Override
	public List<TaskTrigger> getTaskTriggerList() {
		return ttDao.getTaskTriggerList(null);
	}

	/**
	 * 根据定时任务触发器Id获得定时任务触发器基本信息
	 * 
	 * @param id
	 *            定时任务触发器id
	 * @return TaskTrigger 任务触发器实体
	 */
	@Override
	public TaskTrigger getTaskTriggerById(int id) {
		return ttDao.getTaskTriggerById(id);
	}

	/**
	 * 校验触发器编码是否重复
	 * 
	 * @param triggerCode
	 *            定时任务code
	 * @return true:不重复 ;false:重复
	 */
	@Override
	public boolean checkTriggerCode(String triggerCode) {
		TaskTrigger tt = TaskTrigger.getClearTaskTrigger();
		tt.setTriggerCode(triggerCode);
		int result = ttDao.countTaskTrigger(tt);
		if (result == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 添加定时任务触发器
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	@Override
	public boolean addTaskTrigger(TaskTrigger taskTrigger) throws TaskException {
		if (taskTrigger.getId() == null) {
			return ttDao.addTaskTrigger(taskTrigger);
		} else {
			throw new TaskException("添加触发器信息失败");
		}
	}

	/**
	 * 保存定时任务触发器基本信息,任务不在控制器队列时,会自动生成一个任务
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 * @throws TaskException
	 * @throws SchedulerException
	 */
	@Override
	public boolean saveTaskTrigger(TaskTrigger taskTrigger, TaskJob taskJob) throws TaskException {
		if (null == taskTrigger.getId()) {// 追加一个触发器
			taskService.addTriggerToJob(taskJob, taskTrigger);
			return ttDao.addTaskTrigger(taskTrigger);
		} else {// 修改一个触发器
			taskService.unschedule(taskTrigger);// 触发器可能并不在运行
			taskService.addTriggerToJob(taskJob, taskTrigger);
			return ttDao.updateTaskTrgger(taskTrigger);
		}
	}

	/**
	 * 删除定时任务触发器
	 * 
	 * @param taskTrigger
	 *            触发器信息,包括id,编码,分组
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	@Override
	public boolean deleteTaskTrigger(TaskTrigger taskTrigger) throws TaskException {
		if (taskService.unschedule(taskTrigger)) {
			if (ttDao.deleteTaskTrgger(taskTrigger.getId())) {
				return true;
			} else {
				throw new TaskException("删除触发器信息失败");
			}
		} else {
			throw new TaskException("关闭触发器失败");
		}
	}

	@Override
	public boolean checkTriggerGroup(String triggerGroup) {
		TaskTrigger trigger = TaskTrigger.getClearTaskTrigger();
		trigger.setTriggerGroup(triggerGroup);
		if (ttDao.countTaskTrigger(trigger) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TaskTrigger> getTriggerByGroup(String triggerGroup) {
		TaskTrigger trigger = TaskTrigger.getClearTaskTrigger();
		trigger.setTriggerGroup(triggerGroup);
		return ttDao.getTaskTriggerList(trigger);
	}

	@Override
	public TaskTrigger getTaskTriggerByCode(String triggerCode) {
		TaskTrigger trigger = TaskTrigger.getClearTaskTrigger();
		trigger.setTriggerCode(triggerCode);
		List<TaskTrigger> triggerList = ttDao.getTaskTriggerList(trigger);
		return triggerList.get(0);
	}

	@Override
	public List<TaskTrigger> getTaskTriggerList(TaskTrigger trigger) {
		return ttDao.getTaskTriggerList(trigger);
	}
}
