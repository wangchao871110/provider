package com.xfs.corp.service.impl;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.service.BsSysStateReportService;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.dao.CmEmployeeOrgDao;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.model.CmEmployeeOrg;
import com.xfs.corp.service.CmEmployeeOrgService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.service.SpsSchemeEmpService;
import com.xfs.sp.service.SpsSchemeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;



@Service
public class CmEmployeeOrgServiceImpl implements CmEmployeeOrgService,IRedisKeys {

	private static String INSURANCE_TYPE = "INSURANCE";

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmEmployeeOrgServiceImpl.class);
	
	@Autowired
	private CmEmployeeOrgDao cmEmployeeOrgDao;

	@Autowired
	private CmEmployeeService cmEmployeeService;

	@Autowired
	private SpsSchemeService spsSchemeService;

	@Autowired
	private SpsSchemeEmpService spsSchemeEmpService;

	@Autowired
	private BsSysStateReportService bsSysStateReportService;
	
	public int save(ContextInfo cti, CmEmployeeOrg vo ){
		return cmEmployeeOrgDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, CmEmployeeOrg vo ){
		return cmEmployeeOrgDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, CmEmployeeOrg vo ){
		return cmEmployeeOrgDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, CmEmployeeOrg vo ){
		return cmEmployeeOrgDao.update(cti,vo);
	}

	public PageModel findPage(PageInfo pi, CmEmployeeOrg vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeOrgDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmEmployeeOrg> datas = cmEmployeeOrgDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<CmEmployeeOrg> findAll(CmEmployeeOrg vo){
		
		List<CmEmployeeOrg> datas = cmEmployeeOrgDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/09/20 16:10:56

	/**
	 * 获取已经同步的人员信息
	 * @param vo
	 * @return
	 */
	public CmEmployeeOrg queryEmployeeOrg(CmEmployeeOrg vo){
		//检查当前缓存中是否存在此企业同步人员缓存
		vo.setChangeMark("Y");
		vo.setSyncMark("N");
		if(!RedisUtil.exists(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + vo.getCpId())){
			CmEmployeeOrg queryVo = new CmEmployeeOrg();
			queryVo.setCpId(vo.getCpId());
			List<CmEmployeeOrg> datas = findAll(queryVo);
			if(null == datas || datas.isEmpty()){
				datas = new ArrayList<>();
				RedisUtil.hput(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + vo.getCpId(),"--","--");
			}
			for(CmEmployeeOrg cmEmployeeOrg : datas){
				RedisUtil.hput(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployeeOrg.getCpId(),cmEmployeeOrg.getName()+"_"+cmEmployeeOrg.getIdentityCode() + "_" + cmEmployeeOrg.getInsuranceFundType(),cmEmployeeOrg);
			}
			RedisUtil.setKeyExpireTime(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + vo.getCpId(),30);
		}else {
			RedisUtil.setKeyExpireTime(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + vo.getCpId(),30);
		}

		Object ob = RedisUtil.hget(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + vo.getCpId(),vo.getName()+"_"+vo.getIdentityCode() + "_" + vo.getInsuranceFundType());
		if(null == ob){
			return vo;
		}
		CmEmployeeOrg cacheEmployee = (CmEmployeeOrg)ob;
//		if(StringUtils.isNotBlank(vo.getSignMark()) && vo.getSignMark().equals(cacheEmployee.getSignMark()))
//			return null;
		vo.setEmpId(cacheEmployee.getEmpId());
		vo.setId(cacheEmployee.getId());
		return vo;
	}

	/**
	 * 获取同步列表
	 * @param pi
	 * @param vo
	 * @return
	 */
	public PageModel querySynDataList(PageInfo pi, CmEmployeeOrg vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmEmployeeOrgDao.querySynDataListCount(vo);
		pm.setTotal(total);
		List<CmEmployeeOrg> datas = cmEmployeeOrgDao.querySynDataList(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 系统自动自动匹配人数
	 * @param vo
	 * @return
	 */
	public int querySynSystemDataList(CmEmployeeOrg vo){
		Integer total = cmEmployeeOrgDao.querySynSystemDataList(vo);
		return total;
	}

	/**
	 * 获取未匹配同步数据列表
	 * @param vo
	 * @return
	 */
	public List<CmEmployeeOrg> queryUnEmpIdSynDataList( CmEmployeeOrg vo) {
		List<CmEmployeeOrg> datas = cmEmployeeOrgDao.queryUnEmpIdSynDataList(vo);
		return datas;
	}


	/**
	 * 获取需要保存的同步数据
	 * @param vo
	 * @return
	 */
	public void saveSynOrgData(ContextInfo cti, CmCorp cmCorp ,CmEmployeeOrg vo, Result result){
		List<CmEmployee> cmEmployees =  cmEmployeeOrgDao.querySaveSynOrgData(vo,cmCorp);

		if(null != cmEmployees && !cmEmployees.isEmpty()){
			/**
			 * 缓存
			 */
			Map<Integer,SpsScheme> schemeMap = new HashMap<>();
			Map<String,List<CmEmployeeInsurance>> insuranceMap = new HashMap<>();
			for (CmEmployee cmEmployee : cmEmployees){
				SpsScheme scheme = schemeMap.get(cmEmployee.getSchemeId());
				cmEmployee.setDr(0);//将已经删除的员工恢复
				if(null == scheme){
					scheme = new SpsScheme();
					scheme.setSchemeId(cmEmployee.getSchemeId());
					scheme = spsSchemeService.findByPK(scheme);
					schemeMap.put(cmEmployee.getSchemeId(),scheme);
				}

				/**
				 * 方案为空
				 */
				if(null == scheme)
					continue;
				/**
				 * 计算缴费起始月份
				 */
				String beginPeriod = null;
				/**
				 * 保存人员信息去除未交状态标识
				 */
				List<InsuranceTypeDto> insuranceTypeDtoList = null;
				if(INSURANCE_TYPE.equals(cmEmployee.getInsuranceFundType())){
					beginPeriod = cmEmployee.getInsurancePeriod();
					if(StringUtils.isBlank(beginPeriod))
						beginPeriod = DateUtil.getCurYearMonthStr();
					cmEmployee.setFundSalary(null);
					cmEmployee.setFundPeriod(null);
					cmEmployee.setFundState(null);
					cmEmployee.setInsuranceState("ON");
					/**
					 * 获取比例信息
					 */
					List<CmEmployeeInsurance> cmEmployeeInsurances = insuranceMap.get(scheme.getInsuranceAccountId() + "_" + cmEmployee.getInsuranceLivingType() + "_" + beginPeriod);
					if(null == cmEmployeeInsurances){
						insuranceTypeDtoList = cmEmployeeService.queryAccountInsuranceListByAccountId(scheme.getInsuranceAccountId(), cmEmployee.getInsuranceLivingType(), beginPeriod,"Y");
						cmEmployeeInsurances = convertJsonToEmpInsu(insuranceTypeDtoList,cmEmployee.getInsuranceSalary(),beginPeriod);
						insuranceMap.put(scheme.getInsuranceAccountId() + "_" + cmEmployee.getInsuranceLivingType() + "_" + beginPeriod,cmEmployeeInsurances);
					}
					cmEmployee.setCmEmployeeInsurances(cmEmployeeInsurances);
				}else{
					beginPeriod = cmEmployee.getFundPeriod();
					if(StringUtils.isBlank(beginPeriod))
						beginPeriod = DateUtil.getCurYearMonthStr();
					cmEmployee.setInsurancePeriod(null);
					cmEmployee.setInsuranceSalary(null);
					cmEmployee.setInsuranceState(null);
					cmEmployee.setInsuranceLivingType(null);
					cmEmployee.setFundState("ON");
					/**
					 * 获取比例信息
					 */
					List<CmEmployeeInsurance> cmEmployeeInsurances = insuranceMap.get(scheme.getFundAccountId() + "_" + beginPeriod);
					if(null == cmEmployeeInsurances){
						insuranceTypeDtoList = cmEmployeeService.queryAccountInsuranceListByAccountId(scheme.getFundAccountId(), null, beginPeriod,"Y");
						cmEmployeeInsurances = convertJsonToEmpInsu(insuranceTypeDtoList,cmEmployee.getFundSalary(),beginPeriod);
						insuranceMap.put(scheme.getFundAccountId() + "_" + beginPeriod,cmEmployeeInsurances);
					}
					cmEmployee.setCmEmployeeInsurances(cmEmployeeInsurances);
				}
				if(null != cmEmployee.getEmpId()){
					//保存人员信息
					cmEmployeeService.saveEmpInfo(cti,cmEmployee,scheme,cmEmployee.getInsuranceFundType(),result);
				}else if(!"THREEPART".equals(cmCorp.getEmployeeSource())){
					cmEmployeeService.saveEmpInfo(cti,cmEmployee,scheme,cmEmployee.getInsuranceFundType(),result);
				}

				/**
				 * 修改用户同步状态
				 */
				if(result.isSuccess()) {
					CmEmployeeOrg employeeOrg = new CmEmployeeOrg();
					employeeOrg.setCpId(cti.getOrgId());
					employeeOrg.setId(cmEmployee.getId());
					employeeOrg.setChangeMark("N");
					employeeOrg.setSyncMark("Y");
					employeeOrg.setEmpId(cmEmployee.getEmpId());
					cmEmployeeOrgDao.update(cti, employeeOrg);
				}
			}
		}

		/**
		 * 修改用户同步状态
		 */
		if(result.isSuccess()){
			BsSysStateReport report = new BsSysStateReport();
			report.setOwnerType("CUSTOMER");
			report.setOwner(String.valueOf(cti.getUserId()));
			report.setAttributeName("SYNC_ORG_DATA_MARK");
			report.setCpId(cti.getOrgId());
			Map<String,Object> qReport = bsSysStateReportService.findBsSysStateReport(report);
			if(null != qReport){
				report.setId(Integer.parseInt(String.valueOf(qReport.get("id"))));
				report.setAttributeValue("_COMPLAY");
				bsSysStateReportService.insertVersionMessage(cti,report);
			}

		}
	}


	/**
	 * 将险种json转化为计算list
	 * @return
	 */
	public List<CmEmployeeInsurance> convertJsonToEmpInsu(List<InsuranceTypeDto> insurances, BigDecimal base,String beginPeriod){
		List<CmEmployeeInsurance> list = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(insurances)){
			for(InsuranceTypeDto insu:insurances){
				CmEmployeeInsurance employeeInsurance = new CmEmployeeInsurance();
				employeeInsurance.setInsuranceId(insu.getInsuranceTypeId());
				employeeInsurance.setCorpPaybase(base);
				employeeInsurance.setEmpPaybase(base);
				employeeInsurance.setRatioId(insu.getRatioId());
				employeeInsurance.setEmpRatio(insu.getPersonalRatio());
				employeeInsurance.setCorpRatio(insu.getCompanyRatio());
				employeeInsurance.setBeginPeriod(beginPeriod);
				list.add(employeeInsurance);
			}
		}
		return list;
	}

	/**
	 * 获得指定日期的后n月
	 * @param specifiedDay
	 * @param n
	 * @return
	 */
	private static String getSpecifiedMonth(String specifiedDay, int n){
		String dayAfter;
		try {
			Calendar c = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-MM").parse(specifiedDay);
			c.setTime(date);
			int day=c.get(Calendar.MONTH);
			c.set(Calendar.MONTH, day + n);

			dayAfter = new SimpleDateFormat("yyyy-MM").format(c.getTime());
		} catch (ParseException e) {
			return specifiedDay;
		}
		return dayAfter;
	}

	public int updateOrgData(ContextInfo cti,CmEmployeeOrg vo){
		return cmEmployeeOrgDao.updateOrgData(cti,vo);
	}

	/**
	 * 修改当前同步数据状态
	 * @param cti
	 * @param vo
	 * @return
	 */
	public int updateOrgDataMark(ContextInfo cti,CmEmployeeOrg vo){
		return cmEmployeeOrgDao.updateOrgDataMark(cti,vo);
	}
}
