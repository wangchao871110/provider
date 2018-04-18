package com.xfs.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.model.InsuranceFundDto;
import com.xfs.business.model.InsuranceFundPeopleType;
import com.xfs.business.model.InsuranceFundRegulation;
import com.xfs.business.model.InsuranceFundType;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.InsuranceFundService;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.RedisKeyConstant;

@Service
public class InsuranceFundServiceImpl implements InsuranceFundService {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(InsuranceFundServiceImpl.class);
    @Autowired
	private BsArearatioService bsArearatioService;
	@Override
	public List<InsuranceFundRegulation> findInsuranceFundRegulation(InsuranceFundDto insuranceFundDto,String[] cityId,String[] cityName) {
		List<InsuranceFundRegulation> insuranceFundRegulationList = new ArrayList<>();
		for (int i = 0; i < cityId.length; i++) {
			InsuranceFundRegulation insuranceFundRegulation = null;
			insuranceFundRegulation = (InsuranceFundRegulation)RedisUtil.get(RedisKeyConstant.INSURANCEFUNDLIST+cityId[i]);
			if(null == insuranceFundRegulation){
				// 根据城市ID获取户口类型数据
				insuranceFundRegulation = new InsuranceFundRegulation();
				insuranceFundRegulation.setAreaName(cityName[i]);
				List<InsuranceFundPeopleType> insuranceFundPeopleTypeList = bsArearatioService.findInsuranceFundPeopleType(Integer.valueOf(cityId[i]));
				for (InsuranceFundPeopleType insuranceFundPeopleType : insuranceFundPeopleTypeList) {
					// 根据城市ID和户口类型获取社保数据
					List<InsuranceFundType> insuranceFundTypeList = bsArearatioService.findInsuranceFundType(Integer.valueOf(cityId[i]),insuranceFundPeopleType.getPeopleTypeCode());
					List<InsuranceFundType> insuranceFundTypeListMeta = new ArrayList<>();
					// 获取默认社保
					List<Integer> ids = new ArrayList<>();
					for (int j = 0;j < insuranceFundTypeList.size();j++) {
						if(insuranceFundTypeListMeta.size() > 0){
							boolean flag = true;
							for (InsuranceFundType insuranceFundTypeMeta : insuranceFundTypeListMeta) {
								if(insuranceFundTypeMeta.getInsuranceName().equals(insuranceFundTypeList.get(j).getInsuranceName())){
									flag = false;
									break;
								}
							}
							if(flag){
								insuranceFundTypeListMeta.add(insuranceFundTypeList.get(j));
								ids.add(j);
							}
						}else{
							insuranceFundTypeListMeta.add(insuranceFundTypeList.get(j));
							ids.add(j);
						}
					}
					// 删除已经存在的ID
					for (int j = 0; j < ids.size(); j++) {
						if(j == 0){
							insuranceFundTypeList.remove(ids.get(j).intValue());
						}else{
							insuranceFundTypeList.remove(ids.get(j).intValue()-j);
						}
					}
					// 获取其他社保类型
					for (InsuranceFundType insuranceFundTypeMeta : insuranceFundTypeListMeta) {
						String otherRatio = "";
						for (InsuranceFundType insuranceFundType : insuranceFundTypeList) {
							if(insuranceFundTypeMeta.getInsuranceName().equals(insuranceFundType.getInsuranceName())){ 
								if(!"0.00".equals(insuranceFundType.getCorpRatio())){
									otherRatio += insuranceFundType.getCorpRatio()+"%";
								}
								if(!"0.00".equals(insuranceFundType.getCorpRatio()) 
										&& !"0.00".equals(insuranceFundType.getCorpAddition())){
									otherRatio += "+";
								}
								if(!"0.00".equals(insuranceFundType.getCorpAddition())){
									otherRatio += insuranceFundType.getCorpAddition()+"元";
								}
								if(!"0.00".equals(insuranceFundType.getCorpRatio()) 
										&& "0.00".equals(insuranceFundType.getPsnRatio()) 
										&& "0.00".equals(insuranceFundType.getPsnAddition())){
									otherRatio += "（单位）";
								}
								if((!"0.00".equals(insuranceFundType.getCorpRatio()) 
										|| !"0.00".equals(insuranceFundType.getCorpAddition())) 
										&& !"0.00".equals(insuranceFundType.getPsnRatio()) 
										|| !"0.00".equals(insuranceFundType.getPsnAddition())){
									otherRatio += " | ";
								}
								if(!"0.00".equals(insuranceFundType.getPsnRatio())){
									otherRatio += insuranceFundType.getPsnRatio()+"%";
								}
								if(!"0.00".equals(insuranceFundType.getPsnRatio()) 
										&& !"0.00".equals(insuranceFundType.getPsnAddition())){
									otherRatio += "+";
								}
								if(!"0.00".equals(insuranceFundType.getPsnAddition())){
									otherRatio += insuranceFundType.getPsnAddition()+"元";
								}
								if((!"0.00".equals(insuranceFundType.getPsnRatio()) 
										&& "0.00".equals(insuranceFundType.getCorpRatio()) 
										&& "0.00".equals(insuranceFundType.getCorpAddition()))){
									otherRatio += "（个人）";
								}
								otherRatio += ",";
							}
						}
						insuranceFundTypeMeta.setOtherRatio(otherRatio.length()>0?otherRatio.substring(0, otherRatio.length()-1):null);
					}
					insuranceFundPeopleType.setInsuranceFundType(insuranceFundTypeListMeta);
				}
				insuranceFundRegulation.setInsuranceFundPeopleType(insuranceFundPeopleTypeList);
				RedisUtil.set(RedisKeyConstant.INSURANCEFUNDLIST+cityId[i], insuranceFundRegulation, 60*60*24);
			}
			RedisUtil.delete(RedisKeyConstant.INSURANCEFUNDLIST+cityId[i]);
			insuranceFundRegulationList.add(insuranceFundRegulation);
		}
		return insuranceFundRegulationList;
	}
}
