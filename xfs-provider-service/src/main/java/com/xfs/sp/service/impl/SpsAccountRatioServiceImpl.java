package com.xfs.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.acc.dto.AreaSocialRuleVo;
import com.xfs.acc.dto.InsuranceLivingTypeDetailsVo;
import com.xfs.acc.dto.InsuranceLivingTypeVo;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpsAccountRatioDao;
import com.xfs.sp.model.SpsAccountRatio;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsSchemeService;

@Service
public class SpsAccountRatioServiceImpl implements SpsAccountRatioService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsAccountRatioServiceImpl.class);
	
	@Autowired
	private SpsAccountRatioDao spsAccountRatioDao;
	@Autowired
	private SysDictitemService  sysDictitemService;
	@Autowired
	private SpsSchemeService  spsSchemeService;
	@Autowired
	private BsAreaService bsAreaService;
	
	
	public int save(ContextInfo cti, SpsAccountRatio vo ){
		return spsAccountRatioDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsAccountRatio vo ){
		return spsAccountRatioDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsAccountRatio vo ){
		return spsAccountRatioDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsAccountRatio vo ){
		return spsAccountRatioDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsAccountRatio vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsAccountRatioDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsAccountRatio> datas = spsAccountRatioDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsAccountRatio> findAll(SpsAccountRatio vo){
		
		List<SpsAccountRatio> datas = spsAccountRatioDao.freeFind(vo);
		return datas;
		
	}


	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:08:03

	public List<Map<String, Object>> findRatiosByAccountId(Map<String,Object> paraMap){
		return spsAccountRatioDao.findRatiosByAccountId(paraMap);
	}

	public List<SpsAccountRatio> findAllList(SpsAccountRatio vo){

		List<SpsAccountRatio> datas = spsAccountRatioDao.freeFindList(vo);
		return datas;

	}
	/**
	 * 获取社保规则
	 *  @param cti
	 *  @param areaId
	 *  @param schemeId
	 *  @return 
	 *  @createDate  	: 2017年4月5日 上午11:24:52
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月5日 上午11:24:52
	 *  @updateAuthor  	:
	 */
	@Override
	public AreaSocialRuleVo findSocialRule(ContextInfo cti, Integer areaId, Integer schemeId) {
        AreaSocialRuleVo areaSocialRuleVo = new AreaSocialRuleVo();
		// 获取户口性质
		Map<String,Object> paraMap = new HashMap<String,Object>();
        BsArea bsArea = new BsArea();
		bsArea.setAreaId(areaId);
		bsArea = bsAreaService.findByPK(bsArea);
		paraMap.put("area_id", bsArea.getBelongCity());
        List<Map<String, Object>> liveTypeList = sysDictitemService.findLiveTypeByAreaId(paraMap);
        
        // 默认社保大库信息
        List<Map<String, Object>> insAccountRatioList = new ArrayList<>();
        // 默认公积金大库信息
        List<Map<String, Object>> fundAccountRatioList = new ArrayList<>();
        SpsScheme scheme = new SpsScheme();
        if(null != schemeId && !"".equals(schemeId)){
        	// 根据方案ID获取方案
    		scheme.setSchemeId(schemeId);
    		scheme = spsSchemeService.findByPK(scheme);
         	SpsAccountRatio vo = new SpsAccountRatio();
         	// 根据大库ID获取公积金大库信息
         	vo = new SpsAccountRatio();
         	vo.setAccId(scheme.getFundAccountId());
         	fundAccountRatioList = spsAccountRatioDao.findAccountRatioList(vo);
        }
        
        //查询社保比例
        List<InsuranceLivingTypeVo> insuranceLivingTypeVos = new ArrayList<>();
        // 循环户口性质
        for(Map<String, Object> live :liveTypeList){
        	InsuranceLivingTypeVo insuranceLivingTypeVo = new InsuranceLivingTypeVo();
        	// 设置户口性质代码和名称
            insuranceLivingTypeVo.setPeopleTypeCode(live.get("code").toString());
            insuranceLivingTypeVo.setPeopleTypeName(live.get("name").toString());
            // 根据城市ID和户口性质查询比例
            paraMap.put("insFundType", "INSURANCE");
            paraMap.put("livingType", live.get("code").toString());
            // 获取所有比例类型
            List<InsuranceLivingTypeDetailsVo> insuranceLivingTypeDetailsVoList = spsAccountRatioDao.findRatiosByAreaIdAndLive(paraMap);
            // 默认险种比例数组
            List<InsuranceLivingTypeDetailsVo> insuranceLivingTypeDetailsVos = new ArrayList<>();
            
            // 根据大库ID获取社保大库信息
            if(null != scheme.getInsuranceAccountId() && !"".equals(scheme.getInsuranceAccountId())){
            	SpsAccountRatio vo = new SpsAccountRatio();
             	vo.setAccId(scheme.getInsuranceAccountId());
             	vo.setLivingType(live.get("code").toString());
             	insAccountRatioList = spsAccountRatioDao.findAccountRatioList(vo);
            }
            // 获取默认险种比例
            for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetailsVo : insuranceLivingTypeDetailsVoList) {
            	InsuranceLivingTypeDetailsVo insurance = this.copyInsuranceLivingTypeDetailsVo(insuranceLivingTypeDetailsVo);
            	// 如果存在默认比例
            	if(insAccountRatioList.size() > 0){
            		insurance.setIsDefault("N");
            		Integer insuranceId = insurance.getInsuranceId();
            		// 设置默认比例
            		for (Map<String, Object> spsAccountRatio : insAccountRatioList) {
            			// 如果险种相等
						if(insuranceId.equals(Integer.parseInt(spsAccountRatio.get("insurance_id").toString())) 
								&& "Y".equals(spsAccountRatio.get("isdefault"))){
							for (int i=0;i<insuranceLivingTypeDetailsVoList.size();i++) {
								if(insuranceLivingTypeDetailsVoList.get(i).getRatioId().equals(Integer.parseInt(spsAccountRatio.get("ratio_id").toString()))){
									insurance = this.copyInsuranceLivingTypeDetailsVo(insuranceLivingTypeDetailsVoList.get(i));
									break;
								}
							}
							insurance.setIsDefault("Y");
						}
					}
            	}
            	// 当前险种是否已经存在
        		boolean flag = true;
    			for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetailsVo2 : insuranceLivingTypeDetailsVos) {
					if(insuranceLivingTypeDetailsVo2.getInsuranceId().equals(insurance.getInsuranceId())){
						flag = false;
						break;
					}
				}
    			if(flag){
    				insuranceLivingTypeDetailsVos.add(insurance);
    			}
			}
            // 根据默认险种获取其他险种比例
            for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetailsVo : insuranceLivingTypeDetailsVos) {
            	List<InsuranceLivingTypeDetailsVo> otherRatio = new ArrayList<>();
            	for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetails : insuranceLivingTypeDetailsVoList) {
            		InsuranceLivingTypeDetailsVo insurance = this.copyInsuranceLivingTypeDetailsVo(insuranceLivingTypeDetails);
     				if(insuranceLivingTypeDetailsVo.getInsuranceId().equals(insurance.getInsuranceId())){
     					insurance.setIsDefault("N");
     					if(insuranceLivingTypeDetailsVo.getRatioId().equals(insurance.getRatioId())){
     						insurance.setIsDefault("Y");
     					}
     					otherRatio.add(insurance);
     					List<Map<String, Object>> useLivingType = new ArrayList<>();
     					// 适用户口性质
     					for(Map<String, Object> l :liveTypeList){
     						Map<String, Object> map = new HashMap<>();
     						map.put("code", l.get("code"));
     						map.put("name", l.get("name"));
     						map.put("isUse", "N");
     						map.put("isDefault", "N");
     						// 是否可用
     						String[] liveType = insurance.getLiveTypes().split(",");
     						for (int i = 0; i < liveType.length; i++) {
     							if(liveType[i].equals(l.get("code").toString())){
     								map.put("isUse", "Y");
     								break;
         						}
							}
 							if(l.get("code").toString().equals((live.get("code").toString()))){
 								map.put("isDefault", "Y");
 							}
         					useLivingType.add(map);
     					}
     					insurance.setUseLivingType(useLivingType);
     				}
            	}
            	if(otherRatio.size() > 1){
            		insuranceLivingTypeDetailsVo.setOtherRatio(otherRatio);
            	}
			}
            insuranceLivingTypeVo.setInsuranceLivingType(insuranceLivingTypeDetailsVos);
            insuranceLivingTypeVos.add(insuranceLivingTypeVo);
        }
        areaSocialRuleVo.setInsuranceLivingType(insuranceLivingTypeVos);
        
        // 获取公积金比例
        paraMap = new HashMap<String,Object>();
        paraMap.put("area_id", areaId);
        paraMap.put("insFundType", "FUND");
        List<InsuranceLivingTypeDetailsVo> fundDetailsVoList = spsAccountRatioDao.findRatiosByAreaIdAndLive(paraMap);
        List<InsuranceLivingTypeDetailsVo> fundType = new ArrayList<>();
        // 获取默认公积金比例
        for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetailsVo : fundDetailsVoList) {
        	InsuranceLivingTypeDetailsVo fund = this.copyInsuranceLivingTypeDetailsVo(insuranceLivingTypeDetailsVo);
        	if(fundAccountRatioList.size() > 0){
        		fund.setIsDefault("N");
        		Integer insuranceId = fund.getInsuranceId();
        		for (Map<String, Object> spsAccountRatio : fundAccountRatioList) {
        			if(insuranceId.equals(Integer.parseInt(spsAccountRatio.get("insurance_id").toString())) && "Y".equals(spsAccountRatio.get("isdefault"))){
        				
        				for (int i=0;i<fundDetailsVoList.size();i++) {
							if(fundDetailsVoList.get(i).getRatioId().equals(Integer.parseInt(spsAccountRatio.get("ratio_id").toString()))){
								fund = this.copyInsuranceLivingTypeDetailsVo(fundDetailsVoList.get(i));
								break;
							}
						}
						fund.setIsDefault("Y");
					}
				}
        	}
        	boolean flag = true;
    		for (InsuranceLivingTypeDetailsVo insuranceLivingType : fundType) {
				if(insuranceLivingType.getInsuranceId().equals(fund.getInsuranceId())){
					flag = false;
					break;
				}
			}
        	if(flag){
        		fundType.add(fund);
        	}
		}
        // 获取其他公积金比例
        for (InsuranceLivingTypeDetailsVo insuranceLivingType : fundType) {
        	List<InsuranceLivingTypeDetailsVo> otherRatio = new ArrayList<>();
        	for (InsuranceLivingTypeDetailsVo insuranceLivingTypeDetailsVo : fundDetailsVoList) {
        		InsuranceLivingTypeDetailsVo fund = this.copyInsuranceLivingTypeDetailsVo(insuranceLivingTypeDetailsVo);
				if(insuranceLivingType.getInsuranceId().equals(fund.getInsuranceId())){
					if(insuranceLivingType.getRatioId().equals(fund.getRatioId())){
						fund.setIsDefault("Y");
					}else{
						fund.setIsDefault("N");
					}
					fund.setOtherRatio(null);
					otherRatio.add(fund);
				}
				if(otherRatio.size() > 1){
					insuranceLivingType.setOtherRatio(otherRatio);
				}
        	 }
		}
        
        areaSocialRuleVo.setFundType(fundType);
        areaSocialRuleVo.setAreaId(areaId);
        areaSocialRuleVo.setSchemeId(schemeId);
        return areaSocialRuleVo;
	}
	
	/**
	 * 生成新的数据对象
	 *  @param insurance
	 *  @return 
	 *	@return 			: InsuranceLivingTypeDetailsVo 
	 *  @createDate  	: 2017年4月5日 下午5:16:07
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月5日 下午5:16:07
	 *  @updateAuthor  :
	 */
	private InsuranceLivingTypeDetailsVo copyInsuranceLivingTypeDetailsVo( InsuranceLivingTypeDetailsVo insurance) {
		InsuranceLivingTypeDetailsVo insuranceLivingTypeDetails = new InsuranceLivingTypeDetailsVo();
		insuranceLivingTypeDetails.setCorpAddition(insurance.getCorpAddition());
		insuranceLivingTypeDetails.setCorpRatio(insurance.getCorpRatio());
		insuranceLivingTypeDetails.setInsuranceId(insurance.getInsuranceId());
		insuranceLivingTypeDetails.setInsuranceName(insurance.getInsuranceName());
		insuranceLivingTypeDetails.setIsDefault(insurance.getIsDefault());
		insuranceLivingTypeDetails.setLiveTypes(insurance.getLiveTypes());
		insuranceLivingTypeDetails.setLowerNum(insurance.getLowerNum());
		insuranceLivingTypeDetails.setOtherRatio(insurance.getOtherRatio());
		insuranceLivingTypeDetails.setPsnAddition(insurance.getPsnAddition());
		insuranceLivingTypeDetails.setPsnRatio(insurance.getPsnRatio());
		insuranceLivingTypeDetails.setRatioId(insurance.getRatioId());
		insuranceLivingTypeDetails.setUpperNum(insurance.getUpperNum());
		insuranceLivingTypeDetails.setUseLivingType(insurance.getUseLivingType());
		insuranceLivingTypeDetails.setBillingCycle(insurance.getBillingCycle());
		return insuranceLivingTypeDetails;
	}
	
	/**
	 * 根据大库ID删除比例
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年4月9日 下午4:51:19
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月9日 下午4:51:19
	 *  @updateAuthor  	:
	 */
	@Override
	public int removeByAccId(ContextInfo cti, SpsAccountRatio vo) {
		return spsAccountRatioDao.removeByAccId(cti, vo);
	}

	/**
	 * 更新库的默认比例
	 * @param cti
	 * @param accId
	 * @param insuranceId
	 * @param areaId
	 * @param corpRatio
	 * @param empRatio
	 * @return
	 */
	public int updateAccountDefaultRatio(ContextInfo cti, Integer accId,Integer insuranceId,Integer areaId,String corpRatio,String empRatio){
		return spsAccountRatioDao.updateAccountDefaultRatio(cti,accId,insuranceId,areaId,corpRatio,empRatio);
	}
}
