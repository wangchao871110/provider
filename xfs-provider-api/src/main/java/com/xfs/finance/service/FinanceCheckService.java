package com.xfs.finance.service;

import com.xfs.common.ContextInfo;
import com.xfs.finance.model.BlReceiptInfo;
import com.xfs.finance.vo.BlReceiptCheckRequest;
import com.xfs.finance.vo.BlReceiptFormRequest;
import com.xfs.finance.vo.CheckChargeVo;
import com.xfs.finance.vo.CheckCreateVo;
import com.xfs.settlement.model.BlAlreadyCheckPayVoucher;
import com.xfs.settlement.model.BlBalance;
import com.xfs.settlement.model.BlPayVoucher;

import java.util.List;
import java.util.Map;

/**
 * Created by yuanm on 12/29/2016.
 */
public interface FinanceCheckService {
    /**
     * 查询流水
     *
     * @param receiptFormRequest
     * @return
     */
    List<BlReceiptInfo> findSerialsNoByVo(BlReceiptFormRequest receiptFormRequest);

    /**
     * 查询订单
     *
     * @param blPayVoucher
     * @return
     */
    List<BlAlreadyCheckPayVoucher> queryVoucherByTradeNoAndBusId(BlPayVoucher blPayVoucher);

    /**
     * 查询余额
     *
     * @param blBalance
     * @return
     */
    BlBalance queryBalance(BlBalance blBalance);

    /**
     * 核销
     *
     * @param userId
     * @param receiptCheckRequest
     * @param loginUser
     */
    void updateOrderAndReceiptInfo(BlReceiptCheckRequest receiptCheckRequest, ContextInfo loginUser) throws Exception;

    /**
     * 查询订单 核销记录详情
     *
     * @param tradeNo
     * @param busId
     * @return
     */
    Map<String, Object> checkRecordDetail(String tradeNo, String busId);

    /**
     * 创建核销单
     *
     * @param checkCreateVo
     * @param contextInfo
     * @return
     */
    Map<String, Object> createCheckOrder(CheckCreateVo checkCreateVo, ContextInfo contextInfo) throws Exception;

    /**
     * 充值到账户
     *
     * @param checkChargeVo
     * @param contextInfo
     * @return
     */
    Map<String, Object> createChargeOrder(CheckChargeVo checkChargeVo, ContextInfo contextInfo) throws Exception;
}
