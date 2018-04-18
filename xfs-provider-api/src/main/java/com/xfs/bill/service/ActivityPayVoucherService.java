package com.xfs.bill.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.settlement.dto.PayNotify;
import com.xfs.settlement.model.BlPayVoucher;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 活动优惠券支付后操作服务类
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-22 21:29
 */
public interface ActivityPayVoucherService {

    /**
     *  获取3600优惠券详情
     *  @param   lottery_code
     *  @param    cpid
     *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
     *  @createDate  	: 2016-11-30 19:24
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-30 19:24
     *  @updateAuthor  :
     */
    public Map<String, Object> queryPrizeInfo(String lottery_code, Integer cpid);

    /**
     *  优惠券支付成功
     *  @param   cti
     *  @param    payNotify
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-30 19:25
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-30 19:25
     *  @updateAuthor  :
     */
    public Result payActivitySuccess(ContextInfo cti, PayNotify payNotify);

}
