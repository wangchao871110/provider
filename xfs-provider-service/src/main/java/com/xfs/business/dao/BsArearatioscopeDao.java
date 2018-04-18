package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BsArearatioscope;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsArearatioscopeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:04:25
 */
@Repository
public class BsArearatioscopeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_AREARATIOSCOPE.CountFindAllBS_AREARATIOSCOPE", null );
        return ret.intValue();
	}

	public BsArearatioscope findByPK(BsArearatioscope obj) {
		Object ret = queryForObject("BS_AREARATIOSCOPE.FindByPK", obj );
		if(ret !=null)
			return (BsArearatioscope)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatioscope> freeFind(BsArearatioscope obj) {
		return queryForList("BS_AREARATIOSCOPE.FreeFindBS_AREARATIOSCOPE", obj );
	}

	public int countFreeFind(BsArearatioscope obj) {
        Integer ret = (Integer) queryForObject("BS_AREARATIOSCOPE.CountFreeFindBS_AREARATIOSCOPE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatioscope> freeFind(BsArearatioscope obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_AREARATIOSCOPE.FreeFindBS_AREARATIOSCOPE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatioscope> freeFind(BsArearatioscope obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatioscope){
	    	_hashmap = ((BsArearatioscope)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_AREARATIOSCOPE.FreeFindBS_AREARATIOSCOPEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatioscope> freeFind(BsArearatioscope obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatioscope){
	    	_hashmap = ((BsArearatioscope)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_AREARATIOSCOPE.FreeFindBS_AREARATIOSCOPEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsArearatioscope> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsArearatioscope> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsArearatioscope oneObj = (BsArearatioscope)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsArearatioscope vo) {
        BsArearatioscope obj = (BsArearatioscope) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsArearatioscope obj) {

		obj.fixup();
        return insert(cti, "BS_AREARATIOSCOPE.InsertBS_AREARATIOSCOPE", obj );
	}

	public int update(ContextInfo cti, BsArearatioscope obj) {

		obj.fixup();
		return update(cti, "BS_AREARATIOSCOPE.UpdateBS_AREARATIOSCOPE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsArearatioscope obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_AREARATIOSCOPE.DeleteBS_AREARATIOSCOPE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsArearatioscope obj) {

        obj.fixup();
        BsArearatioscope oldObj = ( BsArearatioscope )queryForObject("BS_AREARATIOSCOPE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_AREARATIOSCOPE.DeleteBS_AREARATIOSCOPE", oldObj );

	}

	public boolean exists(BsArearatioscope vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsArearatioscope obj = (BsArearatioscope) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_AREARATIOSCOPE.CountBS_AREARATIOSCOPE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:25
	
	public void deleteScopeInUse(ContextInfo cti, BsArearatioscope obj){
		delete(cti, "DeleteBS_AREARATIOSCOPE_InUser", obj);
	}
	
}
