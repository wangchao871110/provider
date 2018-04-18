package com.xfs.business.model;

/**
 * 同步官网数据
 * Created by konglc on 2017-09-19.
 */
public class ApplySyncData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer areaId;

    private String usbCode;

    private Integer cpId;//企业ID

    private String analysisNum;//解析批次号

    private String insuranceFundType;

    private Integer userId;//用户ID

    private String sessionId;//回话ID

    private Integer schemeId;//对应方案ID

    private String empJson;//人员数据信息

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getUsbCode() {
        return usbCode;
    }

    public void setUsbCode(String usbCode) {
        this.usbCode = usbCode;
    }

    public String getAnalysisNum() {
        return analysisNum;
    }

    public void setAnalysisNum(String analysisNum) {
        this.analysisNum = analysisNum;
    }

    public String getInsuranceFundType() {
        return insuranceFundType;
    }

    public void setInsuranceFundType(String insuranceFundType) {
        this.insuranceFundType = insuranceFundType;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getEmpJson() {
        return empJson;
    }

    public void setEmpJson(String empJson) {
        this.empJson = empJson;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
