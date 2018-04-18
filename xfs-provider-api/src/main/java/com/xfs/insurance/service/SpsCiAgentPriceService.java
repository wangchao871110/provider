package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.SpsCiAgentPrice;

/**
 * SpsCiAgentPriceService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/07 09:14:06
 */
public interface SpsCiAgentPriceService {
	
	public int save(ContextInfo cti, SpsCiAgentPrice vo);
	public int insert(ContextInfo cti, SpsCiAgentPrice vo);
	public int remove(ContextInfo cti, SpsCiAgentPrice vo);
	public int update(ContextInfo cti, SpsCiAgentPrice vo);
	public PageModel findPage(PageInfo pi, SpsCiAgentPrice vo);
	public List<SpsCiAgentPrice> findAll(SpsCiAgentPrice vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/07 09:14:06

	/**
	 * 更具商品编码和服务项编码查询商保承保价格信息
	 *
	 * @param product 商品信息
	 * @return 方案类表
	 */
	public List<CiScheme> findCiItemPrice(CiProduct product);

}
