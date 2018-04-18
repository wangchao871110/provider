package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsExtdatasheet;

/**
 * SpsExtdatasheetDao
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/17 19:55:26
 */
@Repository
public class SpsExtdatasheetDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SPS_EXTDATASHEET.CountFindAllSPS_EXTDATASHEET", null);
		return ret.intValue();
	}

	public SpsExtdatasheet findByPK(SpsExtdatasheet obj) {
		Object ret = queryForObject("SPS_EXTDATASHEET.FindByPK", obj);
		if (ret != null)
			return (SpsExtdatasheet) ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatasheet> freeFind(SpsExtdatasheet obj) {
		return queryForList("SPS_EXTDATASHEET.FreeFindSPS_EXTDATASHEET", obj);
	}

	public int countFreeFind(SpsExtdatasheet obj) {
		Integer ret = (Integer) queryForObject("SPS_EXTDATASHEET.CountFreeFindSPS_EXTDATASHEET", obj);
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatasheet> freeFind(SpsExtdatasheet obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_EXTDATASHEET.FreeFindSPS_EXTDATASHEET", obj, pageIndex, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatasheet> freeFind(SpsExtdatasheet obj, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdatasheet) {
			_hashmap = ((SpsExtdatasheet) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SPS_EXTDATASHEET.FreeFindSPS_EXTDATASHEETOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsExtdatasheet> freeFind(SpsExtdatasheet obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		if (obj instanceof SpsExtdatasheet) {
			_hashmap = ((SpsExtdatasheet) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SPS_EXTDATASHEET.FreeFindSPS_EXTDATASHEETOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<SpsExtdatasheet> objColl) {
		int i = 0;
		if (objColl != null) {
			Iterator<SpsExtdatasheet> it = objColl.iterator();
			while (it.hasNext()) {
				SpsExtdatasheet oneObj = (SpsExtdatasheet) it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, SpsExtdatasheet vo) {
		SpsExtdatasheet obj = (SpsExtdatasheet) vo;

		if (exists(obj)) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, SpsExtdatasheet obj) {

		obj.fixup();
		return insert(cti, "SPS_EXTDATASHEET.InsertSPS_EXTDATASHEET", obj);
	}

	public int update(ContextInfo cti, SpsExtdatasheet obj) {

		obj.fixup();
		return update(cti, "SPS_EXTDATASHEET.UpdateSPS_EXTDATASHEET", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsExtdatasheet obj) {

		if (obj == null) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_EXTDATASHEET.DeleteSPS_EXTDATASHEET", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsExtdatasheet obj) {

		obj.fixup();
		SpsExtdatasheet oldObj = (SpsExtdatasheet) queryForObject("SPS_EXTDATASHEET.FindByPK", obj);
		if (oldObj == null) {
			return 0;
		}

		return delete(cti, "SPS_EXTDATASHEET.DeleteSPS_EXTDATASHEET", oldObj);

	}

	public boolean exists(SpsExtdatasheet vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SpsExtdatasheet obj = (SpsExtdatasheet) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SPS_EXTDATASHEET.CountSPS_EXTDATASHEET", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/17 19:55:26
	@SuppressWarnings("unchecked")
	public List<SpsExtdatasheet> findSheetByExcelId(Integer excelId) {

		return queryForList("SPS_EXTDATASHEET.FindExcelSheetByExcelId", excelId);
	}
}
