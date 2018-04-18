package com.xfs.crawler.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.crawler.model.SysCrawlerRule;

/**
 * SysCrawlerRuleService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/02/20 09:42:24
 */
public interface SysCrawlerRuleService {
	
	public int save(ContextInfo cti , SysCrawlerRule vo);
	public int insert(ContextInfo cti ,SysCrawlerRule vo);
	public int remove(ContextInfo cti ,SysCrawlerRule vo);
	public int update(ContextInfo cti ,SysCrawlerRule vo);
	public PageModel findPage(SysCrawlerRule vo);
	public List<SysCrawlerRule> findAll(SysCrawlerRule vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:24


	public PageModel findAllRulePage(PageInfo pageInfo, SysCrawlerRule vo);

	public SysCrawlerRule findByPk(SysCrawlerRule vo);

	public int delete(ContextInfo cti,SysCrawlerRule vo);

	public boolean addCrawlerTask(ContextInfo cti,int ruleId);

}
