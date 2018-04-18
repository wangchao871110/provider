package com.xfs.employeeside.model;/**
 * @author hongjie
 * @date 2017/3/15.14:14
 */

import java.math.BigDecimal;

/**
 * 公积金和社保详情
 *
 * @author hongjie
 * @create 2017-03-15 14:14
 **/
public class FundAndInsurance {

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuranceFundType() {
        return insuranceFundType;
    }

    public void setInsuranceFundType(String insuranceFundType) {
        this.insuranceFundType = insuranceFundType;
    }


    public BigDecimal getEmpRatio() {
        return empRatio;
    }

    public void setEmpRatio(BigDecimal empRatio) {
        this.empRatio = empRatio;
    }

    public BigDecimal getCorpRatio() {
        return corpRatio;
    }

    public void setCorpRatio(BigDecimal corpRatio) {
        this.corpRatio = corpRatio;
    }

    public BigDecimal getCorpPayment() {
        return corpPayment;
    }

    public void setCorpPayment(BigDecimal corpPayment) {
        this.corpPayment = corpPayment;
    }

    public BigDecimal getEmpPayment() {
        return empPayment;
    }

    public void setEmpPayment(BigDecimal empPayment) {
        this.empPayment = empPayment;
    }


    public String getInsuredMonth() {
        return insuredMonth;
    }

    public void setInsuredMonth(String insuredMonth) {
        this.insuredMonth = insuredMonth;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    private int empId;
    private int insuranceId;
    private String code;
    private String name;
    private String insuranceFundType;
    private BigDecimal empRatio;
    private BigDecimal corpRatio;
    private BigDecimal corpPayment;
    private BigDecimal empPayment;
    private BigDecimal fundBase;
    private BigDecimal insuranceBase;

    public BigDecimal getFundBase() {
        return fundBase;
    }

    public void setFundBase(BigDecimal fundBase) {
        this.fundBase = fundBase;
    }

    public BigDecimal getInsuranceBase() {
        return insuranceBase;
    }

    public void setInsuranceBase(BigDecimal insuranceBase) {
        this.insuranceBase = insuranceBase;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    private BigDecimal payment;
    private String insuredMonth;
    private String payType;
    private String year;
    private String month;


}
