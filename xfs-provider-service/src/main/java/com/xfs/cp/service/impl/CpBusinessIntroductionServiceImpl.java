package com.xfs.cp.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.cp.dao.CpBusinessIntroductionDao;
import com.xfs.cp.model.CpBusinessIntroduction;
import com.xfs.cp.service.CpBusinessIntroductionService;


@Service
public class CpBusinessIntroductionServiceImpl implements CpBusinessIntroductionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpBusinessIntroductionServiceImpl.class);
	
	@Autowired
	private CpBusinessIntroductionDao cpBusinessIntroductionDao;
	
	public int save( CpBusinessIntroduction vo ){
		return cpBusinessIntroductionDao.save(vo);
	}
	public int insert( CpBusinessIntroduction vo ){
		return cpBusinessIntroductionDao.insert(vo);
	}
	public int remove( CpBusinessIntroduction vo ){
		return cpBusinessIntroductionDao.remove(vo);
	}
	public int update( CpBusinessIntroduction vo ){
		return cpBusinessIntroductionDao.update(vo);
	}
	public PageModel findPage(CpBusinessIntroduction vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = cpBusinessIntroductionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpBusinessIntroduction> datas = cpBusinessIntroductionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpBusinessIntroduction> findAll(CpBusinessIntroduction vo){
		
		List<CpBusinessIntroduction> datas = cpBusinessIntroductionDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 11:47:25
	@Override
	public CpBusinessIntroduction findByPK(CpBusinessIntroduction cpBusinessIntroduction) {
		return cpBusinessIntroductionDao.findByPK(cpBusinessIntroduction);
	}
	
	
}
