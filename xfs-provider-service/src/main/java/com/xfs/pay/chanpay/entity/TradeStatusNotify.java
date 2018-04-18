package com.xfs.pay.chanpay.entity;

/**
 * 交易状态通知接口
 * 商户平台需要实现该接口，畅捷支付调用该接口,完成交易状态通知。
 */
public class TradeStatusNotify extends BaseNotify {

    private String outer_trade_no; // 商户网站唯一订单号
    private String inner_trade_no; // 支付平台交易订单号
    private String trade_status; // 交易状态
    private String trade_amount; // 交易金额
    private String gmt_create; // 交易创建时间;
    private String gmt_payment; // 交易支付时间
    private String gmt_close; // 交易关闭时间
    private String extension; // 扩展参数

    public String getOuter_trade_no() {
        return outer_trade_no;
    }

    public void setOuter_trade_no(String outer_trade_no) {
        this.outer_trade_no = outer_trade_no;
    }

    public String getInner_trade_no() {
        return inner_trade_no;
    }

    public void setInner_trade_no(String inner_trade_no) {
        this.inner_trade_no = inner_trade_no;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public String getGmt_close() {
        return gmt_close;
    }

    public void setGmt_close(String gmt_close) {
        this.gmt_close = gmt_close;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
