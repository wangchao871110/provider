package com.xfs.user.model;

import java.util.HashMap;

/**
 * SysFunctionCategory
 * @author:zhangyan
 */
public class SysFunctionCategory implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Integer categoryId;//
	public Integer getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId=categoryId;
	}
	private String categoryName;//
	public String getCategoryName() {
		return this.categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName=categoryName;
	}
	private String categoryNameEq;//
	public String getCategoryNameEq() {
		return this.categoryNameEq;
	}
	public void setCategoryNameEq(String categoryNameEq) {
		this.categoryNameEq=categoryNameEq;
	}
	private Integer priority;//
	public Integer getPriority() {
		return this.priority;
	}
	public void setPriority(Integer priority) {
		this.priority=priority;
	}
	private Integer parentId;//
	public Integer getParentId() {
		return this.parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId=parentId;
	}
	private String iconClass;//
	public String getIconClass() {
		return this.iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass=iconClass;
	}
	private String iconClassEq;//
	public String getIconClassEq() {
		return this.iconClassEq;
	}
	public void setIconClassEq(String iconClassEq) {
		this.iconClassEq=iconClassEq;
	}
	private String sysCode;//所属系统 MALL OP SAAS
	public String getSysCode() {
		return this.sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode=sysCode;
	}
	private String sysCodeEq;//所属系统 MALL OP SAAS
	public String getSysCodeEq() {
		return this.sysCodeEq;
	}
	public void setSysCodeEq(String sysCodeEq) {
		this.sysCodeEq=sysCodeEq;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.categoryId!=null)
			ht.put("categoryId",this.categoryId);
		if(this.categoryName!=null)
			ht.put("categoryName",this.categoryName);
		if(this.categoryNameEq!=null)
			ht.put("categoryNameEq",this.categoryNameEq);
		if(this.priority!=null)
			ht.put("priority",this.priority);
		if(this.parentId!=null)
			ht.put("parentId",this.parentId);
		if(this.iconClass!=null)
			ht.put("iconClass",this.iconClass);
		if(this.iconClassEq!=null)
			ht.put("iconClassEq",this.iconClassEq);
		if(this.sysCode!=null)
			ht.put("sysCode",this.sysCode);
		if(this.sysCodeEq!=null)
			ht.put("sysCodeEq",this.sysCodeEq);
		return ht;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:21

}
