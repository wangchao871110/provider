package com.xfs.settlement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.activity.service.BdUserPrizeRecordService;
import com.xfs.bill.service.BillPayVoucherService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.service.CmCorpService;
import com.xfs.settlement.dto.PayNotify;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.xfs.base.model.SysMessage;
import com.xfs.bill.model.SpsBill;
import com.xfs.bill.service.SpsBillService;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 账单支付服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:19
 * @version 	: V1.0
 */
@Service
public class BillPayVoucherServiceImpl implements BillPayVoucherService,IRedisKeys,IStaticVarConstant {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BillPayVoucherServiceImpl.class);

	@Autowired
	private SpsBillService spsBillService;

	@Autowired
	private BdUserPrizeRecordService bdUserPrizeRecordService;

	@Autowired
	private CmCorpService cmCorpService;

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
	@Override
	public Result payBillSuccess(ContextInfo cti, PayNotify payNotify) {
		SpsBill spsBill = spsBillService.queryBillByBillNum(payNotify.getOuterTradeNo());
		//如果当前账单存在分包账单,对分包账单修改状态，核销分包账单
		if(!StringUtils.isBlank(spsBill.getEntrustedBillids())){
			for(String bill_id : spsBill.getEntrustedBillids().split(",")){
				SpsBill entrusted_bill = new SpsBill();
				entrusted_bill.setBillId(Integer.parseInt(bill_id));
				entrusted_bill = spsBillService.findByPK(entrusted_bill);
				//修改状态
				entrusted_bill.setFeePayStatus(1);//已支付
				entrusted_bill.setBillState("PAID");
				entrusted_bill.setCheckedType("AUTO");
				entrusted_bill.setActualOfficeFee(entrusted_bill.getPerpayOfficeFee());
				entrusted_bill.setActualServiceFee(entrusted_bill.getPerpayServiceFee());
				spsBillService.update(cti,entrusted_bill);
			}
		}
		//修改总包账单状态
		spsBill.setFeePayStatus(1);//已支付
		spsBill.setCheckedType("AUTO");
		spsBill.setBillState("PAID");
		spsBill.setActualOfficeFee(spsBill.getPerpayOfficeFee());
		spsBill.setActualServiceFee(spsBill.getPerpayServiceFee());
		spsBillService.update(cti,spsBill);
		/**
		 * 修改使用优惠券的状态
		 */
		bdUserPrizeRecordService.updateByOrder(cti,payNotify.getOuterTradeNo());
		sendSysMessage(cti,payNotify);
		return Result.createResult().setSuccess(true);
	}

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
	@Override
	public Result payBillFail(ContextInfo cti, PayNotify payNotify) {
		SpsBill spsBill = spsBillService.queryBillByBillNum(payNotify.getOuterTradeNo());
		//如果当前账单存在分包账单,对分包账单修改状态，核销分包账单
		if(!StringUtils.isBlank(spsBill.getEntrustedBillids())){
			for(String bill_id : spsBill.getEntrustedBillids().split(",")){
				SpsBill entrusted_bill = new SpsBill();
				entrusted_bill.setBillId(Integer.parseInt(bill_id));
				entrusted_bill = spsBillService.findByPK(entrusted_bill);
				//修改状态
				entrusted_bill.setFeePayStatus(0);//未支付
				spsBillService.update(cti,entrusted_bill);
			}
		}
		//修改总包账单状态
		spsBill.setFeePayStatus(0);//未支付
		spsBillService.update(cti,spsBill);
		sendSysMessage(cti,payNotify);
		return Result.createResult().setSuccess(true);
	}

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
	@Override
	public Result payBillPaying(ContextInfo cti, PayNotify payNotify) {
		/**
		 * 修改账单状态处于支付中
		 */
		SpsBill spsBill = spsBillService.queryBillByBillNum(payNotify.getOuterTradeNo());
		//如果当前账单存在分包账单,对分包账单修改状态，核销分包账单
		if(!StringUtils.isBlank(spsBill.getEntrustedBillids())){
			for(String bill_id : spsBill.getEntrustedBillids().split(",")){
				SpsBill entrusted_bill = new SpsBill();
				entrusted_bill.setBillId(Integer.parseInt(bill_id));
				entrusted_bill = spsBillService.findByPK(entrusted_bill);
				//修改状态
				entrusted_bill.setFeePayStatus(3);//支付中
				spsBillService.update(cti,entrusted_bill);
			}
		}
		//修改总包账单状态
		spsBill.setFeePayStatus(3);//支付中
		spsBillService.update(cti,spsBill);
		return Result.createResult().setSuccess(true);
	}

	/**
	 *  发送系统消息
	 *  @param   cti
	 *  @param   payNotify
	 *	@return 			: void
	 *  @createDate  	: 2016-11-26 17:43
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-26 17:43
	 *  @updateAuthor  :
	 */
	private void sendSysMessage(ContextInfo cti, PayNotify payNotify){
		try{
			//根据支付账单查询、预支付账单
			List<Map<String,Object>> bills = spsBillService.queryDeputeBillList(payNotify.getOuterTradeNo());
			if(null != bills && !bills.isEmpty()){
				String content = "";
				for(Map<String,Object> bill : bills){
					if(payNotify.getOuterTradeNo().equals(Integer.parseInt(String.valueOf(bill.get("bill_id")))))
						content = String.valueOf(bill.get("corp_name")) + "客户已支付"+String.valueOf(bill.get("period"))+"月份应收账单（"+String.valueOf(bill.get("bill_num"))+"），如有疑问及时联系企业客户";
					else
						content = String.valueOf(bill.get("sp_name")) + "服务机构已支付"+String.valueOf(bill.get("period"))+"月份应收账单（"+String.valueOf(bill.get("bill_num"))+"），如有疑问请及时联系"+String.valueOf(bill.get("sp_name"))+"服务机构";
					//发送消息 短信 系统消息
					SysMessage sysMessage = new SysMessage();
					sysMessage.setContent(content);
					sysMessage.setTitle(content);
					sysMessage.setState("TODO");
					sysMessage.setTodoUserType(CMCORPTYPE_SERVICE);
					sysMessage.setTodoOrg(Integer.parseInt(String.valueOf(bill.get("sp_id"))));
					sysMessage.setSendUserType("CUSTOMER");
					sysMessage.setSendOrg(Integer.parseInt(payNotify.getBuyerId()));
					sysMessage.setType("PAY");
					sysMessage.setSendTime(new Date());
					sysMessage.setDataId(Integer.parseInt(String.valueOf(bill.get("bill_id"))));
					RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE,sysMessage);

					//向HR发送短信、邮件
					CmCorp cmCorp = new CmCorp();
					cmCorp.setCpId(Integer.valueOf(String.valueOf(bill.get("cp_id"))));
					cmCorp = cmCorpService.findByPk(cmCorp);
					//发送邮件
					if(StringUtils.isNotBlank(cmCorp.getMail())){
						spsBillService.sendMail(cmCorp.getMail(),String.valueOf(bill.get("file_id")),"账单提醒","尊敬的用户：用友薪福社已收到您为"+String.valueOf(bill.get("period"))+"账单支付的款项。薪褔社为您提供优质服务！");
					}
					//发送短信
					if(StringUtils.isNotBlank(cmCorp.getMobile())){
						spsBillService.sendSms(cmCorp.getMobile(),"【薪福社】已收到您为"+String.valueOf(bill.get("period"))+"账单支付的款项。薪褔社为您提供优质服务！");
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
