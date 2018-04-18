package com.xfs.timer.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * 任务监听器，主要用于日志记录
 * 
 * @author wangdx
 *
 */
public class TaskJobListener implements JobListener {

	/**
	 * 返回当前监听器的名字，返回值必须与当前类名相同
	 * 
	 * @return 监听器名字
	 */
	public String getName() {
		return "TaskJobListener";
	}

	/**
	 * 任务被触发前触发
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
	}

	/**
	 * 任务执行被拒绝时触发
	 * 
	 * @param context
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	/**
	 * 任务调度完成后触发
	 * 
	 * @param context
	 * @param jobException
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
	}

}
