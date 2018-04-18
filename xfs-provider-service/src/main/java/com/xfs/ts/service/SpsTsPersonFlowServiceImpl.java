package com.xfs.ts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfs.bill.dto.PaidBillDto;
import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.bill.service.SpsBillService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.sps.utils.JacksonAnnotationFieldUtil;
import com.xfs.ts.dao.SpsTsPersonFlowDao;
import com.xfs.ts.model.SpsTsPersonFlow;

@Service
public class SpsTsPersonFlowServiceImpl implements SpsTsPersonFlowService,IRedisKeys {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTsPersonFlowServiceImpl.class);
	
	@Autowired
	private SpsTsPersonFlowDao spsTsPersonFlowDao;

	@Autowired
	private SpsBillService spsBillService;

	public int save(ContextInfo cti, SpsTsPersonFlow vo ){
		return spsTsPersonFlowDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTsPersonFlow vo ){
		return spsTsPersonFlowDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTsPersonFlow vo ){
		return spsTsPersonFlowDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTsPersonFlow vo ){
		return spsTsPersonFlowDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTsPersonFlow vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTsPersonFlowDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTsPersonFlow> datas = spsTsPersonFlowDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTsPersonFlow> findAll(SpsTsPersonFlow vo){
		
		List<SpsTsPersonFlow> datas = spsTsPersonFlowDao.freeFind(vo);
		return datas;
		
	}
	

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/06 13:41:11


	@Override
	public SpsTsPersonFlow getSpsTsPersonFlow(SpsTsPersonFlow vo) {
		return spsTsPersonFlowDao.getSpsTsPersonFlow(vo);
	}
	@Override
	public int count(String accId, String stime) {
		SpsTsPersonFlow flow = new SpsTsPersonFlow();
		flow.setAccId(Integer.parseInt(accId));
		flow.setMonth(stime);
		return spsTsPersonFlowDao.countFreeFind(flow);
	}


	/**
	 * 解析下行数据
     */
	@Override
	public void analysisPaidBill(ContextInfo cti) {
		SpsTsPersonFlow vo = new SpsTsPersonFlow();
		List<SpsTsPersonFlow> personFlows = null;
		try{
			personFlows = spsTsPersonFlowDao.queryAllFlow(vo);
		}catch (Exception e){
			e.printStackTrace();
		}
		if(null != personFlows && !personFlows.isEmpty()){
			List<PaidBillDto> paidBillDtos = new ArrayList<>();
			for(SpsTsPersonFlow personFlow : personFlows){
				if(StringUtils.isBlank(personFlow.getJson()))
					continue;
				TypeReference<Map<String,String>> jsonMapRef = new TypeReference<Map<String,String>>(){};
				Map<String,String> jsonMap = JSON.parseObject(String.valueOf(personFlow.getJson()).replace("},{",","),jsonMapRef);
				jsonMap.put("姓名",personFlow.getName());
				jsonMap.put("身份证",personFlow.getIdCard());
				jsonMap.put("所属月份",personFlow.getMonth());
				jsonMap.put("参保月份",personFlow.getMonth());
				jsonMap.put("公积金库ID",null == personFlow.getFundAccid() ? null : String.valueOf(personFlow.getFundAccid()));
				jsonMap.put("社保库ID",null == personFlow.getInsuranceAccid() ? null : String.valueOf(personFlow.getInsuranceAccid()));
				ObjectMapper showAllMapper = new ObjectMapper();
				showAllMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				showAllMapper.setAnnotationIntrospector(new JacksonAnnotationFieldUtil(jsonMap));
				try{
					PaidBillDto paidBillDto = showAllMapper.readValue(JSON.toJSONString(jsonMap),PaidBillDto.class);
					/**
					 * 过滤实缴保险类型、补全字段
					 */
					List<CmEmployeeInsurance> employeeInsurances = personFlow.getInsurancelist();
					boolean fund_flow = false;
					boolean insurance_flow = false;
					Map<String,SpsEmpActualdetail> spsEmpActualdetailMap = new HashMap<>();
					for(CmEmployeeInsurance employeeInsurance : employeeInsurances){
						SpsEmpActualdetail actualdetail = paidBillDto.queryActualDetail().get(String.valueOf(employeeInsurance.getInsuranceId()));

						/**
						 * 1、支持下行并且下行数据存在
						 * 2、支持下行下行数据不存在
						 * 3、不支持下行但人员保险信息存在
						 */
					     if(employeeInsurance.getInsuranceId().equals(6) && "Y".equals(employeeInsurance.getFlow()))
							 fund_flow = true;
						 if(!employeeInsurance.getInsuranceId().equals(6) && "Y".equals(employeeInsurance.getFlow()))
							 insurance_flow = true;

						if(null != actualdetail && ("Y".equals(employeeInsurance.getFlow()) && actualdetail.isHave())){
							spsEmpActualdetailMap.put(String.valueOf(employeeInsurance.getInsuranceId()),actualdetail);
							//补全保险信息
							if(null == actualdetail.getCorpPayment() || actualdetail.getCorpPayment().equals(0)){
								actualdetail.setCorpPayment(employeeInsurance.getCorpPayment());
							}

							if(null == actualdetail.getEmpPayment() || actualdetail.getEmpPayment().equals(0)){
								actualdetail.setEmpPayment(employeeInsurance.getEmpPayment());
							}

							if(null == actualdetail.getPayBase() || actualdetail.getPayBase().equals(0)){
								actualdetail.setPayBase(employeeInsurance.getCorpPaybase());
							}

							if(null == actualdetail.getCorpRatio() || actualdetail.getCorpRatio().equals(0)){
								actualdetail.setCorpRatio(employeeInsurance.getCorpRatio());
							}

							if(null == actualdetail.getEmpRatio() || actualdetail.getEmpRatio().equals(0)){
								actualdetail.setEmpRatio(employeeInsurance.getEmpRatio());
							}
							paidBillDto.setEmpId(employeeInsurance.getEmpId());
						}
					}
					paidBillDto.setActualDetail(spsEmpActualdetailMap);//设置新保险种类列表
					if(paidBillDto.queryActualDetail().isEmpty() || (fund_flow && insurance_flow && personFlow.getType().indexOf(",") < 0))
						continue;
					paidBillDto.setSource(2);
					paidBillDtos.add(paidBillDto);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			//持久化
			if(null != paidBillDtos && !paidBillDtos.isEmpty()){
				spsBillService.savePaidBillDetail(cti,paidBillDtos,null);
			}
		}

	}
}
