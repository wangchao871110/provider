package com.xfs.business.dao;

import com.xfs.business.model.BdBsareatypeProcess;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhao on 2017/3/21.
 */
@Repository
public class BdBsareatypeProcessDao extends BaseSqlMapDao {

    public int save(ContextInfo cti, BdBsareatypeProcess vo) {
        BdBsareatypeProcess obj = (BdBsareatypeProcess) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, BdBsareatypeProcess obj) {

        obj.fixup();
        return insert(cti, "BD_BSAREATYPE_PROCESS.InsertBD_BSAREATYPE_PROCESS", obj );
    }

    public int update(ContextInfo cti, BdBsareatypeProcess obj) {

        obj.fixup();
        return update(cti, "BD_BSAREATYPE_PROCESS.UpdateBD_BSAREATYPE_PROCESS", obj );

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, BdBsareatypeProcess obj) {

        if ( obj == null ) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "BD_BSAREATYPE_PROCESS.DeleteBD_BSAREATYPE_PROCESS", obj );

    }

    public boolean exists(BdBsareatypeProcess vo) {

        boolean keysFilled = true;
        boolean ret = false;
        BdBsareatypeProcess obj = (BdBsareatypeProcess) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BSAREATYPE_PROCESS.CountBD_BSAREATYPE_PROCESS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
    }

    public List<BdBsareatypeProcess> freeFindVo(BdBsareatypeProcess obj, int pageIndex, int pageSize) {
        return getPaginatedList("BD_BSAREATYPE_PROCESS.findBsareatypeprocess", obj, pageIndex, pageSize );
    }

    @SuppressWarnings("unchecked")
    public List<BdBsareatypeProcess> freeFind(BdBsareatypeProcess obj) {
        return queryForList("BD_BSAREATYPE_PROCESS.findBsareatypeprocess", obj );
    }

    public int countFreeFind(BdBsareatypeProcess obj) {
        Integer ret = (Integer) queryForObject("BD_BSAREATYPE_PROCESS.CountFreeFindBD_BSAREATYPE_PROCESS", obj );
        return ret.intValue();
    }

    public List<BdBsareatypeProcess> findAlWithDictitem(BdBsareatypeProcess obj) {
        return queryForList("BD_BSAREATYPE_PROCESS.findAlWithDictitem", obj );
    }

}
