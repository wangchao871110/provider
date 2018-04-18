package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.BdBstypefield;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 业务字段服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:53
 * @version 	: V1.0
 */
public interface BdBstypefieldService {
	
	public int save(ContextInfo cti, BdBstypefield vo);
	public int insert(ContextInfo cti, BdBstypefield vo);
	public int remove(ContextInfo cti, BdBstypefield vo);
	public int update(ContextInfo cti, BdBstypefield vo);
	public PageModel findPage(PageInfo pi, BdBstypefield vo);
	public List<BdBstypefield> findAll(BdBstypefield vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:06

	/**
	 *  获取业务字段信息
	 *  @param   vo
	 *	@return 			: com.xfs.business.model.BdBstypefield
	 *  @createDate  	: 2016-11-11 14:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:53
	 *  @updateAuthor  :
	 */
	public BdBstypefield findBdBstypefield(BdBstypefield vo);
	
}
