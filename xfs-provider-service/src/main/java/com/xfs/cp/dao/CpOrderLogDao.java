package com.xfs.cp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpOrderLog;

/**
 * CpOrderLogDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
@Repository
public class CpOrderLogDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_ORDER_LOG.CountFindAllCP_ORDER_LOG", null );
        return ret.intValue();
	}

	public CpOrderLog findByPK(CpOrderLog obj) {
		Object ret = queryForObject("CP_ORDER_LOG.FindByPK", obj );
		if(ret !=null)
			return (CpOrderLog)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrderLog> freeFind(CpOrderLog obj) {
		return queryForList("CP_ORDER_LOG.FreeFindCP_ORDER_LOG", obj );
	}

	public int countFreeFind(CpOrderLog obj) {
        Integer ret = (Integer) queryForObject("CP_ORDER_LOG.CountFreeFindCP_ORDER_LOG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrderLog> freeFind(CpOrderLog obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_ORDER_LOG.FreeFindCP_ORDER_LOG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrderLog> freeFind(CpOrderLog obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpOrderLog){
	    	_hashmap = ((CpOrderLog)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_ORDER_LOG.FreeFindCP_ORDER_LOGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrderLog> freeFind(CpOrderLog obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpOrderLog){
	    	_hashmap = ((CpOrderLog)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_ORDER_LOG.FreeFindCP_ORDER_LOGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpOrderLog> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpOrderLog> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpOrderLog oneObj = (CpOrderLog)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpOrderLog vo) {
        CpOrderLog obj = (CpOrderLog) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpOrderLog obj) {

		obj.fixup();
        return insert(cti, "CP_ORDER_LOG.InsertCP_ORDER_LOG", obj );
	}

	public int update(ContextInfo cti, CpOrderLog obj) {

		obj.fixup();
		return update(cti, "CP_ORDER_LOG.UpdateCP_ORDER_LOG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpOrderLog obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_ORDER_LOG.DeleteCP_ORDER_LOG", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpOrderLog obj) {

        obj.fixup();
        CpOrderLog oldObj = ( CpOrderLog )queryForObject("CP_ORDER_LOG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_ORDER_LOG.DeleteCP_ORDER_LOG", oldObj );

	}

	public boolean exists(CpOrderLog vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpOrderLog obj = (CpOrderLog) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_ORDER_LOG.CountCP_ORDER_LOG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41

	public List<CpOrderLog> findCurrStageLogByOrderPks(List<Integer> ids) {

		return queryForList("CP_ORDER_LOG.findCurrStageLogByOrderPks", ids);
	}

	public List<CpOrderLog> findStep4NeedPayOrder() {

		return queryForList("CP_ORDER_LOG.findStep4NeedPayOrder",null);
	}

	public CpOrderLog findOneCpOrderLog(CpOrderLog logVo) {
		Object ret = queryForObject("CP_ORDER_LOG.findOneCpOrderLog", logVo );
		if(ret !=null)
			return (CpOrderLog)ret;
		else 
			return null;
	}

	public int updateByCpOrderLogId(ContextInfo cti, CpOrderLog cpOrderLog) {
		cpOrderLog.fixup();
		return update(cti, "CP_ORDER_LOG.updateByCpOrderLogId", cpOrderLog );
	}
	
}
