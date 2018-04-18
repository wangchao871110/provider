package com.xfs.bill.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisResult;
import com.xfs.analysis.social.CorpBillSpServiceImpl;
import com.xfs.analysis.utils.DateFormatUtil;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.bill.dao.SpsBillDao;
import com.xfs.bill.model.BdBillrule;
import com.xfs.bill.model.SpsBill;
import com.xfs.bill.model.SpsBillDetail;
import com.xfs.bill.model.SpsBillEmp;
import com.xfs.bill.model.SpsBillitem;
import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.business.dao.BdInsuranceareaDao;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.excel.ExcelCore;
import com.xfs.common.excel.ExcelModel;
import com.xfs.common.excel.ExcelUtil;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.Arith;
import com.xfs.common.util.BusinessCode;
import com.xfs.common.util.Config;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.NumberUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.common.util.TransactionUtil;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.serviceBill.dto.ServiceBillCountVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsEmpDataVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsEmpVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsErrorDataVo;
import com.xfs.serviceBill.dto.ServiceBillDetailsListVo;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sp.service.SpsSchemeEmpService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.user.service.SysFunctionDataService;

/**
 * 企业对账单
 *
 * @author : konglc@xinfushe.com
 * @date : 2016-11-10 14:58
 * @version : V1.0
 */
@Service
public class CorpBillServiceImpl implements CorpBillService {

	private static final Logger log = Logger.getLogger(CorpBillServiceImpl.class);

    @Autowired
    private SpsBillDao spsBillDao;

    @Autowired
	private BdInsuranceareaDao bdInsuranceareaDao;

    @Autowired
    private SpServiceService spServiceService;
    
    @Autowired
    private SpsSchemeService spsSchemeService;
    
    @Autowired
    private SpsTaskService spsTaskService;

	@Autowired
	private SpsBillitemService spsBillitemService;

	@Autowired
	SysUploadfileService sysUploadfileService;

	@Autowired
	SpsFeeEmponceService spsFeeEmponceService;

	@Autowired
	SpsSchemeEmpService spsSchemeEmpService;

	@Autowired
	CmCorpService cmCorpService;

	@Autowired
	CmEmployeeService cmEmployeeService;

	@Autowired
	BdBillruleService bdBillruleService;

	@Autowired
	BsArearatioService bsArearatioService;

	@Autowired
	private CorpBillSpServiceImpl corpBillSpServiceImpl;

	@Autowired
	BdInsuranceService bdInsuranceService;

	@Autowired
	SpsBillService spsBillService;

	@Autowired
	SpsBillEmpService spsBillEmpService;

	@Autowired
	SpsBillDetailService spsBillDetailService;

	@Autowired
	BsAreaService bsAreaService;
	
	@Autowired
	SysFunctionDataService sysFunctionDataService;


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
            spsBill.setDr(0);
            List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
            if(checkAuthority(cti, spsBills)){
	            for (int i = 0; i < spsBills.size(); i++) {
	                if("PREPAY".equals(spsBills.get(i).getBillType())){
	                    serviceBillVo.setPrepay(null == spsBills.get(i).getBillamount() ? new BigDecimal(0.00) : spsBills.get(i).getBillamount());
	                    SpsBillEmp spsBillEmp = new SpsBillEmp();
	                    spsBillEmp.setBillId(spsBills.get(i).getBillId());
	                    serviceBillVo.setEmpNumber(spsBillEmpService.findAll(spsBillEmp).size());
	                }
	                if("PAID".equals(spsBills.get(i).getBillType())){
	                    serviceBillVo.setPaid(null == spsBills.get(i).getBillamount() ? new BigDecimal(0.00) : spsBills.get(i).getBillamount());
	                }
	            }
	            if(spsBills.size() == 0){
	            	serviceBillVo.setEmpNumber(spsSchemeEmpService.findEmpByScheme(cti,serviceBillVo.getCpId(),serviceBillVo.getSpId()).size());
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
            serviceBillVo.setPeriod(spsBill.getPeriod());
            serviceBillVo.setAreaName(serviceBillVo.getAreaName().replaceAll(",", "、"));
        }
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 统计服务商对账数据
     *  @param cti
     *  @param vo
     *  @return
     *  @createDate  	: 2017年7月12日 上午11:15:50
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月12日 上午11:15:50
     *  @updateAuthor  	:
     */
    @Override
    public ServiceBillCountVo findServiceBillCount(ContextInfo cti, ServiceBillVo vo) {
    	ServiceBillCountVo countVo = new ServiceBillCountVo();
    	// 预付(系统外包总成本)
    	vo.setBillType("PREPAY");
    	Map<String,Object> prepay = spsBillDao.findBillCount(vo);
    	countVo.setSysPrepayCount(new BigDecimal(prepay.get("prepay").toString()));
        countVo.setSysCorpCount(new BigDecimal(prepay.get("corp").toString()));
        countVo.setSysEmpCount(new BigDecimal(prepay.get("emp").toString()));
        countVo.setSysServiceCount(new BigDecimal(prepay.get("service").toString()));
        
        // 实缴(服务商账单总额)
        vo.setBillType("PAID");
        Map<String,Object> paid = spsBillDao.findBillCount(vo);
        countVo.setServicePrepayCount(new BigDecimal(paid.get("prepay").toString()));
        countVo.setServiceCorpCount(new BigDecimal(paid.get("corp").toString()));
        countVo.setServiceEmpCount(new BigDecimal(paid.get("emp").toString()));
        countVo.setServiceServiceCount(new BigDecimal(paid.get("service").toString()));
    	
        // 总差额合计
        countVo.setDifferenceCount(Arith.sub(countVo.getSysPrepayCount(),countVo.getServicePrepayCount()));
        countVo.setDifferenceCorpCount(Arith.sub(countVo.getSysCorpCount(),countVo.getServiceCorpCount()));
        countVo.setDifferenceEmpCount(Arith.sub(countVo.getSysEmpCount(),countVo.getServiceEmpCount()));
        countVo.setDifferenceServiceCount(Arith.sub(countVo.getSysServiceCount(),countVo.getServiceServiceCount()));
        return countVo;
    }

	public boolean checkAuthority(ContextInfo cti, List<SpsBill> spsBills) {
		boolean flag = true;   
		if(!"NO".equals(cti.getAuthority()) && !"ALL".equals(cti.getAuthority()) && spsBills.size() > 0){
			String spsIds = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), "CUSTOMER", "CM_CORP_SP");
			List<String> authority = Arrays.asList(spsIds.split(","));
	    	String spsId = spsBills.get(0).getSpId().toString();
    		if(!authority.contains(spsId)){
    			flag = false;
    		}
		}
		return flag;
	}
	
	/**
	 * 根据企业ID、服务商ID和月份获取参保城市
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月14日 下午2:08:44
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月14日 下午2:08:44
	 *  @updateAuthor  	:
	 */
	@Override
	public Map<String, Object> findServiceBillDetailsArea(ContextInfo cti, ServiceBillVo vo) {
		Map<String, Object> serviceBillDetails = new HashMap<>();
		// 获取方案ID
		SpsBill obj = new SpsBill();
		obj.setCpId(vo.getCpId());
		obj.setSpId(vo.getSpId());
		obj.setPeriod(vo.getPeriod());
		obj.setDr(0);
		obj.setBillType("PREPAY");
		List<SpsBill> list = spsBillDao.freeFind(obj);
		if(null != list && list.size() > 0){
			// 方案ID转城市ID
			if(StringUtils.isNotBlank(list.get(0).getSchemeIds())){
				String[] schemeIds = list.get(0).getSchemeIds().split(",");
				// 根据权限、企业ID、服务商ID和方案ID获取参保城市
				List<Map<String,Object>> areaList = spsSchemeService.findServiceBillDetailsArea(cti.getAuthority(),vo.getCpId(),vo.getSpId(),schemeIds);
				serviceBillDetails.put("areaList", areaList);
			}
		}
		serviceBillDetails.put("cpId", vo.getCpId());
		serviceBillDetails.put("spId", vo.getSpId());
		SpService service = spServiceService.findByPK(vo.getSpId());
		serviceBillDetails.put("spsName", service.getSpName());
		serviceBillDetails.put("period", vo.getPeriod());
		return serviceBillDetails;
	}
	
	/**
	 * 获取账单基本信息
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月17日 下午4:30:08
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月17日 下午4:30:08
	 *  @updateAuthor  	:
	 */
	@Override
	public Map<String, Object> findServiceBillDetailsBasic(ContextInfo cti, ServiceBillVo vo) {
		Map<String, Object> serviceBillDetails = new HashMap<>();
		SpService service = spServiceService.findByPK(vo.getSpId());
		serviceBillDetails.put("contactor", service.getContactor());// 联系人
		serviceBillDetails.put("mobile", service.getMobile());// 联系方式
		serviceBillDetails.put("empNumber", spsBillDao.findServiceBillDetailsListCount(vo)+spsBillDao.findServiceBillDetailsErrorList(vo).size());// 服务商下员工数
		SpsTask spsTask = new SpsTask();
		spsTask.setCpId(vo.getCpId());
		spsTask.setSpId(vo.getSpId());
		spsTask.setBeginDate(vo.getPeriod());
		if(null != vo.getAreaId() && !"".equals(vo.getAreaId())){
			spsTask.setAreaId(vo.getAreaId());
		}
		spsTask.setAuthority(cti.getAuthority());
		spsTask.setDr(0);
		Integer businessNumber = spsTaskService.findBusinessNumber(spsTask);
		serviceBillDetails.put("businessNumber", businessNumber);// 当月业务数量 只查询当月增减补任务单
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
        try {
			calendar.setTime(sdf.parse(vo.getPeriod()));
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        spsTask.setBeginDate(sdf.format(calendar.getTime()));
		Integer upBusinessNumber = spsTaskService.findBusinessNumber(spsTask);
		if(businessNumber > upBusinessNumber){
			serviceBillDetails.put("addBusinessNumber", businessNumber-upBusinessNumber);// 当月新增业务数量  本月增减补任务单减去上月增减补任务单
		}else{
			serviceBillDetails.put("addBusinessNumber", 0);// 当月新增业务数量  本月增减补任务单减去上月增减补任务单
		}
		return serviceBillDetails;
	}
	
	/**
	 * 获取服务商对账详情统计
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月12日 上午11:15:50
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月12日 上午11:15:50
	 *  @updateAuthor  	:
	 */
	@Override
	public ServiceBillCountVo findServiceBillDetailsCount(ContextInfo cti, ServiceBillVo vo) {
		ServiceBillCountVo countVo = new ServiceBillCountVo();
		// 预付(系统外包总成本)
    	vo.setBillType("PREPAY");
    	Map<String,Object> prepay = spsBillDao.findBillDetailsCount(vo);
    	countVo.setSysPrepayCount(new BigDecimal(prepay.get("prepay").toString()));
        countVo.setSysCorpCount(new BigDecimal(prepay.get("corp").toString()));
        countVo.setSysEmpCount(new BigDecimal(prepay.get("emp").toString()));
        countVo.setSysServiceCount(new BigDecimal(prepay.get("service").toString()));
        
        // 实缴(服务商账单总额)
        vo.setBillType("PAID");
        Map<String,Object> paid = spsBillDao.findBillDetailsCount(vo);
        countVo.setServicePrepayCount(new BigDecimal(paid.get("prepay").toString()));
        countVo.setServiceCorpCount(new BigDecimal(paid.get("corp").toString()));
        countVo.setServiceEmpCount(new BigDecimal(paid.get("emp").toString()));
        countVo.setServiceServiceCount(new BigDecimal(paid.get("service").toString()));
		
		// 总差额合计
		countVo.setDifferenceCount(Arith.sub(countVo.getSysPrepayCount(),countVo.getServicePrepayCount()));
		countVo.setDifferenceCorpCount(Arith.sub(countVo.getSysCorpCount(),countVo.getServiceCorpCount()));
		countVo.setDifferenceEmpCount(Arith.sub(countVo.getSysEmpCount(),countVo.getServiceEmpCount()));
		countVo.setDifferenceServiceCount(Arith.sub(countVo.getSysServiceCount(),countVo.getServiceServiceCount()));
		return countVo;
	}

	/**
	 * 服务商对账详情异常数据列表
	 *  @param cti
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月18日 下午2:51:24
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月18日 下午2:51:24
	 *  @updateAuthor  	:
	 */
	@Override
	public List<ServiceBillDetailsListVo> findServiceBillDetailsErrorList(ContextInfo cti, PageInfo pageInfo,ServiceBillVo vo) {
		SpsBill spsBill = new SpsBill();
		spsBill.setSpId(vo.getSpId());
		spsBill.setCpId(vo.getCpId());
		spsBill.setPeriodEq(vo.getPeriod());
		spsBill.setDr(0);
		// 根据企业ID、服务商ID和月份获取账单
		List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
		List<ServiceBillDetailsListVo> datas = new ArrayList<>();
		if(checkAuthority(cti, spsBills)){
			// 服务商对账详情异常数据列表
	        List<Map<String,Object>> maps = spsBillDao.findServiceBillDetailsErrorList(vo);
	        ServiceBillDetailsListVo serviceBillDetailsListVo = null;
	        ServiceBillDetailsErrorDataVo errorData = null;
	        for(Map<String,Object> map : maps){
	        	serviceBillDetailsListVo = new ServiceBillDetailsListVo();
	        	serviceBillDetailsListVo.setEmpId(null != map.get("empId") ? Integer.valueOf(map.get("empId").toString()) : null);
	        	serviceBillDetailsListVo.setEmpName(null != map.get("name") ? map.get("name").toString() : null);
	        	serviceBillDetailsListVo.setCode(null != map.get("code") ? map.get("code").toString() : null);
	        	serviceBillDetailsListVo.setCorpName((null != map.get("branch") && !"".equals(map.get("branch").toString())) ? map.get("branch").toString() : "--");
	        	serviceBillDetailsListVo.setAreaName(null != map.get("areaName") ? map.get("areaName").toString() : "--");
	        	serviceBillDetailsListVo.setPeriod(null != map.get("month") ? map.get("month").toString() : "--");
	        	serviceBillDetailsListVo.setInsuranceType(null != map.get("insuranceType") ? map.get("insuranceType").toString() : "--");
	        	serviceBillDetailsListVo.setBusinessType(null != map.get("payType") ? map.get("payType").toString() : "--");
	        	serviceBillDetailsListVo.setBusinessStatus(null != map.get("businessStatus") ? map.get("businessStatus").toString() : "--");
	        	BigDecimal fundFeePaid = new BigDecimal(map.get("fundFeePaid").toString()) ;
	        	BigDecimal insFeePaid = new BigDecimal(map.get("insFeePaid").toString()) ;
	        	serviceBillDetailsListVo.setInsDifference(Arith.sub(BigDecimal.ZERO, insFeePaid));
	        	serviceBillDetailsListVo.setFundDifference(Arith.sub(BigDecimal.ZERO, fundFeePaid));
	        	serviceBillDetailsListVo.setOfficeDifference(Arith.sub(BigDecimal.ZERO,Arith.add(fundFeePaid, insFeePaid)));
	        	errorData = new ServiceBillDetailsErrorDataVo();
	        	errorData.setFundCost(BigDecimal.ZERO);
	        	errorData.setFundDifference(Arith.sub(BigDecimal.ZERO, fundFeePaid));
	        	errorData.setFundServiceBill(fundFeePaid);
	        	errorData.setInsCost(BigDecimal.ZERO);
	        	errorData.setInsDifference(Arith.sub(BigDecimal.ZERO, insFeePaid));
	        	errorData.setInsServiceBill(insFeePaid);
	        	errorData.setOfficeCost(BigDecimal.ZERO);
	        	errorData.setOfficeDifference(Arith.sub(BigDecimal.ZERO,Arith.add(fundFeePaid, insFeePaid)));
	        	errorData.setOfficeServiceBill(Arith.add(fundFeePaid, insFeePaid));
	        	serviceBillDetailsListVo.setErrorData(errorData);
	        	datas.add(serviceBillDetailsListVo);
	        }
		}
        return datas;
	}

	/**
	 * 服务商对账详情数据列表
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月18日 下午2:51:33
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月18日 下午2:51:33
	 *  @updateAuthor  	:
	 */
	@Override
	public PageModel findServiceBillDetailsList(ContextInfo cti, PageInfo pi, ServiceBillVo vo) {
		SpsBill spsBill = new SpsBill();
		spsBill.setSpId(vo.getSpId());
		spsBill.setCpId(vo.getCpId());
		spsBill.setPeriodEq(vo.getPeriod());
		spsBill.setDr(0);
		// 根据企业ID、服务商ID和月份获取账单
		List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
		PageModel pm = new PageModel(pi);
		if(checkAuthority(cti, spsBills)){
	        int pageIndex = pi.getOffset();
	        int pageSize = pi.getPagesize();
	        Integer total = spsBillDao.findServiceBillDetailsListCount(vo);
	        pm.setTotal(total);
	        // 服务商对账详情数据列表
	        List<Map<String,Object>> maps = spsBillDao.findServiceBillDetailsList(vo, pageIndex, pageSize);
	        List<ServiceBillDetailsListVo> datas = new ArrayList<>();
	        ServiceBillDetailsListVo serviceBillDetailsListVo = null;
	        ServiceBillDetailsErrorDataVo errorData = null;
	        for(Map<String,Object> map : maps){
	        	serviceBillDetailsListVo = new ServiceBillDetailsListVo();
	        	serviceBillDetailsListVo.setEmpId(null != map.get("empId") ? Integer.valueOf(map.get("empId").toString()) : null);
	        	serviceBillDetailsListVo.setEmpName(null != map.get("name") ? map.get("name").toString() : null);
	        	serviceBillDetailsListVo.setCode(null != map.get("code") ? map.get("code").toString() : null);
	        	serviceBillDetailsListVo.setCorpName((null != map.get("branch") && !"".equals(map.get("branch").toString())) ? map.get("branch").toString() : "--");
	        	serviceBillDetailsListVo.setAreaName(null != map.get("areaName") ? map.get("areaName").toString() : "--");
	        	serviceBillDetailsListVo.setPeriod(null != map.get("month") ? map.get("month").toString() : "--");
	        	serviceBillDetailsListVo.setInsuranceType(null != map.get("insuranceType") ? map.get("insuranceType").toString() : "--");
	        	serviceBillDetailsListVo.setBusinessType(null != map.get("payType") ? map.get("payType").toString() : "--");
	        	serviceBillDetailsListVo.setBusinessStatus(null != map.get("businessStatus") ? map.get("businessStatus").toString() : "--");
	        	BigDecimal fundFeePrepay = new BigDecimal(map.get("fundFeePrepay").toString()) ;
	        	BigDecimal insFeePrepay = new BigDecimal(map.get("insFeePrepay").toString()) ;
	        	BigDecimal fundFeePaid = new BigDecimal(map.get("fundFeePaid").toString()) ;
	        	BigDecimal insFeePaid = new BigDecimal(map.get("insFeePaid").toString()) ;
	        	serviceBillDetailsListVo.setInsDifference(Arith.sub(insFeePrepay, insFeePaid));
	        	serviceBillDetailsListVo.setFundDifference(Arith.sub(fundFeePrepay, fundFeePaid));
	        	serviceBillDetailsListVo.setOfficeDifference(Arith.sub(Arith.add(fundFeePrepay, insFeePrepay),Arith.add(fundFeePaid, insFeePaid)));
	        	BigDecimal serviceFee = new BigDecimal(map.get("serviceFee").toString()) ;
	        	serviceBillDetailsListVo.setServiceFeeDifference(serviceFee);
	        	errorData = new ServiceBillDetailsErrorDataVo();
	        	errorData.setFundCost(fundFeePrepay);
	        	errorData.setFundDifference(Arith.sub(fundFeePrepay, fundFeePaid));
	        	errorData.setFundServiceBill(fundFeePaid);
	        	errorData.setInsCost(insFeePrepay);
	        	errorData.setInsDifference(Arith.sub(insFeePrepay, insFeePaid));
	        	errorData.setInsServiceBill(insFeePaid);
	        	errorData.setOfficeCost(Arith.add(fundFeePrepay, insFeePrepay));
	        	errorData.setOfficeDifference(Arith.sub(Arith.add(fundFeePrepay, insFeePrepay),Arith.add(fundFeePaid, insFeePaid)));
	        	errorData.setOfficeServiceBill(Arith.add(fundFeePaid, insFeePaid));
	        	serviceBillDetailsListVo.setErrorData(errorData);
	        	datas.add(serviceBillDetailsListVo);
	        }
	        pm.setDatas(datas);
		}
        return pm;
	}

	/**
	 * 创建应收账单前检查
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
	public Result checkCreateBill(ContextInfo cti,SpsBill bill, Integer user_id,String filePath,String fileName,Map<String,Object> ext) {
		Result result = Result.createResult().setSuccess(false);
		// 检查是否已经到了账单日
		Calendar calendar = Calendar.getInstance();
		int curr_day = calendar.get(Calendar.DAY_OF_MONTH);
		String month = DateUtil.getDateStr(calendar.getTime(),"yyyy-MM");
		if(!StringUtils.isBlank(bill.getPeriod()) && month.compareTo(bill.getPeriod()) <= 0){
			if (null != bill.getBillDay() && curr_day < bill.getBillDay() && "PREPAY".equals(bill.getBillType())) {
				result.setError("已经超过账单日");
				return result;
			}
		}

		/**
		 * 检查 当前用户是否有权限创建系统账单，检查账单状态
		 */
		SpsScheme qry = new SpsScheme();
		qry.setSpId(bill.getSpId());
		qry.setCpId(bill.getCpId());
		qry.setDr(0);
		List<SpsScheme> schemes = spsSchemeService.findAll(qry);
		if(!"NO".equals(cti.getAuthority()) && !"ALL".equals(cti.getAuthority()) && schemes.size() > 0){
			List<String> authority = Arrays.asList(cti.getAuthority().split(","));
			for(SpsScheme spsScheme : schemes){
				if(!authority.contains(String.valueOf(spsScheme.getSchemeId()))){
					result.setError("没有权限生成该服务商账单");
					return result;
				}
			}
		}
		return createCmBill(cti,bill, user_id,filePath,fileName,ext);
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
	public Result createCmBill(ContextInfo cti,SpsBill bill, Integer user_id,String filePath,String fileName,Map<String,Object> ext) {
		String key = bill.getSpId() + "_" + bill.getCpId() + "_" + bill.getPeriod() + "_" + bill.getBillType();
		if (RedisUtil.getLock(key, 60000L) != 1) {
			Result result = Result.createResult().setSuccess(false);
			result.setError("正在生成请稍后");
			return result;
		}
		Result result = Result.createResult().setSuccess(true);
		try {
			result = createBill(cti,bill,filePath,fileName,ext);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setError(e.getMessage());
			return result;
		} finally {
			RedisUtil.delLock(key);
		}
		return result;
	}

	/**
	 * 创建应收、实缴账单
	 * 实缴账单对应的应收账单id
	 * @return : SpsBill
	 * @createDate : 2016-11-10 11:32
	 * @author : konglc@xinfushe.com
	 * @version : v1.0
	 * @updateDate : 2016-11-10 11:32
	 * @updateAuthor :
	 */
	@Override
	public Result createBill(ContextInfo cti,SpsBill bill,String filePath,String fileName,Map<String,Object> ext) {
		if("PAID".equals(bill.getBillType())){
			return createPaidBill(cti,bill,filePath,fileName,ext);
		}else{
			return createPrePayBill(cti, bill);
		}
	}

	/**
	 * 创建应收账单
	 * @return
	 */
	private Result createPrePayBill(ContextInfo cti, SpsBill parmbill) {
		Result result = Result.createResult().setSuccess(true);
		/**
		 * 作废原有账单
		 */
		String billType = "PREPAY";
		SpsBill billqry = new SpsBill();
		billqry.setCpId(parmbill.getCpId());
		billqry.setSpId(parmbill.getSpId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		String period = StringUtils.isBlank(parmbill.getPeriod()) ? format.format(currTime) : parmbill.getPeriod();
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

		// 创建账单实例
		SpsBill bill = new SpsBill();
		bill.setBillState("GENERATED");
		// 判断账单已生成已对账 作废原有账单
		if (oldbills != null) {
			for (SpsBill oldbill : oldbills) {
				oldbill.setDr(1);
				bill.setBillState(oldbill.getBillState());
				this.save(cti, oldbill);
			}
		}

		/**
		 * 获取所有方案
		 */
		SpsScheme qry = new SpsScheme();
		qry.setSpId(parmbill.getSpId());
		qry.setCpId(parmbill.getCpId());
		qry.setDr(0);


		bill.setCpId(parmbill.getCpId());
		bill.setSpId(parmbill.getSpId());
		bill.setPeriod(period);

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
		// 账单员工
		List<SpsBillEmp> billEmps = new ArrayList<>();
		Map<Integer, SpsBillEmp> billEmpsMap = new HashMap<Integer, SpsBillEmp>();
		// 补缴记录 避免社保公积金分开形成两条记录，及差额账关联错误
		Map<String, SpsBillEmp> patchEmpsMap = new HashMap<String, SpsBillEmp>();
		// 服务方案Map
		Map<Integer, SpsScheme> schemeMap = new HashMap<>();
		// 员工服务方案
		Map<Integer, SpsScheme> empscheme = new HashMap<>();
		// 方案IDS
		List<Integer> schemeIds = new ArrayList<>();
		/**
		 * 循环所有方案，计算方案下的员工成本
		 */
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

			List<CmEmployee> emps = new ArrayList<>();//正常月缴纳人员列表
			Map<Integer, CmEmployee> empMap = new HashMap<>();
			/**
			 * 正常月缴费用
			 */
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
						if (null != insuranceEmp.getCmEmployeeInsurances() && insuranceEmp.getCmEmployeeInsurances().size() > 0) {
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
			if (null != emps) {
				adjustBillEmpDetail(emps,scheme,insureMonth,fundMonth,billEmpsMap,billEmps,officeFee,serviceFee,"MONTH");
			}
			/**
			 * 获取人员补缴信息
			 */
			List<CmEmployee> emponces = cmEmployeeService.findEmpListWithDetailByEmpids(empIds, insureMonth, null,"PATCH");
			if (null != emponces) {
				adjustBillEmpDetail(emponces,scheme,insureMonth,fundMonth,billEmpsMap,billEmps,officeFee,serviceFee,"PATCH");
			}
			schemeIds.add(scheme.getSchemeId());
		}
		bill.setSchemeIds(StringUtils.convertInInt(schemeIds));

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

		bill.setBillOfficeFee(officeFee);
		bill.setBillServiceFee(serviceFee);
		bill.setPerpayOfficeFee(officeFee);
		bill.setPerpayServiceFee(serviceFee);
		bill.setBillamount(Arith.add(bill.getPerpayOfficeFee(), bill.getPerpayServiceFee()));

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
			 String fileid = generateExcel(cti,bill, String.valueOf(cmCorpService.findByPK(parmbill.getCpId()).getCorpName()), bill.getPeriod(), bill.getDeputeType(), bill.getBillType());
			 bill.setFileId(fileid);
		} catch (Exception e) {

		}
		spsBillDao.update(cti, bill);
		result.setDataValue("bill",bill);
		return result;
	}

	/**
	 * 根据费用段进行算费
	 * @param emps
	 * @param scheme
	 * @param insureMonth
	 * @param fundMonth
	 * @param billEmpsMap
	 * @param billEmps
	 * @param officeFee
	 * @param serviceFee
	 */
	private void adjustBillEmpDetail(List<CmEmployee> emps,SpsScheme scheme,String insureMonth,String fundMonth,Map<Integer, SpsBillEmp> billEmpsMap,List<SpsBillEmp> billEmps,BigDecimal officeFee,BigDecimal serviceFee,String payType){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		for (CmEmployee emp : emps) {
			SpsBillEmp billemp = new SpsBillEmp();
			billemp.setName(emp.getName());
			billemp.setEmpId(emp.getEmpId());
			billemp.setIdentityCode(emp.getIdentityCode());
			billemp.setIdentityType(emp.getIdentityType());

			billemp.setFundaccountId(scheme.getFundAccountId());
			billemp.setFundArea(scheme.getAreaId());
			billemp.setFundBase(emp.getFundSalary());

			billemp.setInsuranceaccountId(scheme.getInsuranceAccountId());
			billemp.setInsuranceArea(scheme.getAreaId());
			billemp.setInsuranceBase(emp.getInsuranceSalary());
			billemp.setInsuredMonth(emp.getPeriod());
			billemp.setPayType(payType);
			billemp.setSpsBillDetails(new ArrayList<SpsBillDetail>());

			billemp.setServiceFee(Arith.ignoreNull(scheme.getPrice()));
			billemp.setFundOfficialFee(BigDecimal.ZERO);
			billemp.setInsuranceOfficialFee(BigDecimal.ZERO);
			if (StringUtils.isNotBlank(insureMonth) && insureMonth.equals(emp.getInsurancePeriod()))
				billemp.setInsuranceIsNew("Y");
			if (StringUtils.isNotBlank(fundMonth) && fundMonth.equals(emp.getFundPeriod()))
				billemp.setFundIsNew("Y");
			if (null != emp.getCmEmployeeInsurances()) {
				for (CmEmployeeInsurance insurance : emp.getCmEmployeeInsurances()) {
					BdInsurance bdins = bdInsuranceService.findByPK(insurance.getInsuranceId());
					if (null == insurance.getRatioId())
						throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例", emp.getName(), bdins.getName()));
					BsArearatio ratio = bsArearatioService.findByPK(insurance.getRatioId());
					if (null == ratio)
						throw new BusinessException(String.format("员工%s的%s比例不存在，请在员工参保情况中调整比例", emp.getName(), bdins.getName()));
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
							if (((format.parse(month).getMonth() + 1) == ratio.getPayPeriod()) || month.equals(insurance.getMinPeriod())) {
								// 年缴不足一年按年等同于月缴 不需要处理
								// 年缴不足一年按月
								if (ratio.billingCycle_MONTH_ANNUALY.equals(ratio.getBillingCycle())) {
									if (StringUtils.isNotBlank(insurance.getBeginPeriod())) {
										// 缴费月份-入职月份 如果 小于等于0 则 +12
										int months = ratio.getPayPeriod() - format.parse(insurance.getBeginPeriod()).getMonth() - 1;
										if (months <= 0)
											months += 12;
										detail.setCorpPayment(Arith.dealRound(Arith.div(insurance.getCorpPayment(), new BigDecimal(12.0 / months)), ratio.getCorpCalcMethod(), ratio.getCorpPrecision()));
										detail.setCorpAddition(Arith.dealRound(Arith.div(insurance.getCorpAddition(), new BigDecimal(12.0 / months)), ratio.getCorpCalcMethod(), ratio.getCorpPrecision()));
										detail.setEmpPayment(Arith.dealRound(Arith.div(insurance.getEmpPayment(), new BigDecimal(12.0 / months)), ratio.getPsnCalcMethod(), ratio.getPsnPrecision()));
										detail.setPsnAddition(Arith.dealRound(Arith.div(insurance.getPsnAddition(), new BigDecimal(12.0 / months)), ratio.getPsnCalcMethod(), ratio.getPsnPrecision()));
									}
								}
							} else {
								detail.setCorpPayment(BigDecimal.ZERO);
								detail.setEmpPayment(BigDecimal.ZERO);
							}
						} catch (ParseException e) {
							log.error("日期计算出错", e);
							throw new RuntimeException(e);
						}
					}
					if (null != detail) {
						if (Arith.ignoreNull(detail.getCorpPayment()).doubleValue() > 0 || Arith.ignoreNull(detail.getEmpPayment()).doubleValue() > 0) {
							billemp.getSpsBillDetails().add(detail);
							if ("COMMON".equals(scheme.getFundType()) && "FUND".equals(bdins.getInsuranceFundType())) {
								billemp.setFundOfficialFee(Arith.add(billemp.getFundOfficialFee(), detail.getCorpPayment(), detail.getEmpPayment()));
							} else if ("COMMON".equals(scheme.getInsuranceType())) {
								billemp.setInsuranceOfficialFee(Arith.add(billemp.getInsuranceOfficialFee(), detail.getCorpPayment(), detail.getEmpPayment()));
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
				officeFee = Arith.add(officeFee, billemp.getFundOfficialFee(), billemp.getInsuranceOfficialFee());
			}
		}
	}


	/**
	 * 生成excel账单文件
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
	 * 创建实缴账单
	 * @return
	 */
	private Result createPaidBill(ContextInfo cti, SpsBill prambill,String filePath,String fileName,Map<String,Object> ext) {
		Result result = Result.createResult().setSuccess(true);
		AnalysisResult analysisResult = corpBillSpServiceImpl.analysis(cti,filePath,fileName,ext);
		if(null == analysisResult.getDataList() || analysisResult.getDataList().isEmpty()){
			result.setSuccess(false);
			result.setError("服务商账单无数据");
			return result;
		}
		//获取所有保险列表
		BdInsurance insurance = new BdInsurance();
		List<BdInsurance> insurances = bdInsuranceService.findAll(insurance);
		Map<String,BdInsurance> insuranceMap = new HashMap<>();
		for(BdInsurance bdInsurance : insurances){
			String key = bdInsurance.getCode();
			insuranceMap.put(key,bdInsurance);
		}
		//获取当前服务机构下所有方案
		String insureMonth = prambill.getPeriod();
		SpsBill qry = new SpsBill();
		qry.setSpId(prambill.getSpId());
		qry.setCpId(prambill.getCpId());
		qry.setDr(0);
		qry.setPeriod(insureMonth);//对账单月份
		qry.setBillType("PREPAY");
		//获取系统账单
		List<SpsBill> bills = spsBillService.findAll(qry);
		if(null == bills || bills.isEmpty())
			throw new BusinessException("未生成系统账单");

		//作废之前账单
		String billType = "PAID";
		SpsBill billqry = new SpsBill();
		billqry.setCpId(cti.getOrgId());
		billqry.setSpId(prambill.getSpId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date currTime = new Date();
		if (StringUtils.isBlank(insureMonth))
			insureMonth = format.format(currTime);
		// 上托收取下一期间保险记录
		{
			Calendar nextPeriod = Calendar.getInstance();
			try {
				nextPeriod.setTime(format.parse(insureMonth));
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			nextPeriod.add(Calendar.MONTH, 1);
		}
		billqry.setPeriod(insureMonth);
		billqry.setBillType(billType);
		billqry.setDr(0);
		List<SpsBill> oldbills = spsBillService.findAll(billqry);
		if (oldbills != null) {
			for (SpsBill oldbill : oldbills) {
				oldbill.setDr(1);
				spsBillService.save(cti, oldbill);
			}
		}

		//获取系统账单下所有人员费用
		SpsBillEmp billEmp = new SpsBillEmp();
		billEmp.setBillId(bills.get(0).getBillId());
		List<SpsBillEmp> billEmps = spsBillEmpService.findAll(billEmp);
		Map<String,SpsBillEmp> billEmpMap = new HashMap<>();
		for(SpsBillEmp spsBillEmp : billEmps){
			String key = spsBillEmp.getName() + "_" + spsBillEmp.getIdentityCode() + "_" + spsBillEmp.getPayType();
			billEmpMap.put(key,spsBillEmp);
		}

		// 创建账单实例
		SpsBill bill = new SpsBill();
		bill.setCpId(cti.getOrgId());
		bill.setSpId(prambill.getSpId());
		bill.setPeriod(insureMonth);
		bill.setBillState("GENERATED");
		bill.setBillType("PAID");
		bill.setBuildDate(new Date());
		bill.setDr(0);
		bill.setSchemeIds(bills.get(0).getSchemeIds());
		// 持久化 需要先产生billid
		spsBillService.save(cti, bill);

		// 官费
		bill.setBillOfficeFee(BigDecimal.ZERO);
		bill.setActualOfficeFee(BigDecimal.ZERO);
		// 服务费
		bill.setBillServiceFee(BigDecimal.ZERO);
		bill.setActualServiceFee(BigDecimal.ZERO);


		//实缴人员列表
		List<SpsBillEmp> paidBillEmps = new ArrayList<>();
		//对比人员信息
		//记录总金额
		Map<String,Integer> areaNameMap = new HashMap<>();
		BsArea bsArea = new BsArea();
		bsArea.setAreaType("CITY");
		for (Map<String, AnalysisData> paidBill : analysisResult.getDataList()){
			SpsBillEmp spsBillEmp = new SpsBillEmp();
			spsBillEmp.setPayType("MONTH");
			spsBillEmp.setInsuredMonth(insureMonth);
			spsBillEmp.setBillId(bill.getBillId());
			Map<String,SpsBillDetail> detailMap = new HashMap<>();
			for(Map.Entry<String,AnalysisData> entry : paidBill.entrySet()){
				String key = entry.getKey();
				AnalysisData data = entry.getValue();
				if(key.equals("name")){
					spsBillEmp.setName(data.getAnalysisValue());
				}else if(key.equals("identityCode")){
					spsBillEmp.setIdentityCode(data.getAnalysisValue());
				}else if(key.equals("identityType")){
					spsBillEmp.setIdentityType("IDCard");
				}else if(key.equals("insuredMonth")) {
					if(StringUtils.isNotBlank(data.getAnalysisValue()) && !DateFormatUtil.FormatDate(data.getAnalysisValue()).equals(spsBillEmp.getInsuredMonth())){
						spsBillEmp.setPayType("PATCH");
						spsBillEmp.setInsuredMonth(data.getAnalysisValue());
					}
				}else if(key.equals("serviceFee")){
					BigDecimal value = StringUtils.isNotBlank(data.getAnalysisValue()) ? new BigDecimal(data.getAnalysisValue()) : BigDecimal.ZERO;
					spsBillEmp.setServiceFee(value);
					bill.setActualServiceFee(Arith.add(bill.getActualServiceFee(),value));
					bill.setBillServiceFee(Arith.add(bill.getBillServiceFee(),value));
				}else{
					//解析保险
					String[] inskey = key.split(":");
					BdInsurance bdInsurance = insuranceMap.get(inskey[0]);
					if(null != bdInsurance){
						if(null == detailMap.get(inskey[0]) && !"areaName".equals(inskey[1])){
							detailMap.put(inskey[0],new SpsBillDetail());
							detailMap.get(inskey[0]).setInsuranceType(bdInsurance.getInsuranceId());
						}
						if("areaName".equals(inskey[1])){
							if(StringUtils.isNotBlank(data.getAnalysisValue())){
								//获取城市ID
								if("FUND".equals(bdInsurance.getInsuranceFundType()) && null == spsBillEmp.getFundArea()){
									if(null != areaNameMap.get(data.getAnalysisValue())){
										spsBillEmp.setFundArea(areaNameMap.get(data.getAnalysisValue()));
									}else{
										bsArea.setName(data.getAnalysisValue());
										List<BsArea> areaList = bsAreaService.getBsAreaByName(bsArea);
										if(null != areaList && !areaList.isEmpty()){
											spsBillEmp.setFundArea(areaList.get(0).getAreaId());
											areaNameMap.put(bsArea.getName(),areaList.get(0).getAreaId());
										}
									}
								}else if("INSURANCE".equals(bdInsurance.getInsuranceFundType()) && null == spsBillEmp.getInsuranceaccountId()){
									if(null != areaNameMap.get(data.getAnalysisValue())){
										spsBillEmp.setInsuranceArea(areaNameMap.get(data.getAnalysisValue()));
									}else{
										bsArea.setName(data.getAnalysisValue());
										List<BsArea> areaList = bsAreaService.getBsAreaByName(bsArea);
										if(null != areaList && !areaList.isEmpty()){
											spsBillEmp.setInsuranceArea(areaList.get(0).getAreaId());
											areaNameMap.put(bsArea.getName(),areaList.get(0).getAreaId());
										}
									}
								}
							}
						}else{
							BigDecimal value = BigDecimal.ZERO;
							if(StringUtils.isNotBlank(data.getAnalysisValue()) && NumberUtil.isNumber(data.getAnalysisValue().replace(".",""))){
								if(data.getAnalysisValue().indexOf(".") > 0 && NumberUtil.isNumber(data.getAnalysisValue().replace(".",""))){
									value = new BigDecimal(data.getAnalysisValue());
								}else if(data.getAnalysisValue().indexOf("%") > 0 && NumberUtil.isNumber(data.getAnalysisValue().replace("%",""))){
									try{
										NumberFormat nf= NumberFormat.getPercentInstance();
										Number m= nf.parse(data.getAnalysisValue());
										value = new BigDecimal(m.toString());
									}catch (Exception e){
										e.printStackTrace();
									}
								}else {
									value = new BigDecimal(data.getAnalysisValue());
								}
							}
							if("empRatio".equals(inskey[1])){
								detailMap.get(inskey[0]).setEmpRatio(value);
							}else if("empPaybase".equals(inskey[1])){
								detailMap.get(inskey[0]).setEmpPaybase(value);
							}else if("empPayment".equals(inskey[1])){
								detailMap.get(inskey[0]).setEmpPayment(value);
								if("INSURANCE".equals(bdInsurance.getInsuranceFundType())){
									spsBillEmp.setInsuranceOfficialFee(Arith.add(spsBillEmp.getInsuranceOfficialFee(),value));
								}else{
									spsBillEmp.setFundOfficialFee(Arith.add(spsBillEmp.getFundOfficialFee(),value));
								}
								bill.setActualOfficeFee(Arith.add(bill.getActualOfficeFee(),value));
								bill.setBillOfficeFee(Arith.add(bill.getBillOfficeFee(),value));
							}else if("corpRatio".equals(inskey[1])){
								detailMap.get(inskey[0]).setCorpRatio(value);
							}else if("corpPaybase".equals(inskey[1])){
								detailMap.get(inskey[0]).setCorpPaybase(value);
							}else if("corpPayment".equals(inskey[1])){
								detailMap.get(inskey[0]).setCorpPayment(value);
								if("INSURANCE".equals(bdInsurance.getInsuranceFundType())){
									spsBillEmp.setInsuranceOfficialFee(Arith.add(spsBillEmp.getInsuranceOfficialFee(),value));
								}else{
									spsBillEmp.setFundOfficialFee(Arith.add(spsBillEmp.getFundOfficialFee(),value));
								}
								bill.setActualOfficeFee(Arith.add(bill.getActualOfficeFee(),value));
								bill.setBillOfficeFee(Arith.add(bill.getBillOfficeFee(),value));
							}
						}
					}
				}
			}
			//设置缴存详情信息
			List<SpsBillDetail> details = new ArrayList<SpsBillDetail>(detailMap.values());
			spsBillEmp.setSpsBillDetails(details);
			//对比系统账单人员
			String key = spsBillEmp.getName() + "_" + spsBillEmp.getIdentityCode() + "_" + spsBillEmp.getPayType();
			SpsBillEmp exitEmp = billEmpMap.get(key);
			if(null != exitEmp) {
				spsBillEmp.setEmpId(exitEmp.getEmpId());
				spsBillEmp.setLivingType(exitEmp.getLivingType());
				spsBillEmp.setInsuranceArea(exitEmp.getInsuranceArea());
				spsBillEmp.setFundArea(exitEmp.getFundArea());
			}else{
				if((null == spsBillEmp.getFundArea() || null == spsBillEmp.getInsuranceArea()) && (null != paidBill.get("areaName") && StringUtils.isNotBlank(paidBill.get("areaName").getAnalysisValue()))){
					String areaName = paidBill.get("areaName").getAnalysisValue();
					if(null == spsBillEmp.getInsuranceArea()){
						if(null != areaNameMap.get(areaName)){
							spsBillEmp.setInsuranceArea(areaNameMap.get(areaName));
						}else{
							bsArea.setName(areaName);
							List<BsArea> areaList = bsAreaService.getBsAreaByName(bsArea);
							if(null != areaList && !areaList.isEmpty()){
								spsBillEmp.setInsuranceArea(areaList.get(0).getAreaId());
								areaNameMap.put(bsArea.getName(),areaList.get(0).getAreaId());
							}
						}
					}else if (null == spsBillEmp.getFundArea()){
						if(null != areaNameMap.get(areaName)){
							spsBillEmp.setFundArea(areaNameMap.get(areaName));
						}else{
							bsArea.setName(areaName);
							List<BsArea> areaList = bsAreaService.getBsAreaByName(bsArea);
							if(null != areaList && !areaList.isEmpty()){
								spsBillEmp.setFundArea(areaList.get(0).getAreaId());
								areaNameMap.put(bsArea.getName(),areaList.get(0).getAreaId());
							}
						}
					}
				}
			}
			paidBillEmps.add(spsBillEmp);
		}
		// 总费用
		bill.setBillamount(Arith.add(bill.getActualServiceFee(),bill.getActualOfficeFee()));

		if (null != paidBillEmps && !paidBillEmps.isEmpty()) {
			for (SpsBillEmp emp : paidBillEmps) {
				spsBillEmpService.save(cti, emp);
				if (null != emp.getSpsBillDetails()) {
					for (SpsBillDetail detail : emp.getSpsBillDetails()) {
						if(detail.getCorpPayment().compareTo(BigDecimal.ZERO) == 0 && detail.getEmpPayment().compareTo(BigDecimal.ZERO) == 0 && detail.getCorpRatio().compareTo(BigDecimal.ZERO) == 0 && detail.getEmpRatio().compareTo(BigDecimal.ZERO) == 0 )
							continue;
						detail.setBillEmpId(emp.getId());
						detail.setCorpAddition(BigDecimal.ZERO);
						detail.setPsnAddition(BigDecimal.ZERO);
						if(detail.getCorpRatio().compareTo(BigDecimal.ZERO) > 0)
							detail.setCorpRatio(BigDecimal.ZERO);
						if(detail.getEmpRatio().compareTo(BigDecimal.ZERO) > 0)
							detail.setEmpRatio(BigDecimal.ZERO);
						spsBillDetailService.save(cti, detail);
					}
				}
			}
		}
		//更新账单信息
		spsBillService.update(cti, bill);
		//修改系统账单为已生成已对账
		SpsBill sysBill = new SpsBill();
		sysBill.setBillId(bills.get(0).getBillId());
		sysBill.setBillState("SEND");
		spsBillService.update(cti,sysBill);
		result.setDataValue("total",String.valueOf(analysisResult.getDataList().size()));//处理数量
		return result;
	}

	/**
	 * 服务商账单导出
	 *  @param request
	 *  @param response
	 *  @param cti
	 *  @param vo 
	 *  @createDate  	: 2017年7月20日 上午10:05:49
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月20日 上午10:05:49
	 *  @updateAuthor  	:
	 */
	@Override
	public void serviceBillExport(HttpServletRequest request,HttpServletResponse response,ContextInfo cti, ServiceBillVo vo) {
		try {
			Result result = Result.createResult().setSuccess(false);
			SpsBill spsBill = new SpsBill();
	        spsBill.setSpId(vo.getSpId());
	        spsBill.setCpId(vo.getCpId());
	        spsBill.setPeriod(vo.getPeriod());
	        spsBill.setBillTypeEq("PREPAY");
	        spsBill.setDr(0);
	        List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
	        if(spsBills.size() > 0){
	        	if(checkAuthority(cti, spsBills)){
		        	InputStream is = null;
		    		response.setContentType("application/octet-stream");
		    		request.setCharacterEncoding("UTF-8");
		    		BufferedInputStream bis = null;
		    		BufferedOutputStream bos = null;
		    		is = sysUploadfileService.downloadFile(spsBills.get(0).getFileId());
		    		if (null == is) {
		    			result.setError("文件路径问题，请检查后下载！");
		    			write(response,JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
		    			return;
		    		}
		    		bis = new BufferedInputStream(is);
		    		bos = new BufferedOutputStream(response.getOutputStream());
		    		response.setHeader("Content-disposition", "attachment; filename=" + new Date().getTime()+".xlsx");
		    		byte[] buff = new byte[2048];
		    		int bytesRead;
		    		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		    			bos.write(buff, 0, bytesRead);
		    		}
		    		bis.close();
		    		bos.close();
		    		is.close();
		    		return;
	        	}
	        }else{
	        	result.setError("文件不存在！");
	            write(response,JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
	            return;
	        }
		} catch (Exception e) {
		}
	}
	
	/**
     * write
     *
     * @param response
     * @param str
     */
    public void write(HttpServletResponse response, String str) {
        response.setContentType("text/html;charset=utf-8");
        try {
            OutputStream os = response.getOutputStream();
            os.write(str.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (IOException e) {
        	log.error(e);
        }
    }

    /**
     * 导出对账结果
     *  @param request
     *  @param response
     *  @param cti
     *  @param vo 
     *  @createDate  	: 2017年7月20日 上午10:18:20
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年7月20日 上午10:18:20
     *  @updateAuthor  	:
     */
	@Override
	public void serviceBillDetailsExport(HttpServletRequest request, HttpServletResponse response, ContextInfo cti, ServiceBillVo vo) {
		InputStream in = null;
		try {
			// 获取模板
			in = new FileInputStream(new File(request.getServletContext().getRealPath("")+File.separator
					+"WEB-INF"+File.separator+"classes"+File.separator+"template"+File.separator+"serviceBillDetails.xlsx"));
			Workbook workbook = null;
	        try {
	            workbook = new XSSFWorkbook(in);
	        } catch (Exception ex) {
	            workbook = new HSSFWorkbook(in);
	        }
	        // 获取第一个叶签
	        Sheet sheet = workbook.getSheetAt(0);
	        // 获取第0行  设置服务商名称
	        Row row0=sheet.getRow(0);
	        SpService service = spServiceService.findByPK(vo.getSpId());
	        row0.getCell(0).setCellValue(service.getSpName());
	        SpsBill spsBill = new SpsBill();
			spsBill.setSpId(vo.getSpId());
			spsBill.setCpId(vo.getCpId());
			spsBill.setPeriodEq(vo.getPeriod());
			spsBill.setDr(0);
			// 根据企业ID、服务商ID和月份获取账单
			List<SpsBill> spsBills = spsBillDao.freeFind(spsBill);
			if(checkAuthority(cti, spsBills)){
				// 获取第1行  设置服务商成本
		        Row row1=sheet.getRow(1);
		        vo.setSource(1);
		        ServiceBillCountVo serviceBillCountVo = findServiceBillDetailsCount(cti, vo);
		        row1.getCell(3).setCellValue(serviceBillCountVo.getSysPrepayCount().toString());
		        row1.getCell(9).setCellValue(serviceBillCountVo.getServicePrepayCount().toString());
		        row1.getCell(15).setCellValue(serviceBillCountVo.getDifferenceCount().toString());
		        // 获取第2行  设置服务商成本
		        Row row2=sheet.getRow(2);
		        row2.getCell(1).setCellValue(serviceBillCountVo.getSysCorpCount().toString());
		        row2.getCell(3).setCellValue(serviceBillCountVo.getSysEmpCount().toString());
		        row2.getCell(5).setCellValue(serviceBillCountVo.getSysServiceCount().toString());
		        row2.getCell(7).setCellValue(serviceBillCountVo.getServiceCorpCount().toString());
		        row2.getCell(9).setCellValue(serviceBillCountVo.getServiceEmpCount().toString());
		        row2.getCell(11).setCellValue(serviceBillCountVo.getServiceServiceCount().toString());
		        row2.getCell(13).setCellValue(serviceBillCountVo.getDifferenceCorpCount().toString());
		        row2.getCell(15).setCellValue(serviceBillCountVo.getDifferenceEmpCount().toString());
		        row2.getCell(17).setCellValue(serviceBillCountVo.getDifferenceServiceCount().toString());
		        // 服务商对账详情数据列表
		        List<Map<String,Object>> maps = spsBillDao.serviceBillDetailsExport(vo);
		        for (int i = 0; i < maps.size(); i++) {
		        	Map<String,Object> map = maps.get(i);
		        	Row dataRow=sheet.createRow(i+5);
		        	dataRow.createCell(0).setCellValue(null != map.get("name") ? map.get("name").toString() : "");
		        	dataRow.createCell(1).setCellValue(null != map.get("code") ? map.get("code").toString() : "");
		        	dataRow.createCell(2).setCellValue((null != map.get("branch") && !"".equals(map.get("branch").toString())) ? map.get("branch").toString() : "--");
		        	dataRow.createCell(3).setCellValue(null != map.get("areaName") ? map.get("areaName").toString() : "--");
		        	dataRow.createCell(4).setCellValue(null != map.get("month") ? map.get("month").toString() : "--");
		        	dataRow.createCell(5).setCellValue(null != map.get("insuranceType") ? map.get("insuranceType").toString() : "--");
		        	dataRow.createCell(6).setCellValue(null != map.get("payType") ? map.get("payType").toString() : "--");
		        	dataRow.createCell(7).setCellValue(null != map.get("businessStatus") ? map.get("businessStatus").toString() : "--");
		        	BigDecimal fundFeePrepay = new BigDecimal(map.get("fundFeePrepay").toString()) ;
		        	BigDecimal insFeePrepay = new BigDecimal(map.get("insFeePrepay").toString()) ;
		        	BigDecimal fundFeePaid = new BigDecimal(map.get("fundFeePaid").toString()) ;
		        	BigDecimal insFeePaid = new BigDecimal(map.get("insFeePaid").toString()) ;
		        	
		        	dataRow.createCell(8).setCellValue(insFeePrepay.toString());
		        	dataRow.createCell(9).setCellValue(insFeePaid.toString());
		        	dataRow.createCell(10).setCellValue(Arith.sub(insFeePrepay, insFeePaid).toString());
		        	
		        	dataRow.createCell(11).setCellValue(fundFeePrepay.toString());
		        	dataRow.createCell(12).setCellValue(fundFeePaid.toString());
		        	dataRow.createCell(13).setCellValue(Arith.sub(fundFeePrepay, fundFeePaid).toString());
		        	
		        	dataRow.createCell(14).setCellValue(Arith.add(fundFeePrepay, insFeePrepay).toString());
		        	dataRow.createCell(15).setCellValue(Arith.add(fundFeePaid, insFeePaid).toString());
		        	dataRow.createCell(16).setCellValue(Arith.sub(Arith.add(fundFeePrepay, insFeePrepay),Arith.add(fundFeePaid, insFeePaid)).toString());
		        	
		        }
			}
			response.setHeader("Content-disposition", "attachment; filename=" + new Date().getTime()+".xlsx");
	        workbook.write(response.getOutputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 服务商个人对账详情
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月27日 下午2:26:11
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月27日 下午2:26:11
	 *  @updateAuthor  	:
	 */
	@Override
	public ServiceBillDetailsEmpVo serviceBillDetailsEmp(ContextInfo cti, ServiceBillVo vo) {
		ServiceBillDetailsEmpVo serviceBillDetailsEmpVo = new ServiceBillDetailsEmpVo();
		ServiceBillDetailsEmpDataVo serviceBillDetailsEmpDataVo = null;
		// 获取缴费详情
		List<Map<String, Object>> maps = spsBillDao.findEmpInsurance(vo);
		if(maps.size() > 0){
			Map<String, Object> emp = maps.get(0);
			serviceBillDetailsEmpVo.setEmpName(emp.get("name").toString());
			serviceBillDetailsEmpVo.setCode(emp.get("code").toString());
			serviceBillDetailsEmpVo.setCorpName((null != emp.get("branch") && !"".equals(emp.get("branch").toString())) ? emp.get("branch").toString() : "--");
			serviceBillDetailsEmpVo.setAreaName(emp.get("areaName").toString());
			serviceBillDetailsEmpVo.setPeriod(vo.getPeriod());
		}
		BigDecimal sysCount = BigDecimal.ZERO;
		BigDecimal serviceCount = BigDecimal.ZERO;
		for (int i = 0; i < maps.size(); i++) {
			serviceBillDetailsEmpDataVo = new ServiceBillDetailsEmpDataVo();
			Map<String, Object> data = maps.get(i);
			serviceBillDetailsEmpDataVo.setInsuranceType(data.get("insName").toString());
			serviceBillDetailsEmpDataVo.setTheirPeriod(data.get("month").toString());
			serviceBillDetailsEmpDataVo.setCorpPayBase(new BigDecimal(data.get("corp_paybase").toString()));
			serviceBillDetailsEmpDataVo.setEmpPayBase(new BigDecimal(data.get("emp_paybase").toString()));
			serviceBillDetailsEmpDataVo.setCorpRatio(BigDecimal.valueOf(Double.valueOf(data.get("corpratio").toString())).stripTrailingZeros().toPlainString()+"%");
			serviceBillDetailsEmpDataVo.setEmpRatio(BigDecimal.valueOf(Double.valueOf(data.get("empratio").toString())).stripTrailingZeros().toPlainString()+"%");
			
			serviceBillDetailsEmpDataVo.setSysCorpPayment(new BigDecimal(data.get("syscorp").toString()));
			serviceBillDetailsEmpDataVo.setSysEmpPayment(new BigDecimal(data.get("sysemp").toString()));
			serviceBillDetailsEmpDataVo.setSysCount(new BigDecimal(data.get("syscount").toString()));
			sysCount = Arith.add(sysCount,serviceBillDetailsEmpDataVo.getSysCount());
			
			serviceBillDetailsEmpDataVo.setServiceCorpPayment(new BigDecimal(data.get("servicecorp").toString()));
			serviceBillDetailsEmpDataVo.setServiceEmpPayment(new BigDecimal(data.get("serviceemp").toString()));
			serviceBillDetailsEmpDataVo.setServiceCount(new BigDecimal(data.get("servicecount").toString()));
			serviceCount = Arith.add(serviceCount,serviceBillDetailsEmpDataVo.getServiceCount());
			
			if(serviceBillDetailsEmpDataVo.getSysCorpPayment().compareTo(serviceBillDetailsEmpDataVo.getServiceCorpPayment()) != 0){
				serviceBillDetailsEmpDataVo.setCorpPaymentColor(1);
			}
			if(serviceBillDetailsEmpDataVo.getSysEmpPayment().compareTo(serviceBillDetailsEmpDataVo.getServiceEmpPayment()) != 0){
				serviceBillDetailsEmpDataVo.setEmpPaymentColor(1);
			}
			if(serviceBillDetailsEmpDataVo.getCorpPaymentColor()==1 || serviceBillDetailsEmpDataVo.getEmpPaymentColor()==1){
				serviceBillDetailsEmpDataVo.setCountColor(1);
			}
			serviceBillDetailsEmpVo.getPsnDatas().add(serviceBillDetailsEmpDataVo);
		}
		serviceBillDetailsEmpVo.setCountDifference(Arith.sub(sysCount, serviceCount));
		return serviceBillDetailsEmpVo;
	}
}
