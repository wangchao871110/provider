package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpArea;

/**
 * CpAreaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/10 16:19:46
 */
@Repository
public class CpAreaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_AREA.CountFindAllCP_AREA", null );
        return ret.intValue();
	}

	public CpArea findByPK(CpArea obj) {
		Object ret = queryForObject("CP_AREA.FindByPK", obj );
		if(ret !=null)
			return (CpArea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpArea> freeFind(CpArea obj) {
		return queryForList("CP_AREA.FreeFindCP_AREA", obj );
	}

	public int countFreeFind(CpArea obj) {
        Integer ret = (Integer) queryForObject("CP_AREA.CountFreeFindCP_AREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpArea> freeFind(CpArea obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_AREA.FreeFindCP_AREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpArea> freeFind(CpArea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpArea){
	    	_hashmap = ((CpArea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_AREA.FreeFindCP_AREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpArea> freeFind(CpArea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpArea){
	    	_hashmap = ((CpArea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_AREA.FreeFindCP_AREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpArea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpArea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpArea oneObj = (CpArea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpArea vo) {
        CpArea obj = (CpArea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpArea obj) {

		obj.fixup();
        return insert(cti, "CP_AREA.InsertCP_AREA", obj );
	}

	public int update(ContextInfo cti, CpArea obj) {

		obj.fixup();
		return update(cti, "CP_AREA.UpdateCP_AREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpArea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_AREA.DeleteCP_AREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpArea obj) {

        obj.fixup();
        CpArea oldObj = ( CpArea )queryForObject("CP_AREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_AREA.DeleteCP_AREA", oldObj );

	}

	public boolean exists(CpArea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpArea obj = (CpArea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_AREA.CountCP_AREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/10 16:19:46
	
	/**
	 * 
	 * @method comments: 更加多个区域ID获取区域名称
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月11日 下午9:03:37 
	 * @param areaId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午9:03:37 wangchao 创建
	 *
	 */
	public String findNameInId(String areaId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    _hashmap.put("areaId", areaId );
		String ret = (String) queryForObject("CP_AREA.findNameInId", _hashmap );
		return ret;
	}

	/**
	 * 
	 * @method comments: 获取发包城市
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月26日 下午8:07:00 
	 * @param cpArea
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月26日 下午8:07:00 wangchao 创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<CpArea> findCityByPackage(CpArea cpArea) {
		return queryForList("CP_AREA.findCityByPackage", cpArea );
	}
	
}
