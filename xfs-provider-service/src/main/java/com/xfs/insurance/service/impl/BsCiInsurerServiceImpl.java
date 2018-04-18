package com.xfs.insurance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiInsurerDao;
import com.xfs.insurance.model.BsCiInsurer;
import com.xfs.insurance.service.BsCiInsurerService;

@Service
public class BsCiInsurerServiceImpl implements BsCiInsurerService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsCiInsurerServiceImpl.class);
	
	@Autowired
	private BsCiInsurerDao bsCiInsurerDao;
	
	public int save(ContextInfo cti, BsCiInsurer vo ){
		return bsCiInsurerDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsCiInsurer vo ){
		return bsCiInsurerDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsCiInsurer vo ){
		return bsCiInsurerDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsCiInsurer vo ){
		return bsCiInsurerDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsCiInsurer vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsCiInsurerDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsCiInsurer> datas = bsCiInsurerDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsCiInsurer> findAll(BsCiInsurer vo){
		
		List<BsCiInsurer> datas = bsCiInsurerDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 14:37:15
	
	
	
}
