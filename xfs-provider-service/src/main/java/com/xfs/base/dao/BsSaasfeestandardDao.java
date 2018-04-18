package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsSaasfeestandard;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsSaasfeestandardDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 19:00:17
 */
@Repository
public class BsSaasfeestandardDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_SAASFEESTANDARD.CountFindAllBS_SAASFEESTANDARD", null );
        return ret.intValue();
	}

	public BsSaasfeestandard findByPK(BsSaasfeestandard obj) {
		Object ret = queryForObject("BS_SAASFEESTANDARD.FindByPK", obj );
		if(ret !=null)
			return (BsSaasfeestandard)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSaasfeestandard> freeFind(BsSaasfeestandard obj) {
		return queryForList("BS_SAASFEESTANDARD.FreeFindBS_SAASFEESTANDARD", obj );
	}

	public int countFreeFind(BsSaasfeestandard obj) {
        Integer ret = (Integer) queryForObject("BS_SAASFEESTANDARD.CountFreeFindBS_SAASFEESTANDARD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSaasfeestandard> freeFind(BsSaasfeestandard obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_SAASFEESTANDARD.FreeFindBS_SAASFEESTANDARD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSaasfeestandard> freeFind(BsSaasfeestandard obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSaasfeestandard){
	    	_hashmap = ((BsSaasfeestandard)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_SAASFEESTANDARD.FreeFindBS_SAASFEESTANDARDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSaasfeestandard> freeFind(BsSaasfeestandard obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSaasfeestandard){
	    	_hashmap = ((BsSaasfeestandard)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_SAASFEESTANDARD.FreeFindBS_SAASFEESTANDARDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsSaasfeestandard> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsSaasfeestandard> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsSaasfeestandard oneObj = (BsSaasfeestandard)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsSaasfeestandard vo) {
        BsSaasfeestandard obj = (BsSaasfeestandard) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsSaasfeestandard obj) {

		obj.fixup();
        return insert(cti, "BS_SAASFEESTANDARD.InsertBS_SAASFEESTANDARD", obj );
	}

	public int update(ContextInfo cti, BsSaasfeestandard obj) {

		obj.fixup();
		return update(cti, "BS_SAASFEESTANDARD.UpdateBS_SAASFEESTANDARD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsSaasfeestandard obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_SAASFEESTANDARD.DeleteBS_SAASFEESTANDARD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsSaasfeestandard obj) {

        obj.fixup();
        BsSaasfeestandard oldObj = ( BsSaasfeestandard )queryForObject("BS_SAASFEESTANDARD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_SAASFEESTANDARD.DeleteBS_SAASFEESTANDARD", oldObj );

	}

	public boolean exists(BsSaasfeestandard vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsSaasfeestandard obj = (BsSaasfeestandard) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_SAASFEESTANDARD.CountBS_SAASFEESTANDARD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 19:00:17
	
	public BsSaasfeestandard findReckonStandardFee(BsSaasfeestandard obj){
		Object ret = queryForObject("BS_SAASFEESTANDARD.FindReckonStandardFee_SAASFEESTANDARD", obj );
		if(ret !=null)
			return (BsSaasfeestandard)ret;
		else 
			return null;
	}
	
}
