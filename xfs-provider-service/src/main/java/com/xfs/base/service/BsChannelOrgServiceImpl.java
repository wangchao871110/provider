package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsChannelOrgDao;
import com.xfs.base.model.BsChannelOrg;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsChannelOrgServiceImpl implements BsChannelOrgService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsChannelOrgServiceImpl.class);
	
	@Autowired
	private BsChannelOrgDao bsChannelOrgDao;
	
	public int save(ContextInfo cti, BsChannelOrg vo ){
		return bsChannelOrgDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsChannelOrg vo ){
		return bsChannelOrgDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsChannelOrg vo ){
		return bsChannelOrgDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsChannelOrg vo ){
		return bsChannelOrgDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsChannelOrg vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsChannelOrgDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsChannelOrg> datas = bsChannelOrgDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsChannelOrg> findAll(BsChannelOrg vo){
		
		List<BsChannelOrg> datas = bsChannelOrgDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/29 10:55:28
	
	
	
}
