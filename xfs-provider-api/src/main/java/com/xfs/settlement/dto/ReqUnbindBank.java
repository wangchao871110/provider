package com.xfs.settlement.dto;

/**
 * 解绑银行卡
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-24 17:51
 */
public class ReqUnbindBank extends BasePay {

    private Integer account_id;//银行Id

    private String identity_no;

    private String bank_account_no;//密文，使用RSA 加密

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

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }
}
