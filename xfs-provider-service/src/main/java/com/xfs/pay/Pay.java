package com.xfs.pay;

import java.util.Map;

import com.xfs.common.Result;
import com.xfs.pay.alipay.AlipayTool;
import com.xfs.pay.chanpay.ChanPayTool;
import com.xfs.pay.wxpay.WxPayTool;
import com.xfs.pay.xfspay.XfsPayTool;
import com.xfs.settlement.dto.*;
import com.xfs.settlement.model.BlPayAccount;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * Created by konglc on 2016-10-24.
 * 支付
 */
public class Pay {

    private static BasePayTool aliPayTool = new AlipayTool();

    private static BasePayTool chanPayTool = new ChanPayTool();

    private static BasePayTool wxPayTool = new WxPayTool();

    private static BasePayTool xfsPayTool = new XfsPayTool();

    /**
     * 支付
     * @param respPay 第三方支付信息
     * @param blPayVoucher 支付凭证
     * @param return_url 支付完成重定向地址
     * @param notify_url 支付完成异步通知
     * @param payType 支付类型
     * @return
     */
    public static Result toPay(RespPay respPay, BlPayVoucher blPayVoucher, String return_url, String notify_url, PayType payType){
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.createPayInfo(respPay,blPayVoucher,return_url,notify_url,payType);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.createPayInfo(respPay,blPayVoucher,return_url,notify_url,payType);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.createPayInfo(respPay,blPayVoucher,return_url,notify_url,payType);
        }
        return null;
    }

    /**
     * 校验签名是否有效
     * @param paramst
     * @param payType
     * @return
     */
    public static Result verifyNotify(Map<String, String> paramst, RespPay respPay, PayType payType){
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.verify(paramst,respPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.verify(paramst,respPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.verify(paramst,respPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.verify(paramst,respPay);
        }
        return null;
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
    public static Result openmerch(ReqMerchant reqMerchant, RespOthPay respOthPay, PayType payType) {
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.openmerch(reqMerchant,respOthPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.openmerch(reqMerchant,respOthPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.openmerch(reqMerchant,respOthPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.openmerch(reqMerchant,respOthPay);
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
    public static Result bindBank(ReqBindBank reqBindBank, RespOthPay respOthPay, PayType payType) {
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.bindBank(reqBindBank,respOthPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.bindBank(reqBindBank,respOthPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.bindBank(reqBindBank,respOthPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.bindBank(reqBindBank,respOthPay);
        }
        return null;
    }

    /**
     *  提现接口
     *  @param   reqWithDraw
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-24 17:59
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-24 17:59
     *  @updateAuthor  :
     */
    public static Result withdraw(ReqWithDraw reqWithDraw, RespOthPay respOthPay, PayType payType) {
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.withdraw(reqWithDraw,respOthPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.withdraw(reqWithDraw,respOthPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.withdraw(reqWithDraw,respOthPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.withdraw(reqWithDraw,respOthPay);
        }else if(PayType.CHAN_PC_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.withdraw(reqWithDraw,respOthPay);
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
    public static Result unbundling(BasePay basePay, RespOthPay respOthPay, PayType payType) {
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.unbundling(basePay,respOthPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.unbundling(basePay,respOthPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.unbundling(basePay,respOthPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.unbundling(basePay,respOthPay);
        }
        return null;
    }

    /**
     *  查询子商户状态
     *  @param   basePay
     *  @param   respOthPay
     *  @param   payType
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-12-09 10:36
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-12-09 10:36
     *  @updateAuthor  :
     */
    public static Result queryMerchStatus(BasePay basePay, RespOthPay respOthPay, PayType payType) {
        if(PayType.ALI_APP_PAY.getPayType().equals(payType.getPayType())){
            return aliPayTool.queryMerchStatus(basePay,respOthPay);
        }else if(PayType.CHAN_APP_PAY.getPayType().equals(payType.getPayType())){
            return chanPayTool.queryMerchStatus(basePay,respOthPay);
        }else if(PayType.WX_APP_PAY.getPayType().equals(payType.getPayType())){
            return wxPayTool.queryMerchStatus(basePay,respOthPay);
        }else if(PayType.XFS_PAY.getPayType().equals(payType.getPayType())){
            return xfsPayTool.queryMerchStatus(basePay,respOthPay);
        }
        return null;
    }
}
