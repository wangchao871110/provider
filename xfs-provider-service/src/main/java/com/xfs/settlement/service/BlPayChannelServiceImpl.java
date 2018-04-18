package com.xfs.settlement.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.util.StringUtils;
import com.xfs.pay.PayContants;
import com.xfs.settlement.dao.BlPayChannelDao;
import com.xfs.settlement.dto.*;
import com.xfs.settlement.model.BlPayChannel;
import com.xfs.settlement.model.BlPayVoucher;
import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;

/**
 * 支付渠道服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:39
 * @version 	: V1.0
 */
@Service
public class BlPayChannelServiceImpl implements BlPayChannelService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayChannelServiceImpl.class);
	
	@Autowired
	private BlPayChannelDao blPayChannelDao;

	@Autowired
	private BlPayVoucherService blPayVoucherService;
	
	public int save(ContextInfo cti, BlPayChannel vo ){
		return blPayChannelDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, BlPayChannel vo ){
		return blPayChannelDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, BlPayChannel vo ){
		return blPayChannelDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, BlPayChannel vo ){
		return blPayChannelDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi, BlPayChannel vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blPayChannelDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayChannel> datas = blPayChannelDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayChannel> findAll(BlPayChannel vo){
		
		List<BlPayChannel> datas = blPayChannelDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:40

	/**
	 *  获取业务支付渠道列表
	 *  @param   basePay result
	 *	@return 			: Result
	 *  @createDate  	: 2016-11-19 10:09
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 10:09
	 *  @updateAuthor  :
	 */
	public Result queryBusinPayChannels(BasePay basePay, Result result){
		result = blPayVoucherService.checkOutTradeOrder((ToReqPay)basePay,result);
		if(result.isSuccess()){
			List<RespPayChannel> channels = blPayChannelDao.queryBusinPayChannels(basePay);
			if(null != channels && !channels.isEmpty()) {
				result.setDataValue("channels",channels);
			}else{
				ReqRechargePay reqRechargePay = new ReqRechargePay();
				reqRechargePay.setAppId(basePay.getAppId());
				reqRechargePay.setPayeeType(PayContants.BUSIN_TYPE_XFS);
				reqRechargePay.setPayeeId(null);
				channels = blPayChannelDao.queryBusinPayChannels(reqRechargePay);
				if(null != channels && !channels.isEmpty()) {
					result.setDataValue("channels",channels);
				}else{
					result.setSuccess(false);
					result.setError("当前应用无支付渠道，请联系客服！");
				}
			}
		}
		return result;
	}


	/**
	 *  获取业务支付渠道
	 *  @param   appId
	 *  @param   payId
	 *	@return 			: com.xfs.settlement.dto.RespPayChannel
	 *  @createDate  	: 2016-12-06 19:03
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-06 19:03
	 *  @updateAuthor  :
	 */
	public RespPayChannel queryBusinPayChannel(String appId,Integer payId){
		ToReqPay reqPay = new ToReqPay();
		reqPay.setAppId(appId);
		reqPay.setPayId(payId);
		return blPayChannelDao.queryBusinPayChannel(reqPay);
	}

	/**
	 *  获取第三方支付信息
	 *  @param		basePay
	 *	@return 			: com.xfs.settlement.dto.RespPay
	 *  @createDate  	: 2016-11-21 17:43
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-21 17:43
	 *  @updateAuthor  :
	 */
	public RespPay queryBusinPayInfo(BasePay basePay){
		return blPayChannelDao.queryBusinPayInfo(basePay);
	}


	/**
	 * 获取畅捷通支付参数
	 * @param basePay
	 * @return
	 */
	public RespPay queryChanPayInfo(BasePay basePay){
		return blPayChannelDao.queryChanPayInfo(basePay);
	}

	/**
	 *  根据订单号获取第三方支付信息
	 *  @param   order_id
	 *	@return 			: com.xfs.settlement.dto.RespPay
	 *  @createDate  	: 2016-11-22 09:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-22 09:59
	 *  @updateAuthor  :
	 */
	public RespPay queryBusinPayInfo(String order_id){
		return blPayChannelDao.queryBusinPayInfo(order_id);
	}
}
