package com.xfs.mall.order.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.order.model.SpsMallOrderArea;

/**
 * SpsMallOrderAreaService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/10 19:26:49
 */
public interface SpsMallOrderAreaService {
	
	public int save(ContextInfo cti, SpsMallOrderArea vo);
	public int insert(ContextInfo cti, SpsMallOrderArea vo);
	public int remove(ContextInfo cti, SpsMallOrderArea vo);
	public int update(ContextInfo cti, SpsMallOrderArea vo);
	public PageModel findPage(PageInfo pi, SpsMallOrderArea vo);
	public List<SpsMallOrderArea> findAll(SpsMallOrderArea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 19:26:49
	
	
	
}
