package com.xfs.pay.chanpay.entity;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-09 10:17
 */
public class UnbundlingBankEntity extends BaseChanPayEntity {

    private String identity_no;
    private String bank_account_no;

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }
}
