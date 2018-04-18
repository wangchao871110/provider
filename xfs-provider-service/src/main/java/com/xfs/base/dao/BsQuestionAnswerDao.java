package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsQuestionAnswer;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsQuestionAnswerDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 14:33:28
 */
@Repository
public class BsQuestionAnswerDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_QUESTION_ANSWER.CountFindAllBS_QUESTION_ANSWER", null );
        return ret.intValue();
	}

	public BsQuestionAnswer findByPK(BsQuestionAnswer obj) {
		Object ret = queryForObject("BS_QUESTION_ANSWER.FindByPK", obj );
		if(ret !=null)
			return (BsQuestionAnswer)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestionAnswer> freeFind(BsQuestionAnswer obj) {
		return queryForList("BS_QUESTION_ANSWER.FreeFindBS_QUESTION_ANSWER", obj );
	}

	public int countFreeFind(BsQuestionAnswer obj) {
        Integer ret = (Integer) queryForObject("BS_QUESTION_ANSWER.CountFreeFindBS_QUESTION_ANSWER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestionAnswer> freeFind(BsQuestionAnswer obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_QUESTION_ANSWER.FreeFindBS_QUESTION_ANSWER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestionAnswer> freeFind(BsQuestionAnswer obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsQuestionAnswer){
	    	_hashmap = ((BsQuestionAnswer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_QUESTION_ANSWER.FreeFindBS_QUESTION_ANSWEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestionAnswer> freeFind(BsQuestionAnswer obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsQuestionAnswer){
	    	_hashmap = ((BsQuestionAnswer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_QUESTION_ANSWER.FreeFindBS_QUESTION_ANSWEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsQuestionAnswer> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsQuestionAnswer> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsQuestionAnswer oneObj = (BsQuestionAnswer)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsQuestionAnswer vo) {
        BsQuestionAnswer obj = (BsQuestionAnswer) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsQuestionAnswer obj) {

		obj.fixup();
        return insert(cti, "BS_QUESTION_ANSWER.InsertBS_QUESTION_ANSWER", obj );
	}

	public int update(ContextInfo cti, BsQuestionAnswer obj) {

		obj.fixup();
		return update(cti, "BS_QUESTION_ANSWER.UpdateBS_QUESTION_ANSWER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsQuestionAnswer obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_QUESTION_ANSWER.DeleteBS_QUESTION_ANSWER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsQuestionAnswer obj) {

        obj.fixup();
        BsQuestionAnswer oldObj = ( BsQuestionAnswer )queryForObject("BS_QUESTION_ANSWER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_QUESTION_ANSWER.DeleteBS_QUESTION_ANSWER", oldObj );

	}

	public boolean exists(BsQuestionAnswer vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsQuestionAnswer obj = (BsQuestionAnswer) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_QUESTION_ANSWER.CountBS_QUESTION_ANSWER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:28
	
	public List<Map<String, Object>> getAnswers(BsQuestionAnswer vo,int pageIndex, int pageSize){
		return getPaginatedList("BS_QUESTION_ANSWER.FreeFindASNWER", vo, pageIndex, pageSize);
	}
	
	public int countFreeFind1(BsQuestionAnswer vo) {
        Integer ret = (Integer) queryForObject("BS_QUESTION_ANSWER.FreeFindASNWERCount", vo );
        return ret.intValue();
	}
	
	
}
