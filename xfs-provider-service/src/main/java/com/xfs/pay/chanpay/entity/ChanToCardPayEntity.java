package com.xfs.pay.chanpay.entity;

/**
 * Created by konglc on 2016-06-13.
 * 付款到卡网关接口
 * 针对直客用户,如果没有在支付系统中注册,又需要付款给直客用户,通过此接口,可以将钱直接打到用户的卡中
 */
public class ChanToCardPayEntity extends BaseChanPayEntity{

    public ChanToCardPayEntity() {
        super.setService("cjt_payment_to_card");
    }

    private String outer_trade_no;//商户网站唯一订单号	String(32)	畅捷支付合作商户网站唯一订单号  // 失败后再次请求，需再次生成此订单号
    private String bank_account_no;//银行卡号	String	密文，使用RSA 加密。明文长度：50	不可空
    private String account_name;//	户名	String	密文，使用RSA 加密。明文长度：90	不可空
    private String bank_code;//	银行编号	String(10)	银行编号 见附录	不可空	ICBC,CCB
    private String bank_name;//	银行名称	String(50)	银行全称 见附录	不可空	中国工商银行
    private String bank_line_no;//	银行分支行号	String(12)	银行分支行号	可空
    private String bank_branch;//	支行名称	String(255)		不可空	中国农业银行深圳南山支行
    private String province;//	省份	String(128)		不可空	上海市，江苏省
    private String city;//	城市	String(128)		不可空	上海市，南京市
    private String card_type;//	卡类型	String(10)	借记：DEBIT；贷记：CREDIT	不可空	DEBIT
    private String card_attribute;//	卡属性	String(10)	对公：B；对私：C;	不可空	C
    private String amount;//	付款金额	Number(12)	金额必须大于0	不可空	15000.00
    private String notify_url;

    public String getOuter_trade_no() {
        return outer_trade_no;
    }

    public void setOuter_trade_no(String outer_trade_no) {
        this.outer_trade_no = outer_trade_no;
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

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_line_no() {
        return bank_line_no;
    }

    public void setBank_line_no(String bank_line_no) {
        this.bank_line_no = bank_line_no;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_attribute() {
        return card_attribute;
    }

    public void setCard_attribute(String card_attribute) {
        this.card_attribute = card_attribute;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
