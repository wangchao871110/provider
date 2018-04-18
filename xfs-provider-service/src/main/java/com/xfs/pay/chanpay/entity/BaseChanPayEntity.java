package com.xfs.pay.chanpay.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.xfs.pay.BasePayEntity;

/**
 * Created by konglc on 2016-06-13.
 * 畅捷支付实体
 */
public class BaseChanPayEntity extends BasePayEntity{
    private static final long serialVersionUID = 1L;
    private String merchant_private_key;//签约合作方的密钥

    /******************* 请求基本参数是下面所有请求接口都要加上的 ******************/
    private String service;//不可空	例：cjt_create_instant_trade
    private String version = "1.0";//目前只有固定值1.0	不可空	1.0
    private String partner_id;//签约合作方的唯一用户号	不可空	2088001159940003
    @JSONField(name="_input_charset")
    private String _input_charset = "UTF-8";//商户网站使用的编码格式，如utf-8、gbk、gb2312等	不可空	GBK
    private String sign;//“签名机制”	不可空	e8qdwl9caset5zugii2r7q0k8ikopxor
    private String sign_type="RSA";//签名方式只支持RSA、MD5	不可空	MD5
    private String return_url;//处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public void setMerchant_private_key(String merchant_private_key) {
        this.merchant_private_key = merchant_private_key;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String get_input_charset() {
        return _input_charset;
    }

    public void set_input_charset(String _input_charset) {
        this._input_charset = _input_charset;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

}
