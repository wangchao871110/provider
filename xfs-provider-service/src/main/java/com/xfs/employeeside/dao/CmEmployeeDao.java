package com.xfs.employeeside.dao;


import com.xfs.common.IContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.model.CmEmployee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工表Dao 持久层
 *
 * @date 2017/03/13 14:37:24
 */
@Repository
public class CmEmployeeDao extends BaseSqlMapDao {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("cm_employee.countFindAll", null);
        return ret.intValue();
    }

    /**
     * 根据条件查询
     *
     * @param obj 员工表实体
     * @return 员工表列表
     */
    public List<CmEmployee> freeFind(CmEmployee obj) {
        return queryForList("cm_employee.freeFind", obj);
    }

    /**
     * 根据条件查询的数量
     *
     * @param obj 员工表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(CmEmployee obj) {
        Integer ret = (Integer) queryForObject("cm_employee.countFreeFind", obj);
        return ret.intValue();
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @return 员工表实体
     */
    public CmEmployee findByPK(CmEmployee obj) {
        Object ret = queryForObject("cm_employee.findByPK", obj);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }


    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @return 员工表实体
     */
    public CmEmployee findByFree(CmEmployee obj) {
        Object ret = queryForObject("cm_employee.findByFree", obj);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }

    /**
     * 添加对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int insert(IContextInfo info, CmEmployee obj) {
        return insert(info, "cm_employee.insert", obj);
    }

    /**
     * 根据主键修改对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int update(IContextInfo info, CmEmployee obj) {
        return update(info, "cm_employee.update", obj);
    }

    /**
     * 根据主键删除对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int remove(IContextInfo info, CmEmployee obj) {
        return delete(info, "cm_employee.remove", obj);
    }


    /**
     * 根据 员工id 获取 去id
     *
     * @param empId
     * @return
     */
    public Integer getEmpAreaIdByEmpId(Integer empId) {
        Integer ret = (Integer) queryForObject("cm_employee.getEmpAreaIdByEmpId", empId);
        return ret;
    }


    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @return 员工表实体
     */
    public CmEmployee findByOpenId(String openId) {
        Object ret = queryForObject("cm_employee.findByOpenId", openId);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @retu体
     */
    public CmEmployee findByYrcToken(String yrcToken) {
        Object ret = queryForObject("cm_employee.findByYrcToken", yrcToken);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }

}
