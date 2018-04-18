package com.xfs.ts.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class SpsTsOpresult {
	
	private Integer id;
	
	private Date createDt;
	
	private Date updateDt;
	
	//服务商手机号
	private String mobile = null;
	
	//登录的token
	private String token = null;
	
	//操作的人员身份证号
	private String idCode = null;
	
	//操作的人员姓名
	private String psnName = null;
	
	//操作结果，成功，失败
	private String opStatus  = null;
	
	//工资
	private BigDecimal salary = null;
	
	//操作类型，新参，转入等
	private Integer opType;
	
	//户口类型
	private String liveType = null;
	
	//其他信息
	private String  otherInfo = null;
	
	//操作截图
	private Integer imgId ;
	
	//操作月份
	private String month =null;
	
	//方案ID
	private String scid;
	
	//服务商ID
	private Integer spid;
	
	//公司ID
	private Integer cid;
	
	//是否是最后一条
	private String  isLastFlag;
	
	private String json;
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getPsnName() {
		return psnName;
	}

	public void setPsnName(String psnName) {
		this.psnName = psnName;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}


	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}


	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}



	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public Integer getSpid() {
		return spid;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getIsLastFlag() {
		return isLastFlag;
	}

	public void setIsLastFlag(String isLastFlag) {
		this.isLastFlag = isLastFlag;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getLiveType() {
		return liveType;
	}

	public void setLiveType(String liveType) {
		this.liveType = liveType;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void fixup(){
	}

	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.createDt!=null)
			ht.put("createDt",this.createDt);
		if(this.updateDt!=null)
			ht.put("nameEq",this.updateDt);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.token!=null)
			ht.put("token",this.token);
		if(this.idCode!=null)
			ht.put("idCode",this.idCode);

		if(this.psnName!=null)
			ht.put("btype",this.psnName);

		if(this.opStatus!=null)
			ht.put("opStatus",this.opStatus);
		if(this.salary!=null)
			ht.put("code",this.salary);
		if(this.opType!=null)
			ht.put("opType",this.opType);
		if(this.liveType!=null)
			ht.put("className",this.liveType);

		if(this.otherInfo!=null)
			ht.put("otherInfo",this.otherInfo);
		if(this.imgId!=null)
			ht.put("imgId",this.imgId);
		if(this.month!=null)
			ht.put("createDt",this.month);

		if(this.spid!=null)
			ht.put("spid",this.spid);
		if(this.cid!=null)
			ht.put("cid",this.cid);
		if(this.isLastFlag!=null)
			ht.put("isLastFlag",this.isLastFlag);
		if(this.json!=null)
			ht.put("json",this.json);


		return ht;
	}

}
