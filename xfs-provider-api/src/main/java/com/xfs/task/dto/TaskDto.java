package com.xfs.task.dto;

import java.util.HashMap;

/**
 * 
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午10:10:58
 * @version 	: V1.0
 */
public class TaskDto{

	private String countMonth;//社保公积金统计月份
	
	public String getCountMonth() {
		return countMonth;
	}
	public void setCountMonth(String countMonth) {
		this.countMonth = countMonth;
	}
	private Integer cpId;// 企业ID
	public Integer getCpId() {
	    return this.cpId;
	}
	public void setCpId(Integer cpId) {
	    this.cpId=cpId;
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.countMonth!=null)
			ht.put("countMonth",this.countMonth);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
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
