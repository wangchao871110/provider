package com.xfs.bill.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.bill.dto.OnceFeeDto;
import com.xfs.bill.dto.PaidBillDto;
import com.xfs.bill.dto.SpsBillDto;
import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.serviceBill.dto.ServiceBillVo;

/**
 * 账单服务类
 * 
 * @author : konglc@xinfushe.com
 * @date : 2016-11-10 14:58
 * @version : V1.0
 */
public interface SpsBillService {

	public int save(ContextInfo cti, SpsBill vo);

	public int insert(ContextInfo cti, SpsBill vo);

	public int remove(ContextInfo cti, SpsBill vo);

	public int update(ContextInfo cti, SpsBill vo);

	public PageModel findPage(PageInfo pi, SpsBill vo);

	public List<SpsBill> findAll(SpsBill vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/16 15:32:00

	/**
	 *  获取账单详情
	 *  @param   [vo]
	 *	@return 			: com.xfs.bill.model.SpsBill
	 *  @createDate  	: 2017-01-13 13:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-13 13:50
	 *  @updateAuthor  :
	 */
	public SpsBill queryBillDetail(SpsBill vo);

	/**
	 * 获取账单总数量
	 * 
	 * @param cmName
	 *            企业名称
	 * @param bill
	 *            账单信息
	 * @return : java.lang.Integer
	 * @createDate : 2016-11-10 11:23
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:23
	 * @updateAuthor :
	 */
	public Integer queryBillListCount(String cmName, SpsBill bill, ContextInfo cti);

	/**
	 * 分页获取账单列表
	 * 
	 * @param cmName
	 *            企业名称
	 * @param bill
	 *            账单信息
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 11:22
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:22
	 * @updateAuthor :
	 */
	public PageModel queryBillList(PageInfo pi, String cmName, SpsBill bill, ContextInfo cti);

	/**
	 * 获取企业账单详情信息
	 * 
	 * @param spid
	 *            服务商ID
	 * @param cmid
	 *            企业ID
	 * @param bill_id
	 *            账单ID
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 11:25
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:25
	 * @updateAuthor :
	 */
	public Map<String, Object> queryCmBillDetail(Integer spid, Integer cmid, Integer bill_id);

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
	public Result checkCreateBill(SpsBill bill, Integer user_id);

	/**
	 * 创建实缴前账单检查
	 * 
	 * @param bill
	 *            账单信息
	 * @param user_id
	 *            用户ID
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 11:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:29
	 * @updateAuthor :
	 */
	public Result checkCreatePaidBill(SpsBill bill, Integer user_id);

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
	public Result createCmBill(SpsBill bill, Integer user_id);

	/**
	 * 创建应收、实缴账单
	 * 
	 * @param billType
	 *            PREPAY应收 PAID实缴
	 * @param period
	 *            账期
	 * @param preBillId
	 *            实缴账单对应的应收账单id
	 * @return : SpsBill
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	public SpsBill createBill(Integer spId, Integer cpId, String billType, String period, Integer preBillId);

	/**
	 * 创建应收、实缴账单
	 * 
	 * @param spId
	 *            服务商ID
	 * @param cpId
	 *            企业ID
	 * @param billType
	 *            PREPAY应收 PAID实缴
	 * @return : SpsBill
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	public SpsBill createBill(Integer spId, Integer cpId, String billType);

	/**
	 * 将子账单一次性费用复制到主账单
	 * 
	 * @param bill
	 *            账单信息
	 * @param user_id
	 *            用户ID
	 * @param oncefees
	 *            单次费用
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 11:40
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:40
	 * @updateAuthor :
	 */
	public Result copyChildBill(ContextInfo cti,SpsBill bill, Integer user_id, OnceFeeDto oncefees);

	/**
	 * 发送或重新发送企业账单
	 * 
	 * @param spsBill
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 11:42
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:42
	 * @updateAuthor :
	 */
	public Result sendCmBill(ContextInfo cti,SpsBill spsBill);

	/**
	 * 获取账单Excel发送邮件
	 * 
	 * @param email
	 *            邮箱地址
	 * @param fileId
	 *            文件ID
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @return : SpsBill
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	public boolean sendMail(String email, String fileId, String title, String content);

	/**
	 *  发送短信
	 *  @param   phoneNo, content
	 *	@return 			: boolean
	 *  @createDate  	: 2017-03-20 11:23
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-20 11:23
	 *  @updateAuthor  :
	 */
	public boolean sendSms(String phoneNo,String content);

	/**
	 * 分页获取账目核销列表
	 * 
	 * @param cmName
	 * @param bill
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 11:52
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:52
	 * @updateAuthor :
	 */
	public PageModel queryBillPaymentList(PageInfo pi, String cmName, SpsBill bill, ContextInfo cti);

	/**
	 * 获取账单核销详情
	 * 
	 * @param bill
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 11:54
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:54
	 * @updateAuthor :
	 */
	public Map<String, Object> queryBillPaymentDetail(SpsBill bill);

	/**
	 * 获取账单支付收款详情
	 * 
	 * @param bill
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 11:59
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:59
	 * @updateAuthor :
	 */
	public Map<String, Object> queryCpBillPaymentDetail(SpsBill bill);


	/**
	 * 账目核销-修改实收官费和实收服务费
	 * 
	 * @param spsBill
	 * @return : int
	 * @createDate : 2016-11-10 12:05
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:05
	 * @updateAuthor :
	 */
	public int modifyFeeOfOfficeAndService(ContextInfo cti,SpsBill spsBill);


	/**
	 * 账目核销-协作
	 *
	 * @param spsBill
	 * @return : int
	 * @createDate : 2016-11-10 12:05
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:05
	 * @updateAuthor :
	 */
	public Result writeOffBillByCp(ContextInfo cti,SpsBill spsBill);

	/**
	 * 查询账单详情
	 * 
	 * @param spsBill
	 * @return : com.xfs.bill.model.SpsBill
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	public SpsBill findByPK(SpsBill spsBill);

	/**
	 * 获取应收账单合作伙伴列表
	 * 
	 * @param sp_id
	 *            服务商ID
	 * @param area_id
	 *            地区
	 * @param bill_month
	 *            账单月份
	 * @param spname
	 *            服务商名称
	 *            用户信息
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	public PageModel queryPrepayCooperation(PageInfo pi, Integer sp_id, Integer area_id, String bill_month,
			String spname, ContextInfo cti);

	/**
	 * 获取应收账单下的客户列表
	 * 
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	public PageModel queryDeputeCorp(PageInfo pi, Integer sp_id, Integer parent_sp_id, String bill_month,
			Integer fee_pay_status, String cp_name);

	/**
	 * 应收客户列表
	 * 
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	public PageModel queryDeputeCorp2(PageInfo pi, Integer sp_id, Integer parent_sp_id, String bill_month,
			Integer fee_pay_status, String cp_name, ContextInfo cti);

	/**
	 * 获取协作应收账单下的客户员工列表
	 * 
	 * @param sp_id
	 * @param parent_sp_id
	 * @param cp_id
	 * @param bill_month
	 * @param scheme_id
	 * @param area_id
	 * @param searchWord
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	public PageModel queryDeputeCorpEmp(PageInfo pi, Integer sp_id, Integer parent_sp_id, Integer cp_id,
			String bill_month, Integer scheme_id, Integer area_id, String searchWord);

	/**
	 * 获取实缴账单列表
	 * 
	 * @param whereMap
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:11
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:11
	 * @updateAuthor :
	 */
	public PageModel queryBillPiadList(PageInfo pi, Map<String, Object> whereMap);

	/**
	 * 获取实缴账单详情
	 * 
	 * @param whereMap
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:13
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:13
	 * @updateAuthor :
	 */
	public PageModel queryPaidDetailList(PageInfo pi, Map<String, Object> whereMap);

	/**
	 * 实缴账单列表 差额详情
	 * 
	 * @param whereMap
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:14
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:14
	 * @updateAuthor :
	 */
	public PageModel queryMarginDetailList(PageInfo pi, Map<String, Object> whereMap);

	/**
	 * 查询包含差额信息的账单详情
	 * 
	 * @param spid
	 * @param cmid
	 * @param bill_id
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:15
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:15
	 * @updateAuthor :
	 */
	public Map<String, Object> queryCmBillMargeDetail(Integer spid, Integer cmid, Integer bill_id);

	/**
	 * 财务处理 实缴账单 修改实缴凭证
	 * 
	 * @param whereMap
	 * @return : int
	 * @createDate : 2016-11-10 14:18
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:18
	 * @updateAuthor :
	 */
	public int modifyPaidEvidence(ContextInfo cti,Map<String, Object> whereMap);

	/**
	 * 协作平台 委托实缴账单列表
	 * 
	 * @param whereMap,
	 *            user
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:18
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:18
	 * @updateAuthor :
	 */
	public PageModel queryDeputePiadList(PageInfo pi, Map<String, Object> whereMap, ContextInfo cti);

	/**
	 * 协作平台 委托实缴账单 详情列表
	 * 
	 * @param whereMap,
	 *            user
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:19
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:19
	 * @updateAuthor :
	 */
	public PageModel queryDeputePaidDetailList(PageInfo pi, Map<String, Object> whereMap, ContextInfo cti);

	/**
	 * 协作平台 委托实缴账单 详情 实缴详情
	 * 
	 * @param whereMap
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:20
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:20
	 * @updateAuthor :
	 */
	public PageModel queryPaidDetailOfDeputeList(PageInfo pi, Map<String, Object> whereMap);

	/**
	 * 协作平台 委托实缴账单 详情 差额详情
	 * 
	 * @param whereMap
	 * @return : java.lang.Object
	 * @createDate : 2016-11-10 14:21
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:21
	 * @updateAuthor :
	 */
	public Object queryMarginDetailOfDeputeList(PageInfo pi,Map<String, Object> whereMap);

	/**
	 * 上传实缴账单,解析实缴账单
	 * 
	 * @param accountId
	 * @param fileid
	 * @param spId
	 * @return : com.xfs.common.Result
	 * @createDate : 2016-11-10 14:22
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:22
	 * @updateAuthor :
	 */
	public Result analysis(ContextInfo cti ,Integer accountId, Integer fileid, Integer spId);

	/**
	 * 退回分包账单
	 * 
	 * @param bill
	 * @return : boolean
	 * @createDate : 2016-11-10 14:25
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:25
	 * @updateAuthor :
	 */
	public Result callBackBill(ContextInfo cti, SpsBill bill);

	/**
	 * 检查总包状态
	 * 
	 * @param bill_id
	 * @param sp_id
	 * @return : Integer
	 * @createDate : 2016-11-10 14:25
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:25
	 * @updateAuthor :
	 */
	public Integer checkSpsBillStatusByDeuptId(ContextInfo cti ,Integer bill_id, Integer sp_id);

	/**
	 * 保存实缴账单
	 * 
	 * @param paidBillDtos,
	 *            spId
	 * @return : void
	 * @createDate : 2016-11-10 14:24
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:24
	 * @updateAuthor :
	 */
	public void savePaidBillDetail(ContextInfo cti,List<PaidBillDto> paidBillDtos, Integer spId);

	/**
	 * 首页--查询--账单数量
	 * 
	 * @param parameterMap
	 * @return : java.util.HashMap<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	public HashMap<String, Object> queryBillCount(HashMap<String, Object> parameterMap);

	/**
	 * 首页--查询--账单数量
	 * 
	 * @param bill
	 * @return : =Integer
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	public Integer queryBillCount(SpsBill bill);

	/**
	 * 首页--查询--账单金额
	 * 
	 * @param parameterMap
	 * @return : HashMap<String,Object>
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	public HashMap<String, Object> queryBillMoney(HashMap<String, Object> parameterMap);

	/**
	 * 首页--查询--账单服务费
	 * 
	 * @param parameterMap
	 * @return : HashMap<String,Object>
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	public HashMap<String, Object> queryServiceMoney(HashMap<String, Object> parameterMap);

	/**
	 * 首页--查询--年收入统计
	 * 
	 * @param parameterMap
	 * @return : List<Map<String,Object>>
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	public List<Map<String, Object>> queryYearMoney(HashMap<String, Object> parameterMap);

	/**
	 * 企业实缴列表数据
	 * 
	 * @param bill
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:44
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:44
	 * @updateAuthor :
	 */
	public PageModel findSpsBillByBilltype(PageInfo pi, SpsBill bill);

	/**
	 * 根据查询条件获取企业账单列表
	 * 
	 * @param bill
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:41
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:41
	 * @updateAuthor :
	 */
	public PageModel queryBillList(PageInfo pi, SpsBill bill);

	/**
	 * 获取企业账单详情信息
	 * 
	 * @param cpid,
	 *            bill_id
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:45
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:45
	 * @updateAuthor :
	 */
	public Map<String, Object> queryCmBillDetail(Integer cpid, Integer bill_id);

	/**
	 * 实缴账单详情
	 * 
	 * @param bill
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:40
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:40
	 * @updateAuthor :
	 */
	public Map<String, Object> findPaidBillDetail(SpsBill bill);

	/**
	 * 获取服务商账单联系人
	 * 
	 * @param cpId
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:46
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:46
	 * @updateAuthor :
	 */
	public Map<String, Object> queryBillSpan(Integer cpId);

	/**
	 * 服务商请款
	 * 
	 * @param bill,
	 *            result, user_id, sms
	 * @return : boolean
	 * @createDate : 2016-11-10 14:49
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:49
	 * @updateAuthor :
	 */
	public boolean paySpExpAmount(PageInfo pi,ContextInfo cti,SpsBill bill, Result result, Integer user_id, String issend);

	/**
	 * 服务费付款
	 * 
	 * @param bill,
	 *            result, user_id, sms
	 * @return : boolean
	 * @createDate : 2016-11-10 14:48
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:48
	 * @updateAuthor :
	 */
	public boolean payServiceExpAmount(PageInfo pi,ContextInfo cti ,SpsBill bill, Result result, Integer user_id, String sms);

	/**
	 * 官费查询
	 * 
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:56
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:56
	 * @updateAuthor :
	 */
	public PageModel FindManage(PageInfo pi, SpsBill vo);

	/**
	 * 获取请款总金额
	 * 
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:54
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:54
	 * @updateAuthor :
	 */
	public Map<String, Object> queryExpAmount();

	/**
	 * 获取付款总金额
	 * 
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:54
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:54
	 * @updateAuthor :
	 */
	public Map<String, Object> queryPayAmount();

	/**
	 * 服务费查询
	 * 
	 * @param vo
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:53
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:53
	 * @updateAuthor :
	 */
	public PageModel FindServiceManage(PageInfo pi, SpsBill vo);

	/**
	 * 获取服务费请款总金额
	 * 
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:51
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:51
	 * @updateAuthor :
	 */
	public Map<String, Object> queryServiceExpAmount();

	/**
	 * 获取服务费付款总金额
	 * 
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:51
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:51
	 * @updateAuthor :
	 */
	public Map<String, Object> queryServicePayAmount();

	/**
	 * 分润总金额
	 * 
	 * @param
	 * @return : java.util.Map<java.lang.String,java.lang.Object>
	 * @createDate : 2016-11-10 14:50
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:50
	 * @updateAuthor :
	 */
	public Map<String, Object> queryServiceGainAmount();

	/**
	 * 获取支付账单下的分包账单
	 * 
	 * @param billNum
	 * @return : java.util.List<java.util.Map
	 *         <java.lang.String,java.lang.Object>>
	 * @createDate : 2016-11-10 14:48
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:48
	 * @updateAuthor :
	 */
	public List<Map<String, Object>> queryDeputeBillList(String billNum);

	/**
	 * 获取企业最新应付账单月份
	 * 
	 * @param cpId
	 * @return : String
	 * @createDate : 2016-11-10 14:48
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:48
	 * @updateAuthor :
	 */
	public String queryMaxBillMonth(Integer cpId);

	/**
	 *  根据账单编号获取账单信息
	 *  @param   billNum
	 *	@return 			: com.xfs.bill.model.SpsBill
	 *  @createDate  	: 2016-11-24 11:34
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-24 11:34
	 *  @updateAuthor  :
	 */
	public SpsBill queryBillByBillNum(String billNum);

	/**
	 *  取消支付
	 *  @param   billId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-09 11:32
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-09 11:32
	 *  @updateAuthor  :
	 */
	public Result cancelPay(ContextInfo cti,Integer billId);


	public Result queryBillPayStatus(ContextInfo cti,Integer billId);

	/**
	 *  删除原有账单
	 *  @param   cti, spid, cmid, period
	 *	@return 			: java.lang.Integer
	 *  @createDate  	: 2016-11-23 10:42
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-23 10:42
	 *  @updateAuthor  :
	 */
	public Integer delCmBillByMonth(ContextInfo cti, Integer spid,Integer cmid,String period);


	/**
	 *  发送账单
	 *  @param   cti, spsBill
	 *	@return 			: java.lang.Integer
	 *  @createDate  	: 2016-11-23 10:44
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-23 10:44
	 *  @updateAuthor  :
	 */
	public Integer updateBillSend(ContextInfo cti, SpsBill spsBill);

	/**
	 *  生成账单支付信息
	 *  @param   bill 账单信息
	 *  @param   cti  上下文
	 *  @param   notifyUrl 异步通知
	 *  @param   returnUrl 重定向地址
	 *  @param   isuse 是否使用优惠券
	 *  @param   prize_id 优惠券ID
	 *  @param   payChannleName 支付渠道名称
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-19 21:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-19 21:04
	 *  @updateAuthor  :
	 */
	public Result createPayBillInfo(SpsBill bill,ContextInfo cti,String notifyUrl,String returnUrl,String isuse,Integer prize_id,String payChannleName);

	/**
	 *  核销之前检查是否存在支付凭证
	 *  @param   cti
	 *  @param   bill
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-06 14:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-06 14:38
	 *  @updateAuthor  :
	 */
	public Result writeOffBillCheckVoucher(ContextInfo cti,SpsBill bill) throws Exception;

	/**
	 *  预览账单模版
	 *  @param   fieldIds
	 *	@return 			: java.io.File
	 *  @createDate  	: 2017-01-06 16:48
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-06 16:48
	 *  @updateAuthor  :
	 */
	public File previewBillTempalete(List<Integer> fieldIds);

	public String generateExcel(ContextInfo cti,SpsBill bill, String cmName, String period, String depute_type, String bill_type);

	/**
	 *  获取账单收款管理信息
	 *  @param
	 * @return    :
	 *  @createDate   : 2017/1/5 15:46
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/5 15:46
	 *  @updateAuthor  :
	 */
	public PageModel queryCollectionListPage(PageInfo pi, SpsBill vo,String spName,String corpName,String period,String source,String actualType,String payType);
	/**
	 * 根据 账单编号 查询有效账单
	 * @param spBill
	 * @return
	 */
	public SpsBill findByBillNum(SpsBill spBill);


	public PageModel querySpsBillList(ContextInfo cti,PageInfo pi, SpsBill vo);

	public List<SpsBillDto> queryTodoBillList(ContextInfo cti);

	public List<Map<String,Object>> queryBillTrend(ContextInfo cti,Integer areaId,List<String> months);


	public List<InsureEmpDetail> queryEmpBillInsMonth(Integer cpId, Integer empId, String period);

	public List<InsureEmpDetail> queryEmpBillMonth(ContextInfo cti, Integer empId, String period);

	public List<InsureEmpDetail> queryEmpBillPayment(ContextInfo cti, Integer empId, String period);

	public Map<String, Object> findBillDetailOne(ContextInfo cti,Integer billId) ;

	public Map<String, Object> findBillDetailSed(ContextInfo cti,Integer billId) ;

	public Map<String, Object> findBillDetailThird(ContextInfo cti,Integer billId);

	public Integer updateBillReview(ContextInfo cti,SpsBill bill);

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


}
