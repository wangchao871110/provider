package com.xfs.finance.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxy on 2016/12/29.
 */
public class BlReceiptDetailDto {
    private Integer id;
    private String remark;
    private BigDecimal amount;
    private String checkStatus;
    private String depositBank;
    private String accountName;
    private String account;
    private Date receiptTime;
    private BigDecimal remainAmount;

    private List<BlReceiptDetailVo> details;

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public List<BlReceiptDetailVo> getDetails() {
        return details;
    }

    public void setDetails(List<BlReceiptDetailVo> details) {
        this.details = details;
    }
}
