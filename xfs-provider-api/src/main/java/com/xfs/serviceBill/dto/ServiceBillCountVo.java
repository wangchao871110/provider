package com.xfs.serviceBill.dto;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 服务商对账列表实体
 * @author 	: wangchao
 * @date 	: 2017年7月10日 下午2:49:59
 * @version 	: V1.0
 */
public class ServiceBillCountVo {
	private BigDecimal sysPrepayCount = BigDecimal.ZERO;// 系统外包总成本
	private BigDecimal sysCorpCount = BigDecimal.ZERO;// 系统外包企业总成本
	private BigDecimal sysEmpCount = BigDecimal.ZERO;// 系统外包个人总成本
	private BigDecimal sysServiceCount = BigDecimal.ZERO;// 系统外包服务费总成本
	
	private BigDecimal servicePrepayCount = BigDecimal.ZERO;// 服务商账单总额
	private BigDecimal serviceCorpCount = BigDecimal.ZERO;// 服务商账单企业总额
	private BigDecimal serviceEmpCount = BigDecimal.ZERO;// 服务商账单个人总额
	private BigDecimal serviceServiceCount = BigDecimal.ZERO;// 服务商账单服务费总额
	
	private BigDecimal differenceCount = BigDecimal.ZERO;// 总差额合计
	private BigDecimal differenceCorpCount = BigDecimal.ZERO;// 总差额企业合计
	private BigDecimal differenceEmpCount = BigDecimal.ZERO;// 总差额个人合计
	private BigDecimal differenceServiceCount = BigDecimal.ZERO;// 总差额服务费合计
	
	public BigDecimal getSysPrepayCount() {
		return sysPrepayCount;
	}
	public void setSysPrepayCount(BigDecimal sysPrepayCount) {
		this.sysPrepayCount = sysPrepayCount;
	}
	public BigDecimal getSysCorpCount() {
		return sysCorpCount;
	}
	public void setSysCorpCount(BigDecimal sysCorpCount) {
		this.sysCorpCount = sysCorpCount;
	}
	public BigDecimal getSysEmpCount() {
		return sysEmpCount;
	}
	public void setSysEmpCount(BigDecimal sysEmpCount) {
		this.sysEmpCount = sysEmpCount;
	}
	public BigDecimal getSysServiceCount() {
		return sysServiceCount;
	}
	public void setSysServiceCount(BigDecimal sysServiceCount) {
		this.sysServiceCount = sysServiceCount;
	}
	public BigDecimal getServicePrepayCount() {
		return servicePrepayCount;
	}
	public void setServicePrepayCount(BigDecimal servicePrepayCount) {
		this.servicePrepayCount = servicePrepayCount;
	}
	public BigDecimal getServiceCorpCount() {
		return serviceCorpCount;
	}
	public void setServiceCorpCount(BigDecimal serviceCorpCount) {
		this.serviceCorpCount = serviceCorpCount;
	}
	public BigDecimal getServiceEmpCount() {
		return serviceEmpCount;
	}
	public void setServiceEmpCount(BigDecimal serviceEmpCount) {
		this.serviceEmpCount = serviceEmpCount;
	}
	public BigDecimal getServiceServiceCount() {
		return serviceServiceCount;
	}
	public void setServiceServiceCount(BigDecimal serviceServiceCount) {
		this.serviceServiceCount = serviceServiceCount;
	}
	public BigDecimal getDifferenceCount() {
		return differenceCount;
	}
	public void setDifferenceCount(BigDecimal differenceCount) {
		this.differenceCount = differenceCount;
	}
	public BigDecimal getDifferenceCorpCount() {
		return differenceCorpCount;
	}
	public void setDifferenceCorpCount(BigDecimal differenceCorpCount) {
		this.differenceCorpCount = differenceCorpCount;
	}
	public BigDecimal getDifferenceEmpCount() {
		return differenceEmpCount;
	}
	public void setDifferenceEmpCount(BigDecimal differenceEmpCount) {
		this.differenceEmpCount = differenceEmpCount;
	}
	public BigDecimal getDifferenceServiceCount() {
		return differenceServiceCount;
	}
	public void setDifferenceServiceCount(BigDecimal differenceServiceCount) {
		this.differenceServiceCount = differenceServiceCount;
	}
	
	private Integer cpId;// 企业ID
	private Integer spId;// 服务商ID
	private String period;// 所属月
	private String authority;// 数据权限
	public HashMap<String,Object> toHashMap() {
		HashMap<String,Object> ht = new HashMap<String,Object>();
		if(this.spId!=null)
			ht.put("spId",this.spId);
		if(this.cpId!=null)
			ht.put("cpId",this.cpId);
		if(this.period!=null)
			ht.put("period",this.period);
		if(this.authority!=null)
			ht.put("authority",this.authority);
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
 }
