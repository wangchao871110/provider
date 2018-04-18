package com.xfs.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.BdInsuranceDao;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;

@Service
public class BdInsuranceServiceImpl implements BdInsuranceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdInsuranceServiceImpl.class);

	@Autowired
	private BdInsuranceDao bdInsuranceDao;

	public int save(ContextInfo cti, BdInsurance vo ){
		return bdInsuranceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdInsurance vo ){
		return bdInsuranceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdInsurance vo ){
		return bdInsuranceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdInsurance vo ){
		return bdInsuranceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdInsurance vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdInsuranceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdInsurance> datas = bdInsuranceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List<BdInsurance> findAll(BdInsurance vo){

		List<BdInsurance> datas = bdInsuranceDao.freeFind(vo);
		return datas;

	}



	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/25 12:02:59

	/*
	 * 根据
	 * @see com.xfs.business.service.BdInsuranceService#findInsurancesByAreaId(java.lang.Integer)
	 */
	@Override
	public List<BdInsurance> findInsurancesByAreaId(Integer areaId) {
		String key = IRedisKeys.COMMON_BD_AREAINSURANCE + "_" + areaId;
		List<BdInsurance> ins = (List<BdInsurance>) RedisUtil.get(key);
		if (null == ins) {
			ins = bdInsuranceDao.findBdInsuranceByAreaId(areaId);
			if (ins != null) {
				RedisUtil.set(key, ins, 3600);
			}
		}
		return ins;
	}
	/**
	 * 根据主键查询
	 *
	 * @author lifq
	 *
	 * 2016年10月10日  下午3:28:14
	 */
	@Override
	public BdInsurance findByPK(Integer pk){
		BdInsurance obj = new BdInsurance();
		obj.setInsuranceId(pk);
		return findByPK(obj);
	}
	@Override
	public BdInsurance findByPK(BdInsurance obj){
		String key = IRedisKeys.COMMON_BD_INSURANCE + "_" + obj.getInsuranceId().toString();
		BdInsurance ins = (BdInsurance) RedisUtil.get(key);
		if (null == ins) {
			ins = bdInsuranceDao.findByPK(obj);
			if (ins != null) {
				RedisUtil.set(key, ins, 3600);
			}
		}
		return ins;
	}

	/*
	 * 获取公积金或社保保险类型
	 *
	 */
	@Override
	public List<Integer> findInsuranceIds(String insuranceOrFund) {
		List<Integer> insuranceIds = new ArrayList<>();
		BdInsurance insuranceqry = new BdInsurance();
		if (StringUtils.isNotEmpty(insuranceOrFund))
			insuranceqry.setInsuranceFundType(insuranceOrFund);
		List<BdInsurance> insurances = findAll(insuranceqry);
		for (BdInsurance insurance : insurances) {
			insuranceIds.add(insurance.getInsuranceId());
		}
		return insuranceIds;
	}

	/**通过地区、户口类型、参保日期 反查险种
	 *  @param   areaId, livingType, period]
	 * @return    : java.util.List<com.xfs.business.model.BdInsurance>
	 *  @createDate   : 2016/12/2 21:05
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/2 21:05
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdInsurance> findBdInsuranceByAreaIdAndLivingType(Integer areaId,String livingType,String period) {
		if(areaId == null || StringUtils.isBlank(livingType) || StringUtils.isBlank(period)){
			return null;
		}
		return bdInsuranceDao.findBdInsuranceByAreaIdAndLivingType(areaId,livingType,period);
	}
}
