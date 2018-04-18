package com.xfs.acc.dto;

import java.util.List;
import java.util.Map;

/**
 * 社保规则比例详情数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class InsuranceLivingTypeDetailsVo {
	private Integer insuranceId;// 险种ID
	private Integer ratioId;// 比例ID
	private String insuranceName;// 险种名称
	private Double corpAddition;// 单位附加金额
	private Double corpRatio;// 单位比例
	private Double psnAddition;// 个人附加金额
	private Double psnRatio; // 个人比例
	private String upperNum;// 基数上限
	private String lowerNum;// 基数下限
	private List<InsuranceLivingTypeDetailsVo> otherRatio;// 其他比例
	private List<Map<String, Object>> useLivingType;// 比例适用户口性质
	private String liveTypes;// 适用户口性质
	private String isDefault;// 是否默认
	private String billingCycle;// 缴纳类型 月缴:MONTH;年缴不足一年按月:MONTH_ANNUALY;年缴不足一年按年:YEAR_ANNUALY
	
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Integer getRatioId() {
		return ratioId;
	}
	public void setRatioId(Integer ratioId) {
		this.ratioId = ratioId;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public Double getCorpAddition() {
		return corpAddition;
	}
	public void setCorpAddition(Double corpAddition) {
		this.corpAddition = corpAddition;
	}
	public Double getCorpRatio() {
		return corpRatio;
	}
	public void setCorpRatio(Double corpRatio) {
		this.corpRatio = corpRatio;
	}
	public Double getPsnAddition() {
		return psnAddition;
	}
	public void setPsnAddition(Double psnAddition) {
		this.psnAddition = psnAddition;
	}
	public Double getPsnRatio() {
		return psnRatio;
	}
	public void setPsnRatio(Double psnRatio) {
		this.psnRatio = psnRatio;
	}
	public String getUpperNum() {
		return upperNum;
	}
	public void setUpperNum(String upperNum) {
		this.upperNum = upperNum;
	}
	public String getLowerNum() {
		return lowerNum;
	}
	public void setLowerNum(String lowerNum) {
		this.lowerNum = lowerNum;
	}
	public List<InsuranceLivingTypeDetailsVo> getOtherRatio() {
		return otherRatio;
	}
	public void setOtherRatio(List<InsuranceLivingTypeDetailsVo> otherRatio) {
		this.otherRatio = otherRatio;
	}
	public List<Map<String, Object>> getUseLivingType() {
		return useLivingType;
	}
	public void setUseLivingType(List<Map<String, Object>> useLivingType) {
		this.useLivingType = useLivingType;
	}
	public String getLiveTypes() {
		return liveTypes;
	}
	public void setLiveTypes(String liveTypes) {
		this.liveTypes = liveTypes;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	
 }
