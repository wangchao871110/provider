package com.xfs.score.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.score.model.BdScoreCollection;

/**
 * BdScoreCollectionService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:19
 */
public interface BdScoreCollectionService {
	
	public int save(ContextInfo cti, BdScoreCollection vo);
	public int insert(ContextInfo cti, BdScoreCollection vo);
	public int remove(ContextInfo cti, BdScoreCollection vo);
	public int update(ContextInfo cti, BdScoreCollection vo);
	public PageModel findPage(PageInfo pi, BdScoreCollection vo);
	public List<BdScoreCollection> findAll(BdScoreCollection vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:19


	/**
	 * 根据版
	 * @param calc_version
     */
	public void collectionScore(ContextInfo cti, String calc_version, String type);
	
}
