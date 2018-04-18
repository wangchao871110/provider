package com.xfs.settlement.dto;

import java.io.Serializable;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 16:49
 */
public class BasePay  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;//应用ID
    private String sign;//签名
    private String serviceName;//服务名称

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
