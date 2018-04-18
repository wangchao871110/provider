package com.xfs.employeeside.dao;


import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.employeeside.model.CmEmployeeAudit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 待审核员工表Dao 持久层
 *
 * @date 2017/03/20 10:34:21
 */
@Repository
public class CmEmployeeAuditDao extends BaseSqlMapDao {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("cm_employee_audit.countFindAll", null);
        return ret.intValue();
    }

    /**
     * 根据条件查询
     *
     * @param obj 待审核员工表实体
     * @return 待审核员工表列表
     */
    public List<CmEmployeeAudit> freeFind(CmEmployeeAudit obj) {
        return queryForList("cm_employee_audit.freeFind", obj);
    }

    /**
     * 根据条件查询的数量
     *
     * @param obj 待审核员工表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(CmEmployeeAudit obj) {
        Integer ret = (Integer) queryForObject("cm_employee_audit.countFreeFind", obj);
        return ret.intValue();
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 待审核员工表实体
     * @return 待审核员工表实体
     */
    public CmEmployeeAudit findByPK(CmEmployeeAudit obj) {
        Object ret = queryForObject("cm_employee_audit.findByPK", obj);
        if (ret != null)
            return (CmEmployeeAudit) ret;
        else
            return null;
    }

    /**
     * 添加对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo info, CmEmployeeAudit obj) {
        return insert(info, "cm_employee_audit.insert", obj);
    }

    /**
     * 根据主键修改对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo info, CmEmployeeAudit obj) {
        return update(info, "cm_employee_audit.update", obj);
    }

    /**
     * 根据主键删除对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo info, CmEmployeeAudit obj) {
        return delete(info, "cm_employee_audit.remove", obj);
    }


    public  CmEmployeeAudit getCmEmployeeAuditByOpenId(String openId){
        Object ret = queryForObject("cm_employee_audit.getCmEmployeeAuditByOpenId", openId);
        if (ret != null)
            return (CmEmployeeAudit) ret;
        else
            return null;
    }


    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @return 员工表实体
     */
    public CmEmployeeAudit findByFree(CmEmployeeAudit obj) {
        Object ret = queryForObject("cm_employee_audit.findByFree", obj);
        if (ret != null)
            return (CmEmployeeAudit) ret;
        else
            return null;
    }

    public  int updateOpenIdByOpenId(IContextInfo cti, CmEmployeeAudit obj){
        return update(cti, "cm_employee_audit.updateOpenIdByOpenId", obj);
    }



}
