package com.xfs.business.dao;

import com.xfs.business.model.CmHrDemandRecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * CmHrDemandRecordDao
 * @author:yangfw<yangfw@xinfushe.com>
 * @date:2016/11/30 12:00:46
 */
@Repository
public class CmHrDemandRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_HR_DEMAND_RECORD.CountFindAllCM_HR_DEMAND_RECORD", null );
        return ret.intValue();
	}

	public CmHrDemandRecord findByPK(CmHrDemandRecord obj) {
		Object ret = queryForObject("CM_HR_DEMAND_RECORD.FindByPK", obj );
		if(ret !=null)
			return (CmHrDemandRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmHrDemandRecord> freeFind(CmHrDemandRecord obj) {
		return queryForList("CM_HR_DEMAND_RECORD.FreeFindCM_HR_DEMAND_RECORD", obj );
	}

	public int countFreeFind(CmHrDemandRecord obj) {
        Integer ret = (Integer) queryForObject("CM_HR_DEMAND_RECORD.CountFreeFindCM_HR_DEMAND_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmHrDemandRecord> freeFind(CmHrDemandRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_HR_DEMAND_RECORD.FreeFindCM_HR_DEMAND_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmHrDemandRecord> freeFind(CmHrDemandRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmHrDemandRecord){
	    	_hashmap = ((CmHrDemandRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_HR_DEMAND_RECORD.FreeFindCM_HR_DEMAND_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmHrDemandRecord> freeFind(CmHrDemandRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmHrDemandRecord){
	    	_hashmap = ((CmHrDemandRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_HR_DEMAND_RECORD.FreeFindCM_HR_DEMAND_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<CmHrDemandRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmHrDemandRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmHrDemandRecord oneObj = (CmHrDemandRecord)it.next();
				i += save( cti,oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,CmHrDemandRecord vo) {
        CmHrDemandRecord obj = (CmHrDemandRecord) vo;

        if(exists( obj ) ) {
            return update(  cti,obj );
        } else {
            return insert(  cti,obj );
        }
	}

	public int insert(ContextInfo cti,CmHrDemandRecord obj) {

		obj.fixup();
        return insert(cti,"CM_HR_DEMAND_RECORD.InsertCM_HR_DEMAND_RECORD", obj );
	}

	public int update(ContextInfo cti,CmHrDemandRecord obj) {

		obj.fixup();
		return update(  cti,"CM_HR_DEMAND_RECORD.UpdateCM_HR_DEMAND_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,CmHrDemandRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(  cti,"CM_HR_DEMAND_RECORD.DeleteCM_HR_DEMAND_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti,CmHrDemandRecord obj) {

        obj.fixup();
        CmHrDemandRecord oldObj = ( CmHrDemandRecord )queryForObject("CM_HR_DEMAND_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti, "CM_HR_DEMAND_RECORD.DeleteCM_HR_DEMAND_RECORD", oldObj );

	}

	public boolean exists(CmHrDemandRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmHrDemandRecord obj = (CmHrDemandRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_HR_DEMAND_RECORD.CountCM_HR_DEMAND_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/30 12:00:46
	
	/**
	 * 2016-12-20  bs电话咨询管理
	 * 电话咨询管理页面
	 * xingzixian
	 *
     * @param map
     * @param pageIndex
     * @param pageSize
     * @return map
     */
	public List<Map<String , Object>> freeALL(Map<String, Object> obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_HR_DEMAND_RECORD.FINDRecordToCmcorp", obj, pageIndex, pageSize );
	} 
	
	/**
	 * 2016-12-20  bs电话咨询管理查询记录数
	 * 电话咨询管理页面
	 * xingzixian
	 *
     * @param map
     * @param pageIndex
     * @param pageSize
     * @return map
     */
	public int CountfreeALL(Map<String, Object> obj) {
        Integer ret = (Integer) queryForObject("CM_HR_DEMAND_RECORD.COUNTFINDRecordToCmcorp", obj );
        return ret.intValue();
	}


	public int queryCountByTenantId(ContextInfo cti,String tenantId){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("tenantId",tenantId);
		Integer ret = (Integer) queryForObject("CM_HR_DEMAND_RECORD.queryCountByTenantId", _hashmap );
		return ret.intValue();
	}
	
}
