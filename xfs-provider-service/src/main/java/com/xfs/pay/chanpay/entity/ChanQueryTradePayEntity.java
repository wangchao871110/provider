package com.xfs.pay.chanpay.entity;

/**
 * 交易查询网关接口
 * 服务名称：cjt_query_trade
 * 业务场景：因为某一方技术的原因， 可能导致商家在预期时间内都收不到最终支付通知， 此时商家可以通过该接口来查询订单的详细支付状态。
 */
public class ChanQueryTradePayEntity extends BaseChanPayEntity {

    public ChanQueryTradePayEntity() {
        super.setService("cjt_query_trade");
    }

    private String outer_trade_no; // 单笔订单支付中的订单号；

    private String trade_type; // 交易的类型类型包括 即时到账(INSTANT)，担保交易(ENSURE)，退款(REFUND),提现（WITHDRAWAL） 定金下定（PREPAY）

    private String inner_trade_no; // 支付平台交易订单号

    public String getOuter_trade_no() {
        return outer_trade_no;
    }

    public void setOuter_trade_no(String outer_trade_no) {
        this.outer_trade_no = outer_trade_no;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getInner_trade_no() {
        return inner_trade_no;
    }

    public void setInner_trade_no(String inner_trade_no) {
        this.inner_trade_no = inner_trade_no;
    }
}
