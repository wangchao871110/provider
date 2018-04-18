package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.CsQuestionnaire;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * CsQuestionnaireDao
 * @author:wangchao
 * @date:2016/07/28 10:52:51
 */
@Repository
public class CsQuestionnaireDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_QUESTIONNAIRE.CountFindAllCS_QUESTIONNAIRE", null );
        return ret.intValue();
	}

	public CsQuestionnaire findByPK(CsQuestionnaire obj) {
		Object ret = queryForObject("CS_QUESTIONNAIRE.FindByPK", obj );
		if(ret !=null)
			return (CsQuestionnaire)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsQuestionnaire> freeFind(CsQuestionnaire obj) {
		return queryForList("CS_QUESTIONNAIRE.FreeFindCS_QUESTIONNAIRE", obj );
	}

	public int countFreeFind(CsQuestionnaire obj) {
        Integer ret = (Integer) queryForObject("CS_QUESTIONNAIRE.CountFreeFindCS_QUESTIONNAIRE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsQuestionnaire> freeFind(CsQuestionnaire obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_QUESTIONNAIRE.FreeFindCS_QUESTIONNAIRE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsQuestionnaire> freeFind(CsQuestionnaire obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsQuestionnaire){
	    	_hashmap = ((CsQuestionnaire)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_QUESTIONNAIRE.FreeFindCS_QUESTIONNAIREOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsQuestionnaire> freeFind(CsQuestionnaire obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsQuestionnaire){
	    	_hashmap = ((CsQuestionnaire)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_QUESTIONNAIRE.FreeFindCS_QUESTIONNAIREOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsQuestionnaire> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsQuestionnaire> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsQuestionnaire oneObj = (CsQuestionnaire)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsQuestionnaire vo) {
        CsQuestionnaire obj = (CsQuestionnaire) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsQuestionnaire obj) {

		obj.fixup();
        return insert(cti, "CS_QUESTIONNAIRE.InsertCS_QUESTIONNAIRE", obj );
	}

	public int update(ContextInfo cti, CsQuestionnaire obj) {

		obj.fixup();
		return update(cti, "CS_QUESTIONNAIRE.UpdateCS_QUESTIONNAIRE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsQuestionnaire obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_QUESTIONNAIRE.DeleteCS_QUESTIONNAIRE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsQuestionnaire obj) {

        obj.fixup();
        CsQuestionnaire oldObj = ( CsQuestionnaire )queryForObject("CS_QUESTIONNAIRE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_QUESTIONNAIRE.DeleteCS_QUESTIONNAIRE", oldObj );

	}

	public boolean exists(CsQuestionnaire vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsQuestionnaire obj = (CsQuestionnaire) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_QUESTIONNAIRE.CountCS_QUESTIONNAIRE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/28 10:52:51
	
	
	
}
