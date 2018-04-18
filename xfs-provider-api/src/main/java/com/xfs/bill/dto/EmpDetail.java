package com.xfs.bill.dto;

import com.xfs.bill.model.SpsBillDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-07 14:55
 */
public class EmpDetail {

    private Integer empId;
    private String name;
    private String insuredMonth;
    private BigDecimal serviceFee;
    private BigDecimal corpPayment;
    private BigDecimal empPayment;
    private BigDecimal amount;
    private String adjustReason;
    private List<InsureEmpDetail> insureEmpDetails;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuredMonth() {
        return insuredMonth;
    }

    public void setInsuredMonth(String insuredMonth) {
        this.insuredMonth = insuredMonth;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public List<InsureEmpDetail> getInsureEmpDetails() {
        return insureEmpDetails;
    }

    public void setInsureEmpDetails(List<InsureEmpDetail> insureEmpDetails) {
        this.insureEmpDetails = insureEmpDetails;
    }
}
