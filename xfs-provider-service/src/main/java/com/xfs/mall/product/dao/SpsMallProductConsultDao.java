package com.xfs.mall.product.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.product.model.SpsMallProductConsult;

/**
 * SpsMallProductConsultDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/17 11:24:11
 */
@Repository
public class SpsMallProductConsultDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT_CONSULT.CountFindAllSPS_MALL_PRODUCT_CONSULT", null );
        return ret.intValue();
	}

	public SpsMallProductConsult findByPK(SpsMallProductConsult obj) {
		Object ret = queryForObject("SPS_MALL_PRODUCT_CONSULT.FindByPK", obj );
		if(ret !=null)
			return (SpsMallProductConsult)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductConsult> freeFind(SpsMallProductConsult obj) {
		return queryForList("SPS_MALL_PRODUCT_CONSULT.FreeFindSPS_MALL_PRODUCT_CONSULT", obj );
	}

	public int countFreeFind(SpsMallProductConsult obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT_CONSULT.CountFreeFindSPS_MALL_PRODUCT_CONSULT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductConsult> freeFind(SpsMallProductConsult obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_PRODUCT_CONSULT.FreeFindSPS_MALL_PRODUCT_CONSULT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductConsult> freeFind(SpsMallProductConsult obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductConsult){
	    	_hashmap = ((SpsMallProductConsult)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_PRODUCT_CONSULT.FreeFindSPS_MALL_PRODUCT_CONSULTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductConsult> freeFind(SpsMallProductConsult obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductConsult){
	    	_hashmap = ((SpsMallProductConsult)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_PRODUCT_CONSULT.FreeFindSPS_MALL_PRODUCT_CONSULTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallProductConsult> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallProductConsult> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallProductConsult oneObj = (SpsMallProductConsult)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallProductConsult vo) {
        SpsMallProductConsult obj = (SpsMallProductConsult) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallProductConsult obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_PRODUCT_CONSULT.InsertSPS_MALL_PRODUCT_CONSULT", obj );
	}

	public int update(ContextInfo cti, SpsMallProductConsult obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_PRODUCT_CONSULT.UpdateSPS_MALL_PRODUCT_CONSULT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallProductConsult obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_PRODUCT_CONSULT.DeleteSPS_MALL_PRODUCT_CONSULT", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallProductConsult obj) {

        obj.fixup();
        SpsMallProductConsult oldObj = ( SpsMallProductConsult )queryForObject("SPS_MALL_PRODUCT_CONSULT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_PRODUCT_CONSULT.DeleteSPS_MALL_PRODUCT_CONSULT", oldObj );

	}

	public boolean exists(SpsMallProductConsult vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallProductConsult obj = (SpsMallProductConsult) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_PRODUCT_CONSULT.CountSPS_MALL_PRODUCT_CONSULT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/17 11:24:11

	public  List<SpsMallProductConsult> getSpsMallProducts(){
		List<SpsMallProductConsult> spsMallProducts = queryForList("SPS_MALL_PRODUCT_CONSULT.FIND_SPS_MALL_PRODUCTS", null );
		return spsMallProducts;
	}
	
}
