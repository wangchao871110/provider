package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsExtdata;

/**
 * SpsExtdataDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/13 11:44:25
 */
@Repository
public class SpsExtdataDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_EXTDATA.CountFindAllSPS_EXTDATA", null);
		return ret.intValue();
	}

	public SpsExtdata findByPK(SpsExtdata obj) {
		Object ret = queryForObject("SPS_EXTDATA.FindByPK", obj);
		if (ret != null)
			return (SpsExtdata) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdata> freeFind(SpsExtdata obj) {
		return queryForList("SPS_EXTDATA.FreeFindSPS_EXTDATA", obj);
	}

	public int countFreeFind(SpsExtdata obj) {
		Integer ret = (Integer) queryForObject("SPS_EXTDATA.CountFreeFindSPS_EXTDATA", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdata> freeFind(SpsExtdata obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EXTDATA.FreeFindSPS_EXTDATA", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdata> freeFind(SpsExtdata obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdata) {
			_hashmap = ((SpsExtdata) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_EXTDATA.FreeFindSPS_EXTDATAOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdata> freeFind(SpsExtdata obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdata) {
			_hashmap = ((SpsExtdata) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_EXTDATA.FreeFindSPS_EXTDATAOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsExtdata> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsExtdata> it = objColl.iterator();
			while (it.hasNext()) {
				SpsExtdata oneObj = (SpsExtdata) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsExtdata vo) {
		SpsExtdata obj = (SpsExtdata) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsExtdata obj) {

		obj.fixup();
		return insert(cti, "SPS_EXTDATA.InsertSPS_EXTDATA", obj);
	}

	public int update(ContextInfo cti, SpsExtdata obj) {

		obj.fixup();
		return update(cti, "SPS_EXTDATA.UpdateSPS_EXTDATA", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsExtdata obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_EXTDATA.DeleteSPS_EXTDATA", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsExtdata obj) {

		obj.fixup();
		SpsExtdata oldObj = (SpsExtdata) queryForObject("SPS_EXTDATA.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_EXTDATA.DeleteSPS_EXTDATA", oldObj);

	}

	public boolean exists(SpsExtdata vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsExtdata obj = (SpsExtdata) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_EXTDATA.CountSPS_EXTDATA", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/13 11:44:25

	@SuppressWarnings("unchecked")
	public List<SpsExtdata> freeFindBySpId(SpsExtdata obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EXTDATA.FreeFindSPS_EXTDATABySpId", obj, pageIndex, pageSize);
	}

	public int countFindBySpId(SpsExtdata obj) {
		Integer ret = (Integer) queryForObject("SPS_EXTDATA.CountFind_SPS_EXTDATA_BySpId", obj);
		return ret.intValue();
	}
}
