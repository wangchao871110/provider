package com.xfs.cp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CpImpressLog
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 16:41:44	
 */
public class CpImpressLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer spIdBy;//被点评服务商ID
	public Integer getSpIdBy() {
	    return this.spIdBy;
	}
	public void setSpIdBy(Integer spIdBy) {
	    this.spIdBy=spIdBy;
	}
	private Integer spId;//点评服务商ID
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private Date createDt;//点评时间
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//点评时间
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//点评时间
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private String impressId;//印象ID
	public String getImpressId() {
	    return this.impressId;
	}
	public void setImpressId(String impressId) {
	    this.impressId=impressId;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.spIdBy!=null)
			ht.put("spIdBy",this.spIdBy);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.createDtFrom!=null)
			ht.put("createDtFrom",this.createDtFrom);
		if(this.createDtTo!=null)
			ht.put("createDtTo",this.createDtTo);
		if(this.impressId!=null)
			ht.put("impressId",this.impressId);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 16:41:44
	
	
}

