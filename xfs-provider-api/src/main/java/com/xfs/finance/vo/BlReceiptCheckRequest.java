package com.xfs.finance.vo;

import java.io.Serializable;

/**
 * Created by yuanm on 12/29/2016.
 */
public class BlReceiptCheckRequest implements Serializable {
    private static final long serialVersionUID = -680971307516159315L;
    private Boolean userBalance;
    private String ids;
    private String busId;
    private String tradeNo;

    public Boolean getUserBalance() {
        return userBalance==null?Boolean.FALSE:userBalance;
    }

    public void setUserBalance(Boolean userBalance) {
        this.userBalance = userBalance;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
