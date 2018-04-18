package com.xfs.wxclassroom.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.wxclassroom.dto.WxClassroomShare;

/**
 * WxClassroomShareDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 15:20:42
 */
@Repository
public class WxClassroomShareDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("WX_CLASSROOM_SHARE.CountFindAllWX_CLASSROOM_SHARE", null );
        return ret.intValue();
	}

	public WxClassroomShare findByPK(WxClassroomShare obj) {
		Object ret = queryForObject("WX_CLASSROOM_SHARE.FindByPK", obj );
		if(ret !=null)
			return (WxClassroomShare)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroomShare> freeFind(WxClassroomShare obj) {
		return queryForList("WX_CLASSROOM_SHARE.FreeFindWX_CLASSROOM_SHARE", obj );
	}

	public int countFreeFind(WxClassroomShare obj) {
        Integer ret = (Integer) queryForObject("WX_CLASSROOM_SHARE.CountFreeFindWX_CLASSROOM_SHARE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroomShare> freeFind(WxClassroomShare obj, int pageIndex, int pageSize) {
		return getPaginatedList("WX_CLASSROOM_SHARE.FreeFindWX_CLASSROOM_SHARE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroomShare> freeFind(WxClassroomShare obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxClassroomShare){
	    	_hashmap = ((WxClassroomShare)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("WX_CLASSROOM_SHARE.FreeFindWX_CLASSROOM_SHAREOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<WxClassroomShare> freeFind(WxClassroomShare obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxClassroomShare){
	    	_hashmap = ((WxClassroomShare)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("WX_CLASSROOM_SHARE.FreeFindWX_CLASSROOM_SHAREOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<WxClassroomShare> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<WxClassroomShare> it = objColl.iterator();
            while ( it.hasNext() ) {
            	WxClassroomShare oneObj = (WxClassroomShare)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, WxClassroomShare vo) {
        WxClassroomShare obj = (WxClassroomShare) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, WxClassroomShare obj) {

		obj.fixup();
        return insert(cti, "WX_CLASSROOM_SHARE.InsertWX_CLASSROOM_SHARE", obj );
	}

	public int update(ContextInfo cti, WxClassroomShare obj) {

		obj.fixup();
		return update(cti, "WX_CLASSROOM_SHARE.UpdateWX_CLASSROOM_SHARE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, WxClassroomShare obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "WX_CLASSROOM_SHARE.DeleteWX_CLASSROOM_SHARE", obj );

	}

	public int removeObjectTree(ContextInfo cti, WxClassroomShare obj) {

        obj.fixup();
        WxClassroomShare oldObj = ( WxClassroomShare )queryForObject("WX_CLASSROOM_SHARE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "WX_CLASSROOM_SHARE.DeleteWX_CLASSROOM_SHARE", oldObj );

	}

	public boolean exists(WxClassroomShare vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        WxClassroomShare obj = (WxClassroomShare) vo;

        keysFilled = keysFilled && ( null != obj.getShareId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("WX_CLASSROOM_SHARE.CountWX_CLASSROOM_SHARE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:42

	public List<WxClassroomShare> findByClassroomPK(int i) {
		List<WxClassroomShare> list = queryForList("WX_CLASSROOM_SHARE.findByClassroomId", i );

		if(list !=null)
			return list;
		else
			return null;
	}
	public int updateTimes(ContextInfo cti, WxClassroomShare obj,int shareTimes,int shareId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("shareTimes",obj.getShareId());
		_hashmap.put("shareId",obj.getShareTimes());

		return update(cti, "WX_CLASSROOM_SHARE.UpdateTimesWX_CLASSROOM_SHARE",_hashmap);

	}

	public WxClassroomShare findByClassIdOpenid (WxClassroomShare obj) {
		Object ret = queryForObject("WX_CLASSROOM_SHARE.FindByClassIdOpenid", obj );
		if(ret !=null)
			return (WxClassroomShare)ret;
		else
			return null;

	}

	//分页
	public int _countFreeFind(WxClassroomShare obj) {
		Integer ret = (Integer) queryForObject("WX_CLASSROOM_SHARE.CountFreeFindWX_CLASSROOM_SHARE", obj );
		return ret.intValue();
	}

	public List<WxClassroomShare> _freeFind(WxClassroomShare obj, int pageIndex, int pageSize) {
		return getPaginatedList("WX_CLASSROOM_SHARE._findByClassroomId", obj, pageIndex, pageSize );
	}

}
