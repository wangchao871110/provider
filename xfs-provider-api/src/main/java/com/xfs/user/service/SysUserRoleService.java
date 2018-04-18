package com.xfs.user.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUserRole;

/**
 * SysUserRoleService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:24
 */
public interface SysUserRoleService {
	
	public int save(ContextInfo cti, SysUserRole vo);
	public int insert(ContextInfo cti, SysUserRole vo);
	public int remove(ContextInfo cti, SysUserRole vo);
	public int update(ContextInfo cti, SysUserRole vo);
	public PageModel findPage(PageInfo pi, SysUserRole vo);
	public List<SysUserRole> findAll(SysUserRole vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:24

    PageModel freeFind(PageInfo pi, SysUserRole userRole);

    SysUserRole findByPK(Integer id);

    /**
     * 根据用户实体属性数据查找用户角色列表
     *  @param    : userRole  用户角色实体
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
    List<SysUserRole>  listUserRole(SysUserRole userRole);

    List<SysUserRole> findAll();

    /**
     * 根据用户id跟角色id删除用户角色表数据
     *  @param    : sysUserRole  用户角色实体
     *  @return    :  int
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
    int removeByUserIdAndRoleId(ContextInfo cti, SysUserRole sysUserRole);


    /**
     * 添加或修改用户所属role
     *  @param    : userId 用户id
     *  @param    ： roleIdArr 角色数组
     *  @return    :
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public void addOrEdit(ContextInfo cti, Integer userId, Integer[] roleIdArr);

    /**
     * 查找用户所属role
     *  @param    : userId 用户id
     *  @return    :  List<SysUserRole>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public List<SysUserRole> findUserRole(Integer userId);
	public List<SysUserRole> findUserListByRoleId(Integer spId, Integer roleId);
}
