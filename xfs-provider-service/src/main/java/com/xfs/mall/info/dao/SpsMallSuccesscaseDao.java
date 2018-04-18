package com.xfs.mall.info.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.info.model.SpsMallSuccesscase;

/**
 * SpsMallSuccesscaseDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/27 11:28:03
 */
@Repository
public class SpsMallSuccesscaseDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_MALL_SUCCESSCASE.CountFindAllSPS_MALL_SUCCESSCASE", null );
        return ret.intValue();
	}

	public SpsMallSuccesscase findByPK(SpsMallSuccesscase obj) {
		Object ret = queryForObject("SPS_MALL_SUCCESSCASE.FindByPK", obj );
		if(ret !=null)
			return (SpsMallSuccesscase)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallSuccesscase> freeFind(SpsMallSuccesscase obj) {
		return queryForList("SPS_MALL_SUCCESSCASE.FreeFindSPS_MALL_SUCCESSCASE", obj );
	}

	public int countFreeFind(SpsMallSuccesscase obj) {
        Integer ret = (Integer) queryForObject("SPS_MALL_SUCCESSCASE.CountFreeFindSPS_MALL_SUCCESSCASE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallSuccesscase> freeFind(SpsMallSuccesscase obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_MALL_SUCCESSCASE.FreeFindSPS_MALL_SUCCESSCASE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallSuccesscase> freeFind(SpsMallSuccesscase obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallSuccesscase){
	    	_hashmap = ((SpsMallSuccesscase)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_MALL_SUCCESSCASE.FreeFindSPS_MALL_SUCCESSCASEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsMallSuccesscase> freeFind(SpsMallSuccesscase obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsMallSuccesscase){
	    	_hashmap = ((SpsMallSuccesscase)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_MALL_SUCCESSCASE.FreeFindSPS_MALL_SUCCESSCASEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsMallSuccesscase> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsMallSuccesscase> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsMallSuccesscase oneObj = (SpsMallSuccesscase)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsMallSuccesscase vo) {
        SpsMallSuccesscase obj = (SpsMallSuccesscase) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsMallSuccesscase obj) {

		obj.fixup();
        return insert(cti, "SPS_MALL_SUCCESSCASE.InsertSPS_MALL_SUCCESSCASE", obj );
	}

	public int update(ContextInfo cti, SpsMallSuccesscase obj) {

		obj.fixup();
		return update(cti, "SPS_MALL_SUCCESSCASE.UpdateSPS_MALL_SUCCESSCASE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsMallSuccesscase obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_MALL_SUCCESSCASE.DeleteSPS_MALL_SUCCESSCASE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsMallSuccesscase obj) {

        obj.fixup();
        SpsMallSuccesscase oldObj = ( SpsMallSuccesscase )queryForObject("SPS_MALL_SUCCESSCASE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_MALL_SUCCESSCASE.DeleteSPS_MALL_SUCCESSCASE", oldObj );

	}

	public boolean exists(SpsMallSuccesscase vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsMallSuccesscase obj = (SpsMallSuccesscase) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_MALL_SUCCESSCASE.CountSPS_MALL_SUCCESSCASE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/27 11:28:03

	public int removeSuccessCaseBySpId(ContextInfo cti, int spId) {
		return delete(cti, "SPS_MALL_SUCCESSCASE.DeleteSuccessCaseBySpId", spId );
	}
	public int batchInsert(ContextInfo cti, List<SpsMallSuccesscase> list){
		return  insert(cti, "SPS_MALL_SUCCESSCASE.batchInsert",list);
	}
}
