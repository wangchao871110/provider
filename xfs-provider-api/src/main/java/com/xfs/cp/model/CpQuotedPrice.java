package com.xfs.cp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * CpQuotedPrice
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/20 09:49:54	
 */
public class CpQuotedPrice implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private Integer sendSpid;//发包服务商ID
	public Integer getSendSpid() {
	    return this.sendSpid;
	}
	public void setSendSpid(Integer sendSpid) {
	    this.sendSpid=sendSpid;
	}
	private Integer packageId;//包ID
	public Integer getPackageId() {
	    return this.packageId;
	}
	public void setPackageId(Integer packageId) {
	    this.packageId=packageId;
	}
	private Integer quotedSpid;//报价服务商ID
	public Integer getQuotedSpid() {
	    return this.quotedSpid;
	}
	public void setQuotedSpid(Integer quotedSpid) {
	    this.quotedSpid=quotedSpid;
	}
	private BigDecimal price;//价格
	public BigDecimal getPrice() {
	    return this.price;
	}
	public void setPrice(BigDecimal price) {
	    this.price=price;
	}
	private Date quotedTime;//报价时间
	public Date getQuotedTime() {
	    return this.quotedTime;
	}
	public void setQuotedTime(Date quotedTime) {
	    this.quotedTime=quotedTime;
	}
	private Date quotedTimeFrom;//报价时间
	public Date getQuotedTimeFrom() {
	    return this.quotedTimeFrom;
	}
	public void setQuotedTimeFrom(Date quotedTimeFrom) {
	    this.quotedTimeFrom=quotedTimeFrom;
	}
	private Date quotedTimeTo;//报价时间
	public Date getQuotedTimeTo() {
	    return this.quotedTimeTo;
	}
	public void setQuotedTimeTo(Date quotedTimeTo) {
	    this.quotedTimeTo=quotedTimeTo;
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
	private Integer status;//抢单状态0：等待，1：建立合作，2：放弃，3：拒绝
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	private String statusQuery;//抢单状态0：等待，1：建立合作，2：放弃，3：拒绝
	public String getStatusQuery() {
		return statusQuery;
	}
	public void setStatusQuery(String statusQuery) {
		this.statusQuery = statusQuery;
	}
    private Integer isRead ;

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.sendSpid!=null)
			ht.put("sendSpid",this.sendSpid);
		if(this.packageId!=null)
			ht.put("packageId",this.packageId);
		if(this.quotedSpid!=null)
			ht.put("quotedSpid",this.quotedSpid);
		if(this.price!=null)
			ht.put("price",this.price);
		if(this.quotedTime!=null)
			ht.put("quotedTime",this.quotedTime);
		if(this.quotedTimeFrom!=null)
			ht.put("quotedTimeFrom",this.quotedTimeFrom);
		if(this.quotedTimeTo!=null)
			ht.put("quotedTimeTo",this.quotedTimeTo);
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
		if(this.packageNum!=null)
			ht.put("packageNum",this.packageNum);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.dataDr!=null)
			ht.put("dataDr",this.dataDr);
		return ht;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/20 09:49:54
	//display setter
	private String packageNum;//包编号
	private String project;//委托项目 多个以逗号隔开
	private Integer num;//委托人数
	private BigDecimal packagePrice;//价格
	private Integer packageStatus;
	private String sendSpName;
	private String quotedSpName;
	private Date beginTime;//有效开始时间
	private Integer projectCount; //项目数
	private String timeDisplay; //时间状态显示
	private Date establishedTime;//成立时间
	private String registeredCapital;//注册资本
	private Integer relationId; //关联关系Id
	private Date endTime;//有效结束时间
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPackageNum() {return packageNum;}
	public void setPackageNum(String packageNum) {this.packageNum = packageNum;}
	public String getProject() {return project;}
	public void setProject(String project) {this.project = project;}
	public Integer getNum() {return num;}
	public void setNum(Integer num) {this.num = num;}
	public BigDecimal getPackagePrice() {return packagePrice;}
	public void setPackagePrice(BigDecimal packagePrice) {this.packagePrice = packagePrice;}
	public Integer getPackageStatus() {return packageStatus;}
	public void setPackageStatus(Integer packageStatus) {this.packageStatus = packageStatus;}
	public String getSendSpName() {return sendSpName;}
	public void setSendSpName(String sendSpName) {this.sendSpName = sendSpName;}
	public Date getBeginTime() {return beginTime;}
	public void setBeginTime(Date beginTime) {this.beginTime = beginTime;}
	public Integer getProjectCount() {return projectCount;}
	public void setProjectCount(Integer projectCount) {this.projectCount = projectCount;}
	public String getTimeDisplay() {return timeDisplay;}
	public void setTimeDisplay(String timeDisplay) {this.timeDisplay = timeDisplay;}
	public String getRegisteredCapital() {return registeredCapital;}
	public void setRegisteredCapital(String registeredCapital) {this.registeredCapital = registeredCapital;}
	public Date getEstablishedTime() {return establishedTime;}
	public String getQuotedSpName() {return quotedSpName;}
	public void setQuotedSpName(String quotedSpName) {this.quotedSpName = quotedSpName;}
	public void setEstablishedTime(Date establishedTime) {this.establishedTime = establishedTime;}
	public Integer getRelationId() {return relationId;}
	public void setRelationId(Integer relationId) {this.relationId = relationId;}
	public Date getEndTime() {return endTime;}
	public void setEndTime(Date endTime) {this.endTime = endTime;}
	
	private Double devIndex;//发展指数            
	public Double getDevIndex() {
	    return this.devIndex;
	}
	public void setDevIndex(Double devIndex) {
	    this.devIndex=devIndex;
	}
	private Double riskMargin;//保证金      
	public Double getRiskMargin() {
		return riskMargin;
	}
	public void setRiskMargin(Double riskMargin) {
		this.riskMargin = riskMargin;
	}
	private Integer isca;// 企业是否认证 0：未认证 1：认证
	public Integer getIsca() {
		return isca;
	}
	public void setIsca(Integer isca) {
		this.isca = isca;
	}
	private Integer dataDr;//数据逻辑删除标记位0:未删除1:已删除
	public Integer getDataDr() {
		return dataDr;
	}
	public void setDataDr(Integer dataDr) {
		this.dataDr = dataDr;
	}
	
}

