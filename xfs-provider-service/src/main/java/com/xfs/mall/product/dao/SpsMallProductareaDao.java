package com.xfs.mall.product.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.product.model.SpsMallProductarea;

/**
 * SpsMallProductareaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/09 14:08:05
 */
@Repository
public class SpsMallProductareaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCTAREA.CountFindAllSPS_MALL_PRODUCTAREA", null );
        return ret.intValue();
	}

	public SpsMallProductarea findByPK(SpsMallProductarea obj) {
		Object ret = queryForObject("SPS_MALL_PRODUCTAREA.FindByPK", obj );
		if(ret !=null)
			return (SpsMallProductarea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductarea> freeFind(SpsMallProductarea obj) {
		return queryForList("SPS_MALL_PRODUCTAREA.FreeFindSPS_MALL_PRODUCTAREA", obj );
	}

	public int countFreeFind(SpsMallProductarea obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCTAREA.CountFreeFindSPS_MALL_PRODUCTAREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductarea> freeFind(SpsMallProductarea obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_PRODUCTAREA.FreeFindSPS_MALL_PRODUCTAREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductarea> freeFind(SpsMallProductarea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductarea){
	    	_hashmap = ((SpsMallProductarea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_PRODUCTAREA.FreeFindSPS_MALL_PRODUCTAREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProductarea> freeFind(SpsMallProductarea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallProductarea){
	    	_hashmap = ((SpsMallProductarea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_PRODUCTAREA.FreeFindSPS_MALL_PRODUCTAREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallProductarea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallProductarea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallProductarea oneObj = (SpsMallProductarea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallProductarea vo) {
        SpsMallProductarea obj = (SpsMallProductarea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallProductarea obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_PRODUCTAREA.InsertSPS_MALL_PRODUCTAREA", obj );
	}

	public int update(ContextInfo cti, SpsMallProductarea obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_PRODUCTAREA.UpdateSPS_MALL_PRODUCTAREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallProductarea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_PRODUCTAREA.DeleteSPS_MALL_PRODUCTAREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallProductarea obj) {

        obj.fixup();
        SpsMallProductarea oldObj = ( SpsMallProductarea )queryForObject("SPS_MALL_PRODUCTAREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_PRODUCTAREA.DeleteSPS_MALL_PRODUCTAREA", oldObj );

	}

	public boolean exists(SpsMallProductarea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallProductarea obj = (SpsMallProductarea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_PRODUCTAREA.CountSPS_MALL_PRODUCTAREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:05

	/**
	 * 产品的服务区域
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, String>> findProductAreas(SpsMallProductarea vo) {
		return queryForList("SPS_MALL_PRODUCTAREA.FindProductAreas", vo);
	}
	
	public List<Map> freeFindGROUP(String productId,String spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("productId", productId );
		_hashmap.put("spId", spId );
		return queryForList("SPS_MALL_PRODUCTAREA.FreeFindGROUP", _hashmap);
	}

	public List<Map> freeFindCOOPAndINNER(String productId,String spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("productId", productId );
		_hashmap.put("spId", spId );
		return queryForList("SPS_MALL_PRODUCTAREA.FreeFindCOOPAndINNER", _hashmap);
	}

	public int addBatch(ContextInfo cti, List<SpsMallProductarea> list){
		return  insert(cti, "SPS_MALL_PRODUCTAREA.batchInsert",list);
	}

	public Integer removeByProductId(ContextInfo cti, Integer productId){
		SpsMallProductarea vo = new SpsMallProductarea();
		vo.setProductId(productId);
		return update(cti, "SPS_MALL_PRODUCTAREA.RemoveByProductId", vo);
	}
}
