package com.xfs.bill.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.bill.model.SpsFeeEmponce;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsFeeEmponceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/04 15:43:53
 */
@Repository
public class SpsFeeEmponceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_FEE_EMPONCE.CountFindAllSPS_FEE_EMPONCE", null );
        return ret.intValue();
	}

	public SpsFeeEmponce findByPK(SpsFeeEmponce obj) {
		Object ret = queryForObject("SPS_FEE_EMPONCE.FindByPK", obj );
		if(ret !=null)
			return (SpsFeeEmponce)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponce> freeFind(SpsFeeEmponce obj) {
		return queryForList("SPS_FEE_EMPONCE.FreeFindSPS_FEE_EMPONCE", obj );
	}

	public int countFreeFind(SpsFeeEmponce obj) {
        Integer ret = (Integer) queryForObject("SPS_FEE_EMPONCE.CountFreeFindSPS_FEE_EMPONCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponce> freeFind(SpsFeeEmponce obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_FEE_EMPONCE.FreeFindSPS_FEE_EMPONCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponce> freeFind(SpsFeeEmponce obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeEmponce){
	    	_hashmap = ((SpsFeeEmponce)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_FEE_EMPONCE.FreeFindSPS_FEE_EMPONCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsFeeEmponce> freeFind(SpsFeeEmponce obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsFeeEmponce){
	    	_hashmap = ((SpsFeeEmponce)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_FEE_EMPONCE.FreeFindSPS_FEE_EMPONCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsFeeEmponce> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsFeeEmponce> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsFeeEmponce oneObj = (SpsFeeEmponce)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsFeeEmponce vo) {
        SpsFeeEmponce obj = (SpsFeeEmponce) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsFeeEmponce obj) {

		obj.fixup();
        return insert(cti, "SPS_FEE_EMPONCE.InsertSPS_FEE_EMPONCE", obj );
	}

	public int update(ContextInfo cti, SpsFeeEmponce obj) {

		obj.fixup();
		return update(cti, "SPS_FEE_EMPONCE.UpdateSPS_FEE_EMPONCE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsFeeEmponce obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_FEE_EMPONCE.DeleteSPS_FEE_EMPONCE", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsFeeEmponce obj) {

        obj.fixup();
        SpsFeeEmponce oldObj = ( SpsFeeEmponce )queryForObject("SPS_FEE_EMPONCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_FEE_EMPONCE.DeleteSPS_FEE_EMPONCE", oldObj );

	}

	public boolean exists(SpsFeeEmponce vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsFeeEmponce obj = (SpsFeeEmponce) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_FEE_EMPONCE.CountSPS_FEE_EMPONCE", obj );
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
	 * 获取员工单次费用列表
	 * @param emponce
	 * @return
     */
	public List<SpsFeeEmponce> queryEmpOnceItems(SpsFeeEmponce emponce){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",emponce.getCpId());
		mp.put("sp_id",emponce.getSpId());
		mp.put("period",emponce.getPeriod());
		mp.put("emp_id",emponce.getEmpId());
		return queryForList("SPS_FEE_EMPONCE.QUERY_EMP_ITEMS_BY_PERIOD",mp);
	}

	/**
	 * 逻辑删除费用调整记录
	 * @param emponce
	 * @return
     */
	public int delEmpOnceItems(ContextInfo cti, SpsFeeEmponce emponce){
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",emponce.getCpId());
		mp.put("sp_id",emponce.getSpId());
		mp.put("emp_id",emponce.getEmpId());
		mp.put("id",emponce.getId());
		mp.put("dr",emponce.getDr());
		return update(cti, "SPS_FEE_EMPONCE.DEL_EMP_ITEMS_BY_ID",mp);
	}

	
	public List<SpsFeeEmponce> queryEmponceWithDetail(Integer spid, Integer cpid, String period, List<Integer> empIds) {
		Map<String,Object> mp = new HashMap<>();
		mp.put("spid",spid);
		mp.put("cpid",cpid);
		mp.put("period", period);
		if(empIds.isEmpty())
			empIds.add(-1);
		mp.put("empIds", empIds);
		return queryForList("SPS_FEE_EMPONCE.findSPS_FEE_EMPONCE_with_Detail",mp);
	}


	/**
	 * 查询子帐单费用调整项目
	 * @param sp_id
	 * @param p_sp_id
	 * @param cp_id
	 * @param period
	 * @return
	 */
	public List<Map<String,Object>> queryChildBillEmpOnceItems(Integer sp_id,String p_sp_id,Integer cp_id,String period) {
		Map<String,Object> mp = new HashMap<>();
		mp.put("cp_id",cp_id);
		mp.put("sp_id",sp_id);
		mp.put("p_sp_id",p_sp_id);
		mp.put("period",period);
		return queryForList("SPS_FEE_EMPONCE.QUERY_CHILD_BILL_EMP_ITEMS",mp);
	}

	/**
	 * 任务单 终止操作 对 算费进行dr = 1 更新操作
	 */
	public int removeInsFunSupply(ContextInfo cti, SpsFeeEmponce obj) {
		return update(cti, "SPS_FEE_EMPONCE.RemoveInsFunSupply", obj );
	}

}
