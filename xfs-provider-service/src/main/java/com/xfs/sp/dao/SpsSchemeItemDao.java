package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsSchemeItem;

/**
 * SpsSchemeItemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:26:36
 */
@Repository
public class SpsSchemeItemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_ITEM.CountFindAllSPS_SCHEME_ITEM", null );
        return ret.intValue();
	}

	public SpsSchemeItem findByPK(SpsSchemeItem obj) {
		Object ret = queryForObject("SPS_SCHEME_ITEM.FindByPK", obj );
		if(ret !=null)
			return (SpsSchemeItem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeItem> freeFind(SpsSchemeItem obj) {
		return queryForList("SPS_SCHEME_ITEM.FreeFindSPS_SCHEME_ITEM", obj );
	}

	public int countFreeFind(SpsSchemeItem obj) {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_ITEM.CountFreeFindSPS_SCHEME_ITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeItem> freeFind(SpsSchemeItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SCHEME_ITEM.FreeFindSPS_SCHEME_ITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeItem> freeFind(SpsSchemeItem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeItem){
	    	_hashmap = ((SpsSchemeItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SCHEME_ITEM.FreeFindSPS_SCHEME_ITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeItem> freeFind(SpsSchemeItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeItem){
	    	_hashmap = ((SpsSchemeItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SCHEME_ITEM.FreeFindSPS_SCHEME_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsSchemeItem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsSchemeItem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsSchemeItem oneObj = (SpsSchemeItem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsSchemeItem vo) {
        SpsSchemeItem obj = (SpsSchemeItem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsSchemeItem obj) {

		obj.fixup();
        return insert(cti, "SPS_SCHEME_ITEM.InsertSPS_SCHEME_ITEM", obj );
	}

	public int update(ContextInfo cti, SpsSchemeItem obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME_ITEM.UpdateSPS_SCHEME_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsSchemeItem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SCHEME_ITEM.DeleteSPS_SCHEME_ITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsSchemeItem obj) {

        obj.fixup();
        SpsSchemeItem oldObj = ( SpsSchemeItem )queryForObject("SPS_SCHEME_ITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SCHEME_ITEM.DeleteSPS_SCHEME_ITEM", oldObj );

	}

	public boolean exists(SpsSchemeItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsSchemeItem obj = (SpsSchemeItem) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SCHEME_ITEM.CountSPS_SCHEME_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:36
	/**
	 * 根据 方案id 查询 方案的项目
	 *
	 * @author lifq
	 *
	 * 2016年8月8日  下午5:11:02
	 */
	public List<Map<String,Object>> findItemsBySchemeId(Integer scheme_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("scheme_id",scheme_id);
        return queryForList("SPS_SCHEME_ITEM.findItemsBySchemeId", _hashMap);
	}
	
	
}
