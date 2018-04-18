package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsFeeCorponce;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.Constant;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysFunctionDataService;

/**
 * SpsFeeCorponceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/04 15:43:52
 */
@Repository
public class SpsFeeCorponceDao extends BaseSqlMapDao {
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_FEE_CORPONCE.CountFindAllSPS_FEE_CORPONCE", null );
        return ret.intValue();
	}

	public SpsFeeCorponce findByPK(SpsFeeCorponce obj) {
		Object ret = queryForObject("SPS_FEE_CORPONCE.FindByPK", obj );
		if(ret !=null)
			return (SpsFeeCorponce)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeCorponce> freeFind(SpsFeeCorponce obj) {
		return queryForList("SPS_FEE_CORPONCE.FreeFindSPS_FEE_CORPONCE", obj );
	}

	public int countFreeFind(SpsFeeCorponce obj) {
        Integer ret = (Integer) queryForObject("SPS_FEE_CORPONCE.CountFreeFindSPS_FEE_CORPONCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeCorponce> freeFind(SpsFeeCorponce obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_FEE_CORPONCE.FreeFindSPS_FEE_CORPONCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeCorponce> freeFind(SpsFeeCorponce obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeCorponce){
	    	_hashmap = ((SpsFeeCorponce)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_FEE_CORPONCE.FreeFindSPS_FEE_CORPONCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeCorponce> freeFind(SpsFeeCorponce obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeCorponce){
	    	_hashmap = ((SpsFeeCorponce)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_FEE_CORPONCE.FreeFindSPS_FEE_CORPONCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsFeeCorponce> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsFeeCorponce> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsFeeCorponce oneObj = (SpsFeeCorponce)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsFeeCorponce vo) {
        SpsFeeCorponce obj = (SpsFeeCorponce) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsFeeCorponce obj) {

		obj.fixup();
        return insert(cti, "SPS_FEE_CORPONCE.InsertSPS_FEE_CORPONCE", obj );
	}

	public int update(ContextInfo cti, SpsFeeCorponce obj) {

		obj.fixup();
		return update(cti, "SPS_FEE_CORPONCE.UpdateSPS_FEE_CORPONCE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsFeeCorponce obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_FEE_CORPONCE.DeleteSPS_FEE_CORPONCE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsFeeCorponce obj) {

        obj.fixup();
        SpsFeeCorponce oldObj = ( SpsFeeCorponce )queryForObject("SPS_FEE_CORPONCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_FEE_CORPONCE.DeleteSPS_FEE_CORPONCE", oldObj );

	}

	public boolean exists(SpsFeeCorponce vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsFeeCorponce obj = (SpsFeeCorponce) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_FEE_CORPONCE.CountSPS_FEE_CORPONCE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 15:43:52

	/**
	 * 获取单次费用列表
	 * @param corpname
	 * @param period
     * @return
     */
	public List<Map<String,Object>> queryOnceFeeList(String corpname,String period,Integer sp_id,int pageIndex, int pageSize,ContextInfo cti){
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		Map<String,Object> mp = new HashMap<>();
		mp.put("corp_name",corpname);
		mp.put("insurance_method",period);
		mp.put("sp_id",sp_id);
		mp.put("authority",authority);
		return (List<Map<String,Object>>) getPaginatedList("SPS_FEE_CORPONCE.QUERY_FEE_ONCE_CP_LIST", mp,pageIndex,pageSize );
	}

	/**
	 * 获取单次费用总数
	 * @param corpname
	 * @param period
     * @return
     */
	public int queryOnceFeeCount(String corpname,String period,Integer sp_id,ContextInfo cti){
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);

		Map<String,Object> mp = new HashMap<>();
		mp.put("corp_name",corpname);
		mp.put("insurance_method",period);
		mp.put("sp_id",sp_id);
		mp.put("authority",authority);
		Object ret =  queryForObject("SPS_FEE_CORPONCE.QUERY_FEE_ONCE_CP_COUNT", mp );
		if(null != ret)
			return (Integer)ret;
		else
			return 0;
	}

	/**
	 * 获取方案列表
	 * @param cp_id
	 * @param sp_id
     * @return
     */
	public List<Map<String,Object>> querySchemeList(Integer cp_id,Integer sp_id){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		return queryForList("SPS_FEE_CORPONCE.QUERY_SCHEMES_BY_SPID",mp);
	}

	/**
	 * 查询企业一次费用列表
	 * @param sp_id
	 * @param cp_id
	 * @param schemeId
	 * @param peroid
     * @return
     */
	public List<Map<String,Object>> queryCorpOnceFeeList(Integer sp_id,Integer cp_id,Integer schemeId,String peroid,int pageIndex, int pageSize){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("period",peroid);
		mp.put("scheme_id",schemeId);
		return (List<Map<String,Object>>) getPaginatedList("SPS_FEE_CORPONCE.QUERY_SCHEMES_LIST_BY_SPID_CPID",mp,pageIndex,pageSize);
	}

	/**
	 * 查询企业一次费用总数
	 * @param sp_id
	 * @param cp_id
	 * @param schemeId
	 * @param peroid
     * @return
     */
	public int queryCorpOnceFeeCount(Integer sp_id,Integer cp_id,Integer schemeId,String peroid){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("peroid",peroid);
		mp.put("scheme_id",schemeId);
		Object ret =  queryForObject("SPS_FEE_CORPONCE.QUERY_SCHEMES_COUNT_BY_SPID_CPID", mp );
		if(null != ret)
			return (Integer)ret;
		else
			return 0;
	}


	/**
	 * 查询企业员工一次费用列表
	 * @param sp_id
	 * @param cp_id
	 * @param schemeId
	 * @param peroid
	 * @return
	 */
	public List<Map<String,Object>> queryEmpOnceFeeList(Integer sp_id,Integer cp_id,Integer schemeId,Integer areaId,String peroid,String search_word,int pageIndex, int pageSize){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("period",peroid);
		mp.put("scheme_id",schemeId);
		mp.put("area_id",areaId);
		mp.put("search_word",search_word);
		return (List<Map<String,Object>>) getPaginatedList("SPS_FEE_CORPONCE.QUERY_EMP_ONCE_BY_SCHEME_LIST",mp,pageIndex,pageSize);
	}

	/**
	 * 查询企业一次费用总数
	 * @param sp_id
	 * @param cp_id
	 * @param schemeId
	 * @param peroid
	 * @return
	 */
	public int queryEmpOnceFeeCount(Integer sp_id,Integer cp_id,Integer schemeId,Integer areaId,String peroid,String search_word){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("period",peroid);
		mp.put("scheme_id",schemeId);
		mp.put("area_id",areaId);
		mp.put("search_word",search_word);
		Object ret = queryForObject("SPS_FEE_CORPONCE.QUERY_EMP_ONCE_BY_SCHEME_COUNT", mp );
		if(null != ret)
			return (Integer)ret;
		else
			return 0;
	}

	/**
	 * 获取企业一次性费用调整记录
	 * @param corponce
	 * @return
     */
	public List<SpsFeeCorponce> queryCorpOnceItems(SpsFeeCorponce corponce) {
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",corponce.getCpId());
		mp.put("sp_id",corponce.getSpId());
		mp.put("period",corponce.getPeriod());
		mp.put("scheme_id",corponce.getSchemeId());
		return queryForList("SPS_FEE_CORPONCE.QUERY_CROP_ITEMS_BY_PERIOD",mp);
	}

	/**
	 * 逻辑删除费用调整记录
	 * @param corponce
	 * @return
	 */
	public int delCorpOnceItems(ContextInfo cti, SpsFeeCorponce corponce){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",corponce.getCpId());
		mp.put("sp_id",corponce.getSpId());
		mp.put("id",corponce.getId());
		mp.put("dr",corponce.getDr());
		return update(cti, "SPS_FEE_CORPONCE.DEL_CROP_ITEMS_BY_ID",mp);
	}

	/**
	 * 查询子帐单费用调整项目
	 * @param sp_id
	 * @param p_sp_id
	 * @param cp_id
	 * @param period
     * @return
     */
	public List<SpsFeeCorponce> queryChildBillCorpOnceItems(Integer sp_id,String p_sp_id,Integer cp_id,String period) {
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("p_sp_id",p_sp_id);
		mp.put("period",period);
		return queryForList("SPS_FEE_CORPONCE.QUERY_CHILD_BILL_CORP_ITEMS",mp);
	}

}
