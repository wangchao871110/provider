package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsFeeEmponcedetailDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/04 15:43:53
 */
@Repository
public class SpsFeeEmponcedetailDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_FEE_EMPONCEDETAIL.CountFindAllSPS_FEE_EMPONCEDETAIL", null );
        return ret.intValue();
	}

	public SpsFeeEmponcedetail findByPK(SpsFeeEmponcedetail obj) {
		Object ret = queryForObject("SPS_FEE_EMPONCEDETAIL.FindByPK", obj );
		if(ret !=null)
			return (SpsFeeEmponcedetail)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponcedetail> freeFind(SpsFeeEmponcedetail obj) {
		return queryForList("SPS_FEE_EMPONCEDETAIL.FreeFindSPS_FEE_EMPONCEDETAIL", obj );
	}

	public int countFreeFind(SpsFeeEmponcedetail obj) {
        Integer ret = (Integer) queryForObject("SPS_FEE_EMPONCEDETAIL.CountFreeFindSPS_FEE_EMPONCEDETAIL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponcedetail> freeFind(SpsFeeEmponcedetail obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_FEE_EMPONCEDETAIL.FreeFindSPS_FEE_EMPONCEDETAIL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponcedetail> freeFind(SpsFeeEmponcedetail obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeEmponcedetail){
	    	_hashmap = ((SpsFeeEmponcedetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_FEE_EMPONCEDETAIL.FreeFindSPS_FEE_EMPONCEDETAILOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponcedetail> freeFind(SpsFeeEmponcedetail obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeEmponcedetail){
	    	_hashmap = ((SpsFeeEmponcedetail)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_FEE_EMPONCEDETAIL.FreeFindSPS_FEE_EMPONCEDETAILOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsFeeEmponcedetail> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsFeeEmponcedetail> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsFeeEmponcedetail oneObj = (SpsFeeEmponcedetail)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsFeeEmponcedetail vo) {
        SpsFeeEmponcedetail obj = (SpsFeeEmponcedetail) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsFeeEmponcedetail obj) {

		obj.fixup();
        return insert(cti, "SPS_FEE_EMPONCEDETAIL.InsertSPS_FEE_EMPONCEDETAIL", obj );
	}

	public int update(ContextInfo cti, SpsFeeEmponcedetail obj) {

		obj.fixup();
		return update(cti, "SPS_FEE_EMPONCEDETAIL.UpdateSPS_FEE_EMPONCEDETAIL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsFeeEmponcedetail obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_FEE_EMPONCEDETAIL.DeleteSPS_FEE_EMPONCEDETAIL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsFeeEmponcedetail obj) {

        obj.fixup();
        SpsFeeEmponcedetail oldObj = ( SpsFeeEmponcedetail )queryForObject("SPS_FEE_EMPONCEDETAIL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_FEE_EMPONCEDETAIL.DeleteSPS_FEE_EMPONCEDETAIL", oldObj );

	}

	public boolean exists(SpsFeeEmponcedetail vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsFeeEmponcedetail obj = (SpsFeeEmponcedetail) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_FEE_EMPONCEDETAIL.CountSPS_FEE_EMPONCEDETAIL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 15:43:53

	/**
	 * 查询员工补缴详情
	 * @param sp_id
	 * @param cp_id
	 * @param emp_id
     * @return
     */
	public List<Map<String,Object>> queryEmpBackInusInfo(Integer sp_id, Integer cp_id, Integer emp_id){
		HashMap<String,Object> ob = new HashMap<>();
		ob.put("sp_id",sp_id);
		ob.put("cp_id",cp_id);
		ob.put("emp_id",emp_id);
		return (List<Map<String,Object>>)queryForList("SPS_FEE_EMPONCEDETAIL.QUERY_EMP_BACK_INUS_INFO",ob);
	}

	public List<SpsFeeEmponcedetail> queryEmpBackInusInfoList(Integer emp_fee_id){
		HashMap<String,Object> ob = new HashMap<>();
		ob.put("emp_fee_id",emp_fee_id);
		return (List<SpsFeeEmponcedetail>)queryForList("SPS_FEE_EMPONCEDETAIL.QUERY_EMP_BACK_INUS_INFO_LIST",ob);
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> findEmpBackInusInfo(Integer sourceid, Integer empId){
		HashMap<String,Object> ob = new HashMap<>();
		ob.put("sourceid",sourceid);
		ob.put("empId",empId);
		return queryForList("SPS_FEE_EMPONCEDETAIL.FIND_EMP_BACK_INUS_INFO",ob);
	}
	@SuppressWarnings("unchecked")
	public List<LinkedHashMap<String, Object>> findEmpBackInusInfoHJ(String bstypeId,String taskId, Integer empId){
		HashMap<String,Object> ob = new HashMap<>();
		ob.put("empId",empId);
		ob.put("taskId",taskId);
		ob.put("bstypeId",bstypeId);
		return queryForList("SPS_FEE_EMPONCEDETAIL.FIND_EMP_BACK_INUS_INFO_HJ",ob);
	}
	
}
