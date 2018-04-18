package com.xfs.msg.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.base.model.SysDictitem;
import com.xfs.sp.model.SpsScheme;

/**
 * 其他业务办理状态实体
 * @author 	: wangchao
 * @date 	: 2017年3月15日 下午2:41:44
 * @version 	: V1.0
 */
public class MessageVo {
	private Integer empId;// 人员ID
	private String userName;// 人员名称
	private String code;// 证件号码
	private String mobile;// 手机号
	private Integer ctiyId;// 城市ID
	private String ctiyName;// 城市名称
	private String typeCode;// 户口类型
	private String typeName;// 户口类型
	private Integer schemeType = 0;// 方案类型 0：表示是自服务;1：表示不是自服务
	private Integer schemeId;// 方案ID
	private String schemeName;// 方案名称
	private BigDecimal insurance = BigDecimal.ZERO;// 社保申报基数
	private BigDecimal fund = BigDecimal.ZERO;// 公积金申报基数
	private String json;// 其他参数
	private List<Map<String,Object>> otherParm;// 人员其他信息
	private Integer msgId;// 消息ID
	private String state;// 消息状态
	private List<Map<String, String>> insuranceFundDate;//参保月份
	private List<SysDictitem> liveType;//户口性质
	private String defaultInsuranceFundDate;// 默认参保月份
	private Integer headPhoto;// 头像ID
	private String isRead;//是否已读
	private List<SpsScheme> selfScheme;// 自服务方案
	private List<SpsScheme> entrustScheme;// 外包方案
	private Integer isFund = 0;// 是否缴纳公积金 0：未缴纳；1：缴纳
	private Integer isIns = 0;// 是否缴纳社保 0：未缴纳；1：缴纳
	private String sex;// 性别
	private String nation;// 民族
	private String countryregion;// 国籍
	private String education;// 学历
	private String workdate;// 参加工作日期
	private String identityType;// 证件类型
	
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
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getFund() {
		return fund;
	}
	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Map<String, String>> getInsuranceFundDate() {
		return insuranceFundDate;
	}
	public void setInsuranceFundDate(List<Map<String, String>> insuranceFundDate) {
		this.insuranceFundDate = insuranceFundDate;
	}
	public List<SysDictitem> getLiveType() {
		return liveType;
	}
	public void setLiveType(List<SysDictitem> liveType) {
		this.liveType = liveType;
	}
	public String getDefaultInsuranceFundDate() {
		return defaultInsuranceFundDate;
	}
	public void setDefaultInsuranceFundDate(String defaultInsuranceFundDate) {
		this.defaultInsuranceFundDate = defaultInsuranceFundDate;
	}
	public Integer getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(Integer headPhoto) {
		this.headPhoto = headPhoto;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
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
	public Integer getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(Integer schemeType) {
		this.schemeType = schemeType;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCountryregion() {
		return countryregion;
	}
	public void setCountryregion(String countryregion) {
		this.countryregion = countryregion;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

 }
