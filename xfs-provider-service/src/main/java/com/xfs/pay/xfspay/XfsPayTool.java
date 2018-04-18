package com.xfs.pay.xfspay;

import com.xfs.common.Result;
import com.xfs.pay.BasePayEntity;
import com.xfs.pay.BasePayTool;
import com.xfs.pay.xfspay.util.XfsPayUtils;
import com.xfs.settlement.dto.*;
import com.xfs.settlement.model.BlPayVoucher;

import java.util.Map;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 11:36
 */
public class XfsPayTool extends BasePayTool {

    @Override
    protected Result verify(Map<String, String> params,RespPay respPay) {
        RespXfsPay respXfsPay = (RespXfsPay)respPay;
        boolean isSign = XfsPayUtils.verify(params,respXfsPay.getRsaPublic(),params.get("sign"));
        if(isSign)
            return Result.createResult().setSuccess(isSign);
        else {
            Result result = Result.createResult().setSuccess(isSign);
            result.setMsg("验证签名失败");
            result.setError("提交信息有误");
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

    @Override
    protected BasePayEntity createPayEntity(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url) {
        return null;
    }

    @Override
    protected Result appPay(BasePayEntity entity) {
        return null;
    }

    @Override
    protected Result pcPay(BasePayEntity entity) {
        return null;
    }

    @Override
    protected Result wapPay(BasePayEntity entity) {
        return null;
    }

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
