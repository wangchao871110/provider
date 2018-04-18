package com.xfs.mall.info.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.dao.SpsMallServicecityDao;
import com.xfs.mall.info.model.SpsMallServicecity;
import com.xfs.mall.info.service.SpsMallServicecityService;

@Service
public class SpsMallServicecityServiceImpl implements SpsMallServicecityService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallServicecityServiceImpl.class);
	
	@Autowired
	private SpsMallServicecityDao spsMallServicecityDao;
	
	public int save(ContextInfo cti, SpsMallServicecity vo ){
		return spsMallServicecityDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallServicecity vo ){
		return spsMallServicecityDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallServicecity vo ){
		return spsMallServicecityDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallServicecity vo ){
		return spsMallServicecityDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallServicecity vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallServicecityDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallServicecity> datas = spsMallServicecityDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallServicecity> findAll(SpsMallServicecity vo){
		
		List<SpsMallServicecity> datas = spsMallServicecityDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 16:11:04
	
	
	
}
