package com.xfs.base.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;



/**
 * BsSppayrecord
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/14 14:00:15	
 */
public class BsSppayrecord implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer spId;//
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private Date beginDate;//
	public Date getBeginDate() {
	    return this.beginDate;
	}
	public void setBeginDate(Date beginDate) {
	    this.beginDate=beginDate;
	}
	private Date beginDateFrom;//
	public Date getBeginDateFrom() {
	    return this.beginDateFrom;
	}
	public void setBeginDateFrom(Date beginDateFrom) {
	    this.beginDateFrom=beginDateFrom;
	}
	private Date beginDateTo;//
	public Date getBeginDateTo() {
	    return this.beginDateTo;
	}
	public void setBeginDateTo(Date beginDateTo) {
	    this.beginDateTo=beginDateTo;
	}
	private Date endDate;//
	public Date getEndDate() {
	    return this.endDate;
	}
	public void setEndDate(Date endDate) {
	    this.endDate=endDate;
	}
	private Date endDateFrom;//
	public Date getEndDateFrom() {
	    return this.endDateFrom;
	}
	public void setEndDateFrom(Date endDateFrom) {
	    this.endDateFrom=endDateFrom;
	}
	private Date endDateTo;//
	public Date getEndDateTo() {
	    return this.endDateTo;
	}
	public void setEndDateTo(Date endDateTo) {
	    this.endDateTo=endDateTo;
	}
	private Integer serviceNum;//
	public Integer getServiceNum() {
	    return this.serviceNum;
	}
	public void setServiceNum(Integer serviceNum) {
	    this.serviceNum=serviceNum;
	}
	private String unit;//saas费用单位：SaasFeeUnit                        季度：QUATER            半年：HALF            年：YEAR            三年：THREE
	public String getUnit() {
	    return this.unit;
	}
	public void setUnit(String unit) {
	    this.unit=unit;
	}
	private String unitEq;//saas费用单位：SaasFeeUnit                        季度：QUATER            半年：HALF            年：YEAR            三年：THREE
	public String getUnitEq() {
	    return this.unitEq;
	}
	public void setUnitEq(String unitEq) {
	    this.unitEq=unitEq;
	}
	private BigDecimal standardFee;//
	public BigDecimal getStandardFee() {
	    return this.standardFee;
	}
	public void setStandardFee(BigDecimal standardFee) {
	    this.standardFee=standardFee;
	}
	private BigDecimal discountedFee;//
	public BigDecimal getDiscountedFee() {
	    return this.discountedFee;
	}
	public void setDiscountedFee(BigDecimal discountedFee) {
	    this.discountedFee=discountedFee;
	}
	private int dr;
	public int getDr() {
		return dr;
	}
	public void setDr(int dr) {
		this.dr = dr;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.beginDate!=null)
			ht.put("beginDate",this.beginDate);
		if(this.beginDateFrom!=null)
			ht.put("beginDateFrom",this.beginDateFrom);
		if(this.beginDateTo!=null)
			ht.put("beginDateTo",this.beginDateTo);
		if(this.endDate!=null)
			ht.put("endDate",this.endDate);
		if(this.endDateFrom!=null)
			ht.put("endDateFrom",this.endDateFrom);
		if(this.endDateTo!=null)
			ht.put("endDateTo",this.endDateTo);
		if(this.serviceNum!=null)
			ht.put("serviceNum",this.serviceNum);
		if(this.unit!=null)
			ht.put("unit",this.unit);
		if(this.unitEq!=null)
			ht.put("unitEq",this.unitEq);
		if(this.standardFee!=null)
			ht.put("standardFee",this.standardFee);
		if(this.discountedFee!=null)
			ht.put("discountedFee",this.discountedFee);
		ht.put("dr", this.dr);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/14 14:00:15
	
	
}

