package com.xfs.serviceBill.dto;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillVo {
	private Integer cpId;// 企业ID
	private Integer spId;// 服务商ID
	private String spsName;// 服务商名称
	private String areaName;// 服务城市
	private String period;// 账单月
	private Integer empNumber = 0;// 服务人数
	private String insuredMonth;// 所属月
	private Integer billDay;// 约定账单日
	private BigDecimal prepay = BigDecimal.ZERO;// 系统外包成本
	private BigDecimal paid = BigDecimal.ZERO;// 服务商账单
	private BigDecimal difference = BigDecimal.ZERO;// 对账差额
	private String billState;// 对账状态
	private Integer areaId;// 城市ID
	private String empId;// 人员ID
	private String empName;// 人员名称
	private String authority;// 数据权限
	private Integer insuranceType;// 保险类型 2：社保；1：公积金；0：全部
	private Integer businessType;// 业务类型 2：补缴  1：新增  0：正常缴费
	private Integer businessStatus;// 业务状态  2：未做 1：异常 0：正常
	private Integer userId;// 登录用户ID
	private String billType;// 账单类型
	private Integer source = 0 ;// 统计来源0：表示列表 1：表示导出
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.period!=null)
			ht.put("period",this.period);
		if(this.insuredMonth!=null)
			ht.put("insuredMonth",this.insuredMonth);
		if(this.areaId!=null)
			ht.put("areaId",this.areaId);
		if(this.empId!=null)
			ht.put("empId",this.empId);
		if(this.empName!=null)
			ht.put("empName",this.empName);
		if(this.authority!=null)
			ht.put("authority",this.authority);
		if(this.insuranceType!=null)
			ht.put("insuranceType",this.insuranceType);
		if(this.businessType!=null)
			ht.put("businessType",this.businessType);
		if(this.businessStatus!=null)
			ht.put("businessStatus",this.businessStatus);
		if(this.userId!=null)
			ht.put("userId",this.userId);
		if(this.billType!=null)
			ht.put("billType",this.billType);
		if(this.source!=null)
			ht.put("source",this.source);
		
		return ht;
	}
	public Integer getCpId() {
		return cpId;
	}
	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public String getSpsName() {
		return spsName;
	}
	public void setSpsName(String spsName) {
		this.spsName = spsName;
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
	public Integer getBillDay() {
		return billDay;
	}
	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}
	public BigDecimal getPrepay() {
		return prepay;
	}
	public void setPrepay(BigDecimal prepay) {
		this.prepay = prepay;
	}
	public BigDecimal getPaid() {
		return paid;
	}
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}
	public BigDecimal getDifference() {
		return difference;
	}
	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(Integer insuranceType) {
		this.insuranceType = insuranceType;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getInsuredMonth() {
		return insuredMonth;
	}
	public void setInsuredMonth(String insuredMonth) {
		this.insuredMonth = insuredMonth;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(Integer empNumber) {
		this.empNumber = empNumber;
	}
	
 }
