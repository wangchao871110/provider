package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.SpsTaskHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SpsTaskHistoryDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:29:21
 */
@Repository
public class SpsTaskHistoryDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_TASK_HISTORY.CountFindAllSPS_TASK_HISTORY", null);
        return ret.intValue();
    }

    public SpsTaskHistory findByPK(SpsTaskHistory obj) {
        Object ret = queryForObject("SPS_TASK_HISTORY.FindByPK", obj);
        if (ret != null)
            return (SpsTaskHistory) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<SpsTaskHistory> freeFind(SpsTaskHistory obj) {
        return queryForList("SPS_TASK_HISTORY.FreeFindSPS_TASK_HISTORY", obj);
    }

    public int countFreeFind(SpsTaskHistory obj) {
        Integer ret = (Integer) queryForObject("SPS_TASK_HISTORY.CountFreeFindSPS_TASK_HISTORY", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SpsTaskHistory> freeFind(SpsTaskHistory obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_TASK_HISTORY.FreeFindSPS_TASK_HISTORY", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<SpsTaskHistory> freeFind(SpsTaskHistory obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsTaskHistory) {
            _hashmap = ((SpsTaskHistory) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("SPS_TASK_HISTORY.FreeFindSPS_TASK_HISTORYOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<SpsTaskHistory> freeFind(SpsTaskHistory obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsTaskHistory) {
            _hashmap = ((SpsTaskHistory) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("SPS_TASK_HISTORY.FreeFindSPS_TASK_HISTORYOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<SpsTaskHistory> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<SpsTaskHistory> it = objColl.iterator();
            while (it.hasNext()) {
                SpsTaskHistory oneObj = (SpsTaskHistory) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, SpsTaskHistory vo) {
        SpsTaskHistory obj = (SpsTaskHistory) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, SpsTaskHistory obj) {

        obj.fixup();
        return insert(cti, "SPS_TASK_HISTORY.InsertSPS_TASK_HISTORY", obj);
    }

    public int update(ContextInfo cti, SpsTaskHistory obj) {

        obj.fixup();
        return update(cti, "SPS_TASK_HISTORY.UpdateSPS_TASK_HISTORY", obj);

    }
    
    public int updateByTaskId(ContextInfo cti, SpsTaskHistory obj) {
        obj.fixup();
        return update(cti, "SPS_TASK_HISTORY.UpdateSPS_TASK_HISTORY_BY_TASKID", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, SpsTaskHistory obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SPS_TASK_HISTORY.DeleteSPS_TASK_HISTORY", obj);

    }

    public int removeObjectTree(ContextInfo cti, SpsTaskHistory obj) {

        obj.fixup();
        SpsTaskHistory oldObj = (SpsTaskHistory) queryForObject("SPS_TASK_HISTORY.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "SPS_TASK_HISTORY.DeleteSPS_TASK_HISTORY", oldObj);

    }

    public boolean exists(SpsTaskHistory vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SpsTaskHistory obj = (SpsTaskHistory) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("SPS_TASK_HISTORY.CountSPS_TASK_HISTORY", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/08/03 16:29:21

    /**
     * 2016-08 任务管理
     * <p>
     * 任务单详情页面
     * <p>
     * 查看操作历史记录
     * <p>
     * zhangxiyan
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> seehistory(SpsTaskHistory vo) {
        return queryForList("SPS_TASK_HISTORY.FreeFindSPS_TASK_ALL_LIST", vo);
    }

    /**
     * 查看某个任务的历史详细
     *
     * @param obj
     * @return
     */
    public List<Map<String, Object>> findDetailHistory(SpsTaskHistory obj) {
        return queryForList("SPS_TASK_HISTORY.findDetailHistory", obj);
    }

    /**
     * 根据任务单号
     * 查询任务单历史记录最后一条
     * 2016-08
     * zhangxiyan
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> findHisttoryByTaskId(SpsTaskHistory sth) {
        return (HashMap<String, Object>) queryForObject("SPS_TASK_HISTORY.FreeFindSPS_TASK_HistoryList", sth);
    }

    public int queryHistoryCount(ContextInfo cti,Integer stateFiledId) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap.put("cpId",cti.getOrgId());
        _hashmap.put("authority",cti.getAuthority());
        _hashmap.put("stateFiledId",stateFiledId);
        Integer ret = (Integer) queryForObject("SPS_TASK_HISTORY.QUERY_SPS_TASK_HISTORY_COUNT", _hashmap);
        return ret.intValue();
    }

}
