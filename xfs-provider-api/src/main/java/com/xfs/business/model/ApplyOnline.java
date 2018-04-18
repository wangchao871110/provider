package com.xfs.business.model;

/**
 * 网申对象
 * Created by konglc on 2017-09-06.
 */
public class ApplyOnline implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String taskNo;

    private String bsTypeId;

    private String taskJson;

    private String insuranceFundType;

    private String userId;

    private String usbCode;

    private String state;//任务单执行结果 SUCCESS FAIL

    private String notifyType;//通知状态   参数异常 : PARAMETER_EXCEPTION 业务异常 : BUSINESS_EXCEPTION  网络异常 : NETWORK_EXCEPTION

    private String errorMsg;//异常信息

    private String analysisNum;//解析批次号

    private String sessionId;//当前会话ID

    private String ext;//扩展参数

    private Integer areaId;//城市ID


    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getBsTypeId() {
        return bsTypeId;
    }

    public void setBsTypeId(String bsTypeId) {
        this.bsTypeId = bsTypeId;
    }

    public String getTaskJson() {
        return taskJson;
    }

    public void setTaskJson(String taskJson) {
        this.taskJson = taskJson;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsbCode() {
        return usbCode;
    }

    public void setUsbCode(String usbCode) {
        this.usbCode = usbCode;
    }

    public String getInsuranceFundType() {
        return insuranceFundType;
    }

    public void setInsuranceFundType(String insuranceFundType) {
        this.insuranceFundType = insuranceFundType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAnalysisNum() {
        return analysisNum;
    }

    public void setAnalysisNum(String analysisNum) {
        this.analysisNum = analysisNum;
    }


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
