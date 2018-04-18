package com.xfs.bill.dao;

import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * SpsBillTemplateDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/27 14:06:22
 */
@Repository
public class SpsBillTemplateDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMPLATE.CountFindAllSPS_BILL_TEMPLATE", null );
        return ret.intValue();
	}

	public SpsBillTemplate findByPK(SpsBillTemplate obj) {
		Object ret = queryForObject("SPS_BILL_TEMPLATE.FindByPK", obj );
		if(ret !=null)
			return (SpsBillTemplate)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTemplate> freeFind(SpsBillTemplate obj) {
		return queryForList("SPS_BILL_TEMPLATE.FreeFindSPS_BILL_TEMPLATE", obj );
	}

	public int countFreeFind(SpsBillTemplate obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL_TEMPLATE.CountFreeFindSPS_BILL_TEMPLATE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTemplate> freeFind(SpsBillTemplate obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL_TEMPLATE.FreeFindSPS_BILL_TEMPLATE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTemplate> freeFind(SpsBillTemplate obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTemplate){
	    	_hashmap = ((SpsBillTemplate)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL_TEMPLATE.FreeFindSPS_BILL_TEMPLATEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBillTemplate> freeFind(SpsBillTemplate obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBillTemplate){
	    	_hashmap = ((SpsBillTemplate)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL_TEMPLATE.FreeFindSPS_BILL_TEMPLATEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll( ContextInfo cti, Collection<SpsBillTemplate> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBillTemplate> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBillTemplate oneObj = (SpsBillTemplate)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBillTemplate vo) {
        SpsBillTemplate obj = (SpsBillTemplate) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, SpsBillTemplate obj) {

		obj.fixup();
        return insert(cti,"SPS_BILL_TEMPLATE.InsertSPS_BILL_TEMPLATE", obj );
	}

	public int update(ContextInfo cti, SpsBillTemplate obj) {

		obj.fixup();
		return update(cti, "SPS_BILL_TEMPLATE.UpdateSPS_BILL_TEMPLATE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBillTemplate obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL_TEMPLATE.DeleteSPS_BILL_TEMPLATE", obj );

	}

	public int removeObjectTree(ContextInfo cti,SpsBillTemplate obj) {

        obj.fixup();
        SpsBillTemplate oldObj = (SpsBillTemplate)queryForObject("SPS_BILL_TEMPLATE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"SPS_BILL_TEMPLATE.DeleteSPS_BILL_TEMPLATE", oldObj );

	}

	public boolean exists(SpsBillTemplate vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBillTemplate obj = (SpsBillTemplate) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL_TEMPLATE.CountSPS_BILL_TEMPLATE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:06:22


    public Integer findBillTempCount(SpsBillTemplate vo,  String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("createBy", vo.getCreateBy());
        obj.put("name", vo.getNameEq());
        obj.put("spId", vo.getSpId());
        obj.put("isEffect",  vo.getIsEffect());
        obj.put("isDefault", vo.getIsDefault());

        Integer ret = (Integer) queryForObject("SPS_BILL_TEMPLATE.Find_BillTemp_List_By_Condition_Count", obj);
        return ret.intValue();
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findBillTempList(SpsBillTemplate vo, int pageIndex, int pageSize, String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("name", vo.getNameEq());
        obj.put("spId", vo.getSpId());
        obj.put("isEffect",  vo.getIsEffect());
        obj.put("isDefault", vo.getIsDefault());
        return getPaginatedList("SPS_BILL_TEMPLATE.Find_BillTemp_List_By_Condition", obj, pageIndex, pageSize);
    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int removeLogic(ContextInfo cti, SpsBillTemplate obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return update(cti, "SPS_BILL_TEMPLATE.Lgoic_del_CM_BillTemp", obj);

    }

    public Integer Find_USED_List_By_Condition_Count(ContextInfo cti, String name, Integer temp_id, String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("corpName", name);
        obj.put("spId", cti.getOrgId());
        obj.put("tempId", temp_id);

        Integer ret = (Integer) queryForObject("SPS_BILL_TEMPLATE.Find_USED_List_By_Condition_Count", obj);
        return ret.intValue();
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> Find_USED_List_By_Condition(ContextInfo cti, String name, Integer temp_id, int pageIndex, int pageSize, String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("corpName", name);
        obj.put("spId", cti.getOrgId());
        obj.put("tempId", temp_id);
        return getPaginatedList("SPS_BILL_TEMPLATE.Find_USED_List_By_Condition", obj, pageIndex, pageSize);
    }




@SuppressWarnings("unchecked")
public List<Map<String,Object>> findBillTempListBySpId(Integer spId){
	Map<String,Object> obj = new HashMap<String,Object>();
	obj.put("spId", spId);
	obj.put("dr", 0);
	return queryForList("SPS_BILL_TEMPLATE.SPSTEMPLETE_BY_SPID", obj);
}


}
