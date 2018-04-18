package com.xfs.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.acc.dto.AreaSchemeVo;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dto.AreaTree;
import com.xfs.base.model.BsArea;
import com.xfs.bs.util.TreeObject;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.StringUtils;

/**
 * 地区服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 13:59
 * @version 	: V1.0
 */
@Service
public class BsAreaServiceImpl implements BsAreaService,IRedisKeys {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsAreaServiceImpl.class);
	
	@Autowired
	private BsAreaDao bsAreaDao;
	public int save(ContextInfo cti, BsArea vo ){
		return bsAreaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsArea vo ){
		return bsAreaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsArea vo ){
		return bsAreaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsArea vo ){
		return bsAreaDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsArea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsAreaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsArea> datas = bsAreaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsArea> findAll(BsArea vo){
		List<BsArea> datas = bsAreaDao.freeFind(vo);
		return datas;
	}

	public  BsArea findByPK(BsArea vo){
		return bsAreaDao.findByPK(vo);
	}

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
	@Override
	public BsArea findbyPK(BsArea vo) {
		return bsAreaDao.findByPK(vo);
	}

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
	@Override
	public List<BsArea> findAllEq(BsArea vo) {
		return bsAreaDao.freeFindEq(vo);
	}

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
	@Override
	public List<TreeObject> findAreaTree() {
		List<BsArea> areaList = bsAreaDao.freeFindAll(null);
		List<TreeObject> treeObjects = new ArrayList<TreeObject>();
		if(null != areaList && !areaList.isEmpty()){
			for(BsArea bsArea : areaList){
				if(bsArea.getParent() == -1 || "DISTRICT".equals(bsArea.getAreaType()))
					continue;
				TreeObject treeObject = new TreeObject();
				treeObject.setPk(bsArea.getAreaId());
				treeObject.setId(String.valueOf(bsArea.getAreaId()));
				treeObject.setpId(String.valueOf(bsArea.getParent()));
				treeObject.setName(bsArea.getName());
				treeObject.setRemark(bsArea.getRemark());
				treeObject.setClick("getMethodList(" + bsArea.getAreaId() + ")");
					treeObjects.add(treeObject);
			}
		}
		return treeObjects;
	}

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
	@Override
	public List<TreeObject> getBsAreaList(BsArea bs) {
		List<BsArea> bsAreaList = bsAreaDao.freeFindAll(bs);
		List<TreeObject> treeObjects = new ArrayList<TreeObject>();
		if(null != bsAreaList && !bsAreaList.isEmpty()){
			for(BsArea bsArea : bsAreaList){
				if(bsArea.getParent() == -1 || "DISTRICT".equals(bsArea.getAreaType()))
					continue;
				TreeObject treeObject = new TreeObject();
				treeObject.setPk(bsArea.getAreaId());
				treeObject.setId(String.valueOf(bsArea.getAreaId()));
				treeObject.setpId(String.valueOf(bsArea.getParent()));
				treeObject.setName(bsArea.getName());
				treeObject.setRemark(bsArea.getRemark());
				treeObject.setClick("getMethodList(" + bsArea.getAreaId() + ")");
				treeObjects.add(treeObject);
			}
		}
		return treeObjects;
	}

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
	@Override
	public List<AreaTree> getAreaTree(BsArea bs) {
		List<BsArea> bsAreaList = bsAreaDao.getBsAreaTree();
		List<AreaTree> areaTreeList = new ArrayList<AreaTree>();
		if(null != bsAreaList && !bsAreaList.isEmpty()){
			for(BsArea bsArea : bsAreaList){
				AreaTree areaTree = new AreaTree();
				areaTree.setAreaCode(bsArea.getAreacode());
				areaTree.setAreaName(bsArea.getName());
				areaTree.setChildArea(new ArrayList<AreaTree>());
				areaTree.setParentId(bsArea.getParent());
				areaTree.setAreaId(bsArea.getAreaId());
				if(bsArea.getParent().equals(0)){
					areaTreeList.add(areaTree);
				}else{
					areaTreeList.contains(areaTree);
				}
			}
		}
		return areaTreeList;
	}

	/**
	 *  根据bstype获取地区列表
	 *	@return 			: java.util.Map<java.lang.Integer,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:06
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,Object>> findBsAreasByBsType(String bsType) {
		List<Map<String,Object>> list  = bsAreaDao.findBsAreasByBsType(bsType);
		return list;
	}


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
	@Override
	public List<BsArea> findAllBsAreaCity(BsArea bsArea) {
		return bsAreaDao.findAllBsAreaCity(bsArea);
	}

	/**
	 *  缓存地区
	 *	@return 			: java.util.Map<java.lang.Integer,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:06
	 *  @updateAuthor  :
	 */
	@Override
	public Map<Integer, BsArea> getRedisAreaMap() {
		if (null == RedisUtil.get(COMMON_AREA_ALLAREAS)) {
			synchronized (COMMON_AREA_ALLAREAS) {
				if (null == RedisUtil.get(COMMON_AREA_ALLAREAS)) {
					List<BsArea> areas = findAll(new BsArea());
					if (areas != null) {
						Map<Integer, BsArea> map = new HashMap<Integer, BsArea>();
						for(BsArea area : areas) {
							map.put(area.getAreaId(), area);
						}
						RedisUtil.set(COMMON_AREA_ALLAREAS, map, 3600);
					}
				}
			}
		}
		Map<Integer, BsArea> map = (Map<Integer, BsArea>) RedisUtil.get(COMMON_AREA_ALLAREAS);
		return map;
	}

	/**
	 *  获取已经支持的OpenList权限地区
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	@Override
	public List<BsArea> queryOpenListAreaAuthList() {
		Integer[] areas  = bsAreaDao.queryOpenListSupportAreas();
		if (null == areas || areas.length == 0)
			return  null;
		return bsAreaDao.queryOpenListAreaAuthList(areas);
	}

	/**
	 *  获取地区列表 以Map形式 key--地区名称
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	@Override
	public Map<String,List<BsArea>> queryOpenListAreaAuthMap() {

		Integer[] areas  = bsAreaDao.queryOpenListSupportAreas();
		if (null == areas || areas.length == 0)
			return  null;
		List<BsArea> areaList = bsAreaDao.queryOpenListAreaAuthList(areas);
		LinkedHashMap<String,List<BsArea>> areaMap = new LinkedHashMap<String,List<BsArea>>();
		Map<String,String> areaNameMap = new HashMap<String,String>();
		for(BsArea area : areaList){
			if(Arrays.asList(areas).contains(area.getAreaId())){
				areaMap.put(area.getName(),new ArrayList<BsArea>());
				areaNameMap.put(String.valueOf(area.getAreaId()),area.getName());
			}else{
				List<BsArea>  bsAreaList =  areaMap.get(areaNameMap.get(String.valueOf(area.getParent())));
				bsAreaList.add(area);
			}
		}
		return areaMap;
	}

	/**
	 *  获取地区列表 以Map形式 key--地区ID
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	@Override
	public Map<Integer,BsArea> queryOLAreaAuthMap(){
		Integer[] areas  = bsAreaDao.queryOpenListSupportAreas();
		if (null == areas || areas.length == 0)
			return  null;
		List<BsArea> areaList = bsAreaDao.queryOpenListAreaAuthList(areas);
		Map<Integer,BsArea> areaNameMap = new HashMap<Integer,BsArea>();
		for(BsArea area : areaList){
			areaNameMap.put(area.getAreaId(),area);
		}
		return areaNameMap;
	}

	/**
	 *  获取地区列表 以Map形式 key--地区名称
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-11-11 14:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:04
	 *  @updateAuthor  :
	 */
	@Override
	public Map<String,BsArea> queryOLAreaNameMap(){
		Integer[] areas  = bsAreaDao.queryOpenListSupportAreas();
		if (null == areas || areas.length == 0)
			return  null;
		List<BsArea> areaList = bsAreaDao.queryOpenListAreaAuthList(areas);
		Map<String,BsArea> areaNameMap = new HashMap<String,BsArea>();
		for(BsArea area : areaList){
			areaNameMap.put(area.getName(),area);
		}
		return areaNameMap;
	}


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
	@Override
	public List<BsArea> findAreaByProductId(Integer productId) {
		return bsAreaDao.findAreaByProductId(productId);
	}

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
	@Override
	public List<BsArea> findAreaByOrderId(Integer orderId) {
		return bsAreaDao.findAreaByOrderId(orderId);
	}
	
	/**
	 * 根据拼音获取类型
	 *
	 * @param pinyin
	 * @return
	 */
	public BsArea getAreaByPinyin(String pinyin) {
		try {
			if (StringUtils.isBlank(pinyin)) {
				return null;
			}
			Object o = RedisUtil.get(BS_AREA);
			if (o != null && o instanceof Map) {
				if(null != ((Map) o).get(pinyin)){
					return (BsArea)((Map) o).get(pinyin);
				}
			}
			BsArea vo = new BsArea();
			List<BsArea> areas = findAll(vo);
			if (areas == null || areas.isEmpty()) {
				return null;
			}
			Map<String, BsArea> areaMap = new HashMap<>();
			for (BsArea area: areas) {
				if (StringUtils.isBlank(area.getPinyin())) {
					continue;
				}
				areaMap.put(area.getPinyin(), area);
			}
			RedisUtil.set(BS_AREA, areaMap, EXPIRE_DAY);
			return areaMap.get(pinyin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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
	public List<BsArea> queryAreaProvince(BsArea vo){
		return bsAreaDao.queryAreaProvince(vo);
		}

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
	public List<BsArea> queryAreasCity(BsArea vo){
		return bsAreaDao.queryAreasCity(vo);
	}

	/**
	 *  根据地区ID查询地区
	 *  @param   vo
	 *	@return 			: com.xfs.base.model.BsArea
	 *  @createDate  	: 2016-12-14 17:03
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-14 17:03
	 *  @updateAuthor  :
	 */
	public BsArea queryAreaByAreaCode(BsArea vo){
		return bsAreaDao.queryAreaByAreaCode(vo);
	}

	public List<BsArea> getAreaCityList(BsArea vo){
		return bsAreaDao.getAreaCityList(vo);
	}

	/**
	 * 通过名称查询地区
	 * @param bsArea
	 * @return
	 */
	public List<BsArea> getBsAreaByName(BsArea bsArea){
		return bsAreaDao.getBsAreaByName(bsArea);
	}
	public List<BsArea> queryAreaByScheme(ContextInfo cti){
		if (null == cti.getOrgId() || cti.getOrgId() == 0)
			return null;
		return bsAreaDao.queryAreaByScheme(cti);
	}
	
	/**
	 * 通过cpid查询相关联的城市和方案
	 *  @param isService 是否服务商，   0:自服， 1:服务商
	 *  @return 
	 *  @createDate  	: 2017年3月31日 下午2:55:19
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月31日 下午2:55:19
	 *  @updateAuthor  	:
	 */
	@Override
	public List<AreaSchemeVo> findAreaSchemeByOrg(ContextInfo cti, Integer isService) {
		if (null == cti.getOrgId() || cti.getOrgId() == 0)
			return null;
		return bsAreaDao.findAreaSchemeByOrg(cti, isService);
	}

	/**
	 *  通过areaId查询相关联的方案
	 *  @return
	 *	@return 			: List<AreaSchemeVo>
	 *  @createDate  	: 2017年7月10日 下午2:55:53
	 *  @author         	: quanjiahua@xinfushe.com
	 *  @version        	: v1.0
	 */
	public List<AreaSchemeVo> findSchemeByArea(ContextInfo cti, Integer areaId, Integer isService){
		if (null == cti.getOrgId() || cti.getOrgId() == 0)
			return null;
		return bsAreaDao.findSchemeByArea(cti, areaId, isService);
	}
	
	/**
	 * 获取方案中不存在的城市
	 *  @param bsArea
	 *  @return 
	 *  @createDate  	: 2017年4月19日 下午8:11:53
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月19日 下午8:11:53
	 *  @updateAuthor  	:
	 */
	@Override
	public List<BsArea> findNotInArea(ContextInfo cti,BsArea bsArea) {
		return bsAreaDao.findNotInArea(cti,bsArea);
	}
	/**
	 * 查询 所有城市接口
	 *
	 * @author lifq
	 *
	 * 2017年7月27日  下午3:19:05
	 */
	public List<BsArea> findALLChildArea(ContextInfo cti,BsArea bsArea){
		return bsAreaDao.findALLChildArea(cti,bsArea);
	}
	
	/**
	 * 获取省份直辖市排在前面
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年8月23日 下午2:20:41
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月23日 下午2:20:41
	 *  @updateAuthor  	:
	 */
	@Override
	public List<BsArea> findAllProvince(BsArea vo) {
		return bsAreaDao.findAllProvince(vo);
	}
}
