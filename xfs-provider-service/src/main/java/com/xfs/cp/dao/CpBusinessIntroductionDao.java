package com.xfs.cp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpBusinessIntroduction;

/**
 * CpBusinessIntroductionDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/01/05 11:47:25
 */
@Repository
public class CpBusinessIntroductionDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPBUSINESSINTRODUCTION.CountFindAllCP_BUSINESS_INTRODUCTION", null );
        return ret.intValue();
	}

	public CpBusinessIntroduction findByPK(CpBusinessIntroduction obj) {
		Object ret = queryForObject("CPBUSINESSINTRODUCTION.FindByPK", obj );
		if(ret !=null)
			return (CpBusinessIntroduction)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusinessIntroduction> freeFind(CpBusinessIntroduction obj) {
		return queryForList("CPBUSINESSINTRODUCTION.FreeFindCP_BUSINESS_INTRODUCTION", obj );
	}

	public int countFreeFind(CpBusinessIntroduction obj) {
        Integer ret = (Integer) queryForObject("CPBUSINESSINTRODUCTION.CountFreeFindCP_BUSINESS_INTRODUCTION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusinessIntroduction> freeFind(CpBusinessIntroduction obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPBUSINESSINTRODUCTION.FreeFindCP_BUSINESS_INTRODUCTION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusinessIntroduction> freeFind(CpBusinessIntroduction obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpBusinessIntroduction){
	    	_hashmap = ((CpBusinessIntroduction)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPBUSINESSINTRODUCTION.FreeFindCP_BUSINESS_INTRODUCTIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusinessIntroduction> freeFind(CpBusinessIntroduction obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpBusinessIntroduction){
	    	_hashmap = ((CpBusinessIntroduction)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPBUSINESSINTRODUCTION.FreeFindCP_BUSINESS_INTRODUCTIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(Collection<CpBusinessIntroduction> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpBusinessIntroduction> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpBusinessIntroduction oneObj = (CpBusinessIntroduction)it.next();
				i += save( oneObj );
            }
        }
        return i;
	}

	public int save(CpBusinessIntroduction vo) {
        CpBusinessIntroduction obj = (CpBusinessIntroduction) vo;

        if(exists( obj ) ) {
            return update( obj );
        } else {
            return insert( obj );
        }
	}

	public int insert(CpBusinessIntroduction obj) {

		obj.fixup();
        return insert(null, "CPBUSINESSINTRODUCTION.InsertCP_BUSINESS_INTRODUCTION", obj );
	}

	public int update(CpBusinessIntroduction obj) {

		obj.fixup();
		return update( null, "CPBUSINESSINTRODUCTION.UpdateCP_BUSINESS_INTRODUCTION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(CpBusinessIntroduction obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete( null, "CPBUSINESSINTRODUCTION.DeleteCP_BUSINESS_INTRODUCTION", obj );

	}

	public int removeObjectTree(CpBusinessIntroduction obj) {

        obj.fixup();
        CpBusinessIntroduction oldObj = ( CpBusinessIntroduction )queryForObject("CPBUSINESSINTRODUCTION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( null, "CPBUSINESSINTRODUCTION.DeleteCP_BUSINESS_INTRODUCTION", oldObj );

	}

	public boolean exists(CpBusinessIntroduction vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpBusinessIntroduction obj = (CpBusinessIntroduction) vo;

        keysFilled = keysFilled && ( null != obj.getCpId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPBUSINESSINTRODUCTION.CountCP_BUSINESS_INTRODUCTION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 11:47:25
	
	
	
}
