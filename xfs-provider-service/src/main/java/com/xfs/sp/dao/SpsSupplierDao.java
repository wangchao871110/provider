package com.xfs.sp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.sp.model.SpsSupplier;

/**
 * SpsSupplierDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/07/11 16:01:43
 */
@Repository
public class SpsSupplierDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_SUPPLIER.CountFindAllSPS_SUPPLIER", null );
        return ret.intValue();
	}

	public SpsSupplier findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("SPS_SUPPLIER.FindByPK", _hashmap );
		if(ret !=null)
			return (SpsSupplier)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSupplier> freeFind(SpsSupplier obj) {
		return queryForList("SPS_SUPPLIER.FreeFindSPS_SUPPLIER", obj );
	}

	public int countFreeFind(SpsSupplier obj) {
        Integer ret = (Integer) queryForObject("SPS_SUPPLIER.CountFreeFindSPS_SUPPLIER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSupplier> freeFind(SpsSupplier obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SUPPLIER.FreeFindSPS_SUPPLIER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSupplier> freeFind(SpsSupplier obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSupplier){
	    	_hashmap = ((SpsSupplier)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_SUPPLIER.FreeFindSPS_SUPPLIEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsSupplier> freeFind(SpsSupplier obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsSupplier){
	    	_hashmap = ((SpsSupplier)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_SUPPLIER.FreeFindSPS_SUPPLIEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SpsSupplier> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsSupplier> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsSupplier oneObj = (SpsSupplier)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SpsSupplier vo) {
        SpsSupplier obj = (SpsSupplier) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti,SpsSupplier obj) {

		obj.fixup();
        return insert(cti,"SPS_SUPPLIER.InsertSPS_SUPPLIER", obj );
	}

	public int update(ContextInfo cti,SpsSupplier obj) {

		obj.fixup();
		return update(cti, "SPS_SUPPLIER.UpdateSPS_SUPPLIER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SpsSupplier obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_SUPPLIER.DeleteSPS_SUPPLIER", obj );

	}

	public int removeObjectTree(ContextInfo cti,SpsSupplier obj) {

        obj.fixup();
        SpsSupplier oldObj = ( SpsSupplier )queryForObject("SPS_SUPPLIER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_SUPPLIER.DeleteSPS_SUPPLIER", oldObj );

	}

	public boolean exists(SpsSupplier vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsSupplier obj = (SpsSupplier) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_SUPPLIER.CountSPS_SUPPLIER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/07/11 16:01:43
	/**
	 * 查询服务商信息 记录数
	 *
	 * @author lifq
	 *
	 * 2017年7月13日  下午3:03:32
	 */
	public int countFindSpServicePage(Map<String,Object> paraMap) {
        Integer ret = (Integer) queryForObject("SPS_SUPPLIER.countFindSpServicePage", paraMap);
        return ret.intValue();
	}
	/**
	 * 查询服务商信息 
	 *
	 * @author lifq
	 *
	 * 2017年7月13日  下午3:04:33
	 */
	public List<Map<String,Object>> findSpServicePage(Map<String,Object> paraMap, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_SUPPLIER.findSpServicePage", paraMap, pageIndex, pageSize );
	}
	/**
	 * 查询 该企业下 所有方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月14日  下午4:29:15
	 */
	public List<Map<String,Object>> findAllScheme(Map<String,Object> paraMap){
		return queryForList("SPS_SUPPLIER.findAllScheme", paraMap);
	}
	/**
	 * 查询 企业下方案中 城市信息
	 *
	 * @author lifq
	 *
	 * 2017年7月17日  上午11:13:27
	 */
	public List<Map<String,Object>> findAllSchemeCity(Map<String,Object> paraMap){
		return queryForList("SPS_SUPPLIER.findAllSchemeCity", paraMap);
	}
	
}
