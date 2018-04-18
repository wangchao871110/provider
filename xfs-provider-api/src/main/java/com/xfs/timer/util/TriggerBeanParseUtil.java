package com.xfs.timer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.xfs.timer.model.TaskTrigger;

/**
 * TaskTrigger对象解析类
 * 
 * @author wangdx
 * 
 * @date 2016-6-11
 */
public class TriggerBeanParseUtil {

    /**
     * 根据Trigger对象生成对应的触发器
     * 
     * @param taskTrigger
     *            触发器对象
     * @param jobKey
     * @return Trigger 触发器
     */
    public static Trigger parsetaskTrigger(TaskTrigger taskTrigger, JobKey jobKey) {

        Trigger trigger = null;
        if (taskTrigger != null) {
            switch (taskTrigger.getTriggerType()) {
            case TaskTrigger.TRIGGER_TYPE_SIMPLE:
                TriggerBuilder<Trigger> simplTriggerBuilder = TriggerBuilder.newTrigger().withIdentity(
                        taskTrigger.getTriggerCode(), taskTrigger.getTriggerGroup());//TaskTrigger模型中code编码是唯一的
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    if (TaskUtil.hasValue(taskTrigger.getStartTime())) {
                        simplTriggerBuilder.startAt(sdf.parse(taskTrigger.getStartTime()));
                    } else {
                        simplTriggerBuilder.startAt(DateBuilder.evenSecondDateAfterNow());
                    }
                    if (TaskUtil.hasValue(taskTrigger.getEndTime())) {
                        simplTriggerBuilder.endAt(sdf.parse(taskTrigger.getEndTime()));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
                setRepeatInterval(taskTrigger.getIntervalType(), simpleScheduleBuilder, taskTrigger.getRepeatInterval());
                if (taskTrigger.getRepeatCount() >= 0) {
                    simpleScheduleBuilder.withRepeatCount(taskTrigger.getRepeatCount());
                } else {
                    simpleScheduleBuilder.repeatForever();
                }
                simplTriggerBuilder.withSchedule(simpleScheduleBuilder);
                trigger = simplTriggerBuilder.forJob(jobKey).build();
                break;
            case TaskTrigger.TRIGGER_TYPE_CRON:
                TriggerBuilder<Trigger> cronTriggerBuilder = TriggerBuilder.newTrigger().withIdentity(
                        taskTrigger.getTriggerCode(), taskTrigger.getTriggerGroup());
                cronTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(taskTrigger.getCronExpress()).withMisfireHandlingInstructionDoNothing());
                trigger = cronTriggerBuilder.forJob(jobKey).build();
                break;
            default:
                break;
            }
        }
        return trigger;
    }

    /**
     * 根据不同的重复时间单位设置重复执行时间间隔
     * 
     * @param intervalType
     *            重复时间单位
     * @param simpleScheduleBuilder
     *            简单型任务调度器Builder
     * @param intervalValue
     *            重复间隔时间
     */
    public static void setRepeatInterval(int intervalType, SimpleScheduleBuilder simpleScheduleBuilder,
            int intervalValue) {

        switch (intervalType) {
        case TaskTrigger.INTERVAL_TYPE_MILSEC:
            simpleScheduleBuilder.withIntervalInMilliseconds(intervalValue);
            break;
        case TaskTrigger.INTERVAL_TYPE_SEC:
            simpleScheduleBuilder.withIntervalInSeconds(intervalValue);
            break;
        case TaskTrigger.INTERVAL_TYPE_MIN:
            simpleScheduleBuilder.withIntervalInMinutes(intervalValue);
            break;
        case TaskTrigger.INTERVAL_TYPE_HOUR:
            simpleScheduleBuilder.withIntervalInHours(intervalValue);
            break;
        default:
            simpleScheduleBuilder.repeatForever();
            break;
        }
    }
}
