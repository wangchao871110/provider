package com.xfs.pay.chanpay.entity;

/**
 * Created by konglc on 2016-06-13.
 * 单笔订单支付 -- 适用于在畅捷通注册的商家
 */
public class ChanInstantPayEntity extends  BaseChanPayEntity {

    /******************* 单笔订单支付接口 ******************/
    private String out_trade_no;//商户网站唯一订单号
    private String trade_amount;//交易金额
    private String product_name;//商品名称
    private String action_desc;//交易描述
    private String produc_url;//商品展示URL
    private String notify_url;//服务器异步通知页面路径
    private String expired_time;//设置未付款交易的超时时间一旦超时，该笔交易就会自动被关闭；处在关闭的交易如收到状态变更，系统会自动发起退款操作。取值范围：1m～15d。m-分钟，h-小时，d-天。该参数数值不接受小数点，如1.5h，可转换为90m。
    private String buyer_id;//买家ID 	付款方编号
    /**
     * 买家ID类型 表明ID的类型，有这几种：UID/MEMBER_ID /MOBILE/LOGIN_NAME/COMPANY_ID  匿名用户is_anonymous为Y，可为空，非匿名不可为空
     */
    private String buyer_id_type;
    /**
     * 1:直连(合作方自己有收银台，选择银行时候，
     调用该接口直接跳转到选中的银行网银)；
     2：收银台（合作方没有收银台，订单支付时候，
     调用该接口到畅捷支付收银台）；
     3：余额支付（合作方选择畅捷支付余额付款时候，
     到畅捷支付账户余额付款页面）；
     */
    private String pay_method;
    /**
     * 	对公：B；
     对私：C;
     借记：DC；
     贷记：CC：综合：GC
     */
    private String pay_type;

    /**
     * 银行简码 例如 ICBC
     */
    private String bank_code;

    /**
     * 若该值为Y，表示该笔订单的用户不需要是畅捷支付的用户。
     可空 Y，表示接入方的用户没有同步为畅捷支付的注册会员
     */
    private String is_anonymous;

    /**
     * 付款方名称（客户姓名或者企业开户时全称）
     */
    private String payer_truename;

    /**
     * 付款方银行名称（详细到支行）
     */
    private String payer_bankname;

    /**
     * 付款方银行账号
     */
    private String payer_bankaccountNo;

    /**
     * 交易金额分润账号集
     */
    private String royalty_parameters;

    private String ext1;

    private String sell_id_type;

    private String sell_id;

    private String sell_mobile;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getAction_desc() {
        return action_desc;
    }

    public void setAction_desc(String action_desc) {
        this.action_desc = action_desc;
    }

    public String getProduc_url() {
        return produc_url;
    }

    public void setProduc_url(String produc_url) {
        this.produc_url = produc_url;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(String expired_time) {
        this.expired_time = expired_time;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_id_type() {
        return buyer_id_type;
    }

    public void setBuyer_id_type(String buyer_id_type) {
        this.buyer_id_type = buyer_id_type;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(String is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getPayer_truename() {
        return payer_truename;
    }

    public void setPayer_truename(String payer_truename) {
        this.payer_truename = payer_truename;
    }

    public String getPayer_bankname() {
        return payer_bankname;
    }

    public void setPayer_bankname(String payer_bankname) {
        this.payer_bankname = payer_bankname;
    }

    public String getPayer_bankaccountNo() {
        return payer_bankaccountNo;
    }

    public void setPayer_bankaccountNo(String payer_bankaccountNo) {
        this.payer_bankaccountNo = payer_bankaccountNo;
    }

    public String getRoyalty_parameters() {
        return royalty_parameters;
    }

    public void setRoyalty_parameters(String royalty_parameters) {
        this.royalty_parameters = royalty_parameters;
    }

    public String getSell_id_type() {
        return sell_id_type;
    }

    public void setSell_id_type(String sell_id_type) {
        this.sell_id_type = sell_id_type;
    }

    public String getSell_id() {
        return sell_id;
    }

    public void setSell_id(String sell_id) {
        this.sell_id = sell_id;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getSell_mobile() {
        return sell_mobile;
    }

    public void setSell_mobile(String sell_mobile) {
        this.sell_mobile = sell_mobile;
    }
}
