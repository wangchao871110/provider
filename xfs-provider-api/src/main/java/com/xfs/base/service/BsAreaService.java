package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.acc.dto.AreaSchemeVo;
import com.xfs.base.dto.AreaTree;
import com.xfs.base.model.BsArea;
import com.xfs.bs.util.TreeObject;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


/**
 * 地区服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 13:59
 * @version 	: V1.0
 */
public interface BsAreaService {
	
	public int save(ContextInfo cti, BsArea vo);
	public int insert(ContextInfo cti, BsArea vo);
	public int remove(ContextInfo cti, BsArea vo);
	public int update(ContextInfo cti, BsArea vo);
	public PageModel findPage(PageInfo pi, BsArea vo);
	public List<BsArea> findAll(BsArea vo);
	public  BsArea findByPK(BsArea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/08 14:31:55


	/**
	 *  根据条件获取地区信息
	 *  @param   vo
	 *	@return 			: com.xfs.base.model.BsArea
	 *  @createDate  	: 2016-11-11 14:00
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:00
	 *  @updateAuthor  :
	 */
	public BsArea findbyPK(BsArea vo);

	/**
	 *  获取所有城市并根据城市code排序
	 *  @param   vo
	 *	@return 			: java.util.List<com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:00
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:00
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAllEq(BsArea vo);

	/**
	 *  获取地区树
	 *  @param
	 *	@return 			: java.util.List<com.xfs.bs.util.TreeObject>
	 *  @createDate  	: 2016-11-11 14:01
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:01
	 *  @updateAuthor  :
	 */
	public List<TreeObject> findAreaTree();

	/**
	 *  根据bstype获取地区列表
	 *	@return 			: java.util.Map<java.lang.Integer,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:06
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findBsAreasByBsType(String bsType);

	/**
	 *  根据条件获取地区树
	 *  @param
	 *	@return 			: java.util.List<com.xfs.bs.util.TreeObject>
	 *  @createDate  	: 2016-11-11 14:01
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:01
	 *  @updateAuthor  :
	 */
	public List<TreeObject> getBsAreaList(BsArea bsArea);


	/**
	 *  根据条件获取地区树
	 *  @param
	 *	@return 			: java.util.List<com.xfs.bs.util.TreeObject>
	 *  @createDate  	: 2016-11-11 14:01
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:01
	 *  @updateAuthor  :
	 */
	public List<AreaTree> getAreaTree(BsArea bs);

	/**
	 *  根据条件获取城市列表
	 *  @param   bsArea
	 *	@return 			: java.util.List<com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:07
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:07
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAllBsAreaCity(BsArea bsArea);

	/**
	 *  获取已经支持的OpenList权限地区
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	public List<BsArea> queryOpenListAreaAuthList();

	/**
	 *  获取地区列表 以Map形式 key--地区名称
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	public Map<String,List<BsArea>> queryOpenListAreaAuthMap();

	/**
	 *  获取地区列表 以Map形式 key--地区ID
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	public Map<Integer,BsArea> queryOLAreaAuthMap();

	/**
	 *  获取地区列表 以Map形式 key--地区名称
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	public Map<String,BsArea> queryOLAreaNameMap();

	/**
	 *  缓存地区
	 *	@return 			: java.util.Map<java.lang.Integer,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:06
	 *  @updateAuthor  :
	 */
	public Map<Integer, BsArea> getRedisAreaMap();

	/**
	 *  根据产品ID获取地区列表
	 *  @param   productId
	 *	@return 			: java.util.List<com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAreaByProductId(Integer productId);

	/**
	 *  根据orderId获取地区列表
	 *  @param   orderId
	 *	@return 			: java.util.List<com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:03
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:03
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAreaByOrderId(Integer orderId);
	/**
	 * 根据拼音获取ID
	 *  @param areaPinyin
	 *  @return 
	 *	@return			: BsArea 
	 *  @createDate		: 2016年11月21日 下午3:19:21
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月21日 下午3:19:21
	 *  @updateAuthor  	:
	 */
	public BsArea getAreaByPinyin(String areaPinyin);

	/**
	 *  获取所有省份列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 11:08
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 11:08
	 *  @updateAuthor  :
	 */
	public List<BsArea> queryAreaProvince(BsArea vo);

	/**
	 *  根据省份获取城市列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 11:21
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 11:21
	 *  @updateAuthor  :
	 */

	public List<BsArea> queryAreasCity(BsArea vo);


	public BsArea queryAreaByAreaCode(BsArea vo);

	/**
	 *  获取城市列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/12 10:56
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/12 10:56
	 *  @updateAuthor  :
	 */

	public List<BsArea> getAreaCityList(BsArea vo);
	/**
	 * 通过名称查询地区
	 * @param bsArea
	 * @return
	 */
	public List<BsArea> getBsAreaByName(BsArea bsArea);

	/**
	 * 通过cpid查询相关联的城市
	 * @param cpId
	 * @return
     */
	public List<BsArea> queryAreaByScheme(ContextInfo cti);
	
	/**
	 * 通过cpid查询相关联的城市和方案
	 *  @param isService 是否服务商，   0:自服， 1:服务商
	 *  @return 
	 *	@return 			: List<AreaSchemeVo> 
	 *  @createDate  	: 2017年3月31日 下午2:54:26
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月31日 下午2:54:26
	 *  @updateAuthor  :
	 */
	public List<AreaSchemeVo> findAreaSchemeByOrg(ContextInfo cti, Integer isService);

	/**
	 *  通过areaId查询相关联的方案
	 *  @return
	 *	@return 			: List<AreaSchemeVo>
	 *  @createDate  	: 2017年7月10日 下午2:55:53
	 *  @author         	: quanjiahua@xinfushe.com
	 *  @version        	: v1.0
	 */
	public List<AreaSchemeVo> findSchemeByArea(ContextInfo cti, Integer areaId, Integer isService);
	
	/**
	 * 获取方案中不存在的城市
	 *  @param bsArea
	 *  @return 
	 *	@return 			: List<BsArea> 
	 *  @createDate  	: 2017年4月19日 下午8:11:06
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月19日 下午8:11:06
	 *  @updateAuthor  :
	 */
	public List<BsArea> findNotInArea(ContextInfo cti,BsArea bsArea);
	/**
	 * 查询 所有城市接口
	 *
	 * @author lifq
	 *
	 * 2017年7月27日  下午3:19:05
	 */
	public List<BsArea> findALLChildArea(ContextInfo cti,BsArea bsArea);
	
	/**
	 * 获取省份直辖市排在前面
	 *  @param pbBsArea
	 *  @return 
	 *	@return 			: List<BsArea> 
	 *  @createDate  	: 2017年8月23日 下午2:19:15
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月23日 下午2:19:15
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAllProvince(BsArea pbBsArea);
}
