package com.xfs.mall.order.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.order.dao.SpsMallOrderAreaDao;
import com.xfs.mall.order.model.SpsMallOrderArea;
import com.xfs.mall.order.service.SpsMallOrderAreaService;

@Service
public class SpsMallOrderAreaServiceImpl implements SpsMallOrderAreaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallOrderAreaServiceImpl.class);
	
	@Autowired
	private SpsMallOrderAreaDao spsMallOrderAreaDao;
	
	public int save(ContextInfo cti, SpsMallOrderArea vo ){
		return spsMallOrderAreaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallOrderArea vo ){
		return spsMallOrderAreaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallOrderArea vo ){
		return spsMallOrderAreaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallOrderArea vo ){
		return spsMallOrderAreaDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallOrderArea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderAreaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallOrderArea> datas = spsMallOrderAreaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallOrderArea> findAll(SpsMallOrderArea vo){
		
		List<SpsMallOrderArea> datas = spsMallOrderAreaDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 19:26:49
	
	
	
}
