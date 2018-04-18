package com.xfs.pay.chanpay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xfs.common.exception.BusinessException;
import com.xfs.pay.chanpay.entity.BaseChanPayEntity;
import com.xfs.pay.chanpay.entity.ChanInstantPayEntity;
import com.xfs.pay.chanpay.entity.ChanQueryTradePayEntity;
import com.xfs.pay.chanpay.entity.ChanToCardPayEntity;
import com.xfs.pay.chanpay.sign.MD5;
import com.xfs.pay.chanpay.sign.RSA;
import com.xfs.pay.chanpay.util.httpClient.HttpProtocolHandler;
import com.xfs.pay.chanpay.util.httpClient.HttpRequest;
import com.xfs.pay.chanpay.util.httpClient.HttpResponse;
import com.xfs.pay.chanpay.util.httpClient.HttpResultType;

/**
 * 畅捷通支付工具类
 */
public class ChanPayUtils {

    private static final Logger log = Logger.getLogger(ChanPayUtils.class);

    /**
     * 客户端订单支付参数拼接
     * @param entity
     * @return
     * */
    public static String getAppOrderInfo(ChanInstantPayEntity entity) throws Exception {
        String orderInfo = createRequestParamsStr(entity);
        return orderInfo;
    }

    /**
     * PC 网站支付参数拼接
     * @param entity
     * @return
     * @throws Exception
     */
    public static String getPcOrderInfo(ChanInstantPayEntity entity) throws Exception{
        entity.setService("cjt_create_instant_trade");
        String orderInfo = createRequestParamsStr(entity);
        return orderInfo;
    }

    /**
     * wap支付 参数拼接 h5
     * @param entity
     * @return
     * @throws Exception
     */
    public static String getWapOrderInfo(ChanInstantPayEntity entity) throws Exception {
        entity.setService("cjt_wap_create_instant_trade");
        String orderInfo = createRequestParamsStr(entity);
        return orderInfo;
    }

    /**
     * 构造支付结构需要的参数信息
     * @param baseChanPayEntity
     * @return 返回https请求参数字符串
     */
    private static String createRequestParamsStr(BaseChanPayEntity baseChanPayEntity) throws Exception{
        Map<String, String> sPara = buildRequestPara(baseChanPayEntity);
        String sign = sPara.get("sign");
        String sign_type = sPara.get("sign_type");
        generatNameValuePair(sPara,baseChanPayEntity.get_input_charset());
        String params = createLinkString(sPara,true);
        params += "&sign=" + sign + "&sign_type="+sign_type;
        return params;
    }

    /**
     * 创建支付请求
     * @param baseChanPayEntity
     * @param gatewayUrl
     * @return
     * @throws Exception
     */
    public static String buildRequest(BaseChanPayEntity baseChanPayEntity, String gatewayUrl) throws Exception {
        // 待请求参数数组
        Map<String, String> sPara = buildRequestPara(baseChanPayEntity);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编码集
        request.setCharset(baseChanPayEntity.get_input_charset());

        request.setMethod(HttpRequest.METHOD_POST);

        request.setParameters(generatNameValuePair(sPara, baseChanPayEntity.get_input_charset()));
        request.setUrl(gatewayUrl);

        HttpResponse response = httpProtocolHandler.execute(request, null, null);
        if (response == null) {
            return null;
        }

        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * 将参数拼接成url请求参数串
     * @param params
     * @param encode
     * @return
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {
        params = paraFilter(params);
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        String charset = null == params.get("_input_charset") ? "UTF-8" : params.get("_input_charset");
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                try {
                    value = URLEncoder.encode(value, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

	 /**
	     * 除去数组中的空值和签名参数
	     *
	     * @param sArray
	     *            签名参数组
	     * @return 去掉空值与签名参数后的新签名参数组
	     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("merchant_private_key") || key.equalsIgnoreCase("pay_url") || key.equalsIgnoreCase("service_url")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    
    /**
     * 生成要请求给钱包的参数数组
     * @param baseChanPayEntity
     * @return
     */
    public static Map<String, String> buildRequestPara(BaseChanPayEntity baseChanPayEntity) throws Exception {

        if(null == baseChanPayEntity.getMerchant_private_key() || "".equals(baseChanPayEntity.getMerchant_private_key()))
            throw new BusinessException("商户私钥不能为空");

        // 除去数组中的空值和签名参数
        Map<String,String> sParaTemp = JSON.parseObject(JSON.toJSONString(baseChanPayEntity),Map.class);
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = "";
        if ("MD5".equalsIgnoreCase(baseChanPayEntity.getSign_type())) {
            mysign = buildRequestByMD5(sPara, baseChanPayEntity.getMerchant_private_key(), baseChanPayEntity.get_input_charset());
        } else if("RSA".equalsIgnoreCase(baseChanPayEntity.getSign_type())){
            mysign = buildRequestByRSA(sPara, baseChanPayEntity.getMerchant_private_key(), baseChanPayEntity.get_input_charset());
        }
        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", baseChanPayEntity.getSign_type());
        sPara.remove("merchant_private_key");
        return sPara;
    }
    
    /**
     * 生成MD5签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    private static String buildRequestByMD5(Map<String, String> sPara, String key,String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = MD5.sign(prestr, key, inputCharset);
        return mysign;
    }
    
    /**
     * 生成RSA签名结果
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    private static String buildRequestByRSA(Map<String, String> sPara, String privateKey,String inputCharset) throws Exception {
    	String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
    	String mysign = RSA.sign(prestr, privateKey, inputCharset);
        if(log.isDebugEnabled()){
            log.debug("buildRequestByRSA======加密前排序后串======" + prestr + "======加密后串======" +  mysign);
        }
        return mysign;
    }
    
    /**
     * MAP类型数组转换成NameValuePair类型
     *
     * @param properties
     *            MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset) throws Exception{
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        StringBuilder param = new StringBuilder();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), URLEncoder.encode(entry.getValue(),charset));
            param.append(entry.getKey()).append("=").append(entry.getValue() ).append("&");
        }
        if(log.isDebugEnabled()){
            log.debug(param);
        }
        return nameValuePair;
    }






    /**
     * 获取银行列表
     * @param entity
     */
    public static void channelPay(ChanInstantPayEntity entity) {
        String urlStr = "https://tpay.chanpay.com/mag/gateway/receiveOrder.do?";
        try {
            String result = buildRequest(entity, urlStr);
            if(log.isDebugEnabled()){
                log.debug(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void queryTradePay() {
       String urlStr = "https://tpay.chanpay.com/mag/gateway/receiveOrder.do?";
       ChanQueryTradePayEntity entity = new ChanQueryTradePayEntity();
       entity.setMerchant_private_key("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=");
       entity.setPartner_id("200000400007");
       entity.set_input_charset("UTF-8");
       entity.setSign_type("RSA");
       entity.setVersion("1.0");

       entity.setOuter_trade_no("240867ca53164c5d92d1117524dc3eaa");
       entity.setTrade_type("INSTANT");
       // entity.setInner_trade_no("");
       try {
           String result = buildRequest(entity, urlStr);
           if(log.isDebugEnabled()){
               log.debug(result);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public static void toCardPay() throws Exception {
       String urlStr = "https://tpay.chanpay.com/mag/gateway/receiveOrder.do?";
       ChanToCardPayEntity entity = new ChanToCardPayEntity();
       entity.setMerchant_private_key("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=");
       entity.setPartner_id("200000400007");
       entity.set_input_charset("UTF-8");
       entity.setSign_type("RSA");
       entity.setVersion("1.0");

       entity.setOuter_trade_no("240867ca53164c5d92d11175dd3eaa");
       entity.setBank_account_no(RSA.encrypt("62258801614620132", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB", entity.get_input_charset()));
       entity.setAccount_name(RSA.encrypt("段爱鑫", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB", entity.get_input_charset()));
       entity.setBank_code("ICBC");        // 银行编码 需要调用银行列表接口返回
       entity.setBank_name("中国工商银行");
       entity.setBank_branch("中国农业银行深圳南山支行");
       entity.setProvince("上海市");
       entity.setCity("南京市");
       entity.setCard_attribute("B");
       entity.setCard_type("DEBIT");
       entity.setAmount("100.00");
       entity.setNotify_url("http://2pktbbrhtj.proxy.qqbrowser.cc/withdrawalStatusNotify");

       try {
           String result = buildRequest(entity, urlStr);
           log.debug(result);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
