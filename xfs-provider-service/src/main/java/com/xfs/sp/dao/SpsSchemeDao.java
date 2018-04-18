package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsScheme;


/**
 * 方案Dao类
 * @author 	: yangfw@xinfushe.com
 * @date 	: 2016-11-11 11:09
 * 
 * @version 	: V1.0
 */
@Repository
public class SpsSchemeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SCHEME.CountFindAllSPS_SCHEME", null );
        return ret.intValue();
	}

	public SpsScheme findByPK(SpsScheme obj) {
		Object ret = queryForObject("SPS_SCHEME.FindByPK", obj );
		if(ret !=null)
			return (SpsScheme)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsScheme> freeFind(SpsScheme obj) {
		return queryForList("SPS_SCHEME.FreeFindSPS_SCHEME", obj );
	}

	public int countFreeFind(SpsScheme obj) {
        Integer ret = (Integer) queryForObject("SPS_SCHEME.CountFreeFindSPS_SCHEME", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsScheme> freeFind(SpsScheme obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SCHEME.FreeFindSPS_SCHEME", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsScheme> freeFind(SpsScheme obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsScheme){
	    	_hashmap = ((SpsScheme)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SCHEME.FreeFindSPS_SCHEMEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsScheme> freeFind(SpsScheme obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsScheme){
	    	_hashmap = ((SpsScheme)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SCHEME.FreeFindSPS_SCHEMEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsScheme> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsScheme> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsScheme oneObj = (SpsScheme)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsScheme vo) {
        SpsScheme obj = (SpsScheme) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsScheme obj) {

		obj.fixup();
        return insert(cti, "SPS_SCHEME.InsertSPS_SCHEME", obj );
	}

	public int update(ContextInfo cti, SpsScheme obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME.UpdateSPS_SCHEME", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsScheme obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SCHEME.DeleteSPS_SCHEME", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsScheme obj) {

        obj.fixup();
        SpsScheme oldObj = ( SpsScheme )queryForObject("SPS_SCHEME.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SCHEME.DeleteSPS_SCHEME", oldObj );

	}

	public boolean exists(SpsScheme vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsScheme obj = (SpsScheme) vo;

        keysFilled = keysFilled && ( null != obj.getSchemeId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SCHEME.CountSPS_SCHEME", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:35
	/**
	 * 查询 方案
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午11:42:41
	 */
	public List<Map<String,Object>> findSchemes(Integer sp_id, Integer cp_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("sp_id",sp_id);
        _hashMap.put("cp_id",cp_id);
        return queryForList("SPS_SCHEME.findSchemes", _hashMap);
	}

    public List<Map<String,Object>> findSchemesState(Integer sp_id, Integer cp_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("sp_id",sp_id);
        _hashMap.put("cp_id",cp_id);
        return queryForList("SPS_SCHEME.findSchemes_State", _hashMap);
    }
	
	public List<SpsScheme> findSchemesByIds(Integer sp_id, List<Integer> ids) {
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("sp_id",sp_id);
        _hashMap.put("ids", ids);
        return queryForList("SPS_SCHEME.findSchemesByIds", _hashMap);
	}

	/**
	 * 查询账单日是当日的方案列表
	 * @return
	 */
	public List<SpsScheme> queryAllCorpsByBillDay(String billState){
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("billState",billState);
		return queryForList("SPS_SCHEME.QUERY_ALL_CROPS_BY_BILL_DAY", _hashmap);
	}

	/**
	 * 根据spId查询所有方案所属城市
	 *
	 * @author wuzhe
	 */
	public List<Map<String, Object>> findAllSchemeArea(Map<String,Object> paraMap){
		return queryForList("SPS_SCHEME.findAllSchemeArea", paraMap);
	}


	/**
	 * 通过类型in查询
	 * @param param
	 * @return
	 */
	public List<SpsScheme> freeFindInType(Map<String,Object> param) {

		return queryForList("SPS_SCHEME.FreeFindSPS_SCHEME_IN_TYPE", param);
	}
	/**
	 * 通过spID和CPID删除方案
	 * @param
	 * @return
	 */
	public Integer removeBySpIdAndCpId(ContextInfo cti, SpsScheme obj) {

		return update(cti,"SPS_SCHEME.deleteSchemeBySpIdAndCpId", obj);
	}

    public List<SpsScheme> freeFindInsurance(SpsScheme obj) {
        return queryForList("SPS_SCHEME.FreeFindSPS_SCHEME_BY_INSURANCE_FUND_TYPE", obj );
    }

	public List<SpsScheme> findSchemeListByConditions(SpsScheme spsScheme) {
		return queryForList("SPS_SCHEME.findSchemeListByConditions", spsScheme );
	}

	/**
	 * 根据企业ID和城市获取最小方案
	 *  @param scheme
	 *  @return 
	 *	@return 			: SpsScheme 
	 *  @createDate  	: 2017年3月17日 下午1:40:14
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 下午1:40:14
	 *  @updateAuthor  :
	 */
	public SpsScheme findMinSchemeByCityIdAndCpId(SpsScheme scheme) {
		Object ret = queryForObject("SPS_SCHEME.findMinSchemeByCityIdAndCpId", scheme );
		if(ret !=null)
			return (SpsScheme)ret;
		else
			return null;
	}

    public List<Integer> findSchemeListByCpId(Integer cpId){
        return queryForList("SPS_SCHEME.findSchemeListByCpId", cpId);
    }

    public SpsScheme  findSchemeByEmpId(Integer empId){
        Object ret = queryForObject("SPS_SCHEME.findSchemeByEmpId", empId);
        if (ret != null)
            return (SpsScheme) ret;
        else
            return null;
    }

	/**
	 *  获取当前企业下的方案列表
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 14:33
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 14:33
	 *  @updateAuthor  :
	 */
	public List<SpsScheme> findSchemeByCpId(ContextInfo cti) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("cpId",cti.getOrgId());
		_hashmap.put("authority",cti.getAuthority());
		return queryForList("SPS_SCHEME.find_schemeby_cpid", _hashmap );
	}


	public List<Map<String,Object>> querySchemeAccountAreaList(ContextInfo cti){
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("cpId",cti.getOrgId());
		_hashmap.put("authority",cti.getAuthority());
		return queryForList("SPS_SCHEME.querySchemeAccountAreaList", _hashmap );
	}

	/**
	 * 根据企业和城市名称获取方案
	 *  @param scheme
	 *  @return 
	 *	@return 			: SpsScheme 
	 *  @createDate  	: 2017年5月18日 下午3:43:43
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月18日 下午3:43:43
	 *  @updateAuthor  :
	 */
	public SpsScheme findMinSchemeByCpIdAndCityNmae(SpsScheme scheme) {
		Object ret = queryForObject("SPS_SCHEME.findMinSchemeByCpIdAndCityNmae", scheme );
		if(ret !=null)
			return (SpsScheme)ret;
		else
			return null;
	}

	/**
	 * 根据权限、企业ID、服务商ID和方案ID获取参保城市
	 *  @param authority
	 *  @param cpId
	 *  @param spId
	 *  @param schemeIds
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月14日 下午2:30:35
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月14日 下午2:30:35
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findServiceBillDetailsArea(String authority, Integer cpId, Integer spId,
			String[] schemeIds) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("cpId",cpId);
		_hashmap.put("spId",spId);
		_hashmap.put("authority",authority);
		_hashmap.put("schemeIds",schemeIds);
		return queryForList("SPS_SCHEME.findServiceBillDetailsArea", _hashmap );
	}
	/**
	 *  根据 城市+供应商名称 查询方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月20日  上午9:43:16
	 */
	public List<Map<String,Object>> findSchemeByName(Map<String,Object> paraMap){
		return queryForList("SPS_SCHEME.findSchemeByName", paraMap );
	}

	/**
	 * 根据 服务商获取相关 方案id
	 * @param paraMap
	 * @author quanjh
	 * @return
	 */
	public List<Map<String,Object>> findSchemeBySpIds(Map<String,Object> paraMap){
		return queryForList("SPS_SCHEME.findSchemeBySpIds", paraMap );
	}

	/**
	 * 企业下所有外包方案
	 * @param cti
	 * @return
	 */
	public List<SpsScheme> findAllSpSchemeByCpId(ContextInfo cti){
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("cpId",cti.getOrgId());
		_hashmap.put("authority",cti.getAuthority());
		return queryForList("SPS_SCHEME.findAllSpSchemeByCpId", _hashmap );
	}
	/**
	  * 查询 有权限的 方案（排除自服务方案）
	  *
	  * @author lifq
	  *
	  * 2017年8月8日  下午5:53:03
	  */
	 public List<Map<String,Object>> findSpSchemeByAuthority(Map<String,Object> paraMap){
		 return queryForList("SPS_SCHEME.findSpSchemeByAuthority", paraMap );
	 }

	 /**
	  * 根据企业ID和城市获取方案  按自服务倒序
	  *  @param spsScheme
	  *  @return 
	  *	@return 			: List<SpsScheme> 
	  *  @createDate  	: 2017年8月23日 下午4:39:59
	  *  @author         	: wangchao 
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017年8月23日 下午4:39:59
	  *  @updateAuthor  :
	  */
	public List<SpsScheme> findSchemeByCityIdAndCpIdOderBySpId(SpsScheme spsScheme) {
		return queryForList("SPS_SCHEME.findSchemeByCityIdAndCpIdOderBySpId", spsScheme );
	}

	/**
	 * 获取当前企业下自服务所有方案列表
	 * @param cti
	 * @return
	 */
	public List<Map<String,Object>> findSelfSchemeByCpId(ContextInfo cti){
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("cpId",cti.getOrgId());
		_hashmap.put("authority",cti.getAuthority());
		return queryForList("SPS_SCHEME.find_self_scheme_bycpid", _hashmap );
	}


}
