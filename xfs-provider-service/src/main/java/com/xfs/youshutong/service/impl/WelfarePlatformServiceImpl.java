package com.xfs.youshutong.service.impl;/**
 * @author hongjie
 * @date 2017/5/17.17:43
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.youshutong.YSTUtis.YSTUtils;
import com.xfs.youshutong.dao.WelfarePlatformDao;
import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;
import com.xfs.youshutong.service.WelfarePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 福利平台
 *
 * @author
 * @create 2017-05-17 17:43
 **/
@Service
public class WelfarePlatformServiceImpl implements WelfarePlatformService {


    @Autowired
    private WelfarePlatformDao welfarePlatformDao;

    private  String suffix = "welf_";


    @Override
    public List<UserAdditionalInfo> getUserInfo(Map param) {
        List<UserAdditionalInfo> userAdditionalInfos = new ArrayList<>();
        List<Map> res = welfarePlatformDao.getUserInfo(param);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        for (Map map : res) {
            UserAdditionalInfo vo = new UserAdditionalInfo();
            vo.setUserid(suffix+map.get("id").toString());
            vo.setUsername(map.get("name")==null?map.get("id").toString():map.get("name").toString());
            vo.setMobilephone(map.get("phone")==null?null:map.get("phone").toString());
            vo.setRegisterdate(YSTUtils.getDateString(map.get("create_date").toString()));
            vo.setCorporationid(map.get("com_id")==null?null:map.get("com_id").toString());
            vo.setCorporationname(map.get("com_name")==null?null:map.get("com_name").toString());
            vo.setEmail(map.get("email")==null?null:map.get("email").toString());
            vo.setStatus("active");
            vo.setAccountflag("2");
            vo.setSysmark(YSTUtils.PROFESSIONALSERVICES);
            vo.setAccountfrom(YSTUtils.XINFUSHE);  // todo  查社保
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
    public List<YHTOrder> getOrderInfo(Map param) {
        List<YHTOrder> yhtOrders = new ArrayList<>();
        List<Map> res = welfarePlatformDao.getOrderInfo(param);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        for (Map map : res) {

            YHTOrder yhtOrder = new YHTOrder();
            String json = map.get("data").toString();
            JSONObject object = JSON.parseObject(json);
            BigDecimal price = new BigDecimal(object.get("fTotalMoney").toString());

            yhtOrder.setSysmark(YSTUtils.PROFESSIONALSERVICES);
            yhtOrder.setCusCode(map.get("uid")==null?null:map.get("uid").toString());
            yhtOrder.setCusName(map.get("name")==null?null:map.get("name").toString());
            yhtOrder.setOrderLineID(map.get("id").toString());
            yhtOrder.setOrderCode(map.get("order_no")==null?null:map.get("order_no").toString());
            yhtOrder.setOrderTime(YSTUtils.getDateString(map.get("order_time").toString()));
            yhtOrder.setPayTime(YSTUtils.getDateString(map.get("order_time").toString()));
            yhtOrder.setOrderStartTime(YSTUtils.getDateString(map.get("order_time").toString()));
            yhtOrder.setOrderEndTime(YSTUtils.getDateString(map.get("order_time").toString()));
            yhtOrder.setProductCode(YSTUtils.XINFUSHE_PRODUCT_CODE);
            yhtOrder.setOrderAmount(((Double.parseDouble(price.toString()))*0.15)+"");
            yhtOrder.setOrderPaidAmount(((Double.parseDouble(price.toString()))*0.15)+"");
            yhtOrder.setImpowerusercount("1");
            yhtOrder.setCustomId(map.get("uid")==null?null:map.get("uid").toString());
            yhtOrder.setOrderType("1");
            yhtOrder.setSignTime(map.get("order_time").toString());
            yhtOrder.setDrawerSqua(map.get("uid")==null?null:map.get("uid").toString());
            yhtOrder.setPayerSqua(map.get("uid")==null?null:map.get("uid").toString());
            yhtOrder.setUsingSqua(map.get("uid")==null?null:map.get("uid").toString());
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
            userLoginInfo.setUserid(suffix+sysUser.get("id").toString());
            userLoginInfo.setUsername(sysUser.get("name")==null?null:sysUser.get("name").toString());
            userLoginInfo.setLoginTime(sysUser.get("last_login_dt")==null?null: YSTUtils.getRandomDateString(YSTUtils.getDateString(sysUser.get("last_login_dt").toString())));
            userLoginInfo.setMobile("1");
            userLoginInfo.setCorporationid(sysUser.get("cp_id")==null?null:sysUser.get("cp_id").toString());
            userLoginInfo.setCorpname(sysUser.get("corp_name")==null?null:sysUser.get("corp_name").toString());
            userLoginInfo.setSystag(YSTUtils.PROFESSIONALSERVICES);
            userLoginInfo.setClientid(YSTUtils.XINFUSHE);
            userLoginInfo.setClientsecret(YSTUtils.CLIENTSECRET);
            userLoginInfoList.add(userLoginInfo);
        }
        return userLoginInfoList;
    }

    @Override
    public void makeLoginData(Map param) {
        welfarePlatformDao.makeLoginData(param);
    }


}
