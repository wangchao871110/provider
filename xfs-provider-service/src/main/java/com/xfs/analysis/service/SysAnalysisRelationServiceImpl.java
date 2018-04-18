package com.xfs.analysis.service;
import java.util.List;

import com.xfs.analysis.dao.SysAnalysisRelationDao;
import com.xfs.analysis.dto.SysAnalysisRelation;
import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class SysAnalysisRelationServiceImpl implements SysAnalysisRelationService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysAnalysisRelationServiceImpl.class);
	
	@Autowired
	private SysAnalysisRelationDao sysAnalysisRelationDao;
	
	public int save(ContextInfo cti, SysAnalysisRelation vo ){
		return sysAnalysisRelationDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, SysAnalysisRelation vo ){
		return sysAnalysisRelationDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, SysAnalysisRelation vo ){
		return sysAnalysisRelationDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, SysAnalysisRelation vo ){
		return sysAnalysisRelationDao.update(cti,vo);
	}
	public PageModel findPage(SysAnalysisRelation vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysAnalysisRelationDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysAnalysisRelation> datas = sysAnalysisRelationDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysAnalysisRelation> findAll(SysAnalysisRelation vo){
		
		List<SysAnalysisRelation> datas = sysAnalysisRelationDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:31
	
	
	
}
