package com.xfs.task.dto;

/**
 * 员工流动趋势实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class FlowChartVo {
	private String month;// 月份
	private Integer inNumber;// 在缴人数
	private Integer addNumber;// 增员人数
	private Integer subNumber;// 减员人数
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getInNumber() {
		return inNumber;
	}
	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}
	public Integer getAddNumber() {
		return addNumber;
	}
	public void setAddNumber(Integer addNumber) {
		this.addNumber = addNumber;
	}
	public Integer getSubNumber() {
		return subNumber;
	}
	public void setSubNumber(Integer subNumber) {
		this.subNumber = subNumber;
	}
	
 }
