package com.xfs.settlement.dto;

import java.math.BigDecimal;

/**
 * 支付请求信息
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 15:51
 */
public class ReqPay extends ReqRechargePay {

    private String outerTradeNo;//外部订单号
    private BigDecimal amount;//支付、充值金额
    private BigDecimal price;//订单售价
    private BigDecimal discounted;//折后价格
    private BigDecimal useBalance;//使用余额
    private String payType;//支付方式 ALIPAY:支付宝  CHANPAY:畅捷通  WXPAY:微信 OFFLINE:下线支付 OFFSET余额划转;
    private Integer createBy;//创建人ID
    private String attach;//扩展字段
    private BigDecimal channel_fee;//通道费

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscounted() {
        return discounted;
    }

    public void setDiscounted(BigDecimal discounted) {
        this.discounted = discounted;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(BigDecimal useBalance) {
        this.useBalance = useBalance;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public BigDecimal getChannel_fee() {
        return channel_fee;
    }

    public void setChannel_fee(BigDecimal channel_fee) {
        this.channel_fee = channel_fee;
    }
}
