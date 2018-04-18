package com.xfs.score.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.score.model.BdScoreDimension;

/**
 * BdScoreDimensionService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:16
 */
public interface BdScoreDimensionService {
	
	public int save(ContextInfo cti, BdScoreDimension vo);
	public int insert(ContextInfo cti, BdScoreDimension vo);
	public int remove(ContextInfo cti, BdScoreDimension vo);
	public int update(ContextInfo cti, BdScoreDimension vo);
	public PageModel findPage(PageInfo pi, BdScoreDimension vo);
	public List<BdScoreDimension> findAll(BdScoreDimension vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:16

	/**
	 * 获取评分维度
	 * @param vo
	 * @return
     */
	public  BdScoreDimension findByPk(BdScoreDimension vo);

	/**
	 * 根据评分项目类型获取评分维度列表
	 * @param type
	 * @return
     */
	public List<BdScoreDimension> findAllByItemType(String type);

	/**
	 * 执行动态sql
	 * @param sql
	 * @return
     */
	public List<Map<String,Object>> executeDynamicSql(String sql);
}
