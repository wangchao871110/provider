package com.xfs.cp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * CpService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:39	
 */
public class CpService extends CpServiceHistory {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//a
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer saasSpId;//主表服务商ID
	public Integer getSaasSpId() {
		return saasSpId;
	}
	public void setSaasSpId(Integer saasSpId) {
		this.saasSpId = saasSpId;
	}
	private String name;//服务商名称
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	private String nameEq;//服务商名称
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	private String cpAddress;//公司详细地址
	public String getCpAddress() {
	    return this.cpAddress;
	}
	public void setCpAddress(String cpAddress) {
	    this.cpAddress=cpAddress;
	}
	private String cpAddressEq;//公司详细地址
	public String getCpAddressEq() {
	    return this.cpAddressEq;
	}
	public void setCpAddressEq(String cpAddressEq) {
	    this.cpAddressEq=cpAddressEq;
	}
	private String shortName;//服务商简称
	public String getShortName() {
	    return this.shortName;
	}
	public void setShortName(String shortName) {
	    this.shortName=shortName;
	}
	private String shortNameEq;//服务商简称
	public String getShortNameEq() {
	    return this.shortNameEq;
	}
	public void setShortNameEq(String shortNameEq) {
	    this.shortNameEq=shortNameEq;
	}
	private Integer empNum;//员工数量
	public Integer getEmpNum() {
	    return this.empNum;
	}
	public void setEmpNum(Integer empNum) {
	    this.empNum=empNum;
	}
	private Integer serviceNum;//服务人数
	public Integer getServiceNum() {
	    return this.serviceNum;
	}
	public void setServiceNum(Integer serviceNum) {
	    this.serviceNum=serviceNum;
	}
	private String telephone;//座机号
	public String getTelephone() {
	    return this.telephone;
	}
	public void setTelephone(String telephone) {
	    this.telephone=telephone;
	}
	private String telephoneEq;//座机号
	public String getTelephoneEq() {
	    return this.telephoneEq;
	}
	public void setTelephoneEq(String telephoneEq) {
	    this.telephoneEq=telephoneEq;
	}
	private String mobile;//联系人手机号
	public String getMobile() {
	    return this.mobile;
	}
	public void setMobile(String mobile) {
	    this.mobile=mobile;
	}
	private String mobileEq;//联系人手机号
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
	private String fax;//公司传真
	public String getFax() {
	    return this.fax;
	}
	public void setFax(String fax) {
	    this.fax=fax;
	}
	private String faxEq;//公司传真
	public String getFaxEq() {
	    return this.faxEq;
	}
	public void setFaxEq(String faxEq) {
	    this.faxEq=faxEq;
	}
	private Integer isca;//企业是否认证 0：未认证 1：认证            
	public Integer getIsca() {
	    return this.isca;
	}
	public void setIsca(Integer isca) {
	    this.isca=isca;
	}
	private Integer isdeposit;//企业是否交保证金 0：未交 1：已交
	public Integer getIsdeposit() {
	    return this.isdeposit;
	}
	public void setIsdeposit(Integer isdeposit) {
	    this.isdeposit=isdeposit;
	}
	private Integer allRanking;//全国排名
	public Integer getAllRanking() {
	    return this.allRanking;
	}
	public void setAllRanking(Integer allRanking) {
	    this.allRanking=allRanking;
	}
	private Integer cityRanking;//市排名            
	public Integer getCityRanking() {
	    return this.cityRanking;
	}
	public void setCityRanking(Integer cityRanking) {
	    this.cityRanking=cityRanking;
	}
	private Double devIndex;//发展指数            
	public Double getDevIndex() {
	    return this.devIndex;
	}
	public void setDevIndex(Double devIndex) {
	    this.devIndex=devIndex;
	}
	private Double devIndexOld;//上一次发展指数 
	public Double getDevIndexOld() {
		return devIndexOld;
	}
	public void setDevIndexOld(Double devIndexOld) {
		this.devIndexOld = devIndexOld;
	}
	private Integer browseNum;//浏览次数
	public Integer getBrowseNum() {
	    return this.browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
	    this.browseNum=browseNum;
	}
	private Integer attentionNum;//关注人数
	public Integer getAttentionNum() {
	    return this.attentionNum;
	}
	public void setAttentionNum(Integer attentionNum) {
	    this.attentionNum=attentionNum;
	}
	private Integer industryNum;//同业认证数
	public Integer getIndustryNum() {
	    return this.industryNum;
	}
	public void setIndustryNum(Integer industryNum) {
	    this.industryNum=industryNum;
	}
	private Integer serviceLogo;//服务商logo            
	public Integer getServiceLogo() {
	    return this.serviceLogo;
	}
	public void setServiceLogo(Integer serviceLogo) {
	    this.serviceLogo=serviceLogo;
	}
	private String description;//服务机构介绍
	public String getDescription() {
	    return this.description;
	}
	public void setDescription(String description) {
	    this.description=description;
	}
	private String descriptionEq;//服务机构介绍
	public String getDescriptionEq() {
	    return this.descriptionEq;
	}
	public void setDescriptionEq(String descriptionEq) {
	    this.descriptionEq=descriptionEq;
	}
	private String serviceAreaNum;//服务范围个数
	public String getServiceAreaNum() {
		return serviceAreaNum;
	}
	public void setServiceAreaNum(String serviceAreaNum) {
		this.serviceAreaNum = serviceAreaNum;
	}
	private String serviceAreaName;//服务范围名称
	public String getServiceAreaName() {
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName) {
		this.serviceAreaName = serviceAreaName;
	}
	private String serviceArea;//服务范围
	public String getServiceArea() {
	    return this.serviceArea;
	}
	public void setServiceArea(String serviceArea) {
	    this.serviceArea=serviceArea;
	}
	private String serviceAreaEq;//服务范围
	public String getServiceAreaEq() {
	    return this.serviceAreaEq;
	}
	public void setServiceAreaEq(String serviceAreaEq) {
	    this.serviceAreaEq=serviceAreaEq;
	}
	private String serviceAreaNameEq;//服务范围名称
	public String getServiceAreaNameEq() {
	    return this.serviceAreaNameEq;
	}
	public void setServiceAreaNameEq(String serviceAreaNameEq) {
	    this.serviceAreaNameEq=serviceAreaNameEq;
	}
	private String advantage;//服务优势            
	public String getAdvantage() {
	    return this.advantage;
	}
	public void setAdvantage(String advantage) {
	    this.advantage=advantage;
	}
	private String advantageEq;//服务优势            
	public String getAdvantageEq() {
	    return this.advantageEq;
	}
	public void setAdvantageEq(String advantageEq) {
	    this.advantageEq=advantageEq;
	}
	private String customerType;//服务客户类型
	public String getCustomerType() {
	    return this.customerType;
	}
	public void setCustomerType(String customerType) {
	    this.customerType=customerType;
	}
	private String customerTypeEq;//服务客户类型
	public String getCustomerTypeEq() {
	    return this.customerTypeEq;
	}
	public void setCustomerTypeEq(String customerTypeEq) {
	    this.customerTypeEq=customerTypeEq;
	}
	private String certificate;//证书ID            
	public String getCertificate() {
	    return this.certificate;
	}
	public void setCertificate(String certificate) {
	    this.certificate=certificate;
	}
	private String certificateEq;//证书ID            
	public String getCertificateEq() {
	    return this.certificateEq;
	}
	public void setCertificateEq(String certificateEq) {
	    this.certificateEq=certificateEq;
	}
	private Integer dispatchCa;//劳务派遣证书
	public Integer getDispatchCa() {
		return this.dispatchCa;
	}
	public void setDispatchCa(Integer dispatchCa) {
		this.dispatchCa=dispatchCa;
	}
	
	private Integer hrCa;//人力资源证书
	public Integer getHrCa() {
		return this.hrCa;
	}
	public void setHrCa(Integer hrCa) {
		this.hrCa=hrCa;
	}
	private String ceoName;//总经理名称
	public String getCeoName() {
	    return this.ceoName;
	}
	public void setCeoName(String ceoName) {
	    this.ceoName=ceoName;
	}
	private String ceoNameEq;//总经理名称
	public String getCeoNameEq() {
	    return this.ceoNameEq;
	}
	public void setCeoNameEq(String ceoNameEq) {
	    this.ceoNameEq=ceoNameEq;
	}
	private Integer ceoLogo;//头像            
	public Integer getCeoLogo() {
	    return this.ceoLogo;
	}
	public void setCeoLogo(Integer ceoLogo) {
	    this.ceoLogo=ceoLogo;
	}
	private String ceoDescription;//简介
	public String getCeoDescription() {
	    return this.ceoDescription;
	}
	public void setCeoDescription(String ceoDescription) {
	    this.ceoDescription=ceoDescription;
	}
	private String ceoDescriptionEq;//简介
	public String getCeoDescriptionEq() {
	    return this.ceoDescriptionEq;
	}
	public void setCeoDescriptionEq(String ceoDescriptionEq) {
	    this.ceoDescriptionEq=ceoDescriptionEq;
	}
	private String officeImg;//办公环境照片
	public String getOfficeImg() {
	    return this.officeImg;
	}
	public void setOfficeImg(String officeImg) {
	    this.officeImg=officeImg;
	}
	private String officeImgEq;//办公环境照片
	public String getOfficeImgEq() {
	    return this.officeImgEq;
	}
	public void setOfficeImgEq(String officeImgEq) {
	    this.officeImgEq=officeImgEq;
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
	private Integer dr;//逻辑删除标记位0:未删除1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private String registeredGov;//登记机关
	public String getRegisteredGov() {
	    return this.registeredGov;
	}
	public void setRegisteredGov(String registeredGov) {
	    this.registeredGov=registeredGov;
	}
	private String registeredGovEq;//登记机关
	public String getRegisteredGovEq() {
	    return this.registeredGovEq;
	}
	public void setRegisteredGovEq(String registeredGovEq) {
	    this.registeredGovEq=registeredGovEq;
	}
	private Date establishedTime;//成立时间
	public Date getEstablishedTime() {
	    return this.establishedTime;
	}
	public void setEstablishedTime(Date establishedTime) {
	    this.establishedTime=establishedTime;
	}
	private Date establishedTimeFrom;//成立时间
	public Date getEstablishedTimeFrom() {
	    return this.establishedTimeFrom;
	}
	public void setEstablishedTimeFrom(Date establishedTimeFrom) {
	    this.establishedTimeFrom=establishedTimeFrom;
	}
	private Date establishedTimeTo;//成立时间
	public Date getEstablishedTimeTo() {
	    return this.establishedTimeTo;
	}
	public void setEstablishedTimeTo(Date establishedTimeTo) {
	    this.establishedTimeTo=establishedTimeTo;
	}
	private String registeredCapital;//注册资本
	public String getRegisteredCapital() {
	    return this.registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
	    this.registeredCapital=registeredCapital;
	}
	private String registeredCapitalEq;//注册资本
	public String getRegisteredCapitalEq() {
	    return this.registeredCapitalEq;
	}
	public void setRegisteredCapitalEq(String registeredCapitalEq) {
	    this.registeredCapitalEq=registeredCapitalEq;
	}
	private String busiLicenseNum;//统一社会信用代码
	public String getBusiLicenseNum() {
	    return this.busiLicenseNum;
	}
	public void setBusiLicenseNum(String busiLicenseNum) {
	    this.busiLicenseNum=busiLicenseNum;
	}
	private String busiLicenseNumEq;//统一社会信用代码
	public String getBusiLicenseNumEq() {
	    return this.busiLicenseNumEq;
	}
	public void setBusiLicenseNumEq(String busiLicenseNumEq) {
	    this.busiLicenseNumEq=busiLicenseNumEq;
	}
	private String orgCode;//组织机构代码
	public String getOrgCode() {
	    return this.orgCode;
	}
	public void setOrgCode(String orgCode) {
	    this.orgCode=orgCode;
	}
	private String orgCodeEq;//组织机构代码
	public String getOrgCodeEq() {
	    return this.orgCodeEq;
	}
	public void setOrgCodeEq(String orgCodeEq) {
	    this.orgCodeEq=orgCodeEq;
	}
	private String comanyType;//公司类型
	public String getComanyType() {
	    return this.comanyType;
	}
	public void setComanyType(String comanyType) {
	    this.comanyType=comanyType;
	}
	private String comanyTypeEq;//公司类型
	public String getComanyTypeEq() {
	    return this.comanyTypeEq;
	}
	public void setComanyTypeEq(String comanyTypeEq) {
	    this.comanyTypeEq=comanyTypeEq;
	}
	private String legalPsn;//法定代表人
	public String getLegalPsn() {
	    return this.legalPsn;
	}
	public void setLegalPsn(String legalPsn) {
	    this.legalPsn=legalPsn;
	}
	private String legalPsnEq;//法定代表人
	public String getLegalPsnEq() {
	    return this.legalPsnEq;
	}
	public void setLegalPsnEq(String legalPsnEq) {
	    this.legalPsnEq=legalPsnEq;
	}
	private String busiScope;//经营范围
	public String getBusiScope() {
	    return this.busiScope;
	}
	public void setBusiScope(String busiScope) {
	    this.busiScope=busiScope;
	}
	private String busiScopeEq;//经营范围
	public String getBusiScopeEq() {
	    return this.busiScopeEq;
	}
	public void setBusiScopeEq(String busiScopeEq) {
	    this.busiScopeEq=busiScopeEq;
	}
	private String busiStasus;//经营状态
	public String getBusiStasus() {
	    return this.busiStasus;
	}
	public void setBusiStasus(String busiStasus) {
	    this.busiStasus=busiStasus;
	}
	private String weburl;//公司网址
	public String getWeburl() {
	    return this.weburl;
	}
	public void setWeburl(String weburl) {
	    this.weburl=weburl;
	}
	private String weburlEq;//公司网址
	public String getWeburlEq() {
	    return this.weburlEq;
	}
	public void setWeburlEq(String weburlEq) {
	    this.weburlEq=weburlEq;
	}
	private String customerNum;//法大大客户编号
	public String getCustomerNum() {
	    return this.customerNum;
	}
	public void setCustomerNum(String customerNum) {
	    this.customerNum=customerNum;
	}
	private String customerNumEq;//法大大客户编号
	public String getCustomerNumEq() {
	    return this.customerNumEq;
	}
	public void setCustomerNumEq(String customerNumEq) {
	    this.customerNumEq=customerNumEq;
	}
	
	private String partners;//股东结构
	private String addressesRegister;//注册地址
	private String startDate;//成立日期
	private String checkDate;//发照日期
	private String addressDetail;//公司详细地址
	
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getPartners() {
		return partners;
	}
	public void setPartners(String partners) {
		this.partners = partners;
	}
	private String partnersEq;//股东结构
	public String getPartnersEq() {
	    return this.partnersEq;
	}
	public void setPartnersEq(String partnersEq) {
	    this.partnersEq=partnersEq;
	}
	public String getAddressesRegister() {
		return addressesRegister;
	}
	public void setAddressesRegister(String addressesRegister) {
		this.addressesRegister = addressesRegister;
	}
	
	private String addressesRegisterEq;//注册地址
	public String getAddressesRegisterEq() {
	    return this.addressesRegisterEq;
	}
	public void setAddressesRegisterEq(String addressesRegisterEq) {
	    this.addressesRegisterEq=addressesRegisterEq;
	}
	private String startDateEq;//成立日期
	public String getStartDateEq() {
	    return this.startDateEq;
	}
	public void setStartDateEq(String startDateEq) {
	    this.startDateEq=startDateEq;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	private Integer isfirst;//是否第一次进入个人中心（新手引导）0：还没有进入过个人中心，1：已结进入过个人中心
	public Integer getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(Integer isfirst) {
		this.isfirst = isfirst;
	}
	public void fixup(){
	}
	
	private Integer orgStar;//服务星级
	private String companyLevel;//合作级别
	
	public Integer getOrgStar() {
		return orgStar;
	}
	public void setOrgStar(Integer orgStar) {
		this.orgStar = orgStar;
	}
	public String getCompanyLevel() {
		return companyLevel;
	}
	public void setCompanyLevel(String companyLevel) {
		this.companyLevel = companyLevel;
	}

	private String serviceState;
	private String enabledMall;
	
	
	public String getServiceState() {
		return serviceState;
	}
	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}
	public String getEnabledMall() {
		return enabledMall;
	}
	public void setEnabledMall(String enabledMall) {
		this.enabledMall = enabledMall;
	}
	

	private String isOnsiteAudit;//是否现场审核
	private String isFundSupervision;//是否资金监管
	private BigDecimal riskMargin;//风险保证金
	private Integer riskMarginStar;//保证金星级
	private String isIso;//是否具有ISO900
	private Integer overallEvaluation;//平台综合评价（分）
	
	private Integer isuse;//是否可用0：不可用，1：可用
	private String stopContent;//停用原因
	
	public Integer getIsuse() {
		return isuse;
	}
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	public String getStopContent() {
		return stopContent;
	}
	public void setStopContent(String stopContent) {
		this.stopContent = stopContent;
	}
	public String getIsOnsiteAudit() {
		return isOnsiteAudit;
	}
	public void setIsOnsiteAudit(String isOnsiteAudit) {
		this.isOnsiteAudit = isOnsiteAudit;
	}
	public String getIsFundSupervision() {
		return isFundSupervision;
	}
	public void setIsFundSupervision(String isFundSupervision) {
		this.isFundSupervision = isFundSupervision;
	}
	public BigDecimal getRiskMargin() {
		return riskMargin;
	}
	public void setRiskMargin(BigDecimal riskMargin) {
		this.riskMargin = riskMargin;
	}
	public Integer getRiskMarginStar() {
		return riskMarginStar;
	}
	public void setRiskMarginStar(Integer riskMarginStar) {
		this.riskMarginStar = riskMarginStar;
	}
	public String getIsIso() {
		return isIso;
	}
	public void setIsIso(String isIso) {
		this.isIso = isIso;
	}
	public Integer getOverallEvaluation() {
		return overallEvaluation;
	}
	public void setOverallEvaluation(Integer overallEvaluation) {
		this.overallEvaluation = overallEvaluation;
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.saasSpId!=null)
			ht.put("spId",this.saasSpId);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.cpAddress!=null)
			ht.put("cpAddress",this.cpAddress);
		if(this.cpAddressEq!=null)
			ht.put("cpAddressEq",this.cpAddressEq);
		if(this.shortName!=null)
			ht.put("shortName",this.shortName);
		if(this.shortNameEq!=null)
			ht.put("shortNameEq",this.shortNameEq);
		if(this.empNum!=null)
			ht.put("empNum",this.empNum);
		if(this.serviceNum!=null)
			ht.put("serviceNum",this.serviceNum);
		if(this.telephone!=null)
			ht.put("telephone",this.telephone);
		if(this.telephoneEq!=null)
			ht.put("telephoneEq",this.telephoneEq);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.mobileEq!=null)
			ht.put("mobileEq",this.mobileEq);
		if(this.email!=null)
			ht.put("email",this.email);
		if(this.emailEq!=null)
			ht.put("emailEq",this.emailEq);
		if(this.fax!=null)
			ht.put("fax",this.fax);
		if(this.faxEq!=null)
			ht.put("faxEq",this.faxEq);
		if(this.isca!=null)
			ht.put("isca",this.isca);
		if(this.isdeposit!=null)
			ht.put("isdeposit",this.isdeposit);
		if(this.allRanking!=null)
			ht.put("allRanking",this.allRanking);
		if(this.cityRanking!=null)
			ht.put("cityRanking",this.cityRanking);
		if(this.devIndex!=null)
			ht.put("devIndex",this.devIndex);
		if(this.browseNum!=null)
			ht.put("browseNum",this.browseNum);
		if(this.attentionNum!=null)
			ht.put("attentionNum",this.attentionNum);
		if(this.industryNum!=null)
			ht.put("industryNum",this.industryNum);
		if(this.serviceLogo!=null)
			ht.put("serviceLogo",this.serviceLogo);
		if(this.description!=null)
			ht.put("description",this.description);
		if(this.descriptionEq!=null)
			ht.put("descriptionEq",this.descriptionEq);
		if(this.serviceArea!=null)
			ht.put("serviceArea",this.serviceArea);
		if(this.serviceAreaEq!=null)
			ht.put("serviceAreaEq",this.serviceAreaEq);
		if(this.serviceAreaName!=null)
			ht.put("serviceAreaName",this.serviceAreaName);
		if(this.serviceAreaNameEq!=null)
			ht.put("serviceAreaNameEq",this.serviceAreaNameEq);
		if(this.advantage!=null)
			ht.put("advantage",this.advantage);
		if(this.advantageEq!=null)
			ht.put("advantageEq",this.advantageEq);
		if(this.customerType!=null)
			ht.put("customerType",this.customerType);
		if(this.customerTypeEq!=null)
			ht.put("customerTypeEq",this.customerTypeEq);
		if(this.certificate!=null)
			ht.put("certificate",this.certificate);
		if(this.certificateEq!=null)
			ht.put("certificateEq",this.certificateEq);
		if(this.ceoName!=null)
			ht.put("ceoName",this.ceoName);
		if(this.ceoNameEq!=null)
			ht.put("ceoNameEq",this.ceoNameEq);
		if(this.ceoLogo!=null)
			ht.put("ceoLogo",this.ceoLogo);
		if(this.ceoDescription!=null)
			ht.put("ceoDescription",this.ceoDescription);
		if(this.ceoDescriptionEq!=null)
			ht.put("ceoDescriptionEq",this.ceoDescriptionEq);
		if(this.officeImg!=null)
			ht.put("officeImg",this.officeImg);
		if(this.officeImgEq!=null)
			ht.put("officeImgEq",this.officeImgEq);
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
		if(this.registeredGov!=null)
			ht.put("registeredGov",this.registeredGov);
		if(this.registeredGovEq!=null)
			ht.put("registeredGovEq",this.registeredGovEq);
		if(this.establishedTime!=null)
			ht.put("establishedTime",this.establishedTime);
		if(this.establishedTimeFrom!=null)
			ht.put("establishedTimeFrom",this.establishedTimeFrom);
		if(this.establishedTimeTo!=null)
			ht.put("establishedTimeTo",this.establishedTimeTo);
		if(this.registeredCapital!=null)
			ht.put("registeredCapital",this.registeredCapital);
		if(this.registeredCapitalEq!=null)
			ht.put("registeredCapitalEq",this.registeredCapitalEq);
		if(this.busiLicenseNum!=null)
			ht.put("busiLicenseNum",this.busiLicenseNum);
		if(this.busiLicenseNumEq!=null)
			ht.put("busiLicenseNumEq",this.busiLicenseNumEq);
		if(this.orgCode!=null)
			ht.put("orgCode",this.orgCode);
		if(this.orgCodeEq!=null)
			ht.put("orgCodeEq",this.orgCodeEq);
		if(this.comanyType!=null)
			ht.put("comanyType",this.comanyType);
		if(this.comanyTypeEq!=null)
			ht.put("comanyTypeEq",this.comanyTypeEq);
		if(this.legalPsn!=null)
			ht.put("legalPsn",this.legalPsn);
		if(this.legalPsnEq!=null)
			ht.put("legalPsnEq",this.legalPsnEq);
		if(this.busiScope!=null)
			ht.put("busiScope",this.busiScope);
		if(this.busiScopeEq!=null)
			ht.put("busiScopeEq",this.busiScopeEq);
		if(this.busiStasus!=null)
			ht.put("busiStasus",this.busiStasus);
		if(this.weburl!=null)
			ht.put("weburl",this.weburl);
		if(this.weburlEq!=null)
			ht.put("weburlEq",this.weburlEq);
		if(this.customerNum!=null)
			ht.put("customerNum",this.customerNum);
		if(this.customerNumEq!=null)
			ht.put("customerNumEq",this.customerNumEq);
		if(this.staDate!=null)
			ht.put("staDate",this.staDate);
		if(this.endDate!=null)
			ht.put("endDate",this.endDate);
		if(this.price!=null)
			ht.put("price",this.price);
		if(this.categoryId!=null)
			ht.put("categoryId",this.categoryId);
		if(this.orderBy!=null)
			ht.put("orderBy",this.orderBy);
		if(this.dispatchCa!=null)
			ht.put("dispatchCa",this.dispatchCa);
		if(this.hrCa!=null)
			ht.put("hrCa",this.hrCa);
		if(this.busiLicenseFile!=null)
			ht.put("busiLicenseFile",this.busiLicenseFile);
		if(this.startTime!=null)
			ht.put("startTime",this.startTime);
		if(this.endTime!=null)
			ht.put("endTime",this.endTime);
		if(this.serviceState!=null)
			ht.put("serviceState",this.serviceState);
		if(this.enabledMall!=null)
			ht.put("enabledMall",this.enabledMall);
		if(this.isOnsiteAudit!=null)
			ht.put("isOnsiteAudit",this.isOnsiteAudit);
		if(this.isFundSupervision!=null)
			ht.put("isFundSupervision",this.isFundSupervision);
		if(this.riskMargin!=null)
			ht.put("riskMargin",this.riskMargin);
		if(this.riskMarginStar!=null)
			ht.put("riskMarginStar",this.riskMarginStar);
		if(this.isIso!=null)
			ht.put("isIso",this.isIso);
		if(this.overallEvaluation!=null)
			ht.put("overallEvaluation",this.overallEvaluation);
		if(this.isrob!=null)
			ht.put("isrob",this.isrob);
		if(this.customIndex!=null)
			ht.put("customIndex",this.customIndex);
		if(this.years!=null)
			ht.put("years",this.years);
		if(this.month!=null)
			ht.put("month",this.month);
		return ht;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:40
	private String staDate;// 查询开始日期
	private String endDate;// 查询结束日期
	public String getStaDate() {
		return staDate;
	}
	public void setStaDate(String staDate) {
		this.staDate = staDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double price;// 交易总额
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String categoryId;// 主营业务
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String orderBy;// 排序字段
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer busiLicenseFile;// 排序字段
	public Integer getBusiLicenseFile() {
		return busiLicenseFile;
	}
	public void setBusiLicenseFile(Integer busiLicenseFile) {
		this.busiLicenseFile = busiLicenseFile;
	}
	private String checkDateEq;//发照日期
	public String getCheckDateEq() {
	    return this.checkDateEq;
	}
	public void setCheckDateEq(String checkDateEq) {
	    this.checkDateEq=checkDateEq;
	}
	private String addressDetailEq;//单位详细联系地址
	public String getAddressDetailEq() {
	    return this.addressDetailEq;
	}
	public void setAddressDetailEq(String addressDetailEq) {
	    this.addressDetailEq=addressDetailEq;
	}
	
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private String categoryPinyin;
	public String getCategoryPinyin() {
		return categoryPinyin;
	}
	public void setCategoryPinyin(String categoryPinyin) {
		this.categoryPinyin = categoryPinyin;
	}
	private Integer orderNumber;//订单数量
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	private String areaPinyin;
	public String getAreaPinyin() {
		return areaPinyin;
	}
	public void setAreaPinyin(String areaPinyin) {
		this.areaPinyin = areaPinyin;
	}


	private Integer isrob; //是否可抢单

	public Integer getIsrob() {
		return isrob;
	}

	public void setIsrob(Integer isrob) {
		this.isrob = isrob;
	}

	public Double getCustomIndex() {
		return customIndex;
	}

	public void setCustomIndex(Double customIndex) {
		this.customIndex = customIndex;
	}

	private Double customIndex;  //自定义指数

	private Integer years;//年份
	public Integer getYears() {
		return this.years;
	}
	public void setYears(Integer years) {
		this.years=years;
	}
	private Integer month;//月份
	public Integer getMonth() {
		return this.month;
	}
	public void setMonth(Integer month) {
		this.month=month;
	}
	
	// 业务介绍
	private CpBusinessIntroduction cpBusinessIntroduction;
	public CpBusinessIntroduction getCpBusinessIntroduction() {
		return cpBusinessIntroduction;
	}
	public void setCpBusinessIntroduction(CpBusinessIntroduction cpBusinessIntroduction) {
		this.cpBusinessIntroduction = cpBusinessIntroduction;
	}
	
	// 合作满意度和用户点评
	private CpEvaluation cpEvaluation;
	public CpEvaluation getCpEvaluation() {
		return cpEvaluation;
	}
	public void setCpEvaluation(CpEvaluation cpEvaluation) {
		this.cpEvaluation = cpEvaluation;
	}
	
}

