package com.xfs.mall.product.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.mall.product.model.SpsMallProductItem;

/**
 * SpsMallProductItemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/10 15:11:16
 */
@Repository
public class SpsMallProductItemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT_ITEM.CountFindAllSPS_MALL_PRODUCT_ITEM", null );
        return ret.intValue();
	}

	public SpsMallProductItem findByPK(SpsMallProductItem obj) {
		Object ret = queryForObject("SPS_MALL_PRODUCT_ITEM.FindByPK", obj );
		if(ret !=null)
			return (SpsMallProductItem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductItem> freeFind(SpsMallProductItem obj) {
		return queryForList("SPS_MALL_PRODUCT_ITEM.FreeFindSPS_MALL_PRODUCT_ITEM", obj );
	}

	public int countFreeFind(SpsMallProductItem obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT_ITEM.CountFreeFindSPS_MALL_PRODUCT_ITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductItem> freeFind(SpsMallProductItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_PRODUCT_ITEM.FreeFindSPS_MALL_PRODUCT_ITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductItem> freeFind(SpsMallProductItem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductItem){
	    	_hashmap = ((SpsMallProductItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_PRODUCT_ITEM.FreeFindSPS_MALL_PRODUCT_ITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductItem> freeFind(SpsMallProductItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductItem){
	    	_hashmap = ((SpsMallProductItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_PRODUCT_ITEM.FreeFindSPS_MALL_PRODUCT_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallProductItem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallProductItem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallProductItem oneObj = (SpsMallProductItem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallProductItem vo) {
        SpsMallProductItem obj = (SpsMallProductItem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallProductItem obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_PRODUCT_ITEM.InsertSPS_MALL_PRODUCT_ITEM", obj );
	}

	public int update(ContextInfo cti, SpsMallProductItem obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_PRODUCT_ITEM.UpdateSPS_MALL_PRODUCT_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallProductItem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_PRODUCT_ITEM.DeleteSPS_MALL_PRODUCT_ITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallProductItem obj) {

        obj.fixup();
        SpsMallProductItem oldObj = ( SpsMallProductItem )queryForObject("SPS_MALL_PRODUCT_ITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_PRODUCT_ITEM.DeleteSPS_MALL_PRODUCT_ITEM", oldObj );

	}

	public boolean exists(SpsMallProductItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallProductItem obj = (SpsMallProductItem) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_PRODUCT_ITEM.CountSPS_MALL_PRODUCT_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 15:11:16
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAllByProductId(Integer productId) {
		return queryForList("SPS_MALL_PRODUCT_ITEM.FindAllByProductId", productId);
	}

	public Integer removeByProductId(ContextInfo cti, Integer productId) {
		SpsMallProductItem vo = new SpsMallProductItem();
		vo.setProductId(productId);
		return update(cti, "SPS_MALL_PRODUCT_ITEM.RemoveByProductId", vo);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAllByProductIdAndAreaId(Integer productId,Integer areaId) {
		SpsMallProductItem vo = new SpsMallProductItem();
		vo.setProductId(productId);
		vo.setItemId(areaId);
		return queryForList("SPS_MALL_PRODUCT_ITEM.findAllByProductIdAndAreaId", vo);
	}
	/**
	 *  批量插入产品项目
	 *  @param   list
	 * @return    : int
	 *  @createDate   : 2016/11/10 0010 上午 10:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 10:29
	 *  @updateAuthor  :
	 */
	public int addBatch(ContextInfo cti, List<SpsMallProductItem> list){
	  return  insert(cti, "SPS_MALL_PRODUCT_ITEM.batchInsert",list);
	 }
	 
	 /**
	 * 根据产品id查询产品的服务项
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findProduectItems(SpsMallProduct vo) {
	  	return queryForList("SPS_MALL_PRODUCT_ITEM.FindSPS_MALL_PRODUCT_ITEM", vo);
	}

	/**
	 * 根据产品ids查询产品的服务项
	 *
	 * @param _productIds
	 * @return
	 */
	public List<SpsMallProductItem> findProduectItemsByProductIds(String _productIds) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("_productIds",_productIds);
		return queryForList("SPS_MALL_PRODUCT_ITEM.FindSPS_MALL_PRODUCT_ITEM_BY_PRODUCTIDS", _hashmap);
	}
	
}
