package com.xfs.finance.vo;

import com.xfs.finance.model.BlReceiptInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangxy on 2016/12/28.
 */
public class AccountDetailParam extends BlReceiptInfo {

    private String accountNumOrName;
    private String receiptTimeBegin;
    private String receiptTimeEnd;
    private String busId;

    public String getAccountNumOrName() {
        return accountNumOrName;
    }

    public void setAccountNumOrName(String accountNumOrName) {
        this.accountNumOrName = accountNumOrName;
        if(isNumber(accountNumOrName))
            super.setAccount(accountNumOrName);
        else
            super.setAccountName(accountNumOrName);
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getReceiptTimeBegin() {
        return receiptTimeBegin;
    }

    public void setReceiptTimeBegin(String receiptTimeBegin) {
        this.receiptTimeBegin = receiptTimeBegin;
    }

    public String getReceiptTimeEnd() {
        return receiptTimeEnd;
    }

    public void setReceiptTimeEnd(String receiptTimeEnd) {
        this.receiptTimeEnd = receiptTimeEnd;
    }

    public boolean isNumber(String str){
        if(str != null && !str.trim().equals("")){
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher((CharSequence) str);
            return matcher.matches();
        }
        return false;
    }

}
