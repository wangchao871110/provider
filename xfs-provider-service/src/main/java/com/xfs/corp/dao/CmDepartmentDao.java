package com.xfs.corp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.model.CmDepartment;

/**
 * BdDepartmentDao
 * @author:yangfangwei
 * @date:2016/07/22 11:03:55
 */
@Repository
public class CmDepartmentDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_DEPARTMENT.CountFindAllBD_DEPARTMENT", null );
        return ret.intValue();
	}

	public CmDepartment findByPK(CmDepartment obj) {
		Object ret = queryForObject("BD_DEPARTMENT.FindByPK", obj );
		if(ret !=null)
			return (CmDepartment)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmDepartment> freeFind(CmDepartment obj) {
		return queryForList("BD_DEPARTMENT.FreeFindBD_DEPARTMENT", obj );
	}

	public int countFreeFind(CmDepartment obj) {
        Integer ret = (Integer) queryForObject("BD_DEPARTMENT.CountFreeFindBD_DEPARTMENT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmDepartment> freeFind(CmDepartment obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_DEPARTMENT.FreeFindBD_DEPARTMENT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmDepartment> freeFind(CmDepartment obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmDepartment){
	    	_hashmap = ((CmDepartment)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_DEPARTMENT.FreeFindBD_DEPARTMENTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmDepartment> freeFind(CmDepartment obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmDepartment){
	    	_hashmap = ((CmDepartment)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_DEPARTMENT.FreeFindBD_DEPARTMENTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmDepartment> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmDepartment> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmDepartment oneObj = (CmDepartment)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CmDepartment vo) {
        CmDepartment obj = (CmDepartment) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CmDepartment obj) {

		obj.fixup();
        return insert(cti, "BD_DEPARTMENT.InsertBD_DEPARTMENT", obj );
	}

	public int update(ContextInfo cti, CmDepartment obj) {

		obj.fixup();
		return update(cti, "BD_DEPARTMENT.UpdateBD_DEPARTMENT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmDepartment obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_DEPARTMENT.DeleteBD_DEPARTMENT", obj );

	}

	public int removeObjectTree(ContextInfo cti, CmDepartment obj) {

        obj.fixup();
        CmDepartment oldObj = (CmDepartment)queryForObject("BD_DEPARTMENT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_DEPARTMENT.DeleteBD_DEPARTMENT", oldObj );

	}

	public boolean exists(CmDepartment vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmDepartment obj = (CmDepartment) vo;

        keysFilled = keysFilled && ( null != obj.getDepId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_DEPARTMENT.CountBD_DEPARTMENT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 11:03:55

	/**
	 * 通过id与所属企业查询 防止登录用户查询到其他企业下部门
	 * @param depId
	 * @param orgId
     * @return
     */
	public CmDepartment findByIdAndOrg(Integer depId, Integer orgId){
		Map<String,Object> query = new HashMap<>();
		query.put("depId",depId);
		query.put("orgId",orgId);
		CmDepartment obj = (CmDepartment)queryForObject("BD_DEPARTMENT.FindBD_DEPARTMENTByIdAndOrg", query);
		return obj;
	}
	
}
