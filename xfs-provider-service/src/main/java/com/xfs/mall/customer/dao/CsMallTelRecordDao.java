package com.xfs.mall.customer.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.customer.model.CsMallTelRecord;

/**
 * CsMallTelRecordDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:29:12
 */
@Repository
public class CsMallTelRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_MALL_TEL_RECORD.CountFindAllCS_MALL_TEL_RECORD", null );
        return ret.intValue();
	}

	public CsMallTelRecord findByPK(CsMallTelRecord obj) {
		Object ret = queryForObject("CS_MALL_TEL_RECORD.FindByPK", obj );
		if(ret !=null)
			return (CsMallTelRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallTelRecord> freeFind(CsMallTelRecord obj) {
		return queryForList("CS_MALL_TEL_RECORD.FreeFindCS_MALL_TEL_RECORD", obj );
	}

	public int countFreeFind(CsMallTelRecord obj) {
        Integer ret = (Integer) queryForObject("CS_MALL_TEL_RECORD.CountFreeFindCS_MALL_TEL_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallTelRecord> freeFind(CsMallTelRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_MALL_TEL_RECORD.FreeFindCS_MALL_TEL_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallTelRecord> freeFind(CsMallTelRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallTelRecord){
	    	_hashmap = ((CsMallTelRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_MALL_TEL_RECORD.FreeFindCS_MALL_TEL_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallTelRecord> freeFind(CsMallTelRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallTelRecord){
	    	_hashmap = ((CsMallTelRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_MALL_TEL_RECORD.FreeFindCS_MALL_TEL_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsMallTelRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsMallTelRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsMallTelRecord oneObj = (CsMallTelRecord)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsMallTelRecord vo) {
        CsMallTelRecord obj = (CsMallTelRecord) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsMallTelRecord obj) {

		obj.fixup();
        return insert(cti, "CS_MALL_TEL_RECORD.InsertCS_MALL_TEL_RECORD", obj );
	}

	public int update(ContextInfo cti, CsMallTelRecord obj) {

		obj.fixup();
		return update(cti, "CS_MALL_TEL_RECORD.UpdateCS_MALL_TEL_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsMallTelRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_MALL_TEL_RECORD.DeleteCS_MALL_TEL_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsMallTelRecord obj) {

        obj.fixup();
        CsMallTelRecord oldObj = ( CsMallTelRecord )queryForObject("CS_MALL_TEL_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_MALL_TEL_RECORD.DeleteCS_MALL_TEL_RECORD", oldObj );

	}

	public boolean exists(CsMallTelRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsMallTelRecord obj = (CsMallTelRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_MALL_TEL_RECORD.CountCS_MALL_TEL_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:29:12
	
	
	
}
