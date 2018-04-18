package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.insurance.dto.CiOrderInfo;
import com.xfs.insurance.model.SpsCiOrder;
import com.xfs.user.model.SysUser;

/**
 * SpsCiOrderService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/10 11:59:05
 */
public interface SpsCiOrderService {
	
	public int save(ContextInfo cti, SpsCiOrder vo);
	public int insert(ContextInfo cti, SpsCiOrder vo);
	public int remove(ContextInfo cti, SpsCiOrder vo);
	public int update(ContextInfo cti, SpsCiOrder vo);
	public PageModel findPage(PageInfo pi, SpsCiOrder vo);
	public List<SpsCiOrder> findAll(SpsCiOrder vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/10 11:59:05
	public Result createCiOrder(ContextInfo cti,CiOrderInfo orderInfo, CmCorp corp);
	
}
