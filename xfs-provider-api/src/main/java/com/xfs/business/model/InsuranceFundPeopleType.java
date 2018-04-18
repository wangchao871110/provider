package com.xfs.business.model;import java.util.List;/** * 协作户口性质类别实体 * @author 	: wangchao * @date 	: 2017年1月16日 下午3:01:55 * @version 	: V1.0 */public class InsuranceFundPeopleType implements java.io.Serializable {		/**	 * @Fields serialVersionUID :   	 * @since Ver 1.0	 */	private static final long serialVersionUID = -6847684551940524377L;	private String peopleTypeCode;// 户口性质代码	private String peopleTypeName;// 户口性质类别	private String insuranceScope;// 社保基数范围	private String fundScope;// 公积金基数范围	private String fundRatio;// 公积金比例	private String insuranceStopDt;// 社保局操作截止日期	private String fundStopDt;// 公积金中心操作截止日期	private String insuranceAdjustDt;// 社保基数调整时间	private String fundAdjustDt;// 公积金基数调整时间	private String residual;// 残保金	private String bigMedical;// 大病医疗	private List<InsuranceFundType> insuranceFundType; // 社保公积金缴纳类型		public String getPeopleTypeCode() {		return peopleTypeCode;	}	public void setPeopleTypeCode(String peopleTypeCode) {		this.peopleTypeCode = peopleTypeCode;	}	public String getPeopleTypeName() {		return peopleTypeName;	}	public void setPeopleTypeName(String peopleTypeName) {		this.peopleTypeName = peopleTypeName;	}	public String getInsuranceScope() {		return insuranceScope;	}	public void setInsuranceScope(String insuranceScope) {		this.insuranceScope = insuranceScope;	}	public String getFundScope() {		return fundScope;	}	public void setFundScope(String fundScope) {		this.fundScope = fundScope;	}	public String getFundRatio() {		return fundRatio;	}	public void setFundRatio(String fundRatio) {		this.fundRatio = fundRatio;	}	public String getInsuranceStopDt() {		return insuranceStopDt;	}	public void setInsuranceStopDt(String insuranceStopDt) {		this.insuranceStopDt = insuranceStopDt;	}	public String getFundStopDt() {		return fundStopDt;	}	public void setFundStopDt(String fundStopDt) {		this.fundStopDt = fundStopDt;	}	public String getInsuranceAdjustDt() {		return insuranceAdjustDt;	}	public void setInsuranceAdjustDt(String insuranceAdjustDt) {		this.insuranceAdjustDt = insuranceAdjustDt;	}	public String getFundAdjustDt() {		return fundAdjustDt;	}	public void setFundAdjustDt(String fundAdjustDt) {		this.fundAdjustDt = fundAdjustDt;	}	public String getResidual() {		return residual;	}	public void setResidual(String residual) {		this.residual = residual;	}	public String getBigMedical() {		return bigMedical;	}	public void setBigMedical(String bigMedical) {		this.bigMedical = bigMedical;	}	public List<InsuranceFundType> getInsuranceFundType() {		return insuranceFundType;	}	public void setInsuranceFundType(List<InsuranceFundType> insuranceFundType) {		this.insuranceFundType = insuranceFundType;	}    }