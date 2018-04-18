package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsAreabusi;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsAreabusiDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/11 16:39:16
 */
@Repository
public class BsAreabusiDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_AREABUSI.CountFindAllBS_AREABUSI", null );
        return ret.intValue();
	}

	public BsAreabusi findByPK(BsAreabusi obj) {
		Object ret = queryForObject("BS_AREABUSI.FindByPK", obj );
		if(ret !=null)
			return (BsAreabusi)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsAreabusi> freeFind(BsAreabusi obj) {
		return queryForList("BS_AREABUSI.FreeFindBS_AREABUSI", obj );
	}

	public int countFreeFind(BsAreabusi obj) {
        Integer ret = (Integer) queryForObject("BS_AREABUSI.CountFreeFindBS_AREABUSI", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsAreabusi> freeFind(BsAreabusi obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_AREABUSI.FreeFindBS_AREABUSI", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsAreabusi> freeFind(BsAreabusi obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsAreabusi){
	    	_hashmap = ((BsAreabusi)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_AREABUSI.FreeFindBS_AREABUSIOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsAreabusi> freeFind(BsAreabusi obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsAreabusi){
	    	_hashmap = ((BsAreabusi)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_AREABUSI.FreeFindBS_AREABUSIOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsAreabusi> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsAreabusi> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsAreabusi oneObj = (BsAreabusi)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsAreabusi vo) {
        BsAreabusi obj = (BsAreabusi) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsAreabusi obj) {

		obj.fixup();
        return insert(cti, "BS_AREABUSI.InsertBS_AREABUSI", obj );
	}

	public int update(ContextInfo cti, BsAreabusi obj) {

		obj.fixup();
		return update(cti, "BS_AREABUSI.UpdateBS_AREABUSI", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsAreabusi obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_AREABUSI.DeleteBS_AREABUSI", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsAreabusi obj) {

        obj.fixup();
        BsAreabusi oldObj = ( BsAreabusi )queryForObject("BS_AREABUSI.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_AREABUSI.DeleteBS_AREABUSI", oldObj );

	}

	public boolean exists(BsAreabusi vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsAreabusi obj = (BsAreabusi) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_AREABUSI.CountBS_AREABUSI", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/11 16:39:16

	@SuppressWarnings("unchecked")
	public List<BsAreabusi> findOpenCityAll() {
		return queryForList("BS_AREABUSI.FindOpenCityAll", null);
	}
	
	public BsAreabusi getBsAreabusi(BsAreabusi bsAreabusi){
		Object ret = queryForObject("BS_AREABUSI.getBsAreabusi",bsAreabusi);
		if (ret != null)
			return (BsAreabusi) ret;
		else
			return null;
	}

	public BsAreabusi findBsAreabusiMax(){
		Object ret = queryForObject("BS_AREABUSI.findBsAreabusiMax",null);
		if (ret != null)
			return (BsAreabusi) ret;
		else
			return null;
	}
	
	/**
	 * 通过业务类型查询城市列表
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findAreasByBusi(BsAreabusi vo) {
		return queryForList("BS_AREABUSI.FindBS_AREABUSI_BY_BUSI", vo);
	}
	
}
