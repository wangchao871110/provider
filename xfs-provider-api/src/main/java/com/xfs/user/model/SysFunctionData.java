package com.xfs.user.model;import java.util.Date;import java.util.HashMap;/** * SysFunctionData * @author:zhangyan * @date:2016/08/23 14:25:55 */public class SysFunctionData implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer id;//	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id=id;	}	private Integer userId;//	public Integer getUserId() {		return this.userId;	}	public void setUserId(Integer userId) {		this.userId=userId;	}	private String userType;//SERVICE:sps系统            BUSINESS：bs系统            CUSTOMER：cs系统	public String getUserType() {		return this.userType;	}	public void setUserType(String userType) {		this.userType=userType;	}	private String userTypeEq;//SERVICE:sps系统            BUSINESS：bs系统            CUSTOMER：cs系统	public String getUserTypeEq() {		return this.userTypeEq;	}	public void setUserTypeEq(String userTypeEq) {		this.userTypeEq=userTypeEq;	}	private String dataType;//保存在数据表名，例如cm_corp,业务代码添加数据权限时 根据此字段过滤	public String getDataType() {		return this.dataType;	}	public void setDataType(String dataType) {		this.dataType=dataType;	}	private String dataTypeEq;//保存在数据表名，例如cm_corp,业务代码添加数据权限时 根据此字段过滤	public String getDataTypeEq() {		return this.dataTypeEq;	}	public void setDataTypeEq(String dataTypeEq) {		this.dataTypeEq=dataTypeEq;	}	private Integer dataId;//具体数据表里面数据的id	public Integer getDataId() {		return this.dataId;	}	public void setDataId(Integer dataId) {		this.dataId=dataId;	}	private Integer createBy;//	public Integer getCreateBy() {		return this.createBy;	}	public void setCreateBy(Integer createBy) {		this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {		return this.createDt;	}	public void setCreateDt(Date createDt) {		this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {		return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {		this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {		return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {		this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {		return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {		this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {		return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {		this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {		return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {		this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {		return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {		this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {		return this.dr;	}	public void setDr(Integer dr) {		this.dr=dr;	}	private String isAll;//Y：否            N：否	public String getIsAll() {		return this.isAll;	}	public void setIsAll(String isAll) {		this.isAll=isAll;	}	private String isAllEq;//Y：否            N：否	public String getIsAllEq() {		return this.isAllEq;	}	public void setIsAllEq(String isAllEq) {		this.isAllEq=isAllEq;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.userId!=null)			ht.put("userId",this.userId);		if(this.userType!=null)			ht.put("userType",this.userType);		if(this.userTypeEq!=null)			ht.put("userTypeEq",this.userTypeEq);		if(this.dataType!=null)			ht.put("dataType",this.dataType);		if(this.dataTypeEq!=null)			ht.put("dataTypeEq",this.dataTypeEq);		if(this.dataId!=null)			ht.put("dataId",this.dataId);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.isAll!=null)			ht.put("isAll",this.isAll);		if(this.isAllEq!=null)			ht.put("isAllEq",this.isAllEq);		return ht;	}	//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/08/23 14:25:55}