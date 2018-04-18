package com.xfs.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.order.model.Order;

/**
 * 订单service
 * @author 	: zhangyan
 * @date 	: 2016年10月11日 下午2:41:28
 * @version 	: V1.0
 */

@Service("orderService")
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IBaseTestDubbo2Service baseTestDubbo2Service;
    
    
    public int save(ContextInfo cti, Order order) {
        baseTestDubbo2Service.test3("OrderServiceImpl.delete  #####");
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public int update(ContextInfo cti, Order order) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<Order> findOrderByShopId(int shopId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Order findOrder(int id, String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
