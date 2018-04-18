package com.xfs.youshutong.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.youshutong.model.YhtXfsOrder;

import java.util.List;

/**
 * YhtXfsOrderService 服务层接口
 * @date 2017/05/22 15:53:46
 */
public interface YhtXfsOrderService {

	/**
	 * 查询总数
	 * @return 记录数
	 */
	public int countFindAll();
	
	/**
	 * 根据条件查询
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   订单表-和友互通对接列表
	 */
	public List<YhtXfsOrder> freeFind(YhtXfsOrder obj);
	
	/**
	 * 根据条件查询的数量
	 * @param 	obj  订单表-和友互通对接实体
	 * @return 	返回查询到的数量
	 */
	public int countFreeFind(YhtXfsOrder obj);
	
	/**
	 * 根据主键查询对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   订单表-和友互通对接实体
	 */
	public YhtXfsOrder findByPK(YhtXfsOrder obj);
	
	/**
	 * 添加对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int insert(ContextInfo cti,YhtXfsOrder obj);
	
	/**
	 * 根据主键修改对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int update(ContextInfo cti,YhtXfsOrder obj);
	
	/**
	 * 根据主键删除对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int remove(ContextInfo cti,YhtXfsOrder obj);



	public PageModel findYhtXfsOrder(PageInfo pi, YhtXfsOrder yhtXfsOrder);
	
}
