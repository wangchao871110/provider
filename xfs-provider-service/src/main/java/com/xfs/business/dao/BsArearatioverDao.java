package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BsArearatiover;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsArearatioverDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:04:09
 */
@Repository
public class BsArearatioverDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_AREARATIOVER.CountFindAllBS_AREARATIOVER", null );
        return ret.intValue();
	}

	public BsArearatiover findByPK(BsArearatiover obj) {
		Object ret = queryForObject("BS_AREARATIOVER.FindByPK", obj );
		if(ret !=null)
			return (BsArearatiover)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatiover> freeFind(BsArearatiover obj) {
		return queryForList("BS_AREARATIOVER.FreeFindBS_AREARATIOVER", obj );
	}

	public int countFreeFind(BsArearatiover obj) {
        Integer ret = (Integer) queryForObject("BS_AREARATIOVER.CountFreeFindBS_AREARATIOVER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatiover> freeFind(BsArearatiover obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_AREARATIOVER.FreeFindBS_AREARATIOVER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatiover> freeFind(BsArearatiover obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatiover){
	    	_hashmap = ((BsArearatiover)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_AREARATIOVER.FreeFindBS_AREARATIOVEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatiover> freeFind(BsArearatiover obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatiover){
	    	_hashmap = ((BsArearatiover)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_AREARATIOVER.FreeFindBS_AREARATIOVEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsArearatiover> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsArearatiover> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsArearatiover oneObj = (BsArearatiover)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsArearatiover vo) {
        BsArearatiover obj = (BsArearatiover) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsArearatiover obj) {

		obj.fixup();
        return insert(cti, "BS_AREARATIOVER.InsertBS_AREARATIOVER", obj );
	}

	public int update(ContextInfo cti, BsArearatiover obj) {

		obj.fixup();
		return update(cti, "BS_AREARATIOVER.UpdateBS_AREARATIOVER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsArearatiover obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_AREARATIOVER.DeleteBS_AREARATIOVER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsArearatiover obj) {

        obj.fixup();
        BsArearatiover oldObj = ( BsArearatiover )queryForObject("BS_AREARATIOVER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_AREARATIOVER.DeleteBS_AREARATIOVER", oldObj );

	}

	public boolean exists(BsArearatiover vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsArearatiover obj = (BsArearatiover) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_AREARATIOVER.CountBS_AREARATIOVER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:09

    public List<BsArearatiover> findVersions(BsArearatiover arearatiover) {
        return queryForList("BS_AREARATIOVER.FindVersions", arearatiover );
    }
	
	
}
