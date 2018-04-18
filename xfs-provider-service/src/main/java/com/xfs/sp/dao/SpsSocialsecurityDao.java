package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsSocialsecurity;

/**
 * SpsSocialsecurityDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/20 21:09:23
 */
@Repository
public class SpsSocialsecurityDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SOCIALSECURITY.CountFindAllSPS_SOCIALSECURITY", null );
        return ret.intValue();
	}

	public SpsSocialsecurity findByPK(SpsSocialsecurity obj) {
		Object ret = queryForObject("SPS_SOCIALSECURITY.FindByPK", obj );
		if(ret !=null)
			return (SpsSocialsecurity)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSocialsecurity> freeFind(SpsSocialsecurity obj) {
		return queryForList("SPS_SOCIALSECURITY.FreeFindSPS_SOCIALSECURITY", obj );
	}

	public int countFreeFind(SpsSocialsecurity obj) {
        Integer ret = (Integer) queryForObject("SPS_SOCIALSECURITY.CountFreeFindSPS_SOCIALSECURITY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSocialsecurity> freeFind(SpsSocialsecurity obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SOCIALSECURITY.FreeFindSPS_SOCIALSECURITY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSocialsecurity> freeFind(SpsSocialsecurity obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSocialsecurity){
	    	_hashmap = ((SpsSocialsecurity)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SOCIALSECURITY.FreeFindSPS_SOCIALSECURITYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSocialsecurity> freeFind(SpsSocialsecurity obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSocialsecurity){
	    	_hashmap = ((SpsSocialsecurity)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SOCIALSECURITY.FreeFindSPS_SOCIALSECURITYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsSocialsecurity> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsSocialsecurity> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsSocialsecurity oneObj = (SpsSocialsecurity)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsSocialsecurity vo) {
        SpsSocialsecurity obj = (SpsSocialsecurity) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsSocialsecurity obj) {

		obj.fixup();
        return insert(cti, "SPS_SOCIALSECURITY.InsertSPS_SOCIALSECURITY", obj );
	}

	public int update(ContextInfo cti, SpsSocialsecurity obj) {

		obj.fixup();
		return update(cti, "SPS_SOCIALSECURITY.UpdateSPS_SOCIALSECURITY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsSocialsecurity obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SOCIALSECURITY.DeleteSPS_SOCIALSECURITY", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsSocialsecurity obj) {

        obj.fixup();
        SpsSocialsecurity oldObj = ( SpsSocialsecurity )queryForObject("SPS_SOCIALSECURITY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SOCIALSECURITY.DeleteSPS_SOCIALSECURITY", oldObj );

	}

	public boolean exists(SpsSocialsecurity vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsSocialsecurity obj = (SpsSocialsecurity) vo;

        keysFilled = keysFilled && ( null != obj.getSsId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SOCIALSECURITY.CountSPS_SOCIALSECURITY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/20 21:09:23

	/**
	 * 根据条件查询社保报表总数
	 *
	 * @param map
	 * @return
     */
	public int countFreeFind(Map<String, Object> map) {
		Integer ret = (Integer) queryForObject("SPS_SOCIALSECURITY.CountFindSPS_SOCIALSECURITY", map );
		return ret.intValue();
	}

	/**
	 * 根据条件查询社保报表总数
	 *
	 * @param map
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	public List<Map<String, Object>> freeFind(Map<String, Object> map, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SOCIALSECURITY.FindSPS_SOCIALSECURITY", map, pageIndex, pageSize);
	}

	/**
	 * 查询待处理或者异常任务
	 *
	 * @return
     */
	public List<Map<String, Object>> freeFindUnSuccessReg() {
		return queryForList("SPS_SOCIALSECURITY.FindSP_CM_REG", null);
	}

	/**
	 * 每月执行一次查询社保明细
	 *
	 * @return
	 */
	public List<Map<String, Object>> onceFindForSS(String code,String insuranceFundType) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("insuranceFundType", insuranceFundType);
		return queryForList("SPS_SOCIALSECURITY.FreeFindSP_CM_REG_NOCE", map);
	}
	
}
