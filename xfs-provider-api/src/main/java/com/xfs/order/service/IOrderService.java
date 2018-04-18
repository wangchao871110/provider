package com.xfs.order.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.order.model.Order;

/**
 * 订单接口
 * @author 	: zhangyan
 * @date 	: 2016年10月11日 下午2:00:59
 * @version 	: V1.0
 */
public interface IOrderService {

    /**
     * 保存订单
     *  @param order 订单实体
     *	@return 	影响行数
     *  @createDate  	: 2016年10月11日 下午2:03:33
     *  @author         	: zhangyan 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年10月11日 下午2:03:33
     *  @updateAuthor  :
     */
    int save(ContextInfo cti, Order order);
    
    /**
     * 删除订单
     *  @param id 订单ID
     *  @return 影响行数
     *	@return 			: int 影响行数2
     *  @createDate  	: 2016年10月11日 下午2:03:37
     *  @author         	: zhangyan 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年10月11日 下午2:03:37
     *  @updateAuthor  :
     */
    int delete(int id);
    
    /**
     * 修改订单
     *  @param order order实体，id必填
     *  @return 
     *	@return 			: int 
     *  @createDate  	: 2016年10月11日 下午2:03:40
     *  @author         	: zhangyan 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年10月11日 下午2:03:40
     *  @updateAuthor  :
     */
    int update(ContextInfo cti, Order order);
    
    
    /**
     * 根据商品ID查询订单
     *  @param shopId 商铺ID
     *  @return 订单列表
     *	@return 			: List<Order> 
     *  @createDate  	: 2016年10月11日 下午2:03:44
     *  @author         	: zhangyan 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年10月11日 下午2:03:44
     *  @updateAuthor  :
     */
    List<Order> findOrderByShopId(int shopId);
    

    /**
     * 根据id和name查询订单
     *  @param id 订单ID
     *  @param name 订单名称
     *  @return 订单实体
     *	@return 			: Order 订单实体2 
     *  @createDate  	: 2016年10月11日 下午2:04:23
     *  @author         	: zhangyan 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年10月11日 下午2:04:23
     *  @updateAuthor  :
     */
    Order findOrder(int id,String name);
}
