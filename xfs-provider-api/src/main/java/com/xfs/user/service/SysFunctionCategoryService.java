package com.xfs.user.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysFunctionCategory;

/**
 * SysFunctionCategoryService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:20
 */
public interface SysFunctionCategoryService {
	
	public int save(ContextInfo cti, SysFunctionCategory vo);
	public int insert(ContextInfo cti, SysFunctionCategory vo);
	public int remove(ContextInfo cti, SysFunctionCategory vo);
	public int update(ContextInfo cti, SysFunctionCategory vo);
	public PageModel findPage(PageInfo pi, SysFunctionCategory vo);
	public List<SysFunctionCategory> findAll(SysFunctionCategory vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:20

    /** 查找类别
     *  @param    : sysCode 系统类别
     * @return    :List<SysFunctionCategory>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
    public List<SysFunctionCategory> findTopMenu(String sysCode);
	
	
}
