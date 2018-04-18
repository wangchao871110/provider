package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpCmRelation;

/**
 * @author xiyanzhang
 * @Email: zhangxiyan@xinfushe.com
 * @version 创建时间：2016年11月11日 下午12:14:07
 */
@Repository
public class SpCmRelationDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SP_CM_RELATION.CountFindAllSP_CM_RELATION", null );
        return ret.intValue();
	}

	public SpCmRelation findByPK(SpCmRelation obj) {
		Object ret = queryForObject("SP_CM_RELATION.FindByPK", obj );
		if(ret !=null)
			return (SpCmRelation)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpCmRelation> freeFind(SpCmRelation obj) {
		return queryForList("SP_CM_RELATION.FreeFindSP_CM_RELATION", obj );
	}

	public int countFreeFind(SpCmRelation obj) {
        Integer ret = (Integer) queryForObject("SP_CM_RELATION.CountFreeFindSP_CM_RELATION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpCmRelation> freeFind(SpCmRelation obj, int pageIndex, int pageSize) {
		return getPaginatedList("SP_CM_RELATION.FreeFindSP_CM_RELATION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpCmRelation> freeFind(SpCmRelation obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpCmRelation){
	    	_hashmap = ((SpCmRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SP_CM_RELATION.FreeFindSP_CM_RELATIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpCmRelation> freeFind(SpCmRelation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpCmRelation){
	    	_hashmap = ((SpCmRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SP_CM_RELATION.FreeFindSP_CM_RELATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpCmRelation> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpCmRelation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpCmRelation oneObj = (SpCmRelation)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}
	
	public void saveAll1(ContextInfo cti, Collection<SpCmRelation> objColl) {
        if ( objColl != null ) {
            Iterator<SpCmRelation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpCmRelation oneObj = (SpCmRelation)it.next();
				save(cti, oneObj );
            }
        }
	}

	public int save(ContextInfo cti, SpCmRelation vo) {
        SpCmRelation obj = (SpCmRelation) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public void save1(ContextInfo cti, SpCmRelation vo) {
        SpCmRelation obj = (SpCmRelation) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}
	
	public int insert(ContextInfo cti, SpCmRelation obj) {

		obj.fixup();
        return insert(cti, "SP_CM_RELATION.InsertSP_CM_RELATION", obj );
	}

	public void insert1(ContextInfo cti, SpCmRelation obj) {

		obj.fixup();
        insert(cti, "SP_CM_RELATION.InsertSP_CM_RELATION", obj );
	}
	
	public int update(ContextInfo cti, SpCmRelation obj) {

		obj.fixup();
		return update(cti, "SP_CM_RELATION.UpdateSP_CM_RELATION", obj );

	}
	
	public void update1(ContextInfo cti, SpCmRelation obj) {

		obj.fixup();
		update(cti, "SP_CM_RELATION.UpdateSP_CM_RELATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpCmRelation obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SP_CM_RELATION.DeleteSP_CM_RELATION", obj );

	}
	
	public void remove1(ContextInfo cti, SpCmRelation obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SP_CM_RELATION.DeleteSP_CM_RELATION", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpCmRelation obj) {

        obj.fixup();
        SpCmRelation oldObj = ( SpCmRelation )queryForObject("SP_CM_RELATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SP_CM_RELATION.DeleteSP_CM_RELATION", oldObj );

	}
	
	public void removeObjectTree1(ContextInfo cti, SpCmRelation obj) {

        obj.fixup();
        SpCmRelation oldObj = ( SpCmRelation )queryForObject("SP_CM_RELATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SP_CM_RELATION.DeleteSP_CM_RELATION", oldObj );

	}

	public boolean exists(SpCmRelation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpCmRelation obj = (SpCmRelation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SP_CM_RELATION.CountSP_CM_RELATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/16 18:00:05

	public int removeByCpId(ContextInfo cti, SpCmRelation obj) {

		if (obj == null) {
			return 0;
		}
		obj.fixup();

		return delete(cti, "SP_CM_RELATION.DeleteSP_CM_RELATIONByCpId", obj);

	}
	
	/**
	 * 根据服务商id和区域编号获取企业列表
	 *
	 * @param spId
	 * @param areaCode
     * @return
     */
	public List<Map<String, Object>> findCorpsBySpId(Integer spId, String areaCode) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("spId", spId);
		_hashmap.put("areaCode", areaCode);
		return queryForList("SP_CM_RELATION.FindCorpsBySpId", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public SpCmRelation findBySpidAndCpid(Integer spid, Integer cpid) {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		_hashmap.put("spId", spid);
		_hashmap.put("cpId", cpid);
		 Object object = queryForObject("SP_CM_RELATION.findSP_CM_RELATIONBySpidAndCpid", _hashmap);
		if(null != object)
			return (SpCmRelation)object;
		return null;
	}
	
	/**
	 * 根据账单日获取企业列表
	 * @return
     */
	public List<SpCmRelation> queryAllCropsByBillDay() {
		HashMap<String, Object> _hashmap = new HashMap<String, Object>();
		return queryForList("SP_CM_RELATION.QUERY_ALL_CROPS_BY_BILL_DAY", _hashmap);
	}
	
	/**
	 * 通过企业id获取关系数据列表
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> freeFindByCpId(SpCmRelation vo) {
		return queryForList("SP_CM_RELATION.FINDSP_CM_RELATION_BY_CPID", vo);
	}
	/**
	 * 通过企业id获取关系数据列表
	 *
	 * @param vo
	 * @return
	 */
	public Integer delRELATIONByCpIdandSpId(ContextInfo cti,SpCmRelation vo) {
		return update(cti, "SP_CM_RELATION.delRELATIONByCpIdandSpId", vo);
	}
}
