package com.xfs.wxclassroom.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dao.WxClassroomShareDao;
import com.xfs.wxclassroom.dto.WxClassroomShare;
import com.xfs.wxclassroom.service.WxClassroomShareService;

@Service
public class WxClassroomShareServiceImpl implements WxClassroomShareService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(WxClassroomShareServiceImpl.class);
	
	@Autowired
	private WxClassroomShareDao wxClassroomShareDao;
	
	public int save(ContextInfo cti, WxClassroomShare vo ){
		return wxClassroomShareDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  WxClassroomShare vo ){
		return wxClassroomShareDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  WxClassroomShare vo ){
		return wxClassroomShareDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  WxClassroomShare vo ){
		return wxClassroomShareDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, WxClassroomShare vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = wxClassroomShareDao.countFreeFind(vo);
		pm.setTotal(total);
		List<WxClassroomShare> datas = wxClassroomShareDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<WxClassroomShare> findAll(WxClassroomShare vo){
		
		List<WxClassroomShare> datas = wxClassroomShareDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:42

	public List<WxClassroomShare> findByClassroomPK(int i){

		List<WxClassroomShare> datas = wxClassroomShareDao.findByClassroomPK(i);
		return datas;
	}
	public int updateTimes(ContextInfo cti, WxClassroomShare vo, int shareTimes,int shareId ){
		return wxClassroomShareDao.updateTimes(cti, vo,shareTimes,shareId );
	}
	public WxClassroomShare findByClassIdOpenid (WxClassroomShare vo){

		return wxClassroomShareDao.findByClassIdOpenid(vo);
	}


	//分页
	public PageModel _findPage(PageInfo pi, WxClassroomShare vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = wxClassroomShareDao._countFreeFind(vo);
		pm.setTotal(total);
		List<WxClassroomShare> datas = wxClassroomShareDao._freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}


}
