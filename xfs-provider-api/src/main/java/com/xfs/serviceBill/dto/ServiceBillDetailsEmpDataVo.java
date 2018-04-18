package com.xfs.serviceBill.dto;

import java.math.BigDecimal;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillDetailsEmpDataVo {
	
	private String theirPeriod;// 所属月
	private String insuranceType;// 保险类型
	private BigDecimal corpPayBase = BigDecimal.ZERO;// 员工基数
	private BigDecimal empPayBase = BigDecimal.ZERO;// 员工基数
	private String corpRatio ;// 企业比例
	private String empRatio ;// 个人比例
	
	private BigDecimal sysCorpPayment = BigDecimal.ZERO;// 系统成本统计--企业成本
	private BigDecimal sysEmpPayment = BigDecimal.ZERO;// 系统成本统计--个人成本
	private BigDecimal sysCount = BigDecimal.ZERO;// 系统成本统计--总额
	
	private BigDecimal serviceCorpPayment = BigDecimal.ZERO;// 服务商账单--企业成本
	private BigDecimal serviceEmpPayment = BigDecimal.ZERO;// 服务商账单--个人成本
	private BigDecimal serviceCount = BigDecimal.ZERO;// 服务商账单--总额
	
	private Integer corpPaymentColor = 0;// 系统企业成本与服务商企业成本是否相等 0：相等，1：不等
	private Integer empPaymentColor = 0;// 系统个人成本与服务商个人成本是否相等 0：相等，1：不等
	private Integer countColor = 0;// 系统总成本与服务商总成本是否相等 0：相等，1：不等
	public String getTheirPeriod() {
		return theirPeriod;
	}
	public void setTheirPeriod(String theirPeriod) {
		this.theirPeriod = theirPeriod;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public BigDecimal getCorpPayBase() {
		return corpPayBase;
	}
	public void setCorpPayBase(BigDecimal corpPayBase) {
		this.corpPayBase = corpPayBase;
	}
	public BigDecimal getEmpPayBase() {
		return empPayBase;
	}
	public void setEmpPayBase(BigDecimal empPayBase) {
		this.empPayBase = empPayBase;
	}
	public String getCorpRatio() {
		return corpRatio;
	}
	public void setCorpRatio(String corpRatio) {
		this.corpRatio = corpRatio;
	}
	public String getEmpRatio() {
		return empRatio;
	}
	public void setEmpRatio(String empRatio) {
		this.empRatio = empRatio;
	}
	public BigDecimal getSysCorpPayment() {
		return sysCorpPayment;
	}
	public void setSysCorpPayment(BigDecimal sysCorpPayment) {
		this.sysCorpPayment = sysCorpPayment;
	}
	public BigDecimal getSysEmpPayment() {
		return sysEmpPayment;
	}
	public void setSysEmpPayment(BigDecimal sysEmpPayment) {
		this.sysEmpPayment = sysEmpPayment;
	}
	public BigDecimal getSysCount() {
		return sysCount;
	}
	public void setSysCount(BigDecimal sysCount) {
		this.sysCount = sysCount;
	}
	public BigDecimal getServiceCorpPayment() {
		return serviceCorpPayment;
	}
	public void setServiceCorpPayment(BigDecimal serviceCorpPayment) {
		this.serviceCorpPayment = serviceCorpPayment;
	}
	public BigDecimal getServiceEmpPayment() {
		return serviceEmpPayment;
	}
	public void setServiceEmpPayment(BigDecimal serviceEmpPayment) {
		this.serviceEmpPayment = serviceEmpPayment;
	}
	public BigDecimal getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(BigDecimal serviceCount) {
		this.serviceCount = serviceCount;
	}
	public Integer getCorpPaymentColor() {
		return corpPaymentColor;
	}
	public void setCorpPaymentColor(Integer corpPaymentColor) {
		this.corpPaymentColor = corpPaymentColor;
	}
	public Integer getEmpPaymentColor() {
		return empPaymentColor;
	}
	public void setEmpPaymentColor(Integer empPaymentColor) {
		this.empPaymentColor = empPaymentColor;
	}
	public Integer getCountColor() {
		return countColor;
	}
	public void setCountColor(Integer countColor) {
		this.countColor = countColor;
	}
	
 }
