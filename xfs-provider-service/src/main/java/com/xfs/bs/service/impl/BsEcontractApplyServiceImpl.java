package com.xfs.bs.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsEcontractApplyDao;
import com.xfs.base.model.BsEcontractApply;
import com.xfs.bs.service.BsEcontractApplyService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


@Service
public class BsEcontractApplyServiceImpl implements BsEcontractApplyService{

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsEcontractApplyServiceImpl.class);
	
	@Autowired
	private BsEcontractApplyDao bsEcontractApplyDao;
	
	public int save(ContextInfo cti, BsEcontractApply vo ){
		return bsEcontractApplyDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsEcontractApply vo ){
		return bsEcontractApplyDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsEcontractApply vo ){
		return bsEcontractApplyDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsEcontractApply vo ){
		return bsEcontractApplyDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsEcontractApply vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsEcontractApplyDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsEcontractApply> datas = bsEcontractApplyDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsEcontractApply> findAll(BsEcontractApply vo){
		
		List<BsEcontractApply> datas = bsEcontractApplyDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 22:51:24
	
	
	
}
