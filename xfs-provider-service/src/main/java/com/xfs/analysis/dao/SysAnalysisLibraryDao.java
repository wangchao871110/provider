package com.xfs.analysis.dao;
import java.util.*;

import com.xfs.analysis.dto.SysAnalysisLibrary;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysAnalysisLibraryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:29
 */
@Repository
public class SysAnalysisLibraryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_LIBRARY.CountFindAllSYS_ANALYSIS_LIBRARY", null );
        return ret.intValue();
	}

	public SysAnalysisLibrary findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("SYS_ANALYSIS_LIBRARY.FindByPK", _hashmap );
		if(ret !=null)
			return (SysAnalysisLibrary)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisLibrary> freeFind(SysAnalysisLibrary obj) {
		return queryForList("SYS_ANALYSIS_LIBRARY.FreeFindSYS_ANALYSIS_LIBRARY", obj );
	}

	public int countFreeFind(SysAnalysisLibrary obj) {
        Integer ret = (Integer) queryForObject("SYS_ANALYSIS_LIBRARY.CountFreeFindSYS_ANALYSIS_LIBRARY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisLibrary> freeFind(SysAnalysisLibrary obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ANALYSIS_LIBRARY.FreeFindSYS_ANALYSIS_LIBRARY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisLibrary> freeFind(SysAnalysisLibrary obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisLibrary){
	    	_hashmap = ((SysAnalysisLibrary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ANALYSIS_LIBRARY.FreeFindSYS_ANALYSIS_LIBRARYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysAnalysisLibrary> freeFind(SysAnalysisLibrary obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysAnalysisLibrary){
	    	_hashmap = ((SysAnalysisLibrary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ANALYSIS_LIBRARY.FreeFindSYS_ANALYSIS_LIBRARYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SysAnalysisLibrary> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysAnalysisLibrary> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysAnalysisLibrary oneObj = (SysAnalysisLibrary)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SysAnalysisLibrary vo) {
        SysAnalysisLibrary obj = (SysAnalysisLibrary) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, SysAnalysisLibrary obj) {

		obj.fixup();
        return insert(cti,"SYS_ANALYSIS_LIBRARY.InsertSYS_ANALYSIS_LIBRARY", obj );
	}

	public int update(ContextInfo cti,SysAnalysisLibrary obj) {

		obj.fixup();
		return update(cti, "SYS_ANALYSIS_LIBRARY.UpdateSYS_ANALYSIS_LIBRARY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SysAnalysisLibrary obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_ANALYSIS_LIBRARY.DeleteSYS_ANALYSIS_LIBRARY", obj );

	}

	public int removeObjectTree(ContextInfo cti,SysAnalysisLibrary obj) {

        obj.fixup();
        SysAnalysisLibrary oldObj = ( SysAnalysisLibrary )queryForObject("SYS_ANALYSIS_LIBRARY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_ANALYSIS_LIBRARY.DeleteSYS_ANALYSIS_LIBRARY", oldObj );

	}

	public boolean exists(SysAnalysisLibrary vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysAnalysisLibrary obj = (SysAnalysisLibrary) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ANALYSIS_LIBRARY.CountSYS_ANALYSIS_LIBRARY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:29

	/**
	 *  获取当前解析业务下的所有操作对应的字段信息列表
	 *  @param   analysisType
	 *	@return 			: java.util.Map<java.lang.String,java.util.List<com.xfs.analysis.model.AnalysisTitle>>
	 *  @createDate  	: 2017-03-22 11:25
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-22 11:25
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findAllBusTypeLibrary(String analysisType){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("analysisType", analysisType);
		return queryForList("SYS_ANALYSIS_LIBRARY.FIND_ALL_BUSTYPE_LIBRARY", _hashmap );
	}
}
