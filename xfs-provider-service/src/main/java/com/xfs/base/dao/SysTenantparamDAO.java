package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysTenantparam;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysTenantparamDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/14 12:28:00
 */
@Repository
public class SysTenantparamDAO extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_TENANTPARAM.CountFindAllSYS_TENANTPARAM", null );
        return ret.intValue();
	}

	public SysTenantparam findByPK(SysTenantparam obj) {
		Object ret = queryForObject("SYS_TENANTPARAM.FindByPK", obj );
		if(ret !=null)
			return (SysTenantparam)ret;
		else 
			return null;
	}
	
	public List freeFind(SysTenantparam obj) {
		return queryForList("SYS_TENANTPARAM.FreeFindSYS_TENANTPARAM", obj );
	}

	public int countFreeFind(SysTenantparam obj) {
        Integer ret = (Integer) queryForObject("SYS_TENANTPARAM.CountFreeFindSYS_TENANTPARAM", obj );
        return ret.intValue();
	}

	public List freeFind(SysTenantparam obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_TENANTPARAM.FreeFindSYS_TENANTPARAM", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysTenantparam obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysTenantparam){
	    	_hashmap = ((SysTenantparam)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_TENANTPARAM.FreeFindSYS_TENANTPARAMOrdered", _hashmap);
	}

	public List freeFind(SysTenantparam obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysTenantparam){
	    	_hashmap = ((SysTenantparam)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_TENANTPARAM.FreeFindSYS_TENANTPARAMOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysTenantparam> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysTenantparam oneObj = (SysTenantparam)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysTenantparam vo) {
        SysTenantparam obj = (SysTenantparam) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysTenantparam obj) {

		obj.fixup();
        insert(cti, "SYS_TENANTPARAM.InsertSYS_TENANTPARAM", obj );
	}

	public void update(ContextInfo cti, SysTenantparam obj) {

		obj.fixup();
		update(cti, "SYS_TENANTPARAM.UpdateSYS_TENANTPARAM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysTenantparam obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_TENANTPARAM.DeleteSYS_TENANTPARAM", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysTenantparam obj) {

        obj.fixup();
        SysTenantparam oldObj = ( SysTenantparam )queryForObject("SYS_TENANTPARAM.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_TENANTPARAM.DeleteSYS_TENANTPARAM", oldObj );

	}

	public boolean exists(SysTenantparam vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysTenantparam obj = (SysTenantparam) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_TENANTPARAM.CountSYS_TENANTPARAM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	/**
	 * new quanjiahua
	 * 根据租户tid查询租户级参数
	 */
	public List findByTid(SysTenantparam vo, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_TENANTPARAM.FindByTid", vo, pageIndex, pageSize  );
	}
	/**
	 * new quanjiahua
	 * 查询租户级参数
	 */
	public List findAll(SysTenantparam vo) {
		return queryForList("SYS_TENANTPARAM.FindAll", vo);
	}
	/**
	 * new quanjiahua
	 * @param vo 
	 * @return 根据tid查询总记录数
	 */
	public int countFreeFindByTid(SysTenantparam vo) {
        Integer ret = (Integer) queryForObject("SYS_TENANTPARAM.CountFreeFindSYS_TENANTPARAMByTid", vo );
        return ret.intValue();
	}
	/**
	 * new quanjiahua
	 * @param obj
	 * @return 根据tid及id查询关联子表信息
	 */
	public SysTenantparam findByTidAndId(SysTenantparam obj) {
		Object ret = queryForObject("SYS_TENANTPARAM.FindByTidAndId", obj );
		if(ret !=null)
			return (SysTenantparam)ret;
		else 
			return null;
	}
	
}
