package com.xfs.msg.dto;

import java.util.List;
import java.util.Map;

/**
 * 其他业务办理状态实体
 * @author 	: wangchao
 * @date 	: 2017年3月15日 下午2:41:44
 * @version 	: V1.0
 */
public class UserBusinessVo {
	private Integer empId;// 人员ID
	private String userName;// 人员名称
	private String code;// 证件号码
	private String mobile;// 手机号
	private String ctiyName;// 城市名称
	private String typeName;// 户口类型
	private List<Map<String,Object>> otherParm;// 人员其他信息
	private Integer msgId;// 消息ID
	private String taskId;// 任务单ID
	private List<Map<String, Object>> file;// 附件
	private String modifyDt;// 变更时间
	private String bsTypeName;// 业务名称
	private String state;// 消息状态
	private String title;// 消息标题
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCtiyName() {
		return ctiyName;
	}
	public void setCtiyName(String ctiyName) {
		this.ctiyName = ctiyName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<Map<String, Object>> getOtherParm() {
		return otherParm;
	}
	public void setOtherParm(List<Map<String, Object>> otherParm) {
		this.otherParm = otherParm;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<Map<String, Object>> getFile() {
		return file;
	}
	public void setFile(List<Map<String, Object>> file) {
		this.file = file;
	}
	public String getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}
	public String getBsTypeName() {
		return bsTypeName;
	}
	public void setBsTypeName(String bsTypeName) {
		this.bsTypeName = bsTypeName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
 }
