package com.xfs.cp.dao;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpRelation;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * CpRelationDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
@Repository
public class CpRelationDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_RELATION.CountFindAllCP_RELATION", null );
        return ret.intValue();
	}

	public CpRelation findByPK(CpRelation obj) {
		Object ret = queryForObject("CP_RELATION.FindByPK", obj );
		if(ret !=null)
			return (CpRelation)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpRelation> freeFind(CpRelation obj) {
		return queryForList("CP_RELATION.FreeFindCP_RELATION", obj );
	}

	public int countFreeFind(CpRelation obj) {
        Integer ret = (Integer) queryForObject("CP_RELATION.CountFreeFindCP_RELATION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpRelation> freeFind(CpRelation obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_RELATION.FreeFindCP_RELATION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpRelation> freeFind(CpRelation obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpRelation){
	    	_hashmap = ((CpRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_RELATION.FreeFindCP_RELATIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpRelation> freeFind(CpRelation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpRelation){
	    	_hashmap = ((CpRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_RELATION.FreeFindCP_RELATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpRelation> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpRelation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpRelation oneObj = (CpRelation)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpRelation vo) {
        CpRelation obj = (CpRelation) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpRelation obj) {

		obj.fixup();
        return insert(cti, "CP_RELATION.InsertCP_RELATION", obj );
	}

	public int update(ContextInfo cti, CpRelation obj) {

		obj.fixup();
		return update(cti, "CP_RELATION.UpdateCP_RELATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpRelation obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_RELATION.DeleteCP_RELATION", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpRelation obj) {

        obj.fixup();
        CpRelation oldObj = ( CpRelation )queryForObject("CP_RELATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_RELATION.DeleteCP_RELATION", oldObj );

	}

	public boolean exists(CpRelation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpRelation obj = (CpRelation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_RELATION.CountCP_RELATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41

	public List<CpRelation> getBSpList(CpRelation obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_RELATION.getBSpList", obj, pageIndex, pageSize );
	}

	public int getBSpListCount(CpRelation obj) {
		Integer ret = (Integer) queryForObject("CP_RELATION.getBSpListCount", obj );
		return ret.intValue();
	}

	public List<CpRelation> getASpList(CpRelation obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_RELATION.getASpList", obj, pageIndex, pageSize);
	}

	public int getASpListCount(CpRelation obj) {
		Integer ret = (Integer) queryForObject("CP_RELATION.getASpListCount", obj );
		return ret.intValue();
	}

	public Integer FindManageCount(CpRelation vo){
		Integer ret = (Integer) queryForObject("CP_RELATION.FreeFindAccount_Count", vo );
		return ret.intValue();
	}

	public List<CpRelation> FindManage(CpRelation vo,Integer pageIndex, Integer pageSize){
		return getPaginatedList("CP_RELATION.FreeFindAccount_Manage", vo, pageIndex, pageSize);
	}

	public Integer findPerson(CpRelation cpRelation){
		Integer ret = (Integer) queryForObject("CP_RELATION.FIND_PERSON", cpRelation );
		return ret.intValue();
	}

	public Integer finTask(CpRelation cpRelation){
		Integer ret = (Integer) queryForObject("CP_RELATION.FIND_TASK", cpRelation );
		return ret.intValue();
	}


}
