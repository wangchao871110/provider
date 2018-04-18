package com.xfs.bs.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.MonitorExplain;
import com.xfs.bs.dao.MonitorExplainDao;
import com.xfs.bs.service.MonitorExplainService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


@Service
public class MonitorExplainServiceImpl implements MonitorExplainService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MonitorExplainServiceImpl.class);
	
	@Autowired
	private MonitorExplainDao monitorExplainDao;
	
	public int save(ContextInfo cti, MonitorExplain vo ){
		return monitorExplainDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  MonitorExplain vo ){
		return monitorExplainDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  MonitorExplain vo ){
		return monitorExplainDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  MonitorExplain vo ){
		return monitorExplainDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, MonitorExplain vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = monitorExplainDao.countFreeFind(vo);
		pm.setTotal(total);
		List<MonitorExplain> datas = monitorExplainDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<MonitorExplain> findAll(MonitorExplain vo){
		
		List<MonitorExplain> datas = monitorExplainDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/03/09 15:27:20
	
	
	
}
