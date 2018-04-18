package com.xfs.mall.customer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.customer.dao.CsMallCustomerAssessDao;
import com.xfs.mall.customer.model.CsMallCustomerAssess;
import com.xfs.mall.dto.CsMallCustomerAssessDTO;


@Service
public class CsMallCustomerAssessServiceImpl implements CsMallCustomerAssessService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsMallCustomerAssessServiceImpl.class);
	
	@Autowired
	private CsMallCustomerAssessDao csMallCustomerAssessDao;
	
	public int save(ContextInfo cti, CsMallCustomerAssess vo ){
		return csMallCustomerAssessDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsMallCustomerAssess vo ){
		return csMallCustomerAssessDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsMallCustomerAssess vo ){
		return csMallCustomerAssessDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsMallCustomerAssess vo ){
		return csMallCustomerAssessDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsMallCustomerAssess vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerAssessDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsMallCustomerAssess> datas = csMallCustomerAssessDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsMallCustomerAssess> findAll(CsMallCustomerAssess vo){
		
		List<CsMallCustomerAssess> datas = csMallCustomerAssessDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:55:57
	
	@Override
	public PageModel findPage(PageInfo pi, CsMallCustomerAssessDTO dto) {
		// TODO Auto-generated method stub
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csMallCustomerAssessDao.countFreeFind(dto);
		pm.setTotal(total);
		List<CsMallCustomerAssessDTO> datas = csMallCustomerAssessDao.freeFind(dto, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
}
