package com.xfs.ts.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.dao.SpsTsCitycodeDao;
import com.xfs.ts.model.SpsTsCitycode;

@Service
public class SpsTsCitycodeServiceImpl implements SpsTsCitycodeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTsCitycodeServiceImpl.class);
	
	@Autowired
	private SpsTsCitycodeDao spsTsCitycodeDao;
	
	public int save(ContextInfo cti, SpsTsCitycode vo ){
		return spsTsCitycodeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTsCitycode vo ){
		return spsTsCitycodeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTsCitycode vo ){
		return spsTsCitycodeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTsCitycode vo ){
		return spsTsCitycodeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTsCitycode vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTsCitycodeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTsCitycode> datas = spsTsCitycodeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTsCitycode> findAll(SpsTsCitycode vo){
		
		List<SpsTsCitycode> datas = spsTsCitycodeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/12 13:39:03


	@Override
	public SpsTsCitycode getSpsTsCitycodeByAreaId(int areaId) {
		SpsTsCitycode spsTsCitycode =spsTsCitycodeDao.getSpsTsCitycodeByAreaId(areaId);
		return spsTsCitycode;
	}

	public List<Map<String,Object>> getSpsTsCitycode(){
		return spsTsCitycodeDao.getSpsTsCitycode();
	}
}
