package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;
/**
 * 新手指导 数据表
 * @author xiyanzhang
 * @date:2016/12/13 11:21:28	
 */
public class BsSysStateReport implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String owner; //拥有者ID
	private String ownerType; //拥有者类型
	private String	attributeName; //属性名称
	private String	attributeValue; //属性值	
	private String	createBy; //
	private Date	createDt; //
	private String	modifyBy; //
	private Date	modifyDt; //
	private Integer	dr; //逻辑删除标记位0:未删除 1:已删除
	private Integer	cpId; //企业ID
	private Integer	areaId; //城市ID
	private Date createDtFrom;//
	private Date createDtTo;//
	private Date modifyDtFrom;//
	private Date modifyDtTo;//
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	// 数据权限
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getCpId() {
		return cpId;
	}
	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.owner!=null)
			ht.put("owner",this.owner);
		if(this.ownerType!=null)
			ht.put("ownerType",this.ownerType);
		if(this.attributeName!=null)
			ht.put("attributeName",this.attributeName);
		if(this.attributeValue!=null)
			ht.put("attributeValue",this.attributeValue);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.createBy!=null)
			ht.put("createBy",this.createBy);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.modifyBy!=null)
			ht.put("modifyBy",this.modifyBy);
		if(this.modifyDt!=null)
			ht.put("modifyDt",this.modifyDt);
		if(this.dr!=null)
			ht.put("dr",this.dr);
		return ht; 
	}
}
