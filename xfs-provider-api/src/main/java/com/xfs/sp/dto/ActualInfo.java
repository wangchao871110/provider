package com.xfs.sp.dto;

import java.math.BigDecimal;

public class ActualInfo {
	private String name;//员工姓名
	private String identityCode;//证件号
	private String period;//所属月
	private BigDecimal payBase;//
	private Integer insuranceId;
	private Integer id;//
	private String insuranceName;//险种名称
	private Integer actualid;
	private Integer empid;//员工id
	private String insurancetype;//社保类型
	private String fundtype;//公积金类型
	private String fullname;//地址全名
	private BigDecimal sumcorp;//企业合计
	private BigDecimal sumemp;//个人合计
	private BigDecimal sumec;//总额
	private String corpName;
	
	//养老保险企业个人
    private BigDecimal payBase1;
    private BigDecimal empRatio1;
    private BigDecimal corpRatio1;
    private BigDecimal empPayment1;
    private BigDecimal corpPayment1;
    private BigDecimal sum1;
    //医疗
    private BigDecimal payBase2;
    private BigDecimal empRatio2;
    private BigDecimal corpRatio2;
    private BigDecimal empPayment2;
    private BigDecimal corpPayment2;
    private BigDecimal sum2;
    //工伤
    private BigDecimal payBase3;
    private BigDecimal empRatio3;
    private BigDecimal corpRatio3;
    private BigDecimal empPayment3;
    private BigDecimal corpPayment3;
    private BigDecimal sum3;
    //失业
    private BigDecimal payBase4;
    private BigDecimal empRatio4;
    private BigDecimal corpRatio4;
    private BigDecimal empPayment4;
    private BigDecimal corpPayment4;
    private BigDecimal sum4;
    //生育
    private BigDecimal payBase5;
    private BigDecimal empRatio5;
    private BigDecimal corpRatio5;
    private BigDecimal empPayment5;
    private BigDecimal corpPayment5;
    private BigDecimal sum5;
    //公积金
    private BigDecimal payBase6;
    private BigDecimal empRatio6;
    private BigDecimal corpRatio6;
    private BigDecimal empPayment6;
    private BigDecimal corpPayment6;
    private BigDecimal sum6;
    
    
 // 数据权限
 	private String authority;

 	public String getAuthority() {
 		return authority;
 	}

 	public void setAuthority(String authority) {
 		this.authority = authority;
 	}
	
	
	public BigDecimal getSumcorp() {
		return sumcorp;
	}
	public void setSumcorp(BigDecimal sumcorp) {
		this.sumcorp = sumcorp;
	}
	public BigDecimal getSumemp() {
		return sumemp;
	}
	public void setSumemp(BigDecimal sumemp) {
		this.sumemp = sumemp;
	}
	public BigDecimal getSumec() {
		return sumec;
	}
	public void setSumec(BigDecimal sumec) {
		this.sumec = sumec;
	}
	public String getInsurancetype() {
		return insurancetype;
	}
	public void setInsurancetype(String insurancetype) {
		this.insurancetype = insurancetype;
	}
	public String getFundtype() {
		return fundtype;
	}
	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public BigDecimal getSum1() {
		return sum1;
	}
	public void setSum1(BigDecimal sum1) {
		this.sum1 = sum1;
	}
	public BigDecimal getSum2() {
		return sum2;
	}
	public void setSum2(BigDecimal sum2) {
		this.sum2 = sum2;
	}
	public BigDecimal getSum3() {
		return sum3;
	}
	public void setSum3(BigDecimal sum3) {
		this.sum3 = sum3;
	}
	public BigDecimal getSum4() {
		return sum4;
	}
	public void setSum4(BigDecimal sum4) {
		this.sum4 = sum4;
	}
	public BigDecimal getSum5() {
		return sum5;
	}
	public void setSum5(BigDecimal sum5) {
		this.sum5 = sum5;
	}
	public BigDecimal getSum6() {
		return sum6;
	}
	public void setSum6(BigDecimal sum6) {
		this.sum6 = sum6;
	}
	public BigDecimal getPayBase1() {
		return payBase1;
	}
	public void setPayBase1(BigDecimal payBase1) {
		this.payBase1 = payBase1;
	}
	public BigDecimal getEmpRatio1() {
		return empRatio1;
	}
	public void setEmpRatio1(BigDecimal empRatio1) {
		this.empRatio1 = empRatio1;
	}
	public BigDecimal getCorpRatio1() {
		return corpRatio1;
	}
	public void setCorpRatio1(BigDecimal corpRatio1) {
		this.corpRatio1 = corpRatio1;
	}
	public BigDecimal getEmpPayment1() {
		return empPayment1;
	}
	public void setEmpPayment1(BigDecimal empPayment1) {
		this.empPayment1 = empPayment1;
	}
	public BigDecimal getCorpPayment1() {
		return corpPayment1;
	}
	public void setCorpPayment1(BigDecimal corpPayment1) {
		this.corpPayment1 = corpPayment1;
	}
	public BigDecimal getPayBase2() {
		return payBase2;
	}
	public void setPayBase2(BigDecimal payBase2) {
		this.payBase2 = payBase2;
	}
	public BigDecimal getEmpRatio2() {
		return empRatio2;
	}
	public void setEmpRatio2(BigDecimal empRatio2) {
		this.empRatio2 = empRatio2;
	}
	public BigDecimal getCorpRatio2() {
		return corpRatio2;
	}
	public void setCorpRatio2(BigDecimal corpRatio2) {
		this.corpRatio2 = corpRatio2;
	}
	public BigDecimal getEmpPayment2() {
		return empPayment2;
	}
	public void setEmpPayment2(BigDecimal empPayment2) {
		this.empPayment2 = empPayment2;
	}
	public BigDecimal getCorpPayment2() {
		return corpPayment2;
	}
	public void setCorpPayment2(BigDecimal corpPayment2) {
		this.corpPayment2 = corpPayment2;
	}
	public BigDecimal getPayBase3() {
		return payBase3;
	}
	public void setPayBase3(BigDecimal payBase3) {
		this.payBase3 = payBase3;
	}
	public BigDecimal getEmpRatio3() {
		return empRatio3;
	}
	public void setEmpRatio3(BigDecimal empRatio3) {
		this.empRatio3 = empRatio3;
	}
	public BigDecimal getCorpRatio3() {
		return corpRatio3;
	}
	public void setCorpRatio3(BigDecimal corpRatio3) {
		this.corpRatio3 = corpRatio3;
	}
	public BigDecimal getEmpPayment3() {
		return empPayment3;
	}
	public void setEmpPayment3(BigDecimal empPayment3) {
		this.empPayment3 = empPayment3;
	}
	public BigDecimal getCorpPayment3() {
		return corpPayment3;
	}
	public void setCorpPayment3(BigDecimal corpPayment3) {
		this.corpPayment3 = corpPayment3;
	}
	public BigDecimal getPayBase4() {
		return payBase4;
	}
	public void setPayBase4(BigDecimal payBase4) {
		this.payBase4 = payBase4;
	}
	public BigDecimal getEmpRatio4() {
		return empRatio4;
	}
	public void setEmpRatio4(BigDecimal empRatio4) {
		this.empRatio4 = empRatio4;
	}
	public BigDecimal getCorpRatio4() {
		return corpRatio4;
	}
	public void setCorpRatio4(BigDecimal corpRatio4) {
		this.corpRatio4 = corpRatio4;
	}
	public BigDecimal getEmpPayment4() {
		return empPayment4;
	}
	public void setEmpPayment4(BigDecimal empPayment4) {
		this.empPayment4 = empPayment4;
	}
	public BigDecimal getCorpPayment4() {
		return corpPayment4;
	}
	public void setCorpPayment4(BigDecimal corpPayment4) {
		this.corpPayment4 = corpPayment4;
	}
	public BigDecimal getPayBase5() {
		return payBase5;
	}
	public void setPayBase5(BigDecimal payBase5) {
		this.payBase5 = payBase5;
	}
	public BigDecimal getEmpRatio5() {
		return empRatio5;
	}
	public void setEmpRatio5(BigDecimal empRatio5) {
		this.empRatio5 = empRatio5;
	}
	public BigDecimal getCorpRatio5() {
		return corpRatio5;
	}
	public void setCorpRatio5(BigDecimal corpRatio5) {
		this.corpRatio5 = corpRatio5;
	}
	public BigDecimal getEmpPayment5() {
		return empPayment5;
	}
	public void setEmpPayment5(BigDecimal empPayment5) {
		this.empPayment5 = empPayment5;
	}
	public BigDecimal getCorpPayment5() {
		return corpPayment5;
	}
	public void setCorpPayment5(BigDecimal corpPayment5) {
		this.corpPayment5 = corpPayment5;
	}
	public BigDecimal getPayBase6() {
		return payBase6;
	}
	public void setPayBase6(BigDecimal payBase6) {
		this.payBase6 = payBase6;
	}
	public BigDecimal getEmpRatio6() {
		return empRatio6;
	}
	public void setEmpRatio6(BigDecimal empRatio6) {
		this.empRatio6 = empRatio6;
	}
	public BigDecimal getCorpRatio6() {
		return corpRatio6;
	}
	public void setCorpRatio6(BigDecimal corpRatio6) {
		this.corpRatio6 = corpRatio6;
	}
	public BigDecimal getEmpPayment6() {
		return empPayment6;
	}
	public void setEmpPayment6(BigDecimal empPayment6) {
		this.empPayment6 = empPayment6;
	}
	public BigDecimal getCorpPayment6() {
		return corpPayment6;
	}
	public void setCorpPayment6(BigDecimal corpPayment6) {
		this.corpPayment6 = corpPayment6;
	}
	public Integer getActualid() {
		return actualid;
	}
	public void setActualid(Integer actualid) {
		this.actualid = actualid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getPayBase() {
		return payBase;
	}
	public void setPayBase(BigDecimal payBase) {
		this.payBase = payBase;
	}
	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	
	
	
	
}
