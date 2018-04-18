package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.acc.dto.ApplyMessage;
import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsAccount;

/**
 * SpsAccountDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 18:17:38
 */
@Repository
public class SpsAccountDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_ACCOUNT.CountFindAllSPS_ACCOUNT", null );
        return ret.intValue();
	}

	public SpsAccount findByPK(SpsAccount obj) {
		Object ret = queryForObject("SPS_ACCOUNT.FindByPK", obj );
		if(ret !=null)
			return (SpsAccount)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> freeFind(SpsAccount obj) {
		return queryForList("SPS_ACCOUNT.FreeFindSPS_ACCOUNT", obj );
	}

	public int countFreeFind(SpsAccount obj) {
        Integer ret = (Integer) queryForObject("SPS_ACCOUNT.CountFreeFindSPS_ACCOUNT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> freeFind(SpsAccount obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNT.FreeFindSPS_ACCOUNT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> freeFind(SpsAccount obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAccount){
	    	_hashmap = ((SpsAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_ACCOUNT.FreeFindSPS_ACCOUNTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> freeFind(SpsAccount obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAccount){
	    	_hashmap = ((SpsAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_ACCOUNT.FreeFindSPS_ACCOUNTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsAccount> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsAccount> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsAccount oneObj = (SpsAccount)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsAccount vo) {
        SpsAccount obj = (SpsAccount) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsAccount obj) {

		obj.fixup();
		SpsAccount old = new SpsAccount();
		old.setCpId(obj.getCpId());
		old.setSpId(obj.getSpId());
		old.setInsuranceFundType(obj.getInsuranceFundType());
		old.setState(obj.getState());
		old.setAccType(obj.getAccType());
		remove(cti, old);
        return insert(cti, "SPS_ACCOUNT.InsertSPS_ACCOUNT", obj );
	}

	public int update(ContextInfo cti, SpsAccount obj) {

		obj.fixup();
		return update(cti, "SPS_ACCOUNT.UpdateSPS_ACCOUNT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsAccount obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_ACCOUNT.DeleteSPS_ACCOUNT", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsAccount obj) {

        obj.fixup();
        SpsAccount oldObj = ( SpsAccount )queryForObject("SPS_ACCOUNT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_ACCOUNT.DeleteSPS_ACCOUNT", oldObj );

	}

	public boolean exists(SpsAccount vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsAccount obj = (SpsAccount) vo;

        keysFilled = keysFilled && ( null != obj.getAccId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_ACCOUNT.CountSPS_ACCOUNT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 18:17:38
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> freeFindMap(SpsAccount obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNT.FreeFindSPS_ACCOUNT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccount> querySpsAccountListByIds(Integer spid, List<Integer> ids){
		Map<String,Object> obj = new HashMap<>();
		
		obj.put("spid",spid);
		obj.put("ids", ids);
		return queryForList("SPS_ACCOUNT.QUERY_ACCOUNT_BYIDS",obj);
	}

	
	public List<SpsAccount> findAllWithParent(SpsAccount vo) {
		return queryForList("SPS_ACCOUNT.findAllWithParent", vo);
	}

	/**
	 * 通过spid查询库总数
	 *
	 * @param vo
	 * @return
     */
	public int findSpsAccountCountBySpid(SpsAccount vo) {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNT.findSPS_ACCOUNT_COUNT_BY_SPID", vo);
		return ret.intValue();
	}

	/**
	 * 通过spid查询库列表
	 *
	 * @param vo
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	public List<Map<String, Object>> findSpsAccountBySpid(SpsAccount vo, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNT.findSPS_ACCOUNT_BY_SPID", vo, pageIndex, pageSize);
	}

	/**
	 * 通过库名称查询 -- 库名称的唯一性
	 *
	 * @param vo
	 * @return
     */
	public int findSpsAccountCountByName(SpsAccount vo) {
		Integer ret = (Integer) queryForObject("SPS_ACCOUNT.findSPS_ACCOUNT_COUNT_BY_NAME", vo);
		return ret.intValue();
	}

	/**
	 * 通过条件查询出spsAccount的社保与公积金的最小的accID
	 * @return
	 */
    public List<SpsAccount> findSpsAccountListByConditions(SpsAccount spsAccount) {
		return queryForList("SPS_ACCOUNT.findSpsAccountListByConditions", spsAccount);
    }

	/**
	 * 根据地区获取当前可以网申帐号
	 * @param cti
	 * @param areaIds
	 * @return
	 */
    public List<ApplyMessage> queryApplyMessageList(ContextInfo cti,String[] areaIds,String[] taskIds,String[] schemeIds){
		Map<String,Object> obj = new HashMap<>();
		obj.put("cpId",cti.getOrgId());
		obj.put("areaIds", areaIds);
		obj.put("taskIds", taskIds);
		obj.put("schemeIds",schemeIds);
		obj.put("authority",cti.getAuthority());
    	return queryForList("SPS_ACCOUNT.queryApplyMessageList",obj);
	}

}
