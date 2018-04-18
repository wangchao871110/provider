package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BdBstypeareafield;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdBstypeareafieldDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 11:25:05
 */
@Repository
public class BdBstypeareafieldDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BSTYPEAREAFIELD.CountFindAllBD_BSTYPEAREAFIELD", null );
        return ret.intValue();
	}

	public BdBstypeareafield findByPK(BdBstypeareafield obj) {
		Object ret = queryForObject("BD_BSTYPEAREAFIELD.FindByPK", obj );
		if(ret !=null)
			return (BdBstypeareafield)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypeareafield> freeFind(BdBstypeareafield obj) {
		return queryForList("BD_BSTYPEAREAFIELD.FreeFindBD_BSTYPEAREAFIELD", obj );
	}

	public int countFreeFind(BdBstypeareafield obj) {
        Integer ret = (Integer) queryForObject("BD_BSTYPEAREAFIELD.CountFreeFindBD_BSTYPEAREAFIELD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypeareafield> freeFind(BdBstypeareafield obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BSTYPEAREAFIELD.FreeFindBD_BSTYPEAREAFIELD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypeareafield> freeFind(BdBstypeareafield obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstypeareafield){
	    	_hashmap = ((BdBstypeareafield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BSTYPEAREAFIELD.FreeFindBD_BSTYPEAREAFIELDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstypeareafield> freeFind(BdBstypeareafield obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstypeareafield){
	    	_hashmap = ((BdBstypeareafield)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BSTYPEAREAFIELD.FreeFindBD_BSTYPEAREAFIELDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBstypeareafield> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBstypeareafield> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBstypeareafield oneObj = (BdBstypeareafield)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBstypeareafield vo) {
        BdBstypeareafield obj = (BdBstypeareafield) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBstypeareafield obj) {

		obj.fixup();
        return insert(cti, "BD_BSTYPEAREAFIELD.InsertBD_BSTYPEAREAFIELD", obj );
	}

	public int update(ContextInfo cti, BdBstypeareafield obj) {

		obj.fixup();
		return update(cti, "BD_BSTYPEAREAFIELD.UpdateBD_BSTYPEAREAFIELD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBstypeareafield obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BSTYPEAREAFIELD.DeleteBD_BSTYPEAREAFIELD", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBstypeareafield obj) {

        obj.fixup();
        BdBstypeareafield oldObj = ( BdBstypeareafield )queryForObject("BD_BSTYPEAREAFIELD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BSTYPEAREAFIELD.DeleteBD_BSTYPEAREAFIELD", oldObj );

	}

	public boolean exists(BdBstypeareafield vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBstypeareafield obj = (BdBstypeareafield) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BSTYPEAREAFIELD.CountBD_BSTYPEAREAFIELD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:05

	/**
	 * 通过 城市与类型删除 配置
	 * @param obj
	 * @return
     */
	public int removeObjectByAreaAndType(ContextInfo cti, BdBstypeareafield obj) {

		obj.fixup();
		if ( obj == null ) {
			return 0;
		}
		return delete(cti, "BD_BSTYPEAREAFIELD.DeleteBD_BSTYPEAREAFIELD_BY_AREAANDTYPE", obj );
	}

	public BdBstypeareafield findBdBstypeareafield(BdBstypeareafield vo){
		Object ret = queryForObject("BD_BSTYPEAREAFIELD.findBdBstypeareafield", vo );
		if(ret !=null)
			return (BdBstypeareafield)ret;
		else
			return null;
	}

	public int delete(ContextInfo cti, BdBstypeareafield obj) {

		if ( obj == null ) {
			return 0;
		}

		obj.fixup();

		return delete(cti, "BD_BSTYPEAREAFIELD.DELETE_BdBstypeareafield", obj );

	}

	public List<BdBstypeareafield> findBdBstypeareafieldList(BdBstypeareafield vo){
		return queryForList("BD_BSTYPEAREAFIELD.findBdBstypeareafieldList",vo);
	}

	/**
	 * 查询 某个业务、某个区域下 字段信息
	 *
	 * @author lifq
	 *
	 * 2016年5月18日  下午5:40:24
	 */
	public List<Map<String,String>> findBsTypeField(BdBstypeareafield vo){
		return queryForList("BD_BSTYPEAREAFIELD.findBsTypeField",vo);
	}
	public List<Map<String,String>> findBsTypeFieldByName(BdBstypeareafield vo){
		return queryForList("BD_BSTYPEAREAFIELD.findBsTypeFieldByName",vo);
	}



	public List<Map<String,String>> findBsTypeFieldList(String code){
		Map<String,String> map=new HashMap();
		map.put("code",code);
		return queryForList("BD_BSTYPEAREAFIELD.findBsTypeFieldList",map);
	}
	
}
