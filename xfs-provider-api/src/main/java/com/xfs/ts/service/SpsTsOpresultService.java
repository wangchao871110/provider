package com.xfs.ts.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsOpresult;

/**
 * SpsTsOpresultService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/04 16:10:19
 */
public interface SpsTsOpresultService {
	
	public int save(ContextInfo cti, SpsTsOpresult vo);
	public int insert(ContextInfo cti, SpsTsOpresult vo);
	public int remove(ContextInfo cti, SpsTsOpresult vo);
	public int update(ContextInfo cti, SpsTsOpresult vo);
	public PageModel findPage(PageInfo pi, SpsTsOpresult vo);
	public List<SpsTsOpresult> findAll(SpsTsOpresult vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/04 16:10:19

	public List<Map<String,Object>> findBySchemeId(Integer schemeId);
	
}
