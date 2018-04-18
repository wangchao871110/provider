package com.xfs.mall.product.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;
import com.xfs.mall.product.model.SpsMallProduct;

/**
 * SpsMallProductDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/06/07 11:06:12
 */
@Repository
public class SpsMallProductDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT.CountFindAllSPS_MALL_PRODUCT", null);
		return ret.intValue();
	}

	public SpsMallProduct findByPK(SpsMallProduct obj) {
		Object ret = queryForObject("SPS_MALL_PRODUCT.FindByPK", obj);
		if (ret != null)
			return (SpsMallProduct) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallProduct> freeFind(SpsMallProduct obj) {
		return queryForList("SPS_MALL_PRODUCT.FreeFindSPS_MALL_PRODUCT", obj);
	}

	public int countFreeFind(SpsMallProduct obj) {
		Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT.CountFreeFindSPS_MALL_PRODUCT", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallProduct> freeFind(SpsMallProduct obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_PRODUCT.FreeFindSPS_MALL_PRODUCT", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallProduct> freeFind(SpsMallProduct obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsMallProduct) {
			_hashmap = ((SpsMallProduct) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_MALL_PRODUCT.FreeFindSPS_MALL_PRODUCTOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallProduct> freeFind(SpsMallProduct obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsMallProduct) {
			_hashmap = ((SpsMallProduct) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_MALL_PRODUCT.FreeFindSPS_MALL_PRODUCTOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallProduct> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsMallProduct> it = objColl.iterator();
			while (it.hasNext()) {
				SpsMallProduct oneObj = (SpsMallProduct) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsMallProduct vo) {
		SpsMallProduct obj = (SpsMallProduct) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsMallProduct obj) {

		obj.fixup();
		return insert(cti, "SPS_MALL_PRODUCT.InsertSPS_MALL_PRODUCT", obj);
	}

	public int update(ContextInfo cti, SpsMallProduct obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_PRODUCT.UpdateSPS_MALL_PRODUCT", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallProduct obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_MALL_PRODUCT.DeleteSPS_MALL_PRODUCT", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsMallProduct obj) {

		obj.fixup();
		SpsMallProduct oldObj = (SpsMallProduct) queryForObject("SPS_MALL_PRODUCT.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_MALL_PRODUCT.DeleteSPS_MALL_PRODUCT", oldObj);

	}

	public boolean exists(SpsMallProduct vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsMallProduct obj = (SpsMallProduct) vo;

		keysFilled = keysFilled && (null != obj.getProductId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_MALL_PRODUCT.CountSPS_MALL_PRODUCT", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/06/07 11:06:12

	/**
	 * 根据spId areaId查询项目表 当前区域所有项目
	 * 
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAllBySpIdAreaId(SpsMallProduct vo) {
		return queryForList("SPS_MALL_PRODUCT.FindAllBySpIdAreaId", vo);
	}

	public int updateOrderByByProductId(ContextInfo cti, SpsMallProduct vo) {

		return update(cti, "SPS_MALL_PRODUCT.UpdateOrderByByProductId", vo);

	}

	public SpsMallProduct FindAreaIdBySpAreaId(SpsMallProduct sp) {
		Object ret = queryForObject("SPS_MALL_PRODUCT.FindAreaIdBySpAreaId", sp);
		if (ret != null)
			return (SpsMallProduct) ret;
		else
			return null;
	}

	public String findArea(String areaId) {
		try {
			return (String) queryForObject("SPS_MALL_PRODUCT.FindAreaName", Integer.valueOf(areaId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取服务商品列表
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findMallProducts(SpsMallProduct vo) {
		return queryForList("SPS_MALL_PRODUCT.FindSP_SPS_MALL_PRODUCT", vo);
	}

	/**
	 * 条件搜索产品总数
	 *
	 * @param areaIds
	 * @param categoryIds
	 * @param spId
	 * @param searchWord
	 * @return
	 */
	public int findCountByCondition(String areaIds, String categoryIds, String spId, String searchWord) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (!StringUtils.isBlank(areaIds)) {
			_hashmap.put("areaIds", areaIds);
		}
		if (!StringUtils.isBlank(categoryIds)) {
			_hashmap.put("categoryIds", categoryIds);
		}
		if (!StringUtils.isBlank(spId)) {
			_hashmap.put("spId", spId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT.COUNTFINDSPS_MALL_PRODUCT", _hashmap);
		return ret.intValue();
	}

	/**
	 * 条件搜索产品列表
	 *
	 * @param areaIds
	 * @param categoryIds
	 * @param spId
	 * @param searchWord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findListByCondition(String areaIds, String categoryIds, String spId, String searchWord, Integer pageIndex, Integer pageSize) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (!StringUtils.isBlank(areaIds)) {
			_hashmap.put("areaIds", areaIds);
		}
		if (!StringUtils.isBlank(categoryIds)) {
			_hashmap.put("categoryIds", categoryIds);
		}
		if (!StringUtils.isBlank(spId)) {
			_hashmap.put("spId", spId);
		}
		if (!StringUtils.isBlank(searchWord)) {
			_hashmap.put("searchWord", searchWord);
		}
		return getPaginatedList("SPS_MALL_PRODUCT.FINDSPS_MALL_PRODUCT_LIST", _hashmap, pageIndex, pageSize );
	}

	/**
	 * 查询产品详情
	 *
	 * @param vo
	 * @return
     */
	public Map<String, Object> findProductDetail(SpsMallProduct vo) {
		return (Map<String, Object>)queryForObject("SPS_MALL_PRODUCT.FindSPS_MALL_PRODUCT_DETAIL", vo);
	}

	public List<Map<String, Object>> findMallProductsBySpId(SpsMallProduct vo) {
		return queryForList("SPS_MALL_PRODUCT.FindSP_SPS_MALL_PRODUCT_BY_SPID", vo);
	}
	
	public int countSpsProductByRecom(String productName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("productName", productName);
		Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT.CountSPS_MALL_PRODUCT_By_Recom", _hashmap);
		return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallProduct> findSpsProductByRecom(String productName, String spName,int pageIndex, int pageSize){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("productName", productName);
		_hashmap.put("spName", spName);
		return getPaginatedList("SPS_MALL_PRODUCT.findSPS_MALL_PRODUCT_By_Recom", _hashmap, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findAllmsg(Map Obj,int pageIndex, int pageSize){
		return getPaginatedList("SPS_MALL_PRODUCT.findAllmsg", Obj, pageIndex, pageSize);
	}
	
	public int findAllCount(Map vo){
		Integer ret = (Integer) queryForObject("SPS_MALL_PRODUCT.findAllCount", vo);
		return ret.intValue();
	}
	
}
