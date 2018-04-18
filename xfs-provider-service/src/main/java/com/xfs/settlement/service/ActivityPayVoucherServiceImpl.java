package com.xfs.settlement.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.bill.service.ActivityPayVoucherService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.util.StringUtils;
import com.xfs.settlement.dto.PayNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.model.BdActivityJoinRecord;
import com.xfs.activity.model.BdLotteryPool;
import com.xfs.activity.model.BdPrize;
import com.xfs.activity.model.BdUserPrize;
import com.xfs.activity.service.BdActivityJoinRecordService;
import com.xfs.activity.service.BdLotteryPoolService;
import com.xfs.activity.service.BdLotteryService;
import com.xfs.activity.service.BdUserPrizeService;
import com.xfs.base.dao.SysMessageDao;
import com.xfs.base.model.SysMessage;
import com.xfs.common.util.CompressStrUtil;
import com.xfs.common.util.UUIDUtil;
import com.xfs.settlement.model.BlPayVoucher;

/**
 * 活动支付服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:19
 * @version 	: V1.0
 */
@Service
public class ActivityPayVoucherServiceImpl implements ActivityPayVoucherService {

    @Autowired
    BdLotteryService bdLotteryService;

    @Autowired
    BdActivityJoinRecordService bdActivityJoinRecordService;

    @Autowired
    BdLotteryPoolService bdLotteryPoolService;

    @Autowired
    BdUserPrizeService bdUserPrizeService;

    @Autowired
    SysMessageDao sysMessageDao;


    /**
     *  获取3600优惠券详情
     *  @param   lottery_code
     *  @param    cpid
     *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
     *  @createDate  	: 2016-11-30 19:24
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-30 19:24
     *  @updateAuthor  :
     */
    @Override
    public Map<String, Object> queryPrizeInfo(String lottery_code, Integer cpid) {
        return bdLotteryService.queryProuductInfo(lottery_code, cpid);
    }

    /**
     *  优惠券支付成功
     *  @param   cti
     *  @param    payNotify
     *	@return 			: com.xfs.common.Result
     *  @createDate  	: 2016-11-30 19:25
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-30 19:25
     *  @updateAuthor  :
     */
    @Override
    public Result payActivitySuccess(ContextInfo cti, PayNotify payNotify) {
        Result result = Result.createResult().setSuccess(false);
        String lottery_code = StringUtils.isBlank(payNotify.getOuterTradeNo()) ? "" : payNotify.getOuterTradeNo().substring(0,payNotify.getOuterTradeNo().lastIndexOf("_")); // 活动id
        String orgId = payNotify.getBuyerId(); // 组织id
        Integer userId = Integer.parseInt(payNotify.getCreateBy()); // 用户id
        String orgName = payNotify.getBuyerName(); // 组织名称
        if (StringUtils.isBlank(lottery_code) || StringUtils.isBlank(orgId)) {
            result.setError("活动代码不能为空");
            return result;
        }
        Map<String, Object> lotteryInfo = bdLotteryService.queryProuductInfo(lottery_code, Integer.parseInt(payNotify.getBuyerId()));
        // 1、参与记录
        BdActivityJoinRecord record = new BdActivityJoinRecord();
        record.setActivityId(Integer.parseInt(String.valueOf(lotteryInfo.get("lottery_id"))));
        record.setUserType("CUSTOMER");
        record.setOrgId(Integer.parseInt(orgId));
        List<BdActivityJoinRecord> records = bdActivityJoinRecordService.findAll(record);
        if (records != null && records.size() > 1) {
            result.setError("存在参加记录");
            return result;
        }
        record.setUserId(userId);
        record.setOrgName(orgName);
        record.setJoinType(BdActivityJoinRecord.joinType_JOIN);
        record.setDr(0);
        if (records == null || records.isEmpty()) {
            bdActivityJoinRecordService.insert(cti,record);
        } else {
            record.setId(records.get(0).getId());
            bdActivityJoinRecordService.update(cti,record);
        }
        // 2、用户奖品记录
        BdLotteryPool pool = new BdLotteryPool();
        pool.setLotteryId(Integer.parseInt(String.valueOf(lotteryInfo.get("lottery_id"))));
        List<Map<String, Object>> prizes = bdLotteryPoolService.findLotteryPirze(pool);
        if (prizes == null || prizes.isEmpty()) {
            result.setError("奖品列表不能为空");
            return result;
        }
        for (Map<String, Object> prize: prizes) {
            Object poolId = prize.get("pool_id");
            Object remainNum = prize.get("remain_num");
            Object distributedNum = prize.get("distributed_num");
            Object prizeId = prize.get("prize_id");
            Object prizePrice = prize.get("prize_price");
            Object begintime = prize.get("begintime");
            Object endtime = prize.get("endtime");
            Object needActive = prize.get("need_active");
            if (poolId == null || remainNum == null || prizeId == null) {
                continue;
            }
            // 修改奖池记录
            pool = new BdLotteryPool();
            pool.setId(Integer.parseInt(poolId.toString()));
            pool.setRemainNum(Integer.parseInt(poolId.toString()) - 1);
            pool.setDistributedNum((distributedNum != null ? Integer.parseInt(poolId.toString()) : 0) + 1);
            bdLotteryPoolService.update(cti,pool);
            // 新增用户奖品
            BdUserPrize userPrize = new BdUserPrize();
            userPrize.setUserId(userId);
            userPrize.setUserType("CUSTOMER");
            userPrize.setOrgId(Integer.parseInt(orgId));
            userPrize.setLotteryId(Integer.parseInt(String.valueOf(lotteryInfo.get("lottery_id"))));
            userPrize.setPrizeId(Integer.parseInt(prizeId.toString()));
            userPrize.setPrizeCode(CompressStrUtil.compress(UUIDUtil.randomUUID())[0]);
            if (prizePrice != null) {
                userPrize.setPrizePrice(new BigDecimal(prizePrice.toString()));
            }
            userPrize.setBegintime((Date)begintime);
            userPrize.setEndtime((Date)endtime);
            userPrize.setState((needActive == null || !BdPrize.needActive_N.equals(needActive.toString())) ?
                    BdUserPrize.state_NOT_ACTIVE : BdUserPrize.state_UNUSE);
            userPrize.setDr(0);
            bdUserPrizeService.insert(cti,userPrize);
        }
        sendSysMessage(cti,payNotify);
        return result.setSuccess(true);
    }

    /**
     *  发送系统消息
     *  @param   cti, payNotify
     *	@return 			: void
     *  @createDate  	: 2016-11-30 19:31
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-30 19:31
     *  @updateAuthor  :
     */
    private void sendSysMessage(ContextInfo cti,PayNotify payNotify) {
        try {
            String content = "恭喜您获得3600元服务费抵用券，请去“我的优惠券”中查看";
            Date date = new Date();
            SysMessage sysMessage = new SysMessage();
            sysMessage.setContent(content);
            sysMessage.setTitle(content);
            sysMessage.setState("TODO");
            sysMessage.setTodoUserType("CUSTOMER");
            sysMessage.setTodoOrg(Integer.parseInt(payNotify.getBuyerId()));
            sysMessage.setTodoUser(Integer.parseInt(payNotify.getCreateBy()));
            sysMessage.setSendUser(Integer.parseInt(payNotify.getCreateBy()));
            sysMessage.setSendUserType("CUSTOMER");
            sysMessage.setSendOrg(Integer.parseInt(payNotify.getBuyerId()));
            sysMessage.setType("PAY");
            sysMessage.setSendTime(new Date());
            sysMessage.setUrl("/myPrize/index");
            sysMessage.setState("TODO");//消息处理状态
            sysMessage.setSendTime(date);
            sysMessage.setDr(0);
            sysMessageDao.insert(cti,sysMessage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
