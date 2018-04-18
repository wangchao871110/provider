package com.xfs.score.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.score.model.BdScoreItem;

/**
 * BdScoreItemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:13
 */
@Repository
public class BdScoreItemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SCORE_ITEM.CountFindAllBD_SCORE_ITEM", null );
        return ret.intValue();
	}

	public BdScoreItem findByPK(BdScoreItem obj) {
		Object ret = queryForObject("BD_SCORE_ITEM.FindByPK", obj );
		if(ret !=null)
			return (BdScoreItem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItem> freeFind(BdScoreItem obj) {
		return queryForList("BD_SCORE_ITEM.FreeFindBD_SCORE_ITEM", obj );
	}

	public int countFreeFind(BdScoreItem obj) {
        Integer ret = (Integer) queryForObject("BD_SCORE_ITEM.CountFreeFindBD_SCORE_ITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItem> freeFind(BdScoreItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SCORE_ITEM.FreeFindBD_SCORE_ITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItem> freeFind(BdScoreItem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreItem){
	    	_hashmap = ((BdScoreItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SCORE_ITEM.FreeFindBD_SCORE_ITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItem> freeFind(BdScoreItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreItem){
	    	_hashmap = ((BdScoreItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SCORE_ITEM.FreeFindBD_SCORE_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdScoreItem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdScoreItem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdScoreItem oneObj = (BdScoreItem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdScoreItem vo) {
        BdScoreItem obj = (BdScoreItem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdScoreItem obj) {

		obj.fixup();
        return insert(cti, "BD_SCORE_ITEM.InsertBD_SCORE_ITEM", obj );
	}

	public int update(ContextInfo cti, BdScoreItem obj) {

		obj.fixup();
		return update(cti, "BD_SCORE_ITEM.UpdateBD_SCORE_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdScoreItem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SCORE_ITEM.DeleteBD_SCORE_ITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdScoreItem obj) {

        obj.fixup();
        BdScoreItem oldObj = ( BdScoreItem )queryForObject("BD_SCORE_ITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SCORE_ITEM.DeleteBD_SCORE_ITEM", oldObj );

	}

	public boolean exists(BdScoreItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdScoreItem obj = (BdScoreItem) vo;

        keysFilled = keysFilled && ( null != obj.getItemId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SCORE_ITEM.CountBD_SCORE_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:13
	
	
	
}
