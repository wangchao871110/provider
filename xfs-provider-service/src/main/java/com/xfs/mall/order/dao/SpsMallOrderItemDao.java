package com.xfs.mall.order.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.order.model.SpsMallOrderItem;

/**
 * SpsMallOrderItemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/12 13:10:04
 */
@Repository
public class SpsMallOrderItemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER_ITEM.CountFindAllSPS_MALL_ORDER_ITEM", null );
        return ret.intValue();
	}

	public SpsMallOrderItem findByPK(SpsMallOrderItem obj) {
		Object ret = queryForObject("SPS_MALL_ORDER_ITEM.FindByPK", obj );
		if(ret !=null)
			return (SpsMallOrderItem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderItem> freeFind(SpsMallOrderItem obj) {
		return queryForList("SPS_MALL_ORDER_ITEM.FreeFindSPS_MALL_ORDER_ITEM", obj );
	}

	public int countFreeFind(SpsMallOrderItem obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER_ITEM.CountFreeFindSPS_MALL_ORDER_ITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderItem> freeFind(SpsMallOrderItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_ORDER_ITEM.FreeFindSPS_MALL_ORDER_ITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderItem> freeFind(SpsMallOrderItem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrderItem){
	    	_hashmap = ((SpsMallOrderItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_ORDER_ITEM.FreeFindSPS_MALL_ORDER_ITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrderItem> freeFind(SpsMallOrderItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrderItem){
	    	_hashmap = ((SpsMallOrderItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_ORDER_ITEM.FreeFindSPS_MALL_ORDER_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallOrderItem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallOrderItem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallOrderItem oneObj = (SpsMallOrderItem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallOrderItem vo) {
        SpsMallOrderItem obj = (SpsMallOrderItem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallOrderItem obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_ORDER_ITEM.InsertSPS_MALL_ORDER_ITEM", obj );
	}

	public int update(ContextInfo cti, SpsMallOrderItem obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_ORDER_ITEM.UpdateSPS_MALL_ORDER_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallOrderItem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_ORDER_ITEM.DeleteSPS_MALL_ORDER_ITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallOrderItem obj) {

        obj.fixup();
        SpsMallOrderItem oldObj = ( SpsMallOrderItem )queryForObject("SPS_MALL_ORDER_ITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_ORDER_ITEM.DeleteSPS_MALL_ORDER_ITEM", oldObj );

	}

	public boolean exists(SpsMallOrderItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallOrderItem obj = (SpsMallOrderItem) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_ORDER_ITEM.CountSPS_MALL_ORDER_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/12 13:10:04
	
	
	
}
