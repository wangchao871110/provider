package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.dao.SpsMallProductareaDao;
import com.xfs.mall.product.model.SpsMallProductarea;

@Service
public class SpsMallProductareaServiceImpl implements SpsMallProductareaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallProductareaServiceImpl.class);
	
	@Autowired
	private SpsMallProductareaDao spsMallProductareaDao;
	
	public int save(ContextInfo cti, SpsMallProductarea vo ){
		return spsMallProductareaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallProductarea vo ){
		return spsMallProductareaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallProductarea vo ){
		return spsMallProductareaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallProductarea vo ){
		return spsMallProductareaDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallProductarea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductareaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallProductarea> datas = spsMallProductareaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallProductarea> findAll(SpsMallProductarea vo){
		
		List<SpsMallProductarea> datas = spsMallProductareaDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:05

	/**
	 * 产品的服务区域
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, String>> findProductAreas(SpsMallProductarea vo) {
		return spsMallProductareaDao.findProductAreas(vo);
	}
	
	public List<Map> freeFindGROUP(String productId,String spId){
		List<Map> datas = spsMallProductareaDao.freeFindGROUP(productId,spId);
		return datas;
	}

	public List<Map> freeFindCOOPAndINNER(String productId,String spId){
		List<Map> datas = spsMallProductareaDao.freeFindCOOPAndINNER(productId,spId);
		return datas;
	}
}
