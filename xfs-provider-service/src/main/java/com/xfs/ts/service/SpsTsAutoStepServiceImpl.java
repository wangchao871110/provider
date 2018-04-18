package com.xfs.ts.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.dao.SpsTsAutoStepDao;
import com.xfs.ts.model.SpsTsAutoStep;

@Service
public class SpsTsAutoStepServiceImpl implements SpsTsAutoStepService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTsAutoStepServiceImpl.class);
	
	@Autowired
	private SpsTsAutoStepDao spsTsAutoStepDao;
	
	public int save(ContextInfo cti, SpsTsAutoStep vo ){
		return spsTsAutoStepDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTsAutoStep vo ){
		return spsTsAutoStepDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTsAutoStep vo ){
		return spsTsAutoStepDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTsAutoStep vo ){
		return spsTsAutoStepDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTsAutoStep vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTsAutoStepDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTsAutoStep> datas = spsTsAutoStepDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTsAutoStep> findAll(SpsTsAutoStep vo){
		
		List<SpsTsAutoStep> datas = spsTsAutoStepDao.freeFind(vo);
		return datas;
		
	}
	
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:42

	@Override
	public SpsTsAutoStep findPK(SpsTsAutoStep vo) {
		return spsTsAutoStepDao.findByPK(vo);
	}
	@Override
	public int removeByPageId(ContextInfo cti, Integer pageId) {
		return spsTsAutoStepDao.removeByPageId(cti, pageId);
	}
	
}
