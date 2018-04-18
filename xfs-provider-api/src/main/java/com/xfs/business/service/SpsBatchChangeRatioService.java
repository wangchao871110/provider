package com.xfs.business.service;

import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.business.model.SpsBatchChangeRatio;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * SpsBatchChangeRatioService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/15 16:30:31
 */
public interface SpsBatchChangeRatioService {
	
	public int save(ContextInfo info, SpsBatchChangeRatio vo);
	public int insert(ContextInfo info,SpsBatchChangeRatio vo);
	public int remove(ContextInfo info,SpsBatchChangeRatio vo);
	public int update(ContextInfo info,SpsBatchChangeRatio vo);
	public PageModel findPage(PageInfo info, SpsBatchChangeRatio vo);
	public List<SpsBatchChangeRatio> findAll(SpsBatchChangeRatio vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/15 16:30:31


	/**
	 * 处理批量更新人员比例
	 *  @param   ratioEmpQuery 查询条件, user 登陆用户
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 16:53 2016/11/16
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 16:53 2016/11/16
	 *  @updateAuthor  :
	 */
	public Result handlleBatchRatio(ContextInfo info,BatchRatioEmpQuery ratioEmpQuery);

	/**
	 * 业务类分页查询
	 *  @param   info, vo]
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 11:32 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 11:32 2016/11/17
	 *  @updateAuthor  :
	 */
	public PageModel findVoPage(ContextInfo cti,PageInfo info, SpsBatchChangeRatio vo);
	/**
	 * 通过批处理id查询处理人员信息
	 *  @param   bcrId 批次id
	 * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate   : 15:16 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 15:16 2016/11/17
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findChangeEmplDetail(Integer bcrId);
}
