package com.xfs.ts.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsAutoPage;

/**
 * SpsTsAutoPageDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/14 12:24:41
 */
@Repository
public class SpsTsAutoPageDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TS_AUTO_PAGE.CountFindAllSPS_TS_AUTO_PAGE", null );
        return ret.intValue();
	}

	public SpsTsAutoPage findByPK(SpsTsAutoPage obj) {
		Object ret = queryForObject("SPS_TS_AUTO_PAGE.FindByPK", obj );
		if(ret !=null)
			return (SpsTsAutoPage)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoPage> freeFind(SpsTsAutoPage obj) {
		return queryForList("SPS_TS_AUTO_PAGE.FreeFindSPS_TS_AUTO_PAGE", obj );
	}

	public int countFreeFind(SpsTsAutoPage obj) {
        Integer ret = (Integer) queryForObject("SPS_TS_AUTO_PAGE.CountFreeFindSPS_TS_AUTO_PAGE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoPage> freeFind(SpsTsAutoPage obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TS_AUTO_PAGE.FreeFindSPS_TS_AUTO_PAGE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoPage> freeFind(SpsTsAutoPage obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsAutoPage){
	    	_hashmap = ((SpsTsAutoPage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_TS_AUTO_PAGE.FreeFindSPS_TS_AUTO_PAGEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsAutoPage> freeFind(SpsTsAutoPage obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsAutoPage){
	    	_hashmap = ((SpsTsAutoPage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_TS_AUTO_PAGE.FreeFindSPS_TS_AUTO_PAGEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsTsAutoPage> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsTsAutoPage> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsTsAutoPage oneObj = (SpsTsAutoPage)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsTsAutoPage vo) {
        SpsTsAutoPage obj = (SpsTsAutoPage) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsTsAutoPage obj) {

		obj.fixup();
        return insert(cti, "SPS_TS_AUTO_PAGE.InsertSPS_TS_AUTO_PAGE", obj );
	}

	public int update(ContextInfo cti, SpsTsAutoPage obj) {

		obj.fixup();
		return update(cti, "SPS_TS_AUTO_PAGE.UpdateSPS_TS_AUTO_PAGE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsTsAutoPage obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_TS_AUTO_PAGE.DeleteSPS_TS_AUTO_PAGE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsTsAutoPage obj) {

        obj.fixup();
        SpsTsAutoPage oldObj = ( SpsTsAutoPage )queryForObject("SPS_TS_AUTO_PAGE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_TS_AUTO_PAGE.DeleteSPS_TS_AUTO_PAGE", oldObj );

	}

	public boolean exists(SpsTsAutoPage vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsTsAutoPage obj = (SpsTsAutoPage) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_TS_AUTO_PAGE.CountSPS_TS_AUTO_PAGE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/14 12:24:41


	public SpsTsAutoPage findSpsTsAutoPage(SpsTsAutoPage vo){
		Object ret = queryForObject("SPS_TS_AUTO_PAGE.findSpsTsAutoPage",vo);
		if(ret !=null)
			return (SpsTsAutoPage)ret;
		else
			return null;
	}
}
