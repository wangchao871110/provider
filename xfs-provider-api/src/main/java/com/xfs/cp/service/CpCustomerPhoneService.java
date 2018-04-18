package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpCustomerPhone;

/**
 * CpCustomerPhoneService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:22
 */
public interface CpCustomerPhoneService {
	
	public int save(ContextInfo cti, CpCustomerPhone vo);
	public int insert(ContextInfo cti, CpCustomerPhone vo);
	public int remove(ContextInfo cti, CpCustomerPhone vo);
	public int update(ContextInfo cti, CpCustomerPhone vo);
	public PageModel findPage(PageInfo pi, CpCustomerPhone vo);
	public List<CpCustomerPhone> findAll(CpCustomerPhone vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22
	
	
	
}
