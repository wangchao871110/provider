package com.xfs.settlement.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.settlement.model.BlPayAccount;

/**
 * BlPayAccountDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 21:25:02
 */
@Repository
public class BlPayAccountDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_ACCOUNT.CountFindAllBL_PAY_ACCOUNT", null );
        return ret.intValue();
	}

	public BlPayAccount findByPK(BlPayAccount obj) {
		Object ret = queryForObject("BL_PAY_ACCOUNT.FindByPK", obj );
		if(ret !=null)
			return (BlPayAccount)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayAccount> freeFind(BlPayAccount obj) {
		return queryForList("BL_PAY_ACCOUNT.FreeFindBL_PAY_ACCOUNT", obj );
	}

	public int countFreeFind(BlPayAccount obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_ACCOUNT.CountFreeFindBL_PAY_ACCOUNT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayAccount> freeFind(BlPayAccount obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_ACCOUNT.FreeFindBL_PAY_ACCOUNT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayAccount> freeFind(BlPayAccount obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayAccount){
	    	_hashmap = ((BlPayAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_ACCOUNT.FreeFindBL_PAY_ACCOUNTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayAccount> freeFind(BlPayAccount obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayAccount){
	    	_hashmap = ((BlPayAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_ACCOUNT.FreeFindBL_PAY_ACCOUNTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayAccount> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayAccount> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayAccount oneObj = (BlPayAccount)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlPayAccount vo) {
        BlPayAccount obj = (BlPayAccount) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BlPayAccount obj) {

		obj.fixup();
        return insert(cti, "BL_PAY_ACCOUNT.InsertBL_PAY_ACCOUNT", obj );
	}

	public int update(ContextInfo cti, BlPayAccount obj) {

		obj.fixup();
		return update(cti, "BL_PAY_ACCOUNT.UpdateBL_PAY_ACCOUNT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlPayAccount obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_PAY_ACCOUNT.DeleteBL_PAY_ACCOUNT", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlPayAccount obj) {

        obj.fixup();
        BlPayAccount oldObj = ( BlPayAccount )queryForObject("BL_PAY_ACCOUNT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BL_PAY_ACCOUNT.DeleteBL_PAY_ACCOUNT", oldObj );

	}

	public boolean exists(BlPayAccount vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayAccount obj = (BlPayAccount) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_ACCOUNT.CountBL_PAY_ACCOUNT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:25:02

	/**
	 * 标记删除 支付账户
	 * @param whereMap
	 */
	public int modityAccount(ContextInfo cti, Map<String, Object> whereMap) {
		return update(cti, "BL_PAY_ACCOUNT.modityAccount", whereMap);
	}

	/**
	 * 增加 支付账户
	 * @param whereMap
	 */
	public int addAccount(ContextInfo cti, Map<String, Object> whereMap) {
		return update(cti, "BL_PAY_ACCOUNT.addAccount", whereMap);
	}

	/**
	 * 获取账号
	 * @param whereMap
	 * @return
	 */
	public Map<String, Object> getAccountInfo(Map<String, Object> whereMap) {
		return (Map<String, Object>) queryForObject("BL_PAY_ACCOUNT.getAccountInfo", whereMap);
	}

	/**
	 * 根据交易号获取支付账号信息列表
	 * @param payType
	 * @return
	 */
	public List<Map<String,Object>> queryPayAccountList(String payType){
		Map<String,Object> params = new HashMap<>();
		params.put("payType",payType);
		return queryForList("BL_PAY_ACCOUNT.QUERY_PAY_ACCOUNT_LIST",params);
	}

}
