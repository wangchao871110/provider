package com.xfs.business.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.xfs.business.model.BsRatioCalcInterface;

/**
 * 社保计算dto
 * Created by yangf on 2016/11/28.
 */
public class InsuranceCalcDto implements java.io.Serializable{
    //地区
    private Integer areaId;
    //户口类型
    private String livingType;
    //参保月份 yyyy-MM
    private String period;
    //是否缴纳公积金
    private Boolean isFund;
    //工资
    private BigDecimal wages = BigDecimal.ZERO;
    //企业五险金额
    private BigDecimal corpInsu = BigDecimal.ZERO;
    //个人五险金额
    private BigDecimal psnInsu = BigDecimal.ZERO;
    //企业金额
    private BigDecimal corpFund = BigDecimal.ZERO;
    //个人公积金金额
    private BigDecimal psnFund = BigDecimal.ZERO;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getLivingType() {
        return livingType;
    }

    public void setLivingType(String livingType) {
        this.livingType = livingType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Boolean getIsFund() {
        return isFund;
    }

    public void setIsFund(Boolean fund) {
        isFund = fund;
    }

    public BigDecimal getWages() {
        return wages;
    }

    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }

    public BigDecimal getCorpInsu() {
        return corpInsu;
    }

    public void setCorpInsu(BigDecimal corpInsu) {
        this.corpInsu = corpInsu;
    }

    public BigDecimal getPsnInsu() {
        return psnInsu;
    }

    public void setPsnInsu(BigDecimal psnInsu) {
        this.psnInsu = psnInsu;
    }

    public BigDecimal getCorpFund() {
        return corpFund;
    }

    public void setCorpFund(BigDecimal corpFund) {
        this.corpFund = corpFund;
    }

    public BigDecimal getPsnFund() {
        return psnFund;
    }

    public void setPsnFund(BigDecimal psnFund) {
        this.psnFund = psnFund;
    }
    
    public List<InsuranceCalcDetailDto> getDetails() {
		return details;
	}

    public void setDetails(List<InsuranceCalcDetailDto> details) {
		this.details = details;
	}

	private List<InsuranceCalcDetailDto> details = null;
    
    
}
