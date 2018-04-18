package com.xfs.pay.wxpay.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.xfs.common.util.StringUtils;
import com.xfs.pay.wxpay.sign.MD5Util;

/**
 * 微信支付的统一下单工具类
 *
 * @author L.cm
 */
public class PaymentKit {

    /**
     * 组装签名的字段
     *
     * @param params
     *            参数
     * @param urlEncoder
     *            是否urlEncoder
     * @return String
     */
    public static String packageSign(Map<String, String> params, boolean urlEncoder) {

        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            if (urlEncoder) {
                try {
                    value = urlEncode(value);
                } catch (UnsupportedEncodingException e) {
                }
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * urlEncode
     *
     * @param src
     *            微信参数
     * @return String
     * @throws UnsupportedEncodingException
     *             编码错误
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {

        return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
    }

    /**
     * 生成签名
     *
     * @param params
     *            参数
     * @param paternerKey
     *            支付密钥
     * @return sign
     */
    public static String createSign(Map<String, String> params, String paternerKey) {

        // 生成签名前先去除sign
        params.remove("sign");
        String stringA = packageSign(params, false);
        String stringSignTemp = stringA + "&key=" + paternerKey;
        return MD5Util.MD5Encode(stringSignTemp,"UTF-8").toUpperCase();
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(SortedMap<String, String> packageParams, String mchkey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + mchkey);
        System.out.println(sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;

    }

    /**
     * 支付异步通知时校验sign
     *
     * @param params
     *            参数
     * @param paternerKey
     *            支付密钥
     * @return {boolean}
     */
    public static boolean verifyNotify(Map<String, String> params, String paternerKey) {

        String sign = params.get("sign");
        String localSign = PaymentKit.createSign(params, paternerKey);
        return sign.equals(localSign);
    }

    /**
     * 微信下单，map to xml
     *
     * @param params
     *            参数
     * @return String
     */
    public static String toXml(Map<String, String> params) {

        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StringUtils.isBlank(value))
                continue;
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 针对支付的xml，没有嵌套节点的简单处理
     *
     * @param xmlStr
     *            xml字符串
     * @return map集合
     */
    public static Map<String, String> xmlToMap(String xmlStr) {
        XmlHelper xmlHelper = XmlHelper.of(xmlStr);
        return xmlHelper.toMap();
    }

}