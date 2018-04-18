package com.xfs.business.dto;

import java.math.BigDecimal;

/**
 * 税筹dto
 * Created by yangf on 2016/11/28.
 */
public class TaxPrepareDto implements java.io.Serializable{
    //如果登陆记录 orgid
    private Integer orgId;
    //导入编号  查询用
    private String importNo;
    //工号
    private String empNo;
    //姓名
    private String name;
    //工资
    private BigDecimal wages;
    //城市名称
    private String areaName;
    //城市id
    private Integer areaId;
    //企业成本
    private BigDecimal corpCost;
    //个人实收
    private BigDecimal realWage;
    //税筹后企业成本
    private BigDecimal scCorpCost;
    //税筹后个人实收
    private BigDecimal scRealWage;
    //企业节省
    private BigDecimal corpSave;
    //个人增收
    private BigDecimal psnAddDue;
    //是否缴纳公积金
    private Boolean isFund;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public Boolean getIsFund() {
        return isFund;
    }

    public void setIsFund(Boolean fund) {
        isFund = fund;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWages() {
        return wages;
    }

    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getCorpCost() {
        return corpCost;
    }

    public void setCorpCost(BigDecimal corpCost) {
        this.corpCost = corpCost;
    }

    public BigDecimal getRealWage() {
        return realWage;
    }

    public void setRealWage(BigDecimal realWage) {
        this.realWage = realWage;
    }

    public BigDecimal getScCorpCost() {
        return scCorpCost;
    }

    public void setScCorpCost(BigDecimal scCorpCost) {
        this.scCorpCost = scCorpCost;
    }

    public BigDecimal getScRealWage() {
        return scRealWage;
    }

    public void setScRealWage(BigDecimal scRealWage) {
        this.scRealWage = scRealWage;
    }

    public BigDecimal getCorpSave() {
        return corpSave;
    }

    public void setCorpSave(BigDecimal corpSave) {
        this.corpSave = corpSave;
    }

    public BigDecimal getPsnAddDue() {
        return psnAddDue;
    }

    public void setPsnAddDue(BigDecimal psnAddDue) {
        this.psnAddDue = psnAddDue;
    }
}
