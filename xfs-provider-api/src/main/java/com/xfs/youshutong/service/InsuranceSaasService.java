package com.xfs.youshutong.service;/**
 * @author hongjie
 * @date 2017/5/17.16:15
 */

import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;

import java.util.List;
import java.util.Map;

/**
 * 社保saas 和友互通
 *
 * @author
 * @create 2017-05-17 16:15
 **/
public interface InsuranceSaasService {

    /**
     * 登录日志
     *
     * @param param
     * @return
     */
    List<UserLoginInfo> getUserLoginInfo(Map param);

    /**
     * 获取用户
     *
     * @param param
     * @return
     */
    List<UserAdditionalInfo> getUserInfo(Map param);

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
