package com.xfs.activity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BsMallBannerDao;
import com.xfs.activity.model.BsMallBanner;
import com.xfs.activity.service.BsMallBannerService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsMallBannerServiceImpl implements BsMallBannerService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallBannerServiceImpl.class);
	
	@Autowired
	private BsMallBannerDao bsMallBannerDao;
	
	public int save(ContextInfo cti, BsMallBanner vo ){
		return bsMallBannerDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsMallBanner vo ){
		return bsMallBannerDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallBanner vo ){
		return bsMallBannerDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallBanner vo ){
		return bsMallBannerDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallBanner vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallBannerDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallBanner> datas = bsMallBannerDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallBanner> findAll(BsMallBanner vo){
		
		List<BsMallBanner> datas = bsMallBannerDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:07:57
	
	public BsMallBanner findByPK(BsMallBanner vo){
		return bsMallBannerDao.findByPK(vo);
	}
	
	/**
	 * 商城首页banner列表
	 *
	 * @return
	 */
	public List<BsMallBanner> findMallBanners() {
//		String key = "bs_mall_banner";
//		Object o = RedisUtil.get(key);
//		if (o != null && o instanceof List) {
//			return (List)o;
//		}
		List<BsMallBanner> list = bsMallBannerDao.findMallBanners();
//		RedisUtil.set(key, list, 0);
		return list;
	}
}
