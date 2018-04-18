package com.xfs.sp.model;

import java.util.HashMap;

/**
 * SpsExtdatahead
 * @author:wuzhe
 * 
 * @date:2016/05/18 11:07:29
 */
public class SpsExtdatahead implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String code;// 取excel的A-ZZ作为列编码 json中的数据使用code作为键值

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String codeEq;// 取excel的A-ZZ作为列编码 json中的数据使用code作为键值

	public String getCodeEq() {
		return this.codeEq;
	}

	public void setCodeEq(String codeEq) {
		this.codeEq = codeEq;
	}

	private String name;//

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String nameEq;//

	public String getNameEq() {
		return this.nameEq;
	}

	public void setNameEq(String nameEq) {
		this.nameEq = nameEq;
	}

	private String dataType;// DECIMAL DATE TIME PERCENT VARCHAR
							// 默认为文本，货币、会计专用、分数、科学计数转换成数值存储 常规、特殊、自定义-〉文本

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	private String dataTypeEq;// DECIMAL DATE TIME PERCENT VARCHAR
								// 默认为文本，货币、会计专用、分数、科学计数转换成数值存储 常规、特殊、自定义-〉文本

	public String getDataTypeEq() {
		return this.dataTypeEq;
	}

	public void setDataTypeEq(String dataTypeEq) {
		this.dataTypeEq = dataTypeEq;
	}

	private Integer sheetId;//

	public Integer getSheetId() {
		return this.sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public void fixup() {
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.id != null)
			ht.put("id", this.id);
		if (this.code != null)
			ht.put("code", this.code);
		if (this.codeEq != null)
			ht.put("codeEq", this.codeEq);
		if (this.name != null)
			ht.put("name", this.name);
		if (this.nameEq != null)
			ht.put("nameEq", this.nameEq);
		if (this.dataType != null)
			ht.put("dataType", this.dataType);
		if (this.dataTypeEq != null)
			ht.put("dataTypeEq", this.dataTypeEq);
		if (this.sheetId != null)
			ht.put("sheetId", this.sheetId);
		return ht;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/18 11:07:29

}
