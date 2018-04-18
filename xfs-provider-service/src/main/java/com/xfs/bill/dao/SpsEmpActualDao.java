package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsEmpActual;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.Constant;
import com.xfs.corp.model.CmEmployee;
import com.xfs.sp.dto.ActualInfo;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysFunctionDataService;

/**
 * SpsEmpActualDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 11:05:28
 */
@Repository
public class SpsEmpActualDao extends BaseSqlMapDao {
	
	@Autowired
    private SysFunctionDataService sysFunctionDataService;
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_EMP_ACTUAL.CountFindAllSPS_EMP_ACTUAL", null );
        return ret.intValue();
	}

	public SpsEmpActual findByPK(SpsEmpActual obj) {
		Object ret = queryForObject("SPS_EMP_ACTUAL.FindByPK", obj );
		if(ret !=null)
			return (SpsEmpActual)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActual> freeFind(SpsEmpActual obj) {
		return queryForList("SPS_EMP_ACTUAL.FreeFindSPS_EMP_ACTUAL", obj );
	}

	public int countFreeFind(SpsEmpActual obj) {
        Integer ret = (Integer) queryForObject("SPS_EMP_ACTUAL.CountFreeFindSPS_EMP_ACTUAL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActual> freeFind(SpsEmpActual obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EMP_ACTUAL.FreeFindSPS_EMP_ACTUAL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActual> freeFind(SpsEmpActual obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsEmpActual){
	    	_hashmap = ((SpsEmpActual)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_EMP_ACTUAL.FreeFindSPS_EMP_ACTUALOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsEmpActual> freeFind(SpsEmpActual obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsEmpActual){
	    	_hashmap = ((SpsEmpActual)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_EMP_ACTUAL.FreeFindSPS_EMP_ACTUALOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsEmpActual> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsEmpActual> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsEmpActual oneObj = (SpsEmpActual)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsEmpActual vo) {
        SpsEmpActual obj = (SpsEmpActual) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsEmpActual obj) {

		obj.fixup();
        return insert(cti, "SPS_EMP_ACTUAL.InsertSPS_EMP_ACTUAL", obj );
	}

	public int update(ContextInfo cti, SpsEmpActual obj) {

		obj.fixup();
		return update(cti, "SPS_EMP_ACTUAL.UpdateSPS_EMP_ACTUAL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsEmpActual obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_EMP_ACTUAL.DeleteSPS_EMP_ACTUAL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsEmpActual obj) {

        obj.fixup();
        SpsEmpActual oldObj = ( SpsEmpActual )queryForObject("SPS_EMP_ACTUAL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_EMP_ACTUAL.DeleteSPS_EMP_ACTUAL", oldObj );

	}

	public boolean exists(SpsEmpActual vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsEmpActual obj = (SpsEmpActual) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_EMP_ACTUAL.CountSPS_EMP_ACTUAL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:28
	
	public List<SpsEmpActual> findSpsEmpActualWithDetail(Integer insurance_accid, Integer fund_accid, String period, String payType, List<Integer> empIds, String insuranceOrFund) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    _hashmap.put("insurance_accid", insurance_accid);
	    _hashmap.put("fund_accid", fund_accid);
        _hashmap.put("period", period );
        _hashmap.put("payType", payType);
        _hashmap.put("empIds", empIds);
        _hashmap.put("insuranceOrFund", insuranceOrFund);
		return queryForList("SPS_EMP_ACTUAL.findSPS_EMP_ACTUAL_with_Detail", _hashmap);
	}
	
	//查保险和个人信息
    public List<ActualInfo> findActualEmployee(CmEmployee obj, ContextInfo cti, int pageIndex, int pageSize) {
    	// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.setAuthority(authority);		
        return getPaginatedList("SPS_EMP_ACTUAL.FindActual_EMPLOYEE", obj, pageIndex, pageSize);
   
    }

    public int countFreeFind(CmEmployee obj,ContextInfo cti) {
    	// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.setAuthority(authority);
        Integer ret = (Integer) queryForObject("SPS_EMP_ACTUAL.FindByEmpIdCount", obj);
        return ret.intValue();
    }
    //查社保的社保库类型和缴费地
    public List<ActualInfo> FindActualInsurance(CmEmployee obj) {
    	return queryForList("SPS_EMP_ACTUAL.FindActual_Insurance",obj);
    }
  //公积金的社保库类型和缴费地、
    public List<ActualInfo> FindActualFund(CmEmployee obj) {
    	return queryForList("SPS_EMP_ACTUAL.FindActual_Fund",obj);
    }
    public List<ActualInfo> FindActualSum(CmEmployee obj){
    	
		return queryForList("SPS_EMP_ACTUAL.FindActual_sum",obj);
    }
    
    public int countByAccIdAndPeriod(String insuranceOrFund, Integer insurance_accid, Integer fund_accid, String period, String payType) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("period", period );
        _hashmap.put("payType", payType);
        _hashmap.put("insuranceOrFund", insuranceOrFund);
	    _hashmap.put("insurance_accid", insurance_accid);
	    _hashmap.put("fund_accid", fund_accid);
         Integer ret = (Integer) queryForObject("SPS_EMP_ACTUAL.CountByAccIdAndPeriod", _hashmap);
        return ret.intValue();
        
    }
	
}
