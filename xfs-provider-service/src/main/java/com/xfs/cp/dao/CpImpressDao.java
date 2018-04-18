package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpImpress;

/**
 * CpImpressDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:19
 */
@Repository
public class CpImpressDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_IMPRESS.CountFindAllCP_IMPRESS", null );
        return ret.intValue();
	}

	public CpImpress findByPK(CpImpress obj) {
		Object ret = queryForObject("CP_IMPRESS.FindByPK", obj );
		if(ret !=null)
			return (CpImpress)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpress> freeFind(CpImpress obj) {
		return queryForList("CP_IMPRESS.FreeFindCP_IMPRESS", obj );
	}

	public int countFreeFind(CpImpress obj) {
        Integer ret = (Integer) queryForObject("CP_IMPRESS.CountFreeFindCP_IMPRESS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpress> freeFind(CpImpress obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_IMPRESS.FreeFindCP_IMPRESS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpress> freeFind(CpImpress obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpImpress){
	    	_hashmap = ((CpImpress)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_IMPRESS.FreeFindCP_IMPRESSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpress> freeFind(CpImpress obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpImpress){
	    	_hashmap = ((CpImpress)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_IMPRESS.FreeFindCP_IMPRESSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpImpress> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpImpress> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpImpress oneObj = (CpImpress)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpImpress vo) {
        CpImpress obj = (CpImpress) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpImpress obj) {

		obj.fixup();
        return insert(cti, "CP_IMPRESS.InsertCP_IMPRESS", obj );
	}

	public int update(ContextInfo cti, CpImpress obj) {

		obj.fixup();
		return update(cti, "CP_IMPRESS.UpdateCP_IMPRESS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpImpress obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_IMPRESS.DeleteCP_IMPRESS", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpImpress obj) {

        obj.fixup();
        CpImpress oldObj = ( CpImpress )queryForObject("CP_IMPRESS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_IMPRESS.DeleteCP_IMPRESS", oldObj );

	}

	public boolean exists(CpImpress vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpImpress obj = (CpImpress) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_IMPRESS.CountCP_IMPRESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:19
	
	
	
}
