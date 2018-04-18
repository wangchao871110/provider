package com.xfs.mall.order.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.order.model.SpsMallOrderItem;

/**
 * SpsMallOrderItemService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:06:25
 */
public interface SpsMallOrderItemService {
	
	public int save(ContextInfo cti, SpsMallOrderItem vo);
	public int insert(ContextInfo cti, SpsMallOrderItem vo);
	public int remove(ContextInfo cti, SpsMallOrderItem vo);
	public int update(ContextInfo cti, SpsMallOrderItem vo);
	public PageModel findPage(PageInfo pi, SpsMallOrderItem vo);
	public List<SpsMallOrderItem> findAll(SpsMallOrderItem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:06:25
	/**
	 * 根据 主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  下午3:24:39
	 */
	public SpsMallOrderItem findByPK(SpsMallOrderItem obj);
	
	
}
