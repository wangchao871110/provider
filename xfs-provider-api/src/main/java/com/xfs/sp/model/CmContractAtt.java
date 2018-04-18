package com.xfs.sp.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * CmContractAtt * @author:wuzhe * @date:2016/08/02 10:14:24	 */public class CmContractAtt implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer contractAttId;//主键ID	public Integer getContractAttId() {	    return this.contractAttId;	}	public void setContractAttId(Integer contractAttId) {	    this.contractAttId=contractAttId;	}	private Integer contractId;//合同ID	public Integer getContractId() {	    return this.contractId;	}	public void setContractId(Integer contractId) {	    this.contractId=contractId;	}	private String name;//方案名称	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	private String nameEq;//方案名称	public String getNameEq() {	    return this.nameEq;	}	public void setNameEq(String nameEq) {	    this.nameEq=nameEq;	}	private Integer areaId;//参保区域	public Integer getAreaId() {	    return this.areaId;	}	public void setAreaId(Integer areaId) {	    this.areaId=areaId;	}	private BigDecimal price;//价格	public BigDecimal getPrice() {	    return this.price;	}	public void setPrice(BigDecimal price) {	    this.price=price;	}	private String billType;//账单类型MONTH:月报账单；ADVANCE_CHARGE:预付款账单            	public String getBillType() {	    return this.billType;	}	public void setBillType(String billType) {	    this.billType=billType;	}	private String billTypeEq;//账单类型MONTH:月报账单；ADVANCE_CHARGE:预付款账单            	public String getBillTypeEq() {	    return this.billTypeEq;	}	public void setBillTypeEq(String billTypeEq) {	    this.billTypeEq=billTypeEq;	}	private Integer endDate;//当月申报业务截止日期	public Integer getEndDate() {	    return this.endDate;	}	public void setEndDate(Integer endDate) {	    this.endDate=endDate;	}	private String billDateType;//账单日CURRENT：当月；NEXT：次月	public String getBillDateType() {	    return this.billDateType;	}	public void setBillDateType(String billDateType) {	    this.billDateType=billDateType;	}	private String billDateTypeEq;//账单日CURRENT：当月；NEXT：次月	public String getBillDateTypeEq() {	    return this.billDateTypeEq;	}	public void setBillDateTypeEq(String billDateTypeEq) {	    this.billDateTypeEq=billDateTypeEq;	}	private Integer billDate;//账单日号	public Integer getBillDate() {	    return this.billDate;	}	public void setBillDate(Integer billDate) {	    this.billDate=billDate;	}	private String payDateType;//付款日CURRENT：当月；NEXT：次月	public String getPayDateType() {	    return this.payDateType;	}	public void setPayDateType(String payDateType) {	    this.payDateType=payDateType;	}	private String payDateTypeEq;//付款日CURRENT：当月；NEXT：次月	public String getPayDateTypeEq() {	    return this.payDateTypeEq;	}	public void setPayDateTypeEq(String payDateTypeEq) {	    this.payDateTypeEq=payDateTypeEq;	}	private Integer payDate;//付款日号	public Integer getPayDate() {	    return this.payDate;	}	public void setPayDate(Integer payDate) {	    this.payDate=payDate;	}	private Integer afterpayRule;//补缴规则	public Integer getAfterpayRule() {	    return this.afterpayRule;	}	public void setAfterpayRule(Integer afterpayRule) {	    this.afterpayRule=afterpayRule;	}	private String memo;//备注	public String getMemo() {	    return this.memo;	}	public void setMemo(String memo) {	    this.memo=memo;	}	private String memoEq;//备注	public String getMemoEq() {	    return this.memoEq;	}	public void setMemoEq(String memoEq) {	    this.memoEq=memoEq;	}	private Integer createBy;//创建人	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//创建日期	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//创建日期	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//创建日期	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//更新人	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//更新日期	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//更新日期	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//更新日期	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//逻辑删除标记位 0:未删除 1:已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.contractAttId!=null)			ht.put("contractAttId",this.contractAttId);		if(this.contractId!=null)			ht.put("contractId",this.contractId);		if(this.name!=null)			ht.put("name",this.name);		if(this.nameEq!=null)			ht.put("nameEq",this.nameEq);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.price!=null)			ht.put("price",this.price);		if(this.billType!=null)			ht.put("billType",this.billType);		if(this.billTypeEq!=null)			ht.put("billTypeEq",this.billTypeEq);		if(this.endDate!=null)			ht.put("endDate",this.endDate);		if(this.billDateType!=null)			ht.put("billDateType",this.billDateType);		if(this.billDateTypeEq!=null)			ht.put("billDateTypeEq",this.billDateTypeEq);		if(this.billDate!=null)			ht.put("billDate",this.billDate);		if(this.payDateType!=null)			ht.put("payDateType",this.payDateType);		if(this.payDateTypeEq!=null)			ht.put("payDateTypeEq",this.payDateTypeEq);		if(this.payDate!=null)			ht.put("payDate",this.payDate);		if(this.afterpayRule!=null)			ht.put("afterpayRule",this.afterpayRule);		if(this.memo!=null)			ht.put("memo",this.memo);		if(this.memoEq!=null)			ht.put("memoEq",this.memoEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/08/02 10:14:24		}