package com.xfs.corp.dto;

import java.math.BigDecimal;

/**
 * 员工社保详情
 * @author 	: wangchao
 * @date 	: 2017年5月23日 上午9:17:27
 * @version 	: V1.0
 */
public class EmplnsFundDetailDto  implements java.io.Serializable {
    
    /**
	 * @Fields serialVersionUID :   
	 * @since Ver 1.0
	 */
	private static final long serialVersionUID = 6810866001506672165L;
	private Integer id;// 社保ID
	private String empId;// 人员ID
	private Integer insuranceId;// 险种ID
	private String insuranceName;// 险种名称
	private String insuranceFundType;// 险种类型
	private String insuranceCode; // 险种CODE
	private BigDecimal empPayment;// 个人支付
	private BigDecimal empAddition;// 个人补充
	private BigDecimal corpPayment;// 企业支付
	private BigDecimal corpAddition;// 企业补充
	private String billingCycle;// 缴费周期
	private Integer payPeriod;// 缴费时间
	private String fundPeriod;// 公积金缴费时间
	private String insurancePeriod;// 社保缴费时间
	private String beginPeriod;// 开始缴费时间
	private String endPeriod;// 结束缴费时间
	private String areaName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getInsuranceFundType() {
		return insuranceFundType;
	}
	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}
	public BigDecimal getEmpPayment() {
		return empPayment;
	}
	public void setEmpPayment(BigDecimal empPayment) {
		this.empPayment = empPayment;
	}
	public BigDecimal getEmpAddition() {
		return empAddition;
	}
	public void setEmpAddition(BigDecimal empAddition) {
		this.empAddition = empAddition;
	}
	public BigDecimal getCorpPayment() {
		return corpPayment;
	}
	public void setCorpPayment(BigDecimal corpPayment) {
		this.corpPayment = corpPayment;
	}
	public BigDecimal getCorpAddition() {
		return corpAddition;
	}
	public void setCorpAddition(BigDecimal corpAddition) {
		this.corpAddition = corpAddition;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public Integer getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(Integer payPeriod) {
		this.payPeriod = payPeriod;
	}
	public String getFundPeriod() {
		return fundPeriod;
	}
	public void setFundPeriod(String fundPeriod) {
		this.fundPeriod = fundPeriod;
	}
	public String getInsurancePeriod() {
		return insurancePeriod;
	}
	public void setInsurancePeriod(String insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}
	public String getBeginPeriod() {
		return beginPeriod;
	}
	public void setBeginPeriod(String beginPeriod) {
		this.beginPeriod = beginPeriod;
	}
	public String getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}
	public String getInsuranceCode() {
		return insuranceCode;
	}
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
