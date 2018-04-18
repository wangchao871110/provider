package com.xfs.cp.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpServiceHistory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhao on 2017/1/5.
 *
 */
@Repository
public class CpServiceHistoryDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_SERVICE_HISTORY.CountFindAllCP_SERVICE_HISTORY", null );
        return ret.intValue();
    }

    public CpServiceHistory findByPK(Integer pk) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
        Object ret = queryForObject("CP_SERVICE_HISTORY.FindByPK", _hashmap );
        if(ret !=null)
            return (CpServiceHistory)ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<CpServiceHistory> freeFind(CpServiceHistory obj) {
        return queryForList("CP_SERVICE_HISTORY.FreeFindCP_SERVICE_HISTORY", obj );
    }

    public int countFreeFind(CpServiceHistory obj) {
        Integer ret = (Integer) queryForObject("CP_SERVICE_HISTORY.CountFreeFindCP_SERVICE_HISTORY", obj );
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<CpServiceHistory> freeFind(CpServiceHistory obj, int pageIndex, int pageSize) {
        return getPaginatedList("CP_SERVICE_HISTORY.FreeFindCP_SERVICE_HISTORY", obj, pageIndex, pageSize );
    }

    @SuppressWarnings("unchecked")
    public List<CpServiceHistory> freeFind(CpServiceHistory obj, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof CpServiceHistory){
            _hashmap = ((CpServiceHistory)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return queryForList("CP_SERVICE_HISTORY.FreeFindCP_SERVICE_HISTORYOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<CpServiceHistory> freeFind(CpServiceHistory obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        if(obj instanceof CpServiceHistory){
            _hashmap = ((CpServiceHistory)obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName );

        return getPaginatedList("CP_SERVICE_HISTORY.FreeFindCP_SERVICE_HISTORYOrdered", _hashmap, pageIndex, pageSize );
    }

    public int saveAll(ContextInfo cti, Collection<CpServiceHistory> objColl) {
        int i = 0;
        if ( objColl != null ) {
            Iterator<CpServiceHistory> it = objColl.iterator();
            while ( it.hasNext() ) {
                CpServiceHistory oneObj = (CpServiceHistory)it.next();
                i += save(cti, oneObj );
            }
        }
        return i;
    }

    public int save(ContextInfo cti, CpServiceHistory vo) {
        CpServiceHistory obj = (CpServiceHistory) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
    }

    public int insert(ContextInfo cti, CpServiceHistory obj) {

        obj.fixup();
        return insert(cti,"CP_SERVICE_HISTORY.InsertCP_SERVICE_HISTORY", obj );
    }

    public int update(ContextInfo cti, CpServiceHistory obj) {

        obj.fixup();
        return update(cti, "CP_SERVICE_HISTORY.UpdateCP_SERVICE_HISTORY", obj );

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti,CpServiceHistory obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "CP_SERVICE_HISTORY.DeleteCP_SERVICE_HISTORY", obj );

    }

    public int removeObjectTree(ContextInfo cti,CpServiceHistory obj) {

        obj.fixup();
        CpServiceHistory oldObj = ( CpServiceHistory )queryForObject("CP_SERVICE_HISTORY.FindByPK", obj );
        if ( oldObj == null ) {
            return 0;
        }


        return delete(cti, "CP_SERVICE_HISTORY.DeleteCP_SERVICE_HISTORY", oldObj );

    }

    public boolean exists(CpServiceHistory vo) {

        boolean keysFilled = true;
        boolean ret = false;
        CpServiceHistory obj = (CpServiceHistory) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_SERVICE_HISTORY.CountCP_SERVICE_HISTORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 14:51:32



    //备份cp_service表
    public int backupsForCpService(ContextInfo cti, CpServiceHistory obj) {

        obj.fixup();
        return update(cti, "CP_SERVICE_HISTORY.BACKUPS_FOR_CP_SERVICE", obj );

    }

    public List<CpServiceHistory>findYearOrMonth(CpServiceHistory cpServiceHistory){

        return queryForList("CP_SERVICE_HISTORY.findYearOrMonth", cpServiceHistory);
    }

    public Integer findRankingPageCount(CpServiceHistory cpServiceHistory) {
        Integer ret = (Integer) queryForObject("CP_SERVICE_HISTORY.findRankingPageCount", cpServiceHistory );
        return ret.intValue();
    }

    public List<CpServiceHistory> findRankingPage(CpServiceHistory cpServiceHistory, int pageIndex, int pageSize) {
        return getPaginatedList("CP_SERVICE_HISTORY.findRankingPage", cpServiceHistory, pageIndex, pageSize );
    }
}
