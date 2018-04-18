package com.xfs.pay;

/**
 * 支付参数枚举类
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-07 15:32
 */
public enum PayConfig {



}

/**
 * 配置接口
 */
interface Config {

    String getPayUrl();//支付连接地址
    String getServiceUrl();//服务器连接地址
}
