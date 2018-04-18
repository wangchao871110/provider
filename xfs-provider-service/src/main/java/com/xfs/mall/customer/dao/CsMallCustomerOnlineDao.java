package com.xfs.mall.customer.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.customer.model.CsMallCustomerOnline;

/**
 * CsMallCustomerOnlineDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/22 13:56:10
 */
@Repository
public class CsMallCustomerOnlineDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ONLINE.CountFindAllCS_MALL_CUSTOMER_ONLINE", null );
        return ret.intValue();
	}

	public CsMallCustomerOnline findByPK(CsMallCustomerOnline obj) {
		Object ret = queryForObject("CS_MALL_CUSTOMER_ONLINE.FindByPK", obj );
		if(ret !=null)
			return (CsMallCustomerOnline)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerOnline> freeFind(CsMallCustomerOnline obj) {
		return queryForList("CS_MALL_CUSTOMER_ONLINE.FreeFindCS_MALL_CUSTOMER_ONLINE", obj );
	}

	public int countFreeFind(CsMallCustomerOnline obj) {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ONLINE.CountFreeFindCS_MALL_CUSTOMER_ONLINE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerOnline> freeFind(CsMallCustomerOnline obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_MALL_CUSTOMER_ONLINE.FreeFindCS_MALL_CUSTOMER_ONLINE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerOnline> freeFind(CsMallCustomerOnline obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerOnline){
	    	_hashmap = ((CsMallCustomerOnline)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_MALL_CUSTOMER_ONLINE.FreeFindCS_MALL_CUSTOMER_ONLINEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerOnline> freeFind(CsMallCustomerOnline obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerOnline){
	    	_hashmap = ((CsMallCustomerOnline)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_MALL_CUSTOMER_ONLINE.FreeFindCS_MALL_CUSTOMER_ONLINEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsMallCustomerOnline> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsMallCustomerOnline> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsMallCustomerOnline oneObj = (CsMallCustomerOnline)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsMallCustomerOnline vo) {
        CsMallCustomerOnline obj = (CsMallCustomerOnline) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsMallCustomerOnline obj) {

		obj.fixup();
        return insert(cti, "CS_MALL_CUSTOMER_ONLINE.InsertCS_MALL_CUSTOMER_ONLINE", obj );
	}

	public int update(ContextInfo cti, CsMallCustomerOnline obj) {

		obj.fixup();
		return update(cti, "CS_MALL_CUSTOMER_ONLINE.UpdateCS_MALL_CUSTOMER_ONLINE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsMallCustomerOnline obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_MALL_CUSTOMER_ONLINE.DeleteCS_MALL_CUSTOMER_ONLINE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsMallCustomerOnline obj) {

        obj.fixup();
        CsMallCustomerOnline oldObj = ( CsMallCustomerOnline )queryForObject("CS_MALL_CUSTOMER_ONLINE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_MALL_CUSTOMER_ONLINE.DeleteCS_MALL_CUSTOMER_ONLINE", oldObj );

	}

	public boolean exists(CsMallCustomerOnline vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsMallCustomerOnline obj = (CsMallCustomerOnline) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_MALL_CUSTOMER_ONLINE.CountCS_MALL_CUSTOMER_ONLINE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:10

	/**
	 * 根据条件查询客服时长信息
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String , Object>> selectImOnline(Map map,int pageIndex, int pageSize){
		return  getPaginatedList("CS_MALL_CUSTOMER_ONLINE.selectImOnline", map, pageIndex, pageSize);
	}
	
	public int selectImOnlineCount(Map map){
		Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_ONLINE.selectImOnlineCount", map);
		return ret.intValue();
	}
	
	/**
	 * 判断客服是否在线
	 *
	 * @param vo
	 * @return
     */
	public CsMallCustomerOnline findOnlineCustomer(CsMallCustomerOnline vo) {
		return (CsMallCustomerOnline) queryForObject("CS_MALL_CUSTOMER_ONLINE.findOnlineCustomer", vo);
	}
	
}
