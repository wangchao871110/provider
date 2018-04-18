package com.xfs.report.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.report.model.BsSeach;

@Repository
public class BsSeachDao extends BaseSqlMapDao {

	public int countFindAll() {
		Integer ret = (Integer) queryForObject("BsSeachTable.CountFindAllBS_SEACH", null );
		return ret.intValue();
	}

	public BsSeach findByPK(BsSeach obj) {
		Object ret = queryForObject("BsSeachTable.FindByPK", obj );
		if(ret !=null)
			return (BsSeach)ret;
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<BsSeach> freeFind(BsSeach obj) {
		return queryForList("BsSeachTable.FreeFindBS_SEACH", obj );
	}

	public int countFreeFind(BsSeach obj) {
		Integer ret = (Integer) queryForObject("BsSeachTable.CountFreeFindBS_SEACH", obj );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<BsSeach> freeFind(BsSeach obj, int pageIndex, int pageSize) {
		return getPaginatedList("BsSeachTable.FreeFindBS_SEACH", obj, pageIndex, pageSize );
	}

	@SuppressWarnings("unchecked")
	public List<BsSeach> freeFind(BsSeach obj, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof BsSeach){
			_hashmap = ((BsSeach)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return queryForList("BsSeachTable.FreeFindBS_SEACHOrdered", _hashmap);
	}

	@SuppressWarnings("unchecked")
	public List<BsSeach> freeFind(BsSeach obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof BsSeach){
			_hashmap = ((BsSeach)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return getPaginatedList("BsSeachTable.FreeFindBS_SEACHOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsSeach> objColl) {
		int i = 0;
		if ( objColl != null ) {
			Iterator<BsSeach> it = objColl.iterator();
			while ( it.hasNext() ) {
				BsSeach oneObj = (BsSeach)it.next();
				i += save(cti, oneObj);
			}
		}
		return i;
	}

	public int save(ContextInfo cti, BsSeach vo) {
		BsSeach obj = (BsSeach) vo;

		if(exists( obj ) ) {
			return update(cti, obj);
		} else {
			return insert(cti, obj);
		}
	}

	public int insert(ContextInfo cti, BsSeach obj) {

		obj.fixup();
		return insert(cti, "BsSeachTable.InsertBS_SEACH", obj );
	}

	public int update(ContextInfo cti, BsSeach obj) {

		obj.fixup();
		return update(cti, "BsSeachTable.UpdateBS_SEACH", obj );

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsSeach obj) {

		if ( obj == null ) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "BsSeachTable.DeleteBS_SEACH", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsSeach obj) {

		obj.fixup();
		BsSeach oldObj = ( BsSeach )queryForObject("BsSeachTable.FindByPK", obj );
		if ( oldObj == null ) {
			return 0;
		}


		return delete(cti, "BsSeachTable.DeleteBS_SEACH", oldObj );

	}

	public boolean exists(BsSeach vo) {

		boolean keysFilled = true;
		boolean ret = false;
		BsSeach obj = (BsSeach) vo;

		keysFilled = keysFilled && ( null != obj.getId() );

		if ( keysFilled ) {
			Integer retInt=(Integer)queryForObject("BsSeachTable.CountBS_SEACH", obj );
			if ( retInt != null && retInt.intValue() > 0 ) {
				ret = true;
			}
		}

		return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/10 14:48:42

	public List<HashMap<String, Object>> findAllSeach() {
		return queryForList("BsSeachTable.findAllSeach", null);
	}

	public List<HashMap<String, Object>> findSeach(Integer seachId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("seachId", seachId);
		return queryForList("BsSeachTable.findSeach", hashMap);
	}

	public List<HashMap<String, Object>> findTableData(String sqltext, int pageIndex, int pageSize) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sqltext", sqltext);
		return getPaginatedList("BsSeachTable.findTableData", hashMap, pageIndex, pageSize);
	}

	public Integer countData(String sqltext) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sqltext", sqltext);
		Integer ret = (Integer) queryForObject("BsSeachTable.countData", hashMap);
		return ret.intValue();
	}


	public List<HashMap<String, Object>> findTableData(String sqltext) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sqltext", sqltext);
		return queryForList("BsSeachTable.findTableData", hashMap);
	}

}
