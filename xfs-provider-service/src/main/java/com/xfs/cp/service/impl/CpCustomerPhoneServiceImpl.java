package com.xfs.cp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpCustomerPhoneDao;
import com.xfs.cp.model.CpCustomerPhone;
import com.xfs.cp.service.CpCustomerPhoneService;

@Service
public class CpCustomerPhoneServiceImpl implements CpCustomerPhoneService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpCustomerPhoneServiceImpl.class);
	
	@Autowired
	private CpCustomerPhoneDao cpCustomerPhoneDao;
	
	public int save(ContextInfo cti, CpCustomerPhone vo ){
		return cpCustomerPhoneDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpCustomerPhone vo ){
		return cpCustomerPhoneDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpCustomerPhone vo ){
		return cpCustomerPhoneDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpCustomerPhone vo ){
		return cpCustomerPhoneDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpCustomerPhone vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpCustomerPhoneDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpCustomerPhone> datas = cpCustomerPhoneDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpCustomerPhone> findAll(CpCustomerPhone vo){
		
		List<CpCustomerPhone> datas = cpCustomerPhoneDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22
	
	
	
}
