package com.xfs.corp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.model.CmCorpTraceRecord;

/**
 * 
* @ClassName: CmCorpTraceRecordDao 
* @Description: 跟踪记录
* @author duanax@xinfushe.com
* @date 2016年11月29日 下午4:16:55 
*
 */
@Repository
public class CmCorpTraceRecordDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_CORP_TRACE_RECORD.CountFindAllCM_CORP_TRACE_RECORD", null );
        return ret.intValue();
	}

	public CmCorpTraceRecord findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("CM_CORP_TRACE_RECORD.FindByPK", _hashmap );
		if(ret !=null)
			return (CmCorpTraceRecord)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpTraceRecord> freeFind(CmCorpTraceRecord obj) {
		return queryForList("CM_CORP_TRACE_RECORD.FreeFindCM_CORP_TRACE_RECORD", obj );
	}

	public int countFreeFind(CmCorpTraceRecord obj) {
        Integer ret = (Integer) queryForObject("CM_CORP_TRACE_RECORD.CountFreeFindCM_CORP_TRACE_RECORD", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpTraceRecord> freeFind(CmCorpTraceRecord obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_CORP_TRACE_RECORD.FreeFindCM_CORP_TRACE_RECORD", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpTraceRecord> freeFind(CmCorpTraceRecord obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmCorpTraceRecord){
	    	_hashmap = ((CmCorpTraceRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_CORP_TRACE_RECORD.FreeFindCM_CORP_TRACE_RECORDOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmCorpTraceRecord> freeFind(CmCorpTraceRecord obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmCorpTraceRecord){
	    	_hashmap = ((CmCorpTraceRecord)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_CORP_TRACE_RECORD.FreeFindCM_CORP_TRACE_RECORDOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<CmCorpTraceRecord> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmCorpTraceRecord> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmCorpTraceRecord oneObj = (CmCorpTraceRecord)it.next();
				i += save( cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti ,CmCorpTraceRecord vo) {
        CmCorpTraceRecord obj = (CmCorpTraceRecord) vo;

        if(exists( obj ) ) {
            return update( cti , obj );
        } else {
            return insert( cti , obj );
        }
	}

	public int insert(ContextInfo cti ,CmCorpTraceRecord obj) {

		obj.fixup();
        return insert(cti ,"CM_CORP_TRACE_RECORD.InsertCM_CORP_TRACE_RECORD", obj );
	}

	public int update(ContextInfo cti,CmCorpTraceRecord obj) {

		obj.fixup();
		return update( cti ,"CM_CORP_TRACE_RECORD.UpdateCM_CORP_TRACE_RECORD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,CmCorpTraceRecord obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete( cti,"CM_CORP_TRACE_RECORD.DeleteCM_CORP_TRACE_RECORD", obj );

	}

	public int removeObjectTree(ContextInfo cti,CmCorpTraceRecord obj) {

        obj.fixup();
        CmCorpTraceRecord oldObj = ( CmCorpTraceRecord )queryForObject("CM_CORP_TRACE_RECORD.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"CM_CORP_TRACE_RECORD.DeleteCM_CORP_TRACE_RECORD", oldObj );

	}

	public boolean exists(CmCorpTraceRecord vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmCorpTraceRecord obj = (CmCorpTraceRecord) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_CORP_TRACE_RECORD.CountCM_CORP_TRACE_RECORD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/25 16:51:17
	
	/**
	 * 
	* @Title: findNewInfo 
	* @Description: 查找每个企业最后一条跟踪信息
	* @param @param cti
	* @param @param vo
	* @param @return    设定文件 
	* @return CmCorpTraceRecord    返回类型 
	* @throws
	 */
	public CmCorpTraceRecord findNewInfo(CmCorpTraceRecord vo){
		return (CmCorpTraceRecord) queryForObject("CM_CORP_TRACE_RECORD.FINDNewRecordInfo", vo);
	}
	
}
