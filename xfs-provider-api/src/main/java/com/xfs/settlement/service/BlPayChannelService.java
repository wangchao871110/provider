package com.xfs.settlement.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.RespPay;
import com.xfs.settlement.dto.RespPayChannel;
import com.xfs.settlement.model.BlPayChannel;

/**
 * 支付渠道服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:39
 * @version 	: V1.0
 */
public interface BlPayChannelService {
	
	public int save(ContextInfo cti, BlPayChannel vo);
	public int insert(ContextInfo cti,BlPayChannel vo);
	public int remove(ContextInfo cti,BlPayChannel vo);
	public int update(ContextInfo cti,BlPayChannel vo);
	public PageModel findPage(PageInfo pi, BlPayChannel vo);
	public List<BlPayChannel> findAll(BlPayChannel vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:40

	/**
	 *  获取业务支付渠道列表
	 *  @param   basePay
	 *	@return 			: Result
	 *  @createDate  	: 2016-11-19 10:09
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 10:09
	 *  @updateAuthor  :
	 */
	public Result queryBusinPayChannels(BasePay basePay,Result result);


	public RespPayChannel queryBusinPayChannel(String appId,Integer payId);

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
	public RespPay queryBusinPayInfo(BasePay basePay);

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
	public RespPay queryBusinPayInfo(String order_id);

	/**  
	 *  获取畅捷通支付参数
	 *  @param
	 * @return    : 
	 *  @createDate   : 2016/11/30 16:17
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/30 16:17
	 *  @updateAuthor  :
	 */
	public RespPay queryChanPayInfo(BasePay basePay);
}
