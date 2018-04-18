package com.xfs.corp.dto;

/**
 * 批量减员查询条件类
 * Created by yangf on 2016/12/15.
 */
public class BatchReduceEmplQuery implements java.io.Serializable{
    //服务商id
    private Integer spId;
    //来源
    private String schemeType;
    //缴纳城市
    private Integer belongCity;
    //员工搜索内容
    private String empSearch;
    //企业搜索内容
    private String corpSearch;
    //业务类型 社保/公积金
    private String insuranceFundType;
    //权限过滤
    private String authority;
    //库类型
    private String accType;

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public Integer getBelongCity() {
        return belongCity;
    }

    public void setBelongCity(Integer belongCity) {
        this.belongCity = belongCity;
    }

    public String getEmpSearch() {
        return empSearch;
    }

    public void setEmpSearch(String empSearch) {
        this.empSearch = empSearch;
    }

    public String getCorpSearch() {
        return corpSearch;
    }

    public void setCorpSearch(String corpSearch) {
        this.corpSearch = corpSearch;
    }

    public String getInsuranceFundType() {
        return insuranceFundType;
    }

    public void setInsuranceFundType(String insuranceFundType) {
        this.insuranceFundType = insuranceFundType;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}
