package com.xfs.op.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.op.model.OpHeadlineSearchWords;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;

/**
 * OpHeadlineSearchWordsDao
 * @author:yangfw@xinfushe.com
 * @date:2017/04/19 16:29:44
 */
@Repository
public class OpHeadlineSearchWordsDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_SEARCH_WORDS.CountFindAllOP_HEADLINE_SEARCH_WORDS", null );
        return ret.intValue();
	}

	public OpHeadlineSearchWords findByPK(OpHeadlineSearchWords obj) {
		Object ret = queryForObject("OP_HEADLINE_SEARCH_WORDS.FindByPK", obj );
		if(ret !=null)
			return (OpHeadlineSearchWords)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineSearchWords> freeFind(OpHeadlineSearchWords obj) {
		return queryForList("OP_HEADLINE_SEARCH_WORDS.FreeFindOP_HEADLINE_SEARCH_WORDS", obj );
	}

	public int countFreeFind(OpHeadlineSearchWords obj) {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_SEARCH_WORDS.CountFreeFindOP_HEADLINE_SEARCH_WORDS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineSearchWords> freeFind(OpHeadlineSearchWords obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_HEADLINE_SEARCH_WORDS.FreeFindOP_HEADLINE_SEARCH_WORDS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineSearchWords> freeFind(OpHeadlineSearchWords obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHeadlineSearchWords){
	    	_hashmap = ((OpHeadlineSearchWords)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_HEADLINE_SEARCH_WORDS.FreeFindOP_HEADLINE_SEARCH_WORDSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineSearchWords> freeFind(OpHeadlineSearchWords obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHeadlineSearchWords){
	    	_hashmap = ((OpHeadlineSearchWords)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_HEADLINE_SEARCH_WORDS.FreeFindOP_HEADLINE_SEARCH_WORDSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpHeadlineSearchWords> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpHeadlineSearchWords> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpHeadlineSearchWords oneObj = (OpHeadlineSearchWords)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpHeadlineSearchWords vo) {
        OpHeadlineSearchWords obj = (OpHeadlineSearchWords) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpHeadlineSearchWords obj) {

		obj.fixup();
        return insert(cti,"OP_HEADLINE_SEARCH_WORDS.InsertOP_HEADLINE_SEARCH_WORDS", obj );
	}

	public int update(ContextInfo cti,OpHeadlineSearchWords obj) {

		obj.fixup();
		return update(cti, "OP_HEADLINE_SEARCH_WORDS.UpdateOP_HEADLINE_SEARCH_WORDS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpHeadlineSearchWords obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_HEADLINE_SEARCH_WORDS.DeleteOP_HEADLINE_SEARCH_WORDS", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpHeadlineSearchWords obj) {

        obj.fixup();
        OpHeadlineSearchWords oldObj = ( OpHeadlineSearchWords )queryForObject("OP_HEADLINE_SEARCH_WORDS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_HEADLINE_SEARCH_WORDS.DeleteOP_HEADLINE_SEARCH_WORDS", oldObj );

	}

	public boolean exists(OpHeadlineSearchWords vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpHeadlineSearchWords obj = (OpHeadlineSearchWords) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_HEADLINE_SEARCH_WORDS.CountOP_HEADLINE_SEARCH_WORDS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/19 16:29:44


    /**
     * 使用搜索词查询
     * @param searchWord
     * @return
     */
    public OpHeadlineSearchWords findBySearchWord(String searchWord){
        Object ret = queryForObject("OP_HEADLINE_SEARCH_WORDS.findBySearchWord", searchWord );
        if(ret !=null)
            return (OpHeadlineSearchWords)ret;
        else
            return null;
    }

    /**
     * 更新搜索词的统计数据
     * @param id
     * @return
     */
    public Integer addSearchWordTimes(ContextInfo cti ,Integer id){
      return   update(cti, "OP_HEADLINE_SEARCH_WORDS.addSearchWordTimes", id );
    }



}
