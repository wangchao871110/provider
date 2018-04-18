package com.xfs.corp.dao;

import com.xfs.bs.dto.CorpAccount;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.Constant;
import com.xfs.corp.model.CmCorp;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysFunctionDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-8 14:10:00
 * @version 	: V1.0
 */
@Repository
public class CmCorpDao extends BaseSqlMapDao {
	@Autowired
    private SysFunctionDataService sysFunctionDataService;
	public int countFindAll() {
		Integer ret = (Integer) queryForObject("CM_CORP.CountFindAllCM_CORP", null);
		return ret.intValue();
	}

	public CmCorp findByPK(CmCorp obj) {
		Object ret = queryForObject("CM_CORP.FindByPK", obj);
		if (ret != null)
			return (CmCorp) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFind(CmCorp obj) {
		return queryForList("CM_CORP.FreeFindCM_CORP", obj);
	}

	public int countFreeFind(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.CountFreeFindCM_CORP", obj);
		return ret.intValue();
	}

	/**
	 * <p>Title: bs查询企业总数</p>
	 * <p>Description: bs查询企业总数</p>
	 * ${tags}
	 */
	public int countFreeFindToBs(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.CountFreeFindCM_CORP_BS", obj );
		return ret.intValue();
	}

	/**
	 * <p>Title: cp查询企业总数</p>
	 * <p>Description: cp查询企业总数</p>
	 * ${tags}
	 */
	public int countFreeFindToCp(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.CountFreeFindCM_CORP", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFind(CmCorp obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CORP.FreeFindCM_CORP", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFind(CmCorp obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof CmCorp) {
			_hashmap = ((CmCorp) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("CM_CORP.FreeFindCM_CORPOrdered", _hashmap);
	}

	/**
	 * <p>Title: bs查询企业</p>
	 * <p>Description: bs查询企业</p>
	 * ${tags}
	 */
	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFindToBs(CmCorp obj, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof CmCorp){
			_hashmap = ((CmCorp)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return queryForList("CM_CORP.FreeFindCM_CORPOrdered_BS", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFind(CmCorp obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof CmCorp) {
			_hashmap = ((CmCorp) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("CM_CORP.FreeFindCM_CORPOrdered", _hashmap, pageIndex, pageSize);
	}


	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFindToBs(CmCorp obj, int pageIndex, int pageSize, String orderByColName) {

		return getPaginatedList("CM_CORP.FreeFindCM_CORPOrdered_BS", obj, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmCorp> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<CmCorp> it = objColl.iterator();
			while (it.hasNext()) {
				CmCorp oneObj = (CmCorp) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, CmCorp vo) {
		CmCorp obj = (CmCorp) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, CmCorp obj) {

		obj.fixup();
		return insert(cti, "CM_CORP.InsertCM_CORP", obj);
	}

	public int update(ContextInfo cti, CmCorp obj) {

		obj.fixup();
		return update(cti, "CM_CORP.UpdateCM_CORP", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmCorp obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return update(cti, "CM_CORP.DeleteCM_CORP", obj);

	}

	public int removeObjectTree(ContextInfo cti, CmCorp obj) {

		obj.fixup();
		CmCorp oldObj = (CmCorp) queryForObject("CM_CORP.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "CM_CORP.DeleteCM_CORP", oldObj);

	}

	public boolean exists(CmCorp vo) {

		boolean keysFilled = true;
		boolean ret = false;
		CmCorp obj = (CmCorp) vo;

		keysFilled = keysFilled && (null != obj.getCpId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("CM_CORP.CountCM_CORP", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/11 16:09:14
	public int countFreeFindBySpId(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.CountFreeFindCM_CORP_BySpId", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<CmCorp> freeFindBySpId(CmCorp obj, int pageIndex, int pageSize) {
        return getPaginatedList("CM_CORP.FreeFindCM_CORP_BySpId", obj, pageIndex, pageSize);
	}

	/**
	 * 通过服务商id查询企业
	 * @param obj
	 * @return
     */
	public List<CmCorp> freeFindBySpId(CmCorp obj, ContextInfo cti) {
		 // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        obj.setAuthority(authority);
		return queryForList("CM_CORP.FreeFindCM_CORP_BySpId", obj);
	}

	public CmCorp findOneByCorpId(CmCorp obj) {
		return (CmCorp) queryForObject("CM_CORP.FreeFindCM_CORPByCorpId", obj);
	}

	/**
	 * 通过服务商和企业ID定位企业
	 * @param obj
	 * @return
     */
	public CmCorp findOneByCorpIdAndSpId(CmCorp obj) {
		return (CmCorp) queryForObject("CM_CORP.FreeFindCM_CORPByCorpId_AND_SPID", obj);
	}

	/**
	 * 根据企业名称 查询企业是否重复
	 */
	public int findCorpByCorpName(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.findCorpByCorpName", obj);
		return ret.intValue();
	}

	public int findCorpWithNameNotTheId(String corpName, Integer cpId) {
		HashMap<String,Object> map =new HashMap<>();
		map.put("corpName", corpName);
		map.put("cpId", cpId);
		Integer ret = (Integer) queryForObject("CM_CORP.findCorpWithNameNotTheId", map);
		return ret;
	}

	/**
	 * 通过企业名称查询企业
	 * @param corpName
	 * @return
	 */
	public CmCorp findCorpByName(String corpName,Integer spId) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("corpName", corpName);
		_hashmap.put("spId", spId);
		List<CmCorp> list = queryForList("CM_CORP.findCorpByCorpNameAndSpId", _hashmap);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 通过企业名称查询企业 并排除cpid
	 * @param corpName
	 * @return
	 */
	public CmCorp findCorpByName(String corpName,Integer spId,Integer cpId) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("corpName", corpName);
		_hashmap.put("spId", spId);
		_hashmap.put("cpId", cpId);
		List<CmCorp> list = queryForList("CM_CORP.findCorpByCorpNameAndSpId", _hashmap);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 查询 手机号 是否存在
	 *
	 * @author lifq
	 *
	 * 2016年8月14日  下午5:16:07
	 */
	public List<Map<String,Object>> findExistMobile(String mobile,String cpId){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("mobile",mobile);
        _hashMap.put("cpId",cpId);
        return queryForList("CM_CORP.findExistMobile", _hashMap);
    }

	@SuppressWarnings("unchecked")
	public List<CmCorp> findCorpBySpId(Integer spId, ContextInfo cti) {
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		CmCorp corp = new CmCorp();		
		corp.setAuthority(authority);
		corp.setSpId(spId);
		return queryForList("CM_CORP.FindCmCorpsBySpId", corp);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorp> findCorpBySpIdWithOutAuth(Integer spId,SysUser user) {
		// 数据权限
		CmCorp corp = new CmCorp();		
		corp.setAuthority("ALL");
		corp.setSpId(spId);
		return queryForList("CM_CORP.FindCmCorpsBySpId", corp);
	}
	/**
	 * 查询小库 总数
	 *
	 * @author lifq
	 *
	 * 2016年9月23日  上午11:21:03
	 */
	public int countfindSingleAccountPage(Map<String,String> paraMap) {
		Integer ret = (Integer) queryForObject("CM_CORP.countfindSingleAccountPage", paraMap);
		return ret.intValue();
	}
	/**
	 * 查询小库 数据
	 *
	 * @author lifq
	 *
	 * 2016年9月23日  上午11:21:03
	 */
	public List<Map<String,Object>> findSingleAccountPage(Map<String,String> paraMap, int pageIndex, int pageSize) {
        return getPaginatedList("CM_CORP.findSingleAccountPage", paraMap, pageIndex, pageSize);
	}


	//2016/06/14 15:50:51
	public List<CorpAccount> queryCorpAccountList(CmCorp cmCorp, Integer pageIndex, Integer pageSize) {
		Integer status = null;
		if(StringUtils.isNotBlank(cmCorp.getStatus())) {
			status = Integer.parseInt(cmCorp.getStatus());
		}
		Map<String,Object> obj = new HashMap<String,Object>();
		//  obj.put("statusList", statusList);
		obj.put("createDtFrom",cmCorp.getCreateDtFrom());
		obj.put("createDtTo",cmCorp.getCreateDtTo());
		obj.put("corpName", cmCorp.getCorpName());
		obj.put("status", status);
		return getPaginatedList("CM_CORP.QUERY_CM_CORP_LIST", obj, pageIndex, pageSize);
	}


	public Integer queryCorpAccountCount(CmCorp cmCorp) {
		Integer status = null;
		if(StringUtils.isNotBlank(cmCorp.getStatus())) {
			status = Integer.parseInt(cmCorp.getStatus());
		}

		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("createDtFrom",cmCorp.getCreateDtFrom());
		obj.put("createDtTo",cmCorp.getCreateDtTo());
		obj.put("corpName", cmCorp.getCorpName());
		obj.put("status", status);
		Integer ret = (Integer) queryForObject("CM_CORP.QUERY_CM_CORP_LIST_COUNT", obj );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> countByDay(String stringDate){
		return queryForList("CM_CORP.COUNTBYDAY", stringDate);
	}

	public List<CmCorp> getAllOrgInChannel(CmCorp obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof CmCorp){
			_hashmap = ((CmCorp)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return getPaginatedList("CM_CORP.getAllOrgInChannelCM_CORPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int countAllOrgInChannel(CmCorp obj) {
		Integer ret = (Integer) queryForObject("CM_CORP.countAllOrgInChannel", obj );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> FindAllMap(CmCorp obj){
		return queryForList("CM_CORP.FreeFindCM_CORPMap", obj);
	}


	/**
	 * <p>Title: 通过企业名称查询企业</p>
	 * <p>Description: 通过企业名称查询企业</p>
	 * ${tags}
	 */
	public CmCorp findByCorpName(CmCorp obj) {
		return (CmCorp)queryForObject("CM_CORP.FindByCorpName", obj);
	}
	/**
	 * 查询当前服务商的企业客户联系人 手机号
	 *
	 * 2016年12月27日
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findExistMobileBySpId(Integer spId){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("spId",spId);
        return queryForList("CM_CORP.findExistMobileBySpId", _hashMap);
    }

    public int countFreeFindBySpIdMap(CmCorp obj) {
        Integer ret = (Integer) queryForObject("CM_CORP.CountFreeFindCM_CORP_BySpId_Map", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> freeFindBySpIdMap(CmCorp obj, int pageIndex, int pageSize) {
        return getPaginatedList("CM_CORP.FreeFindCM_CORP_BySpId_Map", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> freeFindCorpIdsBySpId(CmCorp obj) {
        return queryForList("CM_CORP.FreeFindCM_CORP_BySpId_Map", obj);
    }
    public int updateSpCmRelationIfStop(ContextInfo cti, CmCorp obj) {
		return update(cti, "CM_CORP.UPDATESPCMRELATIONIFSTOP", obj);

	}

    /**
     * 根据租户ID获取企业
     *  @param tenantId
     *  @return 
     *	@return 			: CmCorp 
     *  @createDate  	: 2017年5月17日 下午2:55:41
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年5月17日 下午2:55:41
     *  @updateAuthor  :
     */
	public CmCorp findCorpByTenantId(String tenantId) {
		Object ret = queryForObject("CM_CORP.findCorpByTenantId", tenantId);
		if (ret != null)
			return (CmCorp) ret;
		else
			return null;
	}
	
	public CmCorp findCmCorpByTenantId(String tenantId) {
		HashMap<String,Object> _hashMap = new HashMap<String,Object>();
		_hashMap.put("tenantId",tenantId);
		return (CmCorp) queryForObject("CM_CORP.FreeFindCM_BY_TENANTID", _hashMap);
	}

	public List<CmCorp> queryAllUnSynList(){
		return queryForList("CM_CORP.findUnSynCorpList", null);
	}


	public List<Map> getAllCompInfoByCondition(CmCorp cmCorp){
		return queryForList("CM_CORP.getAllCompInfoByCondition", cmCorp);
	}

	public List<ServiceBillVo> findServiceBillList(ServiceBillVo vo, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer findServiceBillListCount(ServiceBillVo vo) {
		// TODO Auto-generated method stub
		return null;
	}


	public CmCorp getCorpByCpIdOrTenantId(String id) {
		HashMap<String,Object> _hashMap = new HashMap<String,Object>();
		_hashMap.put("id",id);
		return (CmCorp) queryForObject("CM_CORP.getCorpByCpIdOrTenantId", _hashMap);
	}

	public List<CmCorp> checkNologinCorpList(){
		return queryForList("CM_CORP.checkNologinCorpList", null);
	}
}
