package com.xfs.bill.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.serviceBill.dto.ServiceBillCountVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsEmpVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsListVo;
import com.xfs.serviceBill.dto.ServiceBillVo;

/**
 * 企业对账单
 *
 * @author : konglc@xinfushe.com
 * @date : 2016-11-10 14:58
 * @version : V1.0
 */
public interface CorpBillService {

    public int save(ContextInfo cti, SpsBill vo);

    public int insert(ContextInfo cti, SpsBill vo);

    public int remove(ContextInfo cti, SpsBill vo);

    public int update(ContextInfo cti, SpsBill vo);

    public PageModel findPage(PageInfo pi, SpsBill vo);

    public List<SpsBill> findAll(SpsBill vo);

    /**
     * 获取服务商对账列表
     *  @param cti
     *  @param pageInfo
     *  @param vo
     *  @return
     *	@return 			: PageModel
     *  @createDate  	: 2017年7月10日 下午3:13:43
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月10日 下午3:13:43
     *  @updateAuthor  :
     */
    public PageModel findServiceBillList(ContextInfo cti, PageInfo pageInfo, ServiceBillVo vo);

    /**
     * 统计服务商对账数据
     *  @param cti
     *  @param vo
     *  @return
     *	@return 			: ServiceBillCountVo
     *  @createDate  	: 2017年7月12日 上午11:15:17
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月12日 上午11:15:17
     *  @updateAuthor  :
     */
    public ServiceBillCountVo findServiceBillCount(ContextInfo cti, ServiceBillVo vo);

    /**
	 * 根据企业ID、服务商ID和月份获取参保城市
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月14日 下午2:08:09
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月14日 下午2:08:09
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findServiceBillDetailsArea(ContextInfo cti, ServiceBillVo vo);
	
	/**
	 * 获取账单基本信息
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: Map<String,Object> 
	 *  @createDate  	: 2017年7月17日 下午4:29:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月17日 下午4:29:50
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findServiceBillDetailsBasic(ContextInfo cti, ServiceBillVo vo);
	
	/**
	 * 获取服务商对账详情统计
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: ServiceBillCountVo 
	 *  @createDate  	: 2017年7月17日 下午2:24:07
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月17日 下午2:24:07
	 *  @updateAuthor  :
	 */
	public ServiceBillCountVo findServiceBillDetailsCount(ContextInfo cti, ServiceBillVo vo);

	/**
	 * 服务商对账详情异常数据列表
	 *  @param cti
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *	@return 			: List<ServiceBillDetailsListVo> 
	 *  @createDate  	: 2017年7月18日 下午2:50:59
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月18日 下午2:50:59
	 *  @updateAuthor  :
	 */
	public List<ServiceBillDetailsListVo> findServiceBillDetailsErrorList(ContextInfo cti, PageInfo pageInfo,
			ServiceBillVo vo);

	/**
	 * 服务商对账详情数据列表
	 *  @param cti
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *	@return 			: PageModel 
	 *  @createDate  	: 2017年7月18日 下午2:50:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月18日 下午2:50:50
	 *  @updateAuthor  :
	 */
	public PageModel findServiceBillDetailsList(ContextInfo cti, PageInfo pageInfo, ServiceBillVo vo);


	/**
	 * 创建应收账单前检查
	 *
	 * @param bill
	 *            账单信息
	 * @param user_id
	 *            用户ID
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 11:28
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:28
	 * @updateAuthor :
	 */
	public Result checkCreateBill(ContextInfo cti,SpsBill bill, Integer user_id,String filePath,String fileName,Map<String,Object> ext);


	/**
	 * 生成或者重建企业账单(应收和实缴)
	 *
	 * @param bill
	 *            账单信息
	 * @param user_id
	 *            用户ID
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	public Result createCmBill(ContextInfo cti,SpsBill bill, Integer user_id,String filePath,String fileName,Map<String,Object> ext);

	/**
	 * 生成账单Excel
	 * @param cti
	 * @param bill
	 * @param cmName
	 * @param period
	 * @param depute_type
	 * @param bill_type
	 * @return
	 */
	public String generateExcel(ContextInfo cti,SpsBill bill, String cmName, String period, String depute_type, String bill_type);

	/**
	 * 创建应收、实缴账单
	 * @return : SpsBill
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	public Result createBill(ContextInfo cti,SpsBill bill,String filePath,String fileName,Map<String,Object> ext);

	/**
	 * 服务商账单导出接口
	 *  @param request
	 *  @param response
	 *  @param cti
	 *  @param vo
	 *	@return 			: void
	 *  @createDate  	: 2017年7月20日 上午10:05:26
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月20日 上午10:05:26
	 *  @updateAuthor  :
	 */
	public void serviceBillExport(HttpServletRequest request,HttpServletResponse response,ContextInfo cti, ServiceBillVo vo);

	/**
	 * 导出对账结果
	 *  @param request
	 *  @param response
	 *  @param cti
	 *  @param vo
	 *	@return 			: void
	 *  @createDate  	: 2017年7月20日 上午10:18:00
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月20日 上午10:18:00
	 *  @updateAuthor  :
	 */
	public void serviceBillDetailsExport(HttpServletRequest request, HttpServletResponse response, ContextInfo cti,
			ServiceBillVo vo);

	/**
	 * 服务商个人对账详情
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: ServiceBillDetailsEmpVo 
	 *  @createDate  	: 2017年7月27日 下午2:25:44
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月27日 下午2:25:44
	 *  @updateAuthor  :
	 */
	public ServiceBillDetailsEmpVo serviceBillDetailsEmp(ContextInfo cti, ServiceBillVo vo);
}
