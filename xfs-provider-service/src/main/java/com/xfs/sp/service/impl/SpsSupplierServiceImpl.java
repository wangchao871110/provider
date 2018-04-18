package com.xfs.sp.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.acc.dto.FundRatio;
import com.xfs.acc.dto.InsuranceRatio;
import com.xfs.acc.dto.Ratio;
import com.xfs.acc.dto.SpsSchemeDto;
import com.xfs.bill.dao.SpsBillTempBusinessFieldDao;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.bill.service.SpsBillTemplateService;
import com.xfs.business.model.BdBstype;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.sp.dao.SpsAccountDao;
import com.xfs.sp.dao.SpsAccountRatioDao;
import com.xfs.sp.dao.SpsSupplierDao;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsAccountRatio;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSupplier;
import com.xfs.sp.service.SpCmRelationService;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.sp.service.SpsSupplierService;
import com.xfs.user.model.SysFunctionData;
import com.xfs.user.service.SysFunctionDataService;

@Service
public class SpsSupplierServiceImpl implements SpsSupplierService,IRedisKeys {

	private static final Logger log = Logger.getLogger(SpsSupplierServiceImpl.class);
	
	@Autowired
	private SpsSupplierDao spsSupplierDao;
	@Autowired
	private SpsAccountDao spsAccountDao;
	@Autowired
	private SpsAccountRatioDao spsAccountRatioDao;
	@Autowired
	private SpsSchemeService  spsSchemeService;
	@Autowired
	private SpServiceService  spServiceService;
	@Autowired
	private SpCmRelationService  spCmRelationService;
	@Autowired
	private SysFunctionDataService  sysFunctionDataService;
	@Autowired
	private SpsBillTemplateService  spsBillTemplateService;
	@Autowired
	private SpsBillTempBusinessFieldDao  spsBillTempBusinessFieldDao;
	@Autowired
	private CmEmployeeConfigService  cmEmployeeConfigService;
	
	
	
	
	public int save(ContextInfo cti, SpsSupplier vo ){
		return spsSupplierDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, SpsSupplier vo ){
		return spsSupplierDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, SpsSupplier vo ){
		return spsSupplierDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, SpsSupplier vo ){
		return spsSupplierDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi,SpsSupplier vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSupplierDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsSupplier> datas = spsSupplierDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsSupplier> findAll(SpsSupplier vo){
		
		List<SpsSupplier> datas = spsSupplierDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/07/11 16:01:43
	
	/**
	 * 查询服务商信息 分页
	 *
	 * @author lifq
	 *
	 * 2017年7月13日  下午3:01:45
	 */
	public PageModel findSpServicePage(PageInfo pi, Map<String,Object> paraMap){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSupplierDao.countFindSpServicePage(paraMap);
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsSupplierDao.findSpServicePage(paraMap, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	/**
	 * 查询 该企业下 所有方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月14日  下午4:29:15
	 */
	public List<Map<String,Object>> findAllScheme(Map<String,Object> paraMap){
		return spsSupplierDao.findAllScheme(paraMap);
	}
	/**
	 * 查询 企业下方案中 城市信息
	 *
	 * @author lifq
	 *
	 * 2017年7月17日  上午11:13:27
	 */
	public List<Map<String,Object>> findAllSchemeCity(Map<String,Object> paraMap){
		return spsSupplierDao.findAllSchemeCity(paraMap);
	}
	/**
	 * 保存服务商&方案
	 *
	 * @author lifq
	 *
	 * 2017年7月19日  上午11:37:38
	 */
	public Result saveSpAndScheme(ContextInfo cti,SpService spService,List<SpsSchemeDto> schemeDtos,Integer billDate,Integer endDate,BigDecimal price){
		Result result = Result.createResult().setSuccess(false);
		/** 开始处理保存逻辑  */
		//校验方案  服务商名称+方案名称 唯一,20170731与国玉讨论，过滤 cpId+服务商名称 不能重复
		Map<String,Object> spParaMap = new HashMap<String,Object>();
		spParaMap.put("cpId", cti.getOrgId());
		spParaMap.put("spName", spService.getSpName());
		if(null!=spService.getSpId()){
			spParaMap.put("spId", spService.getSpId());
		}
		List<Map<String,Object>> spList = spServiceService.findSpByName(spParaMap);
		if(null!=spList && spList.size()>0){
			result.setError("服务商名称重复，请确认！");
			throw new BusinessException("服务商名称重复，请确认！");
		}
		//保存服务商
		if(null==spService.getSpId()){
			spServiceService.insert(cti, spService);
			SpCmRelation spCmVo = new SpCmRelation();
			spCmVo.setSpId(spService.getSpId());
			spCmVo.setCpId(cti.getOrgId());
			spCmVo.setDr(0);
			spCmRelationService.insert(cti, spCmVo);
			//创建默认账单模板
			addDefaultTemp(cti, spService);
			//添加服务商权限
			addSpAuthority(cti, spService);
			RedisUtil.hput(USER_AUTH_MODFIY_TAG, String.valueOf(cti.getUserId()), "Y");
		}else{
			spServiceService.update(cti, spService);
		}
		//保存 方案
		for(SpsSchemeDto schemeDto:schemeDtos){
			SpsScheme spsScheme = new SpsScheme();
			if(null!=schemeDto.getSchemeId()){
				spsScheme.setSchemeId(schemeDto.getSchemeId());
			}
			spsScheme.setSpId(spService.getSpId());
			spsScheme.setCpId(cti.getOrgId());
			spsScheme.setName(schemeDto.getSchemeName());
			spsScheme.setBillDate(billDate);
			spsScheme.setEndDate(endDate);
			spsScheme.setPrice(price);
			spsScheme.setAreaId(schemeDto.getAreaId());
			spsScheme.setMemo(schemeDto.getMemo());//供应商名称
			spsScheme.setInsuranceAccountId(schemeDto.getInsuranceAccountId());
			spsScheme.setFundAccountId(schemeDto.getFundAccountId());
			
			//处理 社保库
			if(null==spsScheme.getInsuranceAccountId()){
				SpsAccount insurance = new SpsAccount();
				insurance.setSpId(spsScheme.getSpId());
				insurance.setCpId(spsScheme.getCpId());
				insurance.setInsuranceFundType("INSURANCE");
				insurance.setAccType("COMMON");
				insurance.setAreaId(spsScheme.getAreaId());
				insurance.setState("ON");
				insurance.setDr(0);
				// 社保             为空就新增，处理库时候 才有accId
				insurance.setAccId(spsScheme.getInsuranceAccountId());
				spsAccountDao.save(cti, insurance);
				spsScheme.setInsuranceAccountId(insurance.getAccId());
			}
			//处理 公积金库
			if(null == spsScheme.getFundAccountId()){
				SpsAccount fund = new SpsAccount();
				fund.setSpId(spsScheme.getSpId());
				fund.setCpId(spsScheme.getCpId());
				fund.setInsuranceFundType("FUND");
				fund.setAccType("COMMON");
				fund.setAreaId(spsScheme.getAreaId());
				fund.setState("ON");
				fund.setDr(0);
				// 公积金
				fund.setAccId(spsScheme.getFundAccountId());
				spsAccountDao.save(cti, fund);
				spsScheme.setFundAccountId(fund.getAccId());
			}
			
			//校验方案  服务商名称+方案名称 唯一,20170731与国玉讨论，过滤 cpId+服务商名称 不能重复
			Map<String,Object> paraMap = new HashMap<String,Object>();
//			paraMap.put("spId", spService.getSpId());
			paraMap.put("cpId", cti.getOrgId());
			paraMap.put("name", spsScheme.getName());
			paraMap.put("schemeId", spsScheme.getSchemeId());
			List<Map<String,Object>> list = spsSchemeService.findSchemeByName(paraMap);
			if(null!=list && list.size()>0){
				result.setError("方案名称重复，请确认！");
				throw new BusinessException("方案名称重复，请确认！");
			}
			//校验通过后 判断是否  添加供应商
			if(StringUtils.isNotBlank(spsScheme.getMemo())){
				SpsSupplier supplierVo = new SpsSupplier();
				supplierVo.setSupplierName(spsScheme.getMemo());
				supplierVo.setSpId(spService.getSpId());
				supplierVo.setType("SUPPLIIER");
				supplierVo.setDr(0);
				List<SpsSupplier> supplierList = spsSupplierDao.freeFind(supplierVo);
				if(null==supplierList || supplierList.size()<1){
					//没有就新增
					spsSupplierDao.insert(cti, supplierVo);
				}
			}
			
			//新增方案
			if(null == spsScheme.getSchemeId()){
				spsScheme.setSchemeType("DIY");
				spsScheme.setState("USE");
				spsScheme.setInsuranceType("COMMON");
				spsScheme.setFundType("COMMON");
				spsScheme.setBillType("ADVANCE_CHARGE");
				spsScheme.setDr(0);
				spsSchemeService.insert(cti, spsScheme);
				//新增时候 处理权限
				SysFunctionData functionDataVo = new SysFunctionData();
				functionDataVo.setDataId(spService.getSpId());
				functionDataVo.setUserType("CUSTOMER");
				functionDataVo.setDataType("CM_CORP_SP");
				functionDataVo.setDr(0);
				List<SysFunctionData> functionDataList = sysFunctionDataService.findAll(functionDataVo);
				if(null!=functionDataList && functionDataList.size()>0){
					//有权限记录，则 新增当前方案的权限到每个人上
					for(SysFunctionData sysFunctionData:functionDataList ){
						SysFunctionData curVo = new SysFunctionData();
						curVo.setUserId(sysFunctionData.getUserId());
						curVo.setUserType("CUSTOMER");
						curVo.setDataType("CM_CORP");
						curVo.setIsAll("N");
						curVo.setDr(0);
						curVo.setDataId(spsScheme.getSchemeId());
						sysFunctionDataService.insert(cti, curVo);
					}
					
				}
				//权限
				RedisUtil.hput(USER_AUTH_MODFIY_TAG, String.valueOf(cti.getUserId()), "Y");
				// modify by lifq 20170824 员工端配置，判断 城市有没有，没有就新增
				CmEmployeeConfig configVo = new CmEmployeeConfig();
				configVo.setCpId(cti.getOrgId());
				configVo.setAreaId(spsScheme.getAreaId());
				configVo.setDr(0);
				List<CmEmployeeConfig> configList = cmEmployeeConfigService.findAll(configVo);
				if(null == configList || configList.size()<1){
					log.info("员工端配置不能存，开始添加！");
					CmEmployeeConfig cmEmployeeConfig = new CmEmployeeConfig();
					cmEmployeeConfig.setCpId(cti.getOrgId());
					cmEmployeeConfig.setAreaId(spsScheme.getAreaId());
					cmEmployeeConfig.setIsEmp(1);
					cmEmployeeConfig.setIsInsFund(1);
					cmEmployeeConfig.setIsEmpService(1);
					// 获取在线服务
			    	List<BdBstype> onLineService = cmEmployeeConfigService.findEmpServiceByAreaId(spsScheme.getAreaId(),0);
			    	String[] onLineBstype = new String[onLineService.size()];
			    	for (int i=0;i<onLineService.size();i++) {
			    		onLineBstype[i] = onLineService.get(i).getBstypeId().toString();
					}
			    	cmEmployeeConfig.setOnLineService(StringUtils.join(onLineBstype, ","));
					cmEmployeeConfig.setIsOnLineService(1);
					// 获取员工服务
					List<BdBstype> empService = cmEmployeeConfigService.findEmpServiceByAreaId(spsScheme.getAreaId(),1);
					String[] empBstype = new String[empService.size()];
					for (int i = 0; i < empService.size(); i++) {
						empBstype[i] = empService.get(i).getBstypeId().toString();
					}
					cmEmployeeConfig.setEmpService(StringUtils.join(empBstype, ","));
					cmEmployeeConfig.setIsAddService(1);
					// 获取增值服务
					List<BdBstype> addService = cmEmployeeConfigService.findAddServiceByAreaId(spsScheme.getAreaId());
					String[] addBstype = new String[addService.size()];
					for (int i = 0; i < addService.size(); i++) {
						addBstype[i] = addService.get(i).getBstypeId().toString();
					}
					cmEmployeeConfig.setAddService(StringUtils.join(addBstype, ","));
					cmEmployeeConfigService.saveEmpConfig(cti, cmEmployeeConfig);
				}else{
					log.info("员工端配置已存在！");
				}
				
			}else{
				spsSchemeService.update(cti, spsScheme);
			}
			//处理所有方案的 截止日期、账单日 lifq 20170802，必须添加，可能单独保存一个方案，需要修改其他所有方案
			SpsScheme schemeVo = new SpsScheme();
			schemeVo.setSpId(spsScheme.getSpId());
			schemeVo.setCpId(spsScheme.getCpId());
			schemeVo.setDr(0);
			List<SpsScheme> schemeList = spsSchemeService.findAll(schemeVo);
			if(null!=schemeList && schemeList.size()>0){
				for(SpsScheme schemeObj :schemeList){
					schemeObj.setBillDate(spsScheme.getBillDate());
					schemeObj.setEndDate(spsScheme.getEndDate());
					schemeObj.setPrice(spsScheme.getPrice());
					spsSchemeService.update(cti, schemeObj);
				}
			}
			// 删除社保所有比例
			SpsAccountRatio vo = new SpsAccountRatio();
			vo.setAccId(spsScheme.getInsuranceAccountId());
			spsAccountRatioDao.removeByAccId(cti, vo);
			// 删除公积金所有比例
			vo = new SpsAccountRatio();
			vo.setAccId(spsScheme.getFundAccountId());
			spsAccountRatioDao.removeByAccId(cti, vo);
			// 插入社保比例
			if(null!=schemeDto.getInsuranceRatio() && schemeDto.getInsuranceRatio().size()>0){
				for(InsuranceRatio insuranceRatioVo :  schemeDto.getInsuranceRatio()){
					for(Ratio ratio :  insuranceRatioVo.getRatio()){
						SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
						spsAccountRatio.setAccId(spsScheme.getInsuranceAccountId());
						spsAccountRatio.setRatioId(ratio.getRatioId());
						spsAccountRatio.setLivingType(insuranceRatioVo.getInsuranceLivingType());
						spsAccountRatio.setIsdefault("Y");
						spsAccountRatio.setDr(0);
						spsAccountRatioDao.save(cti, spsAccountRatio);
					}
				}
			}
			
			// 插入公积金比例
			if(null!=schemeDto.getFundRatio() && schemeDto.getFundRatio().size()>0){
				for(FundRatio fundRatioVo :  schemeDto.getFundRatio()){
					SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
					spsAccountRatio.setAccId(spsScheme.getFundAccountId());
					spsAccountRatio.setRatioId(fundRatioVo.getRatioId());
					spsAccountRatio.setIsdefault("Y");
					spsAccountRatio.setDr(0);
					spsAccountRatioDao.save(cti, spsAccountRatio);
				}
			}
		}
		result.setDataValue("spService", spService);
		result.setSuccess(true);
		return result;
	}
	/**
	 * 删除 服务商
	 *
	 * @author lifq
	 *
	 * 2017年7月25日  下午4:33:57
	 */
	public Result delSpservice(ContextInfo cti,String spIds){
		Result result = Result.createResult().setSuccess(false);
		
		List<Integer> spidsList = new ArrayList<Integer>();
		String[] spidsStr = spIds.split(",");
		for(int k=0;k<spidsStr.length;k++){
			String innerStr = spidsStr[k];
			if(StringUtils.isNotBlank(innerStr)){
				spidsList.add(Integer.valueOf(innerStr));
			}
		}
		if(spidsList.size()<1){
			result.setError("spIds参数不合法！");
			return result;
		}
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("cpId",cti.getOrgId());
		paraMap.put("spIds",spIds);
		//校验方案信息，校验通过则 删除方案信息
		boolean isCheck = false;
		List<Map<String, Object>>  schemeList = spsSupplierDao.findAllScheme(paraMap);
		if(null!=schemeList && schemeList.size()>0){
			for(int i=0;i<schemeList.size();i++){
				Map<String, Object> schemeMap = schemeList.get(i);
				Integer personNum = null==schemeMap.get("personNum")?0:Integer.valueOf(schemeMap.get("personNum").toString());
				if(personNum>0){
					isCheck = true;
					result.setError("服务商【"+schemeMap.get("sp_name").toString()+"】下方案还有人员，不能删除！");
					break;
				} 
			}
			if(isCheck){//校验不通过
				return result;
			}
			//删除 方案信息
			for(int i=0;i<schemeList.size();i++){
				Map<String, Object> schemeMap = schemeList.get(i);
				Integer schemeId = Integer.valueOf(schemeMap.get("scheme_id").toString());
				SpsScheme vo = new SpsScheme();
				vo.setSchemeId(schemeId);
				SpsScheme schemeObj = spsSchemeService.findByPK(vo);
				spsSchemeService.remove(cti, schemeObj);
				//删除方案 相关信息
				delSchemeAtta(cti, schemeObj);
			}
		}
		//删除服务商
		if(null!=spidsList && spidsList.size()>0){
			for(Integer spId:spidsList){
				SpCmRelation relationVo = new SpCmRelation();
				relationVo.setSpId(spId);
				relationVo.setCpId(cti.getOrgId());
				relationVo.setDr(0);
				List<SpCmRelation> relationList = spCmRelationService.findAll(relationVo);
				if(null!=relationList && relationList.size()>0){
					spCmRelationService.remove(cti, relationList.get(0));//删除 sp_cm_relation
					SpService spVo = new SpService();
					spVo.setSpId(spId);
					int res = spServiceService.remove(cti, spVo);
					if(res>0){
						//删除 服务商权限
						SysFunctionData functionDataVo = new SysFunctionData();
						functionDataVo.setDataId(spId);//服务商Id
						functionDataVo.setUserType("CUSTOMER");
						functionDataVo.setDataType("CM_CORP_SP");
						functionDataVo.setDr(0);
						List<SysFunctionData> functionDataList = sysFunctionDataService.findAll(functionDataVo);
						if(null!=functionDataList && functionDataList.size()>0){
							for(SysFunctionData curVo:functionDataList){
								sysFunctionDataService.remove(cti, curVo);
							}
						}
						result.setSuccess(true);
					}
				}
			}
		}
		return result;
	}
	/**
	 * 删除 方案
	 *
	 * @author lifq
	 *
	 * 2017年7月26日  下午3:53:55
	 */
	public Result delScheme(ContextInfo cti,Integer schemeId){
		Result result = Result.createResult().setSuccess(false);
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("cpId",cti.getOrgId());
		paraMap.put("schemeId",schemeId);
		//查询方案信息
		List<Map<String, Object>>  schemeList = spsSupplierDao.findAllScheme(paraMap);
		if(null!=schemeList && schemeList.size()>0){
			Map<String, Object> schemeMap = schemeList.get(0);
			Integer personNum = null==schemeMap.get("personNum")?0:Integer.valueOf(schemeMap.get("personNum").toString());
			if(personNum>0){
				result.setError("该方案还有人员，不能删除！");
				return result;
			}
			//判断 服务商是不是只有一个方案，只要一个方案时候不能删除
			Integer spId = (Integer)schemeMap.get("sp_id");
			SpsScheme checkVo = new SpsScheme();
			checkVo.setSpId(spId);
			checkVo.setDr(0);
			List<SpsScheme> checkList = spsSchemeService.findAll(checkVo);
			if(null!=checkList && checkList.size()==1){
				result.setError("该服务商下只有一个方案，不能删除！");
				return result;
			}
			SpsScheme vo = new SpsScheme();
			vo.setSchemeId(schemeId);
			SpsScheme schemeObj = spsSchemeService.findByPK(vo);
			int res = spsSchemeService.remove(cti, schemeObj);
			if(res>0){
				//删除方案 相关信息
				delSchemeAtta(cti, schemeObj);
				result.setSuccess(true);
			}
		}else{
			result.setError("该方案不存在！");
		}
		return result;
	}
	/**
	 * 删除方案 相关信息（内部调用）
	 *
	 * @author lifq
	 *
	 * 2017年7月26日  下午4:44:39
	 */
	private void delSchemeAtta(ContextInfo cti, SpsScheme schemeObj) {
		//删除方案后 删除权限
		SysFunctionData functionDataVo = new SysFunctionData();
		functionDataVo.setDataId(schemeObj.getSchemeId());
		functionDataVo.setUserType("CUSTOMER");
		functionDataVo.setDataType("CM_CORP");
		functionDataVo.setIsAll("N");
		functionDataVo.setDr(0);
		List<SysFunctionData> functionDataList = sysFunctionDataService.findAll(functionDataVo);
		if(null!=functionDataList && functionDataList.size()>0){
			//有权限记录，则 新增当前方案的权限
			for(SysFunctionData curVo :functionDataList){
				sysFunctionDataService.remove(cti, curVo);
			}
		}
		//删除 社保库、公积金库、比例
		if(null!=schemeObj.getInsuranceAccountId()){
			SpsAccount insurance = new SpsAccount();
			insurance.setAccId(schemeObj.getInsuranceAccountId());
			spsAccountDao.remove(cti, insurance);
			// 删除 所有比例
			SpsAccountRatio vo = new SpsAccountRatio();
			vo.setAccId(schemeObj.getInsuranceAccountId());
			spsAccountRatioDao.removeByAccId(cti, vo);
		}
		if(null!=schemeObj.getFundAccountId()){
			SpsAccount fund = new SpsAccount();
			fund.setAccId(schemeObj.getFundAccountId());
			spsAccountDao.remove(cti, fund);
			// 删除 所有比例
			SpsAccountRatio vo = new SpsAccountRatio();
			vo.setAccId(schemeObj.getFundAccountId());
			spsAccountRatioDao.removeByAccId(cti, vo);
		}
	}
	/**
	 * 创建默认账单模板
	 *
	 * @author lifq
	 *
	 * 2017年7月27日  下午4:19:50
	 */
	 private void addDefaultTemp(ContextInfo cti, SpService sp) {
	        SpsBillTemplate spsBillTemplate = new SpsBillTemplate();
	        spsBillTemplate.setDr(0);
	        spsBillTemplate.setSpId(sp.getSpId());
	        spsBillTemplate.setName("默认模板");
	        spsBillTemplate.setIsEffect("Y");
	        spsBillTemplate.setIsDefault("Y");
	        spsBillTemplateService.insert(cti, spsBillTemplate);
	        spsBillTempBusinessFieldDao.insertBusinessFieldByDicitem(cti, spsBillTemplate);
	 }
	 /**
	  * 添加 服务商权限
	  *
	  * @author lifq
	  *
	  * 2017年8月9日  下午5:29:13
	  */
	 private void addSpAuthority(ContextInfo cti, SpService sp) {
		 if(!"ALL".equals(cti.getAuthority())){
			 log.info("不是超管，新增sp权限给当前用户！");
			 SysFunctionData functionDataVo = new SysFunctionData();
			 functionDataVo.setUserId(cti.getUserId());
			 functionDataVo.setDataId(sp.getSpId());
			 functionDataVo.setUserType("CUSTOMER");
			 functionDataVo.setDataType("CM_CORP_SP");
			 functionDataVo.setIsAll("N");
			 functionDataVo.setDr(0);
			 sysFunctionDataService.insert(cti, functionDataVo);
		 }
	  }
	
}
