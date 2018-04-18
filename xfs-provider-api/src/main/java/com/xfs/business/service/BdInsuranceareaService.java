package com.xfs.business.service;

import java.util.List;
import java.util.Map;

import com.xfs.business.model.BdInsurancearea;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 地区保险服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 15:01
 * @version 	: V1.0
 */
public interface BdInsuranceareaService {
	
	public int save(ContextInfo cti, BdInsurancearea vo);
	public int insert(ContextInfo cti, BdInsurancearea vo);
	public int remove(ContextInfo cti, BdInsurancearea vo);
	public int update(ContextInfo cti, BdInsurancearea vo);
	public PageModel findPage(PageInfo pi, BdInsurancearea vo);
	public List<BdInsurancearea> findAll(BdInsurancearea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 16:36:51

	/**
	 *  获取区域下的社保项目
	 *  @param   spid
	 *  @param   cmid
	 *  @param   bill_id
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:16
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryEmInsuranceAreaList(Integer spid, Integer cmid, Integer bill_id);

	/**
	 *  获取区域下的社保项目
	 *  @param   cmid
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:17
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryEmInsuranceAreaList(Integer cmid);

	/**
	 *  根据地区ID获取社保信息
	 *  @param   areaid
	 *	@return 			: java.util.List<com.xfs.business.model.BdInsurancearea>
	 *  @createDate  	: 2016-11-10 18:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:18
	 *  @updateAuthor  :
	 */
	public List<BdInsurancearea> findBdInsuranceareaByAreaid(Integer areaid);

	/**
	 *  根据地区ID获取社保信息
	 *  @param   areaid
	 *	@return 			: java.util.List<com.xfs.business.model.BdInsurancearea>
	 *  @createDate  	: 2016-11-10 18:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:18
	 *  @updateAuthor  :
	 */
	public List<BdInsurancearea> findBdInsuranceareaByAreaidAndInsuranceid(Integer areaid);

	/**
	 *  根据地区 与类型 查询险种
	 *  @param   area_id, insuFundType
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:19
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:19
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findInsurancesByAreaIdAndType(Integer area_id,String insuFundType);
}
