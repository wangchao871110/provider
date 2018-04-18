package com.xfs.business.dto;

import java.math.BigDecimal;

/**
 * 险种比例 对象  社保计算器用
 * Created by yangf on 2016/12/2.
 */
public class InsuranceTypeRatio implements java.io.Serializable{
    //险种id
    private Integer insuranceId;
    //险种名称
    private String insuarnaceName;
    //险种Code
    private String insuarnaceCode;
    //险种id
    private Integer ratioId;
    //能否改变险种
    private Boolean canChange;
    //企业比例
    private BigDecimal corpRatio;
    //个人比例
    private BigDecimal psnRatio;
    //是否参保
    private Boolean whetherInsured;

    public Boolean getWhetherInsured() {
        return whetherInsured;
    }

    public void setWhetherInsured(Boolean whetherInsured) {
        this.whetherInsured = whetherInsured;
    }

    public String getInsuarnaceCode() {
        return insuarnaceCode;
    }

    public void setInsuarnaceCode(String insuarnaceCode) {
        this.insuarnaceCode = insuarnaceCode;
    }

    public BigDecimal getCorpRatio() {

        return corpRatio;
    }

    public void setCorpRatio(BigDecimal corpRatio) {
        this.corpRatio = corpRatio;
    }

    public BigDecimal getPsnRatio() {
        return psnRatio;
    }

    public void setPsnRatio(BigDecimal psnRatio) {
        this.psnRatio = psnRatio;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuarnaceName() {
        return insuarnaceName;
    }

    public void setInsuarnaceName(String insuarnaceName) {
        this.insuarnaceName = insuarnaceName;
    }

    public Integer getRatioId() {
        return ratioId;
    }

    public void setRatioId(Integer ratioId) {
        this.ratioId = ratioId;
    }

    public Boolean getCanChange() {
        return canChange;
    }

    public void setCanChange(Boolean canChange) {
        this.canChange = canChange;
    }
}
