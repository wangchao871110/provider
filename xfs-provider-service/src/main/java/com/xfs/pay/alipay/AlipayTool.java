package com.xfs.pay.alipay;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.URL;
import com.xfs.common.Result;
import com.xfs.common.util.Arith;
import com.xfs.pay.BasePayEntity;
import com.xfs.pay.BasePayTool;
import com.xfs.pay.alipay.entity.AliPayEntity;
import com.xfs.pay.alipay.util.AliPayUtils;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.ReqBindBank;
import com.xfs.settlement.dto.ReqMerchant;
import com.xfs.settlement.dto.RespOthPay;
import com.xfs.settlement.dto.RespPay;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * 支付宝支付工具类
 * Created by yangf on 2016/5/19.
 */
public class AlipayTool extends BasePayTool {

    private static final Logger log = Logger.getLogger(AlipayTool.class);

    @Override
    protected BasePayEntity createPayEntity(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url) {
        RespOthPay respOthPay = (RespOthPay)respPay;
        AliPayEntity entity = new AliPayEntity();
        entity.setPartner(respOthPay.getPartnerId());
        entity.setSeller_id(respOthPay.getPartnerId());
        entity.set_input_charset("utf-8");
        entity.setPayment_type("1");
        entity.setReturn_url(URL.decode(return_url));
        entity.setNotify_url(notify_url);
        if(null != blPayVoucher.getUseBalance())
            entity.setTotal_fee(Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance()));
        else
            entity.setTotal_fee(blPayVoucher.getAmount());

        entity.setChannelFeeType(blPayVoucher.getChannelFeeType());

        entity.setSubject(blPayVoucher.getProductIntro());
        entity.setOut_trade_no(blPayVoucher.getOrderId());
        entity.setBody(blPayVoucher.getProductInfo());
        entity.setRsa_private(respOthPay.getRsaPrivate());
        entity.setApp_id(respOthPay.getAccountNo());
        return entity;
    }

    /**
     * ali app移动支付 参数
     * @return
     */
    @Override
    protected Result appPay(BasePayEntity entity) {
        Result result = AliPayUtils.verifyEntityParam((AliPayEntity) entity);
        if (!result.isSuccess()) {
            return result;
        }
        try{
            String orderInfo = AliPayUtils.getAppOrderInfo((AliPayEntity) entity);
            result.setDataValue("payInfo", orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ali PC网页支付 参数处理
     * @param entity
     * @return
     */
    @Override
    protected Result pcPay(BasePayEntity entity) {
        Result result = AliPayUtils.verifyEntityParam((AliPayEntity) entity);
        if (!result.isSuccess()) {
            return result;
        }
        //封装阿里支付参数vo
        try{
            String orderInfo = AliPayUtils.getPcOrderInfo((AliPayEntity) entity);
            result.setDataValue("form",orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ali 手机网站支付参数处理
     * @param entity
     * @return
     */
    @Override
    protected Result wapPay(BasePayEntity entity) {
        Result result = AliPayUtils.verifyEntityParam((AliPayEntity) entity);
        if (!result.isSuccess()) {
            return result;
        }
        //封装阿里支付参数vo
        try{
            String orderInfo = AliPayUtils.getWapOrderInfo((AliPayEntity) entity);
            result.setDataValue("form",orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 异步回调处理
     *
     * @param params 支付宝请求
     * @return success=true成功验证为支付宝请求 为true时请继续验证支付状态trade_status 状态详情参见支付宝文档  data为请求参数集合
     */
    @Override
    protected Result verify(Map<String, String> params,RespPay respPay) {
        params.remove("payType");
        RespOthPay respOthPay = (RespOthPay)respPay;
        Result result = Result.createResult().setSuccess(false);
        try{
//            if (AlipaySignature.rsaCheckV1(params, respOthPay.getRsaPublic(), "utf-8") && "TRADE_SUCCESS".equals(params.get("trade_status"))) {//验证成功
//                result.setSuccess(true);
//                result.setData(params);
//            } else {//验证失败
//                result.setSuccess(false);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
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
     * 查询第三方订单信息
     * @param entity
     * @return
     */
    @Override
    protected Result query(BasePayEntity entity) {
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
