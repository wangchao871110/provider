package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdLotteryRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdLotteryRecordDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/10 20:26:29
 */
@Repository
public class BdLotteryRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_RECORD.CountFindAllBD_LOTTERY_RECORD", null );
        return ret.intValue();
	}

	public BdLotteryRecord findByPK(BdLotteryRecord obj) {
		Object ret = queryForObject("BD_LOTTERY_RECORD.FindByPK", obj );
		if(ret !=null)
			return (BdLotteryRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRecord> freeFind(BdLotteryRecord obj) {
		return queryForList("BD_LOTTERY_RECORD.FreeFindBD_LOTTERY_RECORD", obj );
	}

	public int countFreeFind(BdLotteryRecord obj) {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_RECORD.CountFreeFindBD_LOTTERY_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRecord> freeFind(BdLotteryRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_LOTTERY_RECORD.FreeFindBD_LOTTERY_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRecord> freeFind(BdLotteryRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryRecord){
	    	_hashmap = ((BdLotteryRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_LOTTERY_RECORD.FreeFindBD_LOTTERY_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRecord> freeFind(BdLotteryRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryRecord){
	    	_hashmap = ((BdLotteryRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_LOTTERY_RECORD.FreeFindBD_LOTTERY_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdLotteryRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdLotteryRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdLotteryRecord oneObj = (BdLotteryRecord)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdLotteryRecord vo) {
        BdLotteryRecord obj = (BdLotteryRecord) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdLotteryRecord obj) {

		obj.fixup();
        return insert(cti, "BD_LOTTERY_RECORD.InsertBD_LOTTERY_RECORD", obj );
	}

	public int update(ContextInfo cti, BdLotteryRecord obj) {

		obj.fixup();
		return update(cti, "BD_LOTTERY_RECORD.UpdateBD_LOTTERY_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdLotteryRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_LOTTERY_RECORD.DeleteBD_LOTTERY_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdLotteryRecord obj) {

        obj.fixup();
        BdLotteryRecord oldObj = ( BdLotteryRecord )queryForObject("BD_LOTTERY_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_LOTTERY_RECORD.DeleteBD_LOTTERY_RECORD", oldObj );

	}

	public boolean exists(BdLotteryRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdLotteryRecord obj = (BdLotteryRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_LOTTERY_RECORD.CountBD_LOTTERY_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/10 20:26:29
	
	public int batchInsert(ContextInfo cti, List<BdLotteryRecord> list){
		return insert(cti, "BD_LOTTERY_RECORD.batchInsert", list );
	}
	
}
