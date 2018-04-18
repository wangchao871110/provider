package com.xfs.settlement.dto;

/**
 * 其他第三方支付签名参数
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-22 10:17
 */
public class RespOthPay extends RespPay {

    private String partnerId;//合作身份者ID，签约账号
    public String getPartnerId() {
        return this.partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId=partnerId;
    }
    private String payeeName;//开户名称
    public String getPayeeName() {
        return this.payeeName;
    }
    public void setPayeeName(String payeeName) {
        this.payeeName=payeeName;
    }
    private String accountNo;//银行账号
    public String getAccountNo() {
        return this.accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo=accountNo;
    }
    private String rsaPrivate;//
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
    private String clientip;
    public String getClientip() {
        return clientip;
    }
    public void setClientip(String clientip) {
        this.clientip = clientip;
    }
    private String memberId;
    private String cpartnerId;//畅捷通子商户号
    private Integer busId;
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getCpartnerId() {
        return cpartnerId;
    }
    public void setCpartnerId(String cpartnerId) {
        this.cpartnerId = cpartnerId;
    }
    public Integer getBusId() {
        return busId;
    }
    public void setBusId(Integer busId) {
        this.busId = busId;
    }
    private String payUrl;//支付地址
    private String serviceUrl;//第三方服务器地址
    public String getPayUrl() {
        return payUrl;
    }
    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
    public String getServiceUrl() {
        return serviceUrl;
    }
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
}
