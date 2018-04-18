package com.xfs.user.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysRole;

/**
 * SysRoleService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:23
 */
public interface SysRoleService {
	
	public int save(ContextInfo cti, SysRole vo);
	public int insert(ContextInfo cti, SysRole vo);
	public int remove(ContextInfo cti, SysRole vo);
	public int update(ContextInfo cti, SysRole vo);
	public PageModel findPage(PageInfo pi, SysRole vo);
	public List<SysRole> findAll(SysRole vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
    public PageModel getRoleList(PageInfo pi, SysRole role);

    public List getAllRoleList();

    public void delete(ContextInfo cti, SysRole vo);

    public SysRole findByPK(SysRole vo) ;

    /**
     * 根据项目记号查询角色
     *  @param    : Scode 区分项目的记号
     *  @return    : List<Map<String,Object>>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public List<Map<String,Object>> findRoleByScode(String Scode);


	public List<SysRole> queryAllRolesByUserId(Integer userId);


	public List<SysRole> findAllByAppCodes(SysRole vo);
}
