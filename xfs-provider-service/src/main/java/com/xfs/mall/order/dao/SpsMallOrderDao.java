package com.xfs.mall.order.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bs.dto.AppraiseOrder;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiOrder;
import com.xfs.insurance.dto.MyCiOrder;
import com.xfs.insurance.dto.MyOrder;
import com.xfs.mall.order.model.SpsMallOrder;

/**
 * SpsMallOrderDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 16:41:09
 */
@Repository
public class SpsMallOrderDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.CountFindAllSPS_MALL_ORDER", null );
        return ret.intValue();
	}

	public SpsMallOrder findByPK(SpsMallOrder obj) {
		Object ret = queryForObject("SPS_MALL_ORDER.FindByPK", obj );
		if(ret !=null)
			return (SpsMallOrder)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrder> freeFind(SpsMallOrder obj) {
		return queryForList("SPS_MALL_ORDER.FreeFindSPS_MALL_ORDER", obj );
	}

	public int countFreeFind(SpsMallOrder obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.CountFreeFindSPS_MALL_ORDER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrder> freeFind(SpsMallOrder obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_ORDER.FreeFindSPS_MALL_ORDER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrder> freeFind(SpsMallOrder obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrder){
	    	_hashmap = ((SpsMallOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_ORDER.FreeFindSPS_MALL_ORDEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallOrder> freeFind(SpsMallOrder obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallOrder){
	    	_hashmap = ((SpsMallOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_ORDER.FreeFindSPS_MALL_ORDEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallOrder> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallOrder> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallOrder oneObj = (SpsMallOrder)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallOrder vo) {
        SpsMallOrder obj = (SpsMallOrder) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallOrder obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_ORDER.InsertSPS_MALL_ORDER", obj );
	}

	public int update(ContextInfo cti, SpsMallOrder obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_ORDER.UpdateSPS_MALL_ORDER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallOrder obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_ORDER.DeleteSPS_MALL_ORDER", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallOrder obj) {

        obj.fixup();
        SpsMallOrder oldObj = ( SpsMallOrder )queryForObject("SPS_MALL_ORDER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_ORDER.DeleteSPS_MALL_ORDER", oldObj );

	}

	public boolean exists(SpsMallOrder vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallOrder obj = (SpsMallOrder) vo;

        keysFilled = keysFilled && ( null != obj.getOrderId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_ORDER.CountSPS_MALL_ORDER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 16:41:09
	
	//以下bs项目代码
    public List<AppraiseOrder> queryAppraiseOrderList(String searchWord, SpsMallOrder spsMallOrder, Integer pageIndex, Integer pageSize) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("spName",searchWord);
        obj.put("createFrom",spsMallOrder.getCreateDtFrom());
        obj.put("createTo",spsMallOrder.getCreateDtTo());
        obj.put("orderStar", spsMallOrder.getOrderStar());
        return getPaginatedList("SPS_MALL_ORDER.QUERY_ORDER_APPRAISEORDER_LIST", obj, pageIndex, pageSize);
    }

    public Integer queryAppraiseOrderListCount(String searchWord, SpsMallOrder spsMallOrder) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("spName",searchWord);
        obj.put("createFrom",spsMallOrder.getCreateDtFrom());
        obj.put("createTo",spsMallOrder.getCreateDtTo());
        obj.put("orderStar", spsMallOrder.getOrderStar());
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.QUERY_ORDER_APPRAISEORDER_LIST_COUNT", obj );
        return ret.intValue();

    }


    /**
     * 查询商保订单总数
     *
     * @param ciOrder 查询条件
     * @return
     */
    public Integer countCiOrder(CiOrder ciOrder) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.countCiOrder", ciOrder);
        return ret.intValue();
    }

    /**
     * 查询商保订单列表
     * @param ciOrder 查询条件
     * @param pageIndex 分页偏移量
     * @param pageSize 页数
     * @return
     */
    public List<CiOrder> queryCiOrder(CiOrder ciOrder, Integer pageIndex, Integer pageSize) {
        return getPaginatedList("SPS_MALL_ORDER.queryCiOrder", ciOrder, pageIndex, pageSize);
    }

    /**
     * 查询企业意向单列表
     * @param map
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String , Object>> queryMallOrder(Map map,Integer pageIndex, Integer pageSize){
    	return getPaginatedList("SPS_MALL_ORDER.queryMallOrder", map, pageIndex, pageSize);
    }
    
	/**
	 * 查询意向单总数 
	 * @param map
	 * @return
	 */
    public Integer countMallOrder(Map map) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.countMallOrder", map);
        return ret.intValue();
    }
    
    public Map<String, Object> queryLIST(Map map){
    	return (Map<String, Object>) queryForObject("SPS_MALL_ORDER.queryList", map);
    }
    
    public List<Map<String, Object>> queryitem(Map map,Integer pageIndex, Integer pageSize){
    	return getPaginatedList("SPS_MALL_ORDER.queryitem", map,pageIndex,pageSize);
    }
    public Integer countitem(Map map) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.countitem", map);
        return ret.intValue();
    }
    
    
    //以下是cs项目代码
    public List<MyOrder> queryMyOrderList(String searchWord, SpsMallOrder spsMallOrder, Integer pageIndex, Integer pageSize) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("searchWord",searchWord);
        obj.put("cpid",spsMallOrder.getCustomerId());
        obj.put("createDtFrom",spsMallOrder.getCreateDtFrom());
        obj.put("createDtTo",spsMallOrder.getCreateDtTo());
        obj.put("orderState", spsMallOrder.getOrderState());

        return getPaginatedList("SPS_MALL_ORDER.QUERY_CONDITION_SPS_MALL_ORDER_LIST", obj, pageIndex, pageSize);
    }

    public Integer queryMyOrderListCount(String searchWord, SpsMallOrder spsMallOrder) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("searchWord",searchWord);
        obj.put("cpid",spsMallOrder.getCustomerId());
        obj.put("createDtFrom",spsMallOrder.getCreateDtFrom());
        obj.put("createDtTo",spsMallOrder.getCreateDtTo());
        obj.put("orderState", spsMallOrder.getOrderState());
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.QUERY_CONDITION_SPS_MALL_ORDER_LIST_COUNT", obj );
        return ret.intValue();

    }
    public MyOrder findOrderById( SpsMallOrder obj) {
        Object ret = (Object) queryForObject("SPS_MALL_ORDER.FIND_CS_MALL_ORDER", obj );
        if(ret !=null)
            return (MyOrder)ret;
        else
            return null;
    }

    /**
     * 查询商保订单详情
     * @param obj
     * @return
     */
    public MyCiOrder findCiOrderById(SpsMallOrder obj) {
        Object ret = (Object) queryForObject("SPS_MALL_ORDER.FIND_SPS_MALL_CI_ORDER", obj );
        if(ret !=null)
            return (MyCiOrder)ret;
        else
            return null;
    }
    
    /**
	 * 自定义分页查询 记录数
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午7:11:15
	 */
	public int customCountFreeFind(SpsMallOrder obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.CountFind_SPS_MALL_ORDER", obj );
        return ret.intValue();
	}
	/**
	 * 自定义分页查询
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午7:11:15
	 */
	@SuppressWarnings("unchecked")
	public List<SpsMallOrder> customFreeFind(SpsMallOrder obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_ORDER.Find_SPS_MALL_ORDER", obj, pageIndex, pageSize );
	}
	/**
	 * 根据orderId查询 订单及产品信息
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  上午10:56:36
	 */
	public SpsMallOrder findOrderByOrderId(SpsMallOrder obj) {
		List ret = queryForList("SPS_MALL_ORDER.Find_SPS_MALL_ORDER", obj );
		if(ret !=null && ret.size()>0)
			return (SpsMallOrder)ret.get(0);
		else 
			return null;
	}
	/**
	 * 查询 订单及 信息项
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  下午1:22:09
	 */
	public List<Map<String, Object>> findOrderItems(SpsMallOrder vo){
		return queryForList("SPS_MALL_ORDER.findOrderItems",vo);
	}
	
	/**
	 * 
	* @Title: updateState 
	* @Description: 通过审核更改订单状态及合同状态
	* @param @param vo
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @throws
	 */
	public int updateState(ContextInfo cti,SpsMallOrder obj){
		obj.fixup();
		return update(cti, "SPS_MALL_ORDER.updateState", obj );
	}
	
	public Integer findCollaborator(ContextInfo cti, SpsMallOrder obj){
		Integer ret = (Integer) queryForObject("SPS_MALL_ORDER.findCollaborator", obj );
        return ret.intValue();
	}
}
