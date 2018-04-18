package com.xfs.op.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpHrplusCalcConfig;

/**
 * OpHrplusCalcConfigDao
 * @author:yangfw@xinfushe.com
 * @date:2017/04/06 16:45:09
 */
@Repository
public class OpHrplusCalcConfigDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_CALC_CONFIG.CountFindAllOP_HRPLUS_CALC_CONFIG", null );
        return ret.intValue();
	}

	public OpHrplusCalcConfig findByPK(OpHrplusCalcConfig obj) {
		Object ret = queryForObject("OP_HRPLUS_CALC_CONFIG.FindByPK", obj );
		if(ret !=null)
			return (OpHrplusCalcConfig)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusCalcConfig> freeFind(OpHrplusCalcConfig obj) {
		return queryForList("OP_HRPLUS_CALC_CONFIG.FreeFindOP_HRPLUS_CALC_CONFIG", obj );
	}

	public int countFreeFind(OpHrplusCalcConfig obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_CALC_CONFIG.CountFreeFindOP_HRPLUS_CALC_CONFIG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusCalcConfig> freeFind(OpHrplusCalcConfig obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_HRPLUS_CALC_CONFIG.FreeFindOP_HRPLUS_CALC_CONFIG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusCalcConfig> freeFind(OpHrplusCalcConfig obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusCalcConfig){
	    	_hashmap = ((OpHrplusCalcConfig)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_HRPLUS_CALC_CONFIG.FreeFindOP_HRPLUS_CALC_CONFIGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpHrplusCalcConfig> freeFind(OpHrplusCalcConfig obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpHrplusCalcConfig){
	    	_hashmap = ((OpHrplusCalcConfig)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_HRPLUS_CALC_CONFIG.FreeFindOP_HRPLUS_CALC_CONFIGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpHrplusCalcConfig> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpHrplusCalcConfig> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpHrplusCalcConfig oneObj = (OpHrplusCalcConfig)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpHrplusCalcConfig vo) {
        OpHrplusCalcConfig obj = (OpHrplusCalcConfig) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpHrplusCalcConfig obj) {

		obj.fixup();
        return insert(cti,"OP_HRPLUS_CALC_CONFIG.InsertOP_HRPLUS_CALC_CONFIG", obj );
	}

	public int update(ContextInfo cti,OpHrplusCalcConfig obj) {

		obj.fixup();
		return update(cti, "OP_HRPLUS_CALC_CONFIG.UpdateOP_HRPLUS_CALC_CONFIG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpHrplusCalcConfig obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_HRPLUS_CALC_CONFIG.DeleteOP_HRPLUS_CALC_CONFIG", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpHrplusCalcConfig obj) {

        obj.fixup();
        OpHrplusCalcConfig oldObj = ( OpHrplusCalcConfig )queryForObject("OP_HRPLUS_CALC_CONFIG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_HRPLUS_CALC_CONFIG.DeleteOP_HRPLUS_CALC_CONFIG", oldObj );

	}

	public boolean exists(OpHrplusCalcConfig vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpHrplusCalcConfig obj = (OpHrplusCalcConfig) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_HRPLUS_CALC_CONFIG.CountOP_HRPLUS_CALC_CONFIG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/06 16:45:09
	
	
	
}
