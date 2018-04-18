package com.xfs.sp.model;

import java.util.HashMap;

/**
 * SpsExtdatasheet
 * @author:wuzhe
 * 
 * @date:2016/05/17 19:55:26
 */
public class SpsExtdatasheet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer exttabId;//

	public Integer getExttabId() {
		return this.exttabId;
	}

	public void setExttabId(Integer exttabId) {
		this.exttabId = exttabId;
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

	public void fixup() {
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.id != null)
			ht.put("id", this.id);
		if (this.exttabId != null)
			ht.put("exttabId", this.exttabId);
		if (this.name != null)
			ht.put("name", this.name);
		if (this.nameEq != null)
			ht.put("nameEq", this.nameEq);
		return ht;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/17 19:55:26

}
