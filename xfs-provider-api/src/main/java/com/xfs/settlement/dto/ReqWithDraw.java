package com.xfs.settlement.dto;

import java.math.BigDecimal;

/**
 * 账户余额普通提现
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-24 17:47
 */
public class ReqWithDraw extends BasePay {

    private String outer_trade_no;//畅捷支付合作商户网站唯一提现订单号（确保在商户系统中唯一）
    private String member_id;//	商户可请求从其子账户出款
    private BigDecimal amount;//	提现金额
    private String bank_account_no;//密文，使用RSA 加密。明文长度：50
    private String account_name;//户名密文，使用RSA 加密。明文长度：90
    private String notify_url;//支付平台服务器主动通知商户网站里指定的页面http路径。

    public String getOuter_trade_no() {
        return outer_trade_no;
    }

    public void setOuter_trade_no(String outer_trade_no) {
        this.outer_trade_no = outer_trade_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
