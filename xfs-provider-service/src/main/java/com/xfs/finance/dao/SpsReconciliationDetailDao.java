package com.xfs.finance.dao;

import com.xfs.finance.model.SpsReconciliationDetail;
import com.xfs.finance.vo.SpsReconciliationDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpsReconciliationDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationDetail record);

    SpsReconciliationDetail selectByPrimaryKey(Long id);

    List<SpsReconciliationDetail> selectAll();

    int updateByPrimaryKey(SpsReconciliationDetail record);

    void deleteByInfoId(@Param("list") List<Long> list);

    void batchInsert(@Param("infoId") Long infoId, @Param("list") List<SpsReconciliationDetail> list);


    List<SpsReconciliationDetailVo> getDetail(@Param("lList") List<Long> lList,@Param("rList") List<Long> rList);
}