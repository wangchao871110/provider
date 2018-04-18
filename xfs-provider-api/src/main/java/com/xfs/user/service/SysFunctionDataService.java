package com.xfs.user.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysFunctionData;

/**å
 * SysFunctionDataService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 10:46:32
 */
public interface SysFunctionDataService {

	public int save(ContextInfo cti, SysFunctionData vo);
	public int insert(ContextInfo cti, SysFunctionData vo);
	public int remove(ContextInfo cti, SysFunctionData vo);
	public int update(ContextInfo cti, SysFunctionData vo);
	public PageModel findPage(PageInfo pi, SysFunctionData vo);
	public List<SysFunctionData> findAll(SysFunctionData vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 10:46:32


    /** 获取用户数据权限
     *  @param    : userId  用户ID
     *  @param   ：userType 用户类型
     *  @param   ：tableName 数据库表名称
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	public String getDataAuthorityByUser(Integer userId, String userType, String tableName);

	/**
	 * 删除数据权限
	 * @param cti
	 * @param userId
	 */
	public int deleteByUserId(ContextInfo cti, Integer userId);

}
