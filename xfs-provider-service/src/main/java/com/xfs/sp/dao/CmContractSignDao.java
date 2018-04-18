package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.CmContractSign;

/**
 * CmContractSignDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/10 10:44:44
 */
@Repository
public class CmContractSignDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_SIGN.CountFindAllCM_CONTRACT_SIGN", null );
        return ret.intValue();
	}

	public CmContractSign findByPK(CmContractSign obj) {
		Object ret = queryForObject("CM_CONTRACT_SIGN.FindByPK", obj );
		if(ret !=null)
			return (CmContractSign)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractSign> freeFind(CmContractSign obj) {
		return queryForList("CM_CONTRACT_SIGN.FreeFindCM_CONTRACT_SIGN", obj );
	}

	public int countFreeFind(CmContractSign obj) {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_SIGN.CountFreeFindCM_CONTRACT_SIGN", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractSign> freeFind(CmContractSign obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CONTRACT_SIGN.FreeFindCM_CONTRACT_SIGN", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractSign> freeFind(CmContractSign obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractSign){
	    	_hashmap = ((CmContractSign)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CONTRACT_SIGN.FreeFindCM_CONTRACT_SIGNOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractSign> freeFind(CmContractSign obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractSign){
	    	_hashmap = ((CmContractSign)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CONTRACT_SIGN.FreeFindCM_CONTRACT_SIGNOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmContractSign> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmContractSign> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmContractSign oneObj = (CmContractSign)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CmContractSign vo) {
        CmContractSign obj = (CmContractSign) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CmContractSign obj) {

		obj.fixup();
        return insert(cti, "CM_CONTRACT_SIGN.InsertCM_CONTRACT_SIGN", obj );
	}

	public int update(ContextInfo cti, CmContractSign obj) {

		obj.fixup();
		return update(cti, "CM_CONTRACT_SIGN.UpdateCM_CONTRACT_SIGN", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmContractSign obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_CONTRACT_SIGN.DeleteCM_CONTRACT_SIGN", obj );

	}

	public int removeObjectTree(ContextInfo cti, CmContractSign obj) {

        obj.fixup();
        CmContractSign oldObj = ( CmContractSign )queryForObject("CM_CONTRACT_SIGN.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CM_CONTRACT_SIGN.DeleteCM_CONTRACT_SIGN", oldObj );

	}

	public boolean exists(CmContractSign vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmContractSign obj = (CmContractSign) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CONTRACT_SIGN.CountCM_CONTRACT_SIGN", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 10:44:44
	
	
	
}
