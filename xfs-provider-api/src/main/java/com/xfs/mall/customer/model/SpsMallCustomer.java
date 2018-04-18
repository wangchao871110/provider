package com.xfs.mall.customer.model;

import java.util.Date;
import java.util.HashMap;


/**
 * SpsMallCustomer
 * @author:duanax
 * @date:2016/06/15 13:02:25	
 */
public class SpsMallCustomer implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer customerId;//
	public Integer getCustomerId() {
	    return this.customerId;
	}
	public void setCustomerId(Integer customerId) {
	    this.customerId=customerId;
	}
	private Integer spId;//
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private String customerName;//
	public String getCustomerName() {
	    return this.customerName;
	}
	public void setCustomerName(String customerName) {
	    this.customerName=customerName;
	}
	private String customerNameEq;//
	public String getCustomerNameEq() {
	    return this.customerNameEq;
	}
	public void setCustomerNameEq(String customerNameEq) {
	    this.customerNameEq=customerNameEq;
	}
	private String nickName;//
	public String getNickName() {
	    return this.nickName;
	}
	public void setNickName(String nickName) {
	    this.nickName=nickName;
	}
	private String nickNameEq;//
	public String getNickNameEq() {
	    return this.nickNameEq;
	}
	public void setNickNameEq(String nickNameEq) {
	    this.nickNameEq=nickNameEq;
	}
	private String sex;//
	public String getSex() {
	    return this.sex;
	}
	public void setSex(String sex) {
	    this.sex=sex;
	}
	private String sexEq;//
	public String getSexEq() {
	    return this.sexEq;
	}
	public void setSexEq(String sexEq) {
	    this.sexEq=sexEq;
	}
	private String serviceType;//A:企业个人 B：企业C：个人
	public String getServiceType() {
	    return this.serviceType;
	}
	public void setServiceType(String serviceType) {
	    this.serviceType=serviceType;
	}
	private String serviceTypeEq;//A:企业个人 B：企业C：个人
	public String getServiceTypeEq() {
	    return this.serviceTypeEq;
	}
	public void setServiceTypeEq(String serviceTypeEq) {
	    this.serviceTypeEq=serviceTypeEq;
	}
	private Integer serviceStar;//
	public Integer getServiceStar() {
	    return this.serviceStar;
	}
	public void setServiceStar(Integer serviceStar) {
	    this.serviceStar=serviceStar;
	}
	private String serviceYear;//
	public String getServiceYear() {
	    return this.serviceYear;
	}
	public void setServiceYear(String serviceYear) {
	    this.serviceYear=serviceYear;
	}
	private String serviceYearEq;//
	public String getServiceYearEq() {
	    return this.serviceYearEq;
	}
	public void setServiceYearEq(String serviceYearEq) {
	    this.serviceYearEq=serviceYearEq;
	}
	private Integer photo;//
	public Integer getPhoto() {
	    return this.photo;
	}
	public void setPhoto(Integer photo) {
	    this.photo=photo;
	}
	private String serviceFeature;//
	public String getServiceFeature() {
	    return this.serviceFeature;
	}
	public void setServiceFeature(String serviceFeature) {
	    this.serviceFeature=serviceFeature;
	}
	private String serviceFeatureEq;//
	public String getServiceFeatureEq() {
	    return this.serviceFeatureEq;
	}
	public void setServiceFeatureEq(String serviceFeatureEq) {
	    this.serviceFeatureEq=serviceFeatureEq;
	}
	private Integer orderby;//
	public Integer getOrderby() {
	    return this.orderby;
	}
	public void setOrderby(Integer orderby) {
	    this.orderby=orderby;
	}
	private String state;//在岗：ON  离岗 ：OFF
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	private String stateEq;//在岗：ON  离岗 ：OFF
	public String getStateEq() {
	    return this.stateEq;
	}
	public void setStateEq(String stateEq) {
	    this.stateEq=stateEq;
	}
	private Integer createBy;//
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date createDt;//
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private String defaultb;//
	public String getDefaultb() {
	    return this.defaultb;
	}
	public void setDefaultb(String defaultb) {
	    this.defaultb=defaultb;
	}
	private String defaultbEq;//
	public String getDefaultbEq() {
	    return this.defaultbEq;
	}
	public void setDefaultbEq(String defaultbEq) {
	    this.defaultbEq=defaultbEq;
	}
	private String defaultc;//
	public String getDefaultc() {
	    return this.defaultc;
	}
	public void setDefaultc(String defaultc) {
	    this.defaultc=defaultc;
	}
	private String defaultcEq;//
	public String getDefaultcEq() {
	    return this.defaultcEq;
	}
	public void setDefaultcEq(String defaultcEq) {
	    this.defaultcEq=defaultcEq;
	}
	private Integer userId;//
	public Integer getUserId() {
	    return this.userId;
	}
	public void setUserId(Integer userId) {
	    this.userId=userId;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.customerId!=null)
			ht.put("customerId",this.customerId);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.customerName!=null)
			ht.put("customerName",this.customerName);
		if(this.customerNameEq!=null)
			ht.put("customerNameEq",this.customerNameEq);
		if(this.nickName!=null)
			ht.put("nickName",this.nickName);
		if(this.nickNameEq!=null)
			ht.put("nickNameEq",this.nickNameEq);
		if(this.sex!=null)
			ht.put("sex",this.sex);
		if(this.sexEq!=null)
			ht.put("sexEq",this.sexEq);
		if(this.serviceType!=null)
			ht.put("serviceType",this.serviceType);
		if(this.serviceTypeEq!=null)
			ht.put("serviceTypeEq",this.serviceTypeEq);
		if(this.serviceStar!=null)
			ht.put("serviceStar",this.serviceStar);
		if(this.serviceYear!=null)
			ht.put("serviceYear",this.serviceYear);
		if(this.serviceYearEq!=null)
			ht.put("serviceYearEq",this.serviceYearEq);
		if(this.photo!=null)
			ht.put("photo",this.photo);
		if(this.serviceFeature!=null)
			ht.put("serviceFeature",this.serviceFeature);
		if(this.serviceFeatureEq!=null)
			ht.put("serviceFeatureEq",this.serviceFeatureEq);
		if(this.orderby!=null)
			ht.put("orderby",this.orderby);
		if(this.state!=null)
			ht.put("state",this.state);
		if(this.stateEq!=null)
			ht.put("stateEq",this.stateEq);
		if(this.createBy!=null)
			ht.put("createBy",this.createBy);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.createDtFrom!=null)
			ht.put("createDtFrom",this.createDtFrom);
		if(this.createDtTo!=null)
			ht.put("createDtTo",this.createDtTo);
		if(this.modifyBy!=null)
			ht.put("modifyBy",this.modifyBy);
		if(this.modifyDt!=null)
			ht.put("modifyDt",this.modifyDt);
		if(this.modifyDtFrom!=null)
			ht.put("modifyDtFrom",this.modifyDtFrom);
		if(this.modifyDtTo!=null)
			ht.put("modifyDtTo",this.modifyDtTo);
		if(this.dr!=null)
			ht.put("dr",this.dr);
		if(this.defaultb!=null)
			ht.put("defaultb",this.defaultb);
		if(this.defaultbEq!=null)
			ht.put("defaultbEq",this.defaultbEq);
		if(this.defaultc!=null)
			ht.put("defaultc",this.defaultc);
		if(this.defaultcEq!=null)
			ht.put("defaultcEq",this.defaultcEq);
		if(this.userId!=null)
			ht.put("userId",this.userId);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 13:02:26
	//评分
	private Double score;

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	//客服电话号码，用于嘟嘟
	private String mobile;
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile=mobile;
	}
}

