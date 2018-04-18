package com.xfs.activity.service;
import java.util.List;
import java.util.Map;

import com.xfs.activity.model.BdSaleProduct;
import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;

/**
 * BdSaleProductService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/16 15:07:22
 */
public interface BdSaleProductService {
	
	public int save(ContextInfo cti, BdSaleProduct vo);
	public int insert(ContextInfo cti, BdSaleProduct vo);
	public int remove(ContextInfo cti, BdSaleProduct vo);
	public int update(ContextInfo cti, BdSaleProduct vo);
	public PageModel findPage(BdSaleProduct vo);
	public List<BdSaleProduct> findAll(BdSaleProduct vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 15:07:22

	public List<Map<String, Object>> querySaleProduct(BdSaleProduct vo);
	
}
