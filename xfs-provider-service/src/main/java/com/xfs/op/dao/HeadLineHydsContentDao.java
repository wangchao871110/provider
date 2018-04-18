package com.xfs.op.dao;
import java.util.*;

import com.xfs.op.model.HeadLineHydsContent;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;

/**
 * HeadLineHydsContentDao
 * @author:yangfw@xinfushe.com
 * @date:2017/05/02 16:19:11
 */
@Repository
public class HeadLineHydsContentDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("HEAD_LINE_HYDS_CONTENT.CountFindAllHEAD_LINE_HYDS_CONTENT", null );
        return ret.intValue();
	}

	public HeadLineHydsContent findByPK(HeadLineHydsContent obj) {
		Object ret = queryForObject("HEAD_LINE_HYDS_CONTENT.FindByPK", obj );
		if(ret !=null)
			return (HeadLineHydsContent)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<HeadLineHydsContent> freeFind(HeadLineHydsContent obj) {
		return queryForList("HEAD_LINE_HYDS_CONTENT.FreeFindHEAD_LINE_HYDS_CONTENT", obj );
	}

	public int countFreeFind(HeadLineHydsContent obj) {
        Integer ret = (Integer) queryForObject("HEAD_LINE_HYDS_CONTENT.CountFreeFindHEAD_LINE_HYDS_CONTENT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<HeadLineHydsContent> freeFind(HeadLineHydsContent obj, int pageIndex, int pageSize) {
		return getPaginatedList("HEAD_LINE_HYDS_CONTENT.FreeFindHEAD_LINE_HYDS_CONTENT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<HeadLineHydsContent> freeFind(HeadLineHydsContent obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof HeadLineHydsContent){
	    	_hashmap = ((HeadLineHydsContent)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("HEAD_LINE_HYDS_CONTENT.FreeFindHEAD_LINE_HYDS_CONTENTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<HeadLineHydsContent> freeFind(HeadLineHydsContent obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof HeadLineHydsContent){
	    	_hashmap = ((HeadLineHydsContent)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("HEAD_LINE_HYDS_CONTENT.FreeFindHEAD_LINE_HYDS_CONTENTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<HeadLineHydsContent> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<HeadLineHydsContent> it = objColl.iterator();
            while ( it.hasNext() ) {
            	HeadLineHydsContent oneObj = (HeadLineHydsContent)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,HeadLineHydsContent vo) {
        HeadLineHydsContent obj = (HeadLineHydsContent) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,HeadLineHydsContent obj) {

		obj.fixup();
        return insert(cti,"HEAD_LINE_HYDS_CONTENT.InsertHEAD_LINE_HYDS_CONTENT", obj );
	}

	public int update(ContextInfo cti,HeadLineHydsContent obj) {

		obj.fixup();
		return update(cti, "HEAD_LINE_HYDS_CONTENT.UpdateHEAD_LINE_HYDS_CONTENT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,HeadLineHydsContent obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "HEAD_LINE_HYDS_CONTENT.DeleteHEAD_LINE_HYDS_CONTENT", obj );

	}

	public int removeObjectTree(ContextInfo cti,HeadLineHydsContent obj) {

        obj.fixup();
        HeadLineHydsContent oldObj = ( HeadLineHydsContent )queryForObject("HEAD_LINE_HYDS_CONTENT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"HEAD_LINE_HYDS_CONTENT.DeleteHEAD_LINE_HYDS_CONTENT", oldObj );

	}

	public boolean exists(HeadLineHydsContent vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        HeadLineHydsContent obj = (HeadLineHydsContent) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("HEAD_LINE_HYDS_CONTENT.CountHEAD_LINE_HYDS_CONTENT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/05/02 16:19:11




    /**
     * count to 分页
     * @param obj
     * @return
     */
    public int countBySearchQueryMap(HeadLineHydsContent obj) {
        Integer ret = (Integer) queryForObject("HEAD_LINE_HYDS_CONTENT.countBySearchQueryMap", obj );
        return ret.intValue();
    }


    /**
     * 分页查询
     * @param obj
     * @param offset
     * @param pagesize
     * @return
     */
    public List<Map<String,Object>> findBySearchQueryMap(HeadLineHydsContent obj,Integer offset,Integer pagesize){
        return getPaginatedList("HEAD_LINE_HYDS_CONTENT.findBySearchQueryMap",obj,offset,pagesize);
    }



}
