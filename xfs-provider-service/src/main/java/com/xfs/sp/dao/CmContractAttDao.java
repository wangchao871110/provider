package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.CmContractAtt;

/**
 * CmContractAttDao
 * @author:wangchao
 * @date:2016/08/02 10:14:24
 */
@Repository
public class CmContractAttDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_ATT.CountFindAllCM_CONTRACT_ATT", null );
        return ret.intValue();
	}

	public CmContractAtt findByPK(CmContractAtt obj) {
		Object ret = queryForObject("CM_CONTRACT_ATT.FindByPK", obj );
		if(ret !=null)
			return (CmContractAtt)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAtt> freeFind(CmContractAtt obj) {
		return queryForList("CM_CONTRACT_ATT.FreeFindCM_CONTRACT_ATT", obj );
	}

	public int countFreeFind(CmContractAtt obj) {
        Integer ret = (Integer) queryForObject("CM_CONTRACT_ATT.CountFreeFindCM_CONTRACT_ATT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAtt> freeFind(CmContractAtt obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CONTRACT_ATT.FreeFindCM_CONTRACT_ATT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAtt> freeFind(CmContractAtt obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractAtt){
	    	_hashmap = ((CmContractAtt)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CONTRACT_ATT.FreeFindCM_CONTRACT_ATTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContractAtt> freeFind(CmContractAtt obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContractAtt){
	    	_hashmap = ((CmContractAtt)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CONTRACT_ATT.FreeFindCM_CONTRACT_ATTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmContractAtt> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmContractAtt> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmContractAtt oneObj = (CmContractAtt)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CmContractAtt vo) {
        CmContractAtt obj = (CmContractAtt) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CmContractAtt obj) {

		obj.fixup();
        return insert(cti,"CM_CONTRACT_ATT.InsertCM_CONTRACT_ATT", obj);
	}

	public int update(ContextInfo cti, CmContractAtt obj) {

		obj.fixup();
		return update(cti, "CM_CONTRACT_ATT.UpdateCM_CONTRACT_ATT", obj);

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,CmContractAtt obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti,"CM_CONTRACT_ATT.DeleteCM_CONTRACT_ATT", obj);

	}

	public int removeObjectTree(ContextInfo cti,CmContractAtt obj) {

        obj.fixup();
        CmContractAtt oldObj = ( CmContractAtt )queryForObject("CM_CONTRACT_ATT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti,"CM_CONTRACT_ATT.DeleteCM_CONTRACT_ATT", oldObj );

	}

	public boolean exists(CmContractAtt vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmContractAtt obj = (CmContractAtt) vo;

        keysFilled = keysFilled && ( null != obj.getContractAttId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CONTRACT_ATT.CountCM_CONTRACT_ATT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:24
	
	
	
}
