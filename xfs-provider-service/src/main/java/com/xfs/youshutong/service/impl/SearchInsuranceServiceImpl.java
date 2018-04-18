package com.xfs.youshutong.service.impl;/**
 * @author hongjie
 * @date 2017/5/16.18:37
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.UUIDUtil;
import com.xfs.youshutong.YSTUtis.YSTUtils;
import com.xfs.youshutong.dao.SearchInsuranceDao;
import com.xfs.youshutong.model.UserAdditionalInfo;
import com.xfs.youshutong.model.UserLoginInfo;
import com.xfs.youshutong.model.YHTOrder;
import com.xfs.youshutong.model.session;
import com.xfs.youshutong.service.SearchInsuranceService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 友互通和查社保的接口
 *
 * @author
 * @create 2017-05-16 18:37
 **/
@Service
public class SearchInsuranceServiceImpl implements SearchInsuranceService {

    Logger logger = Logger.getLogger(SearchInsuranceServiceImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    private String suffix = "csb_";


//    yyxfs01    查社保
//    yyxfs02    社保SaaS

//    yyxfs04    税筹
//    yyxfs05    福利平台


    //    yyxfs03    薪福帮
//    yyxfs06    快单

    @Autowired
    private SearchInsuranceDao searchInsuranceDao;

    public List<UserAdditionalInfo> getUserInfo(Map param) {
        List<UserAdditionalInfo> userAdditionalInfos = new ArrayList<>();
        List<Map> res = searchInsuranceDao.getUserInfo(param);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        for (Map map : res) {
            UserAdditionalInfo vo = new UserAdditionalInfo();
            vo.setUserid(suffix + map.get("mdn").toString());
            vo.setMobilephone(map.get("mdn").toString());
            vo.setRegisterdate(map.get("create_time").toString());
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
        List<Map> res = searchInsuranceDao.getOrderInfo(param);
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        for (Map map : res) {
            YHTOrder yhtOrder = new YHTOrder();
            yhtOrder.setSysmark(YSTUtils.PROFESSIONALSERVICES);   // todo 改为每个 系统一个
            yhtOrder.setCusCode(suffix + map.get("cusCode").toString());
            yhtOrder.setCusName(suffix + map.get("cusCode").toString());
            yhtOrder.setOrderLineID(suffix + map.get("ordId").toString());
            yhtOrder.setOrderCode(suffix + map.get("ordCode").toString());
            yhtOrder.setOrderTime(YSTUtils.getDateString(map.get("createDt").toString()));
            yhtOrder.setPayTime(YSTUtils.getDateString(map.get("payDate").toString()));
            yhtOrder.setOrderStartTime(YSTUtils.getDateString(map.get("payDate").toString()));
            yhtOrder.setCommonStatus("2");
            yhtOrder.setOrderEndTime(YSTUtils.getDateString(DateUtil.getDateStr(DateUtil.getPreviousNDate(DateUtil.parseDate(map.get("payDate").toString(), "yyyy-MM-dd HH:mm:ss"), -360), "yyyy-MM-dd HH:mm:ss")));

            yhtOrder.setProductCode(YSTUtils.XINFUSHE_PRODUCT_CODE);
            yhtOrder.setOrderAmount(map.get("serPrice").toString());
            yhtOrder.setOrderPaidAmount(map.get("serPrice").toString());
            yhtOrder.setImpowerusercount("1");
            yhtOrder.setCustomId(suffix + map.get("payUser").toString());
            yhtOrder.setOrderType("1");
            yhtOrder.setSignTime(map.get("createDt").toString());
            yhtOrder.setDrawerSqua(suffix + map.get("payUser").toString());
            yhtOrder.setPayerSqua(suffix + map.get("payUser").toString());
            yhtOrder.setUsingSqua(suffix + map.get("payUser").toString());
            yhtOrders.add(yhtOrder);
        }
        return yhtOrders;
    }

    @Override
    public List<UserLoginInfo> getUserLoginInfo(Map param) {

        String startDate = param.get("date").toString() + " 00:00:00";
        String endDate = param.get("date").toString() + " 23:59:59";
        Query query = new Query();
        query.addCriteria(Criteria.where("createTime").gte(startDate).lt(endDate));

        List<session> sessionList = mongoTemplate.find(query, session.class);
        if (CollectionUtils.isEmpty(sessionList)) {
            return null;
        }
        List<UserLoginInfo> userLoginInfos = new ArrayList<>();
        for (session ses : sessionList) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setMobile("0");
            userLoginInfo.setUserid(suffix + ses.getMdn());
            userLoginInfo.setLoginTime(YSTUtils.getDateString(ses.getCreateTime()));
            userLoginInfo.setSystag(YSTUtils.PROFESSIONALSERVICES);
            userLoginInfo.setClientid(YSTUtils.XINFUSHE);
            userLoginInfo.setClientsecret(YSTUtils.CLIENTSECRET);
            userLoginInfos.add(userLoginInfo);
        }
        return userLoginInfos;
    }

    @Override
    public List<Map> getAllOrderUserId(Map param) {
        logger.info("-----------------开始同步数据!-----------------");
        List<Map> res = searchInsuranceDao.getAllOrderUserId(param);
        logger.info("-----------------共获取数据条数为：" + res.size() + "-----------------");
        List<session> sessionList = new ArrayList<>();
        for (Map map : res) {
            String id = map.get("id").toString();
            String mdn = map.get("mdn").toString();
            String createDate = map.get("createDate").toString();
            String orderTime = map.get("orderTime").toString();
            session session = new session();
            session.set_id(UUIDUtil.randomUUID());
            session.setUserId(id);
            session.setMdn(mdn);

            try {
                for (Date date = DateUtil.parseDate(createDate, "yyyy-MM-dd HH:mm:ss"); date.before(DateUtil.parseDate(DateUtil.getTimeDate(), "yyyy-MM-dd HH:mm:ss")); date = DateUtil.getPreviousNDate(date, -30)) {
                    //String startDate =
                    String startDate = StringUtils.substring(DateUtil.getDateStr(date, "yyyy-MM-dd HH:mm:ss"), 0, 8) + "01 00:00:00";
                    String endDate = DateUtil.getDateStr(DateUtil.getPreviousNDate(date, -30), "yyyy-MM-dd HH:mm:ss");
                    String loginDate = DateUtil.getDateStr(YSTUtils.randomDate(startDate, endDate), "yyyy-MM-dd HH:mm:ss");
                    session.setCreateTime(loginDate);
                    sessionList.add(session);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        logger.info("-----------------开始插入到mongo-----------------");
        mongoTemplate.insert(sessionList, session.class);
        logger.info("-----------------插入完成-----------------");
        return null;
    }

//    @Override
//    public List<Map> getUserInfoBygtDate(Map param) {
//        return searchInsuranceDao.getUserInfoBygtDate(param);
//    }

//    @Override
//    public void makeLoginDate(session session) {
//        mongoTemplate.insert(session);
//    }


}
