package com.xfs.corp.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* @author xiyanzhang
* @Email: zhangxiyan@xinfushe.com
* @version 创建时间：2016年12月3日 下午9:39:20 
*/
public class EmpMonthInsuranceDto {

    private String month;
    private String areaName;
    private String costMonth;
	private BigDecimal corpTotal = new BigDecimal(0);
    private BigDecimal empTotal = new BigDecimal(0);
    private BigDecimal officeTotal = new BigDecimal(0);
    private BigDecimal price = new BigDecimal(0);
    private List<Map<String, Object>> monthIns;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getCorpTotal() {
        return corpTotal;
    }

    public void setCorpTotal(BigDecimal corpTotal) {
        this.corpTotal = corpTotal;
    }

    public BigDecimal getEmpTotal() {
        return empTotal;
    }

    public void setEmpTotal(BigDecimal empTotal) {
        this.empTotal = empTotal;
    }

    public List<Map<String, Object>> getMonthIns() {
        return monthIns;
    }

    public void setMonthIns(List<Map<String, Object>> monthIns) {
        this.monthIns = monthIns;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCostMonth() {
        return costMonth;
    }

    public void setCostMonth(String costMonth) {
        this.costMonth = costMonth;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOfficeTotal() {
        return officeTotal;
    }

    public void setOfficeTotal(BigDecimal officeTotal) {
        this.officeTotal = officeTotal;
    }
}
