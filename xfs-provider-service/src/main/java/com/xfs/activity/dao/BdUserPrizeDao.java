package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.BdUserPrize;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdUserPrizeDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/10/10 17:44:33
 */
@Repository
public class BdUserPrizeDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_USER_PRIZE.CountFindAllBD_USER_PRIZE", null);
        return ret.intValue();
    }

    public BdUserPrize findByPK(Integer pk) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap.put("pk", pk);
        Object ret = queryForObject("BD_USER_PRIZE.FindByPK", _hashmap);
        if (ret != null)
            return (BdUserPrize) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<BdUserPrize> freeFind(BdUserPrize obj) {
        return queryForList("BD_USER_PRIZE.FreeFindBD_USER_PRIZE", obj);
    }

    public int countFreeFind(BdUserPrize obj) {
        Integer ret = (Integer) queryForObject("BD_USER_PRIZE.CountFreeFindBD_USER_PRIZE", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<BdUserPrize> freeFind(BdUserPrize obj, int pageIndex, int pageSize) {
        return getPaginatedList("BD_USER_PRIZE.FreeFindBD_USER_PRIZE", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<BdUserPrize> freeFind(BdUserPrize obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BdUserPrize) {
            _hashmap = ((BdUserPrize) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("BD_USER_PRIZE.FreeFindBD_USER_PRIZEOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<BdUserPrize> freeFind(BdUserPrize obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BdUserPrize) {
            _hashmap = ((BdUserPrize) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("BD_USER_PRIZE.FreeFindBD_USER_PRIZEOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<BdUserPrize> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<BdUserPrize> it = objColl.iterator();
            while (it.hasNext()) {
                BdUserPrize oneObj = (BdUserPrize) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, BdUserPrize vo) {
        BdUserPrize obj = (BdUserPrize) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, BdUserPrize obj) {

        obj.fixup();
        return insert(cti, "BD_USER_PRIZE.InsertBD_USER_PRIZE", obj);
    }

    public int update(ContextInfo cti, BdUserPrize obj) {

        obj.fixup();
        return update(cti, "BD_USER_PRIZE.UpdateBD_USER_PRIZE", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, BdUserPrize obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "BD_USER_PRIZE.DeleteBD_USER_PRIZE", obj);

    }

    public int removeObjectTree(ContextInfo cti, BdUserPrize obj) {

        obj.fixup();
        BdUserPrize oldObj = (BdUserPrize) queryForObject("BD_USER_PRIZE.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "BD_USER_PRIZE.DeleteBD_USER_PRIZE", oldObj);

    }

    public boolean exists(BdUserPrize vo) {

        boolean keysFilled = true;
        boolean ret = false;
        BdUserPrize obj = (BdUserPrize) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("BD_USER_PRIZE.CountBD_USER_PRIZE", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/10/10 17:44:33

    /**
     * 获取用户指定类型的奖品
     *
     * @param orgId    组织编码
     * @param prizeType 奖品类型
     * @return 奖品列表
     */
    public List<Map>getUserPrizeByType(String userType, Integer orgId, String prizeType) {
        Map<String, Object> map = new HashMap<>();
        map.put("userType", userType);
        map.put("orgId", orgId);
        map.put("prizeType", prizeType);
        return queryForList("BD_USER_PRIZE.queryUserPriceByType", map);
    }

    public Map findUserPrizeById(String userType, Integer orgId, String prizeType, Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("userType", userType);
        map.put("orgId", orgId);
        map.put("prizeType", prizeType);
        map.put("id", id);
        return (Map) queryForObject("BD_USER_PRIZE.queryUserPriceByType", map);
    }

    public int updateUserPriceStateByType(ContextInfo cti, Integer id, String prizeType, Integer orgId) {
        Map<String, Object> map = new HashMap<>();
        map.put("orgId", orgId);
        map.put("prizeType", prizeType);
        map.put("id", id);
        return update(cti, "BD_USER_PRIZE.updateUserPriceStateByType", map);
    }


    public List<Map<String, Object>> queryUserPrizeList(BdUserPrize vo) {
        return queryForList("BD_USER_PRIZE.queryUserPrizeList", vo);
    }

    public Map<String, Object> queryUserPrize(BdUserPrize vo) {
        return (Map) queryForObject("BD_USER_PRIZE.queryUserPrize", vo);
    }
    
    public List<Map<String, Object>> queryUserPriceByBUP(BdUserPrize vo) {
        return queryForList("BD_USER_PRIZE.queryUserPriceByBUP", vo);
    }

}
