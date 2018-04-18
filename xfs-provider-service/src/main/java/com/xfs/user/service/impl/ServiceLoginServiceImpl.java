package com.xfs.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.util.Constant;
import com.xfs.common.util.MobileValidator;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.ServiceLoginService;

/**
 * @Description:
 * @author: Administrator
 * @Date: 2016-01-18 19:08
 *
 */
@Service
public class ServiceLoginServiceImpl implements ServiceLoginService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

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
	@Override
	public Boolean savePassWord(ContextInfo cti, String mobile, String password) {
		sysUserDao.updateByMobile(cti, mobile, password);
		return true;
	}

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
	@Override
	public SysUser loginIn(String mobile, String password) {
		//无用暂时先注释掉 zxy 2017-01-09
//		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//		mobile = mobile.replaceAll(" ", "").trim();
//		Pattern regex = Pattern.compile(check);
//		Matcher matcher = regex.matcher(mobile.replaceAll(" ", "").trim());
		SysUser sysUser = new SysUser();
		Date date = new Date();
		if (MobileValidator.isMobileNO(mobile)) {
			sysUser.setMobile(mobile);
		}else{
			return null;
		}
		/*if (matcher.matches()) {
			sysUser.setEmail(mobile);
		}*/
		sysUser.setPassword(password);
		sysUser.setEnabled(1);// 审核类型 1 已审核 0待审核
		sysUser.setUserType(Constant.U_TYPE_SERVICE);// 服务商类型
		sysUser = sysUserDao.FindByLoginNamePass(sysUser);
		if (sysUser == null) {
			return null;
		} else {
			sysUser.setLastLoginDt(date);
			Integer upds = sysUserDao.updateByLastLoginDt(null, sysUser);
			if (upds <= 0) {
				return null;
			}
			//登录薪福邦入口校验
			if(null != sysUser.getRoleIds() && !StringUtils.equals("", sysUser.getRoleIds())){
				boolean bol = Arrays.asList(sysUser.getRoleIds().split(",")).contains("9");
				if(bol){
					sysUser.setLoginXfb("0");
				}
			}
		}
		return sysUser;
	}

    /** 找回密码
     *  @param    : user 用户
     * @return    :Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Result findPassByPassWord(ContextInfo cti, SysUser user) {
		Result result = Result.createResult().setSuccess(false);
		if (user.getNewpassword().equals(user.getRepass())) {
			Integer ints = sysUserDao.findPassByPassWord(user);
			if (ints > 0) {
				user.setModifyDt(new Date());
				Integer upds = sysUserDao.updateByNewPass(cti, user);
				if (upds > 0) {
					result.setSuccess(true);
				} else {
					result.setSuccess(false);
				}
			} else {
				result.setSuccess(false);
			}
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	/** 新人指导--根据 userId 查询当前用户所属角色
     *  @param    : Integer userId
     * @return    :Result
     *  @createDate   : 16-12-12 下午17:12
     *  @author          : zhangxiyan@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-12-12 下午17:12
     *  @updateAuthor  :
     */
	@Override
	public Result findNewGuidance(Integer userId){
		Result result = Result.createResult().setSuccess(false);
		//根据用户ID去查询当前用户是否完成新手指导操作
		Integer ints = sysUserDao.countNewGuidance(userId);
		if(0<ints){
			result.setSuccess(false).setError("已经完成新手指导操作！");
		}else{
			//根据UserId查询 角色 集合
			List<SysUserRole>  roleLists = sysUserRoleDao.findByUserId(userId);
			if(null !=roleLists && 0<roleLists.size()){
				List<Integer> roleList = new ArrayList<Integer>();
				Map<Integer,Integer> dataMap = new HashMap<>();
				for (SysUserRole sysUserRole : roleLists) {
					//系统管理员
					if(3==sysUserRole.getRoleId())
						dataMap.put(0,0);
					//客服人员 || 实作专员
					if(4==sysUserRole.getRoleId() || 20==sysUserRole.getRoleId()){
						dataMap.put(0,0);
						dataMap.put(1,1);
						dataMap.put(2,2);
						dataMap.put(3,3);
						dataMap.put(4,4);
					}
					//财务会计
					if(6==sysUserRole.getRoleId())
						dataMap.put(4,4);
				}
				for (Map.Entry<Integer, Integer> entry: dataMap.entrySet()) {
					roleList.add(entry.getKey());
				}
				result.setSuccess(true).setDataValue("roleList", roleList);
				//返回成功之后 更新-新手指导操作表
				sysUserDao.insetNewGuidance(userId);
			}else{
				result.setSuccess(false).setError("已经完成新手指导操作！");
			}
		}
		return result;
	}
}
