package com.xfs.finance.service;

import com.xfs.finance.model.SpsReconciliationInfo;
import com.xfs.finance.model.SpsReconciliationRecord;

import java.util.List;

/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
public interface SpsReconciliationRecordService {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationRecord record);

    SpsReconciliationRecord selectByPrimaryKey(Long id);

    List<SpsReconciliationRecord> selectAll();

    int updateByPrimaryKey(SpsReconciliationRecord record);

    /**
     * @param type      客户 0 服务商 1
     * @param typeId    客户或服务商 id
     * @param billMonth 账单月
     * @return
     */

    List<SpsReconciliationRecord> selectRecordByTypeIdAndMonth(Integer type, Integer typeId, String billMonth);

    /**
     * 删除上传记录
     * 1.删除人员详情记录
     * 2.删除人员基本寄信
     * 3.删除记录信息
     *
     * @param userId
     * @param ids
     */
    void deleteByPrimaryKeys(Long userId, List<Long> ids);

    void addExcelRecord(Long userId, Long fileId, String billMonth, Integer type, Integer typeId, String typeName, List<SpsReconciliationInfo> infoList);

    List<SpsReconciliationRecord> queryByPage(Integer type, String beginDate, String endDate, String createName, int offset, int limit);

    int queryTotal(Integer type, String beginDate, String endDate, String createName);
}
