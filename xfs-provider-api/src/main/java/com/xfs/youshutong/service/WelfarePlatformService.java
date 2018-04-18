package com.xfs.youshutong.service;

import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;

import java.util.List;
import java.util.Map;

/**
 * @author hongjie
 * @date 2017/5/17.17:42
 */
public interface WelfarePlatformService {

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
     * 获取登录信息
     * @param param
     * @return
     */
    List<UserLoginInfo> getUserLoginInfo(Map param);

    /**
     * @Title: 造登录数据
     * @param
     * @return
     * @createDate 2017/7/5 16:56
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	:
     * @updateAuthor  :
     */
    public void makeLoginData(Map param);
}
