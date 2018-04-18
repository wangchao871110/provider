package com.xfs.ts.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsOpresult;

/**
 * SpsTsOpresultDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/04 16:10:19
 */
@Repository
public class SpsTsOpresultDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TS_OPRESULT.CountFindAllSPS_TS_OPRESULT", null );
        return ret.intValue();
	}

	public SpsTsOpresult findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("SPS_TS_OPRESULT.FindByPK", _hashmap );
		if(ret !=null)
			return (SpsTsOpresult)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsOpresult> freeFind(SpsTsOpresult obj) {
		return queryForList("SPS_TS_OPRESULT.FreeFindSPS_TS_OPRESULT", obj );
	}

	public int countFreeFind(SpsTsOpresult obj) {
        Integer ret = (Integer) queryForObject("SPS_TS_OPRESULT.CountFreeFindSPS_TS_OPRESULT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsOpresult> freeFind(SpsTsOpresult obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TS_OPRESULT.FreeFindSPS_TS_OPRESULT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsOpresult> freeFind(SpsTsOpresult obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsOpresult){
	    	_hashmap = ((SpsTsOpresult)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_TS_OPRESULT.FreeFindSPS_TS_OPRESULTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsOpresult> freeFind(SpsTsOpresult obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsOpresult){
	    	_hashmap = ((SpsTsOpresult)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_TS_OPRESULT.FreeFindSPS_TS_OPRESULTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsTsOpresult> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsTsOpresult> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsTsOpresult oneObj = (SpsTsOpresult)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsTsOpresult vo) {
        SpsTsOpresult obj = (SpsTsOpresult) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsTsOpresult obj) {

		obj.fixup();
        return insert(cti, "SPS_TS_OPRESULT.InsertSPS_TS_OPRESULT", obj );
	}

	public int update(ContextInfo cti, SpsTsOpresult obj) {

		obj.fixup();
		return update(cti, "SPS_TS_OPRESULT.UpdateSPS_TS_OPRESULT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsTsOpresult obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_TS_OPRESULT.DeleteSPS_TS_OPRESULT", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsTsOpresult obj) {

        obj.fixup();
        SpsTsOpresult oldObj = (SpsTsOpresult)queryForObject("SPS_TS_OPRESULT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_TS_OPRESULT.DeleteSPS_TS_OPRESULT", oldObj );

	}

	public boolean exists(SpsTsOpresult vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsTsOpresult obj = (SpsTsOpresult) vo;

		keysFilled = keysFilled && ( null!= obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_TS_OPRESULT.CountSPS_TS_OPRESULT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/04 16:10:19

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findBySchemeId(Integer schemeId) {
		return queryForList("SPS_TS_OPRESULT.FindBySchemeId", schemeId );
	}
	
}
