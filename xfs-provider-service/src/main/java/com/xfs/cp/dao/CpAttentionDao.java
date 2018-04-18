package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpAttention;

/**
 * CpAttentionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:17
 */
@Repository
public class CpAttentionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_ATTENTION.CountFindAllCP_ATTENTION", null );
        return ret.intValue();
	}

	public CpAttention findByPK(CpAttention obj) {
		Object ret = queryForObject("CP_ATTENTION.FindByPK", obj );
		if(ret !=null)
			return (CpAttention)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAttention> freeFind(CpAttention obj) {
		return queryForList("CP_ATTENTION.FreeFindCP_ATTENTION", obj );
	}

	public int countFreeFind(CpAttention obj) {
        Integer ret = (Integer) queryForObject("CP_ATTENTION.CountFreeFindCP_ATTENTION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAttention> freeFind(CpAttention obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_ATTENTION.FreeFindCP_ATTENTION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAttention> freeFind(CpAttention obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpAttention){
	    	_hashmap = ((CpAttention)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_ATTENTION.FreeFindCP_ATTENTIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAttention> freeFind(CpAttention obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpAttention){
	    	_hashmap = ((CpAttention)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_ATTENTION.FreeFindCP_ATTENTIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpAttention> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpAttention> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpAttention oneObj = (CpAttention)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpAttention vo) {
        CpAttention obj = (CpAttention) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpAttention obj) {

		obj.fixup();
        return insert(cti, "CP_ATTENTION.InsertCP_ATTENTION", obj );
	}

	public int update(ContextInfo cti, CpAttention obj) {

		obj.fixup();
		return update(cti, "CP_ATTENTION.UpdateCP_ATTENTION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpAttention obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_ATTENTION.DeleteCP_ATTENTION", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpAttention obj) {

        obj.fixup();
        CpAttention oldObj = ( CpAttention )queryForObject("CP_ATTENTION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_ATTENTION.DeleteCP_ATTENTION", oldObj );

	}

	public boolean exists(CpAttention vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpAttention obj = (CpAttention) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_ATTENTION.CountCP_ATTENTION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:17
	
	
	
}
