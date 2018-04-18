package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.dao.BsMallRecomproductDao;
import com.xfs.mall.product.model.BsMallRecomproduct;

@Service
public class BsMallRecomproductServiceImpl implements BsMallRecomproductService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallRecomproductServiceImpl.class);
	
	@Autowired
	private BsMallRecomproductDao bsMallRecomproductDao;
	
	public int save(ContextInfo cti, BsMallRecomproduct vo ){
		return bsMallRecomproductDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsMallRecomproduct vo ){
		return bsMallRecomproductDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallRecomproduct vo ){
		return bsMallRecomproductDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallRecomproduct vo ){
		return bsMallRecomproductDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallRecomproduct vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallRecomproductDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallRecomproduct> datas = bsMallRecomproductDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallRecomproduct> findAll(BsMallRecomproduct vo){
		
		List<BsMallRecomproduct> datas = bsMallRecomproductDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:04
	
	public PageModel findProductPage(PageInfo pi, BsMallRecomproduct vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallRecomproductDao.countFreeFind(vo);
		pm.setTotal(total);
		List<Map> datas = bsMallRecomproductDao.findProductPage(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
	public Map findRecomByPK(Integer id){
		return bsMallRecomproductDao.findRecomByPK(id);
	}

	/**
	 * 商城首页推荐的商品列表
	 *
	 * @return
	 */
	public List<Map<String, Object>> findRecomProducts() {
//		String key = "bs_mall_recomproduct";
//		Object o = RedisUtil.get(key);
//		if (o != null && o instanceof List) {
//			return (List)o;
//		}
		List<Map<String, Object>> list = bsMallRecomproductDao.findRecomProducts();
//		RedisUtil.set(key, list, 0);
		return list;
	}
	
}
