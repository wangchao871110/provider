package com.xfs.timer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务对象
 * 
 * @author wangdx
 * 
 * @date 2016-6-6
 */
public class TaskJob implements Serializable {
	/**
	 * DEFAULT_GROUP
	 */
	public static final String DEFAULT_GROUP_NAME = "DEFAULT_GROUP";// 默认任务分组编码

	@Override
	public String toString() {
		return "TaskJob [id=" + id + ", jobName=" + jobName + ", jobCode=" + jobCode + ", jobGroup=" + jobGroup
				+ ", jobClass=" + jobClass + ", jobListener=" + jobListener + ", jobPara=" + jobPara + ", description="
				+ description + ", creatBy=" + creatBy + ", creatDt=" + creatDt + ", modifyBy=" + modifyBy
				+ ", modifyDt=" + modifyDt + ", dr=" + dr + ", dataMap=" + dataMap + "]";
	}

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务编码
	 */
	private String jobCode;

	/**
	 * 任务分组编码
	 */
	private String jobGroup = DEFAULT_GROUP_NAME;

	/**
	 * 任务执行类
	 */
	private String jobClass;

	/**
	 * 任务监听
	 */
	private String jobListener;

	/**
	 * 任务参数
	 */
	private String jobPara;

	/**
	 * 描述
	 */
	private String description;
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
	 * 正在运行的触发器个数
	 */
	private int runningTriiger;

	/**
	 * job所绑定的触发器集合
	 */
	private List<TaskTrigger> triggerList;

	public static TaskJob getClearJob() {
		TaskJob job = new TaskJob();
		job.setJobGroup(null);
		return job;
	}

	public List<TaskTrigger> getTriggerList() {
		return triggerList;
	}

	public void setTriggerList(List<TaskTrigger> triggerList) {
		this.triggerList = triggerList;
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

	/**
	 * 参数Map,一般用于API调用时放值
	 */
	private Map<String, String> dataMap = new HashMap<String, String>();

	public TaskJob() {
		super();
	}

	public TaskJob(String jobName, String jobGroup) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getJobName() {

		return jobName;
	}

	public void setJobName(String jobName) {

		this.jobName = jobName;
	}

	public String getJobCode() {

		return jobCode;
	}

	public void setJobCode(String jobCode) {

		this.jobCode = jobCode;
	}

	public String getJobGroup() {

		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {

		this.jobGroup = jobGroup;
	}

	public String getJobClass() {

		return jobClass;
	}

	public void setJobClass(String jobClass) {

		this.jobClass = jobClass;
	}

	public String getJobListener() {

		return jobListener;
	}

	public void setJobListener(String jobListener) {

		this.jobListener = jobListener;
	}

	public String getJobPara() {

		return jobPara;
	}

	public void setJobPara(String jobPara) {

		this.jobPara = jobPara;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public Map<String, String> getDataMap() {

		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {

		this.dataMap = dataMap;
	}

	public int getRunningTriiger() {
		return runningTriiger;
	}

	public void setRunningTriiger(int runningTriiger) {
		this.runningTriiger = runningTriiger;
	}

}
