package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsBillitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;

/**
 * SpsBillitemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/16 15:32:12
 */
@Repository
public class SpsBillitemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILLITEM.CountFindAllSPS_BILLITEM", null );
        return ret.intValue();
	}

	public SpsBillitem findByPK(SpsBillitem obj) {
		Object ret = queryForObject("SPS_BILLITEM.FindByPK", obj );
		if(ret !=null)
			return (SpsBillitem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillitem> freeFind(SpsBillitem obj) {
		return queryForList("SPS_BILLITEM.FreeFindSPS_BILLITEM", obj );
	}

	public int countFreeFind(SpsBillitem obj) {
        Integer ret = (Integer) queryForObject("SPS_BILLITEM.CountFreeFindSPS_BILLITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillitem> freeFind(SpsBillitem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILLITEM.FreeFindSPS_BILLITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillitem> freeFind(SpsBillitem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillitem){
	    	_hashmap = ((SpsBillitem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILLITEM.FreeFindSPS_BILLITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillitem> freeFind(SpsBillitem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillitem){
	    	_hashmap = ((SpsBillitem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILLITEM.FreeFindSPS_BILLITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillitem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillitem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillitem oneObj = (SpsBillitem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillitem vo) {
        SpsBillitem obj = (SpsBillitem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsBillitem obj) {

		obj.fixup();
        return insert(cti, "SPS_BILLITEM.InsertSPS_BILLITEM", obj );
	}

	public int update(ContextInfo cti, SpsBillitem obj) {

		obj.fixup();
		return update(cti, "SPS_BILLITEM.UpdateSPS_BILLITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBillitem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILLITEM.DeleteSPS_BILLITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillitem obj) {

        obj.fixup();
        SpsBillitem oldObj = ( SpsBillitem )queryForObject("SPS_BILLITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BILLITEM.DeleteSPS_BILLITEM", oldObj );

	}

	public boolean exists(SpsBillitem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillitem obj = (SpsBillitem) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILLITEM.CountSPS_BILLITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:12

	/**
	 * 获取账单下的自定义项目列表
	 * @param spid
	 * @param cmid
     * @return
     */
	public List<SpsBillitem> queryCmBillitem(Integer spid,Integer cmid,Integer bill_id,String period){
		Map<String,Object> obj = new HashMap<>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		obj.put("bill_id",bill_id);
		if(!StringUtils.isBlank(period))
			obj.put("period",period);
		return queryForList("SPS_BILLITEM.QUERY_BILL_ITEMS_BY_PARAMS",obj);
	}
}
