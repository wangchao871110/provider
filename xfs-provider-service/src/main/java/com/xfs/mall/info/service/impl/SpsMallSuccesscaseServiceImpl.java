package com.xfs.mall.info.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.dao.SpsMallSuccesscaseDao;
import com.xfs.mall.info.model.SpsMallSuccesscase;
import com.xfs.mall.info.service.SpsMallSuccesscaseService;

@Service
public class SpsMallSuccesscaseServiceImpl implements SpsMallSuccesscaseService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallSuccesscaseServiceImpl.class);
	
	@Autowired
	private SpsMallSuccesscaseDao spsMallSuccesscaseDao;
	
	public int save(ContextInfo cti, SpsMallSuccesscase vo ){
		return spsMallSuccesscaseDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallSuccesscase vo ){
		return spsMallSuccesscaseDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallSuccesscase vo ){
		return spsMallSuccesscaseDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallSuccesscase vo ){
		return spsMallSuccesscaseDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallSuccesscase vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallSuccesscaseDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallSuccesscase> datas = spsMallSuccesscaseDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallSuccesscase> findAll(SpsMallSuccesscase vo){
		
		List<SpsMallSuccesscase> datas = spsMallSuccesscaseDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/27 11:28:03

	public int removeSuccessCaseBySpId(ContextInfo cti, int spId ){
		return spsMallSuccesscaseDao.removeSuccessCaseBySpId(cti, spId);
	}
	public int batchInsert(ContextInfo cti, List<SpsMallSuccesscase> list){
		return spsMallSuccesscaseDao.batchInsert(cti, list);
	}
}
