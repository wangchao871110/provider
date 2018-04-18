package com.xfs.timer.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;
import com.xfs.timer.dao.TaskJobDao;
import com.xfs.timer.dao.TaskTriggerDao;
import com.xfs.timer.service.ITaskJobService;
import com.xfs.timer.service.ITaskService;
import com.xfs.timer.service.ITaskTriggerService;

@Service
public class TaskJobServiceImpl implements ITaskJobService {
	@Autowired
	private TaskJobDao tjDao;
	@Autowired
	private TaskTriggerDao ttDao;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ITaskTriggerService ttService;

	/**
	 * 获得所有的定时任务列表
	 * 
	 * @return List<TaskJob> 任务对象列表
	 */
	@Override
	public List<TaskJob> getTaskJobList() {
		TaskJob job = new TaskJob();
		job.setDr(0);
		job.setJobGroup(null);
		return tjDao.getTaskJobList(job);
	}

	/**
	 * 根据定时任务Id获得定时任务基本信息
	 * 
	 * @param id
	 *            定时任务Id
	 * @return TaskJob 任务对象
	 */
	@Override
	public TaskJob getTaskJobById(int id) {
		return tjDao.getTaskJobById(id);
	}

	/**
	 * 获得所有在运行的定时任务列表
	 * 
	 * @return List<TaskJob> 任务对象列表
	 */
	@Override
	public List<TaskJob> getTaskJobRunList() {
		return tjDao.getTaskJobRunList();
	}

	/**
	 * 根据code获取任务对象,jobCode不可以为空
	 * 
	 * @param jobCode
	 *            任务编码,不可以为空
	 * @param jobGroup
	 *            任务分组,可以为空
	 * @return TaskJob 任务对象
	 * @throws TaskException
	 */
	@Override
	public TaskJob getTaskJob(String jobCode, String jobGroup) throws TaskException {
		TaskJob tj = TaskJob.getClearJob();
		if (jobCode == null) {
			throw new TaskException("任务编码为空");
		}
		tj.setJobCode(jobCode);
		if (jobGroup != null) {
			tj.setJobGroup(jobGroup);
		}
		List<TaskJob> tjList = tjDao.getTaskJobList(tj);
		return tjList.get(0);
	}

	/**
	 * 校验任务编码是否唯一
	 * 
	 * @return true:唯一 ;false:不唯一
	 */
	@Override
	public boolean checkJobCode(String jobCode) {
		TaskJob tj = TaskJob.getClearJob();
		tj.setJobCode(jobCode);
		int result = tjDao.countTaskJob(tj);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除定时任务基本信息 此操作会一并删除序列中的相关触发器及其数据库中的信息
	 * 
	 * @param id
	 *            定时任务的id
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	@Override
	public boolean deleteTaskJob(int id) throws TaskException {
		TaskJob taskJob = getTaskJobById(id);
		if (null == taskJob) {
			throw new TaskException("不存在该id任务信息");
		}
		TaskTrigger param = TaskTrigger.getClearTaskTrigger();
		param.setJobId(id);
		List<TaskTrigger> triggerList = ttService.getTaskTriggerList(param);
		taskService.delete(taskJob);// 把任务及其触发器从队列删除
		for (TaskTrigger t : triggerList) {// 删除触发器信息
			if (!ttDao.deleteTaskTrgger(t.getId())) {
				throw new TaskException("删除触发器信息失败");
			}

		}
		if (!tjDao.deleteTaskJob(id)) {// 删除任务信息
			throw new TaskException("删除任务信息失败");
		}
		return true;
	}

	/**
	 * 添加新任务信息及其包含的触发器列表(列表不可为空,size可以为0)
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	@Override
	public boolean addNewTaskJob(TaskJob taskJob) throws TaskException {
		if (null == taskJob.getId()) {
			if (taskService.schedule(taskJob)) {// 把新任务添加到序列
				// 先保存任务信息
				if (!tjDao.addTaskJob(taskJob)) {
					throw new TaskException("插入任务信息失败");
				}
				TaskJob newJob = getTaskJob(taskJob.getJobCode(), null);
				if (null == newJob) {
					throw new TaskException("该任务编码未找到任务");
				}
				int jobId = newJob.getId();
				// 保存触发器信息
				List<TaskTrigger> tList = taskJob.getTriggerList();
				for (TaskTrigger trigger : tList) {
					trigger.setJobId(jobId);
					if (!ttService.addTaskTrigger(trigger)) {
						throw new TaskException("插入触发器信息失败");
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存任务信息
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	@Override
	public boolean saveTaskJob(TaskJob taskJob) throws TaskException {
		if (taskJob.getId() == null) {
			return false;
		} else {
			// 把任务及其触发器从队列删除
			taskService.delete(taskJob);
			// 获得触发器信息
			TaskTrigger param = TaskTrigger.getClearTaskTrigger();
			param.setJobId(taskJob.getId());
			List<TaskTrigger> triggerList = ttService.getTaskTriggerList(param);
			taskJob.setTriggerList(triggerList);
			// 开启新任务及其触发器
			if (!taskService.schedule(taskJob)) {
				throw new TaskException("开启新任务及其触发器失败.原因:");
			}
			return tjDao.updateTaskJob(taskJob);
		}
	}

	@Override
	public int getJobRunningTrigger(int jobId) throws TaskException {
		// 初始化触发器查询条件模型
		TaskTrigger trigger = TaskTrigger.getClearTaskTrigger();
		trigger.setJobId(jobId);
		// 获取触发器信息封入任务信息中
		List<TaskTrigger> triggerList = ttService.getTaskTriggerList(trigger);
		int count = 0;
		for (TaskTrigger t : triggerList) {
			int i = taskService.getTriggerState(t.getTriggerCode(), t.getTriggerGroup());
			// 不存在 0; 待机 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
			if (i == 1) {
				count++;
			}
		}
		return count;
	}
}
