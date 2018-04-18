package com.xfs.cp.service;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dto.SaaSCpBillDto;
import com.xfs.cp.model.CpOrder;

import java.util.List;
import java.util.Map;

/**
 * CpOrderService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
public interface CpOrderService {
	
	public int save(ContextInfo cti, CpOrder vo);
	public int insert(ContextInfo cti, CpOrder vo);
	public int remove(ContextInfo cti, CpOrder vo);
	public int update(ContextInfo cti, CpOrder vo);
	public PageModel findPage(PageInfo pi, CpOrder vo);
	public List<CpOrder> findAll(CpOrder vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41

	/**
	 *
	 * @method comments: 我的派单列表
	 * @author   name  : wuzhe
	 *
	 */
	public PageModel findASpOrder(PageInfo pi,CpOrder vo);

	/**
	 *
	 * @method comments: 我的接单列表
	 * @author   name  : wuzhe
	 *
	 */
	public PageModel findBSpOrder(PageInfo pi,CpOrder vo);

	/**
	 *
	 * @method comments: 查订单
	 * @author   name  : wuzhe
	 *
	 */
	public CpOrder findByOrderId(CpOrder vo);


	/**
	 *
	 * @method comments: 查合作伙伴提示tip数
	 * @author   name  : wuzhe
	 *
	 */
	public Integer getCoopTipCount(ContextInfo sysUser) ;

	/**
	 *
	 * @method comments: 查当前登陆机构未完成单
	 * @author   name  : wuzhe
	 *
	 */
	public Map<String,Integer> getActOrderMapByUser(Integer relationId, ContextInfo user, boolean isASp);

	/**
	 * 
	 * @method comments: 获取接单数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月14日 上午10:26:40 
	 * @param id
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月14日 上午10:26:40 wangchao 创建
	 *
	 */
	public CpOrder findOrderNumByBSpId(Integer id);

	//请款
    public PageModel FindManage(PageInfo pi,CpOrder vo);

    public Map<String, java.lang.Object> queryExpAmount(CpOrder cpOrder);

    public Map<String, java.lang.Object> queryPayAmount(CpOrder cpOrder);

	public Map<String, java.lang.Object> queryNotPayAmount(CpOrder cpOrder);

    public boolean paySpExpAmount(PageInfo pi,ContextInfo cti, CpOrder cpOrder, Result result, Integer user_id, String issend);

    //收款
    public Map<String, java.lang.Object> queryVoucherOffLineAmount(CpOrder cpOrder);

    public Map<String, java.lang.Object> queryVoucherOnlineAmount(CpOrder cpOrder);

    public PageModel queryVoucherList(PageInfo pi,CpOrder vo);

    public CpOrder queryVoucherInfoById(CpOrder vo);
	public CpOrder findByOrderId(String orderId);
	public void sendMessage(CpOrder order);

    public List<Map<String, java.lang.Object>> findAllNews(CpOrder vo);
    
    /**
     * 按订单order_id更新订单
     *  @param cpOrder
     *  @return 
     *	@return 			: int 
     *  @createDate  	: 2016年11月14日 下午7:36:27
     *  @author         	: songby 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年11月14日 下午7:36:27
     *  @updateAuthor  :
     */
	public int updateByOrderId(ContextInfo cti, CpOrder cpOrder);


	public PageModel findByCpId(PageInfo pi,CpOrder vo);
	
	/**
	 * SAAS回传账单接口
	 *  @param cti
	 *  @param saaSCpBillDto
	 *  @return 
	 *	@return			: Result 
	 *  @createDate		: 2016年12月26日 下午4:44:05
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月26日 下午4:44:05
	 *  @updateAuthor  	:
	 */
	public Result SaaSCpBill(ContextInfo cti, SaaSCpBillDto saaSCpBillDto);

	/**
	 *官费，服务费
	 */
	public Map<String, java.lang.Object> servicePrice(CpOrder cpOrder);
}
