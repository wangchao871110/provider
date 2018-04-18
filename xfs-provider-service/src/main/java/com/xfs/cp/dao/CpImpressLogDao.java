package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpImpressLog;

/**
 * CpImpressLogDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:20
 */
@Repository
public class CpImpressLogDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_IMPRESS_LOG.CountFindAllCP_IMPRESS_LOG", null );
        return ret.intValue();
	}

	public CpImpressLog findByPK(CpImpressLog obj) {
		Object ret = queryForObject("CP_IMPRESS_LOG.FindByPK", obj );
		if(ret !=null)
			return (CpImpressLog)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpressLog> freeFind(CpImpressLog obj) {
		return queryForList("CP_IMPRESS_LOG.FreeFindCP_IMPRESS_LOG", obj );
	}

	public int countFreeFind(CpImpressLog obj) {
        Integer ret = (Integer) queryForObject("CP_IMPRESS_LOG.CountFreeFindCP_IMPRESS_LOG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpressLog> freeFind(CpImpressLog obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_IMPRESS_LOG.FreeFindCP_IMPRESS_LOG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpressLog> freeFind(CpImpressLog obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpImpressLog){
	    	_hashmap = ((CpImpressLog)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_IMPRESS_LOG.FreeFindCP_IMPRESS_LOGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpImpressLog> freeFind(CpImpressLog obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpImpressLog){
	    	_hashmap = ((CpImpressLog)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_IMPRESS_LOG.FreeFindCP_IMPRESS_LOGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpImpressLog> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpImpressLog> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpImpressLog oneObj = (CpImpressLog)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpImpressLog vo) {
        CpImpressLog obj = (CpImpressLog) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpImpressLog obj) {

		obj.fixup();
        return insert(cti, "CP_IMPRESS_LOG.InsertCP_IMPRESS_LOG", obj );
	}

	public int update(ContextInfo cti, CpImpressLog obj) {

		obj.fixup();
		return update(cti, "CP_IMPRESS_LOG.UpdateCP_IMPRESS_LOG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpImpressLog obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_IMPRESS_LOG.DeleteCP_IMPRESS_LOG", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpImpressLog obj) {

        obj.fixup();
        CpImpressLog oldObj = ( CpImpressLog )queryForObject("CP_IMPRESS_LOG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_IMPRESS_LOG.DeleteCP_IMPRESS_LOG", oldObj );

	}

	public boolean exists(CpImpressLog vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpImpressLog obj = (CpImpressLog) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_IMPRESS_LOG.CountCP_IMPRESS_LOG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	/**
	 * 
	 * @method comments: 根据服务商ID获取前3个印象
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 上午11:21:04 
	 * @param cpImpressLog
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:21:04 wangchao 创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String, Object>> findByCpId(CpImpressLog cpImpressLog) {
		return queryForList("CP_IMPRESS_LOG.findByCpId", cpImpressLog);
	}
	
}
