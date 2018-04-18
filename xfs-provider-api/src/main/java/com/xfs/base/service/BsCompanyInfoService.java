package com.xfs.base.service;

import com.xfs.base.model.BsCompanyInfo;
/**
 * 启信宝校验服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 18:11
 * @version 	: V1.0
 */
public interface BsCompanyInfoService {

	public BsCompanyInfo queryCompanyInfoByName(String name);

	BsCompanyInfo queryCompanyInfoAllByName(String name);

//	BsCompanyInfo queryCompanyInfoWithDetailByName(String name);
}
