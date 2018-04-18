package com.xfs.base.service;

import com.xfs.base.model.BdExpressCompany;

import java.util.List;
import java.util.Map;

/**
 * 物流公司服务类
 * @author 	: wuzhe
 * @date 	: 2016-11-23
 */
public interface BdExpressCompanyService {


	/**
	 * 查询所有物流公司信息
	 * @return List
	 */
	public List<BdExpressCompany> findAllCompany() ;
	/**
	 * 查询所有物流公司信息
	 * @return Map
	 */
	public Map<String,BdExpressCompany> findAllCompanyNameMap() ;


}
