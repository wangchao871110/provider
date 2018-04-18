package com.xfs.task.dto;

/**
 * 其他业务办理状态实体
 * @author 	: wangchao
 * @date 	: 2017年3月15日 下午2:41:44
 * @version 	: V1.0
 */
public class OtherBusinessVo {
	private String name;// 其他业务名称
	private String stateFiledName;// 状态名称
	private Integer todoNumber;// 代办人数
	private Integer completedNumber;// 成功人数
	private Integer errorNumber;// 异常人数
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateFiledName() {
		return stateFiledName;
	}
	public void setStateFiledName(String stateFiledName) {
		this.stateFiledName = stateFiledName;
	}
	public Integer getTodoNumber() {
		return todoNumber;
	}
	public void setTodoNumber(Integer todoNumber) {
		this.todoNumber = todoNumber;
	}
	public Integer getCompletedNumber() {
		return completedNumber;
	}
	public void setCompletedNumber(Integer completedNumber) {
		this.completedNumber = completedNumber;
	}
	public Integer getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(Integer errorNumber) {
		this.errorNumber = errorNumber;
	}
	
 }
