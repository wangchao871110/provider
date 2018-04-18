package com.xfs.base.model;

import java.util.HashMap;

/**
 * BsAreabusi
 * @author:guopeng
 * 
 * @date:2016/06/07 17:46:12
 */
public class BsAreabusi implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;//

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer areaId;//

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	private String busi;//

	public String getBusi() {
		return this.busi;
	}

	public void setBusi(String busi) {
		this.busi = busi;
	}

	private String busiEq;//

	public String getBusiEq() {
		return this.busiEq;
	}

	public void setBusiEq(String busiEq) {
		this.busiEq = busiEq;
	}

	private Integer orderby;//

	public Integer getOrderby() {
		return this.orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public void fixup() {
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> ht = new HashMap<String, Object>();
		if (this.id != null)
			ht.put("id", this.id);
		if (this.areaId != null)
			ht.put("areaId", this.areaId);
		if (this.busi != null)
			ht.put("busi", this.busi);
		if (this.busiEq != null)
			ht.put("busiEq", this.busiEq);
		if (this.orderby != null)
			ht.put("orderby", this.orderby);
		return ht;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/06/07 17:46:12
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
