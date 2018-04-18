package com.xfs.acc.dto;

import java.util.List;

/**
 * 社保规则数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class AreaSocialRuleVo {
	private Integer areaId;// 城市ID
	private Integer schemeId;// 方案ID
	private List<InsuranceLivingTypeDetailsVo> fundType;
	private List<InsuranceLivingTypeVo> insuranceLivingType;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public List<InsuranceLivingTypeDetailsVo> getFundType() {
		return fundType;
	}
	public void setFundType(List<InsuranceLivingTypeDetailsVo> fundType) {
		this.fundType = fundType;
	}
	public List<InsuranceLivingTypeVo> getInsuranceLivingType() {
		return insuranceLivingType;
	}
	public void setInsuranceLivingType(List<InsuranceLivingTypeVo> insuranceLivingType) {
		this.insuranceLivingType = insuranceLivingType;
	}
	
 }
