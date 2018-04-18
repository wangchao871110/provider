package com.xfs.op.dao;
import java.util.*;

import com.xfs.op.model.OpHrplusHotWords;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;

/**
 * OpHrplusHotWordsDao
 * @author:yangfw@xinfushe.com
 * @date:2017/03/09 10:56:41
 */
@Repository
public class OpHrplusHotWordsDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_HOT_WORDS.CountFindAllOP_HRPLUS_HOT_WORDS", null );
        return ret.intValue();
	}

	public OpHrplusHotWords findByPK(OpHrplusHotWords obj) {
		Object ret = queryForObject("OP_HRPLUS_HOT_WORDS.FindByPK", obj );
		if(ret !=null)
			return (OpHrplusHotWords)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusHotWords> freeFind(OpHrplusHotWords obj) {
		return queryForList("OP_HRPLUS_HOT_WORDS.FreeFindOP_HRPLUS_HOT_WORDS", obj );
	}

	public int countFreeFind(OpHrplusHotWords obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_HOT_WORDS.CountFreeFindOP_HRPLUS_HOT_WORDS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusHotWords> freeFind(OpHrplusHotWords obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_HRPLUS_HOT_WORDS.FreeFindOP_HRPLUS_HOT_WORDS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusHotWords> freeFind(OpHrplusHotWords obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusHotWords){
	    	_hashmap = ((OpHrplusHotWords)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_HRPLUS_HOT_WORDS.FreeFindOP_HRPLUS_HOT_WORDSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusHotWords> freeFind(OpHrplusHotWords obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusHotWords){
	    	_hashmap = ((OpHrplusHotWords)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_HRPLUS_HOT_WORDS.FreeFindOP_HRPLUS_HOT_WORDSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpHrplusHotWords> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpHrplusHotWords> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpHrplusHotWords oneObj = (OpHrplusHotWords)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpHrplusHotWords vo) {
        OpHrplusHotWords obj = (OpHrplusHotWords) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpHrplusHotWords obj) {

		obj.fixup();
        return insert(cti,"OP_HRPLUS_HOT_WORDS.InsertOP_HRPLUS_HOT_WORDS", obj );
	}

	public int update(ContextInfo cti,OpHrplusHotWords obj) {

		obj.fixup();
		return update(cti, "OP_HRPLUS_HOT_WORDS.UpdateOP_HRPLUS_HOT_WORDS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpHrplusHotWords obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_HRPLUS_HOT_WORDS.DeleteOP_HRPLUS_HOT_WORDS", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpHrplusHotWords obj) {

        obj.fixup();
        OpHrplusHotWords oldObj = ( OpHrplusHotWords )queryForObject("OP_HRPLUS_HOT_WORDS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_HRPLUS_HOT_WORDS.DeleteOP_HRPLUS_HOT_WORDS", oldObj );

	}

	public boolean exists(OpHrplusHotWords vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpHrplusHotWords obj = (OpHrplusHotWords) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_HRPLUS_HOT_WORDS.CountOP_HRPLUS_HOT_WORDS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:41




    /**
     * count to 分页
     * @param obj
     * @return
     */
    public int finManageHotListCount(Map<String,Object> obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_HOT_WORDS.finManageHotListCount", obj );
        return ret.intValue();
    }


    /**
     * 分页查询
     * @param obj
     * @param offset
     * @param pagesize
     * @return
     */
    public List<Map<String,Object>> finManageHotList(Map<String,Object> obj,Integer offset,Integer pagesize){
        return getPaginatedList("OP_HRPLUS_HOT_WORDS.finManageHotList",obj,offset,pagesize);
    }
	
}
