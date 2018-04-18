package com.xfs.settlement.dto;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-09 10:28
 */
public class ReqMerchantStatus extends BasePay{

    private String identity_no;

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

}
