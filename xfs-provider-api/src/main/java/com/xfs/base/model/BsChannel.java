package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;



/**
 * BsChannel
 * @author:wangchao
 * @date:2016/07/26 11:15:54	
 */
public class BsChannel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private String name;//渠道名称
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	private String nameEq;//渠道名称
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	private String code;//渠道代码
	public String getCode() {
	    return this.code;
	}
	public void setCode(String code) {
	    this.code=code;
	}
	private String codeEq;//渠道代码
	public String getCodeEq() {
	    return this.codeEq;
	}
	public void setCodeEq(String codeEq) {
	    this.codeEq=codeEq;
	}
	private String area;//授权区域
	public String getArea() {
	    return this.area;
	}
	public void setArea(String area) {
	    this.area=area;
	}
	private String areaEq;//授权区域
	public String getAreaEq() {
	    return this.areaEq;
	}
	public void setAreaEq(String areaEq) {
	    this.areaEq=areaEq;
	}
	private String contactPsn;//联系人
	public String getContactPsn() {
	    return this.contactPsn;
	}
	public void setContactPsn(String contactPsn) {
	    this.contactPsn=contactPsn;
	}
	private String contactPsnEq;//联系人
	public String getContactPsnEq() {
	    return this.contactPsnEq;
	}
	public void setContactPsnEq(String contactPsnEq) {
	    this.contactPsnEq=contactPsnEq;
	}
	private String mobile;//手机号
	public String getMobile() {
	    return this.mobile;
	}
	public void setMobile(String mobile) {
	    this.mobile=mobile;
	}
	private String mobileEq;//手机号
	public String getMobileEq() {
	    return this.mobileEq;
	}
	public void setMobileEq(String mobileEq) {
	    this.mobileEq=mobileEq;
	}
	private String email;//邮箱
	public String getEmail() {
	    return this.email;
	}
	public void setEmail(String email) {
	    this.email=email;
	}
	private String emailEq;//邮箱
	public String getEmailEq() {
	    return this.emailEq;
	}
	public void setEmailEq(String emailEq) {
	    this.emailEq=emailEq;
	}
	private String status;//0：未启用，1：启用
	public String getStatus() {
	    return this.status;
	}
	public void setStatus(String status) {
	    this.status=status;
	}
	private String statusEq;//0：未启用，1：启用
	public String getStatusEq() {
	    return this.statusEq;
	}
	public void setStatusEq(String statusEq) {
	    this.statusEq=statusEq;
	}
	private Date startDate;//启用日期
	public Date getStartDate() {
	    return this.startDate;
	}
	public void setStartDate(Date startDate) {
	    this.startDate=startDate;
	}
	private Date startDateFrom;//启用日期
	public Date getStartDateFrom() {
	    return this.startDateFrom;
	}
	public void setStartDateFrom(Date startDateFrom) {
	    this.startDateFrom=startDateFrom;
	}
	private Date startDateTo;//启用日期
	public Date getStartDateTo() {
	    return this.startDateTo;
	}
	public void setStartDateTo(Date startDateTo) {
	    this.startDateTo=startDateTo;
	}
	private Date endDate;//停用日期
	public Date getEndDate() {
	    return this.endDate;
	}
	public void setEndDate(Date endDate) {
	    this.endDate=endDate;
	}
	private Date endDateFrom;//停用日期
	public Date getEndDateFrom() {
	    return this.endDateFrom;
	}
	public void setEndDateFrom(Date endDateFrom) {
	    this.endDateFrom=endDateFrom;
	}
	private Date endDateTo;//停用日期
	public Date getEndDateTo() {
	    return this.endDateTo;
	}
	public void setEndDateTo(Date endDateTo) {
	    this.endDateTo=endDateTo;
	}
	private Integer createBy;//创建人
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date createDt;//创建时间
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//创建时间
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//创建时间
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//更新人
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//更新时间
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//更新时间
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//更新时间
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//逻辑删除标记位 0:未删除 1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.code!=null)
			ht.put("code",this.code);
		if(this.codeEq!=null)
			ht.put("codeEq",this.codeEq);
		if(this.area!=null)
			ht.put("area",this.area);
		if(this.areaEq!=null)
			ht.put("areaEq",this.areaEq);
		if(this.contactPsn!=null)
			ht.put("contactPsn",this.contactPsn);
		if(this.contactPsnEq!=null)
			ht.put("contactPsnEq",this.contactPsnEq);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.mobileEq!=null)
			ht.put("mobileEq",this.mobileEq);
		if(this.email!=null)
			ht.put("email",this.email);
		if(this.emailEq!=null)
			ht.put("emailEq",this.emailEq);
		if(this.status!=null)
			ht.put("status",this.status);
		if(this.statusEq!=null)
			ht.put("statusEq",this.statusEq);
		if(this.startDate!=null)
			ht.put("startDate",this.startDate);
		if(this.startDateFrom!=null)
			ht.put("startDateFrom",this.startDateFrom);
		if(this.startDateTo!=null)
			ht.put("startDateTo",this.startDateTo);
		if(this.endDate!=null)
			ht.put("endDate",this.endDate);
		if(this.endDateFrom!=null)
			ht.put("endDateFrom",this.endDateFrom);
		if(this.endDateTo!=null)
			ht.put("endDateTo",this.endDateTo);
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
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:55
	
	private Integer startCount;// 在职总数
	private Integer stopCount;// 离职总数
	private Integer registerCount;// 注册总数
	private Integer certificationCount;// 认证总数
	private Integer questionnaireCount;// 问卷总数
	public Integer getStartCount() {
		return startCount;
	}
	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}
	public Integer getStopCount() {
		return stopCount;
	}
	public void setStopCount(Integer stopCount) {
		this.stopCount = stopCount;
	}
	public Integer getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}
	public Integer getCertificationCount() {
		return certificationCount;
	}
	public void setCertificationCount(Integer certificationCount) {
		this.certificationCount = certificationCount;
	}
	public Integer getQuestionnaireCount() {
		return questionnaireCount;
	}
	public void setQuestionnaireCount(Integer questionnaireCount) {
		this.questionnaireCount = questionnaireCount;
	}
	// 隐藏状态，用于判断状态是否改变
	public String hiddenStatus;
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	// 查询使用
	public String searchName;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	// 企业注册数量
	public Integer orgNum;
	public Integer getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(Integer orgNum) {
		this.orgNum = orgNum;
	}
}

