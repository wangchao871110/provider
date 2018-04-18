package com.xfs.crawler.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.crawler.model.SysCrawlerReport;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysCrawlerReportDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/02/20 09:42:28
 */
@Repository
public class SysCrawlerReportDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_CRAWLER_REPORT.CountFindAllSYS_CRAWLER_REPORT", null );
        return ret.intValue();
	}

	public SysCrawlerReport findByPK(SysCrawlerReport obj) {
		Object ret = queryForObject("SYS_CRAWLER_REPORT.FindByPK", obj );
		if(ret !=null)
			return (SysCrawlerReport)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerReport> freeFind(SysCrawlerReport obj) {
		return queryForList("SYS_CRAWLER_REPORT.FreeFindSYS_CRAWLER_REPORT", obj );
	}

	public int countFreeFind(SysCrawlerReport obj) {
        Integer ret = (Integer) queryForObject("SYS_CRAWLER_REPORT.CountFreeFindSYS_CRAWLER_REPORT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerReport> freeFind(SysCrawlerReport obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_CRAWLER_REPORT.FreeFindSYS_CRAWLER_REPORT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerReport> freeFind(SysCrawlerReport obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysCrawlerReport){
	    	_hashmap = ((SysCrawlerReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_CRAWLER_REPORT.FreeFindSYS_CRAWLER_REPORTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerReport> freeFind(SysCrawlerReport obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysCrawlerReport){
	    	_hashmap = ((SysCrawlerReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_CRAWLER_REPORT.FreeFindSYS_CRAWLER_REPORTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SysCrawlerReport> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysCrawlerReport> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysCrawlerReport oneObj = (SysCrawlerReport)it.next();
				i += save( cti,oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SysCrawlerReport vo) {
        SysCrawlerReport obj = (SysCrawlerReport) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti,obj );
        }
	}

	public int insert(ContextInfo cti,SysCrawlerReport obj) {

		obj.fixup();
        return insert(cti,"SYS_CRAWLER_REPORT.InsertSYS_CRAWLER_REPORT", obj );
	}

	public int update(ContextInfo cti,SysCrawlerReport obj) {

		obj.fixup();
		return update(cti, "SYS_CRAWLER_REPORT.UpdateSYS_CRAWLER_REPORT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SysCrawlerReport obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_CRAWLER_REPORT.DeleteSYS_CRAWLER_REPORT", obj );

	}

	public int removeObjectTree(ContextInfo cti,SysCrawlerReport obj) {

        obj.fixup();
        SysCrawlerReport oldObj = ( SysCrawlerReport )queryForObject("SYS_CRAWLER_REPORT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_CRAWLER_REPORT.DeleteSYS_CRAWLER_REPORT", oldObj );

	}

	public boolean exists(SysCrawlerReport vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysCrawlerReport obj = (SysCrawlerReport) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_CRAWLER_REPORT.CountSYS_CRAWLER_REPORT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:28
	
	
	
}
