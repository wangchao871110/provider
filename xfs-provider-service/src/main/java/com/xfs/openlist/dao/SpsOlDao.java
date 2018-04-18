package com.xfs.openlist.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.openlist.model.SpsOl;

/**
 * SpsOlDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:55
 */
@Repository
public class SpsOlDao extends BaseSqlMapDao {

	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_OL.CountFindAllSPS_OL", null );
        return ret.intValue();
	}

	public SpsOl findByPK(SpsOl obj) {
		Object ret = queryForObject("SPS_OL.FindByPK", obj );
		if(ret !=null)
			return (SpsOl)ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsOl> freeFind(SpsOl obj) {
		return queryForList("SPS_OL.FreeFindSPS_OL", obj );
	}

	public int countFreeFind(SpsOl obj) {
        Integer ret = (Integer) queryForObject("SPS_OL.CountFreeFindSPS_OL", obj );
        return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsOl> freeFind(SpsOl obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_OL.FreeFindSPS_OL", obj, pageIndex, pageSize );
	}

	@SuppressWarnings("unchecked")
	public List<SpsOl> freeFind(SpsOl obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOl){
	    	_hashmap = ((SpsOl)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );

		return queryForList("SPS_OL.FreeFindSPS_OLOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsOl> freeFind(SpsOl obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOl){
	    	_hashmap = ((SpsOl)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );

		return getPaginatedList("SPS_OL.FreeFindSPS_OLOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsOl> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsOl> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsOl oneObj = (SpsOl)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsOl vo) {
        SpsOl obj = (SpsOl) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsOl obj) {

		obj.fixup();
        return insert(cti, "SPS_OL.InsertSPS_OL", obj );
	}

	public int update(ContextInfo cti, SpsOl obj) {

		obj.fixup();
		return update(cti, "SPS_OL.UpdateSPS_OL", obj );

	}

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsOl obj) {

        if ( obj == null ) {
            return 0;
        }

		obj.fixup();

        return delete(cti, "SPS_OL.DeleteSPS_OL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsOl obj) {

        obj.fixup();
        SpsOl oldObj = ( SpsOl )queryForObject("SPS_OL.FindByPK", obj );
        if ( oldObj == null ) {
            return 0;
        }


        return delete(cti, "SPS_OL.DeleteSPS_OL", oldObj );

	}

	public boolean exists(SpsOl vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SpsOl obj = (SpsOl) vo;

        keysFilled = keysFilled && ( null != obj.getOlId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_OL.CountSPS_OL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

	/**
	 * 条件搜索openlist总数
	 *
	 * @param obj
	 * @param searchWord
	 * @return
     */
	public int countCustomFind(SpsOl obj, String searchWord, String areaids) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj != null){
			_hashmap = obj.toHashMap();
		}
		if (searchWord != null && !searchWord.trim().isEmpty()) {
			_hashmap.put("_searchWord", searchWord );
		}
		if(null != areaids && !areaids.trim().isEmpty()){
			_hashmap.put("areaids",areaids);
		}
		Integer ret = (Integer) queryForObject("SPS_OL.CountCustomFindSPS_OL", _hashmap);
		return ret.intValue();
	}

	/**
	 * 条件搜索openlist
	 *
	 * @param obj
	 * @param searchWord
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	public List<Map<String, Object>> customFind(SpsOl obj, String searchWord, String areaids,Integer pageIndex, Integer pageSize) {
		if (pageIndex == null) pageIndex = 1;
		if (pageSize == null) pageSize = PAGE_SIZE;
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj != null){
			_hashmap = obj.toHashMap();
		}
		if (searchWord != null && !searchWord.trim().isEmpty()) {
			_hashmap.put("_searchWord", searchWord );
		}
		if(null != areaids && !areaids.trim().isEmpty()){
			_hashmap.put("areaids",areaids);
		}
		return getPaginatedList("SPS_OL.CustomFindSPS_OL", _hashmap, pageIndex, pageSize );
	}


	/**
	 * 条件搜索openlist总数
	 *
	 * @param obj
	 * @param searchWord
	 * @return
	 */
	public int queryMyOlListCount(SpsOl obj, String searchWord, String areaids) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj != null){
			_hashmap = obj.toHashMap();
		}
		if (searchWord != null && !searchWord.trim().isEmpty()) {
			_hashmap.put("_searchWord", searchWord );
		}
		if(null != areaids && !areaids.trim().isEmpty()){
			_hashmap.put("areaids",areaids);
		}
		Integer ret = (Integer) queryForObject("SPS_OL.CountMYSPS_OL", _hashmap);
		return ret.intValue();
	}

	/**
	 * 条件搜索openlist
	 *
	 * @param obj
	 * @param searchWord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> queryMyOlList(SpsOl obj, String searchWord, String areaids,Integer pageIndex, Integer pageSize) {
		if (pageIndex == null) pageIndex = 1;
		if (pageSize == null) pageSize = PAGE_SIZE;
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj != null){
			_hashmap = obj.toHashMap();
		}
		if (searchWord != null && !searchWord.trim().isEmpty()) {
			_hashmap.put("_searchWord", searchWord );
		}
		if(null != areaids && !areaids.trim().isEmpty()){
			_hashmap.put("areaids",areaids);
		}
		return getPaginatedList("SPS_OL.MYSPS_OL", _hashmap, pageIndex, pageSize );
	}


    /**
     * 根据业务类型和区域查询
     *
     * @param vo
     * @return
     */
	public SpsOl findByBstypeAndArea(SpsOl vo) {
		return (SpsOl)queryForObject("SPS_OL.findByBstypeAndArea", vo );
	}

    /**
     * findOlByPK
     *
     * @param obj
     * @return
     */
	public Map<String, Object> findOlByPK(SpsOl obj) {
		return (Map<String, Object>)queryForObject("SPS_OL.FindSPS_OLByPK", obj );
	}

	public List<Map<String,Object>> findSpOIUsedCount(int sp_id){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("sp_id",sp_id);
		return queryForList("FindSPS_OLSum",hashMap);
	}

	/**
	 *  根据模板类型查询OpenList模板，携带城市和材料总数
	 * @param ol
	 * @return
	 */
	public Map<String,List<Map<String,Object>>> queryAllOLByMateialType(SpsOl ol){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		if(null != ol.getMaterialPType()) {
			hashMap.put("ptype", ol.getMaterialPType());
			hashMap.put("material_type",1);
		}
		if(null != ol.getMaterialBType()){
			hashMap.put("btype",ol.getMaterialBType());
			hashMap.put("material_type",1);
		}
		List<Map<String,Object>> olListMap = queryForList("FindSPS_ALL_OL_M_TYPE",hashMap);
		Map<String,List<Map<String,Object>>> result = new LinkedHashMap<>();
		if(null != olListMap){
			for(Map<String,Object> olmap : olListMap){
				String fullname = String.valueOf(olmap.get("fullname"));
				List<Map<String,Object>> ollist = result.get(fullname);
				if(null == ollist){
					ollist = new ArrayList<Map<String,Object>>();
					result.put(fullname,ollist);
				}
				ollist.add(olmap);
			}
		}
		return result;
	}

	/**
	 * 获取区域下的业务材料列表
	 * @param areaId
	 * @return
     */
	public List<Map<String,Object>> queryAreaOlList(Integer areaId){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("area_id",areaId);
		return queryForList("FindSPS_AREA_OL_LIST",hashMap);
	}

	public Map<String,Object> queryOLById(Integer olId){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("olId",olId);
		List<Map<String,Object>> olListMap = queryForList("FindSPS_OL_BY_ID",hashMap);
		if(null != olListMap && !olListMap.isEmpty())
			return olListMap.get(0);
		return null;
	}

}
