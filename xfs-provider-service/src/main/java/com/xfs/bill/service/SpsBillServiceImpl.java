package com.xfs.bill.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.xfs.common.constant.IStaticVarConstant;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfs.activity.model.BdUserPrizeRecord;
import com.xfs.activity.service.BdUserPrizeRecordService;
import com.xfs.activity.service.BdUserPrizeService;
import com.xfs.base.model.SysMessage;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.bill.dao.SpsBillDao;
import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.bill.dto.Management;
import com.xfs.bill.dto.OnceFeeDto;
import com.xfs.bill.dto.PaidBillDto;
import com.xfs.bill.dto.SpsBillDto;
import com.xfs.bill.model.BdBillrule;
import com.xfs.bill.model.SpsBill;
import com.xfs.bill.model.SpsBillDetail;
import com.xfs.bill.model.SpsBillEmp;
import com.xfs.bill.model.SpsBillMargin;
import com.xfs.bill.model.SpsBillitem;
import com.xfs.bill.model.SpsEmpActual;
import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.bill.model.SpsFeeCorponce;
import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.bill.utils.VelocityUtil;
import com.xfs.business.dao.BdInsuranceareaDao;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.BdInsurancearea;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BdInsuranceareaService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.email.util.MailUtil;
import com.xfs.common.email.vo.Email;
import com.xfs.common.excel.ExcelCore;
import com.xfs.common.excel.ExcelModel;
import com.xfs.common.excel.ExcelUtil;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Arith;
import com.xfs.common.util.BusinessCode;
import com.xfs.common.util.Config;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.common.util.TransactionUtil;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.cp.dto.SaaSCpBillDto;
import com.xfs.cp.service.CpOrderService;
import com.xfs.pay.chanpay.util.ChanPayUtils;
import com.xfs.pay.xfspay.sign.Base64;
import com.xfs.pay.xfspay.util.XfsPayUtils;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.settlement.dto.ReqCancelOrder;
import com.xfs.settlement.dto.ReqPay;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.dto.ToReqPay;
import com.xfs.settlement.model.BlBalance;
import com.xfs.settlement.model.BlBalancedetail;
import com.xfs.settlement.model.BlPayVoucher;
import com.xfs.settlement.service.BlBalanceService;
import com.xfs.settlement.service.BlBalancedetailService;
import com.xfs.settlement.service.BlPayBusinessService;
import com.xfs.settlement.service.BlPayEnterpriseService;
import com.xfs.settlement.service.BlPayVoucherService;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sp.service.SpsSchemeEmpService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.sps.utils.ImportExcelUtil;
import com.xfs.sps.utils.JacksonAnnotationFieldUtil;
import com.xfs.ts.dao.SpsTsPersonFlowDao;
import com.xfs.ts.model.SpsTsPersonFlow;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysUserService;

/**
 * 账单服务类
 * 
 * @author : konglc@xinfushe.com
 * @date : 2016-11-10 15:09
 * @version : V1.0
 */
@Service
public class SpsBillServiceImpl implements SpsBillService, IRedisKeys ,IStaticVarConstant{

	private static final Logger log = Logger.getLogger(SpsBillServiceImpl.class);

	@Autowired
	private SpsBillDao spsBillDao;

	@Autowired
	private SpsBillitemService spsBillitemService;

	@Autowired
	private SpsBillDetailService spsBillDetailService;

	@Autowired
	SpsBillMarginService spsBillMarginService;

	@Autowired
	SpsFeeEmponceService spsFeeEmponceService;

	@Autowired
	SpsFeeCorponceService spsFeeCorponceService;

	@Autowired
	SpsSchemeService spsSchemeService;

	@Autowired
	SpsSchemeEmpService spsSchemeEmpService;

	@Autowired
	CmCorpService cmCorpService;

	@Autowired
	SysUploadfileService sysUploadfileService;

	@Autowired
	CmEmployeeService cmEmployeeService;

	@Autowired
	SpsEmpActualService spsEmpActualService;

	@Autowired
	SpsEmpActualdetailService spsEmpActualdetailService;

	@Autowired
	BdInsuranceareaService bdInsuranceareaService;

	@Autowired
	SpsTsPersonFlowDao spsTsPersonFlowDao;

	@Autowired
	BdBillruleService bdBillruleService;

	@Autowired
	SpsTaskService spsTaskService;

	@Autowired
	SpsFeeEmponcedetailService spsFeeEmponcedetailService;

	@Autowired
	BsArearatioService bsArearatioService;

	@Autowired
	BdInsuranceService bdInsuranceService;

	@Autowired
	private BlBalanceService blBalanceService;

	@Autowired
	private BlBalancedetailService blBalancedetailService;

	@Autowired
	private BdInsuranceareaDao bdInsuranceareaDao;

	@Autowired
	private SpsBillEmpService spsBillEmpService;

	@Autowired
	BlPayVoucherService blPayVoucherService;

	@Autowired
	BdUserPrizeService bdUserPrizeService;

	@Autowired
	BdUserPrizeRecordService bdUserPrizeRecordService;

	@Autowired
	BlPayBusinessService blPayBusinessService;

	@Autowired
	BlPayEnterpriseService blPayEnterpriseService;

	@Autowired
	CpOrderService cpOrderService;

	@Autowired
	SpServiceService spServiceService;

	@Autowired
	SysUserService sysUserService;

	public int save(ContextInfo cti, SpsBill vo) {
		return spsBillDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsBill vo) {
		return spsBillDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsBill vo) {
		return spsBillDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsBill vo) {
		return spsBillDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsBill vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBill> datas = spsBillDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsBill> findAll(SpsBill vo) {

		List<SpsBill> datas = spsBillDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/16 15:32:00

	/**
	 *  获取账单详情
	 *  @param
	 *	@return 			: com.xfs.bill.model.SpsBill
	 *  @createDate  	: 2017-01-13 13:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-13 13:50
	 *  @updateAuthor  :
	 */
	public SpsBill queryBillDetail(SpsBill vo){
		return spsBillDao.queryBillDetail(vo);
	}

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
	@Override
	public Integer queryBillListCount(String cmName, SpsBill bill, ContextInfo cti) {
		Integer total = spsBillDao.queryBillListCount(cmName, bill, cti);
		return total;
	}

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
	@Override
	public PageModel queryBillList(PageInfo pi, String cmName, SpsBill bill, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryBillListCount(cmName, bill, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryBillList(cmName, bill, pageIndex, pageSize, cti);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryBillList(PageInfo pi, SpsBill bill) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryBillListCount(bill);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryBillList(bill, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public Map<String, Object> queryCmBillDetail(Integer spid, Integer cmid, Integer bill_id) {
		return spsBillDao.queryCmBillDetail(spid, cmid, bill_id);
	}

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
	@Override
	public Result createCmBill(SpsBill bill, Integer user_id) {
		String key = bill.getSpId() + "_" + bill.getCpId() + "_" + bill.getPeriod() + "_" + bill.getBillType();
		if (RedisUtil.getLock(key, 60000L) != 1) {
			Result result = Result.createResult().setSuccess(false);
			result.setError("正在生成请稍后");
			return result;
		}
		Result result = Result.createResult().setSuccess(true);
		try {
			createBill(bill.getSpId(), bill.getCpId(), bill.getBillType(), bill.getPeriod(), null);
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setError(e.getMessage());
			throw new BusinessException(result.getError());
		} finally {
			RedisUtil.delLock(key);
		}
	}

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
	@Override
	public Result checkCreateBill(SpsBill bill, Integer user_id) {
		Result result = Result.createResult().setSuccess(false);
		// 检查是否已经到了账单日
		Calendar calendar = Calendar.getInstance();
		int curr_day = calendar.get(Calendar.DAY_OF_MONTH);
		String month = DateUtil.getDateStr(calendar.getTime(),"yyyy-MM");
		if(!StringUtils.isBlank(bill.getPeriod()) && month.compareTo(bill.getPeriod()) <= 0){
			if (null != bill.getBillDay() && curr_day < bill.getBillDay() && "PREPAY".equals(bill.getBillType())) {
				result.setError("NODATE");
				return result;
			}
		}

		// 检查当前服务商对应的企业方案是否为委托
		List<Map<String, Object>> billStatus = spsBillDao.checkSpBillStatus(bill.getSpId(), bill.getCpId(),
				bill.getPeriod(), bill.getBillType());
		if (null != billStatus && !billStatus.isEmpty()) {
			boolean iscreat = true;
			StringBuffer p_sp_ids = new StringBuffer();
			for (Map<String, Object> billstat : billStatus) {
				if (null == billstat.get("bill_id") || !billstat.get("bill_state").equals("SEND")) {
					iscreat = false;
					break;
				} else {
					p_sp_ids.append(billstat.get("sp_id")).append(",");
				}
			}
			// 校验当前服务商子账单是否生成
			if (iscreat) {
				// 获取子账单当月调整费用
				p_sp_ids = p_sp_ids.deleteCharAt(p_sp_ids.length() - 1);
				List<Map<String, Object>> spsFeeEmponces = spsFeeEmponceService.queryChildBillEmpOnceItems(
						bill.getSpId(), p_sp_ids.toString(), bill.getCpId(), bill.getPeriod());
				result.setDataValue("spsFeeEmponces", spsFeeEmponces);
				// 获取企业一次性费用
				List<SpsFeeCorponce> spsFeeCorponces = spsFeeCorponceService.queryChildBillCorpOnceItems(bill.getSpId(),
						p_sp_ids.toString(), bill.getCpId(), bill.getPeriod());
				result.setDataValue("spsFeeCorponces", spsFeeCorponces);

				if ((null == spsFeeEmponces || spsFeeEmponces.isEmpty())
						&& (null == spsFeeCorponces || spsFeeCorponces.isEmpty())) {
					return createCmBill(bill, user_id);
				} else {
					result.setError("MERGE");
				}
			} else {
				// 子账单未生成
				result.setError("UNCHILDBILL");
			}
		} else {
			return createCmBill(bill, user_id);
		}
		return result;
	}

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
	@Override
	public Result checkCreatePaidBill(SpsBill bill, Integer user_id) {
		Result result = Result.createResult().setSuccess(false);
		// 校验当前要生成的实缴账单
		/**
		 * 1、当前账单方案下存在不支持支持实缴明细的地区 2、是否存在实缴人员明细
		 */
		boolean isCreate = true;
		boolean isFlow = false;
		// 获取所有方案
		SpsScheme qry = new SpsScheme();
		qry.setSpId(bill.getSpId());
		qry.setCpId(bill.getCpId());
		qry.setState("USE");
		qry.setDr(0);
		// qry.setEffectiveDate(bill.getPeriod());
		// qry.setExpireDate(bill.getPeriod());

		List<SpsScheme> schemes = spsSchemeService.findAll(qry);
		if (null != schemes) {
			for (SpsScheme scheme : schemes) {
				Integer fundAccId = null;
				Integer insuranceAccId = null;
				if ("DEPUTE".equals(scheme.getSchemeType())) {
					SpsScheme childscheme = spsSchemeService.findByParentSchemeId(scheme.getSpId(),
							scheme.getSchemeId());
					if (null != childscheme) {
						fundAccId = childscheme.getFundAccountId();
						insuranceAccId = childscheme.getInsuranceAccountId();
					}
				} else {
					fundAccId = scheme.getFundAccountId();
					insuranceAccId = scheme.getInsuranceAccountId();
				}
				// 实缴账单 获取个人实缴明细 如果存在，使用实缴明细作为月缴
				if (spsEmpActualService.countByAccIdAndPeriod("INSURANCE", insuranceAccId, null, bill.getPeriod(),
						"MONTH") > 0)
					isFlow = true;
				if (spsEmpActualService.countByAccIdAndPeriod("FUND", null, fundAccId, bill.getPeriod(), "MONTH") > 0)
					isFlow = true;
				List<BdInsurancearea> insAreas = bdInsuranceareaService.findBdInsuranceareaByAreaid(scheme.getAreaId());
				for (BdInsurancearea insArea : insAreas) {
					if ("Y".equals(insArea.getFlow())) {
						isCreate = false;
					}
				}
			}
		}

		// 不支持下行数据
		if (isCreate && !isFlow) {
			result.setError("NOFLOW");
			return result;
		} else {
			// 校验是否存在实缴人员明细
			if (!isFlow) {
				result.setError("NODATA");
				return result;
			} else {
				result = checkCreateBill(bill, user_id);
			}
		}
		return result;
	}

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
	@Override
	public SpsBill createBill(Integer spId, Integer cpId, String billType, String period, Integer preBillId) {
		if ("PAID".equals(billType))
			return createPaidBill(null,spId, cpId, period, preBillId);
		else
			return createPrePayBill(null, spId, cpId, period, preBillId);
	}

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
	@Override
	public SpsBill createBill(Integer spId, Integer cpId, String billType) {
		return createBill(spId, cpId, billType, null, null);
	}

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
	@Override
	public Result copyChildBill(ContextInfo cti, SpsBill bill, Integer user_id, OnceFeeDto oncefees) {
		if (null != oncefees.getSpsFeeCorponces() && !oncefees.getSpsFeeCorponces().isEmpty()) {
			for (SpsFeeCorponce spsFeeCorponce : oncefees.getSpsFeeCorponces()) {
				spsFeeCorponce.setCpId(bill.getCpId());
				spsFeeCorponce.setSpId(bill.getSpId());
				spsFeeCorponce.setDr(0);
				spsFeeCorponce.setId(null);
				spsFeeCorponce.setSchemeId(-1);
				spsFeeCorponce.setCreateDt(new Date());
				spsFeeCorponce.setModifyDt(new Date());
				spsFeeCorponce.setSource(2);
				spsFeeCorponceService.insert(cti, spsFeeCorponce);
			}
		}
		if (null != oncefees.getSpsFeeEmponces() && !oncefees.getSpsFeeEmponces().isEmpty()) {
			for (SpsFeeEmponce spsFeeEmponce : oncefees.getSpsFeeEmponces()) {
				int old_once_id = spsFeeEmponce.getSourceid();
				spsFeeEmponce.setCpId(bill.getCpId());
				spsFeeEmponce.setSpId(bill.getSpId());
				spsFeeEmponce.setDr(0);
				spsFeeEmponce.setId(null);
				spsFeeEmponce.setCreateDt(new Date());
				spsFeeEmponce.setModifyDt(new Date());
				spsFeeEmponce.setPeriod(bill.getPeriod());
				spsFeeEmponce.setSource(2);
				spsFeeEmponceService.insert(cti, spsFeeEmponce);
				// 获取单次费用详细列表
				List<SpsFeeEmponcedetail> feeEmponcedetails = spsFeeEmponcedetailService
						.queryEmpBackInusInfoList(old_once_id);
				if (null != feeEmponcedetails && !feeEmponcedetails.isEmpty()) {
					for (SpsFeeEmponcedetail spsFeeEmponcedetail : feeEmponcedetails) {
						spsFeeEmponcedetail.setId(null);
						spsFeeEmponcedetail.setEmpFeeId(spsFeeEmponce.getId());
						spsFeeEmponcedetailService.insert(cti, spsFeeEmponcedetail);
					}
				}
			}
		}
		return createCmBill(bill, user_id);
	}

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
	@Override
	public Result sendCmBill(ContextInfo cti, SpsBill spsBill) {
		Result result = Result.createResult().setSuccess(true);
		// 获取文件ID
		Map<String, Object> spsbillmap = spsBillDao.queryCmBillDetail(spsBill.getSpId(), spsBill.getCpId(), spsBill.getBillId());
		String fileid = String.valueOf(spsbillmap.get("upload_file_id"));
		//if (!"SEND".equals(spsbillmap.get("bill_state"))) {
		/**
		 * 判断当前是否为协作企业
		 */
		SpsBill bill = new SpsBill();
		SpService spService = spServiceService.findSpByInventedCorp(spsBill.getCpId());
		if(null != spService && spsBill.getCpId().equals(spService.getCpId()) && !"SEND".equals(String.valueOf(spsbillmap.get("bill_state")))){
			SaaSCpBillDto saaSCpBillDto = new SaaSCpBillDto();
			saaSCpBillDto.setBillNum(String.valueOf(spsbillmap.get("bill_num")));
			saaSCpBillDto.setFileId(fileid);
			saaSCpBillDto.setASpId(spService.getSpId());
			saaSCpBillDto.setBSpId(spsBill.getSpId());
			saaSCpBillDto.setPrice(Double.valueOf(spsbillmap.get("billamount").toString()));
			saaSCpBillDto.setServicePrice(Double.valueOf(spsbillmap.get("perpay_service_fee").toString()));
			saaSCpBillDto.setPublicPrice(Double.valueOf(spsbillmap.get("perpay_office_fee").toString()));
			bill.setFeePayStatus(3);
			result = cpOrderService.SaaSCpBill(cti,saaSCpBillDto);
		}
		if(result.isSuccess()){
			// 修改账单状态变为已发送
			bill.setBillId(spsBill.getBillId());
			bill.setBillState("SEND");
			spsBillDao.update(cti, bill);
			//向HR发送账单信息--邮件 短信
			//获取HR的手机号 邮箱信息
			CmCorp cmCorp = new CmCorp();
			cmCorp.setCpId(Integer.parseInt(String.valueOf(spsbillmap.get("cp_id"))));
			cmCorp = cmCorpService.findByPk(cmCorp);
			//获取负责此企业的客服联系方式
			List<SysUser> sysUsers = sysUserService.findUserAuth(spsBill.getCpId());
			if(null != sysUsers && !sysUsers.isEmpty()){
				//发送邮件
				if(StringUtils.isNotBlank(cmCorp.getMail())){
					sendMail(cmCorp.getMail(),String.valueOf(spsbillmap.get("file_id")),String.valueOf(spsbillmap.get("period"))+"账单","尊敬的用户：用友薪福社已为您发送"+String.valueOf(spsbillmap.get("period"))+"账单，本次账单总金额："+Double.valueOf(spsbillmap.get("billamount").toString())+",详情请见附件或者<a href=\"https://www.xinfushe.com/corp/index.html\">登录工作台</a>查看，请核对后在工作台中确认。如有疑问请致电"+sysUsers.get(0).getMobile()+(StringUtils.isBlank(sysUsers.get(0).getRealName()) ? "" : "，"+sysUsers.get(0).getRealName()));
				}
				//发送短信
				if(StringUtils.isNotBlank(cmCorp.getMobile())){
					sendSms(cmCorp.getMobile(),"【薪福社】已为您发送"+String.valueOf(spsbillmap.get("period"))+"账单，本次账单总金额："+Double.valueOf(spsbillmap.get("billamount").toString())+"，请核对后在工作台中确认。如有疑问请致电"+sysUsers.get(0).getMobile()+(StringUtils.isBlank(sysUsers.get(0).getRealName()) ? "" : "，"+sysUsers.get(0).getRealName()));
				}
			}
		}
		//}
		return result;
	}

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
	public boolean sendMail(String email, String fileId, String title, String content) {
		File excelFile = null;
		if(StringUtils.isNotBlank(fileId)){
			// 定义临时路径
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
			String filePath = fileRootPath + File.separator + format.format(new Date());
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			excelFile = new File(fileRootPath + File.separator + title + ".xls");
			try {
				bis = new BufferedInputStream(sysUploadfileService.downloadFile(fileId));
				FileOutputStream os = new FileOutputStream(excelFile);
				bos = new BufferedOutputStream(os);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != bis) {
					try {
						bis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (null != bos) {
					try {
						bos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		try {
			// 发送邮件
			Email emailObj = new Email();
			List<String> toAddress = new ArrayList<String>();
			toAddress.add(email);
			emailObj.setToAddress(toAddress);
			emailObj.setSubject(title);
			VelocityContext context = new VelocityContext();
			context.put("content",content);
			emailObj.setContent(VelocityUtil.merge(context, "template/mail.vm"));
			if(null != excelFile && excelFile.exists()){
				List<File> attachments = new ArrayList<File>();
				attachments.add(excelFile);
				emailObj.setAttachments(attachments);
			}
			MailUtil.sendMail(emailObj);
			// 删除 excel
			if (null != excelFile && excelFile.exists()) {
				excelFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

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
	public boolean sendSms(String phoneNo,String content){
		try{
			return SmsUtil.sendSms(phoneNo,content);
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 分页获取账目核销列表
	 * 
	 * @param cmName
	 * @param bill
	 * @param cti
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 11:52
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:52
	 * @updateAuthor :
	 */
	@Override
	public PageModel queryBillPaymentList(PageInfo pi, String cmName, SpsBill bill, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryBillPaymentListCount(cmName, bill, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryBillPaymentList(cmName, bill, pageIndex, pageSize, cti);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public Map<String, Object> queryBillPaymentDetail(SpsBill bill) {
		return spsBillDao.queryBillPaymentDetail(bill);
	}

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
	@Override
	public Map<String, Object> queryCpBillPaymentDetail(SpsBill bill) {
		return spsBillDao.queryCorpBillPaymentDetail(bill);
	}

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
	@Override
	public int modifyFeeOfOfficeAndService(ContextInfo cti, SpsBill spsBill) {
		return spsBillDao.modifyFeeOfOfficeAndService(cti, spsBill);
	}


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
	public Result writeOffBillByCp(ContextInfo cti,SpsBill spsBill){
		Result result = Result.createResult().setSuccess(true);
		spsBill = queryBillByBillNum(spsBill.getBillNum());
		if(null != spsBill){
			spsBill.setActualOfficeFee(spsBill.getPerpayOfficeFee());
			spsBill.setActualServiceFee(spsBill.getPerpayServiceFee());
			spsBill.setFeePayStatus(1);
			spsBill.setCheckedType("AUTO");
			modifyFeeOfOfficeAndService(cti,spsBill);
		}else{
			result.setError("不存在当前账单");
			result.setSuccess(false);
		}
		return result;
	}

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
	@Override
	public SpsBill findByPK(SpsBill spsBill) {
		return spsBillDao.findByPK(spsBill);
	}

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
	 * @param cti
	 *            用户信息
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	@Override
	public PageModel queryPrepayCooperation(PageInfo pi, Integer sp_id, Integer area_id, String bill_month,
			String spname, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryPrepayCooperationCount(sp_id, area_id, bill_month, spname, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryPrepayCooperationList(sp_id, area_id, bill_month, spname,
				pageIndex, pageSize, cti);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryDeputeCorp(PageInfo pi, Integer sp_id, Integer parent_sp_id, String bill_month,
			Integer fee_pay_status, String cp_name) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryDeputeCorpCount(sp_id, parent_sp_id, bill_month, fee_pay_status, cp_name);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryDeputeCorpList(sp_id, parent_sp_id, bill_month,
				fee_pay_status, cp_name, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 应收客户列表
	 * 
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @param cti
	 * @return PageModel
	 * @createDate : 2016-11-10 12:06
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 12:06
	 * @updateAuthor :
	 */
	@Override
	public PageModel queryDeputeCorp2(PageInfo pi, Integer sp_id, Integer parent_sp_id, String bill_month,
			Integer fee_pay_status, String cp_name, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryDeputeCorpCount2(sp_id, parent_sp_id, bill_month, fee_pay_status, cp_name, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryDeputeCorpList2(sp_id, parent_sp_id, bill_month,
				fee_pay_status, cp_name, cti, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryDeputeCorpEmp(PageInfo pi, Integer sp_id, Integer parent_sp_id, Integer cp_id,
			String bill_month, Integer scheme_id, Integer area_id, String searchWord) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryDeputeCorpEmpCount(sp_id, parent_sp_id, cp_id, bill_month, scheme_id, area_id,
				searchWord);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryDeputeCorpEmpList(sp_id, parent_sp_id, cp_id, bill_month,
				scheme_id, area_id, searchWord, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryBillPiadList(PageInfo pi, Map<String, Object> whereMap) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryBillPiadListCount(whereMap);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryBillPiadList(whereMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryPaidDetailList(PageInfo pi, Map<String, Object> whereMap) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryPaidDetailListCount(whereMap);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryPaidDetailList(whereMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryMarginDetailList(PageInfo pi, Map<String, Object> whereMap) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryMarginDetailListCount(whereMap);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryMarginDetailList(whereMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

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
	@Override
	public Map<String, Object> queryCmBillMargeDetail(Integer spid, Integer cmid, Integer bill_id) {
		return spsBillDao.queryCmBillMargeDetail(spid, cmid, bill_id);
	}

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
	@Override
	public int modifyPaidEvidence(ContextInfo cti, Map<String, Object> whereMap) {
		return spsBillDao.modifyPaidEvidence(cti, whereMap);
	}

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
	@Override
	public PageModel queryDeputePiadList(PageInfo pi, Map<String, Object> whereMap, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryDeputePiadListCount(whereMap, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryDeputePiadList(whereMap, pageIndex, pageSize, cti);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryDeputePaidDetailList(PageInfo pi, Map<String, Object> whereMap, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryDeputePaidDetailListCount(whereMap, cti);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryDeputePaidDetailList(whereMap, cti, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public PageModel queryPaidDetailOfDeputeList(PageInfo pi, Map<String, Object> whereMap) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryPaidDetailOfDeputeListCount(whereMap);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryPaidDetailOfDeputeList(whereMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public Object queryMarginDetailOfDeputeList(PageInfo pi, Map<String, Object> whereMap) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsBillDao.queryMarginDetailOfDeputeListCount(whereMap);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsBillDao.queryMarginDetailOfDeputeList(whereMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public Result analysis(ContextInfo cti ,Integer accountId, Integer fileid, Integer spId) {
		Result result = Result.createResult().setSuccess(true);
		try {
			if (null == fileid) {
				result.setError("fileId不能为空！");
				result.setSuccess(false);
				return result;
			}
			SysUploadfile vo = new SysUploadfile();
			vo.setId(Integer.valueOf(fileid));
			SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);
			// 获取文件目录
			String filePath = sysUploadfileService.getPhysicsFile(fileid);
			if (null == uploadFile) {
				result.setError("文件不存在！");
				result.setSuccess(false);
				return result;
			}
			if (null == filePath || "".equals(filePath)) {
				result.setError("文件不存在！");
				result.setSuccess(false);
				return result;
			}
			List<PaidBillDto> paidBillDtos = new ArrayList<>();
			Map<String, Map<String, Map<String, String>>> aa = ImportExcelUtil.importExcels(filePath, null);
			for (Map.Entry<String, Map<String, Map<String, String>>> entry : aa.entrySet()) {
				for (Map.Entry<String, Map<String, String>> c_entry : entry.getValue().entrySet()) {
					if ("姓名".equals(c_entry.getValue().get("姓名")))
						continue;
					ObjectMapper showAllMapper = new ObjectMapper();
					showAllMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					showAllMapper.setAnnotationIntrospector(new JacksonAnnotationFieldUtil(c_entry.getValue()));
					PaidBillDto paidBillDto = showAllMapper.readValue(JSON.toJSONString(c_entry.getValue()),
							PaidBillDto.class);
					paidBillDto.setSource(3);
					paidBillDtos.add(paidBillDto);
				}
			}
			// 持久化
			if (null != paidBillDtos && !paidBillDtos.isEmpty()) {
				savePaidBillDetail(cti,paidBillDtos, spId);
			} else {
				result.setError("未解析到任何数据");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("数据格式错误");
			result.setSuccess(false);
		}
		return result;
	}

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
	@Override
	public void savePaidBillDetail(ContextInfo cti, List<PaidBillDto> paidBillDtos, Integer spId) {
		// 根据库ID查询库下的员工列表
		Map<String, CmEmployee> cmEmployeeMap = null;
		if (null != spId)
			cmEmployeeMap = cmEmployeeService.queryAllEmpBySpId(spId);
		for (PaidBillDto paidBillDto : paidBillDtos) {
			if (null == paidBillDto.getEmpId()) {
				CmEmployee cmEmployee = cmEmployeeMap.get(paidBillDto.getName() + paidBillDto.getIdentityCode());
				if (null == cmEmployee)
					continue;
				// 持久化
				paidBillDto.setEmpId(cmEmployee.getEmpId());
				paidBillDto.setInsuranceLivingType(cmEmployee.getInsuranceLivingType());
			}
			SpsEmpActual spsEmpActual = new SpsEmpActual();
			BeanUtils.copyProperties(paidBillDto, spsEmpActual);
			// 标记删除
			int updateCount = spsEmpActualService.updateDrOfEmpActual(cti, spsEmpActual);
			int actualId = spsEmpActualService.insert(cti, spsEmpActual);
			if (actualId > 0 && !paidBillDto.queryActualDetail().isEmpty()) {
				for (Map.Entry<String, SpsEmpActualdetail> entry : paidBillDto.queryActualDetail().entrySet()) {
					if (null == entry.getValue())
						continue;
					entry.getValue().setActualId(spsEmpActual.getId());
					// id为自增，不需要赋值
					entry.getValue().setId(null);
					spsEmpActualdetailService.insert(cti, entry.getValue());
				}
			}

			// 回写下行数据标识
			if (spsEmpActual.getSource().equals(2)) {
				SpsTsPersonFlow personFlow = new SpsTsPersonFlow();
				personFlow.setMonth(paidBillDto.getPeriod().replace("-", ""));
				personFlow.setName(paidBillDto.getName());
				personFlow.setIdCard(paidBillDto.getIdentityCode());
				spsTsPersonFlowDao.updatePersonFlow(cti, personFlow);
			}
		}
	}

	/**
	 *  退回分包账单
	 *  @param   cti
	 *  @param   spsBill
	 *	@return 			: boolean
	 *  @createDate  	: 2016-12-27 13:54
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-27 13:54
	 *  @updateAuthor  :
	 */
	@Override
	public Result callBackBill(ContextInfo cti, SpsBill spsBill) {
		Result result = Result.createResult().setSuccess(true);
		String remark = spsBill.getRemark();
		spsBill = queryBillByBillNum(spsBill.getBillNum());
		if(null != spsBill){
			spsBill.setRemark(remark);
			spsBill.setBillState("GENERATED");
			update(cti,spsBill);
		}else{
			result.setError("不存在当前账单");
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 检查总包状态
	 * 
	 * @param bill_id,
	 *            sp_id
	 * @return : java.lang.Integer
	 * @createDate : 2016-11-10 14:26
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:26
	 * @updateAuthor :
	 */
	@Override
	public Integer checkSpsBillStatusByDeuptId(ContextInfo cti,Integer bill_id, Integer sp_id) {
		return spsBillDao.queryBillByDeputeBillIdCount(cti,bill_id, sp_id);
	}

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
	@Override
	public HashMap<String, Object> queryBillCount(HashMap<String, Object> parameterMap) {
		return spsBillDao.queryBillCount(parameterMap);
	}

	/**
	 * 首页--查询--账单数量
	 * 
	 * @param bill
	 * @return : Integer
	 * @createDate : 2016-11-10 14:29
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:29
	 * @updateAuthor :
	 */
	@Override
	public Integer queryBillCount(SpsBill bill) {
		return spsBillDao.queryBillListCount(bill);
	}

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
	@Override
	public HashMap<String, Object> queryBillMoney(HashMap<String, Object> parameterMap) {
		return spsBillDao.queryBillMoney(parameterMap);
	}

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
	@Override
	public HashMap<String, Object> queryServiceMoney(HashMap<String, Object> parameterMap) {
		return spsBillDao.queryServiceMoney(parameterMap);
	}

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
	@Override
	public List<Map<String, Object>> queryYearMoney(HashMap<String, Object> parameterMap) {
		return spsBillDao.queryYearMoney(parameterMap);
	}

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
	@Override
	public Map<String, Object> findPaidBillDetail(SpsBill bill) {
		return spsBillDao.findPaidBillDetail(bill);
	}

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
	@Override
	public PageModel findSpsBillByBilltype(PageInfo pi, SpsBill bill) {
		bill.setDr(0);
		bill.setBillState("SEND");
		bill.setDeputeTypeEq("HR");
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		pm.setTotal(spsBillDao.countFreeFind(bill));
		List<Map<String, Object>> datas = spsBillDao.findSpsBillByBilltype(bill, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

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
	@Override
	public Map<String, Object> queryCmBillDetail(Integer cpid, Integer bill_id) {
		return spsBillDao.queryCmBillDetail(cpid, bill_id);
	}

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
	@Override
	public Map<String, Object> queryBillSpan(Integer cpId) {
		return spsBillDao.queryBillSpan(cpId);
	}

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
	@Override
	public List<Map<String, Object>> queryDeputeBillList(String billNum) {
		return spsBillDao.queryDeputeBillList(billNum);
	}

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
	@Override
	public boolean paySpExpAmount(PageInfo pi,ContextInfo cti, SpsBill bill, Result result, Integer user_id, String sms) {
		Integer expendStatus = bill.getExpendStatus();
		bill.setExpendStatus(0);
		PageModel pm = FindManage(pi,bill);
		bill.setExpendStatus(expendStatus);
		boolean issuccess = false;
		if (null != pm && null != pm.getDatas() && !pm.getDatas().isEmpty()) {
			if (null != bill.getExpendStatus()) {
				Management curr_bill = (Management) pm.getDatas().get(0);
				if (curr_bill.getExpendStatus() == 0) {// 当前订单未请款
					if (bill.getExpendStatus() == 1) {
						issuccess = xfsPaySpFee(cti,bill, curr_bill, curr_bill.getExpendOfficeFee(), result);
						if (!issuccess) {
							throw new BusinessException("请款异常");
						} else if (null != sms && sms.equals("1")) {
							String expend = "官费";
							sendSysMessage(curr_bill, user_id, bill.getExpendStatus(), expend);
						}
					}
					update(cti, bill);
				}
			}
		} else {
			result.setError("参数有误");
		}
		return issuccess;
	}

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
	@Override
	public boolean payServiceExpAmount(PageInfo pi, ContextInfo cti, SpsBill bill, Result result, Integer user_id,
			String sms) {
		Integer expendServiceStatus = bill.getExpendServiceStatus();
		bill.setExpendServiceStatus(0);
		PageModel pm = FindServiceManage(pi, bill);
		bill.setExpendServiceStatus(expendServiceStatus);
		boolean issuccess = false;
		if (null != pm && null != pm.getDatas() && !pm.getDatas().isEmpty()) {
			if (null != bill.getExpendServiceStatus()) {
				Management curr_bill = (Management) pm.getDatas().get(0);
				if (curr_bill.getExpendServiceStatus() == 0) {// 当前订单未请款
					if (bill.getExpendServiceStatus() == 1) {
						issuccess = xfsPaySpFee(cti, bill, curr_bill, curr_bill.getExpendServiceFee(), result);
						if (!issuccess) {
							throw new BusinessException("请款异常");
						} else if (null != sms && sms.equals("1")) {
							String expend = "服务费";
							sendSysMessage(curr_bill, user_id, bill.getExpendServiceStatus(), expend);
						}
					}
					update(cti, bill);
				}
			}
		} else {
			result.setError("参数有误");
		}
		return issuccess;
	}

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
	@Override
	public Map<String, Object> queryServiceExpAmount() {
		return spsBillDao.queryServiceExpAmount();
	}

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
	@Override
	public Map<String, Object> queryServicePayAmount() {
		return spsBillDao.queryServicePayAmount();
	}

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
	@Override
	public Map<String, Object> queryServiceGainAmount() {
		return spsBillDao.queryServiceGainAmount();
	}

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
	@Override
	public PageModel FindManage(PageInfo pi, SpsBill vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillDao.FindManageCount(vo);
		pm.setTotal(total);
		List<Management> manage = spsBillDao.FindManage(vo, pageIndex, pageSize);
		pm.setDatas(manage);
		return pm;
	}



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
	@Override
	public Map<String, Object> queryExpAmount() {
		return spsBillDao.queryExpAmount();
	}

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
	@Override
	public Map<String, Object> queryPayAmount() {
		return spsBillDao.queryPayAmount();
	}

	/**
	 * 服务费查询
	 * 
	 * @param pi
	 * @param vo
	 * @return : com.xfs.common.page.PageModel
	 * @createDate : 2016-11-10 14:53
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 14:53
	 * @updateAuthor :
	 */
	@Override
	public PageModel FindServiceManage(PageInfo pi, SpsBill vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillDao.FindServiceManageCount(vo);
		pm.setTotal(total);
		List<Management> manage = spsBillDao.FindServiceManage(vo, pageIndex, pageSize);
		pm.setDatas(manage);
		return pm;
	}

	/**
	 * 创建应收账单
	 * 
	 * @param spId
	 * @param cpId
	 * @param period
	 * @param preBillId
	 * @return
	 */
	private SpsBill createPrePayBill(ContextInfo cti, Integer spId, Integer cpId, String period, Integer preBillId) {
		String billType = "PREPAY";
		// 作废原有账单
		SpsBill billqry = new SpsBill();
		billqry.setCpId(cpId);
		billqry.setSpId(spId);
		// billqry.setDr(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		if (StringUtils.isBlank(period))
			period = format.format(currTime);
		// 上托收取下一期间保险记录
		String nextperiod;
		{
			Calendar nextPeriod = Calendar.getInstance();
			try {
				nextPeriod.setTime(format.parse(period));
			} catch (ParseException e) {
				this.log.debug("计算上托收期间错误", e);
				throw new RuntimeException(e);
			}
			nextPeriod.add(Calendar.MONTH, 1);
			nextperiod = format.format(nextPeriod.getTime());
		}
		billqry.setPeriod(period);
		billqry.setBillType(billType);
		billqry.setDr(0);
		List<SpsBill> oldbills = spsBillDao.freeFind(billqry);

		// 判断账单是否已支付、或未退回
		if (oldbills != null) {
			for (SpsBill bill : oldbills) {
				if (null != bill.getFeePayStatus() && 1 == bill.getFeePayStatus().intValue()) {
					throw new BusinessException("账单已支付，不能重新生成");
				}
				if ("SP".equals(bill.getDeputeType())) {
					if (!StringUtils.isBlank(bill.getBillState()) && "SEND".equals(bill.getBillState())) {
						throw new BusinessException("委托账单已发送，不能重新生成");
					}
				}
			}
		}
		// 获取所有方案
		SpsScheme qry = new SpsScheme();
		qry.setSpId(spId);
		qry.setCpId(cpId);
		qry.setDr(0);
		// qry.setEffectiveDate(period);
		// qry.setExpireDate(period);

		// 创建账单实例
		SpsBill bill = new SpsBill();
		bill.setCpId(cpId);
		bill.setSpId(spId);
		bill.setPeriod(period);
		bill.setBillState("GENERATED");
		bill.setBillType(billType);
		bill.setBuildDate(new Date());
		bill.setDr(0);
		// 持久化 需要先产生billid
		save(cti, bill);

		// 官费
		BigDecimal officeFee = BigDecimal.ZERO;
		// 服务费
		BigDecimal serviceFee = BigDecimal.ZERO;

		List<SpsScheme> schemes = spsSchemeService.findAll(qry);
		if (null == schemes)
			throw new BusinessException("当前企业没有有效的服务方案!");
		// 判断分包账单是否已全部生成
		Integer deputeId = null;
		String deputeType = "HR";
		boolean hasENTRUSTED = false;
		boolean hasDIY = false;
		for (SpsScheme scheme : schemes) {
			if ("DIY".equals(scheme.getSchemeType()) || ("DEPUTE".equals(scheme.getSchemeType())))
				hasDIY = true;
			else if ("ENTRUSTED".equals(scheme.getSchemeType()))
				hasENTRUSTED = true;
			if (hasENTRUSTED && hasDIY) {
				throw new BusinessException("同一企业不能同时含有自营和受托方案!");
			}
			if ("ENTRUSTED".equals(scheme.getSchemeType())) {
				deputeType = "ENTRUSTED";
				if (null == deputeId)
					deputeId = scheme.getParentSpId();
				if (!deputeId.equals(scheme.getParentSpId()))
					throw new BusinessException("请选择相同委托方服务方案生成账单!");
			}
			if ("DEPUTE".equals(scheme.getSchemeType())) {
				billqry = new SpsBill();
				billqry.setPeriod(period);
				billqry.setDr(0);
				billqry.setCpId(cpId);
				billqry.setBillTypeEq("PREPAY");
				SpsScheme child = spsSchemeService.findByParentSchemeId(spId, scheme.getSchemeId());

				billqry.setSpId(child.getSpId());
				billqry.setBillType(billType);
				billqry.setBillState("SEND");
				List<SpsBill> bills = spsBillDao.freeFind(billqry);
				if (bills == null || bills.size() == 0) {
					throw new BusinessException(String.format("方案%s的委托账单还未收到，请联系合作方!", scheme.getName()));
				}
			}
			if ("MONTH".equals(scheme.getBillType())) {
				// 校验任务单
				SpsTask taskqry = new SpsTask();
				taskqry.setSchemeId(scheme.getSchemeId());
				taskqry.setExecuteDate(period);
				taskqry.setTypeEq("TODO");
				Integer taskcount = spsTaskService.freeFindCount(taskqry);
				if (null != taskcount && taskcount > 0) {
					throw new BusinessException(String.format("方案%s本月还有未处理完的任务单，不能生成应收账单!", scheme.getName()));
				}
			}
		}
		// 作废原有账单
		if (oldbills != null) {
			for (SpsBill oldbill : oldbills) {
				oldbill.setDr(1);
				// 账单号不变 获取原账单账单号
				bill.setBillNum(oldbill.getBillNum());
				this.save(cti, oldbill);
				// 未付款情况下 作废应收账单对应的实缴账单
				SpsBill realBill = new SpsBill();
				realBill.setBillType("PAID");
				realBill.setRelBillId(oldbill.getBillId());
				realBill.setDr(0);
				List<SpsBill> reals = this.findAll(realBill);
				if (null != reals && reals.size() > 0) {
					realBill = reals.get(0);
					realBill.setDr(1);
					save(cti, realBill);
				}
				// 作废应收账单对应的差额账
				SpsBillMargin oldMargin = new SpsBillMargin();
				oldMargin.setPrepayBillId(oldbill.getBillId());
				oldMargin.setDr(0);
				oldMargin.setDeputeBillId(realBill.getBillId());
				List<SpsBillMargin> oldMargins = spsBillMarginService.findAll(oldMargin);
				if (null!=oldMargins && oldMargins.size()>0) {
					oldMargin = oldMargins.get(0);
					if("OFFSETED".equals(oldMargin.getStatus())) {
						throw new BusinessException("次月已生成应收账单,不能重复生成!");
					}
					else {
						oldMargin.setDr(1);
						spsBillMarginService.save(cti, oldMargin);
					}
				}
				// 回退上月账单核销状态
				if (!StringUtils.isBlank(oldbill.getOffsetIds())) {
					String[] ids = oldbill.getOffsetIds().split(",");
					for (String id : ids) {
						// 如果已被核销，报错
						// 回滚前一月差额账单核销状态
						SpsBillMargin marginqry = new SpsBillMargin();
						marginqry.setId(Integer.valueOf(id));
						List<SpsBillMargin> margins = spsBillMarginService.findAll(marginqry);
						if (null != margins) {
							for (SpsBillMargin margin : margins) {
								margin.setStatus("UNOFFSET");
								margin.setOffsetType("");
								spsBillMarginService.save(cti, margin);
							}
						}
					}
				}
			}
		}

		// 账单员工
		List<SpsBillEmp> billEmps = new ArrayList<>();
		Map<Integer, SpsBillEmp> billEmpsMap = new HashMap<Integer, SpsBillEmp>();
		// 补缴记录 避免社保公积金分开形成两条记录，及差额账关联错误
		Map<String, SpsBillEmp> patchEmpsMap = new HashMap<String, SpsBillEmp>();
		// 服务方案Map
		Map<Integer, SpsScheme> schemeMap = new HashMap<>();
		// 员工服务方案
		Map<Integer, SpsScheme> empscheme = new HashMap<>();

		// 获取服务商有权限管理的所有员工
		for (SpsScheme scheme : schemes) {
			// 获取地区规则
			BdBillrule billrule = bdBillruleService.findByAreaId(scheme.getAreaId());
			String insureMonth;
			if (BdBillrule.insuranceRule_CURMONTH.equals(billrule.getInsuranceRule())) {
				insureMonth = period;
			} else {
				insureMonth = nextperiod;
			}
			String fundMonth;
			if (BdBillrule.fundRule_CURMONTH.equals(billrule.getFundRule())) {
				fundMonth = period;
			} else {
				fundMonth = nextperiod;
			}
			// List<BdInsurancearea> insArea =
			// bdInsuranceareaService.findBdInsuranceareaByAreaid(scheme.getAreaId());
			List<Integer> empIds = new ArrayList<Integer>();
			// 账单日取最早
			if (null != scheme.getBillDate()) {
				if (null == bill.getBillDay())
					bill.setBillDay(scheme.getBillDate());
				else {
					if (bill.getBillDay() > scheme.getBillDate())
						bill.setBillDay(scheme.getBillDate());
				}
			}
			schemeMap.put(scheme.getSchemeId(), scheme);
			SpsSchemeEmp seqry = new SpsSchemeEmp();
			seqry.setSchemeId(scheme.getSchemeId());
			seqry.setState("USE");
			seqry.setDr(0);
			List<SpsSchemeEmp> ssemps = spsSchemeEmpService.findAll(seqry);
			if (null != ssemps) {
				for (SpsSchemeEmp emp : ssemps) {
					empIds.add(emp.getEmpId());
					empscheme.put(emp.getEmpId(), scheme);
				}
			}

			List<CmEmployee> emps = new ArrayList<>();
			Map<Integer, CmEmployee> empMap = new HashMap<>();
			if (null != empIds && empIds.size() > 0) {
				emps = cmEmployeeService.findEmpEntityBySchemeId(scheme.getSchemeId());
				if (null != emps && emps.size() > 0) {
					for (CmEmployee emp : emps) {
						empMap.put(emp.getEmpId(), emp);
					}
				}
				// 根据账单规则获取社保、公积金数据，合并
				List<CmEmployee> insuranceEmps = new ArrayList<>();
				insuranceEmps = cmEmployeeService.findEmpListWithDetailByEmpids(empIds, insureMonth, "INSURANCE");
				if (null != emps && emps.size() > 0) {
					for (CmEmployee insuranceEmp : insuranceEmps) {
						CmEmployee emp = empMap.get(insuranceEmp.getEmpId());
						if (null != insuranceEmp.getCmEmployeeInsurances()
								&& insuranceEmp.getCmEmployeeInsurances().size() > 0) {
							for (CmEmployeeInsurance detail : insuranceEmp.getCmEmployeeInsurances()) {
								if (emp.getCmEmployeeInsurances() == null) {
									emp.setCmEmployeeInsurances(new ArrayList<CmEmployeeInsurance>());
								}
								emp.getCmEmployeeInsurances().add(detail);
							}
						}
					}
				}
				List<CmEmployee> fundEmps = new ArrayList<>();
				fundEmps = cmEmployeeService.findEmpListWithDetailByEmpids(empIds, fundMonth, "FUND");
				if (null != emps && emps.size() > 0) {
					for (CmEmployee fundEmp : fundEmps) {
						CmEmployee emp = empMap.get(fundEmp.getEmpId());
						if (null != fundEmp.getCmEmployeeInsurances() && fundEmp.getCmEmployeeInsurances().size() > 0) {
							for (CmEmployeeInsurance detail : fundEmp.getCmEmployeeInsurances()) {
								if (emp.getCmEmployeeInsurances() == null) {
									emp.setCmEmployeeInsurances(new ArrayList<CmEmployeeInsurance>());
								}
								emp.getCmEmployeeInsurances().add(detail);
							}
						}
					}
				}
			}
			// 总包方案直接获取分包账单官费
			if ("DEPUTE".equals(scheme.getSchemeType())) {
				SpsBill deputebill = new SpsBill();
				deputebill.setPeriod(period);
				deputebill.setDr(0);
				deputebill.setCpId(cpId);
				deputebill.setBillTypeEq("PAID");
				SpsScheme child = spsSchemeService.findByParentSchemeId(spId, scheme.getSchemeId());
				deputebill.setSpId(child.getSpId());
				deputebill.setBillType(billType);
				deputebill.setBillState("SEND");
				List<SpsBill> bills = spsBillDao.freeFind(billqry);
				if (null != bills && bills.size() > 0) {
					deputebill = bills.get(0);
					List<SpsBillEmp> deputeEmps = spsBillEmpService.findBillEmpWithDetails(deputebill.getBillId());
					if (null != deputeEmps) {
						for (SpsBillEmp emp : deputeEmps) {
							SpsBillEmp billemp = new SpsBillEmp();
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(emp.getFundaccountId());
							billemp.setFundArea(emp.getFundArea());
							billemp.setFundBase(emp.getFundBase());

							billemp.setInsuranceaccountId(emp.getInsuranceaccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(emp.getInsuranceArea());
							billemp.setInsuranceBase(emp.getInsuranceBase());
							billemp.setInsuredMonth(emp.getInsuredMonth());
							billemp.setPayType("MONTH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceIsNew(emp.getInsuranceIsNew());
							billemp.setFundIsNew(emp.getFundIsNew());
							if (null != emp.getSpsBillDetails()) {
								for (SpsBillDetail insurance : emp.getSpsBillDetails()) {
									SpsBillDetail detail = new SpsBillDetail();
									billemp.getSpsBillDetails().add(detail);
									detail.setInsuranceType(insurance.getInsuranceType());
									detail.setCorpPaybase(insurance.getCorpPaybase());
									detail.setEmpPaybase(insurance.getEmpPaybase());
									detail.setCorpRatio(insurance.getCorpRatio());
									detail.setCorpPayment(insurance.getCorpPayment());
									detail.setEmpRatio(insurance.getEmpRatio());
									detail.setEmpPayment(insurance.getEmpPayment());
									detail.setCorpAddition(insurance.getCorpAddition());
									detail.setPsnAddition(insurance.getPsnAddition());
									if ("COMMON".equals(scheme.getFundType()) && "FUND".equals(bdInsuranceService
											.findByPK(insurance.getInsuranceType()).getInsuranceFundType())) {
										billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
									} else if ("COMMON".equals(scheme.getInsuranceType())) {
										billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
									}

								}
							}

							if (null != billemp.getSpsBillDetails() && billemp.getSpsBillDetails().size() > 0) {
								// 有保险数据则计入账单
								billEmps.add(billemp);
								billEmpsMap.put(billemp.getEmpId(), billemp);
								serviceFee = serviceFee.add(billemp.getServiceFee());
								officeFee = Arith.add(officeFee, billemp.getFundOfficialFee(),
										billemp.getInsuranceOfficialFee());
							}
						}
					}
				}
			} else {
				if (null != emps) {
					for (CmEmployee emp : emps) {
						// 由险种反推，如果有明细数据则持久化该员工
						// if ("OFF".equals(emp.getInsuranceState()) &&
						// "OFF".equals(emp.getFundState()))
						// continue;
						// // 实缴账单和月报只包含正常缴纳部分
						// if ("PAID".equals(billType) ||
						// "MONTH".equals(scheme.getBillType())) {
						// if (!"ON".equals(emp.getInsuranceState()) ||
						// !"ON".equals(emp.getFundState()))
						// continue;
						// }
						SpsBillEmp billemp = new SpsBillEmp();
						billemp.setEmpId(emp.getEmpId());

						billemp.setIdentityCode(emp.getIdentityCode());
						billemp.setIdentityType(emp.getIdentityType());

						billemp.setFundaccountId(scheme.getFundAccountId());
						// todo 读取库中的地区
						billemp.setFundArea(scheme.getAreaId());
						billemp.setFundBase(emp.getFundSalary());

						billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
						// todo 读取库中的地区
						billemp.setInsuranceArea(scheme.getAreaId());
						billemp.setInsuranceBase(emp.getInsuranceSalary());
						billemp.setInsuredMonth(insureMonth);
						billemp.setPayType("MONTH");
						billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

						billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
						billemp.setFundOfficialFee(BigDecimal.ZERO);
						billemp.setInsuranceOfficialFee(BigDecimal.ZERO);
						if (insureMonth.equals(emp.getInsurancePeriod()))
							billemp.setInsuranceIsNew("Y");
						if (fundMonth.equals(emp.getFundPeriod()))
							billemp.setFundIsNew("Y");
						if (null != emp.getCmEmployeeInsurances()) {
							for (CmEmployeeInsurance insurance : emp.getCmEmployeeInsurances()) {
								BdInsurance bdins = bdInsuranceService.findByPK(insurance.getInsuranceId());
								if (null == insurance.getRatioId())
									throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",
											emp.getName(), bdins.getName()));
								BsArearatio ratio = bsArearatioService.findByPK(insurance.getRatioId());
								if (null == ratio)
									throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",
											emp.getName(), bdins.getName()));
								SpsBillDetail detail = new SpsBillDetail();
								detail.setInsuranceType(insurance.getInsuranceId());
								detail.setCorpPaybase(insurance.getCorpPaybase());
								detail.setEmpPaybase(insurance.getEmpPaybase());
								detail.setCorpRatio(insurance.getCorpRatio());
								detail.setEmpRatio(insurance.getEmpRatio());
								detail.setCorpPayment(insurance.getCorpPayment());
								detail.setEmpPayment(insurance.getEmpPayment());
								detail.setCorpAddition(insurance.getCorpAddition());
								detail.setPsnAddition(insurance.getPsnAddition());
								String month;
								if ("FUND".equals(bdins.getInsuranceFundType()))
									month = fundMonth;
								else
									month = insureMonth;
								if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())
										|| ratio.billingCycle_YEAR_ANNUALY.equals(ratio.getBillingCycle())) {
									try {
										// 缴费参保月份等于年缴险种月份 或者 新参保员工
										if (((format.parse(month).getMonth() + 1) == ratio.getPayPeriod())
												|| month.equals(insurance.getBeginPeriod())) {
											// 年缴不足一年按年等同于月缴 不需要处理
											// 年缴不足一年按月
											if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())) {
												if (StringUtils.isNotBlank(insurance.getBeginPeriod())) {
													// 缴费月份-入职月份 如果 小于等于0 则 +12
													int months = ratio.getPayPeriod()
															- format.parse(insurance.getBeginPeriod()).getMonth() - 1;
													if (months <= 0)
														months += 12;
													detail.setCorpPayment(Arith.div(insurance.getCorpPayment(),
															new BigDecimal(12.0 / months)));
													detail.setCorpAddition(Arith.div(insurance.getCorpAddition(),
															new BigDecimal(12.0 / months)));
													detail.setEmpPayment(Arith.div(insurance.getEmpPayment(),
															new BigDecimal(12.0 / months)));
													detail.setPsnAddition(Arith.div(insurance.getPsnAddition(),
															new BigDecimal(12.0 / months)));
												}
											}

										} else {
											detail.setCorpPayment(BigDecimal.ZERO);
											detail.setEmpPayment(BigDecimal.ZERO);
										}
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										log.error("日期计算出错", e);
										throw new RuntimeException(e);
									}
								}
								if (null != detail) {
									if (Arith.ignoreNull(detail.getCorpPayment()).doubleValue() > 0 || Arith.ignoreNull(detail.getEmpPayment()).doubleValue() > 0) {
										billemp.getSpsBillDetails().add(detail);
										if ("COMMON".equals(scheme.getFundType())
												&& "FUND".equals(bdins.getInsuranceFundType())) {
											billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
													detail.getCorpPayment(), detail.getEmpPayment()));
										} else if ("COMMON".equals(scheme.getInsuranceType())) {
											billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
													detail.getCorpPayment(), detail.getEmpPayment()));
										}
									}
								}
							}
						}

						if (null != billemp.getSpsBillDetails() && billemp.getSpsBillDetails().size() > 0) {
							// 有保险数据则计入账单
							billEmps.add(billemp);
							billEmpsMap.put(billemp.getEmpId(), billemp);
							serviceFee = serviceFee.add(billemp.getServiceFee());
							officeFee = Arith.add(officeFee, billemp.getFundOfficialFee(),
									billemp.getInsuranceOfficialFee());
						}

					}
				}
			}
			// }
			// 获取一次性费用
			List<SpsFeeEmponce> emponces = spsFeeEmponceService.queryEmponceWithDetails(spId, cpId, period, empIds);
			if (null != emponces) {
				for (SpsFeeEmponce once : emponces) {
					// 一次性官费
					SpsBillEmp billemp;
					if (StringUtils.isBlank(once.getPayType()) || "MONTH".equals(once.getPayType())) {
						billemp = billEmpsMap.get(once.getEmpId());
						if (null == billemp) {
							billemp = new SpsBillEmp();
							CmEmployee emp = empMap.get(once.getEmpId());
							if (null == emp)
								continue;
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(scheme.getFundAccountId());
							// todo 读取库中的地区
							billemp.setFundArea(scheme.getAreaId());
							billemp.setFundBase(emp.getFundSalary());

							billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(scheme.getAreaId());
							billemp.setInsuranceBase(emp.getInsuranceSalary());
							billemp.setInsuredMonth(period);
							billemp.setPayType("MONTH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(BigDecimal.ZERO);
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);

							billemp.setAdjustReason(once.getReason());

							// 循环一次性费用 创建的员工加入map，避免一条一次性费用创建一个账单员工
							billEmpsMap.put(billemp.getEmpId(), billemp);

						}
						// 小库不应有一次性官费，如果有计入 类似深圳重疾险 TODO
						// 官费未分社保公积金，所以全部加入社保 TODO
						// if ("OFFICE".equals(once.getFeeType())) {
						if (Arith.ignoreNull(once.getOfficialFee()).doubleValue() != 0) {
							billemp.setInsuranceOfficialFee(
									Arith.add(billemp.getInsuranceOfficialFee(), once.getOfficialFee()));
							officeFee = Arith.add(officeFee, once.getOfficialFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整官费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getOfficialFee())));
							} else {
								billemp.setAdjustReason(
										billemp.getAdjustReason().concat(String.format("调整官费原因：%s 金额: %s;",
												once.getReason(), Arith.ignoreNull(once.getOfficialFee()))));
							}
						}
						// } else {
						if (Arith.ignoreNull(once.getServiceFee()).doubleValue() != 0) {
							billemp.setServiceFee(Arith.add(billemp.getServiceFee(), once.getServiceFee()));
							serviceFee = Arith.add(serviceFee, once.getServiceFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整服务费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getServiceFee())));
							} else {
								billemp.setAdjustReason(billemp.getAdjustReason().concat(String.format("调整服务费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getServiceFee()))));
							}
						}
					} else {
						// 补缴官费
						billemp = patchEmpsMap.get(String.format("%s_%s", once.getEmpId(), once.getInsuredMonth()));
						if (billemp == null) {
							billemp = new SpsBillEmp();
							CmEmployee emp = empMap.get(once.getEmpId());
							if (null == emp)
								continue;
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(scheme.getFundAccountId());
							// todo 读取库中的地区
							billemp.setFundArea(scheme.getAreaId());

							billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(scheme.getAreaId());
							billemp.setInsuredMonth(once.getInsuredMonth());
							billemp.setPayType("PATCH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
							serviceFee = Arith.add(serviceFee, billemp.getServiceFee());
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);

							//billemp.setAdjustReason(once.getReason());
							patchEmpsMap.put(String.format("%s_%s", once.getEmpId(), once.getInsuredMonth()), billemp);
						}

						if (Arith.ignoreNull(once.getOfficialFee()).doubleValue() != 0 && !"1".equals(String.valueOf(once.getSource()))) {
							billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(), once.getOfficialFee()));
							officeFee = Arith.add(officeFee, once.getOfficialFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整官费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getOfficialFee())));
							} else {
								billemp.setAdjustReason(billemp.getAdjustReason().concat(String.format("调整官费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getOfficialFee()))));
							}
						}
						if (Arith.ignoreNull(once.getServiceFee()).doubleValue() != 0) {
							billemp.setServiceFee(Arith.add(billemp.getServiceFee(), once.getServiceFee()));
							serviceFee = Arith.add(serviceFee, once.getServiceFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整服务费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getServiceFee())));
							} else {
								billemp.setAdjustReason(billemp.getAdjustReason().concat(String.format("调整服务费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getServiceFee()))));
							}
						}


						if (null != once.getSpsFeeDetails()) {
							for (SpsFeeEmponcedetail insurance : once.getSpsFeeDetails()) {
								// 实缴账单和月报只包含正常缴纳部分
								SpsBillDetail detail = new SpsBillDetail();
								billemp.getSpsBillDetails().add(detail);
								detail.setInsuranceType(insurance.getInsuranceType());
								detail.setCorpPaybase(insurance.getCorpPaybase());
								detail.setEmpPaybase(insurance.getEmpPaybase());
								detail.setCorpRatio(insurance.getCorpRatio());
								detail.setCorpPayment(insurance.getCorpPayment());
								detail.setEmpRatio(insurance.getEmpRatio());
								detail.setEmpPayment(insurance.getEmpPayment());
								detail.setCorpAddition(insurance.getCorpAddition());
								detail.setPsnAddition(insurance.getPsnAddition());
								if ("FUND".equals(bdInsuranceService.findByPK(insurance.getInsuranceType())
										.getInsuranceFundType())) {
									if ("COMMON".equals(scheme.getFundType())) {
										billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
										officeFee = Arith.add(officeFee, insurance.getCorpPayment(),
												insurance.getEmpPayment());
									}
									billemp.setFundBase(once.getSalary());
								} else {
									if ("COMMON".equals(scheme.getInsuranceType())) {
										billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
										officeFee = Arith.add(officeFee, insurance.getCorpPayment(),
												insurance.getEmpPayment());
									}
									billemp.setInsuranceBase(once.getSalary());
								}
							}
						}
					}
					// 补缴数据添加到存储对象 不放map
					billEmps.add(billemp);
				}
			}

		}
		// 配置委托方，及委托方id
		if ("ENTRUSTED".equals(deputeType)) {
			bill.setDeputeType("SP");
			bill.setDeputeSpId(deputeId);
		} else {
			bill.setDeputeType("HR");
		}

		// 账单项目
		List<SpsBillitem> billitems = new ArrayList<>();

		// 标准服务费项目
		{
			SpsBillitem item = new SpsBillitem();
			item.setFee(serviceFee);
			item.setName("标准服务费");
			item.setSource(0);
			billitems.add(item);
		}
		// 标准官费项目
		{
			SpsBillitem item = new SpsBillitem();
			item.setFee(officeFee);
			item.setName("代扣代缴");
			item.setSource(0);
			billitems.add(item);
		}

		// 获取一次性企业费用
		SpsFeeCorponce corponce = new SpsFeeCorponce();
		corponce.setCpId(cpId);
		corponce.setSpId(spId);
		corponce.setPeriod(period);
		corponce.setDr(0);
		List<SpsFeeCorponce> corponces = spsFeeCorponceService.findAll(corponce);
		// 企业一次性费用
		if (null != corponces) {
			for (SpsFeeCorponce corpfee : corponces) {
				SpsBillitem item = new SpsBillitem();
				item.setFee(Arith.add(corpfee.getOfficialFee(), corpfee.getServiceFee()));
				item.setName(corpfee.getItem());
				item.setSource(0);
				officeFee = Arith.add(officeFee, corpfee.getOfficialFee());
				serviceFee = Arith.add(serviceFee, corpfee.getServiceFee());
				billitems.add(item);
			}
		}

		// 核销金额
		BigDecimal marginServiceFee = BigDecimal.ZERO;
		BigDecimal marginOfficeFee = BigDecimal.ZERO;
		// 上月差额账单 核销
		SpsBillMargin marginqry = new SpsBillMargin();
		marginqry.setSpId(spId);
		// Calendar cal = Calendar.getInstance();
		// try {
		// cal.setTime(format.parse(period));
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// throw new RuntimeException(e);
		// }
		// cal.add(Calendar.MONTH, -1);
		marginqry.setStatusEq("UNOFFSET");
		if ("HR".equals(deputeType)) {
			marginqry.setDeputeId(cpId);
		} else {
			marginqry.setDeputeId(deputeId);
		}
		marginqry.setDeputeTypeEq(deputeType);
		marginqry.setDr(0);
		// marginqry.setPeriod(format.format(cal.getTime()));
		List<SpsBillMargin> margins = spsBillMarginService.findAll(marginqry);
		StringBuffer marginIds = new StringBuffer();
		if (null != margins) {
			for (SpsBillMargin margin : margins) {
				try {
					// 应收实缴不按顺序生成，核销以前月份差额
					if (format.parse(margin.getPeriod()).compareTo(format.parse(period)) < 0) {
						margin.setStatus("OFFSETED");
						margin.setOffsetType("PREPAY");
						marginServiceFee = Arith.add(marginServiceFee, margin.getServiceFee());
						marginOfficeFee = Arith.add(marginOfficeFee, margin.getOfficeFee());
						marginIds.append("," + margin.getId());
						spsBillMarginService.save(cti, margin);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
			if (marginIds.length() > 0) {
				bill.setOffsetIds(marginIds.substring(1));
			}
		}
		bill.setMarginOfficeFee(marginOfficeFee);
		bill.setMarginServiceFee(marginServiceFee);
		bill.setBillOfficeFee(officeFee);
		bill.setBillServiceFee(serviceFee);
		bill.setPerpayOfficeFee(Arith.sub(officeFee, marginOfficeFee));
		bill.setPerpayServiceFee(Arith.sub(serviceFee, marginServiceFee));
		bill.setBillamount(Arith.add(bill.getPerpayOfficeFee(), bill.getPerpayServiceFee()));

		if ("HR".equals(bill.getDeputeType())) {
			billqry = new SpsBill();
			billqry.setPeriod(period);
			billqry.setDr(0);
			billqry.setCpId(cpId);
			billqry.setDeputeSpId(spId);
			billqry.setDeputeType("SP");
			billqry.setBillState("SEND");
			List<SpsBill> bills = spsBillDao.freeFind(billqry);
			BigDecimal deputeOffice = BigDecimal.ZERO;
			BigDecimal deputeService = BigDecimal.ZERO;
			StringBuffer entrusted_bill_ids = new StringBuffer();
			if (null != bills) {
				for (SpsBill deputeBill : bills) {
					deputeOffice = Arith.add(deputeOffice, deputeBill.getPerpayOfficeFee());
					deputeService = Arith.add(deputeService, deputeBill.getPerpayServiceFee());
					// TODO 记录分包商账单id
					entrusted_bill_ids.append(deputeBill.getBillId()).append(",");
				}
				if (entrusted_bill_ids.length() > 0)
					entrusted_bill_ids.deleteCharAt(entrusted_bill_ids.length() - 1);
			}
			bill.setEntrustedBillids(entrusted_bill_ids.toString());
			bill.setExpendOfficeFee(Arith.sub(bill.getPerpayOfficeFee(), deputeOffice));
			bill.setExpendServiceFee(Arith.sub(bill.getPerpayServiceFee(), deputeService));
		} else {
			bill.setExpendOfficeFee(bill.getPerpayOfficeFee());
			bill.setExpendServiceFee(bill.getPerpayServiceFee());
		}

		// 如果不是重新生成的账单，从redis获取账单号
		if (StringUtils.isBlank(bill.getBillNum()))
			bill.setBillNum(TransactionUtil.getTransactionNumber("billNumber", BusinessCode.BILL.getValue()));

		// 持久化
		save(cti, bill);

		if (null != billEmps) {
			for (SpsBillEmp emp : billEmps) {
				emp.setBillId(bill.getBillId());
				spsBillEmpService.save(cti, emp);
				if (null != emp.getSpsBillDetails()) {
					for (SpsBillDetail detail : emp.getSpsBillDetails()) {
						detail.setBillEmpId(emp.getId());
						spsBillDetailService.save(cti, detail);
					}
				}
			}
		}

		if (null != billitems) {
			for (SpsBillitem item : billitems) {
				item.setBillId(bill.getBillId());
				spsBillitemService.save(cti, item);
			}
		}

		try {
			// 数据创建生成后生成账单excel
			String fileid = generateExcel(cti,bill, String.valueOf(cmCorpService.findByPK(cpId).getCorpName()),
					bill.getPeriod(), bill.getDeputeType(), bill.getBillType());
			bill.setFileId(fileid);
		} catch (Exception e) {

		}

		spsBillDao.update(cti, bill);
		return bill;
	}

	/**
	 * 创建实缴账单
	 * 
	 * @param spId
	 * @param cpId
	 * @return
	 */
	private SpsBill createPaidBill(ContextInfo cti, Integer spId, Integer cpId, String period, Integer preBillId) {
		String billType = "PAID";
		// 作废原有账单
		SpsBill billqry = new SpsBill();
		billqry.setCpId(cpId);
		billqry.setSpId(spId);
		// billqry.setDr(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		if (StringUtils.isBlank(period))
			period = format.format(currTime);
		// 上托收取下一期间保险记录
		String nextperiod;
		{
			Calendar nextPeriod = Calendar.getInstance();
			try {
				nextPeriod.setTime(format.parse(period));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				this.log.debug("计算上托收期间错误", e);
				throw new RuntimeException(e);
			}
			nextPeriod.add(Calendar.MONTH, 1);
			nextperiod = format.format(nextPeriod.getTime());
		}
		billqry.setPeriod(period);
		billqry.setBillType(billType);
		billqry.setDr(0);
		List<SpsBill> oldbills = spsBillDao.freeFind(billqry);

		// 判断账单是否已支付、或未退回
		if (oldbills != null) {
			for (SpsBill bill : oldbills) {
				// if (null != bill.getFeePayStatus() && 1 ==
				// bill.getFeePayStatus().intValue()) {
				// throw new BusinessException("账单已支付，不能重新生成");
				// }
				if ("SP".equals(bill.getDeputeType())) {
					if (!StringUtils.isBlank(bill.getBillState()) && "SEND".equals(bill.getBillState())) {
						throw new BusinessException("委托账单已发送，不能重新生成");
					}
				}
			}
		}
		// 获取所有方案
		SpsScheme qry = new SpsScheme();
		qry.setSpId(spId);
		qry.setCpId(cpId);
		qry.setDr(0);
		// qry.setEffectiveDate(period);
		// qry.setExpireDate(period);

		// 创建账单实例
		SpsBill bill = new SpsBill();
		bill.setCpId(cpId);
		bill.setSpId(spId);
		bill.setPeriod(period);
		bill.setBillState("GENERATED");
		bill.setBillType(billType);
		bill.setBuildDate(new Date());
		bill.setDr(0);
		// 持久化 需要先产生billid
		save(cti, bill);

		// 官费
		BigDecimal officeFee = BigDecimal.ZERO;
		// 服务费
		BigDecimal serviceFee = BigDecimal.ZERO;

		List<SpsScheme> schemes = spsSchemeService.findAll(qry);
		if (null == schemes)
			throw new BusinessException("当前企业没有有效的服务方案!");
		// 判断分包账单是否已全部生成
		Integer deputeId = null;
		String deputeType = "HR";
		boolean hasENTRUSTED = false;
		boolean hasDIY = false;
		for (SpsScheme scheme : schemes) {
			if ("DIY".equals(scheme.getSchemeType()) || ("DEPUTE".equals(scheme.getSchemeType())))
				hasDIY = true;
			else if ("ENTRUSTED".equals(scheme.getSchemeType()))
				hasENTRUSTED = true;
			if (hasENTRUSTED && hasDIY) {
				throw new BusinessException("同一企业不能同时含有自营和受托方案!");
			}
			if ("ENTRUSTED".equals(scheme.getSchemeType())) {
				deputeType = "SP";
				if (null == deputeId)
					deputeId = scheme.getParentSpId();
				if (!deputeId.equals(scheme.getParentSpId()))
					throw new BusinessException("请选择相同委托方服务方案生成账单!");
			}
			if ("DEPUTE".equals(scheme.getSchemeType())) {
				billqry = new SpsBill();
				billqry.setPeriod(period);
				billqry.setDr(0);
				billqry.setCpId(cpId);

				SpsScheme child = spsSchemeService.findByParentSchemeId(spId, scheme.getSchemeId());

				billqry.setSpId(child.getSpId());
				billqry.setBillState("SEND");
				billqry.setBillTypeEq("PAID");
				List<SpsBill> bills = spsBillDao.freeFind(billqry);
				if (bills == null || bills.size() == 0) {
					throw new BusinessException(String.format("方案%s的委托账单还未收到，请联系合作方!", scheme.getName()));
				}
				for (SpsBill deputeBill : bills) {
					if (StringUtils.isNotBlank(deputeBill.getEvidenceIds())) {
						if (StringUtils.isBlank(bill.getEvidenceIds())) {
							bill.setEvidenceIds(deputeBill.getEvidenceIds());
						} else {
							bill.setEvidenceIds(bill.getEvidenceIds() + "," + deputeBill.getEvidenceIds());
						}
					}
				}
			}
			// 校验任务单
			SpsTask taskqry = new SpsTask();
			taskqry.setSchemeId(scheme.getSchemeId());
			taskqry.setExecuteDate(period);
			taskqry.setTypeEq("TODO");
			Integer taskcount = spsTaskService.freeFindCount(taskqry);
			if (null != taskcount && taskcount > 0) {
				throw new BusinessException(String.format("方案%s本月还有未处理完的任务单，不能生成实缴账单!", scheme.getName()));
			}
		}
		SpsBill preBill = null;
		preBill = new SpsBill();
		if (null == preBillId) {
			preBill.setCpId(cpId);
			preBill.setSpId(spId);
			preBill.setPeriod(period);
			preBill.setBillType("PREPAY");
		} else {
			preBill.setBillId(preBillId);
		}
		preBill.setDr(0);
		List<SpsBill> bills = spsBillDao.freeFind(preBill);
		if (null == bills && bills.size() > 0) {
			throw new BusinessException("应收账单未生成，不能生成实缴账单!");
		}
		preBill = bills.get(0);
		/*
		 * 经产品确认 实缴生成订单，与是否支付无关 if (null != preBill.getFeePayStatus() && 0 ==
		 * preBill.getFeePayStatus()) { throw new
		 * BusinessException("应收账单未付款，不能生成实缴账单!"); }
		 */

		// 作废原有账单
		if (oldbills != null) {
			for (SpsBill oldbill : oldbills) {
				// 账单号不变
				bill.setBillNum(oldbill.getBillNum());
				// 凭证附件不变
				if (StringUtils.isBlank(bill.getEvidenceIds())) {
					bill.setEvidenceIds(oldbill.getEvidenceIds());
				} else {
					bill.setEvidenceIds(bill.getEvidenceIds() + "," + oldbill.getEvidenceIds());
				}

				oldbill.setDr(1);
				this.save(cti, oldbill);
				// 作废当月差额账单
				SpsBillMargin marginqry = new SpsBillMargin();
				marginqry.setDeputeBillId(oldbill.getBillId());
				List<SpsBillMargin> margins = spsBillMarginService.findAll(marginqry);
				if (null != margins) {
					for (SpsBillMargin margin : margins) {
						margin.setDr(1);
						spsBillMarginService.save(cti, margin);
					}
				}
			}
		}
		// 去重凭证附件
		if (StringUtils.isNotBlank(bill.getEvidenceIds())) {
			HashMap<String, String> evidenceMap = new HashMap<>();
			String[] evidences = bill.getEvidenceIds().split(",");
			for (String evidence : evidences) {
				evidenceMap.put(evidence, evidence);
			}
			StringBuffer unduplicate = new StringBuffer();
			for (String evidence : evidenceMap.keySet()) {
				unduplicate.append(evidence);
				unduplicate.append(",");
			}
			bill.setEvidenceIds(unduplicate.substring(0, unduplicate.length() - 1));
		}
		// 配置委托方，及委托方id
		if ("SP".equals(deputeType)) {
			bill.setDeputeType("SP");
			bill.setDeputeSpId(deputeId);
		} else {
			bill.setDeputeType("HR");
		}

		// 账单员工
		List<SpsBillEmp> billEmps = new ArrayList<>();
		Map<Integer, SpsBillEmp> billEmpsMap = new HashMap<Integer, SpsBillEmp>();
		// 补缴记录 避免社保公积金分开形成两条记录，及差额账关联错误
		Map<String, SpsBillEmp> patchEmpsMap = new HashMap<String, SpsBillEmp>();

		// 获取服务商有权限管理的所有员工
		for (SpsScheme scheme : schemes) {
			// 获取地区规则
			BdBillrule billrule = bdBillruleService.findByAreaId(scheme.getAreaId());
			String insureMonth;
			if (BdBillrule.insuranceRule_CURMONTH.equals(billrule.getInsuranceRule())) {
				insureMonth = period;
			} else {
				insureMonth = nextperiod;
			}
			String fundMonth;
			if (BdBillrule.fundRule_CURMONTH.equals(billrule.getFundRule())) {
				fundMonth = period;
			} else {
				fundMonth = nextperiod;
			}

			List<BdInsurancearea> insArea = bdInsuranceareaService.findBdInsuranceareaByAreaid(scheme.getAreaId());
			// 账单日取最早
			if (null != scheme.getBillDate()) {
				if (null == bill.getBillDay())
					bill.setBillDay(scheme.getBillDate());
				else {
					if (bill.getBillDay() > scheme.getBillDate())
						bill.setBillDay(scheme.getBillDate());
				}
			}
			List<Integer> empIds = new ArrayList<Integer>();
			SpsSchemeEmp seqry = new SpsSchemeEmp();
			seqry.setSchemeId(scheme.getSchemeId());
			seqry.setState("USE");
			seqry.setDr(0);
			List<SpsSchemeEmp> ssemps = spsSchemeEmpService.findAll(seqry);
			if (null != ssemps) {
				for (SpsSchemeEmp emp : ssemps) {
					empIds.add(emp.getEmpId());
				}
			}

			List<CmEmployee> emps = new ArrayList<>();
			Map<Integer, CmEmployee> empMap = new HashMap<>();
			if (null != empIds && empIds.size() > 0) {
				emps = cmEmployeeService.findEmpEntityBySchemeId(scheme.getSchemeId());
				if (null != emps && emps.size() > 0) {
					for (CmEmployee emp : emps) {
						empMap.put(emp.getEmpId(), emp);
					}
				}
				// 根据账单规则获取社保、公积金数据，合并
				List<CmEmployee> insuranceEmps = new ArrayList<>();
				insuranceEmps = cmEmployeeService.findEmpListWithDetailByEmpids(empIds, insureMonth, "INSURANCE");
				if (null != emps && emps.size() > 0) {
					for (CmEmployee insuranceEmp : insuranceEmps) {
						CmEmployee emp = empMap.get(insuranceEmp.getEmpId());
						if (null != insuranceEmp.getCmEmployeeInsurances()
								&& insuranceEmp.getCmEmployeeInsurances().size() > 0) {
							for (CmEmployeeInsurance detail : insuranceEmp.getCmEmployeeInsurances()) {
								if (emp.getCmEmployeeInsurances() == null) {
									emp.setCmEmployeeInsurances(new ArrayList<CmEmployeeInsurance>());
								}
								emp.getCmEmployeeInsurances().add(detail);
							}
						}
					}
				}
				List<CmEmployee> fundEmps = new ArrayList<>();
				fundEmps = cmEmployeeService.findEmpListWithDetailByEmpids(empIds, fundMonth, "FUND");
				if (null != emps && emps.size() > 0) {
					for (CmEmployee fundEmp : fundEmps) {
						CmEmployee emp = empMap.get(fundEmp.getEmpId());
						if (null != fundEmp.getCmEmployeeInsurances() && fundEmp.getCmEmployeeInsurances().size() > 0) {
							for (CmEmployeeInsurance detail : fundEmp.getCmEmployeeInsurances()) {
								if (emp.getCmEmployeeInsurances() == null) {
									emp.setCmEmployeeInsurances(new ArrayList<CmEmployeeInsurance>());
								}
								emp.getCmEmployeeInsurances().add(detail);
							}
						}
					}
				}
			}

			Integer fundAccId = null;
			Integer insuranceAccId = null;
			if ("DEPUTE".equals(scheme.getSchemeType())) {
				SpsScheme childscheme = spsSchemeService.findByParentSchemeId(scheme.getSpId(), scheme.getSchemeId());
				if (null != childscheme) {
					fundAccId = childscheme.getFundAccountId();
					insuranceAccId = childscheme.getInsuranceAccountId();
				}
			} else {
				fundAccId = scheme.getFundAccountId();
				insuranceAccId = scheme.getInsuranceAccountId();
			}
			// 实缴账单 获取个人实缴明细 如果存在，使用实缴明细作为月缴
			boolean hasInsActual = false;
			if (spsEmpActualService.countByAccIdAndPeriod("INSURANCE", insuranceAccId, null, period, "MONTH") > 0)
				hasInsActual = true;
			boolean hasFundActual = false;
			if (spsEmpActualService.countByAccIdAndPeriod("FUND", null, fundAccId, period, "MONTH") > 0)
				hasFundActual = true;
			Map<Integer, SpsEmpActual> actualInsuranceMap = new HashMap<>();
			Map<Integer, SpsEmpActual> actualFundMap = new HashMap<>();
			if (null != empIds && empIds.size() > 0) {
				List<SpsEmpActual> actuals = null;
				// if
				// (BdBillrule.fundRule_CURMONTH.equals(billrule.getFundRule()))
				// {
				// actuals =
				// spsEmpActualService.findSpsEmpActualWithDetail(period,
				// "MONTH", empIds, "INSURANCE");
				// } else {
				// actuals =
				// spsEmpActualService.findSpsEmpActualWithDetail(nextperiod,
				// "MONTH", empIds, "INSURANCE");
				// }
				actuals = spsEmpActualService.findSpsEmpActualWithDetail(insuranceAccId, null, period, "MONTH", empIds,
						"INSURANCE");
				for (SpsEmpActual actual : actuals) {
					actualInsuranceMap.put(actual.getEmpId(), actual);
				}
				// if
				// (BdBillrule.fundRule_CURMONTH.equals(billrule.getFundRule()))
				// {
				// actuals =
				// spsEmpActualService.findSpsEmpActualWithDetail(period,
				// "MONTH", empIds, "FUND");
				// } else {
				// actuals =
				// spsEmpActualService.findSpsEmpActualWithDetail(nextperiod,
				// "MONTH", empIds, "FUND");
				// }
				actuals = spsEmpActualService.findSpsEmpActualWithDetail(null, fundAccId, period, "MONTH", empIds,
						"FUND");
				for (SpsEmpActual actual : actuals) {
					actualFundMap.put(actual.getEmpId(), actual);
				}
			}

			// 总包方案直接获取分包账单官费
			if ("DEPUTE".equals(scheme.getSchemeType())) {
				SpsBill deputebill = new SpsBill();
				deputebill.setPeriod(period);
				deputebill.setDr(0);
				deputebill.setCpId(cpId);
				deputebill.setBillTypeEq("PREPAY");
				SpsScheme child = spsSchemeService.findByParentSchemeId(spId, scheme.getSchemeId());
				deputebill.setSpId(child.getSpId());
				deputebill.setBillType(billType);
				deputebill.setBillState("SEND");
				List<SpsBill> deputebills = spsBillDao.freeFind(billqry);
				if (null != deputebills && deputebills.size() > 0) {
					deputebill = deputebills.get(0);
					List<SpsBillEmp> deputeEmps = spsBillEmpService.findBillEmpWithDetails(deputebill.getBillId());
					if (null != deputeEmps) {
						for (SpsBillEmp emp : deputeEmps) {
							SpsBillEmp billemp = new SpsBillEmp();
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(emp.getFundaccountId());
							billemp.setFundArea(emp.getFundArea());
							billemp.setFundBase(emp.getFundBase());

							billemp.setInsuranceaccountId(emp.getInsuranceaccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(emp.getInsuranceArea());
							billemp.setInsuranceBase(emp.getInsuranceBase());
							billemp.setInsuredMonth(insureMonth);
							billemp.setPayType("MONTH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);
							billemp.setFundIsNew(emp.getFundIsNew());
							billemp.setInsuranceIsNew(emp.getInsuranceIsNew());
							if (null != emp.getSpsBillDetails()) {
								for (SpsBillDetail insurance : emp.getSpsBillDetails()) {
									SpsBillDetail detail = new SpsBillDetail();
									billemp.getSpsBillDetails().add(detail);
									detail.setInsuranceType(insurance.getInsuranceType());
									detail.setCorpPaybase(insurance.getCorpPaybase());
									detail.setEmpPaybase(insurance.getEmpPaybase());
									detail.setCorpRatio(insurance.getCorpRatio());
									detail.setCorpPayment(insurance.getCorpPayment());
									detail.setEmpRatio(insurance.getEmpRatio());
									detail.setEmpPayment(insurance.getEmpPayment());
									detail.setCorpAddition(insurance.getCorpAddition());
									detail.setPsnAddition(insurance.getPsnAddition());
									if ("COMMON".equals(scheme.getFundType()) && "FUND".equals(bdInsuranceService
											.findByPK(insurance.getInsuranceType()).getInsuranceFundType())) {
										billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
									} else if ("COMMON".equals(scheme.getInsuranceType())) {
										billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
									}

								}
							}

							if (null != billemp.getSpsBillDetails() && billemp.getSpsBillDetails().size() > 0) {
								// 有保险数据则计入账单
								billEmps.add(billemp);
								billEmpsMap.put(billemp.getEmpId(), billemp);
								serviceFee = serviceFee.add(billemp.getServiceFee());
								officeFee = Arith.add(officeFee, billemp.getFundOfficialFee(),
										billemp.getInsuranceOfficialFee());
							}
						}
					}
				}
			} else {
				if (null != emps) {
					for (CmEmployee emp : emps) {
						// 由险种反推，如果有明细数据则持久化该员工
						// if ("OFF".equals(emp.getInsuranceState()) &&
						// "OFF".equals(emp.getFundState()))
						// continue;
						// // 实缴账单和月报只包含正常缴纳部分
						// if ("PAID".equals(billType) ||
						// "MONTH".equals(scheme.getBillType())) {
						// if (!"ON".equals(emp.getInsuranceState()) ||
						// !"ON".equals(emp.getFundState()))
						// continue;
						// }
						SpsBillEmp billemp = new SpsBillEmp();
						billemp.setEmpId(emp.getEmpId());

						billemp.setIdentityCode(emp.getIdentityCode());
						billemp.setIdentityType(emp.getIdentityType());

						billemp.setFundaccountId(scheme.getFundAccountId());
						// todo 读取库中的地区
						billemp.setFundArea(scheme.getAreaId());
						billemp.setFundBase(emp.getFundSalary());

						billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
						// todo 读取库中的地区
						billemp.setInsuranceArea(scheme.getAreaId());
						billemp.setInsuranceBase(emp.getInsuranceSalary());
						billemp.setInsuredMonth(insureMonth);
						billemp.setPayType("MONTH");
						billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

						billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
						billemp.setFundOfficialFee(BigDecimal.ZERO);
						billemp.setInsuranceOfficialFee(BigDecimal.ZERO);
						if (insureMonth.equals(emp.getInsurancePeriod()))
							billemp.setInsuranceIsNew("Y");
						if (fundMonth.equals(emp.getFundPeriod()))
							billemp.setFundIsNew("Y");
						// 如果实缴存在根据实缴计算
						Map<Integer, SpsEmpActualdetail> actualDetailMap = new HashMap<>();
						SpsEmpActual insuranceActual = actualInsuranceMap.get(emp.getEmpId());
						if (null != insuranceActual) {
							for (SpsEmpActualdetail actualDetail : insuranceActual.getActualDetails()) {
								actualDetailMap.put(actualDetail.getInsuranceId(), actualDetail);
							}
						}
						SpsEmpActual fundActual = actualFundMap.get(emp.getEmpId());
						if (null != fundActual) {
							for (SpsEmpActualdetail actualDetail : fundActual.getActualDetails()) {
								actualDetailMap.put(actualDetail.getInsuranceId(), actualDetail);
							}
						}
						Map<Integer, CmEmployeeInsurance> empInsuranceMap = new HashMap<>();
						if (null != emp.getCmEmployeeInsurances()) {
							for (CmEmployeeInsurance insurance : emp.getCmEmployeeInsurances()) {
								empInsuranceMap.put(insurance.getInsuranceId(), insurance);
							}
						}
						// 按地区险种循环
						for (BdInsurancearea ins : insArea) {
							BdInsurance bdins = bdInsuranceService.findByPK(ins.getInsuranceId());
							SpsBillDetail detail = null;
							SpsEmpActualdetail actdetail = null;
							SpsEmpActual actual;
							// 当前险种支持下行数据，并且当前库已存在下行数据
							if (Integer.valueOf(6).equals(ins.getInsuranceId())) {
								actual = actualFundMap.get(emp.getEmpId());
								if (null != actual) {

									actdetail = actualDetailMap.get(ins.getInsuranceId());
								}
							} else {
								actual = actualInsuranceMap.get(emp.getEmpId());
								if (null != actual) {
									actdetail = actualDetailMap.get(ins.getInsuranceId());
								}
							}
							// 当地支持下行数据，并且大库下有下行数据
							// 或者excel导入的实缴明细，存在实缴明细
							if (("Y".equals(ins.getFlow())
									&& (Integer.valueOf(6).equals(ins.getInsuranceId()) && hasFundActual)
									|| (!Integer.valueOf(6).equals(ins.getInsuranceId()) && hasInsActual))
									|| null != actdetail) {
								// 根据实缴生成
								if (actdetail != null) {
									detail = new SpsBillDetail();
									detail.setInsuranceType(actdetail.getInsuranceId());
									detail.setCorpPaybase(actdetail.getPayBase());
									// TODO 下行数据拆分个人企业缴纳基数
									detail.setEmpPaybase(actdetail.getPayBase());
									detail.setCorpRatio(actdetail.getCorpRatio());
									detail.setCorpPayment(actdetail.getCorpPayment());
									detail.setEmpRatio(actdetail.getEmpRatio());
									detail.setEmpPayment(actdetail.getEmpPayment());
								}
							} else {
								CmEmployeeInsurance insuranceDetail = empInsuranceMap.get(ins.getInsuranceId());
								// 社保需要整体判断，actualInsuranceMap中是否存在，可能存在缺险种情况，不能只靠actualdetail
								// 实缴获取不到取
								if (detail == null && insuranceDetail != null) {
									if (null == insuranceDetail.getRatioId())
										throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",
												emp.getName(), bdins.getName()));
									BsArearatio ratio = bsArearatioService.findByPK(insuranceDetail.getRatioId());
									if (null == ratio)
										throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",
												emp.getName(), bdins.getName()));
									detail = new SpsBillDetail();
									detail.setInsuranceType(insuranceDetail.getInsuranceId());
									// TODO 下行数据拆分个人企业缴纳基数
									detail.setEmpPaybase(insuranceDetail.getEmpPaybase());
									detail.setCorpPaybase(insuranceDetail.getCorpPaybase());
									detail.setCorpRatio(insuranceDetail.getCorpRatio());
									detail.setCorpPayment(insuranceDetail.getCorpPayment());
									detail.setEmpRatio(insuranceDetail.getEmpRatio());
									detail.setEmpPayment(insuranceDetail.getEmpPayment());
									detail.setCorpAddition(insuranceDetail.getCorpAddition());
									detail.setPsnAddition(insuranceDetail.getPsnAddition());
									String month;
									if ("FUND".equals(bdins.getInsuranceFundType()))
										month = fundMonth;
									else
										month = insureMonth;
									if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())
											|| ratio.billingCycle_YEAR_ANNUALY.equals(ratio.getBillingCycle())) {
										try {
											// 缴费参保月份等于年缴险种月份 或者 新参保员工
											if (((format.parse(month).getMonth() + 1) == ratio.getPayPeriod())
													|| month.equals(insuranceDetail.getBeginPeriod())) {
												// 年缴不足一年按年等同于月缴 不需要处理
												// 年缴不足一年按月
												if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())) {
													if (StringUtils.isNotBlank(insuranceDetail.getBeginPeriod())) {
														// 缴费月份-入职月份 如果 小于等于0 则
														// +12
														int months = ratio.getPayPeriod() - format
																.parse(insuranceDetail.getBeginPeriod()).getMonth() - 1;
														if (months <= 0)
															months += 12;
														detail.setCorpPayment(
																Arith.div(insuranceDetail.getCorpPayment(),
																		new BigDecimal(12.0 / months)));
														detail.setCorpAddition(
																Arith.div(insuranceDetail.getCorpAddition(),
																		new BigDecimal(12.0 / months)));
														detail.setEmpPayment(Arith.div(insuranceDetail.getEmpPayment(),
																new BigDecimal(12.0 / months)));
														detail.setPsnAddition(
																Arith.div(insuranceDetail.getPsnAddition(),
																		new BigDecimal(12.0 / months)));
													}
												}

											} else {
												detail.setCorpPayment(BigDecimal.ZERO);
												detail.setEmpPayment(BigDecimal.ZERO);
											}
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											log.error("日期计算出错", e);
											throw new RuntimeException(e);
										}
									}
								}
							}
							if (detail != null) {
								if (Arith.ignoreNull(detail.getCorpPayment()).doubleValue() > 0 || Arith.ignoreNull(detail.getEmpPayment()).doubleValue() > 0) {
									billemp.getSpsBillDetails().add(detail);
									// 公积金
									if ("FUND".equals(bdins.getInsuranceFundType())) {
										if ("COMMON".equals(scheme.getFundType())) {
											billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
													detail.getCorpPayment(), detail.getEmpPayment()));
										}
									} else {
										if ("COMMON".equals(scheme.getInsuranceType())) {
											billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
													detail.getCorpPayment(), detail.getEmpPayment()));
										}
									}
								}
							}
						}

						if (null != billemp.getSpsBillDetails() && billemp.getSpsBillDetails().size() > 0) {
							// 有保险数据则计入账单
							billEmps.add(billemp);
							billEmpsMap.put(billemp.getEmpId(), billemp);
							serviceFee = serviceFee.add(billemp.getServiceFee());
							officeFee = Arith.add(officeFee, billemp.getFundOfficialFee(),
									billemp.getInsuranceOfficialFee());
						}

					}
				}
			}

			// 获取一次性费用
			List<SpsFeeEmponce> emponces = spsFeeEmponceService.queryEmponceWithDetails(spId, cpId, period, empIds);
			if (null != emponces) {
				for (SpsFeeEmponce once : emponces) {
					// 一次性官费
					SpsBillEmp billemp;
					if (StringUtils.isBlank(once.getPayType()) || "MONTH".equals(once.getPayType())) {
						billemp = billEmpsMap.get(once.getEmpId());
						if (null == billemp) {
							billemp = new SpsBillEmp();
							CmEmployee emp = empMap.get(once.getEmpId());
							if (null == emp)
								continue;
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(scheme.getFundAccountId());
							// todo 读取库中的地区
							billemp.setFundArea(scheme.getAreaId());
							billemp.setFundBase(emp.getFundSalary());

							billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(scheme.getAreaId());
							billemp.setInsuranceBase(emp.getInsuranceSalary());
							billemp.setInsuredMonth(period);
							billemp.setPayType("MONTH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(BigDecimal.ZERO);
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);

							billemp.setAdjustReason(once.getReason());

							// 循环一次性费用 创建的员工加入map，避免一条一次性费用创建一个账单员工
							billEmpsMap.put(billemp.getEmpId(), billemp);

						}
						// 小库不应有一次性官费，如果有计入 类似深圳重疾险
						// 官费未分社保公积金，所以全部加入社保
						// if ("OFFICE".equals(once.getFeeType())) {
						if (Arith.ignoreNull(once.getOfficialFee()).doubleValue() != 0) {
							billemp.setInsuranceOfficialFee(
									Arith.add(billemp.getInsuranceOfficialFee(), once.getOfficialFee()));
							officeFee = Arith.add(officeFee, once.getOfficialFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整官费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getOfficialFee())));
							} else {
								billemp.setAdjustReason(
										billemp.getAdjustReason().concat(String.format("调整官费原因：%s 金额: %s;",
												once.getReason(), Arith.ignoreNull(once.getOfficialFee()))));
							}
						}
						// } else {
						if (Arith.ignoreNull(once.getServiceFee()).doubleValue() != 0) {
							billemp.setServiceFee(Arith.add(billemp.getServiceFee(), once.getServiceFee()));
							serviceFee = Arith.add(serviceFee, once.getServiceFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整服务费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getServiceFee())));
							} else {
								billemp.setAdjustReason(
										billemp.getAdjustReason().concat(String.format("调整服务费原因：%s 金额: %s;",
												once.getReason(), Arith.ignoreNull(once.getServiceFee()))));
							}
						}
					} else {
						// 补缴官费
						billemp = patchEmpsMap.get(String.format("%s_%s", once.getEmpId(), once.getInsuredMonth()));
						if (billemp == null) {
							billemp = new SpsBillEmp();
							CmEmployee emp = empMap.get(once.getEmpId());
							if (null == emp)
								continue;
							billemp.setEmpId(emp.getEmpId());

							billemp.setIdentityCode(emp.getIdentityCode());
							billemp.setIdentityType(emp.getIdentityType());

							billemp.setFundaccountId(scheme.getFundAccountId());
							// todo 读取库中的地区
							billemp.setFundArea(scheme.getAreaId());

							billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
							// todo 读取库中的地区
							billemp.setInsuranceArea(scheme.getAreaId());

							billemp.setInsuredMonth(once.getInsuredMonth());
							billemp.setPayType("PATCH");
							billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

							billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
							serviceFee = Arith.add(serviceFee, billemp.getServiceFee());
							billemp.setFundOfficialFee(BigDecimal.ZERO);
							billemp.setInsuranceOfficialFee(BigDecimal.ZERO);

							//billemp.setAdjustReason(once.getReason());
							patchEmpsMap.put(String.format("%s_%s", once.getEmpId(), once.getInsuredMonth()), billemp);
						}


						if (Arith.ignoreNull(once.getOfficialFee()).doubleValue() != 0 && !"1".equals(String.valueOf(once.getSource()))) {
							billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(), once.getOfficialFee()));
							officeFee = Arith.add(officeFee, once.getOfficialFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整官费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getOfficialFee())));
							} else {
								billemp.setAdjustReason(billemp.getAdjustReason().concat(String.format("调整官费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getOfficialFee()))));
							}
						}
						if (Arith.ignoreNull(once.getServiceFee()).doubleValue() != 0) {
							billemp.setServiceFee(Arith.add(billemp.getServiceFee(), once.getServiceFee()));
							serviceFee = Arith.add(serviceFee, once.getServiceFee());
							if (StringUtils.isBlank(billemp.getAdjustReason())) {
								billemp.setAdjustReason(String.format("调整服务费原因：%s 金额: %s;", once.getReason(),
										Arith.ignoreNull(once.getServiceFee())));
							} else {
								billemp.setAdjustReason(billemp.getAdjustReason().concat(String.format("调整服务费原因：%s 金额: %s;", once.getReason(), Arith.ignoreNull(once.getServiceFee()))));
							}
						}

						if (null != once.getSpsFeeDetails()) {
							for (SpsFeeEmponcedetail insurance : once.getSpsFeeDetails()) {
								BdInsurance bdins = bdInsuranceService.findByPK(insurance.getInsuranceType());
								// 实缴账单和月报只包含正常缴纳部分
								SpsBillDetail detail = new SpsBillDetail();
								billemp.getSpsBillDetails().add(detail);
								detail.setInsuranceType(insurance.getInsuranceType());
								detail.setCorpPaybase(insurance.getCorpPaybase());
								detail.setEmpPaybase(insurance.getEmpPaybase());
								detail.setCorpRatio(insurance.getCorpRatio());
								detail.setCorpPayment(insurance.getCorpPayment());
								detail.setEmpRatio(insurance.getEmpRatio());
								detail.setEmpPayment(insurance.getEmpPayment());
								detail.setCorpAddition(insurance.getCorpAddition());
								detail.setPsnAddition(insurance.getPsnAddition());
								if ("FUND".equals(bdins.getInsuranceFundType())) {
									if ("COMMON".equals(scheme.getFundType())) {
										billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
										officeFee = Arith.add(officeFee, insurance.getCorpPayment(),
												insurance.getEmpPayment());
									}
									billemp.setFundBase(once.getSalary());
								} else {
									if ("COMMON".equals(scheme.getInsuranceType())) {
										billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(),
												insurance.getCorpPayment(), insurance.getEmpPayment()));
										officeFee = Arith.add(officeFee, insurance.getCorpPayment(),
												insurance.getEmpPayment());
									}
									billemp.setInsuranceBase(once.getSalary());
								}
							}
						}
					}
					// 补缴数据添加到存储对象 不放map
					billEmps.add(billemp);
				}
			}
		}

		// 账单项目
		List<SpsBillitem> billitems = new ArrayList<>();

		// 标准服务费项目
		{
			SpsBillitem item = new SpsBillitem();
			item.setFee(serviceFee);
			item.setName("标准服务费");
			item.setSource(0);
			billitems.add(item);
		}
		// 标准官费项目
		{
			SpsBillitem item = new SpsBillitem();
			item.setFee(officeFee);
			item.setName("代扣代缴");
			item.setSource(0);
			billitems.add(item);
		}

		// 获取一次性企业费用
		SpsFeeCorponce corponce = new SpsFeeCorponce();
		corponce.setCpId(cpId);
		corponce.setSpId(spId);
		corponce.setPeriod(period);
		corponce.setDr(0);
		List<SpsFeeCorponce> corponces = spsFeeCorponceService.findAll(corponce);
		// 企业一次性费用
		if (null != corponces) {
			for (SpsFeeCorponce corpfee : corponces) {
				SpsBillitem item = new SpsBillitem();
				item.setFee(Arith.add(corpfee.getOfficialFee(), corpfee.getServiceFee()));
				item.setName(corpfee.getItem());
				item.setSource(0);
				officeFee = Arith.add(officeFee, corpfee.getOfficialFee());
				serviceFee = Arith.add(serviceFee, corpfee.getServiceFee());
				billitems.add(item);
			}
		}
		// TODO 上月差额账单 核销

		bill.setMarginOfficeFee(preBill.getMarginOfficeFee());
		bill.setMarginServiceFee(preBill.getMarginServiceFee());
		bill.setRelBillId(preBill.getBillId());
		bill.setBillOfficeFee(officeFee);
		bill.setBillServiceFee(serviceFee);
		bill.setActualOfficeFee(Arith.sub(officeFee, preBill.getMarginOfficeFee()));
		bill.setActualServiceFee(Arith.sub(serviceFee, preBill.getMarginServiceFee()));
		bill.setBillamount(Arith.add(bill.getActualOfficeFee(), bill.getActualServiceFee()));
		SpsBillMargin billMargin = new SpsBillMargin();
		billMargin.setSpId(spId);
		if ("HR".equals(deputeType)) {
			billMargin.setDeputeId(cpId);
		} else {
			billMargin.setDeputeId(deputeId);
		}
		billMargin.setDeputeType(deputeType);
		billMargin.setPrepayBillId(preBill.getBillId());
		billMargin.setPeriod(period);
		billMargin.setDeputeBillId(bill.getBillId());
		billMargin.setOfficeFee(Arith.sub(preBill.getActualOfficeFee(), bill.getActualOfficeFee()));
		billMargin.setServiceFee(Arith.sub(preBill.getActualServiceFee(), bill.getActualServiceFee()));
		billMargin.setStatus("UNOFFSET");
		// 存在差额 保存差额账单 konglc强烈要求，为零也保存
		// if (billMargin.getOfficeFee().doubleValue() > 0 ||
		// billMargin.getServiceFee().doubleValue() > 0)
		spsBillMarginService.save(cti, billMargin);

		// 如果不是重新生成的账单，从redis获取账单号
		if (StringUtils.isBlank(bill.getBillNum()))
			bill.setBillNum(TransactionUtil.getTransactionNumber("billNumber", BusinessCode.BILL.getValue()));

		// 持久化
		save(cti, bill);

		if (null != billEmps) {
			for (SpsBillEmp emp : billEmps) {
				emp.setBillId(bill.getBillId());
				spsBillEmpService.save(cti, emp);
				if (null != emp.getSpsBillDetails()) {
					for (SpsBillDetail detail : emp.getSpsBillDetails()) {
						detail.setBillEmpId(emp.getId());
						spsBillDetailService.save(cti, detail);
					}
				}
			}
		}

		if (null != billitems) {
			for (SpsBillitem item : billitems) {
				item.setBillId(bill.getBillId());
				spsBillitemService.save(cti, item);
			}
		}

		try {
			// 数据创建生成后生成账单excel
			String fileid = generateExcel(cti,bill, String.valueOf(cmCorpService.findByPK(cpId).getCorpName()),
					bill.getPeriod(), bill.getDeputeType(), bill.getBillType());
			bill.setFileId(fileid);
		} catch (Exception e) {

		}

		spsBillDao.update(cti, bill);
		return bill;
	}

	/**
	 * 生成excel账单文件
	 *
	 * @param bill
	 */
	@Override
	public String generateExcel(ContextInfo cti,SpsBill bill, String cmName, String period, String depute_type, String bill_type) {
		try {
			// 获取参保信息---Excel动态标题头
			List<Map<String, Object>> headerModels = bdInsuranceareaDao.queryEmInsuranceAreaList(bill.getSpId(), bill.getCpId(), "INSURANCE", bill.getBillId());
			// 获取参保数据
			List<Map<String, Object>> dataMap = spsBillEmpService.queryAllEmpList(bill.getSpId(), bill.getCpId(), bill.getBillId(), depute_type, bill_type);
			// 汇总信息数据
			List<List<Object>> sumDataMap = spsBillitemService.querySumCmBillByItem(bill.getSpId(), bill.getCpId(), bill.getBillId(), null,bill.getMarginOfficeFee(),bill.getMarginServiceFee());
			//获取动态单列头
			List<Map<String, String>> singleHeaderModels = bdInsuranceareaDao.queryEmInsuranceBillTemplateList(bill.getSpId(),bill.getCpId());

			try{
				Map<String, Object> enterprise = blPayEnterpriseService.queryEnterAccount(bill.getSpId());
				if(null != enterprise && !enterprise.isEmpty() && "USE".equals(enterprise.get("state"))){
					/**
					 * 在线支付链接地址
					 */
					cti = new ContextInfo();
					cti.setUserId(bill.getCreateBy());
					Result result = createPayBillInfo(bill,cti,null,null,null,null,"B端账单自有客户支付");
					List<Object> row = new ArrayList<>();
					row.add("在线支付");
					Map<String,String> param = new HashMap<>();
					param.put("url",String.valueOf(result.getData().get("payUrl")));
					String resp = HttpClientUtil.doPost(Config.getProperty("xfs_short_host"),param);
					Result shotUrlResult = JSON.parseObject(resp,Result.class);
					row.add(shotUrlResult.getData().get("shortUrl"));
					row.add("");
					sumDataMap.add(row);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			File excelFile = createBillExcel(cmName,period,sumDataMap,dataMap,singleHeaderModels,headerModels);
			String fileId = sysUploadfileService.uploadFile(excelFile,"images/cs/");
			SysUploadfile sysUploadfile = new SysUploadfile();
			sysUploadfile.setFilename(excelFile.getName());
			sysUploadfile.setSavename(fileId);
			sysUploadfile.setFilepath(bill.getPeriod());
			sysUploadfile.setFilesize(String.valueOf(excelFile.length()));
			sysUploadfileService.insert(cti,sysUploadfile);
			return fileId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

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
	public File previewBillTempalete(List<Integer> fieldIds){
		String cmName = "预览模版";
		String period = "2017-01";
		List<Map<String, Object>> headerModels = bdInsuranceareaDao.perviewEmInsuranceAreaList(fieldIds);
		List<Map<String, String>> singleHeaderModels = bdInsuranceareaDao.perviewEmInsuranceBillTemplateList(fieldIds);
		return createBillExcel(cmName,period,null,null,singleHeaderModels,headerModels);
	}

	/**
	 * 创建账单Excel
	 *  @param   cmName
	 *  @param   period
	 *  @param   sumDataMap
	 *  @param   dataMap
	 *  @param   singleHeaderModels
	 *  @param   headerModels
	 *	@return 			: java.io.File
	 *  @createDate  	: 2017-01-06 18:08
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-06 18:08
	 *  @updateAuthor  :
	 */
	private File createBillExcel(String cmName,String period,List<List<Object>> sumDataMap,List<Map<String, Object>> dataMap,List<Map<String, String>> singleHeaderModels,List<Map<String, Object>> headerModels){
		/**
		 * 第一个sheet页数据
		 */
		List<String> headers = new ArrayList<>();
		headers.add("款项类型");
		headers.add("金额（元）");
		headers.add("备注说明（如有其他服务项目或特别情况请说明）");
		List<ExcelModel> excelModels = new ArrayList<>();
		excelModels.add(ExcelUtil.convert2ExcelModel("结费单", cmName + " " + period + " 结费单", headers, sumDataMap));
		List<Map<String, String>> otherHeaderModels = new ArrayList<>();
		for(Map<String,String> item : singleHeaderModels){
			if(null != item && "wxxj".equals(item.get("parentCode"))){
				/**
				 * 社保小计
				 */
				Map<String, Object> wxxj_crop = new LinkedHashMap<>();
				wxxj_crop.put("parentCode", "wxxj");
				wxxj_crop.put("parentName", "社保小计");
				wxxj_crop.put("childCode", "crop");
				wxxj_crop.put("childName", "企业");
				headerModels.add(wxxj_crop);

				Map<String, Object> wxxj_emp = new LinkedHashMap<>();
				wxxj_emp.put("parentCode", "wxxj");
				wxxj_emp.put("parentName", "社保小计");
				wxxj_emp.put("childCode", "emp");
				wxxj_emp.put("childName", "个人");
				headerModels.add(wxxj_emp);

				Map<String, Object> wxxj_total = new LinkedHashMap<>();
				wxxj_total.put("parentCode", "wxxj");
				wxxj_total.put("parentName", "社保小计");
				wxxj_total.put("childCode", "total_sum");
				wxxj_total.put("childName", "合计");
				headerModels.add(wxxj_total);
			}else if(null != item && "wxxjyj".equals(item.get("parentCode"))){
				/**
				 * 社保公积金小计
				 */
				Map<String, Object> wxxjyj_crop = new LinkedHashMap<>();
				wxxjyj_crop.put("parentCode", "wxxjyj");
				wxxjyj_crop.put("parentName", "社保公积金小计");
				wxxjyj_crop.put("childCode", "crop");
				wxxjyj_crop.put("childName", "企业");
				headerModels.add(wxxjyj_crop);

				Map<String, Object> wxxjyj_emp = new LinkedHashMap<>();
				wxxjyj_emp.put("parentCode", "wxxjyj");
				wxxjyj_emp.put("parentName", "社保公积金小计");
				wxxjyj_emp.put("childCode", "emp");
				wxxjyj_emp.put("childName", "个人");
				headerModels.add(wxxjyj_emp);

				Map<String, Object> wxxjyj_total = new LinkedHashMap<>();
				wxxjyj_total.put("parentCode", "wxxjyj");
				wxxjyj_total.put("parentName", "社保公积金小计");
				wxxjyj_total.put("childCode", "total_sum");
				wxxjyj_total.put("childName", "合计");
				headerModels.add(wxxjyj_total);
			}else if(null != item && "fee_crop_sum".equals(item.get("parentCode"))){
				Map<String, Object> fee_crop_sum = new LinkedHashMap<>();
				fee_crop_sum.put("parentCode", "fee_crop_sum");
				fee_crop_sum.put("parentName", "企业合计");
				headerModels.add(fee_crop_sum);
			}else if(null != item && "fee_emp_sum".equals(item.get("parentCode"))){
				Map<String, Object> fee_crop_sum = new LinkedHashMap<>();
				fee_crop_sum.put("parentCode", "fee_emp_sum");
				fee_crop_sum.put("parentName", "个人合计");
				headerModels.add(fee_crop_sum);
			}else if(null != item && "fee".equals(item.get("parentCode"))){
				Map<String, Object> fee_crop_sum = new LinkedHashMap<>();
				fee_crop_sum.put("parentCode", "fee");
				fee_crop_sum.put("parentName", "服务费");
				headerModels.add(fee_crop_sum);
			}else if(null != item && "fee_total".equals(item.get("parentCode"))){
				Map<String, Object> fee_crop_sum = new LinkedHashMap<>();
				fee_crop_sum.put("parentCode", "fee_total");
				fee_crop_sum.put("parentName", "总计");
				headerModels.add(fee_crop_sum);
			}else if(null != item && "adjust_reason".equals(item.get("parentCode"))){
				Map<String, Object> fee_crop_sum = new LinkedHashMap<>();
				fee_crop_sum.put("parentCode", "adjust_reason");
				fee_crop_sum.put("parentName", "备注");
				headerModels.add(fee_crop_sum);
			}else{
				otherHeaderModels.add(item);
			}
		}
		ExcelModel excelModel = ExcelUtil.convert2ExcelModel("五险一金明细表", "五险一金缴纳详情", otherHeaderModels, headerModels, dataMap);
		// 将动态头拼接到Excel上
		excelModels.add(excelModel);
		ExcelCore core = new ExcelCore();
		core.process(excelModels);

		String fileName = "账单详情.xlsx";
		String filePath = getDateStrByMonth();
		File excelFile = geteEmptyFile(fileName, filePath);
		core.export(excelFile);
		return excelFile;
	}

	/**
	 * 得到年月yyyyMM格式的字符串，供生成生成上传目录使用
	 *
	 * @return
	 */
	private String getDateStrByMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(Calendar.getInstance().getTime());
	}

	/**
	 * 获取空文件
	 *
	 * @param fileName
	 * @param curDate
	 * @return
	 */
	private File geteEmptyFile(String fileName, String curDate) {
		String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
		String filePath = fileRootPath + File.separator + curDate;

		// 获取一个时间格式的名称（微信中为简易解决中文名称问题）
		String saveName = generateFileName(fileName);
		File localFile = new File(filePath, saveName);
		if (!localFile.exists()) {
			try {
				// 如果路径不存在,则创建
				if (!localFile.getParentFile().exists()) {
					localFile.getParentFile().mkdirs();
				}
				localFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			localFile = getUniqueFile(filePath, saveName);
		}
		return localFile;
	}

	/**
	 * 获得一个以时间格式的新名称
	 *
	 * @param fileName
	 *            原图名称
	 * @return
	 */
	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 如果上传文件名重复，处理文件名
	 *
	 * @param path
	 * @param fileName
	 * @return
	 */
	private File getUniqueFile(String path, String fileName) {
		String pname = fileName.substring(0, fileName.lastIndexOf('.'));
		String suffix = fileName.substring(fileName.lastIndexOf('.'));
		int i = 1;
		while (true) {
			String tempName = pname + i + suffix;
			File tf = new File(path, tempName);
			if (!tf.exists())
				return tf;
			i++;
		}
	}

	/**
	 * 发送请款成系统消息
	 */
	private void sendSysMessage(Management curr_bill, Integer user_id, Integer pay, String expend) {
		try {
			if (pay == 1) {
				SmsUtil.sendSms(curr_bill.getAccountMobile(), "尊敬的用户，用友薪福社已将本月" + expend + "打到您的指定账户，请注意查收！");
			} else {
				SmsUtil.sendSms(curr_bill.getAccountMobile(), "尊敬的用户，用友薪福社不支付本月" + expend);
			}
			SysMessage sysMessage = new SysMessage();
			sysMessage.setContent("尊敬的用户，用友薪福社已将本月" + expend + "打到您的指定账户，请注意查收！");
			sysMessage.setTitle("尊敬的用户，用友薪福社已将本月" + expend + "打到您的指定账户，请注意查收！");
			sysMessage.setState("TODO");
			sysMessage.setTodoUserType(CMCORPTYPE_SERVICE);
			sysMessage.setTodoOrg(curr_bill.getCpId());
			sysMessage.setSendUser(user_id);
			sysMessage.setSendUserType("BUSINESS");
			sysMessage.setType("PAY");
			sysMessage.setSendOrg(curr_bill.getCpId());
			sysMessage.setDataId(curr_bill.getBillId());
			sysMessage.setSendTime(new Date());
			RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE, sysMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 薪福社向服务商打款
	 * 
	 * @param spsBill
	 * @param payFee
	 * @return
	 */
	private boolean xfsPaySpFee(ContextInfo cti, SpsBill spsBill, Management curr_bill, BigDecimal payFee,
			Result result) {
		// 触发薪福社账户余额同步锁
		while (RedisUtil.getLock(COMMON_XFS_PAY_LOCK, 30000L) != 1) {// 获取当前锁
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean issuccess = true;
		int ok = 0;
		/**
		 * 1.获取薪福社当前余额 2.增加薪福社余额 3.记录余额流水明细
		 */
		// 获取薪福社余额帐号
		BlBalance xfs_blBalance = new BlBalance();
		xfs_blBalance.setOwnerType("XFS");
		BlBalance xfs_curr_blBalance = blBalanceService.queryBalance(xfs_blBalance);
		if (null == xfs_curr_blBalance) {
			xfs_blBalance.setAmount(new BigDecimal(0.0));
			blBalanceService.insert(cti, xfs_blBalance);
			xfs_curr_blBalance = xfs_blBalance;
		}
		// 减薪福社余额--判断薪福社余额是否够当前请款金额
		if (xfs_curr_blBalance.getAmount().compareTo(payFee) == -1) {
			result.setError("当前用友薪福社余额不足");
			return false;
		}
		xfs_curr_blBalance.setAmount(xfs_curr_blBalance.getAmount().subtract(payFee));
		ok = blBalanceService.update(cti, xfs_curr_blBalance);
		if (ok > 0) {
			// 记录薪福社余额流水 -- 支付
			BlBalancedetail xfs_balancedetail = new BlBalancedetail();
			BigDecimal zero = new BigDecimal(0.0);
			xfs_balancedetail.setAmount(zero.subtract(payFee));
			xfs_balancedetail.setOwnerType("XFS");
			xfs_balancedetail.setBalance(xfs_curr_blBalance.getAmount());
			xfs_balancedetail.setOrderId(curr_bill.getOrderId());
			xfs_balancedetail.setTradeType("DRAW");
			ok = blBalancedetailService.insert(cti, xfs_balancedetail);

			if (ok > 0) {
				/**
				 * 触发服务机构余额同步锁功能
				 */
				while (RedisUtil.getLock(COMMON_SPS_PAY_LOCK + curr_bill.getSpId(), 30000L) != 1) {// 获取当前锁
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 获取服务机构余额帐号
				BlBalance blBalance = new BlBalance();
				blBalance.setOwner(String.valueOf(curr_bill.getSpId()));
				blBalance.setOwnerType(CMCORPTYPE_SERVICE);
				BlBalance curr_blBalance = blBalanceService.queryBalance(blBalance);
				if (null == curr_blBalance) {
					blBalance.setAmount(new BigDecimal(0.0));
					blBalanceService.insert(cti, blBalance);
					curr_blBalance = blBalance;
				}
				// 修改余额--充值
				curr_blBalance.setAmount(curr_blBalance.getAmount().add(payFee));
				ok = blBalanceService.update(cti, curr_blBalance);
				if (ok > 0) {
					// 记录余额流水 -- 支付
					BlBalancedetail balancedetail = new BlBalancedetail();
					balancedetail.setAmount(payFee);
					balancedetail.setOwnerType(CMCORPTYPE_SERVICE);
					balancedetail.setOwner(String.valueOf(curr_bill.getSpId()));
					balancedetail.setBalance(curr_blBalance.getAmount());
					balancedetail.setOrderId(curr_bill.getOrderId());
					balancedetail.setTradeType("COLLECT");
					ok = blBalancedetailService.insert(cti, balancedetail);
					if (ok <= 0)
						issuccess = false;
				}
				/**
				 * 由于当前为线下汇款 so:在做一个提现流水
				 */
				curr_blBalance.setAmount(curr_blBalance.getAmount().subtract(payFee));
				ok = blBalanceService.update(cti, curr_blBalance);
				if (ok > 0) {
					// 记录余额流水 -- 提现
					BlBalancedetail balancedetail = new BlBalancedetail();
					balancedetail.setAmount(zero.subtract(payFee));
					balancedetail.setOwnerType(CMCORPTYPE_SERVICE);
					balancedetail.setOwner(String.valueOf(curr_bill.getSpId()));
					balancedetail.setBalance(curr_blBalance.getAmount());
					balancedetail.setOrderId(curr_bill.getOrderId());
					balancedetail.setTradeType("DRAW");
					ok = blBalancedetailService.insert(cti, balancedetail);
					if (ok <= 0)
						issuccess = false;
				}
				RedisUtil.delLock(COMMON_SPS_PAY_LOCK + curr_bill.getSpId());
			} else {
				issuccess = false;
			}
		} else {
			issuccess = false;
		}
		RedisUtil.delLock(COMMON_XFS_PAY_LOCK); // 释放锁
		return issuccess;
	}

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
	@Override
	public String queryMaxBillMonth(Integer cpId) {
		return spsBillDao.queryMaxBillMonth(cpId);
	}

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
	@Override
	public SpsBill queryBillByBillNum(String billNum){
		return spsBillDao.queryBillByBillNum(billNum);
	}

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
	@Override
	public Result cancelPay(ContextInfo cti,Integer billId){
		Result result = Result.createResult().setSuccess(false);
		SpsBill bill = new SpsBill();
		bill.setBillId(billId);
		SpsBill curr_bill = spsBillDao.findByPK(bill);
		if(null != curr_bill && cti.getOrgId().equals(curr_bill.getCpId())){
			bill.setFeePayStatus(0);
			int row =  spsBillDao.update(cti,bill);
			if(row > 0){
				result.setSuccess(true);
				BdUserPrizeRecord record = new BdUserPrizeRecord();
				record.setOutTradeOrder(curr_bill.getBillNum());
				record.setDr(0);
				record.setUserType(cti.getUserType());
				record.setOrgId(cti.getOrgId());
				record.setState("PRE_PAY");
				List<BdUserPrizeRecord> records = bdUserPrizeRecordService.findAll(record);
				if(null != records && !records.isEmpty()){
					for (BdUserPrizeRecord pr : records ){
						//作废优惠券
						result = bdUserPrizeService.saveUserPrizeRecord(cti,"CUSTOMER",cti.getOrgId(),pr.getPrizeId(),curr_bill.getBillNum(),record.getMonetary(),1);

					}
				}
			}
		}
		//获取账单支付对应的业务信息
		RespXfsPay respXfsPay = blPayBusinessService.queryBusinPayByAppName("B端账单支付");
		try{
			ReqCancelOrder reqPay = new ReqCancelOrder();
			reqPay.setOuterTradeNo(curr_bill.getBillNum());
			reqPay.setAppId(respXfsPay.getAppId());
			reqPay.setSign(XfsPayUtils.sign(reqPay,respXfsPay.getRsaPrivate()));
			TypeReference<Map<String,String> > ref = new TypeReference<Map<String,String> >(){};
			Map<String,String> reqMap = JSON.parseObject(JSON.toJSONString(reqPay),ref);
			String cancel_url = Config.getProperty("xfs_query_voucher_url").substring(0,Config.getProperty("xfs_query_voucher_url").lastIndexOf("/")) + "/cancelOrder";
			String responJson = HttpClientUtil.doPost(cancel_url,reqMap);
			JSONObject object = JSON.parseObject(responJson);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  获取当前账单支付状态
	 *  @param   billId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-09 11:32
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-09 11:32
	 *  @updateAuthor  :
	 */
	@Override
	public Result queryBillPayStatus(ContextInfo cti,Integer billId){
		Result result = Result.createResult().setSuccess(false);
		SpsBill bill = new SpsBill();
		bill.setBillId(billId);
		SpsBill curr_bill = spsBillDao.findByPK(bill);
		if(null != curr_bill && cti.getOrgId().equals(curr_bill.getCpId()) && (curr_bill.getFeePayStatus().equals(1) || curr_bill.getFeePayStatus().equals(3))){
			result.setSuccess(true);
		}
		return result;
	}

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
	@Override
	public Integer delCmBillByMonth(ContextInfo cti, Integer spid,Integer cmid,String period){
		return spsBillDao.delCmBillByMonth(cti,spid,cmid,period);
	}

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
	@Override
	public Integer updateBillSend(ContextInfo cti, SpsBill spsBill){
		return spsBillDao.updateBillSend(cti,spsBill);
	}

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
	@Override
	public Result createPayBillInfo(SpsBill bill,ContextInfo cti,String notifyUrl,String returnUrl,String isuse,Integer prize_id,String payChannleName){
		Result result = Result.createResult().setSuccess(true);
		String service_site = Config.getProperty("xfs_cs_host");
		Map<String,Object> billPaymentDetail = queryBillPaymentDetail(bill);
		//获取账单支付对应的业务信息
		RespXfsPay respXfsPay = blPayBusinessService.queryBusinPayByAppName(payChannleName);
		ReqPay reqPay = new ReqPay();
		reqPay.setBuyerName(String.valueOf(billPaymentDetail.get("corp_name")));//付款方名称
		reqPay.setBuyerId(String.valueOf(bill.getCpId()));
		reqPay.setCreateBy(cti.getUserId());
		reqPay.setPayeeId(String.valueOf(billPaymentDetail.get("sp_id")));
		reqPay.setPayeeName(String.valueOf(billPaymentDetail.get("sp_name")));
		reqPay.setBuyerType("CORP");
		reqPay.setPayeeType(CMCORPTYPE_SERVICE);
		reqPay.setTradeType("PAY_FEE");

		if(StringUtils.isBlank(notifyUrl))
			reqPay.setNotifyUrl(URL.encode(service_site+"/api/bill/payNotify"));
		else
			reqPay.setNotifyUrl(URL.encode(notifyUrl));
		if(StringUtils.isBlank(returnUrl))
			reqPay.setReturnUrl(URL.encode(service_site+"/bill/index"));
		else
			reqPay.setReturnUrl(URL.encode(returnUrl));

		try{
			String productInfo = "<tr><td>商品名称：</td><td class=\"padding-r60\">"+String.valueOf(billPaymentDetail.get("corp_name"))+billPaymentDetail.get("period")+"账单"+"</td><td>出账机构：</td><td>"+String.valueOf(billPaymentDetail.get("sp_name"))+"</td></tr><tr><td>交易类型：</td><td>支付</td><td>交易日期：</td><td>"+ DateUtil.getDateStr(new Date(),"yyyy-MM-dd")+"</td></tr>";
			reqPay.setProductInfo(Base64.encode(productInfo.getBytes()));
		}catch (Exception e){
			e.printStackTrace();
		}
		reqPay.setProductIntro(String.valueOf(billPaymentDetail.get("corp_name"))+billPaymentDetail.get("period")+"账单");
		reqPay.setOuterTradeNo(String.valueOf(billPaymentDetail.get("bill_num")));
		reqPay.setAppId(respXfsPay.getAppId());
		if(!StringUtils.isBlank(isuse) && isuse.equals("Y")){
			/**
			 * 使用优惠券之前先取消已经使用的
			 */
			result = bdUserPrizeService.saveUserPrizeRecord(cti,"CUSTOMER",cti.getOrgId(),prize_id,reqPay.getOuterTradeNo(),reqPay.getDiscounted(),1);
			Map<String,Object> prizeMap = bdUserPrizeService.findUserPrizeById("CUSTOMER",cti.getOrgId(),"SER_COUPON",null);
			//获取优惠券信息
			if(null != prizeMap.get("prize_price") && BigDecimal.valueOf(Double.parseDouble(String.valueOf(prizeMap.get("prize_price")))).compareTo(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("bill_service_fee"))))) >= 0){//优惠券大于服务费
				reqPay.setAmount(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))).subtract(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("bill_service_fee"))))));
				reqPay.setPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))));
				reqPay.setDiscounted(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("bill_service_fee")))));
			}else if(null != prizeMap.get("prize_price")){//优惠券小于服务费
				reqPay.setAmount(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))).subtract(BigDecimal.valueOf(Double.parseDouble(String.valueOf(prizeMap.get("prize_price"))))));
				reqPay.setPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))));
				reqPay.setDiscounted(BigDecimal.valueOf(Double.parseDouble(String.valueOf(prizeMap.get("prize_price")))));
			}
			/**
			 * 使用对应优惠券
			 */
			result = bdUserPrizeService.saveUserPrizeRecord(cti,"CUSTOMER",cti.getOrgId(),prize_id,reqPay.getOuterTradeNo(),reqPay.getDiscounted(),0);
		}else{
			reqPay.setAmount(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))));
			reqPay.setPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))));
			reqPay.setDiscounted(BigDecimal.valueOf(Double.parseDouble(String.valueOf(billPaymentDetail.get("billamount")))));
			if(null != prize_id)
				result = bdUserPrizeService.saveUserPrizeRecord(cti,"CUSTOMER",cti.getOrgId(),prize_id,reqPay.getOuterTradeNo(),reqPay.getDiscounted(),1);
		}
		reqPay.setSign(XfsPayUtils.sign(reqPay,respXfsPay.getRsaPrivate()));
		result.setDataValue("reqPay",reqPay);
		Map<String,String> reqPayMap = JSON.parseObject(JSON.toJSONString(reqPay),new TypeReference<Map<String, String>>(){});
		result.setDataValue("payUrl",Config.getProperty("xfs_cashier_voucher_url")+"?"+ChanPayUtils.createLinkString(reqPayMap,true)+"&sign="+URL.encode(reqPay.getSign()));
		return result;
	}

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
	@Override
	public Result writeOffBillCheckVoucher(ContextInfo cti,SpsBill bill) throws Exception{
		Result result = createPayBillInfo(bill,cti,null,null,null,null,"B端账单支付");
		ReqPay reqPay = (ReqPay)result.getData().get("reqPay");
		if(null != reqPay){
			Result ver_result = blPayBusinessService.verificateReqBodySign(reqPay);
			RespXfsPay blPayBusiness = (RespXfsPay)ver_result.getData().get("blPayBusiness");
			ToReqPay toReqPay = new ToReqPay();
			BeanUtils.copyProperties(reqPay,toReqPay);
			toReqPay.setBusId(blPayBusiness.getBusId());
			BlPayVoucher blPayVoucher = blPayVoucherService.queryVocherByOutTradeOrder(toReqPay);
			if(null == blPayVoucher){
				toReqPay.setPayType("OFFLINE");
				toReqPay.setPayId(4);
				blPayVoucherService.createPayVocher(toReqPay,result,blPayBusiness);
				blPayVoucher = blPayVoucherService.queryVocherByOutTradeOrder(toReqPay);
			}
			result.getData().remove("reqPay");
			result.setSuccess(true);
			result.setDataValue("outerTradeNo",blPayVoucher.getOuterTradeNo());
			result.setDataValue("busId",blPayVoucher.getBusId());
			return result;
		}
		return Result.createResult().setSuccess(false);
	}

	@Override
	public PageModel queryCollectionListPage(PageInfo pi, SpsBill vo,String spname,String corpName,String period,String source,String actualType,String payType) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spName",spname);
		_hashmap.put("corpName",corpName);
		_hashmap.put("period",period);
		_hashmap.put("source",source);
		_hashmap.put("actualType",actualType);
		_hashmap.put("payType",payType);
		Integer total = spsBillDao.countCollection(_hashmap);
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsBillDao.queryCollectionList(_hashmap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public SpsBill findByBillNum(SpsBill spsBill){
		return spsBillDao.findByBillNum(spsBill);
	}


	@Override
	public PageModel querySpsBillList(ContextInfo cti,PageInfo pi, SpsBill vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillDao.countFreeFindByHr(cti,vo);
		pm.setTotal(total);
		List<SpsBillDto> datas = spsBillDao.freeFindByHr(cti,vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	@Override
	public List<SpsBillDto> queryTodoBillList(ContextInfo cti){
		return spsBillDao.queryTodoBillList(cti);
	}

	@Override
	public List<Map<String,Object>> queryBillTrend(ContextInfo cti,Integer areaId,List<String> months){
		return spsBillDao.queryBillTrend(cti,areaId,months);
	}

	@Override
	public List<InsureEmpDetail> queryEmpBillInsMonth(Integer cpId, Integer empId, String period){
		return spsBillDao.queryEmpBillInsMonth(cpId,empId,period);
	}

	@Override
	public List<InsureEmpDetail> queryEmpBillMonth(ContextInfo cti, Integer empId, String period){
		return spsBillDao.queryEmpBillMonth(cti,empId,period);
	}

	public List<InsureEmpDetail> queryEmpBillPayment(ContextInfo cti, Integer empId, String period){
		return spsBillDao.queryEmpBillPayment(cti,empId,period);
	}


	@Override
	public Map<String, Object> findBillDetailOne(ContextInfo cti,Integer billId) {
		return spsBillDao.findBillDetailOne(cti,billId);
	}

	@Override
	public Map<String, Object> findBillDetailSed(ContextInfo cti,Integer billId) {
		return spsBillDao.findBillDetailSed(cti,billId);
	}

	@Override
	public Map<String, Object> findBillDetailThird(ContextInfo cti,Integer billId){
		return spsBillDao.findBillDetailThird(cti,billId);
	}

	@Override
	public Integer updateBillReview(ContextInfo cti,SpsBill bill){
		return spsBillDao.updateBillReview(cti,bill);
	}

	/**
	 * 服务商对账列表
	 *  @param cti
	 *  @param pi
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月12日 上午11:16:27
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月12日 上午11:16:27
	 *  @updateAuthor  	:
	 */
	@Override
	public PageModel findServiceBillList(ContextInfo cti, PageInfo pi, ServiceBillVo vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spServiceService.findServiceBillListCount(vo);
		pm.setTotal(total);
		// 获取服务商列表
		List<ServiceBillVo> datas = spServiceService.findServiceBillList(vo, pageIndex, pageSize);
		for(ServiceBillVo serviceBillVo : datas){
			SpsBill spsBill = new SpsBill();
			spsBill.setSpId(serviceBillVo.getSpId());
			spsBill.setCpId(serviceBillVo.getCpId());
			spsBill.setPeriod(vo.getPeriod());
			List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
			serviceBillVo.setPeriod(spsBill.getPeriod());
			if(spsBills.size() > 0){
				serviceBillVo.setBillDay(spsBills.get(0).getBillDay());
			}
			for (int i = 0; i < spsBills.size(); i++) {
				if("PREPAY".equals(spsBills.get(i).getBillType())){
					serviceBillVo.setPrepay(null == spsBills.get(i).getBillamount() ? new BigDecimal(0.00) : spsBills.get(i).getBillamount());
				}
				if("PAID".equals(spsBills.get(i).getBillType())){
					serviceBillVo.setPaid(null == spsBills.get(i).getBillamount() ? new BigDecimal(0.00) : spsBills.get(i).getBillamount());
				}
			}
			serviceBillVo.setDifference(Arith.sub(serviceBillVo.getPrepay(),serviceBillVo.getPaid()));
			if(null == spsBills || spsBills.size() == 0){
				serviceBillVo.setBillState("未生成未对账");
			}else if(spsBills.size() == 1){
				serviceBillVo.setBillState("已生成未对账");
			}else{
				serviceBillVo.setBillState("已生成已对账");
			}
		}
		pm.setDatas(datas);
		return pm;
	}

}
