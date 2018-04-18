package com.xfs.employeeside.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.employeeside.model.CmEmployeeAttach;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 员工附件表Dao 持久层
 *
 * @date 2017/03/17 11:35:04
 */
@Repository
public class CmEmployeeAttachDao extends BaseSqlMapDao {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("cm_employee_attach.countFindAll", null);
        return ret.intValue();
    }

    /**
     * 根据条件查询
     *
     * @param obj 员工附件表实体
     * @return 员工附件表列表
     */
    public List<CmEmployeeAttach> freeFind(CmEmployeeAttach obj) {
        return queryForList("cm_employee_attach.freeFind", obj);
    }

    /**
     * 根据条件查询的数量
     *
     * @param obj 员工附件表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(CmEmployeeAttach obj) {
        Integer ret = (Integer) queryForObject("cm_employee_attach.countFreeFind", obj);
        return ret.intValue();
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 员工附件表实体
     * @return 员工附件表实体
     */
    public CmEmployeeAttach findByPK(CmEmployeeAttach obj) {
        Object ret = queryForObject("cm_employee_attach.findByPK", obj);
        if (ret != null)
            return (CmEmployeeAttach) ret;
        else
            return null;
    }

    /**
     * 添加对象
     *
     * @param obj 员工附件表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo info, CmEmployeeAttach obj) {
        return insert(info, "cm_employee_attach.insert", obj);
    }

    /**
     * 根据主键修改对象
     *
     * @param obj 员工附件表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo info, CmEmployeeAttach obj) {
        return update(info, "cm_employee_attach.update", obj);
    }

    /**
     * 根据主键删除对象
     *
     * @param obj 员工附件表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo info, CmEmployeeAttach obj) {
        return delete(info, "cm_employee_attach.remove", obj);
    }


}
