package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CpCiOrderDetail;
import com.xfs.insurance.model.CpCiOrder;

/**
 * CpCiOrderService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 19:00:49
 */
public interface CpCiOrderService {
	
	public int save(ContextInfo cti, CpCiOrder vo);
	public int insert(ContextInfo cti, CpCiOrder vo);
	public int remove(ContextInfo cti, CpCiOrder vo);
	public int update(ContextInfo cti, CpCiOrder vo);
	public PageModel findPage(PageInfo pi, CpCiOrder vo);
	public List<CpCiOrder> findAll(CpCiOrder vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 19:00:49

	/**
	 * 分页查询协作平台订单
	 *
	 * @param vo 查询条件
	 * @return 查询结果
	 */
	public PageModel findCpOrder(PageInfo pi,CpCiOrderDetail vo);
	
	public PageModel findCiOrder(PageInfo pi,CpCiOrder orderInfo);

	public int saveOrderInfo(ContextInfo cti,CpCiOrder orderInfo);

	public CpCiOrder findOne(CpCiOrder vo);

	public int payEnd(ContextInfo cti,CpCiOrder vo);
	
}
