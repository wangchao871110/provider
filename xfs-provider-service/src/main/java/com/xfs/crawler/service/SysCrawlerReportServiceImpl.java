package com.xfs.crawler.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.crawler.dao.SysCrawlerReportDao;
import com.xfs.crawler.model.SysCrawlerReport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class SysCrawlerReportServiceImpl implements SysCrawlerReportService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysCrawlerReportServiceImpl.class);
	
	@Autowired
	private SysCrawlerReportDao sysCrawlerReportDao;
	
	public int save(ContextInfo cti , SysCrawlerReport vo ){
		return sysCrawlerReportDao.save(cti ,vo);
	}
	public int insert(ContextInfo cti , SysCrawlerReport vo ){
		return sysCrawlerReportDao.insert(cti ,vo);
	}
	public int remove(ContextInfo cti , SysCrawlerReport vo ){
		return sysCrawlerReportDao.remove(cti ,vo);
	}
	public int update(ContextInfo cti , SysCrawlerReport vo ){
		return sysCrawlerReportDao.update(cti ,vo);
	}
	public PageModel findPage(SysCrawlerReport vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysCrawlerReportDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysCrawlerReport> datas = sysCrawlerReportDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysCrawlerReport> findAll(SysCrawlerReport vo){
		
		List<SysCrawlerReport> datas = sysCrawlerReportDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:28
	
	
	
}
