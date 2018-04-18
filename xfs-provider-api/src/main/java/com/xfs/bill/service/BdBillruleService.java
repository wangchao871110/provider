package com.xfs.bill.service;

import java.util.List;

import com.xfs.bill.model.BdBillrule;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BdBillruleService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/09 11:34:52
 */
public interface BdBillruleService {
	
	public int save(ContextInfo cti, BdBillrule vo);
	public int insert(ContextInfo cti, BdBillrule vo);
	public int remove(ContextInfo cti, BdBillrule vo);
	public int update(ContextInfo cti, BdBillrule vo);
	public PageModel findPage(PageInfo pi, BdBillrule vo);
	public List<BdBillrule> findAll(BdBillrule vo);
	public BdBillrule findByAreaId(Integer areaId);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/09 11:34:52


}
