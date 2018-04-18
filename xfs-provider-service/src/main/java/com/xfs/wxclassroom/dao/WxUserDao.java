package com.xfs.wxclassroom.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.wxclassroom.dto.WxUser;

/**
 * WxUserDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/01 18:03:02
 */
@Repository
public class WxUserDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("WX_USER.CountFindAllWX_USER", null );
        return ret.intValue();
	}

	public WxUser findByPK(WxUser obj) {
		Object ret = queryForObject("WX_USER.FindByPK", obj );
		if(ret !=null)
			return (WxUser)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<WxUser> freeFind(WxUser obj) {
		return queryForList("WX_USER.FreeFindWX_USER", obj );
	}

	public int countFreeFind(WxUser obj) {
        Integer ret = (Integer) queryForObject("WX_USER.CountFreeFindWX_USER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<WxUser> freeFind(WxUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("WX_USER.FreeFindWX_USER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<WxUser> freeFind(WxUser obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxUser){
	    	_hashmap = ((WxUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("WX_USER.FreeFindWX_USEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<WxUser> freeFind(WxUser obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof WxUser){
	    	_hashmap = ((WxUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("WX_USER.FreeFindWX_USEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<WxUser> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<WxUser> it = objColl.iterator();
            while ( it.hasNext() ) {
            	WxUser oneObj = (WxUser)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, WxUser vo) {
        WxUser obj = (WxUser) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, WxUser obj) {

		obj.fixup();
        return insert(cti, "WX_USER.InsertWX_USER", obj );
	}

	public int update(ContextInfo cti, WxUser obj) {

		obj.fixup();
		return update(cti, "WX_USER.UpdateWX_USER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, WxUser obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "WX_USER.DeleteWX_USER", obj );

	}

	public int removeObjectTree(ContextInfo cti, WxUser obj) {

        obj.fixup();
        WxUser oldObj = ( WxUser )queryForObject("WX_USER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "WX_USER.DeleteWX_USER", oldObj );

	}

	public boolean exists(WxUser vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        WxUser obj = (WxUser) vo;

        keysFilled = keysFilled && ( null != obj.getOpenid() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("WX_USER.CountWX_USER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/01 18:03:02
	
	
	
}
