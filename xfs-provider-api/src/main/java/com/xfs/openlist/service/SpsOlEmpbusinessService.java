package com.xfs.openlist.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.model.SpsOlEmpbusiness;

/**
 * SpsOlEmpbusinessService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/05 20:38:18
 */
public interface SpsOlEmpbusinessService {
	
	public int save(ContextInfo cti, SpsOlEmpbusiness vo);
	public int insert(ContextInfo cti, SpsOlEmpbusiness vo);
	public int remove(ContextInfo cti, SpsOlEmpbusiness vo);
	public int update(ContextInfo cti, SpsOlEmpbusiness vo);
	public PageModel findPage(PageInfo pi, SpsOlEmpbusiness vo);
	public List<SpsOlEmpbusiness> findAll(SpsOlEmpbusiness vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/05 20:38:18

    /**
     * 通过openlist查询相关材料
     *  @param    : vo openlist对象
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public Result addEmpbusiness(ContextInfo cti,SpsOlEmpbusiness vo);
	
}
