package com.xfs.finance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpsReconciliationInfo implements Serializable {
    private static final long serialVersionUID = -1060433233109835534L;
    private Long id;

    private Long recordId;

    private Long typeId;

    private String name;

    private String idNo;

    private String socialCity;

    private String fundCity;

    private String attachMonth;

    private Float socialCorp;

    private Float socailPer;

    private Float socialTotal;

    private Float fundCorp;

    private Float fundPer;

    private Float fundTotal;

    private Float corpTotal;

    private Float perTotal;

    private Float serviceFee;

    private Float total;

    private String remark;

    private Integer type;

    private List<SpsReconciliationDetail> detailList = new ArrayList<>();
    /**
     * 新增冗余字段
     */
    private String typeName;

    private String billMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getSocialCity() {
        return socialCity;
    }

    public void setSocialCity(String socialCity) {
        this.socialCity = socialCity == null ? null : socialCity.trim();
    }

    public String getFundCity() {
        return fundCity;
    }

    public void setFundCity(String fundCity) {
        this.fundCity = fundCity == null ? null : fundCity.trim();
    }

    public String getAttachMonth() {
        return attachMonth;
    }

    public void setAttachMonth(String attachMonth) {
        this.attachMonth = attachMonth == null ? null : attachMonth.trim();
    }

    public Float getSocialCorp() {
        return socialCorp;
    }

    public void setSocialCorp(Float socialCorp) {
        this.socialCorp = socialCorp;
    }

    public Float getSocailPer() {
        return socailPer;
    }

    public void setSocailPer(Float socailPer) {
        this.socailPer = socailPer;
    }

    public Float getSocialTotal() {
        return socialTotal;
    }

    public void setSocialTotal(Float socialTotal) {
        this.socialTotal = socialTotal;
    }

    public Float getFundCorp() {
        return fundCorp;
    }

    public void setFundCorp(Float fundCorp) {
        this.fundCorp = fundCorp;
    }

    public Float getFundPer() {
        return fundPer;
    }

    public void setFundPer(Float fundPer) {
        this.fundPer = fundPer;
    }

    public Float getFundTotal() {
        return fundTotal;
    }

    public void setFundTotal(Float fundTotal) {
        this.fundTotal = fundTotal;
    }

    public Float getCorpTotal() {
        return corpTotal;
    }

    public void setCorpTotal(Float corpTotal) {
        this.corpTotal = corpTotal;
    }

    public Float getPerTotal() {
        return perTotal;
    }

    public void setPerTotal(Float perTotal) {
        this.perTotal = perTotal;
    }

    public Float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<SpsReconciliationDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SpsReconciliationDetail> detailList) {
        this.detailList = detailList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
}