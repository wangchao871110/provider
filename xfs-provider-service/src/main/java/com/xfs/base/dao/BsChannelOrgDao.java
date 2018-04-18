package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsChannelOrg;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsChannelOrgDao
 * @author:wangchao
 * @date:2016/07/29 10:55:28
 */
@Repository
public class BsChannelOrgDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CHANNEL_ORG.CountFindAllBS_CHANNEL_ORG", null );
        return ret.intValue();
	}

	public BsChannelOrg findByPK(BsChannelOrg obj) {
		Object ret = queryForObject("BS_CHANNEL_ORG.FindByPK", obj );
		if(ret !=null)
			return (BsChannelOrg)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelOrg> freeFind(BsChannelOrg obj) {
		return queryForList("BS_CHANNEL_ORG.FreeFindBS_CHANNEL_ORG", obj );
	}

	public int countFreeFind(BsChannelOrg obj) {
        Integer ret = (Integer) queryForObject("BS_CHANNEL_ORG.CountFreeFindBS_CHANNEL_ORG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelOrg> freeFind(BsChannelOrg obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CHANNEL_ORG.FreeFindBS_CHANNEL_ORG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelOrg> freeFind(BsChannelOrg obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannelOrg){
	    	_hashmap = ((BsChannelOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_CHANNEL_ORG.FreeFindBS_CHANNEL_ORGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannelOrg> freeFind(BsChannelOrg obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannelOrg){
	    	_hashmap = ((BsChannelOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_CHANNEL_ORG.FreeFindBS_CHANNEL_ORGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsChannelOrg> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsChannelOrg> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsChannelOrg oneObj = (BsChannelOrg)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsChannelOrg vo) {
        BsChannelOrg obj = (BsChannelOrg) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsChannelOrg obj) {

		obj.fixup();
        return insert(cti, "BS_CHANNEL_ORG.InsertBS_CHANNEL_ORG", obj );
	}

	public int update(ContextInfo cti, BsChannelOrg obj) {

		obj.fixup();
		return update(cti, "BS_CHANNEL_ORG.UpdateBS_CHANNEL_ORG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsChannelOrg obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_CHANNEL_ORG.DeleteBS_CHANNEL_ORG", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsChannelOrg obj) {

        obj.fixup();
        BsChannelOrg oldObj = ( BsChannelOrg )queryForObject("BS_CHANNEL_ORG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_CHANNEL_ORG.DeleteBS_CHANNEL_ORG", oldObj );

	}

	public boolean exists(BsChannelOrg vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsChannelOrg obj = (BsChannelOrg) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_CHANNEL_ORG.CountBS_CHANNEL_ORG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/29 10:55:28
	
	
	
}
