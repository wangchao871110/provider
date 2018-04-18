package com.xfs.business.dao;

import com.xfs.business.dto.SpsBatchChangeRatioVo;
import com.xfs.business.model.SpsBatchChangeRatio;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 批量变更比例记录 dao
 * SpsBatchChangeRatioDao
 * @author:yangfw@xinfushe.com
 * @date:2016/11/15 16:30:31
 */
@Repository
public class SpsBatchChangeRatioDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BATCH_CHANGE_RATIO.CountFindAllSPS_BATCH_CHANGE_RATIO", null );
        return ret.intValue();
	}

	public SpsBatchChangeRatio findByPK(SpsBatchChangeRatio obj) {
		Object ret = queryForObject("SPS_BATCH_CHANGE_RATIO.FindByPK", obj );
		if(ret !=null)
			return (SpsBatchChangeRatio)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchChangeRatio> freeFind(SpsBatchChangeRatio obj) {
		return queryForList("SPS_BATCH_CHANGE_RATIO.FreeFindSPS_BATCH_CHANGE_RATIO", obj );
	}

	public int countFreeFind(SpsBatchChangeRatio obj) {
        Integer ret = (Integer) queryForObject("SPS_BATCH_CHANGE_RATIO.CountFreeFindSPS_BATCH_CHANGE_RATIO", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchChangeRatio> freeFind(SpsBatchChangeRatio obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BATCH_CHANGE_RATIO.FreeFindSPS_BATCH_CHANGE_RATIO", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchChangeRatio> freeFind(SpsBatchChangeRatio obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBatchChangeRatio){
	    	_hashmap = ((SpsBatchChangeRatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BATCH_CHANGE_RATIO.FreeFindSPS_BATCH_CHANGE_RATIOOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBatchChangeRatio> freeFind(SpsBatchChangeRatio obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBatchChangeRatio){
	    	_hashmap = ((SpsBatchChangeRatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BATCH_CHANGE_RATIO.FreeFindSPS_BATCH_CHANGE_RATIOOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo info,Collection<SpsBatchChangeRatio> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBatchChangeRatio> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBatchChangeRatio oneObj = (SpsBatchChangeRatio)it.next();
				i += save( info,oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo info,SpsBatchChangeRatio vo) {
        SpsBatchChangeRatio obj = (SpsBatchChangeRatio) vo;

        if(exists( obj ) ) {
            return update( info,obj );
        } else {
            return insert(info, obj );
        }
	}

	public int insert(ContextInfo info, SpsBatchChangeRatio obj) {

		obj.fixup();
        return insert(info,"SPS_BATCH_CHANGE_RATIO.InsertSPS_BATCH_CHANGE_RATIO", obj );
	}

	public int update(ContextInfo info,SpsBatchChangeRatio obj) {

		obj.fixup();
		return update( info, "SPS_BATCH_CHANGE_RATIO.UpdateSPS_BATCH_CHANGE_RATIO", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo info,SpsBatchChangeRatio obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(info, "SPS_BATCH_CHANGE_RATIO.DeleteSPS_BATCH_CHANGE_RATIO", obj );

	}

	public int removeObjectTree(ContextInfo info,SpsBatchChangeRatio obj) {

        obj.fixup();
        SpsBatchChangeRatio oldObj = ( SpsBatchChangeRatio )queryForObject("SPS_BATCH_CHANGE_RATIO.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(info, "SPS_BATCH_CHANGE_RATIO.DeleteSPS_BATCH_CHANGE_RATIO", oldObj );

	}

	public boolean exists(SpsBatchChangeRatio vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBatchChangeRatio obj = (SpsBatchChangeRatio) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BATCH_CHANGE_RATIO.CountSPS_BATCH_CHANGE_RATIO", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/15 16:30:31

	/**
	 * 业务类查询总数
	 *  @param   obj
	 * @return    : int
	 *  @createDate   : 11:30 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 11:30 2016/11/17
	 *  @updateAuthor  :
	 */
	public int countFreeFindVo(SpsBatchChangeRatio obj) {

		Integer ret = (Integer) queryForObject("SPS_BATCH_CHANGE_RATIO.CountFreeFindSPS_BATCH_CHANGE_RATIO_VO", obj );
		return ret.intValue();
	}
	/**
	 * 业务类分页查询
	 *  @param   obj, pageIndex, pageSize, orderByColName
	 * @return    : java.util.List<com.xfs.business.model.SpsBatchChangeRatio>
	 *  @createDate   : 11:29 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 11:29 2016/11/17
	 *  @updateAuthor  :
	 */
	@SuppressWarnings("unchecked")
	public List<SpsBatchChangeRatioVo> freeFindVo(SpsBatchChangeRatio obj, int pageIndex, int pageSize, String orderByColName) {

		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof SpsBatchChangeRatio){
			_hashmap = obj.toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return getPaginatedList("SPS_BATCH_CHANGE_RATIO.FreeFindSPS_BATCH_CHANGE_RATIOO_VO_rdered", _hashmap, pageIndex, pageSize );
	}
	
}
