package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.bill.dto.EmpDetail;
import com.xfs.common.PageInfo;
import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsBillEmp;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;

/**
 * SpsBillEmpDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/16 15:32:07
 */
@Repository
public class SpsBillEmpDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_EMP.CountFindAllSPS_BILL_EMP", null );
        return ret.intValue();
	}

	public SpsBillEmp findByPK(SpsBillEmp obj) {
		Object ret = queryForObject("SPS_BILL_EMP.FindByPK", obj );
		if(ret !=null)
			return (SpsBillEmp)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillEmp> freeFind(SpsBillEmp obj) {
		return queryForList("SPS_BILL_EMP.FreeFindSPS_BILL_EMP", obj );
	}

	public int countFreeFind(SpsBillEmp obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_EMP.CountFreeFindSPS_BILL_EMP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillEmp> freeFind(SpsBillEmp obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_EMP.FreeFindSPS_BILL_EMP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillEmp> freeFind(SpsBillEmp obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillEmp){
	    	_hashmap = ((SpsBillEmp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_EMP.FreeFindSPS_BILL_EMPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillEmp> freeFind(SpsBillEmp obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillEmp){
	    	_hashmap = ((SpsBillEmp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_EMP.FreeFindSPS_BILL_EMPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillEmp> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillEmp> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillEmp oneObj = (SpsBillEmp)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillEmp vo) {
        SpsBillEmp obj = (SpsBillEmp) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsBillEmp obj) {

		obj.fixup();
        return insert(cti, "SPS_BILL_EMP.InsertSPS_BILL_EMP", obj );
	}

	public int update(ContextInfo cti, SpsBillEmp obj) {

		obj.fixup();
		return update(cti, "SPS_BILL_EMP.UpdateSPS_BILL_EMP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBillEmp obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_EMP.DeleteSPS_BILL_EMP", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillEmp obj) {

        obj.fixup();
        SpsBillEmp oldObj = ( SpsBillEmp )queryForObject("SPS_BILL_EMP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BILL_EMP.DeleteSPS_BILL_EMP", oldObj );

	}

	public boolean exists(SpsBillEmp vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillEmp obj = (SpsBillEmp) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_EMP.CountSPS_BILL_EMP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:07

	/**
	 * 根据企业Id和员工Id查询员工信息
	 * @param cmid
	 * @param emp_id
     * @return
     */
	public Map<String,Object> queryEmpBillDetail(Integer spid,Integer cmid, Integer emp_id,Integer bill_id,Integer id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		obj.put("emp_id",emp_id);
		obj.put("bill_id",bill_id);
		obj.put("id",id);
		return (Map<String, Object>)queryForObject("SPS_BILL_EMP.QUERY_CM_EM_BILL_DETAIL", obj );
	}

	/**  查询员工个数
	 *  @param    cpid, bill_id, period, searchWord]
	 *	@return 			: java.lang.Integer
	 *  @createDate  	: 2016-11-09 10:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 10:38
	 *  @updateAuthor  :
	 */
	public Integer queryEmpBillCount(Integer spid,Integer cpid,Integer bill_id,String period,String searchWord,Integer area_id,Integer scheme_id){

		Map<String,Object> obj = new HashMap<String,Object>();
		if(null != spid)
			obj.put("spid",spid);
		if(null != cpid)
			obj.put("cpid",cpid);
		if(null != bill_id)
			obj.put("bill_id",bill_id);
		if(!StringUtils.isBlank(period))
			obj.put("period",period);
		if(!StringUtils.isBlank(searchWord))
			obj.put("searchWord",searchWord);
		if(null != area_id)
			obj.put("area_id",area_id);
		if(null != scheme_id)
			obj.put("scheme_id",scheme_id);
		Integer ret = (Integer)queryForObject("SPS_BILL_EMP.QUERY_BILL_EMP_COUNT", obj );
		return ret;
	}

	/**
	 * 查询员工列表--分页
	 * @param pagerIndex
	 * @param pagerSize
     * @return
     */
	public List<Map<String,Object>> queryEmpBillList(Integer spid,Integer cpid,Integer bill_id,String period,String searchWord,Integer area_id,Integer scheme_id,Integer pagerIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		if(null != spid)
			obj.put("spid",spid);
		if(null != cpid)
			obj.put("cpid",cpid);
		if(null != bill_id)
			obj.put("bill_id",bill_id);
		if(!StringUtils.isBlank(period))
			obj.put("period",period);
		if(!StringUtils.isBlank(searchWord))
			obj.put("searchWord",searchWord);
		if(null != area_id)
			obj.put("area_id",area_id);
		if(null != scheme_id)
			obj.put("scheme_id",scheme_id);
		obj.put("pagerIndex",pagerIndex);
		obj.put("pagerSize",pagerSize);
		return getPaginatedList("SPS_BILL_EMP.QUERY_BILL_EMP_LIST", obj,pagerIndex,pagerSize);
	}

	/**
	 * 查询所有员工列表--不分页
	 * @param spid
	 * @param cmid
	 * @return
	 */
	public List<Map<String,Object>> queryAllEmpBillList(Integer spid,Integer cmid,Integer bill_id,String depute_type,String bill_type){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cpid",cmid);
		obj.put("bill_id",bill_id);
		obj.put("depute_type",depute_type);
		obj.put("bill_type",bill_type);
		return queryForList("SPS_BILL_EMP.QUERY_BILL_EMP_LIST", obj);
	}

	/**
	 *  获取员工费用列表
	 *  @param   cti, period, searchWord, area_id
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-04-17 14:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-04-17 14:21
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> queryAllEmpListByInsDetail(ContextInfo cti,String period,String searchWord,String area_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("area_id",area_id);
		obj.put("cpid",cti.getOrgId());
		obj.put("searchWord",searchWord);
		obj.put("period",period);
		obj.put("authority",cti.getAuthority());
		return queryForList("SPS_BILL_EMP.QUERY_BILL_EMP_LIST_BY_INSDETAIL", obj);
	}


	/**
	 * 实缴账单员工总数
	 *
	 * @param cpId
	 * @param billId
	 * @param searchWord
	 * @return
	 */
	public int findPaidBillEmpCount(Integer cpId, Integer billId, String searchWord) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (cpId != null) {
			_hashmap.put("cpId", cpId);
		}
		if (billId != null) {
			_hashmap.put("billId", billId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		return (Integer) queryForObject("SPS_BILL_EMP.FIND_PAIDBILL_EMP_COUNT", _hashmap);
	}

	/**
	 * 实缴账单员工列表
	 *
	 * @param cpId
	 * @param billId
	 * @param searchWord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findPaidBillEmps(Integer cpId, Integer billId, String searchWord, Integer pageIndex, Integer pageSize) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (cpId != null) {
			_hashmap.put("cpId", cpId);
		}
		if (billId != null) {
			_hashmap.put("billId", billId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		return getPaginatedList("SPS_BILL_EMP.FIND_PAIDBILL_EMP_LIST", _hashmap, pageIndex, pageSize);
	}

	public int findPaidBillMarginEmpCount(Integer spId,Integer cpId, Integer billId,Integer prepay_bill_id, String searchWord) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (spId != null) {
			_hashmap.put("spId", spId);
		}
		if (cpId != null) {
			_hashmap.put("cpId", cpId);
		}
		if (billId != null) {
			_hashmap.put("bill_id", billId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		if(prepay_bill_id != null){
			_hashmap.put("prepay_bill_id", prepay_bill_id);
		}
		return (Integer) queryForObject("SPS_BILL_EMP.queryMarginDetailListCount", _hashmap);
	}

	/**
	 * 实缴账单差额的员工列表
	 *
	 * @param cpId
	 * @param billId
	 * @param searchWord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findPaidBillMarginEmps(Integer spId,Integer cpId, Integer billId,Integer prepay_bill_id, String searchWord, Integer pageIndex, Integer pageSize) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (spId != null) {
			_hashmap.put("spId", spId);
		}
		if (cpId != null) {
			_hashmap.put("cpId", cpId);
		}
		if (billId != null) {
			_hashmap.put("bill_id", billId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		if(prepay_bill_id != null){
			_hashmap.put("prepay_bill_id", prepay_bill_id);
		}
		return getPaginatedList("SPS_BILL_EMP.queryMarginDetailList", _hashmap, pageIndex, pageSize);
	}

	/**
	 * 删除员工的参保信息
	 * @param bill_id
	 * @param emp_id
	 * @return
	 */
	public int delEmpBillBybillId(ContextInfo cti, Integer bill_id,Integer emp_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_id",bill_id);
		obj.put("emp_id",emp_id);
		return delete(cti, "SPS_BILL_EMP.DEL_EMP_BILL_BY_BILLID", obj);
	}


	public List<SpsBillEmp> findBillEmpWithDetails(Integer billId) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billId",billId);
		return queryForList("SPS_BILL_EMP.find_CM_EMPLIST_WithDetail_BY_EMPIDS", obj);
	}


	/**
	 *  企业HR查看账单列表
	 *  @param   billId
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsBillEmp>
	 *  @createDate  	: 2017-03-07 15:10
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-07 15:10
	 *  @updateAuthor  :
	 */
	public Integer findBillHREmpWithDetailsCOUNT(Integer cpId, Integer billId, String searchWords){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billId",billId);
		obj.put("cpId",cpId);
		obj.put("searchWords",searchWords);
		return (Integer) queryForObject("SPS_BILL_EMP.find_CM_EMPLIST_WithDetail_BY_HREMPS_COUNT", obj);
	}

	/**
	 *  企业HR查看账单列表
	 *  @param   billId
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsBillEmp>
	 *  @createDate  	: 2017-03-07 15:10
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-07 15:10
	 *  @updateAuthor  :
	 */
	public List<EmpDetail> findBillHREmpWithDetails(Integer cpId, Integer billId,List<Integer> empIds) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billId",billId);
		obj.put("cpId",cpId);
		obj.put("empIds",empIds);
		return queryForList("SPS_BILL_EMP.find_CM_EMPLIST_WithDetail_BY_HREMPS", obj);
	}

	public List<Integer> findBillHREmpWithEmpIds(Integer cpId, Integer billId, String searchWords, Integer pagerIndex,Integer pagerSize) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billId",billId);
		obj.put("cpId",cpId);
		obj.put("searchWords",searchWords);
		return getPaginatedList("SPS_BILL_EMP.find_CM_EMPLIST_WithDetail_BY_HREMPIDS", obj,pagerIndex,pagerSize);
	}

	/**
	 *  获取账单详情最多社保集合
	 *  @param   billId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-07 15:35
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-07 15:35
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findBillDetailIns(Integer billId){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billId",billId);
		return queryForList("SPS_BILL_EMP.find_CM_EMPLIST_WithDetail_INSNAME", obj);
	}
}
