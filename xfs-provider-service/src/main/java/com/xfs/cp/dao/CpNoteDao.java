package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpNote;

/**
 * CpNoteDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:18
 */
@Repository
public class CpNoteDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_NOTE.CountFindAllCP_NOTE", null );
        return ret.intValue();
	}

	public CpNote findByPK(CpNote obj) {
		Object ret = queryForObject("CP_NOTE.FindByPK", obj );
		if(ret !=null)
			return (CpNote)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpNote> freeFind(CpNote obj) {
		return queryForList("CP_NOTE.FreeFindCP_NOTE", obj );
	}

	public int countFreeFind(CpNote obj) {
        Integer ret = (Integer) queryForObject("CP_NOTE.CountFreeFindCP_NOTE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpNote> freeFind(CpNote obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_NOTE.FreeFindCP_NOTE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpNote> freeFind(CpNote obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpNote){
	    	_hashmap = ((CpNote)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_NOTE.FreeFindCP_NOTEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpNote> freeFind(CpNote obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpNote){
	    	_hashmap = ((CpNote)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_NOTE.FreeFindCP_NOTEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpNote> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpNote> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpNote oneObj = (CpNote)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpNote vo) {
        CpNote obj = (CpNote) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpNote obj) {

		obj.fixup();
        return insert(cti, "CP_NOTE.InsertCP_NOTE", obj );
	}

	public int update(ContextInfo cti, CpNote obj) {

		obj.fixup();
		return update(cti, "CP_NOTE.UpdateCP_NOTE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpNote obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_NOTE.DeleteCP_NOTE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpNote obj) {

        obj.fixup();
        CpNote oldObj = ( CpNote )queryForObject("CP_NOTE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_NOTE.DeleteCP_NOTE", oldObj );

	}

	public boolean exists(CpNote vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpNote obj = (CpNote) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_NOTE.CountCP_NOTE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:18
	/**
	 * 
	 * @method comments: 获取留言和纠错个数
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月25日 下午3:33:59 
	 * @param cpNote
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月25日 下午3:33:59 baoyu 创建
	 *
	 */
	public Integer queryNotePageCount(CpNote cpNote) {
		return (Integer) queryForObject("CP_NOTE.queryNotePageCount", cpNote);
	}

	/**
	 * 
	 * @method comments: 获取留言和纠错
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月25日 下午3:34:56 
	 * @param cpNote
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月25日 下午3:34:56 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> queryNotePage(CpNote cpNote, Integer pageIndex, Integer pageSize) {
		return getPaginatedList("CP_NOTE.queryNotePage",cpNote,pageIndex,pageSize);
	}
	
	
}
