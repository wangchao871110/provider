package com.xfs.analysis.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.analysis.dto.SysAnalysisBustype;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysAnalysisBustypeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:24
 */
@Repository
public class SysAnalysisBustypeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_BUSTYPE.CountFindAllSYS_ANALYSIS_BUSTYPE", null );
        return ret.intValue();
	}

	public SysAnalysisBustype findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("SYS_ANALYSIS_BUSTYPE.FindByPK", _hashmap );
		if(ret !=null)
			return (SysAnalysisBustype)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisBustype> freeFind(SysAnalysisBustype obj) {
		return queryForList("SYS_ANALYSIS_BUSTYPE.FreeFindSYS_ANALYSIS_BUSTYPE", obj );
	}

	public int countFreeFind(SysAnalysisBustype obj) {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_BUSTYPE.CountFreeFindSYS_ANALYSIS_BUSTYPE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisBustype> freeFind(SysAnalysisBustype obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ANALYSIS_BUSTYPE.FreeFindSYS_ANALYSIS_BUSTYPE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisBustype> freeFind(SysAnalysisBustype obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisBustype){
	    	_hashmap = ((SysAnalysisBustype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ANALYSIS_BUSTYPE.FreeFindSYS_ANALYSIS_BUSTYPEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisBustype> freeFind(SysAnalysisBustype obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisBustype){
	    	_hashmap = ((SysAnalysisBustype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ANALYSIS_BUSTYPE.FreeFindSYS_ANALYSIS_BUSTYPEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SysAnalysisBustype> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysAnalysisBustype> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysAnalysisBustype oneObj = (SysAnalysisBustype)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SysAnalysisBustype vo) {
        SysAnalysisBustype obj = (SysAnalysisBustype) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, SysAnalysisBustype obj) {

		obj.fixup();
        return insert(cti,"SYS_ANALYSIS_BUSTYPE.InsertSYS_ANALYSIS_BUSTYPE", obj );
	}

	public int update(ContextInfo cti,SysAnalysisBustype obj) {

		obj.fixup();
		return update(cti, "SYS_ANALYSIS_BUSTYPE.UpdateSYS_ANALYSIS_BUSTYPE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SysAnalysisBustype obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_ANALYSIS_BUSTYPE.DeleteSYS_ANALYSIS_BUSTYPE", obj );

	}

	public int removeObjectTree(ContextInfo cti,SysAnalysisBustype obj) {

        obj.fixup();
        SysAnalysisBustype oldObj = ( SysAnalysisBustype )queryForObject("SYS_ANALYSIS_BUSTYPE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_ANALYSIS_BUSTYPE.DeleteSYS_ANALYSIS_BUSTYPE", oldObj );

	}

	public boolean exists(SysAnalysisBustype vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysAnalysisBustype obj = (SysAnalysisBustype) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ANALYSIS_BUSTYPE.CountSYS_ANALYSIS_BUSTYPE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:24
	
	
	
}
