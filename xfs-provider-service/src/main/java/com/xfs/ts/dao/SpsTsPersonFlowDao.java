package com.xfs.ts.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsPersonFlow;

/**
 * SpsTsPersonFlowDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/06 13:41:11
 */
@Repository
public class SpsTsPersonFlowDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TS_PERSON_FLOW.CountFindAllSPS_TS_PERSON_FLOW", null );
        return ret.intValue();
	}

	public SpsTsPersonFlow findByPK(SpsTsPersonFlow obj) {
		Object ret = queryForObject("SPS_TS_PERSON_FLOW.FindByPK", obj );
		if(ret !=null)
			return (SpsTsPersonFlow)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsPersonFlow> freeFind(SpsTsPersonFlow obj) {
		return queryForList("SPS_TS_PERSON_FLOW.FreeFindSPS_TS_PERSON_FLOW", obj );
	}

	public int countFreeFind(SpsTsPersonFlow obj) {
        Integer ret = (Integer) queryForObject("SPS_TS_PERSON_FLOW.CountFreeFindSPS_TS_PERSON_FLOW", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsPersonFlow> freeFind(SpsTsPersonFlow obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_TS_PERSON_FLOW.FreeFindSPS_TS_PERSON_FLOW", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsPersonFlow> freeFind(SpsTsPersonFlow obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsPersonFlow){
	    	_hashmap = ((SpsTsPersonFlow)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_TS_PERSON_FLOW.FreeFindSPS_TS_PERSON_FLOWOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsTsPersonFlow> freeFind(SpsTsPersonFlow obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsTsPersonFlow){
	    	_hashmap = ((SpsTsPersonFlow)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_TS_PERSON_FLOW.FreeFindSPS_TS_PERSON_FLOWOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsTsPersonFlow> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsTsPersonFlow> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsTsPersonFlow oneObj = (SpsTsPersonFlow)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int insert(ContextInfo cti, SpsTsPersonFlow obj) {

		obj.fixup();
        return insert(cti, "SPS_TS_PERSON_FLOW.InsertSPS_TS_PERSON_FLOW", obj );
	}

	public int update(ContextInfo cti, SpsTsPersonFlow obj) {

		obj.fixup();
		return update(cti, "SPS_TS_PERSON_FLOW.UpdateSPS_TS_PERSON_FLOW", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsTsPersonFlow obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_TS_PERSON_FLOW.DeleteSPS_TS_PERSON_FLOW", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsTsPersonFlow obj) {

        obj.fixup();
        SpsTsPersonFlow oldObj = ( SpsTsPersonFlow )queryForObject("SPS_TS_PERSON_FLOW.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_TS_PERSON_FLOW.DeleteSPS_TS_PERSON_FLOW", oldObj );

	}

	public boolean exists(SpsTsPersonFlow vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsTsPersonFlow obj = (SpsTsPersonFlow) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_TS_PERSON_FLOW.CountSPS_TS_PERSON_FLOW", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/06 13:41:11
	
	public int save(ContextInfo cti, SpsTsPersonFlow vo) {
        SpsTsPersonFlow obj = (SpsTsPersonFlow) vo;
        
        Integer id = existsByMonthAndIdCard( obj ); 
        if(id != null && id !=0 ) {
        	obj.setId(id);
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}
	
	public Integer existsByMonthAndIdCard(SpsTsPersonFlow vo) {
        return (Integer)queryForObject("SPS_TS_PERSON_FLOW.CountSPS_TS_PERSON_FLOWByMonthAndIdCard", vo );
	}


	public SpsTsPersonFlow getSpsTsPersonFlow(SpsTsPersonFlow vo) {
		Object obj = queryForObject("SPS_TS_PERSON_FLOW.GetSPS_TS_PERSON_FLOWByMonthAndIdCard", vo );
		if(obj==null){
			return  null;
		}else {
			return (SpsTsPersonFlow)obj;
		}
	}

	/**
	 * 查询符合条件的下行数据
	 * @param vo
	 * @return
	 */
	public List<SpsTsPersonFlow> queryAllFlow(SpsTsPersonFlow vo){
		return queryForList("SPS_TS_PERSON_FLOW.QUERY_ALL_FLOW",vo);
	}

	/**
	 * 修改下行数据
	 * @param vo
	 * @return
	 */
	public Integer updatePersonFlow(ContextInfo cti, SpsTsPersonFlow vo){
		return update(cti, "SPS_TS_PERSON_FLOW.UPDATE_PERSON_FLOW", vo);
	}
}
