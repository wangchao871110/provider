package com.xfs.bill.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.bill.model.SpsBillTempBusinessField;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsBillTempBusinessFieldDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/01/05 10:43:07
 */
@Repository
public class SpsBillTempBusinessFieldDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMP_BUSINESS_FIELD.CountFindAllSPS_BILL_TEMP_BUSINESS_FIELD", null );
        return ret.intValue();
	}

	public SpsBillTempBusinessField findByPK(SpsBillTempBusinessField obj) {
		Object ret = queryForObject("SPS_BILL_TEMP_BUSINESS_FIELD.FindByPK", obj );
		if(ret !=null)
			return (SpsBillTempBusinessField)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempBusinessField> freeFind(SpsBillTempBusinessField obj) {
		return queryForList("SPS_BILL_TEMP_BUSINESS_FIELD.FreeFindSPS_BILL_TEMP_BUSINESS_FIELD", obj );
	}

	public int countFreeFind(SpsBillTempBusinessField obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMP_BUSINESS_FIELD.CountFreeFindSPS_BILL_TEMP_BUSINESS_FIELD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempBusinessField> freeFind(SpsBillTempBusinessField obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_TEMP_BUSINESS_FIELD.FreeFindSPS_BILL_TEMP_BUSINESS_FIELD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempBusinessField> freeFind(SpsBillTempBusinessField obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTempBusinessField){
	    	_hashmap = ((SpsBillTempBusinessField)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_TEMP_BUSINESS_FIELD.FreeFindSPS_BILL_TEMP_BUSINESS_FIELDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTempBusinessField> freeFind(SpsBillTempBusinessField obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTempBusinessField){
	    	_hashmap = ((SpsBillTempBusinessField)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_TEMP_BUSINESS_FIELD.FreeFindSPS_BILL_TEMP_BUSINESS_FIELDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBillTempBusinessField> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillTempBusinessField> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillTempBusinessField oneObj = (SpsBillTempBusinessField)it.next();
				i += save( cti,oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillTempBusinessField vo) {
        SpsBillTempBusinessField obj = (SpsBillTempBusinessField) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti,SpsBillTempBusinessField obj) {

		obj.fixup();
        return insert(cti, "SPS_BILL_TEMP_BUSINESS_FIELD.InsertSPS_BILL_TEMP_BUSINESS_FIELD", obj );
	}

	public int update(ContextInfo cti, SpsBillTempBusinessField obj) {

		obj.fixup();
		return update( cti,"SPS_BILL_TEMP_BUSINESS_FIELD.UpdateSPS_BILL_TEMP_BUSINESS_FIELD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SpsBillTempBusinessField obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_TEMP_BUSINESS_FIELD.DeleteSPS_BILL_TEMP_BUSINESS_FIELD", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBillTempBusinessField obj) {

        obj.fixup();
        SpsBillTempBusinessField oldObj = ( SpsBillTempBusinessField )queryForObject("SPS_BILL_TEMP_BUSINESS_FIELD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"SPS_BILL_TEMP_BUSINESS_FIELD.DeleteSPS_BILL_TEMP_BUSINESS_FIELD", oldObj );

	}

	public boolean exists(SpsBillTempBusinessField vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillTempBusinessField obj = (SpsBillTempBusinessField) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_TEMP_BUSINESS_FIELD.CountSPS_BILL_TEMP_BUSINESS_FIELD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 10:43:07
    @SuppressWarnings("unchecked")
    public List<String> FreeFindSPS_BILL_TEMP_FIELD_Ids(SpsBillTempBusinessField obj) {
        return queryForList("SPS_BILL_TEMP_BUSINESS_FIELD.FreeFindSPS_BILL_TEMP_FIELD_IDS", obj );
    }


    public int logicDelSpsBillTempField(ContextInfo cti, Integer tempId) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("tempId", tempId);
        return update(cti, "SPS_BILL_TEMP_BUSINESS_FIELD.LogicDelSPS_BILL_TEMP_BUSINESS_FIELD", _hashmap);
    }

    public int insertBusinessFieldByDicitem(ContextInfo cti,SpsBillTemplate obj) {

        obj.fixup();
        return insert(cti, "SPS_BILL_TEMP_BUSINESS_FIELD.InsertSPS_BILL_TEMP_BUSINESS_FIELD_BY_DICITEM", obj );
    }




	
}
