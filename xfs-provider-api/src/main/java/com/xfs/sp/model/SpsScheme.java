package com.xfs.sp.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;import java.util.List;import java.util.Map;/** * SpsScheme * @author:wuzhe * @date:2016/08/03 10:34:05	 */public class SpsScheme implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer schemeId;//	public Integer getSchemeId() {	    return this.schemeId;	}	public void setSchemeId(Integer schemeId) {	    this.schemeId=schemeId;	}	private Integer spId;//	public Integer getSpId() {	    return this.spId;	}	public void setSpId(Integer spId) {	    this.spId=spId;	}	private Integer cpId;//	public Integer getCpId() {	    return this.cpId;	}	public void setCpId(Integer cpId) {	    this.cpId=cpId;	}	private String name;//	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	private String nameEq;//	public String getNameEq() {	    return this.nameEq;	}	public void setNameEq(String nameEq) {	    this.nameEq=nameEq;	}	private Integer areaId;//	public Integer getAreaId() {	    return this.areaId;	}	public void setAreaId(Integer areaId) {	    this.areaId=areaId;	}	private String schemeType;//DIY:自己做            委托：DEPUTE            受托：ENTRUSTED	public String getSchemeType() {	    return this.schemeType;	}	public void setSchemeType(String schemeType) {	    this.schemeType=schemeType;	}	private String schemeTypeEq;//DIY:自己做            委托：DEPUTE            受托：ENTRUSTED	public String getSchemeTypeEq() {	    return this.schemeTypeEq;	}	public void setSchemeTypeEq(String schemeTypeEq) {	    this.schemeTypeEq=schemeTypeEq;	}	private Integer parentSpId;//委托方记录委托方案id	public Integer getParentSpId() {	    return this.parentSpId;	}	public void setParentSpId(Integer parentSpId) {	    this.parentSpId=parentSpId;	}	private Integer parentId;//委托方记录委托方案id	public Integer getParentId() {	    return this.parentId;	}	public void setParentId(Integer parentId) {	    this.parentId=parentId;	}	private String state;//USE:使用            STOP:停用	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}		private String ispackage;		private String stateEq;//USE:使用            STOP:停用	public String getStateEq() {	    return this.stateEq;	}	public void setStateEq(String stateEq) {	    this.stateEq=stateEq;	}	private BigDecimal price;//	public BigDecimal getPrice() {	    return this.price;	}	public void setPrice(BigDecimal price) {	    this.price=price;	}	private String insuranceType;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getInsuranceType() {	    return this.insuranceType;	}	public void setInsuranceType(String insuranceType) {	    this.insuranceType=insuranceType;	}	private String insuranceTypeEq;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getInsuranceTypeEq() {	    return this.insuranceTypeEq;	}	public void setInsuranceTypeEq(String insuranceTypeEq) {	    this.insuranceTypeEq=insuranceTypeEq;	}	private Integer insuranceAccountId;//	public Integer getInsuranceAccountId() {	    return this.insuranceAccountId;	}	public void setInsuranceAccountId(Integer insuranceAccountId) {	    this.insuranceAccountId=insuranceAccountId;	}	private String fundType;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getFundType() {	    return this.fundType;	}	public void setFundType(String fundType) {	    this.fundType=fundType;	}	private String fundTypeEq;//大库:      COMMON            单立户:  SINGLE            社保户类型	public String getFundTypeEq() {	    return this.fundTypeEq;	}	public void setFundTypeEq(String fundTypeEq) {	    this.fundTypeEq=fundTypeEq;	}	private Integer fundAccountId;//	public Integer getFundAccountId() {	    return this.fundAccountId;	}	public void setFundAccountId(Integer fundAccountId) {	    this.fundAccountId=fundAccountId;	}	private String billType;//MONTH:月报账单            ADVANCE_CHARGE:预付款账单            	public String getBillType() {	    return this.billType;	}	public void setBillType(String billType) {	    this.billType=billType;	}	private String billTypeEq;//MONTH:月报账单            ADVANCE_CHARGE:预付款账单            	public String getBillTypeEq() {	    return this.billTypeEq;	}	public void setBillTypeEq(String billTypeEq) {	    this.billTypeEq=billTypeEq;	}	private Integer endDate;//	public Integer getEndDate() {	    return this.endDate;	}	public void setEndDate(Integer endDate) {	    this.endDate=endDate;	}	private String billDateType;//CURRENT：当月            NEXT：次月	public String getBillDateType() {	    return this.billDateType;	}	public void setBillDateType(String billDateType) {	    this.billDateType=billDateType;	}	private String billDateTypeEq;//CURRENT：当月            NEXT：次月	public String getBillDateTypeEq() {	    return this.billDateTypeEq;	}	public void setBillDateTypeEq(String billDateTypeEq) {	    this.billDateTypeEq=billDateTypeEq;	}	private Integer billDate;//	public Integer getBillDate() {	    return this.billDate;	}	public void setBillDate(Integer billDate) {	    this.billDate=billDate;	}	private String payDateType;//CURRENT：当月            NEXT：次月	public String getPayDateType() {	    return this.payDateType;	}	public void setPayDateType(String payDateType) {	    this.payDateType=payDateType;	}	private String payDateTypeEq;//CURRENT：当月            NEXT：次月	public String getPayDateTypeEq() {	    return this.payDateTypeEq;	}	public void setPayDateTypeEq(String payDateTypeEq) {	    this.payDateTypeEq=payDateTypeEq;	}	private Integer payDate;//	public Integer getPayDate() {	    return this.payDate;	}	public void setPayDate(Integer payDate) {	    this.payDate=payDate;	}	private Integer afterpayRule;//	public Integer getAfterpayRule() {	    return this.afterpayRule;	}	public void setAfterpayRule(Integer afterpayRule) {	    this.afterpayRule=afterpayRule;	}	private String memo;//	public String getMemo() {	    return this.memo;	}	public void setMemo(String memo) {	    this.memo=memo;	}	private String memoEq;//	public String getMemoEq() {	    return this.memoEq;	}	public void setMemoEq(String memoEq) {	    this.memoEq=memoEq;	}	private String isdefault;//Y:是            N:否	public String getIsdefault() {	    return this.isdefault;	}	public void setIsdefault(String isdefault) {	    this.isdefault=isdefault;	}	private String isdefaultEq;//Y:是            N:否	public String getIsdefaultEq() {	    return this.isdefaultEq;	}	public void setIsdefaultEq(String isdefaultEq) {	    this.isdefaultEq=isdefaultEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	 	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.schemeId!=null)			ht.put("schemeId",this.schemeId);		if(this.spId!=null)			ht.put("spId",this.spId);		if(this.cpId!=null)			ht.put("cpId",this.cpId);		if(this.name!=null)			ht.put("name",this.name);		if(this.nameEq!=null)			ht.put("nameEq",this.nameEq);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.schemeType!=null)			ht.put("schemeType",this.schemeType);		if(this.schemeTypeEq!=null)			ht.put("schemeTypeEq",this.schemeTypeEq);		if(this.parentSpId!=null)			ht.put("parentSpId",this.parentSpId);		if(this.parentId!=null)			ht.put("parentId",this.parentId);		if(this.state!=null)			ht.put("state",this.state);		if(this.stateEq!=null)			ht.put("stateEq",this.stateEq);		if(this.price!=null)			ht.put("price",this.price);		if(this.insuranceType!=null)			ht.put("insuranceType",this.insuranceType);		if(this.insuranceTypeEq!=null)			ht.put("insuranceTypeEq",this.insuranceTypeEq);		if(this.insuranceAccountId!=null)			ht.put("insuranceAccountId",this.insuranceAccountId);		if(this.fundType!=null)			ht.put("fundType",this.fundType);		if(this.fundTypeEq!=null)			ht.put("fundTypeEq",this.fundTypeEq);		if(this.fundAccountId!=null)			ht.put("fundAccountId",this.fundAccountId);		if(this.billType!=null)			ht.put("billType",this.billType);		if(this.billTypeEq!=null)			ht.put("billTypeEq",this.billTypeEq);		if(this.endDate!=null)			ht.put("endDate",this.endDate);		if(this.billDateType!=null)			ht.put("billDateType",this.billDateType);		if(this.billDateTypeEq!=null)			ht.put("billDateTypeEq",this.billDateTypeEq);		if(this.billDate!=null)			ht.put("billDate",this.billDate);		if(this.payDateType!=null)			ht.put("payDateType",this.payDateType);		if(this.payDateTypeEq!=null)			ht.put("payDateTypeEq",this.payDateTypeEq);		if(this.payDate!=null)			ht.put("payDate",this.payDate);		if(this.afterpayRule!=null)			ht.put("afterpayRule",this.afterpayRule);		if(this.memo!=null)			ht.put("memo",this.memo);		if(this.memoEq!=null)			ht.put("memoEq",this.memoEq);		if(this.isdefault!=null)			ht.put("isdefault",this.isdefault);		if(this.isdefaultEq!=null)			ht.put("isdefaultEq",this.isdefaultEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.areaname!=null)			ht.put("areaname",this.areaname);		if(this.authority!=null)			ht.put("authority",this.authority);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/08/03 10:34:05		private List<SpsSchemeInsurance> insurances = null;	public List<SpsSchemeInsurance> getSchemeInsurances() {		return insurances;	}		public void setSchemeInsurances(List<SpsSchemeInsurance> insurances) {		this.insurances = insurances;	}		private Map<Integer, SpsSchemeInsurance> insurancesMap = null;	public Map<Integer, SpsSchemeInsurance> getInsurancesMap() {		if (insurancesMap == null) {			insurancesMap = new HashMap<Integer, SpsSchemeInsurance>();			if (insurances != null) {				for (SpsSchemeInsurance insurance : insurances) {					insurancesMap.put(insurance.getInsuranceId(), insurance);				}			}		}		return insurancesMap;	}	public String getIspackage() {		return ispackage;	}	public void setIspackage(String ispackage) {		this.ispackage = ispackage;	}		public String getIspackageEq() {		return ispackageEq;	}	public void setIspackageEq(String ispackageEq) {		this.ispackageEq = ispackageEq;	}	private String ispackageEq;//    private String insurance_fund_type;    //附加属性   用于判断方案是否有社保类型    public String getInsurance_fund_type() {        return insurance_fund_type;    }    public void setInsurance_fund_type(String insurance_fund_type) {        this.insurance_fund_type = insurance_fund_type;    }	private String areaname;	public String getAreaname() {		return areaname;	}	public void setAreaname(String areaname) {		this.areaname = areaname;	}	// 数据权限	private String authority;	public String getAuthority() {		return authority;	}	public void setAuthority(String authority) {		this.authority = authority;	}}