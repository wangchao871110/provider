package com.xfs.user.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.user.model.SysFunctionData;

/**
 * SysFunctionDataDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/23 10:46:32
 */
@Repository
public class SysFunctionDataDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_DATA.CountFindAllSYS_FUNCTION_DATA", null );
        return ret.intValue();
    }

    public SysFunctionData findByPK(SysFunctionData obj) {
        Object ret = queryForObject("SYS_FUNCTION_DATA.FindByPK", obj );
        if(ret !=null)
            return (SysFunctionData)ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<SysFunctionData> freeFind(SysFunctionData obj) {
        return queryForList("SYS_FUNCTION_DATA.FreeFindSYS_FUNCTION_DATA", obj );
    }

    public int countFreeFind(SysFunctionData obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_DATA.CountFreeFindSYS_FUNCTION_DATA", obj );
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SysFunctionData> freeFind(SysFunctionData obj, int pageIndex, int pageSize) {
        return getPaginatedList("SYS_FUNCTION_DATA.FreeFindSYS_FUNCTION_DATA", obj, pageIndex, pageSize );
    }

    @SuppressWarnings("unchecked")
    public List<SysFunctionData> freeFind(SysFunctionData obj, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof SysFunctionData){
            _hashmap = ((SysFunctionData)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return queryForList("SYS_FUNCTION_DATA.FreeFindSYS_FUNCTION_DATAOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<SysFunctionData> freeFind(SysFunctionData obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof SysFunctionData){
            _hashmap = ((SysFunctionData)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return getPaginatedList("SYS_FUNCTION_DATA.FreeFindSYS_FUNCTION_DATAOrdered", _hashmap, pageIndex, pageSize );
    }

    public int saveAll(ContextInfo cti, Collection<SysFunctionData> objColl) {
        int i = 0;
        if ( objColl != null ) {
            Iterator<SysFunctionData> it = objColl.iterator();
            while ( it.hasNext() ) {
                SysFunctionData oneObj = (SysFunctionData)it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, SysFunctionData vo) {
        SysFunctionData obj = (SysFunctionData) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, SysFunctionData obj) {

        obj.fixup();
        return insert(cti, "SYS_FUNCTION_DATA.InsertSYS_FUNCTION_DATA", obj );
    }

    public int update(ContextInfo cti, SysFunctionData obj) {

        obj.fixup();
        return update(cti, "SYS_FUNCTION_DATA.UpdateSYS_FUNCTION_DATA", obj );

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, SysFunctionData obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SYS_FUNCTION_DATA.DeleteSYS_FUNCTION_DATA", obj );

    }

    public int removeObjectTree(ContextInfo cti, SysFunctionData obj) {

        obj.fixup();
        SysFunctionData oldObj = ( SysFunctionData )queryForObject("SYS_FUNCTION_DATA.FindByPK", obj );
        if ( oldObj == null ) {
            return 0;
        }


        return delete(cti, "SYS_FUNCTION_DATA.DeleteSYS_FUNCTION_DATA", oldObj );

    }

    public boolean exists(SysFunctionData vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SysFunctionData obj = (SysFunctionData) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION_DATA.CountSYS_FUNCTION_DATA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/08/23 10:46:32

    public String getAuthorityByUserIdAndType (SysFunctionData obj) {
        String ret = (String) queryForObject("SYS_FUNCTION_DATA.getAuthorityByUserIdAndType", obj );
        return ret;
    }

	public int deleteByUserId(ContextInfo cti, Integer userId) {
		SysFunctionData obj = new SysFunctionData();
		obj.setUserId(userId);
		return delete(cti, "SYS_FUNCTION_DATA.deleteByUserId", obj);
	}

}
