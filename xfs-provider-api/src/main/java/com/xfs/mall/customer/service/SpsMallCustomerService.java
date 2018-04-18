package com.xfs.mall.customer.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.model.SpsMallCustomer;
import com.xfs.user.model.SysUser;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 5:54
 * @version  : V1.0
 */
public interface SpsMallCustomerService {
	
	public int save(ContextInfo cti, SpsMallCustomer vo);
	public int insert(ContextInfo cti, SpsMallCustomer vo);
	public int remove(ContextInfo cti, SpsMallCustomer vo);
	public int update(ContextInfo cti, SpsMallCustomer vo);
	public PageModel findPage(PageInfo pi, SpsMallCustomer vo);
	public List<SpsMallCustomer> findAll(SpsMallCustomer vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:06:21
	/**
	 *  根据主键查询 实体
	 *  @param   vo
	 * @return    : SpsMallCustomer
	 *  @createDate   : 2016/11/9 0009 下午 5:32
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:32
	 *  @updateAuthor  :
	 */
	public SpsMallCustomer findByPK(SpsMallCustomer vo);
	/**
	 *  根据客服的userid查找该客服
	 *  @param   userid
	 * @return    : SpsMallCustomer
	 *  @createDate   : 2016/11/9 0009 下午 5:31
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:31
	 *  @updateAuthor  :
	 */
	SpsMallCustomer findByIMUserid(Integer userid);
	/**
	 *  通过userid查找客服
	 *  @param   userid
	 * @return    : Map
	 *  @createDate   : 2016/11/9 0009 下午 5:40
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:40
	 *  @updateAuthor  :
	 */
	Map findByMyUserid(Integer userid);
	/**
	 *  上传截图后图片
	 *  @param   base64Code
	 *  @param   cti
	 * @return    :
	 *  @createDate   : 2016/11/9 0009 下午 5:42
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:42
	 *  @updateAuthor  :
	 */
	public Result uploadImage(ContextInfo cti, String base64Code);
	/**
	 *  保存客服
	 *  @param   cti
	 *  @param   customer
	 *  @param   mobile
	 * @return    :
	 *  @createDate   : 2016/11/9 0009 下午 5:43
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:43
	 *  @updateAuthor  :
	 */
	public Result save(ContextInfo cti, SpsMallCustomer customer, String mobile);
	/**
	 *  上下移动客服顺序
	 *  @param   customerId
	 *  @param   orderby
	 *  @param   customerIdNew
	 *  @param   orderbyNew
	 * @return    : Result
	 *  @createDate   : 2016/11/9 0009 下午 5:44
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:44
	 *  @updateAuthor  :
	 */
	public Result updateOrderBy(ContextInfo cti,Integer customerId, Integer orderby, Integer customerIdNew, Integer orderbyNew);
	/**
	 * 查询 客服记录
	 *
	 * @author lifq
	 *
	 * 2016年8月1日  下午4:40:37
	 */
	/**
	 *  根据userid和customerid查询客服
	 *  @param vo
	 * @return    :List<SpsMallCustomer>
	 *  @createDate   : 2016/11/9 0009 下午 5:59
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:59
	 *  @updateAuthor  :
	 */
	public List<SpsMallCustomer> findAllNotCurrent(SpsMallCustomer vo);
	
	/**
	 *  商铺客服列表
	 *  @param   vo
	 * @return    : List<SpsMallCustomer>
	 *  @createDate   : 2016/11/9 0009 下午 7:13
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 7:13
	 *  @updateAuthor  :
	 */
	public List<SpsMallCustomer> findMallCustomers(SpsMallCustomer vo);

	/**
	 *  薪福社运营客服
	 *  @param
	 * @return    : List<SpsMallCustomer>
	 *  @createDate   : 2016/11/9 0009 下午 5:55
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:55
	 *  @updateAuthor  :
	 */
	public List<SpsMallCustomer> findBusinessCustomers();

	/**
	 *  查询客服的手机号
	 *  @param   vo
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/9 0009 下午 5:45
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 5:45
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findCustomerMobile(SpsMallCustomer vo);
	
}
