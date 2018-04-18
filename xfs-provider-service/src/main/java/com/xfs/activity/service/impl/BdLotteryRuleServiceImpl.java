package com.xfs.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.BdLotteryRuleDao;
import com.xfs.activity.model.BdLotteryRule;
import com.xfs.activity.service.BdLotteryRuleService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BdLotteryRuleServiceImpl implements BdLotteryRuleService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdLotteryRuleServiceImpl.class);
	
	@Autowired
	private BdLotteryRuleDao bdLotteryRuleDao;
	
	public int save(ContextInfo cti, BdLotteryRule vo ){
		return bdLotteryRuleDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdLotteryRule vo ){
		return bdLotteryRuleDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdLotteryRule vo ){
		return bdLotteryRuleDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdLotteryRule vo ){
		return bdLotteryRuleDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdLotteryRule vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdLotteryRuleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdLotteryRule> datas = bdLotteryRuleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdLotteryRule> findAll(BdLotteryRule vo){
		
		List<BdLotteryRule> datas = bdLotteryRuleDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public List<Map<String, Object>> findruleList(Map vo) {
		List<Map<String, Object>> datas = bdLotteryRuleDao.findrulelist(vo);
		return datas;
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/19 20:17:56
	
	/**
	 * 获取没轮的中奖奖品
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findLotteryPrizes(BdLotteryRule vo) {
		return bdLotteryRuleDao.findLotteryPrizes(vo);
	}
	
}
