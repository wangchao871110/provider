package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsChannelUser;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsChannelUserDao
 * @author:wangchao
 * @date:2016/07/26 11:15:55
 */
@Repository
public class BsChannelUserDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CHANNEL_USER.CountFindAllBS_CHANNEL_USER", null );
        return ret.intValue();
	}

	public BsChannelUser findByPK(BsChannelUser obj) {
		Object ret = queryForObject("BS_CHANNEL_USER.FindByPK", obj );
		if(ret !=null)
			return (BsChannelUser)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelUser> freeFind(BsChannelUser obj) {
		return queryForList("BS_CHANNEL_USER.FreeFindBS_CHANNEL_USER", obj );
	}

	public int countFreeFind(BsChannelUser obj) {
        Integer ret = (Integer) queryForObject("BS_CHANNEL_USER.CountFreeFindBS_CHANNEL_USER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelUser> freeFind(BsChannelUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CHANNEL_USER.FreeFindBS_CHANNEL_USER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelUser> freeFind(BsChannelUser obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannelUser){
	    	_hashmap = ((BsChannelUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_CHANNEL_USER.FreeFindBS_CHANNEL_USEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelUser> freeFind(BsChannelUser obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannelUser){
	    	_hashmap = ((BsChannelUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_CHANNEL_USER.FreeFindBS_CHANNEL_USEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsChannelUser> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsChannelUser> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsChannelUser oneObj = (BsChannelUser)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsChannelUser vo) {
        BsChannelUser obj = (BsChannelUser) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsChannelUser obj) {

		obj.fixup();
        return insert(cti, "BS_CHANNEL_USER.InsertBS_CHANNEL_USER", obj );
	}

	public int update(ContextInfo cti, BsChannelUser obj) {

		obj.fixup();
		return update(cti, "BS_CHANNEL_USER.UpdateBS_CHANNEL_USER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsChannelUser obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_CHANNEL_USER.DeleteBS_CHANNEL_USER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsChannelUser obj) {

        obj.fixup();
        BsChannelUser oldObj = ( BsChannelUser )queryForObject("BS_CHANNEL_USER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_CHANNEL_USER.DeleteBS_CHANNEL_USER", oldObj );

	}

	public boolean exists(BsChannelUser vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsChannelUser obj = (BsChannelUser) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_CHANNEL_USER.CountBS_CHANNEL_USER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:55

	public int countFreeFind1(BsChannelUser obj) {
		Integer ret = (Integer) queryForObject("BS_CHANNEL_USER.CountFreeFindBS_CHANNEL_USER1", obj );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Map> freeFind1(BsChannelUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CHANNEL_USER.FreeFindBS_CHANNEL_USER1", obj, pageIndex, pageSize );
	}
	
}
