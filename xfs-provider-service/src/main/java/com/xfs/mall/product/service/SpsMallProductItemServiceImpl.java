package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.dao.SpsMallProductItemDao;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.mall.product.model.SpsMallProductItem;

@Service
public class SpsMallProductItemServiceImpl implements SpsMallProductItemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallProductItemServiceImpl.class);
	
	@Autowired
	private SpsMallProductItemDao spsMallProductItemDao;
	
	public int save(ContextInfo cti, SpsMallProductItem vo ){
		return spsMallProductItemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallProductItem vo ){
		return spsMallProductItemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallProductItem vo ){
		return spsMallProductItemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallProductItem vo ){
		return spsMallProductItemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallProductItem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallProductItem> datas = spsMallProductItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallProductItem> findAll(SpsMallProductItem vo){
		
		List<SpsMallProductItem> datas = spsMallProductItemDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 15:11:16
	
	/**
	 * 根据产品ID
	 * 
	 * @return
	 */

	public List<Map<String, Object>> findAllByProductId(Integer productId) {

		List<Map<String, Object>> datas = spsMallProductItemDao.findAllByProductId(productId);
		return datas;
	}

	@Override
	public List<Map<String, Object>> findAllByProductIdAndAreaId(Integer productId, Integer areaId) {
		return spsMallProductItemDao.findAllByProductIdAndAreaId(productId,areaId);
	}
	
	/**
	 * 根据产品id查询产品的服务项
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findProduectItems(SpsMallProduct vo) {
		return spsMallProductItemDao.findProduectItems(vo);
	}
	/**
	 * 根据产品ids查询产品的服务项
	 *
	 * @param productIds
	 * @return
	 */
	public List<SpsMallProductItem> findProduectItemsByProductIds(String productIds){
		return spsMallProductItemDao.findProduectItemsByProductIds(productIds);
	}
	
}
