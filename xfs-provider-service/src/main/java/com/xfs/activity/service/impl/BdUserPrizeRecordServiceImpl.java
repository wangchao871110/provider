package com.xfs.activity.service.impl;

import com.xfs.activity.dao.BdUserPrizeRecordDao;
import com.xfs.activity.model.BdUserPrizeRecord;
import com.xfs.activity.service.BdUserPrizeRecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BdUserPrizeRecordServiceImpl implements BdUserPrizeRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdUserPrizeRecordServiceImpl.class);
	
	@Autowired
	private BdUserPrizeRecordDao bdUserPrizeRecordDao;
	
	public int save(ContextInfo cti, BdUserPrizeRecord vo ){
		return bdUserPrizeRecordDao.save(cti, vo);
	}
	public int insert(ContextInfo cti, BdUserPrizeRecord vo ){
		return bdUserPrizeRecordDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti, BdUserPrizeRecord vo ){
		return bdUserPrizeRecordDao.remove(cti, vo);
	}
	public int update(ContextInfo cti, BdUserPrizeRecord vo ){
		return bdUserPrizeRecordDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdUserPrizeRecord vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdUserPrizeRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdUserPrizeRecord> datas = bdUserPrizeRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdUserPrizeRecord> findAll(BdUserPrizeRecord vo){
		
		List<BdUserPrizeRecord> datas = bdUserPrizeRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:49:40
	
	public int updateByOrder(ContextInfo cti, String outTradeOrder) {
		return bdUserPrizeRecordDao.updateByOrder(cti, outTradeOrder);
	}
	
}
