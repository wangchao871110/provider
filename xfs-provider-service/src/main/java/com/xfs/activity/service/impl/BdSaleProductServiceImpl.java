package com.xfs.activity.service.impl;
import java.util.List;
import java.util.Map;

import com.xfs.activity.dao.BdSaleProductDao;
import com.xfs.activity.model.BdSaleProduct;
import com.xfs.activity.service.BdSaleProductService;
import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class BdSaleProductServiceImpl implements BdSaleProductService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdSaleProductServiceImpl.class);
	
	@Autowired
	private BdSaleProductDao bdSaleProductDao;
	
	public int save(ContextInfo cti, BdSaleProduct vo ){
		return bdSaleProductDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdSaleProduct vo ){
		return bdSaleProductDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdSaleProduct vo ){
		return bdSaleProductDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdSaleProduct vo ){
		return bdSaleProductDao.update(cti, vo);
	}
	public PageModel findPage(BdSaleProduct vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = bdSaleProductDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdSaleProduct> datas = bdSaleProductDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdSaleProduct> findAll(BdSaleProduct vo){
		
		List<BdSaleProduct> datas = bdSaleProductDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 15:07:22

	public List<Map<String, Object>> querySaleProduct(BdSaleProduct vo) {
		return bdSaleProductDao.querySaleProduct(vo);
	}
	
}
