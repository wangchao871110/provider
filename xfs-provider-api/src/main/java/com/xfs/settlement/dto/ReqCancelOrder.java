package com.xfs.settlement.dto;

/**
 * 取消--作废支付单
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-09 11:51
 */
public class ReqCancelOrder extends BasePay {

    private String outerTradeNo;//业务订单号

    private Integer busId;

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }
}
