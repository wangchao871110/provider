package com.xfs.settlement.service;

import com.xfs.bill.dto.PayVoucher;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.finance.vo.BlPayVoucherDto;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.ReqCancelOrder;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.dto.ToReqPay;
import com.xfs.settlement.model.BlAlreadyCheckPayVoucher;
import com.xfs.settlement.model.BlPayVoucher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 支付凭证服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-09 20:58
 * @version 	: V1.0
 */
public interface BlPayVoucherService {
	
	public int save(ContextInfo cti, BlPayVoucher vo);
	public int insert(ContextInfo cti, BlPayVoucher vo);
	public int remove(ContextInfo cti, BlPayVoucher vo);
	public int update(ContextInfo cti, BlPayVoucher vo);
	public PageModel findPage(PageInfo pi, BlPayVoucher vo);
	public List<BlPayVoucher> findAll(BlPayVoucher vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:08:02

	/**
	 *  根据交易号获取凭证信息
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayVoucher
	 *  @createDate  	: 2016-11-19 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 14:52
	 *  @updateAuthor  :
	 */
	public BlPayVoucher queryVocherByOutTradeOrder(BasePay basePay);


	/**
	 *  根据交易号获取凭证信息
	 *  @param   voucher
	 *	@return 			: com.xfs.settlement.model.BlPayVoucher
	 *  @createDate  	: 2016-11-19 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 14:52
	 *  @updateAuthor  :
	 */
	public BlPayVoucher queryVocherByOrderId(BlPayVoucher voucher);

	/**
	 *  创建支付凭证
	 *  @param   toReqPay
	 *  @param   result
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-19 17:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 17:04
	 *  @updateAuthor  :
	 */
	public Result createPayVocher(ToReqPay toReqPay, Result result,RespXfsPay blPayBusiness) throws Exception;

	/**
	 *  支付成功操作
	 *  @param   blPayVoucher
	 *	@return 			: boolean
	 *  @createDate  	: 2016-11-22 18:19
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 18:19
	 *  @updateAuthor  :
	 */
	public boolean paymentSuccess(BlPayVoucher blPayVoucher) throws Exception;

	/**
	 *  获取充值列表
	 *  @param   pi
	 *  @param  voucher
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-22 21:13
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 21:13
	 *  @updateAuthor  :
	 */
	public PageModel queryRechargeList(PageInfo pi,BlPayVoucher voucher);

	/**
	 *  查询凭证
	 *  @param   voucher
	 *	@return 			: com.xfs.settlement.model.BlPayVoucher
	 *  @createDate  	: 2016-11-22 21:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 21:15
	 *  @updateAuthor  :
	 */
	public BlPayVoucher queryBlPayVoucher(BlPayVoucher voucher);

	/**
	 *  业务处理成功回调各个业务服务器
	 *  @param   blPayVoucher
	 *	@return 			: void
	 *  @createDate  	: 2016-11-24 21:25
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-24 21:25
	 *  @updateAuthor  :
	 */
	public void successNotifyBusin(BlPayVoucher blPayVoucher,String status);

	/**
	 *  审核下线付款
	 *  @param   blPayVoucher
	 *	@return 			: void
	 *  @createDate  	: 2016-11-22 20:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 20:39
	 *  @updateAuthor  :
	 */
	public void auditOffLinePay(BlPayVoucher blPayVoucher);

	/**
	 *  校验当前订单号是否合法
	 *  @param   toReqPay, result
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-29 15:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-29 15:59
	 *  @updateAuthor  :
	 */
	public Result checkOutTradeOrder(ToReqPay toReqPay, Result result);

	/**
	 *  取消作废订单
	 *  @param   reqCancelOrder
	 *  @param   result
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-09 12:00
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-09 12:00
	 *  @updateAuthor  :
	 */
	public Result cancelOrderByOuterTradeNo(ReqCancelOrder reqCancelOrder,Result result);


	public BlPayVoucher queryVoucherInfoById(BlPayVoucher vo);

	public PageModel queryVoucherList(PageInfo pi,PayVoucher vo);

	public Map<String,Object> queryVoucherInfo(BlPayVoucher vo);

	public Map<String,Object> queryVoucherOffLineAmount();

	public Map<String,Object> queryVoucherOnlineAmount();

	public BigDecimal queryAllPayedOnline();
	/**
	 * 根据tradeno和busid查询待核销的订单
	 * @param blPayVoucher
	 * @return
	 */
	public List<BlAlreadyCheckPayVoucher> queryVoucherByTradeNoAndBusId(BlPayVoucher blPayVoucher);

	/**
	 * 更新订单核销金额和余额
	 * @param contextInfo
	 * @param payVoucher
	 * @return
	 */
	int updateOrderByCheck(ContextInfo contextInfo, BlAlreadyCheckPayVoucher payVoucher);

	public List<BlPayVoucherDto> queryOnlinePayData(String payTimeBegin, String payTimeEnd, BlPayVoucher params, Integer offset, Integer pageSize);

	public Integer queryOnlinePayDataTotal(String payTimeBegin, String payTimeEnd, BlPayVoucher params);

	Map checkRecordDetail(String tradeNo, String busId);
}
