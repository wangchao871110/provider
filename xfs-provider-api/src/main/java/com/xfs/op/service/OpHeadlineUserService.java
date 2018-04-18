package com.xfs.op.service;
import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.op.model.OpHeadlineUser;

import java.util.List;

/**
 * OpHeadlineUserService
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
public interface OpHeadlineUserService {
	
	public int save(ContextInfo cti, OpHeadlineUser vo);
	public int insert(ContextInfo cti, OpHeadlineUser vo);
	public int remove(ContextInfo cti, OpHeadlineUser vo);
	public int update(ContextInfo cti, OpHeadlineUser vo);
	public PageModel findPage(OpHeadlineUser vo);
	public List<OpHeadlineUser> findAll(OpHeadlineUser vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03
	/**
	 * 通过openid查询用户
	 * @param obj
	 * @return
	 */
	public OpHeadlineUser getHeadlineUserByOpenId(OpHeadlineUser obj);


	/**
	 * 根据openId 更新用户信息
	 *
	 * @param obj
	 * @return 返回更新后的用户信息
	 */
	public int updateHeadlineUserByOpenId(ContextInfo cti, OpHeadlineUser obj);
	
	
}
