package com.xfs.corp.service.impl;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.bill.model.BdBillrule;
import com.xfs.bill.service.BdBillruleService;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.Arith;
import com.xfs.common.util.Config;
import com.xfs.corp.dao.CmEmployeeInsuranceDao;
import com.xfs.corp.dto.EmplnsFundDetailDto;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeInsuranceService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-10 14:10:00
 * @version 	: V1.0
 */
@Service
public class CmEmployeeInsuranceServiceImpl implements CmEmployeeInsuranceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmEmployeeInsuranceServiceImpl.class);

	@Autowired
	private CmEmployeeInsuranceDao cmEmployeeInsuranceDao;
	@Autowired
	private BsArearatioService bsArearatioService;
	@Autowired
	private BdInsuranceService bdInsuranceService;
	@Autowired
	private CmCorpService cmCorpService;
	@Autowired
	private BdBillruleService bdBillruleService;
	@Autowired
	private CmEmployeeService cmEmployeeService;
	@Autowired
	private SpsSchemeService spsSchemeService;
	@Autowired
	private BsAreaService bsAreaService;

	public int save(ContextInfo cti, BsRatioCalcInterface vo) {
		return cmEmployeeInsuranceDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, CmEmployeeInsurance vo) {
		return cmEmployeeInsuranceDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, CmEmployeeInsurance vo) {
		return cmEmployeeInsuranceDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, CmEmployeeInsurance vo) {
		return cmEmployeeInsuranceDao.update(cti, vo);
	}

	public int updateCmEmployeeInsurance(ContextInfo cti, CmEmployeeInsurance vo) {
		return cmEmployeeInsuranceDao.updateCmEmployeeInsurance(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsRatioCalcInterface vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeInsuranceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmEmployeeInsurance> datas = cmEmployeeInsuranceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<CmEmployeeInsurance> findAll(BsRatioCalcInterface vo) {

		List<CmEmployeeInsurance> datas = cmEmployeeInsuranceDao.freeFind(vo);
		return datas;

	}

	@Override
	public List<Map<String, Object>> FindEmployeeInsuranceMapByEmp(CmEmployeeInsurance employeeInsurance,
			Integer areaId, String insurance) {
		return cmEmployeeInsuranceDao.findEmployeeInsuranceMapByEmp(employeeInsurance, areaId, insurance);
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/08/03 16:26:34

	/*
	 * 调整员工保险数据
	 * @param emp 员工及最新保险情况
	 * @param beginPeriod 调整开始期间 yyyy-MM
	 */
	@Override
	public Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod, Integer taskid,String payType) {
		return adjustEmployeeInsurance(cti, spId, emp, insuranceOrFund, beginPeriod,null, taskid,payType);
	}

	@Override
	public Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod, String endPeriod ,Integer taskid,String payType) {
		List<CmEmployee> emps = new ArrayList<>();
		emps.add(emp);
		List<Integer> insuranceIds = new ArrayList<Integer>();
		for (CmEmployeeInsurance ins : emp.getCmEmployeeInsurances()) {
			insuranceIds.add(ins.getInsuranceId());
		}
		return adjustEmployeeInsurance(cti, spId, emps, insuranceOrFund, beginPeriod,endPeriod, taskid, insuranceIds,payType);
	}

	@Override
	public Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod, Integer taskid) {
		return adjustEmployeeInsurance(cti, spId, emp, insuranceOrFund, beginPeriod, null, taskid,"MONTH");
	}
	
	@Override
	public Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, List<CmEmployee> emps, String insuranceOrFund, String beginPeriod, Integer taskid) {
		List<Integer> insuranceIds = this.bdInsuranceService.findInsuranceIds(insuranceOrFund);
		return adjustEmployeeInsurance(cti, spId, emps, insuranceOrFund, beginPeriod, null,taskid, insuranceIds,"MONTH");
	}
	/*
	 * 调整员工保险数据
	 * @param emps 员工及最新保险情况
	 * @param beginPeriod 调整开始期间 yyyy-MM
	 */
	@Override
	public Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, List<CmEmployee> emps, String insuranceOrFund, String beginPeriod, String endPeriod,Integer taskid, List<Integer> insuranceIds ,String payType) {
		List<Integer> empIds = new ArrayList<>();
		for (CmEmployee emp : emps) {
			empIds.add(emp.getEmpId());
		}
		String key = "empid%s_insid%s";
		Map<String,ArrayList<CmEmployeeInsurance>> oldEmpMap = new HashMap<>();
		//取费用段结束期间大于调整月的所有段
		List<CmEmployeeInsurance> empins = null;
		if("PATCH".equals(payType)){
			empins = cmEmployeeInsuranceDao.findEmployeeInsuranceByEmpIdsAndPeriod(empIds, beginPeriod, endPeriod, insuranceIds,payType);
		}else{
			empins = cmEmployeeInsuranceDao.findEmployeeInsuranceByEmpIdsAndPeriod(empIds, null, beginPeriod, insuranceIds,payType);
		}
		if (null != empins) {
			for(CmEmployeeInsurance oldEmpIns : empins) {
				if("PATCH".equals(payType)){
					oldEmpIns.setDr(1);
					this.save(cti, oldEmpIns);
					continue;
				}
				String realKey = String.format(key, oldEmpIns.getEmpId(),oldEmpIns.getInsuranceId());
				if (!oldEmpMap.containsKey(realKey)) {
					oldEmpMap.put(realKey, new ArrayList<CmEmployeeInsurance>());
				}
				oldEmpMap.get(realKey).add(oldEmpIns);
			}
		}
		Result r = Result.createResult();
		for (CmEmployee emp : emps) {
			if (null != emp.getCmEmployeeInsurances()) {
				for (CmEmployeeInsurance empIns : emp.getCmEmployeeInsurances()) {
					if (insuranceIds.contains(empIns.getInsuranceId())) {
						ArrayList<CmEmployeeInsurance> oldInss = oldEmpMap.get(String.format(key, emp.getEmpId(),empIns.getInsuranceId()));
						if(!StringUtils.isBlank(empIns.getBeginPeriod()))
							beginPeriod = empIns.getBeginPeriod();
						/**
                         * 处理分段
						 */
						r = loopAdjustEmp(cti,emp,empIns,oldInss,beginPeriod,taskid);
						/**
						 * 获取起始时间对应的比例版本列表中
						 */
						if(null != r.getData() && null != r.getData().get("nextRatiover")) {
							BsArearatiover ver = (BsArearatiover) r.getData().get("nextRatiover");
							List<CmEmployee> curr_emps = new ArrayList<>();
							curr_emps.add(emp);
							List<Integer> curr_insuranceIds = new ArrayList<>();
							empIns.setBeginPeriod(null);
							curr_insuranceIds.add(empIns.getInsuranceId());
							adjustEmployeeInsurance(cti, spId, curr_emps, insuranceOrFund, ver.getBeginPeriod(), null ,taskid,curr_insuranceIds,payType);
						}
					}
				}
			}
			try{
				/**
				 * 薪酬费用变更通知
				 */
				CmEmployee e = cmEmployeeService.findByPk(emp.getEmpId());
				CmCorp c = cmCorpService.findByPK(e.getCpId());
				if(null != e.getThreeId() && null != c.getTenantId()){
					JSONObject jsonBody = new JSONObject();
					jsonBody.put("staffId", e.getThreeId().toString());// 员工ID
					jsonBody.put("tenantId", c.getTenantId());// 租户ID
					jsonBody.put("beginPeriod",beginPeriod);
					// 调用薪酬SaaS消息接口
					String msg = HttpClientUtil.doPostJson(Config.getProperty("yonyouhr_apiurl")+"/salarysrv/internal/message/insu", jsonBody.toJSONString());
					System.out.println(msg);
				}
			}catch (Exception e){

			}
		}
		return r;
	}

	/**
	 *  循环调用分段
	 *  @param   cti
	 *  @param   emp
	 *  @param   empIns
	 *  @param   oldInss
	 *  @param   beginPeriod
	 *  @param    taskid
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-16 20:46
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-16 20:46
	 *  @updateAuthor  :
	 */
	public Result loopAdjustEmp(ContextInfo cti,CmEmployee emp,CmEmployeeInsurance empIns,ArrayList<CmEmployeeInsurance> oldInss,String beginPeriod,Integer taskid){
		/**
		 * 定义起始时间--结束时间
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = format.getCalendar();
		Date beginPeriodDate;
		try {
			beginPeriodDate = format.parse(beginPeriod);
		} catch (ParseException e1) {
			throw new RuntimeException(e1);
		}
		cal.setTime(beginPeriodDate);
		cal.add(Calendar.MONTH, -1);
		String endPeriod = format.format(cal.getTime());

		/**
		 * 判断当前是调整还是设置起始时间/结束时间
		 */
		if(empIns.isExtend()){
			return adjustExtendEmp(cti,emp,empIns,oldInss,beginPeriod,endPeriod,taskid);
		}else{
			return adjustEmp(cti,emp,empIns,oldInss,beginPeriod,endPeriod,taskid);
		}
	}

	/**
	 *  调整起始时间
	 *  @param   cti, emp, empIns, oldInss, beginPeriod, endPeriod, taskid
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-19 10:07
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-19 10:07
	 *  @updateAuthor  :
	 */
	private Result adjustExtendEmp(ContextInfo cti,CmEmployee emp,CmEmployeeInsurance empIns,ArrayList<CmEmployeeInsurance> oldInss,String beginPeriod,String endPeriod,Integer taskid){
		Result r = Result.createResult().setSuccess(false);
		BsArearatiover nextRatiover = null;
		//不存在原费用段记录，则创建
		if (null == oldInss || oldInss.size() == 0) {
			CmEmployeeInsurance newIns = new CmEmployeeInsurance();
			BeanUtils.copyProperties(empIns,newIns);
			newIns.setId(null);
			newIns.setDr(0);
			newIns.setEmpId(emp.getEmpId());
			newIns.setBeginPeriod(beginPeriod);
			newIns.setBeginTask(taskid);
			//未考虑跨版本，如跨版本，应产生多条费用段
			r = bsArearatioService.calc(cti, newIns, newIns.getBeginPeriod(),newIns.getEndPeriod());
			if (!r.isSuccess()) {
				return r;
			}
			if(null != r.getData().get("nextRatiover")) {
				nextRatiover = (BsArearatiover) r.getData().get("nextRatiover");
			}
			this.save(cti, newIns);
		} else {
			int index = 0;//执行次数
			for (CmEmployeeInsurance oldIns : oldInss) {
				index ++;
				CmEmployeeInsurance newIns = new CmEmployeeInsurance();
				newIns.setEmpId(oldIns.getEmpId());
				newIns.setEndPeriod(oldIns.getEndPeriod());
				//旧费用段如果已被减员任务单终止，需记录endtask，减员任务单终止，需要根据endtask打开费用段结束期间
				newIns.setEndTask(oldIns.getEndTask());
				newIns.setDr(0);
				newIns.setBeginTask(taskid);
				if(null == empIns.getRatioId())
					newIns.setRatioId(oldIns.getRatioId());
				else
					newIns.setRatioId(empIns.getRatioId());
				newIns.setInsuranceId(empIns.getInsuranceId());
				newIns.setCorpPaybase(empIns.getCorpPaybase());
				newIns.setEmpPaybase(empIns.getEmpPaybase());
				//如果费用段开始期间早于调整期间，结束期间晚于调整期间，截止费用段
				if (oldIns.getBeginPeriod().compareTo(beginPeriod) < 0) {
					if(StringUtils.isBlank(oldIns.getEndPeriod())){
						//截至费用段
						oldIns.setEndTask(taskid);
						oldIns.setEndPeriod(endPeriod);
						newIns.setBeginPeriod(beginPeriod);
					}else{
						if(oldIns.getEndPeriod().compareTo(beginPeriod) >= 0){
							oldIns.setEndTask(taskid);
							oldIns.setEndPeriod(endPeriod);
							newIns.setBeginPeriod(beginPeriod);
						}else{
							newIns.setBeginPeriod(beginPeriod);
							newIns.setEndPeriod(null == empIns.getEndPeriod() ? null : empIns.getEndPeriod());
						}
					}
				} else {
					//如果费用段起始时间相等或晚于调整时间，则作废并产生同等费用段记录
					oldIns.setDr(1);
					newIns.setBeginPeriod(empIns.getBeginPeriod());
					newIns.setEndPeriod(null == empIns.getEndPeriod() ? null : empIns.getEndPeriod());
				}
				//持久化
				this.save(cti, oldIns);
				//未考虑跨版本，如跨版本，应产生多条费用段
				r = bsArearatioService.calc(cti, newIns, newIns.getBeginPeriod(),null);
				if (!r.isSuccess()) {
					return r;
				}
				//记录下个版本时间段
				if(null == nextRatiover){
					if(null != r.getData().get("nextRatiover")) {
						nextRatiover = (BsArearatiover) r.getData().get("nextRatiover");
					}
				}
				/**
				 * 当前险种需要向前、向后调整起始、截至月份
				 */
				if(index > 1){
					continue;
				}
				if(!StringUtils.isBlank(empIns.getEndPeriod())){
					newIns.setEndPeriod(empIns.getEndPeriod());
				}
				this.save(cti, newIns);
			}
		}
		//存入下个版本时间段
		r.setDataValue("nextRatiover",nextRatiover);
		return r;
	}

	/**
	 *  调整变更月份的比例/基数
	 *  @param   cti, emp, empIns, oldInss, beginPeriod, endPeriod, taskid
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-19 10:08
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-19 10:08
	 *  @updateAuthor  :
	 */
	private Result adjustEmp(ContextInfo cti,CmEmployee emp,CmEmployeeInsurance empIns,ArrayList<CmEmployeeInsurance> oldInss,String beginPeriod,String endPeriod,Integer taskid){
		Result r = Result.createResult().setSuccess(false);
		BsArearatiover nextRatiover = null;
		//不存在原费用段记录，则创建
		if (null == oldInss || oldInss.size() == 0) {
			CmEmployeeInsurance newIns = new CmEmployeeInsurance();
			BeanUtils.copyProperties(empIns,newIns);
			newIns.setId(null);
			newIns.setDr(0);
			newIns.setEmpId(emp.getEmpId());
			newIns.setBeginPeriod(beginPeriod);
			newIns.setEndPeriod(null);
			newIns.setBeginTask(taskid);
			//未考虑跨版本，如跨版本，应产生多条费用段
			r = bsArearatioService.calc(cti, newIns, newIns.getBeginPeriod(),null);
			if (!r.isSuccess()) {
				return r;
			}
			if(null != r.getData().get("nextRatiover")) {
				nextRatiover = (BsArearatiover) r.getData().get("nextRatiover");
			}
			this.save(cti, newIns);
		} else {
			int index = 0;//执行次数
			for (CmEmployeeInsurance oldIns : oldInss) {
				if(!StringUtils.isBlank(oldIns.getEndPeriod()) && oldIns.getEndPeriod().compareTo(beginPeriod) < 0)
					continue;
				index++;
				CmEmployeeInsurance newIns = new CmEmployeeInsurance();
				newIns.setEmpId(oldIns.getEmpId());
				newIns.setEndPeriod(oldIns.getEndPeriod());
				//旧费用段如果已被减员任务单终止，需记录endtask，减员任务单终止，需要根据endtask打开费用段结束期间
				newIns.setEndTask(oldIns.getEndTask());
				newIns.setDr(0);
				newIns.setBeginTask(taskid);
				if(null == empIns.getRatioId())
					newIns.setRatioId(oldIns.getRatioId());
				else
					newIns.setRatioId(empIns.getRatioId());
				newIns.setInsuranceId(empIns.getInsuranceId());
				newIns.setCorpPaybase(empIns.getCorpPaybase());
				newIns.setEmpPaybase(empIns.getEmpPaybase());
				//如果费用段开始期间早于调整期间，结束期间晚于调整期间，截止费用段
				if (oldIns.getBeginPeriod().compareTo(beginPeriod) < 0) {
					oldIns.setEndTask(taskid);
					oldIns.setEndPeriod(endPeriod);
					newIns.setBeginPeriod(beginPeriod);
				} else {
					//如果费用段起始时间相等或晚于调整时间，则作废并产生同等费用段记录
					oldIns.setDr(1);
					newIns.setBeginPeriod(oldIns.getBeginPeriod());
				}
				//持久化
				this.save(cti, oldIns);
				//未考虑跨版本，如跨版本，应产生多条费用段
				r = bsArearatioService.calc(cti, newIns, newIns.getBeginPeriod(),null);
				if (!r.isSuccess()) {
					return r;
				}
				//记录下个版本时间段
				if(null == nextRatiover){
					if(null != r.getData().get("nextRatiover")) {
						nextRatiover = (BsArearatiover) r.getData().get("nextRatiover");
					}
				}
				//判断是否进行版本迭代
				if(index == oldInss.size() && null != nextRatiover && !StringUtils.isBlank(newIns.getEndPeriod()) && nextRatiover.getBeginPeriod().compareTo(newIns.getEndPeriod()) >= 0){
					nextRatiover = null;
				}
				this.save(cti, newIns);
			}
		}
		//存入下个版本时间段
		r.setDataValue("nextRatiover",nextRatiover);
		return r;
	}

	@Override
	public void cancelEmployeeInsuranceByBeginTaskId(ContextInfo cti,Integer taskid) {
		cancelEmployeeInsuranceByBeginTaskId(cti,taskid,"MONTH");
	}

	@Override
	public void cancelEmployeeInsuranceByBeginTaskId(ContextInfo cti,Integer taskid,String payType) {
		CmEmployeeInsurance qry = new CmEmployeeInsurance();
		qry.setBeginTask(taskid);
		List<CmEmployeeInsurance> insurances = cmEmployeeInsuranceDao.freeFind(qry);
		if (null != insurances && insurances.size() > 0) {
			for (CmEmployeeInsurance ins : insurances) {
				// 参保任务单终止，废弃任务开始之后所有的费用段
				List<Integer> empids = new ArrayList<>();
				empids.add(ins.getEmpId());
				List<Integer> insIds = new ArrayList<>();
				insIds.add(ins.getInsuranceId());
				if (StringUtils.isEmpty(ins.getEndPeriod())) {
					ins.setDr(1);
					cmEmployeeInsuranceDao.save(cti, ins);
				} else {
					List<CmEmployeeInsurance> cancelInss = cmEmployeeInsuranceDao.findEmployeeInsuranceByEmpIdsAndPeriod(empids, null, ins.getEndPeriod(), insIds,payType);
					if (null != cancelInss) {
						for (CmEmployeeInsurance cancelIns : cancelInss) {
							cancelIns.setDr(1);
							cmEmployeeInsuranceDao.save(cti, cancelIns);
						}
					}
				}
			}
		}
	}
	@Override 
	public void resetEmployeeInsuranceByEndTaskId(ContextInfo cti,Integer taskid) {
		CmEmployeeInsurance qry = new CmEmployeeInsurance();
		qry.setEndTask(taskid);
		qry.setDr(0);
		List<CmEmployeeInsurance> insurances = cmEmployeeInsuranceDao.freeFind(qry);
		if (null != insurances && insurances.size() > 0) {
			for (CmEmployeeInsurance ins : insurances) {
				ins.setEndPeriod(null);
				ins.setEndTask(null);
				ins.setDr(0);
				cmEmployeeInsuranceDao.save(cti, ins);
			}
		}
	}

	/*
	 * 结束员工保险记录
	 * @param empIds 员工id
	 * @param insuranceOrFund 社保INSURANCE公积金FUND
	 * @param period 结束期间
	 */
	@Override
	public void endEmployeeInsuranceByEmpIdsAndPeriod(ContextInfo cti, List<Integer> empIds, String insuranceOrFund, String endPeriod, Integer endTaskId) {

		List<Integer> insuranceIds = this.bdInsuranceService.findInsuranceIds(insuranceOrFund);
		cmEmployeeInsuranceDao.endEmployeeInsuranceListSetDr(cti,empIds,insuranceIds,endPeriod,endTaskId);
		cmEmployeeInsuranceDao.endEmployeeInsuranceListByEmpIdsAndPeriod(cti, empIds, insuranceIds, endPeriod, endTaskId);
	}
	
	@Override
	public void endEmployeeInsuranceByEmpIdsAndPeriod(ContextInfo cti, List<Integer> empIds, String insuranceOrFund, String endPeriod, Integer endTaskId, List<Integer> insuranceIds) {

		cmEmployeeInsuranceDao.endEmployeeInsuranceListByEmpIdsAndPeriod(cti, empIds, insuranceIds, endPeriod, endTaskId);
	}
	
	@Override
    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String period,List<Integer> insuranceIds) {
	    return cmEmployeeInsuranceDao.findEmployeeInsuranceByEmpIdsAndPeriod(empIds, period,insuranceIds);
    }
	@Override
    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String bperiod, String eperiod,List<Integer> insuranceIds) {
	    return cmEmployeeInsuranceDao.findEmployeeInsuranceByEmpIdsAndPeriod(empIds, bperiod, eperiod,insuranceIds);
    }
    /**
     *  获取员工的保险种类信息
     *  @param
     * @return    :
     *  @createDate   : 2017/1/18 11:54
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/18 11:54
     *  @updateAuthor  :
     */
	@Override
	public List<CmEmployeeInsurance> findEmployeeInsuranceByEmployee(List<Integer> empIds, List<Integer> insuranceIds) {
		return cmEmployeeInsuranceDao.findEmployeeInsuranceByEmployee(empIds,insuranceIds);
	}

	/**
	 * 查询社保数据
	 *  @param month
	 *  @param tenantId
	 *  @return 
	 *  @createDate  	: 2017年5月22日 下午4:36:45
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月22日 下午4:36:45
	 *  @updateAuthor  	:
	 */
	@Override
	public Result findAllInsurance(String month, String tenantId) {
		Result result = Result.createResult().setSuccess(true);
		return this.findEmps(result,month,tenantId,null);
	}
	
	/**
	 * 获取个人社保缴费详情
	 *  @param month
	 *  @param tenantId
	 *  @param empId
	 *  @return 
	 *  @createDate  	: 2017年5月23日 上午11:14:17
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月23日 上午11:14:17
	 *  @updateAuthor  	:
	 */
	@Override
	public Result findOneInsurance(String month, String tenantId,String empId) {
		Result result = Result.createResult().setSuccess(true);
		return this.findEmps(result,month,tenantId,empId);
	}
	
	/**
	 * 获取企业下人员
	 *  @param result
	 *  @param month
	 *  @param tenantId
	 *  @param empId
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年5月23日 上午11:10:45
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月23日 上午11:10:45
	 *  @updateAuthor  :
	 */
	public Result findEmps(Result result,String month, String tenantId,String empId){
		try {
			// 根据租户ID查询企业ID
			CmCorp cmCorp = cmCorpService.findCmCorpByTenantId(tenantId);
			if(null != cmCorp){
				// 获取企业下所有员工
				CmEmployee vo = new CmEmployee();
				vo.setCpId(cmCorp.getCpId());
				if(null != empId){
					vo.setThreeId(empId);
				}
				List<CmEmployee> cmEmployees = cmEmployeeService.findAll(vo);
				List<Map<String, Object>> list = new ArrayList<>();
				for(CmEmployee cmEmployee : cmEmployees){
					List<EmplnsFundDetailDto> ins = this.findEmpInsFundDetail(cmEmployee,month,"INSURANCE");
					List<EmplnsFundDetailDto> fund = this.findEmpInsFundDetail(cmEmployee,month,"FUND");
					Map<String, Object> map = new HashMap<>();
					map.put("empId", null==cmEmployee.getThreeId()?"":cmEmployee.getThreeId());
					map.put("name", cmEmployee.getName());
					map.put("insurance", ins);
					map.put("fund", fund);
					list.add(map);
				}
				result.setDataValue("empDetail", list);
			}else{
				result.setSuccess(false);
				result.setMsg("当前企业不存在");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("系统出错请联系负责人");
			return result;
		}
		result.setMsg("请求成功");
		return result;
	}
	
	/**
	 * 根据人员获取社保缴费详情
	 *  @param cmEmployee
	 *  @param month
	 *  @param insuranceFundType
	 *  @return 
	 *	@return 			: List<EmplnsFundDetailDto> 
	 *  @createDate  	: 2017年5月23日 上午11:11:02
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月23日 上午11:11:02
	 *  @updateAuthor  :
	 */
	public List<EmplnsFundDetailDto> findEmpInsFundDetail(CmEmployee cmEmployee,String month,String insuranceFundType){
		try {
			SpsScheme scheme = new SpsScheme();
			scheme.setSchemeId(cmEmployee.getSchemeId());
			scheme = spsSchemeService.findByPK(scheme);
			if(null != scheme){
				BsArea bsArea = new BsArea();
				bsArea.setAreaId(scheme.getAreaId());
				bsArea = bsAreaService.findByPK(bsArea);
				// 获取地区规则
				BdBillrule billrule = bdBillruleService.findByAreaId(bsArea.getBelongCity());
				String insureMonth;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				if (BdBillrule.insuranceRule_CURMONTH.equals(billrule.getInsuranceRule())) {
					insureMonth = month;
				} else {
					Calendar nextPeriod = Calendar.getInstance();
					nextPeriod.setTime(format.parse(month));
					nextPeriod.add(Calendar.MONTH, 1);
					insureMonth = format.format(nextPeriod.getTime());
				}
				String fundMonth;
				if (BdBillrule.fundRule_CURMONTH.equals(billrule.getFundRule())) {
					fundMonth = month;
				} else {
					Calendar nextPeriod = Calendar.getInstance();
					nextPeriod.setTime(format.parse(month));
					nextPeriod.add(Calendar.MONTH, 1);
					fundMonth = format.format(nextPeriod.getTime());
				}
				String findMonth;
				if ("FUND".equals(insuranceFundType))
					findMonth = fundMonth;
				else
					findMonth = insureMonth;
				// 根据规则获取社保数据
				List<EmplnsFundDetailDto> emps = cmEmployeeInsuranceDao.findEmpInsFundDetailByEmpid(cmEmployee.getEmpId(), insureMonth, insuranceFundType);
				for (EmplnsFundDetailDto emplnsFundDetailDto : emps) {
					emplnsFundDetailDto.setAreaName(bsArea.getName());
					if (BsArearatio.billingCycle_MONTH_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())
							|| BsArearatio.billingCycle_YEAR_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())) {
						// 缴费参保月份等于年缴险种月份 或者 新参保员工
						if (((format.parse(findMonth).getMonth() + 1) == emplnsFundDetailDto.getPayPeriod())
								|| findMonth.equals(emplnsFundDetailDto.getBeginPeriod())) {
							// 年缴不足一年按年等同于月缴 不需要处理
							// 年缴不足一年按月
							if (BsArearatio.billingCycle_MONTH_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())) {
								if (StringUtils.isNotBlank(emplnsFundDetailDto.getBeginPeriod())) { 
									// 缴费月份-入职月份 如果 小于等于0 则 +12
									int months = emplnsFundDetailDto.getPayPeriod()
											- format.parse(emplnsFundDetailDto.getBeginPeriod()).getMonth() - 1;
									if (months <= 0)
										months += 12;
									emplnsFundDetailDto.setCorpPayment(Arith.div(emplnsFundDetailDto.getCorpPayment(),
											new BigDecimal(12.0 / months)));
									emplnsFundDetailDto.setCorpAddition(Arith.div(emplnsFundDetailDto.getCorpAddition(),
											new BigDecimal(12.0 / months)));
									emplnsFundDetailDto.setEmpPayment(Arith.div(emplnsFundDetailDto.getEmpPayment(),
											new BigDecimal(12.0 / months)));
									emplnsFundDetailDto.setEmpAddition(Arith.div(emplnsFundDetailDto.getEmpAddition(),
											new BigDecimal(12.0 / months)));
								}
							}
						} else {
							emplnsFundDetailDto.setCorpPayment(BigDecimal.ZERO);
							emplnsFundDetailDto.setEmpPayment(BigDecimal.ZERO);
						}
					}
				}
				return emps;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
