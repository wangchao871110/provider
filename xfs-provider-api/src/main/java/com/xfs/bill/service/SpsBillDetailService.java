package com.xfs.bill.service;

import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsBillDetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 账单详情服务
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-08 18:16
 * @version 	: V1.0
 */
public interface SpsBillDetailService {
	
	public int save(ContextInfo cti, SpsBillDetail vo);
	public int insert(ContextInfo cti, SpsBillDetail vo);
	public int remove(ContextInfo cti, SpsBillDetail vo);
	public int update(ContextInfo cti, SpsBillDetail vo);
	public PageModel findPage(PageInfo pi, SpsBillDetail vo);
	public List<SpsBillDetail> findAll(SpsBillDetail vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:02

	/**
	 * 获取员工的社保信息列表
	 *  @param   bill_emp_id  账单对应的员工ID
	 *  @param   in_type 参保类型
	 *  @Param   id 对应关系ID
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-08 20:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-08 20:50
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> queryEmpInsurances(Integer bill_emp_id, String in_type, Integer id,String insType);

	/**
	 * 获取员工的社保信息列表
	 *  @param   bill_emp_id  账单对应的员工ID
	 *  @param   in_type 参保类型
	 *  @Param   id 对应关系ID
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-08 20:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-08 20:50
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> queryEmpInsurances(Integer bill_emp_id, String in_type, Integer id,String insType,String period);

	/**
	 * 获取账单差额详情
	 *  @param  cpId 企业ID
	 *  @param	billId 账单ID
	 *  @param	empId 员工ID
	 *  @param	spId 服务商ID
	 *  @param  insuredMonth 参保月份
	 *  @param  payType 参保类型 MONTH:月缴 PATCH:补缴
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-08 20:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-08 20:50
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findBillMarginDetail(Integer cpId, Integer billId, Integer empId, Integer spId, String insuredMonth, String payType);

}
