package com.xfs.settlement.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.settlement.model.BlPayBusinessrel;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BlPayBusinessrelDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/16 10:28:39
 */
@Repository
public class BlPayBusinessrelDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_BUSINESSREL.CountFindAllBL_PAY_BUSINESSREL", null );
        return ret.intValue();
	}

	public BlPayBusinessrel findByPK(BlPayBusinessrel obj) {
		Object ret = queryForObject("BL_PAY_BUSINESSREL.FindByPK", obj );
		if(ret !=null)
			return (BlPayBusinessrel)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusinessrel> freeFind(BlPayBusinessrel obj) {
		return queryForList("BL_PAY_BUSINESSREL.FreeFindBL_PAY_BUSINESSREL", obj );
	}

	public int countFreeFind(BlPayBusinessrel obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_BUSINESSREL.CountFreeFindBL_PAY_BUSINESSREL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusinessrel> freeFind(BlPayBusinessrel obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_BUSINESSREL.FreeFindBL_PAY_BUSINESSREL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusinessrel> freeFind(BlPayBusinessrel obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayBusinessrel){
	    	_hashmap = ((BlPayBusinessrel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_BUSINESSREL.FreeFindBL_PAY_BUSINESSRELOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusinessrel> freeFind(BlPayBusinessrel obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayBusinessrel){
	    	_hashmap = ((BlPayBusinessrel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_BUSINESSREL.FreeFindBL_PAY_BUSINESSRELOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayBusinessrel> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayBusinessrel> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayBusinessrel oneObj = (BlPayBusinessrel)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,BlPayBusinessrel vo) {
        BlPayBusinessrel obj = (BlPayBusinessrel) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert( cti,obj );
        }
	}

	public int insert(ContextInfo cti,BlPayBusinessrel obj) {

		obj.fixup();
        return insert(cti,"BL_PAY_BUSINESSREL.InsertBL_PAY_BUSINESSREL", obj );
	}

	public int update(ContextInfo cti,BlPayBusinessrel obj) {

		obj.fixup();
		return update(cti, "BL_PAY_BUSINESSREL.UpdateBL_PAY_BUSINESSREL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,BlPayBusinessrel obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete( cti,"BL_PAY_BUSINESSREL.DeleteBL_PAY_BUSINESSREL", obj );

	}

	public int removeObjectTree(ContextInfo cti,BlPayBusinessrel obj) {

        obj.fixup();
        BlPayBusinessrel oldObj = ( BlPayBusinessrel )queryForObject("BL_PAY_BUSINESSREL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"BL_PAY_BUSINESSREL.DeleteBL_PAY_BUSINESSREL", oldObj );

	}

	public boolean exists(BlPayBusinessrel vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayBusinessrel obj = (BlPayBusinessrel) vo;

        keysFilled = keysFilled && ( null != obj.getPayBusId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_BUSINESSREL.CountBL_PAY_BUSINESSREL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:39
	
	
	
}
