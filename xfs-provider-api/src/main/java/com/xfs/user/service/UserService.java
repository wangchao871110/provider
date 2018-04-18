package com.xfs.user.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;
import com.xfs.user.mongo.UserOperationLog;

public interface UserService {

	public List<SysUser> freeFind(SysUser obj);

	public PageModel getAdminUserList(PageInfo pi, SysUser SysUser);

	public void deleteUser(ContextInfo cti, SysUser vo);

	public SysUser findByPK(SysUser vo);

	public int updateUser(ContextInfo cti, SysUser vo);

	public void insert(ContextInfo cti, SysUser vo);

	public void updateLastLogin(ContextInfo cti, Integer userId);

	public SysUser login(SysUser vo);

	public SysUser getUserByEmail(String email);

	public Map<String, SysUser> getAllUserMap();

	public SysUser getUserByUserName(String name);

	public String getUserIdStrByOrgId(SysUser user);

	public String getUserIdStr(SysUser user);

	public boolean checkUserNameExistance(String name);

	public boolean checkUserMobileExistance(String mobile);

	public int countFreeFind(SysUser sysUser);

	public void resetPassword(ContextInfo cti, SysUser sysUser);

	void activeUserWithSms(ContextInfo cti, SysUser sysUser, String content);

    SysUser findByOrgId(SysUser sysUser);

    PageModel getUserOperation(PageInfo info,String userId);
}
