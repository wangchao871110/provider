package com.xfs.report.model;

import java.util.HashMap;


/**
 * BsSeach
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/10 14:48:42	
 */
public class BsSeach implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	private String sqltext;//
	public String getSqltext() {
		return this.sqltext;
	}
	public void setSqltext(String sqltext) {
		this.sqltext=sqltext;
	}
	private String sqltextEq;//
	public String getSqltextEq() {
		return this.sqltextEq;
	}
	public void setSqltextEq(String sqltextEq) {
		this.sqltextEq=sqltextEq;
	}
	private String name;//查询名称
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	private String nameEq;//查询名称
	public String getNameEq() {
		return this.nameEq;
	}
	public void setNameEq(String nameEq) {
		this.nameEq=nameEq;
	}
	private String columns;//
	public String getColumns() {
		return this.columns;
	}
	public void setColumns(String columns) {
		this.columns=columns;
	}
	private String columnsEq;//
	public String getColumnsEq() {
		return this.columnsEq;
	}
	public void setColumnsEq(String columnsEq) {
		this.columnsEq=columnsEq;
	}
	private String condition;//
	public String getCondition() {
		return this.condition;
	}
	public void setCondition(String condition) {
		this.condition=condition;
	}
	private String conditionEq;//
	public String getConditionEq() {
		return this.conditionEq;
	}
	public void setConditionEq(String conditionEq) {
		this.conditionEq=conditionEq;
	}
	private String conditionName;//
	public String getConditionName() {
		return this.conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName=conditionName;
	}
	private String conditionNameEq;//
	public String getConditionNameEq() {
		return this.conditionNameEq;
	}
	public void setConditionNameEq(String conditionNameEq) {
		this.conditionNameEq=conditionNameEq;
	}

	public String conditionType;
	public String conditionPullValue;

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getConditionPullValue() {
		return conditionPullValue;
	}

	public void setConditionPullValue(String conditionPullValue) {
		this.conditionPullValue = conditionPullValue;
	}

	public void fixup(){
	}
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.id!=null)
			ht.put("id",this.id);
		if(this.sqltext!=null)
			ht.put("sqltext",this.sqltext);
		if(this.sqltextEq!=null)
			ht.put("sqltextEq",this.sqltextEq);
		if(this.name!=null)
			ht.put("name",this.name);
		if(this.nameEq!=null)
			ht.put("nameEq",this.nameEq);
		if(this.columns!=null)
			ht.put("columns",this.columns);
		if(this.columnsEq!=null)
			ht.put("columnsEq",this.columnsEq);
		if(this.condition!=null)
			ht.put("condition",this.condition);
		if(this.conditionEq!=null)
			ht.put("conditionEq",this.conditionEq);
		if(this.conditionName!=null)
			ht.put("conditionName",this.conditionName);
		if(this.conditionNameEq!=null)
			ht.put("conditionNameEq",this.conditionNameEq);
		if(this.conditionType!=null)
			ht.put("conditionType",this.conditionType);
		if(this.conditionPullValue!=null)
			ht.put("conditionPullValue",this.conditionPullValue);
		return ht;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/10 14:48:42


}

