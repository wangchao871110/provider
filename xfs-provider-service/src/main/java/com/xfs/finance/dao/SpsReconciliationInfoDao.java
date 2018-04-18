package com.xfs.finance.dao;

import com.xfs.finance.model.SpsReconciliationInfo;
import com.xfs.finance.vo.SpsReconciliationInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpsReconciliationInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationInfo record);

    SpsReconciliationInfo selectByPrimaryKey(Long id);

    List<SpsReconciliationInfo> selectAll();

    int updateByPrimaryKey(SpsReconciliationInfo record);

    List<SpsReconciliationInfo> selectInfoByrecords(@Param("list") List<Long> list);

    void deleteByKeys(@Param("list") List<Long> list);

    List<SpsReconciliationInfoVo> queryByPage(
            @Param("typeId") Long typeId,
            @Param("lType") Integer lType,
            @Param("rType") Integer rType,
            @Param("attachMonth") String attachMonth,
            @Param("billMonth") String billMonth,
            @Param("socialCity") String socialCity,
            @Param("fundCity") String fundCity,
            @Param("diffType") String diffType,
            @Param("empName") String empName,
            @Param("idNo") String idNo,
            @Param("offset") int offset, @Param("limit") int limit);

    int queryTotal(@Param("typeId") Long typeId,
                   @Param("lType") Integer lType,
                   @Param("rType") Integer rType,
                   @Param("attachMonth") String attachMonth,
                   @Param("billMonth") String billMonth,
                   @Param("socialCity") String socialCity,
                   @Param("fundCity") String fundCity,
                   @Param("diffType") String diffType,
                   @Param("empName") String empName,
                   @Param("idNo") String idNo);

    Float sumTotalByQuery(@Param("typeId") Long typeId,
                          @Param("lType") Integer lType,
                          @Param("rType") Integer rType,
                          @Param("attachMonth") String attachMonth,
                          @Param("billMonth") String billMonth,
                          @Param("socialCity") String socialCity,
                          @Param("fundCity") String fundCity,
                          @Param("diffType") String diffType,
                          @Param("empName") String empName,
                          @Param("idNo") String idNo);
    Float sumTotal();

    List<String> querySocialCity(@Param("type") Integer type);

    List<String> queryFundCity(@Param("type") Integer type);
}