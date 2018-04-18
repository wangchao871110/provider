package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.BdInsurance;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 保险服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 15:01
 * @version 	: V1.0
 */
public interface BdInsuranceService {
	
	public int save(ContextInfo cti, BdInsurance vo);
	public int insert(ContextInfo cti, BdInsurance vo);
	public int remove(ContextInfo cti, BdInsurance vo);
	public int update(ContextInfo cti, BdInsurance vo);
	public PageModel findPage(PageInfo pi, BdInsurance vo);
	public List<BdInsurance> findAll(BdInsurance vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/25 12:02:59
	
	/*
	 * 根据所在城市,find险种名称
	 */
	List<BdInsurance> findInsurancesByAreaId(Integer areaId);
	
	/**
	 * 根据主键查询
	 *
	 * @author lifq
	 *
	 * 2016年10月10日  下午3:28:14
	 */
	public BdInsurance findByPK(BdInsurance obj);
	BdInsurance findByPK(Integer pk);


	List<Integer> findInsuranceIds(String insuranceOrFund);

	/**通过地区、户口类型、参保日期 反查险种
	 *  @param   areaId, livingType, period]
	 * @return    : java.util.List<com.xfs.business.model.BdInsurance>
	 *  @createDate   : 2016/12/2 21:05
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/2 21:05
	 *  @updateAuthor  :
	 */
	public List<BdInsurance> findBdInsuranceByAreaIdAndLivingType(Integer areaId,String livingType,String period);
}
