package com.xfs.settlement.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.xfs.bill.dto.PayVoucher;
import com.xfs.bs.util.GroovyCommonUtil;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.Arith;
import com.xfs.common.util.MakeOrderNum;
import com.xfs.common.util.StringUtils;
import com.xfs.finance.vo.BlPayVoucherDto;
import com.xfs.pay.PayContants;
import com.xfs.pay.xfspay.util.XfsPayUtils;
import com.xfs.settlement.dao.BlPayVoucherDao;
import com.xfs.settlement.dto.*;
import com.xfs.settlement.model.*;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.service.SpCmRelationService;
import groovy.lang.Binding;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付凭证服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:18
 * @version 	: V1.0
 */
@Service
public class BlPayVoucherServiceImpl implements BlPayVoucherService,IRedisKeys {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayVoucherServiceImpl.class);
	
	@Autowired
	private BlPayVoucherDao blPayVoucherDao;
	
	@Autowired
	private BlPayVoucherhistoryService blPayVoucherhistoryService;

	@Autowired
	private BlBalanceService blBalanceService;

	@Autowired
	private BlBalancedetailService blBalancedetailService;

	@Autowired
	private BlPayBusinessService blPayBusinessService;

	@Autowired
	private BlPayChannelService blPayChannelService;

	@Autowired
	private BlPayEnterpriseService blPayEnterpriseService;

	@Autowired
	private SpCmRelationService spCmRelationService;

	public int save(ContextInfo cti,  BlPayVoucher vo ){
		return blPayVoucherDao.save(cti,vo);
	}
	public int insert(ContextInfo cti,  BlPayVoucher vo ){
		return blPayVoucherDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti,  BlPayVoucher vo ){
		return blPayVoucherDao.remove(cti,vo);
	}
	public int update(ContextInfo cti,  BlPayVoucher vo ){
		return blPayVoucherDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi,BlPayVoucher vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blPayVoucherDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayVoucher> datas = blPayVoucherDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List<BlPayVoucher> findAll(BlPayVoucher vo){

		List<BlPayVoucher> datas = blPayVoucherDao.freeFind(vo);
		return datas;

	}



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
	@Override
	public BlPayVoucher queryVocherByOutTradeOrder(BasePay basePay) {
		return blPayVoucherDao.queryVocherByOutTradeOrder(basePay);
	}

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
	@Override
	public BlPayVoucher queryVocherByOrderId(BlPayVoucher voucher) {
		return blPayVoucherDao.queryVocherByOrderId(voucher);
	}

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
	@Override
	public Result createPayVocher(ToReqPay toReqPay, Result result,RespXfsPay blPayBusiness) throws Exception{
		// 校验支付凭证
		result = checkOutTradeOrder(toReqPay,result);
		if(result.isSuccess()) {
			BlPayVoucher blPayVoucher = (BlPayVoucher)result.getData().get("blPayVoucher");
			if(null != toReqPay.getAmount() && toReqPay.getAmount().compareTo(BigDecimal.ZERO) < 0) {
				result.setSuccess(false);
				result.setError("金额不合法");
			}else if(null != toReqPay.getAmount() && toReqPay.getAmount().compareTo(new BigDecimal(9999999)) > 0){
				result.setSuccess(false);
				result.setError("金额超限，请检查");
			}else {
				if(null != toReqPay.getUseBalance() && toReqPay.getUseBalance().compareTo(BigDecimal.ZERO) > 0){
					//使用余额--判断当前用户是否支持余额--获取当前用户余额
					if(PayContants.USE.equals(blPayBusiness.getIsBalance())){
						//获取账户余额
						BlBalance blBalance = new BlBalance();
						blBalance.setOwnerType(toReqPay.getBuyerType());
						blBalance.setOwner(toReqPay.getBuyerId());
						blBalance = blBalanceService.queryBalance(blBalance);
						if((null == blBalance) || (null != blBalance && toReqPay.getUseBalance().compareTo(blBalance.getAmount()) > 0 )){
							result.setSuccess(false);
							result.setError("当前余额不足");
						}else if(toReqPay.getUseBalance().compareTo(toReqPay.getAmount()) > 0){
							result.setSuccess(false);
							result.setError("抵扣金额大于支付金额");
						}
					}else {
						result.setSuccess(false);
						result.setError("当前用户不支持余额");
					}
				}
				//创建支付凭证
				if(result.isSuccess()){
					if(null == blPayVoucher) {
						blPayVoucher = new BlPayVoucher();
						blPayVoucher.setStatus(PayContants.VOUCHER_STATUS_UNPAY);
						blPayVoucher.setDr(0);
					}
					BeanUtils.copyProperties(toReqPay,blPayVoucher);
					/**
					 * 记录支付参数
					 */
					if(null != toReqPay.getUseBalance() && toReqPay.getUseBalance().compareTo(toReqPay.getAmount()) == 0)
						blPayVoucher.setPayType(PayContants.PAY_TYPE_OFFSET);
					else if(PayContants.PAY_TYPE_OFFLINE.equals(toReqPay.getPayType()) && !StringUtils.isBlank(toReqPay.getVoucherFile()))
						blPayVoucher.setStatus(PayContants.VOUCHER_STATUS_PAYING);//审核中
					/**
					 * 计算通道费
					 * 余额付款不计算通道费用
					 */
					if(null != toReqPay.getPayType() && !(PayContants.PAY_TYPE_OFFSET.equals(blPayVoucher.getPayType()) || PayContants.PAY_TYPE_OFFLINE.equals(blPayVoucher.getPayType()))){
						RespPayChannel channel = blPayChannelService.queryBusinPayChannel(blPayBusiness.getAppId(),toReqPay.getPayId());
						if(!StringUtils.isBlank(channel.getChannelFeeFormula())) {
							Binding binding = new Binding();
							binding.setVariable("amount", Arith.sub(toReqPay.getAmount(),toReqPay.getUseBalance()));
							blPayVoucher.setChannelFee(new BigDecimal(String.valueOf(GroovyCommonUtil.invokeScriptStr(channel.getChannelFeeFormula(),binding))).setScale(2,BigDecimal.ROUND_UP));
						}
						blPayVoucher.setChannelFeeType(channel.getChannelFeeType());
					}else{
						blPayVoucher.setChannelFeeType(PayContants.CHANNEL_FEE_TYPE_PLATFORM);
						blPayVoucher.setChannelFee(BigDecimal.ZERO);
					}
					/**
					 * 如果是线下支付 -- 状态为支付中
					 */
					if(PayContants.PAY_TYPE_OFFLINE.equals(blPayVoucher.getPayType())){
						blPayVoucher.setStatus(PayContants.VOUCHER_STATUS_PAYING);
					}
					blPayVoucher.setBusId(blPayBusiness.getBusId());
					blPayVoucher.setPayId(toReqPay.getPayId());
					blPayVoucher.setPayeeName(StringUtils.isBlank(toReqPay.getPayeeName()) ? PayContants.DEFAULT_PAYEE_NAME : toReqPay.getPayeeName());
					blPayVoucher.setPayeeType(StringUtils.isBlank(toReqPay.getPayeeType()) ? PayContants.BUSIN_TYPE_XFS  : toReqPay.getPayeeType());
					if(StringUtils.isBlank(blPayVoucher.getOrderId())) {
						blPayVoucher.setOrderId(MakeOrderNum.makeOrderNum());
						insert(null, blPayVoucher);
					}else {
						update(null, blPayVoucher);
					}
					/**
					 * 记录支付凭证历史记录
					 */
					BlPayVoucherhistory blPayVoucherhistory = new BlPayVoucherhistory();
					BeanUtils.copyProperties(blPayVoucher,blPayVoucherhistory);
					blPayVoucherhistory.setId(null);
					blPayVoucherhistoryService.insert(null,blPayVoucherhistory);
				}
			}
			/**
			 * 余额直接支付
			 * 下线支付待审核通知各业务
			 */
			if(result.isSuccess() && null != blPayVoucher.getUseBalance() && toReqPay.getAmount().subtract(blPayVoucher.getUseBalance()).compareTo(BigDecimal.ZERO) == 0){
				boolean isok = paymentSuccess(blPayVoucher);
				result.setSuccess(isok);
			}else if(PayContants.PAY_TYPE_OFFLINE.equals(toReqPay.getPayType())){//当前为线下支付
				try{
					successNotifyBusin(blPayVoucher,PayContants.NOTIFY_STATUS_PAYING);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			result.setDataValue("blPayVoucher",blPayVoucher);
		}
		return result;
	}

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
	@Override
	public boolean paymentSuccess(BlPayVoucher blPayVoucher) throws Exception{
		boolean issuccess = false;
		if(null != blPayVoucher && (blPayVoucher.getStatus() == PayContants.VOUCHER_STATUS_UNPAY || blPayVoucher.getStatus() == PayContants.VOUCHER_STATUS_PAYING)){
			/**
			 * 获取当前企业和服务机构直接关系
			 * 获取当前服务机构是否存在子商户
			 */
			if(PayContants.BUSIN_TYPE_SERVICE.equals(blPayVoucher.getPayeeType())){
				Map<String, Object> enterprise = blPayEnterpriseService.queryEnterAccount(Integer.parseInt(blPayVoucher.getPayeeId()));
				if(null != enterprise && !enterprise.isEmpty() && "USE".equals(enterprise.get("state")))
					blPayVoucher.setMoneyFlow("MMEMBER");
			}
			if(PayContants.BUSIN_TYPE_CORP.equals(blPayVoucher.getBuyerType()) && PayContants.BUSIN_TYPE_SERVICE.equals(blPayVoucher.getPayeeType())){
				SpCmRelation spCmRelation = spCmRelationService.findBySpIdAndCpId(Integer.parseInt(blPayVoucher.getBuyerId()),Integer.parseInt(blPayVoucher.getPayeeId()));
				if(null != spCmRelation && "2".equals(spCmRelation.getContractType()))
					blPayVoucher.setDrawer("ThirdParty");
			}
			//判断当前支付凭证类型  ： 充值   支付
			if(PayContants.TRADE_TYPE_RECHARGE_FEE.equals(blPayVoucher.getTradeType())){
				//只充值
				issuccess = recharge(blPayVoucher);
			}else {
				//支付
				issuccess = pay(blPayVoucher);
			}
		}
		if(!issuccess)
			throw new BusinessException("支付失败");
		if(issuccess){
			try{
				//付款成功 异步通知各个业务服务器
				successNotifyBusin(blPayVoucher,PayContants.NOTIFY_STATUS_OK);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return issuccess;
	}

	/**
	 *  未审核通过下线付款
	 *  @param   blPayVoucher
	 *	@return 			: void
	 *  @createDate  	: 2016-11-22 20:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 20:39
	 *  @updateAuthor  :
	 */
	@Override
	public void auditOffLinePay(BlPayVoucher blPayVoucher){
		blPayVoucher.setStatus(PayContants.VOUCHER_STATUS_FAIL);
		try{
			//付款成功 异步通知各个业务服务器
			successNotifyBusin(blPayVoucher,PayContants.NOTIFY_STATUS_FAIL);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

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
	public Result checkOutTradeOrder(ToReqPay toReqPay, Result result){
		// 获取支付凭证
		BlPayVoucher blPayVoucher = queryVocherByOutTradeOrder(toReqPay);
		if (null != blPayVoucher && PayContants.VOUCHER_STATUS_OK.equals(blPayVoucher.getStatus())) {//存在支付凭证并且当前订单已经支付
			result.setSuccess(false);
			result.setError("当前订单已经支付!");
		}else if(null != blPayVoucher && PayContants.VOUCHER_STATUS_FAIL.equals(blPayVoucher.getStatus()) && !PayContants.PAY_TYPE_OFFLINE.equals(blPayVoucher.getPayType())){
			result.setSuccess(false);
			result.setError("当前订单支付失败!");
		}else if(null != blPayVoucher && PayContants.VOUCHER_STATUS_PAYING.equals(blPayVoucher.getStatus())){
			result.setSuccess(false);
			result.setError("支付中......！");
		}
		result.setDataValue("blPayVoucher",blPayVoucher);
		return result;
	}

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
	public Result cancelOrderByOuterTradeNo(ReqCancelOrder reqCancelOrder,Result result){
		BlPayVoucher blPayVoucher = queryVocherByOutTradeOrder(reqCancelOrder);
		if (null == blPayVoucher) {
			result.setSuccess(false);
			result.setError("无此支付凭证信息");
		}else{
			blPayVoucher.setDr(1);
			blPayVoucher.setStatus(2);
			reportVoucherHistory(blPayVoucher);
			result.setSuccess(true);
		}
		return result;
	}

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
	@Override
	public void successNotifyBusin(BlPayVoucher blPayVoucher,String status){
		TypeReference<Map<String,String>> mapref = new TypeReference<Map<String,String>>(){};
		RespXfsPay blPayBusiness = blPayBusinessService.queryBusinByBusId(blPayVoucher.getBusId());
		PayNotify payNotify = new PayNotify();
		payNotify.setAppId(blPayBusiness.getAppId());
		payNotify.setOuterTradeNo(blPayVoucher.getOuterTradeNo());
		payNotify.setBuyerId(blPayVoucher.getBuyerId());
		payNotify.setBuyerName(blPayVoucher.getBuyerName());
		payNotify.setBuyerType(blPayVoucher.getBuyerType());
		payNotify.setCreateBy(String.valueOf(blPayVoucher.getCreateBy()));
		payNotify.setPayeeId(blPayVoucher.getBuyerId());
		payNotify.setTradeType(blPayVoucher.getTradeType());
		payNotify.setPayeeType(blPayVoucher.getPayeeType());
		payNotify.setPayeeName(blPayVoucher.getPayeeName());
		payNotify.setPayType(blPayVoucher.getPayType());
		payNotify.setVoucherFile(blPayVoucher.getVoucherFile());
		payNotify.setPayId(String.valueOf(blPayVoucher.getPayId()));
		if(!StringUtils.isBlank(blPayVoucher.getNotifyInfo())){
			Map<String,String> reqMap = JSON.parseObject(blPayVoucher.getNotifyInfo(),mapref);
			payNotify.setBuyerOpenId(reqMap.get("openid"));
		}
		payNotify.setAttach(blPayVoucher.getAttach());
		payNotify.setStatus(status);
		payNotify.setCreateBy(String.valueOf(blPayVoucher.getCreateBy()));
		payNotify.setRemark(blPayVoucher.getRemark());
		payNotify.setSign(XfsPayUtils.sign(payNotify,blPayBusiness.getRsaPrivate()));
		String json = JSON.toJSONString(payNotify);
		Map<String,String> reqMap = JSON.parseObject(json,mapref);
		if(!StringUtils.isBlank(blPayVoucher.getNotifyUrl())){
				String respJson = HttpClientUtil.doPost(URL.decode(blPayVoucher.getNotifyUrl()),reqMap);
				if(StringUtils.isBlank(respJson)){
					//修改支付凭证异步通知状态
					reportVoucherHistory(blPayVoucher);
				}else {
					TypeReference<Result> ref = new TypeReference<Result>(){};
					Result result = JSON.parseObject(respJson,ref);
					if(result.isSuccess()){
						//修改支付凭证异步通知状态
						blPayVoucher.setIsNotify(PayContants.USE);
						reportVoucherHistory(blPayVoucher);
					}
				}
		}else{
			reportVoucherHistory(blPayVoucher);
		}
	}

	/**
	 *  处理充值逻辑
	 *  @param   blPayVoucher
	 *	@return 			: boolean
	 *  @createDate  	: 2016-11-22 20:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 20:04
	 *  @updateAuthor  :
	 */
	private boolean recharge(BlPayVoucher blPayVoucher) throws Exception{
		boolean issuccess = true;
		/**
		 * 触发企业充值、支付同步锁
		 */
		while (RedisUtil.getLock(COMMON_CORP_PAY_LOCK+blPayVoucher.getBuyerId(), 30000L) != 1) {// 获取当前锁
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//修改当前订单状态并记录快照
		blPayVoucher.setStatus(PayContants.VOUCHER_STATUS_OK);
		blPayVoucher.setPayTime(new Date());
		reportVoucherHistory(blPayVoucher);

		/**
		 * 记录第三方流水信息
		 */
		//先做一笔充值
		BigDecimal charge_amount = Arith.sub(blPayVoucher.getAmount());
		if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
			/**
			 * 支付方承担通道费
			 */
			charge_amount = Arith.add(charge_amount,blPayVoucher.getChannelFee());
		}
		handleThirdBalance(blPayVoucher.getOrderId(),blPayVoucher.getBuyerId(),blPayVoucher.getBuyerType(),charge_amount,PayContants.TRADE_TYPE_RECHARGE_FEE,PayContants.BALANCE_OPT_ADD);

		/**
		 * 记录通道费
		 */
		if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
			/**
			 * 支付方承担通道费
			 */
			handleThirdBalance(blPayVoucher.getOrderId(),blPayVoucher.getBuyerId(),blPayVoucher.getBuyerType(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_SUB);
			/**
			 * 平台入款通道费钱
			 */
			handleXFSBalance(blPayVoucher.getOrderId(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_ADD);
		}else if(PayContants.CHANNEL_FEE_TYPE_PLATFORM.equals(blPayVoucher.getChannelFeeType())){
			/**
			 * 平台承担通道费
			 */
			handleXFSBalance(blPayVoucher.getOrderId(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_SUB);
		}

		/**
		 * 释放同步锁
		 */
		RedisUtil.delLock(COMMON_CORP_PAY_LOCK+blPayVoucher.getBuyerId()); // 释放锁
		return issuccess;
	}

	/**
	 *  处理支付逻辑
	 *  @param   blPayVoucher
	 *	@return 			: boolean
	 *  @createDate  	: 2016-11-22 20:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 20:21
	 *  @updateAuthor  :
	 */
	private boolean pay(BlPayVoucher blPayVoucher) throws Exception{
		boolean issuccess = true;
		/**
		 * 触发企业充值、支付同步锁
		 */
		while (RedisUtil.getLock(COMMON_CORP_PAY_LOCK+blPayVoucher.getBuyerId(), 30000L) != 1) {// 获取当前锁
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//修改当前订单状态并记录快照
		blPayVoucher.setStatus(1);
		blPayVoucher.setPayTime(new Date());
		reportVoucherHistory(blPayVoucher);
		/**
		 * 操作当前账户余额
		 * 当前为非余额支付
		 */
		if(!PayContants.PAY_TYPE_OFFSET.equals(blPayVoucher.getPayType())){
			//先做一笔充值
			BigDecimal charge_amount = Arith.sub(blPayVoucher.getAmount(),blPayVoucher.getUseBalance());
			if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
				/**
				 * 支付方承担通道费
				 */
				charge_amount = Arith.add(charge_amount,blPayVoucher.getChannelFee());
			}
			handleThirdBalance(blPayVoucher.getOrderId(),blPayVoucher.getBuyerId(),blPayVoucher.getBuyerType(),charge_amount,PayContants.TRADE_TYPE_RECHARGE_FEE,PayContants.BALANCE_OPT_ADD);
		}
		//再做一笔支付
		handleThirdBalance(blPayVoucher.getOrderId(),blPayVoucher.getBuyerId(),blPayVoucher.getBuyerType(),blPayVoucher.getAmount(),PayContants.TRADE_TYPE_PAY_FEE,PayContants.BALANCE_OPT_SUB);
		/**
		 * 操作薪福社账户余额 -- 薪福社收款
		 */
		handleXFSBalance(blPayVoucher.getOrderId(),blPayVoucher.getAmount(),PayContants.TRADE_TYPE_COLLECT,PayContants.BALANCE_OPT_ADD);

		/**
		 * 记录通道费
		 */
		if(PayContants.CHANNEL_FEE_TYPE_THIRDPARTY.equals(blPayVoucher.getChannelFeeType())){
			/**
			 * 支付方承担通道费
			 */
			handleThirdBalance(blPayVoucher.getOrderId(),blPayVoucher.getBuyerId(),blPayVoucher.getBuyerType(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_SUB);
			/**
			 * 平台入款通道费钱
			 */
			handleXFSBalance(blPayVoucher.getOrderId(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_ADD);
		}else if(PayContants.CHANNEL_FEE_TYPE_PLATFORM.equals(blPayVoucher.getChannelFeeType())){
			/**
			 * 平台承担通道费
			 */
			handleXFSBalance(blPayVoucher.getOrderId(),blPayVoucher.getChannelFee(),PayContants.TRADE_TYPE_CHANNEL_FEE,PayContants.BALANCE_OPT_SUB);
		}

		/**
		 * 释放同步锁
		 */
		RedisUtil.delLock(COMMON_CORP_PAY_LOCK+blPayVoucher.getBuyerId()); // 释放锁
		return issuccess;
	}

	/**
	 * 操作薪福社余额并记录流水明细
	 * @param order_id
	 * @param amount
	 * @param tradeType
	 * @throws Exception
     */
	private void handleXFSBalance(String order_id,BigDecimal amount,String tradeType,String optType) throws Exception{
		if(null == amount || amount.compareTo(BigDecimal.ZERO) <= 0)
			return;
		//触发薪福社支付同步锁
		while (RedisUtil.getLock(COMMON_XFS_PAY_LOCK, 10000L) != 1) {// 获取当前锁
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 1.获取薪福社当前余额
		 * 2.操作薪福社余额
		 * 3.记录余额流水明细
		 */
		try{
			//获取薪福社余额帐号
			BlBalance xfs_blBalance = new BlBalance();
			xfs_blBalance.setOwnerType(PayContants.BUSIN_TYPE_XFS);
			BlBalance xfs_curr_blBalance = blBalanceService.queryBalance(xfs_blBalance);
			if(null == xfs_curr_blBalance){
				xfs_blBalance.setAmount(new BigDecimal(0.0));
				blBalanceService.insert(null,xfs_blBalance);
				xfs_curr_blBalance = xfs_blBalance;
			}
			//操作薪福社余额
			if(PayContants.BALANCE_OPT_ADD.equals(optType))
				xfs_curr_blBalance.setAmount(xfs_curr_blBalance.getAmount().add(amount));
			else
				xfs_curr_blBalance.setAmount(xfs_curr_blBalance.getAmount().subtract(amount));
			blBalanceService.update(null,xfs_curr_blBalance);
			//记录薪福社余额流水 -- 支付
			BlBalancedetail xfs_balancedetail = new BlBalancedetail();
			if(PayContants.BALANCE_OPT_ADD.equals(optType))
				xfs_balancedetail.setAmount(amount);
			else
				xfs_balancedetail.setAmount(Arith.sub(BigDecimal.ZERO,amount));
			xfs_balancedetail.setOwnerType(PayContants.BUSIN_TYPE_XFS);
			xfs_balancedetail.setBalance(xfs_curr_blBalance.getAmount());
			xfs_balancedetail.setOrderId(order_id);
			xfs_balancedetail.setTradeType(tradeType);
			blBalancedetailService.insert(null,xfs_balancedetail);
		}catch (Exception e){
			RedisUtil.delLock(COMMON_XFS_PAY_LOCK); // 释放锁
			throw new BusinessException("修改薪福社余额异常");
		}
		RedisUtil.delLock(COMMON_XFS_PAY_LOCK); // 释放锁
	}

	/**
	 * 操作第三方余额并记录流水明细
	 * @param order_id
	 * @param buyerId
	 * @param buyerType
	 * @param amount
	 * @param tradeType
	 * @param optType
     * @throws Exception
     */
	private void handleThirdBalance(String order_id,String buyerId,String buyerType,BigDecimal amount,String tradeType,String optType) throws Exception {
		if(null == amount || amount.compareTo(BigDecimal.ZERO) <= 0)
			return;
		//获取企业余额帐号
		BlBalance blBalance = new BlBalance();
		blBalance.setOwner(buyerId);
		blBalance.setOwnerType(buyerType);
		BlBalance curr_blBalance = blBalanceService.queryBalance(blBalance);
		if(null == curr_blBalance){
			blBalance.setAmount(new BigDecimal(0.0));
			blBalanceService.insert(null,blBalance);
			curr_blBalance = blBalance;
		}
		//修改余额
		if(PayContants.BALANCE_OPT_ADD.equals(optType))
			curr_blBalance.setAmount(curr_blBalance.getAmount().add(amount));
		else if(PayContants.BALANCE_OPT_SUB.equals(optType) && curr_blBalance.getAmount().compareTo(amount) >= 0) {
			curr_blBalance.setAmount(curr_blBalance.getAmount().subtract(amount));
		}else {
			throw new BusinessException("账户余额不足");
		}
		blBalanceService.update(null,curr_blBalance);
		//记录余额流水 -- 充值
		BlBalancedetail balancedetail = new BlBalancedetail();
		if(PayContants.BALANCE_OPT_ADD.equals(optType))
			balancedetail.setAmount(amount);
		else
			balancedetail.setAmount(Arith.sub(BigDecimal.ZERO,amount));
		balancedetail.setOwnerType(buyerType);
		balancedetail.setOwner(String.valueOf(buyerId));
		balancedetail.setBalance(curr_blBalance.getAmount());
		balancedetail.setOrderId(order_id);
		balancedetail.setTradeType(tradeType);
		balancedetail.setCreateDt(new Date());
		balancedetail.setModifyDt(new Date());
		blBalancedetailService.insert(null,balancedetail);
	}

	/**
	 *  记录支付凭证快照
	 *  @param   blPayVoucher
	 *	@return 			: void
	 *  @createDate  	: 2016-11-22 20:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 20:39
	 *  @updateAuthor  :
	 */
	private void reportVoucherHistory(BlPayVoucher blPayVoucher){
		//修改交易状态--支付完成
		blPayVoucherDao.update(null,blPayVoucher);
		//新增交易快照信息
		BlPayVoucherhistory blPayVoucherhistory = new BlPayVoucherhistory();
		BeanUtils.copyProperties(blPayVoucher,blPayVoucherhistory);
		blPayVoucherhistory.setId(null);
		blPayVoucherhistoryService.insert(null,blPayVoucherhistory);
	}

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
	@Override
	public PageModel queryRechargeList(PageInfo pi,BlPayVoucher voucher) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blPayVoucherDao.queryRechargeCount(voucher);
		pm.setTotal(total);
		List<BlPayVoucher> datas = blPayVoucherDao.queryRechargeList(voucher, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public BlPayVoucher queryBlPayVoucher(BlPayVoucher voucher) {
		return blPayVoucherDao.findByPK(voucher);
	}

	/**
	 *  查询支付凭证列表
	 *  @param   pi, vo
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-22 21:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 21:16
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel queryVoucherList(PageInfo pi, PayVoucher vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blPayVoucherDao.queryVoucherCount(vo);
		pm.setTotal(total);
		List<Map<String,Object>> datas = blPayVoucherDao.queryVoucherList(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	@Override
	public Map<String,Object> queryVoucherInfo(BlPayVoucher vo) {
		return blPayVoucherDao.queryVoucherInfoById(vo);
	}

	@Override
	public Map<String, Object> queryVoucherOffLineAmount() {
		return blPayVoucherDao.queryVoucherOffLineAmount();
	}

	@Override
	public Map<String, Object> queryVoucherOnlineAmount() {
		return blPayVoucherDao.queryVoucherOnLineAmount();
	}

	@Override
	public List<BlAlreadyCheckPayVoucher> queryVoucherByTradeNoAndBusId(BlPayVoucher blPayVoucher) {
		return blPayVoucherDao.queryVoucherByTradeNoAndBusId(blPayVoucher);
	}

	@Override
	public int updateOrderByCheck(ContextInfo contextInfo, BlAlreadyCheckPayVoucher payVoucher) {
		return blPayVoucherDao.updateOrderByCheck(contextInfo,payVoucher);
	}

	@Override
	public BlPayVoucher queryVoucherInfoById(BlPayVoucher vo) {
		return blPayVoucherDao.findByPK(vo);
	}

	@Override
	public BigDecimal queryAllPayedOnline() {
		return blPayVoucherDao.queryAllPayedOnline();
	}

	@Override
	public List<BlPayVoucherDto> queryOnlinePayData(String payTimeBegin, String payTimeEnd, BlPayVoucher params, Integer offset, Integer pageSize) {
		Map<String, Object> param = beanToMap(params);
		param.put("payTimeBegin",payTimeBegin);
		param.put("payTimeEnd",payTimeEnd);
		param.put("offset",offset);
		param.put("pageSize", pageSize);
		return blPayVoucherDao.queryOnlinePayData(param);
	}

	@Override
	public Integer queryOnlinePayDataTotal(String payTimeBegin, String payTimeEnd, BlPayVoucher params) {
		Map<String, Object> param = beanToMap(params);
		param.put("payTimeBegin",payTimeBegin);
		param.put("payTimeEnd",payTimeEnd);
		return blPayVoucherDao.queryOnlinePayDataTotal(param);
	}

    @Override
    public Map checkRecordDetail(String tradeNo, String busId) {
		Map<String,String> params= Maps.newHashMap();
		params.put("tradeNo",tradeNo);
		params.put("busId",busId);

		return blPayVoucherDao.checkRecordDetail(params);
    }

    public Map<String, Object> beanToMap(Object bean){
		Map<String, Object> map = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					Object object = propertyUtilsBean.getNestedProperty(bean, name);
					if(object != null && !object.equals(""))
						map.put(name, object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
