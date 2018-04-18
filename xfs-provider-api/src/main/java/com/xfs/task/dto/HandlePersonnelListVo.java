package com.xfs.task.dto;

import com.xfs.ts.model.SpsTsCitycode;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 社保公积金看板数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:42:33
 * @version 	: V1.0
 */
public class HandlePersonnelListVo {
	
	private String name; // 姓名
	private String code; // 身份证号
	private String cityName; // 城市
	private String mobile;//电话
	private String livingType;//户口性质
	private String insuranceSalary;//社保申报工资
	private String fundSalary;//公积金申报工资
	private String beginPeriod;//参保月份
	private String insuranceFundType; // 服务类型
	private String bstype; // 办理类型
	private String stateFiledName; // 办理状态
	private Date createDt; // 申请时间
	private Date modifyDt;
	private String picUrl;//异常截图
	private String errmsg;//异常信息
	private String taskId;//任务单ID
	private Integer areaId;//地区
	private Integer schemeId;//方案ID
	private SpsTsCitycode spsTsCitycode;
	private String json;//任务单动态字段
	private String taskIds;
	private String sex;// 性别
	private String nation;// 民族
	private String countryregion;// 国籍
	private String education;// 学历
	private String workdate;// 参加工作日期
	private String identityType;// 证件类型
	private List<Map<String,Object>> otherParm;// 人员其他信息
	private String headPhoto; // 头像
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getInsuranceFundType() {
		return insuranceFundType;
	}
	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}
	public String getBstype() {
		return bstype;
	}
	public void setBstype(String bstype) {
		this.bstype = bstype;
	}
	public String getStateFiledName() {
		return stateFiledName;
	}
	public void setStateFiledName(String stateFiledName) {
		this.stateFiledName = stateFiledName;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLivingType() {
		return livingType;
	}

	public void setLivingType(String livingType) {
		this.livingType = livingType;
	}

	public String getInsuranceSalary() {
		return insuranceSalary;
	}

	public void setInsuranceSalary(String insuranceSalary) {
		this.insuranceSalary = insuranceSalary;
	}

	public String getFundSalary() {
		return fundSalary;
	}

	public void setFundSalary(String fundSalary) {
		this.fundSalary = fundSalary;
	}

	public String getBeginPeriod() {
		return beginPeriod;
	}

	public void setBeginPeriod(String beginPeriod) {
		this.beginPeriod = beginPeriod;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public SpsTsCitycode getSpsTsCitycode() {
		return spsTsCitycode;
	}

	public void setSpsTsCitycode(SpsTsCitycode spsTsCitycode) {
		this.spsTsCitycode = spsTsCitycode;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String toString() {
		return String.valueOf(getTaskId());
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
	public List<Map<String, Object>> getOtherParm() {
		return otherParm;
	}
	public void setOtherParm(List<Map<String, Object>> otherParm) {
		this.otherParm = otherParm;
	}
	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

}
