package com.xfs.employeeside.service;

import java.util.List;

import com.xfs.base.model.SysMessage;
import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.employeeside.model.CmEmployeeAudit;

/**
 * CmEmployeeAuditService 服务层接口
 *
 * @date 2017/03/20 10:34:21
 */
public interface CmEmployeeAuditService {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll();

    /**
     * 根据条件查询
     *
     * @param obj 待审核员工表实体
     * @return 待审核员工表列表
     */
    public List<CmEmployeeAudit> freeFind(CmEmployeeAudit obj);

    /**
     * 根据条件查询的数量
     *
     * @param obj 待审核员工表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(CmEmployeeAudit obj);

    /**
     * 根据主键查询对象
     *
     * @param obj 待审核员工表实体
     * @return 待审核员工表实体
     */
    public CmEmployeeAudit findByPK(CmEmployeeAudit obj);

    /**
     * 添加对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo info, CmEmployeeAudit obj);

    /**
     * 根据主键修改对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo info, CmEmployeeAudit obj);

    /**
     * 根据主键删除对象
     *
     * @param obj 待审核员工表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo info, CmEmployeeAudit obj);


    public CmEmployeeAudit getCmEmployeeAuditByOpenId(String openId);


    public CmEmployeeAudit getCmEmployeeAuditByLastFour(CmEmployeeAudit obj);


    public SysMessage sendMessage(ContextInfo cti, CmEmployeeAudit obj);


    public int updateOpenIdByOpenId(IContextInfo cti, CmEmployeeAudit obj);


}
