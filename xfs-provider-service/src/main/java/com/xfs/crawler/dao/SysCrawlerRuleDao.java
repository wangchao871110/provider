package com.xfs.crawler.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.crawler.model.SysCrawlerRule;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysCrawlerRuleDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/02/20 09:42:24
 */
@Repository
public class SysCrawlerRuleDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_CRAWLER_RULE.CountFindAllSYS_CRAWLER_RULE", null );
        return ret.intValue();
	}

	public SysCrawlerRule findByPK(SysCrawlerRule obj) {
		Object ret = queryForObject("SYS_CRAWLER_RULE.FindByPK", obj.getId() );
		if(ret !=null)
			return (SysCrawlerRule)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerRule> freeFind(SysCrawlerRule obj) {
		return queryForList("SYS_CRAWLER_RULE.FreeFindSYS_CRAWLER_RULE", obj );
	}

	public int countFreeFind(SysCrawlerRule obj) {
        Integer ret = (Integer) queryForObject("SYS_CRAWLER_RULE.CountFreeFindSYS_CRAWLER_RULE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerRule> freeFind(SysCrawlerRule obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_CRAWLER_RULE.FreeFindSYS_CRAWLER_RULE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerRule> freeFind(SysCrawlerRule obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysCrawlerRule){
	    	_hashmap = ((SysCrawlerRule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_CRAWLER_RULE.FreeFindSYS_CRAWLER_RULEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCrawlerRule> freeFind(SysCrawlerRule obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SysCrawlerRule){
	    	_hashmap = ((SysCrawlerRule)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_CRAWLER_RULE.FreeFindSYS_CRAWLER_RULEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<SysCrawlerRule> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SysCrawlerRule> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysCrawlerRule oneObj = (SysCrawlerRule)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,SysCrawlerRule vo) {
        SysCrawlerRule obj = (SysCrawlerRule) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti,SysCrawlerRule obj) {

		obj.fixup();
        return insert(cti,"SYS_CRAWLER_RULE.InsertSYS_CRAWLER_RULE", obj );
	}

	public int update(ContextInfo cti,SysCrawlerRule obj) {

		obj.fixup();
		return update(cti, "SYS_CRAWLER_RULE.UpdateSYS_CRAWLER_RULE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,SysCrawlerRule obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SYS_CRAWLER_RULE.DeleteSYS_CRAWLER_RULE", obj );

	}

	public int removeObjectTree(ContextInfo cti,SysCrawlerRule obj) {

        obj.fixup();
        SysCrawlerRule oldObj = ( SysCrawlerRule )queryForObject("SYS_CRAWLER_RULE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SYS_CRAWLER_RULE.DeleteSYS_CRAWLER_RULE", oldObj );

	}

	public boolean exists(SysCrawlerRule vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysCrawlerRule obj = (SysCrawlerRule) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_CRAWLER_RULE.CountSYS_CRAWLER_RULE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/20 09:42:24
	

	public int updateDr(ContextInfo cti,SysCrawlerRule vo) {
		return update(cti, "SYS_CRAWLER_RULE.UpdateSYS_CRAWLER_RULE", vo );
	}
	
}
