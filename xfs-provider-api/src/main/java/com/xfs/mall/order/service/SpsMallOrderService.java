package com.xfs.mall.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiOrder;
import com.xfs.insurance.dto.MyCiOrder;
import com.xfs.insurance.dto.MyOrder;
import com.xfs.mall.order.model.SpsMallOrder;

/**
 * SpsMallOrderService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 16:41:09
 */
public interface SpsMallOrderService {
	
	public int save(ContextInfo cti, SpsMallOrder vo);
	public int insert(ContextInfo cti, SpsMallOrder vo);
	public int remove(ContextInfo cti, SpsMallOrder vo);
	public int update(ContextInfo cti, SpsMallOrder vo);
	public PageModel findPage(PageInfo pi, SpsMallOrder vo);
	public List<SpsMallOrder> findAll(SpsMallOrder vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 16:41:09
	
	/**
	 * bs
	 * @param o
	 * @param spsMallOrder
	 * @return
	 */
	PageModel queryAppraiseOrderList(PageInfo pi, String o, SpsMallOrder spsMallOrder);
    SpsMallOrder findByOrderId(SpsMallOrder spsMallOrder);

	PageModel findCiOrderPage(PageInfo pi, CiOrder ciOrder);
	
	public PageModel queryorderList(PageInfo pi, Map map);
	public Map<String, Object> queryLIST(Map map);
	public PageModel queryitem(PageInfo pi, Map map);
	
	/**
	 * 查询cs企业订单列表
	 * @param spName
	 * @param spsMallOrder
     * @return
     */
	PageModel queryMyOrderList(PageInfo pi, String o, SpsMallOrder spsMallOrder);
	/**
	 * 获取订单号
	 *
	 * @return
	 */
	public String getOrderNo();
	public String getOrderNo(String key);
    public MyOrder getOrderDetail(SpsMallOrder spsMallOrder);
	/**
	 * 获取商保订单详情
	 * @param spsMallOrder
	 * @return
	 */
	public MyCiOrder getCiOrderDetail(SpsMallOrder spsMallOrder);
	
	/**
	 * 自定义 分页查询
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午8:13:46
	 */
	public PageModel customfindPage(PageInfo pi, SpsMallOrder vo);
	/**
	 * 根据orderId查询 订单及产品信息
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午8:13:46
	 */
	public SpsMallOrder findOrderByOrderId(SpsMallOrder obj);
	/**
	 * 查询 订单明细 项目信息
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  下午1:19:18
	 */
	public List<Map<String, Object>> findOrderItems(SpsMallOrder vo);
	
	/**
	 * @throws Exception 
	 * 
	* @Title: updateState 
	* @Description: 通过审核更改订单状态及合同状态
	* @param @param request
	* @param @param vo
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @throws
	 */
	public Result updateState(ContextInfo cti, HttpServletRequest request,SpsMallOrder vo) throws Exception;
}

