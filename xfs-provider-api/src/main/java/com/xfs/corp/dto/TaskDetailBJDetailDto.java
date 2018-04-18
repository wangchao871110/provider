package com.xfs.corp.dto;

import java.util.List;
import java.util.Map;

/** 
* @author xiyanzhang
* @Email: zhangxiyan@xinfushe.com
* @version 创建时间：2016年12月9日 下午6:12:49 
*/
public class TaskDetailBJDetailDto {
	//缴纳月份
	private String bjMonth;
	//补缴办理月
	private String bjDealMonth;
	//补缴
	private  List<Map<String, Object>> bjMonthDetail;
	public String getBjMonth() {
		return bjMonth;
	}
	public void setBjMonth(String bjMonth) {
		this.bjMonth = bjMonth;
	}
	public String getBjDealMonth() {
		return bjDealMonth;
	}
	public void setBjDealMonth(String bjDealMonth) {
		this.bjDealMonth = bjDealMonth;
	}
	public List<Map<String, Object>> getBjMonthDetail() {
		return bjMonthDetail;
	}
	public void setBjMonthDetail(List<Map<String, Object>> bjMonthDetail) {
		this.bjMonthDetail = bjMonthDetail;
	}
	
	
}
