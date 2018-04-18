package com.xfs.timer.service;

import java.util.List;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;

/**
 * 定时任务接口
 * 
 * @author wangdx
 * 
 * @date 2016-6-13
 */
public interface ITaskJobService {

	/**
	 * 获得所有的定时任务列表
	 * 
	 * @return List<TaskJob> 任务对象列表
	 */
	public List<TaskJob> getTaskJobList();

	/**
	 * 删除定时任务基本信息, 此操作会一并删除序列中的相关触发器及其数据库中的信息
	 * 
	 * @param id
	 *            定时任务的id
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	public boolean deleteTaskJob(int id) throws TaskException;

	/**
	 * 根据定时任务Id获得定时任务基本信息
	 * 
	 * @param id
	 *            定时任务Id
	 * @return TaskJob 任务对象
	 */
	public TaskJob getTaskJobById(int id);

	/**
	 * 获得所有在运行的定时任务列表 !!!!!!!未实现
	 * 
	 * @return List<TaskJob> 任务对象列表
	 */
	public List<TaskJob> getTaskJobRunList();

	/**
	 * 保存任务信息
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	public boolean saveTaskJob(TaskJob taskJob) throws TaskException;

	/**
	 * 添加新任务信息及其包含的触发器(可为空)
	 * 
	 * @param taskJob
	 *            任务对象
	 * @return true:成功;false:失败
	 * @throws TaskException
	 */
	public boolean addNewTaskJob(TaskJob taskJob) throws TaskException;

	/**
	 * 校验任务编码是否唯一
	 * 
	 * @param jobName
	 * @return true:唯一 ;false:不唯一
	 */
	public boolean checkJobCode(String jobCode);

	/**
	 * 根据code获取任务对象
	 * 
	 * @param jobCode
	 *            任务编码
	 * @param jobGroup
	 *            任务分组
	 * @return TaskJob 任务对象
	 * @throws TaskException
	 */
	public TaskJob getTaskJob(String jobCode, String jobGroup) throws TaskException;

	/**
	 * 获取一个任务正在运行的触发器个数
	 * 
	 * @param JobId
	 * @return
	 * @throws TaskException
	 */
	public int getJobRunningTrigger(int jobId) throws TaskException;
}
