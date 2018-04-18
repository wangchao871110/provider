package com.xfs.pay.chanpay;

import java.text.DecimalFormat;
import java.util.Map;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.common.util.Arith;
import com.xfs.pay.PayContants;
import com.xfs.pay.alipay.sign.Base64;
import com.xfs.pay.chanpay.entity.*;
import com.xfs.pay.chanpay.sign.RSA;
import com.xfs.settlement.dto.*;
import com.xfs.sps.util.HttpUtilCrawler;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.xfs.common.Result;
import com.xfs.pay.BasePayEntity;
import com.xfs.pay.BasePayTool;
import com.xfs.pay.chanpay.util.ChanPayNotify;
import com.xfs.pay.chanpay.util.ChanPayUtils;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * Created by konglc on 2016-10-24.
 * 畅捷通支付
 */
public class ChanPayTool extends BasePayTool{

    private static final Logger log = Logger.getLogger(ChanPayTool.class);

    @Override
    protected BasePayEntity createPayEntity(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url) {
        RespOthPay respOthPay = (RespOthPay)respPay;
        ChanInstantPayEntity entity = new ChanInstantPayEntity();
        entity.setMerchant_private_key(respOthPay.getRsaPrivate());
        entity.setSign_type("RSA");
        entity.setIs_anonymous("Y");
        entity.setReturn_url(URL.decode(return_url));
        entity.setPartner_id(respOthPay.getPartnerId());
        entity.setProduct_name(blPayVoucher.getProductIntro());
        entity.set_input_charset("UTF-8");
        entity.setOut_trade_no(blPayVoucher.getOrderId());
        entity.setPay_method("2");
        entity.setNotify_url(notify_url);
        //支付地址
        entity.setService_url(respOthPay.getServiceUrl());
        entity.setPay_url(respOthPay.getPayUrl());

        DecimalFormat format = new DecimalFormat("######0.00");
        if(null != blPayVoucher.getUseBalance()){
            if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
                String sMoney = format.format(Arith.add(Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance()),blPayVoucher.getChannelFee()));
                entity.setTrade_amount(sMoney);
            }else{
                String sMoney = format.format(Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance()));
                entity.setTrade_amount(sMoney);
            }
        }else {
            if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
                String sMoney = format.format(Arith.add(blPayVoucher.getAmount(),blPayVoucher.getChannelFee()));
                entity.setTrade_amount(sMoney);
            }else{
                String sMoney = format.format(blPayVoucher.getAmount());
                entity.setTrade_amount(sMoney);
            }
        }
        /**
         * 判断是否开通子商户
         */
        if(null != respOthPay.getCpartnerId()){
            entity.setSell_id(respOthPay.getCpartnerId());
            entity.setSell_id_type("MEMBER_ID");
        }
        return entity;
    }

    @Override
    protected Result appPay(BasePayEntity entity) {
        Result result = Result.createResult();
        try{
            String orderInfo = ChanPayUtils.getAppOrderInfo((ChanInstantPayEntity) entity);
            result.setDataValue("pay_url", orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Result pcPay(BasePayEntity entity) {
        Result result = Result.createResult();
        try{
            ChanInstantPayEntity chanInstantPayEntity = (ChanInstantPayEntity)entity;
            String orderInfo = ChanPayUtils.getPcOrderInfo(chanInstantPayEntity);
            result.setDataValue("pay_url", entity.getPay_url() + orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Result wapPay(BasePayEntity entity) {
        Result result = Result.createResult();
        try{
            String orderInfo = ChanPayUtils.getWapOrderInfo((ChanInstantPayEntity) entity);
            result.setDataValue("pay_url", entity.getPay_url() + orderInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected Result query(BasePayEntity entity) {
        return null;
    }

    /**
     * 异步通知校验
     * @param params
     * @return
     */
    @Override
    protected Result verify(Map<String, String> params,RespPay respPay) {
        Result result = Result.createResult();
        RespOthPay respOthPay = (RespOthPay)respPay;
        params.remove("payType");
        if (ChanPayNotify.verify(params,respOthPay.getRsaPublic()) && "TRADE_SUCCESS".equals(params.get("trade_status"))) {//验证成功
            result.setSuccess(true);
            result.setData(params);
        } else {//验证失败
            result.setSuccess(false);
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
    protected Result openmerch(ReqMerchant reqMerchant, RespOthPay respOthPay){
        Result result = Result.createResult().setSuccess(false);
        ChanMerchantPayEntity merchantPayEntity = new ChanMerchantPayEntity();
        merchantPayEntity.setService("create_enterprise_member");
        merchantPayEntity.setPartner_id(respOthPay.getPartnerId());
        merchantPayEntity.setUid(reqMerchant.getUid());
        merchantPayEntity.set_input_charset("UTF-8");
        merchantPayEntity.setMerchant_private_key(respOthPay.getRsaPrivate());
        merchantPayEntity.setLogin_name(reqMerchant.getLogin_name());
        merchantPayEntity.setCompany_nature(reqMerchant.getCompany_nature());
        merchantPayEntity.setEnterprise_name(reqMerchant.getEnterprise_name());
        merchantPayEntity.setLegal_person(RSA.encrypt(reqMerchant.getLegal_person(),respOthPay.getRsaPublic(),"UTF-8"));
        merchantPayEntity.setId_card_no(RSA.encrypt(reqMerchant.getId_card_no(),respOthPay.getRsaPublic(),"UTF-8"));
        merchantPayEntity.setRegistered_capital(reqMerchant.getRegistered_capital());
        merchantPayEntity.setAddress(reqMerchant.getAddress());
        merchantPayEntity.setCounterman(reqMerchant.getCounterman());
        merchantPayEntity.setTelephone(reqMerchant.getTelephone());
        merchantPayEntity.setEmail(reqMerchant.getEmail());
        merchantPayEntity.setMerchant_private_key(respOthPay.getRsaPrivate());
        //支付地址
        merchantPayEntity.setService_url(respOthPay.getServiceUrl());
        merchantPayEntity.setPay_url(respOthPay.getPayUrl());
        try{
            String resp = ChanPayUtils.buildRequest(merchantPayEntity,merchantPayEntity.getService_url());
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> map = JSON.parseObject(resp,ref);
            if("T".equals(map.get("is_success"))){
                result.setSuccess(true);
                result.setDataValue("member_id",map.get("member_id"));
                result.setDataValue("partner_id",map.get("partner_id"));
            }
            result.setData(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
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
        Result result = Result.createResult().setSuccess(false);
        ChanBindBankEntity chanBindBankEntity = new ChanBindBankEntity();
        chanBindBankEntity.setService("create_bank_card");
        chanBindBankEntity.setPartner_id(respOthPay.getPartnerId());
        chanBindBankEntity.setIdentity_no(reqBindBank.getIdentity_no());
        chanBindBankEntity.setCard_attribute(reqBindBank.getCard_attribute());
        chanBindBankEntity.setBank_code(reqBindBank.getBank_code());
        chanBindBankEntity.setBank_name(reqBindBank.getBank_name());
        chanBindBankEntity.setBank_branch(reqBindBank.getBank_branch());
        chanBindBankEntity.setBranch_no(reqBindBank.getBranch_no());
        chanBindBankEntity.setBank_account_no(RSA.encrypt(reqBindBank.getBank_account_no(),respOthPay.getRsaPublic(),"UTF-8"));//公钥加密
        chanBindBankEntity.setAccount_name(RSA.encrypt(reqBindBank.getAccount_name(),respOthPay.getRsaPublic(),"UTF-8"));//公钥加密
        chanBindBankEntity.setId_card_no(RSA.encrypt(reqBindBank.getId_card_no(),respOthPay.getRsaPublic(),"UTF-8"));//公钥加密
        chanBindBankEntity.setLicense_no(RSA.encrypt(reqBindBank.getLicense_no(),respOthPay.getRsaPublic(),"UTF-8"));//公钥加密
        chanBindBankEntity.setProvince(reqBindBank.getProvince());
        chanBindBankEntity.setCity(reqBindBank.getCity());
        chanBindBankEntity.setMerchant_private_key(respOthPay.getRsaPrivate());
        //支付地址
        chanBindBankEntity.setService_url(respOthPay.getServiceUrl());
        chanBindBankEntity.setPay_url(respOthPay.getPayUrl());
        try{
            String resp = ChanPayUtils.buildRequest(chanBindBankEntity,chanBindBankEntity.getService_url());
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> map = JSON.parseObject(resp,ref);
            if("T".equals(map.get("is_success"))){
                result.setSuccess(true);
            }
            result.setData(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
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
        ReqUnbindBank reqUnbindBank = (ReqUnbindBank)basePay;
        Result result = Result.createResult().setSuccess(false);
        UnbundlingBankEntity reqBindBank = new UnbundlingBankEntity();
        reqBindBank.setService("unbundling_bank_card");
        reqBindBank.setPartner_id(respOthPay.getPartnerId());
        reqBindBank.setIdentity_no(reqUnbindBank.getIdentity_no());
        reqBindBank.setBank_account_no(RSA.encrypt(reqUnbindBank.getBank_account_no(),respOthPay.getRsaPublic(),"UTF-8"));
        reqBindBank.setMerchant_private_key(respOthPay.getRsaPrivate());

        //支付地址
        reqBindBank.setService_url(respOthPay.getServiceUrl());
        reqBindBank.setPay_url(respOthPay.getPayUrl());
        try{
            String resp = ChanPayUtils.buildRequest(reqBindBank,reqBindBank.getService_url());
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> map = JSON.parseObject(resp,ref);
            if("T".equals(map.get("is_success"))){
                result.setSuccess(true);
            }
            result.setData(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
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
        ReqMerchantStatus reqMerchantStatus = (ReqMerchantStatus)basePay;
        Result result = Result.createResult().setSuccess(false);
        QueryVerifyStatusEntity reqBindBank = new QueryVerifyStatusEntity();
        reqBindBank.setService("query_verify_status");
        reqBindBank.setPartner_id(respOthPay.getPartnerId());
        reqBindBank.setIdentity_no(reqMerchantStatus.getIdentity_no());
        reqBindBank.setMerchant_private_key(respOthPay.getRsaPrivate());
        //支付地址
        reqBindBank.setService_url(respOthPay.getServiceUrl());
        reqBindBank.setPay_url(respOthPay.getPayUrl());
        try{
            String resp = ChanPayUtils.buildRequest(reqBindBank,reqBindBank.getService_url());
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> map = JSON.parseObject(resp,ref);
            if("T".equals(map.get("is_success")) && "1".equals(map.get("type_code"))){
                result.setSuccess(true);
            }
            result.setData(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  提现接口 -- 到子商户
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
        ReqWithDraw reqWithDraw = (ReqWithDraw)basePay;
        Result result = Result.createResult().setSuccess(false);
        WithDrawMemberEntity withDrawMemberEntity = new WithDrawMemberEntity();
        withDrawMemberEntity.setService("query_verify_status");
        withDrawMemberEntity.setPartner_id(respOthPay.getPartnerId());
        withDrawMemberEntity.setAccount_name(RSA.encrypt(reqWithDraw.getAccount_name(),respOthPay.getRsaPublic(),"UTF-8"));
        withDrawMemberEntity.setAmount(reqWithDraw.getAmount());
        withDrawMemberEntity.setBank_account_no(RSA.encrypt(reqWithDraw.getBank_account_no(),respOthPay.getRsaPublic(),"UTF-8"));
        withDrawMemberEntity.setMember_id(reqWithDraw.getMember_id());
        withDrawMemberEntity.setNotify_url(reqWithDraw.getNotify_url());
        withDrawMemberEntity.setOuter_trade_no(reqWithDraw.getOuter_trade_no());
        //支付地址
        withDrawMemberEntity.setService_url(respOthPay.getServiceUrl());
        withDrawMemberEntity.setPay_url(respOthPay.getPayUrl());
        try{
            String resp = ChanPayUtils.buildRequest(withDrawMemberEntity,withDrawMemberEntity.getPay_url());
            TypeReference<Map<String,String>> ref = new TypeReference<Map<String,String>>(){};
            Map<String,String> map = JSON.parseObject(resp,ref);
            if("T".equals(map.get("is_success")) && "1".equals(map.get("type_code"))){
                result.setSuccess(true);
            }
            result.setData(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
