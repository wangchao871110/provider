package com.xfs.employeeside.service;/**
 * @author hongjie
 * @date 2017/3/15.11:33
 */

import com.xfs.common.Result;

/**
 * 社保服务接口
 *
 * @author
 * @create 2017-03-15 11:33
 **/
public interface FundAndInsuranceService {


    /**
     * 查询公积金 概要
     *
     * @param empId
     * @return
     */
    Result queryFund(Integer empId);



    /**
     * 查询公积金 概要
     *
     * @param empId
     * @return
     */
    Result queryLastFundAndInsurance(Integer empId);

    /**
     * 查询 某一个月公积金详细
     *
     * @param empId
     * @param year
     * @param month
     * @return
     */
    Result getFundDetailByMonth(Integer empId, String year, String month);

    /**
     * 查询社保 概要
     *
     * @param empId
     * @return
     */
    Result queryInsurance(Integer empId);


    /**
     * 查询 某一个月社保详细
     *
     * @param empId
     * @param year
     * @param month
     * @return
     */
    Result getInsuranceDetailByMonth(Integer empId, String year, String month);


}
