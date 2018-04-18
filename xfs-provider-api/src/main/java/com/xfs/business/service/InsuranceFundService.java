package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.InsuranceFundDto;
import com.xfs.business.model.InsuranceFundRegulation;

/**
 * 社保公积金规则
 * @author 	: wangchao
 * @date 	: 2017年1月16日 下午4:46:00
 * @version 	: V1.0
 */
public interface InsuranceFundService {
	
	/**
	 * 根据条件获取社保公积金规则
	 *  @param insuranceFundDto
	 *  @param cityId
	 *  @param cityName
	 *  @return 
	 *	@return 			: List<InsuranceFundRegulation> 
	 *  @createDate  	: 2017年1月19日 上午11:51:27
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月19日 上午11:51:27
	 *  @updateAuthor  :
	 */
	public List<InsuranceFundRegulation> findInsuranceFundRegulation(InsuranceFundDto insuranceFundDto,String[] cityId,String[] cityName);

}
