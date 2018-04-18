package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.constant.IStaticVarConstant;
import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysUserRole;

/**
 * SysUserRoleDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:24
 */
@Repository
public class SysUserRoleDao extends BaseSqlMapDao implements IStaticVarConstant {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER_ROLE.CountFindAllSYS_USER_ROLE", null );
        return ret.intValue();
	}

	public SysUserRole findByPK(SysUserRole obj) {
		Object ret = queryForObject("SYS_USER_ROLE.FindByPK", obj );
		if(ret !=null)
			return (SysUserRole)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserRole> freeFind(SysUserRole obj) {
		return queryForList("SYS_USER_ROLE.FreeFindSYS_USER_ROLE", obj );
	}

	public int countFreeFind(SysUserRole obj) {
        Integer ret = (Integer) queryForObject("SYS_USER_ROLE.CountFreeFindSYS_USER_ROLE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserRole> freeFind(SysUserRole obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER_ROLE.FreeFindSYS_USER_ROLE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserRole> freeFind(SysUserRole obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysUserRole){
	    	_hashmap = ((SysUserRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER_ROLE.FreeFindSYS_USER_ROLEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserRole> freeFind(SysUserRole obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysUserRole){
	    	_hashmap = ((SysUserRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER_ROLE.FreeFindSYS_USER_ROLEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysUserRole> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysUserRole> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUserRole oneObj = (SysUserRole)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SysUserRole vo) {
        SysUserRole obj = (SysUserRole) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SysUserRole obj) {

		obj.fixup();
        return insert(cti, "SYS_USER_ROLE.InsertSYS_USER_ROLE", obj );
	}

	public int update(ContextInfo cti, SysUserRole obj) {

		obj.fixup();
		return update(cti, "SYS_USER_ROLE.UpdateSYS_USER_ROLE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysUserRole obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_USER_ROLE.DeleteSYS_USER_ROLE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SysUserRole obj) {

        obj.fixup();
        SysUserRole oldObj = ( SysUserRole )queryForObject("SYS_USER_ROLE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_USER_ROLE.DeleteSYS_USER_ROLE", oldObj );

	}

	public boolean exists(SysUserRole vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUserRole obj = (SysUserRole) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER_ROLE.CountSYS_USER_ROLE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:24
	
	/**
	 * 通过userId删除
	 * @param userId
	 */
	public void deleteByUserId(ContextInfo cti, Integer userId){
		delete(cti, "SYS_USER_ROLE.DeleteByUserIdSYS_USER_ROLE", userId);
	}
	/**
	 * 通过userId查找所属role
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysUserRole> findByUserId(Integer userId){
		return queryForList("SYS_USER_ROLE.FindByUserIdSYS_USER_ROLE", userId);
	}


    public int removeByUserIdAndRoleId(ContextInfo cti, SysUserRole sysUserRole) {
        if ( sysUserRole == null ) {
            return 0;
        }

        sysUserRole.fixup();

        return delete(cti, "SYS_USER_ROLE.DeleteSYS_USER_ROLE_ByUserIdAndRoleId", sysUserRole );
    }
    
    @SuppressWarnings("unchecked")
	public List<SysUserRole> findUserListByRoleId(Integer spId,Integer roleId){
    	HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("roleId", roleId);
		_hashmap.put("spId", spId);
		_hashmap.put("userType", CMCORPTYPE_SERVICE);
		return queryForList("SYS_USER_ROLE.FreeFindSYS_USER_ROLE_BY_SPID", _hashmap );
    }
	
}
