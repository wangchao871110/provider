package com.xfs.settlement.dao;
import java.util.*;

import com.xfs.common.ContextInfo;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.model.BlPayBusiness;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BlPayBusinessDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/16 10:28:38
 */
@Repository
public class BlPayBusinessDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_BUSINESS.CountFindAllBL_PAY_BUSINESS", null );
        return ret.intValue();
	}

	public BlPayBusiness findByPK(BlPayBusiness obj) {
		Object ret = queryForObject("BL_PAY_BUSINESS.FindByPK", obj );
		if(ret !=null)
			return (BlPayBusiness)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusiness> freeFind(BlPayBusiness obj) {
		return queryForList("BL_PAY_BUSINESS.FreeFindBL_PAY_BUSINESS", obj );
	}

	public int countFreeFind(BlPayBusiness obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_BUSINESS.CountFreeFindBL_PAY_BUSINESS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusiness> freeFind(BlPayBusiness obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_BUSINESS.FreeFindBL_PAY_BUSINESS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusiness> freeFind(BlPayBusiness obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayBusiness){
	    	_hashmap = ((BlPayBusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_BUSINESS.FreeFindBL_PAY_BUSINESSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayBusiness> freeFind(BlPayBusiness obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayBusiness){
	    	_hashmap = ((BlPayBusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_BUSINESS.FreeFindBL_PAY_BUSINESSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayBusiness> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayBusiness> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayBusiness oneObj = (BlPayBusiness)it.next();
				i += save(  cti,oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,BlPayBusiness vo) {
        BlPayBusiness obj = (BlPayBusiness) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,BlPayBusiness obj) {

		obj.fixup();
        return insert( cti,"BL_PAY_BUSINESS.InsertBL_PAY_BUSINESS", obj );
	}

	public int update(ContextInfo cti,BlPayBusiness obj) {

		obj.fixup();
		return update(  cti,"BL_PAY_BUSINESS.UpdateBL_PAY_BUSINESS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,BlPayBusiness obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(  cti,"BL_PAY_BUSINESS.DeleteBL_PAY_BUSINESS", obj );

	}

	public int removeObjectTree(ContextInfo cti,BlPayBusiness obj) {

        obj.fixup();
        BlPayBusiness oldObj = ( BlPayBusiness )queryForObject("BL_PAY_BUSINESS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti, "BL_PAY_BUSINESS.DeleteBL_PAY_BUSINESS", oldObj );

	}

	public boolean exists(BlPayBusiness vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayBusiness obj = (BlPayBusiness) vo;

        keysFilled = keysFilled && ( null != obj.getBusId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_BUSINESS.CountBL_PAY_BUSINESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:38


	/**
	 *  根据应用appId 和 payId 获取业务信息
	 *  @param   basePay
	 *	@return 			: BlPayBusiness
	 *  @createDate  	: 2016-11-18 10:24
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-18 10:24
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppIdAndPayID(BasePay basePay){
		return (RespXfsPay)queryForObject("BL_PAY_BUSINESS.QUERY_BUSIN_PAY_BY_APPID_AND_PAYID",basePay);
	}

	/**
	 *  根据应用appId 获取业务信息
	 *  @param   basePay
	 *	@return 			: BlPayBusiness
	 *  @createDate  	: 2016-11-18 10:24
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-18 10:24
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppId(BasePay basePay){
		return (RespXfsPay)queryForObject("BL_PAY_BUSINESS.QUERY_BUSIN_PAY_BY_APPID",basePay);
	}


	/**
	 *  根据应用名称 获取业务信息
	 *  @param   appName
	 *	@return 			: com.xfs.settlement.dto.RespXfsPay
	 *  @createDate  	: 2016-11-26 13:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-26 13:21
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppName(String appName){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("appName",appName);
		return (RespXfsPay)queryForObject("BL_PAY_BUSINESS.QUERY_BUSIN_PAY_BY_APPNAME",obj);
	}

	/**
	 *  根据业务ID获取业务信息
	 *  @param   busId
	 *	@return 			: com.xfs.settlement.model.BlPayBusiness
	 *  @createDate  	: 2016-11-26 18:31
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-26 18:31
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinByBusId(Integer busId){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("busId",busId);
		return (RespXfsPay)queryForObject("BL_PAY_BUSINESS.QUERY_BUSIN_PAY_BY_BUSID",obj);
	}
}
