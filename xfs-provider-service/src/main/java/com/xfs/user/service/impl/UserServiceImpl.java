package com.xfs.user.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.user.mongo.UserOperationLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.email.util.MailUtil;
import com.xfs.common.email.vo.Email;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private SysUserDao userDAO;

	@Autowired
	private SysUserRoleDao userRoleDao;


    @Autowired
    private MongoDao mongoDao;

	public List<SysUser> freeFind(SysUser user) {
		return userDAO.freeFind(user);
	}

	/**
	 *  分页查询管理员
	 *  @param user   SysUser对象
	 *  @return 		:PageModel
	 *  @createDate  	: 2016年11月15日 下午5:51:35
	 *  @author         : 
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:51:35
	 *  @updateAuthor  	:
	 */
	public PageModel getAdminUserList(PageInfo pi, SysUser user) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = userDAO.countFreeFind(user);
		pm.setTotal(total);
		List<SysUser> datas = userDAO.freeFind(user, pageIndex, pageSize);

		for (SysUser sysUser : datas) {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			List<SysUserRole> list = userRoleDao.freeFind(userRole);
			if (list != null && list.size() > 0) {
				String roleNmae = "";
				for (SysUserRole role : list) {
					roleNmae += role.getRoleName() + ",";
				}
				sysUser.setRoleNames(roleNmae.substring(0, roleNmae.length() - 1));
			}
		}

		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  查询所有用户
	 *  @return         : Map<String, SysUser>
	 *  @createDate  	: 2016年11月15日 下午2:35:19
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午2:35:19
	 *  @updateAuthor  	:
	 */
	public Map<String, SysUser> getAllUserMap() {
		HashMap<String, SysUser> map = new HashMap();
		SysUser user = new SysUser();
		List<SysUser> datas = userDAO.freeFind(user);
		DecimalFormat df = new DecimalFormat("#");
		for (SysUser u : datas) {
			map.put(df.format(u.getUserId()), u);
		}
		return map;
	}

	/**
	 *  删除用户
	 *  @param vo    SysUser对象
	 *  @createDate  	: 2016年11月15日 下午5:52:48
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:52:48
	 *  @updateAuthor  	:
	 */
	public void deleteUser(ContextInfo cti, SysUser vo) {
		userDAO.remove(cti, vo);

	}

	/**
	 *  查询用户
	 *  @param vo    SysUser对象
	 *  @return 		:SysUser
	 *  @createDate  	: 2016年11月15日 下午5:53:36
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:53:36
	 *  @updateAuthor  	:
	 */
	public SysUser findByPK(SysUser vo) {
		return userDAO.findByPK(vo);

	}

	/**
	 * 	更新用户
	 *  @param vo    SysUser对象
	 *  @createDate  	: 2016年11月15日 下午5:54:30
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:54:30
	 *  @updateAuthor  	:
	 */
	public int updateUser(ContextInfo cti, SysUser vo) {
		return userDAO.update(cti, vo);
	}

	/**
	 *  新增用户
	 *  @param vo     SysUser对象
	 *  @createDate  	: 2016年11月15日 下午5:55:36
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 下午5:55:36
	 *  @updateAuthor  	:
	 */
	public void insert(ContextInfo cti, SysUser vo) {
		userDAO.insert(cti, vo);
		if (vo.getRoleIds() != null) {
			String rolesId = vo.getRoleIds();
			String roldID[] = rolesId.split(",");
			for (int i = 0; i < roldID.length; i++) {
				if (!StringUtils.isBlank(roldID[i])) {
					int roleId = Integer.valueOf(roldID[i]);
					SysUserRole userRole = new SysUserRole();
					userRole.setUserId(vo.getUserId());
					userRole.setRoleId(roleId);
					userRoleDao.insert(cti, userRole);
				}
			}
		}

	}

	/**
	 * 更新用户最后一次登陆时间
	 * 
	 * @param userId 用户id
	 * @createDate : 2016年11月15日 下午12:12:49
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午12:12:49
	 * @updateAuthor :
	 */
	public void updateLastLogin(ContextInfo cti, Integer userId) {
		SysUser user = new SysUser();
		user.setUserId(userId);
		user.setLastLoginDt(Calendar.getInstance().getTime());
		try {
			userDAO.update(cti, user);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 登录
	 * 
	 * @param user SysUser对象
	 * @return
	 * @createDate : 2016年11月15日 下午2:16:38
	 * @author :
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:16:38
	 * @updateAuthor :
	 */
	public SysUser login(SysUser user) {
		user.setPassword(StringUtils.md5(user.getPassword()));
		List<SysUser> list = userDAO.freeFind(user);
		if (list != null && list.size() > 0) {
			return (SysUser) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 通过email查询用户
	 * 
	 * @param email  邮箱
	 * @return
	 * @createDate : 2016年11月15日 下午2:17:01
	 * @author :
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:17:01
	 * @updateAuthor :
	 */
	public SysUser getUserByEmail(String email) {
		SysUser user = new SysUser();
		user.setEmail(email);
		List<SysUser> list = userDAO.freeFind(user);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 通过用户姓名查询用户
	 * 
	 * @param name  用户名
	 * @return
	 * @createDate : 2016年11月15日 下午2:18:02
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:18:02
	 * @updateAuthor :
	 */
	public SysUser getUserByUserName(String name) {
		SysUser user = new SysUser();
		user.setUserNameEq(name);
		List<SysUser> list = userDAO.freeFind(user, 0, 1);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 通过用户名检查用户是否存在
	 * 
	 * @param name  用户名
	 * @return
	 * @createDate : 2016年11月15日 下午2:18:48
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:18:48
	 * @updateAuthor :
	 */
	public boolean checkUserNameExistance(String name) {
		SysUser user = new SysUser();
		user.setUserNameEq(name);
		int count = userDAO.countFreeFind(user);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 通过mobile检查用户是否存在
	 * 
	 * @param mobile  电话
	 * @return
	 * @createDate : 2016年11月15日 下午2:19:39
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:19:39
	 * @updateAuthor :
	 */
	public boolean checkUserMobileExistance(String mobile) {
		SysUser user = new SysUser();
		user.setMobileEq(mobile);
		int count = userDAO.countFreeFind(user);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 得到与入参user统一机构的所有用户id，拼成sql in查询部分格式
	 * 
	 * @param user   SysUser对象
	 * @return
	 * @createDate : 2016年11月15日 下午12:13:05
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午12:13:05
	 * @updateAuthor :
	 */
	public String getUserIdStrByOrgId(SysUser user) {
		if (user == null)
			return null;
		if (user.getOrgId() == null)
			return user.getUserId() + "";
		SysUser vo = new SysUser();
		vo.setOrgId(user.getOrgId());
		List<SysUser> list = userDAO.freeFind(vo);
		List idList = new ArrayList();
		if (list.size() > 0) {
			for (SysUser u : list) {
				idList.add(u.getUserId());
			}
		}
		return StringUtils.convertInInt(idList);
	}

	/**
	 * 根据各字段条件带模糊查询 用户表
	 * 
	 * @param user  SysUser对象
	 * @return
	 * @createDate : 2016年11月15日 下午2:21:54
	 * @author :
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:21:54
	 * @updateAuthor :
	 */
	public String getUserIdStr(SysUser user) {
		if (user == null)
			return null;
		List<SysUser> list = userDAO.freeFind(user);
		List idList = new ArrayList();
		if (list.size() > 0) {
			for (SysUser u : list) {
				idList.add(u.getUserId());
			}
		}
		return StringUtils.convertInInt(idList);
	}

	/**
	 * 根据各字段条件带模糊查询 用户表 记录数
	 * 
	 * @param sysUser  sysUser对象
	 * @return
	 * @createDate : 2016年11月15日 下午2:23:16
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:23:16
	 * @updateAuthor :
	 */
	public int countFreeFind(SysUser sysUser) {
		return userDAO.countFreeFind(sysUser);
	}

	/**
	 * 重置密码
	 * 
	 * @param sysUser  sysUser对象
	 * @createDate : 2016年11月15日 下午2:24:25
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:24:25
	 * @updateAuthor :
	 */
	public void resetPassword(ContextInfo cti, SysUser sysUser) {

		// 判断信息是否匹配
		if (countFreeFind(sysUser) == 0) {
			throw new BusinessException("用户名邮箱匹配不正确!");
		}

		// 重置密码
		String newPassword = StringUtils.getRandomSixString();
		sysUser.setPassword(StringUtils.md5(newPassword));
		userDAO.save(cti, sysUser);
		Email email = new Email();
		List<String> toAddress = new ArrayList<String>();
		toAddress.add(sysUser.getEmail());
		email.setToAddress(toAddress);
		email.setSubject("重置密码");
		email.setContent("重置后密码为：" + newPassword);
		try {
			MailUtil.sendMail(email);
		} catch (Exception e) {
			throw new BusinessException("邮件发送失败！");
		}
	}

	/**
	 * 重置密码
	 * @param sysUser sysUser对象
	 * @return
	 * @return : String
	 * @createDate : 2016年11月15日 下午2:25:16
	 * @author : NDD
	 * @version : v1.0
	 * @updateDate : 2016年11月15日 下午2:25:16
	 * @updateAuthor :
	 */
	private String resetPassword1(ContextInfo cti, SysUser sysUser) {
		// 重置密码
		String newPassword = StringUtils.getRandomSixString();
		sysUser.setPassword(StringUtils.md5(newPassword));
		userDAO.save(cti, sysUser);
		return newPassword;
	}

	/**
	 * 发送短信
	 *  @param sysUser  sysUser对象
	 *  @param content  发送内容
	 *  @createDate  	: 2016年11月15日 下午2:26:57
	 *  @author         	: NDD
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月15日 下午2:26:57
	 *  @updateAuthor  	:
	 */
	@Override
	public void activeUserWithSms(ContextInfo cti, SysUser sysUser, String content) {
		String newPassword = resetPassword1(cti, sysUser);
		try {
			content = String.format(content, newPassword);
			boolean smsresult = SmsUtil.sendSms(sysUser.getMobile(), content);
			if (!smsresult)
				throw new BusinessException("短信发送失败!请检查配置文件是否正确!");
		} catch (Exception e) {
			String errorMessage = "短信发送失败!" + e.getMessage();
			log.error(errorMessage, e);
			throw new BusinessException(errorMessage);
		}
	}

	/**
	 * 模糊查询sysuser表
	 *  @param sysUser  sysUser对象
	 *  @return 
	 *  @createDate  	: 2016年11月15日 下午2:29:20
	 *  @author         	: NDD
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月15日 下午2:29:20
	 *  @updateAuthor  	:
	 */
	@Override
	public SysUser findByOrgId(SysUser sysUser) {
		List<SysUser> list = userDAO.freeFind(sysUser);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}


    public PageModel getUserOperation(PageInfo info,String userId){
        PageModel pm = new PageModel(info);
        String query = "{";
        query +=   "'user.userId':"+userId +"}";
        Long count = mongoDao.count("userOperationLog",query);
        pm.setTotal(count.intValue());
        List data = mongoDao.query("userOperationLog",query, (info.getOffset()/info.getPagesize())+1, info.getPagesize(),"{reqTime:-1}");
        pm.setDatas(data);
        return pm;
    }

}
