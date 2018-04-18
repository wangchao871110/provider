package com.xfs.bill.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.bill.model.SpsBillTempCorp;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsBillTempCorpDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/30 10:15:54
 */
@Repository
public class SpsBillTempCorpDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMP_CORP.CountFindAllSPS_BILL_TEMP_CORP", null );
        return ret.intValue();
	}

	public SpsBillTempCorp findByPK(SpsBillTempCorp obj) {
		Object ret = queryForObject("SPS_BILL_TEMP_CORP.FindByPK", obj );
		if(ret !=null)
			return (SpsBillTempCorp)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempCorp> freeFind(SpsBillTempCorp obj) {
		return queryForList("SPS_BILL_TEMP_CORP.FreeFindSPS_BILL_TEMP_CORP", obj );
	}

	public int countFreeFind(SpsBillTempCorp obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMP_CORP.CountFreeFindSPS_BILL_TEMP_CORP", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempCorp> freeFind(SpsBillTempCorp obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_TEMP_CORP.FreeFindSPS_BILL_TEMP_CORP", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempCorp> freeFind(SpsBillTempCorp obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTempCorp){
	    	_hashmap = ((SpsBillTempCorp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_TEMP_CORP.FreeFindSPS_BILL_TEMP_CORPOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempCorp> freeFind(SpsBillTempCorp obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTempCorp){
	    	_hashmap = ((SpsBillTempCorp)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_TEMP_CORP.FreeFindSPS_BILL_TEMP_CORPOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillTempCorp> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillTempCorp> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillTempCorp oneObj = (SpsBillTempCorp)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillTempCorp vo) {
        SpsBillTempCorp obj = (SpsBillTempCorp) vo;

        if(exists( obj ) ) {
            return update(cti,obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, SpsBillTempCorp obj) {

		obj.fixup();
        return insert(cti,"SPS_BILL_TEMP_CORP.InsertSPS_BILL_TEMP_CORP", obj );
	}

	public int update(ContextInfo cti, SpsBillTempCorp obj) {

		obj.fixup();
		return update( cti, "SPS_BILL_TEMP_CORP.UpdateSPS_BILL_TEMP_CORP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBillTempCorp obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_TEMP_CORP.DeleteSPS_BILL_TEMP_CORP", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillTempCorp obj) {

        obj.fixup();
        SpsBillTempCorp oldObj = ( SpsBillTempCorp )queryForObject("SPS_BILL_TEMP_CORP.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti, "SPS_BILL_TEMP_CORP.DeleteSPS_BILL_TEMP_CORP", oldObj );

	}

	public boolean exists(SpsBillTempCorp vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillTempCorp obj = (SpsBillTempCorp) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_TEMP_CORP.CountSPS_BILL_TEMP_CORP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/30 10:15:54

    @SuppressWarnings("unchecked")
    public List<String> FreeFindSPS_BILL_TEMP_CORP_Ids(SpsBillTempCorp obj) {
        return queryForList("SPS_BILL_TEMP_CORP.FreeFindSPS_BILL_TEMP_CORP_Ids", obj );
    }


    public int logicRemove(ContextInfo cti, Integer tempId,  List<Integer> corpIds) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("corpIds", corpIds );
        _hashmap.put("spId", cti.getOrgId());
        _hashmap.put("tempId", tempId);
        return update(cti, "SPS_BILL_TEMP_CORP.LOGIC_REMOVE_BILL_TEMP_CORP", _hashmap);
    }


    public int logicRemoveSelf(ContextInfo cti,List<Integer> corpIds) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("corpIds", corpIds );
        _hashmap.put("spId", cti.getOrgId());
       // _hashmap.put("tempId", tempId);
        return update(cti, "SPS_BILL_TEMP_CORP.LOGIC_REMOVE_BILL_TEMP_CORP_SELF", _hashmap);
    }
    /**
     * 根据 cpId,spId,dr 查询企业模板中间表中是否存在
     * @param vo
     * @return
     */
    public SpsBillTempCorp dataExisits(SpsBillTempCorp vo) {
    	return (SpsBillTempCorp) queryForObject("SPS_BILL_TEMP_CORP.CountSPS_BILL_TEMP_CORP_BY_SPID_CPID_DR", vo );
    }
	
}
