package com.xfs.corp.model;

import java.util.*;
import java.lang.Integer;import java.lang.String;import java.util.Date;


/**
 * CmCorpTraceRecord
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/25 16:51:17	
 */
public class CmCorpTraceRecord implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private Integer cpId;//	public Integer getCpId() {	    return this.cpId;	}	public void setCpId(Integer cpId) {	    this.cpId=cpId;	}	private String situation;//电话错误：ERROR_PHONE，            挂断：HANG_UP，            拒接：REFUSE，            无人接听：NO_ANSWER，            在忙：BUSYING            接听：ANSWER            	public String getSituation() {	    return this.situation;	}	public void setSituation(String situation) {	    this.situation=situation;	}	private String requirement;//有：Y            无：N	public String getRequirement() {	    return this.requirement;	}	public void setRequirement(String requirement) {	    this.requirement=requirement;	}	private String state;//验证中：VALIDATING            验证通过：PASSED            验证驳回：REFUSED	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
	private String suggestion;
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.cpId!=null)			ht.put("cpId",this.cpId);		if(this.situation!=null)			ht.put("situation",this.situation);		if(this.requirement!=null)			ht.put("requirement",this.requirement);		if(this.state!=null)			ht.put("state",this.state);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/11/25 16:51:17		
}

