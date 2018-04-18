package com.xfs.business.dao;

import com.xfs.business.dto.SpsBatchAdjBaseVo;
import com.xfs.business.model.SpsBatchAdjBase;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * SpsBatchAdjBaseDao
 * @author:yangfw@xinfushe.com
 * @date:2016/12/26 21:14:54
 */
@Repository
public class SpsBatchAdjBaseDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BATCH_ADJ_BASE.CountFindAllSPS_BATCH_ADJ_BASE", null );
        return ret.intValue();
	}

	public SpsBatchAdjBase findByPK(SpsBatchAdjBase obj) {
		Object ret = queryForObject("SPS_BATCH_ADJ_BASE.FindByPK", obj );
		if(ret !=null)
			return (SpsBatchAdjBase)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchAdjBase> freeFind(SpsBatchAdjBase obj) {
		return queryForList("SPS_BATCH_ADJ_BASE.FreeFindSPS_BATCH_ADJ_BASE", obj );
	}

	public int countFreeFind(SpsBatchAdjBase obj) {
        Integer ret = (Integer) queryForObject("SPS_BATCH_ADJ_BASE.CountFreeFindSPS_BATCH_ADJ_BASE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchAdjBase> freeFind(SpsBatchAdjBase obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BATCH_ADJ_BASE.FreeFindSPS_BATCH_ADJ_BASE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchAdjBase> freeFind(SpsBatchAdjBase obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBatchAdjBase){
	    	_hashmap = ((SpsBatchAdjBase)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BATCH_ADJ_BASE.FreeFindSPS_BATCH_ADJ_BASEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchAdjBase> freeFind(SpsBatchAdjBase obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBatchAdjBase){
	    	_hashmap = ((SpsBatchAdjBase)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BATCH_ADJ_BASE.FreeFindSPS_BATCH_ADJ_BASEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SpsBatchAdjBase> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBatchAdjBase> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBatchAdjBase oneObj = (SpsBatchAdjBase)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SpsBatchAdjBase vo) {
        SpsBatchAdjBase obj = (SpsBatchAdjBase) vo;

        if(exists( cti,obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti,SpsBatchAdjBase obj) {

		obj.fixup();
        return insert(cti,"SPS_BATCH_ADJ_BASE.InsertSPS_BATCH_ADJ_BASE", obj );
	}

	public int update(ContextInfo cti,SpsBatchAdjBase obj) {

		obj.fixup();
		return update( cti,"SPS_BATCH_ADJ_BASE.UpdateSPS_BATCH_ADJ_BASE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SpsBatchAdjBase obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete( cti,"SPS_BATCH_ADJ_BASE.DeleteSPS_BATCH_ADJ_BASE", obj );

	}

	public int removeObjectTree(ContextInfo cti,SpsBatchAdjBase obj) {

        obj.fixup();
        SpsBatchAdjBase oldObj = ( SpsBatchAdjBase )queryForObject("SPS_BATCH_ADJ_BASE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BATCH_ADJ_BASE.DeleteSPS_BATCH_ADJ_BASE", oldObj );

	}

	public boolean exists(ContextInfo cti,SpsBatchAdjBase vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBatchAdjBase obj = (SpsBatchAdjBase) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BATCH_ADJ_BASE.CountSPS_BATCH_ADJ_BASE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/26 21:14:54
	/**
	 * 条件查询列表
	 *  @param   obj
	 * @return    : java.util.List<com.xfs.business.dto.SpsBatchAdjBaseVo>
	 *  @createDate   : 2016/12/28 11:03
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/28 11:03
	 *  @updateAuthor  :
	 */
	@SuppressWarnings("unchecked")
	public List<SpsBatchAdjBaseVo> freeFindVo(SpsBatchAdjBaseVo obj,Integer ignore,Integer pageSize) {
		return getPaginatedList("SPS_BATCH_ADJ_BASE.FreeFindSPS_BATCH_ADJ_BASEOrdered1", obj,ignore,pageSize);
	}
	/**
	 * 条件查询列表总数
	 *  @param   obj
	 * @return    : int
	 *  @createDate   : 2016/12/28 11:02
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/28 11:02
	 *  @updateAuthor  :
	 */
	public int countFreeFindVo(SpsBatchAdjBaseVo obj) {
		Integer ret = (Integer) queryForObject("SPS_BATCH_ADJ_BASE.CountFreeFindSPS_BATCH_ADJ_BASE1", obj );
		return ret.intValue();
	}
	
}
