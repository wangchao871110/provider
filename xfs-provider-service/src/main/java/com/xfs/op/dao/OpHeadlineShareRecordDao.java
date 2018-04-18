package com.xfs.op.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.op.model.OpHeadlineShareRecord;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;

/**
 * OpHeadlineShareRecordDao
 * @author:yangfw@xinfushe.com
 * @date:2017/03/09 10:56:41
 */
@Repository
public class OpHeadlineShareRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_SHARE_RECORD.CountFindAllOP_HEADLINE_SHARE_RECORD", null );
        return ret.intValue();
	}

	public OpHeadlineShareRecord findByPK(OpHeadlineShareRecord obj) {
		Object ret = queryForObject("OP_HEADLINE_SHARE_RECORD.FindByPK", obj );
		if(ret !=null)
			return (OpHeadlineShareRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineShareRecord> freeFind(OpHeadlineShareRecord obj) {
		return queryForList("OP_HEADLINE_SHARE_RECORD.FreeFindOP_HEADLINE_SHARE_RECORD", obj );
	}

	public int countFreeFind(OpHeadlineShareRecord obj) {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_SHARE_RECORD.CountFreeFindOP_HEADLINE_SHARE_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineShareRecord> freeFind(OpHeadlineShareRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_HEADLINE_SHARE_RECORD.FreeFindOP_HEADLINE_SHARE_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineShareRecord> freeFind(OpHeadlineShareRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHeadlineShareRecord){
	    	_hashmap = ((OpHeadlineShareRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_HEADLINE_SHARE_RECORD.FreeFindOP_HEADLINE_SHARE_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHeadlineShareRecord> freeFind(OpHeadlineShareRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHeadlineShareRecord){
	    	_hashmap = ((OpHeadlineShareRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_HEADLINE_SHARE_RECORD.FreeFindOP_HEADLINE_SHARE_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpHeadlineShareRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpHeadlineShareRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpHeadlineShareRecord oneObj = (OpHeadlineShareRecord)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpHeadlineShareRecord vo) {
        OpHeadlineShareRecord obj = (OpHeadlineShareRecord) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpHeadlineShareRecord obj) {

		obj.fixup();
        return insert(cti,"OP_HEADLINE_SHARE_RECORD.InsertOP_HEADLINE_SHARE_RECORD", obj );
	}

	public int update(ContextInfo cti,OpHeadlineShareRecord obj) {

		obj.fixup();
		return update(cti, "OP_HEADLINE_SHARE_RECORD.UpdateOP_HEADLINE_SHARE_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpHeadlineShareRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_HEADLINE_SHARE_RECORD.DeleteOP_HEADLINE_SHARE_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpHeadlineShareRecord obj) {

        obj.fixup();
        OpHeadlineShareRecord oldObj = ( OpHeadlineShareRecord )queryForObject("OP_HEADLINE_SHARE_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_HEADLINE_SHARE_RECORD.DeleteOP_HEADLINE_SHARE_RECORD", oldObj );

	}

	public boolean exists(OpHeadlineShareRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpHeadlineShareRecord obj = (OpHeadlineShareRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_HEADLINE_SHARE_RECORD.CountOP_HEADLINE_SHARE_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:41
	
	
	
}
