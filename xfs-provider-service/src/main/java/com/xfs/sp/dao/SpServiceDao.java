package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsArea;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.sp.model.SpService;

/**
 * SpServiceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/11 14:04:55
 */
@Repository
public class SpServiceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SP_SERVICE.CountFindAllSP_SERVICE", null );
        return ret.intValue();
	}

	public SpService findByPK(SpService obj) {
		Object ret = queryForObject("SP_SERVICE.FindByPK", obj );
		if(ret !=null)
			return (SpService)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpService> freeFind(SpService obj) {
		return queryForList("SP_SERVICE.FreeFindSP_SERVICE", obj );
	}

	public int countFreeFind(SpService obj) {
        Integer ret = (Integer) queryForObject("SP_SERVICE.CountFreeFindSP_SERVICE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpService> freeFind(SpService obj, int pageIndex, int pageSize) {
		return getPaginatedList("SP_SERVICE.FreeFindSP_SERVICE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpService> freeFind(SpService obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpService){
	    	_hashmap = ((SpService)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SP_SERVICE.FreeFindSP_SERVICEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpService> freeFind(SpService obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpService){
	    	_hashmap = ((SpService)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SP_SERVICE.FreeFindSP_SERVICEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpService> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpService> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpService oneObj = (SpService)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpService vo) {
        SpService obj = (SpService) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti,  SpService obj) {

		obj.fixup();
        return insert(cti, "SP_SERVICE.InsertSP_SERVICE", obj );
	}

	public int update(ContextInfo cti, SpService obj) {

		obj.fixup();
		return update(cti, "SP_SERVICE.UpdateSP_SERVICE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpService obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SP_SERVICE.DeleteSP_SERVICE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpService obj) {

        obj.fixup();
        SpService oldObj = ( SpService )queryForObject("SP_SERVICE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SP_SERVICE.DeleteSP_SERVICE", oldObj );

	}

	public boolean exists(SpService vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpService obj = (SpService) vo;

        keysFilled = keysFilled && ( null != obj.getSpId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SP_SERVICE.CountSP_SERVICE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 13:57:13
	/**
	 * 查询OpenList联系人信息
	 * @param spId
	 * @return
     */
	public SpService findOLContacts(Integer spId){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("sp_id",spId);
		Object ret = queryForObject("FindOL_Contact_By_SPID",hashMap);
		if (ret != null)
			return (SpService) ret;
		else
			return null;
	}

	/**
	 *  根据员工信息 反查服务商信息
	 * @param mobile
	 * @return
     */
	public SpService findOLContactsByCmPerson(String mobile){
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("mobile",mobile);
		Object ret = queryForObject("FindOL_Contact_By_CM_Person",hashMap);
		if (ret != null)
			return (SpService) ret;
		else
			return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SpService> find(SpService obj) {
		return queryForList("SP_SERVICE.findSP_SERVICE_By_SpService", obj);
	}

    public List<String> findAllSpServiceName(SpService spService) {
        return queryForList("SP_SERVICE.find_AllSpServiceName", spService);


    }

	public Map findAreaIdBySpId(String spId){
		return (Map) queryForObject("SP_SERVICE.findAreaIdBySpId",spId);
	}
	
	/*模糊查询spId和spName*/
	public List<SpService> freeFindSpidSpName(SpService spService){
		return queryForList("SP_SERVICE.FreeFindspIdspName", spService);
	}






    /**
     * 查询优质服务商列表
     *
     * @return
     */
    public List<SpService> findMallSpServies(BsArea area) {
        return queryForList("SP_SERVICE.Find_MALL_SP_SERVICE", area);
    }

    /**
     * 查询服务机构列表
     *
     * @param areaIds
     * @return
     */
    public List<Map<String, Object>> findMallSpServices(String areaIds) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if (!StringUtils.isBlank(areaIds)) {
            _hashmap.put("areaIds", areaIds);
        }
        return queryForList("SP_SERVICE.Find_MALL_SP_SERVICES", _hashmap);
    }

    /**
     * 服务机构总数
     *
     * @param areaId
     * @param categoryId
     * @param certificationType
     * @param searchWord
     * @return
     */
    public int findCountByCondition(String areaId, String categoryId, String certificationType, String beginYear, String endYear, String searchWord) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if (!StringUtils.isBlank(areaId)) {
            _hashmap.put("areaId", areaId);
        }
        if (!StringUtils.isBlank(categoryId)) {
            _hashmap.put("categoryId", categoryId);
        }
        if (!StringUtils.isBlank(certificationType)) {
            _hashmap.put("certificationType", certificationType);
        }
        if (!StringUtils.isBlank(beginYear)) {
            _hashmap.put("beginYear", beginYear);
        }
        if (!StringUtils.isBlank(endYear)) {
            _hashmap.put("endYear", endYear);
        }
        if (!StringUtils.isBlank(searchWord)) {
            _hashmap.put("searchWord", searchWord);
        }
        Object count = queryForObject("SP_SERVICE.COUNTFIND_MALL_SP_SERVICE", _hashmap);
        return count != null ? (Integer)count : 0;
    }

    /**
     * 服务机构列表
     *
     * @param areaId
     * @param categoryId
     * @param certificationType
     * @param searchWord
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findListByCondition(String areaId, String categoryId, String certificationType, String beginYear, String endYear, String searchWord, int pageIndex, int pageSize) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if (!StringUtils.isBlank(areaId)) {
            _hashmap.put("areaId", areaId);
        }
        if (!StringUtils.isBlank(categoryId)) {
            _hashmap.put("categoryId", categoryId);
        }
        if (!StringUtils.isBlank(certificationType)) {
            _hashmap.put("certificationType", certificationType);
        }
        if (!StringUtils.isBlank(beginYear)) {
            _hashmap.put("beginYear", beginYear);
        }
        if (!StringUtils.isBlank(endYear)) {
            _hashmap.put("endYear", endYear);
        }
        if (!StringUtils.isBlank(searchWord)) {
            _hashmap.put("searchWord", searchWord);
        }
        return getPaginatedList("SP_SERVICE.FIND_MALL_SP_SERVICES", _hashmap, pageIndex, pageSize);
    }

    public Map<String, Object> findMallSpServiceByProductId(SpsMallProduct vo) {
        return (Map<String, Object>)queryForObject("SP_SERVICE.FIND_MALL_SERVICE_BY_PRODUCTID", vo);
    }

    /**
     * 与地区连接查询
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @param orderByColName
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SpService> freeFindAndarea(SpService obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof SpService){
            _hashmap = ((SpService)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return getPaginatedList("SP_SERVICE.FreeFindSP_SERVICEAndBS_AREAOrdered", _hashmap, pageIndex, pageSize );
    }

    /**
     * 服务商审核
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @param orderByColName
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SpService> findSpServiceByState(String serviceState,String enabledMall, String spname, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("enabledMall", enabledMall);
        _hashmap.put("serviceState", serviceState);
        _hashmap.put("spname", spname);
        _hashmap.put("_orderBy", orderByColName );

        return getPaginatedList("SP_SERVICE.findSP_SERVICE_By_State", _hashmap, pageIndex, pageSize );
    }

    public int countSpServiceByState( String serviceState,String enabledMall, String spname) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("enabledMall", enabledMall);
        _hashmap.put("serviceState", serviceState);
        _hashmap.put("spname", spname);

        Integer ret = (Integer) queryForObject("SP_SERVICE.CountSP_SERVICE_By_State", _hashmap);
        return ret.intValue();
    }

    public int countFreeFindByStatus() {
        Integer ret = (Integer) queryForObject("SP_SERVICE.CountFreeFindByStatusSP_SERVICE", null);
        return ret.intValue();
    }

    public int countSpServiceByRecom(String spName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("spName", spName);
        Integer ret = (Integer) queryForObject("SP_SERVICE.CountSP_SERVICE_By_Recom", _hashmap);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SpService> findSpServiceByRecom(String spName, int pageIndex, int pageSize){
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("spName", spName);
        return getPaginatedList("SP_SERVICE.findSP_SERVICE_By_Recom", _hashmap, pageIndex, pageSize );
    }

    public Map<String,Object> CountFreeInfoBySpId(Integer spId) {
        Map<String,Object> map = (Map<String,Object>) queryForObject("SP_SERVICE.CountFreeInfoBySpId", spId);
        return map;
    }

    public int updateSpService(ContextInfo cti, SpService obj) {
        obj.fixup();
        return update(cti, "SP_SERVICE.UpdateSP_SERVICEToStar", obj);

    }

    public SpService findSpServiceById(Integer saasSpId) {
        SpService service = new SpService();
        service.setSpId(saasSpId);
        return (SpService) queryForObject("SP_SERVICE.FindByPK", service);
    }

    public SpService findSpByInventedCorp(Integer cpId){
        SpService service = new SpService();
        service.setCpId(cpId);
        return (SpService) queryForObject("SP_SERVICE.findSpByInventedCorp", service);
    }

	public List<ServiceBillVo> findServiceBillList(ServiceBillVo vo, int pageIndex, int pageSize) {
		return getPaginatedList("SP_SERVICE.findServiceBillList", vo, pageIndex, pageSize);
	}

	public Integer findServiceBillListCount(ServiceBillVo vo) {
		return (Integer) queryForObject("SP_SERVICE.findServiceBillListCount", vo);
	}

	public List<ServiceBillVo> findServiceBillCount(ServiceBillVo vo) {
		return queryForList("SP_SERVICE.findServiceBillList", vo);
	}
	
	/**
	 * 根据 服务商名称 查询，进行重复校验
	 *
	 * @author lifq
	 *
	 * 2017年8月7日  下午1:51:52
	 */
	public List<Map<String,Object>> findSpByName(Map<String,Object> paraMap){
		return queryForList("SP_SERVICE.findSpByName", paraMap );
	}
	
}
