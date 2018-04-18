package com.xfs.task.dto;

/**
 * 事项提醒实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:42:33
 * @version 	: V1.0
 */
public class ItemWarnVo {
	private Integer endDate;// 增员日
	private String name;// 名称
	private String item;// 事项
	
	public Integer getEndDate() {
		return endDate;
	}
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
}
