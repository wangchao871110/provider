package com.xfs.finance.service.impl;

import com.google.common.collect.Maps;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.service.CmCorpService;
import com.xfs.finance.model.BlReceiptInfo;
import com.xfs.finance.model.BlReceiptWriteOffInfo;
import com.xfs.finance.service.BlReceiptInfoService;
import com.xfs.finance.service.BlReceiptWriteOffInfoService;
import com.xfs.finance.service.FinanceCheckService;
import com.xfs.finance.vo.BlReceiptCheckRequest;
import com.xfs.finance.vo.BlReceiptFormRequest;
import com.xfs.finance.vo.CheckChargeVo;
import com.xfs.finance.vo.CheckCreateVo;
import com.xfs.pay.PayContants;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.dto.ToReqPay;
import com.xfs.settlement.model.BlAlreadyCheckPayVoucher;
import com.xfs.settlement.model.BlBalance;
import com.xfs.settlement.model.BlPayVoucher;
import com.xfs.settlement.service.BlBalanceService;
import com.xfs.settlement.service.BlPayBusinessService;
import com.xfs.settlement.service.BlPayVoucherService;
import com.xfs.sp.model.SpService;
import com.xfs.sp.service.SpServiceService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by yuanm on 12/29/2016.
 */
@Service
public class FinanceCheckServiceImpl implements FinanceCheckService {
    private Logger log = Logger.getLogger(FinanceCheckServiceImpl.class);

    @Autowired
    BlReceiptInfoService blReceiptInfoService;
    @Autowired
    BlReceiptWriteOffInfoService blReceiptWriteOffInfoService;

    @Autowired
    BlPayVoucherService blPayVoucherService;

    @Autowired
    BlBalanceService blBalanceService;
    @Autowired
    SpServiceService spServiceService;
    @Autowired
    CmCorpService cmCorpService;
    @Autowired
    BlPayBusinessService blPayBusinessService;

    @Override
    public List<BlReceiptInfo> findSerialsNoByVo(BlReceiptFormRequest receiptFormRequest) {
        return blReceiptInfoService.findSerialsNoByVo(receiptFormRequest);
    }

    @Override
    public List<BlAlreadyCheckPayVoucher> queryVoucherByTradeNoAndBusId(BlPayVoucher blPayVoucher) {
        return blPayVoucherService.queryVoucherByTradeNoAndBusId(blPayVoucher);
    }

    @Override
    public BlBalance queryBalance(BlBalance blBalance) {
        return blBalanceService.queryBalance(blBalance);
    }

    @Override
    public void updateOrderAndReceiptInfo(BlReceiptCheckRequest request, ContextInfo loginUser) throws Exception {
        //第一步 查询订单
        BlPayVoucher blPayVoucher = new BlPayVoucher();
        blPayVoucher.setOuterTradeNoEq(request.getTradeNo());
        blPayVoucher.setBusId(Integer.parseInt(request.getBusId()));
        List<BlAlreadyCheckPayVoucher> all = blPayVoucherService.queryVoucherByTradeNoAndBusId(blPayVoucher);
        Assert.isTrue(all.size() == 1, "订单查询异常");
        BlAlreadyCheckPayVoucher payVoucher = all.get(0);
        Integer status = payVoucher.getStatus();
        Assert.isTrue(status != null && (!Objects.equals(PayContants.VOUCHER_STATUS_OK, status)), "订单支付状态异常，建议刷新页面");
        Assert.isTrue(Objects.equals(PayContants.PAY_TYPE_OFFLINE, payVoucher.getPayType()), "非线下支付的不得使用核销");
        Boolean userBalance = request.getUserBalance();
        String ids = request.getIds();
        verificateOrder(loginUser, payVoucher, userBalance, ids, null, false);
    }

    /**
     * 核销
     *
     * @param loginUser     登陆用户
     * @param payVoucher    订单
     * @param userBalance   是否使用余额
     * @param ids           流水单
     * @param remark
     * @param completeCheck 是否本次完全核销掉 仅使用于自建核销单和充值到账户
     * @throws Exception
     */
    private void verificateOrder(ContextInfo loginUser, BlAlreadyCheckPayVoucher payVoucher, Boolean userBalance, String ids, String remark, boolean completeCheck) throws Exception {
        BigDecimal amount = payVoucher.getAmount().setScale(2);//总金额
        BigDecimal alreadyCheck = payVoucher.getAlreadyCheck();//已核销金额

        if (alreadyCheck == null) {
            alreadyCheck = new BigDecimal("0.00").setScale(2);
        }


        //第三步 查询余额
        BlBalance blBalance = null;

        if (userBalance) {
            BigDecimal useBalance = payVoucher.getUseBalance();
            Assert.isTrue(useBalance == null || useBalance.setScale(2).floatValue() == 0, "该订单已经使用过余额");
            BlBalance query = new BlBalance();
            query.setOwner(payVoucher.getBuyerId());
            query.setOwnerType(payVoucher.getBuyerType());
            query.setOwnerTypeEq(payVoucher.getBuyerType());
            blBalance = blBalanceService.queryBalance(query);
            Assert.notNull(blBalance, "账户余额查询错误");
            Assert.isTrue(blBalance.getAmount().floatValue() > 0f, "账户余额为零");
        }
        BigDecimal useBalance = payVoucher.getUseBalance();
        if (useBalance == null) {
            useBalance = new BigDecimal("0.00").setScale(2);
        }
        Assert.isTrue(alreadyCheck.add(useBalance).compareTo(amount) < 0, "该订单核销异常，刷新页面查看已核销金额");
        //第二步 查询流水

        List<BlReceiptInfo> receiptInfoList = new ArrayList<>();
        if (StringUtils.isNotBlank(ids)) {
            String[] split = ids.split(",");
            List<Integer> list = new ArrayList<>(split.length);
            for (String s : split) {
                list.add(Integer.parseInt(s.trim()));
            }
            receiptInfoList = blReceiptInfoService.findSerialsByIds(list);
        }
        List<BlReceiptInfo> toUpdateReceipt = new ArrayList<>();
        List<BlReceiptWriteOffInfo> toInsertWriteOff = new ArrayList<>();
        int temp = -1;
        int compareTo = -1;
        for (int i = 0; i < receiptInfoList.size(); i++) {
            BlReceiptInfo blReceiptInfo = receiptInfoList.get(i);
            BigDecimal remainAmount = blReceiptInfo.getRemainAmount().setScale(2);
            Assert.isTrue(remainAmount.floatValue() > 0f, "流水已被更新，请刷新后再核销");
            BigDecimal alreadyAmount = blReceiptInfo.getAlreadyAmount();
            if (alreadyAmount == null) {
                alreadyAmount = new BigDecimal("0.00");
            }

            compareTo = alreadyCheck.add(remainAmount).add(useBalance).compareTo(amount);
            BigDecimal subtract; //核销金额
            if (compareTo > 0) {//已满
                subtract = amount.subtract(alreadyCheck).subtract(useBalance);
                remainAmount = remainAmount.subtract(subtract);
                alreadyAmount = alreadyAmount.add(subtract);
                alreadyCheck = amount.subtract(useBalance);
            } else {//不足
                subtract = remainAmount;
                alreadyCheck = alreadyCheck.add(remainAmount);
                remainAmount = new BigDecimal("0.00");
                alreadyAmount = blReceiptInfo.getAmount();
            }
            // gengxin liushui
            blReceiptInfo.setOldAlreadyAmount(blReceiptInfo.getAlreadyAmount());
            blReceiptInfo.setOldRemainAmount(blReceiptInfo.getRemainAmount());
            blReceiptInfo.setModifyBy(loginUser.getUserId());
            blReceiptInfo.setModifyDt(new Date());
            blReceiptInfo.setRemainAmount(remainAmount);
            blReceiptInfo.setAlreadyAmount(alreadyAmount);
            if (Objects.equals(new BigDecimal("0.00"), blReceiptInfo.getRemainAmount())) {
                blReceiptInfo.setCheckStatus(BlReceiptInfo.CHECK_STATUS_ALL_CHECKED);
            } else {
                blReceiptInfo.setCheckStatus(BlReceiptInfo.CHECK_STATUS_PART_CHECKED);
            }
            toUpdateReceipt.add(blReceiptInfo);
            //增加核销记录
            BlReceiptWriteOffInfo info = new BlReceiptWriteOffInfo();
            info.setOrderCode(payVoucher.getId().toString());
            info.setReceiptId(blReceiptInfo.getId());
            info.setWriteOffType(payVoucher.getTradeType());
            info.setWriteOffAmount(subtract);
            info.setCreateTime(new Date());
            info.setCreateBy(loginUser.getUserId());
            info.setDr(0);
            info.setRemark(remark);
            toInsertWriteOff.add(info);
            if (compareTo > -1) {
                temp = i;
                break;
            }
        }
        /*if (completeCheck) {
            if (compareTo != 0 || (temp != receiptInfoList.size() - 1) ){
                throw new RuntimeException("创建核销单/充值到账户 必须一次核销掉所选流水剩余全部金额");
            }
        }*/
//        if (request.getUserBalance()) {
////            alreadyCheck = alreadyCheck.add(blBalance.getAmount());
//        }
        //更新订单
        payVoucher.setOldAlreadyCheck(payVoucher.getAlreadyCheck());
        payVoucher.setOldUseBalace(payVoucher.getUseBalance());
        if (userBalance && amount.compareTo(alreadyCheck) > 0) {
            BigDecimal blBalanceAmount = blBalance.getAmount();
            BigDecimal subtract = amount.subtract(alreadyCheck);
            if (blBalanceAmount.compareTo(subtract) > -1) {
                payVoucher.setUseBalance(subtract);
//                alreadyCheck = alreadyCheck.add(subtract);
            } else {
                subtract = blBalanceAmount;
                payVoucher.setUseBalance(subtract);
//                alreadyCheck = alreadyCheck.add(blBalanceAmount);
            }
            useBalance = subtract;
           /* BlReceiptWriteOffInfo info = new BlReceiptWriteOffInfo();
            info.setOrderCode(payVoucher.getId().toString());
            info.setReceiptId(blBalance.getId());
            info.setWriteOffType(payVoucher.getTradeType());
            info.setWriteOffAmount(subtract);
            info.setCreateTime(new Date());
            info.setCreateBy(loginUser.getUserId());
            info.setDr(0);
            info.setRemark("use balance");
            toInsertWriteOff.add(info);*/
        }
        payVoucher.setAlreadyCheck(alreadyCheck);
        payVoucher.setModifyBy(loginUser.getUserId());
        log.info(MessageFormat.format("begin to update payvoucher....id:{0},check:{1},balance:{2}", payVoucher.getId(), payVoucher.getAlreadyCheck(), payVoucher.getUseBalance()));
        int success = blPayVoucherService.updateOrderByCheck(loginUser, payVoucher);
        Assert.isTrue(success > 0, "更新订单失败");
        log.info(MessageFormat.format("begin to update payvoucher..end...id:{0}", payVoucher.getId()));
        //更新流水
        for (BlReceiptInfo receiptInfo : toUpdateReceipt) {
            log.info(MessageFormat.format("begin to update receiptInfo....id:{0},already:{1},remain:{2}", receiptInfo.getId(), receiptInfo.getAlreadyAmount(), receiptInfo.getRemainAmount()));
            int i = blReceiptInfoService.updateReceiptInfByCheck(loginUser, receiptInfo);
            Assert.isTrue(i > 0, "更新流水失败");
            log.info(MessageFormat.format("begin to update receiptInfo....id:{0}", receiptInfo.getId()));
        }
        //更新流水核销明细
        for (BlReceiptWriteOffInfo info : toInsertWriteOff) {
            log.info(MessageFormat.format("begin to insert receiptInfo....getReceiptId:{0},getOrderCode:{1},getWriteOffAmount:{2}", info.getReceiptId(), info.getOrderCode(), info.getWriteOffAmount()));
            int save = blReceiptWriteOffInfoService.save(loginUser, info);
            Assert.isTrue(save > 0, "写入流水详情（核销明细）失败");
            log.info(MessageFormat.format("begin to insert receiptInfo end....getReceiptId:{0},getOrderCode:{1},getWriteOffAmount:{2}", info.getReceiptId(), info.getOrderCode(), info.getWriteOffAmount()));
        }
        if (alreadyCheck.add(useBalance).compareTo(payVoucher.getAmount()) > -1) {
            //通知订单完成
            boolean paymentSuccess = blPayVoucherService.paymentSuccess(payVoucher);
            Assert.isTrue(paymentSuccess,"核销失败");
        } else if (completeCheck) {
            //创建核销单/充值到账户
            throw new RuntimeException("创建核销单或者充值金额异常，请查看流水是否被占用");
        }
    }

    @Override
    public Map<String, Object> checkRecordDetail(String tradeNo, String busId) {
        Map data = blPayVoucherService.checkRecordDetail(tradeNo, busId);
        Assert.notNull(data, "订单查询失败");
        Integer orderId = (Integer) data.get("id");
        BlReceiptWriteOffInfo blReceiptWriteOffInfo = new BlReceiptWriteOffInfo();
        blReceiptWriteOffInfo.setId(orderId);
        List<Map> serials = blReceiptWriteOffInfoService.checkRecordDetail(blReceiptWriteOffInfo);
        Map<String, Object> result = Maps.newHashMap();
        result.put("order", data);
        result.put("serials", serials);
        return result;
    }

    @Override
    public Map<String, Object> createCheckOrder(CheckCreateVo checkCreateVo, ContextInfo contextInfo) throws Exception {
        Map<String, Object> response = Maps.newHashMap();
        SpService spService = new SpService();
        spService.setSpNameEq(checkCreateVo.getBuyerName());
        spService.setMobileEq(checkCreateVo.getMobile());
        List<SpService> spServices = spServiceService.findAll(spService);
        Assert.isTrue(spServices.size() == 1, "查询服务商失败，请正确填写服务商名称和手机号码");
        SpService temp = spServices.get(0);
        RespXfsPay respXfsPay = blPayBusinessService.queryBusinByBusId(7);
        Assert.notNull(respXfsPay, "找不到对应的busid为7的支付基础信息");
        ToReqPay toReqPay = new ToReqPay();
        toReqPay.setBusId(respXfsPay.getBusId());
        toReqPay.setPayId(4);
        toReqPay.setOuterTradeNo("CHE" + DateTime.now().toString("yyyyMMddHHmmss"));
        toReqPay.setAmount(new BigDecimal(checkCreateVo.getCheckMoney()));
        toReqPay.setPayType(PayContants.PAY_TYPE_OFFLINE);
        toReqPay.setCreateBy(contextInfo.getUserId());
        toReqPay.setTradeType(PayContants.TRADE_TYPE_PAY_FEE);
        toReqPay.setBuyerName(temp.getSpName());
        toReqPay.setBuyerId(temp.getSpId().toString());
        toReqPay.setBuyerType(PayContants.BUSIN_TYPE_SERVICE);
        toReqPay.setProductIntro("自建核销单:" + checkCreateVo.getCheckType());
        Result result = Result.createResult();
        result.setSuccess(true);
        blPayVoucherService.createPayVocher(toReqPay, result, respXfsPay);
        if (result.isSuccess()) {
            BlPayVoucher blPayVoucher = (BlPayVoucher) result.getData().get("blPayVoucher");
            BlAlreadyCheckPayVoucher blAlreadyCheckPayVoucher = new BlAlreadyCheckPayVoucher();
            BeanUtils.copyProperties(blPayVoucher, blAlreadyCheckPayVoucher);
            blAlreadyCheckPayVoucher.setAlreadyCheck(null);
            blAlreadyCheckPayVoucher.setOldAlreadyCheck(null);
            blAlreadyCheckPayVoucher.setOldUseBalace(null);
            verificateOrder(contextInfo, blAlreadyCheckPayVoucher, false, checkCreateVo.getIds(), "自建核销单:" + checkCreateVo.getCheckRemark(), true);
            response.put("tradeNo", blAlreadyCheckPayVoucher.getOuterTradeNo());
            response.put("busId", blAlreadyCheckPayVoucher.getBusId());
        } else {
            throw new RuntimeException(result.getMsg());
        }

        return response;
    }

    @Override
    public Map<String, Object> createChargeOrder(CheckChargeVo checkChargeVo, ContextInfo contextInfo) throws Exception {
        Map<String, Object> response = Maps.newHashMap();
        CmCorp cmCorpTemp=new CmCorp();
        SpService spServiceTemp =new SpService();
        if(Objects.equals("service",(checkChargeVo.getTarget()))){
            SpService spService = new SpService();
            spService.setSpNameEq(checkChargeVo.getChargeName());
            spService.setMobileEq(checkChargeVo.getChargeMoblie());
            List<SpService> spServices = spServiceService.findAll(spService);
            Assert.isTrue(spServices.size() == 1, "查询服务商失败，请正确填写服务商名称和手机号码");
            spServiceTemp = spServices.get(0);
        }else {
            CmCorp cmCorp = new CmCorp();
            cmCorp.setDr(0);
            cmCorp.setMobileEq(checkChargeVo.getChargeMoblie());
            cmCorp.setCorpNameEq(checkChargeVo.getChargeName());
            List<CmCorp> cmCorps = cmCorpService.findAll(cmCorp);
            Assert.isTrue(cmCorps.size() == 1, "查询客户失败，请正确填写客户名称和手机号码");
            cmCorpTemp= cmCorps.get(0);
        }
        RespXfsPay respXfsPay = blPayBusinessService.queryBusinByBusId(7);
        Assert.notNull(respXfsPay, "找不到对应的busid为7的支付基础信息");
        ToReqPay toReqPay = new ToReqPay();
        toReqPay.setBusId(respXfsPay.getBusId());
        toReqPay.setPayId(4);
        toReqPay.setOuterTradeNo("CHR" + DateTime.now().toString("yyyyMMddHHmmss"));
        toReqPay.setAmount(new BigDecimal(checkChargeVo.getChargeMoney()));
        toReqPay.setPayType(PayContants.PAY_TYPE_OFFLINE);
        toReqPay.setCreateBy(contextInfo.getUserId());
        toReqPay.setTradeType(PayContants.TRADE_TYPE_RECHARGE_FEE);
        if (Objects.equals("service", (checkChargeVo.getTarget()))) {
            toReqPay.setBuyerName(spServiceTemp.getSpName());
            toReqPay.setBuyerId(spServiceTemp.getSpId().toString());
            toReqPay.setBuyerType(PayContants.BUSIN_TYPE_SERVICE);
            toReqPay.setProductIntro("核销充值(SERVICE)");
        }else{
            toReqPay.setBuyerName(cmCorpTemp.getCorpName());
            toReqPay.setBuyerId(cmCorpTemp.getCpId().toString());
            toReqPay.setBuyerType(PayContants.BUSIN_TYPE_CORP);
            toReqPay.setProductIntro("核销充值(CORP)");
        }
        Result result = Result.createResult();
        result.setSuccess(true);
        blPayVoucherService.createPayVocher(toReqPay, result, respXfsPay);
        if (result.isSuccess()) {
            BlPayVoucher blPayVoucher = (BlPayVoucher) result.getData().get("blPayVoucher");
            BlAlreadyCheckPayVoucher blAlreadyCheckPayVoucher = new BlAlreadyCheckPayVoucher();
            BeanUtils.copyProperties(blPayVoucher, blAlreadyCheckPayVoucher);
            blAlreadyCheckPayVoucher.setAlreadyCheck(null);
            blAlreadyCheckPayVoucher.setOldAlreadyCheck(null);
            blAlreadyCheckPayVoucher.setOldUseBalace(null);
            verificateOrder(contextInfo, blAlreadyCheckPayVoucher, false, checkChargeVo.getIds(), "核销充值:" + checkChargeVo.getChargeRemark(), true);
            response.put("tradeNo", blAlreadyCheckPayVoucher.getOuterTradeNo());
            response.put("busId", blAlreadyCheckPayVoucher.getBusId());
        } else {
            throw new RuntimeException(result.getMsg());
        }
        return response;
    }

}
