package com.xfs.user.service;


import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysRelRoleFunc;


public interface SysFunctionService {
	public void save(ContextInfo cti, SysFunction vo);
	public void insert(ContextInfo cti, SysFunction vo);
	public void remove(ContextInfo cti, SysFunction vo);
	public  void update(ContextInfo cti, SysFunction vo);
	public PageModel findPage(PageInfo pi, SysFunction vo);
	public List<SysRelRoleFunc> findAll(SysFunction vo);

	public SysFunction queryLastSysFunction(SysFunction obj);


	public List<SysFunction> getFuctionList(SysFunction function);
}
