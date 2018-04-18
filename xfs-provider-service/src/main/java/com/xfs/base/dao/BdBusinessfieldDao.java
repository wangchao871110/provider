package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.dto.BsBussinessFieldVo;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/** 
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:11
 * @version 	: V1.0
 */
@Repository
public class BdBusinessfieldDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BUSINESSFIELD.CountFindAllBD_BUSINESSFIELD", null );
        return ret.intValue();
	}

	public BdBusinessfield findByPK(BdBusinessfield obj) {
		Object ret = queryForObject("BD_BUSINESSFIELD.FindByPK", obj );
		if(ret !=null)
			return (BdBusinessfield)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBusinessfield> freeFind(BdBusinessfield obj) {
		return queryForList("BD_BUSINESSFIELD.FreeFindBD_BUSINESSFIELD", obj );
	}

	public int countFreeFind(BdBusinessfield obj) {
        Integer ret = (Integer) queryForObject("BD_BUSINESSFIELD.CountFreeFindBD_BUSINESSFIELD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBusinessfield> freeFind(BdBusinessfield obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BUSINESSFIELD.FreeFindBD_BUSINESSFIELD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBusinessfield> freeFind(BdBusinessfield obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBusinessfield){
	    	_hashmap = ((BdBusinessfield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BUSINESSFIELD.FreeFindBD_BUSINESSFIELDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBusinessfield> freeFind(BdBusinessfield obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBusinessfield){
	    	_hashmap = ((BdBusinessfield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BUSINESSFIELD.FreeFindBD_BUSINESSFIELDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBusinessfield> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBusinessfield> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBusinessfield oneObj = (BdBusinessfield)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBusinessfield vo) {
        BdBusinessfield obj = (BdBusinessfield) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBusinessfield obj) {

		obj.fixup();
        return insert(cti, "BD_BUSINESSFIELD.InsertBD_BUSINESSFIELD", obj );
	}

	public int update(ContextInfo cti, BdBusinessfield obj) {

		obj.fixup();
		return update(cti, "BD_BUSINESSFIELD.UpdateBD_BUSINESSFIELD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBusinessfield obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BUSINESSFIELD.DeleteBD_BUSINESSFIELD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBusinessfield obj) {

        obj.fixup();
        BdBusinessfield oldObj = ( BdBusinessfield )queryForObject("BD_BUSINESSFIELD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BUSINESSFIELD.DeleteBD_BUSINESSFIELD", oldObj );

	}

	public boolean exists(BdBusinessfield vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBusinessfield obj = (BdBusinessfield) vo;

        keysFilled = keysFilled && ( null != obj.getFieldId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BUSINESSFIELD.CountBD_BUSINESSFIELD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:08

	/**
	 * 通过类型查询字段范围
	 * @param typeId
	 * @return
     */
	public List<BsBussinessFieldVo> freeFindByType(Integer typeId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("typeId", typeId );

		return queryForList("BD_BUSINESSFIELD.FindBD_BUSINESSFIELD_BY_TYPE", _hashmap);
	}

	public List<BdBusinessfield> findbsTypeAreaFieldPage(int areaId,int bstypeId, int pageIndex, int pageSize) {
		HashMap<String,Object> hashMap=new HashMap<>();
		hashMap.put("areaId",areaId);
		hashMap.put("bstypeId",bstypeId);
		return getPaginatedList("BD_BUSINESSFIELD.findbsTypeAreaFieldPage", hashMap, pageIndex, pageSize );
	}

	/**
	 * 根据地区动态获取控件组
	 * @param areaId
	 * @param bstypeId
	 * @return
	 */
	public List<BusinessField> queryBusinessFields(Integer areaId, Integer[] bstypeId){
		HashMap<String,Object> hm = new HashMap<>();
		hm.put("areaId",areaId);
		hm.put("bstypeId",bstypeId);
		return queryForList("BD_BUSINESSFIELD.QUERY_BUSINESS_FIELDS",hm);
	}

	public List<BdBusinessfield> bdBusinessFieldList(Integer areaId, Integer bstypeId){
		HashMap<String,Object> map = new HashMap<>();
		map.put("areaId",areaId);
		map.put("bstypeId",bstypeId);
		return queryForList("BD_BUSINESSFIELD.bdBusinessFieldList",map);
	}

    /**
     * 根据地区动态获取控件组
     * @return
     */
    public List<BdBusinessfield> queryBusinessFieldsBySource(BdBusinessfield obj){
        HashMap<String,Object> hm = new HashMap<>();
        return queryForList("BD_BUSINESSFIELD.FreeFindBD_BUSINESSFIELD_By_Source",hm);
    }

	public BdBusinessfield findNameByCode(BdBusinessfield vo) {
		Object ret = queryForObject("BD_BUSINESSFIELD.findNameByCode", vo );
		if(ret !=null)
			return (BdBusinessfield)ret;
		else 
			return null;
	}
}
