package com.xfs.mall.customer.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CsMallCustomerSession
 * @author:duanax
 * @date:2016/07/22 13:56:38	
 */
public class CsMallCustomerSession implements java.io.Serializable {

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
	private Integer userId;//
	public Integer getUserId() {
	    return this.userId;
	}
	public void setUserId(Integer userId) {
	    this.userId=userId;
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
	public Date getmodifyDt() {
	    return this.modifyDt;
	}
	public void setmodifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//
	public Date getmodifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setmodifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//
	public Date getmodifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setmodifyDtTo(Date modifyDtTo) {
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
		if(this.userId!=null)
			ht.put("userId",this.userId);
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
	//2016/07/22 13:56:38
	
	
}

