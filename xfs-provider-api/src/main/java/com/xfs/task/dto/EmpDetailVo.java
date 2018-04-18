package com.xfs.task.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.SysDictitem;
import com.xfs.sp.model.SpsScheme;

/**
 * 任务中心--人员详细信息实体
 * @author 	: wangchao
 * @date 	: 2017年3月15日 下午2:41:44
 * @version 	: V1.0
 */
public class EmpDetailVo {
	private String taskType;// 任务类型
	private Integer taskId;// 任务单ID
	private Integer empId;// 人员ID
	private String userName;// 人员名称
	private String code;// 证件号码
	private Integer ctiyId;// 城市ID
	private String ctiyName;// 城市名称
	private String insFundType;//保险类型
	private String bstypeName;//网申业务
	private Integer isFund = 0;// 是否缴纳公积金 0：未缴纳；1：缴纳
	private Integer isIns = 0;// 是否缴纳社保 0：未缴纳；1：缴纳
	private Integer schemeType = 0;// 方案类型 0：表示是自服务;1：表示不是自服务
	private String typeCode;// 默认户口性质CODE
	private String typeName;// 默认户口类型名称
	private List<SysDictitem> liveType;//所有户口性质
	private Integer schemeId;// 默认方案ID
	private String schemeName;// 默认方案名称
	private List<SpsScheme> selfScheme;// 自服务方案
	private List<SpsScheme> entrustScheme;// 外包方案
	private BigDecimal insuranceSalary = BigDecimal.ZERO;// 社保申报基数
	private BigDecimal fund = BigDecimal.ZERO;// 公积金申报基数
	private String defaultInsFundDate;// 默认参保月份
	private List<Map<String, String>> insFundDate;//参保月份
	private String headPhoto;// 头像ID
	//private List<Map<String,Object>> otherParm;// 人员其他信息
	private List<BusinessField> bdBusinessfieldList;// 人员动态信息
	//private List<Map<String, Object>> file;// 附件
	private String type;// 任务状态 待处理:TODO 已完成:COMPLETED 异常:ERROR 提交:SUBMIT
	private String errmsg;// 错误信息
	private Integer filedId;// 任务小状态ID
	private String filedName;// 任务小状态名称
	private Integer messageId;// 消息ID
	private Integer auditId;// 中间表ID
	private List<Map<String, Object>> hospital;// 医院
	
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
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
	public Integer getCtiyId() {
		return ctiyId;
	}
	public void setCtiyId(Integer ctiyId) {
		this.ctiyId = ctiyId;
	}
	public String getCtiyName() {
		return ctiyName;
	}
	public void setCtiyName(String ctiyName) {
		this.ctiyName = ctiyName;
	}
	public String getInsFundType() {
		return insFundType;
	}
	public void setInsFundType(String insFundType) {
		this.insFundType = insFundType;
	}
	public String getBstypeName() {
		return bstypeName;
	}
	public void setBstypeName(String bstypeName) {
		this.bstypeName = bstypeName;
	}
	public Integer getIsFund() {
		return isFund;
	}
	public void setIsFund(Integer isFund) {
		this.isFund = isFund;
	}
	public Integer getIsIns() {
		return isIns;
	}
	public void setIsIns(Integer isIns) {
		this.isIns = isIns;
	}
	public Integer getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(Integer schemeType) {
		this.schemeType = schemeType;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<SysDictitem> getLiveType() {
		return liveType;
	}
	public void setLiveType(List<SysDictitem> liveType) {
		this.liveType = liveType;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public List<SpsScheme> getSelfScheme() {
		return selfScheme;
	}
	public void setSelfScheme(List<SpsScheme> selfScheme) {
		this.selfScheme = selfScheme;
	}
	public List<SpsScheme> getEntrustScheme() {
		return entrustScheme;
	}
	public void setEntrustScheme(List<SpsScheme> entrustScheme) {
		this.entrustScheme = entrustScheme;
	}
	public BigDecimal getInsuranceSalary() {
		return insuranceSalary;
	}
	public void setInsuranceSalary(BigDecimal insuranceSalary) {
		this.insuranceSalary = insuranceSalary;
	}
	public BigDecimal getFund() {
		return fund;
	}
	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}
	public String getDefaultInsFundDate() {
		return defaultInsFundDate;
	}
	public void setDefaultInsFundDate(String defaultInsFundDate) {
		this.defaultInsFundDate = defaultInsFundDate;
	}
	public List<Map<String, String>> getInsFundDate() {
		return insFundDate;
	}
	public void setInsFundDate(List<Map<String, String>> insFundDate) {
		this.insFundDate = insFundDate;
	}
	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getFiledId() {
		return filedId;
	}
	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getAuditId() {
		return auditId;
	}
	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
	public List<BusinessField> getBdBusinessfieldList() {
		return bdBusinessfieldList;
	}
	public void setBdBusinessfieldList(List<BusinessField> bdBusinessfieldList) {
		this.bdBusinessfieldList = bdBusinessfieldList;
	}
	public List<Map<String, Object>> getHospital() {
		return hospital;
	}
	public void setHospital(List<Map<String, Object>> hospital) {
		this.hospital = hospital;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
 }
