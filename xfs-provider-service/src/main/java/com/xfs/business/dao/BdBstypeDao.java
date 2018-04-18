package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BdBstype;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdBstypeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 11:25:05
 */
@Repository
public class BdBstypeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BSTYPE.CountFindAllBD_BSTYPE", null );
        return ret.intValue();
	}

	public BdBstype findByPK(BdBstype obj) {
		Object ret = queryForObject("BD_BSTYPE.FindByPK", obj );
		if(ret !=null)
			return (BdBstype)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstype> freeFind(BdBstype obj) {
		return queryForList("BD_BSTYPE.FreeFindBD_BSTYPE", obj );
	}

	public int countFreeFind(BdBstype obj) {
        Integer ret = (Integer) queryForObject("BD_BSTYPE.CountFreeFindBD_BSTYPE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstype> freeFind(BdBstype obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BSTYPE.FreeFindBD_BSTYPE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstype> freeFind(BdBstype obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstype){
	    	_hashmap = ((BdBstype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BSTYPE.FreeFindBD_BSTYPEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBstype> freeFind(BdBstype obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBstype){
	    	_hashmap = ((BdBstype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BSTYPE.FreeFindBD_BSTYPEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBstype> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBstype> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBstype oneObj = (BdBstype)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBstype vo) {
        BdBstype obj = (BdBstype) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBstype obj) {

		obj.fixup();
        return insert(cti, "BD_BSTYPE.InsertBD_BSTYPE", obj );
	}

	public int update(ContextInfo cti, BdBstype obj) {

		obj.fixup();
		return update(cti, "BD_BSTYPE.UpdateBD_BSTYPE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBstype obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BSTYPE.DeleteBD_BSTYPE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBstype obj) {

        obj.fixup();
        BdBstype oldObj = ( BdBstype )queryForObject("BD_BSTYPE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BSTYPE.DeleteBD_BSTYPE", oldObj );

	}

	public boolean exists(BdBstype vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBstype obj = (BdBstype) vo;

        keysFilled = keysFilled && ( null != obj.getBstypeId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BSTYPE.CountBD_BSTYPE", obj );
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
	 * 获取单个城市业务类型列表
	 * @param areaId
	 * @return
     */
	public List<BdBstype> findBdBsTypeByAreaId(int areaId,int pageIndex, int pageSize){
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("areaId",areaId);
		List<BdBstype> bdBstypes = getPaginatedList("BD_BSTYPE.findBdBsTypeByAreaId", hashMap,pageIndex,pageSize );
		return bdBstypes;
	}

	public int countBsTypeByAreaId(int areaId) {
		HashMap<String,Object> hashMap = new HashMap<>();
		hashMap.put("areaId",areaId);
		Integer ret = (Integer) queryForObject("BD_BSTYPE.countBsTypeByAreaId", hashMap );
		return ret.intValue();
	}


	public List<BdBstype> findBstypeList(BdBstype ob) {
		return queryForList("BD_BSTYPE.find_bdBstype", ob);
	}


	public List<BdBstype> findBdBstypeListByAreaId(Integer areaId){
		HashMap<String,Object> map =new HashMap<>();
		map.put("areaId",areaId);
		return queryForList("BD_BSTYPE.findBdBstypeListByAreaId",map);
	}

	/**
	 * 获取员工服务
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<BdBstype> 
	 *  @createDate  	: 2017年6月7日 下午2:37:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 下午2:37:50
	 *  @updateAuthor  :
	 */
	public List<BdBstype> findEmpServiceByAreaId(Integer areaId,Integer bsType) {
		HashMap<String,Object> map =new HashMap<>();
		map.put("areaId",areaId);
		map.put("bsType",bsType);
		return queryForList("BD_BSTYPE.findEmpServiceByAreaId",map);
	}

	/**
	 * 获取增值服务
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<BdBstype> 
	 *  @createDate  	: 2017年6月7日 下午2:37:53
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 下午2:37:53
	 *  @updateAuthor  :
	 */
	public List<BdBstype> findAddServiceByAreaId(Integer areaId) {
		HashMap<String,Object> map =new HashMap<>();
		map.put("areaId",areaId);
		return queryForList("BD_BSTYPE.findAddService",map);
	}

}
