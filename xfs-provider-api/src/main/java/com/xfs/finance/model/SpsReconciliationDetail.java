package com.xfs.finance.model;

import java.io.Serializable;

public class SpsReconciliationDetail implements Serializable {
    private static final long serialVersionUID = -4669287154236637609L;

    private Long id;

    private Long infoId;

    private Long insuranceId;

    private String insuranceName;

    private Float corpBase;

    private Float corpRatio;

    private Float corpExtra;

    private Float corpFee;

    private Float perBase;

    private Float perRatio;

    private Float perExtra;

    private Float perFee;

    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName == null ? null : insuranceName.trim();
    }

    public Float getCorpBase() {
        return corpBase;
    }

    public void setCorpBase(Float corpBase) {
        this.corpBase = corpBase;
    }

    public Float getCorpRatio() {
        return corpRatio;
    }

    public void setCorpRatio(Float corpRatio) {
        this.corpRatio = corpRatio;
    }

    public Float getCorpExtra() {
        return corpExtra;
    }

    public void setCorpExtra(Float corpExtra) {
        this.corpExtra = corpExtra;
    }

    public Float getCorpFee() {
        return corpFee;
    }

    public void setCorpFee(Float corpFee) {
        this.corpFee = corpFee;
    }

    public Float getPerBase() {
        return perBase;
    }

    public void setPerBase(Float perBase) {
        this.perBase = perBase;
    }

    public Float getPerRatio() {
        return perRatio;
    }

    public void setPerRatio(Float perRatio) {
        this.perRatio = perRatio;
    }

    public Float getPerExtra() {
        return perExtra;
    }

    public void setPerExtra(Float perExtra) {
        this.perExtra = perExtra;
    }

    public Float getPerFee() {
        return perFee;
    }

    public void setPerFee(Float perFee) {
        this.perFee = perFee;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
