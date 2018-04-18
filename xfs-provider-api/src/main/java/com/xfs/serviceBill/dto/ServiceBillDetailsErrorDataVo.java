package com.xfs.serviceBill.dto;

import java.math.BigDecimal;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillDetailsErrorDataVo {
	
	private BigDecimal insCost = BigDecimal.ZERO;// 社保合计--社保成本
	private BigDecimal insServiceBill = BigDecimal.ZERO;// 社保合计--服务商账单
	private BigDecimal insDifference = BigDecimal.ZERO;// 社保合计--差额
	
	private BigDecimal fundCost = BigDecimal.ZERO;// 公积金合计--公积金成本
	private BigDecimal fundServiceBill = BigDecimal.ZERO;// 公积金合计--服务商账单
	private BigDecimal fundDifference = BigDecimal.ZERO;// 公积金合计--差额
	
	private BigDecimal officeCost = BigDecimal.ZERO;// 官费合计--总成本
	private BigDecimal officeServiceBill = BigDecimal.ZERO;// 官费合计--服务商
	private BigDecimal officeDifference = BigDecimal.ZERO;// 官费合计--差额
	public BigDecimal getInsCost() {
		return insCost;
	}
	public void setInsCost(BigDecimal insCost) {
		this.insCost = insCost;
	}
	public BigDecimal getInsServiceBill() {
		return insServiceBill;
	}
	public void setInsServiceBill(BigDecimal insServiceBill) {
		this.insServiceBill = insServiceBill;
	}
	public BigDecimal getInsDifference() {
		return insDifference;
	}
	public void setInsDifference(BigDecimal insDifference) {
		this.insDifference = insDifference;
	}
	public BigDecimal getFundCost() {
		return fundCost;
	}
	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}
	public BigDecimal getFundServiceBill() {
		return fundServiceBill;
	}
	public void setFundServiceBill(BigDecimal fundServiceBill) {
		this.fundServiceBill = fundServiceBill;
	}
	public BigDecimal getFundDifference() {
		return fundDifference;
	}
	public void setFundDifference(BigDecimal fundDifference) {
		this.fundDifference = fundDifference;
	}
	public BigDecimal getOfficeCost() {
		return officeCost;
	}
	public void setOfficeCost(BigDecimal officeCost) {
		this.officeCost = officeCost;
	}
	public BigDecimal getOfficeServiceBill() {
		return officeServiceBill;
	}
	public void setOfficeServiceBill(BigDecimal officeServiceBill) {
		this.officeServiceBill = officeServiceBill;
	}
	public BigDecimal getOfficeDifference() {
		return officeDifference;
	}
	public void setOfficeDifference(BigDecimal officeDifference) {
		this.officeDifference = officeDifference;
	}
	
 }
