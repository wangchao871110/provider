package com.xfs.timer.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import com.xfs.timer.util.TaskJobParseUtil;
import com.xfs.timer.util.TriggerBeanParseUtil;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;
import com.xfs.timer.service.ITaskService;
import com.xfs.timer.service.ITaskTriggerService;

/**
 * 定时任务接口实现类
 * 
 * @author wangdx
 * 
 * @date 2016-6-6
 */
@Service
public class TaskServiceImpl implements ITaskService {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	private static Scheduler scheduler;

	static {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(TaskJob... taskJobs) throws TaskException {

		if (taskJobs == null) {
			logger.error("taskJobs is null.");
			throw new TaskException("删除任务失败.原因:任务为空");
		}
		try {
			List<JobKey> jobKeys = new ArrayList<JobKey>();
			for (TaskJob taskJob : taskJobs) {
				JobKey jobKey = JobKey.jobKey(taskJob.getJobCode(), taskJob.getJobGroup());
				jobKeys.add(jobKey);
			}
			return scheduler.deleteJobs(jobKeys);
		} catch (SchedulerException e) {
			logger.error("batch delete job fail.");
			e.printStackTrace();
			throw new TaskException("删除任务失败.原因查看日志", e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.horizon.task.api.IHorizonTask#pause(com.horizon.task.manager.entity
	 * .taskJob)
	 */
	@Override
	public boolean pause(TaskJob taskJob) throws TaskException {

		if (taskJob == null) {
			logger.error("taskJob is null.");
			throw new TaskException("暂停任务失败.原因:任务为空");
		}
		try {
			JobKey jobKey = JobKey.jobKey(taskJob.getJobCode(), taskJob.getJobGroup());
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error(
					"pause job with name: " + taskJob.getJobCode() + " group :" + taskJob.getJobGroup() + " fail.");
			throw new TaskException("暂停任务失败.原因查看日志", e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.horizon.task.api.IHorizonTask#pause(com.horizon.task.manager.entity
	 * .taskTrigger)
	 */
	@Override
	public boolean pause(TaskTrigger taskTrigger) throws TaskException {

		if (taskTrigger == null) {
			logger.error("taskTrigger is null.");
			throw new TaskException("暂停触发器失败.原因:触发器为空");
		}
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(taskTrigger.getTriggerCode(), taskTrigger.getTriggerGroup());
			scheduler.pauseTrigger(triggerKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error("pause trigger with name: " + taskTrigger.getTriggerCode() + " group :"
					+ taskTrigger.getTriggerGroup() + " fail.");
			throw new TaskException("暂停触发器失败.原因查看日志", e.getMessage());
		}
	}

	@Autowired
	ITaskTriggerService ttService;

	@Override
	public boolean schedule(TaskJob taskJob) throws TaskException {

		try {
			TaskJobParseUtil.scheduleTaskJob(taskJob, scheduler);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TaskException("运行任务失败.原因查看日志", e.getMessage());
		}
	}

	@Override
	public boolean pauseAll() throws TaskException {

		try {
			scheduler.pauseAll();
			return true;
		} catch (SchedulerException e) {
			logger.error("Pause All Job Fail.");
			e.printStackTrace();
			throw new TaskException("暂停所有任务失败.原因:" + e.getMessage());
		}
	}

	@Override
	public boolean resume(TaskJob taskJob) throws TaskException {

		if (taskJob == null) {
			logger.error("taskJob is null.");
			throw new TaskException("唤醒任务失败.原因:任务为空。");
		}
		try {
			JobKey jobKey = JobKey.jobKey(taskJob.getJobCode(), taskJob.getJobGroup());
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error(
					"resume job with name: " + taskJob.getJobCode() + " group :" + taskJob.getJobGroup() + " fail.");
			throw new TaskException("唤醒任务失败.原因查看日志", e.getMessage());
		}
	}

	@Override
	public boolean resume(TaskTrigger taskTrigger) throws TaskException {

		if (taskTrigger == null) {
			logger.error("taskTrigger is null.");
			throw new TaskException("唤醒触发器失败.原因:触发器信息为空。");
		}
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(taskTrigger.getTriggerCode(), taskTrigger.getTriggerGroup());
			scheduler.resumeTrigger(triggerKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("resume trigger with name: " + taskTrigger.getTriggerCode() + " group :"
					+ taskTrigger.getTriggerGroup() + " fail.");
			throw new TaskException("唤醒触发器失败.原因查看日志", e.getMessage());
		}
	}

	@Override
	public boolean resumeAll() throws TaskException {

		try {
			scheduler.resumeAll();
			return true;
		} catch (SchedulerException e) {
			logger.error("Resume All Job Fail.");
			e.printStackTrace();
			throw new TaskException("唤醒所有任务失败.原因:" + e.getMessage());
		}
	}

	@Override
	@PreDestroy
	public boolean shutdown() throws TaskException {
		try {
			scheduler.shutdown();
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new TaskException("关闭任务调度器失败.原因:" + e.getMessage());
		}
	}

	@Override
	public boolean startup() throws TaskException {

		try {
			scheduler.getListenerManager().addJobListener(new TaskJobListener());
			scheduler.start();
			return true;
		} catch (SchedulerException e) {
			throw new TaskException("启动任务调度器失败.原因:", e.getMessage());
		}
	}

	@Override
	public boolean isStarted() throws TaskException {

		try {
			return scheduler.isStarted();
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new TaskException("获取调度器是否启动状态失败。");
		}
	}

	@Override
	public boolean unschedule(TaskTrigger... taskTriggers) throws TaskException {

		if (taskTriggers == null) {
			logger.error("taskTriggers is null.");
			throw new TaskException("取消触发器失败.原因:触发器为空");
		}
		try {
			List<TriggerKey> triggerKeys = new ArrayList<TriggerKey>();
			for (TaskTrigger taskTrigger : taskTriggers) {
				TriggerKey triggerKey = TriggerKey.triggerKey(taskTrigger.getTriggerCode(),
						taskTrigger.getTriggerGroup());
				triggerKeys.add(triggerKey);
			}
			return scheduler.unscheduleJobs(triggerKeys);
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error("unschedule triggers fail.");
			throw new TaskException("取消触发器失败.原因:" + e.getMessage());
		}
	}

	@Override
	public TaskJob getJob(String jobCode, String jobGroup) throws TaskException {

		try {
			JobKey jobKey = JobKey.jobKey(jobCode, jobGroup);
			JobDetail detail = scheduler.getJobDetail(jobKey);
			if (detail == null) {
				return null;
			}
			TaskJob taskJob = new TaskJob();
			taskJob.setJobCode(jobCode);
			taskJob.setJobGroup(jobGroup);
			taskJob.setJobClass(detail.getJobClass().getName());
			// taskJob.setJobPara(TaskJobParseUtil.parseJob
			// MapToStr(detail.getJobDataMap()));
			taskJob.setDescription(detail.getDescription());
			return taskJob;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new TaskException("获取任务信息失败.原因:" + e.getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trigger> getTriggersOfJob(String jobCode, String jobGroup) throws TaskException {
		try {
			JobKey jobKey = JobKey.jobKey(jobCode, jobGroup);
			return (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new TaskException("获取任务的触发器信息失败.原因:" + e.getMessage());
		}
	}

	@Override
	public Scheduler getScheduler() {
		return scheduler;
	}

	@Override
	public void addTriggerToJob(TaskJob taskJob, TaskTrigger taskTrigger) throws TaskException {
		JobKey jobKey = JobKey.jobKey(taskJob.getJobCode(), taskJob.getJobGroup());
		Trigger trigger = TriggerBeanParseUtil.parsetaskTrigger(taskTrigger, jobKey);

		boolean r;
		try {
			r = scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			throw new TaskException("检查任务存在情况异常", e.getMessage());
		}

		if (r) {
			try {
				scheduler.scheduleJob(trigger);
			} catch (SchedulerException e) {
				throw new TaskException("启动触发器异常", e.getMessage());
			}
		} else {
			Class<Job> jobclass = null;
			try {
				jobclass = (Class<Job>) Class.forName(taskJob.getJobClass());
			} catch (ClassNotFoundException e) {
				logger.error("can not find class with classname:" + taskJob.getJobClass());
				e.printStackTrace();
			}
			JobDetail detail = JobBuilder.newJob(jobclass).withIdentity(jobKey).storeDurably()
					.withDescription(taskJob.getDescription()).build();
			try {
				scheduler.scheduleJob(detail, trigger);
			} catch (SchedulerException e) {
				throw new TaskException("启动触发器及任务异常", e.getMessage());
			}
		}
	}

	/**
	 * 获得触发器状态,不存在 0; 激活 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 * 
	 * @param triggerCode
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	@Override
	public int getTriggerState(String triggerCode, String triggerGroup) throws TaskException {
		TriggerKey triggerKey = new TriggerKey(triggerCode, triggerGroup);
		TriggerState state;
		try {
			state = scheduler.getTriggerState(triggerKey);
		} catch (SchedulerException e) {
			throw new TaskException("查询触发器状态异常", e.getMessage());
		}
		String stateS = state.toString();
		if ("NONE".equals(stateS)) {
			return 0;
		} else if ("NORMAL".equals(stateS)) {
			return 1;
		} else if ("PAUSED".equals(stateS)) {
			return 2;
		} else if ("COMPLETE".equals(stateS)) {
			return 3;
		} else if ("ERROR".equals(stateS)) {
			return 4;
		} else if ("BLOCKED".equals(stateS)) {
			return 5;
		} else {
			throw new TaskException("未知的触发器状态" + stateS);
		}
	}

}
