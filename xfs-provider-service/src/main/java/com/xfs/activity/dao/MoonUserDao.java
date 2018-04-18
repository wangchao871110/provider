package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.MoonUser;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * MoonUserDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 11:20:20
 */
@Repository
public class MoonUserDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("MOON_USER.CountFindAllMOON_USER", null );
        return ret.intValue();
	}

	public MoonUser findByPK(MoonUser obj) {
		Object ret = queryForObject("MOON_USER.FindByPK", obj );
		if(ret !=null)
			return (MoonUser)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUser> freeFind(MoonUser obj) {
		return queryForList("MOON_USER.FreeFindMOON_USER", obj );
	}

	public int countFreeFind(MoonUser obj) {
        Integer ret = (Integer) queryForObject("MOON_USER.CountFreeFindMOON_USER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUser> freeFind(MoonUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("MOON_USER.FreeFindMOON_USER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUser> freeFind(MoonUser obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonUser){
	    	_hashmap = ((MoonUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("MOON_USER.FreeFindMOON_USEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUser> freeFind(MoonUser obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonUser){
	    	_hashmap = ((MoonUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("MOON_USER.FreeFindMOON_USEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<MoonUser> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<MoonUser> it = objColl.iterator();
            while ( it.hasNext() ) {
            	MoonUser oneObj = (MoonUser)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, MoonUser vo) {
        MoonUser obj = (MoonUser) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, MoonUser obj) {

		obj.fixup();
        return insert(cti, "MOON_USER.InsertMOON_USER", obj );
	}

	public int update(ContextInfo cti, MoonUser obj) {

		obj.fixup();
		return update(cti, "MOON_USER.UpdateMOON_USER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, MoonUser obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "MOON_USER.DeleteMOON_USER", obj );

	}

	public int removeObjectTree(ContextInfo cti, MoonUser obj) {

        obj.fixup();
        MoonUser oldObj = ( MoonUser )queryForObject("MOON_USER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "MOON_USER.DeleteMOON_USER", oldObj );

	}

	public boolean exists(MoonUser vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        MoonUser obj = (MoonUser) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("MOON_USER.CountMOON_USER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:20

	public int updateShareNum(ContextInfo cti, MoonUser obj) {

		obj.fixup();
		return update(cti, "MOON_USER.UpdateShareNum", obj );

	}
	public MoonUser queryPic(MoonUser obj) {
		Object ret = queryForObject("MOON_USER.QueryPic", obj );
		if(ret !=null)
			return (MoonUser)ret;
		else
			return null;
	}

	public int updateUserState(ContextInfo cti, MoonUser obj) {

		obj.fixup();
		return update(cti, "MOON_USER.UpdateUserState", obj );

	}
	public int updateRemark(ContextInfo cti, MoonUser obj) {

		obj.fixup();
		return update(cti, "MOON_USER.UpdateRemark", obj );

	}
}
