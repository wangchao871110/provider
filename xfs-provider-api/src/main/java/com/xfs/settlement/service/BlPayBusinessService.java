package com.xfs.settlement.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.RespPayChannel;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.model.BlPayBusiness;

/**
 * 支付业务服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:43
 * @version 	: V1.0
 */
public interface BlPayBusinessService {
	
	public int save(ContextInfo cti, BlPayBusiness vo);
	public int insert(ContextInfo cti,BlPayBusiness vo);
	public int remove(ContextInfo cti,BlPayBusiness vo);
	public int update(ContextInfo cti,BlPayBusiness vo);
	public PageModel findPage(PageInfo pi, BlPayBusiness vo);
	public List<BlPayBusiness> findAll(BlPayBusiness vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:38

	/**
	 *  根据应用appId 和 payId 获取业务参数
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayBusiness
	 *  @createDate  	: 2016-11-17 21:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-17 21:06
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppIdAndPayID(BasePay basePay);

	/**
	 *  根据应用appId 获取业务参数
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayBusiness
	 *  @createDate  	: 2016-11-17 21:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-17 21:06
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppId(BasePay basePay);

	/**
	 *  根据应用名称 获取业务信息
	 *  @param   appName
	 *	@return 			: com.xfs.settlement.dto.RespXfsPay
	 *  @createDate  	: 2016-11-26 13:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-26 13:21
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppName(String appName);

	/**
	 *  根据业务ID查询业务信息
	 *  @param   busId
	 *	@return 			: com.xfs.settlement.dto.RespXfsPay
	 *  @createDate  	: 2016-12-12 21:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:18
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinByBusId(Integer busId);

	/**
	 *  检查请求参数信息
	 *  @param   basePay
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-12 21:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:21
	 *  @updateAuthor  :
	 */
	public Result checkReqBody(BasePay basePay);

	/**
	 *  校验签名信息
	 *  @param   basePay
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-12 21:22
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:22
	 *  @updateAuthor  :
	 */
	public Result verificateReqBodySign(BasePay basePay);
}
