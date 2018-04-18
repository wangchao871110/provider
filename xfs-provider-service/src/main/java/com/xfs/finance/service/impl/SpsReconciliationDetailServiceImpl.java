package com.xfs.finance.service.impl;

import com.xfs.finance.dao.SpsReconciliationDetailDao;
import com.xfs.finance.model.SpsReconciliationDetail;
import com.xfs.finance.service.SpsReconciliationDetailService;
import com.xfs.finance.vo.SpsReconciliationDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
@Service
public class SpsReconciliationDetailServiceImpl implements SpsReconciliationDetailService {
    //@Autowired
    //SpsReconciliationDetailDao reconciliationDetailDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
//        return reconciliationDetailDao.deleteByPrimaryKey(id);
    	return 0;
    }

    @Override
    public int insert(SpsReconciliationDetail record) {
//        return reconciliationDetailDao.insert(record);
    	return 0;
    }

    @Override
    public SpsReconciliationDetail selectByPrimaryKey(Long id) {
//        return reconciliationDetailDao.selectByPrimaryKey(id
//        );
        return null;
    }

    @Override
    public List<SpsReconciliationDetail> selectAll() {
//        return reconciliationDetailDao.selectAll();
    	return null;
    }

    @Override
    public int updateByPrimaryKey(SpsReconciliationDetail record) {
//        return reconciliationDetailDao.updateByPrimaryKey(record);
    	return 1;
    }

    @Override
    public void deleteDetailsByInfoId(List<Long> list) {
//        if (list != null && !list.isEmpty()) {
//            reconciliationDetailDao.deleteByInfoId(list);
//        }
    }

    @Override
    public void batchInsert(Long infoId, List<SpsReconciliationDetail> list) {
//        reconciliationDetailDao.batchInsert(infoId, list);
    }

    @Override
    public List<SpsReconciliationDetailVo> getDetail(List<Long> lIds ,List<Long> rIds) {
//        return reconciliationDetailDao.getDetail(lIds,rIds);
    	return null;
    }
//    ==================================================================================================== //
}
