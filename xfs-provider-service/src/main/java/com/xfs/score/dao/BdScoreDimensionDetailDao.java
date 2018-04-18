package com.xfs.score.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.score.model.BdScoreDimensionDetail;

/**
 * BdScoreDimensionDetailDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:20
 */
@Repository
public class BdScoreDimensionDetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SCORE_DIMENSION_DETAIL.CountFindAllBD_SCORE_DIMENSION_DETAIL", null );
        return ret.intValue();
	}

	public BdScoreDimensionDetail findByPK(BdScoreDimensionDetail obj) {
		Object ret = queryForObject("BD_SCORE_DIMENSION_DETAIL.FindByPK", obj );
		if(ret !=null)
			return (BdScoreDimensionDetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimensionDetail> freeFind(BdScoreDimensionDetail obj) {
		return queryForList("BD_SCORE_DIMENSION_DETAIL.FreeFindBD_SCORE_DIMENSION_DETAIL", obj );
	}

	public int countFreeFind(BdScoreDimensionDetail obj) {
        Integer ret = (Integer) queryForObject("BD_SCORE_DIMENSION_DETAIL.CountFreeFindBD_SCORE_DIMENSION_DETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimensionDetail> freeFind(BdScoreDimensionDetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SCORE_DIMENSION_DETAIL.FreeFindBD_SCORE_DIMENSION_DETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimensionDetail> freeFind(BdScoreDimensionDetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreDimensionDetail){
	    	_hashmap = ((BdScoreDimensionDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SCORE_DIMENSION_DETAIL.FreeFindBD_SCORE_DIMENSION_DETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimensionDetail> freeFind(BdScoreDimensionDetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreDimensionDetail){
	    	_hashmap = ((BdScoreDimensionDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SCORE_DIMENSION_DETAIL.FreeFindBD_SCORE_DIMENSION_DETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdScoreDimensionDetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdScoreDimensionDetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdScoreDimensionDetail oneObj = (BdScoreDimensionDetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdScoreDimensionDetail vo) {
        BdScoreDimensionDetail obj = (BdScoreDimensionDetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdScoreDimensionDetail obj) {

		obj.fixup();
        return insert(cti, "BD_SCORE_DIMENSION_DETAIL.InsertBD_SCORE_DIMENSION_DETAIL", obj );
	}

	public int update(ContextInfo cti, BdScoreDimensionDetail obj) {

		obj.fixup();
		return update(cti, "BD_SCORE_DIMENSION_DETAIL.UpdateBD_SCORE_DIMENSION_DETAIL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdScoreDimensionDetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SCORE_DIMENSION_DETAIL.DeleteBD_SCORE_DIMENSION_DETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdScoreDimensionDetail obj) {

        obj.fixup();
        BdScoreDimensionDetail oldObj = ( BdScoreDimensionDetail )queryForObject("BD_SCORE_DIMENSION_DETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SCORE_DIMENSION_DETAIL.DeleteBD_SCORE_DIMENSION_DETAIL", oldObj );

	}

	public boolean exists(BdScoreDimensionDetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdScoreDimensionDetail obj = (BdScoreDimensionDetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SCORE_DIMENSION_DETAIL.CountBD_SCORE_DIMENSION_DETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:20

	/**
	 * 获取当前版本号
	 * @return
     */
	public String queryCurrVersion(String type){
		HashMap<String,Object> obj = new HashMap<>();
		obj.put("type",type);
		Object ret = queryForObject("BD_SCORE_DIMENSION_DETAIL.QUERY_CURR_VERSION", obj );
		if(ret !=null)
			return (String)ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getBdScoreDimensionDetailList(String  calVersion,String type) {
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("calVersion",calVersion);
		hashMap.put("type",type);
		return queryForList("BD_SCORE_DIMENSION_DETAIL.getBdScoreDimensionDetailList", hashMap);
	}

}
