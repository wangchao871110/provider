package com.xfs.wxclassroom.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dto.WxUser;

/**
 * WxUserService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/01 18:03:02
 */
public interface WxUserService {
	
	public int save(ContextInfo cti, WxUser vo);
	public int insert(ContextInfo cti, WxUser vo);
	public int remove(ContextInfo cti, WxUser vo);
	public int update(ContextInfo cti, WxUser vo);
	public PageModel findPage(PageInfo pi, WxUser vo);
	public List<WxUser> findAll(WxUser vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/01 18:03:02

	/**
	 * 根据主键ID查询用户
	 * @param obj WxUser
	 * @return WxUser
     */

	public WxUser findByPK(WxUser obj) ;
	
}
