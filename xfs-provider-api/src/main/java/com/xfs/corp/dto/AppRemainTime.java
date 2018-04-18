package com.xfs.corp.dto;

/**
 * 应用有效期
 * Created by konglc on 2017-08-01.
 */
public class AppRemainTime implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String code;//应用标识
    private String appName;//应用名称
    private String remainTime;//有效期或者说明
    private String tenantId;//租户信息
    private String appVersion;//版本

    private String timeStr;

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
