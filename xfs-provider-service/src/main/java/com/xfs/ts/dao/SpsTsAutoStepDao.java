package com.xfs.ts.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsAutoStep;

/**
 * SpsTsAutoStepDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/14 12:24:42
 */
@Repository
public class SpsTsAutoStepDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TS_AUTO_STEP.CountFindAllSPS_TS_AUTO_STEP", null );
        return ret.intValue();
	}

	public SpsTsAutoStep findByPK(SpsTsAutoStep obj) {
		Object ret = queryForObject("SPS_TS_AUTO_STEP.FindByPK", obj );
		if(ret !=null)
			return (SpsTsAutoStep)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoStep> freeFind(SpsTsAutoStep obj) {
		return queryForList("SPS_TS_AUTO_STEP.FreeFindSPS_TS_AUTO_STEP", obj );
	}

	public int countFreeFind(SpsTsAutoStep obj) {
        Integer ret = (Integer) queryForObject("SPS_TS_AUTO_STEP.CountFreeFindSPS_TS_AUTO_STEP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoStep> freeFind(SpsTsAutoStep obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TS_AUTO_STEP.FreeFindSPS_TS_AUTO_STEP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoStep> freeFind(SpsTsAutoStep obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsAutoStep){
	    	_hashmap = ((SpsTsAutoStep)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_TS_AUTO_STEP.FreeFindSPS_TS_AUTO_STEPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoStep> freeFind(SpsTsAutoStep obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsAutoStep){
	    	_hashmap = ((SpsTsAutoStep)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_TS_AUTO_STEP.FreeFindSPS_TS_AUTO_STEPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsTsAutoStep> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsTsAutoStep> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsTsAutoStep oneObj = (SpsTsAutoStep)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsTsAutoStep vo) {
        SpsTsAutoStep obj = (SpsTsAutoStep) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsTsAutoStep obj) {

		obj.fixup();
        return insert(cti, "SPS_TS_AUTO_STEP.InsertSPS_TS_AUTO_STEP", obj );
	}

	public int update(ContextInfo cti, SpsTsAutoStep obj) {

		obj.fixup();
		return update(cti, "SPS_TS_AUTO_STEP.UpdateSPS_TS_AUTO_STEP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsTsAutoStep obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_TS_AUTO_STEP.DeleteSPS_TS_AUTO_STEP", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsTsAutoStep obj) {

        obj.fixup();
        SpsTsAutoStep oldObj = ( SpsTsAutoStep )queryForObject("SPS_TS_AUTO_STEP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_TS_AUTO_STEP.DeleteSPS_TS_AUTO_STEP", oldObj );

	}

	public boolean exists(SpsTsAutoStep vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsTsAutoStep obj = (SpsTsAutoStep) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_TS_AUTO_STEP.CountSPS_TS_AUTO_STEP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:42
	
	public int removeByPageId(ContextInfo cti, Integer pageId) {

        if ( pageId == null || pageId == 0) { 
            return 0; 
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageId", pageId);
		
        return delete(cti, "SPS_TS_AUTO_STEP.DeleteSPS_TS_AUTO_STEPByPageId", hashMap );

	}
	
	
}
