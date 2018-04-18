package com.xfs.sp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * SpsAccountRatio
 * @author:wuzhe
 * @date:2016/09/22 15:16:10	
 */
public class SpsAccountRatio implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer accId;//库id
	public Integer getAccId() {
	    return this.accId;
	}
	public void setAccId(Integer accId) {
	    this.accId=accId;
	}
	private Integer ratioId;//险种比例主键
	public Integer getRatioId() {
	    return this.ratioId;
	}
	public void setRatioId(Integer ratioId) {
	    this.ratioId=ratioId;
	}
	private String livingType;//sysdictionary=93
	public String getLivingType() {
	    return this.livingType;
	}
	public void setLivingType(String livingType) {
	    this.livingType=livingType;
	}
	private String livingTypeEq;//sysdictionary=93
	public String getLivingTypeEq() {
	    return this.livingTypeEq;
	}
	public void setLivingTypeEq(String livingTypeEq) {
	    this.livingTypeEq=livingTypeEq;
	}
	private String isdefault;//Y/N
	public String getIsdefault() {
	    return this.isdefault;
	}
	public void setIsdefault(String isdefault) {
	    this.isdefault=isdefault;
	}
	private String isdefaultEq;//Y/N
	public String getIsdefaultEq() {
	    return this.isdefaultEq;
	}
	public void setIsdefaultEq(String isdefaultEq) {
	    this.isdefaultEq=isdefaultEq;
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
	private Integer dr;//逻辑删除标记位                        0:未删除            1:已删除
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
		if(this.accId!=null)
			ht.put("accId",this.accId);
		if(this.ratioId!=null)
			ht.put("ratioId",this.ratioId);
		if(this.livingType!=null)
			ht.put("livingType",this.livingType);
		if(this.livingTypeEq!=null)
			ht.put("livingTypeEq",this.livingTypeEq);
		if(this.isdefault!=null)
			ht.put("isdefault",this.isdefault);
		if(this.isdefaultEq!=null)
			ht.put("isdefaultEq",this.isdefaultEq);
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
	//2016/09/22 15:16:11
	
	
}






