package com.xfs.sp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpsChangedocDao;
import com.xfs.sp.model.SpsChangedoc;
import com.xfs.sp.service.SpsChangedocService;

@Service
public class SpsChangedocServiceImpl implements SpsChangedocService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsChangedocServiceImpl.class);
	
	@Autowired
	private SpsChangedocDao spsChangedocDao;
	
	public int save(ContextInfo cti, SpsChangedoc vo ){
		return spsChangedocDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsChangedoc vo ){
		return spsChangedocDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsChangedoc vo ){
		return spsChangedocDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsChangedoc vo ){
		return spsChangedocDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsChangedoc vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsChangedocDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsChangedoc> datas = spsChangedocDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsChangedoc> findAll(SpsChangedoc vo){
		
		List<SpsChangedoc> datas = spsChangedocDao.freeFind(vo);
		return datas;
		
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 20:11:52
	
	@Override
	public List<Map<String, Object>> finspsChange(SpsChangedoc vo) {
		// TODO Auto-generated method stub
		return spsChangedocDao.Freespschange(vo);
	}
	@Override
	public SpsChangedoc findByPK(SpsChangedoc vo) {
		
		return spsChangedocDao.findByPK(vo);
	}
	
	
}
