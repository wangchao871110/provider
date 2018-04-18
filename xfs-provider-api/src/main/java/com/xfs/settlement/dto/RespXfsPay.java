package com.xfs.settlement.dto;

import java.math.BigDecimal;

/**
 * 新福社签名响应信息
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-22 10:16
 */
public class RespXfsPay extends RespPay {

    private Integer busId;//
    public Integer getBusId() {
        return this.busId;
    }
    public void setBusId(Integer busId) {
        this.busId=busId;
    }
    private String appId;//
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId=appId;
    }
    private String appName;//应用名称
    public String getAppName() {
        return this.appName;
    }
    public void setAppName(String appName) {
        this.appName=appName;
    }
    private String rsaPrivate;//应用私钥
    public String getRsaPrivate() {
        return this.rsaPrivate;
    }
    public void setRsaPrivate(String rsaPrivate) {
        this.rsaPrivate=rsaPrivate;
    }
    private String rsaPublic;//
    public String getRsaPublic() {
        return this.rsaPublic;
    }
    public void setRsaPublic(String rsaPublic) {
        this.rsaPublic=rsaPublic;
    }
    private String rsaPublicEq;//
    public String getRsaPublicEq() {
        return this.rsaPublicEq;
    }
    public void setRsaPublicEq(String rsaPublicEq) {
        this.rsaPublicEq=rsaPublicEq;
    }
    private String isOffline;//是否支持下线付款 Y:是 N:否
    public String getIsOffline() {
        return this.isOffline;
    }
    public void setIsOffline(String isOffline) {
        this.isOffline=isOffline;
    }
    private String isBalance;//是否支持余额 Y:是 N:否
    public String getIsBalance() {
        return this.isBalance;
    }
    public void setIsBalance(String isBalance) {
        this.isBalance=isBalance;
    }
    private String state;//USE：在用  STOP：停用
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state=state;
    }

}
