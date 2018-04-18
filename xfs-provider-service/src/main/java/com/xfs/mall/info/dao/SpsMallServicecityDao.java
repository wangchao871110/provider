package com.xfs.mall.info.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.info.model.SpsMallServicecity;

/**
 * SpsMallServicecityDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 16:11:04
 */
@Repository
public class SpsMallServicecityDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_SERVICECITY.CountFindAllSPS_MALL_SERVICECITY", null );
        return ret.intValue();
	}

	public SpsMallServicecity findByPK(SpsMallServicecity obj) {
		Object ret = queryForObject("SPS_MALL_SERVICECITY.FindByPK", obj );
		if(ret !=null)
			return (SpsMallServicecity)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicecity> freeFind(SpsMallServicecity obj) {
		return queryForList("SPS_MALL_SERVICECITY.FreeFindSPS_MALL_SERVICECITY", obj );
	}

	public int countFreeFind(SpsMallServicecity obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_SERVICECITY.CountFreeFindSPS_MALL_SERVICECITY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicecity> freeFind(SpsMallServicecity obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_SERVICECITY.FreeFindSPS_MALL_SERVICECITY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicecity> freeFind(SpsMallServicecity obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallServicecity){
	    	_hashmap = ((SpsMallServicecity)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_SERVICECITY.FreeFindSPS_MALL_SERVICECITYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicecity> freeFind(SpsMallServicecity obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallServicecity){
	    	_hashmap = ((SpsMallServicecity)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_SERVICECITY.FreeFindSPS_MALL_SERVICECITYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallServicecity> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallServicecity> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallServicecity oneObj = (SpsMallServicecity)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallServicecity vo) {
        SpsMallServicecity obj = (SpsMallServicecity) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallServicecity obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_SERVICECITY.InsertSPS_MALL_SERVICECITY", obj );
	}

	public int update(ContextInfo cti, SpsMallServicecity obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_SERVICECITY.UpdateSPS_MALL_SERVICECITY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallServicecity obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_SERVICECITY.DeleteSPS_MALL_SERVICECITY", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallServicecity obj) {

        obj.fixup();
        SpsMallServicecity oldObj = ( SpsMallServicecity )queryForObject("SPS_MALL_SERVICECITY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_SERVICECITY.DeleteSPS_MALL_SERVICECITY", oldObj );

	}

	public boolean exists(SpsMallServicecity vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallServicecity obj = (SpsMallServicecity) vo;

        keysFilled = keysFilled && ( null != obj.getCityId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_SERVICECITY.CountSPS_MALL_SERVICECITY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 16:11:04
	
	
	
}
