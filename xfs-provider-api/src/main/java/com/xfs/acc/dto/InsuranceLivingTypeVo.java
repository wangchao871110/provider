package com.xfs.acc.dto;

import java.util.List;

/**
 * 社保规则比例数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class InsuranceLivingTypeVo {
	private String peopleTypeCode;// 户口性质CODE
	private String peopleTypeName;// 户口性质名称
	private List<InsuranceLivingTypeDetailsVo> insuranceLivingType;
	public String getPeopleTypeCode() {
		return peopleTypeCode;
	}
	public void setPeopleTypeCode(String peopleTypeCode) {
		this.peopleTypeCode = peopleTypeCode;
	}
	public String getPeopleTypeName() {
		return peopleTypeName;
	}
	public void setPeopleTypeName(String peopleTypeName) {
		this.peopleTypeName = peopleTypeName;
	}
	public List<InsuranceLivingTypeDetailsVo> getInsuranceLivingType() {
		return insuranceLivingType;
	}
	public void setInsuranceLivingType(List<InsuranceLivingTypeDetailsVo> insuranceLivingType) {
		this.insuranceLivingType = insuranceLivingType;
	}
	
 }
