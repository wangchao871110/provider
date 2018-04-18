package com.xfs.mall.order.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.order.model.SpsMallOrderArea;

/**
 * SpsMallOrderAreaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/10 19:26:49
 */
@Repository
public class SpsMallOrderAreaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER_AREA.CountFindAllSPS_MALL_ORDER_AREA", null );
        return ret.intValue();
	}

	public SpsMallOrderArea findByPK(SpsMallOrderArea obj) {
		Object ret = queryForObject("SPS_MALL_ORDER_AREA.FindByPK", obj );
		if(ret !=null)
			return (SpsMallOrderArea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderArea> freeFind(SpsMallOrderArea obj) {
		return queryForList("SPS_MALL_ORDER_AREA.FreeFindSPS_MALL_ORDER_AREA", obj );
	}

	public int countFreeFind(SpsMallOrderArea obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER_AREA.CountFreeFindSPS_MALL_ORDER_AREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderArea> freeFind(SpsMallOrderArea obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_ORDER_AREA.FreeFindSPS_MALL_ORDER_AREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderArea> freeFind(SpsMallOrderArea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrderArea){
	    	_hashmap = ((SpsMallOrderArea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_ORDER_AREA.FreeFindSPS_MALL_ORDER_AREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderArea> freeFind(SpsMallOrderArea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrderArea){
	    	_hashmap = ((SpsMallOrderArea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_ORDER_AREA.FreeFindSPS_MALL_ORDER_AREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallOrderArea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallOrderArea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallOrderArea oneObj = (SpsMallOrderArea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallOrderArea vo) {
        SpsMallOrderArea obj = (SpsMallOrderArea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallOrderArea obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_ORDER_AREA.InsertSPS_MALL_ORDER_AREA", obj );
	}

	public int update(ContextInfo cti, SpsMallOrderArea obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_ORDER_AREA.UpdateSPS_MALL_ORDER_AREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallOrderArea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_ORDER_AREA.DeleteSPS_MALL_ORDER_AREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallOrderArea obj) {

        obj.fixup();
        SpsMallOrderArea oldObj = ( SpsMallOrderArea )queryForObject("SPS_MALL_ORDER_AREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_ORDER_AREA.DeleteSPS_MALL_ORDER_AREA", oldObj );

	}

	public boolean exists(SpsMallOrderArea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallOrderArea obj = (SpsMallOrderArea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_ORDER_AREA.CountSPS_MALL_ORDER_AREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 19:26:49
	
	
	
}
