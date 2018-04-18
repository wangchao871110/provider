package com.xfs.business.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BdInsurancearea;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdInsuranceareaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/16 16:36:51
 */
@Repository
public class BdInsuranceareaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_INSURANCEAREA.CountFindAllBD_INSURANCEAREA", null );
        return ret.intValue();
	}

	public BdInsurancearea findByPK(BdInsurancearea obj) {
		Object ret = queryForObject("BD_INSURANCEAREA.FindByPK", obj );
		if(ret !=null)
			return (BdInsurancearea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurancearea> freeFind(BdInsurancearea obj) {
		return queryForList("BD_INSURANCEAREA.FreeFindBD_INSURANCEAREA", obj );
	}

	public int countFreeFind(BdInsurancearea obj) {
        Integer ret = (Integer) queryForObject("BD_INSURANCEAREA.CountFreeFindBD_INSURANCEAREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurancearea> freeFind(BdInsurancearea obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_INSURANCEAREA.FreeFindBD_INSURANCEAREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurancearea> freeFind(BdInsurancearea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdInsurancearea){
	    	_hashmap = ((BdInsurancearea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_INSURANCEAREA.FreeFindBD_INSURANCEAREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurancearea> freeFind(BdInsurancearea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdInsurancearea){
	    	_hashmap = ((BdInsurancearea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_INSURANCEAREA.FreeFindBD_INSURANCEAREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdInsurancearea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdInsurancearea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdInsurancearea oneObj = (BdInsurancearea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdInsurancearea vo) {
        BdInsurancearea obj = (BdInsurancearea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdInsurancearea obj) {

		obj.fixup();
        return insert(cti, "BD_INSURANCEAREA.InsertBD_INSURANCEAREA", obj );
	}

	public int update(ContextInfo cti, BdInsurancearea obj) {

		obj.fixup();
		return update(cti, "BD_INSURANCEAREA.UpdateBD_INSURANCEAREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdInsurancearea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_INSURANCEAREA.DeleteBD_INSURANCEAREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdInsurancearea obj) {

        obj.fixup();
        BdInsurancearea oldObj = ( BdInsurancearea )queryForObject("BD_INSURANCEAREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_INSURANCEAREA.DeleteBD_INSURANCEAREA", oldObj );

	}

	public boolean exists(BdInsurancearea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdInsurancearea obj = (BdInsurancearea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_INSURANCEAREA.CountBD_INSURANCEAREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 16:36:51


	/**
	 * 获取地区的
	 * @param spid
	 * @param cmid
	 * @return
	 */
	public List<Map<String,Object>> queryEmInsuranceAreaList(Integer spid, Integer cmid,String in_type,Integer bill_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		obj.put("in_type",in_type);
		obj.put("bill_id",bill_id);
		List<Map<String,Object>> areaInsuranceList = queryForList("BD_INSURANCEAREA.QUERY_EM_INSURANCE_AREA_LIST",obj);
		List<Map<String,Object>> removeInsurance = new ArrayList<>();
		if(null != areaInsuranceList && !areaInsuranceList.isEmpty()){
			int sum = 0;
			for (Map<String,Object> insurance : areaInsuranceList){
				if(null != insurance.get("childCode") && "apaybase".equals(insurance.get("childCode"))){
					removeInsurance.add(insurance);
					sum = sum > 0 ? sum : 1;
				}
				if(null != insurance.get("childCode") && "corp_paybase".equals(insurance.get("childCode"))){
					sum++;
				}
			}
			if(!removeInsurance.isEmpty() && sum > 2){
				for(Map<String,Object> insurance : removeInsurance){
					areaInsuranceList.remove(insurance);
				}
			}
		}
		return areaInsuranceList;
	}

	/**
	 * 获取账单模版单列字段
	 * @param spid
	 * @param cmid
     * @return
     */
	public List<Map<String,String>> queryEmInsuranceBillTemplateList(Integer spid, Integer cmid){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		return queryForList("BD_INSURANCEAREA.QUERY_EM_INSURANCE_SPSBILL_TEMPLATE_LIST",obj);
	}


	/**
	 * 预览地区模版
	 * @return
	 */
	public List<Map<String,Object>> perviewEmInsuranceAreaList(List<Integer> fieldIds){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("filedIds",fieldIds);
		return queryForList("BD_INSURANCEAREA.PERVIEW_EM_INSURANCE_AREA_LIST",obj);
	}

	/**
	 * 获取账单模版单列字段
	 * @param fieldIds
	 * @return
	 */
	public List<Map<String,String>> perviewEmInsuranceBillTemplateList(List<Integer> fieldIds){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("filedIds",fieldIds);
		return queryForList("BD_INSURANCEAREA.PERVIEW_EM_INSURANCE_SPSBILL_TEMPLATE_LIST",obj);
	}



	public List<BdInsurancearea> findBdInsuranceareaByAreaid(Integer areaid){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("areaId",areaid);
		return (List<BdInsurancearea>) queryForList("BD_INSURANCEAREA.FindBD_INSURANCEAREAbyAreaid",obj);
	}
	/**
	 * 根据areaid 查询 险种信息
	 *
	 * @author lifq
	 *
	 * 2016年8月8日  下午8:17:32
	 */
	public List<Map<String,Object>> findInsurancesByAreaId(Integer area_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("area_id",area_id);
        return queryForList("BD_INSURANCEAREA.findInsurancesByAreaId", _hashMap);
	}

	/**
	 * 获取地区的
	 * @param cmid
	 * @return
	 */
	public List<Map<String,Object>> queryEmInsuranceAreaList(Integer cmid,String in_type){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmid",cmid);
		obj.put("in_type",in_type);
		return queryForList("BD_INSURANCEAREA.QUERY_EM_INSURANCE_AREA_LIST_CS",obj);
	}

	public List<BdInsurancearea> findBdInsuranceareaByAreaidAndInsuranceid(Integer areaid){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("areaId",areaid);
		return (List<BdInsurancearea>) queryForList("BD_INSURANCEAREA.FindBD_INSURANCEAREAbyAreaidAndInsuranceTypeID",obj);
	}

	/**
	 * 根据地区 与类型 查询险种
	 * @param area_id
	 * @param insuFundType
	 * @return
	 */
	public List<Map<String,Object>> findInsurancesByAreaIdAndType(Integer area_id,String insuFundType){
		HashMap<String,Object> _hashMap = new HashMap<String,Object>();
		_hashMap.put("area_id",area_id);
		_hashMap.put("insuFundType",insuFundType);
		return queryForList("BD_INSURANCEAREA.findInsurancesByAreaId", _hashMap);
	}

	/**
	 * 获取参保信息---Excel动态标题头
	 *  @param orgId
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年4月13日 下午2:58:22
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月13日 下午2:58:22
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> fundInsFundByCpId(Integer orgId,String insuFundType) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpId",orgId);
		obj.put("insuFundType",insuFundType);
		return queryForList("BD_INSURANCEAREA.fundInsFundByCpIdSpId",obj);
	}
	/**
	 * 根据 cpId和spId查询 
	 *
	 * @author lifq
	 *
	 * 2017年8月3日  下午4:01:42
	 */
	public List<Map<String, Object>> fundInsFundByCpIdSpId(Integer cpId,Integer spId,String insuFundType) {
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spId",spId);
		obj.put("cpId",cpId);
		obj.put("insuFundType",insuFundType);
		return queryForList("BD_INSURANCEAREA.fundInsFundByCpIdSpId",obj);
	}

}
