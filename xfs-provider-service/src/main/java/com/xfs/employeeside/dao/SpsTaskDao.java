package com.xfs.employeeside.dao;


import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.employeeside.dto.SpsStateDto;
import com.xfs.employeeside.model.SpsTask;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调度任务表Dao 持久层
 *
 * @date 2017/03/13 14:37:25
 */
@Repository
public class SpsTaskDao extends BaseSqlMapDao {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("sps_task.countFindAll", null);
        return ret.intValue();
    }

    /**
     * 根据条件查询
     *
     * @param obj 调度任务表实体
     * @return 调度任务表列表
     */
    public List<SpsTask> freeFind(SpsTask obj) {
        return queryForList("sps_task.freeFind", obj);
    }

    /**
     * 根据条件查询的数量
     *
     * @param obj 调度任务表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(SpsTask obj) {
        Integer ret = (Integer) queryForObject("sps_task.countFreeFind", obj);
        return ret.intValue();
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 调度任务表实体
     * @return 调度任务表实体
     */
    public SpsTask findByPK(SpsTask obj) {
        Object ret = queryForObject("sps_task.findByPK", obj);
        if (ret != null)
            return (SpsTask) ret;
        else
            return null;
    }

    /**
     * 添加对象
     *
     * @param obj 调度任务表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo cti, SpsTask obj) {
        return insert(cti, "sps_task.insert", obj);
    }

    /**
     * 根据主键修改对象
     *
     * @param obj 调度任务表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo cti, SpsTask obj) {
        return update(cti, "sps_task.update", obj);
    }

    /**
     * 根据主键删除对象
     *
     * @param obj 调度任务表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo cti, SpsTask obj) {
        return delete(cti, "sps_task.remove", obj);
    }

    public List<SpsStateDto> getAllStateBy(Integer bsTypeId, Integer areaId) {
        Map obj = new HashMap();
        obj.put("bsTypeId", bsTypeId);
        obj.put("areaId", areaId);
        return queryForList("sps_task.getAllStateBy", obj);
    }


}
