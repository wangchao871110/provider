package com.xfs.employeeside.service;

import com.xfs.common.Result;

/**
 * 在职服务
 *
 * @author hongjie
 * @date 2017/4/7.17:03
 */
public interface InJobService {

    /**
     * 根据 业务类型 和 员工id  获取需要的字段名称
     *
     * @param empId      员工id
     * @param bsTypeName 业务类型名称
     * @return
     */
    public Result getNeedFieldByBsTypeName(Integer empId, String bsTypeName);

    public Result getWorkGuide(Integer empId, String bsTypeName);




}
