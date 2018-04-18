package com.xfs.pay.chanpay.entity;

/**
 * Created by konglc on 2016-06-13.
 * 查询银行列表接口
 * 商户系统自行封装收银台（支付列表选择页面）需要获取支付系统中支持的银行机构信息
 */
public class ChanChannelPayEntity extends BaseChanPayEntity {
    /**
     * 商户对接支付系统时
     约定支持的产品服务比如充值，
     交易等对应的代码。
     以商户签约为准
     */
    private String product_code;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
