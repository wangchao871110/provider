package com.xfs.score.service;


import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.score.model.BdScoreDimension;
import com.xfs.score.model.BdScoreItem;

/**
 * BdScoreItemService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:13
 */
public interface BdScoreItemService {
	
	public int save(ContextInfo cti, BdScoreItem vo);
	public int insert(ContextInfo cti, BdScoreItem vo);
	public int remove(ContextInfo cti, BdScoreItem vo);
	public int update(ContextInfo cti, BdScoreItem vo);
	public PageModel findPage(PageInfo pi, BdScoreItem vo);
	public List<BdScoreItem> findAll(BdScoreItem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:13

	/**
	 * 根据Id获取评分项
	 * @param vo
	 * @return
     */
	public BdScoreItem findByPk(BdScoreItem vo);

	/**
	 * 增加事件监听对象
	 * @param baseCalcScore
     */
	public void addEventObject(BaseCalcScore baseCalcScore);

	/**
	 * 核算评分系统
	 * @param type
     */
	public void queryCalcScore(String type);


	/**
	 * 具体核算方法
	 * @param bdScoreDimension
	 * @return
     */
	public void calcScore(BdScoreDimension bdScoreDimension);
}
