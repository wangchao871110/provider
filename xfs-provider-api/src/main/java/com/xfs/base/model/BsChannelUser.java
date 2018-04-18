package com.xfs.base.model;

import java.util.Date;
import java.util.HashMap;



/**
 * BsChannelUser
 * @author:wangchao
 * @date:2016/07/26 17:19:35	
 */
public class BsChannelUser implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	private String name;//员工名称
	public String getName() {
	    return this.name;
	}
	public void setName(String name) {
	    this.name=name;
	}
	private String nameEq;//员工名称
	public String getNameEq() {
	    return this.nameEq;
	}
	public void setNameEq(String nameEq) {
	    this.nameEq=nameEq;
	}
	private String code;//员工代码
	public String getCode() {
	    return this.code;
	}
	public void setCode(String code) {
	    this.code=code;
	}
	private String codeEq;//员工代码
	public String getCodeEq() {
	    return this.codeEq;
	}
	public void setCodeEq(String codeEq) {
	    this.codeEq=codeEq;
	}
	private String mobile;//手机号
	public String getMobile() {
	    return this.mobile;
	}
	public void setMobile(String mobile) {
	    this.mobile=mobile;
	}
	private String mobileEq;//手机号
	public String getMobileEq() {
	    return this.mobileEq;
	}
	public void setMobileEq(String mobileEq) {
	    this.mobileEq=mobileEq;
	}
	private String position;//职位
	public String getPosition() {
	    return this.position;
	}
	public void setPosition(String position) {
	    this.position=position;
	}
	private String positionEq;//职位
	public String getPositionEq() {
	    return this.positionEq;
	}
	public void setPositionEq(String positionEq) {
	    this.positionEq=positionEq;
	}
	private String status;//0：未启用，1：启用
	public String getStatus() {
	    return this.status;
	}
	public void setStatus(String status) {
	    this.status=status;
	}
	private String statusEq;//0：未启用，1：启用
	public String getStatusEq() {
	    return this.statusEq;
	}
	public void setStatusEq(String statusEq) {
	    this.statusEq=statusEq;
	}
	private Date startDate;//启用日期
	public Date getStartDate() {
	    return this.startDate;
	}
	public void setStartDate(Date startDate) {
	    this.startDate=startDate;
	}
	private Date startDateFrom;//启用日期
	public Date getStartDateFrom() {
	    return this.startDateFrom;
	}
	public void setStartDateFrom(Date startDateFrom) {
	    this.startDateFrom=startDateFrom;
	}
	private Date startDateTo;//启用日期
	public Date getStartDateTo() {
	    return this.startDateTo;
	}
	public void setStartDateTo(Date startDateTo) {
	    this.startDateTo=startDateTo;
	}
	private Date endDate;//停用日期
	public Date getEndDate() {
	    return this.endDate;
	}
	public void setEndDate(Date endDate) {
	    this.endDate=endDate;
	}
	private Date endDateFrom;//停用日期
	public Date getEndDateFrom() {
	    return this.endDateFrom;
	}
	public void setEndDateFrom(Date endDateFrom) {
	    this.endDateFrom=endDateFrom;
	}
	private Date endDateTo;//停用日期
	public Date getEndDateTo() {
	    return this.endDateTo;
	}
	public void setEndDateTo(Date endDateTo) {
	    this.endDateTo=endDateTo;
	}
	private String area;//区域名称
	public String getArea() {
	    return this.area;
	}
	public void setArea(String area) {
	    this.area=area;
	}
	private String areaEq;//区域名称
	public String getAreaEq() {
	    return this.areaEq;
	}
	public void setAreaEq(String areaEq) {
	    this.areaEq=areaEq;
	}
	private String groups;//组名称
	public String getGroups() {
	    return this.groups;
	}
	public void setGroups(String groups) {
	    this.groups=groups;
	}
	private String groupsEq;//组名称
	public String getGroupsEq() {
	    return this.groupsEq;
	}
	public void setGroupsEq(String groupsEq) {
	    this.groupsEq=groupsEq;
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
	private Integer dr;//逻辑删除标记位 0:未删除 1:已删除
	public Integer getDr() {
	    return this.dr;
	}
	public void setDr(Integer dr) {
	    this.dr=dr;
	}
	private Integer channelId;//渠道ID
	public Integer getChannelId() {
	    return this.channelId;
	}
	public void setChannelId(Integer channelId) {
	    this.channelId=channelId;
	}
	private String userStatus;//0：注册，1：已认证，2：已问卷
	public String getUserStatus() {
	    return this.userStatus;
	}
	public void setUserStatus(String userStatus) {
	    this.userStatus=userStatus;
	}
	private String userStatusEq;//0：注册，1：已认证，2：已问卷
	public String getUserStatusEq() {
	    return this.userStatusEq;
	}
	public void setUserStatusEq(String userStatusEq) {
	    this.userStatusEq=userStatusEq;
	}
	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.code!=null)
			ht.put("code",this.code);
		if(this.codeEq!=null)
			ht.put("codeEq",this.codeEq);
		if(this.mobile!=null)
			ht.put("mobile",this.mobile);
		if(this.mobileEq!=null)
			ht.put("mobileEq",this.mobileEq);
		if(this.position!=null)
			ht.put("position",this.position);
		if(this.positionEq!=null)
			ht.put("positionEq",this.positionEq);
		if(this.status!=null)
			ht.put("status",this.status);
		if(this.statusEq!=null)
			ht.put("statusEq",this.statusEq);
		if(this.startDate!=null)
			ht.put("startDate",this.startDate);
		if(this.startDateFrom!=null)
			ht.put("startDateFrom",this.startDateFrom);
		if(this.startDateTo!=null)
			ht.put("startDateTo",this.startDateTo);
		if(this.endDate!=null)
			ht.put("endDate",this.endDate);
		if(this.endDateFrom!=null)
			ht.put("endDateFrom",this.endDateFrom);
		if(this.endDateTo!=null)
			ht.put("endDateTo",this.endDateTo);
		if(this.area!=null)
			ht.put("area",this.area);
		if(this.areaEq!=null)
			ht.put("areaEq",this.areaEq);
		if(this.groups!=null)
			ht.put("groups",this.groups);
		if(this.groupsEq!=null)
			ht.put("groupsEq",this.groupsEq);
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
		if(this.channelId!=null)
			ht.put("channelId",this.channelId);
		if(this.userStatus!=null)
			ht.put("userStatus",this.userStatus);
		if(this.userStatusEq!=null)
			ht.put("userStatusEq",this.userStatusEq);
		if(this.areaName!=null)
			ht.put("areaName",this.areaName);
		if(this.groupsName!=null)
			ht.put("groupsName",this.groupsName);
		return ht; 
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 17:19:36
	
	// 隐藏状态，用于判断状态是否改变
	public String hiddenStatus;
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	
	// 查询使用
	public String searchName;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	// 区域名称
	public String areaName;
	public void setAreaName(String  areaName) {
		this.areaName=areaName;
	}
	public String getAreaName() {
		return this.areaName;
	}
	// 分组名称
	public String groupsName;
	public void setGroupsName(String  groupsName) {
		this.groupsName=groupsName;
	}
	public String getGroupsName() {
		return this.groupsName;
	}
	
	public String openId;// 微信openID
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}

