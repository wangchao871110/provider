package com.xfs.base.service;

import com.xfs.base.dao.BdExpressCompanyDao;
import com.xfs.base.model.BdExpressCompany;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物流公司服务类
 * @author 	: wuzhe
 * @date 	: 2016-11-23
 */
@Service
public class BdExpressCompanyImpl implements BdExpressCompanyService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdExpressCompanyImpl.class);
	
	@Autowired
	private BdExpressCompanyDao bdExpressCompanyDao;

	/**
	 * 查询所有物流公司信息
	 * @return List
	 */
	public List<BdExpressCompany> findAllCompany() {
		BdExpressCompany obj = new BdExpressCompany();
		return bdExpressCompanyDao.findAllCompany(obj);
	}
	/**
	 * 查询所有物流公司信息
	 * @return Map
	 */
	public Map<String,BdExpressCompany> findAllCompanyNameMap(){
		List<BdExpressCompany> companies = findAllCompany();
		Map<String,BdExpressCompany> retMap = new HashMap<>(0);
		for(BdExpressCompany company : companies){
			retMap.put(company.getName(),company);
		}
		return retMap;
	}

	
}
