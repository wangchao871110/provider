package com.xfs.settlement.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.model.BlBalancedetail;

/**
 * 余额流水明细服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-09 17:25
 * @version 	: V1.0
 */
public interface BlBalancedetailService {
	
	public int save(ContextInfo cti, BlBalancedetail vo);
	public int insert(ContextInfo cti, BlBalancedetail vo);
	public int remove(ContextInfo cti, BlBalancedetail vo);
	public int update(ContextInfo cti, BlBalancedetail vo);
	public PageModel findPage(PageInfo pi, BlBalancedetail vo);
	public List<BlBalancedetail> findAll(BlBalancedetail vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:29

	/**
	 *  获取余额详情
	 *  @param   blBalancedetail
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-09 17:24
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:24
	 *  @updateAuthor  :
	 */
	public Map<String,Object> queryBalanceDetail(BlBalancedetail blBalancedetail);

	/**
	 *  获取余额明细
	 *  @param   vo
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 17:24
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:24
	 *  @updateAuthor  :
	 */
	public PageModel queryBalanceDetailList(PageInfo pi,BlBalancedetail vo);
	
}
