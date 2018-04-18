package com.xfs.mall.item.model;import java.util.Date;import java.util.HashMap;import java.util.List;/** * BsMallItemCategory * @author:duanax * @date:2016/10/21 17:46:57	 */public class BsMallItemCategory implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer categoryId;//	public Integer getCategoryId() {	    return this.categoryId;	}	public void setCategoryId(Integer categoryId) {	    this.categoryId=categoryId;	}	private Integer pid;//	public Integer getPid() {	    return this.pid;	}	public void setPid(Integer pid) {	    this.pid=pid;	}	private String categoryName;//	public String getCategoryName() {	    return this.categoryName;	}	public void setCategoryName(String categoryName) {	    this.categoryName=categoryName;	}	private String categoryNameEq;//	public String getCategoryNameEq() {		return this.categoryNameEq;	}	public void setCategoryNameEq(String categoryNameEq) {		this.categoryNameEq=categoryNameEq;	}	private String pinyin;//	public String getPinyin() {	    return this.pinyin;	}	public void setPinyin(String pinyin) {	    this.pinyin=pinyin;	}	private String categoryType;//	public String getCategoryType() {	    return this.categoryType;	}	public void setCategoryType(String categoryType) {	    this.categoryType=categoryType;	}	private Integer orderby;//	public Integer getOrderby() {	    return this.orderby;	}	public void setOrderby(Integer orderby) {	    this.orderby=orderby;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//0：否   1：已删除	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private String categoryTypeEq;//	public String getCategoryTypeEq() {		return this.categoryTypeEq;	}	public void setCategoryTypeEq(String categoryTypeEq) {		this.categoryTypeEq=categoryTypeEq;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.categoryId!=null)			ht.put("categoryId",this.categoryId);		if(this.pid!=null)			ht.put("pid",this.pid);		if(this.categoryName!=null)			ht.put("categoryName",this.categoryName);		if(this.pinyin!=null)			ht.put("pinyin",this.pinyin);		if(this.categoryType!=null)			ht.put("categoryType",this.categoryType);		if(this.orderby!=null)			ht.put("orderby",this.orderby);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/10/21 17:46:58	/**	 * 分类子集	 */	private List<BsMallItemCategory> children;	public List<BsMallItemCategory> getChildren() {		return children;	}	public void setChildren(List<BsMallItemCategory> children) {		this.children = children;	}}