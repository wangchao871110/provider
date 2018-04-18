package com.xfs.pay;

/**
 * Created by konglc on 2016-10-24.
 * 支付实体基础类
 */
public class BasePayEntity {
    /**
     * 以下参数进行加密
     */
    private String pay_url;//当前支付地址
    private String service_url;//当前支付服务地址

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }
}
