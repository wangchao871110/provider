package com.xfs.cp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpAccountDao;
import com.xfs.cp.model.CpAccount;
import com.xfs.cp.service.CpAccountService;


@Service
public class CpAccountServiceImpl implements CpAccountService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpAccountServiceImpl.class);
	
	@Autowired
	private CpAccountDao cpAccountDao;
	
	public int save(ContextInfo cti, CpAccount vo ){
		return cpAccountDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpAccount vo ){
		return cpAccountDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpAccount vo ){
		return cpAccountDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpAccount vo ){
		return cpAccountDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpAccount vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpAccountDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpAccount> datas = cpAccountDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpAccount> findAll(CpAccount vo){
		
		List<CpAccount> datas = cpAccountDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public CpAccount getAccountById(CpAccount cpAccount) {
		return cpAccountDao.getAccountById(cpAccount);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/11 21:59:58
	
	
	
}
