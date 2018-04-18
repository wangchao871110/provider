package com.xfs.mall.customer.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.customer.model.CsMallCustomerSession;

/**
 * CsMallCustomerSessionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/07/22 13:56:38
 */
@Repository
public class CsMallCustomerSessionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_SESSION.CountFindAllCS_MALL_CUSTOMER_SESSION", null );
        return ret.intValue();
	}

	public CsMallCustomerSession findByPK(CsMallCustomerSession obj) {
		Object ret = queryForObject("CS_MALL_CUSTOMER_SESSION.FindByPK", obj );
		if(ret !=null)
			return (CsMallCustomerSession)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerSession> freeFind(CsMallCustomerSession obj) {
		return queryForList("CS_MALL_CUSTOMER_SESSION.FreeFindCS_MALL_CUSTOMER_SESSION", obj );
	}

	public int countFreeFind(CsMallCustomerSession obj) {
        Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_SESSION.CountFreeFindCS_MALL_CUSTOMER_SESSION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerSession> freeFind(CsMallCustomerSession obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_MALL_CUSTOMER_SESSION.FreeFindCS_MALL_CUSTOMER_SESSION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerSession> freeFind(CsMallCustomerSession obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerSession){
	    	_hashmap = ((CsMallCustomerSession)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_MALL_CUSTOMER_SESSION.FreeFindCS_MALL_CUSTOMER_SESSIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMallCustomerSession> freeFind(CsMallCustomerSession obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsMallCustomerSession){
	    	_hashmap = ((CsMallCustomerSession)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_MALL_CUSTOMER_SESSION.FreeFindCS_MALL_CUSTOMER_SESSIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsMallCustomerSession> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsMallCustomerSession> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsMallCustomerSession oneObj = (CsMallCustomerSession)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsMallCustomerSession vo) {
        CsMallCustomerSession obj = (CsMallCustomerSession) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsMallCustomerSession obj) {

		obj.fixup();
        return insert(cti, "CS_MALL_CUSTOMER_SESSION.InsertCS_MALL_CUSTOMER_SESSION", obj );
	}

	public int update(ContextInfo cti, CsMallCustomerSession obj) {

		obj.fixup();
		return update(cti, "CS_MALL_CUSTOMER_SESSION.UpdateCS_MALL_CUSTOMER_SESSION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsMallCustomerSession obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_MALL_CUSTOMER_SESSION.DeleteCS_MALL_CUSTOMER_SESSION", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsMallCustomerSession obj) {

        obj.fixup();
        CsMallCustomerSession oldObj = ( CsMallCustomerSession )queryForObject("CS_MALL_CUSTOMER_SESSION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_MALL_CUSTOMER_SESSION.DeleteCS_MALL_CUSTOMER_SESSION", oldObj );

	}

	public boolean exists(CsMallCustomerSession vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsMallCustomerSession obj = (CsMallCustomerSession) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_MALL_CUSTOMER_SESSION.CountCS_MALL_CUSTOMER_SESSION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 13:56:38

	public CsMallCustomerSession findByCustomerIdAndUserId(CsMallCustomerSession vo){
		return (CsMallCustomerSession)queryForObject("CS_MALL_CUSTOMER_SESSION.FreeFind_By_CustomerId_UserId",vo);
	}

	public int batchInsert(ContextInfo cti, List<CsMallCustomerSession> list) {
		return insert(cti, "CS_MALL_CUSTOMER_SESSION.batchInsert", list );
	}

	public List<Map> freeFindTopTen(String customerId){
		return queryForList("CS_MALL_CUSTOMER_SESSION.FreeFindTopTen", customerId );
	}

	/**
	 * 客服的历史会话用户总数
	 *
	 * @param vo
	 * @return
	 */
	public int findCustomerSessionUserCount(CsMallCustomerSession vo, String searchWord) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(vo != null){
			_hashmap = vo.toHashMap();
		}
		_hashmap.put("searchWord", searchWord);
		Integer ret = (Integer) queryForObject("CS_MALL_CUSTOMER_SESSION.FindCustomerSessionUserCount", _hashmap);
		return ret.intValue();
	}

	/**
	 * 客服的历史会话用户列表
	 *
	 * @param vo
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	public List<Map<String, Object>> findCustomerSessionUsers(CsMallCustomerSession vo, String searchWord, int pageIndex, int pageSize) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(vo != null){
			_hashmap = vo.toHashMap();
		}
		_hashmap.put("searchWord", searchWord);
		return getPaginatedList("CS_MALL_CUSTOMER_SESSION.FindCustomerSessionUsers", _hashmap, pageIndex, pageSize);
	}

}
