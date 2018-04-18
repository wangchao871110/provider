package com.xfs.mall.dto.customMadeCalc;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/29.
 */
public class CustomMadeCalcEmpDTO {

    public Integer areaId;
    public String areaName;
    public int empNum;
    public BigDecimal wage;

    public BigDecimal corpCost; // 企业成本（单个人）

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getEmpNum() {
        return empNum;
    }

    public void setEmpNum(int empNum) {
        this.empNum = empNum;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public BigDecimal getCorpCost() {
        return corpCost;
    }

    public void setCorpCost(BigDecimal corpCost) {
        this.corpCost = corpCost;
    }
}
