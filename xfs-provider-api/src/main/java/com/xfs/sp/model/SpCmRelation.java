package com.xfs.sp.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * SpCmRelation * @author:wuzhe * @date:2016/08/13 16:45:38	 */public class SpCmRelation implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer id;//	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id=id;	}	private Integer cpId;//	public Integer getCpId() {		return this.cpId;	}	public void setCpId(Integer cpId) {		this.cpId=cpId;	}	private Integer spId;//	public Integer getSpId() {		return this.spId;	}	public void setSpId(Integer spId) {		this.spId=spId;	}	private String insuranceType;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getInsuranceType() {		return this.insuranceType;	}	public void setInsuranceType(String insuranceType) {		this.insuranceType=insuranceType;	}	private String insuranceTypeEq;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getInsuranceTypeEq() {		return this.insuranceTypeEq;	}	public void setInsuranceTypeEq(String insuranceTypeEq) {		this.insuranceTypeEq=insuranceTypeEq;	}	private String regNum;//	public String getRegNum() {		return this.regNum;	}	public void setRegNum(String regNum) {		this.regNum=regNum;	}	private String regNumEq;//	public String getRegNumEq() {		return this.regNumEq;	}	public void setRegNumEq(String regNumEq) {		this.regNumEq=regNumEq;	}	private Integer areaId;//	public Integer getAreaId() {		return this.areaId;	}	public void setAreaId(Integer areaId) {		this.areaId=areaId;	}	private Integer createBy;//	public Integer getCreateBy() {		return this.createBy;	}	public void setCreateBy(Integer createBy) {		this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {		return this.createDt;	}	public void setCreateDt(Date createDt) {		this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {		return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {		this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {		return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {		this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {		return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {		this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {		return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {		this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {		return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {		this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {		return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {		this.modifyDtTo=modifyDtTo;	}	private String regNumpass;//社保登记证登录口令	public String getRegNumpass() {		return this.regNumpass;	}	public void setRegNumpass(String regNumpass) {		this.regNumpass=regNumpass;	}	private String regNumpassEq;//社保登记证登录口令	public String getRegNumpassEq() {		return this.regNumpassEq;	}	public void setRegNumpassEq(String regNumpassEq) {		this.regNumpassEq=regNumpassEq;	}	private String regUsbkeypass;//社保登记证证书密码	public String getRegUsbkeypass() {		return this.regUsbkeypass;	}	public void setRegUsbkeypass(String regUsbkeypass) {		this.regUsbkeypass=regUsbkeypass;	}	private String regUsbkeypassEq;//社保登记证证书密码	public String getRegUsbkeypassEq() {		return this.regUsbkeypassEq;	}	public void setRegUsbkeypassEq(String regUsbkeypassEq) {		this.regUsbkeypassEq=regUsbkeypassEq;	}	private Integer insuranceAccountId;//	public Integer getInsuranceAccountId() {		return this.insuranceAccountId;	}	public void setInsuranceAccountId(Integer insuranceAccountId) {		this.insuranceAccountId=insuranceAccountId;	}	private String fundType;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getFundType() {		return this.fundType;	}	public void setFundType(String fundType) {		this.fundType=fundType;	}	private String fundTypeEq;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getFundTypeEq() {		return this.fundTypeEq;	}	public void setFundTypeEq(String fundTypeEq) {		this.fundTypeEq=fundTypeEq;	}	private Integer fundAccountId;//	public Integer getFundAccountId() {		return this.fundAccountId;	}	public void setFundAccountId(Integer fundAccountId) {		this.fundAccountId=fundAccountId;	}	private String billLiaison;//月报、账单发送时维护	public String getBillLiaison() {		return this.billLiaison;	}	public void setBillLiaison(String billLiaison) {		this.billLiaison=billLiaison;	}	private String billLiaisonEq;//月报、账单发送时维护	public String getBillLiaisonEq() {		return this.billLiaisonEq;	}	public void setBillLiaisonEq(String billLiaisonEq) {		this.billLiaisonEq=billLiaisonEq;	}	private String billMail;//月报、账单发送时维护	public String getBillMail() {		return this.billMail;	}	public void setBillMail(String billMail) {		this.billMail=billMail;	}	private String billMailEq;//月报、账单发送时维护	public String getBillMailEq() {		return this.billMailEq;	}	public void setBillMailEq(String billMailEq) {		this.billMailEq=billMailEq;	}	private String billDay;//01-31	public String getBillDay() {		return this.billDay;	}	public void setBillDay(String billDay) {		this.billDay=billDay;	}	private String billDayEq;//01-31	public String getBillDayEq() {		return this.billDayEq;	}	public void setBillDayEq(String billDayEq) {		this.billDayEq=billDayEq;	}	private BigDecimal servicePrice;//	public BigDecimal getServicePrice() {		return this.servicePrice;	}	public void setServicePrice(BigDecimal servicePrice) {		this.servicePrice=servicePrice;	}	private String chargeMode;//收费方式: chargeMode                        按人月收费:MANMONTH            其他:OTHER	public String getChargeMode() {		return this.chargeMode;	}	public void setChargeMode(String chargeMode) {		this.chargeMode=chargeMode;	}	private String chargeModeEq;//收费方式: chargeMode                        按人月收费:MANMONTH            其他:OTHER	public String getChargeModeEq() {		return this.chargeModeEq;	}	public void setChargeModeEq(String chargeModeEq) {		this.chargeModeEq=chargeModeEq;	}	private String chargeModeRemark;//	public String getChargeModeRemark() {		return this.chargeModeRemark;	}	public void setChargeModeRemark(String chargeModeRemark) {		this.chargeModeRemark=chargeModeRemark;	}	private String chargeModeRemarkEq;//	public String getChargeModeRemarkEq() {		return this.chargeModeRemarkEq;	}	public void setChargeModeRemarkEq(String chargeModeRemarkEq) {		this.chargeModeRemarkEq=chargeModeRemarkEq;	}	private String source;//SAAS：自有 MALL：商城  ENTRUSTED：受托	public String getSource() {		return this.source;	}	public void setSource(String source) {		this.source=source;	}	private String sourceEq;//SAAS：自有 MALL：商城  ENTRUSTED：受托	public String getSourceEq() {		return this.sourceEq;	}	public void setSourceEq(String sourceEq) {		this.sourceEq=sourceEq;	}	private Integer contractType;//	public Integer getContractType() {		return this.contractType;	}	public void setContractType(Integer contractType) {		this.contractType=contractType;	}	private Integer dr;//是否删除 0 未删除 1 已删除	public Integer getDr() {		return this.dr;	}	public void setDr(Integer dr) {		this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.cpId!=null)			ht.put("cpId",this.cpId);		if(this.spId!=null)			ht.put("spId",this.spId);		if(this.insuranceType!=null)			ht.put("insuranceType",this.insuranceType);		if(this.insuranceTypeEq!=null)			ht.put("insuranceTypeEq",this.insuranceTypeEq);		if(this.regNum!=null)			ht.put("regNum",this.regNum);		if(this.regNumEq!=null)			ht.put("regNumEq",this.regNumEq);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.regNumpass!=null)			ht.put("regNumpass",this.regNumpass);		if(this.regNumpassEq!=null)			ht.put("regNumpassEq",this.regNumpassEq);		if(this.regUsbkeypass!=null)			ht.put("regUsbkeypass",this.regUsbkeypass);		if(this.regUsbkeypassEq!=null)			ht.put("regUsbkeypassEq",this.regUsbkeypassEq);		if(this.insuranceAccountId!=null)			ht.put("insuranceAccountId",this.insuranceAccountId);		if(this.fundType!=null)			ht.put("fundType",this.fundType);		if(this.fundTypeEq!=null)			ht.put("fundTypeEq",this.fundTypeEq);		if(this.fundAccountId!=null)			ht.put("fundAccountId",this.fundAccountId);		if(this.billLiaison!=null)			ht.put("billLiaison",this.billLiaison);		if(this.billLiaisonEq!=null)			ht.put("billLiaisonEq",this.billLiaisonEq);		if(this.billMail!=null)			ht.put("billMail",this.billMail);		if(this.billMailEq!=null)			ht.put("billMailEq",this.billMailEq);		if(this.billDay!=null)			ht.put("billDay",this.billDay);		if(this.billDayEq!=null)			ht.put("billDayEq",this.billDayEq);		if(this.servicePrice!=null)			ht.put("servicePrice",this.servicePrice);		if(this.chargeMode!=null)			ht.put("chargeMode",this.chargeMode);		if(this.chargeModeEq!=null)			ht.put("chargeModeEq",this.chargeModeEq);		if(this.chargeModeRemark!=null)			ht.put("chargeModeRemark",this.chargeModeRemark);		if(this.chargeModeRemarkEq!=null)			ht.put("chargeModeRemarkEq",this.chargeModeRemarkEq);		if(this.source!=null)			ht.put("source",this.source);		if(this.sourceEq!=null)			ht.put("sourceEq",this.sourceEq);		if(this.contractType!=null)			ht.put("contractType",this.contractType);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht;	}	//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/12/21 12:46:38	}