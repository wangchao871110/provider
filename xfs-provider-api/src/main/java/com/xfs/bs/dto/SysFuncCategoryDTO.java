package com.xfs.bs.dto;

import java.util.List;

import com.xfs.user.model.SysFunction;

/**
 * rolecontroller与前台交互dto
 * @author han
 *
 */
public class SysFuncCategoryDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 类别名称
	 */
	private String topMenuName;
	/**
	 * 类别下功能列表
	 */
	private List<SysFunction> funcList;
	public String getTopMenuName() {
		return topMenuName;
	}
	public void setTopMenuName(String topMenuName) {
		this.topMenuName = topMenuName;
	}
	public List<SysFunction> getFuncList() {
		return funcList;
	}
	public void setFuncList(List<SysFunction> funcList) {
		this.funcList = funcList;
	}
}
