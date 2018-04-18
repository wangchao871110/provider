package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BsMallBanner;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsMallBannerDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:07:57
 */
@Repository
public class BsMallBannerDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_BANNER.CountFindAllBS_MALL_BANNER", null );
        return ret.intValue();
	}

	public BsMallBanner findByPK(BsMallBanner obj) {
		Object ret = queryForObject("BS_MALL_BANNER.FindByPK", obj );
		if(ret !=null)
			return (BsMallBanner)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallBanner> freeFind(BsMallBanner obj) {
		return queryForList("BS_MALL_BANNER.FreeFindBS_MALL_BANNER", obj );
	}

	public int countFreeFind(BsMallBanner obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_BANNER.CountFreeFindBS_MALL_BANNER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallBanner> freeFind(BsMallBanner obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_BANNER.FreeFindBS_MALL_BANNER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallBanner> freeFind(BsMallBanner obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallBanner){
	    	_hashmap = ((BsMallBanner)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_BANNER.FreeFindBS_MALL_BANNEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallBanner> freeFind(BsMallBanner obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallBanner){
	    	_hashmap = ((BsMallBanner)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_BANNER.FreeFindBS_MALL_BANNEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallBanner> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallBanner> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallBanner oneObj = (BsMallBanner)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallBanner vo) {
        BsMallBanner obj = (BsMallBanner) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallBanner obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_BANNER.InsertBS_MALL_BANNER", obj );
	}

	public int update(ContextInfo cti, BsMallBanner obj) {

		obj.fixup();
		return update(cti, "BS_MALL_BANNER.UpdateBS_MALL_BANNER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallBanner obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_BANNER.DeleteBS_MALL_BANNER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallBanner obj) {

        obj.fixup();
        BsMallBanner oldObj = ( BsMallBanner )queryForObject("BS_MALL_BANNER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_BANNER.DeleteBS_MALL_BANNER", oldObj );

	}

	public boolean exists(BsMallBanner vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallBanner obj = (BsMallBanner) vo;

        keysFilled = keysFilled && ( null != obj.getBannerId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_BANNER.CountBS_MALL_BANNER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:07:57
	
	/**
	 * 商城首页banner列表
	 *
	 * @return
     */
	public List<BsMallBanner> findMallBanners() {
		return queryForList("BS_MALL_BANNER.FindBS_MALL_BANNER", null);
	}
	
}
