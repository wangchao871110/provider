package com.xfs.finance.service;

import com.xfs.finance.model.SpsReconciliationDetail;
import com.xfs.finance.vo.SpsReconciliationDetailVo;

import java.util.List;

/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
public interface SpsReconciliationDetailService {
    int deleteByPrimaryKey(Long id);

    int insert(SpsReconciliationDetail record);

    SpsReconciliationDetail selectByPrimaryKey(Long id);

    List<SpsReconciliationDetail> selectAll();

    int updateByPrimaryKey(SpsReconciliationDetail record);

    /**
     * 删除详情
     *
     * @param list
     */
    void deleteDetailsByInfoId(List<Long> list);

    /**
     * 批量插入险种
     *
     * @param infoId
     * @param list
     */
    void batchInsert(Long infoId, List<SpsReconciliationDetail> list);

    List<SpsReconciliationDetailVo> getDetail(List<Long> lIds ,List<Long> rIds);
}
