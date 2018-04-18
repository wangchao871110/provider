package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdLotteryPool;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdLotteryPoolDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/14 16:10:55
 */
@Repository
public class BdLotteryPoolDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_POOL.CountFindAllBD_LOTTERY_POOL", null );
        return ret.intValue();
	}

	public BdLotteryPool findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_LOTTERY_POOL.FindByPK", _hashmap );
		if(ret !=null)
			return (BdLotteryPool)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryPool> freeFind(BdLotteryPool obj) {
		return queryForList("BD_LOTTERY_POOL.FreeFindBD_LOTTERY_POOL", obj );
	}

	public int countFreeFind(BdLotteryPool obj) {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_POOL.CountFreeFindBD_LOTTERY_POOL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryPool> freeFind(BdLotteryPool obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_LOTTERY_POOL.FreeFindBD_LOTTERY_POOL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryPool> freeFind(BdLotteryPool obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryPool){
	    	_hashmap = ((BdLotteryPool)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_LOTTERY_POOL.FreeFindBD_LOTTERY_POOLOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryPool> freeFind(BdLotteryPool obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryPool){
	    	_hashmap = ((BdLotteryPool)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_LOTTERY_POOL.FreeFindBD_LOTTERY_POOLOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdLotteryPool> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdLotteryPool> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdLotteryPool oneObj = (BdLotteryPool)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdLotteryPool vo) {
        BdLotteryPool obj = (BdLotteryPool) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdLotteryPool obj) {

		obj.fixup();
        return insert(cti, "BD_LOTTERY_POOL.InsertBD_LOTTERY_POOL", obj );
	}

	public int update(ContextInfo cti, BdLotteryPool obj) {

		obj.fixup();
		return update(cti, "BD_LOTTERY_POOL.UpdateBD_LOTTERY_POOL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdLotteryPool obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_LOTTERY_POOL.DeleteBD_LOTTERY_POOL", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdLotteryPool obj) {

        obj.fixup();
        BdLotteryPool oldObj = ( BdLotteryPool )queryForObject("BD_LOTTERY_POOL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_LOTTERY_POOL.DeleteBD_LOTTERY_POOL", oldObj );

	}

	public boolean exists(BdLotteryPool vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdLotteryPool obj = (BdLotteryPool) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_LOTTERY_POOL.CountBD_LOTTERY_POOL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/14 16:10:55
	
	public List<Map<String, Object>> findPool(Map map,int pageIndex, int pageSize){
		return getPaginatedList("BD_LOTTERY_POOL.FindPool", map, pageIndex, pageSize);
	} 	
	
	public Integer findPoolCount(Map map){
		Integer ret = (Integer) queryForObject("BD_LOTTERY_POOL.FindPoolCount", map );
        return ret.intValue();
	}
	
	public List<Map> FindBD_LOTTERY_POOL_BY_LotteryCode(BdLotteryPool obj, String lotteryCode) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof BdLotteryPool){
			_hashmap = ((BdLotteryPool)obj).toHashMap();
		}
		_hashmap.put("lotteryCode", lotteryCode );

		return queryForList("BD_LOTTERY_POOL.FindBD_LOTTERY_POOL_BY_LotteryCode", _hashmap);
	}
	
	/**
	 * 查询活动的奖品
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findLotteryPirze(BdLotteryPool vo) {
		return queryForList("BD_LOTTERY_POOL.FindLotteryPirze", vo);
	}
}
