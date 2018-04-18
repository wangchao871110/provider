package com.xfs.mall.customer.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.model.CsMallCustomerSession;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 5:21
 * @version  : V1.0
 */
public interface CsMallCustomerSessionService {
	
	public int save(ContextInfo cti, CsMallCustomerSession vo);
	public int insert(ContextInfo cti, CsMallCustomerSession vo);
	public int remove(ContextInfo cti, CsMallCustomerSession vo);
	public int update(ContextInfo cti, CsMallCustomerSession vo);
	public PageModel findPage(PageInfo pi, CsMallCustomerSession vo);
	public List<CsMallCustomerSession> findAll(CsMallCustomerSession vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:38
	/**
	 *  根据用户userid查询某客服与该用户的历史会话
	 *  @param   vo
	 * @return    : CsMallCustomerSession
	 *  @createDate   : 2016/11/9 0009 下午 5:23
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:23
	 *  @updateAuthor  :
	 */
	public CsMallCustomerSession findByCustomerIdAndUserId(CsMallCustomerSession vo);
	/**
	 *  批量保存会话用户
	 *  @param   list
	 * @return    : int
	 *  @createDate   : 2016/11/9 0009 下午 5:26
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:26
	 *  @updateAuthor  :
	 */
	public int batchSaveUsers(ContextInfo cti, List<CsMallCustomerSession> list);
	/**
	 *  查找与某客服会话历史中的前10位用户
	 *  @param   customerId
	 * @return    : List<Map>
	 *  @createDate   : 2016/11/9 0009 下午 5:27
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:27
	 *  @updateAuthor  :
	 */
	public List<Map> freeFindTopTen(String customerId);

	/**
	 *  客服的历史会话用户列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/11/9 0009 下午 5:28
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:28
	 *  @updateAuthor  :
	 */
	public PageModel findCustomerSessionUsers(PageInfo pi, CsMallCustomerSession vo, String searchWord);

}
