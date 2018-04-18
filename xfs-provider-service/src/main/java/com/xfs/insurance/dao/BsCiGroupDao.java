package com.xfs.insurance.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiGroup;
import com.xfs.insurance.model.BsCiGroup;

/**
 * BsCiGroupDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 18:33:04
 */
@Repository
public class BsCiGroupDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CI_GROUP.CountFindAllBS_CI_GROUP", null );
        return ret.intValue();
	}

	public BsCiGroup findByPK(BsCiGroup obj) {
		Object ret = queryForObject("BS_CI_GROUP.FindByPK", obj );
		if(ret !=null)
			return (BsCiGroup)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiGroup> freeFind(BsCiGroup obj) {
		return queryForList("BS_CI_GROUP.FreeFindBS_CI_GROUP", obj );
	}

	public int countFreeFind(BsCiGroup obj) {
        Integer ret = (Integer) queryForObject("BS_CI_GROUP.CountFreeFindBS_CI_GROUP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiGroup> freeFind(BsCiGroup obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CI_GROUP.FreeFindBS_CI_GROUP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiGroup> freeFind(BsCiGroup obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsCiGroup){
	    	_hashmap = ((BsCiGroup)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_CI_GROUP.FreeFindBS_CI_GROUPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsCiGroup> freeFind(BsCiGroup obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsCiGroup){
	    	_hashmap = ((BsCiGroup)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_CI_GROUP.FreeFindBS_CI_GROUPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsCiGroup> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsCiGroup> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsCiGroup oneObj = (BsCiGroup)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsCiGroup vo) {
        BsCiGroup obj = (BsCiGroup) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsCiGroup obj) {

		obj.fixup();
        return insert(cti, "BS_CI_GROUP.InsertBS_CI_GROUP", obj );
	}

	public int update(ContextInfo cti, BsCiGroup obj) {

		obj.fixup();
		return update(cti, "BS_CI_GROUP.UpdateBS_CI_GROUP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsCiGroup obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_CI_GROUP.DeleteBS_CI_GROUP", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsCiGroup obj) {

        obj.fixup();
        BsCiGroup oldObj = ( BsCiGroup )queryForObject("BS_CI_GROUP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_CI_GROUP.DeleteBS_CI_GROUP", oldObj );

	}

	public boolean exists(BsCiGroup vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsCiGroup obj = (BsCiGroup) vo;

        keysFilled = keysFilled && ( null != obj.getGroupId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_CI_GROUP.CountBS_CI_GROUP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 18:33:04
	public List<CiGroup> findAllCIGroupWithSp(CiGroup ciGroup) {
		return queryForList("BS_CI_GROUP.findAllCIGroupWithSp", ciGroup);
	}


}
