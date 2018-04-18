package com.xfs.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysUserRoleServiceImpl.class);
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	public int save(ContextInfo cti, SysUserRole vo ){
		return sysUserRoleDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SysUserRole vo ){
		return sysUserRoleDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SysUserRole vo ){
		return sysUserRoleDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SysUserRole vo ){
		return sysUserRoleDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysUserRole vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysUserRoleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysUserRole> datas = sysUserRoleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysUserRole> findAll(SysUserRole vo){
		
		List<SysUserRole> datas = sysUserRoleDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:24

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
	public void addOrEdit(ContextInfo cti, Integer userId, Integer[] roleIdArr){
		sysUserRoleDao.deleteByUserId(cti, userId);
		if (roleIdArr != null){
			for (int i = 0; i < roleIdArr.length; i++){
				SysUserRole sur = new  SysUserRole();
				sur.setRoleId(roleIdArr[i]);
				sur.setUserId(userId);
				sysUserRoleDao.save(cti, sur);
			}
		}
	}

    /**
     * 根据用户id查找用户角色表数据
     *  @param    : userId  用户id
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public List<SysUserRole> findUserRole(Integer userId){
		return sysUserRoleDao.findByUserId(userId);
	}

    @Override
    public PageModel freeFind(PageInfo pi, SysUserRole userRole) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysUserRoleDao.countFreeFind(userRole);
        pm.setTotal(total);
        List<SysUserRole> datas = sysUserRoleDao.freeFind(userRole, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public SysUserRole findByPK(Integer id) {
        SysUserRole userRole = new SysUserRole();
        userRole.setId(id);
        List<SysUserRole> list = sysUserRoleDao.freeFind(userRole);
        return list.isEmpty()?null:list.get(0);
    }

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
    @Override
    public int removeByUserIdAndRoleId(ContextInfo cti, SysUserRole sysUserRole) {
        return sysUserRoleDao.removeByUserIdAndRoleId(cti, sysUserRole);
    }

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
    @Override
    public List<SysUserRole> listUserRole(SysUserRole userRole) {
        return sysUserRoleDao.freeFind(userRole);
    }

    @Override
    public List<SysUserRole> findAll() {
        return sysUserRoleDao.freeFind(null);
    }
	@Override
	public List<SysUserRole> findUserListByRoleId(Integer spId, Integer roleId) {
		return sysUserRoleDao.findUserListByRoleId(spId,roleId);
		
	}
	
}
