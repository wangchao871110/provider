package com.xfs.mall.customer.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.customer.model.CsMallCustomerAssess;
import com.xfs.mall.dto.CsMallCustomerAssessDTO;

/**
 * CsMallCustomerAssessDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/22 13:55:57
 */
@Repository
public class CsMallCustomerAssessDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ASSESS.CountFindAllCS_MALL_CUSTOMER_ASSESS", null );
        return ret.intValue();
	}

	public CsMallCustomerAssess findByPK(CsMallCustomerAssess obj) {
		Object ret = queryForObject("CS_MALL_CUSTOMER_ASSESS.FindByPK", obj );
		if(ret !=null)
			return (CsMallCustomerAssess)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerAssess> freeFind(CsMallCustomerAssess obj) {
		return queryForList("CS_MALL_CUSTOMER_ASSESS.FreeFindCS_MALL_CUSTOMER_ASSESS", obj );
	}

	public int countFreeFind(CsMallCustomerAssess obj) {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ASSESS.CountFreeFindCS_MALL_CUSTOMER_ASSESS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerAssess> freeFind(CsMallCustomerAssess obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_MALL_CUSTOMER_ASSESS.FreeFindCS_MALL_CUSTOMER_ASSESS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerAssess> freeFind(CsMallCustomerAssess obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerAssess){
	    	_hashmap = ((CsMallCustomerAssess)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_MALL_CUSTOMER_ASSESS.FreeFindCS_MALL_CUSTOMER_ASSESSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerAssess> freeFind(CsMallCustomerAssess obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerAssess){
	    	_hashmap = ((CsMallCustomerAssess)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_MALL_CUSTOMER_ASSESS.FreeFindCS_MALL_CUSTOMER_ASSESSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsMallCustomerAssess> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsMallCustomerAssess> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsMallCustomerAssess oneObj = (CsMallCustomerAssess)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsMallCustomerAssess vo) {
        CsMallCustomerAssess obj = (CsMallCustomerAssess) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsMallCustomerAssess obj) {

		obj.fixup();
        return insert(cti, "CS_MALL_CUSTOMER_ASSESS.InsertCS_MALL_CUSTOMER_ASSESS", obj );
	}

	public int update(ContextInfo cti, CsMallCustomerAssess obj) {

		obj.fixup();
		return update(cti, "CS_MALL_CUSTOMER_ASSESS.UpdateCS_MALL_CUSTOMER_ASSESS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsMallCustomerAssess obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_MALL_CUSTOMER_ASSESS.DeleteCS_MALL_CUSTOMER_ASSESS", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsMallCustomerAssess obj) {

        obj.fixup();
        CsMallCustomerAssess oldObj = ( CsMallCustomerAssess )queryForObject("CS_MALL_CUSTOMER_ASSESS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_MALL_CUSTOMER_ASSESS.DeleteCS_MALL_CUSTOMER_ASSESS", oldObj );

	}

	public boolean exists(CsMallCustomerAssess vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsMallCustomerAssess obj = (CsMallCustomerAssess) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_MALL_CUSTOMER_ASSESS.CountCS_MALL_CUSTOMER_ASSESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:55:57
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerAssessDTO> freeFind(CsMallCustomerAssessDTO dto, int pageIndex, int pageSize) {
		return getPaginatedList("CS_MALL_CUSTOMER_ASSESS.BsFreeFindCS_MALL_CUSTOMER_ASSESS", dto, pageIndex, pageSize );
	}
	public int countFreeFind(CsMallCustomerAssessDTO dto) {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ASSESS.CountBsFreeFindCS_MALL_CUSTOMER_ASSESS", dto );
        return ret.intValue();
	}
}
