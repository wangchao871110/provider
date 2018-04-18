package com.xfs.youshutong.service;

import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;

import java.util.List;
import java.util.Map;

/**
 * 税筹
 *
 * @author hongjie
 * @date 2017/5/17.17:40
 */
public interface RaiseTaxesService {

    /**
     *  获取订单信息
     * @param param
     * @return
     */
    List<YHTOrder> getOrderInfo(Map param);



    /**
     * 获取登录信息
     * @param param
     * @return
     */
    List<UserLoginInfo> getUserLoginInfo(Map param);
}
