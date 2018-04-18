package com.xfs.finance.vo;

import java.io.Serializable;

public class SpsReconciliationInfoVo implements Serializable {
    private static final long serialVersionUID = -1060433233109835534L;

    private String typeName;
    private String rTypeName;
    private String typeIds;
    private String lInfoIds;
    private String rInfoIds;

    private String name;

    private String idNo;

    private String socialCity;

    private String fundCity;

    private String attachMonth;

    private Float lSocialTotal;

    private Float lFundTotal;

    private Float rSocialTotal;

    private Float rFundTotal;

    private Float diffSocial;

    private Float diffFund;

    private Float diffTotal;

    private String billMonths;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSocialCity() {
        return socialCity;
    }

    public void setSocialCity(String socialCity) {
        this.socialCity = socialCity;
    }

    public String getFundCity() {
        return fundCity;
    }

    public void setFundCity(String fundCity) {
        this.fundCity = fundCity;
    }

    public String getAttachMonth() {
        return attachMonth;
    }

    public void setAttachMonth(String attachMonth) {
        this.attachMonth = attachMonth;
    }

    public Float getlSocialTotal() {
        return lSocialTotal;
    }

    public void setlSocialTotal(Float lSocialTotal) {
        this.lSocialTotal = lSocialTotal;
    }

    public Float getlFundTotal() {
        return lFundTotal;
    }

    public void setlFundTotal(Float lFundTotal) {
        this.lFundTotal = lFundTotal;
    }

    public Float getrSocialTotal() {
        return rSocialTotal;
    }

    public void setrSocialTotal(Float rSocialTotal) {
        this.rSocialTotal = rSocialTotal;
    }

    public Float getrFundTotal() {
        return rFundTotal;
    }

    public void setrFundTotal(Float rFundTotal) {
        this.rFundTotal = rFundTotal;
    }

    public Float getDiffSocial() {
        return diffSocial;
    }

    public void setDiffSocial(Float diffSocial) {
        this.diffSocial = diffSocial;
    }

    public Float getDiffFund() {
        return diffFund;
    }

    public void setDiffFund(Float diffFund) {
        this.diffFund = diffFund;
    }

    public Float getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Float diffTotal) {
        this.diffTotal = diffTotal;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public String getrTypeName() {
        return rTypeName;
    }

    public void setrTypeName(String rTypeName) {
        this.rTypeName = rTypeName;
    }

    public String getlInfoIds() {
        return lInfoIds;
    }

    public void setlInfoIds(String lInfoIds) {
        this.lInfoIds = lInfoIds;
    }

    public String getrInfoIds() {
        return rInfoIds;
    }

    public void setrInfoIds(String rInfoIds) {
        this.rInfoIds = rInfoIds;
    }

    public String getBillMonths() {
        return billMonths;
    }

    public void setBillMonths(String billMonths) {
        this.billMonths = billMonths;
    }
}