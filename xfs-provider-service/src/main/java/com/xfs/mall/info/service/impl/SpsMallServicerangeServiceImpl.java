package com.xfs.mall.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.dao.SpsMallServicerangeDao;
import com.xfs.mall.info.model.SpsMallServicerange;
import com.xfs.mall.info.service.SpsMallServicerangeService;

@Service
public class SpsMallServicerangeServiceImpl implements SpsMallServicerangeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallServicerangeServiceImpl.class);
	
	@Autowired
	private SpsMallServicerangeDao spsMallServicerangeDao;
	
	public int save(ContextInfo cti, SpsMallServicerange vo ){
		return spsMallServicerangeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallServicerange vo ){
		return spsMallServicerangeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallServicerange vo ){
		return spsMallServicerangeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallServicerange vo ){
		return spsMallServicerangeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallServicerange vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallServicerangeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallServicerange> datas = spsMallServicerangeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallServicerange> findAll(SpsMallServicerange vo){
		
		List<SpsMallServicerange> datas = spsMallServicerangeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/23 11:04:37

	/**
	 * 查询服务范围
	 *
	 * @param spId
	 * @return
	 */
	public List<Map<String, Object>> findSpsMallCategories(Integer spId) {
		SpsMallServicerange vo = new SpsMallServicerange();
		vo.setSpId(spId);
		return spsMallServicerangeDao.findSpsMallCategories(vo);
	}

	
}
