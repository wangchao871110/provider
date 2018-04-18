package com.xfs.insurance.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiScheme;

/**
 * BsCiSchemeDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 14:04:27
 */
@Repository
public class BsCiSchemeDao extends BaseSqlMapDao {

    public int countFindAll() {

        Integer ret = (Integer) queryForObject("BS_CI_SCHEME.CountFindAllBS_CI_SCHEME", null);
        return ret.intValue();
    }

    public BsCiScheme findByPK(BsCiScheme obj) {

        Object ret = queryForObject("BS_CI_SCHEME.FindByPK", obj);
        if (ret != null)
            return (BsCiScheme) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<BsCiScheme> freeFind(BsCiScheme obj) {

        return queryForList("BS_CI_SCHEME.FreeFindBS_CI_SCHEME", obj);
    }

    public int countFreeFind(BsCiScheme obj) {

        Integer ret = (Integer) queryForObject("BS_CI_SCHEME.CountFreeFindBS_CI_SCHEME", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<BsCiScheme> freeFind(BsCiScheme obj, int pageIndex, int pageSize) {

        return getPaginatedList("BS_CI_SCHEME.FreeFindBS_CI_SCHEME", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiScheme> freeFind(BsCiScheme obj, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiScheme) {
            _hashmap = ((BsCiScheme) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("BS_CI_SCHEME.FreeFindBS_CI_SCHEMEOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiScheme> freeFind(BsCiScheme obj, int pageIndex, int pageSize, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiScheme) {
            _hashmap = ((BsCiScheme) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("BS_CI_SCHEME.FreeFindBS_CI_SCHEMEOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<BsCiScheme> objColl) {

        int i = 0;
        if (objColl != null) {
            Iterator<BsCiScheme> it = objColl.iterator();
            while (it.hasNext()) {
                BsCiScheme oneObj = (BsCiScheme) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, BsCiScheme vo) {

        BsCiScheme obj = (BsCiScheme) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, BsCiScheme obj) {

        obj.fixup();
        return insert(cti, "BS_CI_SCHEME.InsertBS_CI_SCHEME", obj);
    }

    public int update(ContextInfo cti, BsCiScheme obj) {

        obj.fixup();
        return update(cti, "BS_CI_SCHEME.UpdateBS_CI_SCHEME", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, BsCiScheme obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "BS_CI_SCHEME.DeleteBS_CI_SCHEME", obj);

    }

    public int removeObjectTree(ContextInfo cti, BsCiScheme obj) {

        obj.fixup();
        BsCiScheme oldObj = (BsCiScheme) queryForObject("BS_CI_SCHEME.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }

        return delete(cti, "BS_CI_SCHEME.DeleteBS_CI_SCHEME", oldObj);

    }

    public boolean exists(BsCiScheme vo) {

        boolean keysFilled = true;
        boolean ret = false;
        BsCiScheme obj = (BsCiScheme) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("BS_CI_SCHEME.CountBS_CI_SCHEME", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/29 14:04:27

    /**
     * 删除保障信息
     *
     * @param itemId 保险编码
     * @return 删除条数
     */
    public int deleteCiSchemeByItemId(ContextInfo cti, Integer itemId) {

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("mallItemId", itemId);
        return delete(cti, "BS_CI_SCHEME.DeleteBS_CI_SCHEME_BY_ITEM", obj);
    }
    public List<CiScheme> findBsCiSchemeByMallItemIds(List<Integer> mallItemIds) {

        return queryForList("BS_CI_SCHEME.findByMallItemIds", mallItemIds);
    }
}
