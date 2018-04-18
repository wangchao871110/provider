package com.xfs.openlist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsAreamaterial;

/**
 * SpsAreamaterialDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:54
 */
@Repository
public class SpsAreamaterialDao extends BaseSqlMapDao {

	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_AREAMATERIAL.CountFindAllSPS_AREAMATERIAL", null );
        return ret.intValue();
	}

	public SpsAreamaterial findByPK(SpsAreamaterial obj) {
		Object ret = queryForObject("SPS_AREAMATERIAL.FindByPK", obj );
		if(ret !=null)
			return (SpsAreamaterial)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAreamaterial> freeFind(SpsAreamaterial obj) {
		return queryForList("SPS_AREAMATERIAL.FreeFindSPS_AREAMATERIAL", obj );
	}

	public int countFreeFind(SpsAreamaterial obj) {
        Integer ret = (Integer) queryForObject("SPS_AREAMATERIAL.CountFreeFindSPS_AREAMATERIAL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAreamaterial> freeFind(SpsAreamaterial obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_AREAMATERIAL.FreeFindSPS_AREAMATERIAL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAreamaterial> freeFind(SpsAreamaterial obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAreamaterial){
	    	_hashmap = ((SpsAreamaterial)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_AREAMATERIAL.FreeFindSPS_AREAMATERIALOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAreamaterial> freeFind(SpsAreamaterial obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAreamaterial){
	    	_hashmap = ((SpsAreamaterial)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_AREAMATERIAL.FreeFindSPS_AREAMATERIALOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsAreamaterial> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsAreamaterial> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsAreamaterial oneObj = (SpsAreamaterial)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsAreamaterial vo) {
        SpsAreamaterial obj = (SpsAreamaterial) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsAreamaterial obj) {

		obj.fixup();
        return insert(cti, "SPS_AREAMATERIAL.InsertSPS_AREAMATERIAL", obj );
	}

	public int update(ContextInfo cti, SpsAreamaterial obj) {

		obj.fixup();
		return update(cti, "SPS_AREAMATERIAL.UpdateSPS_AREAMATERIAL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsAreamaterial obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_AREAMATERIAL.DeleteSPS_AREAMATERIAL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsAreamaterial obj) {

        obj.fixup();
        SpsAreamaterial oldObj = ( SpsAreamaterial )queryForObject("SPS_AREAMATERIAL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_AREAMATERIAL.DeleteSPS_AREAMATERIAL", oldObj );

	}

	public boolean exists(SpsAreamaterial vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsAreamaterial obj = (SpsAreamaterial) vo;

        keysFilled = keysFilled && ( null != obj.getMaterialId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_AREAMATERIAL.CountSPS_AREAMATERIAL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:54

	/**
	 * 根据区域和材料名称获取区域材料
	 *
	 * @param vo
	 * @return
     */
	public SpsAreamaterial findByAraeAndName(SpsAreamaterial vo) {
		 Object ret = queryForObject("SPS_AREAMATERIAL.findByAraeAndName", vo);
		if(ret !=null)
			return (SpsAreamaterial)ret;
		else
			return null;
	}

	public List<SpsAreamaterial> findOlMaterialList(Integer olid,Integer materialType){
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("material_type",materialType);
		hashmap.put("olid",olid);
		return queryForList("Find_OL_Material_List",hashmap);
	}

}
