package com.xfs.pay.alipay.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xfs.common.Result;
import com.xfs.pay.alipay.entity.AliPayEntity;
import com.xfs.pay.alipay.sign.RSA;

/**
 * 支付操作工具类
 * Created by yangf on 2016/5/20.
 */
public class AliPayUtils {

    private static final String ALGORITHM = "RSA";

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final Logger log = Logger.getLogger(AliPayUtils.class);

    /**
     * 客户端订单支付参数拼接
     * @param entity
     * @return
     * */
    public static String getAppOrderInfo(AliPayEntity entity) throws UnsupportedEncodingException {
        Map<String, String> sParaTemp = JSON.parseObject(JSON.toJSONString(entity),Map.class);
        sParaTemp.put("service", "mobile.securitypay.pay");
        String orderInfo = paramsAndSign(sParaTemp,entity.getRsa_private());
        return orderInfo;
    }

    /**
     * PC 网站支付参数拼接
     * @param entity
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getPcOrderInfo(AliPayEntity entity) throws UnsupportedEncodingException{
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.pay_url, entity.getApp_id(), entity.getRsa_private(), "json", entity.get_input_charset(), entity.getRes_public(), "RSA"); //获得初始化的AlipayClient
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl(entity.getReturn_url());
//        alipayRequest.setNotifyUrl(entity.getNotify_url());//在公共参数中设置回跳和通知地址
//        alipayRequest.setBizContent("{" +
//                "    \"out_trade_no\":\""+entity.getOut_trade_no()+"\"," +
//                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "    \"total_amount\":"+entity.getTotal_fee()+"," +
//                "    \"subject\":\""+entity.getSubject()+"\"," +
//                "    \"body\":\""+entity.getSubject()+"\"," +
//                "    \"enable_pay_channels\":\"balance,moneyFund,debitCardExpress,mcard,pcard,bankPay\"" +
//                "  }");
//        StringBuffer orderInfo = new StringBuffer();
//        try {
//            String body = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//            orderInfo.append(body);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
        //return orderInfo.toString();
    	return null;
    }

    /**
     * wap支付 参数拼接 h5
     * @param entity
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getWapOrderInfo(AliPayEntity entity) throws UnsupportedEncodingException {
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.pay_url, entity.getApp_id(), entity.getRsa_private(), "json", entity.get_input_charset(), entity.getRes_public(), "RSA"); //获得初始化的AlipayClient
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl(entity.getReturn_url());
//        alipayRequest.setNotifyUrl(entity.getNotify_url());//在公共参数中设置回跳和通知地址
//        alipayRequest.setBizContent("{" +
//                "    \"out_trade_no\":\""+entity.getOut_trade_no()+"\"," +
//                "    \"product_code\":\"QUICK_WAP_WAY\"," +
//                "    \"total_amount\":"+entity.getTotal_fee()+"," +
//                "    \"subject\":\""+entity.getSubject()+"\"," +
//                "    \"body\":\""+entity.getSubject()+"\"," +
//                "    \"enable_pay_channels\":\"balance,moneyFund,debitCardExpress,mcard,pcard,bankPay\"" +
//                "  }");
//        StringBuffer orderInfo = new StringBuffer();
//        try {
//            String body = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//            orderInfo.append(body);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        return orderInfo.toString();
    	return null;
    }

    /**
     * 参数拼接并签名
     * @param sParaTemp
     * @param rsa_private
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String paramsAndSign(Map<String,String> sParaTemp,String rsa_private) throws UnsupportedEncodingException{
        //除去数组中的空值和签名参数
        sParaTemp.remove("rsa_private");
        Map<String, String> sPara = paraFilter(sParaTemp);
        //生成签名结果
        String prestr = buildRequestSign(sPara);
        String mysign = RSA.sign(prestr, rsa_private, DEFAULT_CHARSET);
        if(log.isDebugEnabled()){
            log.debug(prestr+"=========="+mysign);
        }
        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", ALGORITHM);
        StringBuffer orderInfo = new StringBuffer();

        for(Map.Entry<String,String> entry : sPara.entrySet()){
            orderInfo.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        orderInfo.deleteCharAt(orderInfo.length()-1);
        return orderInfo.toString();
    }

    /**
     * 校验必填参数
     * @param entity
     * @return
     */
    public static Result verifyEntityParam(AliPayEntity entity) {
        Result result = new Result();
        if (StringUtils.isEmpty(entity.getOut_trade_no())) {
            result.setSuccess(false).setError("订单号不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(entity.getSubject())) {
            result.setSuccess(false).setError("产品名称不能为空！");
            return result;
        }
        if (entity.getTotal_fee() == null || entity.getTotal_fee().compareTo(BigDecimal.valueOf(0.01)) < 0) {
            result.setSuccess(false).setError("支付金额不正确！");
            return result;
        }
        return result;
    }

    /**
     * 参数排序拼接
     * @param sPara
     * @return
     */
    public static String buildRequestSign(Map<String, String> sPara) {
        List<String> keys = new ArrayList<String>(sPara.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sPara.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            if(null == sArray.get(key))
                continue;
            String value = String.valueOf(sArray.get(key));
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("pay_url") || key.equalsIgnoreCase("service_url")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
}
