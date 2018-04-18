package com.xfs.mall.product.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.dao.SpsMallProductConsultDao;
import com.xfs.mall.product.model.SpsMallProductConsult;

@Service
public class SpsMallProductConsultServiceImpl implements SpsMallProductConsultService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallProductConsultServiceImpl.class);
	
	@Autowired
	private SpsMallProductConsultDao spsMallProductConsultDao;
	
	public int save(ContextInfo cti, SpsMallProductConsult vo ){
		return spsMallProductConsultDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallProductConsult vo ){
		return spsMallProductConsultDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallProductConsult vo ){
		return spsMallProductConsultDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallProductConsult vo ){
		return spsMallProductConsultDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallProductConsult vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductConsultDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallProductConsult> datas = spsMallProductConsultDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallProductConsult> findAll(SpsMallProductConsult vo){
		
		List<SpsMallProductConsult> datas = spsMallProductConsultDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/17 11:45:05
	
	
	
}
