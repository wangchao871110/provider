package com.xfs.mall.customer.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.customer.model.SpsMallCustomer;

/**
 * SpsMallCustomerDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 13:02:25
 */
@Repository
public class SpsMallCustomerDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_CUSTOMER.CountFindAllSPS_MALL_CUSTOMER", null );
        return ret.intValue();
	}

	public SpsMallCustomer findByPK(SpsMallCustomer obj) {
		Object ret = queryForObject("SPS_MALL_CUSTOMER.FindByPK", obj );
		if(ret !=null)
			return (SpsMallCustomer)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallCustomer> freeFind(SpsMallCustomer obj) {
		return queryForList("SPS_MALL_CUSTOMER.FreeFindSPS_MALL_CUSTOMER", obj );
	}

	public int countFreeFind(SpsMallCustomer obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_CUSTOMER.CountFreeFindSPS_MALL_CUSTOMER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallCustomer> freeFind(SpsMallCustomer obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_CUSTOMER.FreeFindSPS_MALL_CUSTOMER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallCustomer> freeFind(SpsMallCustomer obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallCustomer){
	    	_hashmap = ((SpsMallCustomer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_CUSTOMER.FreeFindSPS_MALL_CUSTOMEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallCustomer> freeFind(SpsMallCustomer obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallCustomer){
	    	_hashmap = ((SpsMallCustomer)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_CUSTOMER.FreeFindSPS_MALL_CUSTOMEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallCustomer> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallCustomer> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallCustomer oneObj = (SpsMallCustomer)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallCustomer vo) {
        SpsMallCustomer obj = (SpsMallCustomer) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallCustomer obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_CUSTOMER.InsertSPS_MALL_CUSTOMER", obj );
	}

	public int update(ContextInfo cti, SpsMallCustomer obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_CUSTOMER.UpdateSPS_MALL_CUSTOMER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallCustomer obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_CUSTOMER.DeleteSPS_MALL_CUSTOMER", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallCustomer obj) {

        obj.fixup();
        SpsMallCustomer oldObj = ( SpsMallCustomer )queryForObject("SPS_MALL_CUSTOMER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_CUSTOMER.DeleteSPS_MALL_CUSTOMER", oldObj );

	}

	public boolean exists(SpsMallCustomer vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallCustomer obj = (SpsMallCustomer) vo;

        keysFilled = keysFilled && ( null != obj.getCustomerId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_CUSTOMER.CountSPS_MALL_CUSTOMER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 13:02:25
	
	public SpsMallCustomer findByIMUserid(Integer user_id) {
		Object ret = queryForObject("SPS_MALL_CUSTOMER.findByIMUserid", user_id);
		if(ret !=null)
			return (SpsMallCustomer)ret;
		else
			return null;
	}

	public Map findByMyUserid(Integer user_id) {
		Object ret = queryForObject("SPS_MALL_CUSTOMER.findByMyUserid", user_id);
		if(ret !=null)
			return (Map) ret;
		else
			return null;
	}
	/**
	 * 查询 客服记录
	 *
	 * @author lifq
	 *
	 * 2016年8月1日  下午4:40:37
	 */
	@SuppressWarnings("unchecked")
	public List<SpsMallCustomer> findAllNotCurrent(SpsMallCustomer obj) {
		return queryForList("SPS_MALL_CUSTOMER.findAllNotCurrent", obj );
	}
	
	/**
	 * 商铺客服列表
	 *
	 * @param vo
	 * @return
     */
	public List<SpsMallCustomer> findMallCustomers(SpsMallCustomer vo) {
		return queryForList("SPS_MALL_CUSTOMER.FindSPS_MALL_CUSTOMER", vo);
	}

	/**
	 * 薪福社运营客服
	 *
	 * @return
     */
	public List<SpsMallCustomer> findBusinessCustomers() {
		return queryForList("SPS_MALL_CUSTOMER.FindBusinessCustomers", null);
	}

	/**
	 * 查询客服的手机号
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findCustomerMobile(SpsMallCustomer vo) {
		return queryForList("SPS_MALL_CUSTOMER.FindSPS_MALL_CUSTOMER_MOBILE", vo);
	}
}
