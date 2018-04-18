package com.xfs.cp.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CpOrderLog
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/11 19:12:26	
 */
public class CpOrderLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private String cpOrderId;//订单ID
	public String getCpOrderId() {
	    return this.cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
	    this.cpOrderId=cpOrderId;
	}
	private Date operationTime;//操作时间
	public Date getOperationTime() {
	    return this.operationTime;
	}
	public void setOperationTime(Date operationTime) {
	    this.operationTime=operationTime;
	}
	private Date operationTimeFrom;//操作时间
	public Date getOperationTimeFrom() {
	    return this.operationTimeFrom;
	}
	public void setOperationTimeFrom(Date operationTimeFrom) {
	    this.operationTimeFrom=operationTimeFrom;
	}
	private Date operationTimeTo;//操作时间
	public Date getOperationTimeTo() {
	    return this.operationTimeTo;
	}
	public void setOperationTimeTo(Date operationTimeTo) {
	    this.operationTimeTo=operationTimeTo;
	}
	private Integer status;//状态
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	private String fileId;//附件ID
	public String getFileId() {
	    return this.fileId;
	}
	public void setFileId(String fileId) {
	    this.fileId=fileId;
	}
	private String fileIdEq;//附件ID
	public String getFileIdEq() {
	    return this.fileIdEq;
	}
	public void setFileIdEq(String fileIdEq) {
	    this.fileIdEq=fileIdEq;
	}
	private Integer createBy;//创建人
	public Integer getCreateBy() {
	    return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
	    this.createBy=createBy;
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
	private Integer stage; //  阶段

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.cpOrderId!=null)
			ht.put("cpOrderId",this.cpOrderId);
		if(this.operationTime!=null)
			ht.put("operationTime",this.operationTime);
		if(this.operationTimeFrom!=null)
			ht.put("operationTimeFrom",this.operationTimeFrom);
		if(this.operationTimeTo!=null)
			ht.put("operationTimeTo",this.operationTimeTo);
		if(this.status!=null)
			ht.put("status",this.status);
		if(this.fileId!=null)
			ht.put("fileId",this.fileId);
		if(this.fileIdEq!=null)
			ht.put("fileIdEq",this.fileIdEq);
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
		if(this.content!=null)
			ht.put("content",this.content);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/11 19:12:26
	private String content;//备注
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

