package com.xfs.corp.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.corp.dao.CmCorpTraceRecordDao;
import com.xfs.corp.model.CmCorpTraceRecord;
import com.xfs.corp.service.CmCorpTraceRecordService;


@Service
public class CmCorpTraceRecordServiceImpl implements CmCorpTraceRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmCorpTraceRecordServiceImpl.class);
	
	@Autowired
	private CmCorpTraceRecordDao cmCorpTraceRecordDao;
	
	public int save( ContextInfo cti,CmCorpTraceRecord vo ){
		return cmCorpTraceRecordDao.save(cti,vo);
	}
	public int insert( ContextInfo cti,CmCorpTraceRecord vo ){
		return cmCorpTraceRecordDao.insert(cti,vo);
	}
	public int remove( ContextInfo cti,CmCorpTraceRecord vo ){
		return cmCorpTraceRecordDao.remove(cti,vo);
	}
	public int update( ContextInfo cti,CmCorpTraceRecord vo ){
		return cmCorpTraceRecordDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi,CmCorpTraceRecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = cmCorpTraceRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmCorpTraceRecord> datas = cmCorpTraceRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmCorpTraceRecord> findAll(CmCorpTraceRecord vo){
		
		List<CmCorpTraceRecord> datas = cmCorpTraceRecordDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:51:17
	
	@Override
	public CmCorpTraceRecord findRecordInfo(CmCorpTraceRecord vo) {
		return cmCorpTraceRecordDao.findNewInfo(vo);
	}
	
}
