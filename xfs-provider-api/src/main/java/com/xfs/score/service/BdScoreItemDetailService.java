package com.xfs.score.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.score.model.BdScoreItemDetail;

/**
 * BdScoreItemDetailService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:20
 */
public interface BdScoreItemDetailService {
	
	public int save(ContextInfo cti, BdScoreItemDetail vo);
	public int insert(ContextInfo cti, BdScoreItemDetail vo);
	public int remove(ContextInfo cti, BdScoreItemDetail vo);
	public int update(ContextInfo cti, BdScoreItemDetail vo);
	public PageModel findPage(PageInfo pi, BdScoreItemDetail vo);
	public List<BdScoreItemDetail> findAll(BdScoreItemDetail vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:20

	public String insertBdScoreItemDetail(ContextInfo cti, String calc_version, String type);
	
}
