package com.xfs.task.dto;

import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午10:10:58
 * @version 	: V1.0
 */
public class TaskCenterDto{
	private Integer cpId;// 企业ID
	private Integer cityId;// 城市ID
	private Integer type;//办理状态 0：待处理，1：处理中，2：已完成
	private Integer stateFiledId;// 任务小状态
	private String name;// 姓名或者证件号码
	private Integer bstype;// 业务状态
	private String insuranceFundType;//保险类型  INSURANCE FUND
	private String queryMonth;// 查询时间
	private String taskType; // 任务类型 ALL：全部，SELFSERVICE：网申任务，SERVICE：服务商任务，ENTRY：入职任务
	private String taskStatus;// 任务状态 PENDING:待处理,ERROR:失败  TODO:处理中, COMPLETED:已完成, CLOSED:已关闭
	private Date outTaskCenterTime;// 退出任务中心时间

	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStateFiledId() {
		return stateFiledId;
	}
	public void setStateFiledId(Integer stateFiledId) {
		this.stateFiledId = stateFiledId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// 数据权限
	private String authority;
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.cityId!=null)
			ht.put("cityId",this.cityId);
		if(this.stateFiledId!=null)
			ht.put("stateFiledId",this.stateFiledId);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.authority!=null)
			ht.put("authority",this.authority);
		if(this.queryMonth!=null)
			ht.put("queryMonth",this.queryMonth);
		if(this.bstype!=null)
			ht.put("bstype",this.bstype);
		if(this.taskType!=null)
			ht.put("taskType",this.taskType);
		if(this.taskStatus!=null)
			ht.put("taskStatus",this.taskStatus);
		if(this.outTaskCenterTime!=null)
			ht.put("outTaskCenterTime",this.outTaskCenterTime);
		return ht;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getBstype() {
		return bstype;
	}
	public void setBstype(Integer bstype) {
		this.bstype = bstype;
	}
	public String getInsuranceFundType() {
		return insuranceFundType;
	}
	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}
	public String getQueryMonth() {
		return queryMonth;
	}
	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Date getOutTaskCenterTime() {
		return outTaskCenterTime;
	}
	public void setOutTaskCenterTime(Date outTaskCenterTime) {
		this.outTaskCenterTime = outTaskCenterTime;
	}
	
}
