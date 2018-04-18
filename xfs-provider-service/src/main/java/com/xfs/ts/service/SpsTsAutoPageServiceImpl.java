package com.xfs.ts.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.dao.SpsTsAutoPageDao;
import com.xfs.ts.model.SpsTsAutoPage;

@Service
public class SpsTsAutoPageServiceImpl implements SpsTsAutoPageService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTsAutoPageServiceImpl.class);
	
	@Autowired
	private SpsTsAutoPageDao spsTsAutoPageDao;
	
	public int save(ContextInfo cti, SpsTsAutoPage vo ){
		return spsTsAutoPageDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTsAutoPage vo ){
		return spsTsAutoPageDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTsAutoPage vo ){
		return spsTsAutoPageDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTsAutoPage vo ){
		return spsTsAutoPageDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTsAutoPage vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTsAutoPageDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTsAutoPage> datas = spsTsAutoPageDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTsAutoPage> findAll(SpsTsAutoPage vo){
		
		List<SpsTsAutoPage> datas = spsTsAutoPageDao.freeFind(vo);
		return datas;
		
	}
	
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:41
	
	@Override
	public SpsTsAutoPage findPK(SpsTsAutoPage vo) {
		return spsTsAutoPageDao.findByPK(vo);
	}

	@Override
	public SpsTsAutoPage findSpsTsAutoPage(SpsTsAutoPage vo){
		return spsTsAutoPageDao.findSpsTsAutoPage(vo);
	}
	
}
