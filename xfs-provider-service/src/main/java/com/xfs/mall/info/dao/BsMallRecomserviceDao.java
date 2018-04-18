package com.xfs.mall.info.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.info.model.BsMallRecomservice;

/**
 * BsMallRecomserviceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:12
 */
@Repository
public class BsMallRecomserviceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_RECOMSERVICE.CountFindAllBS_MALL_RECOMSERVICE", null );
        return ret.intValue();
	}

	public BsMallRecomservice findByPK(BsMallRecomservice obj) {
		Object ret = queryForObject("BS_MALL_RECOMSERVICE.FindByPK", obj );
		if(ret !=null)
			return (BsMallRecomservice)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomservice> freeFind(BsMallRecomservice obj) {
		return queryForList("BS_MALL_RECOMSERVICE.FreeFindBS_MALL_RECOMSERVICE", obj );
	}

	public int countFreeFind(BsMallRecomservice obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_RECOMSERVICE.CountFreeFindBS_MALL_RECOMSERVICE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomservice> freeFind(BsMallRecomservice obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_RECOMSERVICE.FreeFindBS_MALL_RECOMSERVICE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomservice> freeFind(BsMallRecomservice obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallRecomservice){
	    	_hashmap = ((BsMallRecomservice)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_RECOMSERVICE.FreeFindBS_MALL_RECOMSERVICEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomservice> freeFind(BsMallRecomservice obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallRecomservice){
	    	_hashmap = ((BsMallRecomservice)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_RECOMSERVICE.FreeFindBS_MALL_RECOMSERVICEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallRecomservice> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallRecomservice> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallRecomservice oneObj = (BsMallRecomservice)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallRecomservice vo) {
        BsMallRecomservice obj = (BsMallRecomservice) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallRecomservice obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_RECOMSERVICE.InsertBS_MALL_RECOMSERVICE", obj );
	}

	public int update(ContextInfo cti, BsMallRecomservice obj) {

		obj.fixup();
		return update(cti, "BS_MALL_RECOMSERVICE.UpdateBS_MALL_RECOMSERVICE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallRecomservice obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_RECOMSERVICE.DeleteBS_MALL_RECOMSERVICE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallRecomservice obj) {

        obj.fixup();
        BsMallRecomservice oldObj = ( BsMallRecomservice )queryForObject("BS_MALL_RECOMSERVICE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_RECOMSERVICE.DeleteBS_MALL_RECOMSERVICE", oldObj );

	}

	public boolean exists(BsMallRecomservice vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallRecomservice obj = (BsMallRecomservice) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_RECOMSERVICE.CountBS_MALL_RECOMSERVICE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:12
	
	//bs
	@SuppressWarnings("unchecked")
	public List<Map> findServicePage(BsMallRecomservice vo, int pageIndex, int pageSize){
		return getPaginatedList("BS_MALL_RECOMSERVICE.findservicedata", vo, pageIndex, pageSize);
	}
	
	public Map findRecomByPK(Integer id){
		return (Map) queryForObject("BS_MALL_RECOMSERVICE.findRecomByPK",id);
	}
	
	//cs
	/**
	 * 商城首页推荐的服务商列表
	 *
	 * @return
     */
	public List<Map<String, Object>> findRecomServices() {
		return queryForList("BS_MALL_RECOMSERVICE.FindBS_MALL_RECOMSERVICE", null);
	}
}
