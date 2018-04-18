package com.xfs.insurance.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.SpsCiAgentPrice;

/**
 * SpsCiAgentPriceDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/07 09:14:06
 */
@Repository
public class SpsCiAgentPriceDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_CI_AGENT_PRICE.CountFindAllSPS_CI_AGENT_PRICE", null);
        return ret.intValue();
    }

    public SpsCiAgentPrice findByPK(SpsCiAgentPrice obj) {
        Object ret = queryForObject("SPS_CI_AGENT_PRICE.FindByPK", obj);
        if (ret != null)
            return (SpsCiAgentPrice) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<SpsCiAgentPrice> freeFind(SpsCiAgentPrice obj) {
        return queryForList("SPS_CI_AGENT_PRICE.FreeFindSPS_CI_AGENT_PRICE", obj);
    }

    public int countFreeFind(SpsCiAgentPrice obj) {
        Integer ret = (Integer) queryForObject("SPS_CI_AGENT_PRICE.CountFreeFindSPS_CI_AGENT_PRICE", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SpsCiAgentPrice> freeFind(SpsCiAgentPrice obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_CI_AGENT_PRICE.FreeFindSPS_CI_AGENT_PRICE", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<SpsCiAgentPrice> freeFind(SpsCiAgentPrice obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsCiAgentPrice) {
            _hashmap = ((SpsCiAgentPrice) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("SPS_CI_AGENT_PRICE.FreeFindSPS_CI_AGENT_PRICEOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<SpsCiAgentPrice> freeFind(SpsCiAgentPrice obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsCiAgentPrice) {
            _hashmap = ((SpsCiAgentPrice) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("SPS_CI_AGENT_PRICE.FreeFindSPS_CI_AGENT_PRICEOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<SpsCiAgentPrice> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<SpsCiAgentPrice> it = objColl.iterator();
            while (it.hasNext()) {
                SpsCiAgentPrice oneObj = (SpsCiAgentPrice) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, SpsCiAgentPrice vo) {
        SpsCiAgentPrice obj = (SpsCiAgentPrice) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, SpsCiAgentPrice obj) {

        obj.fixup();
        return insert(cti, "SPS_CI_AGENT_PRICE.InsertSPS_CI_AGENT_PRICE", obj);
    }

    public int update(ContextInfo cti, SpsCiAgentPrice obj) {

        obj.fixup();
        return update(cti, "SPS_CI_AGENT_PRICE.UpdateSPS_CI_AGENT_PRICE", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, SpsCiAgentPrice obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SPS_CI_AGENT_PRICE.DeleteSPS_CI_AGENT_PRICE", obj);

    }

    public int removeObjectTree(ContextInfo cti, SpsCiAgentPrice obj) {

        obj.fixup();
        SpsCiAgentPrice oldObj = (SpsCiAgentPrice) queryForObject("SPS_CI_AGENT_PRICE.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "SPS_CI_AGENT_PRICE.DeleteSPS_CI_AGENT_PRICE", oldObj);

    }

    public boolean exists(SpsCiAgentPrice vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SpsCiAgentPrice obj = (SpsCiAgentPrice) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("SPS_CI_AGENT_PRICE.CountSPS_CI_AGENT_PRICE", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/07 09:14:06

    /**
     * 更具商品编码和服务项编码查询商保承保价格信息
     *
     * @param product 商品信息
     * @return 方案类表
     */
    public List<CiScheme> findCiItemPrice(CiProduct product) {
        return queryForList("SPS_CI_AGENT_PRICE.findCiItemPrice", product);
    }


}
