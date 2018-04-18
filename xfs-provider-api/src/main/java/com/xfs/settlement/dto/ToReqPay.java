package com.xfs.settlement.dto;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-19 13:31
 */
public class ToReqPay extends ReqPay {


    private String voucherFile;//
    private Integer busId;
    private Integer payId;

    public String getVoucherFile() {
        return voucherFile;
    }

    public void setVoucherFile(String voucherFile) {
        this.voucherFile = voucherFile;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }
}
