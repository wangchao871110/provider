package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsExtdatahead;

/**
 * SpsExtdataheadDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/13 11:44:25
 */
@Repository
public class SpsExtdataheadDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_EXTDATAHEAD.CountFindAllSPS_EXTDATAHEAD", null);
		return ret.intValue();
	}

	public SpsExtdatahead findByPK(SpsExtdatahead obj) {
		Object ret = queryForObject("SPS_EXTDATAHEAD.FindByPK", obj);
		if (ret != null)
			return (SpsExtdatahead) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatahead> freeFind(SpsExtdatahead obj) {
		return queryForList("SPS_EXTDATAHEAD.FreeFindSPS_EXTDATAHEAD", obj);
	}

	public int countFreeFind(SpsExtdatahead obj) {
		Integer ret = (Integer) queryForObject("SPS_EXTDATAHEAD.CountFreeFindSPS_EXTDATAHEAD", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatahead> freeFind(SpsExtdatahead obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EXTDATAHEAD.FreeFindSPS_EXTDATAHEAD", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatahead> freeFind(SpsExtdatahead obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdatahead) {
			_hashmap = ((SpsExtdatahead) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_EXTDATAHEAD.FreeFindSPS_EXTDATAHEADOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatahead> freeFind(SpsExtdatahead obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdatahead) {
			_hashmap = ((SpsExtdatahead) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_EXTDATAHEAD.FreeFindSPS_EXTDATAHEADOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsExtdatahead> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsExtdatahead> it = objColl.iterator();
			while (it.hasNext()) {
				SpsExtdatahead oneObj = (SpsExtdatahead) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsExtdatahead vo) {
		SpsExtdatahead obj = (SpsExtdatahead) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsExtdatahead obj) {

		obj.fixup();
		return insert(cti, "SPS_EXTDATAHEAD.InsertSPS_EXTDATAHEAD", obj);
	}

	public int update(ContextInfo cti, SpsExtdatahead obj) {

		obj.fixup();
		return update(cti, "SPS_EXTDATAHEAD.UpdateSPS_EXTDATAHEAD", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsExtdatahead obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_EXTDATAHEAD.DeleteSPS_EXTDATAHEAD", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsExtdatahead obj) {

		obj.fixup();
		SpsExtdatahead oldObj = (SpsExtdatahead) queryForObject("SPS_EXTDATAHEAD.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_EXTDATAHEAD.DeleteSPS_EXTDATAHEAD", oldObj);

	}

	public boolean exists(SpsExtdatahead vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsExtdatahead obj = (SpsExtdatahead) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_EXTDATAHEAD.CountSPS_EXTDATAHEAD", obj);
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
	public List<SpsExtdatahead> findHeadBySheetIdExttabIdName(SpsExtdatahead vo) {
		return queryForList("SPS_EXTDATAHEAD.FindSPS_EXTDATAHEADBySheetIdExttabIdName", vo);
	}

}
