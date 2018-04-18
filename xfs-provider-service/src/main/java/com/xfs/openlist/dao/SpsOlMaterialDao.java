package com.xfs.openlist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.openlist.model.SpsOl;
import com.xfs.openlist.model.SpsOlMaterial;

/**
 * SpsOlMaterialDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:56
 */
@Repository
public class SpsOlMaterialDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_OL_MATERIAL.CountFindAllSPS_OL_MATERIAL", null );
        return ret.intValue();
	}

	public SpsOlMaterial findByPK(SpsOlMaterial obj) {
		Object ret = queryForObject("SPS_OL_MATERIAL.FindByPK", obj );
		if(ret !=null)
			return (SpsOlMaterial)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlMaterial> freeFind(SpsOlMaterial obj) {
		return queryForList("SPS_OL_MATERIAL.FreeFindSPS_OL_MATERIAL", obj );
	}

	public int countFreeFind(SpsOlMaterial obj) {
        Integer ret = (Integer) queryForObject("SPS_OL_MATERIAL.CountFreeFindSPS_OL_MATERIAL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlMaterial> freeFind(SpsOlMaterial obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_OL_MATERIAL.FreeFindSPS_OL_MATERIAL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlMaterial> freeFind(SpsOlMaterial obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlMaterial){
	    	_hashmap = ((SpsOlMaterial)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_OL_MATERIAL.FreeFindSPS_OL_MATERIALOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlMaterial> freeFind(SpsOlMaterial obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlMaterial){
	    	_hashmap = ((SpsOlMaterial)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_OL_MATERIAL.FreeFindSPS_OL_MATERIALOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsOlMaterial> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsOlMaterial> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsOlMaterial oneObj = (SpsOlMaterial)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsOlMaterial vo) {
        SpsOlMaterial obj = (SpsOlMaterial) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsOlMaterial obj) {

		obj.fixup();
        return insert(cti, "SPS_OL_MATERIAL.InsertSPS_OL_MATERIAL", obj );
	}

	public int update(ContextInfo cti, SpsOlMaterial obj) {

		obj.fixup();
		return update(cti, "SPS_OL_MATERIAL.UpdateSPS_OL_MATERIAL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsOlMaterial obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_OL_MATERIAL.DeleteSPS_OL_MATERIAL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsOlMaterial obj) {

        obj.fixup();
        SpsOlMaterial oldObj = ( SpsOlMaterial )queryForObject("SPS_OL_MATERIAL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_OL_MATERIAL.DeleteSPS_OL_MATERIAL", oldObj );

	}

	public boolean exists(SpsOlMaterial vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsOlMaterial obj = (SpsOlMaterial) vo;

        keysFilled = keysFilled && ( null != obj.getRelId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_OL_MATERIAL.CountSPS_OL_MATERIAL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

	/**
	 * 根据业务类型id删除业务材料
	 *
	 * @param obj
	 * @return
     */
	public int removeByOlId(ContextInfo cti, SpsOlMaterial obj) {

		if ( obj == null ) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "SPS_OL_MATERIAL.DeleteSPS_OL_MATERIAL_BY_OLID", obj );

	}

	/**
	 * 通过openlist查询相关材料
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findByOlId(SpsOl vo) {
		return queryForList("SPS_OL_MATERIAL.FindSPS_OL_MATERIAL", vo);
	}

	/**
	 * 检查材料是否被使用
	 *
	 * @param vo
	 * @return
     */
	public int checkUesed(SpsOlMaterial vo) {
		Integer ret = (Integer) queryForObject("SPS_OL_MATERIAL.CountSPS_OL_MATERIAL_For_Check", vo);
		return ret.intValue();
	}
	
}
