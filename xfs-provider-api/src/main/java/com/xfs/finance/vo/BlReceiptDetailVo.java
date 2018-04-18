package com.xfs.finance.vo;

import com.xfs.finance.model.BlReceiptInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangxy on 2016/12/28.
 */
public class BlReceiptDetailVo extends BlReceiptInfo{

    private String writeOffType;
    private String buyerName;
    private String appName;
    private String payeeName;
    private String userName;
    private Date writeOffCreateTime;
    private String writeOffCreateBy;
    private String orderId;
    private BigDecimal bpvAmount;
    private BigDecimal alreadyCheck;
    private Date bpvCreateDt;
    private String bpvStatus;
    private BigDecimal channelFee;
    private String outerTradeNo;
    private BigDecimal balanceAmount;
    private BigDecimal writeOffAmount;
    private String productIntro;

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public BigDecimal getWriteOffAmount() {
        return writeOffAmount;
    }

    public void setWriteOffAmount(BigDecimal writeOffAmount) {
        this.writeOffAmount = writeOffAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getChannelFee() {
        return channelFee;
    }

    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    public String getOuterTradeNo() {
        return outerTradeNo;
    }

    public void setOuterTradeNo(String outerTradeNo) {
        this.outerTradeNo = outerTradeNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getBpvAmount() {
        return bpvAmount;
    }

    public void setBpvAmount(BigDecimal bpvAmount) {
        this.bpvAmount = bpvAmount;
    }

    public BigDecimal getAlreadyCheck() {
        return alreadyCheck;
    }

    public void setAlreadyCheck(BigDecimal alreadyCheck) {
        this.alreadyCheck = alreadyCheck;
    }

    public Date getBpvCreateDt() {
        return bpvCreateDt;
    }

    public void setBpvCreateDt(Date bpvCreateDt) {
        this.bpvCreateDt = bpvCreateDt;
    }

    public String getBpvStatus() {
        return bpvStatus;
    }

    public void setBpvStatus(String bpvStatus) {
        this.bpvStatus = bpvStatus;
    }

    public String getWriteOffCreateBy() {
        return writeOffCreateBy;
    }

    public void setWriteOffCreateBy(String writeOffCreateBy) {
        this.writeOffCreateBy = writeOffCreateBy;
    }

    public Date getWriteOffCreateTime() {
        return writeOffCreateTime;
    }

    public void setWriteOffCreateTime(Date writeOffCreateTime) {
        this.writeOffCreateTime = writeOffCreateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getWriteOffType() {
        return writeOffType;
    }

    public void setWriteOffType(String writeOffType) {
        this.writeOffType = writeOffType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
