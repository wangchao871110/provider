package com.xfs.serviceBill.dto;

import java.math.BigDecimal;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillDetailsListVo {
	
	private Integer empId;// 员工ID
	private String empName;// 员工姓名
	private String code;// 证件号
	private String corpName;// 员工所属公司
	private String areaId;// 城市ID
	private String areaName;// 城市名称
	private String period;// 所属月
	private String insuranceType;// 保险类型
	private String businessType;// 业务类型
	private String businessStatus;// 业务状态
	private BigDecimal insDifference = BigDecimal.ZERO;// 社保差额
	private BigDecimal fundDifference = BigDecimal.ZERO;// 公积金差额
	private BigDecimal officeDifference = BigDecimal.ZERO;// 官费差额
	private BigDecimal serviceFeeDifference = BigDecimal.ZERO;// 服务费差额
	private ServiceBillDetailsErrorDataVo errorData;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	public BigDecimal getInsDifference() {
		return insDifference;
	}
	public void setInsDifference(BigDecimal insDifference) {
		this.insDifference = insDifference;
	}
	public BigDecimal getFundDifference() {
		return fundDifference;
	}
	public void setFundDifference(BigDecimal fundDifference) {
		this.fundDifference = fundDifference;
	}
	public BigDecimal getOfficeDifference() {
		return officeDifference;
	}
	public void setOfficeDifference(BigDecimal officeDifference) {
		this.officeDifference = officeDifference;
	}
	public ServiceBillDetailsErrorDataVo getErrorData() {
		return errorData;
	}
	public void setErrorData(ServiceBillDetailsErrorDataVo errorData) {
		this.errorData = errorData;
	}
	public BigDecimal getServiceFeeDifference() {
		return serviceFeeDifference;
	}
	public void setServiceFeeDifference(BigDecimal serviceFeeDifference) {
		this.serviceFeeDifference = serviceFeeDifference;
	}
	
 }
