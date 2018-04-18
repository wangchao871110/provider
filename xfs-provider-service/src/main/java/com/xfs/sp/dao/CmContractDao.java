package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.CmContract;

/**
 * CmContractDao
 * @author:wangchao
 * @date:2016/08/02 15:23:53
 */
@Repository
public class CmContractDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CONTRACT.CountFindAllCM_CONTRACT", null );
        return ret.intValue();
	}

	public CmContract findByPK(CmContract obj) {
		Object ret = queryForObject("CM_CONTRACT.FindByPK", obj );
		if(ret !=null)
			return (CmContract)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContract> freeFind(CmContract obj) {
		return queryForList("CM_CONTRACT.FreeFindCM_CONTRACT", obj );
	}

	public int countFreeFind(CmContract obj) {
        Integer ret = (Integer) queryForObject("CM_CONTRACT.CountFreeFindCM_CONTRACT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContract> freeFind(CmContract obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CONTRACT.FreeFindCM_CONTRACT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContract> freeFind(CmContract obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContract){
	    	_hashmap = ((CmContract)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CONTRACT.FreeFindCM_CONTRACTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmContract> freeFind(CmContract obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmContract){
	    	_hashmap = ((CmContract)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CONTRACT.FreeFindCM_CONTRACTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CmContract> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmContract> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmContract oneObj = (CmContract)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CmContract vo) {
        CmContract obj = (CmContract) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CmContract obj) {

		obj.fixup();
        return insert(cti, "CM_CONTRACT.InsertCM_CONTRACT", obj );
	}

	public int update(ContextInfo cti, CmContract obj) {

		obj.fixup();
		return update(cti, "CM_CONTRACT.UpdateCM_CONTRACT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmContract obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_CONTRACT.DeleteCM_CONTRACT", obj );

	}

	public int removeObjectTree(ContextInfo cti, CmContract obj) {

        obj.fixup();
        CmContract oldObj = ( CmContract )queryForObject("CM_CONTRACT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CM_CONTRACT.DeleteCM_CONTRACT", oldObj );

	}

	public boolean exists(CmContract vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmContract obj = (CmContract) vo;

        keysFilled = keysFilled && ( null != obj.getContractId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CONTRACT.CountCM_CONTRACT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 15:23:53
	/**
	 * 查询 合同信息
	 *
	 * @author lifq
	 *
	 * 2016年8月2日  下午9:02:31
	 */
	public List<Map<String,Object>> findContracts(Integer sp_id,Integer cp_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("sp_id",sp_id);
        _hashMap.put("cp_id",cp_id);
        return queryForList("CM_CONTRACT.findContracts", _hashMap);
	}
	
	
}
