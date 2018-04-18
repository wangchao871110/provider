package com.xfs.bill.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.settlement.dto.PayNotify;
import com.xfs.settlement.model.BlPayVoucher;
import java.util.Map;
/**
 * 账单支付后续处理服务类
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-22 21:20
 */
public interface BillPayVoucherService {

    /**
     *  账单支付成功
     *  @param   cti
     *  @param   payNotify
     *	@return 			: void
     *  @createDate  	: 2016-11-26 17:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 17:43
     *  @updateAuthor  :
     */
    public Result payBillSuccess(ContextInfo cti, PayNotify payNotify);

    /**
     *  账单支付失败
     *  @param   cti
     *  @param   payNotify
     *	@return 			: void
     *  @createDate  	: 2016-11-26 17:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 17:43
     *  @updateAuthor  :
     */
    public Result payBillFail(ContextInfo cti, PayNotify payNotify);

    /**
     *  账单线下支付中
     *  @param   cti
     *  @param   payNotify
     *	@return 			: void
     *  @createDate  	: 2016-11-26 17:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-26 17:43
     *  @updateAuthor  :
     */
    public Result payBillPaying(ContextInfo cti, PayNotify payNotify);

}
