package com.xfs.analysis.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.analysis.dto.SysAnalysisRelation;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysAnalysisRelationDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:31
 */
@Repository
public class SysAnalysisRelationDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_RELATION.CountFindAllSYS_ANALYSIS_RELATION", null );
        return ret.intValue();
	}

	public SysAnalysisRelation findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("SYS_ANALYSIS_RELATION.FindByPK", _hashmap );
		if(ret !=null)
			return (SysAnalysisRelation)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisRelation> freeFind(SysAnalysisRelation obj) {
		return queryForList("SYS_ANALYSIS_RELATION.FreeFindSYS_ANALYSIS_RELATION", obj );
	}

	public int countFreeFind(SysAnalysisRelation obj) {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_RELATION.CountFreeFindSYS_ANALYSIS_RELATION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisRelation> freeFind(SysAnalysisRelation obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ANALYSIS_RELATION.FreeFindSYS_ANALYSIS_RELATION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisRelation> freeFind(SysAnalysisRelation obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisRelation){
	    	_hashmap = ((SysAnalysisRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ANALYSIS_RELATION.FreeFindSYS_ANALYSIS_RELATIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisRelation> freeFind(SysAnalysisRelation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisRelation){
	    	_hashmap = ((SysAnalysisRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ANALYSIS_RELATION.FreeFindSYS_ANALYSIS_RELATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SysAnalysisRelation> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysAnalysisRelation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysAnalysisRelation oneObj = (SysAnalysisRelation)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SysAnalysisRelation vo) {
        SysAnalysisRelation obj = (SysAnalysisRelation) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, SysAnalysisRelation obj) {

		obj.fixup();
        return insert(cti,"SYS_ANALYSIS_RELATION.InsertSYS_ANALYSIS_RELATION", obj );
	}

	public int update(ContextInfo cti,SysAnalysisRelation obj) {

		obj.fixup();
		return update( cti,"SYS_ANALYSIS_RELATION.UpdateSYS_ANALYSIS_RELATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SysAnalysisRelation obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_ANALYSIS_RELATION.DeleteSYS_ANALYSIS_RELATION", obj );

	}

	public int removeObjectTree(ContextInfo cti,SysAnalysisRelation obj) {

        obj.fixup();
        SysAnalysisRelation oldObj = ( SysAnalysisRelation )queryForObject("SYS_ANALYSIS_RELATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_ANALYSIS_RELATION.DeleteSYS_ANALYSIS_RELATION", oldObj );

	}

	public boolean exists(SysAnalysisRelation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysAnalysisRelation obj = (SysAnalysisRelation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ANALYSIS_RELATION.CountSYS_ANALYSIS_RELATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:31
	
	
	
}
