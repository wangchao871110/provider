package com.xfs.openlist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.openlist.model.SpsOlEmpbusiness;

/**
 * SpsOlEmpbusinessDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/05 20:38:18
 */
@Repository
public class SpsOlEmpbusinessDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_OL_EMPBUSINESS.CountFindAllSPS_OL_EMPBUSINESS", null );
        return ret.intValue();
	}

	public SpsOlEmpbusiness findByPK(SpsOlEmpbusiness obj) {
		Object ret = queryForObject("SPS_OL_EMPBUSINESS.FindByPK", obj );
		if(ret !=null)
			return (SpsOlEmpbusiness)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlEmpbusiness> freeFind(SpsOlEmpbusiness obj) {
		return queryForList("SPS_OL_EMPBUSINESS.FreeFindSPS_OL_EMPBUSINESS", obj );
	}

	public int countFreeFind(SpsOlEmpbusiness obj) {
        Integer ret = (Integer) queryForObject("SPS_OL_EMPBUSINESS.CountFreeFindSPS_OL_EMPBUSINESS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlEmpbusiness> freeFind(SpsOlEmpbusiness obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_OL_EMPBUSINESS.FreeFindSPS_OL_EMPBUSINESS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlEmpbusiness> freeFind(SpsOlEmpbusiness obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlEmpbusiness){
	    	_hashmap = ((SpsOlEmpbusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_OL_EMPBUSINESS.FreeFindSPS_OL_EMPBUSINESSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlEmpbusiness> freeFind(SpsOlEmpbusiness obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlEmpbusiness){
	    	_hashmap = ((SpsOlEmpbusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_OL_EMPBUSINESS.FreeFindSPS_OL_EMPBUSINESSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsOlEmpbusiness> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsOlEmpbusiness> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsOlEmpbusiness oneObj = (SpsOlEmpbusiness)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsOlEmpbusiness vo) {
        SpsOlEmpbusiness obj = (SpsOlEmpbusiness) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsOlEmpbusiness obj) {

		obj.fixup();
        return insert(cti, "SPS_OL_EMPBUSINESS.InsertSPS_OL_EMPBUSINESS", obj );
	}

	public int update(ContextInfo cti, SpsOlEmpbusiness obj) {

		obj.fixup();
		return update(cti, "SPS_OL_EMPBUSINESS.UpdateSPS_OL_EMPBUSINESS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsOlEmpbusiness obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_OL_EMPBUSINESS.DeleteSPS_OL_EMPBUSINESS", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsOlEmpbusiness obj) {

        obj.fixup();
        SpsOlEmpbusiness oldObj = ( SpsOlEmpbusiness )queryForObject("SPS_OL_EMPBUSINESS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_OL_EMPBUSINESS.DeleteSPS_OL_EMPBUSINESS", oldObj );

	}

	public boolean exists(SpsOlEmpbusiness vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsOlEmpbusiness obj = (SpsOlEmpbusiness) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_OL_EMPBUSINESS.CountSPS_OL_EMPBUSINESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/05 20:38:18
	
	
	
}
