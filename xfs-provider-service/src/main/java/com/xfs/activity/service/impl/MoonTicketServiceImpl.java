package com.xfs.activity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.MoonTicketDao;
import com.xfs.activity.model.MoonTicket;
import com.xfs.activity.service.MoonTicketService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class MoonTicketServiceImpl implements MoonTicketService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MoonTicketServiceImpl.class);
	
	@Autowired
	private MoonTicketDao moonTicketDao;
	
	public int save(ContextInfo cti, MoonTicket vo ){
		return moonTicketDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  MoonTicket vo ){
		return moonTicketDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  MoonTicket vo ){
		return moonTicketDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  MoonTicket vo ){
		return moonTicketDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, MoonTicket vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = moonTicketDao.countFreeFind(vo);
		pm.setTotal(total);
		List<MoonTicket> datas = moonTicketDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<MoonTicket> findAll(MoonTicket vo){
		
		List<MoonTicket> datas = moonTicketDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21
	
	
	
}
