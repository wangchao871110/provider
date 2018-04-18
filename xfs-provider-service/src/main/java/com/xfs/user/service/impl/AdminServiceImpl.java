package com.xfs.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.util.StringUtils;
import com.xfs.user.dao.SysFunctionCategoryDao;
import com.xfs.user.dao.SysFunctionDao;
import com.xfs.user.dao.SysRelRoleFuncDao;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysFunctionCategory;
import com.xfs.user.model.SysRelRoleFunc;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger log = Logger.getLogger(AdminServiceImpl.class);
	@Autowired
	private SysFunctionCategoryDao categoryDAO;
	@Autowired
	private SysFunctionDao functionDAO;
	@Autowired
	private SysRelRoleFuncDao relRoleFuncDAO;
	@Autowired
	private SysUserDao userDAO;
	@Autowired
	private SysUserRoleDao userRoleDao;
	@Autowired
	private SysUserDao sysUserDao;
	

	/**
	 * 根据用户取得其所有菜单目录
	 * 
	 *  @param admin   SysUser对象
	 *  @return 
	 *	@return 			: java.util.Map 
	 *  @createDate  	    : 2016年11月15日 上午11:57:44
	 *  @author         	: zhengdan@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	    : 2016年11月15日 上午11:57:44
	 *  @updateAuthor       :
	 */
	public Map getCategory(SysUser admin) {
		Map<Integer, SysFunctionCategory> map = new HashMap();
		SysUserRole sysUserRole =new SysUserRole();
		sysUserRole.setUserId(admin.getUserId());
		List<SysUserRole> userRoles = userRoleDao.freeFind(sysUserRole);
		List<Integer> roles = new ArrayList<Integer>();
		for(SysUserRole userRole:userRoles){
			roles.add(userRole.getRoleId());
		}
		// 1. 取第2级目录(第一级目录未使用)
		List<SysFunctionCategory> secondCatLists = categoryDAO.getSecondMenuByRoleList(roles);
		for (SysFunctionCategory cat : secondCatLists) {
			map.put(cat.getCategoryId(), cat);
		}
		return map;
	}

	
	/**
	 * 根据用户取得其所有菜单
	 * 
	 *  @param admin   SysUser对象
	 *  @return 		:java.util.Map
	 *  @createDate  	: 2016年11月15日 上午11:58:00
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:58:00
	 *  @updateAuthor  	:
	 */
	public Map getMenus(SysUser admin) {
		Map<SysFunctionCategory, List> map = new LinkedHashMap();
		SysUserRole sysUserRole =new SysUserRole();
		sysUserRole.setUserId(admin.getUserId());
		List<SysUserRole> userRoles = userRoleDao.freeFind(sysUserRole);
		String role_ids ="";
		List<Integer> roles = new ArrayList<Integer>();
		if(userRoles.isEmpty()){
			role_ids = "-1";
		}else{
			for(SysUserRole userRole:userRoles){
				roles.add(userRole.getRoleId());
				role_ids +=userRole.getRoleId() + ",";
			}
			role_ids = role_ids.substring(0, role_ids.length()-1);
		}
	
		// 1. 取第2级目录(第一级目录未使用)
		List<SysFunctionCategory> secondCatLists = categoryDAO.getSecondMenuByRoleList(roles);
		for (SysFunctionCategory cat : secondCatLists) {
			// 2. 取菜单
			List<SysFunction> menus = functionDAO.getFunctionByRoleAndCategoryIn(role_ids, cat.getCategoryId());
			map.put(cat, menus);
		}
		return map;
	}
	
	/**
	 * 根据用户取得其所有菜单
	 *  @param 	admin     SysUser对象
	 *  @param  sys_code  功能所属系统代码
	 *  @return 		:java.util.Map
	 *  @createDate  	: 2016年11月15日 上午11:58:13
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:58:13
	 *  @updateAuthor  	:
	 */
	public Map getMenus(SysUser admin,String sys_code) {
		/**
		 * 1.判断当前用户是否存在角色
		 * 2.根据角色在缓存中拿去功能菜单
		 * 3.缓存中不存在，查询数据库并存储到缓存中
		 */
		Map<SysFunctionCategory, List<SysFunction>> functionCategoryListMap = new LinkedHashMap<>();
		if(StringUtils.isBlank(admin.getRoleIds()))
				return functionCategoryListMap;
		functionCategoryListMap = queryUserRoleFuncList(admin.getRoleIds(),sys_code);
		return functionCategoryListMap;
	}

	/**
	 * 根据用户角色获取功能菜单
	 *  @param  roleIds   角色id
	 *  @param  sys_code  功能所属系统代码
	 *  @return 	     : java.util.Map<com.xfs.user.model.SysFunctionCategory, java.util.List<com.xfs.user.model.SysFunction>>
	 *  @createDate  	 : 2016年11月15日 上午11:58:29
	 *  @author          : zhengdan@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate    	 : 2016年11月15日 上午11:58:29
	 *  @updateAuthor  	 :
	 */
	@Override
	public Map<SysFunctionCategory, List<SysFunction>> queryUserRoleFuncList(String roleIds,String sys_code){
		List<Map<String,Object>> funclist = sysUserDao.queryUserRoleFuncList(roleIds,sys_code);
		Map<SysFunctionCategory, List<SysFunction>> functionCategoryListMap = new LinkedHashMap<>();
		Map<Integer,SysFunctionCategory> sysFunctionCategory = new HashMap<>();
		if(null != funclist && !funclist.isEmpty()){
			SysFunctionCategory category;
			for(Map<String,Object> funmap : funclist){
				if(null == funmap.get("category_id")){
					category = null;
				}else{
					if(null == sysFunctionCategory.get(Integer.parseInt(String.valueOf(funmap.get("category_id"))))){
						category = new SysFunctionCategory();
						category.setCategoryId(Integer.parseInt(String.valueOf(funmap.get("category_id"))));
						category.setCategoryName(String.valueOf(funmap.get("category_name")));
						sysFunctionCategory.put(category.getCategoryId(),category);
					}else{
						category = sysFunctionCategory.get(Integer.parseInt(String.valueOf(funmap.get("category_id"))));
					}
				}
				/**
				 * 二级功能菜单
				 */
				SysFunction function = new SysFunction();
				function.setFunctionId(Integer.parseInt(String.valueOf(funmap.get("function_id"))));
				function.setFunctionName(String.valueOf(funmap.get("function_name")));
				function.setUrl(String.valueOf(funmap.get("url")));
				if(!functionCategoryListMap.containsKey(category)){
					functionCategoryListMap.put(category,new ArrayList<SysFunction>());
				}
				functionCategoryListMap.get(category).add(function);
			}
		}
		return functionCategoryListMap;
	}
	
	
	/**
	 *  管理员登陆验证
	 *  @param  userName   用户名
	 *  @param  password   密码
	 *  @return            : com.xfs.user.model.SysUser
	 *  @createDate  	   : 2016年11月15日 上午11:58:43
	 *  @author            : zhengdan@xinfushe.com
	 *  @version           : v1.0
	 *  @updateDate    	   : 2016年11月15日 上午11:58:43
	 *  @updateAuthor  	   :
	 */
	public SysUser adminLogin(String userName, String password) {
		SysUser user=null;
		user=checkUser(userName, password);
		if(user!=null){
			return user;
		}
		return null;
	}

	/**
	 *  登录
	 *  @param userName  用户名
	 *  @param password  密码
	 *  @return 		:java.lang.Integer
	 *  @createDate  	: 2016年11月15日 下午5:18:39
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:18:39
	 *  @updateAuthor  	:
	 */
	public int mobileLogin(String userName, String password) {
		SysUser user = new SysUser();
		user.setUserNameEq(userName);
		user.setPasswordEq(password);
		user.setEnabled(1);
		try {
			List list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return 0;// 成功登陆

			user.setPasswordEq(null);
			list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return 1;// 密码错误
			else
				return 2;// 用户名不存在
		} catch (Exception e) {
			log.error(e);
		}
		return 2;
	}

	/**
	 *  修改密码
	 *  @param userName   用户名
	 *  @param passNew    密码
	 *  @return 		: java.lang.Integer
	 *  @createDate  	: 2016年11月15日 下午5:22:38
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:22:38
	 *  @updateAuthor  	:
	 */
	public int mobileUpdatePass(ContextInfo cti, String userName, String passNew) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		try {
			List list = userDAO.freeFind(user);
			if (list == null || list.size() == 0)
				return 1;// 登陆失败

			user = (SysUser) list.get(0);
			user.setPassword(passNew);
			user.setModifyDt(new Date());
			user.setModifyBy(userName);
			userDAO.update(cti, user);

		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	/**
	 *  修改密码
	 *  @param user  SysUser对象
	 *  @createDate  	: 2016年11月15日 下午5:25:16
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:25:16
	 *  @updateAuthor  	:
	 */
	public void updatePassword(ContextInfo cti, SysUser user) {
		this.userDAO.update(cti, user);

	}

	/**
	 * 根据角色id查询SysRelRoleFunc
	 * 
	 *  @param roleId  角色id
	 *  @return 		: java.util.List
	 *  @createDate  	: 2016年11月15日 下午5:28:53
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:28:53
	 *  @updateAuthor  	:
	 */
	public List getPriviledeList(int roleId) {
		SysRelRoleFunc func = new SysRelRoleFunc();
		func.setRoleId(roleId);
		List<SysRelRoleFunc> list = relRoleFuncDAO.freeFind(func);
		return list;
	}

	/**
	 *  检查用户是否存在
	 * 
	 *  @param user   SysUser对象
	 *  @return 
	 *	@return 		: SysUser 
	 *  @createDate  	: 2016年11月15日 下午5:37:13
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:37:13
	 *  @updateAuthor   :
	 */
	private SysUser checkUser(SysUser user) {
		user.setEnabled(1);
		try {
			List list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return (SysUser) list.get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return null;

	}

	/**
	 *  检查用户是否存在
	 *  @param userName  用户名
	 *  @param password  密码
	 *  @return 
	 *	@return 		: SysUser 
	 *  @createDate  	: 2016年11月15日 下午5:39:10
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:39:10
	 *  @updateAuthor   :
	 */
	private SysUser checkUser(String userName, String password) {
		SysUser user = new SysUser();
		user.setUserNameEq(userName);
		user.setPasswordEq(password);
		user = checkUser(user);
		if (user != null) {
			return user;
		} else {
			return null;
		}

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
	public List<Map> getUserFuncCurdByRole(String roles,String sysCode){
		return sysUserDao.getUserFuncCurdByRole(roles, sysCode);
	}
}
