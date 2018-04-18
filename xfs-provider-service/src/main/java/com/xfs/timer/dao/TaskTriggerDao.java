package com.xfs.timer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.timer.model.TaskTrigger;

@Repository
public class TaskTriggerDao extends BaseSqlMapDao {
	/**
	 * 根据条件查询所有触发器
	 * 
	 * @author zys
	 * @param taskTrigger
	 *            查询条件封装
	 * @return List<TaskTrigger> 触发器列表
	 */
	public List<TaskTrigger> getTaskTriggerList(TaskTrigger taskTrigger) {
		return queryForList("TASK_TRIGGER.GetTaskTriggerList", taskTrigger);
	}

	/**
	 * 根据条件查询触发器个数
	 * 
	 * @author zys
	 * @param taskTrigger
	 *            查询条件封装
	 * @return List<TaskTrigger> 触发器列表
	 */
	public int countTaskTrigger(TaskTrigger taskTrigger) {
		Integer result = (Integer) queryForObject("TASK_TRIGGER.CountTaskTrigger", taskTrigger);
		return result.intValue();
	}

	/**
	 * 保存定时任务触发器基本信息
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 */
	public boolean addTaskTrigger(TaskTrigger taskTrigger) {
		int result = insert(null,"TASK_TRIGGER.AddTaskTrigger", taskTrigger);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据id修改定时任务触发器基本信息
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 */
	public boolean updateTaskTrgger(TaskTrigger taskTrigger) {
		int result = update(null,"TASK_TRIGGER.UpdateTaskTrgger", taskTrigger);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据id删除定时任务触发器基本信息
	 * 
	 * @param taskTrigger
	 *            定时任务基本信息
	 * @return true:成功;false:失败
	 */
	public boolean deleteTaskTrgger(int id) {
		int result = delete(null,"TASK_TRIGGER.DeleteTaskTrgger", id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据定时任务Id获得定时任务触发器基本信息
	 * 
	 * @param id
	 *            定时任务触发器id
	 * @return TaskTrigger 任务触发器实体
	 */
	public TaskTrigger getTaskTriggerById(int id) {
		return (TaskTrigger) queryForObject("TASK_TRIGGER.GetTaskTriggerById", id);
	}
}
