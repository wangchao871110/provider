package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpIndustry;

/**
 * CpIndustryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:20
 */
@Repository
public class CpIndustryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_INDUSTRY.CountFindAllCP_INDUSTRY", null );
        return ret.intValue();
	}

	public CpIndustry findByPK(CpIndustry obj) {
		Object ret = queryForObject("CP_INDUSTRY.FindByPK", obj );
		if(ret !=null)
			return (CpIndustry)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpIndustry> freeFind(CpIndustry obj) {
		return queryForList("CP_INDUSTRY.FreeFindCP_INDUSTRY", obj );
	}

	public int countFreeFind(CpIndustry obj) {
        Integer ret = (Integer) queryForObject("CP_INDUSTRY.CountFreeFindCP_INDUSTRY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpIndustry> freeFind(CpIndustry obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_INDUSTRY.FreeFindCP_INDUSTRY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpIndustry> freeFind(CpIndustry obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpIndustry){
	    	_hashmap = ((CpIndustry)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_INDUSTRY.FreeFindCP_INDUSTRYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpIndustry> freeFind(CpIndustry obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpIndustry){
	    	_hashmap = ((CpIndustry)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_INDUSTRY.FreeFindCP_INDUSTRYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpIndustry> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpIndustry> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpIndustry oneObj = (CpIndustry)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpIndustry vo) {
        CpIndustry obj = (CpIndustry) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpIndustry obj) {

		obj.fixup();
        return insert(cti, "CP_INDUSTRY.InsertCP_INDUSTRY", obj );
	}

	public int update(ContextInfo cti, CpIndustry obj) {

		obj.fixup();
		return update(cti, "CP_INDUSTRY.UpdateCP_INDUSTRY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpIndustry obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_INDUSTRY.DeleteCP_INDUSTRY", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpIndustry obj) {

        obj.fixup();
        CpIndustry oldObj = ( CpIndustry )queryForObject("CP_INDUSTRY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_INDUSTRY.DeleteCP_INDUSTRY", oldObj );

	}

	public boolean exists(CpIndustry vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpIndustry obj = (CpIndustry) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_INDUSTRY.CountCP_INDUSTRY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	
	
}
