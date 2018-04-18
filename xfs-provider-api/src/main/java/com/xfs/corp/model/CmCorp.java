package com.xfs.corp.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.xfs.base.model.BsArea;

/**
 * CmCorp
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/01/22 10:12:02
 * 
 * @modifyBy xiyanzhang
 * @date 2016年4月8日下午2:04:39
 */
public class CmCorp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cpId;// 主键
	private String corpName;// 企业名称
	private String status;// 状态
	private String contactPsn;// 联系人
	private String mobile;// 联系手机
	private String mail;// 邮件
	private Integer collaborator;// 客户经理
	private String remark;// 备注
	private Integer createBy;// 创建人
	private Date createDt;// 创建时间
	private Integer modifyBy;// 修改人
	private Date modifyDt;// 修改时间

	private Integer spsId;// 服务商表主键ID
	private Integer spId;// 服务商ID
	private Integer areaId;// 区域ID
	
	private Integer  insuranceCount;//社保在缴人数
	
	private Integer  fundCount; //公积金在缴人数
	
	private Integer ccount;
	
	private String condition;
	
	
	private String shortName;//公司简称
	private String shortNameEq;
	
	private String channelName;
	private String cCode;
	
	private String cpIfStop;
	private String tenantId;// 租户ID
	
	public String getCpIfStop() {
		return cpIfStop;
	}

	public void setCpIfStop(String cpIfStop) {
		this.cpIfStop = cpIfStop;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	/**
	 * 职位 A、HR助理   B、HR专员   C、HR主管   D、HR经理   E、HR总监  G，其他
	 */
	private String position;

	public String getShortNameEq() {
		return shortNameEq;
	}

	public void setShortNameEq(String shortNameEq) {
		this.shortNameEq = shortNameEq;
	}

	public Integer getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Integer collaborator) {
		this.collaborator = collaborator;
	}

	public Integer getCollaboratorEq() {
		return collaboratorEq;
	}

	public void setCollaboratorEq(Integer collaboratorEq) {
		this.collaboratorEq = collaboratorEq;
	}

	//父亲一级ID
	private Integer addressParentId;
	
	
	//公司所在地区的ID
	private Integer cpAddressAreaId;

    //公司所在地区的ID
    private Integer cpAddressArea;

    public Integer getCpAddressArea() {
        return cpAddressArea;
    }



    public void setCpAddressArea(Integer cpAddressArea) {
        this.cpAddressArea = cpAddressArea;
    }

    //第一个下拉框的值
	private List<BsArea> areaList;
	
	
	//第二个下拉框的值
	private List<BsArea>  childAreaList;

	private String corpNameEq;//yyyy-MM
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 缴纳类型 COMMON大库 SINGEL单立户
	 */
	private String insuranceType;
	/**
	 * 社保登记证号
	 */
	private String regNum;
	/**
	 * 缴交类型名称
	 */
	private String insuranceName;
	/**
	 * 社保登记证登陆口令
	 */
	private String regNumpass;
	/**
	 * 社保登记证证书密码
	 */
	private String regUsbkeypass;
	/**
	 * 企业邮政编码
	 */
	private String cpZipcode;
	private String cpZipcodeEq;
	
	private String telephone;//座机号码

	public String getCpZipcodeEq() {
		return cpZipcodeEq;
	}

	public void setCpZipcodeEq(String cpZipcodeEq) {
		this.cpZipcodeEq = cpZipcodeEq;
	}

	/**
	 * 企业详细地址
	 */
	private String cpAddress;
	private String cpAddressEq;

	public String getCpAddressEq() {
		return cpAddressEq;
	}

	public void setCpAddressEq(String cpAddressEq) {
		this.cpAddressEq = cpAddressEq;
	}

	/**
	 * 未完成状态：Y/N null
	 */
	private String unComplete;
	private String unCompleteEq;

	public String getUnCompleteEq() {
		return unCompleteEq;
	}

	public void setUnCompleteEq(String unCompleteEq) {
		this.unCompleteEq = unCompleteEq;
	}

	/**
	 * 自注册标识 MALL/SAAS
	 */
	private String source;
	private String sourceEq;

	public String getSourceEq() {
		return sourceEq;
	}

	public void setSourceEq(String sourceEq) {
		this.sourceEq = sourceEq;
	}

	/**
	 * 是否开通商城功能 Y/N null
	 */
	private String openmall;

	private Integer statusEq;

	private String contactPsnEq;

	private String mobileEq;

	private String mailEq;

	private Integer collaboratorEq;

	private String remarkEq;

	private String createDtFrom;
	
	private String createDtTo;

	private Date modifyDtFrom;

	private Date modifyDtTo;
	//saas员工数
	private Integer saasEmpCount;
	//福利员工数
	private Integer welfEmpCount;

	private String employeeSource;

	public Integer getSaasEmpCount() {
		return saasEmpCount;
	}

	public void setSaasEmpCount(Integer saasEmpCount) {
		this.saasEmpCount = saasEmpCount;
	}

	public Integer getWelfEmpCount() {
		return welfEmpCount;
	}

	public void setWelfEmpCount(Integer welfEmpCount) {
		this.welfEmpCount = welfEmpCount;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactPsn() {
		return contactPsn;
	}

	public void setContactPsn(String contactPsn) {
		this.contactPsn = contactPsn;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	 

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public Integer getSpsId() {
		return spsId;
	}

	public void setSpsId(Integer spsId) {
		this.spsId = spsId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getRegNumpass() {
		return regNumpass;
	}

	public void setRegNumpass(String regNumpass) {
		this.regNumpass = regNumpass;
	}

	public String getRegUsbkeypass() {
		return regUsbkeypass;
	}

	public void setRegUsbkeypass(String regUsbkeypass) {
		this.regUsbkeypass = regUsbkeypass;
	}

	public void fixup() {
	}

	public String getCorpNameEq() {
		return corpNameEq;
	}

	public void setCorpNameEq(String corpNameEq) {
		this.corpNameEq = corpNameEq;
	}

	public String getCpZipcode() {
		return cpZipcode;
	}

	public void setCpZipcode(String cpZipcode) {
		this.cpZipcode = cpZipcode;
	}

	public String getCpAddress() {
		return cpAddress;
	}

	public void setCpAddress(String cpAddress) {
		this.cpAddress = cpAddress;
	}

	public String getUnComplete() {
		return unComplete;
	}

	public void setUnComplete(String unComplete) {
		this.unComplete = unComplete;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.cpId != null)
			ht.put("cpId", this.cpId);
		if (this.corpName != null)
			ht.put("corpName", this.corpName);
		if (this.status != null)
			ht.put("status", this.status);
		if (this.contactPsn != null)
			ht.put("contactPsn", this.contactPsn);
		if (this.mobile != null)
			ht.put("mobile", this.mobile);
		if (this.mail != null)
			ht.put("mail", this.mail);
		if (this.collaborator != null)
			ht.put("collaborator", this.collaborator);
		if (this.remark != null)
			ht.put("remark", this.remark);
		if (this.createBy != null)
			ht.put("createBy", this.createBy);
		if (this.createDt != null)
			ht.put("createDt", this.createDt);
		if (this.modifyBy != null)
			ht.put("modifyBy", this.modifyBy);
		if (this.modifyDt != null)
			ht.put("modifyDt", this.modifyDt);
		if (this.spId != null)
			ht.put("spId", this.spId);
		if (this.shortName != null)
			ht.put("shortName", this.shortName);
		if (this.unComplete != null)
			ht.put("unComplete", this.unComplete);
		if(this.openmall!=null)
			ht.put("openmall",this.openmall);
		if(this.createDtFrom!=null)
			ht.put("createDtFrom",this.createDtFrom);
		if(this.createDtTo!=null)
			ht.put("createDtTo",this.createDtTo);
		
		if(this.source!=null)
			ht.put("source", this.source);
		
		if(this.sourceEq!=null)
			ht.put("sourceEq",this.sourceEq);
		if(this.position!=null)
			ht.put("position",this.position);
		if(this.busiLicenseNum!=null)
			ht.put("busiLicenseNum",this.busiLicenseNum);
		if(this.busiLicenseNumEq!=null)
			ht.put("busiLicenseNumEq",this.busiLicenseNumEq);
		if(this.busiLicenseFile!=null)
			ht.put("busiLicenseFile",this.busiLicenseFile);
		if(this.corporateFile!=null)
			ht.put("corporateFile",this.corporateFile);
		if(this.deputyFile!=null)
			ht.put("deputyFile",this.deputyFile);
		if(this.otherFile!=null)
			ht.put("otherFile",this.otherFile);
		if(this.otherFileEq!=null)
			ht.put("otherFileEq",this.otherFileEq);
		if(this.isLegalize!=null)
			ht.put("isLegalize",this.isLegalize);
		if(this.customerNo!=null)
			ht.put("customerNo",this.customerNo);
		if(this.customerNoEq!=null)
			ht.put("customerNoEq",this.customerNoEq);
		if(this.channelCode!=null)
			ht.put("channelCode",this.channelCode);
		if(this.channelCodeEq!=null)
			ht.put("channelCodeEq",this.channelCodeEq);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.dr!=null)
			ht.put("dr",this.dr);
		if(this.authority!=null)
			ht.put("authority",this.authority);
		if(this.channelName!=null)
			ht.put("channelName",this.channelName);
		if(this.cCode!=null)
			ht.put("cCode",this.cCode);
		if(this.tenantId!=null)
			ht.put("tenantId",this.tenantId);
		
		return ht;
	}

	public Integer getInsuranceCount() {
		return insuranceCount;
	}

	public void setInsuranceCount(Integer insuranceCount) {
		this.insuranceCount = insuranceCount;
	}

	public Integer getFundCount() {
		return fundCount;
	}

	public void setFundCount(Integer fundCount) {
		this.fundCount = fundCount;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getCpAddressAreaId() {
		return cpAddressAreaId;
	}

	public void setCpAddressAreaId(Integer cpAddressAreaId) {
		this.cpAddressAreaId = cpAddressAreaId;
	}

	public List<BsArea> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<BsArea> areaList) {
		this.areaList = areaList;
	}

	public Integer getAddressParentId() {
		return addressParentId;
	}

	public void setAddressParentId(Integer addressParentId) {
		this.addressParentId = addressParentId;
	}

	public List<BsArea> getChildAreaList() {
		return childAreaList;
	}

	public void setChildAreaList(List<BsArea> childAreaList) {
		this.childAreaList = childAreaList;
	}

	public Integer getStatusEq() {
		return statusEq;
	}

	public void setStatusEq(Integer statusEq) {
		this.statusEq = statusEq;
	}

	public String getContactPsnEq() {
		return contactPsnEq;
	}

	public void setContactPsnEq(String contactPsnEq) {
		this.contactPsnEq = contactPsnEq;
	}

	public String getMobileEq() {
		return mobileEq;
	}

	public void setMobileEq(String mobileEq) {
		this.mobileEq = mobileEq;
	}

	public String getMailEq() {
		return mailEq;
	}

	public void setMailEq(String mailEq) {
		this.mailEq = mailEq;
	}

	 
	public String getRemarkEq() {
		return remarkEq;
	}

	public void setRemarkEq(String remarkEq) {
		this.remarkEq = remarkEq;
	}

	public Date getModifyDtFrom() {
		return modifyDtFrom;
	}

	public void setModifyDtFrom(Date modifyDtFrom) {
		this.modifyDtFrom = modifyDtFrom;
	}

	public Date getModifyDtTo() {
		return modifyDtTo;
	}

	public void setModifyDtTo(Date modifyDtTo) {
		this.modifyDtTo = modifyDtTo;
	}


	public Integer getCcount() {
		return ccount;
	}

	public void setCcount(Integer ccount) {
		this.ccount = ccount;
	}


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOpenmall() {
		return openmall;
	}

	public void setOpenmall(String openmall) {
		this.openmall = openmall;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private String busiLicenseNum;//
	public String getBusiLicenseNum() {
		return this.busiLicenseNum;
	}
	public void setBusiLicenseNum(String busiLicenseNum) {
		this.busiLicenseNum=busiLicenseNum;
	}
	private String busiLicenseNumEq;//
	public String getBusiLicenseNumEq() {
		return this.busiLicenseNumEq;
	}
	public void setBusiLicenseNumEq(String busiLicenseNumEq) {
		this.busiLicenseNumEq=busiLicenseNumEq;
	}
	private Integer busiLicenseFile;//
	public Integer getBusiLicenseFile() {
		return this.busiLicenseFile;
	}
	public void setBusiLicenseFile(Integer busiLicenseFile) {
		this.busiLicenseFile=busiLicenseFile;
	}
	private Integer corporateFile;//
	public Integer getCorporateFile() {
		return this.corporateFile;
	}
	public void setCorporateFile(Integer corporateFile) {
		this.corporateFile=corporateFile;
	}
	private Integer deputyFile;//
	public Integer getDeputyFile() {
		return this.deputyFile;
	}
	public void setDeputyFile(Integer deputyFile) {
		this.deputyFile=deputyFile;
	}
	private String otherFile;//
	public String getOtherFile() {
		return this.otherFile;
	}
	public void setOtherFile(String otherFile) {
		this.otherFile=otherFile;
	}
	private String otherFileEq;//
	public String getOtherFileEq() {
		return this.otherFileEq;
	}
	public void setOtherFileEq(String otherFileEq) {
		this.otherFileEq=otherFileEq;
	}
	private Integer isLegalize;//
	public Integer getIsLegalize() {
		return this.isLegalize;
	}
	public void setIsLegalize(Integer isLegalize) {
		this.isLegalize=isLegalize;
	}
	private String customerNo;//
	public String getCustomerNo() {
		return this.customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo=customerNo;
	}
	private String customerNoEq;//
	public String getCustomerNoEq() {
		return this.customerNoEq;
	}
	public void setCustomerNoEq(String customerNoEq) {
		this.customerNoEq=customerNoEq;
	}
	private String channelCode;//
	public String getChannelCode() {
		return this.channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode=channelCode;
	}
	private String channelCodeEq;//
	public String getChannelCodeEq() {
		return this.channelCodeEq;
	}
	public void setChannelCodeEq(String channelCodeEq) {
		this.channelCodeEq=channelCodeEq;
	}
	private Integer corpLogo;

	public Integer getCorpLogo() {
		return corpLogo;
	}

	public void setCorpLogo(Integer corpLogo) {
		this.corpLogo = corpLogo;
	}
	
	public String getOpenmallEq() {
		return openmallEq;
	}

	public void setOpenmallEq(String openmallEq) {
		this.openmallEq = openmallEq;
	}

	public String getTelephoneEq() {
		return telephoneEq;
	}

	public void setTelephoneEq(String telephoneEq) {
		this.telephoneEq = telephoneEq;
	}

	private String openmallEq;
	
	private String telephoneEq;
	
	//新增标记dr
	private Integer dr;

	
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}
	// 首字母拼音
	private String pinYin;

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String name; //注册人

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// 数据权限
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
	private String isChannelR;

	public String getIsChannelR() {
		return isChannelR;
	}

	public void setIsChannelR(String isChannelR) {
		this.isChannelR = isChannelR;
	}
	
	private String isAnswerQ;

	public String getIsAnswerQ() {
		return isAnswerQ;
	}

	public void setIsAnswerQ(String isAnswerQ) {
		this.isAnswerQ = isAnswerQ;
	}
	public String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	private String situation;

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	private String isSign;

	private String requirement;
	
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	public String getCreateDtFrom() {
		return createDtFrom;
	}

	public void setCreateDtFrom(String createDtFrom) {
		this.createDtFrom = createDtFrom;
	}

	public String getCreateDtTo() {
		return createDtTo;
	}

	public void setCreateDtTo(String createDtTo) {
		this.createDtTo = createDtTo;
	}
	
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	private String suggestion;



	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}


	private String appid;
	private String appsecret;

	public String getAppid() {
		return appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	//员工数据来源 SYSTEM : 自添加 THREEPART : 第三方 ALL : 都有
	public String getEmployeeSource() {
		return employeeSource;
	}

	public void setEmployeeSource(String employeeSource) {
		this.employeeSource = employeeSource;
	}
}
