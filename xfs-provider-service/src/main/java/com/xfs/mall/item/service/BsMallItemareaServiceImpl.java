package com.xfs.mall.item.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.item.dao.BsMallItemareaDao;
import com.xfs.mall.item.model.BsMallItemarea;

@Service
public class BsMallItemareaServiceImpl implements BsMallItemareaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallItemareaServiceImpl.class);
	
	@Autowired
	private BsMallItemareaDao bsMallItemareaDao;
	
	public int save(ContextInfo cti, BsMallItemarea vo ){
		return bsMallItemareaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsMallItemarea vo ){
		return bsMallItemareaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallItemarea vo ){
		return bsMallItemareaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallItemarea vo ){
		return bsMallItemareaDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallItemarea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallItemareaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallItemarea> datas = bsMallItemareaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallItemarea> findAll(BsMallItemarea vo){
		
		List<BsMallItemarea> datas = bsMallItemareaDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:11



	public List<BsMallItemarea> findByItemId(int itemId){
		return bsMallItemareaDao.findByItemId(itemId);
	}

	public List<Map> FreeFindItems(String chooseCityId, String categoryId, String searchWord){
		List<Map> datas = bsMallItemareaDao.FreeFindItems(chooseCityId,categoryId,searchWord);
		return  datas;
	}
	
}
