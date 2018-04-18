package com.xfs.task.dto;

import java.util.HashMap;

/**
 * 
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午10:10:58
 * @version 	: V1.0
 */
public class FlowChartDto{

	private Integer cityId;// 城市ID
	private Integer cpId;// 企业ID
	private String beginPeriod;// 开始月份
	private String endPeriod;// 结束月份
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	
	public String getBeginPeriod() {
		return beginPeriod;
	}
	public void setBeginPeriod(String beginPeriod) {
		this.beginPeriod = beginPeriod;
	}
	public String getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.cityId!=null)
			ht.put("cityId",this.cityId);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.beginPeriod!=null)
			ht.put("beginPeriod",this.beginPeriod);
		if(this.endPeriod!=null)
			ht.put("endPeriod",this.endPeriod);
		if(this.authority!=null)
			ht.put("authority",this.authority);
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

}
