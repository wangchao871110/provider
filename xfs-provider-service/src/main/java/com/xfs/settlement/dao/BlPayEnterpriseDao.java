package com.xfs.settlement.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.settlement.dto.ReqMerchant;
import com.xfs.settlement.model.BlPayEnterprise;

import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;

/**
 * BlPayEnterpriseDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/17 16:32:49
 */
@Repository
public class BlPayEnterpriseDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_ENTERPRISE.CountFindAllBL_PAY_ENTERPRISE", null );
        return ret.intValue();
	}

	public BlPayEnterprise findByPK(BlPayEnterprise obj) {
		Object ret = queryForObject("BL_PAY_ENTERPRISE.FindByPK", obj );
		if(ret !=null)
			return (BlPayEnterprise)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayEnterprise> freeFind(BlPayEnterprise obj) {
		return queryForList("BL_PAY_ENTERPRISE.FreeFindBL_PAY_ENTERPRISE", obj );
	}

	public int countFreeFind(BlPayEnterprise obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_ENTERPRISE.CountFreeFindBL_PAY_ENTERPRISE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayEnterprise> freeFind(BlPayEnterprise obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_ENTERPRISE.FreeFindBL_PAY_ENTERPRISE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayEnterprise> freeFind(BlPayEnterprise obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayEnterprise){
	    	_hashmap = ((BlPayEnterprise)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_ENTERPRISE.FreeFindBL_PAY_ENTERPRISEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayEnterprise> freeFind(BlPayEnterprise obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayEnterprise){
	    	_hashmap = ((BlPayEnterprise)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_ENTERPRISE.FreeFindBL_PAY_ENTERPRISEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayEnterprise> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayEnterprise> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayEnterprise oneObj = (BlPayEnterprise)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlPayEnterprise vo) {
        BlPayEnterprise obj = (BlPayEnterprise) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, BlPayEnterprise obj) {

		obj.fixup();
        return insert(cti,"BL_PAY_ENTERPRISE.InsertBL_PAY_ENTERPRISE", obj );
	}

	public int update(ContextInfo cti, BlPayEnterprise obj) {

		obj.fixup();
		return update(cti, "BL_PAY_ENTERPRISE.UpdateBL_PAY_ENTERPRISE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlPayEnterprise obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_PAY_ENTERPRISE.DeleteBL_PAY_ENTERPRISE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlPayEnterprise obj) {

        obj.fixup();
        BlPayEnterprise oldObj = ( BlPayEnterprise )queryForObject("BL_PAY_ENTERPRISE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"BL_PAY_ENTERPRISE.DeleteBL_PAY_ENTERPRISE", oldObj );

	}

	public boolean exists(BlPayEnterprise vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayEnterprise obj = (BlPayEnterprise) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_ENTERPRISE.CountBL_PAY_ENTERPRISE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/17 16:32:49

	/**
	 *  根据拥有者ID 和 拥有者类型获取子商户信息
	 *  @param   reqMerchant
	 *	@return 			: com.xfs.settlement.model.BlPayEnterprise
	 *  @createDate  	: 2016-11-19 16:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 16:29
	 *  @updateAuthor  :
	 */
	public BlPayEnterprise queryEnterpriseByOwnId(ReqMerchant reqMerchant){
		BlPayEnterprise enterprise = ( BlPayEnterprise )queryForObject("BL_PAY_ENTERPRISE.QUERY_ENTERPRISE_BY_OWNID", reqMerchant );
		return enterprise;
	}

	/**
	 *  @param   enterprise
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-09 11:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-09 11:15
	 *  @updateAuthor  :
	 */
	public int updateEnterprise(BlPayEnterprise enterprise){
		return update(null,"BL_PAY_ENTERPRISE.UPDATE_ENTERPRISE_STATUS",enterprise);
	}
	
	/**
	 * @param spid
	 * @author         	: zhengdan@xinfushe.com
	 * @return
	 */
	public Map<String,Object> queryEnterAccount(Integer spid){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		return (Map<String,Object>)queryForObject("BL_PAY_ENTERPRISE.QUERY_ENTER_ACCOUNT",obj);
	}
	
}
