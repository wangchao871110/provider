package com.xfs.mall.product.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.product.model.BsMallRecomproduct;

/**
 * BsMallRecomproductDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:04
 */
@Repository
public class BsMallRecomproductDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_RECOMPRODUCT.CountFindAllBS_MALL_RECOMPRODUCT", null );
        return ret.intValue();
	}

	public BsMallRecomproduct findByPK(BsMallRecomproduct obj) {
		Object ret = queryForObject("BS_MALL_RECOMPRODUCT.FindByPK", obj );
		if(ret !=null)
			return (BsMallRecomproduct)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomproduct> freeFind(BsMallRecomproduct obj) {
		return queryForList("BS_MALL_RECOMPRODUCT.FreeFindBS_MALL_RECOMPRODUCT", obj );
	}

	public int countFreeFind(BsMallRecomproduct obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_RECOMPRODUCT.CountFreeFindBS_MALL_RECOMPRODUCT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomproduct> freeFind(BsMallRecomproduct obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_RECOMPRODUCT.FreeFindBS_MALL_RECOMPRODUCT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomproduct> freeFind(BsMallRecomproduct obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallRecomproduct){
	    	_hashmap = ((BsMallRecomproduct)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_RECOMPRODUCT.FreeFindBS_MALL_RECOMPRODUCTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallRecomproduct> freeFind(BsMallRecomproduct obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallRecomproduct){
	    	_hashmap = ((BsMallRecomproduct)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_RECOMPRODUCT.FreeFindBS_MALL_RECOMPRODUCTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallRecomproduct> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallRecomproduct> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallRecomproduct oneObj = (BsMallRecomproduct)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallRecomproduct vo) {
        BsMallRecomproduct obj = (BsMallRecomproduct) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallRecomproduct obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_RECOMPRODUCT.InsertBS_MALL_RECOMPRODUCT", obj );
	}

	public int update(ContextInfo cti, BsMallRecomproduct obj) {

		obj.fixup();
		return update(cti, "BS_MALL_RECOMPRODUCT.UpdateBS_MALL_RECOMPRODUCT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallRecomproduct obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_RECOMPRODUCT.DeleteBS_MALL_RECOMPRODUCT", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallRecomproduct obj) {

        obj.fixup();
        BsMallRecomproduct oldObj = ( BsMallRecomproduct )queryForObject("BS_MALL_RECOMPRODUCT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_RECOMPRODUCT.DeleteBS_MALL_RECOMPRODUCT", oldObj );

	}

	public boolean exists(BsMallRecomproduct vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallRecomproduct obj = (BsMallRecomproduct) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_RECOMPRODUCT.CountBS_MALL_RECOMPRODUCT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:04
	
	@SuppressWarnings("unchecked")
	public List<Map> findProductPage(BsMallRecomproduct vo, int pageIndex, int pageSize){
		return getPaginatedList("BS_MALL_RECOMPRODUCT.findproductdata", vo, pageIndex, pageSize);
	}
	
	public Map findRecomByPK(Integer id){
		return (Map) queryForObject("BS_MALL_RECOMPRODUCT.findRecomByPK",id);
	}
	
	/**
	 * 商城首页推荐的商品列表
	 *
	 * @return
     */
	public List<Map<String, Object>> findRecomProducts() {
		return queryForList("BS_MALL_RECOMPRODUCT.FindBS_MALL_RECOMPRODUCT", null);
	}

}
