package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsEcontractApply;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsEcontractApplyDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 22:51:24
 */
@Repository
public class BsEcontractApplyDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_ECONTRACT_APPLY.CountFindAllBS_ECONTRACT_APPLY", null );
        return ret.intValue();
	}

	public BsEcontractApply findByPK(BsEcontractApply obj) {
		Object ret = queryForObject("BS_ECONTRACT_APPLY.FindByPK", obj );
		if(ret !=null)
			return (BsEcontractApply)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsEcontractApply> freeFind(BsEcontractApply obj) {
		return queryForList("BS_ECONTRACT_APPLY.FreeFindBS_ECONTRACT_APPLY", obj );
	}

	public int countFreeFind(BsEcontractApply obj) {
        Integer ret = (Integer) queryForObject("BS_ECONTRACT_APPLY.CountFreeFindBS_ECONTRACT_APPLY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsEcontractApply> freeFind(BsEcontractApply obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_ECONTRACT_APPLY.FreeFindBS_ECONTRACT_APPLY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsEcontractApply> freeFind(BsEcontractApply obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsEcontractApply){
	    	_hashmap = ((BsEcontractApply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_ECONTRACT_APPLY.FreeFindBS_ECONTRACT_APPLYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsEcontractApply> freeFind(BsEcontractApply obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsEcontractApply){
	    	_hashmap = ((BsEcontractApply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_ECONTRACT_APPLY.FreeFindBS_ECONTRACT_APPLYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsEcontractApply> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsEcontractApply> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsEcontractApply oneObj = (BsEcontractApply)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsEcontractApply vo) {
        BsEcontractApply obj = (BsEcontractApply) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsEcontractApply obj) {

		obj.fixup();
        return insert(cti, "BS_ECONTRACT_APPLY.InsertBS_ECONTRACT_APPLY", obj );
	}

	public int update(ContextInfo cti, BsEcontractApply obj) {

		obj.fixup();
		return update(cti, "BS_ECONTRACT_APPLY.UpdateBS_ECONTRACT_APPLY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsEcontractApply obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_ECONTRACT_APPLY.DeleteBS_ECONTRACT_APPLY", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsEcontractApply obj) {

        obj.fixup();
        BsEcontractApply oldObj = ( BsEcontractApply )queryForObject("BS_ECONTRACT_APPLY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_ECONTRACT_APPLY.DeleteBS_ECONTRACT_APPLY", oldObj );

	}

	public boolean exists(BsEcontractApply vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsEcontractApply obj = (BsEcontractApply) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_ECONTRACT_APPLY.CountBS_ECONTRACT_APPLY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 22:51:24
	
	
	
}
