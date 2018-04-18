package com.xfs.business.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.BsArearatioscopeDao;
import com.xfs.business.model.BsArearatioscope;
import com.xfs.business.service.BsArearatioscopeService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsArearatioscopeServiceImpl implements BsArearatioscopeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsArearatioscopeServiceImpl.class);
	
	@Autowired
	private BsArearatioscopeDao bsArearatioscopeDao;
	
	public int save(ContextInfo cti, BsArearatioscope vo ){
		return bsArearatioscopeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsArearatioscope vo ){
		return bsArearatioscopeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsArearatioscope vo ){
		return bsArearatioscopeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsArearatioscope vo ){
		return bsArearatioscopeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsArearatioscope vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsArearatioscopeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsArearatioscope> datas = bsArearatioscopeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsArearatioscope> findAll(BsArearatioscope vo){
		
		List<BsArearatioscope> datas = bsArearatioscopeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:25
	
	
	@Override
	public void changeScope(ContextInfo cti, BsArearatioscope arearatioscope) {
		List<BsArearatioscope> list = bsArearatioscopeDao.freeFind(arearatioscope);
		if (list != null && list.size() >0) {
			bsArearatioscopeDao.deleteScopeInUse(cti, arearatioscope);
		}else {
			bsArearatioscopeDao.save(cti, arearatioscope);
		}
	}
	
}
