package com.xfs.mall.order.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.order.dao.SpsMallOrderItemDao;
import com.xfs.mall.order.model.SpsMallOrderItem;
import com.xfs.mall.order.service.SpsMallOrderItemService;

@Service
public class SpsMallOrderItemServiceImpl implements SpsMallOrderItemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallOrderItemServiceImpl.class);
	
	@Autowired
	private SpsMallOrderItemDao spsMallOrderItemDao;
	
	public int save(ContextInfo cti, SpsMallOrderItem vo ){
		return spsMallOrderItemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallOrderItem vo ){
		return spsMallOrderItemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallOrderItem vo ){
		return spsMallOrderItemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallOrderItem vo ){
		return spsMallOrderItemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallOrderItem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallOrderItem> datas = spsMallOrderItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallOrderItem> findAll(SpsMallOrderItem vo){
		
		List<SpsMallOrderItem> datas = spsMallOrderItemDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/12 13:10:04
	
	/**
	 * 根据 主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  下午3:24:39
	 */
	public SpsMallOrderItem findByPK(SpsMallOrderItem obj) {
		return spsMallOrderItemDao.findByPK(obj);
	}
	
}
