package com.xfs.openlist.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.openlist.model.SpsOlCorpset;

/**
 * SpsOlCorpsetDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:56
 */
@Repository
public class SpsOlCorpsetDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_OL_CORPSET.CountFindAllSPS_OL_CORPSET", null );
        return ret.intValue();
	}

	public SpsOlCorpset findByPK(SpsOlCorpset obj) {
		Object ret = queryForObject("SPS_OL_CORPSET.FindByPK", obj );
		if(ret !=null)
			return (SpsOlCorpset)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlCorpset> freeFind(SpsOlCorpset obj) {
		return queryForList("SPS_OL_CORPSET.FreeFindSPS_OL_CORPSET", obj );
	}

	public int countFreeFind(SpsOlCorpset obj) {
        Integer ret = (Integer) queryForObject("SPS_OL_CORPSET.CountFreeFindSPS_OL_CORPSET", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlCorpset> freeFind(SpsOlCorpset obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_OL_CORPSET.FreeFindSPS_OL_CORPSET", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlCorpset> freeFind(SpsOlCorpset obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlCorpset){
	    	_hashmap = ((SpsOlCorpset)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_OL_CORPSET.FreeFindSPS_OL_CORPSETOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsOlCorpset> freeFind(SpsOlCorpset obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsOlCorpset){
	    	_hashmap = ((SpsOlCorpset)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_OL_CORPSET.FreeFindSPS_OL_CORPSETOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsOlCorpset> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsOlCorpset> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsOlCorpset oneObj = (SpsOlCorpset)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsOlCorpset vo) {
        SpsOlCorpset obj = (SpsOlCorpset) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsOlCorpset obj) {

		obj.fixup();
        return insert(cti, "SPS_OL_CORPSET.InsertSPS_OL_CORPSET", obj );
	}

	public int update(ContextInfo cti, SpsOlCorpset obj) {

		obj.fixup();
		return update(cti, "SPS_OL_CORPSET.UpdateSPS_OL_CORPSET", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsOlCorpset obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_OL_CORPSET.DeleteSPS_OL_CORPSET", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsOlCorpset obj) {

        obj.fixup();
        SpsOlCorpset oldObj = ( SpsOlCorpset )queryForObject("SPS_OL_CORPSET.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_OL_CORPSET.DeleteSPS_OL_CORPSET", oldObj );

	}

	public boolean exists(SpsOlCorpset vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsOlCorpset obj = (SpsOlCorpset) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_OL_CORPSET.CountSPS_OL_CORPSET", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

	/**
	 * 根据服务商Id查询 企业设置联系人列表
	 * @param sp_id
	 * @return
     */
	public List<Map<String,Object>> findCorpsetList(int sp_id){
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sp_id",sp_id);
		return queryForList("Find_CorpSet_Union__Customer",hashMap);
	}

	/**
	 * 根据员工信息 查询企业设置联系人信息
	 * @param mobile
	 * @return
     */
	public Map<String,Object> findCorpsetByCmPerson(String mobile,Integer cpid){
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("mobile",mobile);
		hashMap.put("cpid",cpid);
		List<Map<String,Object>> corpsetList = queryForList("Find_CorpSet_By__CM_Person",hashMap);
		if(null != corpsetList && !corpsetList.isEmpty()){
			return  corpsetList.get(0);
		}
		return null;
	}

}
