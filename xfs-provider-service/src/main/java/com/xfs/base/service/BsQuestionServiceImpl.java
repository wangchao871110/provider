package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsQuestionDao;
import com.xfs.base.model.BsQuestion;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsQuestionServiceImpl implements BsQuestionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsQuestionServiceImpl.class);
	
	@Autowired
	private BsQuestionDao bsQuestionDao;
	
	public int save(ContextInfo cti, BsQuestion vo ){
		return bsQuestionDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsQuestion vo ){
		return bsQuestionDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsQuestion vo ){
		return bsQuestionDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsQuestion vo ){
		return bsQuestionDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsQuestion vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsQuestionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsQuestion> datas = bsQuestionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsQuestion> findAll(BsQuestion vo){
		
		List<BsQuestion> datas = bsQuestionDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public BsQuestion findByPK(BsQuestion vo) {
		return bsQuestionDao.findByPK(vo);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27
	
	
}
