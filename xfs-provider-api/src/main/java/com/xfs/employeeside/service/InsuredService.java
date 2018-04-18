package com.xfs.employeeside.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;

import java.util.List;

/**
 * 信息变更服务层接口
 *
 * @author hongjie
 * @date 2017/3/17.9:34
 */
public interface InsuredService {
    /**
     * 创建修改医院的任务单
     *
     * @param cti
     * @param empId
     * @param hosptialCodes
     * @return
     */
    Result saveHosptial(ContextInfo cti, Integer empId, List<String> hosptialCodes);

    /**
     * 创建修改 电话号码的任务单
     *
     * @param cti
     * @param empId
     * @param mobile
     * @return
     */
    Result changeMobile(ContextInfo cti, Integer empId, String mobile);

    /**
     * 根据城市 编码， 获取该该城市的下的所有的医院
     *
     * @param areaCode
     * @return
     */
    Result getHospitalByAreaId(Integer areaCode);

    /**
     * 根据员工id  获取员工所在城市的区信息
     *
     * @param empId
     * @return
     */
    Result getRegion(Integer empId);

    /**
     *  根据地区id 获取 城市的区信息
     * @param areaId
     * @return
     */
     Result getRegionByAreaId(Integer areaId);

}
