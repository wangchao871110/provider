package com.xfs.ts.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsAutoPage;

/**
 * SpsTsAutoPageService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/14 12:24:41
 */
public interface SpsTsAutoPageService {
	
	public int save(ContextInfo cti, SpsTsAutoPage vo);
	public int insert(ContextInfo cti, SpsTsAutoPage vo);
	public int remove(ContextInfo cti, SpsTsAutoPage vo);
	public int update(ContextInfo cti, SpsTsAutoPage vo);
	public PageModel findPage(PageInfo pi, SpsTsAutoPage vo);
	public List<SpsTsAutoPage> findAll(SpsTsAutoPage vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:41
	
	public SpsTsAutoPage findPK(SpsTsAutoPage vo);

	public SpsTsAutoPage findSpsTsAutoPage(SpsTsAutoPage vo);
	
}
