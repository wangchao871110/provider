package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;

/**
 * SysLogs
 * @author:zhangyan
 */
public class SysLogs implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer logId;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}




	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}




	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userNameEq;

	public String getUserNameEq() {
		return userNameEq;
	}

	public void setUserNameEq(String userName) {
		this.userNameEq = userName;
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



	private Date cdate;

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}




	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	private String ipEq;

	public String getIpEq() {
		return ipEq;
	}

	public void setIpEq(String ip) {
		this.ipEq = ip;
	}



	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private String locationEq;

	public String getLocationEq() {
		return locationEq;
	}

	public void setLocationEq(String location) {
		this.locationEq = location;
	}



	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String typeEq;

	public String getTypeEq() {
		return typeEq;
	}

	public void setTypeEq(String type) {
		this.typeEq = type;
	}



	private Integer functionId;

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
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




	private Date cdateFrom;

	public Date getCdateFrom() {
		return cdateFrom;
	}

	public void setCdateFrom(Date cdateFrom) {
		this.cdateFrom = cdateFrom;
	}

	private Date cdateTo;

	public Date getCdateTo() {
		return cdateTo;
	}

	public void setCdateTo(Date cdateTo) {
		this.cdateTo = cdateTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.logId!=null)
        	ht.put("logId",this.logId);
        if(this.userId!=null)
        	ht.put("userId",this.userId);
        if(this.userName!=null)
        	ht.put("userName",this.userName);
        if(this.userNameEq!=null)
        	ht.put("userNameEq",this.userNameEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.cdate!=null)
        	ht.put("cdate",this.cdate);
        if(this.cdateFrom!=null)
        	ht.put("cdateFrom",this.cdateFrom);
        if(this.cdateTo!=null)
        	ht.put("cdateTo",this.cdateTo);
        if(this.ip!=null)
        	ht.put("ip",this.ip);
        if(this.ipEq!=null)
        	ht.put("ipEq",this.ipEq);
        if(this.location!=null)
        	ht.put("location",this.location);
        if(this.locationEq!=null)
        	ht.put("locationEq",this.locationEq);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.functionId!=null)
        	ht.put("functionId",this.functionId);
        if(this.functionName!=null)
        	ht.put("functionName",this.functionName);
        if(this.functionNameEq!=null)
        	ht.put("functionNameEq",this.functionNameEq);
		return ht;
	}	
	
}
