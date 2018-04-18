package com.xfs.task.dto;

import java.util.HashMap;

/**
 * 
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午10:10:58
 * @version 	: V1.0
 */
public class HandlePersonnelDto{
	private Integer cpId;// 企业ID
	private Integer cityId;// 城市ID
	private Integer stateFiledId;//办理状态
	private String name;// 姓名或者证件号码
	private String countMonth;//社保公积金统计月份
	private String bstype;//申报业务
	private String insuranceFundType;//保险类型  INSURANCE
	private String queryMonth;// 查询时间

	public String getCountMonth() {
		return countMonth;
	}

	public void setCountMonth(String countMonth) {
		this.countMonth = countMonth;
	}
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStateFiledId() {
		return stateFiledId;
	}
	public void setStateFiledId(Integer stateFiledId) {
		this.stateFiledId = stateFiledId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.cityId!=null)
			ht.put("cityId",this.cityId);
		if(this.stateFiledId!=null)
			ht.put("stateFiledId",this.stateFiledId);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.authority!=null)
			ht.put("authority",this.authority);
		if(this.queryMonth!=null)
			ht.put("queryMonth",this.queryMonth);
		if(this.bstype!=null)
			ht.put("bstype",this.bstype);
		if(this.countMonth!=null)
			ht.put("countMonth",this.countMonth);
		return ht;
	}
	// 数据权限
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getBstype() {
		return bstype;
	}

	public void setBstype(String bstype) {
		this.bstype = bstype;
	}

	public String getInsuranceFundType() {
		return insuranceFundType;
	}

	public void setInsuranceFundType(String insuranceFundType) {
		this.insuranceFundType = insuranceFundType;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}
	
}
