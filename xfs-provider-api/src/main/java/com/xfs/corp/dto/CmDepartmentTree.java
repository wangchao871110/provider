package com.xfs.corp.dto;import java.util.List;import com.xfs.corp.model.CmDepartment;/** * CmDepartment * @author:yangfangwei * @date:2016/07/22 11:03:55	 */public class CmDepartmentTree extends CmDepartment implements java.io.Serializable ,Comparable<CmDepartmentTree> {	private static final long serialVersionUID = 1L;	/*子部门*/	List<CmDepartmentTree> children;	public List<CmDepartmentTree> getChildren() {		return children;	}	public void setChildren(List<CmDepartmentTree> children) {		this.children = children;	}	@Override	public int compareTo(CmDepartmentTree o) {		return this.getDepName().compareTo(o.getDepName());	}}