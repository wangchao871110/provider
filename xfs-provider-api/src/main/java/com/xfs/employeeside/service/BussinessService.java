package com.xfs.employeeside.service;


import com.xfs.common.Result;

/**
 * @author hongjie
 * @date 2017/3/15.17:02
 */
public interface BussinessService {

    /**
     *  获取所有的业务
     * @param empId
     * @return
     */
    Result getAllBusiness(Integer empId);

    /**
     * 获取所有的 正在进行的 业务
     * @param empId
     * @return
     */
    Result getAllTodo(Integer empId);

    /**
     * 获取某个业务的 详细情况
     * @param taskId
     * @return
     */
    public  Result getBusinessDetailByTaskId(Integer taskId);


    public Result getBusinessHistoryByTaskId(Integer taskId);

}
