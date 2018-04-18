package com.xfs.activity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdLotteryRecordDao;
import com.xfs.activity.model.BdLotteryRecord;
import com.xfs.activity.service.BdLotteryRecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BdLotteryRecordServiceImpl implements BdLotteryRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdLotteryRecordServiceImpl.class);
	
	@Autowired
	private BdLotteryRecordDao bdLotteryRecordDao;
	
	public int save(ContextInfo cti, BdLotteryRecord vo ){
		return bdLotteryRecordDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdLotteryRecord vo ){
		return bdLotteryRecordDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdLotteryRecord vo ){
		return bdLotteryRecordDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdLotteryRecord vo ){
		return bdLotteryRecordDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdLotteryRecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdLotteryRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdLotteryRecord> datas = bdLotteryRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdLotteryRecord> findAll(BdLotteryRecord vo){
		
		List<BdLotteryRecord> datas = bdLotteryRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/10 20:26:29

	public int countFreeFind(BdLotteryRecord vo){
		return bdLotteryRecordDao.countFreeFind(vo);
	}

	public int batchInsert(ContextInfo cti, List<BdLotteryRecord> list){
		return bdLotteryRecordDao.batchInsert(cti, list);
	}

	public List<BdLotteryRecord> findAll(BdLotteryRecord obj, String orderByColName) {
		return bdLotteryRecordDao.freeFind(obj, orderByColName);
	}

}
