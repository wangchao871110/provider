package com.xfs.timer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.timer.model.TaskJob;

@Repository
public class TaskJobDao extends BaseSqlMapDao {
	/**
	 * 根据条件查询所有定时任务
	 * 
	 * @param taskJob
	 *            查询条件封装
	 * 
	 * @return
	 */
	public List<TaskJob> getTaskJobList(TaskJob taskJob) {
		return queryForList("TASK_JOB.GetTaskJobList", taskJob);
	}

	/**
	 * 根据条件查询定时任务个数
	 * 
	 * @param taskJob
	 *            查询参数
	 * @return TaskJob 任务对象
	 */
	public int countTaskJob(TaskJob taskJob) {
		Integer result = (Integer) queryForObject("TASK_JOB.CountTaskJob", taskJob);
		return result.intValue();
	}

	/**
	 * 删除定时任务基本信息
	 * 
	 * @param id
	 *            定时任务的id
	 * @return true:成功;false:失败
	 */
	public boolean deleteTaskJob(int id) {
		int result = delete(null,"TASK_JOB.DeleteTaskJob", id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据定时任务Id获得定时任务基本信息
	 * 
	 * @param id
	 *            定时任务Id
	 * @return TaskJob 任务对象
	 */
	public TaskJob getTaskJobById(int id) {
		return (TaskJob) queryForObject("TASK_JOB.GetTaskJobById", id);
	}

	/**
	 * 获得所有在运行的定时任务列表
	 * 
	 * @return List<TaskJob> 任务对象列表
	 */
	public List<TaskJob> getTaskJobRunList() {
		return queryForList("TASK_JOB.GetTaskJobRunList", null);
	}

	/**
	 * 保存任务信息
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return true:成功;false:失败
	 */
	public boolean addTaskJob(TaskJob taskJob) {
		int result = insert(null,"TASK_JOB.AddTaskJob", taskJob);
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean updateTaskJob(TaskJob taskJob) {
		int result = update(null,"UpdateTaskJob", taskJob);
		if (result > 0) {
			return true;
		}
		return false;
	}
}
