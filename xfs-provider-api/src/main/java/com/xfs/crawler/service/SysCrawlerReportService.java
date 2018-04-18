package com.xfs.crawler.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.crawler.model.SysCrawlerReport;

/**
 * SysCrawlerReportService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/02/20 09:42:28
 */
public interface SysCrawlerReportService {
	
	public int save(ContextInfo cti , SysCrawlerReport vo);
	public int insert(ContextInfo cti ,SysCrawlerReport vo);
	public int remove(ContextInfo cti ,SysCrawlerReport vo);
	public int update(ContextInfo cti ,SysCrawlerReport vo);
	public PageModel findPage(SysCrawlerReport vo);
	public List<SysCrawlerReport> findAll(SysCrawlerReport vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:28
	
	
	
}
