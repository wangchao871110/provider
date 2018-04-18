package com.xfs.pay.xfspay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.common.util.StringUtils;
import com.xfs.pay.xfspay.sign.RSA;
import com.xfs.settlement.dto.BasePay;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 薪福社支付工具类
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-26 12:34
 */
public class XfsPayUtils {

    private static Logger logger = Logger.getLogger(XfsPayUtils.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     *  签名
     *  @param   basePay, privateKey
     *	@return 			: java.lang.String
     *  @createDate  	: 2016-11-26 12:46
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 12:46
     *  @updateAuthor  :
     */
    public static String sign(BasePay basePay, String privateKey){
        String prestr = buildRequestSign(paraFilter(convertMap(basePay)));
        logger.info("==============XFS签名"+prestr);
        return RSA.sign(prestr, privateKey, DEFAULT_CHARSET);
    }

    /** 校验签名
     *  @param   basePay
     *	@return 			: boolean
     *  @createDate  	: 2016-11-26 12:47
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 12:47
     *  @updateAuthor  :
     */
    public static boolean verify(BasePay basePay,String publicKey) {
        return verify(convertMap(basePay),publicKey,basePay.getSign());
    }

    /**
     *  校验签名
     *  @param   map, publicKey, sign
     *	@return 			: boolean
     *  @createDate  	: 2016-11-26 13:00
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 13:00
     *  @updateAuthor  :
     */
    public static boolean verify(Map<String,String> map,String publicKey,String sign) {
        if(null == sign)
            return false;
        String preSignStr = buildRequestSign(paraFilter(map));
        logger.info("==============XFS签证签名"+preSignStr);
        return RSA.verify(preSignStr, sign, publicKey, DEFAULT_CHARSET);
    }

    /**
     *  过滤加密字符串
     *  @param   map
     *	@return 			: java.util.Map<java.lang.String,java.lang.String>
     *  @createDate  	: 2016-11-26 12:52
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 12:52
     *  @updateAuthor  :
     */
    public static Map<String,String> paraFilter(Map<String,String> map){
        //移除不需要加密字段
        map.remove("sign");
        map.remove("useBalance");
        map.remove("payType");
        map.remove("payId");
        map.remove("voucherFile");
        if("RECHARGE_FEE".equals(map.get("tradeType")))//充值
            map.remove("amount");

        Map<String, String> result = new HashMap<String, String>();
        for(Map.Entry<String,String> entry :  map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(StringUtils.isBlank(value))
                continue;
            result.put(key,value);
        }
        return result;
    }

    /**
     *  转化为Map
     *  @param   basePay
     *	@return 			: java.util.Map<java.lang.String,java.lang.String>
     *  @createDate  	: 2016-11-26 12:58
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 12:58
     *  @updateAuthor  :
     */
    public static Map<String,String> convertMap(BasePay basePay){
        TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
        Map<String,String> map = JSON.parseObject(JSON.toJSONString(basePay),ref);
        return map;
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
            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

}
