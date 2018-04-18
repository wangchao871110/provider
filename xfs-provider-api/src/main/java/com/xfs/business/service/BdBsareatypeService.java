package com.xfs.business.service;

import java.util.List;
import java.util.Map;

import com.xfs.business.dto.BsTypeAreaFiledDto;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 地区业务类型
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:30
 * @version 	: V1.0
 */
public interface BdBsareatypeService {
	
	public int save(ContextInfo cti, BdBsareatype vo);
	public int insert(ContextInfo cti, BdBsareatype vo);
	public int remove(ContextInfo cti, BdBsareatype vo);
	public int update(ContextInfo cti, BdBsareatype vo);
	public PageModel findPage(PageInfo pi, BdBsareatype vo);
	public List<BdBsareatype> findAll(BdBsareatype vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:00


	/**
	 *  根据条件获取地区业务信息
	 *  @param   bdBsareatype
	 *	@return 			: com.xfs.business.model.BdBsareatype
	 *  @createDate  	: 2016-11-11 14:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:41
	 *  @updateAuthor  :
	 */
	public BdBsareatype findBdBsAreaType(BdBsareatype bdBsareatype);

	/**
	 *  保存城市与业务关系
	 *  @param   bsTypes, areaId
	 *	@return 			: void
	 *  @createDate  	: 2016-11-11 14:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:41
	 *  @updateAuthor  :
	 */
	public void saveCityBsTypeRelation(ContextInfo cti, String bsTypes, Integer areaId);

	/**
	 *  保存城市与业务关系
	 *  @param   areaId, bstypeId fieldIds
	 *	@return 			: void
	 *  @createDate  	: 2016-11-11 14:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:41
	 *  @updateAuthor  :
	 */
	public void saveBsTypeAreaField(ContextInfo cti, Integer areaId, Integer bstypeId, String fieldIds);

	/**
	 * 	预制地区业务数据
	 *  @param   areaId
	 *	@return 			: void
	 *  @createDate  	: 2016-11-11 14:42
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:42
	 *  @updateAuthor  :
	 */
	public void resetData(ContextInfo cti, Integer areaId);

	/**
	 *  根据城市id 查询 业务类型信息
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:40
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:40
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findBstypeByCity(BdBsareatype vo);

	/**
	 *  根据城市id查询业务类型、待处理总数
	 *  @param   spId, vo, bstype
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:39
	 *  @updateAuthor  :
	 */
	List<Map<String,Object>> getBstypeByArea(Integer spId, BdBsareatype vo, BdBstype bstype);

	/**
	 *  通过城市与类型查询参保方式
	 *  @param   areaId, type
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:39
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findAddBstypeByCityAndType(Integer areaId, String type);

	/**
	 *  查询其他类型业务
	 *  @param   areaId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:38
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findOtherTaskBstype(Integer areaId);


	public List<Map<String, Object>> getBstypeByArea2(Integer spId, SpsTask task, BdBstype bstype);

	/**
	 *  根据企业方案所在地区获取业务对应的字段信息
	 *  @param   cpId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:53
	 *  @updateAuthor  :
	 */
	public List<BsTypeAreaFiledDto> findBdBsAreaTypeBySchemeAreaId(Integer cpId, String bstypeId);

	public List<BsTypeAreaFiledDto> findBdBsAreaTypeByAreaId();

	/**
	 *  获取当前企业对应方案地区下业务字段值信息
	 *  @param   cpId, code
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:52
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findBdBsAreaTypeFiledBySchemeAreaId(Integer cpId, String code,Integer schemeId);
	
	/**
	 * 根据城市和类型获取地区业务类型
	 *  @param areaId
	 *  @param bstypeId
	 *  @return 
	 *	@return 			: BdBsareatype 
	 *  @createDate  	: 2017年4月24日 下午4:48:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月24日 下午4:48:50
	 *  @updateAuthor  :
	 */
	public BdBsareatype findBdBstypeByCityAndType(Integer areaId, Integer bstypeId);

	/**
	 * 根据网申结束日期获取用户信息
	 * @param endDays 结束日期
	 * @return
	 */
	public List<Map<String, Object>> findUserByEndDay(String endDays);
}
