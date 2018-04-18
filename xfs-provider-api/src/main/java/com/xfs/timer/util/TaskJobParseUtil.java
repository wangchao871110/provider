package com.xfs.timer.util;

import java.util.List;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xfs.common.exception.TaskException;
import com.xfs.timer.model.TaskJob;
import com.xfs.timer.model.TaskTrigger;

/**
 * TaskJob对象解析类
 * 
 * @author wangdx
 * 
 * @date 2016-6-11
 */
public class TaskJobParseUtil {
	private static Logger logger = LoggerFactory.getLogger(TaskJobParseUtil.class);

	/**
	 * 解析taskJob对象，生成对应的JOB和Trigger,并启动任务
	 * 
	 * @param taskJob
	 *            任务对象
	 * @param scheduler
	 *            任务调度器
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws HorizonTaskException
	 */
	public static void scheduleTaskJob(TaskJob taskJob, Scheduler scheduler) throws Exception {

		if (taskJob != null) {
			String jobName = taskJob.getJobCode();// taskjob模型中code编码是唯一的
			String jobGroup = !TaskUtil.hasValue(taskJob.getJobGroup()) ? taskJob.DEFAULT_GROUP_NAME
					: taskJob.getJobGroup();
			Class<Job> jobclass = null;
			try {
				jobclass = (Class<Job>) Class.forName(taskJob.getJobClass());
			} catch (ClassNotFoundException e) {
				logger.error("can not find class with classname:" + taskJob.getJobClass());
				e.printStackTrace();
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			JobDetail detail = JobBuilder.newJob(jobclass).withIdentity(jobKey).storeDurably()
					.withDescription(taskJob.getDescription()).build();
			detail.getJobDataMap().put("param", taskJob.getJobPara());
			parseJobPara(taskJob, detail);
			if (TaskUtil.hasValue(taskJob.getJobListener())) {
				JobListener listener = (JobListener) Class.forName(taskJob.getJobListener()).newInstance();
				Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobKey);
				scheduler.getListenerManager().addJobListener(listener, matcher);
			}
			if (scheduler.checkExists(jobKey)) {
				throw new TaskException("在分组：" + jobGroup + "下已存在相同的任务，任务名：" + jobName + ".保存失败.");
			}
			List<TaskTrigger> triggerList = taskJob.getTriggerList();
			for (int i = 0; i < triggerList.size(); i++) {
				Trigger trigger = TriggerBeanParseUtil.parsetaskTrigger(triggerList.get(i), jobKey);
				if (scheduler.checkExists(jobKey)) {
					scheduler.scheduleJob(trigger);
				} else {
					scheduler.scheduleJob(detail, trigger);
				}
			}
		}
	}

	/**
	 * 检查任务参数是否符合标准
	 * 
	 * @param para
	 * @return true 符合,false不符合
	 */
	public static boolean checkJobPara(String para) {
		if (null != para && !"".equals(para)) {
			String[] paraArr = para.split(";");
			for (int i = 0; i < paraArr.length; i++) {
				String paraInfo[] = paraArr[i].split("=");
				if (2 != paraInfo.length) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 解析Job的自定义参数
	 * 
	 * @param taskJob
	 *            任务对象
	 * @param jobDetail
	 */
	public static void parseJobPara(TaskJob taskJob, JobDetail jobDetail) {
		String para = taskJob.getJobPara();
		if (checkJobPara(para)) {
			if (para != null && !"".equals(para)) {
				String[] paraArr = para.split(";");
				for (int i = 0; i < paraArr.length; i++) {
					String paraInfo[] = paraArr[i].split("=");
					jobDetail.getJobDataMap().put(paraInfo[0], paraInfo[1]);
				}
			}
			jobDetail.getJobDataMap().putAll(taskJob.getDataMap());
		}
	}

	/**
	 * 将dataMap转成字符串，用于前台显示
	 * 
	 * @param dataMap
	 * @return
	 */
	public static String parseJobMapToStr(JobDataMap dataMap) {

		if (dataMap.isEmpty()) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		for (Entry<String, Object> entry : dataMap.entrySet()) {
			buffer.append(entry.getKey() + "=" + entry.getValue() + ";");
		}
		return buffer.toString();
	}
}
