package com.xfs.bill.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.bill.dto.EmpDetail;
import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.common.util.Arith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xfs.bill.dao.SpsBillEmpDao;
import com.xfs.bill.model.SpsBillEmp;
import com.xfs.business.service.BdInsuranceareaService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;

/**
 * 账单员工服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:09
 * @version 	: V1.0
 */
@Service
public class SpsBillEmpServiceImpl implements SpsBillEmpService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBillEmpServiceImpl.class);
	
	@Autowired
	private SpsBillEmpDao spsBillEmpDao;

	@Autowired
	private BdInsuranceareaService bdInsuranceareaService;

	@Autowired
	private SpsBillDetailService spsBillDetailService;
	
	public int save(ContextInfo cti, SpsBillEmp vo ){
		return spsBillEmpDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsBillEmp vo ){
		return spsBillEmpDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsBillEmp vo ){
		return spsBillEmpDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsBillEmp vo ){
		return spsBillEmpDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsBillEmp vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillEmpDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBillEmp> datas = spsBillEmpDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsBillEmp> findAll(SpsBillEmp vo){
		
		List<SpsBillEmp> datas = spsBillEmpDao.freeFind(vo);
		return datas;
		
	}
	
	
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
	@Override
	public Map<String,Object> queryEmBillDetail(Integer spid,Integer cmid,Integer empid,Integer bill_id,Integer id){
		return spsBillEmpDao.queryEmpBillDetail(spid,cmid,empid,bill_id,id);
	}

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
	@Override
	public PageModel queryEmpList(PageInfo pi, Integer spid,Integer cpid,Integer bill_id,String period,String searchWord,Integer area_id,Integer scheme_id) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillEmpDao.queryEmpBillCount(spid,cpid,bill_id,period,searchWord,area_id,scheme_id);
		pm.setTotal(total);
		pm.setDatas(spsBillEmpDao.queryEmpBillList(spid,cpid,bill_id,period,searchWord,area_id,scheme_id,pageIndex,pageSize));
		return pm;
	}

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
	@Override
	public List<Map<String,Object>> queryAllEmpList(Integer spid,Integer cmid,Integer bill_id,String depute_type,String bill_type){
		//获取员工基本信息列表
		List<Map<String,Object>>  empInfoList = spsBillEmpDao.queryAllEmpBillList(spid,cmid,bill_id,depute_type,bill_type);
		//获取员工动态标题列表
		//List<Map<String,Object>>  insuranceTitles = bdInsuranceareaService.queryEmInsuranceAreaList(spid,cmid,bill_id);
		if(null != empInfoList && !empInfoList.isEmpty()){
			bindEmpInsuranceI(empInfoList,"BILL",null);
		}
		return empInfoList;
	}

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
	public List<Map<String,Object>> queryAllEmpListInsDetail(ContextInfo cti,String period,String searchWord,String area_id){
		//获取员工基本信息列表
		List<Map<String,Object>>  empInfoList = spsBillEmpDao.queryAllEmpListByInsDetail(cti,period,searchWord,area_id);
		//获取员工动态标题列表
		if(null != empInfoList && !empInfoList.isEmpty()){
			bindEmpInsuranceI(empInfoList,"INSDETAIL",period);
		}
		return empInfoList;
	}

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
	@Override
	public List<SpsBillEmp> findBillEmpWithDetails(Integer billId) {
		return spsBillEmpDao.findBillEmpWithDetails(billId);
	}

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
	@Override
	public PageModel findPaidBillEmps(PageInfo pi, Integer cpId, Integer billId, String searchWord) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		pm.setTotal(spsBillEmpDao.findPaidBillEmpCount(cpId, billId, searchWord));
		pm.setDatas(spsBillEmpDao.findPaidBillEmps(cpId, billId, searchWord, pageIndex, pageSize));
		return pm;
	}

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
	public PageModel findPaidBillMarginEmps(PageInfo pi, Integer spId,Integer cpId, Integer billId, Integer prepay_bill_id,String searchWord) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		pm.setTotal(spsBillEmpDao.findPaidBillMarginEmpCount(spId,cpId, billId,prepay_bill_id, searchWord));
		pm.setDatas(spsBillEmpDao.findPaidBillMarginEmps(spId,cpId, billId, prepay_bill_id,searchWord, pageIndex, pageSize));
		return pm;
	}

	/**
	 * 查询绑定员工社保信息
	 * @param empInfoList
	 */
	private void bindEmpInsuranceI(List<Map<String,Object>> empInfoList,String insType,String period){
		//费用总计
		BigDecimal total_crop_sum = BigDecimal.ZERO;
		BigDecimal total_emp_sum = BigDecimal.ZERO;
		BigDecimal total_fee_sum = BigDecimal.ZERO;
		for(Map<String,Object> item : empInfoList){
			if(null == item.get("id"))
				continue;
			List<Map<String,Object>> billInsurance = spsBillDetailService.queryEmpInsurances(Integer.parseInt(String.valueOf(item.get("id"))),null,null,insType,period);
			BigDecimal crop_sum = BigDecimal.ZERO;
			BigDecimal emp_sum = BigDecimal.ZERO;
			BigDecimal fun_crop_sum = BigDecimal.ZERO;
			BigDecimal fun_emp_sum = BigDecimal.ZERO;
			for(Map<String,Object> rpt : billInsurance){//解析社保信息
				if(null == rpt.get("ins_type"))
					continue;
				String ins_type = String.valueOf(rpt.get("ins_type"));
				Map<String,String> detail = JSON.parseObject(JSON.toJSONString(rpt),Map.class);
				item.put(String.valueOf(rpt.get("code")),detail);
				if("INSURANCE".equals(ins_type)){
					crop_sum = crop_sum.add(StringUtils.toBigDecimal(rpt.get("corp_payment")));
					emp_sum = emp_sum.add(StringUtils.toBigDecimal(rpt.get("emp_payment")));
				}else if("FUND".equals(ins_type)){
					fun_crop_sum = fun_crop_sum.add(StringUtils.toBigDecimal(rpt.get("corp_payment")));
					fun_emp_sum = fun_emp_sum.add(StringUtils.toBigDecimal(rpt.get("emp_payment")));
				}
				/**
				 * 获取公积金参保地
				 */
				item.put("fund_area",rpt.get("fund_area"));
			}
			//五险小计
			Map<String,Object> wxxj = new HashMap<>();
			item.put("wxxj",wxxj);
			wxxj.put("crop",crop_sum);
			wxxj.put("emp",emp_sum);
			wxxj.put("total_sum",crop_sum.add(emp_sum));
			//五险一金小计
			Map<String,Object> wxxjyj = new HashMap<>();
			item.put("wxxjyj",wxxjyj);
			wxxjyj.put("crop",crop_sum.add(fun_crop_sum));
			wxxjyj.put("emp",emp_sum.add(fun_emp_sum));
			wxxjyj.put("total_sum",crop_sum.add(emp_sum).add(fun_crop_sum).add(fun_emp_sum));
			//总费用合计---获取员工费用信息
			item.put("fee_crop_sum",crop_sum.add(fun_crop_sum));
			item.put("fee_emp_sum",emp_sum.add(fun_emp_sum));
			item.put("fee",null == item.get("service_fee") ? "" : item.get("service_fee"));
			if("INSDETAIL".equals(insType)){
				item.put("fee_total", Arith.add(crop_sum.add(fun_crop_sum),emp_sum.add(fun_emp_sum),(BigDecimal.valueOf(null == item.get("service_fee") ? 0L : Long.parseLong(String.valueOf(item.get("service_fee")))))));
			}else{
				item.put("fee_total", Arith.add(crop_sum.add(fun_crop_sum),emp_sum.add(fun_emp_sum),(BigDecimal)(null == item.get("service_fee") ? "" : item.get("service_fee"))));
			}
			item.put("adjust_reason",null == item.get("adjust_reason") ? "" : item.get("adjust_reason"));
			total_crop_sum = total_crop_sum.add(crop_sum).add(fun_crop_sum);
			total_emp_sum = total_emp_sum.add(emp_sum).add(fun_emp_sum);
			total_fee_sum = total_fee_sum.add(StringUtils.toBigDecimal(item.get("service_fee")));
		}
		//多出合计一列
		Map<String,Object> t_fee_total = new HashMap<>();
		t_fee_total.put("fee_crop_sum",total_crop_sum);
		t_fee_total.put("fee_emp_sum",total_emp_sum);
		t_fee_total.put("fee",total_fee_sum);
		t_fee_total.put("fee_total", Arith.add(total_crop_sum,total_emp_sum,total_fee_sum));
		t_fee_total.put("adjust_reason","总计");

		empInfoList.add(t_fee_total);
	}

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
	public PageModel findBillHREmpWithDetails(PageInfo pi,Integer cpId, Integer billId, String searchWords){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		List<Integer> empIds = spsBillEmpDao.findBillHREmpWithEmpIds(cpId,billId,searchWords,pageIndex,pageSize);
		if(null != empIds && !empIds.isEmpty()){
			List<EmpDetail> empDetails = spsBillEmpDao.findBillHREmpWithDetails(cpId,billId,empIds);
			List<Map<String,Object>> insMap = spsBillEmpDao.findBillDetailIns(billId);
			/**
			 * 补全社保数据
			 */
			for (EmpDetail empDetail : empDetails){
				if(null != insMap && !insMap.isEmpty()){
					for(int i = 0;i < insMap.size();i++){
						String inName = String.valueOf(insMap.get(i).get("name"));
						boolean isOk = false;
						if(i >= empDetail.getInsureEmpDetails().size()){
							isOk = true;
						}else{
							InsureEmpDetail detail = empDetail.getInsureEmpDetails().get(i);
							if(!inName.equals(detail.getInsureName())){
								isOk = true;
							}
						}
						if(isOk){
							InsureEmpDetail insureEmp = new InsureEmpDetail();
							insureEmp.setPeriod(empDetail.getInsuredMonth());
							insureEmp.setAreaName("--");
							insureEmp.setCorpPaybase("--");
							insureEmp.setCorpPayment("--");
							insureEmp.setCorpRatio("--");
							insureEmp.setEmpPaybase("--");
							insureEmp.setEmpPayment("--");
							insureEmp.setEmpRatio("--");
							insureEmp.setInsureName(inName);
							insureEmp.setTotalPayment("--");
							empDetail.getInsureEmpDetails().add(i,insureEmp);
						}
					}
				}
			}
			pm.setDatas(empDetails);
		}
		pm.setTotal(spsBillEmpDao.findBillHREmpWithDetailsCOUNT(cpId,billId,searchWords));
		return pm;
	}
}
