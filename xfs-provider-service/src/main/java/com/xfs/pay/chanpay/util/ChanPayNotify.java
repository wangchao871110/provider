package com.xfs.pay.chanpay.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.xfs.pay.alipay.sign.MD5;
import com.xfs.pay.alipay.sign.RSA;
import com.xfs.pay.chanpay.config.ChanPayConfig;

/**
 * Created by konglc on 2016-10-24.
 */
public class ChanPayNotify {

    private static final Logger log = Logger.getLogger(ChanPayNotify.class);

    public static boolean verify(Map<String, String> params,String rsaPublic) {
        String prestr = ChanPayUtils.createLinkString(params, false);
        if(log.isDebugEnabled()){
            log.debug(prestr);
        }
        boolean isSign = false;
        String sign = "";
        if(params.get("sign") != null && null != params.get("sign_type")) {
            sign = params.get("sign");
        }else {
            return isSign;
        }
        if ("MD5".equalsIgnoreCase(params.get("sign_type"))) {
            isSign =  MD5.verify(prestr, sign, rsaPublic, ChanPayConfig.input_charset);

        }
        if("RSA".equalsIgnoreCase(params.get("sign_type"))){
            isSign = RSA.verify(prestr, sign, rsaPublic, ChanPayConfig.input_charset);
        }
        return isSign;
    }

}
