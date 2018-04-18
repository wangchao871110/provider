package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdActivityJoinRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdActivityJoinRecordDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/18 16:34:21
 */
@Repository
public class BdActivityJoinRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_ACTIVITY_JOIN_RECORD.CountFindAllBD_ACTIVITY_JOIN_RECORD", null );
        return ret.intValue();
	}

	public BdActivityJoinRecord findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_ACTIVITY_JOIN_RECORD.FindByPK", _hashmap );
		if(ret !=null)
			return (BdActivityJoinRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdActivityJoinRecord> freeFind(BdActivityJoinRecord obj) {
		return queryForList("BD_ACTIVITY_JOIN_RECORD.FreeFindBD_ACTIVITY_JOIN_RECORD", obj );
	}

	public int countFreeFind(BdActivityJoinRecord obj) {
        Integer ret = (Integer) queryForObject("BD_ACTIVITY_JOIN_RECORD.CountFreeFindBD_ACTIVITY_JOIN_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdActivityJoinRecord> freeFind(BdActivityJoinRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_ACTIVITY_JOIN_RECORD.FreeFindBD_ACTIVITY_JOIN_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdActivityJoinRecord> freeFind(BdActivityJoinRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdActivityJoinRecord){
	    	_hashmap = ((BdActivityJoinRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_ACTIVITY_JOIN_RECORD.FreeFindBD_ACTIVITY_JOIN_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdActivityJoinRecord> freeFind(BdActivityJoinRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdActivityJoinRecord){
	    	_hashmap = ((BdActivityJoinRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_ACTIVITY_JOIN_RECORD.FreeFindBD_ACTIVITY_JOIN_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdActivityJoinRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdActivityJoinRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdActivityJoinRecord oneObj = (BdActivityJoinRecord)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdActivityJoinRecord vo) {
        BdActivityJoinRecord obj = (BdActivityJoinRecord) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdActivityJoinRecord obj) {

		obj.fixup();
        return insert(cti, "BD_ACTIVITY_JOIN_RECORD.InsertBD_ACTIVITY_JOIN_RECORD", obj );
	}

	public int update(ContextInfo cti, BdActivityJoinRecord obj) {

		obj.fixup();
		return update(cti, "BD_ACTIVITY_JOIN_RECORD.UpdateBD_ACTIVITY_JOIN_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdActivityJoinRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_ACTIVITY_JOIN_RECORD.DeleteBD_ACTIVITY_JOIN_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdActivityJoinRecord obj) {

        obj.fixup();
        BdActivityJoinRecord oldObj = ( BdActivityJoinRecord )queryForObject("BD_ACTIVITY_JOIN_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_ACTIVITY_JOIN_RECORD.DeleteBD_ACTIVITY_JOIN_RECORD", oldObj );

	}

	public boolean exists(BdActivityJoinRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdActivityJoinRecord obj = (BdActivityJoinRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_ACTIVITY_JOIN_RECORD.CountBD_ACTIVITY_JOIN_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/18 16:34:21
	
	
	
}
