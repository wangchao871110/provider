package com.xfs.wxclassroom.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.wxclassroom.dto.WxClassroom;

/**
 * WxClassroomDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 15:20:43
 */
@Repository
public class WxClassroomDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("WX_CLASSROOM.CountFindAllWX_CLASSROOM", null );
        return ret.intValue();
	}

	public WxClassroom findByPK(WxClassroom obj) {
		Object ret = queryForObject("WX_CLASSROOM.FindByPK", obj );
		if(ret !=null)
			return (WxClassroom)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroom> freeFind(WxClassroom obj) {
		return queryForList("WX_CLASSROOM.FreeFindWX_CLASSROOM", obj );
	}

	public int countFreeFind(WxClassroom obj) {
        Integer ret = (Integer) queryForObject("WX_CLASSROOM.CountFreeFindWX_CLASSROOM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroom> freeFind(WxClassroom obj, int pageIndex, int pageSize) {
		return getPaginatedList("WX_CLASSROOM.FreeFindWX_CLASSROOM", obj, pageIndex, pageSize );
	}

	@SuppressWarnings("unchecked")
	public List<WxClassroom> freeFind(WxClassroom obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxClassroom){
	    	_hashmap = ((WxClassroom)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("WX_CLASSROOM.FreeFindWX_CLASSROOMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroom> freeFind(WxClassroom obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxClassroom){
	    	_hashmap = ((WxClassroom)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("WX_CLASSROOM.FreeFindWX_CLASSROOMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<WxClassroom> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<WxClassroom> it = objColl.iterator();
            while ( it.hasNext() ) {
            	WxClassroom oneObj = (WxClassroom)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, WxClassroom vo) {
        WxClassroom obj = (WxClassroom) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, WxClassroom obj) {

		obj.fixup();
        return insert(cti, "WX_CLASSROOM.InsertWX_CLASSROOM", obj );
	}

	public int update(ContextInfo cti, WxClassroom obj) {

		obj.fixup();
		return update(cti, "WX_CLASSROOM.UpdateWX_CLASSROOM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, WxClassroom obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "WX_CLASSROOM.DeleteWX_CLASSROOM", obj );

	}

	public int removeObjectTree(ContextInfo cti, WxClassroom obj) {

        obj.fixup();
        WxClassroom oldObj = ( WxClassroom )queryForObject("WX_CLASSROOM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "WX_CLASSROOM.DeleteWX_CLASSROOM", oldObj );

	}

	public boolean exists(WxClassroom vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        WxClassroom obj = (WxClassroom) vo;

        keysFilled = keysFilled && ( null != obj.getClassId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("WX_CLASSROOM.CountWX_CLASSROOM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:43


	//为修改为list接受之前

	public  List<WxClassroom> FreeFind(WxClassroom vo) {
		return queryForList("WX_CLASSROOM.FreeFind", null );
	}
	//修改后
	public List<Map<String, Object>> FreeFindAll(WxClassroom vo) {
		return queryForList("WX_CLASSROOM.FreeFindAll", vo );

	}

	public WxClassroom findByClassPK(int i) {
		Object ret = queryForObject("WX_CLASSROOM.FindByPK", i );
		if(ret !=null)
			return (WxClassroom)ret;
		else
			return null;
	}

	//findByTime分页数
	public int countfindByTime(WxClassroom obj) {
		Integer ret = (Integer) queryForObject("WX_CLASSROOM.CountFindByTime", obj );
		return ret.intValue();
	}


	public List<WxClassroom> findByTime(WxClassroom obj, int pageIndex, int pageSize) {
		return getPaginatedList("WX_CLASSROOM.findByTime", obj, pageIndex, pageSize );
	}

}
