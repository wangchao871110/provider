package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.CmContractAttitem;

/**
 * CmContractAttitemDao
 * @author:wangchao
 * @date:2016/08/02 10:14:25
 */
@Repository
public class CmContractAttitemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_ATTITEM.CountFindAllCM_CONTRACT_ATTITEM", null );
        return ret.intValue();
	}

	public CmContractAttitem findByPK(CmContractAttitem obj) {
		Object ret = queryForObject("CM_CONTRACT_ATTITEM.FindByPK", obj );
		if(ret !=null)
			return (CmContractAttitem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAttitem> freeFind(CmContractAttitem obj) {
		return queryForList("CM_CONTRACT_ATTITEM.FreeFindCM_CONTRACT_ATTITEM", obj );
	}

	public int countFreeFind(CmContractAttitem obj) {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_ATTITEM.CountFreeFindCM_CONTRACT_ATTITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAttitem> freeFind(CmContractAttitem obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CONTRACT_ATTITEM.FreeFindCM_CONTRACT_ATTITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAttitem> freeFind(CmContractAttitem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractAttitem){
	    	_hashmap = ((CmContractAttitem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CONTRACT_ATTITEM.FreeFindCM_CONTRACT_ATTITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAttitem> freeFind(CmContractAttitem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractAttitem){
	    	_hashmap = ((CmContractAttitem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CONTRACT_ATTITEM.FreeFindCM_CONTRACT_ATTITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmContractAttitem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmContractAttitem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmContractAttitem oneObj = (CmContractAttitem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CmContractAttitem vo) {
        CmContractAttitem obj = (CmContractAttitem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CmContractAttitem obj) {

		obj.fixup();
        return insert(cti, "CM_CONTRACT_ATTITEM.InsertCM_CONTRACT_ATTITEM", obj );
	}

	public int update(ContextInfo cti, CmContractAttitem obj) {

		obj.fixup();
		return update(cti, "CM_CONTRACT_ATTITEM.UpdateCM_CONTRACT_ATTITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmContractAttitem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_CONTRACT_ATTITEM.DeleteCM_CONTRACT_ATTITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, CmContractAttitem obj) {

        obj.fixup();
        CmContractAttitem oldObj = ( CmContractAttitem )queryForObject("CM_CONTRACT_ATTITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CM_CONTRACT_ATTITEM.DeleteCM_CONTRACT_ATTITEM", oldObj );

	}

	public boolean exists(CmContractAttitem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmContractAttitem obj = (CmContractAttitem) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CONTRACT_ATTITEM.CountCM_CONTRACT_ATTITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:25
	
	public int deleteByContractAttId(ContextInfo cti, Integer contractAttId) {
		CmContractAttitem obj = new CmContractAttitem();
		obj.fixup();
		obj.setContractAttId(contractAttId);
        return delete(cti, "CM_CONTRACT_ATTITEM.DeleteByContractAttId", obj);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAllByContractAttIdAndAreaId(Integer contractAttId, Integer areaId) {
		CmContractAttitem vo = new CmContractAttitem();
		vo.setContractAttId(contractAttId);
		vo.setItemId(areaId);
		return queryForList("CM_CONTRACT_ATTITEM.findAllByContractAttIdAndAreaId", vo);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findContractAttItem(Integer productId, Integer areaId, String itemId) {
		 HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		 _hashmap.put("productId", productId);
		 _hashmap.put("areaId", areaId);
		 _hashmap.put("itemId", itemId);
		return queryForList("CM_CONTRACT_ATTITEM.findContractAttItem", _hashmap);
	}

	public List<CmContractAttitem> findInsFundContractAttItem(CmContractAttitem vo) {
		return queryForList("CM_CONTRACT_ATTITEM.FindINSFUND_CM_CONTRACT_ATTITEM", vo);
	}
	
}
