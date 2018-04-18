package com.xfs.finance.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.xfs.finance.dao.SpsReconciliationRecordDao;
import com.xfs.finance.model.SpsReconciliationInfo;
import com.xfs.finance.model.SpsReconciliationRecord;
import com.xfs.finance.service.SpsReconciliationDetailService;
import com.xfs.finance.service.SpsReconciliationInfoService;
import com.xfs.finance.service.SpsReconciliationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
@Service
public class SpsReconciliationRecordServiceImpl implements SpsReconciliationRecordService {
    //@Autowired
    //SpsReconciliationRecordDao reconciliationRecordDao;

    @Autowired
    SpsReconciliationInfoService reconciliationInfoService;
    @Autowired
    SpsReconciliationDetailService reconciliationDetailService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //return reconciliationRecordDao.deleteByPrimaryKey(id);
    	return 1;
    }

    @Override
    public int insert(SpsReconciliationRecord record) {
        //return reconciliationRecordDao.insert(record);
    	return 1;
    }

    @Override
    public SpsReconciliationRecord selectByPrimaryKey(Long id) {
        //return reconciliationRecordDao.selectByPrimaryKey(id);
    	return null;
    }

    @Override
    public List<SpsReconciliationRecord> selectAll() {
        //return reconciliationRecordDao.selectAll();
    	return null;
    }

    @Override
    public int updateByPrimaryKey(SpsReconciliationRecord record) {
        //return reconciliationRecordDao.updateByPrimaryKey(record);
    	return 1;
    }


    // ===================================================

    @Override
    public List<SpsReconciliationRecord> selectRecordByTypeIdAndMonth(Integer type, Integer typeId, String billMonth) {

        //return reconciliationRecordDao.selectRecordByTypeIdAndMonth(type, typeId, billMonth);
    	return null;
    }

    @Override
    public void deleteByPrimaryKeys(Long userId, List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            final List<SpsReconciliationInfo> infoList = reconciliationInfoService.selectInfoByRecords(ids);
            if (!infoList.isEmpty()) {
                List<Long> infoIds = Lists.transform(infoList, new Function<SpsReconciliationInfo, Long>() {
                    @Override
                    public Long apply(SpsReconciliationInfo input) {
                        return input.getId();
                    }
                });
                reconciliationDetailService.deleteDetailsByInfoId(infoIds);
                reconciliationInfoService.deleteByKeys(infoIds);
            }
            //reconciliationRecordDao.deleteByKeys(userId, ids, new Date());
        }
    }

    @Override
    public void addExcelRecord(Long userId, Long fileId, String billMonth, Integer type, Integer typeId, String typeName, List<SpsReconciliationInfo> infoList) {
        if (!infoList.isEmpty()) {
            //查询是否有重复的记录
            //List<SpsReconciliationRecord> records = reconciliationRecordDao.selectRecordByTypeIdAndMonth(type, typeId, billMonth);
        	List<SpsReconciliationRecord> records = null;
        	// 若有一存在的记录则删除
            if (!records.isEmpty()) {
                List<Long> ids = Lists.transform(records, new Function<SpsReconciliationRecord, Long>() {
                    @Override
                    public Long apply(SpsReconciliationRecord input) {
                        return input.getId();
                    }
                });
                this.deleteByPrimaryKeys(Long.valueOf(userId), ids);
            }
            Date createDate = new Date();
            SpsReconciliationRecord record = new SpsReconciliationRecord();
            record.setFileId(fileId);
            record.setType(type);
            record.setTypeId(typeId);
            record.setTypeName(typeName);
            record.setBillMonth(billMonth);
            record.setCreateBy(userId);
            record.setCreateTime(createDate);
            record.setDr(0);
            //reconciliationRecordDao.insert(record);
            for (SpsReconciliationInfo info : infoList) {
                info.setType(type);
                info.setTypeId(Long.valueOf(typeId));
                info.setTypeName(typeName);
                info.setBillMonth(billMonth);
                info.setRecordId(record.getId());
                reconciliationInfoService.insert(info);

                if (info.getDetailList() != null && !info.getDetailList().isEmpty()) {
                    reconciliationDetailService.batchInsert(info.getId(), info.getDetailList());
                }

            }
        }


    }

    @Override
    public List<SpsReconciliationRecord> queryByPage(Integer type, String beginDate, String endDate, String createName, int offset, int limit) {
        //return reconciliationRecordDao.queryByPage(type,beginDate,endDate,createName,offset,limit);
    	return null;
    }

    @Override
    public int queryTotal(Integer type, String beginDate, String endDate, String createName) {
        //return reconciliationRecordDao.queryTotal(type,beginDate,endDate,createName);
    	return 1;
    }
}
