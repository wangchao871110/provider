package com.xfs.score.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.score.model.BdScoreCollection;

/**
 * BdScoreCollectionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:19
 */
@Repository
public class BdScoreCollectionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SCORE_COLLECTION.CountFindAllBD_SCORE_COLLECTION", null );
        return ret.intValue();
	}

	public BdScoreCollection findByPK(BdScoreCollection obj) {
		Object ret = queryForObject("BD_SCORE_COLLECTION.FindByPK", obj );
		if(ret !=null)
			return (BdScoreCollection)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreCollection> freeFind(BdScoreCollection obj) {
		return queryForList("BD_SCORE_COLLECTION.FreeFindBD_SCORE_COLLECTION", obj );
	}

	public int countFreeFind(BdScoreCollection obj) {
        Integer ret = (Integer) queryForObject("BD_SCORE_COLLECTION.CountFreeFindBD_SCORE_COLLECTION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreCollection> freeFind(BdScoreCollection obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SCORE_COLLECTION.FreeFindBD_SCORE_COLLECTION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreCollection> freeFind(BdScoreCollection obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreCollection){
	    	_hashmap = ((BdScoreCollection)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SCORE_COLLECTION.FreeFindBD_SCORE_COLLECTIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreCollection> freeFind(BdScoreCollection obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreCollection){
	    	_hashmap = ((BdScoreCollection)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SCORE_COLLECTION.FreeFindBD_SCORE_COLLECTIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdScoreCollection> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdScoreCollection> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdScoreCollection oneObj = (BdScoreCollection)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdScoreCollection vo) {
        BdScoreCollection obj = (BdScoreCollection) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdScoreCollection obj) {

		obj.fixup();
        return insert(cti, "BD_SCORE_COLLECTION.InsertBD_SCORE_COLLECTION", obj );
	}

	public int update(ContextInfo cti, BdScoreCollection obj) {

		obj.fixup();
		return update(cti, "BD_SCORE_COLLECTION.UpdateBD_SCORE_COLLECTION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdScoreCollection obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SCORE_COLLECTION.DeleteBD_SCORE_COLLECTION", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdScoreCollection obj) {

        obj.fixup();
        BdScoreCollection oldObj = ( BdScoreCollection )queryForObject("BD_SCORE_COLLECTION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SCORE_COLLECTION.DeleteBD_SCORE_COLLECTION", oldObj );

	}

	public boolean exists(BdScoreCollection vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdScoreCollection obj = (BdScoreCollection) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SCORE_COLLECTION.CountBD_SCORE_COLLECTION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:19


	public List<Map<String,Object>> getBdScoreCollectionList(String calcVersion,String type){
	    HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("calcVersion",calcVersion);
        hashMap.put("type",type);
		return queryForList("BD_SCORE_COLLECTION.getBdScoreCollectionList", hashMap);
	}

	public BdScoreCollection findBdScoreCollection(BdScoreCollection obj) {
		Object ret = queryForObject("BD_SCORE_COLLECTION.findBdScoreCollection", obj );
		if(ret !=null)
			return (BdScoreCollection)ret;
		else
			return null;
	}
	
	
}
