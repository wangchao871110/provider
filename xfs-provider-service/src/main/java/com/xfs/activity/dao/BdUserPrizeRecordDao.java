package com.xfs.activity.dao;

import com.xfs.activity.model.BdUserPrizeRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * BdUserPrizeRecordDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/25 16:49:40
 */
@Repository
public class BdUserPrizeRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_USER_PRIZE_RECORD.CountFindAllBD_USER_PRIZE_RECORD", null );
        return ret.intValue();
	}

	public BdUserPrizeRecord findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_USER_PRIZE_RECORD.FindByPK", _hashmap );
		if(ret !=null)
			return (BdUserPrizeRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdUserPrizeRecord> freeFind(BdUserPrizeRecord obj) {
		return queryForList("BD_USER_PRIZE_RECORD.FreeFindBD_USER_PRIZE_RECORD", obj );
	}

	public int countFreeFind(BdUserPrizeRecord obj) {
        Integer ret = (Integer) queryForObject("BD_USER_PRIZE_RECORD.CountFreeFindBD_USER_PRIZE_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdUserPrizeRecord> freeFind(BdUserPrizeRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_USER_PRIZE_RECORD.FreeFindBD_USER_PRIZE_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdUserPrizeRecord> freeFind(BdUserPrizeRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdUserPrizeRecord){
	    	_hashmap = ((BdUserPrizeRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_USER_PRIZE_RECORD.FreeFindBD_USER_PRIZE_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdUserPrizeRecord> freeFind(BdUserPrizeRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdUserPrizeRecord){
	    	_hashmap = ((BdUserPrizeRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_USER_PRIZE_RECORD.FreeFindBD_USER_PRIZE_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdUserPrizeRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdUserPrizeRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdUserPrizeRecord oneObj = (BdUserPrizeRecord)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdUserPrizeRecord vo) {
        BdUserPrizeRecord obj = (BdUserPrizeRecord) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, BdUserPrizeRecord obj) {

		obj.fixup();
        return insert(cti, "BD_USER_PRIZE_RECORD.InsertBD_USER_PRIZE_RECORD", obj );
	}

	public int update(ContextInfo cti, BdUserPrizeRecord obj) {

		obj.fixup();
		return update(cti, "BD_USER_PRIZE_RECORD.UpdateBD_USER_PRIZE_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdUserPrizeRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_USER_PRIZE_RECORD.DeleteBD_USER_PRIZE_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdUserPrizeRecord obj) {

        obj.fixup();
        BdUserPrizeRecord oldObj = ( BdUserPrizeRecord )queryForObject("BD_USER_PRIZE_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_USER_PRIZE_RECORD.DeleteBD_USER_PRIZE_RECORD", oldObj );

	}

	public boolean exists(BdUserPrizeRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdUserPrizeRecord obj = (BdUserPrizeRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_USER_PRIZE_RECORD.CountBD_USER_PRIZE_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:49:40

	public int updateByOrder(ContextInfo cti, String outTradeOrder) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("outTradeOrder", outTradeOrder );
		return update(cti, "BD_USER_PRIZE_RECORD.UpdateBD_USER_PRIZE_RECORD_BY_ORDER", _hashmap);
	}
	
}
