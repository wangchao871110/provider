package com.xfs.activity.service.impl;

import com.xfs.activity.dao.BdUserPrizeDao;
import com.xfs.activity.model.BdUserPrize;
import com.xfs.activity.model.BdUserPrizeRecord;
import com.xfs.activity.service.BdUserPrizeRecordService;
import com.xfs.activity.service.BdUserPrizeService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.Arith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BdUserPrizeServiceImpl implements BdUserPrizeService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BdUserPrizeServiceImpl.class);

    @Autowired
    private BdUserPrizeDao bdUserPrizeDao;

    @Autowired
    private BdUserPrizeRecordService bdUserPrizeRecordService;

    public int save(ContextInfo cti, BdUserPrize vo) {
        return bdUserPrizeDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, BdUserPrize vo) {
        return bdUserPrizeDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, BdUserPrize vo) {
        return bdUserPrizeDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, BdUserPrize vo) {
        return bdUserPrizeDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, BdUserPrize vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bdUserPrizeDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BdUserPrize> datas = bdUserPrizeDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<BdUserPrize> findAll(BdUserPrize vo) {

        List<BdUserPrize> datas = bdUserPrizeDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/10/10 17:44:33

    /**
     * 获取用户指定类型的奖品
     *
     * @param orgId    组织编码
     * @param prizeType 奖品类型
     * @return 奖品列表
     */
    public List<Map> getPrizeByType(String userType, Integer orgId, String prizeType) {
        return bdUserPrizeDao.getUserPrizeByType(userType, orgId, prizeType);
    }

    @Override
    public Map findUserPrizeById(String userType, Integer orgId, String prizeType, Integer id) {
        return bdUserPrizeDao.findUserPrizeById(userType, orgId, prizeType, id);
    }

    @Override
    public BdUserPrize findByPk(Integer id) {
        return bdUserPrizeDao.findByPK(id);
    }


    public List<Map<String, Object>> queryUserPrizeList(BdUserPrize vo) {
        return bdUserPrizeDao.queryUserPrizeList(vo);
    }

    @Override
    public Map<String, Object> queryUserPrize(BdUserPrize vo) {
        return bdUserPrizeDao.queryUserPrize(vo);
    }
    /**
     * 查询我的优惠券
     * @param vo
     * @return
     */
    public List<Map<String, Object>> queryUserPriceByBUP(BdUserPrize vo) {
        return bdUserPrizeDao.queryUserPriceByBUP(vo);
    }

    /**
     * 使用或回退优惠券
     *
     * @param cti
     * @param userType
     * @param orgId
     * @param prizeId
     * @param outTradeOrder
     * @param monetary
     * @param optType 操作类型 0：使用，1：回退
     * @return
     * @throws Exception
     */
    public Result saveUserPrizeRecord(ContextInfo cti, String userType, Integer orgId, Integer prizeId, String outTradeOrder, BigDecimal monetary, int optType) {
        Result result = Result.createResult().setSuccess(false);
        BdUserPrize userPrize = new BdUserPrize();
        userPrize.setUserType(userType);
        userPrize.setOrgId(orgId);
        userPrize.setPrizeId(prizeId);
        List<BdUserPrize> prizes = findAll(userPrize);
        if (prizes == null || prizes.isEmpty()) {
            result.setMsg("优惠券不存在");
            return result;
        }
        BigDecimal price = prizes.get(0).getPrizePrice();
        if (price == null) {
            price = new BigDecimal("0");
        }
        if (optType == 0 && price.doubleValue() < monetary.doubleValue()) {
            result.setMsg("优惠券价值不够");
            return result;
        }
        BdUserPrizeRecord record = new BdUserPrizeRecord();
        record.setUserType(userType);
        record.setOrgId(orgId);
        record.setPrizeId(prizeId);
        record.setOutTradeOrder(outTradeOrder);
        record.setState("PRE_PAY");
        record.setDr(0);
        List<BdUserPrizeRecord> records = bdUserPrizeRecordService.findAll(record);
        if (records != null && !records.isEmpty() && optType == 0) {
            return result.setSuccess(true);
        } else if (records == null || records.isEmpty() && optType == 1) {
            return result.setSuccess(true);
        } else if (optType == 1) {
            monetary = records.get(0).getMonetary();
        }
        BdUserPrize bdUserPrize = new BdUserPrize();
        bdUserPrize.setId(prizes.get(0).getId());
        bdUserPrize.setPrizePrice(optType == 0 ? Arith.sub(price, monetary) : Arith.add(price, monetary));
        if (optType == 0 && price.doubleValue() == monetary.doubleValue()) {
            bdUserPrize.setState("USED");
        } else if (optType == 1) {
            bdUserPrize.setState("UNUSE");
        }
        int flag = update(cti, bdUserPrize);
        if (flag <= 0) {
            result.setMsg("修改优惠券失败");
            return result;
        }
        if (optType == 0) {
            if (cti != null) {
                record.setUserId(cti.getUserId());
            }
            record.setMonetary(monetary);
            record.setMemo("支付使用优惠券");
            record.setDr(0);
            flag = bdUserPrizeRecordService.insert(cti, record);
        } else if (optType == 1) {
            record.setId(records.get(0).getId());
            record.setDr(1);
            flag = bdUserPrizeRecordService.update(cti, record);
        }
        if (flag <= 0) {
            result.setMsg("保存使用优惠券记录失败");
            return result;
        }
        return result.setSuccess(true);
    }

}
