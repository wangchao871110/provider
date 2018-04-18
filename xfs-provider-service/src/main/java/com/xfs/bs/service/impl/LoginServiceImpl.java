package com.xfs.bs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bs.service.LoginService;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.model.SysUser;

/**
 * Created by Admin on 2016/1/26.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SysUserDao sysUserDAO;

    @Override
    public SysUser userLogin(String userName, String password, String userType) {
        SysUser checkUser = new SysUser();
        checkUser.setUserName(userName);
        checkUser.setPassword(password);
        checkUser.setUserType(userType);
        checkUser.setEnabled(1);
        SysUser user = sysUserDAO.FindByLoginNamePass(checkUser);;
        return user;
    }
}
