package com.xfs.cp.model;

import java.util.*;
import java.lang.Integer;import java.lang.String;import java.util.Date;


/**
 * CpBusinessIntroduction
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/01/09 14:41:40	
 */
public class CpBusinessIntroduction implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer cpId;//服务商ID	public Integer getCpId() {	    return this.cpId;	}	public void setCpId(Integer cpId) {	    this.cpId=cpId;	}	private Integer socialAddDateType;//本地社保增员申报截止日类型,0:为当月缴当月,1:为当月缴次月	public Integer getSocialAddDateType() {	    return this.socialAddDateType;	}	public void setSocialAddDateType(Integer socialAddDateType) {	    this.socialAddDateType=socialAddDateType;	}	private Integer socialAddDate;//本地社保增员申报截止日	public Integer getSocialAddDate() {	    return this.socialAddDate;	}	public void setSocialAddDate(Integer socialAddDate) {	    this.socialAddDate=socialAddDate;	}	private Integer socialReductionDateType;//本地社保减员申报截止日类型：0=当月减当月，当月不产生费用,1=当月减次月，当月不产生费用	public Integer getSocialReductionDateType() {	    return this.socialReductionDateType;	}	public void setSocialReductionDateType(Integer socialReductionDateType) {	    this.socialReductionDateType=socialReductionDateType;	}	private Integer socialReductionDate;//本地社保减员申报截止日	public Integer getSocialReductionDate() {	    return this.socialReductionDate;	}	public void setSocialReductionDate(Integer socialReductionDate) {	    this.socialReductionDate=socialReductionDate;	}	private String accumulationFundRatio;//公积金可选缴纳比例	public String getAccumulationFundRatio() {	    return this.accumulationFundRatio;	}	public void setAccumulationFundRatio(String accumulationFundRatio) {	    this.accumulationFundRatio=accumulationFundRatio;	}	private String accumulationFundRatioEq;//公积金可选缴纳比例	public String getAccumulationFundRatioEq() {	    return this.accumulationFundRatioEq;	}	public void setAccumulationFundRatioEq(String accumulationFundRatioEq) {	    this.accumulationFundRatioEq=accumulationFundRatioEq;	}	private Integer residualPremium;//残保金是否必缴，0：否；1：是	public Integer getResidualPremium() {	    return this.residualPremium;	}	public void setResidualPremium(Integer residualPremium) {	    this.residualPremium=residualPremium;	}	private Integer accountsReceivable;//应收账单生成时间	public Integer getAccountsReceivable() {	    return this.accountsReceivable;	}	public void setAccountsReceivable(Integer accountsReceivable) {	    this.accountsReceivable=accountsReceivable;	}	private Integer documentUpload;//实做凭证上传时间	public Integer getDocumentUpload() {	    return this.documentUpload;	}	public void setDocumentUpload(Integer documentUpload) {	    this.documentUpload=documentUpload;	}	private Integer latestDt;//最迟付款日	public Integer getLatestDt() {	    return this.latestDt;	}	public void setLatestDt(Integer latestDt) {	    this.latestDt=latestDt;	}	private Integer isPay;//是否支持垫付，0：否；1：是	public Integer getIsPay() {	    return this.isPay;	}	public void setIsPay(Integer isPay) {	    this.isPay=isPay;	}	private Integer isQa;//是否支持解答日常咨询，0：否，1：是	public Integer getIsQa() {	    return this.isQa;	}	public void setIsQa(Integer isQa) {	    this.isQa=isQa;	}	private Integer collectingMaterial;//是否支持代收材料，0：否；1：是	public Integer getCollectingMaterial() {	    return this.collectingMaterial;	}	public void setCollectingMaterial(Integer collectingMaterial) {	    this.collectingMaterial=collectingMaterial;	}	private Integer createBy;//创建人	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//更新人	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//更新时间	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//更新时间	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//更新时间	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//逻辑删除标记位0:未删除1:已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private Integer insuranceAddDateType;//本地公积金增员申报截止日类型：0=当月缴当月,1=当月缴次月	public Integer getInsuranceAddDateType() {	    return this.insuranceAddDateType;	}	public void setInsuranceAddDateType(Integer insuranceAddDateType) {	    this.insuranceAddDateType=insuranceAddDateType;	}	private Integer insuranceAddDate;//本地公积金增员申报截止日	public Integer getInsuranceAddDate() {	    return this.insuranceAddDate;	}	public void setInsuranceAddDate(Integer insuranceAddDate) {	    this.insuranceAddDate=insuranceAddDate;	}	private Integer insuranceReductionDateType;//本地公积金增员申报截止日类型：0=当月减当月，当月不产生费用,1=当月减次月，当月不产生费用	public Integer getInsuranceReductionDateType() {	    return this.insuranceReductionDateType;	}	public void setInsuranceReductionDateType(Integer insuranceReductionDateType) {	    this.insuranceReductionDateType=insuranceReductionDateType;	}	private Integer insuranceReductionDate;//	public Integer getInsuranceReductionDate() {	    return this.insuranceReductionDate;	}	public void setInsuranceReductionDate(Integer insuranceReductionDate) {	    this.insuranceReductionDate=insuranceReductionDate;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.cpId!=null)			ht.put("cpId",this.cpId);		if(this.socialAddDateType!=null)			ht.put("socialAddDateType",this.socialAddDateType);		if(this.socialAddDate!=null)			ht.put("socialAddDate",this.socialAddDate);		if(this.socialReductionDateType!=null)			ht.put("socialReductionDateType",this.socialReductionDateType);		if(this.socialReductionDate!=null)			ht.put("socialReductionDate",this.socialReductionDate);		if(this.accumulationFundRatio!=null)			ht.put("accumulationFundRatio",this.accumulationFundRatio);		if(this.accumulationFundRatioEq!=null)			ht.put("accumulationFundRatioEq",this.accumulationFundRatioEq);		if(this.residualPremium!=null)			ht.put("residualPremium",this.residualPremium);		if(this.accountsReceivable!=null)			ht.put("accountsReceivable",this.accountsReceivable);		if(this.documentUpload!=null)			ht.put("documentUpload",this.documentUpload);		if(this.latestDt!=null)			ht.put("latestDt",this.latestDt);		if(this.isPay!=null)			ht.put("isPay",this.isPay);		if(this.isQa!=null)			ht.put("isQa",this.isQa);		if(this.collectingMaterial!=null)			ht.put("collectingMaterial",this.collectingMaterial);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.insuranceAddDateType!=null)			ht.put("insuranceAddDateType",this.insuranceAddDateType);		if(this.insuranceAddDate!=null)			ht.put("insuranceAddDate",this.insuranceAddDate);		if(this.insuranceReductionDateType!=null)			ht.put("insuranceReductionDateType",this.insuranceReductionDateType);		if(this.insuranceReductionDate!=null)			ht.put("insuranceReductionDate",this.insuranceReductionDate);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2017/01/09 14:41:40
}

