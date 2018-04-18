package com.xfs.finance.vo;

import java.io.Serializable;

public class SpsReconciliationDetailVo implements Serializable {
    private static final long serialVersionUID = -1060433233109835534L;

    private String billMonth;
    private String insuranceName;
    private Integer type;

    private Float corpFee;

    private Float perFee;

    private Float total;


    private Float lCorpFee;

    private Float lPerFee;

    private Float lTotal;

    private Float rCorpFee;

    private Float rPerFee;

    private Float rTotal;

    private Float diffTotal;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public Float getCorpFee() {
        return corpFee;
    }

    public void setCorpFee(Float corpFee) {
        this.corpFee = corpFee;
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

    public Float getlCorpFee() {
        return lCorpFee;
    }

    public void setlCorpFee(Float lCorpFee) {
        this.lCorpFee = lCorpFee;
    }

    public Float getlPerFee() {
        return lPerFee;
    }

    public void setlPerFee(Float lPerFee) {
        this.lPerFee = lPerFee;
    }

    public Float getlTotal() {
        return lTotal;
    }

    public void setlTotal(Float lTotal) {
        this.lTotal = lTotal;
    }

    public Float getrCorpFee() {
        return rCorpFee;
    }

    public void setrCorpFee(Float rCorpFee) {
        this.rCorpFee = rCorpFee;
    }

    public Float getrPerFee() {
        return rPerFee;
    }

    public void setrPerFee(Float rPerFee) {
        this.rPerFee = rPerFee;
    }

    public Float getrTotal() {
        return rTotal;
    }

    public void setrTotal(Float rTotal) {
        this.rTotal = rTotal;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Float diffTotal) {
        this.diffTotal = diffTotal;
    }
}