package com.xfs.cp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CpNote
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/13 15:14:40	
 */
public class CpNote implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer spIdBy;//被纠错服务商ID，留言不需要
	public Integer getSpIdBy() {
	    return this.spIdBy;
	}
	public void setSpIdBy(Integer spIdBy) {
	    this.spIdBy=spIdBy;
	}
	private String mobile;//手机号
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private Integer spId;//纠错服务商ID，留言不需要
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private String content;//内容
	public String getContent() {
	    return this.content;
	}
	public void setContent(String content) {
	    this.content=content;
	}
	private String contentEq;//内容
	public String getContentEq() {
	    return this.contentEq;
	}
	public void setContentEq(String contentEq) {
	    this.contentEq=contentEq;
	}
	private String img;//图片 多个以逗号隔开
	public String getImg() {
	    return this.img;
	}
	public void setImg(String img) {
	    this.img=img;
	}
	private String imgEq;//图片 多个以逗号隔开
	public String getImgEq() {
	    return this.imgEq;
	}
	public void setImgEq(String imgEq) {
	    this.imgEq=imgEq;
	}
	private Integer type;//类型0：纠错  1：留言
	public Integer getType() {
	    return this.type;
	}
	public void setType(Integer type) {
	    this.type=type;
	}
	private Integer createBy;//创建人
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
	}
	private Date beginTime;//开始时间
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	private Date endTime;//结束时间
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	private Date createDt;//创建时间
	public Date getCreateDt() {
	    return this.createDt;
	}
	public void setCreateDt(Date createDt) {
	    this.createDt=createDt;
	}
	private Date createDtFrom;//创建时间
	public Date getCreateDtFrom() {
	    return this.createDtFrom;
	}
	public void setCreateDtFrom(Date createDtFrom) {
	    this.createDtFrom=createDtFrom;
	}
	private Date createDtTo;//创建时间
	public Date getCreateDtTo() {
	    return this.createDtTo;
	}
	public void setCreateDtTo(Date createDtTo) {
	    this.createDtTo=createDtTo;
	}
	private Integer modifyBy;//更新人
	public Integer getModifyBy() {
	    return this.modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
	    this.modifyBy=modifyBy;
	}
	private Date modifyDt;//更新时间
	public Date getModifyDt() {
	    return this.modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
	    this.modifyDt=modifyDt;
	}
	private Date modifyDtFrom;//更新时间
	public Date getModifyDtFrom() {
	    return this.modifyDtFrom;
	}
	public void setModifyDtFrom(Date modifyDtFrom) {
	    this.modifyDtFrom=modifyDtFrom;
	}
	private Date modifyDtTo;//更新时间
	public Date getModifyDtTo() {
	    return this.modifyDtTo;
	}
	public void setModifyDtTo(Date modifyDtTo) {
	    this.modifyDtTo=modifyDtTo;
	}
	private Integer dr;//逻辑删除标记位0:未删除1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	public void fixup(){
	}
	
	private Integer status;//是否处理
	
	private String backContent;//回复内容
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBackContent() {
		return backContent;
	}
	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.spIdBy!=null)
			ht.put("spIdBy",this.spIdBy);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.content!=null)
			ht.put("content",this.content);
		if(this.contentEq!=null)
			ht.put("contentEq",this.contentEq);
		if(this.img!=null)
			ht.put("img",this.img);
		if(this.imgEq!=null)
			ht.put("imgEq",this.imgEq);
		if(this.type!=null)
			ht.put("type",this.type);
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
		if(this.status!=null)
			ht.put("status",this.status);
		if(this.backContent!=null)
			ht.put("backContent",this.backContent);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/13 15:14:41
	
	
}

