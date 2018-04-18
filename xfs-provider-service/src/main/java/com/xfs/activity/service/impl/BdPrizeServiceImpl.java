package com.xfs.activity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdPrizeDao;
import com.xfs.activity.model.BdPrize;
import com.xfs.activity.service.BdPrizeService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BdPrizeServiceImpl implements BdPrizeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdPrizeServiceImpl.class);
	
	@Autowired
	private BdPrizeDao bdPrizeDao;
	
	public int save(ContextInfo cti, BdPrize vo ){
		return bdPrizeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdPrize vo ){
		return bdPrizeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdPrize vo ){
		return bdPrizeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdPrize vo ){
		return bdPrizeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdPrize vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdPrizeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdPrize> datas = bdPrizeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdPrize> findAll(BdPrize vo){
		
		List<BdPrize> datas = bdPrizeDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public BdPrize findByPK(Integer id) {
		return bdPrizeDao.findByPK(id);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/17 10:59:57
	
	
	
}
