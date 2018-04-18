package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;
import com.xfs.user.model.SysFunctionCrud;

/**
 * SysFunctionCrudDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/13 17:28:22
 */
@Repository
public class SysFunctionCrudDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CRUD.CountFindAllSYS_FUNCTION_CRUD", null );
        return ret.intValue();
	}

	public SysFunctionCrud findByPK(SysFunctionCrud obj) {
		Object ret = queryForObject("SYS_FUNCTION_CRUD.FindByPK", obj );
		if(ret !=null)
			return (SysFunctionCrud)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCrud> freeFind(SysFunctionCrud obj) {
		return queryForList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUD", obj );
	}

	public int countFreeFind(SysFunctionCrud obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CRUD.CountFreeFindSYS_FUNCTION_CRUD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCrud> freeFind(SysFunctionCrud obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCrud> freeFind(SysFunctionCrud obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysFunctionCrud){
	    	_hashmap = ((SysFunctionCrud)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysFunctionCrud> freeFind(SysFunctionCrud obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysFunctionCrud){
	    	_hashmap = ((SysFunctionCrud)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysFunctionCrud> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysFunctionCrud> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFunctionCrud oneObj = (SysFunctionCrud)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SysFunctionCrud vo) {
        SysFunctionCrud obj = (SysFunctionCrud) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SysFunctionCrud obj) {

		obj.fixup();
        return insert(cti, "SYS_FUNCTION_CRUD.InsertSYS_FUNCTION_CRUD", obj );
	}

	public int update(ContextInfo cti, SysFunctionCrud obj) {

		obj.fixup();
		return update(cti, "SYS_FUNCTION_CRUD.UpdateSYS_FUNCTION_CRUD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysFunctionCrud obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_FUNCTION_CRUD.DeleteSYS_FUNCTION_CRUD", obj );

	}

	public int removeObjectTree(ContextInfo cti, SysFunctionCrud obj) {

        obj.fixup();
        SysFunctionCrud oldObj = ( SysFunctionCrud )queryForObject("SYS_FUNCTION_CRUD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_FUNCTION_CRUD.DeleteSYS_FUNCTION_CRUD", oldObj );

	}

	public boolean exists(SysFunctionCrud vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFunctionCrud obj = (SysFunctionCrud) vo;

        keysFilled = keysFilled && ( null != obj.getRelId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CRUD.CountSYS_FUNCTION_CRUD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:22

	/**
	 * 根据URL获取受控角色
	 * @param currUrl
	 * @return
	 */
	public String queryRoleIdsByUrl(String currUrl){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("url",currUrl);
		Object roleIds = queryForObject("SYS_FUNCTION_CRUD.QUERY_ROLEIDS_BY_URL",_hashmap);
		if(null == roleIds || StringUtils.isBlank(String.valueOf(roleIds)))
			return "";
		return String.valueOf(roleIds);
	}

    /**
     * 根据URL获取受控角色
     * @param currUrl
     * @return
     */
    public String queryRoleIdsByUrlWithCs(ContextInfo cti,String currUrl){
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("url",currUrl);
        _hashmap.put("userId",cti.getUserId());
        _hashmap.put("cpId",cti.getOrgId());
        Object roleIds = queryForObject("SYS_FUNCTION_CRUD.CS_QUERY_ROLEIDS_BY_URL",_hashmap);
        if(null == roleIds || StringUtils.isBlank(String.valueOf(roleIds)))
            return "";
        return String.valueOf(roleIds);
    }

    /**
     * 根据URL获取受控角色
     * @param currUrl
     * @return
     */
    public String queryRoleIdsByUrlWithBs(String currUrl){
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("url",currUrl);
        Object roleIds = queryForObject("SYS_FUNCTION_CRUD.BS_QUERY_ROLEIDS_BY_URL",_hashmap);
        if(null == roleIds || StringUtils.isBlank(String.valueOf(roleIds)))
            return "";
        return String.valueOf(roleIds);
    }

    /**
     * 根据URL获取受控角色
     * @param currUrl
     * @return
     */
    public String queryRoleIdsByUrlWithCoop(String currUrl){
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("url",currUrl);
        Object roleIds = queryForObject("SYS_FUNCTION_CRUD.COOP_QUERY_ROLEIDS_BY_URL",_hashmap);
        if(null == roleIds || StringUtils.isBlank(String.valueOf(roleIds)))
            return "";
        return String.valueOf(roleIds);
    }


	public  List<SysFunctionCrud> queryNoAuthUrlByRoleIds(String roleIds){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("roleIds",roleIds);
		return queryForList("SYS_FUNCTION_CRUD.CP_QUERY_ROLEIDS_NO_AUTH_URL",_hashmap);
	}
	
}
