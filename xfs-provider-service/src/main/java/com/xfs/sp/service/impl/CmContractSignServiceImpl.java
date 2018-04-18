package com.xfs.sp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.CmContractSignDao;
import com.xfs.sp.model.CmContractSign;
import com.xfs.sp.service.CmContractSignService;

@Service
public class CmContractSignServiceImpl implements CmContractSignService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmContractSignServiceImpl.class);
	
	@Autowired
	private CmContractSignDao cmContractSignDao;
	
	public int save(ContextInfo cti, CmContractSign vo ){
		return cmContractSignDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CmContractSign vo ){
		return cmContractSignDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CmContractSign vo ){
		return cmContractSignDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CmContractSign vo ){
		return cmContractSignDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CmContractSign vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmContractSignDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmContractSign> datas = cmContractSignDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmContractSign> findAll(CmContractSign vo){
		
		List<CmContractSign> datas = cmContractSignDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 10:44:44
	
	
	
}
