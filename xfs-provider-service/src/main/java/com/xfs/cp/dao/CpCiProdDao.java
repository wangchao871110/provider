package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.dto.CpCiProdDetail;
import com.xfs.insurance.model.CpCiProd;

/**
 * CpCiProdDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 15:45:17
 */
@Repository
public class CpCiProdDao extends BaseSqlMapDao {

    public int countFindAll() {

        Integer ret = (Integer) queryForObject("CP_CI_PROD.CountFindAllCP_CI_PROD", null);
        return ret.intValue();
    }

    public CpCiProd findByPK(CpCiProd obj) {

        Object ret = queryForObject("CP_CI_PROD.FindByPK", obj);
        if (ret != null)
            return (CpCiProd) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<CpCiProd> freeFind(CpCiProd obj) {

        return queryForList("CP_CI_PROD.FreeFindCP_CI_PROD", obj);
    }

    public int countFreeFind(CpCiProd obj) {

        Integer ret = (Integer) queryForObject("CP_CI_PROD.CountFreeFindCP_CI_PROD", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<CpCiProd> freeFind(CpCiProd obj, int pageIndex, int pageSize) {

        return getPaginatedList("CP_CI_PROD.FreeFindCP_CI_PROD", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<CpCiProd> freeFind(CpCiProd obj, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CpCiProd) {
            _hashmap = ((CpCiProd) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("CP_CI_PROD.FreeFindCP_CI_PRODOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<CpCiProd> freeFind(CpCiProd obj, int pageIndex, int pageSize, String orderByColName) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CpCiProd) {
            _hashmap = ((CpCiProd) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("CP_CI_PROD.FreeFindCP_CI_PRODOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<CpCiProd> objColl) {

        int i = 0;
        if (objColl != null) {
            Iterator<CpCiProd> it = objColl.iterator();
            while (it.hasNext()) {
                CpCiProd oneObj = (CpCiProd) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, CpCiProd vo) {

        CpCiProd obj = (CpCiProd) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, CpCiProd obj) {

        obj.fixup();
        return insert(cti, "CP_CI_PROD.InsertCP_CI_PROD", obj);
    }

    public int update(ContextInfo cti, CpCiProd obj) {

        obj.fixup();
        return update(cti, "CP_CI_PROD.UpdateCP_CI_PROD", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, CpCiProd obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "CP_CI_PROD.DeleteCP_CI_PROD", obj);

    }

    public int removeObjectTree(ContextInfo cti, CpCiProd obj) {

        obj.fixup();
        CpCiProd oldObj = (CpCiProd) queryForObject("CP_CI_PROD.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }

        return delete(cti, "CP_CI_PROD.DeleteCP_CI_PROD", oldObj);

    }

    public boolean exists(CpCiProd vo) {

        boolean keysFilled = true;
        boolean ret = false;
        CpCiProd obj = (CpCiProd) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("CP_CI_PROD.CountCP_CI_PROD", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/09/21 15:45:17

    /**
     * 查询商保产品
     *
     * @return 商保产品列表
     */
    public List<CpCiProdDetail> findCiProd() {

        return queryForList("CP_CI_PROD.findCiProd", new HashMap<>());
    }

    /**
     * 查询商保承保方案列表
     *
     * @param itemIdList 商保编码列表
     * @return 承保方案列表
     */
    public List<CiScheme> findCiScheme(List<Integer> itemIdList) {

        return queryForList("CP_CI_PROD.findCiScheme", itemIdList);
    }


    /**
     * 商保单品查询详情
     *
     * @param id 商保编码
     * @return 商保详情
     */
    public CpCiProdDetail findCiProdById(Integer id) {

        return (CpCiProdDetail) queryForObject("CP_CI_PROD.findCiProdById", id);
    }

    public int addProdSales(ContextInfo cti, Integer prodId, Integer num) {
        Map<String, Object> map = new HashMap<>();
        map.put("ciCode", prodId);
        map.put("add", num);
        return update(cti, "CP_CI_PROD.addProdSales", map);
    }

}
