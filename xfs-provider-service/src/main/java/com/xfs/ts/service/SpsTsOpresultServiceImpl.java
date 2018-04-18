package com.xfs.ts.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.dao.SpsTsOpresultDao;
import com.xfs.ts.model.SpsTsOpresult;

@Service
public class SpsTsOpresultServiceImpl implements SpsTsOpresultService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTsOpresultServiceImpl.class);
	
	@Autowired
	private SpsTsOpresultDao spsTsOpresultDao;
	
	public int save(ContextInfo cti, SpsTsOpresult vo ){
		return spsTsOpresultDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTsOpresult vo ){
		return spsTsOpresultDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTsOpresult vo ){
		return spsTsOpresultDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTsOpresult vo ){
		return spsTsOpresultDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTsOpresult vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTsOpresultDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTsOpresult> datas = spsTsOpresultDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTsOpresult> findAll(SpsTsOpresult vo){
		
		List<SpsTsOpresult> datas = spsTsOpresultDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/04 16:10:19

	public List<Map<String,Object>> findBySchemeId(Integer schemeId){
		List<Map<String,Object>> datas = spsTsOpresultDao.findBySchemeId(schemeId);
		return datas;
	}
	
}
