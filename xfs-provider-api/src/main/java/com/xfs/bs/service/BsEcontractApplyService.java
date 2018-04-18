package com.xfs.bs.service;

import java.util.List;

import com.xfs.base.model.BsEcontractApply;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsEcontractApplyService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 22:51:24
 */
public interface BsEcontractApplyService {
	
	public int save(ContextInfo cti, BsEcontractApply vo);
	public int insert(ContextInfo cti, BsEcontractApply vo);
	public int remove(ContextInfo cti, BsEcontractApply vo);
	public int update(ContextInfo cti, BsEcontractApply vo);
	public PageModel findPage(PageInfo pi, BsEcontractApply vo);
	public List<BsEcontractApply> findAll(BsEcontractApply vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 22:51:24
	
	
	
}
