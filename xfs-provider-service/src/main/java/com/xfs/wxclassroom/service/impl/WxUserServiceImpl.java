package com.xfs.wxclassroom.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dao.WxUserDao;
import com.xfs.wxclassroom.dto.WxUser;
import com.xfs.wxclassroom.service.WxUserService;

@Service
public class WxUserServiceImpl implements WxUserService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(WxUserServiceImpl.class);
	
	@Autowired
	private WxUserDao wxUserDao;
	
	public int save(ContextInfo cti, WxUser vo ){
		return wxUserDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  WxUser vo ){
		return wxUserDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  WxUser vo ){
		return wxUserDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  WxUser vo ){
		return wxUserDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, WxUser vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = wxUserDao.countFreeFind(vo);
		pm.setTotal(total);
		List<WxUser> datas = wxUserDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<WxUser> findAll(WxUser vo){
		
		List<WxUser> datas = wxUserDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/01 18:03:02

	public WxUser findByPK(WxUser vo){
		return wxUserDao.findByPK(vo);
	}
	
}
