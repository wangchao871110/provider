package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpRemarkLog;


/**
 * Created by zhao on 2016/12/12.
 */
@Repository
public class CpRemarkLogDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_REMARK_LOG.CountFindAllCP_REMARK_LOG", null );
        return ret.intValue();
    }

    public CpRemarkLog findByPK(Integer pk) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
        Object ret = queryForObject("CP_REMARK_LOG.FindByPK", _hashmap );
        if(ret !=null)
            return (CpRemarkLog)ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<CpRemarkLog> freeFind(CpRemarkLog obj) {
        return queryForList("CP_REMARK_LOG.FreeFindCP_REMARK_LOG", obj );
    }

    public int countFreeFind(CpRemarkLog obj) {
        Integer ret = (Integer) queryForObject("CP_REMARK_LOG.CountFreeFindCP_REMARK_LOG", obj );
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<CpRemarkLog> freeFind(CpRemarkLog obj, int pageIndex, int pageSize) {
        return getPaginatedList("CP_REMARK_LOG.FreeFindCP_REMARK_LOG", obj, pageIndex, pageSize );
    }

    @SuppressWarnings("unchecked")
    public List<CpRemarkLog> freeFind(CpRemarkLog obj, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof CpRemarkLog){
            _hashmap = ((CpRemarkLog)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return queryForList("CP_REMARK_LOG.FreeFindCP_REMARK_LOGOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<CpRemarkLog> freeFind(CpRemarkLog obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof CpRemarkLog){
            _hashmap = ((CpRemarkLog)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return getPaginatedList("CP_REMARK_LOG.FreeFindCP_REMARK_LOGOrdered", _hashmap, pageIndex, pageSize );
    }

    public int saveAll(ContextInfo cti, Collection<CpRemarkLog> objColl) {
        int i = 0;
        if ( objColl != null ) {
            Iterator<CpRemarkLog> it = objColl.iterator();
            while ( it.hasNext() ) {
                CpRemarkLog oneObj = (CpRemarkLog)it.next();
                i += save(cti, oneObj );
            }
        }
        return i;
    }

    public int save(ContextInfo cti,CpRemarkLog vo) {
        CpRemarkLog obj = (CpRemarkLog) vo;

        if(exists( obj ) ) {
            return update( cti,obj );
        } else {
            return insert(cti, obj );
        }
    }

    public int insert(ContextInfo cti, CpRemarkLog obj) {

        obj.fixup();
        return insert(cti,"CP_REMARK_LOG.InsertCP_REMARK_LOG", obj );
    }

    public int update(ContextInfo cti, CpRemarkLog obj) {

        obj.fixup();
        return update( cti,"CP_REMARK_LOG.UpdateCP_REMARK_LOG", obj );

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, CpRemarkLog obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete( cti,"CP_REMARK_LOG.DeleteCP_REMARK_LOG", obj );

    }



    public int removeObjectTree(ContextInfo cti, CpRemarkLog obj) {

        obj.fixup();
        CpRemarkLog oldObj = ( CpRemarkLog )queryForObject("CP_REMARK_LOG.FindByPK", obj );
        if ( oldObj == null ) {
            return 0;
        }


        return delete(cti, "CP_REMARK_LOG.DeleteCP_REMARK_LOG", oldObj );

    }

    public boolean exists(CpRemarkLog vo) {

        boolean keysFilled = true;
        boolean ret = false;
        CpRemarkLog obj = (CpRemarkLog) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_REMARK_LOG.CountCP_REMARK_LOG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
    }



    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/12/09 18:44:18


    @SuppressWarnings("unchecked")
    public List findSysUser(CpRemarkLog vo, Integer pageIndex, Integer pageSize) {
        return getPaginatedList("CP_REMARK_LOG.findSysUser", vo ,pageIndex,pageSize);
    }

}
