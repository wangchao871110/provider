package com.xfs.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.xfs.business.model.BsRatioCalcInterface;

public class InsuranceCalcDetailDto implements BsRatioCalcInterface, Serializable {

    public Integer ratioId;

    private Integer insuranceId;
    //险种名称
    private String insuarnaceName;
    private BigDecimal empPayment;
    private BigDecimal corpPayment;

    private BigDecimal corpRatio;

    public BigDecimal getCorpRatio() {
        return corpRatio;
    }

    public void setCorpRatio(BigDecimal corpRatio) {
        this.corpRatio = corpRatio;
    }

    public BigDecimal getEmpPaybase() {
        return empPaybase;
    }

    public void setEmpPaybase(BigDecimal empPaybase) {
        this.empPaybase = empPaybase;
    }

    public BigDecimal getCorpPaybase() {
        return corpPaybase;
    }

    public void setCorpPaybase(BigDecimal corpPaybase) {
        this.corpPaybase = corpPaybase;
    }

    private BigDecimal empPaybase;
    private BigDecimal corpPaybase;

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public BigDecimal getEmpPayment() {
        return empPayment;
    }

    public void setEmpPayment(BigDecimal empPayment) {
        this.empPayment = empPayment;
    }

    public BigDecimal getCorpPayment() {
        return corpPayment;
    }

    public void setCorpPayment(BigDecimal corpPayment) {
        this.corpPayment = corpPayment;
    }

    public BigDecimal getEmpRatio() {
        return empRatio;
    }

    public void setEmpRatio(BigDecimal empRatio) {
        this.empRatio = empRatio;
    }

    private BigDecimal empRatio;

    public Integer getRatioId() {
        return ratioId;
    }

    public void setRatioId(Integer ratioId) {
        this.ratioId = ratioId;
    }

    private BigDecimal corpAddition;

    private BigDecimal psnAddition;

    public BigDecimal getCorpAddition() {
        return corpAddition;
    }

    public void setCorpAddition(BigDecimal corpAddition) {
        this.corpAddition = corpAddition;
    }

    public BigDecimal getPsnAddition() {
        return psnAddition;
    }

    public void setPsnAddition(BigDecimal psnAddition) {
        this.psnAddition = psnAddition;
    }

    public String getInsuarnaceName() {
        return insuarnaceName;
    }

    public void setInsuarnaceName(String insuarnaceName) {
        this.insuarnaceName = insuarnaceName;
    }
}
