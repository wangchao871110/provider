package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsSchemeItem;

/**
 * SpsSchemeItemService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:26:36
 */
public interface SpsSchemeItemService {
	
	public int save(ContextInfo cti, SpsSchemeItem vo);
	public int insert(ContextInfo cti, SpsSchemeItem vo);
	public int remove(ContextInfo cti, SpsSchemeItem vo);
	public int update(ContextInfo cti, SpsSchemeItem vo);
	public PageModel findPage(PageInfo pi, SpsSchemeItem vo);
	public List<SpsSchemeItem> findAll(SpsSchemeItem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:36
	/**
	 * 根据 方案id 查询 方案的项目
	 *
	 * @author lifq
	 *
	 * 2016年8月8日  下午5:11:02
	 */
	public List<Map<String,Object>> findItemsBySchemeId(Integer scheme_id);
	
	
}
