package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysDictionary;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysDictionaryDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/10 17:19:50
 */
@Repository
public class SysDictionaryDAO extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_DICTIONARY.CountFindAllSYS_DICTIONARY", null );
        return ret.intValue();
	}

	public SysDictionary findByPK(SysDictionary obj) {
		Object ret = queryForObject("SYS_DICTIONARY.FindByPK", obj );
		if(ret !=null)
			return (SysDictionary)ret;
		else 
			return null;
	}
	
	public List freeFind(SysDictionary obj) {
		return queryForList("SYS_DICTIONARY.FreeFindSYS_DICTIONARY", obj );
	}

	public int countFreeFind(SysDictionary obj) {
        Integer ret = (Integer) queryForObject("SYS_DICTIONARY.CountFreeFindSYS_DICTIONARY", obj );
        return ret.intValue();
	}

	public List freeFind(SysDictionary obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_DICTIONARY.FreeFindSYS_DICTIONARY", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysDictionary obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDictionary){
	    	_hashmap = ((SysDictionary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_DICTIONARY.FreeFindSYS_DICTIONARYOrdered", _hashmap);
	}

	public List freeFind(SysDictionary obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDictionary){
	    	_hashmap = ((SysDictionary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_DICTIONARY.FreeFindSYS_DICTIONARYOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysDictionary> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysDictionary oneObj = (SysDictionary)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysDictionary vo) {
        SysDictionary obj = (SysDictionary) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysDictionary obj) {

		obj.fixup();
        insert(cti, "SYS_DICTIONARY.InsertSYS_DICTIONARY", obj );
	}

	public void update(ContextInfo cti, SysDictionary obj) {

		obj.fixup();
		update(cti, "SYS_DICTIONARY.UpdateSYS_DICTIONARY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysDictionary obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_DICTIONARY.DeleteSYS_DICTIONARY", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysDictionary obj) {

        obj.fixup();
        SysDictionary oldObj = ( SysDictionary )queryForObject("SYS_DICTIONARY.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_DICTIONARY.DeleteSYS_DICTIONARY", oldObj );

	}

	public boolean exists(SysDictionary vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysDictionary obj = (SysDictionary) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_DICTIONARY.CountSYS_DICTIONARY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	
	/**
	 * new qjh 2015/12/12
	 * 新增子集查询：findForItemByPK
	 */
	public SysDictionary findForItemByPK(SysDictionary obj) {
		Object ret = queryForObject("SYS_DICTIONARY.FindForItemByPK", obj );
		if(ret !=null)
			return (SysDictionary)ret;
		else 
			return null;
	}


	public SysDictionary findSysDicByName(SysDictionary obj){
		Object ret = queryForObject("SYS_DICTIONARY.findSysDicByName", obj );
		if(ret !=null)
			return (SysDictionary)ret;
		else
			return null;

	}
}
