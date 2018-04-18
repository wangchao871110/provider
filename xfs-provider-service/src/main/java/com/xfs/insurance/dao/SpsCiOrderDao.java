package com.xfs.insurance.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.model.SpsCiOrder;

/**
 * SpsCiOrderDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/10 11:59:05
 */
@Repository
public class SpsCiOrderDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_CI_ORDER.CountFindAllSPS_CI_ORDER", null );
        return ret.intValue();
	}

	public SpsCiOrder findByPK(SpsCiOrder obj) {
		Object ret = queryForObject("SPS_CI_ORDER.FindByPK", obj );
		if(ret !=null)
			return (SpsCiOrder)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsCiOrder> freeFind(SpsCiOrder obj) {
		return queryForList("SPS_CI_ORDER.FreeFindSPS_CI_ORDER", obj );
	}

	public int countFreeFind(SpsCiOrder obj) {
        Integer ret = (Integer) queryForObject("SPS_CI_ORDER.CountFreeFindSPS_CI_ORDER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsCiOrder> freeFind(SpsCiOrder obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_CI_ORDER.FreeFindSPS_CI_ORDER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsCiOrder> freeFind(SpsCiOrder obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsCiOrder){
	    	_hashmap = ((SpsCiOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_CI_ORDER.FreeFindSPS_CI_ORDEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsCiOrder> freeFind(SpsCiOrder obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsCiOrder){
	    	_hashmap = ((SpsCiOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_CI_ORDER.FreeFindSPS_CI_ORDEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsCiOrder> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsCiOrder> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsCiOrder oneObj = (SpsCiOrder)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsCiOrder vo) {
        SpsCiOrder obj = (SpsCiOrder) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsCiOrder obj) {

		obj.fixup();
        return insert(cti, "SPS_CI_ORDER.InsertSPS_CI_ORDER", obj );
	}

	public int update(ContextInfo cti, SpsCiOrder obj) {

		obj.fixup();
		return update(cti, "SPS_CI_ORDER.UpdateSPS_CI_ORDER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsCiOrder obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_CI_ORDER.DeleteSPS_CI_ORDER", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsCiOrder obj) {

        obj.fixup();
        SpsCiOrder oldObj = ( SpsCiOrder )queryForObject("SPS_CI_ORDER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_CI_ORDER.DeleteSPS_CI_ORDER", oldObj );

	}

	public boolean exists(SpsCiOrder vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsCiOrder obj = (SpsCiOrder) vo;

        keysFilled = keysFilled && ( null != obj.getOrderId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_CI_ORDER.CountSPS_CI_ORDER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/10 11:59:05
	
	
	
}
