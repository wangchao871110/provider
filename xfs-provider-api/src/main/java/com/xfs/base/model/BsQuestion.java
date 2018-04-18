package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;


/**
 * BsQuestion
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 14:33:27	
 */
public class BsQuestion implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;		private Integer id;//	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	private String questionName;//	public String getQuestionName() {	    return this.questionName;	}	public void setQuestionName(String questionName) {	    this.questionName=questionName;	}	private String questionNameEq;//	public String getQuestionNameEq() {	    return this.questionNameEq;	}	public void setQuestionNameEq(String questionNameEq) {	    this.questionNameEq=questionNameEq;	}	private String state;//	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	private String stateEq;//	public String getStateEq() {	    return this.stateEq;	}	public void setStateEq(String stateEq) {	    this.stateEq=stateEq;	}	private Integer createBy;//	public Integer getCreateBy() {	    return this.createBy;	}	public void setCreateBy(Integer createBy) {	    this.createBy=createBy;	}	private Date createDt;//	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	private Date createDtFrom;//	public Date getCreateDtFrom() {	    return this.createDtFrom;	}	public void setCreateDtFrom(Date createDtFrom) {	    this.createDtFrom=createDtFrom;	}	private Date createDtTo;//	public Date getCreateDtTo() {	    return this.createDtTo;	}	public void setCreateDtTo(Date createDtTo) {	    this.createDtTo=createDtTo;	}	private Integer modifyBy;//	public Integer getModifyBy() {	    return this.modifyBy;	}	public void setModifyBy(Integer modifyBy) {	    this.modifyBy=modifyBy;	}	private Date modifyDt;//	public Date getModifyDt() {	    return this.modifyDt;	}	public void setModifyDt(Date modifyDt) {	    this.modifyDt=modifyDt;	}	private Date modifyDtFrom;//	public Date getModifyDtFrom() {	    return this.modifyDtFrom;	}	public void setModifyDtFrom(Date modifyDtFrom) {	    this.modifyDtFrom=modifyDtFrom;	}	private Date modifyDtTo;//	public Date getModifyDtTo() {	    return this.modifyDtTo;	}	public void setModifyDtTo(Date modifyDtTo) {	    this.modifyDtTo=modifyDtTo;	}	private Integer dr;//	public Integer getDr() {	    return this.dr;	}	public void setDr(Integer dr) {	    this.dr=dr;	}	private Integer sortNumber;//	public Integer getSortNumber() {	    return this.sortNumber;	}	public void setSortNumber(Integer sortNumber) {	    this.sortNumber=sortNumber;	}
	private String selectState;
	private String isInput;
	public String getSelectState() {
		return selectState;
	}
	public void setSelectState(String selectState) {
		this.selectState = selectState;
	}
	public String getIsInput() {
		return isInput;
	}
	public void setIsInput(String isInput) {
		this.isInput = isInput;
	}
	public void fixup(){	}	public HashMap<String,Object> toHashMap() {		HashMap<String,Object> ht = new HashMap<String,Object>();		if(this.id!=null)			ht.put("id",this.id);		if(this.questionName!=null)			ht.put("questionName",this.questionName);		if(this.questionNameEq!=null)			ht.put("questionNameEq",this.questionNameEq);		if(this.state!=null)			ht.put("state",this.state);		if(this.stateEq!=null)			ht.put("stateEq",this.stateEq);		if(this.createBy!=null)			ht.put("createBy",this.createBy);		if(this.createDt!=null)			ht.put("createDt",this.createDt);		if(this.createDtFrom!=null)			ht.put("createDtFrom",this.createDtFrom);		if(this.createDtTo!=null)			ht.put("createDtTo",this.createDtTo);		if(this.modifyBy!=null)			ht.put("modifyBy",this.modifyBy);		if(this.modifyDt!=null)			ht.put("modifyDt",this.modifyDt);		if(this.modifyDtFrom!=null)			ht.put("modifyDtFrom",this.modifyDtFrom);		if(this.modifyDtTo!=null)			ht.put("modifyDtTo",this.modifyDtTo);		if(this.dr!=null)			ht.put("dr",this.dr);		if(this.sortNumber!=null)			ht.put("sortNumber",this.sortNumber);		if(this.sortNumber!=null)			ht.put("selectState",this.selectState);		if(this.sortNumber!=null)			ht.put("isInput",this.isInput);		return ht; 	}		//温馨提示：	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写	//2016/08/23 14:33:27		
}

