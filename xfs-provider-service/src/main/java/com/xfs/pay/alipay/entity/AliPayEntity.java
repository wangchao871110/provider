package com.xfs.pay.alipay.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.xfs.pay.BasePayEntity;

/**
 * 支付参数类
 * Created by yangf on 2016/5/19.
 */
public class AliPayEntity extends BasePayEntity{
    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    private String partner;

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    private String seller_id;

    // MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    private String key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String return_url;

    // 签名方式
    private String sign_type;


    // 字符编码格式 目前支持 gbk 或 utf-8
    @JSONField(name="_input_charset")
    private String _input_charset;

    // 支付类型 ，无需修改
    private String payment_type;

    // 调用的接口名，无需修改
    private String service;
    //支付宝日志记录地址
    private String log_path;

    //商户私钥，pkcs8格式
    private String rsa_private;
    //支付宝公钥
    private String res_public;
    //移动支付接口
    private String mobile_service;
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
    private String anti_phishing_key;

    // 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
    private String exter_invoke_ip;

//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//--------------------动态传入参数-----------------------------
    //系统内唯一订单号
    private String out_trade_no;
    //支付金额
    private BigDecimal total_fee;
    //商品描述
    private String body;

    //订单名称
    private String subject;

    public String getMobile_service() {
        return mobile_service;
    }

    public void setMobile_service(String mobile_service) {
        this.mobile_service = mobile_service;
    }

    public String getRsa_private() {
        return rsa_private;
    }

    public void setRsa_private(String rsa_private) {
        this.rsa_private = rsa_private;
    }

    public String getRes_public() {
        return res_public;
    }

    public void setRes_public(String res_public) {
        this.res_public = res_public;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLog_path() {
        return log_path;
    }

    public void setLog_path(String log_path) {
        this.log_path = log_path;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAnti_phishing_key() {
        return anti_phishing_key;
    }

    public void setAnti_phishing_key(String anti_phishing_key) {
        this.anti_phishing_key = anti_phishing_key;
    }

    public String getExter_invoke_ip() {
        return exter_invoke_ip;
    }

    public void setExter_invoke_ip(String exter_invoke_ip) {
        this.exter_invoke_ip = exter_invoke_ip;
    }

    public String get_input_charset() {
        return _input_charset;
    }

    public void set_input_charset(String _input_charset) {
        this._input_charset = _input_charset;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public BigDecimal getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(BigDecimal total_fee) {
        this.total_fee = total_fee;
    }

    private String channelFeeType;//通道费类型  PLATFORM:平台 THIRDPARTY:第三方

    public String getChannelFeeType() {
        return channelFeeType;
    }

    public void setChannelFeeType(String channelFeeType) {
        this.channelFeeType = channelFeeType;
    }

    private String app_id;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}
