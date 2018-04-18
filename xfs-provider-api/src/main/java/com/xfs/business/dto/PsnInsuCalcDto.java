package com.xfs.business.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 社保计算器 人员社保计算类
 * Created by yangf on 2016/12/3.
 */
public class PsnInsuCalcDto implements java.io.Serializable{
    //参保类型
    private String livingType;
    //参保地区
    private Integer areaId;
    //社保基数
    private BigDecimal insuranceBase;
    //公积金基数
    private BigDecimal fundBase;
    //缴纳月份
    private String beginMonth;
    //险种比例
    private List<InsuranceTypeRatio> insuranceRatios;

    public String getLivingType() {
        return livingType;
    }

    public void setLivingType(String livingType) {
        this.livingType = livingType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getInsuranceBase() {
        return insuranceBase;
    }

    public void setInsuranceBase(BigDecimal insuranceBase) {
        this.insuranceBase = insuranceBase;
    }

    public BigDecimal getFundBase() {
        return fundBase;
    }

    public void setFundBase(BigDecimal fundBase) {
        this.fundBase = fundBase;
    }

    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        this.beginMonth = beginMonth;
    }

    public List<InsuranceTypeRatio> getInsuranceRatios() {
        return insuranceRatios;
    }

    public void setInsuranceRatios(List<InsuranceTypeRatio> insuranceRatios) {
        this.insuranceRatios = insuranceRatios;
    }
}
