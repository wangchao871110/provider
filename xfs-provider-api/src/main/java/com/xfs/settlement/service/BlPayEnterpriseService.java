package com.xfs.settlement.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dto.ReqMerchant;
import com.xfs.settlement.dto.ReqMerchantStatus;
import com.xfs.settlement.model.BlPayEnterprise;

/**
 * 畅捷通开户服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-17 16:38
 * @version 	: V1.0
 */
public interface BlPayEnterpriseService {
	
	public int save(ContextInfo cti, BlPayEnterprise vo);
	public int insert(ContextInfo cti,BlPayEnterprise vo);
	public int remove(ContextInfo cti,BlPayEnterprise vo);
	public int update(ContextInfo cti,BlPayEnterprise vo);
	public PageModel findPage(PageInfo pi, BlPayEnterprise vo);
	public List<BlPayEnterprise> findAll(BlPayEnterprise vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/17 16:32:49

	/**
	 *  根据拥有者ID 和 拥有者类型获取子商户信息
	 *  @param   reqMerchant
	 *	@return 			: com.xfs.settlement.model.BlPayEnterprise
	 *  @createDate  	: 2016-11-19 16:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 16:29
	 *  @updateAuthor  :
	 */
	public BlPayEnterprise queryEnterpriseByOwnId(ReqMerchant reqMerchant);

	/**
	 *  创建子商户信息
	 *  @param   reqMerchant
	 *  @param   result
	 *	@return 			: int
	 *  @createDate  	: 2016-11-19 16:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 16:29
	 *  @updateAuthor  :
	 */
	public Result insertEnterprise(ReqMerchant reqMerchant,Result result);

	/**
	 * 修改子商户实名审核状态
	 * @param reqMerchantStatus
	 * @param result
     * @return
     */
	public Result updateEnterprise(ReqMerchantStatus reqMerchantStatus,Result result);
	
	/**
	 * 根据spId查询是否开通子账户
	 * @param spid
	 * @author         	: zhengdan@xinfushe.com
	 * @return
	 */
	public Map<String,Object> queryEnterAccount(Integer spid);
}
