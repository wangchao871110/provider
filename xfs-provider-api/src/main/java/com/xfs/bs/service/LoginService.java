package com.xfs.bs.service;

import com.xfs.user.model.SysUser;

/**
 * 登陆服务
 * 
 * @author wangdx
 */
public interface LoginService {

    /**
     * 用户登录
     * 
     * @param userName
     * @param password
     * @param userType
     * @return
     */
    public SysUser userLogin(String userName, String password, String userType);
}
