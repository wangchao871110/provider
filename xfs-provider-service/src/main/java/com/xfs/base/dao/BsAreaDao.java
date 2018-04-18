package com.xfs.base.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.acc.dto.AreaSchemeVo;
import com.xfs.base.model.BsArea;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;


/**
 * BsAreaDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/08 14:31:55
 */
@Repository
public class BsAreaDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_AREA.CountFindAllBS_AREA", null);
        return ret.intValue();
    }

    public BsArea findByPK(BsArea obj) {
        Object ret = queryForObject("BS_AREA.FindByPK", obj);
        if (ret != null)
            return (BsArea) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<BsArea> freeFind(BsArea obj) {
        return queryForList("BS_AREA.FreeFindBS_AREA", obj);
    }

    public int countFreeFind(BsArea obj) {
        Integer ret = (Integer) queryForObject("BS_AREA.CountFreeFindBS_AREA", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<BsArea> freeFind(BsArea obj, int pageIndex, int pageSize) {
        return getPaginatedList("BS_AREA.FreeFindBS_AREA", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<BsArea> freeFind(BsArea obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsArea) {
            _hashmap = ((BsArea) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("BS_AREA.FreeFindBS_AREAOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<BsArea> freeFind(BsArea obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsArea) {
            _hashmap = ((BsArea) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("BS_AREA.FreeFindBS_AREAOrdered", _hashmap, pageIndex, pageSize);
    }

	public int saveAll(ContextInfo cti, Collection<BsArea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsArea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsArea oneObj = (BsArea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsArea vo) {
        BsArea obj = (BsArea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsArea obj) {

		obj.fixup();
        return insert(cti, "BS_AREA.InsertBS_AREA", obj );
	}

	public int update(ContextInfo cti, BsArea obj) {

		obj.fixup();
		return update(cti, "BS_AREA.UpdateBS_AREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsArea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_AREA.DeleteBS_AREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsArea obj) {

        obj.fixup();
        BsArea oldObj = ( BsArea )queryForObject("BS_AREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_AREA.DeleteBS_AREA", oldObj );

	}

	public boolean exists(BsArea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsArea obj = (BsArea) vo;

        keysFilled = keysFilled && ( null != obj.getAreaId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_AREA.CountBS_AREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/08 14:31:55


	/**
	 * 精确查找
	 * @param obj
	 * @return
     */
	@SuppressWarnings("unchecked")
	public List<BsArea> freeFindEq(BsArea obj) {
		return queryForList("BS_AREA.FreeFindBS_AREAEq", obj);
	}

	/**
	 *  获取所有城市并根据城市code排序
	 * @param obj
	 * @return
     */
	public List<BsArea> freeFindAll(BsArea obj) {
		return queryForList("BS_AREA.FreeFindBS_AREA_ALL", obj );
	}

	public  List<Map<String,Object>> findBsAreasByBsType(String bsType){
		HashMap<String,Object> hashMap= new HashMap<>();
		hashMap.put("bsType",bsType);
		return queryForList("BS_AREA.findBsAreasByBsType",hashMap);
	}


	public List<BsArea> getBsAreaList(BsArea obj){
		return queryForList("BS_AREA.getBsAreaList",obj);
	}

	public List<BsArea> findAllBsAreaCity(BsArea bsArea){
		return queryForList("BS_AREA.findAllBsAreaCity",bsArea);
	}
	
	/** cp合并 王超 sta*/
	/**
	 *  根据地区ID查询，地区以及地区下的区县
	 * @param areas
	 * @return
     */
	public List<BsArea> queryOpenListAreaAuthList(Integer[] areas ){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("areas",areas);
		return queryForList("BS_AREA.FreeFindOL_AreaAuthList",hashMap);
	}

	/**
	 *  查询openlistt所支持的地区
	 * @return
     */
	public Integer[] queryOpenListSupportAreas(){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<BsArea> areaList = queryForList("BS_AREA.FreeFindOL_Support_Areas",hashMap);
		List<Integer> areas = new ArrayList<Integer>();
		if(null != areaList && !areaList.isEmpty()){
			for(BsArea area : areaList){
				areas.add(area.getAreaId());
			}
			return areas.toArray(new Integer[areas.size()]);
		}
		return new Integer[]{};
	}
	/** cp合并 王超 end*/
	
	/** cs合并 王超 sta*/
	//无
	/** cs合并 王超 end*/
	
	/** sps合并 王超 sta*/
	@SuppressWarnings("unchecked")
	public List<BsArea> findAreaByProductId(Integer productId) {
		return queryForList("BS_AREA.FindAreaByProductId",productId);
	}

	@SuppressWarnings("unchecked")
	public List<BsArea> findAreaByOrderId(Integer orderId) {
		return queryForList("BS_AREA.findAreaByOrderId",orderId);
	}
	/** sps合并 王超 end*/

	/**  
	 *  获取省份列表
	 *  @param
	 * @return    : 
	 *  @createDate   : 2016/12/7 11:11
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 11:11
	 *  @updateAuthor  :
	 */

	public List<BsArea> queryAreaProvince(BsArea vo){
		return queryForList("BS_AREA.queryAreaProvince",vo);
	}

	/**
	 *  获取城市列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 11:22
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 11:22
	 *  @updateAuthor  :
	 */
	public List<BsArea> queryAreasCity(BsArea vo){
		return queryForList("BS_AREA.queryAreasCity",vo);
	}


	/**
	 *  获取所有城市列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/12 14:18
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/12 14:18
	 *  @updateAuthor  :
	 */
    public List<BsArea> getAreaCityList(BsArea vo){
        return queryForList("BS_AREA.getAreaCityList",vo);
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
		Object ret = queryForObject("BS_AREA.queryAreaByAreaCode", vo );
		if(ret !=null)
			return (BsArea)ret;
		else
			return null;
	}

	/**
	 * 根据城市类型查询城市列表(areaid , areaname)
	 * @param vo
	 * @return
	 */
	public List<BsArea> queryAreaByAreaType(BsArea vo){
		List<BsArea> list = queryForList("BS_AREA.queryAreaByAreaType", vo);
			return list;
	}

	/**
	 *  获取地区列表--排序后的
	 *  @param
	 *	@return 			: java.util.List<com.xfs.base.model.BsArea>
	 *  @createDate  	: 2016-12-14 17:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-14 17:29
	 *  @updateAuthor  :
	 */
	public List<BsArea> getBsAreaTree(){
		return queryForList("BS_AREA.getBsAreaTree",null);
	}

	/**
	 * 通过名称查询地区
	 * @param bsArea
	 * @return
	 */
	public List<BsArea> getBsAreaByName(BsArea bsArea){
		return queryForList("BS_AREA.queryAreaByAreaName",bsArea);
	}

	/**
	 * 通过cpid查询相关联的城市
	 * @return
	 */
	public List<BsArea> queryAreaByScheme(ContextInfo cti){
		Map map = new HashMap();
		map.put("cpId", cti.getOrgId());
		map.put("authority", cti.getAuthority());
		return queryForList("BS_AREA.queryAreaByScheme",map);
	}

    /**
     * @return
     */
    public BsArea getBsAreaListByAreaId(Integer areaId) {

        Object ret = queryForObject("BS_AREA.getBsAreaListByAreaId", areaId);
        if (ret != null)
            return (BsArea) ret;
        else
            return null;

    }


    /**
     * 根据 城市id 查询城市下的所有的区
     *
     * @return
     */
    public List<BsArea> getAreaListByCityId(Integer cityId) {
        return queryForList("BS_AREA.getAreaListByCityId", cityId);
    }

    /**
     * 通过cpid查询相关联的城市和方案
	 * @param isService 是否服务商，   0:自服， 1:服务商
     *  @return
     *	@return 			: List<AreaSchemeVo> 
     *  @createDate  	: 2017年3月31日 下午2:55:53
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年3月31日 下午2:55:53
     *  @updateAuthor  :
     */
	public List<AreaSchemeVo> findAreaSchemeByOrg(ContextInfo cti, Integer isService) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("cpId",cti.getOrgId());
		hashMap.put("authority", cti.getAuthority());
		hashMap.put("isService", isService);

		List<AreaSchemeVo> list = queryForList("BS_AREA.findAreaSchemeByOrg", hashMap);
		if(list!=null && list.size()>0){
			for (AreaSchemeVo vo : list){
				vo.setAuthority(null);
				vo.setCpId(null);
				vo.setIsServiceByArea(null);
			}
		}

		return list;
	}

	/**
	 *  通过areaId查询相关联的方案
	 *  @return
	 *	@return 			: List<AreaSchemeVo>
	 *  @createDate  	: 2017年7月10日 下午2:55:53
	 *  @author         	: quanjiahua@xinfushe.com
	 *  @version        	: v1.0
	 */
	public List<AreaSchemeVo> findSchemeByArea(ContextInfo cti, Integer areaId, Integer isService) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("cpId",cti.getOrgId());
		hashMap.put("authority", cti.getAuthority());
		hashMap.put("areaId", areaId);
		hashMap.put("isService", isService);
		return queryForList("BS_AREA.findSchemeByArea", hashMap);
	}

	/**
	 * 获取方案中不存在的城市
	 *  @return
	 *	@return 			: List<BsArea> 
	 *  @createDate  	: 2017年4月19日 下午8:12:30
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月19日 下午8:12:30
	 *  @updateAuthor  :
	 */
	public List<BsArea> findNotInArea(ContextInfo cti,BsArea obj) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("cpId",cti.getOrgId());
		hashMap.put("parent",obj.getParent());
		return queryForList("BS_AREA.findNotInArea", hashMap);
	}
	/**
	 * 查询 所有城市接口
	 *
	 * @author lifq
	 *
	 * 2017年7月27日  下午3:19:05
	 */
	public List<BsArea> findALLChildArea(ContextInfo cti,BsArea obj){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("parent",obj.getParent());
		return queryForList("BS_AREA.findALLChildArea", hashMap);
	}

	/**
	 * 获取省份直辖市排在前面
	 *  @param vo
	 *  @return 
	 *	@return 			: List<BsArea> 
	 *  @createDate  	: 2017年8月23日 下午2:21:01
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月23日 下午2:21:01
	 *  @updateAuthor  :
	 */
	public List<BsArea> findAllProvince(BsArea vo) {
		return queryForList("BS_AREA.findAllProvince", vo);
	}


}
