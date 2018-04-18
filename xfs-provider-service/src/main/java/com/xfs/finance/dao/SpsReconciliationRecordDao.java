package com.xfs.finance.dao;

import com.xfs.finance.model.SpsReconciliationRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SpsReconciliationRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationRecord record);

    SpsReconciliationRecord selectByPrimaryKey(Long id);

    List<SpsReconciliationRecord> selectAll();

    int updateByPrimaryKey(SpsReconciliationRecord record);

    List<SpsReconciliationRecord> selectRecordByTypeIdAndMonth(@Param("type") Integer type, @Param("typeId") Integer typeId, @Param("billMonth") String billMonth);

    void deleteByKeys(@Param("userId") Long userId, @Param("list") List<Long> list, @Param("updateTime") Date updateTime);

    List<SpsReconciliationRecord> queryByPage(
            @Param("type") Integer type,
            @Param("beginDate") String beginDate,
            @Param("endDate") String endDate,
            @Param("createName") String createName,
            @Param("offset") int offset, @Param("limit") int limit);

    int queryTotal(@Param("type") Integer type,
                   @Param("beginDate") String beginDate,
                   @Param("endDate") String endDate,
                   @Param("createName") String createName);
}