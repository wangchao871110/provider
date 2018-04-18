package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysUploadfile;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysUploadfileDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/09 16:13:49
 */
@Repository
public class SysUploadfileDAO extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_UPLOADFILE.CountFindAllSYS_UPLOADFILE", null );
        return ret.intValue();
	}

	public SysUploadfile findByPK(SysUploadfile obj) {
		Object ret = queryForObject("SYS_UPLOADFILE.FindByPK", obj );
		if(ret !=null)
			return (SysUploadfile)ret;
		else 
			return null;
	}
	
	public SysUploadfile findBySaveName(SysUploadfile obj) {
		Object ret = queryForObject("SYS_UPLOADFILE.findBySaveName", obj );
		if(ret !=null)
			return (SysUploadfile)ret;
		else 
			return null;
	}
	
	public List freeFind(SysUploadfile obj) {
		return queryForList("SYS_UPLOADFILE.FreeFindSYS_UPLOADFILE", obj );
	}

	public int countFreeFind(SysUploadfile obj) {
        Integer ret = (Integer) queryForObject("SYS_UPLOADFILE.CountFreeFindSYS_UPLOADFILE", obj );
        return ret.intValue();
	}

	public List freeFind(SysUploadfile obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_UPLOADFILE.FreeFindSYS_UPLOADFILE", obj, pageIndex, pageSize );
	}

	public List freeFind(SysUploadfile obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUploadfile){
	    	_hashmap = ((SysUploadfile)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );

		return queryForList("SYS_UPLOADFILE.FreeFindSYS_UPLOADFILEOrdered", _hashmap);
	}

	public List freeFind(SysUploadfile obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUploadfile){
	    	_hashmap = ((SysUploadfile)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_UPLOADFILE.FreeFindSYS_UPLOADFILEOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SysUploadfile> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUploadfile oneObj = (SysUploadfile)it.next();
				save(cti, oneObj );
            }
        }
	}

	public void save(ContextInfo cti, SysUploadfile vo) {
        SysUploadfile obj = (SysUploadfile) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SysUploadfile obj) {
		if(null != cti && null != cti.getOrgId())
			obj.setOwnerid(cti.getOrgId());
		obj.fixup();
        insert(cti, "SYS_UPLOADFILE.InsertSYS_UPLOADFILE", obj );
	}

	public void update(ContextInfo cti, SysUploadfile obj) {

		obj.fixup();
		update(cti, "SYS_UPLOADFILE.UpdateSYS_UPLOADFILE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SysUploadfile obj) {

        if ( obj == null ) { 
            return 0;
        }
		
		obj.fixup();

        return delete(cti, "SYS_UPLOADFILE.DeleteSYS_UPLOADFILE", obj );

	}

	public void removeObjectTree(ContextInfo cti, SysUploadfile obj) {

        obj.fixup();
        SysUploadfile oldObj = ( SysUploadfile )queryForObject("SYS_UPLOADFILE.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SYS_UPLOADFILE.DeleteSYS_UPLOADFILE", oldObj );

	}

	public boolean exists(SysUploadfile vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUploadfile obj = (SysUploadfile) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_UPLOADFILE.CountSYS_UPLOADFILE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	public SysUploadfile findSysUploadfile(SysUploadfile obj) {
		Object ret = queryForObject("SYS_UPLOADFILE.findSysUploadfile", obj );
		if(ret !=null)
			return (SysUploadfile)ret;
		else
			return null;
	}
	
	public List freeFindByIds(List<String> ids) {
		HashMap _hashmap = new HashMap();
		_hashmap.put("ids", ids );

		return queryForList("SYS_UPLOADFILE.FreeFindSYS_UPLOADFILE_By_IDS", _hashmap);
	}


	public SysUploadfile findSysUploadfileByFilePath(String filePath) {
		HashMap _hashmap = new HashMap();
		_hashmap.put("filepath",filePath);
		Object ret = queryForObject("SYS_UPLOADFILE.findSysUploadfileByFilePath", _hashmap );
		if(ret !=null)
			return (SysUploadfile)ret;
		else
			return null;
	}
}
