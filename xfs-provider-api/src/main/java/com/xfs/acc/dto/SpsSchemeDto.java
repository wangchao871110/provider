package com.xfs.acc.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务商管理 方案实体
 *
 * @author lifq
 *
 * 2017年7月31日  下午4:28:55
 */
public class SpsSchemeDto{
	
	private Integer schemeId;//方案id
    private String schemeName;// 方案名称
	
	private Integer billDate;//账单日
	private Integer endDate;//截止日
	private BigDecimal price;//服务费
	private Integer areaId;
	private Integer insuranceAccountId;//社保库id
	private Integer fundAccountId;//公积金库id
	private String memo;//供应商名称
	private List<InsuranceRatio> insuranceRatio;// 社保比例
	private List<FundRatio> fundRatio;// 公积金比例 
	
	
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
	public Integer getBillDate() {
		return billDate;
	}
	public void setBillDate(Integer billDate) {
		this.billDate = billDate;
	}
	public Integer getEndDate() {
		return endDate;
	}
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getInsuranceAccountId() {
		return insuranceAccountId;
	}
	public void setInsuranceAccountId(Integer insuranceAccountId) {
		this.insuranceAccountId = insuranceAccountId;
	}
	public Integer getFundAccountId() {
		return fundAccountId;
	}
	public void setFundAccountId(Integer fundAccountId) {
		this.fundAccountId = fundAccountId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<InsuranceRatio> getInsuranceRatio() {
		return insuranceRatio;
	}
	public void setInsuranceRatio(List<InsuranceRatio> insuranceRatio) {
		this.insuranceRatio = insuranceRatio;
	}
	public List<FundRatio> getFundRatio() {
		return fundRatio;
	}
	public void setFundRatio(List<FundRatio> fundRatio) {
		this.fundRatio = fundRatio;
	}
	
	
	
}
