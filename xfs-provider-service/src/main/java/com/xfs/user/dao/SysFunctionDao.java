package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysFunction;

/**
 * SysFunctionDAO
 * @author:Lusir<lusire@gmail.com>
 */
@Repository
public class SysFunctionDao extends BaseSqlMapDao{


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION.CountFindAllSYS_FUNCTION", null );
        return ret.intValue();
	}

	public SysFunction findByPK(SysFunction obj) {
		Object ret = queryForObject("SYS_FUNCTION.FindByPKSYS_FUNCTION", obj );
		if(ret !=null)
			return (SysFunction)ret;
		else 
			return null;
	}

	public List freeFind(SysFunction obj) {
		return queryForList("SYS_FUNCTION.FreeFindSYS_FUNCTION", obj );
	}

	public int countFreeFind(SysFunction obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION.CountFreeFindSYS_FUNCTION", obj );
        return ret.intValue();
	}

	public List freeFind(SysFunction obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FUNCTION.FreeFindSYS_FUNCTION", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysFunction obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunction){
	    	_hashmap = ((SysFunction)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FUNCTION.FreeFindSYS_FUNCTIONOrdered", _hashmap);
	}

	public List freeFind(SysFunction obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunction){
	    	_hashmap = ((SysFunction)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FUNCTION.FreeFindSYS_FUNCTIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysFunction> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFunction oneObj = (SysFunction)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysFunction vo) {
        SysFunction obj = (SysFunction) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysFunction obj) {

		obj.fixup();
        insert(cti, "SYS_FUNCTION.InsertSYS_FUNCTION", obj );
	}

	public void update(ContextInfo cti, SysFunction obj) {

		obj.fixup();
		update(cti, "SYS_FUNCTION.UpdateSYS_FUNCTION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysFunction obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_FUNCTION.DeleteSYS_FUNCTION", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysFunction obj) {

        obj.fixup();
        SysFunction oldObj = ( SysFunction )queryForObject("SYS_FUNCTION.FindByPKSYS_FUNCTION", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_FUNCTION.DeleteSYS_FUNCTION", oldObj );

	}

	public boolean exists(SysFunction vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFunction obj = (SysFunction) vo;

        keysFilled = keysFilled && ( null != obj.getFunctionId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION.CountSYS_FUNCTION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List getFunctionByRoleAndCategory(Object role_id,Object category_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "category_id", category_id );
		return queryForList( "SYS_FUNCTION.GetFunctionByRoleAndCategorySYS_FUNCTION", _hashmap );
	}
	
	public List getFunctionByRoleAndCategoryIn(String role_ids,Object category_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_ids", role_ids );
		_hashmap.put( "category_id", category_id );
		return queryForList( "SYS_FUNCTION.GetFunctionByRoleAndCategorySYS_FUNCTION_IN", _hashmap );
	}

	public int countGetFunctionByRoleAndCategory(Object role_id,Object category_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "category_id", category_id );
		Integer retInt=(Integer)queryForObject("SYS_FUNCTION.CountGetFunctionByRoleAndCategorySYS_FUNCTION", _hashmap );
		return retInt.intValue();
	}

	public List getFunctionByRoleAndCategory(Object role_id,Object category_id , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "category_id", category_id );
		return getPaginatedList( "SYS_FUNCTION.GetFunctionByRoleAndCategorySYS_FUNCTION", _hashmap, pageIndex, pageSize );
	}
	public SysFunction findLastFunction(SysFunction obj){
		Object ret = queryForObject("SYS_FUNCTION.findLastFunction", obj );
		if(ret !=null)
			return (SysFunction)ret;
		else
			return null;
	}
}
