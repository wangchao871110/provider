package com.xfs.cp.dao;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;
import com.xfs.cp.model.CpOrder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * CpOrderDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
@Repository
public class CpOrderDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_ORDER.CountFindAllCP_ORDER", null );
        return ret.intValue();
	}

	public CpOrder findByPK(CpOrder obj) {
		Object ret = queryForObject("CP_ORDER.FindByPK", obj );
		if(ret !=null)
			return (CpOrder)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrder> freeFind(CpOrder obj) {
		return queryForList("CP_ORDER.FreeFindCP_ORDER", obj );
	}

	public int countFreeFind(CpOrder obj) {
        Integer ret = (Integer) queryForObject("CP_ORDER.CountFreeFindCP_ORDER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrder> freeFind(CpOrder obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_ORDER.FreeFindCP_ORDER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrder> freeFind(CpOrder obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpOrder){
	    	_hashmap = ((CpOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_ORDER.FreeFindCP_ORDEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpOrder> freeFind(CpOrder obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpOrder){
	    	_hashmap = ((CpOrder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_ORDER.FreeFindCP_ORDEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpOrder> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpOrder> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpOrder oneObj = (CpOrder)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpOrder vo) {
        CpOrder obj = (CpOrder) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpOrder obj) {

		obj.fixup();
        return insert(cti, "CP_ORDER.InsertCP_ORDER", obj );
	}

	public int update(ContextInfo cti, CpOrder obj) {

		obj.fixup();
		return update(cti, "CP_ORDER.UpdateCP_ORDER", obj );

	}

	public int updateByOrderId(ContextInfo cti, CpOrder obj) {
		
		obj.fixup();
		return update(cti, "CP_ORDER.updateByOrderId", obj );
		
	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpOrder obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_ORDER.DeleteCP_ORDER", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpOrder obj) {

        obj.fixup();
        CpOrder oldObj = ( CpOrder )queryForObject("CP_ORDER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_ORDER.DeleteCP_ORDER", oldObj );

	}

	public boolean exists(CpOrder vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpOrder obj = (CpOrder) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_ORDER.CountCP_ORDER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	
	/**
	 * 
	 * @method comments: 获取接单成功数和价格
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月14日 上午10:28:00 
	 * @param vo
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月14日 上午10:28:00 wangchao 创建
	 *
	 */
	public CpOrder findOrderNumByBSpId(CpOrder vo) {
		Object ret = queryForObject("CP_ORDER.findOrderNumByBSpId", vo );
		if(ret !=null)
			return (CpOrder)ret;
		else 
			return null;
	}

	public CpOrder findByOrderId(CpOrder obj) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof CpOrder){
			_hashmap = ((CpOrder)obj).toHashMap();
		}
		List<CpOrder> list = queryForList("CP_ORDER.FindByOrderId", _hashmap);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	public List<CpOrder> findASpOrder(CpOrder obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_ORDER.findASpOrder", obj, pageIndex, pageSize );
	}

	public int findASpOrderCount(CpOrder obj) {
		Integer ret = (Integer) queryForObject("CP_ORDER.findASpOrderCount", obj );
		return ret.intValue();
	}

	public List<CpOrder> findBSpOrder(CpOrder obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_ORDER.findBSpOrder", obj, pageIndex, pageSize);
	}

	public int findBSpOrderCount(CpOrder obj) {
		Integer ret = (Integer) queryForObject("CP_ORDER.findBSpOrderCount", obj );
		return ret.intValue();
	}

	public List<CpOrder> findNotFinishOrder(CpOrder obj) {
		return queryForList("CP_ORDER.findNotFinishOrder", obj);
	}

    /**
     * @autor luyong
     * @desc 服务商查询自己未完成且未查看的派/接单任务的数量
     * @since 2016-10-19 create
     * @param obj
     * @return
     */
	public Integer findNotFinishNoReadOrder(CpOrder obj) {
        Integer ret = (Integer) queryForObject("CP_ORDER.findNotFinishNoReadOrder", obj );
		return ret.intValue();
	}

	public CpOrder findPriceBySpId(Integer id,Date createDtFrom,Date createDtTo) {
		CpOrder obj = new CpOrder();
		obj.setBSpId(id);
		obj.setCreateDtFrom(createDtFrom);
		obj.setCreateDtTo(createDtTo);
		Object ret = queryForObject("CP_ORDER.findOrderNumByBSpId", obj );
		if(ret !=null)
			return (CpOrder)ret;
		else 
			return null;
	}

	//
    public List<CpOrder> FindManage(CpOrder vo, Integer pageIndex, Integer pageSize) {
            if (StringUtils.isBlank(vo.getOrderId()))
                vo.setOrderId(null);
            if (null == vo.getPleaseStatus())
                vo.setPleaseStatus(null);
            return getPaginatedList("CP_ORDER.FreeFindAccount_Manage", vo, pageIndex, pageSize);

        }

    public Integer FindManageCount(CpOrder vo)
    {
        if (StringUtils.isBlank(vo.getOrderId()))
            vo.setOrderId(null);
        if (null == vo.getModifyBy())
            vo.setModifyBy(null);
        Integer ret = (Integer) queryForObject("CP_ORDER.FreeFindAccount_Count", vo );
        return ret.intValue();
    }

    /**
     * 获取待付款金额
     * @return
     */
    public Map<String,Object> queryExpAmount(CpOrder cpOrder){
        return (Map<String,Object>)queryForObject("CP_ORDER.QUERY_EXP_AMOUNT", cpOrder );
    }

    /**
     * 获取已付款金额
     * @return
     */
    public Map<String,Object> queryPayAmount(CpOrder cpOrder){
        return (Map<String,Object>)queryForObject("CP_ORDER.QUERY_PAY_AMOUNT", cpOrder );
    }


	/**
	 * 获取未付款金额
	 * @return
	 */
	public Map<String,Object> queryNotPayAmount(CpOrder cpOrder){
		return (Map<String,Object>)queryForObject("CP_ORDER.QUERY_NOT_PAY_AMOUNT", cpOrder );
	}

    //收款
    /**
     * 汇总线下充值总金额
     * @return
     */
    public Map<String,Object> queryVoucherOffLineAmount(CpOrder cpOrder){
        return (Map<String,Object>)queryForObject("CP_ORDER.QUERY_CP_ORDER_OFFLINE_AMOUNT",cpOrder);
    }

    /**
     * 汇总线上充值总金额
     * @return
     */
    public Map<String,Object> queryVoucherOnLineAmount(CpOrder cpOrder){
        return (Map<String,Object>)queryForObject("CP_ORDER.QUERY_CP_ORDER_ONLINE_AMOUNT",cpOrder);
    }

    /**
     * 获取列表数量
     * @param cpOrder
     * @return
     */
    public Integer queryVoucherCount(CpOrder cpOrder){
        Integer ret = (Integer) queryForObject("CP_ORDER.FreeFindAccount_Count", cpOrder );
        return ret.intValue();
    }

    /**
     * 获取列表
     * @param cpOrder
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<CpOrder> queryVoucherList(CpOrder cpOrder,Integer pageIndex,Integer pageSize){
        return (List<CpOrder>)getPaginatedList("CP_ORDER.FreeFindAccount_Manage", cpOrder, pageIndex, pageSize );
    }

    public List<Map<String, Object>> findAllNews(CpOrder obj) {
        return queryForList("CP_ORDER.FreeFindAccount_Manage1", obj );
    }


	public List<CpOrder> findByCpId(CpOrder vo, Integer pageIndex, Integer pageSize) {
		return getPaginatedList("CP_ORDER.FIND_BY_CPID", vo, pageIndex, pageSize);

	}

	public Integer countFindByCpId(CpOrder vo)
	{
		Integer ret = (Integer) queryForObject("CP_ORDER.FIND_BY_CPID_COUNT", vo );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findByASpIdAndBSpId(CpOrder vo) {
		return queryForList("CP_ORDER.findByASpIdAndBSpId", vo);
	}


	/**
	 * 服务费
	 * @return
	 */
	public Map<String,Object> servicePrice(CpOrder cpOrder){
		return (Map<String,Object>)queryForObject("CP_ORDER.SERVICE-PRICE", cpOrder );
	}

}
