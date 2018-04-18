package com.xfs.finance.service;

import com.xfs.finance.model.SpsReconciliationInfo;
import com.xfs.finance.vo.SpsReconciliationInfoVo;

import java.util.List;

/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
public interface SpsReconciliationInfoService {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationInfo record);

    SpsReconciliationInfo selectByPrimaryKey(Long id);

    List<SpsReconciliationInfo> selectAll();

    int updateByPrimaryKey(SpsReconciliationInfo record);


    List<SpsReconciliationInfoVo> queryByPage(
            Long typeId,
            Integer lType,
            Integer rType,
            String attachMonth,
            String billMonth,
            String socialCity,
            String fundCity,
            String diffType,
            String empName,String idNo,
            int offset, int limit);

    int queryTotal(Long typeId,
                   Integer lType,
                   Integer rType,
                   String attachMonth,
                   String billMonth,
                   String socialCity,
                   String fundCity,
                   String diffType,
                   String empName,String idNo);

    Float sumTotalByQuery(Long typeId,
                          Integer lType,
                          Integer rType,
                          String attachMonth,
                          String billMonth,
                          String socialCity,
                          String fundCity,
                          String diffType,
                          String empName,String idNo);

    Float sumTotalByTypeId(Long typeId, Integer lType, Integer rType);

    Float sumTotal();

    List<String> querySocialCity(Integer type);

    List<String> queryFundCity(Integer type);

    /**
     * 查询人员基本信息
     *
     * @param ids 上传记录id
     * @return
     */
    List<SpsReconciliationInfo> selectInfoByRecords(List<Long> ids);

    /**
     * 根据id 删除人员信息
     *
     * @param list
     */
    void deleteByKeys(List<Long> list);

    List<SpsReconciliationInfo> resolveExcel(Long fileId);
}
