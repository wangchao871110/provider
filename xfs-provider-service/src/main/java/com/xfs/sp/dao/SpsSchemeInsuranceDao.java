package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsSchemeInsurance;


/**
 * 方案险种Dao类
 * @author 	: yangfw@xinfushe.com
 * @date 	: 2016-11-11 11:09
 *
 * @version 	: V1.0
 */
@Repository
public class SpsSchemeInsuranceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_INSURANCE.CountFindAllSPS_SCHEME_INSURANCE", null );
        return ret.intValue();
	}

	public SpsSchemeInsurance findByPK(SpsSchemeInsurance obj) {
		Object ret = queryForObject("SPS_SCHEME_INSURANCE.FindByPK", obj );
		if(ret !=null)
			return (SpsSchemeInsurance)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeInsurance> freeFind(SpsSchemeInsurance obj) {
		return queryForList("SPS_SCHEME_INSURANCE.FreeFindSPS_SCHEME_INSURANCE", obj );
	}

	public int countFreeFind(SpsSchemeInsurance obj) {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_INSURANCE.CountFreeFindSPS_SCHEME_INSURANCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeInsurance> freeFind(SpsSchemeInsurance obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SCHEME_INSURANCE.FreeFindSPS_SCHEME_INSURANCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeInsurance> freeFind(SpsSchemeInsurance obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeInsurance){
	    	_hashmap = ((SpsSchemeInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SCHEME_INSURANCE.FreeFindSPS_SCHEME_INSURANCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeInsurance> freeFind(SpsSchemeInsurance obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeInsurance){
	    	_hashmap = ((SpsSchemeInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SCHEME_INSURANCE.FreeFindSPS_SCHEME_INSURANCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsSchemeInsurance> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsSchemeInsurance> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsSchemeInsurance oneObj = (SpsSchemeInsurance)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsSchemeInsurance vo) {
        SpsSchemeInsurance obj = (SpsSchemeInsurance) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsSchemeInsurance obj) {

		obj.fixup();
        return insert(cti, "SPS_SCHEME_INSURANCE.InsertSPS_SCHEME_INSURANCE", obj );
	}

	public int update(ContextInfo cti, SpsSchemeInsurance obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME_INSURANCE.UpdateSPS_SCHEME_INSURANCE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsSchemeInsurance obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SCHEME_INSURANCE.DeleteSPS_SCHEME_INSURANCE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsSchemeInsurance obj) {

        obj.fixup();
        SpsSchemeInsurance oldObj = ( SpsSchemeInsurance )queryForObject("SPS_SCHEME_INSURANCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SCHEME_INSURANCE.DeleteSPS_SCHEME_INSURANCE", oldObj );

	}

	public boolean exists(SpsSchemeInsurance vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsSchemeInsurance obj = (SpsSchemeInsurance) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SCHEME_INSURANCE.CountSPS_SCHEME_INSURANCE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:36
	/**
	 * 根据方案id 查询 险种信息
	 *
	 * @author lifq
	 *
	 * 2016年8月8日  下午7:51:29
	 */
	public List<Map<String,Object>> findInsurancesBySchemeId(Integer scheme_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("scheme_id",scheme_id);
        return queryForList("SPS_SCHEME_INSURANCE.findInsurancesBySchemeId", _hashMap);
	}

    /**
     * 根据方案id、区域 查询 险种信息
     *
     * @author lifq
     *
     * 2016年8月9日  下午1:51:09
     */
    public List<Map<String,Object>> findSchemeInsurancesBySchemeIdAreaId(Integer scheme_id,String insurance_fund_type){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("scheme_id",scheme_id);
        _hashMap.put("insurance_fund_type",insurance_fund_type);
        return queryForList("SPS_SCHEME_INSURANCE.findSchemeInsurancesBySchemeIdAreaId", _hashMap);
    }


    public List<Map<String,Object>> findSchemeInsuranceBySchemeIdAndAccId(Integer accountId,String livingType,Integer schemceId){
		HashMap<String,Object> _hashMap = new HashMap<String,Object>();
		_hashMap.put("accountId",accountId);
		_hashMap.put("livingType",livingType);
		_hashMap.put("schemceId",schemceId);
		return queryForList("SPS_SCHEME_INSURANCE.findSchemeInsuranceBySchemeIdAndAccId", _hashMap);


	}
	
}
