package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsEmpActualdetailDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 11:05:28
 */
@Repository
public class SpsEmpActualdetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_EMP_ACTUALDETAIL.CountFindAllSPS_EMP_ACTUALDETAIL", null );
        return ret.intValue();
	}

	public SpsEmpActualdetail findByPK(SpsEmpActualdetail obj) {
		Object ret = queryForObject("SPS_EMP_ACTUALDETAIL.FindByPK", obj );
		if(ret !=null)
			return (SpsEmpActualdetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActualdetail> freeFind(SpsEmpActualdetail obj) {
		return queryForList("SPS_EMP_ACTUALDETAIL.FreeFindSPS_EMP_ACTUALDETAIL", obj );
	}

	public int countFreeFind(SpsEmpActualdetail obj) {
        Integer ret = (Integer) queryForObject("SPS_EMP_ACTUALDETAIL.CountFreeFindSPS_EMP_ACTUALDETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActualdetail> freeFind(SpsEmpActualdetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EMP_ACTUALDETAIL.FreeFindSPS_EMP_ACTUALDETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActualdetail> freeFind(SpsEmpActualdetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsEmpActualdetail){
	    	_hashmap = ((SpsEmpActualdetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_EMP_ACTUALDETAIL.FreeFindSPS_EMP_ACTUALDETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActualdetail> freeFind(SpsEmpActualdetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsEmpActualdetail){
	    	_hashmap = ((SpsEmpActualdetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_EMP_ACTUALDETAIL.FreeFindSPS_EMP_ACTUALDETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsEmpActualdetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsEmpActualdetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsEmpActualdetail oneObj = (SpsEmpActualdetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsEmpActualdetail vo) {
        SpsEmpActualdetail obj = (SpsEmpActualdetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsEmpActualdetail obj) {

		obj.fixup();
        return insert(cti, "SPS_EMP_ACTUALDETAIL.InsertSPS_EMP_ACTUALDETAIL", obj );
	}

	public int update(ContextInfo cti, SpsEmpActualdetail obj) {

		obj.fixup();
		return update(cti, "SPS_EMP_ACTUALDETAIL.UpdateSPS_EMP_ACTUALDETAIL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsEmpActualdetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_EMP_ACTUALDETAIL.DeleteSPS_EMP_ACTUALDETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsEmpActualdetail obj) {

        obj.fixup();
        SpsEmpActualdetail oldObj = ( SpsEmpActualdetail )queryForObject("SPS_EMP_ACTUALDETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_EMP_ACTUALDETAIL.DeleteSPS_EMP_ACTUALDETAIL", oldObj );

	}

	public boolean exists(SpsEmpActualdetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsEmpActualdetail obj = (SpsEmpActualdetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_EMP_ACTUALDETAIL.CountSPS_EMP_ACTUALDETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:28
	
	
	
}
