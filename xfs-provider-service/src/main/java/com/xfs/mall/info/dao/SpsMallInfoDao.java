package com.xfs.mall.info.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.info.model.SpsMallInfo;

/**
 * SpsMallInfoDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/06/07 11:06:19
 */
@Repository
public class SpsMallInfoDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_MALL_INFO.CountFindAllSPS_MALL_INFO", null);
		return ret.intValue();
	}

	public SpsMallInfo findByPK(SpsMallInfo obj) {
		Object ret = queryForObject("SPS_MALL_INFO.FindByPK", obj);
		if (ret != null)
			return (SpsMallInfo) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallInfo> freeFind(SpsMallInfo obj) {
		return queryForList("SPS_MALL_INFO.FreeFindSPS_MALL_INFO", obj);
	}

	public int countFreeFind(SpsMallInfo obj) {
		Integer ret = (Integer) queryForObject("SPS_MALL_INFO.CountFreeFindSPS_MALL_INFO", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallInfo> freeFind(SpsMallInfo obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_INFO.FreeFindSPS_MALL_INFO", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallInfo> freeFind(SpsMallInfo obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsMallInfo) {
			_hashmap = ((SpsMallInfo) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_MALL_INFO.FreeFindSPS_MALL_INFOOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsMallInfo> freeFind(SpsMallInfo obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsMallInfo) {
			_hashmap = ((SpsMallInfo) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_MALL_INFO.FreeFindSPS_MALL_INFOOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallInfo> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsMallInfo> it = objColl.iterator();
			while (it.hasNext()) {
				SpsMallInfo oneObj = (SpsMallInfo) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsMallInfo vo) {
		SpsMallInfo obj = (SpsMallInfo) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsMallInfo obj) {

		obj.fixup();
		return insert(cti, "SPS_MALL_INFO.InsertSPS_MALL_INFO", obj);
	}

	public int update(ContextInfo cti, SpsMallInfo obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_INFO.UpdateSPS_MALL_INFO", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallInfo obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_MALL_INFO.DeleteSPS_MALL_INFO", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsMallInfo obj) {

		obj.fixup();
		SpsMallInfo oldObj = (SpsMallInfo) queryForObject("SPS_MALL_INFO.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_MALL_INFO.DeleteSPS_MALL_INFO", oldObj);

	}

	public boolean exists(SpsMallInfo vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsMallInfo obj = (SpsMallInfo) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_MALL_INFO.CountSPS_MALL_INFO", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/06/07 11:06:19

	//cs
	/**
	 * 商铺首页详情
	 *
	 * @param vo
	 * @return
     */
	public Map<String, Object> findMallInfo(SpsMallInfo vo) {
		return (Map<String, Object>)queryForObject("SPS_MALL_INFO.FindSPS_MALL_INFO", vo);
	}

	/**
	 * 机构评分详情
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findMallScoreDetai(SpsMallInfo vo) {
		return queryForList("SPS_MALL_INFO.FIND_SPS_MALL_SCOTE_DETAIL", vo);
	}
	
}
