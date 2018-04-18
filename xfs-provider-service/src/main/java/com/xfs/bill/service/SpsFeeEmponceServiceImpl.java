package com.xfs.bill.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsFeeEmponceDao;
import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.Arith;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.corp.service.CmEmployeeService;

/**
 * 人员单次费用服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
@Service
public class SpsFeeEmponceServiceImpl implements SpsFeeEmponceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsFeeEmponceServiceImpl.class);
	
	@Autowired
	private SpsFeeEmponceDao spsFeeEmponceDao;
	
	@Autowired
	private CmEmployeeService cmEmployeeService;
	
	@Autowired
	private BsArearatioService bsArearatioService;
	
	@Autowired
	private SpsFeeEmponcedetailService spsFeeEmponcedetailService;

	@Autowired
	private BdInsuranceService bdInsuranceService;

	@Autowired
	private SpsSchemeService spsSchemeService;
	
	@Autowired
	private CmEmployeeInsuranceService cmEmployeeInsuranceService;

	public int save(ContextInfo cti, SpsFeeEmponce vo ){
		return spsFeeEmponceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsFeeEmponce vo ){
		return spsFeeEmponceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsFeeEmponce vo ){
		return spsFeeEmponceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsFeeEmponce vo ){
		return spsFeeEmponceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsFeeEmponce vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeEmponceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsFeeEmponce> datas = spsFeeEmponceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsFeeEmponce> findAll(SpsFeeEmponce vo){
		
		List<SpsFeeEmponce> datas = spsFeeEmponceDao.freeFind(vo);
		return datas;
		
	}



	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 11:35:49

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
	@Override
	public List<SpsFeeEmponce> queryEmpOnceItems(SpsFeeEmponce emponce) {
		return spsFeeEmponceDao.queryEmpOnceItems(emponce);
	}

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
	@Override
	public int delEmpOnceItems(ContextInfo cti, SpsFeeEmponce emponce) {
		return spsFeeEmponceDao.delEmpOnceItems(cti, emponce);
	}

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
	@Override
	public Result createEmItems(ContextInfo cti, Integer spId, SpsFeeEmponce[] emponces,String payType,String insuredMonth) {
		if(null != emponces){
			for (SpsFeeEmponce item : emponces){
				//替换空格，中英文的分号冒号
				item.setReason(item.getReason().replaceAll("[\\s:;：；]",""));
				if(null != item.getOfficialFee()){
					item.setFeeType("OFFICE");
				}
				item.setSpId(spId);
				if(null != item.getId()){
					item.setModifyDt(new Date());
					spsFeeEmponceDao.update(cti, item);
				}else{
					item.setDr(0);
					item.setSource(2);
					item.setCreateDt(new Date());
					item.setModifyDt(new Date());
					if(!StringUtils.isBlank(payType))
						item.setPayType(payType);
					if(!StringUtils.isBlank(insuredMonth))
						item.setInsuredMonth(insuredMonth);
					spsFeeEmponceDao.insert(cti, item);
				}
			}
		}
		return Result.createResult().setSuccess(true);
	}

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
	@Override
	public List<SpsFeeEmponce> queryEmponceWithDetails(Integer spid, Integer cpid, String period, List<Integer> empIds) {
		return spsFeeEmponceDao.queryEmponceWithDetail(spid, cpid, period, empIds);
	}

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
	@Override
	public List<Map<String, Object>> queryChildBillEmpOnceItems(Integer sp_id, String p_sp_id, Integer cp_id, String period) {
		return spsFeeEmponceDao.queryChildBillEmpOnceItems(sp_id,p_sp_id,cp_id,period);
	}


	/**
	 *  任务单补缴 终止
	 *  执行 算费 dr = 1操作
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-09 14:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 14:15
	 *  @updateAuthor  :
	 */
	@Override
	public Result removeInsFunSupply(ContextInfo cti, SpsFeeEmponce vo) {
		Result result = Result.createResult().setSuccess(false);
		Integer rem = spsFeeEmponceDao.removeInsFunSupply(cti, vo);
		if(0 <rem){
			result.setSuccess(true).setError("终止补缴任务单执行成功！");
		}else{
			result.setSuccess(false).setError("终止补缴任务单执行失败！");
		}
		return result;
	}

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
	@Override
	public void insFunSupply(ContextInfo cti, Integer spId, Integer taskId, Integer empId, Integer bsTypeId,Integer areaId,Integer schemeId, BigDecimal payBase, List<BsRatioCalcInterface> empinsurances, Date startDate, Date endDate) {
		if (null != taskId && null != empId && null != bsTypeId && null != payBase && null != startDate
				&& null != endDate) {
			// TODO 处理算费
			List<Integer> empIds = new ArrayList<>();
			empIds.add(empId);
			CmEmployee employee = cmEmployeeService.findEmpListWithDetailByEmpids(empIds).get(0);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			// 作废以前生成的补缴一次性费用
			SpsFeeEmponce onceqry = new SpsFeeEmponce();
			onceqry.setPayType("PATCH");
			onceqry.setSource(1);
			onceqry.setSourceid(taskId);
			List<SpsFeeEmponce> oldonces = findAll(onceqry);
			if (null != oldonces && oldonces.size() > 0) {
				for(SpsFeeEmponce oldonce : oldonces) {
					oldonce.setDr(1);
					save(cti, oldonce);
				}
			}

			int index = 0;
			/**
			 * 判断当前业务截至日期
			 */
			SpsScheme spsScheme = new SpsScheme();
			spsScheme.setSchemeId(schemeId);
			spsScheme = spsSchemeService.findByPK(spsScheme);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int curr_day = calendar.get(Calendar.DAY_OF_MONTH);
			String period = format.format(new Date());
			if(null != spsScheme &&  null != spsScheme.getEndDate() && curr_day > spsScheme.getEndDate()){
				calendar.add(Calendar.MONTH,1);
				period = format.format(calendar.getTime());
			}
			
			/*获取当前员工的缴费详情数据
			 * 不足一年按年，判断当前年缴险种年度是否缴费，年度交叉
			 * 不足一年按月，判断当前缴费详情是否包含当前补缴月度，不考虑差额补缴
			 * 月缴险种，等同于不足一年按月
			 */
			//判断年缴险种，需要判断年度是否有交叉，所以向前向后多取12月数据
			Calendar bcal = Calendar.getInstance();
			bcal.setTime(startDate);
			bcal.add(Calendar.MONTH, -12);
			Calendar ecal = Calendar.getInstance();
			ecal.setTime(endDate);
			ecal.add(Calendar.MONTH, 12);
			List<CmEmployeeInsurance> existEmpIns = cmEmployeeInsuranceService.findEmployeeInsuranceByEmpIdsAndPeriod(empIds, format.format(ecal.getTime()), format.format(bcal.getTime()), null);
			
			Map<Integer, List<CmEmployeeInsurance>> existEmpInsMap = new HashMap<>();
			for(CmEmployeeInsurance ins : existEmpIns) {
				if (!existEmpInsMap.containsKey(ins.getInsuranceId())) {
					existEmpInsMap.put(ins.getInsuranceId(), new ArrayList<CmEmployeeInsurance>());
				}
				List<CmEmployeeInsurance> inss = existEmpInsMap.get(ins.getInsuranceId());
				inss.add(ins);
			}
			
			while (cal.getTime().compareTo(endDate) <= 0) {
				SpsFeeEmponce emponce = new SpsFeeEmponce();
				emponce.setSpId(spId);
				emponce.setCpId(employee.getCpId());
				emponce.setEmpId(empId);
				emponce.setFeeType("OFFICE");
				emponce.setInsuredMonth(format.format(cal.getTime()));
				emponce.setPayType("PATCH");
				emponce.setPeriod(period);
				emponce.setSource(1);
				emponce.setSourceid(taskId);
				emponce.setReason(String.format("补缴%s费用", emponce.getInsuredMonth()));
				emponce.setSpsFeeDetails(new ArrayList<SpsFeeEmponcedetail>());
                emponce.setSalary(payBase);
				if (null != empinsurances && empinsurances.size() > 0) {
					for (BsRatioCalcInterface employeeInsurance : empinsurances) {
						BsArearatio ratio = bsArearatioService.findByPK(employeeInsurance.getRatioId());
						if (null == ratio) {
							BdInsurance bdins = bdInsuranceService.findByPK(employeeInsurance.getInsuranceId());
							if (null == employeeInsurance.getRatioId())
								throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",employee.getName(),bdins.getName()));
							throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例",employee.getName(),bdins.getName()));
						}
						//月缴、年缴头一月，或者年缴与缴费期间
						if (ratio.billingCycle_MONTH.equals(ratio.getBillingCycle()) || (index == 0 || (cal.get(Calendar.MONTH) + 1) == ratio.getPayPeriod())) {
							SpsFeeEmponcedetail detail = new SpsFeeEmponcedetail();
							detail.setCorpRatio(employeeInsurance.getCorpRatio());
							detail.setEmpRatio(employeeInsurance.getEmpRatio());
							detail.setInsuranceType(employeeInsurance.getInsuranceId());
							detail.setRatioId(employeeInsurance.getRatioId());
							detail.setCorpPaybase(payBase);
							detail.setEmpPaybase(payBase);
							detail.setCorpAddition(employeeInsurance.getCorpAddition());
							detail.setPsnAddition(employeeInsurance.getPsnAddition());
							bsArearatioService.calc(cti, detail, emponce.getInsuredMonth(),null);
							if (!ratio.billingCycle_MONTH.equals(ratio.getBillingCycle())) {
								//取当前期间对应的年缴期间范围
								Calendar bYearPayCal = Calendar.getInstance();
								Calendar eYearPayCal = Calendar.getInstance();
								if((cal.get(Calendar.MONTH) + 1) < ratio.getPayPeriod()) {
									bYearPayCal.setTime(new Date(cal.getTime().getYear(),ratio.getPayPeriod() - 1,1));
									bYearPayCal.add(Calendar.YEAR, -1);
									eYearPayCal.setTime(new Date(bYearPayCal.getTime().getYear() + 1,bYearPayCal.get(Calendar.MONTH) - 1,bYearPayCal.get(Calendar.DATE)));
									if(cal.compareTo(bYearPayCal) > 0)
										bYearPayCal = cal;
									if(endCal.compareTo(eYearPayCal) < 0)
										eYearPayCal = endCal;
								}else{
									bYearPayCal.setTime(new Date(cal.getTime().getYear(),ratio.getPayPeriod() - 1,1));
									eYearPayCal.setTime(new Date(bYearPayCal.getTime().getYear() + 1,bYearPayCal.get(Calendar.MONTH) - 1,bYearPayCal.get(Calendar.DATE)));
									if(cal.compareTo(bYearPayCal) > 0)
										bYearPayCal = cal;
									if(endCal.compareTo(eYearPayCal) < 0)
										eYearPayCal = endCal;
								}
								//判断年缴期间范围和缴纳详情未匹配月数
								List<CmEmployeeInsurance> existInss = existEmpInsMap.get(ratio.getInsuranceId());
								int unPayMonths = (eYearPayCal.get(Calendar.YEAR) - bYearPayCal.get(Calendar.YEAR)) * 12 + eYearPayCal.get(Calendar.MONTH) - bYearPayCal.get(Calendar.MONTH) + 1;
								if (null != existInss && existInss.size() > 0) {
									for (CmEmployeeInsurance ins : existInss) {
										Date acrossBegin = bYearPayCal.getTime();
										Date acrossEnd = eYearPayCal.getTime();
										try {
											String beginPeriod = ins.getBeginPeriod();
											if (StringUtils.isBlank(beginPeriod))
												beginPeriod = "1900-01";
											if (format.parse(beginPeriod).after(bYearPayCal.getTime())) {
												acrossBegin = format.parse(beginPeriod);
											}
											String endPeriod = ins.getEndPeriod();
											if (StringUtils.isBlank(endPeriod))
												endPeriod = "2099-12";
											if (format.parse(endPeriod).before(eYearPayCal.getTime())) {
												acrossEnd = format.parse(endPeriod);
											}
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											throw new RuntimeException(e.getMessage());
										}
										//取出年缴期间与缴纳详情交叉月数，这些已算作支付过费用
										int months =  (acrossEnd.getYear() - acrossBegin.getYear()) * 12 + acrossEnd.getMonth() - acrossBegin.getMonth() + 1;
										if (months > 0)
											unPayMonths = unPayMonths - months;
									}
								}
								if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())) {
									// 未匹配月数计补缴金额
									if(unPayMonths > 0){
										detail.setCorpPayment(Arith.div(detail.getCorpPayment(),new BigDecimal(12.0/unPayMonths)));
										detail.setCorpAddition(Arith.div(detail.getCorpAddition(),new BigDecimal(12.0/unPayMonths)));
										detail.setEmpPayment(Arith.div(detail.getEmpPayment(),new BigDecimal(12.0/unPayMonths)));
										detail.setPsnAddition(Arith.div(detail.getPsnAddition(),new BigDecimal(12.0/unPayMonths)));
									}else{
										detail.setCorpPayment(BigDecimal.ZERO);
										detail.setCorpAddition(BigDecimal.ZERO);
										detail.setEmpPayment(BigDecimal.ZERO);
										detail.setPsnAddition(BigDecimal.ZERO);
									}
								}
								else if (ratio.billingCycle_YEAR_ANNUALY.equals(ratio.getBillingCycle())) {
									// 未匹配月数＝12，计一年补缴费用，否则0
									if (unPayMonths != 12) {
										detail.setCorpPayment(BigDecimal.ZERO);
										detail.setCorpAddition(BigDecimal.ZERO);
										detail.setEmpPayment(BigDecimal.ZERO);
										detail.setPsnAddition(BigDecimal.ZERO);
									}
								}
							}
							if (Arith.ignoreNull(detail.getCorpPayment()).doubleValue() > 0 || Arith.ignoreNull(detail.getCorpPayment()).doubleValue() > 0) {
								emponce.getSpsFeeDetails().add(detail);
								emponce.setOfficialFee(Arith.add(emponce.getOfficialFee(), detail.getCorpPayment(),
										detail.getEmpPayment()));
							}
						}
					}
				} else {
					// TODO 如果未上保险 根据地区规则+库生成
//						for (BsInsurancerule insrule : rule.getCurBsRuleversion().getBsInsurancerules()) {
//							if ("FUND".equals(insuranceorfund) && Integer.valueOf(6).equals(insrule.getInsuranceId())) {
//								;
//							} else if ("INSURANCE".equals(insuranceorfund)
//									&& insrule.getInsuranceId().intValue() <= 5) {
//							}
//						}

				}
				emponce.setDr(0);
				this.save(cti, emponce);
				for (SpsFeeEmponcedetail detail : emponce.getSpsFeeDetails()) {
					detail.setEmpFeeId(emponce.getId());
					spsFeeEmponcedetailService.save(cti, detail);
				}
				cal.add(Calendar.MONTH, 1);
				index++;
			}
			/*}*/
		}
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2016,8,1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar bYearPayCal = Calendar.getInstance();
		Calendar eYearPayCal = Calendar.getInstance();
			bYearPayCal.setTime(new Date(cal.getTime().getYear(),9,1));
			bYearPayCal.add(Calendar.YEAR, -1);
			eYearPayCal.setTime(bYearPayCal.getTime());
			eYearPayCal.add(Calendar.MONTH, 11);
		System.out.println(format.format(bYearPayCal.getTime()));
		System.out.println(format.format(eYearPayCal.getTime()));
	}

}
