package com.xfs.cp.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpNoteDao;
import com.xfs.cp.model.CpNote;
import com.xfs.cp.service.CpNoteService;

@Service
public class CpNoteServiceImpl implements CpNoteService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpNoteServiceImpl.class);
	
	@Autowired
	private CpNoteDao cpNoteDao;
	
	public int save(ContextInfo cti, CpNote vo ){
		return cpNoteDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpNote vo ){
		return cpNoteDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpNote vo ){
		return cpNoteDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpNote vo ){
		return cpNoteDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpNote vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpNoteDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpNote> datas = cpNoteDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpNote> findAll(CpNote vo){
		
		List<CpNote> datas = cpNoteDao.freeFind(vo);
		return datas;
		
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	
	/**
	 * 保存纠错和留言
	 */
	@Override
	public Result saveNote(ContextInfo user, CpNote cpNote, Result result) {
		if (user != null) {
			cpNote.setCreateBy(user.getUserId());
			cpNote.setSpId(user.getOrgId());
		}
		cpNote.setCreateDt(new Date());
		if(cpNoteDao.insert(user,cpNote) > 0){
			result.setSuccess(true);
			result.setMsg("操作成功！");
		}else{
			result.setMsg("操作失败！");
		}
		return result;
	}
	@Override
	public PageModel queryNotePage(PageInfo pi,CpNote cpNote) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = cpNoteDao.queryNotePageCount(cpNote);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cpNoteDao.queryNotePage(cpNote, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
}
