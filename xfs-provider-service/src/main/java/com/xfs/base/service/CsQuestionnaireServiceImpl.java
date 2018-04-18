package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.CsQuestionnaireDao;
import com.xfs.base.model.CsQuestionnaire;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class CsQuestionnaireServiceImpl implements CsQuestionnaireService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsQuestionnaireServiceImpl.class);
	
	@Autowired
	private CsQuestionnaireDao csQuestionnaireDao;
	
	public int save(ContextInfo cti, CsQuestionnaire vo ){
		return csQuestionnaireDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsQuestionnaire vo ){
		return csQuestionnaireDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsQuestionnaire vo ){
		return csQuestionnaireDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsQuestionnaire vo ){
		return csQuestionnaireDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsQuestionnaire vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csQuestionnaireDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsQuestionnaire> datas = csQuestionnaireDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsQuestionnaire> findAll(CsQuestionnaire vo){
		
		List<CsQuestionnaire> datas = csQuestionnaireDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/28 10:52:51
	
	
	
}
