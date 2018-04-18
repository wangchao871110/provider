package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.internal.adaptor.EclipseLazyStarter;
import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsServicearea;

/**
 * SpsServiceareaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/09 14:08:08
 */
@Repository
public class SpsServiceareaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SERVICEAREA.CountFindAllSPS_SERVICEAREA", null );
        return ret.intValue();
	}

	public SpsServicearea findByPK(SpsServicearea obj) {
		Object ret = queryForObject("SPS_SERVICEAREA.FindByPK", obj );
		if(ret !=null)
			return (SpsServicearea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsServicearea> freeFind(SpsServicearea obj) {
		return queryForList("SPS_SERVICEAREA.FreeFindSPS_SERVICEAREA", obj );
	}

	public int countFreeFind(SpsServicearea obj) {
        Integer ret = (Integer) queryForObject("SPS_SERVICEAREA.CountFreeFindSPS_SERVICEAREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsServicearea> freeFind(SpsServicearea obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SERVICEAREA.FreeFindSPS_SERVICEAREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsServicearea> freeFind(SpsServicearea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsServicearea){
	    	_hashmap = ((SpsServicearea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SERVICEAREA.FreeFindSPS_SERVICEAREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsServicearea> freeFind(SpsServicearea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsServicearea){
	    	_hashmap = ((SpsServicearea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SERVICEAREA.FreeFindSPS_SERVICEAREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsServicearea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsServicearea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsServicearea oneObj = (SpsServicearea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsServicearea vo) {
        SpsServicearea obj = (SpsServicearea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsServicearea obj) {

		obj.fixup();
        return insert(cti, "SPS_SERVICEAREA.InsertSPS_SERVICEAREA", obj);
	}

	public int update(ContextInfo cti, SpsServicearea obj) {

		obj.fixup();
		return update(cti, "SPS_SERVICEAREA.UpdateSPS_SERVICEAREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SpsServicearea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti,"SPS_SERVICEAREA.DeleteSPS_SERVICEAREA", obj);

	}

	public int removeObjectTree(ContextInfo cti, SpsServicearea obj) {

        obj.fixup();
        SpsServicearea oldObj = ( SpsServicearea )queryForObject("SPS_SERVICEAREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti,"SPS_SERVICEAREA.DeleteSPS_SERVICEAREA", oldObj );

	}

	public boolean exists(SpsServicearea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsServicearea obj = (SpsServicearea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SERVICEAREA.CountSPS_SERVICEAREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:08

	public List<Map> FreeFindAreaBySpId(Integer spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spId", spId );
		return queryForList("SPS_SERVICEAREA.FreeFindAreaBySpId", _hashmap);
	}
	
	public List<Map> freeFindCOOPAndINNER(String spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spId", spId );
		return queryForList("SPS_SERVICEAREA.FreeFindCOOPAndINNER", _hashmap);
	}



	public List<Map> freeFindGROUP(String spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spId", spId );
		return queryForList("SPS_SERVICEAREA.FreeFindGROUP", _hashmap);
	}

	/**
	 *  获取服务商服务地区列表
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/20 11:53
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/20 11:53
	 *  @updateAuthor  :
	 */
	public List<Map> freeFindSpsServiceArea(SpsServicearea vo){
		return queryForList("SPS_SERVICEAREA.freeFindSpsArea", vo);
	}

	public  int removeSpsServiceArea(ContextInfo cti,String spId,String areaId){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spId", spId );
		_hashmap.put("areaId", areaId );
		return update(cti,"SPS_SERVICEAREA.removeSpsServiceArea", _hashmap);
	}

	public SpsServicearea getSpsServiceArea(SpsServicearea spsServicearea){
		Object ret = queryForObject("SPS_SERVICEAREA.getSpsServiceArea",spsServicearea);
		if(ret != null) {
			return (SpsServicearea) ret;
		}else
			return null;
	}
	
	/**
	 * 服务商的服务区域
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, String>> findServiceAreas(SpsServicearea vo) {
		return queryForList("SPS_SERVICEAREA.FindServiceAreas", vo);
	}

	public List<Map<String, Object>> findServiceAreasBySpId(String spId) {
		return queryForList("SPS_SERVICEAREA.FindServiceAreasBySpId", spId);
	}


	public Map<String,String> findSpsServicearea(SpsServicearea vo){
		Object ret = queryForObject("SPS_SERVICEAREA.findSpsServicearea", vo );
		if(ret !=null)
			return (Map<String,String>)ret;
		else
			return null;
	}
	
}
