package com.xfs.insurance.dao;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiProduct;

/**
 * BsCiProductDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 14:04:27
 */
@Repository
public class BsCiProductDao extends BaseSqlMapDao {

    public int countFindAll() {

        Integer ret = (Integer) queryForObject("BS_CI_PRODUCT.CountFindAllBS_CI_PRODUCT", null);
        return ret.intValue();
    }

    public BsCiProduct findByPK(BsCiProduct obj) {

        Object ret = queryForObject("BS_CI_PRODUCT.FindByPK", obj);
        if (ret != null)
            return (BsCiProduct) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<BsCiProduct> freeFind(BsCiProduct obj) {

        return queryForList("BS_CI_PRODUCT.FreeFindBS_CI_PRODUCT", obj);
    }

    public int countFreeFind(BsCiProduct obj) {

        Integer ret = (Integer) queryForObject("BS_CI_PRODUCT.CountFreeFindBS_CI_PRODUCT", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<BsCiProduct> freeFind(BsCiProduct obj, int pageIndex, int pageSize) {

        return getPaginatedList("BS_CI_PRODUCT.FreeFindBS_CI_PRODUCT", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiProduct> freeFind(BsCiProduct obj, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiProduct) {
            _hashmap = ((BsCiProduct) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("BS_CI_PRODUCT.FreeFindBS_CI_PRODUCTOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<BsCiProduct> freeFind(BsCiProduct obj, int pageIndex, int pageSize, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof BsCiProduct) {
            _hashmap = ((BsCiProduct) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("BS_CI_PRODUCT.FreeFindBS_CI_PRODUCTOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<BsCiProduct> objColl) {

        int i = 0;
        if (objColl != null) {
            Iterator<BsCiProduct> it = objColl.iterator();
            while (it.hasNext()) {
                BsCiProduct oneObj = (BsCiProduct) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, BsCiProduct vo) {

        BsCiProduct obj = (BsCiProduct) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, BsCiProduct obj) {

        obj.fixup();
        return insert(cti, "BS_CI_PRODUCT.InsertBS_CI_PRODUCT", obj);
    }

    public int update(ContextInfo cti, BsCiProduct obj) {

        obj.fixup();
        return update(cti, "BS_CI_PRODUCT.UpdateBS_CI_PRODUCT", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, BsCiProduct obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "BS_CI_PRODUCT.DeleteBS_CI_PRODUCT", obj);

    }

    public int removeObjectTree(ContextInfo cti, BsCiProduct obj) {

        obj.fixup();
        BsCiProduct oldObj = (BsCiProduct) queryForObject("BS_CI_PRODUCT.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }

        return delete(cti, "BS_CI_PRODUCT.DeleteBS_CI_PRODUCT", oldObj);

    }

    public boolean exists(BsCiProduct vo) {

        boolean keysFilled = true;
        boolean ret = false;
        BsCiProduct obj = (BsCiProduct) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("BS_CI_PRODUCT.CountBS_CI_PRODUCT", obj);
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
     * 查询商保产品
     *
     * @param ciProduct 查询条件
     * @return 结果列表
     */
    public List<CiProduct> findPaginatedCiProd(CiProduct ciProduct, int pageIndex, int pageSize) {

        return getPaginatedList("BS_CI_PRODUCT.findCiProduct", ciProduct, pageIndex, pageSize);
    }

    /**
     * 查询总数
     *
     * @param ciProduct 查询条件
     * @return
     */
    public int countPaginatedCiProd(CiProduct ciProduct) {
        Integer ret = (Integer) queryForObject("BS_CI_PRODUCT.countCiProduct", ciProduct);
        return ret.intValue();
    }

    /**
     * 查询单个商保信息
     *
     * @param itemId 条目
     * @return 商保信息
     */
    public CiProduct findCiProductByitemId(Integer itemId) {

        CiProduct ciProduct = new CiProduct();
        ciProduct.setItemId(itemId);
        Object ret = queryForObject("BS_CI_PRODUCT.findCiProduct", ciProduct);
        if (ret != null)
            return (CiProduct) ret;
        else
            return null;
    }

    /**
     * 查询可用于组合险的商保产品
     *
     * @param ciProduct 查询条件
     * @return 查询结果
     */
    public List<CiProduct> findCiProductForGroup(CiProduct ciProduct) {
        return queryForList("BS_CI_PRODUCT.findCiProductForGroup", ciProduct);
    }
    public List<CiProduct> findCiProd(CiProduct ciProduct){
        return queryForList("BS_CI_PRODUCT.FreeFindCI_PRODUCT", ciProduct);
    }

    public CiProduct findCiProdById(CiProduct ciProduct) {

        Object ret = queryForObject("BS_CI_PRODUCT.FreeFindCI_PRODUCT", ciProduct);
        if (ret != null)
            return (CiProduct) ret;
        else
            return null;
    }

    public CiProduct findCiProdById(Integer itemId) {
        CiProduct ciProduct = new CiProduct();
        ciProduct.setItemId(itemId);
        Object ret = queryForObject("BS_CI_PRODUCT.FreeFindCI_PRODUCT", ciProduct);
        if (ret != null)
            return (CiProduct) ret;
        else
            return null;
    }

    public List<CiScheme> findCiGroupItemDetail(Integer itemId, Integer prodId){
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("prodId", prodId);
         return queryForList("BS_CI_PRODUCT.findCiGroupItemDetail", map);

    }



}
