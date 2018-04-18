package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysRole;

/**
 * SysRoleDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:23
 */
@Repository
public class SysRoleDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ROLE.CountFindAllSYS_ROLE", null );
        return ret.intValue();
	}

	public SysRole findByPK(SysRole obj) {
		Object ret = queryForObject("SYS_ROLE.FindByPKSYS_ROLE", obj );
		if(ret !=null)
			return (SysRole)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRole> freeFind(SysRole obj) {
		return queryForList("SYS_ROLE.FreeFindSYS_ROLE", obj );
	}

	public int countFreeFind(SysRole obj) {
        Integer ret = (Integer) queryForObject("SYS_ROLE.CountFreeFindSYS_ROLE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRole> freeFind(SysRole obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ROLE.FreeFindSYS_ROLE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRole> freeFind(SysRole obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysRole){
	    	_hashmap = ((SysRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ROLE.FreeFindSYS_ROLEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysRole> freeFind(SysRole obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysRole){
	    	_hashmap = ((SysRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ROLE.FreeFindSYS_ROLEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysRole> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysRole> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysRole oneObj = (SysRole)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	//bs
	public void saveAll1(ContextInfo cti, Collection<SysRole> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysRole oneObj = (SysRole)it.next();
				save(cti, oneObj );
            }
        }
	}
	
	public int save(ContextInfo cti, SysRole vo) {
        SysRole obj = (SysRole) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	//bs
	public void save1(ContextInfo cti, SysRole vo) {
        SysRole obj = (SysRole) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}
	
	public int insert(ContextInfo cti, SysRole obj) {

		obj.fixup();
        return insert(cti, "SYS_ROLE.InsertSYS_ROLE", obj );
	}

	//bs
	public void insert1(ContextInfo cti, SysRole obj) {

		obj.fixup();
        insert(cti, "SYS_ROLE.InsertSYS_ROLE", obj );
	}
	
	public int update(ContextInfo cti, SysRole obj) {

		obj.fixup();
		return update(cti, "SYS_ROLE.UpdateSYS_ROLE", obj );

	}
	
	//bs
	public void update1(ContextInfo cti, SysRole obj) {

		obj.fixup();
		update(cti, "SYS_ROLE.UpdateSYS_ROLE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysRole obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_ROLE.DeleteSYS_ROLE", obj );

	}
	
	//bs
	public void remove1(ContextInfo cti, SysRole obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SYS_ROLE.DeleteSYS_ROLE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SysRole obj) {

        obj.fixup();
        SysRole oldObj = ( SysRole )queryForObject("SYS_ROLE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_ROLE.DeleteSYS_ROLE", oldObj );

	}
	
	//bs
	public void removeObjectTree1(ContextInfo cti, SysRole obj) {

        obj.fixup();
        SysRole oldObj = ( SysRole )queryForObject("SYS_ROLE.FindByPKSYS_ROLE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_ROLE.DeleteSYS_ROLE", oldObj );

	}

	public boolean exists(SysRole vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysRole obj = (SysRole) vo;

        keysFilled = keysFilled && ( null != obj.getRoleId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ROLE.CountSYS_ROLE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
	
	public List<Map<String,Object>> findRoleByScode(String Scode) {
		List<Map<String,Object>> list =  (List<Map<String,Object>>)queryForList("SYS_ROLE.queryRoleByScode", Scode);
		return list;
	}


	public List<SysRole> queryAllRolesByUserId(Integer userId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("userId", userId );
		return queryForList("SYS_ROLE.queryAllRolesByUserId", _hashmap);
	}


	public List<SysRole> freeFindByAppCodes(SysRole obj) {
		return queryForList("SYS_ROLE.FreeFindSYS_ROLE_BY_APPCODES", obj );
	}

	
}
