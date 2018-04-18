package com.xfs.score.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.score.model.BdScoreDimension;

/**
 * BdScoreDimensionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 11:40:16
 */
@Repository
public class BdScoreDimensionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SCORE_DIMENSION.CountFindAllBD_SCORE_DIMENSION", null );
        return ret.intValue();
	}

	public BdScoreDimension findByPK(BdScoreDimension obj) {
		Object ret = queryForObject("BD_SCORE_DIMENSION.FindByPK", obj );
		if(ret !=null)
			return (BdScoreDimension)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimension> freeFind(BdScoreDimension obj) {
		return queryForList("BD_SCORE_DIMENSION.FreeFindBD_SCORE_DIMENSION", obj );
	}

	public int countFreeFind(BdScoreDimension obj) {
        Integer ret = (Integer) queryForObject("BD_SCORE_DIMENSION.CountFreeFindBD_SCORE_DIMENSION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimension> freeFind(BdScoreDimension obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SCORE_DIMENSION.FreeFindBD_SCORE_DIMENSION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimension> freeFind(BdScoreDimension obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreDimension){
	    	_hashmap = ((BdScoreDimension)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SCORE_DIMENSION.FreeFindBD_SCORE_DIMENSIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdScoreDimension> freeFind(BdScoreDimension obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdScoreDimension){
	    	_hashmap = ((BdScoreDimension)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SCORE_DIMENSION.FreeFindBD_SCORE_DIMENSIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdScoreDimension> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdScoreDimension> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdScoreDimension oneObj = (BdScoreDimension)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdScoreDimension vo) {
        BdScoreDimension obj = (BdScoreDimension) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdScoreDimension obj) {

		obj.fixup();
        return insert(cti, "BD_SCORE_DIMENSION.InsertBD_SCORE_DIMENSION", obj );
	}

	public int update(ContextInfo cti, BdScoreDimension obj) {

		obj.fixup();
		return update(cti, "BD_SCORE_DIMENSION.UpdateBD_SCORE_DIMENSION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdScoreDimension obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SCORE_DIMENSION.DeleteBD_SCORE_DIMENSION", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdScoreDimension obj) {

        obj.fixup();
        BdScoreDimension oldObj = ( BdScoreDimension )queryForObject("BD_SCORE_DIMENSION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SCORE_DIMENSION.DeleteBD_SCORE_DIMENSION", oldObj );

	}

	public boolean exists(BdScoreDimension vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdScoreDimension obj = (BdScoreDimension) vo;

        keysFilled = keysFilled && ( null != obj.getDimId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SCORE_DIMENSION.CountBD_SCORE_DIMENSION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:16

	/**
	 * 根据评分项类型获取维度列表
	 * @param type
	 * @return
     */
	public List<BdScoreDimension> queryBdScoreDimensionListByType(String type){
		Map<String,Object> obj = new HashMap<>();
		obj.put("type",type);
		return queryForList("BD_SCORE_DIMENSION.QUERY_DIMENSION_LIST", obj);
	}

	/**
	 * 执行动态sql
	 * @param sqlStr
	 * @return
     */
	public List<Map<String,Object>> executeDynamicSql(String sqlStr){
		HashMap<String,Object> obj = new HashMap<>();
		obj.put("sqlStr",sqlStr);
		return queryForList("BD_SCORE_DIMENSION.selectPublicItemList", obj);
	}

}
