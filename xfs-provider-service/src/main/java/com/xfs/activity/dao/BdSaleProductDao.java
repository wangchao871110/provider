package com.xfs.activity.dao;
import java.util.*;

import com.xfs.activity.model.BdSaleProduct;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdSaleProductDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/16 15:07:22
 */
@Repository
public class BdSaleProductDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_SALE_PRODUCT.CountFindAllBD_SALE_PRODUCT", null );
        return ret.intValue();
	}

	public BdSaleProduct findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BD_SALE_PRODUCT.FindByPK", _hashmap );
		if(ret !=null)
			return (BdSaleProduct)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdSaleProduct> freeFind(BdSaleProduct obj) {
		return queryForList("BD_SALE_PRODUCT.FreeFindBD_SALE_PRODUCT", obj );
	}

	public int countFreeFind(BdSaleProduct obj) {
        Integer ret = (Integer) queryForObject("BD_SALE_PRODUCT.CountFreeFindBD_SALE_PRODUCT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdSaleProduct> freeFind(BdSaleProduct obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_SALE_PRODUCT.FreeFindBD_SALE_PRODUCT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdSaleProduct> freeFind(BdSaleProduct obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdSaleProduct){
	    	_hashmap = ((BdSaleProduct)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_SALE_PRODUCT.FreeFindBD_SALE_PRODUCTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdSaleProduct> freeFind(BdSaleProduct obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdSaleProduct){
	    	_hashmap = ((BdSaleProduct)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_SALE_PRODUCT.FreeFindBD_SALE_PRODUCTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdSaleProduct> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdSaleProduct> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdSaleProduct oneObj = (BdSaleProduct)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdSaleProduct vo) {
        BdSaleProduct obj = (BdSaleProduct) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, BdSaleProduct obj) {

		obj.fixup();
        return insert(cti, "BD_SALE_PRODUCT.InsertBD_SALE_PRODUCT", obj );
	}

	public int update(ContextInfo cti, BdSaleProduct obj) {

		obj.fixup();
		return update(cti, "BD_SALE_PRODUCT.UpdateBD_SALE_PRODUCT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdSaleProduct obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_SALE_PRODUCT.DeleteBD_SALE_PRODUCT", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdSaleProduct obj) {

        obj.fixup();
        BdSaleProduct oldObj = ( BdSaleProduct )queryForObject("BD_SALE_PRODUCT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_SALE_PRODUCT.DeleteBD_SALE_PRODUCT", oldObj );

	}

	public boolean exists(BdSaleProduct vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdSaleProduct obj = (BdSaleProduct) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_SALE_PRODUCT.CountBD_SALE_PRODUCT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 15:07:22

	public List<Map<String, Object>> querySaleProduct(BdSaleProduct vo) {
		return queryForList("BD_SALE_PRODUCT.querySaleProduct", vo);
	}
	
}
