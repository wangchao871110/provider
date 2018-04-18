package com.xfs.bill.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * SpsBillMargin * @author:konglc * @date:2016/08/12 16:23:20	 */public class SpsBillMargin implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer spId;//	public Integer getSpId() {	    return this.spId;	}	public void setSpId(Integer spId) {	    this.spId=spId;	}	private Integer deputeId;//	public Integer getDeputeId() {	    return this.deputeId;	}	public void setDeputeId(Integer deputeId) {	    this.deputeId=deputeId;	}	private String deputeType;//企业：HR            总包：SP	public String getDeputeType() {	    return this.deputeType;	}	public void setDeputeType(String deputeType) {	    this.deputeType=deputeType;	}	private String deputeTypeEq;//企业：HR            总包：SP	public String getDeputeTypeEq() {	    return this.deputeTypeEq;	}	public void setDeputeTypeEq(String deputeTypeEq) {	    this.deputeTypeEq=deputeTypeEq;	}	private Integer prepayBillId;//	public Integer getPrepayBillId() {	    return this.prepayBillId;	}	public void setPrepayBillId(Integer prepayBillId) {	    this.prepayBillId=prepayBillId;	}	private Integer deputeBillId;//	public Integer getDeputeBillId() {	    return this.deputeBillId;	}	public void setDeputeBillId(Integer deputeBillId) {	    this.deputeBillId=deputeBillId;	}	private BigDecimal officeFee;//	public BigDecimal getOfficeFee() {	    return this.officeFee;	}	public void setOfficeFee(BigDecimal officeFee) {	    this.officeFee=officeFee;	}	private BigDecimal serviceFee;//	public BigDecimal getServiceFee() {	    return this.serviceFee;	}	public void setServiceFee(BigDecimal serviceFee) {	    this.serviceFee=serviceFee;	}	private String offsetType;//应收核销：PREPAY            系统核销：AUTO            运营核销：OP	public String getOffsetType() {	    return this.offsetType;	}	public void setOffsetType(String offsetType) {	    this.offsetType=offsetType;	}	private String offsetTypeEq;//应收核销：PREPAY            系统核销：AUTO            运营核销：OP	public String getOffsetTypeEq() {	    return this.offsetTypeEq;	}	public void setOffsetTypeEq(String offsetTypeEq) {	    this.offsetTypeEq=offsetTypeEq;	}	private String status;//未核销：UNOFFSET            已核销：OFFSETED	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	private String statusEq;//未核销：UNOFFSET            已核销：OFFSETED	public String getStatusEq() {	    return this.statusEq;	}	public void setStatusEq(String statusEq) {	    this.statusEq=statusEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//逻辑删除标记位            0:未删除            1:已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private String period;	public String getPeriod() {		return period;	}	public void setPeriod(String period) {		this.period = period;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.spId!=null)			ht.put("spId",this.spId);		if(this.deputeId!=null)			ht.put("deputeId",this.deputeId);		if(this.deputeType!=null)			ht.put("deputeType",this.deputeType);		if(this.deputeTypeEq!=null)			ht.put("deputeTypeEq",this.deputeTypeEq);		if(this.prepayBillId!=null)			ht.put("prepayBillId",this.prepayBillId);		if(this.deputeBillId!=null)			ht.put("deputeBillId",this.deputeBillId);		if(this.officeFee!=null)			ht.put("officeFee",this.officeFee);		if(this.serviceFee!=null)			ht.put("serviceFee",this.serviceFee);		if(this.offsetType!=null)			ht.put("offsetType",this.offsetType);		if(this.offsetTypeEq!=null)			ht.put("offsetTypeEq",this.offsetTypeEq);		if(this.status!=null)			ht.put("status",this.status);		if(this.statusEq!=null)			ht.put("statusEq",this.statusEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.period!=null)			ht.put("period", this.period);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/08/12 16:23:21		}