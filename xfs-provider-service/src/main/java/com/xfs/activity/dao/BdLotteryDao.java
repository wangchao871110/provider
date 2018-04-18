package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdLottery;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdLotteryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/12 15:55:00
 */
@Repository
public class BdLotteryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_LOTTERY.CountFindAllBD_LOTTERY", null );
        return ret.intValue();
	}

	public BdLottery findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_LOTTERY.FindByPK", _hashmap );
		if(ret !=null)
			return (BdLottery)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLottery> freeFind(BdLottery obj) {
		return queryForList("BD_LOTTERY.FreeFindBD_LOTTERY", obj );
	}

	public int countFreeFind(BdLottery obj) {
        Integer ret = (Integer) queryForObject("BD_LOTTERY.CountFreeFindBD_LOTTERY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLottery> freeFind(BdLottery obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_LOTTERY.FreeFindBD_LOTTERY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLottery> freeFind(BdLottery obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLottery){
	    	_hashmap = ((BdLottery)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_LOTTERY.FreeFindBD_LOTTERYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdLottery> freeFind(BdLottery obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdLottery){
	    	_hashmap = ((BdLottery)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_LOTTERY.FreeFindBD_LOTTERYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdLottery> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdLottery> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdLottery oneObj = (BdLottery)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdLottery vo) {
        BdLottery obj = (BdLottery) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdLottery obj) {

		obj.fixup();
        return insert(cti, "BD_LOTTERY.InsertBD_LOTTERY", obj );
	}

	public int update(ContextInfo cti, BdLottery obj) {

		obj.fixup();
		return update(cti, "BD_LOTTERY.UpdateBD_LOTTERY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdLottery obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_LOTTERY.DeleteBD_LOTTERY", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdLottery obj) {

        obj.fixup();
        BdLottery oldObj = ( BdLottery )queryForObject("BD_LOTTERY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_LOTTERY.DeleteBD_LOTTERY", oldObj );

	}

	public boolean exists(BdLottery vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdLottery obj = (BdLottery) vo;

        keysFilled = keysFilled && ( null != obj.getLotteryId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_LOTTERY.CountBD_LOTTERY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/12 15:55:00
	
	/**
	 * 通过id或者code获取抽奖活动
	 *
	 * @param vo
	 * @return
	 */
	public BdLottery findLottery(BdLottery vo) {
		return (BdLottery) queryForObject("BD_LOTTERY.FindBD_LOTTERY", vo);
	}
	
	/**
	 * 嘉年华专用
	 * @param lottery_code
	 * @param cpid
     * @return
     */
	public Map<String,Object> queryProuductInfo(String lottery_code, Integer cpid){
		HashMap<String,Object> vo = new HashMap<>();
		vo.put("lottery_code",lottery_code);
		vo.put("cpid",cpid);
		return (Map<String,Object>)queryForObject("BD_LOTTERY.queryProuductInfo", vo);
	}



	public List<Map<String,Object>> queryPartnerList(){

		return queryForList("BD_LOTTERY.queryPartnerList",null);
	}

}
