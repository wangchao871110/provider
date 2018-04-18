package com.xfs.acc.dto;

import java.util.List;

/**
 * 已有账户城市数据传输实体
 * @author 	: wangchao
 * @date 	: 2017年3月6日 上午9:49:18
 * @version 	: V1.0
 */
public class AreaSchemeVo {
	private Integer areaId;// 城市ID
	private Integer schemeId;// 方案ID
	private String areaName;// 城市名称
	private String memo;
	private String authority;
	private Integer cpId;
	private Integer spId;
	private String spName;
	private Integer isServiceByArea;

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	private List<AreaSchemeVo> schemeList;
	public List<AreaSchemeVo> getSchemeList() {
		return schemeList;
	}
	public void setSchemeList(List<AreaSchemeVo> schemeList) {
		this.schemeList = schemeList;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public Integer getIsServiceByArea() {
		return isServiceByArea;
	}

	public void setIsServiceByArea(Integer isServiceByArea) {
		this.isServiceByArea = isServiceByArea;
	}
}
