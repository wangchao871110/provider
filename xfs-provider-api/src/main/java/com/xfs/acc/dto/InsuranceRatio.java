package com.xfs.acc.dto;

import java.util.List;

/**
 * 保存方案请求实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:22:31
 * @version 	: V1.0
 */
public class InsuranceRatio{
	private String insuranceLivingType;// 户口性质
	private List<Ratio> ratio;// 比例ID
	public String getInsuranceLivingType() {
		return insuranceLivingType;
	}
	public void setInsuranceLivingType(String insuranceLivingType) {
		this.insuranceLivingType = insuranceLivingType;
	}
	public List<Ratio> getRatio() {
		return ratio;
	}
	public void setRatio(List<Ratio> ratio) {
		this.ratio = ratio;
	}
	
}
