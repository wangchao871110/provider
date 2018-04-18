package com.xfs.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.cp.dto.UserDto;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysFunctionCategory;
import com.xfs.user.model.SysUser;

/**
 * SysUserService
 * @author:quanjiahua<quanjiahua@163.com>
 *
 * @date:2016/01/21 11:03:16
 */
public interface SysUserService {

	public int save(ContextInfo cti, SysUser vo);

	public int insert(ContextInfo cti, SysUser vo);

	public int remove(ContextInfo cti, SysUser vo);

	public int update(ContextInfo cti, SysUser vo);

	public PageModel findPage(PageInfo pi, SysUser vo);

	public List<SysUser> findAll(SysUser vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/01/21 11:03:16

    /**
     *  增加或更改
     *  @param    : vo  用户实体
     *  @param    : cmCorpAll  是否所有企业权限
     *  @param    : cmCorp  企业id数组
     *  @return    :  Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Result addOrEditWithDataFunc(ContextInfo cti, SysUser vo, String cmCorpAll, Integer[] cmCorp);

    /**
     *  更改用户状态
     *  @param    : sys  用户实体
     *  @return    :  Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Result updateUserState(ContextInfo cti, SysUser sys);

    /**
     *  重置密码
     *  @param    : sys  用户实体
     *  @return    :  Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Result repassWord(ContextInfo cti, SysUser sys);

    /**
     *  根据用户id查找用户
     *  @param    : vo  用户实体
     *  @return    :  SysUser
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public SysUser findByUserIdEq(SysUser vo);

    /**
     *  获取菜单
     *  @param    : user  用户实体
     *  @param    : sys_code  区分不同项目的code
     *  @return    :  Map<SysFunctionCategory, List<SysFunction>>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	Map<SysFunctionCategory, List<SysFunction>> getMenus(SysUser user);
    /**
     *  获取菜单
     *  @param    : userId  用户主键
     *  @param    : sys_code  区分不同项目的code
     *  @return    :  Map<SysFunctionCategory, List<SysFunction>>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	Map<SysFunctionCategory, List<SysFunction>> getMenus(Integer userId);

	List<SysFunctionCategory> getCategory(SysUser user);

	SysUser findByMobile(String mobile, String usertype, Integer orgid);

	/**
     * 根据主键查询
     *
     * @author lifq
     *
     * 2016年6月15日  下午2:23:37
     */
    SysUser findByPK(SysUser obj);

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
	boolean existMobile(String mobile, String usertype);

    /**
     *  根据用户角色获取功能菜单
     *  @param    : roleIds  角色ids
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	Map<SysFunctionCategory, List<SysFunction>> queryUserRoleFuncList(SysUser user);

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
    SysUser loginCorp(String mobile, String password);

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
    List<SysUser> findByMobile(SysUser vo);

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
    Result editUserPass(ContextInfo cti, SysUser vo);

    /**
     * 分页查询账号管理的条件查询
     * @param
     * @return
     */
    public PageModel accountManage(PageInfo pi, SysUser sysUser);

    public void updateState(ContextInfo cti, SysUser sysUser);

    public SysUser findManager(SysUser sysUser);

    public Integer findCustomer(Map map);

    public Integer updateManager(ContextInfo cti, Map map);

    public Result resetPwd(ContextInfo cti, Map map);

    public int findMobile(Map map);

    public Result keep(ContextInfo cti, SysUser vo);

    public Integer addMobile(ContextInfo cti, Map map);

    public Result updateLinkman(ContextInfo cti, Map map);

    public Integer findPwd(Map map);

    public Integer updatePwd(ContextInfo cti, Map map);

    public Integer findByEmail(SysUser sysUser);

    /**
     *
     * @method comments: 根据手机号获取用户信息
     * @author   name  : wangchao
     * @create   time  : 2016年9月2日 下午4:29:08
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月2日 下午4:29:08 wangchao 创建
     *
     */
    public SysUser findUserByMobile(UserDto userDto);

    /**
     *
     * @method comments: 获取验证码
     * @author   name  : wangchao
     * @create   time  : 2016年9月2日 下午4:28:50
     * @param userDto
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月2日 下午4:28:50 wangchao 创建
     *
     */
    public Result getCode(UserDto userDto);

    /**
     *
     * @method comments: 找回密码获取验证码
     * @author   name  : wangchao
     * @create   time  : 2016年9月6日 下午7:38:03
     * @param userDto
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月6日 下午7:38:03 wangchao 创建
     *
     */
    public Result sendCode(UserDto userDto,Integer type);

    /**
     *
     * @method comments: 重置密码
     * @author   name  : wangchao
     * @create   time  : 2016年9月6日 下午7:53:26
     * @param userDto
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月6日 下午7:53:26 wangchao 创建
     *
     */
    public Result resetUserPass(HttpServletRequest request, UserDto userDto);


    public Result note(UserDto userDto);

    /**
     *
     * @method comments: 手机登录验证码
     * @author   name  : wangchao
     * @create   time  : 2016年9月23日 下午3:25:40
     * @param userDto
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月23日 下午3:25:40 wangchao 创建
     *
     */
    public Result sendLoginCode(UserDto userDto);

    /**
     * findUsersBySpId
     *
     * @param spId
     * @return
     */
    public List<SysUser> findUsersBySpId(Integer spId);

    SysUser findByPK(Integer pk);

	/*
	 * 根据用户ID创建token，用于单点登录
	 * @param cti 当前系统会话
	 * @param userId 用户id
	 * @param fromSys 创建token的系统 mall saas op cp
	 * @param toSys 应用token的系统
	 * @param expire token过期时间
	 * @param isOnce 一次有效,用后删除
	 */
	String createTokenWithUserId(IContextInfo cti, Integer userId, String fromSys, String toSys, Integer expire,
			boolean isOnce, boolean isForceLogOut);

	/*
	 * 验证令牌是否有效
	 * @param token 接收到的令牌
	 * @param curSys 接收令牌的系统标识  mall saas op cp
	 */
	Result checkTokenIsValid(String token, String curSys);

	void initUserDataFunc(ContextInfo cti, SysUser vo, String cmCorpAll, Integer[] cmCorp);

	Result addOrEdit(ContextInfo cti, SysUser vo);


    public List<SysUser> findUserAuth(Integer cpId);


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
    public SysUser findUserByYHTUserId(SysUser sysUser);


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
    public SysUser findUserByAccessToken(SysUser sysUser);

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
    public int updateByLastLoginDt(ContextInfo cti, SysUser obj);


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
    public List<SysUser> queryAllUnSynList(Integer userId,String mobile);

    public void setUserSession(HttpServletRequest request, CmCorp cmCorp, SysUser sysUser);

    public void setUserSession(HttpServletRequest request, CmCorp cmCorp, SysUser sysUser,String productCodes);

    public List<SysUser> findUsersBySpIdAndName(SysUser sysUser);

    public SysUser findUsersByUserId(Integer userId, Integer orgId);

    public Integer countSysUser(SysUser sysUser);

    public boolean checkSysUserSet(SysUser sysUser);

    /**
     * 删除角色关联及数据权限
     * @param cti
     * @param userId
     */
    public void deleteRole(ContextInfo cti, Integer userId);

    /**
     * 删除用户、角色关联及数据权限
     * @param cti
     * @param userId
     */
    public void deleteUserAndRole(ContextInfo cti, Integer userId);

    public int checkUserCount(SysUser sysUser);
}
