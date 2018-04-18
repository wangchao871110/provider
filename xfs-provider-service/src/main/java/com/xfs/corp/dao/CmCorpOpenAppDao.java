package com.xfs.corp.dao;
import java.util.*;

import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;
import com.xfs.corp.model.CmCorpOpenApp;

/**
 * CmCorpOpenAppDao
 * @author:yangfw@xinfushe.com
 * @date:2017/08/01 14:40:13
 */
@Repository
public class CmCorpOpenAppDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CORP_OPEN_APP.CountFindAllCM_CORP_OPEN_APP", null );
        return ret.intValue();
	}

	public CmCorpOpenApp findByPK(CmCorpOpenApp obj) {
		Object ret = queryForObject("CM_CORP_OPEN_APP.FindByPK", obj );
		if(ret !=null)
			return (CmCorpOpenApp)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpOpenApp> freeFind(CmCorpOpenApp obj) {
		return queryForList("CM_CORP_OPEN_APP.FreeFindCM_CORP_OPEN_APP", obj );
	}

	public int countFreeFind(CmCorpOpenApp obj) {
        Integer ret = (Integer) queryForObject("CM_CORP_OPEN_APP.CountFreeFindCM_CORP_OPEN_APP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpOpenApp> freeFind(CmCorpOpenApp obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CORP_OPEN_APP.FreeFindCM_CORP_OPEN_APP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpOpenApp> freeFind(CmCorpOpenApp obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmCorpOpenApp){
	    	_hashmap = ((CmCorpOpenApp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CORP_OPEN_APP.FreeFindCM_CORP_OPEN_APPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpOpenApp> freeFind(CmCorpOpenApp obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmCorpOpenApp){
	    	_hashmap = ((CmCorpOpenApp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CORP_OPEN_APP.FreeFindCM_CORP_OPEN_APPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<CmCorpOpenApp> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmCorpOpenApp> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmCorpOpenApp oneObj = (CmCorpOpenApp)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,CmCorpOpenApp vo) {
        CmCorpOpenApp obj = (CmCorpOpenApp) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,CmCorpOpenApp obj) {

		obj.fixup();
        return insert(cti,"CM_CORP_OPEN_APP.InsertCM_CORP_OPEN_APP", obj );
	}

	public int update(ContextInfo cti,CmCorpOpenApp obj) {

		obj.fixup();
		return update(cti, "CM_CORP_OPEN_APP.UpdateCM_CORP_OPEN_APP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,CmCorpOpenApp obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_CORP_OPEN_APP.DeleteCM_CORP_OPEN_APP", obj );

	}

	public int removeObjectTree(ContextInfo cti,CmCorpOpenApp obj) {

        obj.fixup();
        CmCorpOpenApp oldObj = ( CmCorpOpenApp )queryForObject("CM_CORP_OPEN_APP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"CM_CORP_OPEN_APP.DeleteCM_CORP_OPEN_APP", oldObj );

	}

	public boolean exists(CmCorpOpenApp vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmCorpOpenApp obj = (CmCorpOpenApp) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CORP_OPEN_APP.CountCM_CORP_OPEN_APP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/08/01 14:40:13


	public List<CmCorpOpenApp> queryCurrCmCorpApps(CmCorpOpenApp vo){
		return queryForList("CM_CORP_OPEN_APP.queryCurrCmCorpApps", vo);
	}
	
}
