package com.xfs.wxclassroom.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dao.WxClassroomDao;
import com.xfs.wxclassroom.dto.WxClassroom;
import com.xfs.wxclassroom.service.WxClassroomService;

@Service
public class WxClassroomServiceImpl implements WxClassroomService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(WxClassroomServiceImpl.class);
	
	@Autowired
	private WxClassroomDao wxClassroomDao;
	
	public int save(ContextInfo cti, WxClassroom vo ){
		return wxClassroomDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  WxClassroom vo ){
		return wxClassroomDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  WxClassroom vo ){
		return wxClassroomDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  WxClassroom vo ){
		return wxClassroomDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, WxClassroom vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = wxClassroomDao.countFreeFind(vo);
		pm.setTotal(total);
		List<WxClassroom> datas = wxClassroomDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<WxClassroom> findAll(WxClassroom vo){
		
		List<WxClassroom> datas = wxClassroomDao.freeFind(vo);
		return datas;
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:43

	public List<WxClassroom> FreeFind(WxClassroom vo){
		List<WxClassroom> datas = wxClassroomDao.FreeFind(vo);
		return datas;

	}

	public List<Map<String, Object>> FreeFindAll(WxClassroom vo){
		List<Map<String, Object>> datas = wxClassroomDao.FreeFindAll(vo);
		return datas;

	}

	public WxClassroom findByClassPK(int i){

		WxClassroom datas = wxClassroomDao.findByClassPK(i);
		return datas;
	}

	public PageModel findByTimePage(PageInfo pi, WxClassroom vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = wxClassroomDao.countfindByTime(vo);
		pm.setTotal(total);
		List<WxClassroom> datas = wxClassroomDao.findByTime(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

}
