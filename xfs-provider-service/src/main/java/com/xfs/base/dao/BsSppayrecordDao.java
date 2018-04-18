package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsSppayrecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsSppayrecordDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 11:14:40
 */
@Repository
public class BsSppayrecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_SPPAYRECORD.CountFindAllBS_SPPAYRECORD", null );
        return ret.intValue();
	}

	public BsSppayrecord findByPK(BsSppayrecord obj) {
		Object ret = queryForObject("BS_SPPAYRECORD.FindByPK", obj );
		if(ret !=null)
			return (BsSppayrecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSppayrecord> freeFind(BsSppayrecord obj) {
		return queryForList("BS_SPPAYRECORD.FreeFindBS_SPPAYRECORD", obj );
	}

	public int countFreeFind(BsSppayrecord obj) {
        Integer ret = (Integer) queryForObject("BS_SPPAYRECORD.CountFreeFindBS_SPPAYRECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSppayrecord> freeFind(BsSppayrecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_SPPAYRECORD.FreeFindBS_SPPAYRECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSppayrecord> freeFind(BsSppayrecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSppayrecord){
	    	_hashmap = ((BsSppayrecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_SPPAYRECORD.FreeFindBS_SPPAYRECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSppayrecord> freeFind(BsSppayrecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSppayrecord){
	    	_hashmap = ((BsSppayrecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_SPPAYRECORD.FreeFindBS_SPPAYRECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsSppayrecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsSppayrecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsSppayrecord oneObj = (BsSppayrecord)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsSppayrecord vo) {
        BsSppayrecord obj = (BsSppayrecord) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsSppayrecord obj) {

		obj.fixup();
        return insert(cti, "BS_SPPAYRECORD.InsertBS_SPPAYRECORD", obj );
	}

	public int update(ContextInfo cti, BsSppayrecord obj) {

		obj.fixup();
		return update(cti, "BS_SPPAYRECORD.UpdateBS_SPPAYRECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsSppayrecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_SPPAYRECORD.DeleteBS_SPPAYRECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsSppayrecord obj) {

        obj.fixup();
        BsSppayrecord oldObj = ( BsSppayrecord )queryForObject("BS_SPPAYRECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_SPPAYRECORD.DeleteBS_SPPAYRECORD", oldObj );

	}

	public boolean exists(BsSppayrecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsSppayrecord obj = (BsSppayrecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_SPPAYRECORD.CountBS_SPPAYRECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 11:14:40
	
	public int countLisBySpid(BsSppayrecord obj){
		Integer ret = (Integer) queryForObject("BS_SPPAYRECORD.CountLisBySpid_SPPAYRECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSppayrecord> findLisBySpid(BsSppayrecord obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof BsSppayrecord){
			_hashmap = ((BsSppayrecord)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return getPaginatedList("BS_SPPAYRECORD.FindLisBySpid_SPPAYRECORD", _hashmap, pageIndex, pageSize );
	}
	
	public BsSppayrecord findMaxDateBySpId(BsSppayrecord obj){
		Object ret = queryForObject("BS_SPPAYRECORD.FindMaxDateBySpId_SPPAYRECORD", obj );
		if(ret !=null)
			return (BsSppayrecord)ret;
		else 
			return null;
	}
	
	public BsSppayrecord getTotleStandareFee(BsSppayrecord obj){
		Object ret = queryForObject("BS_SPPAYRECORD.GetTotleStandareFee_SPPAYRECORD", obj );
		if(ret !=null)
			return (BsSppayrecord)ret;
		else 
			return null;
	}
	
	
}
