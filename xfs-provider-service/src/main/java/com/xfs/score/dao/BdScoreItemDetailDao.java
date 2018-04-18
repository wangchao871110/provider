package com.xfs.score.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.score.model.BdScoreItemDetail;

/**
 * BdScoreItemDetailDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:20
 */
@Repository
public class BdScoreItemDetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SCORE_ITEM_DETAIL.CountFindAllBD_SCORE_ITEM_DETAIL", null );
        return ret.intValue();
	}

	public BdScoreItemDetail findByPK(BdScoreItemDetail obj) {
		Object ret = queryForObject("BD_SCORE_ITEM_DETAIL.FindByPK", obj );
		if(ret !=null)
			return (BdScoreItemDetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItemDetail> freeFind(BdScoreItemDetail obj) {
		return queryForList("BD_SCORE_ITEM_DETAIL.FreeFindBD_SCORE_ITEM_DETAIL", obj );
	}

	public int countFreeFind(BdScoreItemDetail obj) {
        Integer ret = (Integer) queryForObject("BD_SCORE_ITEM_DETAIL.CountFreeFindBD_SCORE_ITEM_DETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItemDetail> freeFind(BdScoreItemDetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SCORE_ITEM_DETAIL.FreeFindBD_SCORE_ITEM_DETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItemDetail> freeFind(BdScoreItemDetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreItemDetail){
	    	_hashmap = ((BdScoreItemDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SCORE_ITEM_DETAIL.FreeFindBD_SCORE_ITEM_DETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreItemDetail> freeFind(BdScoreItemDetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreItemDetail){
	    	_hashmap = ((BdScoreItemDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SCORE_ITEM_DETAIL.FreeFindBD_SCORE_ITEM_DETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdScoreItemDetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdScoreItemDetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdScoreItemDetail oneObj = (BdScoreItemDetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdScoreItemDetail vo) {
        BdScoreItemDetail obj = (BdScoreItemDetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdScoreItemDetail obj) {

		obj.fixup();
        return insert(cti, "BD_SCORE_ITEM_DETAIL.InsertBD_SCORE_ITEM_DETAIL", obj );
	}

	public int update(ContextInfo cti, BdScoreItemDetail obj) {

		obj.fixup();
		return update(cti, "BD_SCORE_ITEM_DETAIL.UpdateBD_SCORE_ITEM_DETAIL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdScoreItemDetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SCORE_ITEM_DETAIL.DeleteBD_SCORE_ITEM_DETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdScoreItemDetail obj) {

        obj.fixup();
        BdScoreItemDetail oldObj = ( BdScoreItemDetail )queryForObject("BD_SCORE_ITEM_DETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SCORE_ITEM_DETAIL.DeleteBD_SCORE_ITEM_DETAIL", oldObj );

	}

	public boolean exists(BdScoreItemDetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdScoreItemDetail obj = (BdScoreItemDetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SCORE_ITEM_DETAIL.CountBD_SCORE_ITEM_DETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:20
	
	
	
}
