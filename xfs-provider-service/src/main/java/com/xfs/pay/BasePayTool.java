package com.xfs.pay;

import java.util.Map;

import com.xfs.common.Result;
import com.xfs.settlement.dto.*;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * Created by konglc on 2016-10-24.
 * 支付基类
 */
public abstract class BasePayTool {

    /**
     * 获取支付信息
     * @param respPay
     * @param blPayVoucher
     * @param return_url
     * @param notify_url
     * @param payType
     * @return
     */
    protected Result createPayInfo(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url, PayType payType){
        BasePayEntity entity = createPayEntity(respPay,blPayVoucher,return_url,notify_url);
        if("PC".equals(payType.getVisitType())){
            return pcPay(entity);
        }else if("APP".equals(payType.getVisitType())){
            return appPay(entity);
        }else if("WAP".equals(payType.getVisitType())){
            return wapPay(entity);
        }
        return null;
    }


    /**
     * 创建支付实体
     * @param respPay
     * @param blPayVoucher
     * @param return_url
     * @param notify_url
     * @return
     */
    protected abstract BasePayEntity createPayEntity(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url);


    /**
     * app--app端支付
     * @param entity
     * @return
     */
    protected abstract Result appPay(BasePayEntity entity);

    /**
     *  pc——web端支付
     * @return
     */
    protected abstract Result pcPay(BasePayEntity entity);

    /**
     * wap-web端支付
     * @param entity
     * @return
     */
    protected abstract Result wapPay(BasePayEntity entity);

    /**
     * 收单线下交易查询
     * @param entity
     * @return
     */
    protected abstract Result query(BasePayEntity entity);

    /**
     * 异步通知校验
     * @param params
     * @return
     */
    protected abstract  Result verify(Map<String, String> params, RespPay respPay);

    /**
     * 开通商户
     * @param reqMerchant
     * @return
     */
    protected abstract Result openmerch(ReqMerchant reqMerchant, RespOthPay respOthPay);

    /**
     * 对商户绑定银行卡
     * @param reqBindBank
     * @return
     */
    protected abstract Result bindBank(ReqBindBank reqBindBank, RespOthPay respOthPay);

    /**
     * 提现接口
     * @param basePay
     * @return
     */
    protected abstract Result withdraw(BasePay basePay, RespOthPay respOthPay);

    /**
     * 解绑银行卡
     * @param basePay
     * @return
     */
    protected abstract Result unbundling(BasePay basePay, RespOthPay respOthPay);

    /**
     * 查询子商户状态
     * @param basePay
     * @param respOthPay
     * @return
     */
    protected abstract Result queryMerchStatus(BasePay basePay, RespOthPay respOthPay);
}
