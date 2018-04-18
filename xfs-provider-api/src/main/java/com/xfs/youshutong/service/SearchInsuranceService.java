package com.xfs.youshutong.service;/**
 * @author hongjie
 * @date 2017/5/16.18:16
 */

import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;

import java.util.List;
import java.util.Map;

/**
 * 查社保
 *
 * @author
 * @create 2017-05-16 18:16
 **/
public interface SearchInsuranceService {
    /**
     * 获取用户信息
     *
     * @param param
     * @return
     */
    List<UserAdditionalInfo> getUserInfo(Map param);
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


    public List<Map> getAllOrderUserId(Map param) ;


}
