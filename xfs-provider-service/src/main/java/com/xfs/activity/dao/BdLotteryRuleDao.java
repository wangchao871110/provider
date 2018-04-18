package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdLotteryRule;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdLotteryRuleDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/19 20:17:56
 */
@Repository
public class BdLotteryRuleDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_RULE.CountFindAllBD_LOTTERY_RULE", null );
        return ret.intValue();
	}

	public BdLotteryRule findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_LOTTERY_RULE.FindByPK", _hashmap );
		if(ret !=null)
			return (BdLotteryRule)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRule> freeFind(BdLotteryRule obj) {
		return queryForList("BD_LOTTERY_RULE.FreeFindBD_LOTTERY_RULE", obj );
	}

	public int countFreeFind(BdLotteryRule obj) {
        Integer ret = (Integer) queryForObject("BD_LOTTERY_RULE.CountFreeFindBD_LOTTERY_RULE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRule> freeFind(BdLotteryRule obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_LOTTERY_RULE.FreeFindBD_LOTTERY_RULE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRule> freeFind(BdLotteryRule obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryRule){
	    	_hashmap = ((BdLotteryRule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_LOTTERY_RULE.FreeFindBD_LOTTERY_RULEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLotteryRule> freeFind(BdLotteryRule obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLotteryRule){
	    	_hashmap = ((BdLotteryRule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_LOTTERY_RULE.FreeFindBD_LOTTERY_RULEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdLotteryRule> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdLotteryRule> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdLotteryRule oneObj = (BdLotteryRule)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdLotteryRule vo) {
        BdLotteryRule obj = (BdLotteryRule) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdLotteryRule obj) {

		obj.fixup();
        return insert(cti, "BD_LOTTERY_RULE.InsertBD_LOTTERY_RULE", obj );
	}

	public int update(ContextInfo cti, BdLotteryRule obj) {

		obj.fixup();
		return update(cti, "BD_LOTTERY_RULE.UpdateBD_LOTTERY_RULE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdLotteryRule obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_LOTTERY_RULE.DeleteBD_LOTTERY_RULE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdLotteryRule obj) {

        obj.fixup();
        BdLotteryRule oldObj = ( BdLotteryRule )queryForObject("BD_LOTTERY_RULE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_LOTTERY_RULE.DeleteBD_LOTTERY_RULE", oldObj );

	}

	public boolean exists(BdLotteryRule vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdLotteryRule obj = (BdLotteryRule) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_LOTTERY_RULE.CountBD_LOTTERY_RULE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/19 20:17:56
	
	public List<Map<String, Object>> findrulelist(Map vo){
		return queryForList("BD_LOTTERY_RULE.findrulelist", vo);
	}
	
	/**
	 * 获取没轮的中奖奖品
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findLotteryPrizes(BdLotteryRule vo) {
		return queryForList("BD_LOTTERY_RULE.FindBD_LOTTERY_RULE_PRIZE", vo);
	}
}
