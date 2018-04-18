package com.xfs.sp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xfs.acc.dto.*;
import com.xfs.business.enums.BsType;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.business.model.BdBstype;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.Constant;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.sp.dao.SpsAccountDao;
import com.xfs.sp.dao.SpsAccountRatioDao;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsAccountRatio;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsAccountService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.user.model.SysRole;

import javax.servlet.http.HttpServletRequest;

@Service
public class SpsAccountServiceImpl implements SpsAccountService,IRedisKeys {

	private static final Logger log = Logger.getLogger(SpsAccountServiceImpl.class);
	
	@Autowired
	private SpsAccountDao spsAccountDao;
	@Autowired
	private SpsAccountRatioDao spsAccountRatioDao;
	@Autowired
	private SpsSchemeService  spsSchemeService;
	@Autowired
	private CmCorpService cmCorpService;
	@Autowired
	private BsAreaService bsAreaService;
	@Autowired
	private SpsAccountRatioService spsAccountRatioService;
	@Autowired
	private CmEmployeeConfigService cmEmployeeConfigService;

	public int save(ContextInfo cti, SpsAccount vo ){
		return spsAccountDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsAccount vo ){
		return spsAccountDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsAccount vo ){
		return spsAccountDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsAccount vo ){
		return spsAccountDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsAccount vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsAccountDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsAccount> datas = spsAccountDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsAccount> findAll(SpsAccount vo){
		
		List<SpsAccount> datas = spsAccountDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 18:17:38
	
	public PageModel findPageMap(PageInfo pi, SpsAccount vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsAccountDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsAccount> datas = spsAccountDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	
	@Override
	public List<SpsAccount> qrySpsAccountByIds(Integer spid, List<Integer> ids)
	{
		return spsAccountDao.querySpsAccountListByIds(spid, ids);
	}

	/**
	 * findByPK
	 *
	 * @param vo
	 * @return
     */
	public SpsAccount findByPK(SpsAccount vo) {
		return spsAccountDao.findByPK(vo);
	}

	/**
	 * 通过spid查询库列表
	 *
	 * @param vo
	 * @return
     */
	public PageModel findSpsAccountBySpid(PageInfo pi, SpsAccount vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsAccountDao.findSpsAccountCountBySpid(vo);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsAccountDao.findSpsAccountBySpid(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 通过库名称查询 -- 库名称的唯一性
	 *
	 * @param vo
	 * @return
	 */
	public int findSpsAccountCountByName(SpsAccount vo) {
		return spsAccountDao.findSpsAccountCountByName(vo);
	}
	
	
	@Override
	public List<SpsAccount> findAllWithParent(SpsAccount vo){
		List<SpsAccount> datas = spsAccountDao.findAllWithParent(vo);
		return datas;
		
	}
	/**
	 * 保存库
	 *
	 * @author lifq
	 *
	 * 2016年9月27日  上午11:08:18
	 */
	public Result saveAccount(ContextInfo cti, Integer spId,SpsAccount account,String account_ratio[]){
		Result result = Result.createResult().setSuccess(false);
		if(null==account.getAccId()){//新增
			account.setSpId(spId);
	        account.setDr(0);
	        account.setState("ON");//状态：启用
	        int res = spsAccountDao.insert(cti,account);
	        if(res > 0){
	         	//保存 account_ratio
	        	if(null!=account_ratio && account_ratio.length>0){
	        		for(int i=0;i<account_ratio.length;i++){
	        			//1_OUTSIDETOWN_N
	        			String account_ratio_str = account_ratio[i];
	        			String []arr = account_ratio_str.split("_");
	        			if(null!=arr && arr.length>2){
	        				SpsAccountRatio obj = new SpsAccountRatio();
	        				obj.setAccId(account.getAccId());
	        				obj.setRatioId(Integer.valueOf(arr[0]));
	        				obj.setLivingType(arr[1]);
	        				obj.setIsdefault(arr[2]);
	        				spsAccountRatioDao.insert(cti,obj);
	        			}
	        		}
	        	}
	         	result.setDataValue("account", account);
	         	result.setSuccess(true);
	        }
		}else{//更新
			 int res = spsAccountDao.update(cti, account);
		        if(res > 0){
		        	//删除历史account_ratio
		        	SpsAccountRatio vo = new SpsAccountRatio();
		        	vo.setAccId(account.getAccId());
		        	List<SpsAccountRatio> oldRatioList = spsAccountRatioDao.freeFind(vo);
		        	if(null!=oldRatioList && oldRatioList.size()>0){
		        		for(int i=0;i<oldRatioList.size();i++){
		        			spsAccountRatioDao.remove(cti, oldRatioList.get(i));
		        		}
		        	}
		         	//保存 account_ratio
		        	if(null!=account_ratio && account_ratio.length>0){
		        		for(int i=0;i<account_ratio.length;i++){
		        			//1_OUTSIDETOWN_N
		        			String account_ratio_str = account_ratio[i];
		        			String []arr = account_ratio_str.split("_");
		        			if(null!=arr && arr.length>2){
		        				SpsAccountRatio obj = new SpsAccountRatio();
		        				obj.setAccId(account.getAccId());
		        				obj.setRatioId(Integer.valueOf(arr[0]));
		        				obj.setLivingType(arr[1]);
		        				obj.setIsdefault(arr[2]);
		        				spsAccountRatioDao.insert(cti,obj);
		        			}
		        		}
		        	}
		         	result.setDataValue("account", account);
		         	result.setSuccess(true);
		        }
		}
        return result;
	}

	/**
	 * 通过条件查询出spsAccountList(按照GROUP BY area_id,insurance_fund_type分组得出最小的accID的spsAccount)
	 * @return
	 */
	public List<SpsAccount> findSpsAccountListByConditions(SpsAccount spsAccount){
		return spsAccountDao.findSpsAccountListByConditions(spsAccount);
	}

	/**
	 * 根据城市获取自动网上申报信息
	 *  @param areaIds
	 *  @param taskIds
	 *  @return
	 *  @createDate  	: 2017年4月6日 下午2:11:28
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月6日 下午2:11:28
	 *  @updateAuthor  	:
	 */
	@Override
	public List<ApplyMessage> findApplyMessage(ContextInfo cti,CmCorp cmCorp,String[] areaIds,String[] taskIds,String[] schemeIds) {
		List<ApplyMessage> applyMessages = spsAccountDao.queryApplyMessageList(cti,areaIds,taskIds,schemeIds);
		return applyMessages;
	}

	/**
	 * 保存account账号信息
	 * @param cti
	 * @param applyMessage
	 */
	@Override
	public void saveAccountInfo(ContextInfo cti,ApplyMessage applyMessage){
		SpsScheme scheme = null;
		if(null != applyMessage.getSchemeId() && !"".equals(applyMessage.getSchemeId())){
			scheme = new SpsScheme();
			scheme.setSchemeId(applyMessage.getSchemeId());
			scheme.setDr(0);
			scheme = spsSchemeService.findByPK(scheme);
		}
		if(null != scheme){
			SpsAccount insurance = new SpsAccount();
			SpsAccount fund = new SpsAccount();
			insurance.setAreaId(applyMessage.getAreaId());
			insurance.setAutoLogin(applyMessage.getAutoLogin());
			fund.setAreaId(applyMessage.getAreaId());
			fund.setAutoLogin(applyMessage.getAutoLogin());
			/**
			 * 三证合一
			 */
			if("THREE".equals(applyMessage.getUkeyType()) || "INSURANCE,FUND".equals(applyMessage.getUkeyType())){
				insurance.setRegNum(applyMessage.getRegNum());
				insurance.setRegNumpass(applyMessage.getRegNumpass());
				insurance.setRegUsbkeypass(applyMessage.getRegUsbkeypass());
				insurance.setUsbcode(applyMessage.getUsbCode());
				insurance.setContaninerName(applyMessage.getContaninerName());
				insurance.setCorpName(applyMessage.getCorpName());

				fund.setRegNum(applyMessage.getRegNum());
				fund.setRegNumpass(applyMessage.getRegNumpass());
				fund.setUsbcode(applyMessage.getUsbCode());
				fund.setRegUsbkeypass(applyMessage.getRegUsbkeypass());
				fund.setContaninerName(applyMessage.getContaninerName());
				fund.setCorpName(applyMessage.getCorpName());

				insurance.setAccId(scheme.getInsuranceAccountId());
				fund.setAccId(scheme.getFundAccountId());
				spsAccountDao.save(cti, insurance);
				spsAccountDao.save(cti, fund);
			}else if("INSURANCE".equals(applyMessage.getUkeyType())){
				insurance.setRegNum(applyMessage.getRegNum());
				insurance.setRegNumpass(applyMessage.getRegNumpass());
				insurance.setRegUsbkeypass(applyMessage.getRegUsbkeypass());
				insurance.setUsbcode(applyMessage.getUsbCode());
				insurance.setContaninerName(applyMessage.getContaninerName());
				insurance.setCorpName(applyMessage.getCorpName());
				insurance.setAccId(scheme.getInsuranceAccountId());
				spsAccountDao.save(cti, insurance);
			}else if("FUND".equals(applyMessage.getUkeyType())){
				fund.setRegNum(applyMessage.getRegNum());
				fund.setRegNumpass(applyMessage.getRegNumpass());
				fund.setRegUsbkeypass(applyMessage.getRegUsbkeypass());
				fund.setUsbcode(applyMessage.getUsbCode());
				fund.setContaninerName(applyMessage.getContaninerName());
				fund.setCorpName(applyMessage.getCorpName());
				fund.setAccId(scheme.getFundAccountId());
				spsAccountDao.save(cti, fund);
			}

			if(StringUtils.isNotBlank(applyMessage.getRemark())){
				/**
				 * 修改库默认比例ID
				 */
				List<Map> ratio = JSON.parseArray(applyMessage.getRemark(),Map.class);
				for(Map map : ratio){
					String type = String.valueOf(map.get("type"));
					Integer insuranceId = Integer.parseInt(String.valueOf(map.get("insuranceId")));
					String corpRatio = String.valueOf(map.get("corpRatio"));
					String empRatio = null;
					if(null != map.get("empRatio"))
						empRatio = String.valueOf(map.get("empRatio"));
					if("FUND".equals(type)){
						spsAccountRatioService.updateAccountDefaultRatio(cti,fund.getAccId(),insuranceId,fund.getAreaId(),corpRatio,empRatio);
					}else{
						spsAccountRatioService.updateAccountDefaultRatio(cti,insurance.getAccId(),insuranceId,insurance.getAreaId(),corpRatio,null);
					}
				}
			}


		}
	}

	/**
	 * 保存方案
	 *  @param cti
	 *  @param accountRatio
	 *  @createDate  	: 2017年4月6日 下午3:38:29
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月6日 下午3:38:29
	 *  @updateAuthor  	:
	 */
	@Override
	public SpsScheme saveAccount(ContextInfo cti, SchemeAccountRatio accountRatio) {
		SpsScheme scheme = new SpsScheme();
		if(null != accountRatio.getSchemeId() && !"".equals(accountRatio.getSchemeId())){
			scheme.setSchemeId(accountRatio.getSchemeId());
			scheme.setDr(0);
			scheme = spsSchemeService.findByPK(scheme);
			if(null == scheme){
				scheme = new SpsScheme();
			}
		}
		// 获取默认SP
		scheme.setSpId(-999);
		scheme.setCpId(cti.getOrgId());
		CmCorp cmCorp = new CmCorp();
		cmCorp.setCpId(cti.getOrgId());
		cmCorp = cmCorpService.findByPk(cmCorp);
		BsArea bsArea = new BsArea();
		bsArea.setAreaId(accountRatio.getAreaId());
		bsArea = bsAreaService.findByPK(bsArea);
		bsArea.setAreaId(bsArea.getBelongCity());
		bsArea = bsAreaService.findByPK(bsArea);
		scheme.setName(cmCorp.getCorpName()+"_"+bsArea.getName()+"方案");
		scheme.setAreaId(accountRatio.getAreaId());
		scheme.setSchemeType("DIY");
		scheme.setState("USE");
		scheme.setInsuranceType("SINGLE");
		scheme.setFundType("SINGLE");
		scheme.setBillType("ADVANCE_CHARGE");
		scheme.setAreaname(bsArea.getName());
		scheme.setDr(0);
		// 创建方案
		if(null == accountRatio.getSchemeId() || "".equals(accountRatio.getSchemeId())){
			spsSchemeService.save(cti, scheme);
			//为创建方案用户设置权限
			if(null != cti && !"ALL".equals(cti.getAuthority())){
				cmCorpService.insertFunctionData(cti,cti.getUserId(),scheme.getSchemeId(), Constant.DATATYPE,"CUSTOMER");
				RedisUtil.hput(USER_AUTH_MODFIY_TAG,String.valueOf(cti.getUserId()),"Y");
			}
		}else{
			scheme.setSchemeId(accountRatio.getSchemeId());
			spsSchemeService.update(cti, scheme);
		}
		
		// 添加默认员工端配置
		CmEmployeeConfig cmEmployeeConfig = new CmEmployeeConfig();
		cmEmployeeConfig.setCpId(cti.getOrgId());
		cmEmployeeConfig.setAreaId(scheme.getAreaId());
		cmEmployeeConfig.setIsEmp(1);
		cmEmployeeConfig.setIsInsFund(1);
		cmEmployeeConfig.setIsEmpService(1);
		// 获取在线服务
    	List<BdBstype> onLineService = cmEmployeeConfigService.findEmpServiceByAreaId(scheme.getAreaId(),0);
    	String[] onLineBstype = new String[onLineService.size()];
    	for (int i=0;i<onLineService.size();i++) {
    		onLineBstype[i] = onLineService.get(i).getBstypeId().toString();
		}
    	cmEmployeeConfig.setOnLineService(StringUtils.join(onLineBstype, ","));
		cmEmployeeConfig.setIsOnLineService(1);
		// 获取员工服务
    	List<BdBstype> empService = cmEmployeeConfigService.findEmpServiceByAreaId(scheme.getAreaId(),1);
    	String[] empBstype = new String[empService.size()];
    	for (int i=0;i<empService.size();i++) {
    		empBstype[i] = empService.get(i).getBstypeId().toString();
		}
		cmEmployeeConfig.setEmpService(StringUtils.join(empBstype, ","));
		cmEmployeeConfig.setIsAddService(1);
		// 获取增值服务
    	List<BdBstype> addService = cmEmployeeConfigService.findAddServiceByAreaId(scheme.getAreaId());
    	String[] addBstype = new String[addService.size()];
    	for (int i=0;i<addService.size();i++) {
    		addBstype[i] = addService.get(i).getBstypeId().toString();
		}
		cmEmployeeConfig.setAddService(StringUtils.join(addBstype, ","));
		cmEmployeeConfigService.saveEmpConfig(cti, cmEmployeeConfig);
		
		SpsAccount insurance = new SpsAccount();
		insurance.setSpId(scheme.getSpId());
		insurance.setCpId(scheme.getCpId());
		insurance.setName(cmCorp.getCorpName()+"_"+bsArea.getName()+"单立户");
		insurance.setInsuranceFundType("INSURANCE");
		insurance.setAccType("SINGLE");
		insurance.setAreaId(scheme.getAreaId());
		insurance.setState("ON");
		insurance.setDr(0);
		
		SpsAccount fund = new SpsAccount();
		fund.setSpId(scheme.getSpId());
		fund.setCpId(scheme.getCpId());
		fund.setName(cmCorp.getCorpName()+"_"+bsArea.getName()+"公积金");
		fund.setInsuranceFundType("FUND");
		fund.setAccType("SINGLE");
		fund.setAreaId(scheme.getAreaId());
		fund.setState("ON");
		fund.setDr(0);
		// 创建大库
		// if(null == applyMessage.getSchemeId() || "".equals(applyMessage.getSchemeId())){
			// 社保
			insurance.setAccId(scheme.getInsuranceAccountId());
			spsAccountDao.save(cti, insurance);
			// 公积金
			fund.setAccId(scheme.getFundAccountId());
			spsAccountDao.save(cti, fund);
			// 更新方案大库ID
			if(null == scheme.getInsuranceAccountId() || null == scheme.getFundAccountId()){
				scheme.setInsuranceAccountId(insurance.getAccId());
				scheme.setFundAccountId(fund.getAccId());
				spsSchemeService.update(cti, scheme);
			}
		// 删除社保所有比例
		SpsAccountRatio vo = new SpsAccountRatio();
		vo.setAccId(scheme.getInsuranceAccountId());
		spsAccountRatioService.removeByAccId(cti, vo);
		// 删除公积金所有比例
		vo = new SpsAccountRatio();
		vo.setAccId(scheme.getFundAccountId());
		spsAccountRatioService.removeByAccId(cti, vo);
		// 插入社保比例
		for(InsuranceRatio insuranceRatioVo :  accountRatio.getInsuranceRatio()){
			for(Ratio ratio :  insuranceRatioVo.getRatio()){
				SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
				spsAccountRatio.setAccId(scheme.getInsuranceAccountId());
				spsAccountRatio.setRatioId(ratio.getRatioId());
				spsAccountRatio.setLivingType(insuranceRatioVo.getInsuranceLivingType());
				spsAccountRatio.setIsdefault("Y");
				spsAccountRatio.setDr(0);
				spsAccountRatioService.save(cti, spsAccountRatio);
			}
			/**
			 * 删除比例缓存
			 */
			String ins_ratio_key = BsType.NEW_INSURANCE.getInsurance_fund_type() + "_" + scheme.getInsuranceAccountId() + "_" + insuranceRatioVo.getInsuranceLivingType();
			RedisUtil.delete(ins_ratio_key);
		}
		// 插入公积金比例
		for(FundRatio fundRatioVo :  accountRatio.getFundRatio()){
			SpsAccountRatio spsAccountRatio = new SpsAccountRatio();
			spsAccountRatio.setAccId(scheme.getFundAccountId());
			spsAccountRatio.setRatioId(fundRatioVo.getRatioId());
			spsAccountRatio.setIsdefault("Y");
			spsAccountRatio.setDr(0);
			spsAccountRatioService.save(cti, spsAccountRatio);

			/**
			 * 删除比例缓存
			 */
			String fund_ratio_key = BsType.ADD_INSURANCE.getInsurance_fund_type() + "_" + scheme.getFundAccountId();
			RedisUtil.delete(fund_ratio_key);
		}
		return scheme;
	}


	/**
	 * 获取已经登录T的城市列表
	 * @param cti
	 * @param cmCorp
	 * @param taskIds
	 * @param areaIds
	 * @return
	 */
	public List<ApplyMessage> filterNoLoginAreas(ContextInfo cti, CmCorp cmCorp, String[] taskIds, String[] areaIds, String[] schemeIds){
		List<ApplyMessage> applyMessages = findApplyMessage(cti,cmCorp,areaIds,taskIds,schemeIds);
		/**
		 * 获取已经登录
		 */
		List<ApplyMessage> isLoginList = new ArrayList<ApplyMessage>();
		String sessionId = String.valueOf(cti.getExt().get("sessionId"));
		for (ApplyMessage applyMessage : applyMessages){
			if(applyMessage.getInsuranceFundType().indexOf(",")>0){
				/**
				 * 三证合一存在未登录 就必须要重新登录
				 */

				Object insob = RedisUtil.hget(sessionId,applyMessage.getInsuranceFundType().split(",")[0]+"_"+applyMessage.getRegNum()+"_"+cti.getUserId());
				Object fundob = RedisUtil.hget(sessionId,applyMessage.getInsuranceFundType().split(",")[1]+"_"+applyMessage.getRegNum()+"_"+cti.getUserId());

//				Object insob = RedisUtil.get(applyMessage.getInsuranceFundType().split(",")[0]+"_"+applyMessage.getRegNum()+"_"+cti.getUserId());
//				Object fundob = RedisUtil.get(applyMessage.getInsuranceFundType().split(",")[1]+"_"+applyMessage.getRegNum()+"_"+cti.getUserId());
				if(null != schemeIds && null != insob && null != fundob){
					isLoginList.add(applyMessage);
				}else {
					if(null != insob){
						ApplyMessage insApply = new ApplyMessage();
						BeanUtils.copyProperties(applyMessage,insApply);
						insApply.setInsuranceFundType(applyMessage.getInsuranceFundType().split(",")[0]);
						isLoginList.add(insApply);
					}
					if(null != fundob){
						ApplyMessage fundApply = new ApplyMessage();
						BeanUtils.copyProperties(applyMessage,fundApply);
						fundApply.setInsuranceFundType(applyMessage.getInsuranceFundType().split(",")[1]);
						isLoginList.add(fundApply);
					}
				}
			}else{
				Object ob = RedisUtil.hget(sessionId,applyMessage.getInsuranceFundType().split(",")+"_"+applyMessage.getRegNum());
				//Object ob = RedisUtil.get(applyMessage.getInsuranceFundType().split(",")+"_"+applyMessage.getRegNum());
				if(null != ob){
					isLoginList.add(applyMessage);
				}
			}
		}
		return isLoginList;
	}


	/**
	 *  创建默认方案
	 *  @param   cti, areaId
	 *	@return 			: com.xfs.sp.model.SpsScheme
	 *  @createDate  	: 2017-05-18 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-18 14:52
	 *  @updateAuthor  :
	 */
	public SpsScheme createDefaultScheme(ContextInfo cti,Integer areaId){
		boolean isCreate = false;
		for(String roleId : cti.getRoleIds().split(",")){
			if(roleId.equals(String.valueOf(SysRole.CUSTOMER_SUPER_ROLE_ID)) || roleId.equals(String.valueOf(SysRole.CUSTOMER_BUSSINES_BOSS))){
				isCreate = true;
			}
		}
		/**
		 * 无权限创建方案
		 */
		if(!isCreate)
			return null;
		AreaSocialRuleVo areaSocialRuleVo = spsAccountRatioService.findSocialRule(cti,areaId,null);
		SchemeAccountRatio accountRatio = new SchemeAccountRatio();
		accountRatio.setAreaId(areaId);
		List<InsuranceRatio> insuranceRatio = new ArrayList<>();
		List<FundRatio> fundRatio = new ArrayList<>();
		accountRatio.setInsuranceRatio(insuranceRatio);
		accountRatio.setFundRatio(fundRatio);
		for(InsuranceLivingTypeVo insurance : areaSocialRuleVo.getInsuranceLivingType()){
			InsuranceRatio ratio = new InsuranceRatio();
			ratio.setInsuranceLivingType(insurance.getPeopleTypeCode());
			ratio.setRatio(new ArrayList<Ratio>());
			for(InsuranceLivingTypeDetailsVo detailvo : insurance.getInsuranceLivingType()){
				Ratio r = new Ratio();
				r.setRatioId(detailvo.getRatioId());
				ratio.getRatio().add(r);
			}
			insuranceRatio.add(ratio);
		}
		for(InsuranceLivingTypeDetailsVo insurance : areaSocialRuleVo.getFundType()){
			FundRatio ratio = new FundRatio();
			ratio.setRatioId(insurance.getRatioId());
			fundRatio.add(ratio);
		}

		SpsScheme scheme = new SpsScheme();
		scheme.setCpId(cti.getOrgId());
		scheme.setAreaId(areaId);
		scheme.setAuthority("ALL");
		scheme.setSpId(-999);
		SpsScheme exitsScheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
		if(null != exitsScheme){
			accountRatio.setSchemeId(exitsScheme.getSchemeId());
		}
		return saveAccount(cti,accountRatio);
	}

}
