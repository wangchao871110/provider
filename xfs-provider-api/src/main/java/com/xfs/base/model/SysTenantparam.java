package com.xfs.base.model;

import java.util.HashMap;

/**
 * SysTenantparam
 * @author:zhangyan
 * @date:2015/12/14 12:28:00	
 */
public class SysTenantparam implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	private String paramType;//
	private String paramTypeEq;//
	private String paramCode;//
	private String paramCodeEq;//
	private String paramName;//
	private String paramNameEq;//
	private String defValue;//   默认值
	private String defValueEq;//   默认值
	private String paramContent;//
	private String paramContentEq;//
	private String paramDesc;//
	private String paramDescEq;//
	private Integer isDisplay;//   是否放开让用户配置0:否1：是
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public String getParamType() {
	    return this.paramType;
	}
	public void setParamType(String paramType) {
	    this.paramType=paramType;
	}
	public String getParamTypeEq() {
	    return this.paramTypeEq;
	}
	public void setParamTypeEq(String paramTypeEq) {
	    this.paramTypeEq=paramTypeEq;
	}
	public String getParamCode() {
	    return this.paramCode;
	}
	public void setParamCode(String paramCode) {
	    this.paramCode=paramCode;
	}
	public String getParamCodeEq() {
	    return this.paramCodeEq;
	}
	public void setParamCodeEq(String paramCodeEq) {
	    this.paramCodeEq=paramCodeEq;
	}
	public String getParamName() {
	    return this.paramName;
	}
	public void setParamName(String paramName) {
	    this.paramName=paramName;
	}
	public String getParamNameEq() {
	    return this.paramNameEq;
	}
	public void setParamNameEq(String paramNameEq) {
	    this.paramNameEq=paramNameEq;
	}
	public String getDefValue() {
	    return this.defValue;
	}
	public void setDefValue(String defValue) {
	    this.defValue=defValue;
	}
	public String getDefValueEq() {
	    return this.defValueEq;
	}
	public void setDefValueEq(String defValueEq) {
	    this.defValueEq=defValueEq;
	}
	public String getParamContent() {
	    return this.paramContent;
	}
	public void setParamContent(String paramContent) {
	    this.paramContent=paramContent;
	}
	public String getParamContentEq() {
	    return this.paramContentEq;
	}
	public void setParamContentEq(String paramContentEq) {
	    this.paramContentEq=paramContentEq;
	}
	public String getParamDesc() {
	    return this.paramDesc;
	}
	public void setParamDesc(String paramDesc) {
	    this.paramDesc=paramDesc;
	}
	public String getParamDescEq() {
	    return this.paramDescEq;
	}
	public void setParamDescEq(String paramDescEq) {
	    this.paramDescEq=paramDescEq;
	}
	public Integer getIsDisplay() {
	    return this.isDisplay;
	}
	public void setIsDisplay(Integer isDisplay) {
	    this.isDisplay=isDisplay;
	}
	public void fixup(){
	}
	public HashMap toHashMap() {
		HashMap ht = new HashMap();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.paramType!=null)
			ht.put("paramType",this.paramType);
		if(this.paramTypeEq!=null)
			ht.put("paramTypeEq",this.paramTypeEq);
		if(this.paramCode!=null)
			ht.put("paramCode",this.paramCode);
		if(this.paramCodeEq!=null)
			ht.put("paramCodeEq",this.paramCodeEq);
		if(this.paramName!=null)
			ht.put("paramName",this.paramName);
		if(this.paramNameEq!=null)
			ht.put("paramNameEq",this.paramNameEq);
		if(this.defValue!=null)
			ht.put("defValue",this.defValue);
		if(this.defValueEq!=null)
			ht.put("defValueEq",this.defValueEq);
		if(this.paramContent!=null)
			ht.put("paramContent",this.paramContent);
		if(this.paramContentEq!=null)
			ht.put("paramContentEq",this.paramContentEq);
		if(this.paramDesc!=null)
			ht.put("paramDesc",this.paramDesc);
		if(this.paramDescEq!=null)
			ht.put("paramDescEq",this.paramDescEq);
		if(this.isDisplay!=null)
			ht.put("isDisplay",this.isDisplay);
		return ht; 
	}
	
	/**
	 * 冗余子表字段remark
	 */
	private String remark;
	private String tid;
	private Integer plistid;//冗余SysTenantparamlist表id
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Integer getPlistid() {
		return plistid;
	}
	public void setPlistid(Integer plistid) {
		this.plistid = plistid;
	}
	
}

