package com.xfs.settlement.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.settlement.model.BlBalancedetail;

/**
 * BlBalancedetailDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 11:05:29
 */
@Repository
public class BlBalancedetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_BALANCEDETAIL.CountFindAllBL_BALANCEDETAIL", null );
        return ret.intValue();
	}

	public BlBalancedetail findByPK(BlBalancedetail obj) {
		Object ret = queryForObject("BL_BALANCEDETAIL.FindByPK", obj );
		if(ret !=null)
			return (BlBalancedetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalancedetail> freeFind(BlBalancedetail obj) {
		return queryForList("BL_BALANCEDETAIL.FreeFindBL_BALANCEDETAIL", obj );
	}

	public int countFreeFind(BlBalancedetail obj) {
        Integer ret = (Integer) queryForObject("BL_BALANCEDETAIL.CountFreeFindBL_BALANCEDETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalancedetail> freeFind(BlBalancedetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_BALANCEDETAIL.FreeFindBL_BALANCEDETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalancedetail> freeFind(BlBalancedetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlBalancedetail){
	    	_hashmap = ((BlBalancedetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_BALANCEDETAIL.FreeFindBL_BALANCEDETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlBalancedetail> freeFind(BlBalancedetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlBalancedetail){
	    	_hashmap = ((BlBalancedetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_BALANCEDETAIL.FreeFindBL_BALANCEDETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlBalancedetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlBalancedetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlBalancedetail oneObj = (BlBalancedetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlBalancedetail vo) {
        BlBalancedetail obj = (BlBalancedetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BlBalancedetail obj) {

		obj.fixup();
        return insert(cti, "BL_BALANCEDETAIL.InsertBL_BALANCEDETAIL", obj );
	}

	public int update(ContextInfo cti, BlBalancedetail obj) {

		obj.fixup();
		return update(cti, "BL_BALANCEDETAIL.UpdateBL_BALANCEDETAIL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlBalancedetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_BALANCEDETAIL.DeleteBL_BALANCEDETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlBalancedetail obj) {

        obj.fixup();
        BlBalancedetail oldObj = ( BlBalancedetail )queryForObject("BL_BALANCEDETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BL_BALANCEDETAIL.DeleteBL_BALANCEDETAIL", oldObj );

	}

	public boolean exists(BlBalancedetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlBalancedetail obj = (BlBalancedetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_BALANCEDETAIL.CountBL_BALANCEDETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:29

	/**
	 * 查询余额详情
	 * @param blBalancedetail
	 * @return
	 */
	public Map<String,Object> queryBalanceDetail(BlBalancedetail blBalancedetail){
		Object ret = queryForObject("BL_BALANCEDETAIL.QUERY_DETAIL_BY_ID", blBalancedetail );
		if(ret !=null)
			return (Map<String,Object>)ret;
		else
			return null;
	}


	public Integer queryBalanceDetailCount(BlBalancedetail blBalancedetail){
		Integer ret = (Integer) queryForObject("BL_BALANCEDETAIL.queryBalanceDetailCount", blBalancedetail );
		return ret.intValue();
	}

	public List<Map<String,Object>> queryBalanceDetailList(BlBalancedetail blBalancedetail,Integer pageIndex,Integer pageSize){
		return getPaginatedList("BL_BALANCEDETAIL.queryBalanceDetailList", blBalancedetail, pageIndex, pageSize );
	}
	
}
