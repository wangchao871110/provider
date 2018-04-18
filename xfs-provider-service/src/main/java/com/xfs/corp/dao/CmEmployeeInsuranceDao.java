package com.xfs.corp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.dto.EmplnsFundDetailDto;
import com.xfs.corp.model.CmEmployeeInsurance;

/**
 * CmEmployeeInsuranceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:26:34
 */
@Repository
public class CmEmployeeInsuranceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_INSURANCE.CountFindAllCM_EMPLOYEE_INSURANCE", null );
        return ret.intValue();
	}

	public BsRatioCalcInterface findByPK(BsRatioCalcInterface obj) {
		Object ret = queryForObject("CM_EMPLOYEE_INSURANCE.FindByPK", obj );
		if(ret !=null)
			return (BsRatioCalcInterface)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeInsurance> freeFind(BsRatioCalcInterface obj) {
		return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCE", obj );
	}

    @SuppressWarnings("unchecked")
    public List<CmEmployeeInsurance> freeFind(CmEmployeeInsurance obj) {
        return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCE", obj );
    }

	public int countFreeFind(BsRatioCalcInterface obj) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_INSURANCE.CountFreeFindCM_EMPLOYEE_INSURANCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeInsurance> freeFind(BsRatioCalcInterface obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeInsurance> freeFind(BsRatioCalcInterface obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeInsurance){
	    	_hashmap = ((CmEmployeeInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeInsurance> freeFind(BsRatioCalcInterface obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeInsurance){
	    	_hashmap = ((CmEmployeeInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmEmployeeInsurance> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmEmployeeInsurance> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsRatioCalcInterface oneObj = (BsRatioCalcInterface)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsRatioCalcInterface vo) {
        CmEmployeeInsurance obj = (CmEmployeeInsurance) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

    public int save(ContextInfo cti, CmEmployeeInsurance obj) {

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

	public int insert(ContextInfo cti, CmEmployeeInsurance obj) {

		obj.fixup();
        return insert(cti, "CM_EMPLOYEE_INSURANCE.InsertCM_EMPLOYEE_INSURANCE", obj );
	}

	public int update(ContextInfo cti, CmEmployeeInsurance obj) {

		obj.fixup();
		return update(cti, "CM_EMPLOYEE_INSURANCE.UpdateCM_EMPLOYEE_INSURANCE", obj );

	}

    public int updateCmEmployeeInsurance(ContextInfo cti, CmEmployeeInsurance obj) {

        obj.fixup();
        return update(cti, "CM_EMPLOYEE_INSURANCE.UpdateCM_EMPLOYEE_INSURANCE_INFO", obj );

    }

    public int deleteCmEmployeeInsurance(ContextInfo cti, CmEmployeeInsurance obj) {

        obj.fixup();
        return update(cti, "CM_EMPLOYEE_INSURANCE.DeleteCM_EMPLOYEE_INSURANCE_BY_empId", obj );

    }

	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmEmployeeInsurance obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_EMPLOYEE_INSURANCE.DeleteCM_EMPLOYEE_INSURANCE", obj );

	}

    public int removeEmpInsuranceByEmp(ContextInfo cti, CmEmployeeInsurance obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "CM_EMPLOYEE_INSURANCE.DeleteCM_EMPLOYEE_BY_EMPID", obj );

    }


	public int removeObjectTree(ContextInfo cti, CmEmployeeInsurance obj) {

        obj.fixup();
        BsRatioCalcInterface oldObj = ( BsRatioCalcInterface )queryForObject("CM_EMPLOYEE_INSURANCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CM_EMPLOYEE_INSURANCE.DeleteCM_EMPLOYEE_INSURANCE", oldObj );

	}

	public boolean exists(BsRatioCalcInterface vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmEmployeeInsurance obj = (CmEmployeeInsurance) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_EMPLOYEE_INSURANCE.CountCM_EMPLOYEE_INSURANCE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:34
    /**
     *  员工管理 > 员工列表 >缴费详情 >五险一金查询
     *  @param vo
     *  @return 
     *	@return 			: List<Map<String,Object>> 
     *  @createDate  	: 2016年11月25日 下午2:19:35
     *  @author         	: xiyanzhang 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年11月25日 下午2:19:35
     *  @updateAuthor  :
     */
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> findEmployeeInsuranceListMap(CmEmployeeInsurance vo,List<String> lists) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empId",vo.getEmpId());
        _hashmap.put("lists",lists);
        _hashmap.put("productName",vo.getProductName());
        return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCE_By_Emp", _hashmap);
    }
    /**
     * 查询产品名称
     */
    @SuppressWarnings("unchecked")
	public List<String> findProductNameList(Integer empId){
    	HashMap<String,Object> _hashmap = new HashMap<String,Object>();
    		_hashmap.put("empId",empId);
    		return queryForList("CM_EMPLOYEE_INSURANCE.findProductNameList", _hashmap);
    }
	/**
	 *  员工管理 > 员工列表 >缴费详情 >服务费查询
	 *  @param vo
	 *  @return
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2016年11月28日 下午2:41:06
	 *  @author         	: xiyanzhang 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月28日 下午2:41:06
	 *  @updateAuthor  :
	 */
    public List<Map<String, Object>> findEmployeeServiceChargeMap(CmEmployeeInsurance vo) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empId",vo.getEmpId());
        return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_SERVICE_CHARGE", _hashmap);
    }

    public List<Map<String, Object>> findEmployeeInsuranceMapByEmp(CmEmployeeInsurance vo, Integer areaId, String insuranceFundType) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empId", vo.getEmpId());
        _hashmap.put("areaId", areaId);
        _hashmap.put("insuranceFundType", insuranceFundType);
        return queryForList("CM_EMPLOYEE_INSURANCE.FreeFindCM_EMPLOYEE_INSURANCE_MAP_By_Emp", _hashmap);
    }


//	public int cancelEmployeeInsuranceListByEmpIdsAndAfterPeriod(ContextInfo cti, List<Integer> empIds, List<Integer> insuranceIds, String period) {
//	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
//        _hashmap.put("empIds", empIds );
//        _hashmap.put("period", period);
//        _hashmap.put("insuranceIds", insuranceIds);
//		return update(cti, "CM_EMPLOYEE_INSURANCE.cancelCM_EMPLOYEE_INSURANCE_by_EmpIds_and_afterPeriod", _hashmap);
//	}

	public int endEmployeeInsuranceListByEmpIdsAndPeriod(ContextInfo cti, List<Integer> empIds, List<Integer> insuranceIds, String period, Integer taskid) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empIds", empIds );
        _hashmap.put("period", period);
        _hashmap.put("taskid", taskid);
        _hashmap.put("insuranceIds", insuranceIds);
		return update(cti, "CM_EMPLOYEE_INSURANCE.endCM_EMPLOYEE_INSURANCE_by_EmpIds_and_Period", _hashmap);
	}
    /**
     * 减员将 开始时间大于减员时间的险种记录作废
     *  @param   cti, empIds, insuranceIds, period]
     * @return    : int
     *  @createDate   : 2016/12/14 15:23
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/14 15:23
     *  @updateAuthor  :
     */
    public int endEmployeeInsuranceListSetDr(ContextInfo cti, List<Integer> empIds, List<Integer> insuranceIds, String period,Integer endTaskId) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empIds", empIds );
        _hashmap.put("period", period);
        _hashmap.put("insuranceIds", insuranceIds);
        _hashmap.put("taskid",endTaskId);
        return update(cti, "CM_EMPLOYEE_INSURANCE.endCM_EMPLOYEE_INSURANCE_SET_DR", _hashmap);
    }

    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String period, List<Integer> insuranceIds) {
	    return findEmployeeInsuranceByEmpIdsAndPeriod(empIds,period,period,insuranceIds);
    }
    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String beginperiod, String endperiod, List<Integer> insuranceIds){
        return findEmployeeInsuranceByEmpIdsAndPeriod(empIds,beginperiod,endperiod,insuranceIds,"MONTH");
    }
    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String beginperiod, String endperiod, List<Integer> insuranceIds,String payType) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empIds", empIds);
        _hashmap.put("beginperiod", beginperiod);
        _hashmap.put("endperiod", endperiod);
        _hashmap.put("payType", payType);
        _hashmap.put("insuranceIds", insuranceIds!=null&&insuranceIds.size()>0?insuranceIds:null);
        return queryForList("CM_EMPLOYEE_INSURANCE.find_CM_EMPLIST_BY_EMPIDS_and_Period", _hashmap);
    }


 /*----------- cs 合并 start -----------*/


    public List<CmEmployeeInsurance> findEmployeeInsuranceByEmployee(List<Integer> empIds, List<Integer> insuranceIds) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empIds", empIds);
        _hashmap.put("insuranceIds", insuranceIds);
        return queryForList("CM_EMPLOYEE_INSURANCE.findEmployeeInsuranceByEmployee", _hashmap);
    }

    /**
     * 根据规则获取社保、公积金数据
     *  @param empId
     *  @param month
     *  @param insuranceFundType
     *  @return 
     *	@return 			: List<EmplnsFundDetailDto> 
     *  @createDate  	: 2017年5月23日 上午9:30:00
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年5月23日 上午9:30:00
     *  @updateAuthor  :
     */
	public List<EmplnsFundDetailDto> findEmpInsFundDetailByEmpid(Integer empId, String month, String insuranceFundType) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("empId", empId);
        _hashmap.put("month", month);
        _hashmap.put("insuranceFundType", insuranceFundType);
        return queryForList("CM_EMPLOYEE_INSURANCE.findEmpInsFundDetailByEmpid", _hashmap);
	}

    /**
     * 查询正在使用（未结束）的费用段
     */
    public List<CmEmployeeInsurance> findInsuranceByEmpId(CmEmployeeInsurance obj) {
        return queryForList("CM_EMPLOYEE_INSURANCE.findInsuranceByEmpId", obj );
    }

    /**
     * 修改结束时间
     * @param cti
     *  empId
     *  endPeriod
     *  insuranceFundType 社保或公积金，可不传，若不传则认为：社保及公积金
     * @return
     */
    public int updateEndPeriod(ContextInfo cti, CmEmployeeInsurance obj){
        return update(cti, "CM_EMPLOYEE_INSURANCE.updateEndPeriod", obj);
    }
}
