package com.xfs.mall.product.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.model.SpsMallProductConsult;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/10 0010 上午 10:23
 * @version  : V1.0
 */
public interface SpsMallProductConsultService {
	
	public int save(ContextInfo cti, SpsMallProductConsult vo);
	public int insert(ContextInfo cti, SpsMallProductConsult vo);
	public int remove(ContextInfo cti, SpsMallProductConsult vo);
	public int update(ContextInfo cti, SpsMallProductConsult vo);
	public PageModel findPage(PageInfo pi, SpsMallProductConsult vo);
	public List<SpsMallProductConsult> findAll(SpsMallProductConsult vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/17 11:24:11
	
	
	
}
