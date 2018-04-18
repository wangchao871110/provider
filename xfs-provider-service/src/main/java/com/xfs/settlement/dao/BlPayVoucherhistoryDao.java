package com.xfs.settlement.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.settlement.model.BlPayVoucherhistory;

/**
 * BlPayVoucherhistoryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 21:08:04
 */
@Repository
public class BlPayVoucherhistoryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_VOUCHERHISTORY.CountFindAllBL_PAY_VOUCHERHISTORY", null );
        return ret.intValue();
	}

	public BlPayVoucherhistory findByPK(BlPayVoucherhistory obj) {
		Object ret = queryForObject("BL_PAY_VOUCHERHISTORY.FindByPK", obj );
		if(ret !=null)
			return (BlPayVoucherhistory)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucherhistory> freeFind(BlPayVoucherhistory obj) {
		return queryForList("BL_PAY_VOUCHERHISTORY.FreeFindBL_PAY_VOUCHERHISTORY", obj );
	}

	public int countFreeFind(BlPayVoucherhistory obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_VOUCHERHISTORY.CountFreeFindBL_PAY_VOUCHERHISTORY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucherhistory> freeFind(BlPayVoucherhistory obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_VOUCHERHISTORY.FreeFindBL_PAY_VOUCHERHISTORY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucherhistory> freeFind(BlPayVoucherhistory obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayVoucherhistory){
	    	_hashmap = ((BlPayVoucherhistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_VOUCHERHISTORY.FreeFindBL_PAY_VOUCHERHISTORYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucherhistory> freeFind(BlPayVoucherhistory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayVoucherhistory){
	    	_hashmap = ((BlPayVoucherhistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_VOUCHERHISTORY.FreeFindBL_PAY_VOUCHERHISTORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayVoucherhistory> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayVoucherhistory> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayVoucherhistory oneObj = (BlPayVoucherhistory)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlPayVoucherhistory vo) {
        BlPayVoucherhistory obj = (BlPayVoucherhistory) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BlPayVoucherhistory obj) {

		obj.fixup();
        return insert(cti, "BL_PAY_VOUCHERHISTORY.InsertBL_PAY_VOUCHERHISTORY", obj );
	}

	public int update(ContextInfo cti, BlPayVoucherhistory obj) {

		obj.fixup();
		return update(cti, "BL_PAY_VOUCHERHISTORY.UpdateBL_PAY_VOUCHERHISTORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlPayVoucherhistory obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_PAY_VOUCHERHISTORY.DeleteBL_PAY_VOUCHERHISTORY", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlPayVoucherhistory obj) {

        obj.fixup();
        BlPayVoucherhistory oldObj = ( BlPayVoucherhistory )queryForObject("BL_PAY_VOUCHERHISTORY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BL_PAY_VOUCHERHISTORY.DeleteBL_PAY_VOUCHERHISTORY", oldObj );

	}

	public boolean exists(BlPayVoucherhistory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayVoucherhistory obj = (BlPayVoucherhistory) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_VOUCHERHISTORY.CountBL_PAY_VOUCHERHISTORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:08:04
	
	
	
}
