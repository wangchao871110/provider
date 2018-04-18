package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpBusiness;
import com.xfs.cp.model.CpService;
import com.xfs.mall.item.model.BsMallItemCategory;
import com.xfs.sp.model.SpService;

/**
 * CpServiceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:39
 */
@Repository
public class CpServiceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_SERVICE.CountFindAllCP_SERVICE", null );
        return ret.intValue();
	}

	public CpService findByPK(CpService obj) {
		Object ret = queryForObject("CP_SERVICE.FindByPK", obj );
		if(ret !=null)
			return (CpService)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpService> freeFind(CpService obj) {
		return queryForList("CP_SERVICE.FreeFindCP_SERVICE", obj );
	}

	public int countFreeFind(CpService obj) {
        Integer ret = (Integer) queryForObject("CP_SERVICE.CountFreeFindCP_SERVICE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpService> freeFind(CpService obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_SERVICE.FreeFindCP_SERVICE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpService> freeFind(CpService obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpService){
	    	_hashmap = ((CpService)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_SERVICE.FreeFindCP_SERVICEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpService> freeFind(CpService obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpService){
	    	_hashmap = ((CpService)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_SERVICE.FreeFindCP_SERVICEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpService> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpService> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpService oneObj = (CpService)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpService vo) {
        CpService obj = (CpService) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpService obj) {

		obj.fixup();
        return insert(cti, "CP_SERVICE.InsertCP_SERVICE", obj );
	}

	public int update(ContextInfo cti, CpService obj) {

		obj.fixup();
		return update(cti, "CP_SERVICE.UpdateCP_SERVICE", obj );

	}

	public int updateSAASSp(ContextInfo cti, SpService obj) {
		
		obj.fixup();
		return update(cti, "CP_SERVICE.UpdateSP_SERVICE", obj );
		
	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpService obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_SERVICE.DeleteCP_SERVICE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpService obj) {

        obj.fixup();
        CpService oldObj = ( CpService )queryForObject("CP_SERVICE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_SERVICE.DeleteCP_SERVICE", oldObj );

	}

	public boolean exists(CpService vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpService obj = (CpService) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_SERVICE.CountCP_SERVICE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	/**
	 * 
	 * @method comments: 个人中心 获取公司信息
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月8日 下午7:31:14 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月8日 下午7:31:14 baoyu 创建
	 *
	 */
	public CpService getCpServiceById(CpService cpService) { 
		return (CpService) queryForObject("CP_SERVICE.getCpServiceById", cpService);
	}

	/**
	 * 
	 * @method comments: 获取所有主营业务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月9日 上午10:36:47 
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月9日 上午10:36:47 baoyu 创建
	 *
	 */
	public List<BsMallItemCategory> getAllBusiness() {
		return queryForList("CP_SERVICE.getAllBusiness", null);
	}

	/**
	 * 
	 * @method comments: 获取服务商选择的主营业务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月9日 上午10:56:43 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月9日 上午10:56:43 baoyu 创建
	 *
	 */
	public List<CpBusiness> getBusinessById(CpService cpService) {
		return queryForList("CP_SERVICE.getBusinessById", cpService);
	}

	/**
	 * 
	 * @method comments: 用户中心 统一获取左侧服务商信息（当前排名，发展指数，同业认证）
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月11日 上午1:27:26 
	 * @param cpServiceLeft
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 上午1:27:26 baoyu 创建
	 *
	 */
	public CpService getSpLeftBaseInfo(CpService cpServiceLeft) {
		return (CpService) queryForObject("CP_SERVICE.getSpLeftBaseInfo", cpServiceLeft);
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:39
	
	/**
	 * 
	 * @method comments: 获取排行榜总数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月11日 下午4:45:19 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午4:45:19 wangchao 创建
	 *
	 */
	public Integer findRankingPageCount(CpService cpService) {
		Integer ret = (Integer) queryForObject("CP_SERVICE.findRankingPageCount", cpService );
        return ret.intValue();
	}

	/**
	 * 
	 * @method comments: 获取排行榜列表
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月11日 下午4:45:37 
	 * @param cpService
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午4:45:37 wangchao 创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<CpService> findRankingPage(CpService cpService, int pageIndex, int pageSize) {
		return getPaginatedList("CP_SERVICE.findRankingPage", cpService, pageIndex, pageSize );
	}

	/**
	 * 
	 * @method comments: 获取选择的一级主营业务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 上午10:17:53 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:17:53 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessAById(CpService cpService) {
		return queryForList("CP_SERVICE.getBusinessAById", cpService);
	}

	/**
	 * 
	 * @method comments: 获取特色服务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 上午10:34:06 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:34:06 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> spSpecialBusinessList(CpService cpService) {
		return queryForList("CP_SERVICE.spSpecialBusinessList", cpService);
	}

	/**
	 * 
	 * @method comments: 获取二级主营业务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 上午11:25:23 
	 * @param string
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:25:23 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessBById(Map<String, Object> content_id) {
		return queryForList("CP_SERVICE.getBusinessBById", content_id);
	}

	/**
	 * 
	 * @method comments: 编辑页面 点击 主营业务 获取二级主营业务
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 下午3:00:53 
	 * @param whereMap
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午3:00:53 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessBByCategoryId(Map<String, Object> whereMap) {
		return queryForList("CP_SERVICE.getBusinessBByCategoryId", whereMap);
	}
	/**
	 * 
	 * @method comments: 根据ID获取服务商详细信息
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 上午10:34:08 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:34:08 wangchao 创建
	 *
	 */
	public CpService findCpServiceDetailById(CpService cpService) {
		return (CpService) queryForObject("CP_SERVICE.findCpServiceDetailById", cpService);
	}

	public Integer findOrderById(CpService cpService) {
		Integer ret = (Integer) queryForObject("CP_SERVICE.findOrderById", cpService );
        return ret;
	}

	/**
	 * 
	 * @method comments: 获取平均成立年限
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 下午9:55:58 
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午9:55:58 baoyu 创建
	 *
	 */
	public double getEstablishedAvgByCpId(Integer cpId) {
		Double ret =  (Double) queryForObject("CP_SERVICE.getEstablishedAvgByCpId", cpId);
        return ret;
	}

	/**
	 * 
	 * @method comments: 主营业务大类数/平均主营业务大类数；
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 下午10:24:12 
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午10:24:12 baoyu 创建
	 *
	 */
	public Double getRateBusinessByCpId(Integer cpId) {
        return (Double) queryForObject("CP_SERVICE.getRateBusinessByCpId", cpId);
	}

	public Double getRateServiceAreaByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateServiceAreaByCpId", cpId);
	}

	public Double getTransactionPriceByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getTransactionPriceByCpId", cpId);
	}

	public int getReciveOrderByCpId(Integer cpId) {
		return (Integer) queryForObject("CP_SERVICE.getReciveOrderByCpId", cpId);
	}

	public Double getRateBrowseNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateBrowseNumByCpId", cpId);
	}

	public Double getRateAttentionNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateAttentionNumByCpId", cpId);
	}

	public Double getRateIndustryNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateIndustryNumByCpId", cpId);
	}

	public Double getEfficiencyNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getEfficiencyNumByCpId", cpId);
	}

	public Double getAttitudeNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getAttitudeNumByCpId", cpId);
	}

	public Double getPowerNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getPowerNumByCpId", cpId);
	}

	public Double getProfessionNumByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getProfessionNumByCpId", cpId);
	}

	public List<Map<String, Object>> getProvinceList() {
		return queryForList("CP_SERVICE.getProvinceList", null);
	}

	public List<Map<String, Object>> getCityList() {
		return queryForList("CP_SERVICE.getCityList", null);
	}

	public List<Map<String, Object>> getAreaList() {
		return queryForList("CP_SERVICE.getAreaList", null);
	}

	public List<Map<String, Object>> getCityByFather(Map<String, Object> whereMap) {
		return queryForList("CP_SERVICE.getCityByFather", whereMap);
	}

	public List<Map<String, Object>> getAreaByFather(Map<String, Object> whereMap) {
		return queryForList("CP_SERVICE.getAreaByFather", whereMap);
	}

	public Double getRateRegisteredCapitalByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateRegisteredCapitalByCpId", cpId);
	}

	/**
	 * 
	 * @method comments: 获取同城前两名服务机构
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月13日 下午5:09:46 
	 * @param city
	 * @param id
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 下午5:09:46 wangchao 创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<CpService> findCpServiceByCity(String city, Integer id) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("id", id);
		_hashmap.put("city", city);
		return queryForList("CP_SERVICE.findCpServiceByCity", _hashmap);
	}

	public Double getReciveOrderRateByCpId(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getReciveOrderRateByCpId", cpId);
	}

	public int getComplainNumByCpId(Integer cpId) {
		return (Integer) queryForObject("CP_SERVICE.getComplainNumByCpId", cpId);
	}

	/**
	 * 
	 * @method comments: 验证填写的公司名称是否被其他公司注册过
	 * @author   name  : baoyu  
	 * @create   time  : 2016年10月9日 下午5:15:32 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:15:32 baoyu 创建
	 *
	 */
	public int getCpServiceExpId(CpService cpService) {
		return (Integer) queryForObject("CP_SERVICE.getCpServiceExpId", cpService);
	}

	/**
	 * 
	 * @method comments: 获取派单数
	 * @author   name  : baoyu  
	 * @create   time  : 2016年10月9日 下午5:15:32 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:15:32 baoyu 创建
	 *
	 */
	public Integer getCreatPackageCount(Integer cpId) {
		return (Integer) queryForObject("CP_SERVICE.getCreatPackageCount", cpId);
	}
	/**
	 *
	 * @method comments: 获取派单次数/最高派单数*100
	 * @author   name  : baoyu
	 * @create   time  : 2016年10月9日 下午5:15:32
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:15:32 baoyu 创建
	 *
	 */
	public Double getRateCreatPackageCount(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRateCreatPackageCount", cpId);
	}
	/**
	 *
	 * @method comments: 获取保证金
	 * @author   name  : baoyu
	 * @create   time  : 2016年10月9日 下午5:15:32
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:15:32 baoyu 创建
	 *
	 */
	public Double getRiskMarginOfSpsMallInfo(Integer cpId) {
		return (Double) queryForObject("CP_SERVICE.getRiskMarginOfSpsMallInfo", cpId);
	}
	/**
	 *
	 * @method comments: 获取派单次数/最高派单数*100
	 * @author   name  : baoyu
	 * @create   time  : 2016年10月9日 下午5:15:32
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:15:32 baoyu 创建
	 *
	 */
	public Integer getCpCustomerPhoneCount(Integer cpId) {
		return (Integer) queryForObject("CP_SERVICE.getCpCustomerPhoneCount", cpId);
	}

	public Integer updateBrowseNum(ContextInfo cti, CpService cps) {
		cps.fixup();
		return update(cti, "CP_SERVICE.updateBrowseNum",cps );
	}

	public List<Map<String, Object>> getCityByName(String cityOfCpAddress) {
		return queryForList("CP_SERVICE.getCityByName", cityOfCpAddress);
	}

	//根据id查询信息
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> FindCpServiceMap(CpService obj){
    	return queryForList("CP_SERVICE.FreeFindCp_ServiceMap", obj);
    }

	//增加指数更新
	public int updateCustomIndex(ContextInfo cti, CpService obj) {

		obj.fixup();
		return update(cti, "CP_SERVICE.UpdateCustomIndexCP_SERVICE", obj );

	}

	public Integer getAreaByAreacode(String areacode) {
		return (Integer) queryForObject("CP_SERVICE.getAreaByAreacode", areacode);
	}

	public double findPriceById(CpService cp2) {
		Integer ret = (Integer) queryForObject("CP_SERVICE.findPriceById", cp2 );
		if(null == ret){
			return 0.00;
		}
        return Double.valueOf(ret.toString());
	}
}
