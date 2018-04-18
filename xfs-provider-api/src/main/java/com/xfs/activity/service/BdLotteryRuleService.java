package com.xfs.activity.service;

import java.util.List;
import java.util.Map;

import com.xfs.activity.model.BdLotteryRule;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
/**
 * 
* @ClassName: BdLotteryRuleService 
* @Description: 活动中奖规则
* @author duanax@xinfushe.com 
* @date 2016年11月15日 上午11:24:21 
*
 */
public interface BdLotteryRuleService {
	
	public int save(ContextInfo cti, BdLotteryRule vo);
	public int insert(ContextInfo cti, BdLotteryRule vo);
	public int remove(ContextInfo cti, BdLotteryRule vo);
	public int update(ContextInfo cti, BdLotteryRule vo);
	public PageModel findPage(PageInfo pi, BdLotteryRule vo);
	public List<BdLotteryRule> findAll(BdLotteryRule vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/19 20:17:56
	
	/**
	 * 
	* @Title: findruleList 
	* @Description: 根据奖品查询中间规则
	* @param @param vo
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findruleList(Map vo);
	
	/**
	 * 获取没轮的中奖奖品
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findLotteryPrizes(BdLotteryRule vo);
}
