package com.xfs.crawler.service;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.redies.RedisUtil;
import com.xfs.crawler.dao.SysCrawlerRuleDao;
import com.xfs.crawler.model.SysCrawlerRule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class SysCrawlerRuleServiceImpl implements SysCrawlerRuleService {

	private static String CRAWLER_TASK_QUEUE_KEY = "CRAWLER_TASK_QUEUE_KEY";

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysCrawlerRuleServiceImpl.class);
	
	@Autowired
	private SysCrawlerRuleDao sysCrawlerRuleDao;
	
	public int save(ContextInfo cti , SysCrawlerRule vo ){
		return sysCrawlerRuleDao.save(cti,vo);
	}
	public int insert(ContextInfo cti , SysCrawlerRule vo ){
		return sysCrawlerRuleDao.insert(cti ,vo);
	}
	public int remove(ContextInfo cti , SysCrawlerRule vo ){
		return sysCrawlerRuleDao.remove(cti ,vo);
	}
	public int update(ContextInfo cti , SysCrawlerRule vo ){
		return sysCrawlerRuleDao.update(cti ,vo);
	}
	public PageModel findPage(SysCrawlerRule vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysCrawlerRuleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysCrawlerRule> datas = sysCrawlerRuleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysCrawlerRule> findAll(SysCrawlerRule vo){
		
		List<SysCrawlerRule> datas = sysCrawlerRuleDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:24


	/**
	 *  获取配置列表页面
	 *  @param   pageInfo, vo
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2017-02-20 10:05
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-02-20 10:05
	 *  @updateAuthor  :
	 */
	public PageModel findAllRulePage(PageInfo pageInfo, SysCrawlerRule vo){
		PageModel pm = new PageModel(pageInfo);
		int pageIndex = pageInfo.getOffset();
		int pageSize = pageInfo.getPagesize();
		Integer total = sysCrawlerRuleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysCrawlerRule> datas = sysCrawlerRuleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  获取爬取规则
	 *  @param   vo
	 *	@return 			: com.xfs.crawler.model.SysCrawlerRule
	 *  @createDate  	: 2017-02-20 10:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-02-20 10:50
	 *  @updateAuthor  :
	 */
	public SysCrawlerRule findByPk(SysCrawlerRule vo){
		return sysCrawlerRuleDao.findByPK(vo);
	}

	/**
	 *  删除规则
	 *  @param   cti, vo
	 *	@return 			: int
	 *  @createDate  	: 2017-02-20 10:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-02-20 10:52
	 *  @updateAuthor  :
	 */
	public int delete(ContextInfo cti,SysCrawlerRule vo){
		vo.setDr(1);
		return sysCrawlerRuleDao.updateDr(cti,vo);
	}

	/**
	 *  增加定时任务
	 *  @param   ruleId
	 *	@return 			: boolean
	 *  @createDate  	: 2017-02-20 11:12
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-02-20 11:12
	 *  @updateAuthor  :
	 */
	public boolean addCrawlerTask(ContextInfo cti,int ruleId){
		SysCrawlerRule vo = new SysCrawlerRule();
		vo.setId(ruleId);
		SysCrawlerRule sysCrawlerRule = findByPk(vo);
		RedisUtil.push(CRAWLER_TASK_QUEUE_KEY, sysCrawlerRule);
		//启动
		sysCrawlerRule.setStatus(1);
		return sysCrawlerRuleDao.update(cti,sysCrawlerRule) > 0 ? true : false;
	}
	
}
