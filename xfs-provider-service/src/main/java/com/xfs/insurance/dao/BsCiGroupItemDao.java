package com.xfs.insurance.dao;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiGroup;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.model.BsCiGroup;
import com.xfs.insurance.model.BsCiGroupItem;

/**
 * BsCiGroupItemDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 18:33:04
 */
@Repository
public class BsCiGroupItemDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CI_GROUP_ITEM.CountFindAllBS_CI_GROUP_ITEM", null);
        return ret.intValue();
    }

    public BsCiGroupItem findByPK(BsCiGroupItem obj) {
        Object ret = queryForObject("BS_CI_GROUP_ITEM.FindByPK", obj);
        if (ret != null)
            return (BsCiGroupItem) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<BsCiGroupItem> freeFind(BsCiGroupItem obj) {
        return queryForList("BS_CI_GROUP_ITEM.FreeFindBS_CI_GROUP_ITEM", obj);
    }

    public int countFreeFind(BsCiGroupItem obj) {
        Integer ret = (Integer) queryForObject("BS_CI_GROUP_ITEM.CountFreeFindBS_CI_GROUP_ITEM", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<BsCiGroupItem> freeFind(BsCiGroupItem obj, int pageIndex, int pageSize) {
        return getPaginatedList("BS_CI_GROUP_ITEM.FreeFindBS_CI_GROUP_ITEM", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiGroupItem> freeFind(BsCiGroupItem obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiGroupItem) {
            _hashmap = ((BsCiGroupItem) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("BS_CI_GROUP_ITEM.FreeFindBS_CI_GROUP_ITEMOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiGroupItem> freeFind(BsCiGroupItem obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiGroupItem) {
            _hashmap = ((BsCiGroupItem) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("BS_CI_GROUP_ITEM.FreeFindBS_CI_GROUP_ITEMOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<BsCiGroupItem> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<BsCiGroupItem> it = objColl.iterator();
            while (it.hasNext()) {
                BsCiGroupItem oneObj = (BsCiGroupItem) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, BsCiGroupItem vo) {
        BsCiGroupItem obj = (BsCiGroupItem) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, BsCiGroupItem obj) {

        obj.fixup();
        return insert(cti, "BS_CI_GROUP_ITEM.InsertBS_CI_GROUP_ITEM", obj);
    }

    public int update(ContextInfo cti, BsCiGroupItem obj) {

        obj.fixup();
        return update(cti, "BS_CI_GROUP_ITEM.UpdateBS_CI_GROUP_ITEM", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, BsCiGroupItem obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "BS_CI_GROUP_ITEM.DeleteBS_CI_GROUP_ITEM", obj);

    }

    public int removeObjectTree(ContextInfo cti, BsCiGroupItem obj) {

        obj.fixup();
        BsCiGroupItem oldObj = (BsCiGroupItem) queryForObject("BS_CI_GROUP_ITEM.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "BS_CI_GROUP_ITEM.DeleteBS_CI_GROUP_ITEM", oldObj);

    }

    public boolean exists(BsCiGroupItem vo) {

        boolean keysFilled = true;
        boolean ret = false;
        BsCiGroupItem obj = (BsCiGroupItem) vo;

        keysFilled = keysFilled && (null != obj.getGroupItemId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("BS_CI_GROUP_ITEM.CountBS_CI_GROUP_ITEM", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/06 18:33:04

    /**
     * 查询组合险子条目心胸
     *
     * @param group 组合险信息
     * @return 查询结果
     */
    public List<CiProduct> findGroupItemsDetail(BsCiGroup group) {

        return queryForList("BS_CI_GROUP_ITEM.findGroupItemDetail", group);
    }

    /**
     * 删除组合险信息
     *
     * @param group 组合险信息
     * @return 删除条数
     */
    public int deleteAllGroupItems(ContextInfo cti, BsCiGroup group) {

        return delete(cti, "BS_CI_GROUP_ITEM.delGroupItems", group);
    }

    /**
     * 查询组合险条目详情
     *
     * @param ciGroup 查询条件
     * @return 条目列表
     */
    public List<CiGroup> findAllCIGroupWithSp(CiGroup ciGroup) {
        return queryForList("BS_CI_GROUP.findAllCIGroupWithSp", ciGroup);
    }

    /**
     * 查询组合险子条目心胸
     *
     * @return 查询结果
     */
    public List<CiProduct> findAllGroupItemsDetail(List<Integer> prodIds) {

        return queryForList("BS_CI_GROUP_ITEM.findAllGroupItemDetail", prodIds);
    }


}
