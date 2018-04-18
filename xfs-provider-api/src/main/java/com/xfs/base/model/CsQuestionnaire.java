package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;


/**
 * CsQuestionnaire
 * @author:wangc
 * @date:2016/07/28 10:52:51	
 */
public class CsQuestionnaire implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer userId;//回答人ID	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	private String answer;//答案	public String getAnswer() {	    return this.answer;	}	public void setAnswer(String answer) {	    this.answer=answer;	}	private String answerEq;//答案	public String getAnswerEq() {	    return this.answerEq;	}	public void setAnswerEq(String answerEq) {	    this.answerEq=answerEq;	}	private Integer createBy;//创建人	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//创建时间	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//创建时间	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//创建时间	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//更新人	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//更新时间	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//更新时间	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//更新时间	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}
	private Integer orgId;
		public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.userId!=null)			ht.put("userId",this.userId);		if(this.answer!=null)			ht.put("answer",this.answer);		if(this.answerEq!=null)			ht.put("answerEq",this.answerEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);
		if(this.orgId!=null)
			ht.put("orgId",this.orgId);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/07/28 10:52:51
	private Integer registHistoryId;
	public Integer getRegistHistoryId() {
		return registHistoryId;
	}
	public void setRegistHistoryId(Integer registHistoryId) {
		this.registHistoryId = registHistoryId;
	}
	
	
}

