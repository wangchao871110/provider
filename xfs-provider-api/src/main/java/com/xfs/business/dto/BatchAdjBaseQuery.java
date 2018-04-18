package com.xfs.business.dto;

import java.util.List;

/**
 * 批量调整基数查询类
 * Created by yangf on 2016/12/26.
 */
public class BatchAdjBaseQuery implements java.io.Serializable{
    private Integer spId;
    private Integer cpId;
    private Boolean searchInsu;
    private Boolean searchFund;
    private String insuranceState;
    private String fundState;
    private String beginPeriod;
    private Integer areaId;
    private String empNameAndIdCode;
    private List<String> insuranceStates;
    private List<String> fundStates;

    // 数据权限
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<String> getInsuranceStates() {
        return insuranceStates;
    }

    public void setInsuranceStates(List<String> insuranceStates) {
        this.insuranceStates = insuranceStates;
    }

    public List<String> getFundStates() {
        return fundStates;
    }

    public void setFundStates(List<String> fundStates) {
        this.fundStates = fundStates;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public Boolean getSearchInsu() {
        return searchInsu;
    }

    public void setSearchInsu(Boolean searchInsu) {
        this.searchInsu = searchInsu;
    }

    public Boolean getSearchFund() {
        return searchFund;
    }

    public void setSearchFund(Boolean searchFund) {
        this.searchFund = searchFund;
    }

    public String getInsuranceState() {
        return insuranceState;
    }

    public void setInsuranceState(String insuranceState) {
        this.insuranceState = insuranceState;
    }

    public String getFundState() {
        return fundState;
    }

    public void setFundState(String fundState) {
        this.fundState = fundState;
    }

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getEmpNameAndIdCode() {
        return empNameAndIdCode;
    }

    public void setEmpNameAndIdCode(String empNameAndIdCode) {
        this.empNameAndIdCode = empNameAndIdCode;
    }
}
