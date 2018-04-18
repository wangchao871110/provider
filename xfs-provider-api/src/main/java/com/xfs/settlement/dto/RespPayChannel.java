package com.xfs.settlement.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付渠道
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 09:53
 */
public class RespPayChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer payId;//渠道支付ID
    private String name;//支付渠道名称
    private String showName;//支付名称显示名
    private String payType;//CHANPAY:畅捷支付     WXPAY:微信支付       ALIPAY:支付宝            OFFLINE:下线支付
    private String payeeName;//开户名称
    private String accountBank;//开户银行
    private String accountBranchBank;//开户支行
    private String accountNo;//银行账号
    private String isOffline;//是否支持下线付款 Y:是 N:否
    private String isBalance;//是否支持余额 Y:是 N:否
    private String channelFeeType;//通道费类型  PLATFORM:平台 THIRDPARTY:第三方
    private String channelFeeFormula;//通道费公式
    private BigDecimal channelFee;//通道费
    private String isPc;//USE：在用  STOP：停用
    private String isWap;//USE：在用  STOP：停用
    private String isApp;//USE：在用  STOP：停用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountBranchBank() {
        return accountBranchBank;
    }

    public void setAccountBranchBank(String accountBranchBank) {
        this.accountBranchBank = accountBranchBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getChannelFee() {
        return channelFee;
    }

    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    public String getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(String isOffline) {
        this.isOffline = isOffline;
    }

    public String getIsBalance() {
        return isBalance;
    }

    public void setIsBalance(String isBalance) {
        this.isBalance = isBalance;
    }

    public String getChannelFeeType() {
        return channelFeeType;
    }

    public void setChannelFeeType(String channelFeeType) {
        this.channelFeeType = channelFeeType;
    }

    public String getIsPc() {
        return isPc;
    }

    public void setIsPc(String isPc) {
        this.isPc = isPc;
    }

    public String getIsWap() {
        return isWap;
    }

    public void setIsWap(String isWap) {
        this.isWap = isWap;
    }

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getChannelFeeFormula() {
        return channelFeeFormula;
    }

    public void setChannelFeeFormula(String channelFeeFormula) {
        this.channelFeeFormula = channelFeeFormula;
    }
}
