package com.xfs.bs.service;

import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysRelRoleFunc;

import java.util.List;

/**
 * SysRelRoleFuncService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:23
 */
public interface SysRelRoleFuncService {
	
	public void save(SysRelRoleFunc vo);
	public void insert(SysRelRoleFunc vo);
	public void remove(SysRelRoleFunc vo);
	public  void update(SysRelRoleFunc vo);
	public PageModel findPage(SysRelRoleFunc vo);
	public List<SysRelRoleFunc> findAll(SysRelRoleFunc vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
	
	
	
}
