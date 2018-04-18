package com.xfs.insurance.dto;

import java.util.Date;

/**
 * 商保订单详情类
 *
 * @author wangxs
 */


/**
 * MyCiOrder
 * @author:yangfw
 * @date:2016/09/12
 */
public class MyCiOrder {
    private Integer orderId;
    private String orderNo;
    private String orderState;
    private String spName;
    //交易日期
    private Date transactionDate;
    //商品名称
    private String productName;
    //缴费类型
    private String payType;
    //成交价
    private Double totalPrice;
    //订购份数/人
    private Integer amount;
    //保障期限
    private String guaranteePeriod;
    private String corpName;
    //企业证件类型
    private String corpLicenseType;
    //企业证件号码
    private String corpLicenseNum;
    //联系人
    private String contacts;
    //联系方式
    private String contactPhone;
    private String appraise;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpLicenseType() {
        return corpLicenseType;
    }

    public void setCorpLicenseType(String corpLicenseType) {
        this.corpLicenseType = corpLicenseType;
    }

    public String getCorpLicenseNum() {
        return corpLicenseNum;
    }

    public void setCorpLicenseNum(String corpLicenseNum) {
        this.corpLicenseNum = corpLicenseNum;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }
}

