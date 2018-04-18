package com.xfs.task.dto;

/**
 * 社保公积金看板数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:42:33
 * @version 	: V1.0
 */
public class TaskMapVo {
	private String cityName;// 城市名称
	private Integer peopleNumber;// 人数
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
}
