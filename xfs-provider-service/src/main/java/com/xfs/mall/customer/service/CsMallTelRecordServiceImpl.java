package com.xfs.mall.customer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.dao.CsMallTelRecordDao;
import com.xfs.mall.customer.model.CsMallTelRecord;


@Service
public class CsMallTelRecordServiceImpl implements CsMallTelRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsMallTelRecordServiceImpl.class);
	
	@Autowired
	private CsMallTelRecordDao csMallTelRecordDao;
	
	public int save(ContextInfo cti, CsMallTelRecord vo ){
		return csMallTelRecordDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsMallTelRecord vo ){
		return csMallTelRecordDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsMallTelRecord vo ){
		return csMallTelRecordDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsMallTelRecord vo ){
		return csMallTelRecordDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsMallTelRecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallTelRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsMallTelRecord> datas = csMallTelRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsMallTelRecord> findAll(CsMallTelRecord vo){
		
		List<CsMallTelRecord> datas = csMallTelRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:29:12
	
	
	
}
