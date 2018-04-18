package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpBusiness;

/**
 * CpBusinessDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:18
 */
@Repository
public class CpBusinessDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_BUSINESS.CountFindAllCP_BUSINESS", null );
        return ret.intValue();
	}

	public CpBusiness findByPK(CpBusiness obj) {
		Object ret = queryForObject("CP_BUSINESS.FindByPK", obj );
		if(ret !=null)
			return (CpBusiness)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusiness> freeFind(CpBusiness obj) {
		return queryForList("CP_BUSINESS.FreeFindCP_BUSINESS", obj );
	}

	public int countFreeFind(CpBusiness obj) {
        Integer ret = (Integer) queryForObject("CP_BUSINESS.CountFreeFindCP_BUSINESS", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusiness> freeFind(CpBusiness obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_BUSINESS.FreeFindCP_BUSINESS", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusiness> freeFind(CpBusiness obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpBusiness){
	    	_hashmap = ((CpBusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_BUSINESS.FreeFindCP_BUSINESSOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpBusiness> freeFind(CpBusiness obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpBusiness){
	    	_hashmap = ((CpBusiness)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_BUSINESS.FreeFindCP_BUSINESSOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpBusiness> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpBusiness> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpBusiness oneObj = (CpBusiness)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpBusiness vo) {
        CpBusiness obj = (CpBusiness) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpBusiness obj) {

		obj.fixup();
        return insert(cti, "CP_BUSINESS.InsertCP_BUSINESS", obj );
	}

	public int update(ContextInfo cti, CpBusiness obj) {

		obj.fixup();
		return update(cti, "CP_BUSINESS.UpdateCP_BUSINESS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpBusiness obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_BUSINESS.DeleteCP_BUSINESS", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpBusiness obj) {

        obj.fixup();
        CpBusiness oldObj = ( CpBusiness )queryForObject("CP_BUSINESS.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_BUSINESS.DeleteCP_BUSINESS", oldObj );

	}

	public boolean exists(CpBusiness vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpBusiness obj = (CpBusiness) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_BUSINESS.CountCP_BUSINESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	/**
	 * 
	 * @method comments: 逻辑删除
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月12日 下午8:24:16 
	 * @param cpBusiness
	 * @return 
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午8:24:16 baoyu 创建
	 *
	 */
	public int updateBySpId(ContextInfo cti, CpBusiness cpBusiness) {
		return update(cti, "CP_BUSINESS.updateBySpId", cpBusiness);
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	
	
	
}
