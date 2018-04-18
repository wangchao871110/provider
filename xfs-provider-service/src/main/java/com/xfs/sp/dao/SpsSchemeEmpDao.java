package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsSchemeEmp;

/**
 * SpsSchemeEmpDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:26:38
 */
@Repository
public class SpsSchemeEmpDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_EMP.CountFindAllSPS_SCHEME_EMP", null );
        return ret.intValue();
	}

	public SpsSchemeEmp findByPK(SpsSchemeEmp obj) {
		Object ret = queryForObject("SPS_SCHEME_EMP.FindByPK", obj );
		if(ret !=null)
			return (SpsSchemeEmp)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeEmp> freeFind(SpsSchemeEmp obj) {
		return queryForList("SPS_SCHEME_EMP.FreeFindSPS_SCHEME_EMP", obj );
	}

	public int countFreeFind(SpsSchemeEmp obj) {
        Integer ret = (Integer) queryForObject("SPS_SCHEME_EMP.CountFreeFindSPS_SCHEME_EMP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeEmp> freeFind(SpsSchemeEmp obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SCHEME_EMP.FreeFindSPS_SCHEME_EMP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeEmp> freeFind(SpsSchemeEmp obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeEmp){
	    	_hashmap = ((SpsSchemeEmp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SCHEME_EMP.FreeFindSPS_SCHEME_EMPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSchemeEmp> freeFind(SpsSchemeEmp obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSchemeEmp){
	    	_hashmap = ((SpsSchemeEmp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SCHEME_EMP.FreeFindSPS_SCHEME_EMPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsSchemeEmp> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsSchemeEmp> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsSchemeEmp oneObj = (SpsSchemeEmp)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsSchemeEmp vo) {
        SpsSchemeEmp obj = (SpsSchemeEmp) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsSchemeEmp obj) {

		obj.fixup();
        return insert(cti, "SPS_SCHEME_EMP.InsertSPS_SCHEME_EMP", obj );
	}

	public int update(ContextInfo cti, SpsSchemeEmp obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME_EMP.UpdateSPS_SCHEME_EMP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsSchemeEmp obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SCHEME_EMP.DeleteSPS_SCHEME_EMP", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsSchemeEmp obj) {

        obj.fixup();
        SpsSchemeEmp oldObj = ( SpsSchemeEmp )queryForObject("SPS_SCHEME_EMP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SCHEME_EMP.DeleteSPS_SCHEME_EMP", oldObj );

	}

	public boolean exists(SpsSchemeEmp vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsSchemeEmp obj = (SpsSchemeEmp) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SCHEME_EMP.CountSPS_SCHEME_EMP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:38
	
	public List<SpsSchemeEmp> findSchemeEmpByEmpIds(Integer spId, List<Integer> empIds) {
		HashMap<String, Object> _hashMap = new HashMap<String, Object>();
		_hashMap.put("empIds", empIds);
		_hashMap.put("spId", spId);
		return  queryForList("SPS_SCHEME_EMP.find_SCHEME_EMP_BY_EMPIDS", _hashMap);
	 	
	}

    public int removeByEmpId(ContextInfo cti, SpsSchemeEmp obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SPS_SCHEME_EMP.DeleteSPS_SCHEME_EMP_BY_EMPID", obj );

    }

	public int updateStateByEmpAndScheme(ContextInfo cti, SpsSchemeEmp obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME_EMP.UpdateSPS_SCHEME_EMP_STATE_BY_EMP_AND_SCHEME", obj );

	}

	public int updateByEmp(ContextInfo cti, SpsSchemeEmp obj) {

		obj.fixup();
		return update(cti, "SPS_SCHEME_EMP.UpdateSPS_SCHEME_EMP_BY_EMP", obj );

	}
	/**
	 * 根据empIds 更新状态
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午5:16:07
	 */
	public int updateStateByEmpIds(ContextInfo cti,Integer cpId,String empIds){
		Map<String, Object> map = new HashMap<>();
        map.put("cpId", cpId);
        map.put("empIds", empIds);
        map.put("authority",cti.getAuthority());
        return update(cti, "SPS_SCHEME_EMP.updateStateByEmpIds", map);
	}

	/**
	 * 根据数据权限获取方案下的服务人员
	 *  @param cti
	 *  @param cpId
	 *  @param spId
	 *  @return 
	 *	@return 			: List<SpsBill> 
	 *  @createDate  	: 2017年8月9日 上午10:33:56
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月9日 上午10:33:56
	 *  @updateAuthor  :
	 */
	public List<SpsSchemeEmp> findEmpByScheme(ContextInfo cti, Integer cpId, Integer spId) {
		HashMap<String, Object> _hashMap = new HashMap<String, Object>();
		_hashMap.put("authority", cti.getAuthority());
		_hashMap.put("cpId", cpId);
		_hashMap.put("spId", spId);
		return  queryForList("SPS_SCHEME_EMP.findEmpByScheme", _hashMap);
	}

	
}
