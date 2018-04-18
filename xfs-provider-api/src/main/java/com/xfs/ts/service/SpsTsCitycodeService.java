package com.xfs.ts.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsCitycode;

/**
 * SpsTsCitycodeService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/12 13:39:03
 */
public interface SpsTsCitycodeService {
	
	public int save(ContextInfo cti, SpsTsCitycode vo);
	public int insert(ContextInfo cti, SpsTsCitycode vo);
	public int remove(ContextInfo cti, SpsTsCitycode vo);
	public int update(ContextInfo cti, SpsTsCitycode vo);
	public PageModel findPage(PageInfo pi, SpsTsCitycode vo);
	public List<SpsTsCitycode> findAll(SpsTsCitycode vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/12 13:39:03


	public SpsTsCitycode getSpsTsCitycodeByAreaId(int areaId);
	public List<Map<String,Object>> getSpsTsCitycode();
	
}
