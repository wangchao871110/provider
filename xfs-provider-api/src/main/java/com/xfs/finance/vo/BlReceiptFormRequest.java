package com.xfs.finance.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuanm on 12/28/2016.
 */
public class BlReceiptFormRequest implements Serializable {
    private static final long serialVersionUID = 8217877153403153598L;
    private String accountName;
    private String account;
    private String amount;
    private String depositBank;
    private String receiptTimeStart;
    private String receiptTimeEnd;
    private String checkStatus;
    private Date startTime;
    private Date endTime;

    private Integer id;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getReceiptTimeStart() {
        return receiptTimeStart;
    }

    public void setReceiptTimeStart(String receiptTimeStart) {
        this.receiptTimeStart = receiptTimeStart;
    }

    public String getReceiptTimeEnd() {
        return receiptTimeEnd;
    }

    public void setReceiptTimeEnd(String receiptTimeEnd) {
        this.receiptTimeEnd = receiptTimeEnd;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
