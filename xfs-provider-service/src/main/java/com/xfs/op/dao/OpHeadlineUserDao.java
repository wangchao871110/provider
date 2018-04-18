package com.xfs.op.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.op.model.OpHeadlineUser;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * OpHeadlineUserDao
 *
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
@Repository
public class OpHeadlineUserDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_USER.CountFindAllOP_HEADLINE_USER", null);
        return ret.intValue();
    }

    public OpHeadlineUser findByPK(OpHeadlineUser obj) {
        Object ret = queryForObject("OP_HEADLINE_USER.FindByPK", obj);
        if (ret != null)
            return (OpHeadlineUser) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<OpHeadlineUser> freeFind(OpHeadlineUser obj) {
        return queryForList("OP_HEADLINE_USER.FreeFindOP_HEADLINE_USER", obj);
    }

    public int countFreeFind(OpHeadlineUser obj) {
        Integer ret = (Integer) queryForObject("OP_HEADLINE_USER.CountFreeFindOP_HEADLINE_USER", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<OpHeadlineUser> freeFind(OpHeadlineUser obj, int pageIndex, int pageSize) {
        return getPaginatedList("OP_HEADLINE_USER.FreeFindOP_HEADLINE_USER", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<OpHeadlineUser> freeFind(OpHeadlineUser obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof OpHeadlineUser) {
            _hashmap = ((OpHeadlineUser) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("OP_HEADLINE_USER.FreeFindOP_HEADLINE_USEROrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<OpHeadlineUser> freeFind(OpHeadlineUser obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof OpHeadlineUser) {
            _hashmap = ((OpHeadlineUser) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("OP_HEADLINE_USER.FreeFindOP_HEADLINE_USEROrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<OpHeadlineUser> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<OpHeadlineUser> it = objColl.iterator();
            while (it.hasNext()) {
                OpHeadlineUser oneObj = (OpHeadlineUser) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, OpHeadlineUser vo) {
        OpHeadlineUser obj = (OpHeadlineUser) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, OpHeadlineUser obj) {

        obj.fixup();
        return insert(cti, "OP_HEADLINE_USER.InsertOP_HEADLINE_USER", obj);
    }

    public int update(ContextInfo cti, OpHeadlineUser obj) {

        obj.fixup();
        return update(cti, "OP_HEADLINE_USER.UpdateOP_HEADLINE_USER", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, OpHeadlineUser obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "OP_HEADLINE_USER.DeleteOP_HEADLINE_USER", obj);

    }

    public int removeObjectTree(ContextInfo cti, OpHeadlineUser obj) {

        obj.fixup();
        OpHeadlineUser oldObj = (OpHeadlineUser) queryForObject("OP_HEADLINE_USER.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "OP_HEADLINE_USER.DeleteOP_HEADLINE_USER", oldObj);

    }

    public boolean exists(OpHeadlineUser vo) {

        boolean keysFilled = true;
        boolean ret = false;
        OpHeadlineUser obj = (OpHeadlineUser) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("OP_HEADLINE_USER.CountOP_HEADLINE_USER", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/02/22 10:59:03

    /**
     * 通过openid查询用户
     *
     * @param obj
     * @return
     */
    public OpHeadlineUser getHeadlineUserByOpenId(OpHeadlineUser obj) {
        Object ret = queryForObject("OP_HEADLINE_USER.GetHeadlineUserByOpenId", obj);
        if (ret != null)
            return (OpHeadlineUser) ret;
        else
            return null;
    }


    /**
     * 根据openId 更新用户信息
     *
     * @param obj
     * @return 返回更新后的用户信息
     */
    public int updateHeadlineUserByOpenId(ContextInfo cti, OpHeadlineUser obj) {
        return update(cti, "OP_HEADLINE_USER.updateHeadlineUserByOpenId", obj);
    }
}
