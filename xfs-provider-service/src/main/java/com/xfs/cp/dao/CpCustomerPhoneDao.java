package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpCustomerPhone;

/**
 * CpCustomerPhoneDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:22
 */
@Repository
public class CpCustomerPhoneDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_CUSTOMER_PHONE.CountFindAllCP_CUSTOMER_PHONE", null );
        return ret.intValue();
	}

	public CpCustomerPhone findByPK(CpCustomerPhone obj) {
		Object ret = queryForObject("CP_CUSTOMER_PHONE.FindByPK", obj );
		if(ret !=null)
			return (CpCustomerPhone)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpCustomerPhone> freeFind(CpCustomerPhone obj) {
		return queryForList("CP_CUSTOMER_PHONE.FreeFindCP_CUSTOMER_PHONE", obj );
	}

	public int countFreeFind(CpCustomerPhone obj) {
        Integer ret = (Integer) queryForObject("CP_CUSTOMER_PHONE.CountFreeFindCP_CUSTOMER_PHONE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpCustomerPhone> freeFind(CpCustomerPhone obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_CUSTOMER_PHONE.FreeFindCP_CUSTOMER_PHONE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpCustomerPhone> freeFind(CpCustomerPhone obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpCustomerPhone){
	    	_hashmap = ((CpCustomerPhone)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_CUSTOMER_PHONE.FreeFindCP_CUSTOMER_PHONEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpCustomerPhone> freeFind(CpCustomerPhone obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpCustomerPhone){
	    	_hashmap = ((CpCustomerPhone)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_CUSTOMER_PHONE.FreeFindCP_CUSTOMER_PHONEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpCustomerPhone> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpCustomerPhone> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpCustomerPhone oneObj = (CpCustomerPhone)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpCustomerPhone vo) {
        CpCustomerPhone obj = (CpCustomerPhone) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpCustomerPhone obj) {

		obj.fixup();
        return insert(cti, "CP_CUSTOMER_PHONE.InsertCP_CUSTOMER_PHONE", obj );
	}

	public int update(ContextInfo cti, CpCustomerPhone obj) {

		obj.fixup();
		return update(cti, "CP_CUSTOMER_PHONE.UpdateCP_CUSTOMER_PHONE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpCustomerPhone obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_CUSTOMER_PHONE.DeleteCP_CUSTOMER_PHONE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpCustomerPhone obj) {

        obj.fixup();
        CpCustomerPhone oldObj = ( CpCustomerPhone )queryForObject("CP_CUSTOMER_PHONE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_CUSTOMER_PHONE.DeleteCP_CUSTOMER_PHONE", oldObj );

	}

	public boolean exists(CpCustomerPhone vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpCustomerPhone obj = (CpCustomerPhone) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_CUSTOMER_PHONE.CountCP_CUSTOMER_PHONE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22
	
	
	
}
