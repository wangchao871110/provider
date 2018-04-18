package com.xfs.bill.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

/**
 * 人员单次费用服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsFeeEmponceService {

	public int save(ContextInfo cti, SpsFeeEmponce vo);

	public int insert(ContextInfo cti, SpsFeeEmponce vo);

	public int remove(ContextInfo cti, SpsFeeEmponce vo);

	public int update(ContextInfo cti, SpsFeeEmponce vo);

	public PageModel findPage(PageInfo pi, SpsFeeEmponce vo);

	public List<SpsFeeEmponce> findAll(SpsFeeEmponce vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/08/04 11:35:49

	/**
	 *  获取员工单次费用列表
	 *  @param   emponce	查询参数
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsFeeEmponce>
	 *  @createDate  	: 2016-11-09 14:09
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:09
	 *  @updateAuthor  :
	 */
	public List<SpsFeeEmponce> queryEmpOnceItems(SpsFeeEmponce emponce);

	/**
	 *  移除员工单次费用
	 *  @param   emponce	查询参数
	 *	@return 			: int
	 *  @createDate  	: 2016-11-09 14:10
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:10
	 *  @updateAuthor  :
	 */
	public int delEmpOnceItems(ContextInfo cti, SpsFeeEmponce emponce);

	/**
	 * 	增加员工单次费用
	 *  @param   spId	服务商ID
	 *  @param   emponces 单次费用
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-09 14:10
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:10
	 *  @updateAuthor  :
	 */
	public Result createEmItems(ContextInfo cti, Integer spId, SpsFeeEmponce[] emponces,String payType,String insuredMonth);

	/**
	 *  查询员工单次费用及明细
	 *  @param   spid	服务商ID
	 *  @param   cpid	企业ID
	 *  @param	 period	费用月份
	 *  @param	 empIds	员工IDs
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsFeeEmponce>
	 *  @createDate  	: 2016-11-09 14:12
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:12
	 *  @updateAuthor  :
	 */
	public List<SpsFeeEmponce> queryEmponceWithDetails(Integer spid, Integer cpid, String period, List<Integer> empIds);

	/**
	 *  查询分包账单下的单次费用
	 *  @param   sp_id	服务商ID
	 *  @param 	 p_sp_id	分包商IDs
	 *  @param 	 cp_id		企业ID
	 *  @param 	 period		月份
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-09 14:19
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:19
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryChildBillEmpOnceItems(Integer sp_id, String p_sp_id, Integer cp_id, String period);

	/**
	 *  任务单补缴 终止
	 *  执行 算费 dr = 1操作
	 *  @param   [vo]
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-09 14:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:15
	 *  @updateAuthor  :
	 */
	public Result removeInsFunSupply(ContextInfo cti, SpsFeeEmponce vo);

	/**
	 *  任务单补缴
	 *  @param   spId	服务商ID
	 *  @param 	 taskId 	任务单ID
	 *  @param 	 empId	员工ID
	 *  @param 	 bsTypeId	业务ID
	 *  @param 	 payBase	缴纳基数
	 *  @param 	 empinsurances	保险列表
	 *  @param 	 startDate	开始时间
	 *  @param 	 endDate	结束时间
	 *	@return 			: void
	 *  @createDate  	: 2016-11-09 14:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:15
	 *  @updateAuthor  :
	 */
	void insFunSupply(ContextInfo cti, Integer spId, Integer taskId, Integer empId, Integer bsTypeId, Integer areaId, Integer schemeId, BigDecimal payBase, List<BsRatioCalcInterface> empinsurances, Date startDate, Date endDate);

}
