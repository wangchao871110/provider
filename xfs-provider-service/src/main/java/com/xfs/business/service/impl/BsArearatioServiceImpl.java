package com.xfs.business.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.corp.model.CmEmployeeInsurance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xfs.business.dao.BsArearatioDao;
import com.xfs.business.dao.BsArearatioscopeDao;
import com.xfs.business.dao.BsArearatioverDao;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsArearatioscope;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.model.InsuranceFundPeopleType;
import com.xfs.business.model.InsuranceFundType;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.Arith;
import com.xfs.common.util.RedisKeyConstant;

@Service
public class BsArearatioServiceImpl implements BsArearatioService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsArearatioServiceImpl.class);
	
	@Autowired
	private BsArearatioDao bsArearatioDao;

    @Autowired
    private BdInsuranceService bdInsuranceService;

    @Autowired
    private BsArearatioverDao bsArearatioverDao;

    @Autowired
    private BsArearatioscopeDao bsArearatioscopeDao;



	
	public int save(ContextInfo cti, BsArearatio vo ){
		return bsArearatioDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsArearatio vo ){
		return bsArearatioDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsArearatio vo ){
		return bsArearatioDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsArearatio vo ){
		return bsArearatioDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsArearatio vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsArearatioDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsArearatio> datas = bsArearatioDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}



	public List<BsArearatio> findAll(BsArearatio vo){
		
		List<BsArearatio> datas = bsArearatioDao.freeFind(vo);
		return datas;
		
	}


    @Override
    public Map<String, Object> findAreaRatioDetail(BsArearatio obj) {
        return bsArearatioDao.findAreaRatioDetail(obj);
    }
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:03:17
	@Override
	public BsArearatio findByPK(Integer ratioId) {
		if (null == ratioId)
			return null;
		String key = IRedisKeys.COMMON_AREA_RATIO + "_" + ratioId.toString();
		BsArearatio ratio = (BsArearatio) RedisUtil.get(key);
		if (null == ratio) {
			ratio = bsArearatioDao.findWithVers(ratioId);
			if (ratio != null) {
				RedisUtil.set(key, ratio, 3600);
			}
		}
		return ratio;
	}

	@Override
	public Result calc(ContextInfo cti,List<? extends BsRatioCalcInterface> calcEntities, String period) {
		for(BsRatioCalcInterface calcEntity : calcEntities) {
			Result r = calc(cti, calcEntity, period,null);
			if (!r.isSuccess())
				return r;
		}
		return Result.createResult();
	}
	@Override
	public Result calc(ContextInfo cti,BsRatioCalcInterface calcEntity, String period,String endPeriod) {
		Result r = Result.createResult();
		BsArearatio ratio = findByPK(calcEntity.getRatioId());
		if (null == ratio) {
			r.setSuccess(false);
			r.setError("未找到险种比例");
			return r;
		}
		BsArearatiover ver = getVerByPeriod(period, ratio);
		//没有取到版本，获取与调整时间最近对应版本
		if (null == ver) {
            for(BsArearatiover bsArearatiover : ratio.getBsArearatiovers()){
                if(null == ver && (StringUtils.isEmpty(bsArearatiover.getEndPeriod()) || bsArearatiover.getEndPeriod().compareTo(period) >= 0 )){
                    ver = bsArearatiover;
                    if(calcEntity instanceof CmEmployeeInsurance){
                        CmEmployeeInsurance insurance = (CmEmployeeInsurance)calcEntity;
                        insurance.setBeginPeriod(ver.getBeginPeriod());
                    }
                }
            }
			//ver = getVerByPeriod(new SimpleDateFormat("yyyy-MM").format(new Date()), ratio);
		}
		if (null == ver) {
			r.setSuccess(false);
			r.setError(String.format("在%s中未找到适用于%s期间的比例版本",ratio.getName(),period));
			return r;
		}
		
		calcEntity.setCorpPaybase(calcPayBase(calcEntity.getCorpPaybase(), ver.getCorpBaseLower(), ver.getCorpBaseUpper()));
		calcEntity.setEmpPaybase(calcPayBase(calcEntity.getEmpPaybase(), ver.getPsnBaseLower(), ver.getPsnBaseUpper()));
		//if (null == calcEntity.getCorpRatio())
			calcEntity.setCorpRatio(ver.getCorpRatio());
		//if (null == calcEntity.getEmpRatio())
			calcEntity.setEmpRatio(ver.getPsnRatio());
		//if (null == calcEntity.getCorpAddition())
			calcEntity.setCorpAddition(ver.getCorpAddition());
		//if (null == calcEntity.getPsnAddition())
			calcEntity.setPsnAddition(ver.getPsnAddition());
		calcEntity.setCorpPayment(calcPayment(calcEntity.getCorpPaybase(), calcEntity.getCorpRatio(), calcEntity.getCorpAddition(), ratio.getCorpCalcMethod(), ratio.getCorpPrecision()));
		calcEntity.setEmpPayment(calcPayment(calcEntity.getEmpPaybase(), calcEntity.getEmpRatio(), calcEntity.getPsnAddition(), ratio.getPsnCalcMethod(), ratio.getPsnPrecision()));

        /**
         * 获取当前版本之后的一个区间版本
         */
        BsArearatiover nextRatiover = null;
        boolean isnext = false;
        //判断当前费用段是否跨版本
        if(com.xfs.common.util.StringUtils.isBlank(endPeriod) || ver.getEndPeriod().compareTo(endPeriod) < 0){
            for(BsArearatiover ratiover : ratio.getBsArearatiovers()){
                if(isnext){
                    nextRatiover = ratiover;
                    break;
                }
                if(ratiover.equals(ver)){
                    isnext = true;
                }
            }
        }
        r.setDataValue("nextRatiover",nextRatiover);
        return r;
	}
	
	@Override
	public BsArearatiover getVerByPeriod(String period, BsArearatio ratio) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		if (StringUtils.isEmpty(period))
			period = format.format(new Date());
		for(BsArearatiover verloop : ratio.getBsArearatiovers()) {
			try {
				if (StringUtils.isEmpty(verloop.getBeginPeriod()) || format.parse(verloop.getBeginPeriod()).compareTo(format.parse(period))<=0) {
					if (StringUtils.isEmpty(verloop.getEndPeriod()) || format.parse(verloop.getEndPeriod()).compareTo(format.parse(period))>=0) {
						return verloop;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("期间转换失败!",e);
				throw new RuntimeException("期间转换失败!",e);
			}
		}
		return null;
	}
	
	private BigDecimal calcPayBase(BigDecimal payBase, BigDecimal baseLower, BigDecimal baseUpper) {
		payBase = Arith.ignoreNull(payBase);
        if (baseUpper !=null && payBase.compareTo(BigDecimal.ZERO) > 0 && payBase.compareTo(baseUpper) > 0)
            payBase = baseUpper;
        if (baseLower !=null && payBase.compareTo(baseLower) < 0)
            payBase = baseLower;
        return payBase;
	}
	private BigDecimal calcPayment(BigDecimal payBase, BigDecimal ratio, BigDecimal addition, String calcMethod, Integer precision) {
		BigDecimal payment = Arith.mul(payBase, ratio);
		payment = Arith.add(payment, addition);
		payment = Arith.dealRound(payment, calcMethod, precision);
		return payment;
	}
	
	/**
     * 根据areaId查询 险种信息
     *
     * @author lifq
     *
     * 2016年9月26日  下午2:20:48
     */
    public List<Map<String, Object>> findRatiosByAreaId(Map<String,Object> paraMap){
    	return bsArearatioDao.findRatiosByAreaId(paraMap);
    }

    /**
     * 获取指定区域社保规则分页
     */
    public List findByQueryList(Map map) {
        //获取现在时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentTime = sdf.format(new Date());
        map.put("currentTime", currentTime);
        List<Map<String, Object>> datas = bsArearatioDao.findRatioListByQueryList(map);
        return datas;
    }
    /*public PageModel findPageByQueryList(Map map) {
        //获取现在时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentTime = sdf.format(new Date());
        map.put("currentTime", currentTime);
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bsArearatioDao.countFreeFindRuleByAreaId(map);
        pm.setTotal(total);
        List<Map<String, Object>> datas = bsArearatioDao.findRatioListByQueryList(map, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }*/
    @Override
    public void deleteRatio(ContextInfo cti, Integer ratioId) {
        //删除主表
        BsArearatio arearatio =  new BsArearatio();
        arearatio.setRatioId(ratioId);
        List<BsArearatio> list = bsArearatioDao.freeFind(arearatio);
        BsArearatio ratio = new BsArearatio();
        ratio = list.get(0);
        ratio.setDr(1);
        bsArearatioDao.update(cti, ratio);
    }
    /*
     * 更新规则
     * @see com.xfs.bs.service.BsArearatioService#updateRule(com.xfs.common.model.BsArearatio, java.lang.String[], java.lang.String[], java.lang.String[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[], java.math.BigDecimal[])
     */
    @Override
    public Result updateRule(ContextInfo cti,BsArearatio ratio, String[] code, String[] beginPeriod, String[] endPeriod,
                             BigDecimal[] corpBaseLower, BigDecimal[] corpBaseUpper, BigDecimal[] psnBaseLower,
                             BigDecimal[] psnBaseUpper, BigDecimal[] corpRatio, BigDecimal[] psnRatio, BigDecimal[] corpAddition,
                             BigDecimal[] psnAddition) {
        Integer ratioId = ratio.getRatioId();
//
        //存入险种比例主表
        BsArearatio bsArearatio = new BsArearatio();
        bsArearatio.setInsuranceId(ratio.getInsuranceId());
        bsArearatio.setAreaId(ratio.getAreaId());
        bsArearatio.setName(ratio.getName());
        bsArearatio.setRatioId(ratio.getRatioId());
        List<BsArearatio> checkList = bsArearatioDao.findRatio(bsArearatio);
    	/*if (checkList != null && checkList.size()>0) {
    		Result result = Result.createResult();
    		result.setError("输入的比例名称已存在!");
    		result.setSuccess(false);
    		return result;
		}*/
//    	deleteRatio(ratio.getRatioId());
//    	bsArearatioDao.insert(ratio);
        ratio.setDr(0);
        bsArearatioDao.update(cti, ratio);

        //删除版本
        BsArearatiover ratiover = new BsArearatiover();
        ratiover.setRatioId(ratioId);
        List<BsArearatiover> verList = bsArearatioverDao.freeFind(ratiover);
        if (verList != null && verList.size() > 0) {
            for (BsArearatiover ver : verList) {
                bsArearatioverDao.remove(cti, ver);
            }
        }
        //删除户口性质
        BsArearatioscope ratioscope = new BsArearatioscope();
        ratioscope.setRatioId(ratioId);
        List<BsArearatioscope> scopeList = bsArearatioscopeDao.freeFind(ratioscope);
        if (scopeList != null && scopeList.size() > 0) {
            for (BsArearatioscope scope : scopeList) {
                bsArearatioscopeDao.remove(cti, scope);
            }
        }
        //把比例版本封装成集合
        List<BsArearatiover> list = new ArrayList<>();
        for (int i = 0; i < psnAddition.length; i++) {
            BsArearatiover arearatiover = new BsArearatiover();
            arearatiover.setBeginPeriod(beginPeriod[i]);
            arearatiover.setEndPeriod(endPeriod[i]);
            arearatiover.setCorpBaseLower(corpBaseLower[i]);
            arearatiover.setCorpBaseUpper(corpBaseUpper[i]);
            arearatiover.setPsnBaseLower(psnBaseLower[i]);
            arearatiover.setPsnBaseUpper(psnBaseUpper[i]);
            arearatiover.setCorpRatio(corpRatio[i]);
            arearatiover.setPsnRatio(psnRatio[i]);
            arearatiover.setCorpAddition(corpAddition[i]);
            arearatiover.setPsnAddition(psnAddition[i]);
            arearatiover.setRatioId(ratioId);
            bsArearatioverDao.insert(cti, arearatiover);
        }
        //添加比例户口性质
        BdInsurance insuranceVo= bdInsuranceService.findByPK(ratio.getInsuranceId());
//    	if (6 != ratio.getInsuranceId()) {
        if("INSURANCE".equals(insuranceVo.getInsuranceFundType())){
            for (int i = 0; i < code.length; i++) {
                BsArearatioscope arearatioscope = new BsArearatioscope();
                arearatioscope.setRatioId(ratioId);
                arearatioscope.setLivingType(code[i]);
                bsArearatioscopeDao.insert(cti, arearatioscope);
            }
        }

        String key = IRedisKeys.COMMON_AREA_RATIO + "_" + ratio.getRatioId().toString();
        RedisUtil.delete(key);
        RedisUtil.delete(RedisKeyConstant.INSURANCEFUNDLIST+ratio.getAreaId());
        return Result.createResult().setSuccess(true);
        //删除旧表,保存新表
    }

    /*
     * 保存规则
     */
    public Result saveRule(ContextInfo cti,BsArearatio ratio, String[] code, String[] beginPeriod, String[] endPeriod,
                           BigDecimal[] corpBaseLower, BigDecimal[] corpBaseUpper, BigDecimal[] psnBaseLower,
                           BigDecimal[] psnBaseUpper, BigDecimal[] corpRatio, BigDecimal[] psnRatio, BigDecimal[] corpAddition,
                           BigDecimal[] psnAddition) {
        //存入险种比例主表
        BsArearatio bsArearatio = new BsArearatio();
        bsArearatio.setInsuranceId(ratio.getInsuranceId());
        bsArearatio.setAreaId(ratio.getAreaId());
        bsArearatio.setName(ratio.getName());
        List<BsArearatio> checkList = bsArearatioDao.findRatio(bsArearatio);
		/*if (checkList != null && checkList.size()>0) {
			Result result = Result.createResult();
			result.setError("输入的比例名称已存在!");
			result.setSuccess(false);
			return result;
		}*/
        ratio.setRatioId(null);
        bsArearatioDao.insert(cti, ratio);

        //把比例版本封装成集合
        List<BsArearatiover> list = new ArrayList<>();
        for (int i = 0; i < psnAddition.length; i++) {
            BsArearatiover arearatiover = new BsArearatiover();
            arearatiover.setBeginPeriod(beginPeriod[i]);
            arearatiover.setEndPeriod(endPeriod[i]);
            arearatiover.setCorpBaseLower(corpBaseLower[i]);
            arearatiover.setCorpBaseUpper(corpBaseUpper[i]);
            arearatiover.setPsnBaseLower(psnBaseLower[i]);
            arearatiover.setPsnBaseUpper(psnBaseUpper[i]);
            arearatiover.setCorpRatio(corpRatio[i]);
            arearatiover.setPsnRatio(psnRatio[i]);
            arearatiover.setCorpAddition(corpAddition[i]);
            arearatiover.setPsnAddition(psnAddition[i]);
            arearatiover.setRatioId(ratio.getRatioId());
            bsArearatioverDao.insert(cti, arearatiover);
        }
        //添加比例户口性质
        BdInsurance insuranceVo= bdInsuranceService.findByPK(ratio.getInsuranceId());
//		if (6 != ratio.getInsuranceId()) {
        if("INSURANCE".equals(insuranceVo.getInsuranceFundType())){
            for (int i = 0; i < code.length; i++) {
                BsArearatioscope arearatioscope = new BsArearatioscope();
                arearatioscope.setRatioId(ratio.getRatioId());
                arearatioscope.setLivingType(code[i]);
                bsArearatioscopeDao.insert(cti, arearatioscope);
            }
        }

        String key = IRedisKeys.COMMON_AREA_RATIO + "_" + ratio.getRatioId().toString();
        RedisUtil.delete(key);
        RedisUtil.delete(RedisKeyConstant.INSURANCEFUNDLIST+ratio.getAreaId());
        return Result.createResult().setSuccess(true);
        //删除旧表,保存新表
    }

    /*
     * 查找出保险类型id
     * @see com.xfs.bs.service.BsArearatioService#findInsuranceIdsByAreaId(java.lang.Integer)
     */
    @Override
    public List findInsuranceIdsByAreaId(Integer areaId) {
        List list = bsArearatioDao.findInsuranceIdsByAreaId(areaId);
        return list;
    }

    /**
     * 批量修改比例查询 比例
     *  @param   beginMonth, areaId, insuranceId, livingType, ratioId
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 17:02 2016/11/15
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 17:02 2016/11/15
     *  @updateAuthor  :
     */
    @Override
    public List<Map<String,Object>> findRatioByBatchQuery(String beginMonth,Integer areaId,Integer insuranceId,String livingType,Integer ratioId,Integer ratioIdNe,Integer dr){

        Map<String,Object> query = new HashMap<>();
        query.put("beginMonth",beginMonth);
        query.put("areaId",areaId);
        query.put("insuranceId",insuranceId);
        query.put("livingType",livingType);
        query.put("ratioId",ratioId);
        query.put("ratioIdNe",ratioIdNe);
        query.put("dr",dr);
        return bsArearatioDao.findRatioByBatchQuery(query);
    }

    /**
     * 查询比例所属险种类型
     *  @param   ratioId
     * @return    : java.lang.String
     *  @createDate   : 10:35 2016/11/18
     *  @author          : yfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 10:35 2016/11/18
     *  @updateAuthor  :
     */
    @Override
    public String findRatioInsuranceType(Integer ratioId){
        if(ratioId == null){
            return null;
        }
        return bsArearatioDao.findRatioInsuranceType(ratioId);
    }

    /**
     * 查询地区社保基数下限中最高的值 小于3500 取3500
     *  @param   areaId 地区id
     * @return    : java.lang.Integer
     *  @createDate   : 2016/11/28 18:15
     *  @author          : konglc@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 18:15
     *  @updateAuthor  :
     */
    public BigDecimal findInsuBaseLower(Integer areaId){
        return bsArearatioDao.findInsuBaseLower(areaId);
    }
    
	@Override
	public BsArearatio findDefaultRatio(Integer areaId, Integer insuranceId, String livingType, String period) {
		String key = String.format("%s_%s_%s_%s_%s", IRedisKeys.COMMON_AREA_DEFAULTRATIOID, areaId,insuranceId,livingType,period);
		Integer ratioId = (Integer) RedisUtil.get(key);
		if (null == ratioId){
			ratioId = bsArearatioDao.findDefaultRatioId(areaId, insuranceId, livingType, period);
			if (ratioId != null) {
				RedisUtil.set(key, ratioId, 3600);
			}
		}
		return findByPK(ratioId);
	}

    @Override
    public BigDecimal queryInsuMin(Integer areaId) {
        return bsArearatioDao.queryInsuMin(areaId);
    }

    @Override
    public BigDecimal queryInsuMax(Integer areaId) {
        return bsArearatioDao.queryInsuMax(areaId);
    }


    @Override
    public BigDecimal queryFundMin(Integer areaId) {
        return bsArearatioDao.queryFundMin(areaId);
    }

    @Override
    public BigDecimal queryFundMax(Integer areaId) {
        return bsArearatioDao.queryFundMax(areaId);
    }
    /**
     * 获取薪福邦小助手数据
     *  @param areaId
     *  @return 
     *  @createDate  	: 2017年1月17日 下午1:40:15
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年1月17日 下午1:40:15
     *  @updateAuthor  	:
     */
	@Override
	public List<InsuranceFundPeopleType> findInsuranceFundPeopleType(Integer areaId) {
		return bsArearatioDao.findInsuranceFundPeopleType(areaId);
	}public BsArearatioServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取薪福邦小助手户口类型数据
	 *  @param areaId
	 *  @param peopleTypeCode
	 *  @return 
	 *  @createDate  	: 2017年1月18日 上午11:00:44
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月18日 上午11:00:44
	 *  @updateAuthor  	:
	 */
	@Override
	public List<InsuranceFundType> findInsuranceFundType(Integer areaId, String peopleTypeCode) {
		return bsArearatioDao.findInsuranceFundType(areaId,peopleTypeCode);
	}
}
