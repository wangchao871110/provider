package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdPrize;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdPrizeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/17 10:59:57
 */
@Repository
public class BdPrizeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_PRIZE.CountFindAllBD_PRIZE", null );
        return ret.intValue();
	}

	public BdPrize findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_PRIZE.FindByPK", _hashmap );
		if(ret !=null)
			return (BdPrize)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdPrize> freeFind(BdPrize obj) {
		return queryForList("BD_PRIZE.FreeFindBD_PRIZE", obj );
	}

	public int countFreeFind(BdPrize obj) {
        Integer ret = (Integer) queryForObject("BD_PRIZE.CountFreeFindBD_PRIZE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdPrize> freeFind(BdPrize obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_PRIZE.FreeFindBD_PRIZE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdPrize> freeFind(BdPrize obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdPrize){
	    	_hashmap = ((BdPrize)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_PRIZE.FreeFindBD_PRIZEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdPrize> freeFind(BdPrize obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdPrize){
	    	_hashmap = ((BdPrize)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_PRIZE.FreeFindBD_PRIZEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdPrize> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdPrize> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdPrize oneObj = (BdPrize)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdPrize vo) {
        BdPrize obj = (BdPrize) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdPrize obj) {

		obj.fixup();
        return insert(cti, "BD_PRIZE.InsertBD_PRIZE", obj );
	}

	public int update(ContextInfo cti, BdPrize obj) {

		obj.fixup();
		return update(cti, "BD_PRIZE.UpdateBD_PRIZE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdPrize obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_PRIZE.DeleteBD_PRIZE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdPrize obj) {

        obj.fixup();
        BdPrize oldObj = ( BdPrize )queryForObject("BD_PRIZE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_PRIZE.DeleteBD_PRIZE", oldObj );

	}

	public boolean exists(BdPrize vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdPrize obj = (BdPrize) vo;

        keysFilled = keysFilled && ( null != obj.getPrizeId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_PRIZE.CountBD_PRIZE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/17 10:59:57
	
	
	
}
