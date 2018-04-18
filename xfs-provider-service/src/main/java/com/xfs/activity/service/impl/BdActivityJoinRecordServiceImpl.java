package com.xfs.activity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdActivityJoinRecordDao;
import com.xfs.activity.model.BdActivityJoinRecord;
import com.xfs.activity.service.BdActivityJoinRecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BdActivityJoinRecordServiceImpl implements BdActivityJoinRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdActivityJoinRecordServiceImpl.class);
	
	@Autowired
	private BdActivityJoinRecordDao bdActivityJoinRecordDao;
	
	public int save(ContextInfo cti, BdActivityJoinRecord vo ){
		return bdActivityJoinRecordDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdActivityJoinRecord vo ){
		return bdActivityJoinRecordDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdActivityJoinRecord vo ){
		return bdActivityJoinRecordDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdActivityJoinRecord vo ){
		return bdActivityJoinRecordDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdActivityJoinRecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdActivityJoinRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdActivityJoinRecord> datas = bdActivityJoinRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdActivityJoinRecord> findAll(BdActivityJoinRecord vo){
		
		List<BdActivityJoinRecord> datas = bdActivityJoinRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/18 16:34:21
	
	
	
}
