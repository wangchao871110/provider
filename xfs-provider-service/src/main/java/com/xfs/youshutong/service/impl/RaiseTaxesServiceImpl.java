package com.xfs.youshutong.service.impl;/**
 * @author hongjie
 * @date 2017/5/17.17:41
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xfs.youshutong.YSTUtis.YSTUtils;
import com.xfs.youshutong.dao.RaiseTaxesDao;
import com.xfs.youshutong.dao.WelfarePlatformDao;
import com.xfs.youshutong.dao.YhtXfsOrderDao;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;
import com.xfs.youshutong.model.YhtXfsOrder;
import com.xfs.youshutong.service.RaiseTaxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 税筹-友互通
 *
 * @author
 * @create 2017-05-17 17:41
 **/
@Service
public class RaiseTaxesServiceImpl implements RaiseTaxesService {

    @Autowired
    private RaiseTaxesDao raiseTaxesDao;

    @Autowired
    private YhtXfsOrderDao yhtXfsOrderDao;

    @Autowired
    private WelfarePlatformDao welfarePlatformDao;





    @Override
    public List<YHTOrder> getOrderInfo(Map param) {
        List<YHTOrder> yhtOrders = new ArrayList<>();
        List<YhtXfsOrder> yhtXfsOrders = yhtXfsOrderDao.findOrderByCondition(param);
        for (YhtXfsOrder yhtXfsOrder: yhtXfsOrders){
            YHTOrder yhtOrder = new YHTOrder();
            yhtOrder.setCusCode(yhtXfsOrder.getCustomeId());
            yhtOrder.setCusName(yhtXfsOrder.getCustomeName());
         //   yhtOrder.setOrderClass();
            yhtOrder.setCommonStatus(yhtXfsOrder.getCommonStatus());
            yhtOrder.setOrderTime(YSTUtils.getDateString(yhtXfsOrder.getOrderTime()));
            yhtOrder.setPayTime(YSTUtils.getDateString(yhtXfsOrder.getPayTime()));
            yhtOrder.setOrderStartTime(YSTUtils.getDateString(yhtXfsOrder.getOrderStartTime()));
            yhtOrder.setOrderEndTime(YSTUtils.getDateString(yhtXfsOrder.getOrderEndTime()));
            yhtOrder.setSysmark(yhtXfsOrder.getSysMark());
            yhtOrder.setImpowerusercount(yhtXfsOrder.getImpowerUserCount()+"");
            yhtOrder.setOrderPaidAmount(yhtXfsOrder.getOrderPaidAmount().toString());
            yhtOrder.setOrderAmount(yhtXfsOrder.getOrderAmount().toString());
            yhtOrder.setCustomId(yhtXfsOrder.getCustomeId());
            yhtOrder.setCusName(yhtXfsOrder.getCustomeName());
            yhtOrder.setOrderLineID(yhtXfsOrder.getOrderId()+"");
            yhtOrder.setOrderCode(yhtXfsOrder.getOrderId()+"");
            yhtOrder.setProductCode(YSTUtils.XINFUSHE_PRODUCT_CODE);
            yhtOrder.setCustomId(yhtXfsOrder.getCustomeId());
            yhtOrder.setOrderType("1");
            yhtOrder.setSignTime(YSTUtils.getDateString(yhtXfsOrder.getOrderTime()));
            yhtOrder.setDrawerSqua(yhtXfsOrder.getCustomeId());
            yhtOrder.setPayerSqua(yhtXfsOrder.getCustomeId());
            yhtOrder.setUsingSqua(yhtXfsOrder.getCustomeId());
            yhtOrders.add(yhtOrder);
        }
        return yhtOrders;
    }

    @Override
    public List<UserLoginInfo> getUserLoginInfo(Map param) {
        List<Map> sysUsers = welfarePlatformDao.getUserLoginInfo(param);

        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<UserLoginInfo> userLoginInfoList = new ArrayList<>();
        for (Map sysUser : sysUsers) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUserid(sysUser.get("user_id").toString());
            userLoginInfo.setUsername(sysUser.get("user_name")==null?null:sysUser.get("user_name").toString());
            userLoginInfo.setLoginTime(sysUser.get("last_login_dt")==null?null: YSTUtils.getRandomDateString(YSTUtils.getDateString(sysUser.get("last_login_dt").toString())));
            userLoginInfo.setMobile("1");
            userLoginInfo.setCorporationid(sysUser.get("cp_id")==null?null:sysUser.get("cp_id").toString());
            userLoginInfo.setCorpname(sysUser.get("corp_name")==null?null:sysUser.get("corp_name").toString());
            userLoginInfo.setSystag(YSTUtils.SAASUTILS);
            userLoginInfo.setClientid(YSTUtils.XINFUSHE);
            userLoginInfo.setClientsecret(YSTUtils.CLIENTSECRET);
            userLoginInfoList.add(userLoginInfo);
        }
        return userLoginInfoList;
    }


}
