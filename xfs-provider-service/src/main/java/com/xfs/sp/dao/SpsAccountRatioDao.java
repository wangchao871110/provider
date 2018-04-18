package com.xfs.sp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.acc.dto.InsuranceLivingTypeDetailsVo;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsAccountRatio;

/**
 * SpsAccountRatioDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:08:03
 */
@Repository
public class SpsAccountRatioDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_ACCOUNT_RATIO.CountFindAllSPS_ACCOUNT_RATIO", null );
        return ret.intValue();
	}

	public SpsAccountRatio findByPK(SpsAccountRatio obj) {
		Object ret = queryForObject("SPS_ACCOUNT_RATIO.FindByPK", obj );
		if(ret !=null)
			return (SpsAccountRatio)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccountRatio> freeFind(SpsAccountRatio obj) {
		return queryForList("SPS_ACCOUNT_RATIO.FreeFindSPS_ACCOUNT_RATIO", obj );
	}

	public int countFreeFind(SpsAccountRatio obj) {
        Integer ret = (Integer) queryForObject("SPS_ACCOUNT_RATIO.CountFreeFindSPS_ACCOUNT_RATIO", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccountRatio> freeFind(SpsAccountRatio obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_ACCOUNT_RATIO.FreeFindSPS_ACCOUNT_RATIO", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccountRatio> freeFind(SpsAccountRatio obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAccountRatio){
	    	_hashmap = ((SpsAccountRatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_ACCOUNT_RATIO.FreeFindSPS_ACCOUNT_RATIOOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsAccountRatio> freeFind(SpsAccountRatio obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsAccountRatio){
	    	_hashmap = ((SpsAccountRatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_ACCOUNT_RATIO.FreeFindSPS_ACCOUNT_RATIOOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsAccountRatio> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsAccountRatio> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsAccountRatio oneObj = (SpsAccountRatio)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsAccountRatio vo) {
        SpsAccountRatio obj = (SpsAccountRatio) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsAccountRatio obj) {

		obj.fixup();
        return insert(cti, "SPS_ACCOUNT_RATIO.InsertSPS_ACCOUNT_RATIO", obj );
	}

	public int update(ContextInfo cti, SpsAccountRatio obj) {

		obj.fixup();
		return update(cti, "SPS_ACCOUNT_RATIO.UpdateSPS_ACCOUNT_RATIO", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsAccountRatio obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_ACCOUNT_RATIO.DeleteSPS_ACCOUNT_RATIO", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsAccountRatio obj) {

        obj.fixup();
        SpsAccountRatio oldObj = ( SpsAccountRatio )queryForObject("SPS_ACCOUNT_RATIO.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_ACCOUNT_RATIO.DeleteSPS_ACCOUNT_RATIO", oldObj );

	}

	public boolean exists(SpsAccountRatio vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsAccountRatio obj = (SpsAccountRatio) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_ACCOUNT_RATIO.CountSPS_ACCOUNT_RATIO", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:08:03

	/**
	 * 根据ACCOUNTID查询 险种信息
	 *
	 */
	public List<Map<String, Object>> findRatiosByAccountId(Map<String,Object> paraMap){
		return queryForList("SPS_ACCOUNT_RATIO.findRatiosByAccountId", paraMap);
	}

	@SuppressWarnings("unchecked")
	public List<SpsAccountRatio> freeFindList(SpsAccountRatio obj) {
		return queryForList("SPS_ACCOUNT_RATIO.FreeFindListSPS_ACCOUNT_RATIO", obj );
	}

	public List<InsuranceLivingTypeDetailsVo> findRatiosByAreaIdAndLive(Map<String, Object> paraMap) {
		return queryForList("SPS_ACCOUNT_RATIO.findRatiosByAreaIdAndLive", paraMap);
	}

	/**
	 * 根据大库ID获取默认比例
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年4月5日 下午3:53:21
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月5日 下午3:53:21
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findAccountRatioList(SpsAccountRatio vo) {
		return queryForList("SPS_ACCOUNT_RATIO.findAccountRatioList", vo);
	}

	/**
	 * 根据大库ID删除比例
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2017年4月9日 下午4:52:02
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月9日 下午4:52:02
	 *  @updateAuthor  :
	 */
	public int removeByAccId(ContextInfo cti, SpsAccountRatio obj) {
		 if ( obj == null ) { 
	            return 0; 
	        }
			
			obj.fixup();

	        return delete(cti, "SPS_ACCOUNT_RATIO.removeByAccId", obj );
	}

	/**
	 * 更新库的默认比例
	 * @param cti
	 * @param accId
	 * @param insuranceId
	 * @param areaId
	 * @param corpRatio
	 * @param empRatio
	 * @return
	 */
	public int updateAccountDefaultRatio(ContextInfo cti, Integer accId,Integer insuranceId,Integer areaId,String corpRatio,String empRatio) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("accId",accId);
		_hashmap.put("insuranceId",insuranceId);
		_hashmap.put("areaId",areaId);
		_hashmap.put("corpRatio",corpRatio);
		_hashmap.put("empRatio",empRatio);
		return update(cti, "SPS_ACCOUNT_RATIO.UpdateSPS_ACCOUNT_DEFAULT_RATIO", _hashmap );

	}

}
