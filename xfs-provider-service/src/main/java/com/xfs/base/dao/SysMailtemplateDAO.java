package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysMailtemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysMailtemplateDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/08 17:21:48
 */
@Repository
public class SysMailtemplateDAO extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_MAILTEMPLATE.CountFindAllSYS_MAILTEMPLATE", null );
        return ret.intValue();
	}

	public SysMailtemplate findByPK(SysMailtemplate obj) {
		Object ret = queryForObject("SYS_MAILTEMPLATE.FindByPK", obj );
		if(ret !=null)
			return (SysMailtemplate)ret;
		else 
			return null;
	}
	
	public List freeFind(SysMailtemplate obj) {
		return queryForList("SYS_MAILTEMPLATE.FreeFindSYS_MAILTEMPLATE", obj );
	}

	public int countFreeFind(SysMailtemplate obj) {
        Integer ret = (Integer) queryForObject("SYS_MAILTEMPLATE.CountFreeFindSYS_MAILTEMPLATE", obj );
        return ret.intValue();
	}

	public List freeFind(SysMailtemplate obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_MAILTEMPLATE.FreeFindSYS_MAILTEMPLATE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysMailtemplate obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailtemplate){
	    	_hashmap = ((SysMailtemplate)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_MAILTEMPLATE.FreeFindSYS_MAILTEMPLATEOrdered", _hashmap);
	}

	public List freeFind(SysMailtemplate obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailtemplate){
	    	_hashmap = ((SysMailtemplate)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_MAILTEMPLATE.FreeFindSYS_MAILTEMPLATEOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysMailtemplate> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysMailtemplate oneObj = (SysMailtemplate)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysMailtemplate vo) {
        SysMailtemplate obj = (SysMailtemplate) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysMailtemplate obj) {

		obj.fixup();
        insert(cti, "SYS_MAILTEMPLATE.InsertSYS_MAILTEMPLATE", obj );
	}

	public void update(ContextInfo cti, SysMailtemplate obj) {

		obj.fixup();
		update(cti, "SYS_MAILTEMPLATE.UpdateSYS_MAILTEMPLATE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysMailtemplate obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_MAILTEMPLATE.DeleteSYS_MAILTEMPLATE", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysMailtemplate obj) {

        obj.fixup();
        SysMailtemplate oldObj = ( SysMailtemplate )queryForObject("SYS_MAILTEMPLATE.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_MAILTEMPLATE.DeleteSYS_MAILTEMPLATE", oldObj );

	}

	public boolean exists(SysMailtemplate vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysMailtemplate obj = (SysMailtemplate) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_MAILTEMPLATE.CountSYS_MAILTEMPLATE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
}
