package com.xfs.bill.service;

import java.util.List;
import java.util.Map;

import com.xfs.bill.dto.EmpDetail;
import com.xfs.bill.model.SpsBillEmp;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 账单员工服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsBillEmpService {
	
	public int save(ContextInfo cti, SpsBillEmp vo);
	public int insert(ContextInfo cti, SpsBillEmp vo);
	public int remove(ContextInfo cti, SpsBillEmp vo);
	public int update(ContextInfo cti, SpsBillEmp vo);
	public PageModel findPage(PageInfo pi, SpsBillEmp vo);
	public List<SpsBillEmp> findAll(SpsBillEmp vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:07

	/**
	 *  获取账单中员工缴纳详情
	 *  @param  spid	服务商ID
	 *  @param	cmid	企业ID
	 *  @param	empid	员工ID
	 *  @param	bill_id	账单ID
	 *  @param	id	账单与员工对应关系ID
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-09 10:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 10:53
	 *  @updateAuthor  :
	 */
	public Map<String,Object> queryEmBillDetail(Integer spid, Integer cpid, Integer empid, Integer bill_id, Integer id);

	/**
	 * 	分页获取账单下的员工列表
	 *  @param  spid 服务商ID
	 *  @param	cpid 企业ID
	 *  @param	bill_id 账单ID
	 *  @param	period 账单月份
	 *  @param	searchWord 搜索条件（员工姓名、员工身份证号）
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 10:56
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 10:56
	 *  @updateAuthor  :
	 */
	public PageModel queryEmpList(PageInfo pi, Integer spid, Integer cpid, Integer bill_id, String period, String searchWord,Integer area_id,Integer scheme_id);



	/**
	 *  获取账单下所有员工列表
	 *  @param  spid	服务商ID
	 *  @param	cmid	企业ID
	 *  @param	bill_id		账单ID
	 *  @param	depute_type 账单对象 HR:总包账单 SP:分包账单
	 *  @param	bill_type	账单类型 PREPAY:应收账单 PAID:实缴账单
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-09 11:02
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:02
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryAllEmpList(Integer spid, Integer cmid, Integer bill_id, String depute_type, String bill_type);

	/**
	 *  获取费用下所有员工列表
	 *  @param   cti, period, searchWord, area_id
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-04-17 14:24
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-04-17 14:24
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryAllEmpListInsDetail(ContextInfo cti,String period,String searchWord,String area_id);

	/**
	 *  获取账单下员工保险详情
	 *  @param   billId 账单ID
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsBillEmp>
	 *  @createDate  	: 2016-11-09 11:12
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:12
	 *  @updateAuthor  :
	 */
	public List<SpsBillEmp> findBillEmpWithDetails(Integer billId);

	/**
	 * 	实缴账单员工列表
	 *  @param  cpId	企业ID
	 *  @param	billId	账单ID
	 *  @param	searchWord	搜索条件（员工姓名或员工身份证号）
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 11:13
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:13
	 *  @updateAuthor  :
	 */
	public PageModel findPaidBillEmps(PageInfo pi, Integer cpId, Integer billId, String searchWord);

	/**
	 * 	实缴账单差额的员工列表
	 *  @param	spId	服务商ID
	 * 	@param	cpId	企业ID
	 * 	@param	billId	实缴账单ID
	 * 	@param	prepay_bill_id	应收账单ID
	 * 	@param	searchWord    搜索条件（员工姓名或员工身份证号）
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 11:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:16
	 *  @updateAuthor  :
	 */
	public PageModel findPaidBillMarginEmps(PageInfo pi, Integer spId, Integer cpId, Integer billId, Integer prepay_bill_id, String searchWord);



	/**
	 *  获取企业HR查看账单员工列表
	 *  @param   billId
	 *	@return 			: java.util.List<com.xfs.bill.dto.EmpDetail>
	 *  @createDate  	: 2017-03-07 15:13
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-07 15:13
	 *  @updateAuthor  :
	 */
	public PageModel findBillHREmpWithDetails(PageInfo pi,Integer cpId, Integer billId, String searchWords);

}
