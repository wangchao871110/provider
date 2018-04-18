package com.xfs.mall.item.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.item.model.BsMallItemarea;

/**
 * BsMallItemareaService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/09 14:08:11
 */
public interface BsMallItemareaService {
	
	public int save(ContextInfo cti, BsMallItemarea vo);
	public int insert(ContextInfo cti, BsMallItemarea vo);
	public int remove(ContextInfo cti, BsMallItemarea vo);
	public int update(ContextInfo cti, BsMallItemarea vo);
	public PageModel findPage(PageInfo pi, BsMallItemarea vo);
	public List<BsMallItemarea> findAll(BsMallItemarea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:11

	/**
	 * 通过itemId查找所属城市
	 * @param itemId
	 * @return
	 */
	public List<BsMallItemarea> findByItemId(int itemId);

	public List<Map> FreeFindItems(String chooseCityId, String categoryId, String searchWord);
	
}
