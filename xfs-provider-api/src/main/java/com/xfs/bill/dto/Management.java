package com.xfs.bill.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Management  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String spName;
	private Integer spId;
	private String corp_name;
	private String accountBank;//银行名字
	private String accountBranchBank;//银行分支名字
	private String accountName;
	private Integer billId;//主键
	private BigDecimal billAmount;//总金额    官费
	private BigDecimal expendOfficeFee;//请款金额
	private String period;//请款月份
	private Integer expendStatus;//请款状态  0:未支付 1:已支付 2:不支付
	
	private Integer expendServiceStatus;//服务费 请款状态 0:未支付 1:已支付 2:不支付
	private Date expendServiceTime;//服务费 出纳审核时间 
	private BigDecimal gain;//分润
	private BigDecimal perpayServiceFee;//服务费 总金额
	private BigDecimal expendServiceFee;//服务费 请款金额   
    private String username;
    private Integer servicefeeby;
    
    private String billType;//账单状态
	
	
	
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getServicefeeby() {
		return servicefeeby;
	}

	public void setServicefeeby(Integer servicefeeby) {
		this.servicefeeby = servicefeeby;
	}

	private String billNum;//账单号
	private String billState;//付款状态
	
	private String remark;//备注
	
	private Integer billingDay;//请款日

	private String accountNo;

	private Date expendTime;
	
	private String accountMobile;

	private String orderId;

	private Integer cpId;

	
	
	public Integer getBillingDay() {
		return billingDay;
	}

	public void setBillingDay(Integer billingDay) {
		this.billingDay = billingDay;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountBranchBank() {
		return accountBranchBank;
	}

	public void setAccountBranchBank(String accountBranchBank) {
		this.accountBranchBank = accountBranchBank;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public BigDecimal getExpendOfficeFee() {
		return expendOfficeFee;
	}

	public void setExpendOfficeFee(BigDecimal expendOfficeFee) {
		this.expendOfficeFee = expendOfficeFee;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getExpendStatus() {
		return expendStatus;
	}

	public void setExpendStatus(Integer expendStatus) {
		this.expendStatus = expendStatus;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getExpendTime() {
		return expendTime;
	}

	public void setExpendTime(Date expendTime) {
		this.expendTime = expendTime;
	}

	public String getAccountMobile() {
		return accountMobile;
	}

	public void setAccountMobile(String accountMobile) {
		this.accountMobile = accountMobile;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getExpendServiceStatus() {
		return expendServiceStatus;
	}

	public void setExpendServiceStatus(Integer expendServiceStatus) {
		this.expendServiceStatus = expendServiceStatus;
	}

	public Date getExpendServiceTime() {
		return expendServiceTime;
	}

	public void setExpendServiceTime(Date expendServiceTime) {
		this.expendServiceTime = expendServiceTime;
	}

	public BigDecimal getGain() {
		return gain;
	}

	public void setGain(BigDecimal gain) {
		this.gain = gain;
	}

	public BigDecimal getPerpayServiceFee() {
		return perpayServiceFee;
	}

	public void setPerpayServiceFee(BigDecimal perpayServiceFee) {
		this.perpayServiceFee = perpayServiceFee;
	}

	public BigDecimal getExpendServiceFee() {
		return expendServiceFee;
	}

	public void setExpendServiceFee(BigDecimal expendServiceFee) {
		this.expendServiceFee = expendServiceFee;
	}


	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
}
