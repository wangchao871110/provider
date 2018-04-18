package com.xfs.insurance.service.impl;

import java.text.MessageFormat;
import java.util.List;

import com.alibaba.dubbo.container.page.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdUserPrizeDao;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.TransactionUtil;
import com.xfs.insurance.dao.CpCiOrderDao;
import com.xfs.insurance.dao.CpCiProdDao;
import com.xfs.insurance.dto.CpCiOrderDetail;
import com.xfs.insurance.model.CpCiOrder;
import com.xfs.insurance.service.CpCiOrderService;

@Service
public class CpCiOrderServiceImpl implements CpCiOrderService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CpCiOrderServiceImpl.class);

    @Autowired
    private CpCiOrderDao cpCiOrderDao;

    public int save(ContextInfo cti, CpCiOrder vo) {

        return cpCiOrderDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, CpCiOrder vo) {

        return cpCiOrderDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, CpCiOrder vo) {

        return cpCiOrderDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, CpCiOrder vo) {

        return cpCiOrderDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, CpCiOrder vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cpCiOrderDao.countFreeFind(vo);
        pm.setTotal(total);
        List<CpCiOrder> datas = cpCiOrderDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<CpCiOrder> findAll(CpCiOrder vo) {
        List<CpCiOrder> datas = cpCiOrderDao.freeFind(vo);
        return datas;
    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/09/21 19:00:49

    @Autowired
    private CpCiProdDao prodDao;
    @Autowired
    private BdUserPrizeDao bdUserPrizeDao;

    /**
     * 分页查询协作平台订单
     *
     * @param vo 查询条件
     * @return 查询结果
     */
    @Override
    public PageModel findCpOrder(PageInfo pi, CpCiOrderDetail vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cpCiOrderDao.countCpCiOrders(vo);
        pm.setTotal(total);
        List<CpCiOrderDetail> datas = cpCiOrderDao.findCpCiOrderList(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 查询商保订单列表
     *
     * @param orderInfo 查询条件
     * @return 商保订单列表
     */
    @Override
    public PageModel findCiOrder(PageInfo pi, CpCiOrder orderInfo) {

        PageModel pm = new PageModel(pi);
        pm.setPagesize(10);
        Integer total = cpCiOrderDao.countCiOrder(orderInfo);
        pm.setTotal(total);
        List<CpCiOrder> datas = cpCiOrderDao.findCiOrders(orderInfo, pi.getOffset(), 10);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 保存订单信息
     *
     * @param orderInfo 订单信息
     * @return
     */
    @Override
    public int saveOrderInfo(ContextInfo cti, CpCiOrder orderInfo) {
        if (null != orderInfo.getUserPrizeId()) {
            if (bdUserPrizeDao.updateUserPriceStateByType(cti, orderInfo.getUserPrizeId(), "CI_COUPON", orderInfo.getOrgId()) == 0) {
                return 0;
            }
        }
        if (null == orderInfo.getPayAmout()) {
            orderInfo.setPayAmout(orderInfo.getTotalPrice());
        }
        if (orderInfo.getPayAmout().doubleValue() > 0) {
            orderInfo.setPayStatus(0);
        } else {
            orderInfo.setPayStatus(1);
            orderInfo.setAcceptedState(0);
            prodDao.addProdSales(cti, orderInfo.getProdId(), orderInfo.getAmount());
        }
        orderInfo.setDr(0);
        return cpCiOrderDao.save(cti, orderInfo);
    }

    public CpCiOrder findOne(CpCiOrder vo) {
        return cpCiOrderDao.freeOne(vo);
    }

    @Override
    public int payEnd(ContextInfo cti, CpCiOrder order) {
        order = cpCiOrderDao.freeOne(order);
        order.setPayStatus(1);
        order.setAcceptedState(0);
        cpCiOrderDao.update(cti, order);
        try {
            SmsUtil.sendNotificationMsg("13121967649", MessageFormat.format("编码为{0}的用户成功支付了订单{1},请及时处理", order.getUserId(), order.getOrderCode()));
        } catch (Exception e) {

        }
        prodDao.addProdSales(cti, order.getProdId(), order.getAmount());
        return 1;
    }
}
