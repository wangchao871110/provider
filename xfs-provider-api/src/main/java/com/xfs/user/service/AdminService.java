package com.xfs.user.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysFunctionCategory;
import com.xfs.user.model.SysUser;


public interface AdminService {
	public Map getMenus(SysUser admin);
	public Map getMenus(SysUser admin,String sys_code);
	public SysUser adminLogin(String userName, String password);
	public void updatePassword(ContextInfo cti, SysUser user);
	public int mobileLogin(String userName, String password);
	public int mobileUpdatePass(ContextInfo cti, String userName, String passNew);
	public List getPriviledeList(int roleId);
	public Map<SysFunctionCategory, List<SysFunction>> queryUserRoleFuncList(String roleIds,String sys_code);
	/**
	 *  通过ruleids 获取 curd等级的功能权限
	 *  @param
	 * @return    :
	 *  @createDate   : 2017/11/9 16:30
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/11/9 16:30
	 *  @updateAuthor  :
	 */
	public List<Map> getUserFuncCurdByRole(String roles,String sysCode);
}
