package com.xfs.cp.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpOrder;
import com.xfs.cp.model.CpOrderLog;

/**
 * CpOrderLogService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
public interface CpOrderLogService {
	
	public int save(ContextInfo cti, CpOrderLog vo);
	public int insert(ContextInfo cti, CpOrderLog vo);
	public int remove(ContextInfo cti, CpOrderLog vo);
	public int update(ContextInfo cti, CpOrderLog vo);
	public PageModel findPage(PageInfo pi, CpOrderLog vo);
	public List<CpOrderLog> findAll(CpOrderLog vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	public void saveLog(ContextInfo cti, Integer status, String orderId, Integer userId, Integer stage);

	public List<CpOrderLog> findCurrStageLogByOrderPks(List<Integer> ids) ;

	public List<CpOrderLog> findStep4NeedPayOrder() ;
	/**
	 * 获取LOG
	 *  @param logVo
	 *  @return 
	 *	@return			: CpOrderLog 
	 *  @createDate		: 2016年11月14日 下午8:14:17
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月14日 下午8:14:17
	 *  @updateAuthor  	:
	 */
	public CpOrderLog findOneCpOrderLog(CpOrderLog logVo);
	/**
	 * 根据订单ID和状态更新LOG日志
	 *  @param cpOrderLog 
	 *	@return			: void 
	 *  @createDate		: 2016年11月14日 下午9:14:43
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月14日 下午9:14:43
	 *  @updateAuthor  	:
	 */
	public int updateByCpOrderLogId(ContextInfo cti, CpOrderLog cpOrderLog);
	
	/**
	 * SAAS保存数据和校验
	 *  @param sysUser
	 *  @param order
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2016年12月27日 下午6:35:53
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 * @throws Exception 
	 *  @updateDate    	: 2016年12月27日 下午6:35:53
	 *  @updateAuthor  :
	 */
	public Result userToSAAS(ContextInfo sysUser, CpOrder order) throws Exception;

}
