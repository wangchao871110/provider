package com.xfs.openlist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.openlist.model.SpsOlAreaauth;

/**
 * SpsOlAreaauthDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:55
 */
@Repository
public class SpsOlAreaauthDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_OL_AREAAUTH.CountFindAllSPS_OL_AREAAUTH", null );
        return ret.intValue();
	}

	public SpsOlAreaauth findByPK(SpsOlAreaauth obj) {
		Object ret = queryForObject("SPS_OL_AREAAUTH.FindByPK", obj );
		if(ret !=null)
			return (SpsOlAreaauth)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlAreaauth> freeFind(SpsOlAreaauth obj) {
		return queryForList("SPS_OL_AREAAUTH.FreeFindSPS_OL_AREAAUTH", obj );
	}

	public int countFreeFind(SpsOlAreaauth obj) {
        Integer ret = (Integer) queryForObject("SPS_OL_AREAAUTH.CountFreeFindSPS_OL_AREAAUTH", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlAreaauth> freeFind(SpsOlAreaauth obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_OL_AREAAUTH.FreeFindSPS_OL_AREAAUTH", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlAreaauth> freeFind(SpsOlAreaauth obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlAreaauth){
	    	_hashmap = ((SpsOlAreaauth)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_OL_AREAAUTH.FreeFindSPS_OL_AREAAUTHOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlAreaauth> freeFind(SpsOlAreaauth obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlAreaauth){
	    	_hashmap = ((SpsOlAreaauth)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_OL_AREAAUTH.FreeFindSPS_OL_AREAAUTHOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsOlAreaauth> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsOlAreaauth> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsOlAreaauth oneObj = (SpsOlAreaauth)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsOlAreaauth vo) {
        SpsOlAreaauth obj = (SpsOlAreaauth) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsOlAreaauth obj) {

		obj.fixup();
        return insert(cti, "SPS_OL_AREAAUTH.InsertSPS_OL_AREAAUTH", obj );
	}

	public int update(ContextInfo cti, SpsOlAreaauth obj) {

		obj.fixup();
		return update(cti, "SPS_OL_AREAAUTH.UpdateSPS_OL_AREAAUTH", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsOlAreaauth obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_OL_AREAAUTH.DeleteSPS_OL_AREAAUTH", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsOlAreaauth obj) {

        obj.fixup();
        SpsOlAreaauth oldObj = ( SpsOlAreaauth )queryForObject("SPS_OL_AREAAUTH.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_OL_AREAAUTH.DeleteSPS_OL_AREAAUTH", oldObj );

	}

	public boolean exists(SpsOlAreaauth vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsOlAreaauth obj = (SpsOlAreaauth) vo;

        keysFilled = keysFilled && ( null != obj.getAreaauthId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_OL_AREAAUTH.CountSPS_OL_AREAAUTH", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

	/**
	 * 获取权限区域
     *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findAuthAreas(SpsOlAreaauth vo) {
		return queryForList("SPS_OL_AREAAUTH.FindSPS_OL_AREAAUTH_AREA", vo);
	}
	
}
