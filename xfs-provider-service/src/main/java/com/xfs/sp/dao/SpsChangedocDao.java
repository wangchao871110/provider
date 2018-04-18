package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsChangedoc;

/**
 * SpsChangedocDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 14:07:19
 */
@Repository
public class SpsChangedocDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_CHANGEDOC.CountFindAllSPS_CHANGEDOC", null );
        return ret.intValue();
	}

	public SpsChangedoc findByPK(SpsChangedoc obj) {
		Object ret = queryForObject("SPS_CHANGEDOC.FindByPK", obj );
		if(ret !=null)
			return (SpsChangedoc)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsChangedoc> freeFind(SpsChangedoc obj) {
		return queryForList("SPS_CHANGEDOC.FreeFindSPS_CHANGEDOC", obj );
	}

	public int countFreeFind(SpsChangedoc obj) {
        Integer ret = (Integer) queryForObject("SPS_CHANGEDOC.CountFreeFindSPS_CHANGEDOC", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsChangedoc> freeFind(SpsChangedoc obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_CHANGEDOC.FreeFindSPS_CHANGEDOC", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsChangedoc> freeFind(SpsChangedoc obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsChangedoc){
	    	_hashmap = ((SpsChangedoc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_CHANGEDOC.FreeFindSPS_CHANGEDOCOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsChangedoc> freeFind(SpsChangedoc obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsChangedoc){
	    	_hashmap = ((SpsChangedoc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_CHANGEDOC.FreeFindSPS_CHANGEDOCOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsChangedoc> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsChangedoc> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsChangedoc oneObj = (SpsChangedoc)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsChangedoc vo) {
        SpsChangedoc obj = (SpsChangedoc) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsChangedoc obj) {

		obj.fixup();
        return insert(cti, "SPS_CHANGEDOC.InsertSPS_CHANGEDOC", obj );
	}

	public int update(ContextInfo cti, SpsChangedoc obj) {

		obj.fixup();
		return update(cti, "SPS_CHANGEDOC.UpdateSPS_CHANGEDOC", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsChangedoc obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_CHANGEDOC.DeleteSPS_CHANGEDOC", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsChangedoc obj) {

        obj.fixup();
        SpsChangedoc oldObj = ( SpsChangedoc )queryForObject("SPS_CHANGEDOC.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_CHANGEDOC.DeleteSPS_CHANGEDOC", oldObj );

	}

	public boolean exists(SpsChangedoc vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsChangedoc obj = (SpsChangedoc) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_CHANGEDOC.CountSPS_CHANGEDOC", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 14:07:19
	
	public List<Map<String, Object>> Freespschange(SpsChangedoc vo){
		return   queryForList("SPS_CHANGEDOC.FreeSPS_CHANGEDOC", vo);
	}
	
}
