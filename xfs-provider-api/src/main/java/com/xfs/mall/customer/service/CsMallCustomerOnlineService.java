package com.xfs.mall.customer.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.model.CsMallCustomerOnline;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 5:20
 * @version  : V1.0
 */
public interface CsMallCustomerOnlineService {
	
	public int save(ContextInfo cti, CsMallCustomerOnline vo);
	public int insert(ContextInfo cti, CsMallCustomerOnline vo);
	public int remove(ContextInfo cti, CsMallCustomerOnline vo);
	public int update(ContextInfo cti, CsMallCustomerOnline vo);
	public PageModel findPage(PageInfo pi, CsMallCustomerOnline vo);
	public List<CsMallCustomerOnline> findAll(CsMallCustomerOnline vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:10

	/**
	 *  查询IM在线时长列表
	 *  @param   map
	 * @return    :PageModel
	 *  @createDate   : 2016/11/9 0009 下午 5:20
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:20
	 *  @updateAuthor  :
	 */
	public PageModel selectImOnlinePage(PageInfo pi, Map map);
	
	/**
	 *  在数据库中查出endtime为null的客服，即查询出在线状态的客服
	 *  @param    vo
	 * @return    :CsMallCustomerOnline
	 *  @createDate   : 2016/11/9 0009 上午 11:07
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 上午 11:07
	 *  @updateAuthor  :
	 */
	public CsMallCustomerOnline findOnlineCustomer(CsMallCustomerOnline vo);
	
}
