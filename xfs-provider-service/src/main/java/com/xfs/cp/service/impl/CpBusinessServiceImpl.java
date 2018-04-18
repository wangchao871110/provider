package com.xfs.cp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpBusinessDao;
import com.xfs.cp.model.CpBusiness;
import com.xfs.cp.service.CpBusinessService;

@Service
public class CpBusinessServiceImpl implements CpBusinessService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpBusinessServiceImpl.class);
	
	@Autowired
	private CpBusinessDao cpBusinessDao;
	
	public int save(ContextInfo cti, CpBusiness vo ){
		return cpBusinessDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpBusiness vo ){
		return cpBusinessDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpBusiness vo ){
		return cpBusinessDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpBusiness vo ){
		return cpBusinessDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpBusiness vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpBusinessDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpBusiness> datas = cpBusinessDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpBusiness> findAll(CpBusiness vo){
		
		List<CpBusiness> datas = cpBusinessDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public int updateBySpId(ContextInfo sysUser,CpBusiness cpBusiness) {
		 return cpBusinessDao.updateBySpId(sysUser,cpBusiness);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	
	
	
}
