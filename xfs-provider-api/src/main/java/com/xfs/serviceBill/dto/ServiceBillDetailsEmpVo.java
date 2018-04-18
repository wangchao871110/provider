package com.xfs.serviceBill.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillDetailsEmpVo {
	
	private Integer empId;// 员工ID
	private String empName;// 员工姓名
	private String code;// 证件号
	private String corpName;// 员工所属公司
	private String areaId;// 城市ID
	private String areaName;// 城市名称
	private String period;// 账单月
	private Integer billEmpId;// 人员账单ID
	private BigDecimal countDifference = BigDecimal.ZERO;// 差额
	private List<ServiceBillDetailsEmpDataVo> psnDatas = new ArrayList<>();
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getCountDifference() {
		return countDifference;
	}
	public void setCountDifference(BigDecimal countDifference) {
		this.countDifference = countDifference;
	}
	public List<ServiceBillDetailsEmpDataVo> getPsnDatas() {
		return psnDatas;
	}
	public void setPsnDatas(List<ServiceBillDetailsEmpDataVo> psnDatas) {
		this.psnDatas = psnDatas;
	}
	public Integer getBillEmpId() {
		return billEmpId;
	}
	public void setBillEmpId(Integer billEmpId) {
		this.billEmpId = billEmpId;
	}
	
 }
