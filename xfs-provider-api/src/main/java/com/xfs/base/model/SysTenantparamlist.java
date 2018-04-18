package com.xfs.base.model;

import java.util.HashMap;

/**
 * SysTenantparamlist
 * @author:zhangyan
 * @date:2015/12/14 12:28:00	
 */
public class SysTenantparamlist implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String tid;//   租户id
	private String tidEq;//   租户id
	private String paramCode;//
	private String paramCodeEq;//
	private String paramValue;//
	private String paramValueEq;//
	private String remark;//
	private String remarkEq;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public String getTid() {
	    return this.tid;
	}
	public void setTid(String tid) {
	    this.tid=tid;
	}
	public String getTidEq() {
	    return this.tidEq;
	}
	public void setTidEq(String tidEq) {
	    this.tidEq=tidEq;
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
	public String getParamValue() {
	    return this.paramValue;
	}
	public void setParamValue(String paramValue) {
	    this.paramValue=paramValue;
	}
	public String getParamValueEq() {
	    return this.paramValueEq;
	}
	public void setParamValueEq(String paramValueEq) {
	    this.paramValueEq=paramValueEq;
	}
	public String getRemark() {
	    return this.remark;
	}
	public void setRemark(String remark) {
	    this.remark=remark;
	}
	public String getRemarkEq() {
	    return this.remarkEq;
	}
	public void setRemarkEq(String remarkEq) {
	    this.remarkEq=remarkEq;
	}
	public void fixup(){
	}
	public HashMap toHashMap() {
		HashMap ht = new HashMap();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.tid!=null)
			ht.put("tid",this.tid);
		if(this.tidEq!=null)
			ht.put("tidEq",this.tidEq);
		if(this.paramCode!=null)
			ht.put("paramCode",this.paramCode);
		if(this.paramCodeEq!=null)
			ht.put("paramCodeEq",this.paramCodeEq);
		if(this.paramValue!=null)
			ht.put("paramValue",this.paramValue);
		if(this.paramValueEq!=null)
			ht.put("paramValueEq",this.paramValueEq);
		if(this.remark!=null)
			ht.put("remark",this.remark);
		if(this.remarkEq!=null)
			ht.put("remarkEq",this.remarkEq);
		return ht; 
	}
}

