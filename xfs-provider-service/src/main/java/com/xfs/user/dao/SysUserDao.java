package com.xfs.user.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.user.model.SysFunctionCrud;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;
import com.xfs.user.model.SysUser;

/**
 * SysUserDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/01/21 11:03:16
 */
@Repository
public class SysUserDao extends BaseSqlMapDao implements IStaticVarConstant {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SYS_USER.CountFindAllSYS_USER", null);
		return ret.intValue();
	}

	public SysUser findByPK(SysUser obj) {
		Object ret = queryForObject("SYS_USER.FindByPK", obj);
		if (ret != null)
			return (SysUser) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SysUser> freeFind(SysUser obj) {
		return queryForList("SYS_USER.FreeFindSYS_USER", obj);
	}

	public int countFreeFind(SysUser obj) {
		Integer ret = (Integer) queryForObject("SYS_USER.CountFreeFindSYS_USER", obj);
		return ret.intValue();
	}


	@SuppressWarnings("unchecked")
	public List<SysUser> freeFind(SysUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER.FreeFindSYS_USER", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SysUser> freeFind(SysUser obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SysUser) {
			_hashmap = ((SysUser) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SYS_USER.FreeFindSYS_USEROrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SysUser> freeFind(SysUser obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SysUser) {
			_hashmap = ((SysUser) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SYS_USER.FreeFindSYS_USEROrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SysUser> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SysUser> it = objColl.iterator();
			while (it.hasNext()) {
				SysUser oneObj = (SysUser) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SysUser vo) {
		SysUser obj = (SysUser) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SysUser obj) {

		obj.fixup();
		return insert(cti, "SYS_USER.InsertSYS_USER", obj);
	}

	public int update(ContextInfo cti, SysUser obj) {

		obj.fixup();
		return update(cti, "SYS_USER.UpdateSYS_USER", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysUser obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return update(cti, "SYS_USER.DeleteSYS_USER", obj);

	}

	public int removeObjectTree(ContextInfo cti, SysUser obj) {

		obj.fixup();
		SysUser oldObj = (SysUser) queryForObject("SYS_USER.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SYS_USER.DeleteSYS_USER", oldObj);

	}

	public boolean exists(SysUser vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SysUser obj = (SysUser) vo;

		keysFilled = keysFilled && (null != obj.getUserId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SYS_USER.CountSYS_USER", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/01/21 11:03:16

	/**
	 * 2015-01-18 nieqq 根据电话号修改密码
	 * 
	 * @param mobile
	 * @param password
	 */
	public void updateByMobile(ContextInfo cti, String mobile, String password) {
		Map map = new HashMap();
		map.put("mobile", mobile);
		map.put("password", password);
		update(cti, "SYS_USER.UpdateByPhone", map);
	}

	public int findPassByPassWord(SysUser user) {
		Integer ret = (Integer) queryForObject("SYS_USER.FindPassByPassWord", user);
		return ret.intValue();
	}

	public int updateByNewPass(ContextInfo cti, SysUser user) {
		return update(cti, "SYS_USER.UpdateByNewPass", user);
	}

	/**
	 * 更新最后登录时间
	 */
	public int updateByLastLoginDt(ContextInfo cti, SysUser obj) {
		return update(cti, "SYS_USER.updateByLastLoginDt", obj);
	}

	/**
	 * 根据 登录名(邮箱、手机号)、密码、用户类型、审核类型 查询用户
	 * 
	 */
	public SysUser FindByLoginNamePass(SysUser obj) {
		return (SysUser) queryForObject("SYS_USER.FindByLoginNamePass", obj);
	}

	/**
	 * 根据 服务商ID spId 服务类型，审核类型 查询当前服务商下所有 用户
	 */
	@SuppressWarnings("unchecked")
	public List<SysUser> findBySpIdUserTypeEnabled(SysUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER.findBySpIdUserTypeEnabled", obj, pageIndex, pageSize);
	}

	/**
	 * 根据 服务商ID spId 服务类型，审核类型 查询当前服务商下所有 用户总数
	 */
	public int findBySpIdUserTypeEnabledCount(SysUser obj) {
		Integer ret = (Integer) queryForObject("SYS_USER.findBySpIdUserTypeEnabledCount", obj);
		return ret.intValue();
	}

	/**
	 * 根据当前行里的userIdEq 来修改当前数据
	 */
	public int updateByUserIdEq(ContextInfo cti, SysUser obj) {
		return update(cti, "SYS_USER.updateByUserIdEq", obj);
	}

	/**
	 * 新增用户数据
	 */
	public int insertByUserIdEq(ContextInfo cti, SysUser obj) {
		return insert(cti, "SYS_USER.insertByUserIdEq", obj);
	}

	/**
	 * 根据服务商ID，用户类型，UserIdEq 来查询当前用户数据
	 */
	public SysUser findByUserIdEq(SysUser obj) {
		return (SysUser) queryForObject("SYS_USER.findByUserIdEq", obj);
	}

	/**
	 * 根据当前登录用户 来修改用户列表状态 
	 */
	public int updateUserState(ContextInfo cti, SysUser obj) {
		return update(cti, "SYS_USER.UpdateSYS_USERState", obj);
	}

	/**
	 * 根据用户名，手机号，来查询当前用户数据
	 */
	public int findByEmailMobile(SysUser obj) {
		return (Integer) queryForObject("SYS_USER.findByEmailMobile", obj);
	}
	/**
	 * 根据当前登录用户 修改创建新用户或重置用户密码
	 */
	public int UpdateSYS_USERPassWord(ContextInfo cti, SysUser obj) {
		return update(cti, "SYS_USER.UpdateSYS_USERPassWord", obj);
	}
	
	public SysUser findByMobile(SysUser obj) {
		return (SysUser) queryForObject("SYS_USER.findByMobile", obj);
	}

	/**
	 * 根据企业ID获取用户信息
	 * @param cpid
	 * @return
     */
	public SysUser querySysUserByCorpId(Integer cpid){
		Map<String,Object> obj = new HashMap<>();
		obj.put("orgId",cpid);
		obj.put("userType","CUSTOMER");
		return (SysUser) queryForObject("SYS_USER.querySysUserByCorpId", obj);
	}


	/**
	 * 2016-08 
	 * zhangxiyan
	 * 根据用户ID 查询当前用户
	 */
	public SysUser findUserByUserId(Integer userId) {
		SysUser obj = new SysUser();
		obj.setUserId(userId);
		return (SysUser) queryForObject("SYS_USER.findUserByUserId", obj);
	}

    public void updatePass(ContextInfo cti, SysUser obj) {
        obj.fixup();
        update(cti, "SYS_USER.UpdateSYS_USERS", obj);
    }

    /**
     * HR 登录
     */
    @SuppressWarnings("unchecked")
    public List<SysUser> hrLogin(SysUser obj) {
        return queryForList("SYS_USER.LoginSYS_USER", obj);
    }

    public SysUser hrLogins(SysUser obj) {
        return (SysUser) queryForObject("SYS_USER.LoginSYS_USER", obj);
    }

    /**
     * 更新最后登录时间
     */
    public List findByMobileList(SysUser obj) {
        return queryForList("SYS_USER.FindByMobile", obj);
    }

    /**
     * 根据角色Id获取对应的功能菜单
     * @param roleIds
     * @return
     */
    public List<Map<String,Object>> queryUserRoleFuncList(String roleIds,String sys_code){
        Map<String,Object> obj = new HashMap<>();
        obj.put("roleIds",roleIds);
        obj.put("sys_code",sys_code);
        return  queryForList("SYS_USER.queryUserRoleFuncList", obj);
    }

	/**
	 * 根据用户角色和企业版本获取功能菜单
	 * @param roleIds
	 * @param cpId
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> queryUserRoleCorpTypeFuncList(String roleIds,Integer cpId,Integer userId){
		Map<String,Object> obj = new HashMap<>();
		obj.put("roleIds",roleIds);
		obj.put("cpId",cpId);
		obj.put("userId",userId);
		return  queryForList("SYS_USER.queryUserRoleCorpTypeFuncList", obj);
	}

    /**
     * 账号管理条件查询
     * @param
     * @return
     */
    public List accountManage(SysUser sysUser,Integer pageSize,Integer pageIndex,String orderBy){
        Map<String,Object> param = sysUser.toHashMap();
        if(!StringUtils.isBlank(orderBy)){
            param.put("orderBy",orderBy);
        }
        return queryForList("SYS_USER.accountManage",param) ;
    }

    /**
     * 查询分页总数
     * @param sysUser
     * @return
     */
    public Integer accountManageCount(SysUser sysUser){
        return (Integer) queryForObject("SYS_USER.accountManageCount",sysUser) ;
    }
    public void updateState(ContextInfo cti, SysUser sysUser){
        update(cti, "SYS_USER.updateState",sysUser);
    }

    /*
    * 点击编辑的回显
    * */
    public SysUser findManager(SysUser sysUser){

        return (SysUser) queryForObject("SYS_USER.findManager",sysUser) ;
    }
    /*
    * 先查询要修改的手机号在数据库中是否存在，再修改
    * */
    public Integer findCustomer(Map map){
        Integer cus= (Integer) queryForObject("SYS_USER.findCustomer",map);
        return cus.intValue();
    }

    public Integer updateManager(ContextInfo cti, Map map){
        return update(cti, "SYS_USER.updateManager",map);
    }
    /*
    * 修改密码
    * */
    public Integer resetPwd(ContextInfo cti, Map map){
        return update(cti, "SYS_USER.resetPwd",map);
    };
    /*
    * 查询数据库中是否有重复的手机号
    * */
    public int findMobile(Map map){
        Integer mob= (Integer) queryForObject("SYS_USER.findMobile",map);
        return mob.intValue();
    }

    public Integer addMobile(ContextInfo cti, Map map){
        return insert(cti, "SYS_USER.addMobile",map);
    }




    /*
    * 修改联系人
    * */
    public Integer updateLinkman(ContextInfo cti, Map map){
        return  update(cti, "SYS_USER.updateLinkman",map);
    }


    public Integer findPwd(Map map){
        Integer pwd=(Integer) queryForObject("SYS_USER.findPwd",map);
        return pwd.intValue();
    }

    public Integer updatePwd(ContextInfo cti, Map map){
        return update(cti, "SYS_USER.updatePwd",map);
    }

	/**
	 *
	 * @method comments: 根据手机号获取用户信息
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月2日 下午4:54:30
	 * @param sysUser
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月2日 下午4:54:30 wangchao 创建
	 *
	 */
    public SysUser findUserByMobile(SysUser sysUser) {
		try {
			return (SysUser) queryForObject("SYS_USER.findUserByMobile",sysUser) ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 * @method comments: 重置密码
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月6日 下午8:04:33
	 * @param sysUser
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月6日 下午8:04:33 wangchao 创建
	 *
	 */
	public int resetPassWord(ContextInfo cti, SysUser sysUser) {
		sysUser.fixup();
		return update(cti, "SYS_USER.UpdateSYS_USER_PASSWORD", sysUser);
	}
    /**
     * findUsersBySpId
     *
     * @param spId
     * @return
     */
    public List<SysUser> findUsersBySpId(Integer spId) {
        Map<String, Object> map = new HashedMap();
        map.put("orgId", spId);
        return queryForList("SYS_USER.findUsersBySpId", map);
    }
    /** 新人指导--根据user_id 查询指导记录表是否完成指导操作
     *  @param    : Integer userId
     * @return    :Result
     *  @createDate   : 16-12-12 下午17:12
     *  @author          : zhangxiyan@xinfushe.com
     *  @version         : v1.0
     */
	public Integer countNewGuidance(Integer userId){
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("owner", userId);
        map.put("dr", 0);
        map.put("ownerType",CMCORPTYPE_SERVICE);
        map.put("attributeName","NEWGUIDANCE");//新手指导
        map.put("attributeValue","1");//1已经操作过新手指导
        Integer ret = (Integer) queryForObject("BS_SYS_STATE_REPORT.checkNewGuidanceByUserId",map);
        return ret.intValue();
    }
	 /** 新人指导--根据user_id插入一条表数据
     *  @param    : Integer userId
     * @return    :Result
     *  @createDate   : 16-12-12 下午17:12
     *  @author          : zhangxiyan@xinfushe.com
     *  @version         : v1.0
     */
	public Integer insetNewGuidance(Integer userId){
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("owner", userId);
        map.put("dr", 0);
        map.put("ownerType",CMCORPTYPE_SERVICE);
        map.put("attributeName","NEWGUIDANCE");//新手指导
        map.put("attributeValue","1");//1已经操作过新手指导
        map.put("createBy",userId);
        map.put("createDt",new Date());
        return insert(null,"BS_SYS_STATE_REPORT.insertNewGuidance",map);
    }

    public List<Map> queryFunctionCrudByroleIds(String roleIds,Integer cpId) {
		Map<String,Object> map = new HashMap<>();
		map.put("roleIds",roleIds);
		map.put("cpId",cpId);
		return queryForList("SYS_USER.queryFunctionCrudByroleIds", map);
	}

	public List<SysUser> findUserAuth(Integer cpId){
		Map<String,Object> map = new HashMap<>();
		map.put("cpId",cpId);
		return queryForList("SYS_USER.find_User_Auth", map);
	}

	public SysUser findUserByYHTUserId(SysUser sysUser){
		try {
			return (SysUser) queryForObject("SYS_USER.findUserByYHTUserId",sysUser) ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public SysUser findUserByAccessToken(SysUser sysUser){
		try {
			return (SysUser) queryForObject("SYS_USER.findUserByAccessToken",sysUser) ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<SysUser> queryAllUnSynList(Integer userId,String mobile){
		Map<String,Object> map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("userId",userId);
		return queryForList("SYS_USER.findUnSynUserList", map);
	}

	public List<SysUser> findUsersBySpIdAndName(SysUser sysUser){
		return queryForList("SYS_USER.findUsersBySpIdAndName", sysUser);
	}

	public SysUser findUsersByUserId(SysUser sysUser){
		return (SysUser) queryForObject("SYS_USER.findUsersByUserId", sysUser);
	}

	public int CountSysUserByRoleId(SysUser obj) {
		Integer ret = (Integer) queryForObject("SYS_USER.CountSysUserByRoleId", obj);
		return ret.intValue();
	}

	public int checkUserCount(SysUser sysUser) {
		Integer ret = (Integer) queryForObject("SYS_USER.checkUserCount", sysUser);
		return ret.intValue();
	}

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
	public List<Map> getUserFuncCurdByRole(String roleIds,String sysCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("roleIds",roleIds);
		map.put("sysCode",sysCode);
		return queryForList("SYS_USER.getUserFuncCurdByRole", map);
	}
}
