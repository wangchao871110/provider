package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsQuestion;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsQuestionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 14:33:27
 */
@Repository
public class BsQuestionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_QUESTION.CountFindAllBS_QUESTION", null );
        return ret.intValue();
	}

	public BsQuestion findByPK(BsQuestion obj) {
		Object ret = queryForObject("BS_QUESTION.FindByPK", obj );
		if(ret !=null)
			return (BsQuestion)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestion> freeFind(BsQuestion obj) {
		return queryForList("BS_QUESTION.FreeFindBS_QUESTION", obj );
	}

	public int countFreeFind(BsQuestion obj) {
        Integer ret = (Integer) queryForObject("BS_QUESTION.CountFreeFindBS_QUESTION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestion> freeFind(BsQuestion obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_QUESTION.FreeFindBS_QUESTION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestion> freeFind(BsQuestion obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsQuestion){
	    	_hashmap = ((BsQuestion)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_QUESTION.FreeFindBS_QUESTIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsQuestion> freeFind(BsQuestion obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsQuestion){
	    	_hashmap = ((BsQuestion)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_QUESTION.FreeFindBS_QUESTIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsQuestion> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsQuestion> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsQuestion oneObj = (BsQuestion)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsQuestion vo) {
        BsQuestion obj = (BsQuestion) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsQuestion obj) {

		obj.fixup();
        return insert(cti, "BS_QUESTION.InsertBS_QUESTION", obj );
	}

	public int update(ContextInfo cti, BsQuestion obj) {

		obj.fixup();
		return update(cti, "BS_QUESTION.UpdateBS_QUESTION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsQuestion obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_QUESTION.DeleteBS_QUESTION", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsQuestion obj) {

        obj.fixup();
        BsQuestion oldObj = ( BsQuestion )queryForObject("BS_QUESTION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_QUESTION.DeleteBS_QUESTION", oldObj );

	}

	public boolean exists(BsQuestion vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsQuestion obj = (BsQuestion) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_QUESTION.CountBS_QUESTION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27
	
	
}
