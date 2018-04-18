package com.xfs.aop.task.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.service.impl.SpsTaskServiceImpl;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.MobileValidator;
import com.xfs.sp.dto.HospitalDto;
import com.xfs.sp.dto.InsuranceTypeDto;
import com.xfs.sps.service.TaskDataParseInterface;

/**
 * 
 * 基础类，目前 添加 校验功能
 * @author lifq
 *
 * 2016年5月19日  上午10:32:23
 */
public abstract class BaseTaskDataParse implements TaskDataParseInterface {
	
	IdcardValidator idValid = new IdcardValidator();
	
//	private static BsRuleService bsRuleService; 
//
//	private synchronized static void init() {
//		bsRuleService = (BsRuleService) SpringContextUtil.getBean(BsRuleService.class);
//	}

//	static {
//		init();
//	}
	
	/**
	 * 导入前 基本校验（校验必录项、去除多余字段）
	 *
	 * @author lifq
	 *
	 * 2016年5月19日  上午10:32:23
	 */
	@Override
	public String checkBeforeImport(ContextInfo cti, Map<String,String> curMap, Map<String,Object> paraMap) {
		String err = "";
		List<Map<String,Object>> fieldList = (List<Map<String, Object>>) paraMap.get("fieldList");
		if(null!=fieldList && fieldList.size()>0){
			
			String isNeedDelField = paraMap.get("isNeedDelField").toString();
			if("1".equals(isNeedDelField)){
				//去除 多余字段
				Map<String,String> needFieldMap = new HashMap<String,String>();
				needFieldMap.put(SpsTaskServiceImpl.SYS_BS_TYPE, "业务类型");
				for(Map<String,Object> fieldMap:fieldList){
					if(curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE).equals(fieldMap.get("bstype_id").toString())){
						needFieldMap.put(fieldMap.get("code").toString(), fieldMap.get("name").toString());
					}
				}
				List<String> delList = new ArrayList<String>();
				for(Map.Entry<String, String> entry:curMap.entrySet()){
					if(!needFieldMap.containsKey(entry.getKey())){
						delList.add(entry.getKey());
					}
				}
				if(null!=delList && delList.size()>0){
					for(int i=0;i<delList.size();i++){
						curMap.remove(delList.get(i));
					}
				}
			}
			
			Map<String,String> dictMap = (Map<String, String>) paraMap.get("dictMap");
			Map<String,Map<String,String>> dictItemMap = (Map<String, Map<String, String>>) paraMap.get("dictItemMap");
			
			//校验 必录项
			for(Map<String,Object> fieldMap:fieldList){
				String code = fieldMap.get("code").toString();
				if("Y".equals(fieldMap.get("required")) && 
						curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE).equals(fieldMap.get("bstype_id").toString())){
					if(StringUtils.isBlank(curMap.get(code))){
						err += fieldMap.get("name")+"为空！";
						continue;
					}
				}
				//校验 字典信息
				if(null!=dictMap && !dictMap.isEmpty() && StringUtils.isNotBlank(curMap.get(code))){
					String code_doc = code+"_doc";
					if(dictMap.containsKey(code_doc)){
						Map<String,String> itemMap = dictItemMap.get(code_doc);
						if(null!=itemMap && !itemMap.containsKey(curMap.get(code))){
							err += "【"+fieldMap.get("name")+"】内容有误！";
						}
						if(itemMap.containsKey(curMap.get(code))&&null!=itemMap){
							curMap.put(code,itemMap.get(curMap.get(code)));
						}
					}
				}
			}
			
			
			
			//校验 手机号
			if(StringUtils.isNotBlank(curMap.get("mobile"))){
				if(!MobileValidator.isMobileNO(curMap.get("mobile"))){
	        		 err += "手机号格式错误！";
	       		}
			}
			
			//校验身份证
			if(StringUtils.isBlank(curMap.get("identity_type"))){//证件类型 为空时 则 认为是身份证
//				IDCard
				curMap.put("identity_type","IDCard");
				if(StringUtils.isNotBlank(curMap.get("identity_code"))){
					if(!idValid.isValidatedAllIdcard(curMap.get("identity_code").trim())){
						err += "证件号码格式错误！";
					}
				}
			}
			Integer areaId = (Integer)paraMap.get("areaId");
			//校验 缴费基数
			if(StringUtils.isNotBlank(curMap.get("insurancebase"))){
				String funType = "";
				List<Map<String,Object>> bsTypeList = (List<Map<String, Object>>) paraMap.get("bsTypeList");
				if(null!=bsTypeList && bsTypeList.size()>0){
					for(Map<String,Object> tmpMap:bsTypeList){
			 			String tmpType = null==tmpMap.get("bstype_id")?"":tmpMap.get("bstype_id").toString();
			 			if(curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE).equals(tmpType)){
			 				funType = tmpMap.get("insurance_fund_type").toString();
			 				break;
			 			}
			 		 }
				}
				
			}
			Map<String, SpsFixedpointhospital> hosiptalNameMap = (Map<String, SpsFixedpointhospital>)paraMap.get("hosiptalNames");
			Map<String, SpsFixedpointhospital> hosiptalFullNameMap =  (Map<String, SpsFixedpointhospital>)paraMap.get("hosiptalFullNames");

			List<String> hospitalList = new ArrayList<String>();
			for (Map.Entry<String, String> entry : curMap.entrySet()) {
				// 校验医院
				if (null != entry.getKey() && entry.getKey().startsWith("hospital") && StringUtils.isNotBlank(entry.getValue())) {
					if (!hosiptalNameMap.containsKey(entry.getValue())
							&& !hosiptalFullNameMap.containsKey(entry.getValue())) {
						err += "[" + entry.getValue() + "]名称有误！";
					}else{
						//验证合格后放到 list中
						if(hospitalList.contains(entry.getValue())){
							err += "[" + entry.getValue() + "]名称重复！";
						}else{
							hospitalList.add(entry.getValue());
						}
					}
				}
			}
			
		}
		return err;
	}

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	@Override
	public String parseJson(ContextInfo cti,Map<String,String> curMap,Map<String,Object> paraMap){
		String month  = paraMap.get("month").toString();
		curMap.put("beginDate", month);
		curMap.remove(SpsTaskServiceImpl.SYS_BS_TYPE);//清除  SYS_BS_TYPE
		Map<String,Object> jsonMap = new HashMap<String,Object>(0);
		for(String key : curMap.keySet()){
			if(!key.equals("row")){
				jsonMap.put(key,curMap.get(key));
			}
		}
		String json = JSON.toJSONString(jsonMap);
		return json;
	}

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	public void parseInsurance(Map<String,String> curMap,Map<String,Object> paraMap){

		BdBstype bstype  = (BdBstype)paraMap.get("bstype");
		List<Map<String, Object>> ratioList = (List<Map<String, Object>>)paraMap.get("ratioList");
		String living_type = curMap.get("insuranceLiveType");
		List<InsuranceTypeDto> insuranceTypeDtoList = new ArrayList<InsuranceTypeDto>(0);
		List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>(0);
		if(bstype.getInsuranceFundType().equals("FUND")){
			tmpList.add(ratioList.get(0));
		}
		if(bstype.getInsuranceFundType().equals("INSURANCE"))
			for(int k =0; k<ratioList.size(); k++){
				Map<String, Object> ration = ratioList.get(k);
				if(ration.get("living_type").toString().equals(living_type)){
					tmpList.add(ration);
				}
			}
		for(int k =0; k<tmpList.size(); k++){
			Map<String, Object> ration = tmpList.get(k);
			InsuranceTypeDto dto = new InsuranceTypeDto();
			dto.setCompanyRatio(new BigDecimal(ration.get("corp_ratio").toString()));
			dto.setInsuranceTypeId(Integer.valueOf(ration.get("insurance_id").toString()));
			dto.setInsuranceName(ration.get("insurance_name").toString());
			dto.setPersonalRatio(new BigDecimal(ration.get("psn_ratio").toString()));
			dto.setRatioId(Integer.valueOf(ration.get("ratio_id").toString()));
			insuranceTypeDtoList.add(dto);
		}
		if(living_type!=null){
			curMap.put("insuranceLiveType",living_type);
		}
		curMap.put("insurance", JSON.toJSONString(insuranceTypeDtoList));
	}

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	public void parseHospital(Map<String,String> curMap,Map<String,Object> paraMap){
		List<HospitalDto> hospitalList = new ArrayList<HospitalDto>();
		Map<String, SpsFixedpointhospital> hosiptalNameMap = (Map<String, SpsFixedpointhospital>)paraMap.get("hosiptalNames");
		Map<String, SpsFixedpointhospital> hosiptalFullNameMap =  (Map<String, SpsFixedpointhospital>)paraMap.get("hosiptalFullNames");
		//遍历
		for (Map.Entry<String, String> entry : curMap.entrySet()) {
			// 校验医院
			if (null != entry.getKey() && entry.getKey().startsWith("hospital") && StringUtils.isNotBlank(entry.getValue())) {
				SpsFixedpointhospital hospital = null;
				if(hosiptalNameMap.containsKey(entry.getValue())){
					hospital = hosiptalNameMap.get(entry.getValue());
				}
				if(hosiptalFullNameMap.containsKey(entry.getValue())){
					hospital = hosiptalFullNameMap.get(entry.getValue());
				}
				if(hospital!=null){
					HospitalDto hospitalDto = new HospitalDto();
					hospitalDto.setName(hospital.getFullName());
					hospitalDto.setCode(hospital.getHospitalCode());
					hospitalDto.setLevel(hospital.getHospitalLevel());
					hospitalDto.setAddress(hospital.getHospitalAddress());
					hospitalDto.setRegion(hospital.getRegion());
					hospitalList.add(hospitalDto);
				}
				curMap.remove(entry.getKey());
			}
		}
		if(!hospitalList.isEmpty()){
			curMap.put("hospital",JSON.toJSONString(hospitalList));
		}
	}


	

}
