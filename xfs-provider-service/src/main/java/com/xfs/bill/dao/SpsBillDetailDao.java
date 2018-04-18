package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsBillDetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * 账单详情DAO
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-08 18:15
 * @version 	: V1.0
 */
@Repository
public class SpsBillDetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_DETAIL.CountFindAllSPS_BILL_DETAIL", null );
        return ret.intValue();
	}

	public SpsBillDetail findByPK(SpsBillDetail obj) {
		Object ret = queryForObject("SPS_BILL_DETAIL.FindByPK", obj );
		if(ret !=null)
			return (SpsBillDetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillDetail> freeFind(SpsBillDetail obj) {
		return queryForList("SPS_BILL_DETAIL.FreeFindSPS_BILL_DETAIL", obj );
	}

	public int countFreeFind(SpsBillDetail obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_DETAIL.CountFreeFindSPS_BILL_DETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillDetail> freeFind(SpsBillDetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_DETAIL.FreeFindSPS_BILL_DETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillDetail> freeFind(SpsBillDetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillDetail){
	    	_hashmap = ((SpsBillDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_DETAIL.FreeFindSPS_BILL_DETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillDetail> freeFind(SpsBillDetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillDetail){
	    	_hashmap = ((SpsBillDetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_DETAIL.FreeFindSPS_BILL_DETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillDetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillDetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillDetail oneObj = (SpsBillDetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillDetail vo) {
        SpsBillDetail obj = (SpsBillDetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsBillDetail obj) {

		obj.fixup();
        return insert(cti, "SPS_BILL_DETAIL.InsertSPS_BILL_DETAIL", obj );
	}

	public int update(ContextInfo cti, SpsBillDetail obj) {

		obj.fixup();
		return update(cti, "SPS_BILL_DETAIL.UpdateSPS_BILL_DETAIL", obj );

	}

	public int remove(ContextInfo cti, SpsBillDetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_DETAIL.DeleteSPS_BILL_DETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillDetail obj) {

        obj.fixup();
        SpsBillDetail oldObj = ( SpsBillDetail )queryForObject("SPS_BILL_DETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BILL_DETAIL.DeleteSPS_BILL_DETAIL", oldObj );

	}

	public boolean exists(SpsBillDetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillDetail obj = (SpsBillDetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_DETAIL.CountSPS_BILL_DETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:02

	/**
	 * 获取员工的社保信息列表
	 *  @param   bill_emp_id, in_type, id
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-08 18:15
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-08 18:15
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryEmpDetail(Integer bill_emp_id,String in_type,Integer id,String insType,String period){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bill_emp_id",bill_emp_id);
		params.put("in_type",in_type);
		params.put("insType",insType);
		params.put("id",id);
		params.put("period",period);
		return queryForList("SPS_BILL_DETAIL.QUERY_EM_BILL_DETAIL_LIST",params);
	}

	/**
	 * 获取账单差额详情
	 *  @param   cpId, billId, empId, spId, insuredMonth, payType
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-08 18:14
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-08 18:14
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findBillMarginDetail(Integer cpId, Integer billId, Integer empId,Integer spId,String insuredMonth,String payType) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (cpId != null) {
			_hashmap.put("cpId", cpId);
		}
		if (billId != null) {
			_hashmap.put("billId", billId);
		}
		if (empId != null) {
			_hashmap.put("empId", empId);
		}
		if (spId != null) {
			_hashmap.put("spId", spId);
		}
		if (insuredMonth != null) {
			_hashmap.put("insuredMonth", insuredMonth);
		}
		if (payType != null) {
			_hashmap.put("payType", payType);
		}
		return queryForList("SPS_BILL_DETAIL.FIND_BILL_MARGIN_DETAIL", _hashmap);
	}

}
