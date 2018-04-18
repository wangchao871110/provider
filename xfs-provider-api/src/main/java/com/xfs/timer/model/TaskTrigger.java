package com.xfs.timer.model;

import java.util.Date;

public class TaskTrigger {

	/**
	 * 触发器类型：简单型
	 */
	public static final int TRIGGER_TYPE_SIMPLE = 0;

	/**
	 * 触发器类型：cron型
	 */
	public static final int TRIGGER_TYPE_CRON = 1;

	@Override
	public String toString() {
		return "TaskTrigger [id=" + id + ", triggerName=" + triggerName + ", triggerCode=" + triggerCode
				+ ", triggerGroup=" + triggerGroup + ", triggerType=" + triggerType + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", repeatInterval=" + repeatInterval + ", intervalType=" + intervalType
				+ ", repeatCount=" + repeatCount + ", cronExpress=" + cronExpress + ", jobId=" + jobId + ", creatBy="
				+ creatBy + ", creatDt=" + creatDt + ", modifyBy=" + modifyBy + ", modifyDt=" + modifyDt + ", dr=" + dr
				+ "]";
	}

	public TaskTrigger() {
		super();
	}

	public static TaskTrigger getClearTaskTrigger() {
		TaskTrigger trigger = new TaskTrigger();
		trigger.setIntervalType(null);
		trigger.setRepeatCount(null);
		return trigger;
	}

	public TaskTrigger(String triggerName, String triggerCode, String triggerGroup, Integer triggerType,
			String startTime, String endTime, Integer repeatInterval, Integer intervalType, Integer repeatCount,
			String cronExpress) {
		super();
		this.triggerName = triggerName;
		this.triggerCode = triggerCode;
		this.triggerGroup = triggerGroup;
		this.triggerType = triggerType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.repeatInterval = repeatInterval;
		this.intervalType = intervalType;
		this.repeatCount = repeatCount;
		this.cronExpress = cronExpress;
	}

	/**
	 * 默认触发器分组编码
	 */
	public static final String DEFAULT_TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP";

	/**
	 * 重复间隔：毫秒
	 */
	public static final int INTERVAL_TYPE_MILSEC = 0;

	/**
	 * 重复间隔：秒
	 */
	public static final int INTERVAL_TYPE_SEC = 1;

	/**
	 * 重复间隔：分
	 */
	public static final int INTERVAL_TYPE_MIN = 2;

	/**
	 * 重复间隔：小时
	 */
	public static final int INTERVAL_TYPE_HOUR = 3;
	/**
	 * 绑定任务id
	 */
	private Integer jobId;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 触发器名称
	 */
	private String triggerName;

	/**
	 * 触发器编码
	 */
	private String triggerCode;

	/**
	 * 触发器分组编码
	 */
	private String triggerGroup;

	/**
	 * 触发器类型(简单型[simple] or cron型[cron])
	 */
	private Integer triggerType;

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 重复间隔
	 */
	private Integer repeatInterval;

	/**
	 * 重复时间单位,默认MS（毫秒）
	 */
	private Integer intervalType = INTERVAL_TYPE_MILSEC;

	/**
	 * 重复次数：-1表示不限制次数（直到过期）
	 */
	private Integer repeatCount = -1;

	/**
	 * cron表达式
	 */
	private String cronExpress;
	/**
	 * 创建人
	 */
	private Integer creatBy;
	/**
	 * 创建时间
	 */
	private Date creatDt;
	/**
	 * 修改人
	 */
	private Integer modifyBy;
	/**
	 * 修改时间
	 */
	private Date modifyDt;
	/**
	 * 删除标识
	 */
	private Integer dr;
	/**
	 * 触发器状态 不存在 0; 激活 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 */
	private int state;
	/**
	 * 触发器状态 不存在 0; 激活 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 */
	private String stateValue;

	/**
	 * 触发器状态 不存在 0; 激活1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 */
	public int getState() {
		return state;
	}

	/**
	 * 触发器状态 不存在 0; 激活 1 ;挂起2 ;完成 3 ;错误 4 ;阻塞 5
	 */
	public void setState(int state) {
		this.state = state;
		setStateValue(this.state);
	}

	public String getStateValue() {
		return stateValue;
	}

	private void setStateValue(int state) {
		if (state == 0) {
			stateValue = "不存在";
		} else if (state == 1) {
			stateValue = "激活";
		} else if (state == 2) {
			stateValue = "暂停";
		} else if (state == 3) {
			stateValue = "完成";
		} else if (state == 4) {
			stateValue = "错误";
		} else if (state == 5) {
			stateValue = "阻塞";
		}
	}

	public Integer getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(Integer creatBy) {
		this.creatBy = creatBy;
	}

	public Date getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getTriggerName() {

		return triggerName;
	}

	public void setTriggerName(String triggerName) {

		this.triggerName = triggerName;
	}

	public String getTriggerCode() {

		return triggerCode;
	}

	public void setTriggerCode(String triggerCode) {

		this.triggerCode = triggerCode;
	}

	public String getTriggerGroup() {

		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {

		this.triggerGroup = triggerGroup;
	}

	public Integer getTriggerType() {

		return triggerType;
	}

	public void setTriggerType(Integer triggerType) {

		this.triggerType = triggerType;
	}

	public String getStartTime() {

		return startTime;
	}

	public void setStartTime(String startTime) {

		this.startTime = startTime;
	}

	public String getEndTime() {

		return endTime;
	}

	public void setEndTime(String endTime) {

		this.endTime = endTime;
	}

	public Integer getRepeatInterval() {

		return repeatInterval;
	}

	public void setRepeatInterval(Integer repeatInterval) {

		this.repeatInterval = repeatInterval;
	}

	public Integer getIntervalType() {

		return intervalType;
	}

	public void setIntervalType(Integer intervalType) {

		this.intervalType = intervalType;
	}

	public Integer getRepeatCount() {

		return repeatCount;
	}

	public void setRepeatCount(Integer repeatCount) {

		this.repeatCount = repeatCount;
	}

	public String getCronExpress() {

		return cronExpress;
	}

	public void setCronExpress(String cronExpress) {

		this.cronExpress = cronExpress;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

}
