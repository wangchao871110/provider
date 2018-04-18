package com.xfs.mall.product.model;import java.math.BigDecimal;import java.util.Date;import java.util.HashMap;/** * SpsMallProduct * @author:duanax * @date:2016/06/20 14:56:21	 */public class SpsMallProduct implements java.io.Serializable {		private static final long serialVersionUID = 1L;		private Integer productId;//	public Integer getProductId() {	    return this.productId;	}	public void setProductId(Integer productId) {	    this.productId=productId;	}	private Integer spId;//	public Integer getSpId() {	    return this.spId;	}	public void setSpId(Integer spId) {	    this.spId=spId;	}	private String productName;//来源表，或者是一组业务表共同字段                        保险字段            任务字段	public String getProductName() {	    return this.productName;	}	public void setProductName(String productName) {	    this.productName=productName;	}	private String productNameEq;//来源表，或者是一组业务表共同字段                        保险字段            任务字段	public String getProductNameEq() {	    return this.productNameEq;	}	public void setProductNameEq(String productNameEq) {	    this.productNameEq=productNameEq;	}	private String areaId;//取当前服务商的area_id	public String getAreaId() {	    return this.areaId;	}	public void setAreaId(String areaId) {	    this.areaId=areaId;	}	private String areaIdEq;//取当前服务商的area_id	public String getAreaIdEq() {	    return this.areaIdEq;	}	public void setAreaIdEq(String areaIdEq) {	    this.areaIdEq=areaIdEq;	}	private String serviceType;//个人、企业	public String getServiceType() {	    return this.serviceType;	}	public void setServiceType(String serviceType) {	    this.serviceType=serviceType;	}	private String serviceTypeEq;//个人、企业	public String getServiceTypeEq() {	    return this.serviceTypeEq;	}	public void setServiceTypeEq(String serviceTypeEq) {	    this.serviceTypeEq=serviceTypeEq;	}	private String perMonth;//0:否  1：是	public String getPerMonth() {	    return this.perMonth;	}	public void setPerMonth(String perMonth) {	    this.perMonth=perMonth;	}	private String perMonthEq;//0:否  1：是	public String getPerMonthEq() {	    return this.perMonthEq;	}	public void setPerMonthEq(String perMonthEq) {	    this.perMonthEq=perMonthEq;	}	private BigDecimal perMonthPrice;//	public BigDecimal getPerMonthPrice() {	    return this.perMonthPrice;	}	public void setPerMonthPrice(BigDecimal perMonthPrice) {	    this.perMonthPrice=perMonthPrice;	}	private String once;//0:否  1：是	public String getOnce() {	    return this.once;	}	public void setOnce(String once) {	    this.once=once;	}	private String onceEq;//0:否  1：是	public String getOnceEq() {	    return this.onceEq;	}	public void setOnceEq(String onceEq) {	    this.onceEq=onceEq;	}	private BigDecimal oncePrice;//	public BigDecimal getOncePrice() {	    return this.oncePrice;	}	public void setOncePrice(BigDecimal oncePrice) {	    this.oncePrice=oncePrice;	}	private String photo;//提供的20张图片选一张	public String getPhoto() {	    return this.photo;	}	public void setPhoto(String photo) {	    this.photo=photo;	}	private String photoEq;//提供的20张图片选一张	public String getPhotoEq() {	    return this.photoEq;	}	public void setPhotoEq(String photoEq) {	    this.photoEq=photoEq;	}	private Integer orderby;//	public Integer getOrderby() {	    return this.orderby;	}	public void setOrderby(Integer orderby) {	    this.orderby=orderby;	}	private String state;//上架：ON  下架：OFF	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	private String stateEq;//上架：ON  下架：OFF	public String getStateEq() {	    return this.stateEq;	}	public void setStateEq(String stateEq) {	    this.stateEq=stateEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private String productDes;//	public String getProductDes() {	    return this.productDes;	}	public void setProductDes(String productDes) {	    this.productDes=productDes;	}	private String productDesEq;//	public String getProductDesEq() {	    return this.productDesEq;	}	public void setProductDesEq(String productDesEq) {	    this.productDesEq=productDesEq;	}		private Integer agentCiId;//	public Integer getAgentCiId() {	    return this.agentCiId;	}	public void setAgentCiId(Integer agentCiId) {	    this.agentCiId=agentCiId;	}	private Integer agentGroupId;//代理商保组合编码	public Integer getAgentGroupId() {	    return this.agentGroupId;	}	public void setAgentGroupId(Integer agentGroupId) {	    this.agentGroupId=agentGroupId;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.productId!=null)			ht.put("productId",this.productId);		if(this.spId!=null)			ht.put("spId",this.spId);		if(this.productName!=null)			ht.put("productName",this.productName);		if(this.productNameEq!=null)			ht.put("productNameEq",this.productNameEq);		if(this.areaId!=null)			ht.put("areaId",this.areaId);		if(this.areaIdEq!=null)			ht.put("areaIdEq",this.areaIdEq);		if(this.serviceType!=null)			ht.put("serviceType",this.serviceType);		if(this.serviceTypeEq!=null)			ht.put("serviceTypeEq",this.serviceTypeEq);		if(this.perMonth!=null)			ht.put("perMonth",this.perMonth);		if(this.perMonthEq!=null)			ht.put("perMonthEq",this.perMonthEq);		if(this.perMonthPrice!=null)			ht.put("perMonthPrice",this.perMonthPrice);		if(this.once!=null)			ht.put("once",this.once);		if(this.onceEq!=null)			ht.put("onceEq",this.onceEq);		if(this.oncePrice!=null)			ht.put("oncePrice",this.oncePrice);		if(this.photo!=null)			ht.put("photo",this.photo);		if(this.photoEq!=null)			ht.put("photoEq",this.photoEq);		if(this.orderby!=null)			ht.put("orderby",this.orderby);		if(this.state!=null)			ht.put("state",this.state);		if(this.stateEq!=null)			ht.put("stateEq",this.stateEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.productDes!=null)			ht.put("productDes",this.productDes);		if(this.productDesEq!=null)			ht.put("productDesEq",this.productDesEq);		if(this.agentCiId!=null)			ht.put("agentCiId",this.agentCiId);		if(this.agentGroupId!=null)			ht.put("agentGroupId",this.agentGroupId);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/06/20 14:56:22		}