package com.xfs.sp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * SpsAccount
 * @author:wuzhe
 * @date:2016/07/06 17:59:35	
 */
public class SpsAccount implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer accId;//
	public Integer getAccId() {
	    return this.accId;
	}
	public void setAccId(Integer accId) {
	    this.accId=accId;
	}
	private Integer spId;//
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private Integer cpId;//
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	private String name;//
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	private String nameEq;//
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	private String insuranceFundType;//社保       INSURANCE            公积金   FUND
	public String getInsuranceFundType() {
	    return this.insuranceFundType;
	}
	public void setInsuranceFundType(String insuranceFundType) {
	    this.insuranceFundType=insuranceFundType;
	}
	private String insuranceFundTypeEq;//社保       INSURANCE            公积金   FUND
	public String getInsuranceFundTypeEq() {
	    return this.insuranceFundTypeEq;
	}
	public void setInsuranceFundTypeEq(String insuranceFundTypeEq) {
	    this.insuranceFundTypeEq=insuranceFundTypeEq;
	}
	private String accType;//大库:      COMMON            单立户:  SINGLE            社保户类型
	public String getAccType() {
	    return this.accType;
	}
	public void setAccType(String accType) {
	    this.accType=accType;
	}
	private String accTypeEq;//大库:      COMMON            单立户:  SINGLE            社保户类型
	public String getAccTypeEq() {
	    return this.accTypeEq;
	}
	public void setAccTypeEq(String accTypeEq) {
	    this.accTypeEq=accTypeEq;
	}
	private Integer areaId;//
	public Integer getAreaId() {
	    return this.areaId;
	}
	public void setAreaId(Integer areaId) {
	    this.areaId=areaId;
	}
	private Integer collectionDay;//1-31
	public Integer getCollectionDay() {
	    return this.collectionDay;
	}
	public void setCollectionDay(Integer collectionDay) {
	    this.collectionDay=collectionDay;
	}
	private String regNum;//
	public String getRegNum() {
	    return this.regNum;
	}
	public void setRegNum(String regNum) {
	    this.regNum=regNum;
	}
	private String regNumEq;//
	public String getRegNumEq() {
	    return this.regNumEq;
	}
	public void setRegNumEq(String regNumEq) {
	    this.regNumEq=regNumEq;
	}
	private String regNumpass;//
	public String getRegNumpass() {
	    return this.regNumpass;
	}
	public void setRegNumpass(String regNumpass) {
	    this.regNumpass=regNumpass;
	}
	private String regNumpassEq;//
	public String getRegNumpassEq() {
	    return this.regNumpassEq;
	}
	public void setRegNumpassEq(String regNumpassEq) {
	    this.regNumpassEq=regNumpassEq;
	}
	private String regUsbkeypass;//
	public String getRegUsbkeypass() {
	    return this.regUsbkeypass;
	}
	public void setRegUsbkeypass(String regUsbkeypass) {
	    this.regUsbkeypass=regUsbkeypass;
	}
	private String regUsbkeypassEq;//
	public String getRegUsbkeypassEq() {
	    return this.regUsbkeypassEq;
	}
	public void setRegUsbkeypassEq(String regUsbkeypassEq) {
	    this.regUsbkeypassEq=regUsbkeypassEq;
	}
	private BigDecimal empRatio;//
	public BigDecimal getEmpRatio() {
	    return this.empRatio;
	}
	public void setEmpRatio(BigDecimal empRatio) {
	    this.empRatio=empRatio;
	}
	private BigDecimal corpRatio;//
	public BigDecimal getCorpRatio() {
	    return this.corpRatio;
	}
	public void setCorpRatio(BigDecimal corpRatio) {
	    this.corpRatio=corpRatio;
	}
	private String state;//docState
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	private String stateEq;//docState
	public String getStateEq() {
	    return this.stateEq;
	}
	public void setStateEq(String stateEq) {
	    this.stateEq=stateEq;
	}
	private String remark;//
	public String getRemark() {
	    return this.remark;
	}
	public void setRemark(String remark) {
	    this.remark=remark;
	}
	private String remarkEq;//
	public String getRemarkEq() {
	    return this.remarkEq;
	}
	public void setRemarkEq(String remarkEq) {
	    this.remarkEq=remarkEq;
	}
	private Integer dr;//逻辑删除标记位                        0:未删除            1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
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
	private BigDecimal unemploymentRate;//失业比例
	public BigDecimal getUnemploymentRate() {
	    return this.unemploymentRate;
	}
	public void setUnemploymentRate(BigDecimal unemploymentRate) {
	    this.unemploymentRate=unemploymentRate;
	}

	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.accId!=null)
			ht.put("accId",this.accId);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.insuranceFundType!=null)
			ht.put("insuranceFundType",this.insuranceFundType);
		if(this.insuranceFundTypeEq!=null)
			ht.put("insuranceFundTypeEq",this.insuranceFundTypeEq);
		if(this.accType!=null)
			ht.put("accType",this.accType);
		if(this.accTypeEq!=null)
			ht.put("accTypeEq",this.accTypeEq);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.collectionDay!=null)
			ht.put("collectionDay",this.collectionDay);
		if(this.regNum!=null)
			ht.put("regNum",this.regNum);
		if(this.regNumEq!=null)
			ht.put("regNumEq",this.regNumEq);
		if(this.regNumpass!=null)
			ht.put("regNumpass",this.regNumpass);
		if(this.regNumpassEq!=null)
			ht.put("regNumpassEq",this.regNumpassEq);
		if(this.regUsbkeypass!=null)
			ht.put("regUsbkeypass",this.regUsbkeypass);
		if(this.regUsbkeypassEq!=null)
			ht.put("regUsbkeypassEq",this.regUsbkeypassEq);
		if(this.empRatio!=null)
			ht.put("empRatio",this.empRatio);
		if(this.corpRatio!=null)
			ht.put("corpRatio",this.corpRatio);
		if(this.state!=null)
			ht.put("state",this.state);
		if(this.stateEq!=null)
			ht.put("stateEq",this.stateEq);
		if(this.remark!=null)
			ht.put("remark",this.remark);
		if(this.remarkEq!=null)
			ht.put("remarkEq",this.remarkEq);
		if(this.dr!=null)
			ht.put("dr",this.dr);
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
		if(this.unemploymentRate!=null)
			ht.put("unemploymentRate",this.unemploymentRate);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/06 17:59:36
	
	private BigDecimal disabilityPay;//残障金
	public BigDecimal getDisabilityPay() {
	    return this.disabilityPay;
	}
	public void setDisabilityPay(BigDecimal disabilityPay) {
	    this.disabilityPay=disabilityPay;
	}
	
	public int getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(int parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	private int  parentAreaId;

	private String usbport;
	public String getUsbport() {
		return this.usbport;
	}
	public void setUsbport(String usbport) {
		this.usbport=usbport;
	}

	private String usbcode;
	private String usbname;

	public String getUsbcode() {
		return usbcode;
	}

	public void setUsbcode(String usbcode) {
		this.usbcode = usbcode;
	}

	public String getUsbname() {
		return usbname;
	}

	public void setUsbname(String usbname) {
		this.usbname = usbname;
	}


	private String corpName;

	private String contaninerName;

	private Date userSignedDate;

	private String autoLogin;

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getContaninerName() {
		return contaninerName;
	}

	public void setContaninerName(String contaninerName) {
		this.contaninerName = contaninerName;
	}

	public Date getUserSignedDate() {
		return userSignedDate;
	}

	public void setUserSignedDate(Date userSignedDate) {
		this.userSignedDate = userSignedDate;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
}

