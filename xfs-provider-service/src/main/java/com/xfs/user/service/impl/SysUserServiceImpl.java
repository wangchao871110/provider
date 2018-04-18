package com.xfs.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Constant;
import com.xfs.common.util.MobileValidator;
import com.xfs.common.util.SessionUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.model.CmCorp;
import com.xfs.cp.dto.UserDto;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.user.dao.SysFunctionCategoryDao;
import com.xfs.user.dao.SysFunctionDataDao;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysFunctionCategory;
import com.xfs.user.model.SysFunctionData;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.model.dto.SessionMenu;
import com.xfs.user.model.dto.SysUserToken;
import com.xfs.user.service.SysFunctionDataService;
import com.xfs.user.service.SysUserService;



@Service
public class SysUserServiceImpl implements SysUserService, IRedisKeys {

	private static final Logger log = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysFunctionDataDao sysFunctionDataDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	@Autowired
	private SpsSchemeService  spsSchemeService;

	public int save(ContextInfo cti, SysUser vo) {
		return sysUserDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SysUser vo) {
		return sysUserDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SysUser vo) {
		return sysUserDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SysUser vo) {
		return sysUserDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SysUser vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysUserDao.findBySpIdUserTypeEnabledCount(vo);
		pm.setTotal(total);
		List<SysUser> datas = sysUserDao.findBySpIdUserTypeEnabled(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SysUser> findAll(SysUser vo) {

		List<SysUser> datas = sysUserDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/01/21 11:03:16
	/**
	 * 新增或编辑用户
	 * 
	 * 2016-12-03 薪福邦 sass免费入入驻调用过此方法 
	 */
	@Override
	public Result addOrEdit(ContextInfo cti, SysUser vo) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		/**
		 * 先查询，判断手机号是否已经注册
		 */
		Integer queryC = sysUserDao.findByEmailMobile(vo);
		if (queryC > 0) {
			result.setSuccess(false);
			result.setError("该用户已存在！");
			return result;
		}
		if (null != vo) {
			// 修改
			if (null != vo.getUserIdEq()) {
				result.setDataValue("_isEdit", true);
				vo.setModifyBy(vo.getUserId().toString());
				vo.setModifyDt(date);
				Integer upds = sysUserDao.updateByUserIdEq(cti, vo);
				if (upds > 0) {
					result.setSuccess(true);
					// ol_opt_modelTODO 调用发送短信接口
				}
			}
			// 新增
			else {
				result.setDataValue("_isEdit", false);
				if (vo.getUserId() != null)
					vo.setCreateBy(vo.getUserId().toString());
				vo.setCreateDt(date);
				vo.setEnabled(1);
				if (StringUtils.isBlank(vo.getPassword())) {
					String pass = this.resetPassword(vo);
					vo.setPassword(StringUtils.md5(pass));
					Integer ints = sysUserDao.insertByUserIdEq(cti, vo);
					if (ints > 0) {
						result.setSuccess(true);
						vo.setUserIdEq(vo.getUserId());
						vo.setUserId(Integer.parseInt(vo.getCreateBy()));
						vo.setPassword(pass);
						this.activeUserWithSms(vo, "尊敬的服务商，您的账号已经开通，快来进入吧！请使用手机号登陆，密码为：%s【用友薪福社】");
					} else {
						result.setSuccess(false);
						result.setError("保存失败！");
					}
				} else {
					Integer ints = sysUserDao.insertByUserIdEq(cti, vo);
					if (ints > 0) {
						result.setSuccess(true);
						this.activeUserWithSms(vo, "尊敬的服务商，您的账号已经开通，快来进入吧，请使用手机号登陆!【用友薪福社】");
					} else {
						result.setSuccess(false);
						result.setError("保存失败！");
					}
				}
			}
		}
		return result;
	}

	@Override
	public Result addOrEditWithDataFunc(ContextInfo cti, SysUser vo, String cmCorpAll, Integer[] cmCorp) {
		Result r = addOrEdit(cti, vo);
		if (r.isSuccess()) {
			initUserDataFunc(cti, vo, cmCorpAll, cmCorp);
		}
		return r;
	}
	@Override
	public void initUserDataFunc(ContextInfo cti, SysUser vo, String cmCorpAll, Integer[] cmCorp) {
		// 保存权限
		sysFunctionDataDao.deleteByUserId(cti, vo.getUserIdEq());
		if ("Y".equals(cmCorpAll)) {
			SysFunctionData sysFunctionData = new SysFunctionData();
			sysFunctionData.setUserId(vo.getUserIdEq());
			sysFunctionData.setUserType(vo.getUserType());
			sysFunctionData.setDataType("CM_CORP");
			sysFunctionData.setIsAll("Y");
			sysFunctionData.setDr(0);
			sysFunctionDataDao.save(cti, sysFunctionData);
		}else {
			if(null != cmCorp){
				for (Integer dataId : cmCorp) {
					SysFunctionData sysFunctionData = new SysFunctionData();
					sysFunctionData.setUserId(vo.getUserIdEq());
					sysFunctionData.setUserType(vo.getUserType());
					sysFunctionData.setDataType("CM_CORP");
					sysFunctionData.setDataId(dataId);
					sysFunctionData.setIsAll("N");
					sysFunctionData.setDr(0);
					sysFunctionDataDao.save(cti, sysFunctionData);
				}
			}
		}
	}

	/**
	 * 点击编辑时根据UserIdEq 查询当前用户数据
	 */
	public SysUser findByUserIdEq(SysUser vo) {
		vo = sysUserDao.findByUserIdEq(vo);
		return vo;
	}

	/**
	 * 修改 用户状态 停用，已启用
	 */
	@Override
	public Result updateUserState(ContextInfo cti, SysUser sys) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		sys.setModifyBy(sys.getUserId().toString());
		sys.setModifyDt(date);
		if ("y".equals(sys.getUserNameEq())) {
			sys.setEnabled(1);
		}
		if ("n".equals(sys.getUserNameEq())) {
			sys.setEnabled(0);
		}
		Integer upds = sysUserDao.updateUserState(cti, sys);
		if (upds > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public Result repassWord(ContextInfo cti, SysUser sys) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
//		SysUser syss = sysUserDao.findByUserIdEq(sys);//根据被修改用户ID 查询其信息
		SysUser syss = sysUserDao.findByPK(sys);
		String pass = this.resetPassword(syss);
		syss.setPassword(StringUtils.md5(pass));
		syss.setModifyBy(sys.getUserId().toString());
		syss.setModifyDt(date);
		Integer upds  = sysUserDao.update(cti, syss);
//		Integer upds = sysUserDao.UpdateSYS_USERPassWord(syss);//保存创建或修改用户的重置后的密码
		if (upds > 0) {
			syss.setPassword(pass);
			this.activeUserWithSms(syss, "尊敬的服务商，您的账号已经开通，快来进入吧！请使用手机号登陆，密码为：%s【用友薪福社】");
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 新增或修改用户 发送短信
	 */
	public void activeUserWithSms(SysUser sysUser, String content) {
		try {
			content = String.format(content, sysUser.getPassword());
			boolean smsresult =  SmsUtil.sendSms(sysUser.getMobile(), content);
			if (!smsresult)
				throw new BusinessException("短信发送失败!请检查配置文件是否正确!");
		} catch (Exception e) {
			String errorMessage = "短信发送失败!" + e.getMessage();
			log.error(errorMessage, e);
			throw new BusinessException(errorMessage);
		}
	}

	private String resetPassword(SysUser sysUser) {
		String newPassword = StringUtils.getRandomSixString();
		return newPassword;
	}

	@Autowired
	private SysFunctionCategoryDao categoryDAO;

	/**
	 * 根据用户取得其所有菜单目录
	 * 
	 * @param admin
	 * @return
	 */
	@Override
	public List<SysFunctionCategory> getCategory(SysUser admin) {
		return getSpsCategory();
	}

	private List<SysFunctionCategory> getSpsCategory() {
		List<SysFunctionCategory> cats = new ArrayList<>();
		SysFunctionCategory category = new SysFunctionCategory();
		category.setParentId(200);
		// 1. 取第2级目录(第一级目录未使用)
		List<SysFunctionCategory> secondCatLists = categoryDAO.freeFind(category);
		for (SysFunctionCategory cat : secondCatLists) {
			cats.add(cat);
		}
		return cats;
	}

    /**
     *  根据用户角色获取功能菜单
     *  @param    : roleIds  角色ids
     *  @return    :  sys_code 系统区别
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Map<SysFunctionCategory, List<SysFunction>> queryUserRoleFuncList(SysUser user){
		List<Map<String,Object>> funclist = sysUserDao.queryUserRoleCorpTypeFuncList(user.getRoleIds(),user.getOrgId(),user.getUserId());
		Map<SysFunctionCategory, List<SysFunction>> functionCategoryListMap = new LinkedHashMap<>();
		Map<String,SysFunctionCategory> sysFunctionCategory = new HashMap<>();
		if(null != funclist && !funclist.isEmpty()){
			SysFunctionCategory category;
			StringBuffer product_code = new StringBuffer();
			for(Map<String,Object> funmap : funclist){
				if(null == funmap.get("category_id")){
					category = null;
				}else{
					String category_key = "";
					if("XFS_SS".equals(String.valueOf(funmap.get("sys_code"))) && !"设置".equals(String.valueOf(funmap.get("category_name")))){
						category_key = "社保";
					}else if("设置".equals(String.valueOf(funmap.get("category_name")))){
						category_key = "设置";
					}else {
						category_key = "工资条";
					}
					if(!product_code.toString().contains(String.valueOf(funmap.get("sys_code")))){
						product_code.append(String.valueOf(funmap.get("sys_code")));
					}
					if(null == sysFunctionCategory.get(category_key)){
						category = new SysFunctionCategory();
						category.setCategoryName(category_key);
						sysFunctionCategory.put(category.getCategoryName(),category);
					}else{
						category = sysFunctionCategory.get(category_key);
					}
				}
				/**
				 * 二级功能菜单
				 */
			    SysFunction function = new SysFunction();
				function.setFunctionId(Integer.parseInt(String.valueOf(funmap.get("function_id"))));
				function.setCategoryId(Integer.parseInt(String.valueOf(funmap.get("category_id"))));
				function.setFunctionName(String.valueOf(funmap.get("category_name")));
				function.setUrl(String.valueOf(funmap.get("url")));
				if(!functionCategoryListMap.containsKey(category)){
					functionCategoryListMap.put(category,new ArrayList<SysFunction>());
				}
				List<SysFunction> functions = functionCategoryListMap.get(category);
				if(functions.isEmpty() || String.valueOf(funmap.get("category_name")).equals(String.valueOf(funmap.get("function_name")))){
					SysFunction tagerFun = new SysFunction();
					BeanUtils.copyProperties(function,tagerFun);
					functions.add(tagerFun);
				}
				/**
				 * 三级功能菜单
				 */
				//判断是否存在三级菜单
				for(SysFunction fct : functions){
					if(!String.valueOf(funmap.get("category_name")).equals(String.valueOf(funmap.get("function_name")))){
						fct.setUrl("");
						function.setFunctionName(String.valueOf(funmap.get("function_name")));
						List<SysFunction> childFunctions = fct.getChildFunctions();
						if(null == childFunctions){
							fct.setChildFunctions(new ArrayList<SysFunction>());
						}
						fct.getChildFunctions().add(function);
						break;
					}
				}
			}
			//当单品时去掉 分类显示字段
			/*
			if(product_code.toString().equals("XFS_SS") || product_code.toString().equals("XFS_PAY")){
				for (Map.Entry<SysFunctionCategory, List<SysFunction>> entry : functionCategoryListMap.entrySet()){
					SysFunctionCategory sysfct = entry.getKey();
					sysfct.setCategoryName("");
				}
			}
			*/
		}
		return functionCategoryListMap;
	}

    /**
     *  根据手机号查找用户
     *  @param    : vo  用户实体
     *  @return    :   List<SysUser>
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public SysUser findByMobile(String mobile, String usertype, Integer orgid) {
		SysUser qry = new SysUser();
		qry.setMobile(mobile);
		qry.setUserType(usertype);
		qry.setOrgId(orgid);
		return sysUserDao.findByMobile(qry);
	}

    /**
     *  手机号码是否存在
     *  @param    : mobile  手机号码
     *  @param    : usertype  用户类型
     *  @return    :  boolean
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public boolean existMobile(String mobile, String usertype) {
		SysUser qry = new SysUser();
		qry.setMobileEq(mobile);
		qry.setUserType(usertype);
		int c = sysUserDao.countFreeFind(qry);
		if (c > 0)
			return true;
		else
			return false;
	}
	/**
     * 根据主键查询
     *
     * @author lifq
     *
     * 2016年6月15日  下午2:23:37
     */
    public SysUser findByPK(SysUser obj){
    	return sysUserDao.findByPK(obj);
    }

    @Override
    public SysUser findByPK(Integer pk){
    	SysUser qry = new SysUser();
    	qry.setUserId(pk);
    	return sysUserDao.findByPK(qry);
    }


    /**
     *  企业登录
     *  @param    : mobile  手机号码
     *  @param    :  password 密码
     *  @return    :  SysUser
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public SysUser loginCorp(String mobile, String password) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mobile);
        SysUser sysUser = new SysUser();
        Date date = new Date();
        if (MobileValidator.isMobileNO(mobile)) {
            sysUser.setMobile(mobile);
        } else {
            return null;
        }
        sysUser.setPassword(password);
        sysUser.setUserType("CUSTOMER");
        sysUser.setEnabled(1);// 审核类型 1 已审核 0待审核
        sysUser.setUserType("CUSTOMER");
        sysUser = sysUserDao.FindByLoginNamePass(sysUser);
        if (sysUser == null) {
            return null;
        } else {
            sysUser.setLastLoginDt(date);
            Integer upds = sysUserDao.updateByLastLoginDt(null, sysUser);
            if (upds <= 0) {
                return null;
            }
        }
        return sysUser;
    }

    /**
     *  修改用户密码
     *  @param    : vo  用户实体
     *  @return    :   Result
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public Result editUserPass(ContextInfo cti, SysUser vo) {
        Result result = Result.createResult().setSuccess(false);
        if (vo != null) {
            if (vo.getPassword() == null) {
                result.setDataValue("msg", "请输入密码！");
                result.setSuccess(false);
            } else {
                SysUser sysUser = new SysUser();
                sysUser.setMobile(vo.getMobile());
                sysUser.setUserType("CUSTOMER");
                List<SysUser> userList = sysUserDao.findByMobileList(sysUser);
                if (userList != null && userList.size() > 0) {
                    sysUser = userList.get(0);
                    sysUser.setPassword(vo.getPassword());
                    int flag = sysUserDao.update(cti, sysUser);
                    if (flag > 0) {
                        result.setSuccess(true);
                        result.setDataValue("msg","设置密码成功");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<SysUser> findByMobile(SysUser vo) {
        return sysUserDao.findByMobileList(vo).size() > 0 ? sysUserDao.findByMobileList(vo) : null ;
    }

    /**
     * 根据用户角色获取用户角色列表
     * @param user
     * @return
     */
    @Override
    public Map<SysFunctionCategory, List<SysFunction>> getMenus(SysUser user) {
        /**
         * 1.判断当前用户是否存在角色
         * 2.根据角色在缓存中拿去功能菜单
         * 3.缓存中不存在，查询数据库并存储到缓存中
         */
        //获取按钮权限列表并放到reidis中
        setAuthButton(user);
        Map<SysFunctionCategory, List<SysFunction>> functionCategoryListMap = new LinkedHashMap<>();
        if(StringUtils.isBlank(user.getRoleIds()))
            return functionCategoryListMap;
        String key = MALL_USER_ROLE_FUNC_LIST + "_" + user.getRoleIds();
        RedisUtil.delete(key);
        if (null == RedisUtil.get(key)) {
            functionCategoryListMap = queryUserRoleFuncList(user);
			RedisUtil.set(key,functionCategoryListMap, 0);
        }else{
            functionCategoryListMap = (Map<SysFunctionCategory, List<SysFunction>>) RedisUtil.get(key);
        }
        return functionCategoryListMap;
    }

    @Override
    public Map<SysFunctionCategory, List<SysFunction>> getMenus(Integer userId) {
    	return getMenus(findByPK(userId));
    }

    /**
     *账号管理的条件查询
     * @param
     * @return
     */
    @Override
    public PageModel accountManage(PageInfo pi, SysUser sysUser) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysUserDao.accountManageCount(sysUser);
        pm.setTotal(total);
        List<SysUser> datas = sysUserDao.accountManage(sysUser,pageSize,pageIndex,"create_dt desc");

        pm.setDatas(datas);
        return pm;
    }

    @Override
    public void updateState(ContextInfo cti, SysUser sysUser) {
        sysUserDao.updateState(cti, sysUser);
    }

    @Override
    public SysUser findManager(SysUser sysUser) {
        return sysUserDao.findManager(sysUser);
    }

    @Override
    public Integer findCustomer(Map map) {
        return sysUserDao.findCustomer(map);
    }

    @Override
    public Integer updateManager(ContextInfo cti, Map map) {
        String error="";
        if(org.apache.commons.lang.StringUtils.isNotEmpty(map.get("mobile").toString())){
            if (!MobileValidator.isMobileNO(map.get("mobile").toString())) {
                error += "手机号格式错误！<br>";
            }
        }
        map.put("error",error);
        return sysUserDao.updateManager(cti, map);
    }

    @Override
    public Result resetPwd(ContextInfo cti, Map map) {

        Result result = new Result();
        String error="";
        if(StringUtils.isBlank(map.get("mobile").toString())){
            result.setSuccess(false).setError("手机号不能为空");
            return result;
        }
        if (!MobileValidator.isMobileNO(map.get("mobile").toString())) {
            result.setSuccess(false).setError("手机号格式错误");
            return result;
        }
        map.put("newPwd",com.xfs.common.util.StringUtils.md5((String) map.get("acPwd")));
        sysUserDao.resetPwd(cti, map);

        return result;
    }

    @Override
    public int findMobile(Map map) {
        return sysUserDao.findMobile(map);
    }

    @Override
    public Result keep(ContextInfo cti, SysUser vo) {

        Result result = new Result();

        String error="";
        if(StringUtils.isBlank(vo.getMobile())){
            result.setSuccess(false).setError("手机号不能为空");
            return result;
        }
        if (!MobileValidator.isMobileNO(vo.getMobile())) {
            result.setSuccess(false).setError("手机号格式错误");
            return result;
        }
        sysUserDao.insert(cti, vo);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(vo.getUserId());
        sysUserRole.setRoleId(SysRole.CUSTOMER_SUPER_ROLE_ID);
        sysUserRoleDao.insert(cti, sysUserRole);
        return result;
    }


    @Override
    public Integer addMobile(ContextInfo cti, Map map) {
        return sysUserDao.addMobile(cti, map);
    }

    @Override
    public Result updateLinkman(ContextInfo cti, Map map) {

        Result result = new Result();

        String error="";
        if(StringUtils.isBlank(map.get("mobile").toString())){
            result.setSuccess(false).setError("手机号不能为空");
            return result;
        }
        if (!MobileValidator.isMobileNO(map.get("mobile").toString())) {
            result.setSuccess(false).setError("手机号格式错误");
            return result;
        }

        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(map.get("uemail").toString());
        boolean isMatched = matcher.matches();
        if(isMatched!=true){
            result.setSuccess(false).setError("邮箱格式错误");
            return result;
        }

        sysUserDao.updateLinkman(cti, map);
        return result;
    }

    @Override
    public Integer findPwd(Map map) {
        return sysUserDao.findPwd(map);
    }

    @Override
    public Integer updatePwd(ContextInfo cti, Map map) {
        return sysUserDao.updatePwd(cti, map);
    }

    @Override
    public Integer findByEmail(SysUser sysUser){
        Integer pm = sysUserDao.countFreeFind(sysUser);
        return pm;
    }


	/**
	 * 根据手机号获取用户信息
	 */
	@Override
	public SysUser findUserByMobile(UserDto userDto) {
		SysUser sysUser = new SysUser();
		if (MobileValidator.isMobileNO(userDto.getMobile())) {
			sysUser.setUserType(Constant.U_TYPE_SERVICE);
			sysUser.setMobile(userDto.getMobile());
			//sysUser.setEnabled(1);
			if(null != userDto.getPassword()){
				sysUser.setPassword(userDto.getPassword());
			}
		} else {
			return null;
		}
		return sysUserDao.findUserByMobile(sysUser);
	}

	/**
	 * 给手机号发验证码 并校验手机
	 */
	@Override
	public Result getCode(UserDto userDto) {
		Result result = Result.createResult().setSuccess(false);
		if (null != userDto && !StringUtils.isBlank(userDto.getMobile())) {
			if (StringUtils.isMobileNO(userDto.getMobile())) {
				try {
					// 手机号
					String mobile = userDto.getMobile();
					//校验当前手机号是否存在
					SysUser sysUser = new SysUser();
					sysUser.setMobile(mobile);
					sysUser.setUserType(Constant.U_TYPE_SERVICE);
					sysUser = sysUserDao.findUserByMobile(sysUser);
					if(null != sysUser){
						result.setDataValue("msg", "该手机号已被注册");
					}else{
						Object obj = RedisUtil.get(mobile);
						if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
							obj = (int)(Math.random() * 9000 + 1000);
						}
						// 验证码 600秒有效期
						boolean flag = RedisUtil.set(mobile, obj, 600);
						if (flag) {
							boolean smsFlag = SmsUtil.sendVerificationCode(mobile,"【薪福邦】验证码:" + obj + ",如不是本人操作,请忽略！");
							result.setSuccess(true);
							log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
							result.setDataValue("msg", "短信发送成功，请注意查收...");
						} else {
							result.setDataValue("msg", "验证码生成失败...");
						}
					}
				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码");
		}
		return result;
	}

	/**
	 * 给手机号发验证码 并校验手机 0:修改密码，1：修改账号
	 * 
	 */
	@Override
	public Result sendCode(UserDto userDto,Integer type) {
		Result result = Result.createResult().setSuccess(false);
		if (null != userDto && !StringUtils.isBlank(userDto.getMobile())) {
			if (StringUtils.isMobileNO(userDto.getMobile())) {
				try {
					// 手机号
					String mobile = userDto.getMobile();
					//校验当前手机号是否存在
					SysUser sysUser = new SysUser();
					sysUser.setMobile(mobile);
					sysUser.setUserType(Constant.U_TYPE_SERVICE);
					sysUser = sysUserDao.findUserByMobile(sysUser);
					if(null == sysUser){
						result.setDataValue("msg", "该用户不存在！");
					}else{
						Object obj = RedisUtil.get(mobile);
						if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
							obj = (int)(Math.random() * 9000 + 1000);
						}
						// 验证码 600秒有效期
						boolean flag = RedisUtil.set(mobile, obj, 600);
						if (flag) {
							String msg = ",您正在修改密码,如不是本人操作,请忽略！";
							if(type == 1){
								msg = ",您正在修改账号,如不是本人操作,请忽略！";
							}
							boolean smsFlag = SmsUtil.sendVerificationCode(mobile,"【薪福邦】验证码:" + obj + msg);
							result.setSuccess(true);
							log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
							result.setDataValue("msg", "短信发送成功，请注意查收...");
						} else {
							result.setDataValue("msg", "验证码生成失败...");
						}
					}
				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码");
		}
		return result;
	}
	

	/**
	 *
	 * @method comments: 发送短信
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月6日 下午3:03:34
	 * @param sysUser
	 * @param content
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月6日 下午3:03:34 wangchao 创建
	 *
	 */
	public void activeUserWithSms(SysUser sysUser,String password, String content) {
		try {
			content = String.format(content, password);
			boolean smsresult =  SmsUtil.sendSms(sysUser.getMobile(), content);
			if (!smsresult)
				throw new BusinessException("短信发送失败!请检查配置文件是否正确!");
		} catch (Exception e) {
			String errorMessage = "短信发送失败!" + e.getMessage();
			log.error(errorMessage, e);
			throw new BusinessException(errorMessage);
		}
	}

	/**
	 * 重置密码
	 */
	@Override
	public Result resetUserPass(HttpServletRequest request,UserDto userDto) {
		Result result = Result.createResult().setSuccess(false);
		if (userDto != null) {
			String valiCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (null == userDto.getValiCode() || !userDto.getValiCode().equalsIgnoreCase(valiCode)) {
				result.setSuccess(false);
				result.setMsg("图片验证码不正确！");
			}
			Object send = RedisUtil.get(userDto.getMobile());
			String code = userDto.getMsgCode();
			if (StringUtils.isBlank(code) || !code.equals(String.valueOf(send))) {
				result.setSuccess(false);
				result.setError("短信验证码输入不正确！");
			}
			if (userDto.getPassword() == null) {
				result.setError("请输入密码！");
				result.setSuccess(false);
			} else {
				SysUser sysUser = new SysUser();
				sysUser.setMobile(userDto.getMobile());
				sysUser.setUserType(Constant.U_TYPE_SERVICE);
				sysUser = sysUserDao.findUserByMobile(sysUser);
				if (null != sysUser) {
					sysUser.setPassword(userDto.getPassword());
					int flag = sysUserDao.resetPassWord(null, sysUser);
					if (flag > 0) {
						result.setSuccess(true);
						result.setMsg("您设置密码成功，请牢记密码！");
					}
				}
			}
		}
		return result;
	}


	/**
	 * 给手机号发验证码
	 */
	@Override
	public Result note(UserDto userDto) {
		Result result = Result.createResult().setSuccess(false);
		if (null != userDto && !StringUtils.isBlank(userDto.getMobile())) {
			if (StringUtils.isMobileNO(userDto.getMobile())) {
				try {
					// 手机号
					String mobile = userDto.getMobile();
					Object obj = RedisUtil.get(mobile);
					if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
						obj = (int) (Math.random() * 9000 + 1000);
					}
					// 验证码 600秒有效期
					boolean flag = RedisUtil.set(mobile, obj, 600);
					if (flag) {
						boolean smsFlag = SmsUtil.sendVerificationCode(mobile,"【薪福邦】验证码:" + obj + ",如不是本人操作,请忽略！");
						result.setSuccess(true);
						log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
						result.setDataValue("msg", "短信发送成功，请注意查收...");
					} else {
						result.setDataValue("msg", "验证码生成失败...");
					}

				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码");
		}
		return result;
	}

	@Override
	public Result sendLoginCode(UserDto userDto) {
		Result result = Result.createResult().setSuccess(false);
		if (null != userDto && !StringUtils.isBlank(userDto.getMobile())) {
			if (StringUtils.isMobileNO(userDto.getMobile())) {
				try {
					// 手机号
					String mobile = userDto.getMobile();
					//校验当前手机号是否存在
					SysUser sysUser = new SysUser();
					sysUser.setMobile(mobile);
					sysUser.setUserType(Constant.U_TYPE_SERVICE);
					sysUser = sysUserDao.findUserByMobile(sysUser);
					if(null == sysUser){
						result.setDataValue("msg", "该用户不存在！");
					}else{
						Object obj = RedisUtil.get(mobile);
						if (obj == null || obj == "" || String.valueOf(obj).length() > 4) {
							obj = (int)(Math.random() * 9000 + 1000);
						}
						// 验证码 600秒有效期
						boolean flag = RedisUtil.set(mobile, obj, 600);
						if (flag) {
							boolean smsFlag = SmsUtil.sendVerificationCode(mobile,"【薪福邦】验证码:" + obj + ",您正在手机登录系统,如不是本人操作,请忽略！");
							result.setSuccess(true);
							log.info("已发送验证码为：" + obj + ",发送状态:" + smsFlag);
							result.setDataValue("msg", "短信发送成功，请注意查收...");
						} else {
							result.setDataValue("msg", "验证码生成失败...");
						}
					}
				} catch (Exception e) {
					result.setDataValue("msg", "验证码发送失败");
					e.printStackTrace();
				}
			} else {
				result.setDataValue("msg", "请输入正确的手机号码");
			}
		} else {
			result.setDataValue("msg", "请输入手机号码");
		}
		return result;
	}

	/**
	 * findUsersBySpId
	 *
	 * @param spId
	 * @return
	 */
	public List<SysUser> findUsersBySpId(Integer spId) {
		return sysUserDao.findUsersBySpId(spId);
	}

	private static final int maxExpireTime = 3600*24*10;
	private static final int minExpireTime = 1;

	/*
	 * 根据用户ID创建token，用于单点登录
	 * @param cti 当前系统会话
	 * @param userId 用户id
	 * @param fromSys 创建token的系统 mall saas op cp
	 * @param toSys 应用token的系统
	 * @param expire token过期时间
	 * @param isOnce 一次有效,用后删除
	 */
	/* (non-Javadoc)
	 * @see com.xfs.user.service.impl.SysUserTokenService#createTokenWithUserId(com.xfs.common.IContextInfo, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, boolean)
	 */
	@Override
	public String createTokenWithUserId(IContextInfo cti, Integer userId, String fromSys, String toSys, Integer expire, boolean isOnce, boolean isForceLogOut) {
		SysUser user = findByPK(userId);
		if (null != user && Integer.valueOf(1).equals(user.getEnabled())) {
			String token = UUID.randomUUID().toString();
			String key = IRedisKeys.COMMON_XFS_USER_TOKEN + token;
			SysUserToken uToken = new SysUserToken();
			uToken.setToSys(toSys);
			uToken.setUserId(userId);
			uToken.setIsOnce(isOnce);
			uToken.setIsForceLogOut(isForceLogOut);
			if (null == expire)
				expire = 60;
			if (Integer.valueOf(maxExpireTime) < expire)
				expire = maxExpireTime;
			if (Integer.valueOf(minExpireTime) > expire)
				expire = minExpireTime;
			RedisUtil.set(key, uToken, expire);
			log.info(String.format("用户%s%s从%s使用令牌方式登陆到%s，模拟用户身份%s", cti.getUserId(),cti.getUserName(),fromSys,toSys,userId));
			return token;
		} else {
			throw new BusinessException("用户无效");
		}
		
	}
	
	/*
	 * 验证令牌是否有效
	 * @param token 接收到的令牌
	 * @param curSys 接收令牌的系统标识  mall saas op cp
	 */
	/* (non-Javadoc)
	 * @see com.xfs.user.service.impl.SysUserTokenService#checkTokenIsValid(java.lang.String, java.lang.String)
	 */
	@Override
	public Result checkTokenIsValid(String token, String curSys) {
		Result r = Result.createResult();
		String key = IRedisKeys.COMMON_XFS_USER_TOKEN + token;
		SysUserToken uToken = (SysUserToken) RedisUtil.get(key);
		if (null == uToken) {
			r.setSuccess(false);
			r.setError("令牌未找到，或已过期");
			return r;
		}
		if (StringUtils.isBlank(curSys))
			throw new RuntimeException("需要提供当前系统标识curSys");
		if (!curSys.equalsIgnoreCase(uToken.getToSys())) {
			r.setSuccess(false);
			r.setError("令牌未找到，或已过期");
			return r;
		}
		if (uToken.getIsOnce())
			RedisUtil.delete(key);
		r.setDataValue("userId", uToken.getUserId());
		r.setDataValue("token", uToken);
		return r;
	}

    /**
     * 存入用户的权限列表
     * @param user
     */
    private void setAuthButton(SysUser user) {
		String key = "auth:" + user.getRoleIds();
        RedisUtil.delete(key);
        List<Map> list = sysUserDao.queryFunctionCrudByroleIds(user.getRoleIds(),user.getOrgId());
        RedisUtil.set(key, list, 0);
    }

	public List<SysUser> findUserAuth(Integer cpId){
		return sysUserDao.findUserAuth(cpId);
	}

	/**
	 *  根据友互通返回的用户ID获取用户信息
	 *  @param   sysUser
	 *	@return 			: com.xfs.user.model.SysUser
	 *  @createDate  	: 2017-05-11 16:45
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-11 16:45
	 *  @updateAuthor  :
	 */
	public SysUser findUserByYHTUserId(SysUser sysUser){
		return sysUserDao.findUserByYHTUserId(sysUser);
	}

	/**
	 *  根据ACCESS_TOKEN返回的用户ID获取用户信息
	 *  @param   sysUser
	 *	@return 			: com.xfs.user.model.SysUser
	 *  @createDate  	: 2017-05-11 16:45
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-11 16:45
	 *  @updateAuthor  :
	 */
	public SysUser findUserByAccessToken(SysUser sysUser){
		return sysUserDao.findUserByAccessToken(sysUser);
	}


	/**
	 *  更新最后登录时间
	 *  @param   cti, obj
	 *	@return 			: int
	 *  @createDate  	: 2017-05-11 16:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-11 16:52
	 *  @updateAuthor  :
	 */
	public int updateByLastLoginDt(ContextInfo cti, SysUser obj){
		return sysUserDao.updateByLastLoginDt(cti,obj);
	}

	/**
	 *  查询所有未同步的用户信息
	 *  @param
	 *	@return 			: java.util.List<com.xfs.user.model.SysUser>
	 *  @createDate  	: 2017-05-22 15:14
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-22 15:14
	 *  @updateAuthor  :
	 */
	public List<SysUser> queryAllUnSynList(Integer userId,String mobile){
		return sysUserDao.queryAllUnSynList(userId,mobile);
	}

	/**
	 *  设置用户Session 信息
	 *  @param   request, cmCorp, sysUser
	 *	@return 			: void
	 *  @createDate  	: 2017-06-19 11:20
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-06-19 11:20
	 *  @updateAuthor  :
	 */
	public void setUserSession(HttpServletRequest request, CmCorp cmCorp, SysUser sysUser){
		setUserSession(request,cmCorp,sysUser,null);
	}

	/**
	 *  设置用户Session 信息
	 *  @param   request, cmCorp, sysUser
	 *	@return 			: void
	 *  @createDate  	: 2017-06-19 11:20
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-06-19 11:20
	 *  @updateAuthor  :
	 */
	public void setUserSession(HttpServletRequest request, CmCorp cmCorp, SysUser sysUser,String productCodes){
		SessionUtil.setUserCorp(request.getSession(), cmCorp);
		String authority = sysFunctionDataService.getDataAuthorityByUser(sysUser.getUserId(), sysUser.getUserType(), Constant.DATATYPE);
		if(StringUtils.isNotBlank(authority)) {
			sysUser.setAuthority(authority);
		}
		SessionUtil.setContextInfo(request.getSession(), sysUser);
		if(StringUtils.isNotBlank(sysUser.getRoleIds()))
			SessionUtil.setMenu(request.getSession(), new SessionMenu(getMenus(sysUser)));
	}

	public List<SysUser> findUsersBySpIdAndName(SysUser sysUser){
		return sysUserDao.findUsersBySpIdAndName(sysUser);
	}

	public SysUser findUsersByUserId(Integer userId, Integer orgId){
		SysUser vo = new SysUser();
		vo.setUserId(userId);
		vo.setOrgId(orgId);
		return sysUserDao.findUsersByUserId(vo);

	}

	public Integer countSysUser(SysUser sysUser){
		Integer pm = sysUserDao.countFreeFind(sysUser);
		return pm;
	}

	public Integer CountSysUserByRoleId(SysUser sysUser){
		Integer pm = sysUserDao.CountSysUserByRoleId(sysUser);
		return pm;
	}

	public boolean checkSysUserSet(SysUser sysUser){

		sysUser.setRoleId(799);

		SysUser vo = findUsersByUserId(sysUser.getUserId(), sysUser.getOrgId());
		if(vo!= null && 799==vo.getRoleId().intValue()){//查看当前用户是否为超管，如果是则继续查询 超管个数；否则直接"过"
			sysUser.setUserId(null);
			return CountSysUserByRoleId(sysUser) > 1;
		}

		return true;
	}

	public void deleteRole(ContextInfo cti, Integer userId){
		//删除用户所有数据权限
		sysFunctionDataService.deleteByUserId(cti, userId);
		sysUserRoleDao.deleteByUserId(cti, userId);
	}

	public void deleteUserAndRole(ContextInfo cti, Integer userId){
		deleteRole(cti, userId);
		SysUser vo = new SysUser();
		vo.setUserId(userId);
		sysUserDao.remove(cti, vo);
	}

	public int checkUserCount(SysUser sysUser){
		return sysUserDao.checkUserCount(sysUser);
	}
}
