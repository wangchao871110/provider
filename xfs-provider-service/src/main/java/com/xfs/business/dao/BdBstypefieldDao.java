package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BdBstypefield;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdBstypefieldDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 11:25:06
 */
@Repository
public class BdBstypefieldDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BSTYPEFIELD.CountFindAllBD_BSTYPEFIELD", null );
        return ret.intValue();
	}

	public BdBstypefield findByPK(BdBstypefield obj) {
		Object ret = queryForObject("BD_BSTYPEFIELD.FindByPK", obj );
		if(ret !=null)
			return (BdBstypefield)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypefield> freeFind(BdBstypefield obj) {
		return queryForList("BD_BSTYPEFIELD.FreeFindBD_BSTYPEFIELD", obj );
	}

	public int countFreeFind(BdBstypefield obj) {
        Integer ret = (Integer) queryForObject("BD_BSTYPEFIELD.CountFreeFindBD_BSTYPEFIELD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypefield> freeFind(BdBstypefield obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BSTYPEFIELD.FreeFindBD_BSTYPEFIELD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypefield> freeFind(BdBstypefield obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstypefield){
	    	_hashmap = ((BdBstypefield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BSTYPEFIELD.FreeFindBD_BSTYPEFIELDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypefield> freeFind(BdBstypefield obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstypefield){
	    	_hashmap = ((BdBstypefield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BSTYPEFIELD.FreeFindBD_BSTYPEFIELDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBstypefield> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBstypefield> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBstypefield oneObj = (BdBstypefield)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBstypefield vo) {
        BdBstypefield obj = (BdBstypefield) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBstypefield obj) {

		obj.fixup();
        return insert(cti, "BD_BSTYPEFIELD.InsertBD_BSTYPEFIELD", obj );
	}

	public int update(ContextInfo cti, BdBstypefield obj) {

		obj.fixup();
		return update(cti, "BD_BSTYPEFIELD.UpdateBD_BSTYPEFIELD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBstypefield obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BSTYPEFIELD.DeleteBD_BSTYPEFIELD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBstypefield obj) {

        obj.fixup();
        BdBstypefield oldObj = ( BdBstypefield )queryForObject("BD_BSTYPEFIELD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BSTYPEFIELD.DeleteBD_BSTYPEFIELD", oldObj );

	}

	public boolean exists(BdBstypefield vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBstypefield obj = (BdBstypefield) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BSTYPEFIELD.CountBD_BSTYPEFIELD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:06

	public BdBstypefield findBdBstypefield(BdBstypefield vo){
		Object ret = queryForObject("BD_BSTYPEFIELD.findBdBstypefield", vo );
		if(ret !=null)
			return (BdBstypefield)ret;
		else
			return null;
	}
	
}
