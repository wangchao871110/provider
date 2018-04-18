package com.xfs.ts.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsCitycode;

/**
 * SpsTsCitycodeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/12 13:39:03
 */
@Repository
public class SpsTsCitycodeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TS_CITYCODE.CountFindAllSPS_TS_CITYCODE", null );
        return ret.intValue();
	}

	public SpsTsCitycode findByPK(SpsTsCitycode obj) {
		Object ret = queryForObject("SPS_TS_CITYCODE.FindByPK", obj );
		if(ret !=null)
			return (SpsTsCitycode)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsCitycode> freeFind(SpsTsCitycode obj) {
		return queryForList("SPS_TS_CITYCODE.FreeFindSPS_TS_CITYCODE", obj );
	}

	public int countFreeFind(SpsTsCitycode obj) {
        Integer ret = (Integer) queryForObject("SPS_TS_CITYCODE.CountFreeFindSPS_TS_CITYCODE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsCitycode> freeFind(SpsTsCitycode obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TS_CITYCODE.FreeFindSPS_TS_CITYCODE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsCitycode> freeFind(SpsTsCitycode obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsCitycode){
	    	_hashmap = ((SpsTsCitycode)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_TS_CITYCODE.FreeFindSPS_TS_CITYCODEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsCitycode> freeFind(SpsTsCitycode obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsCitycode){
	    	_hashmap = ((SpsTsCitycode)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_TS_CITYCODE.FreeFindSPS_TS_CITYCODEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsTsCitycode> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsTsCitycode> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsTsCitycode oneObj = (SpsTsCitycode)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsTsCitycode vo) {
        SpsTsCitycode obj = (SpsTsCitycode) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsTsCitycode obj) {

		obj.fixup();
        return insert(cti, "SPS_TS_CITYCODE.InsertSPS_TS_CITYCODE", obj );
	}

	public int update(ContextInfo cti, SpsTsCitycode obj) {

		obj.fixup();
		return update(cti, "SPS_TS_CITYCODE.UpdateSPS_TS_CITYCODE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsTsCitycode obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_TS_CITYCODE.DeleteSPS_TS_CITYCODE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsTsCitycode obj) {

        obj.fixup();
        SpsTsCitycode oldObj = ( SpsTsCitycode )queryForObject("SPS_TS_CITYCODE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_TS_CITYCODE.DeleteSPS_TS_CITYCODE", oldObj );

	}

	public boolean exists(SpsTsCitycode vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsTsCitycode obj = (SpsTsCitycode) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_TS_CITYCODE.CountSPS_TS_CITYCODE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/12 13:39:03
	
	public SpsTsCitycode getSpsTsCitycodeByAreaId(int areaId){
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("areaId",areaId);
		Object ret = queryForObject("SPS_TS_CITYCODE.getSpsTsCitycodeByAreaId", hashMap );
		if(ret !=null)
			return (SpsTsCitycode)ret;
		else
			return null;
	}

	public List<Map<String,Object>> getSpsTsCitycode(){
		HashMap<String,Object> hashMap = new HashMap<>();
		List<Map<String, Object>> ret = queryForList("SPS_TS_CITYCODE.getSpsTsCitycode", hashMap );
		if(ret !=null)
			return ret;
		else
			return null;
	}
	
}
