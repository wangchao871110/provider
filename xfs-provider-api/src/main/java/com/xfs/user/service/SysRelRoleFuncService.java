package com.xfs.user.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysRelRoleFunc;

/**
 * SysRelRoleFuncService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:23
 */
public interface SysRelRoleFuncService {
	
	public int save(ContextInfo cti, SysRelRoleFunc vo);
	public int insert(ContextInfo cti, SysRelRoleFunc vo);
	public int remove(ContextInfo cti, SysRelRoleFunc vo);
	public int update(ContextInfo cti, SysRelRoleFunc vo);
	public PageModel findPage(PageInfo pi, SysRelRoleFunc vo);
	public List<SysRelRoleFunc> findAll(SysRelRoleFunc vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
    public void delete(ContextInfo cti, SysRelRoleFunc vo);

    public void deleteByRoleId(ContextInfo cti, Integer roleId);

    public SysRelRoleFunc findByPK(SysRelRoleFunc obj);
	
	
}
