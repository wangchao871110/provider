package com.xfs.bs.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xfs.base.dao.BsSppayrecordDao;
import com.xfs.base.model.BsSppayrecord;
import com.xfs.bs.service.BsSppayrecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.Constant;
import com.xfs.common.util.DateUtil;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.model.CmCorp;
import com.xfs.common.util.NumberUtil;
import com.xfs.ts.dao.SpsTsPersonFlowDao;
import com.xfs.ts.model.SpsTsPersonFlow;
import com.xfs.user.service.SysFunctionDataService;

@Service
public class BsSppayrecordServiceImpl implements BsSppayrecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsSppayrecordServiceImpl.class);
	
	@Autowired
	private BsSppayrecordDao bsSppayrecordDao;
	@Autowired
	private CmCorpDao cmCorpDao;
	@Autowired
	private SpsTsPersonFlowDao spsTsPersonFlowDao;
	@Autowired
	private CmEmployeeDao cmEmployeeDao;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	
	public int save(ContextInfo cti, BsSppayrecord vo ){
		return bsSppayrecordDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsSppayrecord vo ){
		return bsSppayrecordDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsSppayrecord vo ){
		return bsSppayrecordDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsSppayrecord vo ){
		return bsSppayrecordDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsSppayrecord vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSppayrecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsSppayrecord> datas = bsSppayrecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsSppayrecord> findAll(BsSppayrecord vo){
		
		List<BsSppayrecord> datas = bsSppayrecordDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 11:14:40
	

	@Override
	public PageModel findLisBySpid(PageInfo pi, BsSppayrecord vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSppayrecordDao.countLisBySpid(vo);
		pm.setTotal(total);
		List<BsSppayrecord> datas = bsSppayrecordDao.findLisBySpid(vo, pageIndex, pageSize, null);
		pm.setDatas(datas);
		return pm;
	}
	
	@Override
	public BsSppayrecord findMaxDateBySpId(BsSppayrecord vo) {
		return bsSppayrecordDao.findMaxDateBySpId(vo);
	}
	
	@Override
	public BsSppayrecord getTotleStandareFee(BsSppayrecord vo) {
		return bsSppayrecordDao.getTotleStandareFee(vo);
	}
	
	@Override
	public Result querySpPayForIndex(ContextInfo cti){
		Result result = Result.createResult();
		CmCorp corpQuery = new CmCorp();
		String queryMonth = DateUtil.getDateStr(new Date(),"yyyyMM");
		corpQuery.setSpId(cti.getOrgId());
		// 获取数据权限
		corpQuery.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		//服务商下企业数
		Integer corpNum = cmCorpDao.countFreeFindBySpId(corpQuery);
		SpsTsPersonFlow perQuery = new SpsTsPersonFlow();
		perQuery.setSpId(cti.getOrgId());
		perQuery.setMonthEq(queryMonth);
		perQuery.setType("1");
		//服务商代缴社保人数
		Integer oneEmp = spsTsPersonFlowDao.countFreeFind(perQuery);
		perQuery.setType("2");
		//服务商代缴公积金人数;
		Integer twoEmp = spsTsPersonFlowDao.countFreeFind(perQuery);
		Map<String,Object> empQuery = new HashMap();
		empQuery.put("spId",cti.getOrgId());
		//服务商下企业员工数
		Integer empNum = (Integer)cmEmployeeDao.queryForObject("CM_EMPLOYEE.FindEmpCountBySpIdAndState",empQuery);
		//获取三种员工数中最大值
		Integer finalNum = Math.max(oneEmp,twoEmp);
		finalNum = Math.max(finalNum,empNum);
		BsSppayrecord sppayrecord = new BsSppayrecord();
		sppayrecord.setSpId(cti.getOrgId());
		Date queryDate = new Date();
		sppayrecord.setBeginDateTo(queryDate);
		sppayrecord.setEndDateFrom(queryDate);
		sppayrecord.setDr(0);
		List<BsSppayrecord> list =  bsSppayrecordDao.freeFind(sppayrecord);
		BigDecimal standardFee = BigDecimal.ZERO;
		BigDecimal discountedFee = BigDecimal.ZERO;
		Integer serviceNum = 0;
		String unitNme = "";
		String unit = "";
		if(CollectionUtils.isNotEmpty(list)){
			standardFee = list.get(0).getStandardFee() == null ? BigDecimal.ZERO : list.get(0).getStandardFee();
			discountedFee = list.get(0).getDiscountedFee() == null ? BigDecimal.ZERO : list.get(0).getDiscountedFee();
			serviceNum = list.get(0).getServiceNum() == null ? 0 : list.get(0).getServiceNum();
			if(list.get(0).getUnit() != null){
				unit = list.get(0).getUnit();
				if(unit.equals("QUARTER")){
					unitNme = "季度";
				}else if(unit.equals("HALF")){
					unitNme = "半年";
				}else if(unit.equals("YEAR")){
					unitNme = "年";
				}else if(unit.equals("THREE")){
					unitNme = "三年";
				}else if(unit.equals("TRIAL")){
					unitNme = "试用";
				}
			}
		}
		String actualFeeStr = "";
		String discountedFeeStr = "";
		if(unit.equals("TRIAL")){
			actualFeeStr = "试用";
			discountedFeeStr = "试用";
		}else{
			if(standardFee.doubleValue() == 0){
				actualFeeStr = "——";
			}else{
				actualFeeStr =  NumberUtil.formatMoney(NumberUtil.sub(standardFee, discountedFee) + "" ,2);
			}
			if(discountedFee.doubleValue() == 0){
				discountedFeeStr = "——";
			}else{
				discountedFeeStr =  NumberUtil.formatMoney((-discountedFee.doubleValue()) + "",2);
			}
		}
		result.setDataValue("finalNum",NumberUtil.formatNumber(finalNum));
		result.setDataValue("standardFee",standardFee);
		result.setDataValue("discountedFee",discountedFeeStr);
		result.setDataValue("serviceNum",serviceNum);
		result.setDataValue("unitNme",unitNme);
		result.setDataValue("unit",unit);
		result.setDataValue("actualFee",actualFeeStr);
		result.setDataValue("corpNum",corpNum);
		return result;
	}
	public static void  main(String[] args){
		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(sdf.parse("2014-01-11"));
			c2.setTime(sdf.parse("2015-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		Integer year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

		System.out.print((year * 12) + result);
	}
	
	
}
