package com.xfs.user.model;


import java.util.Date;
import java.util.HashMap;

/**
 * SysFunctionCrud
 * @author:zhangyan
 */
public class SysFunctionCrud implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer relId;//
	public Integer getRelId() {
		return this.relId;
	}
	public void setRelId(Integer relId) {
		this.relId=relId;
	}
	private String alias;//
	public String getAlias() {
		return this.alias;
	}
	public void setAlias(String alias) {
		this.alias=alias;
	}
	private String aliasEq;//
	public String getAliasEq() {
		return this.aliasEq;
	}
	public void setAliasEq(String aliasEq) {
		this.aliasEq=aliasEq;
	}
	private String description;//
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	private String descriptionEq;//
	public String getDescriptionEq() {
		return this.descriptionEq;
	}
	public void setDescriptionEq(String descriptionEq) {
		this.descriptionEq=descriptionEq;
	}
	private String url;//
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	private String urlEq;//
	public String getUrlEq() {
		return this.urlEq;
	}
	public void setUrlEq(String urlEq) {
		this.urlEq=urlEq;
	}
	private Integer priority;//
	public Integer getPriority() {
		return this.priority;
	}
	public void setPriority(Integer priority) {
		this.priority=priority;
	}
	private String createdBy;//
	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy=createdBy;
	}
	private String createdByEq;//
	public String getCreatedByEq() {
		return this.createdByEq;
	}
	public void setCreatedByEq(String createdByEq) {
		this.createdByEq=createdByEq;
	}
	private Date createdDt;//
	public Date getCreatedDt() {
		return this.createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt=createdDt;
	}
	private Date createdDtFrom;//
	public Date getCreatedDtFrom() {
		return this.createdDtFrom;
	}
	public void setCreatedDtFrom(Date createdDtFrom) {
		this.createdDtFrom=createdDtFrom;
	}
	private Date createdDtTo;//
	public Date getCreatedDtTo() {
		return this.createdDtTo;
	}
	public void setCreatedDtTo(Date createdDtTo) {
		this.createdDtTo=createdDtTo;
	}
	private Integer functionId;//
	public Integer getFunctionId() {
		return this.functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId=functionId;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.relId!=null)
			ht.put("relId",this.relId);
		if(this.alias!=null)
			ht.put("alias",this.alias);
		if(this.aliasEq!=null)
			ht.put("aliasEq",this.aliasEq);
		if(this.description!=null)
			ht.put("description",this.description);
		if(this.descriptionEq!=null)
			ht.put("descriptionEq",this.descriptionEq);
		if(this.url!=null)
			ht.put("url",this.url);
		if(this.urlEq!=null)
			ht.put("urlEq",this.urlEq);
		if(this.priority!=null)
			ht.put("priority",this.priority);
		if(this.createdBy!=null)
			ht.put("createdBy",this.createdBy);
		if(this.createdByEq!=null)
			ht.put("createdByEq",this.createdByEq);
		if(this.createdDt!=null)
			ht.put("createdDt",this.createdDt);
		if(this.createdDtFrom!=null)
			ht.put("createdDtFrom",this.createdDtFrom);
		if(this.createdDtTo!=null)
			ht.put("createdDtTo",this.createdDtTo);
		if(this.functionId!=null)
			ht.put("functionId",this.functionId);
		return ht;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
	
}
