package com.xfs.insurance.dao;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.model.BsCiInsurer;

/**
 * BsCiInsurerDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 14:37:15
 */
@Repository
public class BsCiInsurerDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CI_INSURER.CountFindAllBS_CI_INSURER", null );
        return ret.intValue();
	}

	public BsCiInsurer findByPK(BsCiInsurer obj) {
		Object ret = queryForObject("BS_CI_INSURER.FindByPK", obj );
		if(ret !=null)
			return (BsCiInsurer)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiInsurer> freeFind(BsCiInsurer obj) {
		return queryForList("BS_CI_INSURER.FreeFindBS_CI_INSURER", obj );
	}

	public int countFreeFind(BsCiInsurer obj) {
        Integer ret = (Integer) queryForObject("BS_CI_INSURER.CountFreeFindBS_CI_INSURER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiInsurer> freeFind(BsCiInsurer obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CI_INSURER.FreeFindBS_CI_INSURER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiInsurer> freeFind(BsCiInsurer obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsCiInsurer){
	    	_hashmap = ((BsCiInsurer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_CI_INSURER.FreeFindBS_CI_INSUREROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiInsurer> freeFind(BsCiInsurer obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsCiInsurer){
	    	_hashmap = ((BsCiInsurer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_CI_INSURER.FreeFindBS_CI_INSUREROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsCiInsurer> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsCiInsurer> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsCiInsurer oneObj = (BsCiInsurer)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsCiInsurer vo) {
        BsCiInsurer obj = (BsCiInsurer) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsCiInsurer obj) {

		obj.fixup();
        return insert(cti, "BS_CI_INSURER.InsertBS_CI_INSURER", obj );
	}

	public int update(ContextInfo cti, BsCiInsurer obj) {

		obj.fixup();
		return update(cti, "BS_CI_INSURER.UpdateBS_CI_INSURER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsCiInsurer obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_CI_INSURER.DeleteBS_CI_INSURER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsCiInsurer obj) {

        obj.fixup();
        BsCiInsurer oldObj = ( BsCiInsurer )queryForObject("BS_CI_INSURER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_CI_INSURER.DeleteBS_CI_INSURER", oldObj );

	}

	public boolean exists(BsCiInsurer vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsCiInsurer obj = (BsCiInsurer) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_CI_INSURER.CountBS_CI_INSURER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 14:37:15
	
	
	
}
