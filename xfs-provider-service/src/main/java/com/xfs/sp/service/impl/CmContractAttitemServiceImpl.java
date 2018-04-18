package com.xfs.sp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.CmContractAttitemDao;
import com.xfs.sp.model.CmContractAttitem;
import com.xfs.sp.service.CmContractAttitemService;

@Service
public class CmContractAttitemServiceImpl implements CmContractAttitemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmContractAttitemServiceImpl.class);
	
	@Autowired
	private CmContractAttitemDao cmContractAttitemDao;
	
	public int save(ContextInfo cti, CmContractAttitem vo ){
		return cmContractAttitemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CmContractAttitem vo ){
		return cmContractAttitemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CmContractAttitem vo ){
		return cmContractAttitemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CmContractAttitem vo ){
		return cmContractAttitemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CmContractAttitem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmContractAttitemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmContractAttitem> datas = cmContractAttitemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmContractAttitem> findAll(CmContractAttitem vo){
		
		List<CmContractAttitem> datas = cmContractAttitemDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:25
	
	@Override
	public List<Map<String, Object>> findAllByContractAttIdAndAreaId(Integer contractAttId, Integer areaId) {
		return cmContractAttitemDao.findAllByContractAttIdAndAreaId(contractAttId,areaId);
	}
	@Override
	public List<Map<String, Object>> findContractAttItem(Integer productId, Integer areaId, String itemId) {
		return cmContractAttitemDao.findContractAttItem(productId,areaId,itemId);
	}
	
}
