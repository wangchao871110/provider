package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysRelRoleFunc;

/**
 * SysRelRoleFuncDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:23
 */
@Repository
public class SysRelRoleFuncDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_REL_ROLE_FUNC.CountFindAllSYS_REL_ROLE_FUNC", null );
        return ret.intValue();
	}

	public SysRelRoleFunc findByPK(SysRelRoleFunc obj) {
		Object ret = queryForObject("SYS_REL_ROLE_FUNC.FindByPK", obj );
		if(ret !=null)
			return (SysRelRoleFunc)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRelRoleFunc> freeFind(SysRelRoleFunc obj) {
		return queryForList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNC", obj );
	}

	public int countFreeFind(SysRelRoleFunc obj) {
        Integer ret = (Integer) queryForObject("SYS_REL_ROLE_FUNC.CountFreeFindSYS_REL_ROLE_FUNC", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRelRoleFunc> freeFind(SysRelRoleFunc obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNC", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRelRoleFunc> freeFind(SysRelRoleFunc obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysRelRoleFunc){
	    	_hashmap = ((SysRelRoleFunc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNCOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRelRoleFunc> freeFind(SysRelRoleFunc obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysRelRoleFunc){
	    	_hashmap = ((SysRelRoleFunc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNCOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysRelRoleFunc> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysRelRoleFunc> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysRelRoleFunc oneObj = (SysRelRoleFunc)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SysRelRoleFunc vo) {
        SysRelRoleFunc obj = (SysRelRoleFunc) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SysRelRoleFunc obj) {

		obj.fixup();
        return insert(cti, "SYS_REL_ROLE_FUNC.InsertSYS_REL_ROLE_FUNC", obj );
	}

	public int update(ContextInfo cti, SysRelRoleFunc obj) {

		obj.fixup();
		return update(cti, "SYS_REL_ROLE_FUNC.UpdateSYS_REL_ROLE_FUNC", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysRelRoleFunc obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_REL_ROLE_FUNC.DeleteSYS_REL_ROLE_FUNC", obj );

	}

	public int removeObjectTree(ContextInfo cti, SysRelRoleFunc obj) {

        obj.fixup();
        SysRelRoleFunc oldObj = ( SysRelRoleFunc )queryForObject("SYS_REL_ROLE_FUNC.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_REL_ROLE_FUNC.DeleteSYS_REL_ROLE_FUNC", oldObj );

	}

	public boolean exists(SysRelRoleFunc vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysRelRoleFunc obj = (SysRelRoleFunc) vo;

        keysFilled = keysFilled && ( null != obj.getRoleFuncId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_REL_ROLE_FUNC.CountSYS_REL_ROLE_FUNC", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
    public int deleteByRoleId(ContextInfo cti, Object role_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        int retInt=update(cti, "SYS_REL_ROLE_FUNC.DeleteByRoleIdSYS_REL_ROLE_FUNC", _hashmap );
        return retInt;
    }
	
	
}
