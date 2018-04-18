package com.xfs.pay.wxpay.utils;

import java.net.InetAddress;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.xfs.common.util.IPUtil;
import org.apache.log4j.Logger;

import com.xfs.pay.wxpay.entity.WxPayEntity;
import com.xfs.pay.wxpay.utils.http.PaymentApi;
import sun.net.util.IPAddressUtil;

/**
 * 微信支付工具类
 */
public class WxPayUtils {

    private static final Logger log = Logger.getLogger(WxPayUtils.class);

    /**
     * 客户端订单支付参数拼接
     * @param entity
     * @return
     * */
    public static String getAppOrderInfo(WxPayEntity entity) throws Exception {
        return null;
    }

    /**
     * PC 网站支付参数拼接
     * @param entity
     * @return
     * @throws Exception
     */
    public static Map<String, String> getPcOrderInfo(WxPayEntity entity) throws Exception{
        entity.setTradeType("NATIVE");
        try{
            entity.setOpenId(null);
            entity.setSpbillCreateIp(InetAddress.getLocalHost().getHostAddress());
        }catch (Exception e){

        }
        return getPackage(entity);
    }

    /**
     * wap支付 参数拼接 h5
     * @param entity
     * @return
     * @throws Exception
     */
    public static Map<String, String> getWapOrderInfo(WxPayEntity entity) throws Exception {
        entity.setTradeType("JSAPI");
        return getPackage(entity);
    }

    /**
     * 获取请求预支付id报文
     *
     * @return
     */
    private static Map<String, String> getPackage(WxPayEntity entity) {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", entity.getAppId());
        packageParams.put("mch_id", entity.getMchId());
        packageParams.put("nonce_str", entity.getNonceStr());
        packageParams.put("body", entity.getBody());
        packageParams.put("out_trade_no", entity.getOrderId());
        packageParams.put("device_info", "WEB");
        packageParams.put("fee_type", "CNY");
        packageParams.put("product_id",entity.getOrderId());
        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", getMoney(entity.getTotalFee()));
        packageParams.put("spbill_create_ip", entity.getSpbillCreateIp());
        packageParams.put("notify_url", entity.getNotifyUrl());
        packageParams.put("limit_pay", "no_credit");// no_credit--指定不能使用信用卡支付
        packageParams.put("trade_type", entity.getTradeType());
        packageParams.put("openid", entity.getOpenId());
        String sign = PaymentKit.createSign(packageParams, entity.getRsaPrivate());
        packageParams.put("sign", sign);
        log.info("获得签名sign:" + sign);

        log.info("params:" + packageParams.toString());
        String respContext2 = PaymentApi.pushOrder(packageParams);// 向微信申请下单
        Map<String,String> map = PaymentKit.xmlToMap(respContext2);
        log.info("申请与支付结果xml：" + respContext2);
        log.info("申请与支付结果map：" + map.toString());

        SortedMap<String, String> finalPackage = new TreeMap<String, String>();
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String packages = "prepay_id=" + map.get("prepay_id");
        finalPackage.put("appId", entity.getAppId());
        finalPackage.put("timeStamp", timestamp);
        finalPackage.put("nonceStr", entity.getNonceStr());
        finalPackage.put("package", packages);
        finalPackage.put("signType", "MD5");

        // 签名
        String paySign = PaymentKit.createSign(finalPackage, entity.getRsaPrivate());
        map.put("sign",paySign);
        map.put("nonce_str",entity.getNonceStr());
        map.put("timestamp",timestamp);
        return map;
    }

    /**
     * 元转换成分
     *
     * @param amount
     * @return
     */
    private static String getMoney(String amount) {
        if (amount == null) {
            return "";
        }
        // 金额转化为分为单位
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }
}
