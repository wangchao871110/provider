package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.BdBillrule;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdBillruleDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/09 11:34:52
 */
@Repository
public class BdBillruleDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BILLRULE.CountFindAllBD_BILLRULE", null );
        return ret.intValue();
	}

	public BdBillrule findByPK(BdBillrule obj) {
		Object ret = queryForObject("BD_BILLRULE.FindByPK", obj );
		if(ret !=null)
			return (BdBillrule)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBillrule> freeFind(BdBillrule obj) {
		return queryForList("BD_BILLRULE.FreeFindBD_BILLRULE", obj );
	}

	public int countFreeFind(BdBillrule obj) {
        Integer ret = (Integer) queryForObject("BD_BILLRULE.CountFreeFindBD_BILLRULE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBillrule> freeFind(BdBillrule obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BILLRULE.FreeFindBD_BILLRULE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBillrule> freeFind(BdBillrule obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBillrule){
	    	_hashmap = ((BdBillrule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BILLRULE.FreeFindBD_BILLRULEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBillrule> freeFind(BdBillrule obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBillrule){
	    	_hashmap = ((BdBillrule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BILLRULE.FreeFindBD_BILLRULEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBillrule> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBillrule> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBillrule oneObj = (BdBillrule)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBillrule vo) {
        BdBillrule obj = (BdBillrule) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBillrule obj) {

		obj.fixup();
        return insert(cti, "BD_BILLRULE.InsertBD_BILLRULE", obj );
	}

	public int update(ContextInfo cti, BdBillrule obj) {

		obj.fixup();
		return update(cti, "BD_BILLRULE.UpdateBD_BILLRULE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBillrule obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BILLRULE.DeleteBD_BILLRULE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBillrule obj) {

        obj.fixup();
        BdBillrule oldObj = ( BdBillrule )queryForObject("BD_BILLRULE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BILLRULE.DeleteBD_BILLRULE", oldObj );

	}

	public boolean exists(BdBillrule vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBillrule obj = (BdBillrule) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BILLRULE.CountBD_BILLRULE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/09 11:34:52
	
	
	
}
