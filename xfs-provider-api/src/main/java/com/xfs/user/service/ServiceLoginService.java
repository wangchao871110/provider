package com.xfs.user.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.user.model.SysUser;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2016-01-18 19:08
 *
 */
public interface ServiceLoginService {

    /**
     * 修改密码
     *  @param    : mobile 手机号
     *  @param    : password   密码
     * @return    : Boolean
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	Boolean savePassWord(ContextInfo cti, String mobile, String password);

    /** 登录
     *  @param    : mobile 手机号
     *  @param    : password   密码
     * @return    : SysUser
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	SysUser loginIn(String mobile, String password);

    /** 找回密码
     *  @param    : user 用户
     * @return    :Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	Result findPassByPassWord(ContextInfo cti, SysUser user);
	/**
	 * 新人指导
     * @param     : userId
     * @createDate     : 16-12-12 下午17:12
     * @author         : zhangxiyan@xinfushe.com
     * @version        : v1.0
     * @return    :Result
	 */
	public Result findNewGuidance(Integer userId);
}
