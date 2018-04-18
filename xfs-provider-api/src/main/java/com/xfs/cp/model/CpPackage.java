package com.xfs.cp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;


/**
 * CpPackage
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/20 10:21:18	
 */
public class CpPackage implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private String packageNum;//包编号
	public String getPackageNum() {
	    return this.packageNum;
	}
	public void setPackageNum(String packageNum) {
	    this.packageNum=packageNum;
	}
	private String packageNumEq;//包编号
	public String getPackageNumEq() {
	    return this.packageNumEq;
	}
	public void setPackageNumEq(String packageNumEq) {
	    this.packageNumEq=packageNumEq;
	}
	private Integer spId;//发包服务商ID
	public Integer getSpId() {
	    return this.spId;
	}
	public void setSpId(Integer spId) {
	    this.spId=spId;
	}
	private String city;//委托城市   多个以逗号隔开
	public String getCity() {
	    return this.city;
	}
	public void setCity(String city) {
	    this.city=city;
	}
	private String cityEq;//委托城市   多个以逗号隔开
	public String getCityEq() {
	    return this.cityEq;
	}
	public void setCityEq(String cityEq) {
	    this.cityEq=cityEq;
	}
	private String cityName;//委托城市名称  多个以逗号隔开
	public String getCityName() {
	    return this.cityName;
	}
	public void setCityName(String cityName) {
	    this.cityName=cityName;
	}
	private String cityNameEq;//委托城市名称  多个以逗号隔开
	public String getCityNameEq() {
	    return this.cityNameEq;
	}
	public void setCityNameEq(String cityNameEq) {
	    this.cityNameEq=cityNameEq;
	}
	private String project;//委托项目   多个以逗号隔开
	public String getProject() {
	    return this.project;
	}
	public void setProject(String project) {
	    this.project=project;
	}
	private String projectEq;//委托项目   多个以逗号隔开
	public String getProjectEq() {
	    return this.projectEq;
	}
	public void setProjectEq(String projectEq) {
	    this.projectEq=projectEq;
	}
	private String projectName;//委托项目名称   多个以逗号隔开
	public String getProjectName() {
	    return this.projectName;
	}
	public void setProjectName(String projectName) {
	    this.projectName=projectName;
	}
	private String projectNameEq;//委托项目名称   多个以逗号隔开
	public String getProjectNameEq() {
	    return this.projectNameEq;
	}
	public void setProjectNameEq(String projectNameEq) {
	    this.projectNameEq=projectNameEq;
	}
	private Integer num;//委托人数
	public Integer getNum() {
	    return this.num;
	}
	public void setNum(Integer num) {
	    this.num=num;
	}
	private Integer customerType;//户类型  0: 大户  1：单立户
	public Integer getCustomerType() {
	    return this.customerType;
	}
	public void setCustomerType(Integer customerType) {
	    this.customerType=customerType;
	}
	private BigDecimal price;//价格
	public BigDecimal getPrice() {
	    return this.price;
	}
	public void setPrice(BigDecimal price) {
	    this.price=price;
	}
	private Integer days;//有效天数
	public Integer getDays() {
	    return this.days;
	}
	public void setDays(Integer days) {
	    this.days=days;
	}
	private Date beginTime;//有效开始时间
	public Date getBeginTime() {
	    return this.beginTime;
	}
	public void setBeginTime(Date beginTime) {
	    this.beginTime=beginTime;
	}
	private Date beginTimeFrom;//有效开始时间
	public Date getBeginTimeFrom() {
	    return this.beginTimeFrom;
	}
	public void setBeginTimeFrom(Date beginTimeFrom) {
	    this.beginTimeFrom=beginTimeFrom;
	}
	private Date beginTimeTo;//有效开始时间
	public Date getBeginTimeTo() {
	    return this.beginTimeTo;
	}
	public void setBeginTimeTo(Date beginTimeTo) {
	    this.beginTimeTo=beginTimeTo;
	}
	private Date endTime;//有效结束时间
	public Date getEndTime() {
	    return this.endTime;
	}
	public void setEndTime(Date endTime) {
	    this.endTime=endTime;
	}
	private Date endTimeFrom;//有效结束时间
	public Date getEndTimeFrom() {
	    return this.endTimeFrom;
	}
	public void setEndTimeFrom(Date endTimeFrom) {
	    this.endTimeFrom=endTimeFrom;
	}
	private Date endTimeTo;//有效结束时间
	public Date getEndTimeTo() {
	    return this.endTimeTo;
	}
	public void setEndTimeTo(Date endTimeTo) {
	    this.endTimeTo=endTimeTo;
	}
	private String content;//补充内容
	public String getContent() {
	    return this.content;
	}
	public void setContent(String content) {
	    this.content=content;
	}
	private String contentEq;//补充内容
	public String getContentEq() {
	    return this.contentEq;
	}
	public void setContentEq(String contentEq) {
	    this.contentEq=contentEq;
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
	private Integer status;//发包状态0：保存，1：发布，2:撤销，3：已建立，4已结束
	public Integer getStatus() {
	    return this.status;
	}
	public void setStatus(Integer status) {
	    this.status=status;
	}
	public void fixup(){
	}
	private String sp_name;//服务商名称
	public String getSp_name() {
		return sp_name;
	}
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
    private String backReson;//撤销原因
    public String getBackReson() {
        return backReson;
    }
    public void setBackReson(String backReson) {
        this.backReson = backReson;
    }

    // add by luyong
    private Integer isRead;

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.packageNum!=null)
			ht.put("packageNum",this.packageNum);
		if(this.packageNumEq!=null)
			ht.put("packageNumEq",this.packageNumEq);
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.city!=null)
			ht.put("city",this.city);
		if(this.cityEq!=null)
			ht.put("cityEq",this.cityEq);
		if(this.cityName!=null)
			ht.put("cityName",this.cityName);
		if(this.cityNameEq!=null)
			ht.put("cityNameEq",this.cityNameEq);
		if(this.project!=null)
			ht.put("project",this.project);
		if(this.projectEq!=null)
			ht.put("projectEq",this.projectEq);
		if(this.projectName!=null)
			ht.put("projectName",this.projectName);
		if(this.projectNameEq!=null)
			ht.put("projectNameEq",this.projectNameEq);
		if(this.num!=null)
			ht.put("num",this.num);
		if(this.customerType!=null)
			ht.put("customerType",this.customerType);
		if(this.price!=null)
			ht.put("price",this.price);
		if(this.days!=null)
			ht.put("days",this.days);
		if(this.beginTime!=null)
			ht.put("beginTime",this.beginTime);
		if(this.beginTimeFrom!=null)
			ht.put("beginTimeFrom",this.beginTimeFrom);
		if(this.beginTimeTo!=null)
			ht.put("beginTimeTo",this.beginTimeTo);
		if(this.endTime!=null)
			ht.put("endTime",this.endTime);
		if(this.endTimeFrom!=null)
			ht.put("endTimeFrom",this.endTimeFrom);
		if(this.endTimeTo!=null)
			ht.put("endTimeTo",this.endTimeTo);
		if(this.content!=null)
			ht.put("content",this.content);
		if(this.contentEq!=null)
			ht.put("contentEq",this.contentEq);
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
		if(this.dateSta!=null)
			ht.put("dateSta",this.dateSta);
		if(this.dateEnd!=null)
			ht.put("dateEnd",this.dateEnd);
		if(this.priceSta!=null)
			ht.put("priceSta",this.priceSta);
		if(this.priceEnd!=null)
			ht.put("priceEnd",this.priceEnd);
		if(this.orderBy!=null)
			ht.put("orderBy",this.orderBy);
		if(this.areaPinyin!=null)
			ht.put("areaPinyin",this.areaPinyin);
		if(this.dataDr!=null)
			ht.put("dataDr",this.dataDr);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/20 10:21:18
	private Integer dateSta;
	private Integer dateEnd;
	private Integer priceSta;
	private Integer priceEnd;
	private String spName;// 服务商名称
	private Integer isca;// 企业是否认证 0：未认证 1：认证
	private Integer serviceLogo;//服务商logo          
	public Integer getServiceLogo() {
	    return this.serviceLogo;
	}
	public void setServiceLogo(Integer serviceLogo) {
	    this.serviceLogo=serviceLogo;
	}
	public Integer getIsca() {
	    return this.isca;
	}
	public void setIsca(Integer isca) {
	    this.isca=isca;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Integer getDateSta() {
		return dateSta;
	}
	public void setDateSta(Integer dateSta) {
		this.dateSta = dateSta;
	}
	public Integer getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Integer dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Integer getPriceSta() {
		return priceSta;
	}
	public void setPriceSta(Integer priceSta) {
		this.priceSta = priceSta;
	}
	public Integer getPriceEnd() {
		return priceEnd;
	}
	public void setPriceEnd(Integer priceEnd) {
		this.priceEnd = priceEnd;
	}

	//display setter
	private String timeDisplay; //我的发包列表 时间状态显示
	private Integer quotedCount; //我的发包列表 抢单数
	private Integer projectCount;//我的发包列表 项目数
	public String getTimeDisplay() {
		return timeDisplay;
	}

	public void setTimeDisplay(String timeDisplay) {
		this.timeDisplay = timeDisplay;
	}

	public Integer getQuotedCount() {
		return quotedCount;
	}

	public void setQuotedCount(Integer quotedCount) {
		this.quotedCount = quotedCount;
	}

	public Integer getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}
	public String orderBy;// 排序字段
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	private String areaPinyin;// 区域拼音
	public String getAreaPinyin() {
		return areaPinyin;
	}
	public void setAreaPinyin(String areaPinyin) {
		this.areaPinyin = areaPinyin;
	}
	
	private String quotedSpName;// 已合作服务商（抢包成功的服务商名）
	public String getQuotedSpName() {
		return quotedSpName;
	}
	public void setQuotedSpName(String quotedSpName) {
		this.quotedSpName = quotedSpName;
	}
	private Integer dataDr;//数据逻辑删除标记位0:未删除1:已删除
	public Integer getDataDr() {
		return dataDr;
	}
	public void setDataDr(Integer dataDr) {
		this.dataDr = dataDr;
	}
	
	private BigDecimal everyonePrice;//服务费每人
	private Date startMonth;//启动月份
	public BigDecimal getEveryonePrice() {
		return everyonePrice;
	}
	public void setEveryonePrice(BigDecimal everyonePrice) {
		this.everyonePrice = everyonePrice;
	}
	public Date getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Date startMonth) {
		this.startMonth = startMonth;
	}
	
}

