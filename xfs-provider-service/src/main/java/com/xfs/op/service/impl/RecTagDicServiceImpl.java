package com.xfs.op.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

import com.xfs.op.service.RecTagDicService;
import com.xfs.op.dao.RecTagDicDao;
import com.xfs.op.model.RecTagDic;

@Service
public class RecTagDicServiceImpl implements RecTagDicService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(RecTagDicServiceImpl.class);
	
	@Autowired
	private RecTagDicDao recTagDicDao;
	
	public int save(ContextInfo cti, RecTagDic vo ){
		return recTagDicDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, RecTagDic vo ){
		return recTagDicDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, RecTagDic vo ){
		return recTagDicDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, RecTagDic vo ){
		return recTagDicDao.update(cti,vo);
	}
	public PageModel findPage(RecTagDic vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = recTagDicDao.countFreeFind(vo);
		pm.setTotal(total);
		List<RecTagDic> datas = recTagDicDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<RecTagDic> findAll(RecTagDic vo){
		
		List<RecTagDic> datas = recTagDicDao.freeFind(vo);
		return datas;
		
	}

	@Override
	public List<RecTagDic> findTagByTypes(Map<String, Object> parem) {
		return recTagDicDao.findTagByTypes(parem);
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03

	@Override
	public List<RecTagDic> findUserTagById(Map<String,Object> parem){
		return recTagDicDao.findUserTagById(parem);
	}
	
}
