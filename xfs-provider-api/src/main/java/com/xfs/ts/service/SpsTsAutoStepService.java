package com.xfs.ts.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsAutoStep;

/**
 * SpsTsAutoStepService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/14 12:24:42
 */
public interface SpsTsAutoStepService {
	
	public int save(ContextInfo cti, SpsTsAutoStep vo);
	public int insert(ContextInfo cti, SpsTsAutoStep vo);
	public int remove(ContextInfo cti, SpsTsAutoStep vo);
	public int update(ContextInfo cti, SpsTsAutoStep vo);
	public PageModel findPage(PageInfo pi, SpsTsAutoStep vo);
	public List<SpsTsAutoStep> findAll(SpsTsAutoStep vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:42
	
	public SpsTsAutoStep findPK(SpsTsAutoStep vo);
	public int removeByPageId(ContextInfo cti, Integer pageId);
}
