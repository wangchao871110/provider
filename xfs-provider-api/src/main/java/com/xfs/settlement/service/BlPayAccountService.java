package com.xfs.settlement.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dto.ReqBindBank;
import com.xfs.settlement.dto.ReqMerchant;
import com.xfs.settlement.model.BlPayAccount;

/**
 * 支付帐号服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-09 17:40
 * @version 	: V1.0
 */
public interface BlPayAccountService {
	
	public int save(ContextInfo cti, BlPayAccount vo);
	public int insert(ContextInfo cti, BlPayAccount vo);
	public int remove(ContextInfo cti, BlPayAccount vo);
	public int update(ContextInfo cti, BlPayAccount vo);
	public PageModel findPage(PageInfo pi, BlPayAccount vo);
	public List<BlPayAccount> findAll(BlPayAccount vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:30

	/**
	 *  获取服务商下的支付方式列表
	 *  @param   account
	 *	@return 			: java.util.List<com.xfs.settlement.model.BlPayAccount>
	 *  @createDate  	: 2016-11-09 17:35
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:35
	 *  @updateAuthor  :
	 */
	public List<BlPayAccount> querySpsPayAccount(BlPayAccount account);

	/**
	 *  获取支付帐号详情
	 *  @param   account
	 *	@return 			: com.xfs.settlement.model.BlPayAccount
	 *  @createDate  	: 2016-11-09 17:36
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:36
	 *  @updateAuthor  :
	 */
	public BlPayAccount findByPK(BlPayAccount account);

	/**
	 *  编辑支付帐号
	 *  @param   cti
	 *  @param   whereMapService
	 *	@return 			: int
	 *  @createDate  	: 2016-11-09 17:37
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:37
	 *  @updateAuthor  :
	 */
	public int editAccount(ContextInfo cti,Map<String, Object> whereMapOffic, Map<String, Object> whereMapService);

	/**
	 *  获取支付帐号详情
	 *  @param   whereMap
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-09 17:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:38
	 *  @updateAuthor  :
	 */
	public Map<String, Object> getAccountInfo(Map<String, Object> whereMap);


	/**
	 *  根据交易号获取支付账号信息列表
	 *  @param   payType
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-09 17:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:38
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryPayAccountList(String payType);

	public Result insertBindBankInfo(ReqBindBank reqBindBank, Result result,ContextInfo cti);
}
