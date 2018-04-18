package com.xfs.user.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysFunctionCrud;

/**
 * SysFunctionCrudService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:22
 */
public interface SysFunctionCrudService {
	
	public int save(ContextInfo cti, SysFunctionCrud vo);
	public int insert(ContextInfo cti, SysFunctionCrud vo);
	public int remove(ContextInfo cti, SysFunctionCrud vo);
	public int update(ContextInfo cti, SysFunctionCrud vo);
	public PageModel findPage(PageInfo pi, SysFunctionCrud vo);
	public List<SysFunctionCrud> findAll(SysFunctionCrud vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:22

    public SysFunctionCrud findByPK(SysFunctionCrud obj);

    /** 根据URL获取受控角色
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	public String queryRoleIdsByUrl(String currUrl);

    /** 根据URL获取受控角色（Cs）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    public String queryRoleIdsByUrlWithCs(ContextInfo cti,String currUrl);

    /**
     *  根据角色查询无权限访问按钮
     *  @param   roleIds
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-06-19 18:10
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-19 18:10
     *  @updateAuthor  :
     */
    public List<SysFunctionCrud> queryNoAuthUrlByRoleIds(String roleIds);


    /** 根据URL获取受控角色（Bs）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    public String queryRoleIdsByUrlWithBs(String currUrl);


    /** 根据URL获取受控角色（Cooperation）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    public String queryRoleIdsByUrlWithCoop(String currUrl);
	
}
