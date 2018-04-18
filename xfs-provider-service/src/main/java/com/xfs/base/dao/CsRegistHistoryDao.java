package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.CsRegistHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * CsRegistHistoryDao
 * @author:wangchao
 * @date:2016/07/29 17:32:17
 */
@Repository
public class CsRegistHistoryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CS_REGIST_HISTORY.CountFindAllCS_REGIST_HISTORY", null );
        return ret.intValue();
	}

	public CsRegistHistory findByPK(CsRegistHistory obj) {
		Object ret = queryForObject("CS_REGIST_HISTORY.FindByPK", obj );
		if(ret !=null)
			return (CsRegistHistory)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsRegistHistory> freeFind(CsRegistHistory obj) {
		return queryForList("CS_REGIST_HISTORY.FreeFindCS_REGIST_HISTORY", obj );
	}

	public int countFreeFind(CsRegistHistory obj) {
        Integer ret = (Integer) queryForObject("CS_REGIST_HISTORY.CountFreeFindCS_REGIST_HISTORY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CsRegistHistory> freeFind(CsRegistHistory obj, int pageIndex, int pageSize) {
		return getPaginatedList("CS_REGIST_HISTORY.FreeFindCS_REGIST_HISTORY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CsRegistHistory> freeFind(CsRegistHistory obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsRegistHistory){
	    	_hashmap = ((CsRegistHistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CS_REGIST_HISTORY.FreeFindCS_REGIST_HISTORYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CsRegistHistory> freeFind(CsRegistHistory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CsRegistHistory){
	    	_hashmap = ((CsRegistHistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CS_REGIST_HISTORY.FreeFindCS_REGIST_HISTORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CsRegistHistory> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CsRegistHistory> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CsRegistHistory oneObj = (CsRegistHistory)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CsRegistHistory vo) {
        CsRegistHistory obj = (CsRegistHistory) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CsRegistHistory obj) {

		obj.fixup();
        return insert(cti, "CS_REGIST_HISTORY.InsertCS_REGIST_HISTORY", obj );
	}

	public int update(ContextInfo cti, CsRegistHistory obj) {

		obj.fixup();
		return update(cti, "CS_REGIST_HISTORY.UpdateCS_REGIST_HISTORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CsRegistHistory obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CS_REGIST_HISTORY.DeleteCS_REGIST_HISTORY", obj );

	}

	public int removeObjectTree(ContextInfo cti, CsRegistHistory obj) {

        obj.fixup();
        CsRegistHistory oldObj = ( CsRegistHistory )queryForObject("CS_REGIST_HISTORY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CS_REGIST_HISTORY.DeleteCS_REGIST_HISTORY", oldObj );

	}

	public boolean exists(CsRegistHistory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CsRegistHistory obj = (CsRegistHistory) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CS_REGIST_HISTORY.CountCS_REGIST_HISTORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/29 17:32:17
	
	public Integer findRegisterCount(CsRegistHistory registHistory) {
		Integer ret = (Integer) queryForObject("CS_REGIST_HISTORY.CountFreeFindRegistHistory", registHistory );
        return ret.intValue();
	}
	
	public List<Map<String, Object>> findRegitCorpMsg(Map map,int pageIndex, int pageSize){
		return getPaginatedList("CS_REGIST_HISTORY.findRegitCorpMsg", map, pageIndex, pageSize);
	}
	public Integer findRegitCorpMsgCount(Map map){
		Integer ret = (Integer) queryForObject("CS_REGIST_HISTORY.findRegitCorpMsgCount", map );
        return ret.intValue();

	}
	/**
	 * 查询渠道注册的企业
	 *
	 * @param channel
	 * @param sname
	 * @return
	 */
	public List<Map<String, Object>> findRegistCorps(String channel, String sname) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("channel", channel);
		_hashmap.put("sname", sname);
		return (List)queryForList("CS_REGIST_HISTORY.FindRegistCorpByChannel", _hashmap);
	}
}
