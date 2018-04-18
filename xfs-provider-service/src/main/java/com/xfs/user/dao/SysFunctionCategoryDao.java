package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysFunctionCategory;

/**
 * SysFunctionCategoryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:20
 */
@Repository
public class SysFunctionCategoryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CATEGORY.CountFindAllSYS_FUNCTION_CATEGORY", null );
        return ret.intValue();
	}

	public SysFunctionCategory findByPK(SysFunctionCategory obj) {
		Object ret = queryForObject("SYS_FUNCTION_CATEGORY.FindByPK", obj );
		if(ret !=null)
			return (SysFunctionCategory)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCategory> freeFind(SysFunctionCategory obj) {
		return queryForList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORY", obj );
	}

	public int countFreeFind(SysFunctionCategory obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CATEGORY.CountFreeFindSYS_FUNCTION_CATEGORY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCategory> freeFind(SysFunctionCategory obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORY", obj, pageIndex, pageSize );
	}
	    
	@SuppressWarnings("unchecked")
	public List<SysFunctionCategory> freeFind(SysFunctionCategory obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysFunctionCategory){
	    	_hashmap = ((SysFunctionCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCategory> freeFind(SysFunctionCategory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysFunctionCategory){
	    	_hashmap = ((SysFunctionCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysFunctionCategory> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysFunctionCategory> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFunctionCategory oneObj = (SysFunctionCategory)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SysFunctionCategory vo) {
        SysFunctionCategory obj = (SysFunctionCategory) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SysFunctionCategory obj) {

		obj.fixup();
        return insert(cti, "SYS_FUNCTION_CATEGORY.InsertSYS_FUNCTION_CATEGORY", obj );
	}

	public int update(ContextInfo cti, SysFunctionCategory obj) {

		obj.fixup();
		return update(cti, "SYS_FUNCTION_CATEGORY.UpdateSYS_FUNCTION_CATEGORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysFunctionCategory obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_FUNCTION_CATEGORY.DeleteSYS_FUNCTION_CATEGORY", obj );

	}

	public int removeObjectTree(ContextInfo cti, SysFunctionCategory obj) {

        obj.fixup();
        SysFunctionCategory oldObj = ( SysFunctionCategory )queryForObject("SYS_FUNCTION_CATEGORY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_FUNCTION_CATEGORY.DeleteSYS_FUNCTION_CATEGORY", oldObj );

	}

	public boolean exists(SysFunctionCategory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFunctionCategory obj = (SysFunctionCategory) vo;

        keysFilled = keysFilled && ( null != obj.getCategoryId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountSYS_FUNCTION_CATEGORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:20
    public List getFirstMenuByRoleId(Object role_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        return queryForList( "SYS_FUNCTION_CATEGORY.GetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
    }

    public int countGetFirstMenuByRoleId(Object role_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
        return retInt.intValue();
    }

    public List getFirstMenuByRoleId(Object role_id , int pageIndex, int pageSize){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
    }

    public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        _hashmap.put( "parent_id", parent_id );
        return queryForList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap );
    }

    public int countGetSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        _hashmap.put( "parent_id", parent_id );
        Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap );
        return retInt.intValue();
    }

    public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id , int pageIndex, int pageSize){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        _hashmap.put( "parent_id", parent_id );
        return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
    }

    public List getSecondMenuByRoleId(Object role_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        return queryForList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
    }


    public List getSecondMenuByRoleList(List<Integer> userRoleList){
        HashMap _hashmap = new HashMap();
        if(userRoleList.isEmpty()){
            userRoleList.add(0);
        }
        _hashmap.put( "roleList", userRoleList );
        return queryForList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY_IN", _hashmap );
    }


    public int countGetSecondMenuByRoleId(Object role_id){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
        return retInt.intValue();
    }

    public List getSecondMenuByRoleId(Object role_id , int pageIndex, int pageSize){
        HashMap _hashmap = new HashMap();
        _hashmap.put( "role_id", role_id );
        return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
    }

    @SuppressWarnings("unchecked")
    public List<SysFunctionCategory> findTopMenu(String sysCode){
        return queryForList("SYS_FUNCTION_CATEGORY.FindTopMenuOPSYS_FUNCTION_CATEGORY", sysCode);
    }
	
	
}
