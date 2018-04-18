package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BdEvaluation;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;


/** 
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 12:00
 * @version 	: V1.0
 */
@Repository
public class BdEvaluationDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_EVALUATION.CountFindAllBD_EVALUATION", null );
        return ret.intValue();
	}

	public BdEvaluation findByPK(BdEvaluation obj) {
		Object ret = queryForObject("BD_EVALUATION.FindByPK", obj );
		if(ret !=null)
			return (BdEvaluation)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdEvaluation> freeFind(BdEvaluation obj) {
		return queryForList("BD_EVALUATION.FreeFindBD_EVALUATION", obj );
	}

	public int countFreeFind(BdEvaluation obj) {
        Integer ret = (Integer) queryForObject("BD_EVALUATION.CountFreeFindBD_EVALUATION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdEvaluation> freeFind(BdEvaluation obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_EVALUATION.FreeFindBD_EVALUATION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdEvaluation> freeFind(BdEvaluation obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdEvaluation){
	    	_hashmap = ((BdEvaluation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_EVALUATION.FreeFindBD_EVALUATIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdEvaluation> freeFind(BdEvaluation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdEvaluation){
	    	_hashmap = ((BdEvaluation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_EVALUATION.FreeFindBD_EVALUATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdEvaluation> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdEvaluation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdEvaluation oneObj = (BdEvaluation)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdEvaluation vo) {
        BdEvaluation obj = (BdEvaluation) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdEvaluation obj) {

		obj.fixup();
        return insert(cti, "BD_EVALUATION.InsertBD_EVALUATION", obj );
	}

	public int update(ContextInfo cti, BdEvaluation obj) {

		obj.fixup();
		return update(cti, "BD_EVALUATION.UpdateBD_EVALUATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdEvaluation obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_EVALUATION.DeleteBD_EVALUATION", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdEvaluation obj) {

        obj.fixup();
        BdEvaluation oldObj = ( BdEvaluation )queryForObject("BD_EVALUATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_EVALUATION.DeleteBD_EVALUATION", oldObj );

	}

	public boolean exists(BdEvaluation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdEvaluation obj = (BdEvaluation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_EVALUATION.CountBD_EVALUATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/05 10:52:21

	/**
	 * 获取评价信息
	 * @param bdEvaluation
	 * @return
	 */
	public Map<String, Object> getEvaluationById(BdEvaluation bdEvaluation) {
		return (Map<String, Object>) queryForObject("BD_EVALUATION.getEvaluationById", bdEvaluation);
	}
	
}
