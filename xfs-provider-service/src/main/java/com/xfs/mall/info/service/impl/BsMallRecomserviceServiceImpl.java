package com.xfs.mall.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.dao.BsMallRecomserviceDao;
import com.xfs.mall.info.model.BsMallRecomservice;
import com.xfs.mall.info.service.BsMallRecomserviceService;

@Service
public class BsMallRecomserviceServiceImpl implements BsMallRecomserviceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallRecomserviceServiceImpl.class);
	
	@Autowired
	private BsMallRecomserviceDao bsMallRecomserviceDao;
	
	public int save(ContextInfo cti, BsMallRecomservice vo ){
		return bsMallRecomserviceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsMallRecomservice vo ){
		return bsMallRecomserviceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallRecomservice vo ){
		return bsMallRecomserviceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallRecomservice vo ){
		return bsMallRecomserviceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallRecomservice vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallRecomserviceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallRecomservice> datas = bsMallRecomserviceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallRecomservice> findAll(BsMallRecomservice vo){
		
		List<BsMallRecomservice> datas = bsMallRecomserviceDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:12
	
	public PageModel findServicePage(PageInfo pi, BsMallRecomservice vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallRecomserviceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<Map> datas = bsMallRecomserviceDao.findServicePage(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	public Map findRecomByPK(Integer id){
		return bsMallRecomserviceDao.findRecomByPK(id);
	}
	/**
	 * 商城首页推荐的服务商列表
	 *
	 * @return
	 */
	public List<Map<String, Object>> findRecomServices() {
//		String key = "bs_mall_recomservice";
//		Object o = RedisUtil.get(key);
//		if (o != null && o instanceof List) {
//			return (List)o;
//		}
		List<Map<String, Object>> list = bsMallRecomserviceDao.findRecomServices();
//		RedisUtil.set(key, list, 0);
		return list;
	}
}
