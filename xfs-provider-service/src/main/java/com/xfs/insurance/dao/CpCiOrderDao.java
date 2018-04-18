package com.xfs.insurance.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CpCiOrderDetail;
import com.xfs.insurance.model.CpCiOrder;

/**
 * CpCiOrderDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 19:00:49
 */
@Repository
public class CpCiOrderDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_CI_ORDER.CountFindAllCP_CI_ORDER", null);
        return ret.intValue();
    }

    public CpCiOrder findByPK(CpCiOrder obj) {
        Object ret = queryForObject("CP_CI_ORDER.FindByPK", obj);
        if (ret != null)
            return (CpCiOrder) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<CpCiOrder> freeFind(CpCiOrder obj) {
        return queryForList("CP_CI_ORDER.FreeFindCP_CI_ORDER", obj);
    }

    public int countFreeFind(CpCiOrder obj) {
        Integer ret = (Integer) queryForObject("CP_CI_ORDER.CountFreeFindCP_CI_ORDER", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<CpCiOrder> freeFind(CpCiOrder obj, int pageIndex, int pageSize) {
        return getPaginatedList("CP_CI_ORDER.FreeFindCP_CI_ORDER", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<CpCiOrder> freeFind(CpCiOrder obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CpCiOrder) {
            _hashmap = ((CpCiOrder) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("CP_CI_ORDER.FreeFindCP_CI_ORDEROrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<CpCiOrder> freeFind(CpCiOrder obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CpCiOrder) {
            _hashmap = ((CpCiOrder) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("CP_CI_ORDER.FreeFindCP_CI_ORDEROrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<CpCiOrder> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<CpCiOrder> it = objColl.iterator();
            while (it.hasNext()) {
                CpCiOrder oneObj = (CpCiOrder) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, CpCiOrder vo) {
        CpCiOrder obj = (CpCiOrder) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, CpCiOrder obj) {

        obj.fixup();
        return insert(cti, "CP_CI_ORDER.InsertCP_CI_ORDER", obj);
    }

    public int update(ContextInfo cti, CpCiOrder obj) {

        obj.fixup();
        return update(cti, "CP_CI_ORDER.UpdateCP_CI_ORDER", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, CpCiOrder obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "CP_CI_ORDER.DeleteCP_CI_ORDER", obj);

    }

    public int removeObjectTree(ContextInfo cti, CpCiOrder obj) {

        obj.fixup();
        CpCiOrder oldObj = (CpCiOrder) queryForObject("CP_CI_ORDER.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "CP_CI_ORDER.DeleteCP_CI_ORDER", oldObj);

    }

    public boolean exists(CpCiOrder vo) {

        boolean keysFilled = true;
        boolean ret = false;
        CpCiOrder obj = (CpCiOrder) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("CP_CI_ORDER.CountCP_CI_ORDER", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/21 19:00:49


    /**
     * 查询商保订单列表
     *
     * @param orderDetail 查询条件
     * @param pageIndex 偏移量
     * @param pageSize 查询数量
     * @return 列表
     */
    public List<CpCiOrderDetail> findCpCiOrderList(CpCiOrderDetail orderDetail, int pageIndex, int pageSize) {
        return getPaginatedList("CP_CI_ORDER.findCpCiOrderList", orderDetail, pageIndex, pageSize);
    }

    /**
     * 查询商保订单总数
     * @param orderDetail 查询条件
     * @return 总数
     */
    public int countCpCiOrders(CpCiOrderDetail orderDetail) {
        Integer ret = (Integer) queryForObject("CP_CI_ORDER.countCpCiOrders", orderDetail);
        return ret.intValue();
    }
    /**
     * 查询商保订单总数
     * 
     * @param orderInfo
     *            查询条件
     * @return 商保订单总数
     */
    public int countCiOrder(CpCiOrder orderInfo) {

        Integer ret = (Integer) queryForObject("CP_CI_ORDER.countCiOrder", orderInfo);
        return ret.intValue();
    }

    /**
     * 查询商保订单列表
     * 
     * @param orderInfo
     *            查询条件
     * @param pageIndex
     *            偏移量
     * @param pageSize
     *            查询条数
     * @return 订单列表
     */
    public List<CpCiOrder> findCiOrders(CpCiOrder orderInfo, int pageIndex, int pageSize) {

        return getPaginatedList("CP_CI_ORDER.findCiOrders", orderInfo, pageIndex, pageSize);
    }

	public CpCiOrder freeOne(CpCiOrder obj) {
		return (CpCiOrder) queryForObject("CP_CI_ORDER.FreeFindCP_CI_ORDER", obj);
	}
}
