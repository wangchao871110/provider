package com.xfs.youshutong.service.impl;/**
 * @author hongjie
 * @date 2017/5/17.16:35
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xfs.youshutong.YSTUtis.YSTUtils;
import com.xfs.youshutong.dao.InsuranceSaasDao;
import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.service.InsuranceSaasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 社保saas  友互通
 *
 * @author
 * @create 2017-05-17 16:35
 **/
@Service
public class InsuranceSaasServiceImpl implements InsuranceSaasService {


    @Autowired
    private InsuranceSaasDao insuranceSaasDao;
    private  String suffix = "saas_";


    @Override
    public List<UserLoginInfo> getUserLoginInfo(Map param) {
        List<Map> sysUsers = insuranceSaasDao.getUserLoginInfo(param);

        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<UserLoginInfo> userLoginInfoList = new ArrayList<>();
        for (Map sysUser : sysUsers) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUserid(suffix+sysUser.get("emp_id").toString());
            userLoginInfo.setUsername(sysUser.get("name") == null ? null : sysUser.get("name").toString());
            userLoginInfo.setLoginTime(sysUser.get("last_login_dt") == null ? null : YSTUtils.getRandomDateString(YSTUtils.getDateString(sysUser.get("last_login_dt").toString())));
            userLoginInfo.setMobile("1");
            userLoginInfo.setCorporationid(sysUser.get("cp_id") == null ? null : sysUser.get("cp_id").toString());
            userLoginInfo.setCorpname(sysUser.get("corp_name") == null ? null : sysUser.get("corp_name").toString());
            userLoginInfo.setSystag(YSTUtils.SAASUTILS);
            userLoginInfo.setClientid(YSTUtils.XINFUSHE);
            userLoginInfo.setClientsecret(YSTUtils.CLIENTSECRET);
            userLoginInfoList.add(userLoginInfo);
        }
        return userLoginInfoList;
    }

    @Override
    public List<UserAdditionalInfo> getUserInfo(Map param) {
        List<UserAdditionalInfo> userAdditionalInfos = new ArrayList<>();
        List<Map> res = insuranceSaasDao.getUserInfo(param);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        for (Map map : res) {
            UserAdditionalInfo vo = new UserAdditionalInfo();
            vo.setUserid(suffix+map.get("emp_id").toString());
            vo.setIdentitycard(map.get("identity_code") == null ? null : map.get("identity_code").toString());
            vo.setCorporationid(map.get("cp_id") == null ? null : map.get("cp_id").toString());
            vo.setCorporationname(map.get("corp_name") == null ? null : map.get("corp_name").toString());
            vo.setEmail(map.get("email") == null ? null : map.get("email").toString());
            vo.setSysmark(YSTUtils.SAASUTILS);
            vo.setMobilephone(map.get("mobile") == null ? null : map.get("mobile").toString());
            vo.setRegisterdate(YSTUtils.getDateString(map.get("create_dt").toString()));
            vo.setStatus("active");
            vo.setAccountflag("2");
            vo.setAccountfrom(YSTUtils.XINFUSHE);
            vo.setClientid(YSTUtils.XINFUSHE);
            vo.setClientsecret(YSTUtils.CLIENTSECRET);
            vo.setTerminalType("1");
            vo.setVersion(System.currentTimeMillis());
            vo.setAdditionStatus(1);
            userAdditionalInfos.add(vo);
        }
        return userAdditionalInfos;
    }

    @Override
    public void makeLoginData(Map param) {
        insuranceSaasDao.makeLoginData(param);
    }
}
