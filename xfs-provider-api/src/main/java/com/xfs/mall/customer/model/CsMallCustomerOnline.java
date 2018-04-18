package com.xfs.mall.customer.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CsMallCustomerOnline
 * @author:duanax
 * @date:2016/07/22 13:56:10	
 */
public class CsMallCustomerOnline implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer customerId;//
	public Integer getCustomerId() {
	    return this.customerId;
	}
	public void setCustomerId(Integer customerId) {
	    this.customerId=customerId;
	}
	private Date begintime;//
	public Date getBegintime() {
	    return this.begintime;
	}
	public void setBegintime(Date begintime) {
	    this.begintime=begintime;
	}
	private Date begintimeFrom;//
	public Date getBegintimeFrom() {
	    return this.begintimeFrom;
	}
	public void setBegintimeFrom(Date begintimeFrom) {
	    this.begintimeFrom=begintimeFrom;
	}
	private Date begintimeTo;//
	public Date getBegintimeTo() {
	    return this.begintimeTo;
	}
	public void setBegintimeTo(Date begintimeTo) {
	    this.begintimeTo=begintimeTo;
	}
	private Date endtime;//
	public Date getEndtime() {
	    return this.endtime;
	}
	public void setEndtime(Date endtime) {
	    this.endtime=endtime;
	}
	private Date endtimeFrom;//
	public Date getEndtimeFrom() {
	    return this.endtimeFrom;
	}
	public void setEndtimeFrom(Date endtimeFrom) {
	    this.endtimeFrom=endtimeFrom;
	}
	private Date endtimeTo;//
	public Date getEndtimeTo() {
	    return this.endtimeTo;
	}
	public void setEndtimeTo(Date endtimeTo) {
	    this.endtimeTo=endtimeTo;
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
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.customerId!=null)
			ht.put("customerId",this.customerId);
		if(this.begintime!=null)
			ht.put("begintime",this.begintime);
		if(this.begintimeFrom!=null)
			ht.put("begintimeFrom",this.begintimeFrom);
		if(this.begintimeTo!=null)
			ht.put("begintimeTo",this.begintimeTo);
		if(this.endtime!=null)
			ht.put("endtime",this.endtime);
		if(this.endtimeFrom!=null)
			ht.put("endtimeFrom",this.endtimeFrom);
		if(this.endtimeTo!=null)
			ht.put("endtimeTo",this.endtimeTo);
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
	//2016/07/22 13:56:10
	
	
}

