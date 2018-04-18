package com.xfs.settlement.dto;

/**
 * 充值请求
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 17:03
 */
public class ReqRechargePay extends BasePay{

    private String tradeType;//交易类型 PAY_FEE:支付 RECHARGE_FEE:充值
    private String buyerName;//付款方名称
    private String buyerId;//付款方唯一标识
    private String buyerType;//付款方类型 PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    private String payeeName;//收款方名称
    private String payeeId;//收款方唯一标识;
    private String payeeType;//收款方类型 PERSONAL:个人  COPR:企业  SERVICE:服务商 XFS:新福社
    private String productIntro;//商品简介
    private String productInfo;//商品详情
    private String notifyUrl;//异步通知url
    private String returnUrl;//重定向地址

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
