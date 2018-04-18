package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysTenantparamlist;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysTenantparamlistDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/14 12:28:00
 */
@Repository
public class SysTenantparamlistDAO extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_TENANTPARAMLIST.CountFindAllSYS_TENANTPARAMLIST", null );
        return ret.intValue();
	}

	public SysTenantparamlist findByPK(SysTenantparamlist obj) {
		Object ret = queryForObject("SYS_TENANTPARAMLIST.FindByPK", obj );
		if(ret !=null)
			return (SysTenantparamlist)ret;
		else 
			return null;
	}
	
	public List freeFind(SysTenantparamlist obj) {
		return queryForList("SYS_TENANTPARAMLIST.FreeFindSYS_TENANTPARAMLIST", obj );
	}

	public int countFreeFind(SysTenantparamlist obj) {
        Integer ret = (Integer) queryForObject("SYS_TENANTPARAMLIST.CountFreeFindSYS_TENANTPARAMLIST", obj );
        return ret.intValue();
	}

	public List freeFind(SysTenantparamlist obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_TENANTPARAMLIST.FreeFindSYS_TENANTPARAMLIST", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysTenantparamlist obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysTenantparamlist){
	    	_hashmap = ((SysTenantparamlist)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_TENANTPARAMLIST.FreeFindSYS_TENANTPARAMLISTOrdered", _hashmap);
	}

	public List freeFind(SysTenantparamlist obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysTenantparamlist){
	    	_hashmap = ((SysTenantparamlist)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_TENANTPARAMLIST.FreeFindSYS_TENANTPARAMLISTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysTenantparamlist> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysTenantparamlist oneObj = (SysTenantparamlist)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysTenantparamlist vo) {
        SysTenantparamlist obj = (SysTenantparamlist) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysTenantparamlist obj) {

		obj.fixup();
        insert(cti, "SYS_TENANTPARAMLIST.InsertSYS_TENANTPARAMLIST", obj );
	}

	public void update(ContextInfo cti, SysTenantparamlist obj) {

		obj.fixup();
		update(cti, "SYS_TENANTPARAMLIST.UpdateSYS_TENANTPARAMLIST", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysTenantparamlist obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_TENANTPARAMLIST.DeleteSYS_TENANTPARAMLIST", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysTenantparamlist obj) {

        obj.fixup();
        SysTenantparamlist oldObj = ( SysTenantparamlist )queryForObject("SYS_TENANTPARAMLIST.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_TENANTPARAMLIST.DeleteSYS_TENANTPARAMLIST", oldObj );

	}

	public boolean exists(SysTenantparamlist vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysTenantparamlist obj = (SysTenantparamlist) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_TENANTPARAMLIST.CountSYS_TENANTPARAMLIST", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
}
