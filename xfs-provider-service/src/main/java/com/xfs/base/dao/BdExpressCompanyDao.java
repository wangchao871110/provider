package com.xfs.base.dao;

import com.xfs.base.model.BdExpressCompany;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * @author 	: wuzhe
 * @date 	: 2016-11-23
 */
@Repository
public class BdExpressCompanyDao extends BaseSqlMapDao {
	

	public List<BdExpressCompany> findAllCompany(BdExpressCompany obj) {
		return queryForList("BD_EXPRESS_COMPANY.findAllCompany", obj );
	}


}
