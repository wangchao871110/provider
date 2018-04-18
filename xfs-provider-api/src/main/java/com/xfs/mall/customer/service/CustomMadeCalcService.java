package com.xfs.mall.customer.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.mall.dto.customMadeCalc.CustomMadeCalcDTO;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/29.
 */
public interface CustomMadeCalcService {

    final String TABLE_NAME = "MALLCustomMadeCalc";

    /**
     * 从mongodb中获取数据
     *
     * @param id
     * @return
     */
    CustomMadeCalcDTO getCustomMadeCalcData(String id);

    /**
     * 保存信息
     *
     * @param emps
     * @param months
     * @param insuServices
     * @param ciType
     * @param ciServices
     * @param welfareType
     * @param welfareServices
     * @param residentType
     * @param residentService
     * @param otherServices
     * @param traditionalHR
     * @param traditionalCorpCost
     * @param customCutsourcing
     * @param customCorpCost
     * @return
     */
    public Result setCustomMadeCalcData(ContextInfo cti, String id, String emps, Integer months, String insuServices, Integer ciType, String ciServices,
                                        Integer welfareType, String welfareServices, Integer residentType, String residentService, String otherServices,
                                        BigDecimal traditionalHR, BigDecimal traditionalCorpCost, BigDecimal customCutsourcing, BigDecimal customCorpCost);

    /**
     * 设置数据到mongodb
     *
     * @param dto
     */
    boolean setCustomMadeCalcData(CustomMadeCalcDTO dto);

}
