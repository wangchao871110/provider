package com.xfs.settlement.dto;

/**
 * 获取畅捷通子商户余额
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-13 17:08
 */
public class ReqQueryMerchantBalance extends BasePay{

    private String identity_no;//商户号
    private String identity_type;//商户号：MEMBER_ID
    private String account_type;//企业基本户：201

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
