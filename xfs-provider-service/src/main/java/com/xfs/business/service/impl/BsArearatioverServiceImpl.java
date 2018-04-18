package com.xfs.business.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.BsArearatioverDao;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.service.BsArearatioverService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsArearatioverServiceImpl implements BsArearatioverService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsArearatioverServiceImpl.class);
	
	@Autowired
	private BsArearatioverDao bsArearatioverDao;
	
	public int save(ContextInfo cti, BsArearatiover vo ){
		return bsArearatioverDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsArearatiover vo ){
		return bsArearatioverDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsArearatiover vo ){
		return bsArearatioverDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsArearatiover vo ){
		return bsArearatioverDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsArearatiover vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsArearatioverDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsArearatiover> datas = bsArearatioverDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsArearatiover> findAll(BsArearatiover vo){
		
		List<BsArearatiover> datas = bsArearatioverDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:09
	
	
	@Override
	public List<BsArearatiover> findVersions(BsArearatiover arearatiover) {
		List<BsArearatiover> datas = bsArearatioverDao.findVersions(arearatiover);
		return datas;
	}
	
}
