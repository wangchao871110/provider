package com.xfs.pay.wxpay.entity;

import com.xfs.pay.BasePayEntity;

/**
 * konglc 微信支付实体
 */
public class WxPayEntity extends BasePayEntity{
    private String orderId;//订单号
    private String totalFee;//金额 以分为单位 无小数点
    private String spbillCreateIp;//订单生成的机器 IP
    private String body;// 商品描述根据情况修改
    private String openId;//微信用户对一个公众号唯一
    private String tradeType;//支付类型
    private String mchId;//商户ID
    private String appId;//公共账号appID
    private String rsaPrivate;//商户私钥
    private String notifyUrl;
    private String nonceStr;
    private String returnUrl;
    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the totalFee
     */
    public String getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee the totalFee to set
     */
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @return the spbillCreateIp
     */
    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    /**
     * @param spbillCreateIp the spbillCreateIp to set
     */
    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRsaPrivate() {
        return rsaPrivate;
    }

    public void setRsaPrivate(String rsaPrivate) {
        this.rsaPrivate = rsaPrivate;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
