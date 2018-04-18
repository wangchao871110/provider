package com.xfs.mall.info.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.info.model.SpsMallServicerange;

/**
 * SpsMallServicerangeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/23 11:04:37
 */
@Repository
public class SpsMallServicerangeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_SERVICERANGE.CountFindAllSPS_MALL_SERVICERANGE", null );
        return ret.intValue();
	}

	public SpsMallServicerange findByPK(SpsMallServicerange obj) {
		Object ret = queryForObject("SPS_MALL_SERVICERANGE.FindByPK", obj );
		if(ret !=null)
			return (SpsMallServicerange)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicerange> freeFind(SpsMallServicerange obj) {
		return queryForList("SPS_MALL_SERVICERANGE.FreeFindSPS_MALL_SERVICERANGE", obj );
	}

	public int countFreeFind(SpsMallServicerange obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_SERVICERANGE.CountFreeFindSPS_MALL_SERVICERANGE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicerange> freeFind(SpsMallServicerange obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_SERVICERANGE.FreeFindSPS_MALL_SERVICERANGE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicerange> freeFind(SpsMallServicerange obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallServicerange){
	    	_hashmap = ((SpsMallServicerange)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_SERVICERANGE.FreeFindSPS_MALL_SERVICERANGEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallServicerange> freeFind(SpsMallServicerange obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallServicerange){
	    	_hashmap = ((SpsMallServicerange)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_SERVICERANGE.FreeFindSPS_MALL_SERVICERANGEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallServicerange> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallServicerange> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallServicerange oneObj = (SpsMallServicerange)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallServicerange vo) {
        SpsMallServicerange obj = (SpsMallServicerange) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallServicerange obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_SERVICERANGE.InsertSPS_MALL_SERVICERANGE", obj );
	}

	public int update(ContextInfo cti, SpsMallServicerange obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_SERVICERANGE.UpdateSPS_MALL_SERVICERANGE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallServicerange obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_SERVICERANGE.DeleteSPS_MALL_SERVICERANGE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallServicerange obj) {

        obj.fixup();
        SpsMallServicerange oldObj = ( SpsMallServicerange )queryForObject("SPS_MALL_SERVICERANGE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_SERVICERANGE.DeleteSPS_MALL_SERVICERANGE", oldObj );

	}

	public boolean exists(SpsMallServicerange vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallServicerange obj = (SpsMallServicerange) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_SERVICERANGE.CountSPS_MALL_SERVICERANGE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/23 11:04:37

	/**
	 * 查询服务范围
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findSpsMallCategories(SpsMallServicerange vo) {
		return queryForList("SPS_MALL_SERVICERANGE.FIND_SPS_MALL_SERVICE_CATEGORIES", vo);
	}
	
}
