package com.xfs.acc.dto;

import java.util.List;

/**
 * 方案比例
 * Created by konglc on 2017-09-14.
 */
public class SchemeAccountRatio {

    private Integer schemeId;
    private Integer areaId;
    private List<InsuranceRatio> insuranceRatio;// 社保比例
    private List<FundRatio> fundRatio;// 公积金比例

    public List<InsuranceRatio> getInsuranceRatio() {
        return insuranceRatio;
    }

    public void setInsuranceRatio(List<InsuranceRatio> insuranceRatio) {
        this.insuranceRatio = insuranceRatio;
    }

    public List<FundRatio> getFundRatio() {
        return fundRatio;
    }

    public void setFundRatio(List<FundRatio> fundRatio) {
        this.fundRatio = fundRatio;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
