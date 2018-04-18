package com.xfs.analysis.service;
import java.util.List;

import com.xfs.analysis.dao.SysAnalysisBustypeDao;
import com.xfs.analysis.dto.SysAnalysisBustype;
import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class SysAnalysisBustypeServiceImpl implements SysAnalysisBustypeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysAnalysisBustypeServiceImpl.class);
	
	@Autowired
	private SysAnalysisBustypeDao sysAnalysisBustypeDao;
	
	public int save(ContextInfo cti, SysAnalysisBustype vo ){
		return sysAnalysisBustypeDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, SysAnalysisBustype vo ){
		return sysAnalysisBustypeDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, SysAnalysisBustype vo ){
		return sysAnalysisBustypeDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, SysAnalysisBustype vo ){
		return sysAnalysisBustypeDao.update(cti,vo);
	}
	public PageModel findPage(SysAnalysisBustype vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysAnalysisBustypeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysAnalysisBustype> datas = sysAnalysisBustypeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysAnalysisBustype> findAll(SysAnalysisBustype vo){

		List<SysAnalysisBustype> datas = sysAnalysisBustypeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:24
	
	
	
}
