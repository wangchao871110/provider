package com.xfs.pay.wxpay;

import java.util.Map;

import com.alibaba.dubbo.common.URL;
import com.xfs.common.util.Arith;
import com.xfs.pay.PayContants;
import com.xfs.settlement.dto.*;
import org.apache.log4j.Logger;

import com.xfs.common.Result;
import com.xfs.common.util.UUIDUtil;
import com.xfs.pay.BasePayEntity;
import com.xfs.pay.BasePayTool;
import com.xfs.pay.wxpay.entity.WxPayEntity;
import com.xfs.pay.wxpay.utils.PaymentKit;
import com.xfs.pay.wxpay.utils.WxPayUtils;
import com.xfs.settlement.model.BlPayAccount;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * 微信支付
 * Created by yangf on 2016/5/23.
 */
public class WxPayTool extends BasePayTool{

    private static final Logger log = Logger.getLogger(WxPayTool.class);

    @Override
    protected BasePayEntity createPayEntity(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url) {
        RespOthPay respOthPay = (RespOthPay)respPay;
        WxPayEntity entity = new WxPayEntity();
        entity.setOpenId(respOthPay.getMemberId());
        entity.setOrderId(blPayVoucher.getOrderId());
        if(null != blPayVoucher.getUseBalance()){
            if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
                entity.setTotalFee(String.valueOf(Arith.add(Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance()),blPayVoucher.getChannelFee())));
            }else{
                entity.setTotalFee(String.valueOf(Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance())));
            }
        }else{
            if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
                entity.setTotalFee(String.valueOf(Arith.add(blPayVoucher.getAmount(),blPayVoucher.getChannelFee())));
            }else{
                entity.setTotalFee(String.valueOf(blPayVoucher.getAmount()));
            }
        }
        entity.setAppId(respOthPay.getAccountNo());
        entity.setMchId(respOthPay.getPartnerId());
        entity.setRsaPrivate(respOthPay.getRsaPrivate());
        entity.setNonceStr(UUIDUtil.randomUUID());
        entity.setNotifyUrl(notify_url);
        entity.setBody(blPayVoucher.getProductIntro());
        entity.setReturnUrl(URL.decode(return_url));
        entity.setSpbillCreateIp(respOthPay.getClientip());
        return entity;
    }

    @Override
    protected Result appPay(BasePayEntity entity) {
        return null;
    }

    @Override
    protected Result pcPay(BasePayEntity entity) {
        Result result = Result.createResult();
        try{
            result.setData(WxPayUtils.getPcOrderInfo((WxPayEntity) entity));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Result wapPay(BasePayEntity entity) {
        Result result = Result.createResult();
        try{
            result.setData(WxPayUtils.getWapOrderInfo((WxPayEntity) entity));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Result query(BasePayEntity entity) {
        return null;
    }

    @Override
    protected Result verify(Map<String, String> paramst,RespPay respPay) {
        RespOthPay respOthPay = (RespOthPay)respPay;
        Result result = Result.createResult().setSuccess(false);
        try {
            //校验
            result.setSuccess(PaymentKit.verifyNotify(paramst, respOthPay.getRsaPrivate()));
            result.setData(paramst);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return result;
    }

    /**
     *  开通商户
     *  @param   reqMerchant
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-24 17:58
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-24 17:58
     *  @updateAuthor  :
     */
    @Override
    protected Result openmerch(ReqMerchant reqMerchant, RespOthPay respOthPay) {
        return null;
    }

    /**
     *  对商户绑定银行卡
     *  @param   reqBindBank
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-24 17:58
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-24 17:58
     *  @updateAuthor  :
     */
    @Override
    protected Result bindBank(ReqBindBank reqBindBank, RespOthPay respOthPay) {
        return null;
    }

    /**
     *  提现接口
     *  @param   basePay
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-24 17:59
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-24 17:59
     *  @updateAuthor  :
     */
    @Override
    protected Result withdraw(BasePay basePay, RespOthPay respOthPay) {
        return null;
    }

    /**
     *  解绑银行卡
     *  @param   basePay
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-24 17:59
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-24 17:59
     *  @updateAuthor  :
     */
    @Override
    protected Result unbundling(BasePay basePay, RespOthPay respOthPay) {
        return null;
    }

    /**
     * 获取子商户状态
     * @param basePay
     * @param respOthPay
     * @return
     */
    @Override
    protected Result queryMerchStatus(BasePay basePay, RespOthPay respOthPay){
        return null;
    }
}
