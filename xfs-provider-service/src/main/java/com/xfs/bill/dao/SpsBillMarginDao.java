package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsBillMargin;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsBillMarginDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 11:05:29
 */
@Repository
public class SpsBillMarginDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_MARGIN.CountFindAllSPS_BILL_MARGIN", null );
        return ret.intValue();
	}

	public SpsBillMargin findByPK(SpsBillMargin obj) {
		Object ret = queryForObject("SPS_BILL_MARGIN.FindByPK", obj );
		if(ret !=null)
			return (SpsBillMargin)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillMargin> freeFind(SpsBillMargin obj) {
		return queryForList("SPS_BILL_MARGIN.FreeFindSPS_BILL_MARGIN", obj );
	}

	public int countFreeFind(SpsBillMargin obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_MARGIN.CountFreeFindSPS_BILL_MARGIN", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillMargin> freeFind(SpsBillMargin obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_MARGIN.FreeFindSPS_BILL_MARGIN", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillMargin> freeFind(SpsBillMargin obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillMargin){
	    	_hashmap = ((SpsBillMargin)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_MARGIN.FreeFindSPS_BILL_MARGINOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillMargin> freeFind(SpsBillMargin obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillMargin){
	    	_hashmap = ((SpsBillMargin)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_MARGIN.FreeFindSPS_BILL_MARGINOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillMargin> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillMargin> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillMargin oneObj = (SpsBillMargin)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillMargin vo) {
        SpsBillMargin obj = (SpsBillMargin) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsBillMargin obj) {

		obj.fixup();
        return insert(cti, "SPS_BILL_MARGIN.InsertSPS_BILL_MARGIN", obj );
	}

	public int update(ContextInfo cti, SpsBillMargin obj) {

		obj.fixup();
		return update(cti, "SPS_BILL_MARGIN.UpdateSPS_BILL_MARGIN", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBillMargin obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_MARGIN.DeleteSPS_BILL_MARGIN", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillMargin obj) {

        obj.fixup();
        SpsBillMargin oldObj = ( SpsBillMargin )queryForObject("SPS_BILL_MARGIN.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BILL_MARGIN.DeleteSPS_BILL_MARGIN", oldObj );

	}

	public boolean exists(SpsBillMargin vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillMargin obj = (SpsBillMargin) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_MARGIN.CountSPS_BILL_MARGIN", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:29
	
	
	
}
