package com.xfs.settlement.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.settlement.model.BlBalance;

/**
 * BlBalanceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 11:05:30
 */
@Repository
public class BlBalanceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_BALANCE.CountFindAllBL_BALANCE", null );
        return ret.intValue();
	}

	public BlBalance findByPK(BlBalance obj) {
		Object ret = queryForObject("BL_BALANCE.FindByPK", obj );
		if(ret !=null)
			return (BlBalance)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalance> freeFind(BlBalance obj) {
		return queryForList("BL_BALANCE.FreeFindBL_BALANCE", obj );
	}

	public int countFreeFind(BlBalance obj) {
        Integer ret = (Integer) queryForObject("BL_BALANCE.CountFreeFindBL_BALANCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalance> freeFind(BlBalance obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_BALANCE.FreeFindBL_BALANCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalance> freeFind(BlBalance obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlBalance){
	    	_hashmap = ((BlBalance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_BALANCE.FreeFindBL_BALANCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalance> freeFind(BlBalance obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlBalance){
	    	_hashmap = ((BlBalance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_BALANCE.FreeFindBL_BALANCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlBalance> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlBalance> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlBalance oneObj = (BlBalance)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlBalance vo) {
        BlBalance obj = (BlBalance) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BlBalance obj) {

		obj.fixup();
        return insert(cti, "BL_BALANCE.InsertBL_BALANCE", obj );
	}

	public int update(ContextInfo cti, BlBalance obj) {

		obj.fixup();
		return update(cti, "BL_BALANCE.UpdateBL_BALANCE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlBalance obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_BALANCE.DeleteBL_BALANCE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlBalance obj) {

        obj.fixup();
        BlBalance oldObj = ( BlBalance )queryForObject("BL_BALANCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BL_BALANCE.DeleteBL_BALANCE", oldObj );

	}

	public boolean exists(BlBalance vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlBalance obj = (BlBalance) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_BALANCE.CountBL_BALANCE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:30

	/**
	 * 查询账户余额
	 * @param vo
	 * @return
	 */
	public BlBalance queryBalance(BlBalance vo){
		Object ret = queryForObject("BL_BALANCE.QUERY_BALANCE_BY_OWNER", vo );
		if(ret !=null)
			return (BlBalance)ret;
		else
			return null;
	}
	
}
