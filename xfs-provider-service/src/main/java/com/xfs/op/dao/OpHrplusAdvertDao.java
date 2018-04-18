package com.xfs.op.dao;
import java.util.*;

import com.xfs.op.model.OpHrplusAdvert;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;

/**
 * OpHrplusAdvertDao
 * @author:yangfw@xinfushe.com
 * @date:2017/03/09 10:56:40
 */
@Repository
public class OpHrplusAdvertDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_ADVERT.CountFindAllOP_HRPLUS_ADVERT", null );
        return ret.intValue();
	}

	public OpHrplusAdvert findByPK(OpHrplusAdvert obj) {
		Object ret = queryForObject("OP_HRPLUS_ADVERT.FindByPK", obj );
		if(ret !=null)
			return (OpHrplusAdvert)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusAdvert> freeFind(OpHrplusAdvert obj) {
		return queryForList("OP_HRPLUS_ADVERT.FreeFindOP_HRPLUS_ADVERT", obj );
	}

	public int countFreeFind(OpHrplusAdvert obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_ADVERT.CountFreeFindOP_HRPLUS_ADVERT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusAdvert> freeFind(OpHrplusAdvert obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_HRPLUS_ADVERT.FreeFindOP_HRPLUS_ADVERT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusAdvert> freeFind(OpHrplusAdvert obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusAdvert){
	    	_hashmap = ((OpHrplusAdvert)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_HRPLUS_ADVERT.FreeFindOP_HRPLUS_ADVERTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusAdvert> freeFind(OpHrplusAdvert obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusAdvert){
	    	_hashmap = ((OpHrplusAdvert)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_HRPLUS_ADVERT.FreeFindOP_HRPLUS_ADVERTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpHrplusAdvert> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpHrplusAdvert> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpHrplusAdvert oneObj = (OpHrplusAdvert)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpHrplusAdvert vo) {
        OpHrplusAdvert obj = (OpHrplusAdvert) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpHrplusAdvert obj) {

		obj.fixup();
        return insert(cti,"OP_HRPLUS_ADVERT.InsertOP_HRPLUS_ADVERT", obj );
	}

	public int update(ContextInfo cti,OpHrplusAdvert obj) {

		obj.fixup();
		return update(cti, "OP_HRPLUS_ADVERT.UpdateOP_HRPLUS_ADVERT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpHrplusAdvert obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_HRPLUS_ADVERT.DeleteOP_HRPLUS_ADVERT", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpHrplusAdvert obj) {

        obj.fixup();
        OpHrplusAdvert oldObj = ( OpHrplusAdvert )queryForObject("OP_HRPLUS_ADVERT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_HRPLUS_ADVERT.DeleteOP_HRPLUS_ADVERT", oldObj );

	}

	public boolean exists(OpHrplusAdvert vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpHrplusAdvert obj = (OpHrplusAdvert) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_HRPLUS_ADVERT.CountOP_HRPLUS_ADVERT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:40


    /**
     * count to 分页
     * @param obj
     * @return
     */
    public int finManageAdvertListCount(Map<String,Object> obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_ADVERT.finManageAdvertListCount", obj );
        return ret.intValue();
    }


    /**
     * 分页查询
     * @param obj
     * @param offset
     * @param pagesize
     * @return
     */
    public List<Map<String,Object>> finManageAdvertList(Map<String,Object> obj,Integer offset,Integer pagesize){
        return getPaginatedList("OP_HRPLUS_ADVERT.finManageAdvertList",obj,offset,pagesize);
    }
	
	
}
