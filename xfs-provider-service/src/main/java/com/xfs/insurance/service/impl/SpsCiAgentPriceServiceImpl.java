package com.xfs.insurance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.SpsCiAgentPriceDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.SpsCiAgentPrice;
import com.xfs.insurance.service.SpsCiAgentPriceService;

@Service
public class SpsCiAgentPriceServiceImpl implements SpsCiAgentPriceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsCiAgentPriceServiceImpl.class);
	
	@Autowired
	private SpsCiAgentPriceDao spsCiAgentPriceDao;
	
	public int save(ContextInfo cti, SpsCiAgentPrice vo ){
		return spsCiAgentPriceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsCiAgentPrice vo ){
		return spsCiAgentPriceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsCiAgentPrice vo ){
		return spsCiAgentPriceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsCiAgentPrice vo ){
		return spsCiAgentPriceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsCiAgentPrice vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsCiAgentPriceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsCiAgentPrice> datas = spsCiAgentPriceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsCiAgentPrice> findAll(SpsCiAgentPrice vo){
		
		List<SpsCiAgentPrice> datas = spsCiAgentPriceDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/07 09:14:06


	/**
	 * 更具商品编码和服务项编码查询商保承保价格信息
	 *
	 * @param product 商品信息
	 * @return 方案类表
	 */
	@Override
	public List<CiScheme> findCiItemPrice(CiProduct product) {
		return spsCiAgentPriceDao.findCiItemPrice(product);
	}
}
