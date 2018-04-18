package com.xfs.settlement.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dto.ToReqPay;
import com.xfs.settlement.model.BlBalance;

/**
 * 余额服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-09 17:40
 * @version 	: V1.0
 */
public interface BlBalanceService {
	
	public int save(ContextInfo cti, BlBalance vo);
	public int insert(ContextInfo cti, BlBalance vo);
	public int remove(ContextInfo cti, BlBalance vo);
	public int update(ContextInfo cti, BlBalance vo);
	public PageModel findPage(PageInfo pi, BlBalance vo);
	public List<BlBalance> findAll(BlBalance vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:30

	/**
	 *  查询账户余额
	 *  @param   vo
	 *	@return 			: com.xfs.settlement.model.BlBalance
	 *  @createDate  	: 2016-11-10 15:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:15
	 *  @updateAuthor  :
	 */
	public BlBalance queryBalance(BlBalance vo);

	/**
	 *  查询账户余额
	 *  @param   toReqPay
	 *	@return 			: com.xfs.settlement.model.BlBalance
	 *  @createDate  	: 2016-11-10 15:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:15
	 *  @updateAuthor  :
	 */
	public BlBalance queryBalance(ToReqPay toReqPay);
}
