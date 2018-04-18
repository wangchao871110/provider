package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsAccountinfo;

/**
 * SpsAccountinfoDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/04/19 15:34:17
 */
@Repository
public class SpsAccountinfoDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNTINFO.CountFindAllSPS_ACCOUNTINFO", null);
		return ret.intValue();
	}

	public SpsAccountinfo findByPK(SpsAccountinfo obj) {
		Object ret = queryForObject("SPS_ACCOUNTINFO.FindByPK", obj);
		if (ret != null)
			return (SpsAccountinfo) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> freeFind(SpsAccountinfo obj) {
		return queryForList("SPS_ACCOUNTINFO.FreeFindSPS_ACCOUNTINFO", obj);
	}

	public int countFreeFind(SpsAccountinfo obj) {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNTINFO.CountFreeFindSPS_ACCOUNTINFO", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> freeFind(SpsAccountinfo obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNTINFO.FreeFindSPS_ACCOUNTINFO", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> freeFind(SpsAccountinfo obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsAccountinfo) {
			_hashmap = ((SpsAccountinfo) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_ACCOUNTINFO.FreeFindSPS_ACCOUNTINFOOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> freeFind(SpsAccountinfo obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsAccountinfo) {
			_hashmap = ((SpsAccountinfo) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_ACCOUNTINFO.FreeFindSPS_ACCOUNTINFOOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsAccountinfo> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsAccountinfo> it = objColl.iterator();
			while (it.hasNext()) {
				SpsAccountinfo oneObj = (SpsAccountinfo) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsAccountinfo vo) {
		SpsAccountinfo obj = (SpsAccountinfo) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsAccountinfo obj) {

		obj.fixup();
		return insert(cti, "SPS_ACCOUNTINFO.InsertSPS_ACCOUNTINFO", obj);
	}

	public int update(ContextInfo cti, SpsAccountinfo obj) {

		obj.fixup();
		return update(cti, "SPS_ACCOUNTINFO.UpdateSPS_ACCOUNTINFO", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsAccountinfo obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_ACCOUNTINFO.DeleteSPS_ACCOUNTINFO", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsAccountinfo obj) {

		obj.fixup();
		SpsAccountinfo oldObj = (SpsAccountinfo) queryForObject("SPS_ACCOUNTINFO.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_ACCOUNTINFO.DeleteSPS_ACCOUNTINFO", oldObj);

	}

	public boolean exists(SpsAccountinfo vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsAccountinfo obj = (SpsAccountinfo) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_ACCOUNTINFO.CountSPS_ACCOUNTINFO", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/19 15:34:17
	/**
	 * 根据spId查询 当前服务商下边的分账列表 按时间倒序
	 */
	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> freeFindBySpId(SpsAccountinfo obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNTINFO.FindBySpId_ACCOUNTINFO", obj, pageIndex, pageSize);
	}

	/**
	 * 根据spId查询 当前服务商下边的分账总数
	 */
	public int countFreeFindBySpId(SpsAccountinfo obj) {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNTINFO.CountFreeFindBySpId_ACCOUNTINFO", obj);
		if (null == ret)
			return 0;
		return ret.intValue();
	}

	/**
	 * 根据spId查询 当前服务商下边的账款总数
	 */
	public int countMoneyFindBySpId(SpsAccountinfo obj) {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNTINFO.CountMoneyFindBySpId_ACCOUNTINFO", obj);
		if (null == ret)
			return 0;
		return ret.intValue();
	}
	/**
	 * 根据SpId 查询 当前服务商 下边所有账款中时间和银行名称
	 */
	@SuppressWarnings("unchecked")
	public List<SpsAccountinfo> findBankNameDateBySpId(Integer obj) {
		return queryForList("SPS_ACCOUNTINFO.findBankNameDateBySpId", obj);
	}
}
