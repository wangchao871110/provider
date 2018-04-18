package com.xfs.pay;

/**
 * 支付常量
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-12-01 20:32
 */
public class PayContants {

    /**
     * 支付完成第三方异步通知地址
     */
    public static String ALI_PC_PAY_NOTIFY_URL = "ali_pc_pay_notify_url";
    /**
     * 薪福社统一收银台充值地址
     */
    public static String XFS_RECHARGE_VOUCHER_URL = "xfs_recharge_voucher_url";

    /**
     * 薪福社统一收银台
     */
    public static String XFS_CASHIER_VOUCHER_URL = "xfs_cashier_voucher_url";

    public static String DEFAULT_PAYEE_NAME = "北京用友薪福社云科技有限公司";//默认收款方状态

    /**
     * 支付类型
     */
    public static String PAY_TYPE_ALIPAY = "ALIPAY";//阿里支付
    public static String PAY_TYPE_WXPAY = "WXPAY";//微信支付
    public static String PAY_TYPE_CHANPAY = "CHANPAY";//畅捷支付支付
    public static String PAY_TYPE_OFFLINE = "OFFLINE";//下行支付
    public static String PAY_TYPE_OFFSET = "OFFSET";//余额划款


    /**
     * 交易类型
     */
    public static String TRADE_TYPE_PAY_FEE = "PAY_FEE";//支付
    public static String TRADE_TYPE_RECHARGE_FEE = "RECHARGE_FEE";//充值
    public static String TRADE_TYPE_COLLECT = "COLLECT_FEE";//收款
    public static String TRADE_TYPE_CHANNEL_FEE = "CHANNEL_FEE";//通道费
    /**
     * 业务类型
     */
    public static String BUSIN_TYPE_XFS = "XFS";//XFS业务类型
    public static String BUSIN_TYPE_SERVICE = "SERVICE";//服务机构
    public static String BUSIN_TYPE_CORP = "CORP";//企业客户
    public static String BUSIN_TYPE_PERSONAL = "PERSONAL";//个人

    /**
     * 是否支持
     */
    public static String USE = "Y";
    public static String UN_USE = "N";

    /**
     * 异步通知状态
     */
    public static String NOTIFY_STATUS_OK = "SUCCESS";
    public static String NOTIFY_STATUS_FAIL = "FAIL";
    public static String NOTIFY_STATUS_PAYING = "PAYING";

    /**
     * 当前支付凭证状态
     */
    public static Integer VOUCHER_STATUS_OK = 1;
    public static Integer VOUCHER_STATUS_FAIL = 2;
    public static Integer VOUCHER_STATUS_PAYING = 3;
    public static Integer VOUCHER_STATUS_UNPAY = 0;

    /**
     * 通道费用
     */
    public static String CHANNEL_FEE_TYPE_PLATFORM = "PLATFORM";
    public static String CHANNEL_FEE_TYPE_THIRDPARTY = "THIRDPARTY";

    /**
     * 余额操作类型
     */
    public static String BALANCE_OPT_ADD = "ADD";
    public static String BALANCE_OPT_SUB = "SUB";


}
