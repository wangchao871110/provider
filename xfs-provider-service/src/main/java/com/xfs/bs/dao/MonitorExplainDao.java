package com.xfs.bs.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.MonitorExplain;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;


/**
 * MonitorExplainDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/03/09 15:27:20
 */
@Repository
public class MonitorExplainDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("MONITOR_EXPLAIN.CountFindAllMONITOR_EXPLAIN", null );
        return ret.intValue();
	}

	public MonitorExplain findByPK(MonitorExplain obj) {
		Object ret = queryForObject("MONITOR_EXPLAIN.FindByPK", obj );
		if(ret !=null)
			return (MonitorExplain)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MonitorExplain> freeFind(MonitorExplain obj) {
		return queryForList("MONITOR_EXPLAIN.FreeFindMONITOR_EXPLAIN", obj );
	}

	public int countFreeFind(MonitorExplain obj) {
        Integer ret = (Integer) queryForObject("MONITOR_EXPLAIN.CountFreeFindMONITOR_EXPLAIN", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<MonitorExplain> freeFind(MonitorExplain obj, int pageIndex, int pageSize) {
		return getPaginatedList("MONITOR_EXPLAIN.FreeFindMONITOR_EXPLAIN", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<MonitorExplain> freeFind(MonitorExplain obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MonitorExplain){
	    	_hashmap = ((MonitorExplain)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("MONITOR_EXPLAIN.FreeFindMONITOR_EXPLAINOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<MonitorExplain> freeFind(MonitorExplain obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MonitorExplain){
	    	_hashmap = ((MonitorExplain)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("MONITOR_EXPLAIN.FreeFindMONITOR_EXPLAINOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<MonitorExplain> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<MonitorExplain> it = objColl.iterator();
            while ( it.hasNext() ) {
            	MonitorExplain oneObj = (MonitorExplain)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, MonitorExplain vo) {
        MonitorExplain obj = (MonitorExplain) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, MonitorExplain obj) {

		obj.fixup();
        return insert(cti, "MONITOR_EXPLAIN.InsertMONITOR_EXPLAIN", obj );
	}

	public int update(ContextInfo cti, MonitorExplain obj) {

		obj.fixup();
		return update(cti, "MONITOR_EXPLAIN.UpdateMONITOR_EXPLAIN", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, MonitorExplain obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "MONITOR_EXPLAIN.DeleteMONITOR_EXPLAIN", obj );

	}

	public int removeObjectTree(ContextInfo cti, MonitorExplain obj) {

        obj.fixup();
        MonitorExplain oldObj = ( MonitorExplain )queryForObject("MONITOR_EXPLAIN.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "MONITOR_EXPLAIN.DeleteMONITOR_EXPLAIN", oldObj );

	}

	public boolean exists(MonitorExplain vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        MonitorExplain obj = (MonitorExplain) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("MONITOR_EXPLAIN.CountMONITOR_EXPLAIN", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/03/09 15:27:20
	
	
	
}
