package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;

/**
 * SysNotification
 * @author:Lusir<lusire@gmail.com>
 */
public class SysNotification implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	private String functionName;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	private String functionNameEq;

	public String getFunctionNameEq() {
		return functionNameEq;
	}

	public void setFunctionNameEq(String functionName) {
		this.functionNameEq = functionName;
	}



	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String descriptionEq;

	public String getDescriptionEq() {
		return descriptionEq;
	}

	public void setDescriptionEq(String description) {
		this.descriptionEq = description;
	}



	private Date createDt;

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}




	private String isProceed;

	public String getIsProceed() {
		return isProceed;
	}

	public void setIsProceed(String isProceed) {
		this.isProceed = isProceed;
	}




	private String processUserName;

	public String getProcessUserName() {
		return processUserName;
	}

	public void setProcessUserName(String processUserName) {
		this.processUserName = processUserName;
	}

	private String processUserNameEq;

	public String getProcessUserNameEq() {
		return processUserNameEq;
	}

	public void setProcessUserNameEq(String processUserName) {
		this.processUserNameEq = processUserName;
	}



	private Integer processUserId;

	public Integer getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(Integer processUserId) {
		this.processUserId = processUserId;
	}





	private Date createDtFrom;

	public Date getCreateDtFrom() {
		return createDtFrom;
	}

	public void setCreateDtFrom(Date createDtFrom) {
		this.createDtFrom = createDtFrom;
	}

	private Date createDtTo;

	public Date getCreateDtTo() {
		return createDtTo;
	}

	public void setCreateDtTo(Date createDtTo) {
		this.createDtTo = createDtTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.functionName!=null)
        	ht.put("functionName",this.functionName);
        if(this.functionNameEq!=null)
        	ht.put("functionNameEq",this.functionNameEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.isProceed!=null)
        	ht.put("isProceed",this.isProceed);
        if(this.processUserName!=null)
        	ht.put("processUserName",this.processUserName);
        if(this.processUserNameEq!=null)
        	ht.put("processUserNameEq",this.processUserNameEq);
        if(this.processUserId!=null)
        	ht.put("processUserId",this.processUserId);
		return ht;
	}	
	
}
